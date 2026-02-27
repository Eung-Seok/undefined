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
				<a class="tab"
					href="${pageContext.request.contextPath}/project/overview?projectId=${project.id}">
					개요 </a> <a class="tab active"
					href="${pageContext.request.contextPath}/project/tasks?projectId=${project.id}">업무</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/calendar?projectId=${project.id}">프로젝트
					캘린더</a><a class="tab"
					href="${pageContext.request.contextPath}/project/docs?projectId=${project.id}">문서</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/members?projectId=${project.id}">참여자</a
					><a class="tab"
					href="${pageContext.request.contextPath}/project/report?projectId=${project.id}">보고서</a>
					<c:if test="${canManageMembers}"><a
					class="tab"
					href="${pageContext.request.contextPath}/project/settings?projectId=${project.id}">설정</a></c:if>
			</div>


			<div class="card">

				<div
					style="display: flex; gap: 10px; justify-content: space-between; align-items: center">

					<div>
						<h3>업무(Task)</h3>
						<span class="small">총 <c:if test="${canManageMembers}">${taskNum}</c:if>
							<c:if test="${notManageMembers}">${userTaskNum}</c:if> 건
						</span>
					</div>
					<c:if test="${canManageMembers}">
						<a class="btn primary"
							href="${pageContext.request.contextPath}/project/tasks/add?projectId=${project.id}">업무
							생성</a>
					</c:if>
				</div>
				<div style="height: 12px"></div>
				<table class="table">
					<thead>
						<tr>
							<th>업무</th>
							<th>담당</th>
							<th>상태</th>
							<th>중요도</th>
							<th>기간</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${notManageMembers}">
							<c:forEach var="task" items="${userTaskList}">
								<tr class="clickable-row"
    onclick="location.href='${pageContext.request.contextPath}/project/tasks/view?projectId=${project.id}&taskId=${task.id}'">
									<td>${task.name}</td>
									<td>${userName[task.ownerUserId] }</td>
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
									<td>${task.startDate}~${task.dueDate}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${canManageMembers}">
							<c:forEach var="task" items="${taskList}">
								<tr class="clickable-row"
    onclick="location.href='${pageContext.request.contextPath}/project/tasks/view?projectId=${project.id}&taskId=${task.id}'">
									<td>${task.name}</td>
									<td>${userName[task.ownerUserId] }</td>
									<td><span
										class="badge ${task.status == 'READY' ? 'ready' : 
          task.status == 'PROGRESS' ? 'progress' : 
          task.status == 'DONE' ? 'done' : ''}">${task.status == 'READY' ? '대기' : 
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
									<td>${task.startDate}~${task.dueDate}</td>
								</tr>
							</c:forEach>
						</c:if>
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
