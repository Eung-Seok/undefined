<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>대시보드</title>
<link rel="stylesheet" href="/css/dashboard/dashboard.css">
<link rel="stylesheet" href="/css/common/sidebar.css">
</head>
<body>
	<div class="app">
		<c:set var="role" value="${sessionScope.loginUserRole}" />
		<c:set var="canManageMembers"
			value="${role == 'ADMIN' || role == 'PM'}" />
		<c:set var="notManageMembers" value="${role == 'MEMBER'}" />
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="dashboard" />
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

			<!-- KPI 영역 -->
			<div class="grid cols-4">
				<div class="card">
					<div class="kpi">
						<div>
							<div class="label">진행중인 프로젝트</div>
							<c:if test="${canManageMembers}">
								<div class="value">${activeProjectCount}</div>
							</c:if>
							<c:if test="${notManageMembers}">
								<div class="value">${count3}</div>
							</c:if>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="kpi">
						<div>
							<div class="label">진행중인 업무</div>
							<div class="value">${count4}</div>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="kpi">
						<div>
							<div class="label">지연 업무</div>
							<div class="value">${delayedTaskCount}</div>
						</div>
					</div>
				</div>
			</div>

			<div style="height: 14px"></div>

			<!-- 프로젝트 현황 -->
			<div class="grid cols-2">
				<div class="card">
					<h3>프로젝트 현황</h3>
					<table class="table">
						<thead>
							<tr>
								<th>프로젝트</th>
								<th>PM</th>
								<th>참여인원</th>
								<th>잔여업무</th>
								<th>마감</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${canManageMembers}">
								<c:forEach var="p" items="${projectList}">
									<tr class="clickable-row"
										data-href="${pageContext.request.contextPath}/project/overview?projectId=${p.id}">
										<td>${p.name}</td>
										<td>${userName[p.ownerUserId]}</td>
										<td>${projectMemberCount[p.id]}</td>
										<td>${projectTaskCount[p.id]}</td>
										<td><fmt:formatDate value="${p.endDate}" pattern="MM/dd" /></td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${notManageMembers}">
								<c:forEach var="p" items="${userProjectList}">
									<tr class="clickable-row"
										data-href="${pageContext.request.contextPath}/project/overview?projectId=${p.id}">
										<td>${p.name}</td>
										<td>${userName[p.ownerUserId]}</td>
										<td>${projectMemberCount[p.id]}</td>
										<td>${projectTaskCount[p.id]}</td>
										<td><fmt:formatDate value="${p.endDate}" pattern="MM/dd" /></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>

				<!-- 오늘 할 일 (여전히 하드코딩 가능, 나중에 DB 연결 가능) -->
				<div class="card">
					<h3>잔여 업무</h3>
					<div style="height: 12px"></div>
					<table class="table">
						<thead>
							<tr>
								<th>업무</th>
								<th>상태</th>
								<th>우선순위</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${canManageMembers}">
								<c:forEach var="task" items="${taskList}">
									<tr class="clickable-row"
										data-href="${pageContext.request.contextPath}/project/tasks/view?projectId=${task.projectId}&taskId=${task.id}">
										<td>${task.name}</td>
										<td><span
											class="badge ${task.status == 'READY' ? 'ready' : 
          task.status == 'PROGRESS' ? 'progress' : 
          task.status == 'DONE' ? 'done' : ''}">
												${task.status == 'READY' ? '대기' : 
          task.status == 'PROGRESS' ? '진행중' : 
          task.status == 'DONE' ? '완료' : ''}</span></td>
										<td><span
											class="badge 
    ${task.priority == 'HIGHT' ? 'high' :
      task.priority == 'MEDIUMN' ? 'medium' :
      task.priority == 'LOW' ? 'low' : ''}">

												${task.priority == 'HIGHT' ? '상' :
      task.priority == 'MEDIUMN' ? '중' :
      task.priority == 'LOW' ? '하' : task.priority}
										</span></td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${notManageMembers}">
								<c:forEach var="task" items="${userTaskList}">
									<tr class="clickable-row"
										data-href="${pageContext.request.contextPath}/project/tasks/view?projectId=${task.projectId}&taskId=${task.id}">
										<td>${task.name}</td>
										<td><span
											class="badge ${task.status == 'READY' ? 'ready' : 
          task.status == 'PROGRESS' ? 'progress' : 
          task.status == 'DONE' ? 'done' : ''}">
												${task.status == 'READY' ? '대기' : 
          task.status == 'PROGRESS' ? '진행중' : 
          task.status == 'DONE' ? '완료' : ''}</span></td>
										<td><span
											class="badge 
    ${task.priority == 'HIGHT' ? 'high' :
      task.priority == 'MEDIUMN' ? 'medium' :
      task.priority == 'LOW' ? 'low' : ''}">

												${task.priority == 'HIGHT' ? '상' :
      task.priority == 'MEDIUMN' ? '중' :
      task.priority == 'LOW' ? '하' : task.priority}
										</span></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>

		</main>
	</div>
	<script src="/js/dashboard/dashboard.js"></script>
</body>
</html>