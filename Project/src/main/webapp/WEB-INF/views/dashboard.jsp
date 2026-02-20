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
<title>대시보드</title>
<style>
</style>
<link rel="stylesheet" href="/css/dashboard/dashboard.css">
</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="dashboard" />
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




			<div class="grid cols-4">
				<div class="card">
					<div class="kpi">
						<div>
							<div class="label">진행중 프로젝트</div>
							<div class="value">3</div>
						</div>
						<span class="badge good">▲ 1</span>
					</div>
				</div>
				<div class="card">
					<div class="kpi">
						<div>
							<div class="label">오늘 마감 업무</div>
							<div class="value">5</div>
						</div>
						<span class="badge warn">주의</span>
					</div>
				</div>
				<div class="card">
					<div class="kpi">
						<div>
							<div class="label">지연 업무</div>
							<div class="value">2</div>
						</div>
						<span class="badge bad">필요</span>
					</div>
				</div>
				<div class="card">
					<div class="kpi">
						<div>
							<div class="label">이번주 일정</div>
							<div class="value">8</div>
						</div>
						<span class="badge">D-3</span>
					</div>
				</div>
			</div>

			<div style="height: 14px"></div>

			<div class="grid cols-2">
				<div class="card">
					<h3>프로젝트 현황</h3>
					<table class="table">
						<thead>
							<tr>
								<th>프로젝트</th>
								<th>PM</th>
								<th>참여인원</th>
								<th>잔여업무</th>
								<th>마감</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>신규 웹사이트 구축</td>
								<td>김PM</td>
								<td>7</td>
								<td>24</td>
								<td>4/25</td>
							</tr>
							<tr>
								<td>ERP 연동</td>
								<td>박PM</td>
								<td>5</td>
								<td>12</td>
								<td>4/24</td>
							</tr>
							<tr>
								<td>모바일 앱 개선</td>
								<td>이PM</td>
								<td>4</td>
								<td>6</td>
								<td>4/27</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="card">
					<h3>오늘 할 일</h3>
					<div class="notice">업무/일정 데이터는 데모입니다. 다음 단계에서 DB CRUD로 연결하세요.</div>
					<div style="height: 12px"></div>
					<table class="table">
						<thead>
							<tr>
								<th>업무</th>
								<th>우선</th>
								<th>상태</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>요구사항 정리</td>
								<td><span class="badge warn">중</span></td>
								<td><span class="badge good">진행중</span></td>
							</tr>
							<tr>
								<td>WBS 초안 작성</td>
								<td><span class="badge bad">높음</span></td>
								<td><span class="badge warn">대기</span></td>
							</tr>
							<tr>
								<td>회의록 업로드</td>
								<td><span class="badge">낮음</span></td>
								<td><span class="badge">완료</span></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</main>
	</div>
	<script src="/js/dashboard/dashboard.js"></script>
	<script>
		
	</script>
</body>
</html>
