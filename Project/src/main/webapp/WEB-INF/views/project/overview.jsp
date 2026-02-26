<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>프로젝트 개요</title>
<style>
</style>
<link rel="stylesheet" href="/css/project/overview.css">
<link rel="stylesheet" href="/css/common/sidebar.css">
</head>
<body>
	<div class="app">
		<c:set var="role" value="${sessionScope.loginUserRole}" />
		<c:set var="canManageMembers"
			value="${role == 'ADMIN' || role == 'PM'}" />
		<c:set var="notManageMembers" value="${role == 'MEMBER'}" />
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
					href="${pageContext.request.contextPath}/project/overview?projectId=${project.id}">
					개요 </a> <a class="tab"
					href="${pageContext.request.contextPath}/project/tasks?projectId=${project.id}">업무</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/calendar?projectId=${project.id}">프로젝트
					캘린더</a><a class="tab"
					href="${pageContext.request.contextPath}/project/docs?projectId=${project.id}">문서</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/members?projectId=${project.id}">참여자</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/report?projectId=${project.id}">보고서</a>
				<c:if test="${canManageMembers}">
					<a class="tab"
						href="${pageContext.request.contextPath}/project/settings?projectId=${project.id}">설정</a>
				</c:if>
			</div>


			<div class="card">
				<h3>프로젝트 개요</h3>
				<div class="small">${project.name}</div>
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
						<button class="btn primary"
							onclick="location.href='${pageContext.request.contextPath}/project/report/write?projectId=${project.id}'">
							보고서 생성</button>

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
