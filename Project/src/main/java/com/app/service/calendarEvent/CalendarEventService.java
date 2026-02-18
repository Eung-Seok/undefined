package com.app.service.calendarEvent;

import java.util.List;

import com.app.dto.calendarEvent.CalendarEvent;

public interface CalendarEventService {
	List<CalendarEvent> findCalendarEventList();

	int saveCalendarEvent(CalendarEvent calendarEvent);

	CalendarEvent findCalendarEventById(int id);

	int removeCalendarEvent(int id);

	int modifyCalendarEvent(CalendarEvent calendarEvent);
}
