<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>마이페이지</title>
<style>
</style>
<link rel="stylesheet" href="/css/mypage/mypage.css">
<link rel="stylesheet" href="/css/common/sidebar.css">
</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="mypage" />
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




			<div class="grid cols-2">
				<div class="card">
					<h3>프로필</h3>
					<div class="small">세션의 loginUser(Map)를 표시합니다.</div>
					<div style="height: 12px"></div>
					<table class="table">
						<tbody>
							<tr>
								<th style="width: 140px">이름</th>
								<td><c:out value="${sessionScope.loginUser.name}" /></td>
							</tr>
							<tr>
								<th>직급</th>
								<td><c:out value="${sessionScope.loginUser.position}" /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="card">
					<h3>권한 테스트</h3>
					<div class="small">버튼은 role에 따라 숨김 처리(프론트 데모)</div>
					<div style="height: 12px"></div>
					<button class="btn" data-action="MEMBER 기능">MEMBER 기능</button>
					<button class="btn" data-requires="PM,ADMIN"
						data-action="PM/ADMIN 기능" style="margin-left: 8px">PM/ADMIN
						기능</button>
					<div style="height: 12px"></div>
					<div class="small">role 값은 _로그인 후_ 세션에서 바꾸는게 정석입니다.</div>
				</div>
			</div>

		</main>
	</div>
	<script src="/js/mypage/mypage.js"></script>
	<script>
		
	</script>
</body>
</html>
