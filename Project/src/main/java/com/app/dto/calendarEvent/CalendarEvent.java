package com.app.dto.calendarEvent;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CalendarEvent {
	int id;
	int userId;
	Integer projectId;
	Integer taskId;
	String name;
	LocalDateTime startDate;
	LocalDateTime endDate;
	String type;
	String createdAt;
	String updatedAt;
}
