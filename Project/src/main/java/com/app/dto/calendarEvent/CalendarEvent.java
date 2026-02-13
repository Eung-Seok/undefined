package com.app.dto.calendarEvent;

import lombok.Data;

@Data
public class CalendarEvent {
	int id;
	int userId;
	int projectId;
	int taskId;
	String name;
	String startDate;
	String endDate;
	String type;
	String createdAt;
	String updatedAt;
}
