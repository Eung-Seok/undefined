package com.app.service.task.impl;

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
}
