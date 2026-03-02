package com.app.service.task.impl;

import java.time.ZoneId;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.task.TaskDAO;
import com.app.dao.taskAssignee.TaskAssigneeDAO;
import com.app.dto.calendarEvent.CalendarEvent;
import com.app.dto.task.Task;
import com.app.dto.taskAssignee.TaskAssignee;
import com.app.service.api.GoogleCalendarService;
import com.app.service.calendarEvent.CalendarEventService;
import com.app.service.task.TaskService;
import com.app.service.taskAssignee.TaskAssigneeService;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskDAO taskDao;
	
	@Autowired
	private TaskAssigneeDAO taskAssigneeDao;
	
	@Autowired
	private GoogleCalendarService googleCalendarService;
	
	@Autowired
	private CalendarEventService calendarEventService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	TaskAssigneeService taskAssigneeService;

	@Override
	public List<Task> findTaskList() {
		List<Task> taskList = taskDao.findTaskList();
		return taskList;
	}

	@Override
	public int saveTask(Task task) {
		int result = taskDao.saveTask(task);
		return result;
	}

	@Override
	public Task findTaskById(int id) {
		Task task = taskDao.findTaskById(id);
		return task;
	}

	@Override
	public int removeTask(int id) {
		int result = taskDao.removeTask(id);
		return result;
	}

	@Override
	public int modifyTask(Task task) {
		int result = taskDao.modifyTask(task);
		return result;
	}
	

	@Override
	public List<Task> findTaskListByProjectId(int porjectId) {
		List<Task> taskList = taskDao.findTaskListByProjectId(porjectId);
		return taskList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveTaskWithGoogleSync(int projectId, Task task, int loginUserId) throws Exception {

		// [Step 1] 업무 기본 데이터 저장
		task.setProjectId(projectId);
		taskDao.saveTask(task); // task.id 생성됨 (useGeneratedKeys 필수)

		// [Step 2] 담당자 할당 정보 저장
		TaskAssignee taskAssignee = new TaskAssignee();
		taskAssignee.setTaskId(task.getId());
		taskAssignee.setUserId(task.getOwnerUserId());
		taskAssignee.setStatus("ONGOING");
		taskAssigneeDao.saveTaskAssignee(taskAssignee);

		// [Step 3] 구글 캘린더 전송용 DTO 생성
		CalendarEvent ce = new CalendarEvent();
		ce.setUserId(task.getOwnerUserId()); // 담당자의 캘린더에 등록
		ce.setName(task.getName());
		ce.setTaskId(task.getId()); // 어떤 업무인지 ID 매핑

		// java.sql.Date -> LocalDateTime 변환
		if (task.getStartDate() != null) {
			ce.setStartDate(task.getStartDate().toLocalDate().atStartOfDay());
		}
		if (task.getDueDate() != null) {
			ce.setEndDate(task.getDueDate().toLocalDate().atTime(23, 59, 59));
		}
		ce.setType("TASK");

		// [Step 4] 구글 캘린더 API 호출 (Write-Through)
		// 구글에 데이터를 보내고, 그 즉시 구글이 발급한 ID가 포함된 Event 객체를 받습니다.
		Event googleEvent = googleCalendarService.insertEvent(ce);

		// [Step 5] 구글에서 받은 ID(eId)를 CALENDAREVENT 테이블에 함께 저장하여 연동 완료
		ce.setEId(googleEvent.getId());
		ce.setETag(googleEvent.getEtag());

		// 이미 구현된 upsert 또는 save 메서드를 활용하여 TF_CALENDAR_EVENT에 기록
		calendarEventService.upsertCalendarEvent(ce);
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTaskWithGoogleSync(int taskId) throws Exception {
        // 1. 구글 동기화 정보 조회 (삭제 전 데이터 확보)
        CalendarEvent event = calendarEventService.findCalendarEventByTaskId(taskId);
        String eId = event.getEId();
        // 2. 구글 캘린더에서 삭제
        if (event != null && event.getEId() != null) {
            try {
                googleCalendarService.deleteEvent(eId);
        		calendarEventService.deleteCalendarEventByEId(eId);
            } catch (Exception e) {
                // 구글 삭제 실패가 전체 로직을 중단시키지 않도록 로그만 남김
                System.err.println("Google API Delete Error: " + e.getMessage());
            }
        }

        // 4. 담당자 할당 정보(TaskAssignee) 삭제
        taskAssigneeDao.removeTaskAssigneeByTaskId(taskId);

        // 5. 실제 업무(Task) 삭제
        taskDao.removeTask(taskId);
    }
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void modifyTaskWithGoogleSync(Task task, int taskId) throws Exception {
	    // 1. 업무 기본 정보 수정 (Task 테이블)
	    taskService.modifyTask(task);

	    // 2. 담당자 변경 처리 (기존 로직 유지: 없으면 추가)
	    List<TaskAssignee> taList = taskAssigneeService.findTaskAssigneeListByTaskId(taskId);
	    boolean isAlreadyAssignee = false;
	    for (TaskAssignee ta : taList) {
	        if (ta.getUserId() == task.getOwnerUserId()) {
	            isAlreadyAssignee = true;
	            break;
	        }
	    }

	    if (!isAlreadyAssignee) {
	        TaskAssignee taskAssignee = new TaskAssignee();
	        taskAssignee.setTaskId(taskId);
	        taskAssignee.setUserId(task.getOwnerUserId());
	        taskAssignee.setStatus("ONGOING");
	        taskAssigneeService.saveTaskAssignee(taskAssignee);
	    }

	    // 3. 구글 캘린더 동기화 (기존 eId 기준 내용 수정)
	    CalendarEvent ce = calendarEventService.findCalendarEventByTaskId(taskId);
	    if (ce != null && ce.getEId() != null) {
	        // 수정된 내용 반영
	        ce.setName(task.getName());
	        
	        // 날짜 변환 (시작일 00:00:00 / 종료일 23:59:59)
	        if (task.getStartDate() != null) {
	            ce.setStartDate(task.getStartDate().toLocalDate().atStartOfDay());
	        }
	        if (task.getDueDate() != null) {
	            ce.setEndDate(task.getDueDate().toLocalDate().atTime(23, 59, 59));
	        }

	        try {
	            // 구글 서버 업데이트 호출
	        	Event updatedEvent = googleCalendarService.updateEvent(ce);
	        	if (updatedEvent != null) {
	                ce.setETag(updatedEvent.getEtag());
	                ce.setName(updatedEvent.getSummary());
	                if (updatedEvent.getStart() != null) {
	                    DateTime startDt = updatedEvent.getStart().getDateTime();
	                    if (startDt == null) { // 종일 일정인 경우 처리
	                        startDt = updatedEvent.getStart().getDate();
	                    }
	                    ce.setStartDate(new java.util.Date(startDt.getValue())
	                                     .toInstant()
	                                     .atZone(ZoneId.systemDefault())
	                                     .toLocalDateTime());
	                }
	                
	                // 종료 날짜 추출 및 변환
	                if (updatedEvent.getEnd() != null) {
	                    DateTime endDt = updatedEvent.getEnd().getDateTime();
	                    if (endDt == null) {
	                        endDt = updatedEvent.getEnd().getDate();
	                    }
	                    ce.setEndDate(new java.util.Date(endDt.getValue())
	                                   .toInstant()
	                                   .atZone(ZoneId.systemDefault())
	                                   .toLocalDateTime());
	                }
	            }
	            // 내 DB의 연동 정보(TF_CALENDAR_EVENT) 최신화 (날짜, 제목 등)
	            calendarEventService.upsertCalendarEvent(ce);
	        } catch (Exception e) {
	            System.err.println("구글 일정 업데이트 실패 (업무 ID: " + taskId + "): " + e.getMessage());
	        }
	    }
	}
}
