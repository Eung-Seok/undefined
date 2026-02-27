<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>🗓 주간 캘린더</title>
    <!-- FullCalendar CSS & JS -->
    <link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js"></script>

    <style>
        /* 카드형 달력 크기 조정 */
        .calendar-card {
            max-width: 600px;   /* 폭 제한 */
            margin: 0 auto;     /* 가운데 정렬 */
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 10px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }
        #calendar {
            height: 400px;      /* 높이 제한 */
        }
    </style>
</head>
<body>
    <div class="calendar-card">
        <h3>🗓 이번 주 일정</h3>
        <div id="calendar"></div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'timeGridWeek', // ✅ 주간 뷰
                locale: 'ko',                // 한국어
                contentHeight: 350,          // 높이 줄이기
                aspectRatio: 1.5,            // 가로/세로 비율 조정
                headerToolbar: {             // 헤더 최소화
                    left: 'prev,next',
                    center: 'title',
                    right: ''
                },
                events: [
                    <c:forEach var="event" items="${weekEvents}">
                    {
                        title: '${event.name}',
                        start: '${event.startDate}',
                        end: '${event.endDate}',
                        description: '${event.type}'
                    },
                    </c:forEach>
                ]
            });
            calendar.render();
        });
    </script>
</body>
</html>