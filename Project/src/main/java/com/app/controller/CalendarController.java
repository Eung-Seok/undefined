package com.app.controller;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.calendarEvent.CalendarEvent;
import com.app.service.api.GoogleCalendarService;
import com.app.service.calendarEvent.CalendarEventService;
import com.google.api.services.calendar.model.Event;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

	@Autowired
	private GoogleCalendarService googleCalendarService;
	@Autowired
	private CalendarEventService calendarEventService;

	@GetMapping("/events")
	public List<Map<String, Object>> getEvents() {
		List<Map<String, Object>> resultList = new ArrayList<>();
		try {
			List<Event> items = googleCalendarService.getUpcomingEvents();
			int userId = 1234; // 고정된 사용자 ID 예시

			for (Event event : items) {
				Map<String, Object> eventMap = new HashMap<>();
				String summary = event.getSummary();
				String eId = event.getId();
				String eTag = event.getEtag();
				// 1. Google DateTime을 String으로 안전하게 추출 (null 체크 포함)
				String startStr = (event.getStart().getDateTime() != null) ? event.getStart().getDateTime().toString()
						: event.getStart().getDate().toString() + "T00:00:00Z";

				String endStr = (event.getEnd().getDateTime() != null) ? event.getEnd().getDateTime().toString()
						: event.getEnd().getDate().toString() + "T23:59:59Z"; // 종일 일정은 그날 끝까지

				// 2. OffsetDateTime으로 파싱 후 LocalDateTime으로 변환
				LocalDateTime startLdt = OffsetDateTime.parse(startStr).toLocalDateTime();
				LocalDateTime endLdt = OffsetDateTime.parse(endStr).toLocalDateTime();

//				System.out.println(startStr);
//				System.out.println(endStr);
//				System.out.println(startLdt);
//				System.out.println(endLdt);
				System.out.println(event.getId());
				System.out.println(event.getEtag());
				
				eventMap.put("title", summary);
				eventMap.put("start", startStr);
				eventMap.put("end", endStr);
//				eventMap.put("googleEventId", eventId);
				resultList.add(eventMap);

			     CalendarEvent existing = calendarEventService.findCalendarEventByEId(eId);
			     if (existing != null && eTag.equals(existing.getETag())) {
			         continue;
			     } // 수정사항 없으면 DB 저장 X
				CalendarEvent ce = new CalendarEvent();
				ce.setUserId(1234);
				ce.setName(summary);
				ce.setStartDate(startLdt);
				ce.setEndDate(endLdt);
				ce.setType("TASK");
				ce.setEId(eId);
				ce.setETag(eTag);

				calendarEventService.upsertCalendarEvent(ce);
				System.out.println("새 일정 저장 완료: " + summary);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
}