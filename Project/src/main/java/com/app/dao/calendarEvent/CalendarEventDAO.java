package com.app.dao.calendarEvent;

import java.util.List;

import com.app.dto.calendarEvent.CalendarEvent;

public interface CalendarEventDAO {
	List<CalendarEvent> findCalendarEventListByUserId(int userId);
	
	CalendarEvent findCalendarEventByTaskId(int taskId);

	CalendarEvent findCalendarEventByEId(String eId);

	int saveCalendarEvent(CalendarEvent calendarEvent);

	int deleteCalendarEventByEId(String eId);

	int modifyCalendarEvent(CalendarEvent calendarEvent);
	
	int upsertCalendarEvent(CalendarEvent calendarEvent);
	
	int deleteRemovedEvents(List<String> eIdList);
}
