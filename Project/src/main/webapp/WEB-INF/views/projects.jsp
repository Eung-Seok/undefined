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
<title>프로젝트</title>
<style>
</style>
<link rel="stylesheet" href="/css/projects/projects.css">
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




			<div class="card">
				<h3>프로젝트 목록</h3>
				<div class="small">프로젝트 선택 후 내부 탭(개요/업무/WBS/문서 등)으로 이동하세요.</div>
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
						<tr>
							<td>신규 웹사이트 구축</td>
							<td><span class="badge good">진행중</span></td>
							<td>김PM</td>
							<td>2026-02-01 ~ 2026-04-25</td>
							<td><a class="btn"
								href="${pageContext.request.contextPath}/project/overview">열기</a></td>
						</tr>
						<tr>
							<td>ERP 연동</td>
							<td><span class="badge warn">리스크</span></td>
							<td>박PM</td>
							<td>2026-01-15 ~ 2026-04-24</td>
							<td><a class="btn"
								href="${pageContext.request.contextPath}/project/overview">열기</a></td>
						</tr>
					</tbody>
				</table>
				<div style="height: 12px"></div>
				<button class="btn primary" data-requires="PM,ADMIN"
					data-action="프로젝트 생성">프로젝트 생성</button>
			</div>

		</main>
	</div>
	<script src="/js/projects/projects.js"></script>
	<script>
		
	</script>
</body>
</html>
