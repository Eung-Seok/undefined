package com.app.dao.calendarEvent;

import java.util.List;

import com.app.dto.calendarEvent.CalendarEvent;

public interface CalendarEventDAO {
	List<CalendarEvent> findCalendarEventListByUserId(int userId);

	int saveCalendarEvent(CalendarEvent calendarEvent);

	CalendarEvent findCalendarEventByEId(String eId);

	int removeCalendarEvent(int id);

	int modifyCalendarEvent(CalendarEvent calendarEvent);
	
	int upsertCalendarEvent(CalendarEvent calendarEvent);
	
	int deleteRemovedEvents(List<String> eIdList);
}
