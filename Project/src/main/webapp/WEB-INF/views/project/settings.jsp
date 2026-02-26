<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>프로젝트 설정</title>
<style>
</style>
<link rel="stylesheet" href="/css/project/settings.css">
<link rel="stylesheet" href="/css/common/sidebar.css">
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
					href="${pageContext.request.contextPath}/project/overview?projectId=${project.id}">
					개요 </a> <a class="tab"
					href="${pageContext.request.contextPath}/project/tasks?projectId=${project.id}">업무</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/calendar?projectId=${project.id}">프로젝트
					캘린더</a><a class="tab"
					href="${pageContext.request.contextPath}/project/wbs?projectId=${project.id}">WBS</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/docs?projectId=${project.id}">문서</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/members?projectId=${project.id}">참여자</a><a
					class="tab active"
					href="${pageContext.request.contextPath}/project/settings?projectId=${project.id}">설정</a>
			</div>


			<div class="card">
				<h3>프로젝트 설정</h3>
				<div class="small">PM/ADMIN만 접근(데모)</div>
				<div style="height: 12px"></div>
				<div class="notice" data-requires="PM,ADMIN">설정 변경은 데모이며, 서버단
					권한 체크를 꼭 추가하세요.</div>
				<div style="height: 12px"></div>
				<button class="btn primary" data-requires="PM,ADMIN"
					data-action="설정 저장">저장</button>
			</div>

		</main>
	</div>
	<script src="/js/project/settings.js"></script>
	<script>
		
	</script>
</body>
</html>
