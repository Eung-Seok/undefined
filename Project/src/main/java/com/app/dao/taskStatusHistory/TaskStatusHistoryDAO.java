package com.app.dao.taskStatusHistory;

import java.util.List;

import com.app.dto.taskStatusHistory.TaskStatusHistory;

public interface TaskStatusHistoryDAO {
	List<TaskStatusHistory> findTaskStatusHistoryList();
}
