package com.app.dto.report;

import java.sql.Date;

import lombok.Data;

@Data
public class Report {
	int id;
	int projectId;
	int authorUserId;
	String periodType;
	Date periodStart;
	Date periodEnd;
	String summary;
	String issue;
	Date createdAt;
}
