package com.app.dto.project;

import java.sql.Date;

import lombok.Data;

@Data
public class Project {
	int id;
	int ownerUserId;
	String name;
	String description;
	Date startDate;
	Date endDate;
	String status;
	Date createdAt;
	Date updatedAt;
}
