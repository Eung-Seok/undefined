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
	public List<CalendarEvent> findCalendarEventListByUserId(int userId) {
		List<CalendarEvent> calendarEventList = sqlSessionTemplate.selectList("calendarEvent_mapper.findCalendarEventListByUserId", userId);
		return calendarEventList;
	}

	@Override
	public int saveCalendarEvent(CalendarEvent calendarEvent) {
		int result = sqlSessionTemplate.insert("calendarEvent_mapper.saveCalendarEvent", calendarEvent);
		return result;
	}

	@Override
	public List<CalendarEvent> findCalendarEventByTaskId(int taskId) {
		List<CalendarEvent> calendarEvent = sqlSessionTemplate.selectList("calendarEvent_mapper.findCalendarEventByTaskId", taskId);
		return calendarEvent;
	}
	
	@Override
	public CalendarEvent findCalendarEventByEId(String eId) {
		CalendarEvent calendarEvent = sqlSessionTemplate.selectOne("calendarEvent_mapper.findCalendarEventByEId", eId);
		return calendarEvent;
	}

	@Override
	public int deleteCalendarEventByEId(String eId) {
		int result = sqlSessionTemplate.delete("calendarEvent_mapper.deleteCalendarEventByEId",eId);
		return result;
	}

	@Override
	public int modifyCalendarEvent(CalendarEvent calendarEvent) {
		int result = sqlSessionTemplate.update("calendarEvent_mapper.modifyCalendarEvent", calendarEvent);
		return result;
	}


	@Override
	public int upsertCalendarEvent(CalendarEvent calendarEvent) {
		int result = sqlSessionTemplate.update("calendarEvent_mapper.upsertCalendarEvent", calendarEvent);
		return result;
	}

	@Override
	public int deleteRemovedEvents(List<String> eIdList) {
		int result = sqlSessionTemplate.delete("calendarEvent_mapper.deleteRemovedEvents", eIdList);
		return result;
	}

}
