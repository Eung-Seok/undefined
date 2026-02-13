package com.app.dto.task;

import lombok.Data;

@Data
public class Task {
	int id;
	int projectId;
	int parentTaskId;
	String name;
	String description;
	String status;
	String priority;
	String startDate;
	String dueDate;
	int progressPercent;
	String createdAt;
	String updatedAt;
}
