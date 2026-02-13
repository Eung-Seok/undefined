package com.app.dto.report;

import lombok.Data;

@Data
public class Report {
	int id;
	int projectId;
	String periodType;
	String periodStart;
	String periodEnd;
	String summary;
	String createdAt;
}
