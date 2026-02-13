package com.app.dto.projectMember;

import lombok.Data;

@Data
public class ProjectMember {
	int id;
	int projectId;
	int userId;
	String projectRole;
	String status;
	String joinedAt;
	String createdAt;
}
