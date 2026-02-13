package com.app.dto.project;

import lombok.Data;

@Data
public class Project {
	int id;
	int ownerUserId;
	String name;
	String description;
	String startDate;
	String endDate;
	String status;
	String createdAt;
	String updatedAt;
}
