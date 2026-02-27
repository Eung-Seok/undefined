package com.app.service.task;

import java.util.List;

import com.app.dto.task.Task;

public interface TaskService {
	List<Task> findTaskList();
	
	List<Task> findTaskListByProjectId(int porjectId);

	int saveTask(Task task);

	Task findTaskById(int id);

	int removeTask(int id);

	int modifyTask(Task task);

	// 오늘 할 일 전용
	List<Task> getTodayTask(int empno);
	// 상태 변경 추가
    int updateTaskStatus(int taskId, String status);
    //프로젝트 공정율 계산
    double calculateProjectProgress(int projectId);
    //작업 완료 처리
    void completeTask(int taskId);
  //프로젝트 공정 진행률 업데이트 기능
    int updateProjectProgress(int projectId);
}
