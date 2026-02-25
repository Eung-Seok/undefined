<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>프로젝트 업무</title>
<style>
</style>
<link rel="stylesheet" href="/css/project/tasks.css">
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
					개요 </a> <a class="tab active"
					href="${pageContext.request.contextPath}/project/tasks?projectId=${project.id}">업무</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/calendar?projectId=${project.id}">프로젝트
					캘린더</a><a class="tab"
					href="${pageContext.request.contextPath}/project/wbs?projectId=${project.id}">WBS</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/issues?projectId=${project.id}">이슈</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/docs?projectId=${project.id}">문서</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/members?projectId=${project.id}">참여자</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/settings?projectId=${project.id}">설정</a>
			</div>


			<div class="card">
				<h3>업무(Task)</h3>
				<div class="small">업무는 프로젝트 내부에 포함됩니다(요구사항 반영)</div>
				<div style="height: 12px"></div>
				<div
					style="display: flex; gap: 10px; justify-content: space-between; align-items: center">
					<div class="small">총 6건</div>
					<button class="btn primary" data-requires="PM,ADMIN"
						data-action="업무 생성">업무 생성</button>
				</div>
				<div style="height: 12px"></div>
				<table class="table">
					<thead>
						<tr>
							<th>업무</th>
							<th>담당</th>
							<th>상태</th>
							<th>마감</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>요구사항 정리</td>
							<td>홍길동</td>
							<td><span class="badge good">진행중</span></td>
							<td>2/13</td>
						</tr>
						<tr>
							<td>WBS 초안 작성</td>
							<td>김PM</td>
							<td><span class="badge warn">대기</span></td>
							<td>2/15</td>
						</tr>
						<tr>
							<td>디자인 리뷰</td>
							<td>디자이너</td>
							<td><span class="badge">완료</span></td>
							<td>2/11</td>
						</tr>
					</tbody>
				</table>
			</div>

		</main>
	</div>
	<script src="/js/project/tasks.js"></script>
	<script>
		
	</script>
</body>
</html>
