<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>일정</title>
<style>
</style>
<link rel="stylesheet" href="/css/calendar/calendar.css">
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js'></script>
</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="calendar" />
		</jsp:include>

		<main class="main">
			<div class="topbar">
				<div class="search">
					🔎 <input placeholder="프로젝트, 업무, 사용자 검색(데모)" />
				</div>
				<div class="actions">
					<button class="btn" data-action="알림">🔔</button>
					<button class="btn primary" data-action="빠른 생성">＋</button>
				</div>
			</div>




			<div class="grid cols-2">
				<div class="card">
					<h3>월간 캘린더(데모)</h3>
					<div class="small">실제 캘린더는 FullCalendar 등으로 교체 가능</div>
					<div style="height: 12px"></div>
					<table class="table">
						<thead>
							<tr>
								<th>날짜</th>
								<th>일정</th>
								<th>프로젝트</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>2/13</td>
								<td>킥오프 회의</td>
								<td>신규 웹사이트 구축</td>
							</tr>
							<tr>
								<td>2/15</td>
								<td>API 연동 점검</td>
								<td>ERP 연동</td>
							</tr>
							<tr>
								<td>2/18</td>
								<td>디자인 리뷰</td>
								<td>모바일 앱 개선</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="card">
					<h3>외부 캘린더 연동</h3>
					<div class="notice">Google/Outlook 연동은 API 키 설정 후 구현하세요.</div>
					<div style="height: 12px"></div>
					<!-- 					<iframe -->
					<!-- 						src="https://calendar.google.com/calendar/embed?src=108bdeccc6e1caae59585e7281d2214533594d143d16892320714d854208503d%40group.calendar.google.com&ctz=Asia%2FSeoul" -->
					<!-- 						style="border: 0" width="400" height="300" frameborder="0" -->
					<!-- 						scrolling="no"></iframe> -->
					<div id='calendar-container'
						style="background: white; padding: 10px; border-radius: 8px;">
						<div id='google-calendar'></div>
					</div>
					<button class="btn" data-action="Google Calendar 연결">Google
						Calendar 연결</button>
					<button class="btn" data-action="Outlook Calendar 연결"
						style="margin-left: 8px">Outlook Calendar 연결</button>
				</div>
			</div>

		</main>
	</div>
	<script src="/js/calendar/calendar.js"></script>
	<script>
		document
				.addEventListener(
						'DOMContentLoaded',
						function() {
							var calendarEl = document
									.getElementById('google-calendar');

							var calendar = new FullCalendar.Calendar(
									calendarEl,
									{
										initialView : 'dayGridMonth', // 월간 보기
										locale : 'ko', // 한국어 설정
										headerToolbar : {
											left : 'prev,next',
											center : 'title',
											right : 'today'
										},
										height : 450, // 카드 크기에 맞춰 조절

										// 핵심: 우리가 만든 Spring Controller 주소에서 데이터를 가져옵니다.
										events : '${pageContext.request.contextPath}/api/calendar/events',

										eventClick : function(info) {
											alert('일정: ' + info.event.title);
										},
										eventColor : '#4285F4', // 구글 브랜드 색상
										loading : function(isLoading) {
											if (isLoading) {
												console.log("구글 일정 로딩 중...");
											}
										}
									});

							calendar.render();
						});
	</script>
</body>
</html>
