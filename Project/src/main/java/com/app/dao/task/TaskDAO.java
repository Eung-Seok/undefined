package com.app.dao.task;

import java.util.List;

import com.app.dto.task.Task;

public interface TaskDAO {
	List<Task> findTaskList();
}
