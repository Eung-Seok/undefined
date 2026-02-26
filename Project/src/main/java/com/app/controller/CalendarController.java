package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.calendarEvent.CalendarEvent;
import com.app.dto.user.User;
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
	public List<CalendarEvent> getAllEvents(HttpSession session) {
		List<CalendarEvent> resultList = new ArrayList<CalendarEvent>();
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			System.out.println("로그인 정보가 없습니다.");
			return resultList;
		}
		resultList = calendarEventService.findCalendarEventListByUserId(loginUser.getEmpno());

		return resultList;
	}

	@PostMapping("/sync")
	public ResponseEntity<Map<String, Object>> syncEvents(HttpSession session) {
		Map<String, Object> response = new HashMap<>();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) {
			response.put("message", "로그인 정보가 없습니다.");
			return ResponseEntity.status(401).body(response);
		}

		try {
			List<Event> googleEventList = googleCalendarService.getUpcomingEvents();
			calendarEventService.syncWithGoogle(loginUser.getEmpno(), googleEventList);

			response.put("status", "success");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "error");
			response.put("message", e.getMessage());
			return ResponseEntity.status(500).body(response);
		}
	}
}