package com.app.service.calendarEvent.impl;

import java.util.List;

import com.app.dto.calendarEvent.CalendarEvent;

public interface CalendarEventService {
	List<CalendarEvent> findCalendarEventList();
}
