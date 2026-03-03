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
			<div class="tabs">
				<a class="tab"
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
					<a class="tab active"
						href="${pageContext.request.contextPath}/project/settings?projectId=${project.id}">설정</a>
				</c:if>
			</div>


			<div class="card">
				<h3>프로젝트 설정</h3>
				<div style="height: 16px;"></div>

				<form
					action="${pageContext.request.contextPath}/project/update?projectId=${project.id}"
					method="post" class="form">

					<div class="form-group">
						<label>프로젝트명</label> <input type="text" name="name"
							value="${project.name}" required>
					</div>

					<div class="form-group">
						<label>설명</label>
						<textarea name="description" rows="4">${project.description}</textarea>
					</div>
					<div class="form-group">
						<label>프로젝트 매니저 (PM)</label> <select name="ownerUserId" required>
							<option value="">선택하세요</option>
							<c:forEach var="user" items="${userList}">
								<option value="${user.empno}"
									<c:if test="${project.ownerUserId == user.empno}">selected</c:if>>${user.name}
									(${deptName[user.deptno]} ${user.position})</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-row">
						<div class="form-group">
							<label>시작일</label> <input type="date" id="startDate"
								name="startDate" max="${project.endDate}"
								value="${project.startDate}">
						</div>

						<div class="form-group">
							<label>종료일</label> <input type="date" id="endDate" name="endDate"
								min="${project.startDate}" value="${project.endDate}">
						</div>
					</div>

					<div class="form-group">
						<label>상태</label> <select name="status">
							<option value="READY"
								<c:if test="${project.status eq 'READY'}">selected</c:if>>준비</option>
							<option value="PROGRESS"
								<c:if test="${project.status eq 'PROGRESS'}">selected</c:if>>진행중</option>
							<option value="DONE"
								<c:if test="${project.status eq 'DONE'}">selected</c:if>>완료</option>
						</select>
					</div>

					<div style="text-align: right; margin-top: 20px;">
						<button type="submit" class="btn" name="action" value="delete"
							style="background-color: red; color: white"  onclick="return confirm('정말 삭제하시겠습니까?');">삭제</button>
						<button type="submit" name="action" value="save"
							class="btn primary">저장</button>
					</div>

				</form>
			</div>

		</main>
	</div>
	<script src="/js/projects/projects.js"></script>
	<script>
		
	</script>
</body>
</html>