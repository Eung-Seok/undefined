package com.app.service.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.calendarEvent.CalendarEvent;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

@Service
public class GoogleCalendarService {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "D:/study/undefined/Project/tokens";
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static final String CALENDAR_ID = "108bdeccc6e1caae59585e7281d2214533594d143d16892320714d854208503d@group.calendar.google.com";

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream in = GoogleCalendarService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public List<Event> getUpcomingEvents() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName("Calendar")
                .build();

        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list(CALENDAR_ID)
                .setMaxResults(20)
//                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        return events.getItems();
    }
    
    public Event insertEvent(CalendarEvent ce) throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName("Calendar")
                .build();

        // 1. Google Event 객체 생성
        Event event = new Event()
                .setSummary(ce.getName()) // 일정 제목
                .setDescription("업무 관리 시스템 자동 등록 일정");

        // 2. 시작 시간 설정 (LocalDateTime -> DateTime)
        long startMillis = ce.getStartDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        DateTime startDateTime = new DateTime(startMillis);
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Asia/Seoul");
        event.setStart(start);

        // 3. 종료 시간 설정 (LocalDateTime -> DateTime)
        long endMillis = ce.getEndDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        DateTime endDateTime = new DateTime(endMillis);
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Asia/Seoul");
        event.setEnd(end);

        // 4. 구글로 전송 및 결과 반환
        return service.events().insert(CALENDAR_ID, event).execute();
    }
    
    public Event updateEvent(CalendarEvent ce) throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName("Calendar")
                .build();
        
        String eId = ce.getEId();
        // 1. 기존 이벤트 가져오기
        Event event = service.events().get(CALENDAR_ID, eId).execute();

        // 2. 내용 변경
        event.setSummary(ce.getName());
        event.setDescription("업무 관리 시스템에서 수정됨");

        // 시작 시간 설정
        long startMillis = ce.getStartDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        event.setStart(new EventDateTime()
                .setDateTime(new DateTime(startMillis))
                .setTimeZone("Asia/Seoul"));

        // 종료 시간 설정
        long endMillis = ce.getEndDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        event.setEnd(new EventDateTime()
                .setDateTime(new DateTime(endMillis))
                .setTimeZone("Asia/Seoul"));

        // 3. 구글로 업데이트 전송
		return service.events().update(CALENDAR_ID, eId, event).execute();
	}
    
    public void deleteEvent(String eventId) throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName("Calendar")
                .build();

        // 구글 서버에서 삭제 실행
        service.events().delete(CALENDAR_ID, eventId).execute();
    }
}