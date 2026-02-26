package com.app.dao.task;

import java.util.List;

import com.app.dto.task.Task;

public interface TaskDAO {
	List<Task> findTaskList();
	
	List<Task> findTaskListByProjectId(int porjectId);
	
	int saveTask(Task task);

	Task findTaskById(int id);

	int removeTask(int id);

	int modifyTask(Task task);
}
