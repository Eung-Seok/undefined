package com.app.dao.calendarEvent.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.calendarEvent.CalendarEventDAO;
import com.app.dto.calendarEvent.CalendarEvent;

@Repository
public class CalendarEventDAOImpl implements CalendarEventDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<CalendarEvent> findCalendarEventList() {
		List<CalendarEvent> commentList = sqlSessionTemplate.selectList("calendarEvent_mapper.findCalendarEventList");
		return commentList;
	}
	
}
