<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>문서</title>
<style>
</style>
<link rel="stylesheet" href="/css/project/docs.css">
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
					캘린더</a><a
					class="tab active"
					href="${pageContext.request.contextPath}/project/docs?projectId=${project.id}">문서</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/members?projectId=${project.id}">참여자</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/settings?projectId=${project.id}">설정</a>
			</div>


			<div class="card">
				<h3>문서</h3>
				<div class="small">요구사항/회의록/설계서 업로드(데모)</div>
				<div style="height: 12px"></div>
				<button class="btn" data-action="문서 업로드">문서 업로드</button>
				<div style="height: 12px"></div>
				<table class="table">
					<thead>
						<tr>
							<th>문서명</th>
							<th>분류</th>
							<th>업로드</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>요구사항_v1.pdf</td>
							<td>요구사항</td>
							<td>2026-02-12</td>
						</tr>
						<tr>
							<td>회의록_0212.md</td>
							<td>회의록</td>
							<td>2026-02-12</td>
						</tr>
					</tbody>
				</table>
			</div>

		</main>
	</div>
	<script src="/js/project/docs.js"></script>
	<script>
		
	</script>
</body>
</html>
