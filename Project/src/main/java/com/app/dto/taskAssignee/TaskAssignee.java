package com.app.dto.taskAssignee;

import lombok.Data;

@Data
public class TaskAssignee {
	int id;
	int taskId;
	int userId;
	String status;
	String joinedAt;
}
