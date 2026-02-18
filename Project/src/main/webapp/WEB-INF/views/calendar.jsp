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
<body data-role="${sessionScope.loginUser.role}">
	<div class="app">
		<aside class="sidebar">
			<div class="brand">
				<div class="logo">P</div>
				<div>
					<div class="brand-title">프로젝트 통합 일정/업무 관리</div>
					<div class="brand-sub">Project Scheduler · JSP Demo UI</div>
				</div>
			</div>

			<div class="profile">
				<div class="avatar">👤</div>
				<div>
					<div class="profile-name">
						<c:out value="${sessionScope.loginUser.name}" />
					</div>
					<div class="profile-role">
						<c:out value="${sessionScope.loginUser.position}" />
						·
						<c:out value="${sessionScope.loginUser.role}" />
					</div>
				</div>
			</div>

			<div class="nav">
				<div class="section">Main</div>
				<a class="" href="${pageContext.request.contextPath}/dashboard"><span>🏠</span>대시보드</a>
				<a class="" href="${pageContext.request.contextPath}/projects"><span>📁</span>프로젝트</a>
				<a class="active" href="${pageContext.request.contextPath}/calendar"><span>🗓️</span>일정</a>
				<a class="" href="${pageContext.request.contextPath}/board"><span>📝</span>게시판</a>
				<a class="" href="${pageContext.request.contextPath}/employees"><span>👥</span>직원정보</a>
				<a class="" href="${pageContext.request.contextPath}/mypage"><span>⚙️</span>마이페이지</a>

				<div class="section">Admin</div>
				<a data-requires="ADMIN"
					href="${pageContext.request.contextPath}/admin/users">👮 사용자 관리</a>
				<a data-requires="ADMIN"
					href="${pageContext.request.contextPath}/admin/roles">🔐 권한 관리</a>
				<a data-requires="ADMIN"
					href="${pageContext.request.contextPath}/admin/org">🏢 조직도 관리</a> <a
					data-requires="ADMIN"
					href="${pageContext.request.contextPath}/admin/system">🧰 시스템</a>
			</div>

			<hr class="line">
			<div class="small">※ 데모 UI: 권한별 숨김은 프론트 처리입니다(보안X)</div>
		</aside>

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
