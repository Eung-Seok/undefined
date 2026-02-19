<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
// Demo session user (Map) - replace with real login later
if (session.getAttribute("loginUser") == null) {
	java.util.Map<String, Object> u = new java.util.HashMap<>();
	u.put("name", "홍길동");
	u.put("position", "사원");
	u.put("role", "MEMBER"); // ADMIN / PM / MEMBER / VIEWER
	session.setAttribute("loginUser", u);
}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>일정</title>
<style>
</style>
<link rel="stylesheet" href="/css/calendar/calendar.css">
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
		
	</script>
</body>
</html>
