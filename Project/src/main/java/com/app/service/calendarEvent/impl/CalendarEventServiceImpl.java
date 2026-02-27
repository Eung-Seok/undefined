package com.app.service.calendarEvent.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.calendarEvent.CalendarEventDAO;
import com.app.dto.calendarEvent.CalendarEvent;
import com.app.service.calendarEvent.CalendarEventService;

@Service
public class CalendarEventServiceImpl implements CalendarEventService{

	@Autowired
	CalendarEventDAO calendarEventDao;

	@Override
	public List<CalendarEvent> findCalendarEventList() {
		List<CalendarEvent> calendarEventList = calendarEventDao.findCalendarEventList();
		return calendarEventList;
	}

	@Override
	public int saveCalendarEvent(CalendarEvent calendarEvent) {
		int result = calendarEventDao.saveCalendarEvent(calendarEvent);
		return result;
	}

	@Override
	public CalendarEvent findCalendarEventByEId(String eId) {
		CalendarEvent calendarEvent = calendarEventDao.findCalendarEventByEId(eId);
		return calendarEvent;
	}

	@Override
	public int removeCalendarEvent(int id) {
		int result = calendarEventDao.removeCalendarEvent(id);
		return result;
	}

	@Override
    public int modifyCalendarEvent(CalendarEvent calendarEvent) {
        return calendarEventDao.modifyCalendarEvent(calendarEvent);
    }

	@Override
	public List<CalendarEvent> getWeekCalendarEvents(int userId) {
	    return calendarEventDao.findWeekCalendarEvents(userId);
	}

	@Override
	public int upsertCalendarEvent(CalendarEvent calendarEvent) {
		int result = calendarEventDao.upsertCalendarEvent(calendarEvent);
		return result;
	}
}