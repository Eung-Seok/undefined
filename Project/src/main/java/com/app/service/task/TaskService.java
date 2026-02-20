package com.app.service.task;

import java.util.List;

import com.app.dto.task.Task;

public interface TaskService {
	List<Task> findTaskList();

	int saveTask(Task task);

	Task findTaskById(int id);

	int removeTask(int id);

	int modifyTask(Task task);

	// 오늘 할 일 전용
	List<Task> getTodayTask(int empno);
}
