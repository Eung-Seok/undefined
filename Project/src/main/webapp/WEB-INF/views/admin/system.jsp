<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>시스템</title>
<style>
</style>
<link rel="stylesheet" href="/css/admin/system.css">
</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="adminSystem" />
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
				<h3>시스템</h3>
				<div class="small">ADMIN 전용(데모)</div>
				<div style="height: 12px"></div>
				<button class="btn" data-action="캐시 초기화">캐시 초기화</button>
				<button class="btn" data-action="로그 다운로드" style="margin-left: 8px">로그
					다운로드</button>
			</div>

		</main>
	</div>
	<script src="/js/admin/system.js"></script>
	<script>
		
	</script>
</body>
</html>
