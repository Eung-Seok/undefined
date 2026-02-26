package com.app.dto.task;

import java.sql.Date;

import lombok.Data;

@Data
public class Task {
	int id;
	int projectId;
	int ownerUserId;
	Integer parentTaskId;
	String name;
	String description;
	String status;
	String priority;
	Date startDate;
	Date dueDate;
	Integer progressPercent;
	Date createdAt;
	Date updatedAt;
}
