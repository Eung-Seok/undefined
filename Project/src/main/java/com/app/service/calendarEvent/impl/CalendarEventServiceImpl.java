package com.app.service.calendarEvent.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.calendarEvent.CalendarEventDAO;
import com.app.dto.calendarEvent.CalendarEvent;

@Service
public class CalendarEventServiceImpl implements CalendarEventService{

	@Autowired
	CalendarEventDAO calendarEventDao;

	@Override
	public List<CalendarEvent> findCalendarEventList() {
		List<CalendarEvent> calendarEventList = calendarEventDao.findCalendarEventList();
		return calendarEventList;
	}
}