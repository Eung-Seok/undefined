package com.app.controller;

import com.app.service.api.GoogleCalendarService;
import com.google.api.services.calendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

    @Autowired
    private GoogleCalendarService googleCalendarService;

    @GetMapping("/events")
    public List<Map<String, Object>> getEvents() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try {
            List<Event> items = googleCalendarService.getUpcomingEvents();
            
            for (Event event : items) {
                Map<String, Object> eventMap = new HashMap<>();
                eventMap.put("title", event.getSummary());
                
                // FullCalendar 형식에 맞게 시작/종료 시간 설정
                String start = (event.getStart().getDateTime() != null) 
                               ? event.getStart().getDateTime().toString() 
                               : event.getStart().getDate().toString();
                String end = (event.getEnd().getDateTime() != null) 
                             ? event.getEnd().getDateTime().toString() 
                             : event.getEnd().getDate().toString();
                
                eventMap.put("start", start);
                eventMap.put("end", end);
                resultList.add(eventMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}