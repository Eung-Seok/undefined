package com.app.service.calendarEvent;

import java.util.List;

import com.app.dto.calendarEvent.CalendarEvent;
import com.google.api.services.calendar.model.Event;

public interface CalendarEventService {
	List<CalendarEvent> findCalendarEventListByUserId(int userId);

	List<CalendarEvent> findCalendarEventByTaskId(int taskId);
	
	CalendarEvent findCalendarEventByEId(String eId);
	
	int saveCalendarEvent(CalendarEvent calendarEvent);

	int deleteCalendarEventByEId(String eId);

	int modifyCalendarEvent(CalendarEvent calendarEvent);
	
	int upsertCalendarEvent(CalendarEvent calendarEvent);
	
	int deleteRemovedEvents(List<String> eIdList);
	
	void syncWithGoogle(int userId, List<Event> googleEvents);
}

