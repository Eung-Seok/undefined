<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>참여자</title>
<style>
</style>
<link rel="stylesheet" href="/css/project/members.css?v=7">
<link rel="stylesheet" href="/css/common/sidebar.css?v=7">
</head>
<body>
	<div class="app">
		<c:set var="role" value="${sessionScope.loginUserRole}" />
		<c:set var="canManageMembers"
			value="${role == 'ADMIN' || role == 'PM'}" />


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
					class="tab active"
					href="${pageContext.request.contextPath}/project/members?projectId=${project.id}">참여자</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/report?projectId=${project.id}">보고서</a>
				<c:if test="${canManageMembers}">
					<a class="tab"
						href="${pageContext.request.contextPath}/project/settings?projectId=${project.id}">설정</a>
				</c:if>
			</div>


			<div class="card">
				<div class="card-head">
					<h3 class="card-title">참여자</h3>

					<c:if test="${canManageMembers}">
						<a class="btn primary"
							href="${pageContext.request.contextPath}/project/members/add?projectId=${project.id}">
							참여자 추가 </a>
					</c:if>
				</div>
				<div style="height: 12px"></div>
				<table class="table">
					<thead>
						<tr>
							<th>이름</th>
							<th>역할</th>
							<c:if test="${canManageMembers}">
								<th class="col-actions">관리</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${userList}">
							<tr>
								<td>${user.name}</td>
								<td>${userRoleMap[user.empno]}</td>
								<c:if test="${canManageMembers}">
									<td class="actions">
										<!-- 수정: 예) 역할 변경 페이지 --> <a class="btn small"
										href="${pageContext.request.contextPath}/project/members/edit?projectId=${project.id}&empno=${user.empno}">
											수정 </a> <!-- 삭제: 예) 프로젝트에서 참여자 제거 -->
										<form class="inline"
											action="${pageContext.request.contextPath}/project/members/delete"
											method="post" onsubmit="return confirm('정말 삭제(제외)할까요?');">
											<input type="hidden" name="projectId" value="${project.id}" />
											<input type="hidden" name="empno" value="${user.empno}" />
											<button type="submit" class="btn small danger">삭제</button>
										</form>
									</td>
								</c:if>
							</tr>
						</c:forEach>
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
