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
<title>프로젝트 개요</title>
<style>
</style>
<link rel="stylesheet" href="/css/project/overview.css">
</head>
<body data-role="${sessionScope.loginUser.role}">
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
				<a class="tab active"
					href="${pageContext.request.contextPath}/project/overview">개요</a><a
					class="tab" href="${pageContext.request.contextPath}/project/tasks">업무</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/calendar">프로젝트
					캘린더</a><a class="tab"
					href="${pageContext.request.contextPath}/project/wbs">WBS</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/issues">이슈</a><a
					class="tab" href="${pageContext.request.contextPath}/project/docs">문서</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/members">참여자</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/settings">설정</a>
			</div>


			<div class="card">
				<h3>프로젝트 개요</h3>
				<div class="small">예시 프로젝트: 신규 웹사이트 구축</div>
				<div style="height: 12px"></div>
				<div class="grid cols-2">
					<div class="card" style="box-shadow: none">
						<h3>진척률</h3>
						<div class="kpi">
							<div>
								<div class="label">완료</div>
								<div class="value">49%</div>
							</div>
							<span class="badge good">정상</span>
						</div>
						<div style="height: 10px"></div>
						<div
							style="height: 10px; background: #e5e7eb; border-radius: 999px; overflow: hidden">
							<div style="width: 49%; height: 100%; background: var(--pri)"></div>
						</div>
					</div>
					<div class="card" style="box-shadow: none">
						<h3>핵심 지표</h3>
						<div class="small">잔여 업무 24 · 이슈 3 · 참여 7명</div>
						<div style="height: 12px"></div>
						<button class="btn primary" data-action="프로젝트 보고서">보고서 생성</button>
					</div>
				</div>
			</div>

		</main>
	</div>
	<script src="/js/project/overview.js"></script>
	<script>
		
	</script>
</body>
</html>
