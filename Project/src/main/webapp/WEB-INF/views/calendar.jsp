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
<link rel="stylesheet" href="/css/common/sidebar.css">
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js'></script>
</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="calendar" />
		</jsp:include>

		<main class="main">
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
						<tbody id="calendar-data-body">
						</tbody>
					</table>
				</div>
				<div class="card">
					<h3>외부 캘린더 연동</h3>
					<div style="height: 12px"></div>
					<div id='calendar-container'
						style="background: white; padding: 10px; border-radius: 8px;">
						<div id='google-calendar'></div>
					</div>
				</div>
			</div>
		</main>
	</div>
	<script>
		window.contextPath = '${pageContext.request.contextPath}';
	</script>
	<script src="/js/calendar/calendar.js"></script>
</body>
</html>
