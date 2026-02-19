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
<title>참여자</title>
<style>
</style>
<link rel="stylesheet" href="/css/project/members.css">
</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="projects" />
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

			<div class="tabs">
				<a class="tab"
					href="${pageContext.request.contextPath}/project/overview">개요</a><a
					class="tab" href="${pageContext.request.contextPath}/project/tasks">업무</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/calendar">프로젝트
					캘린더</a><a class="tab"
					href="${pageContext.request.contextPath}/project/wbs">WBS</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/issues">이슈</a><a
					class="tab" href="${pageContext.request.contextPath}/project/docs">문서</a><a
					class="tab active"
					href="${pageContext.request.contextPath}/project/members">참여자</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/settings">설정</a>
			</div>


			<div class="card">
				<h3>참여자</h3>
				<div class="small">PM/ADMIN만 관리 가능(데모)</div>
				<div style="height: 12px"></div>
				<button class="btn primary" data-requires="PM,ADMIN"
					data-action="참여자 초대">참여자 초대</button>
				<div style="height: 12px"></div>
				<table class="table">
					<thead>
						<tr>
							<th>이름</th>
							<th>역할</th>
							<th>권한</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>김PM</td>
							<td>PM</td>
							<td><span class="badge good">PM</span></td>
						</tr>
						<tr>
							<td>홍길동</td>
							<td>개발</td>
							<td><span class="badge">MEMBER</span></td>
						</tr>
					</tbody>
				</table>
			</div>

		</main>
	</div>
	<script src="/js/project/members.js"></script>
	<script>
		
	</script>
</body>
</html>
