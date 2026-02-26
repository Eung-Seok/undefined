<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>프로젝트</title>
<style>
</style>
<link rel="stylesheet" href="/css/projects/projects.css">
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




			<div class="card">
				<div
					style="display: flex; gap: 10px; justify-content: space-between; align-items: center">
					<h3>프로젝트 목록</h3>
					<c:if test="${canManageMembers}">
						<a class="btn primary"
							href="${pageContext.request.contextPath}/project/create">프로젝트
							생성</a>
					</c:if>
				</div>


				<div style="height: 12px"></div>
				<table class="table">
					<thead>
						<tr>
							<th>프로젝트</th>
							<th>상태</th>
							<th>PM</th>
							<th>기간</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${notManageMembers}">
							<c:forEach var="project" items="${projectList}">
								<tr>
									<td>${project.name}</td>
									<td><span
										class="badge ${project.status == 'READY' ? 'ready' : 
          project.status == 'PROGRESS' ? 'progress' : 
          project.status == 'DONE' ? 'done' : ''}">${project.status == 'READY' ? '대기' : 
          project.status == 'PROGRESS' ? '진행중' : 
          project.status == 'DONE' ? '완료' : ''}</span></td>
									<td>${userNameMap[project.ownerUserId]}</td>
									<td>${project.startDate}~${project.endDate}</td>
									<td><a class="btn"
										href="${pageContext.request.contextPath}/project/overview?projectId=${project.id}">열기</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${canManageMembers}">
							<c:forEach var="project" items="${allProjectList}">
								<tr>
									<td>${project.name}</td>
									<td><span
										class="badge ${project.status == 'READY' ? 'ready' : 
          project.status == 'PROGRESS' ? 'progress' : 
          project.status == 'DONE' ? 'done' : ''}">${project.status == 'READY' ? '대기' : 
          project.status == 'PROGRESS' ? '진행중' : 
          project.status == 'DONE' ? '완료' : ''}</span></td>
									<td>${userNameMap[project.ownerUserId]}</td>
									<td>${project.startDate}~${project.endDate}</td>
									<td><a class="btn"
										href="${pageContext.request.contextPath}/project/overview?projectId=${project.id}">열기</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<div style="height: 12px"></div>
			</div>

		</main>
	</div>
	<script src="/js/projects/projects.js"></script>
	<script>
		
	</script>
</body>
</html>
