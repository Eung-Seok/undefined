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
<title>게시판</title>

<link href="/css/board/board.css" rel="stylesheet">
</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="board" />
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
				<h3>게시판</h3>
				<div class="small">공지/사내소통/부서게시판 (데모)</div>
				<div style="height: 12px"></div>
				<div
					style="display: flex; justify-content: space-between; gap: 10px; align-items: center">
					<div class="small">총 3건</div>
					<a class="btn primary"
						href="${pageContext.request.contextPath}/board/write">글쓰기</a>
				</div>
				<div style="height: 12px"></div>
				<table class="table">
					<thead>
						<tr>
							<th>분류</th>
							<th>제목</th>
							<th>작성자</th>
							<th>일자</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>공지</td>
							<td><a href="${pageContext.request.contextPath}/board/view">프로젝트
									일정관리 시스템 오픈</a></td>
							<td>관리자</td>
							<td>2026-02-12</td>
						</tr>
						<tr>
							<td>사내활동</td>
							<td><a href="${pageContext.request.contextPath}/board/view">워크샵
									안내</a></td>
							<td>인사팀</td>
							<td>2026-02-10</td>
						</tr>
						<tr>
							<td>부서</td>
							<td><a href="${pageContext.request.contextPath}/board/view">개발팀
									주간회의</a></td>
							<td>개발팀</td>
							<td>2026-02-09</td>
						</tr>
					</tbody>
				</table>
			</div>

		</main>
	</div>
	<script src="/js/board/board.js"></script>
</body>
</html>
