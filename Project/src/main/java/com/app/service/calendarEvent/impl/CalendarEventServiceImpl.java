package com.app.service.calendarEvent.impl;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.calendarEvent.CalendarEventDAO;
import com.app.dto.calendarEvent.CalendarEvent;
import com.app.dto.user.User;
import com.app.service.calendarEvent.CalendarEventService;
import com.google.api.services.calendar.model.Event;

@Service
public class CalendarEventServiceImpl implements CalendarEventService {

	@Autowired
	CalendarEventDAO calendarEventDao;

	@Override
	public List<CalendarEvent> findCalendarEventListByUserId(int userId) {
		List<CalendarEvent> calendarEventList = calendarEventDao.findCalendarEventListByUserId(userId);
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
		int result = calendarEventDao.modifyCalendarEvent(calendarEvent);
		return result;
	}

	@Override
	public int upsertCalendarEvent(CalendarEvent calendarEvent) {
		int result = calendarEventDao.upsertCalendarEvent(calendarEvent);
		return result;
	}

	@Override
	public int deleteRemovedEvents(List<String> eIdList) {
		int result = calendarEventDao.deleteRemovedEvents(eIdList);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void syncWithGoogle(int userId, List<Event> googleEventList) {
		if (googleEventList == null) return;
		
		List<String> eIdList = new ArrayList<>();

		for (Event event : googleEventList) {
			String summary = event.getSummary();
			String eId = event.getId();
			String eTag = event.getEtag();
			eIdList.add(eId);
			
			// 1. Google DateTime을 String으로 안전하게 추출 (null 체크 포함)
			String startStr = (event.getStart().getDateTime() != null) ? event.getStart().getDateTime().toString()
					: event.getStart().getDate().toString() + "T00:00:00Z";

			String endStr = (event.getEnd().getDateTime() != null) ? event.getEnd().getDateTime().toString()
					: event.getEnd().getDate().toString() + "T23:59:59Z"; // 종일 일정은 그날 끝까지

			// 2. OffsetDateTime으로 파싱 후 LocalDateTime으로 변환
			LocalDateTime startLdt = OffsetDateTime.parse(startStr).toLocalDateTime();
			LocalDateTime endLdt = OffsetDateTime.parse(endStr).toLocalDateTime();

			System.out.println(event.getId());
			System.out.println(event.getEtag());

			CalendarEvent existing = calendarEventDao.findCalendarEventByEId(eId);
			if (existing != null && eTag.equals(existing.getETag())) {
				continue;
			} // 수정사항 없으면 DB 저장 X
			CalendarEvent ce = new CalendarEvent();
			ce.setUserId(userId);
			ce.setName(summary);
			ce.setStartDate(startLdt);
			ce.setEndDate(endLdt);
			ce.setType("Test");
			ce.setEId(eId);
			ce.setETag(eTag);

			calendarEventDao.upsertCalendarEvent(ce);
			System.out.println("일정 수정/저장 완료: " + summary);
		}
		if (!eIdList.isEmpty()) {
			int deletedCount = calendarEventDao.deleteRemovedEvents(eIdList);
			if (deletedCount > 0) {
				System.out.println("동기화 완료: 구글에서 삭제된 일정 " + deletedCount + "건을 DB에서도 삭제했습니다.");
			}
		}
	}

}