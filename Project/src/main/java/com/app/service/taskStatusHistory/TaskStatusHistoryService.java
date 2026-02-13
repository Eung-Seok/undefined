package com.app.service.taskStatusHistory;

import java.util.List;

import com.app.dto.taskStatusHistory.TaskStatusHistory;

public interface TaskStatusHistoryService {
	List<TaskStatusHistory> findTaskStatusHistoryList();
}
