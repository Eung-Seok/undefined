package com.app.dao.calendarEvent;

import java.util.List;

import com.app.dto.calendarEvent.CalendarEvent;

public interface CalendarEventDAO {
	List<CalendarEvent> findCalendarEventList();

	int saveCalendarEvent(CalendarEvent calendarEvent);

	CalendarEvent findCalendarEventById(int id);

	int removeCalendarEvent(int id);

	int modifyCalendarEvent(CalendarEvent calendarEvent);
	// ✅ 주간 일정 조회 추가
    List<CalendarEvent> findWeekCalendarEvents(int userId);

}
