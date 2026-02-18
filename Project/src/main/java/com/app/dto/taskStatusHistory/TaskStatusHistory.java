package com.app.dto.taskStatusHistory;

import lombok.Data;

@Data
public class TaskStatusHistory {
	int id;
	int taskId;
	String fromStatus;
	String toStatus;
	int changedBy;
	String changedAt;
	String memo;
}
