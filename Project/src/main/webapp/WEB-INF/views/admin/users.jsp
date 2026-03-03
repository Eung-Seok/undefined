<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>사용자 관리</title>
<style>
</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/users.css?v=1">
<link rel="stylesheet" href="/css/common/sidebar.css">
</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="adminUsers" />
		</jsp:include>

		<main class="main">
			<div class="card">
				<div class="card-header">
					<h3 style="margin: 0;">사용자 관리</h3>

					<a class="btn-create"
						href="<%=request.getContextPath()%>/admin/users/create"> <span
						class="icon">＋</span> <span>사용자 생성</span>
					</a>
				</div>

				<div style="height: 12px"></div>
				<table class="table">
					<thead>
						<tr>
							<th>사원번호</th>
							<th>이름</th>
							<th>부서</th>
							<th>직급</th>
							<th>권한</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${userList}">
							<tr>
								<td>${user.empno }</td>
								<td>${user.name }</td>
								<td>${departmentMap[user.deptno]}</td>
								<td>${user.position}</td>
								<td>${userMap[user.empno]}</td>
								<td><a class="btn"
									href="<%=request.getContextPath()%>/admin/users/edit?empno=${user.empno}">변경</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</main>
	</div>
	<script src="/js/admin/users.js"></script>
	<script>
		
	</script>
</body>
</html>
