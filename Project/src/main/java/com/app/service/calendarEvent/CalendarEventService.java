package com.app.service.calendarEvent;

import java.util.List;

import com.app.dto.calendarEvent.CalendarEvent;

public interface CalendarEventService {
	List<CalendarEvent> findCalendarEventList();

	int saveCalendarEvent(CalendarEvent calendarEvent);

	CalendarEvent findCalendarEventByEId(String eId);

	int removeCalendarEvent(int id);

	// ✅ 주간 일정 조회 추가
    List<CalendarEvent> getWeekCalendarEvents(int userId);
    // ✅ 인터페이스에 추가
    int modifyCalendarEvent(CalendarEvent calendarEvent);

	int modifyCalendarEvent(CalendarEvent calendarEvent);
	
	int upsertCalendarEvent(CalendarEvent calendarEvent);
}

