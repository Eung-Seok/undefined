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
<title>게시글 보기</title>

<link href="/css/board/board-view.css" rel="stylesheet">
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
				<h3>프로젝트 일정관리 시스템 오픈</h3>
				<div class="small">공지 · 관리자 · 2026-02-12</div>
				<div style="height: 12px"></div>
				<div style="line-height: 1.7">
					안녕하세요. 프로젝트 통합 일정/업무 관리 시스템 데모 화면입니다.<br /> 다음 단계에서 로그인/권한/DB 연동을
					붙여 완성하세요.
				</div>
				<div style="height: 14px"></div>
				<div style="display: flex; gap: 10px; justify-content: flex-end">
					<a class="btn" href="${pageContext.request.contextPath}/board">목록</a>
					<button class="btn" data-action="수정">수정</button>
					<button class="btn" data-action="삭제">삭제</button>
				</div>
			</div>

		</main>
	</div>

	<script src="/js/board/board-view.js"></script>
</body>
</html>
