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
<title>직원정보</title>
<style>
</style>
<link rel="stylesheet" href="/css/employee/employees.css">
</head>
<body>
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
				<h3>직원 목록</h3>
				<div class="small">직원 프로필, 휴가, 연락처 정보(데모)</div>
				<div style="height: 12px"></div>
				<table class="table">
					<thead>
						<tr>
							<th>이름</th>
							<th>직급</th>
							<th>부서</th>
							<th>이메일</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>홍길동</td>
							<td>사원</td>
							<td>개발</td>
							<td>hong@company.com</td>
							<td><a class="btn"
								href="${pageContext.request.contextPath}/employees/view">보기</a></td>
						</tr>
						<tr>
							<td>김PM</td>
							<td>과장</td>
							<td>기획</td>
							<td>pm@company.com</td>
							<td><a class="btn"
								href="${pageContext.request.contextPath}/employees/view">보기</a></td>
						</tr>
					</tbody>
				</table>
			</div>

		</main>
	</div>
	<script src="/js/employee/employees.js"></script>
	<script>
		
	</script>
</body>
</html>
