<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
// Demo session user (Map) - replace with real login later
if (session.getAttribute("loginUser") == null) {
	java.util.Map<String, Object> u = new java.util.HashMap<>();
	u.put("name", "홍길동");
	u.put("position", "사원");
	u.put("role", "MEMBER"); // ADMIN / PM / MEMBER / VIEWER
	session.setAttribute("loginUser", u);
}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>직원 프로필</title>
<style>
</style>
<link rel="stylesheet" href="/css/employee/employee-view.css">
</head>
<body data-role="${sessionScope.loginUser.role}">
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="employees" />
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
				<h3>직원 프로필</h3>
				<div style="height: 12px"></div>
				<table class="table">
					<tbody>
						<tr>
							<th style="width: 140px">이름</th>
							<td>홍길동</td>
						</tr>
						<tr>
							<th>직급</th>
							<td>사원</td>
						</tr>
						<tr>
							<th>부서</th>
							<td>개발</td>
						</tr>
						<tr>
							<th>연락처</th>
							<td>010-1234-5678</td>
						</tr>
						<tr>
							<th>이메일</th>
							<td>hong@company.com</td>
						</tr>
					</tbody>
				</table>
				<div style="height: 12px"></div>
				<a class="btn" href="${pageContext.request.contextPath}/employees">목록</a>
			</div>

		</main>
	</div>
	<script src="/js/employee/employee-view.js"></script>
	<script>
		
	</script>
</body>
</html>
