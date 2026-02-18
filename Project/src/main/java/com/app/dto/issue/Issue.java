package com.app.dto.issue;

import lombok.Data;

@Data
public class Issue {
	int id;
	int projectId;
	Integer taskId;
	int reporterUserId;
	String title;
	String content;
	String status;
	String createdAt;
	String updatedAt;
}
