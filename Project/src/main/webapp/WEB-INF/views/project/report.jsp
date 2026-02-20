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
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>프로젝트 보고서 생성</title>
<style>
</style>
<link rel="stylesheet" href="/css/project/report.css">
</head>
<body data-role="${sessionScope.loginUser.role}">
	<div class="app">
		<aside class="sidebar">
			<div class="brand">
				<div class="logo">P</div>
				<div>
					<div class="brand-title">프로젝트 통합 일정/업무 관리</div>
					<div class="brand-sub">Project Scheduler · JSP Demo UI</div>
				</div>
			</div>

			<div class="profile">
				<div class="avatar">👤</div>
				<div>
					<div class="profile-name">
						<c:out value="${sessionScope.loginUser.name}" />
					</div>
					<div class="profile-role">
						<c:out value="${sessionScope.loginUser.position}" />
					</div>
				</div>
			</div>

			<div class="nav">
				<div class="section">Main</div>
				<a class="" href="${pageContext.request.contextPath}/dashboard"><span>🏠</span>대시보드</a>
				<a class="active" href="${pageContext.request.contextPath}/projects"><span>📁</span>프로젝트</a>
				<a class="" href="${pageContext.request.contextPath}/calendar"><span>🗓️</span>일정</a>
				<a class="" href="${pageContext.request.contextPath}/board"><span>📝</span>게시판</a>
				<a class="" href="${pageContext.request.contextPath}/employees"><span>👥</span>직원정보</a>
				<a class="" href="${pageContext.request.contextPath}/mypage"><span>⚙️</span>마이페이지</a>

				<div class="section">Admin</div>
				<a data-requires="ADMIN"
					href="${pageContext.request.contextPath}/admin/users">👮 사용자 관리</a>
				<a data-requires="ADMIN"
					href="${pageContext.request.contextPath}/admin/roles">🔐 권한 관리</a>
				<a data-requires="ADMIN"
					href="${pageContext.request.contextPath}/admin/org">🏢 조직도 관리</a> <a
					data-requires="ADMIN"
					href="${pageContext.request.contextPath}/admin/system">🧰 시스템</a>
			</div>

			<hr class="line">
			<div class="small">※ 데모 UI: 권한별 숨김은 프론트 처리입니다(보안X)</div>
		</aside>

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

			<div class="tabs">
				<a class="tab active"
					href="${pageContext.request.contextPath}/project/overview?projectId=${project.id}">
					개요 </a> <a class="tab"
					href="${pageContext.request.contextPath}/project/tasks?projectId=${project.id}">업무</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/calendar?projectId=${project.id}">프로젝트
					캘린더</a><a class="tab"
					href="${pageContext.request.contextPath}/project/wbs?projectId=${project.id}">WBS</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/issues?projectId=${project.id}">이슈</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/docs?projectId=${project.id}">문서</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/members?projectId=${project.id}">참여자</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/settings?projectId=${project.id}">설정</a>
			</div>
			<div class="report-wrapper">
				<form
					action="${pageContext.request.contextPath}/project/report/save"
					method="post">
					<input type="hidden" name="projectId" value="${project.id}">
					<div class="report-title">프로젝트 진행 보고서</div>

					<div class="report-section">
						<div class="report-section-title">1. 프로젝트 개요</div>
						<div class="report-box">
							<p class="report-text">프로젝트명 : ${project.name}</p>
							<p class="report-text">기간: ${project.startDate} ~
								${project.endDate}</p>
							<p class="report-text">상태: ${project.status}</p>
						</div>
					</div>

					<div class="report-section">
						<div class="report-section-title">2. 진척률</div>
						<div class="report-box">
							<div class="report-progress">49%</div>
						</div>
					</div>

					<div class="report-section">
						<div class="report-section-title">3. 주요 업무</div>
						<div class="report-box">
							<ul class="report-list">
								<c:forEach var="task" items="${taskList}">
									<li>${task.name}</li>
								</c:forEach>
							</ul>
							<textarea name="taskContent" class="report-textarea"
								placeholder="추가 업무 내용을 작성하세요"></textarea>
						</div>
					</div>

					<div class="report-section">
						<div class="report-section-title">4. 이슈</div>
						<div class="report-box">
							<ul class="report-list">
								<c:forEach var="issue" items="${issueList}">
									<li>${issue.title}</li>
								</c:forEach>
							</ul>
							<textarea name="issueContent" class="report-textarea"
								placeholder="새로운 이슈를 작성하세요"></textarea>
						</div>
					</div>
					<div class="report-actions">
						<button class="btn primary" type="submit">제출</button>
					</div>
					<c:if test="${param.success eq 'true'}">
						<div id="successModal" class="modal">
							<div class="modal-content">
								<p>보고서 제출 완료하였습니다.</p>
								<button onclick="closeModal()" class="btn primary">확인</button>
							</div>
						</div>
					</c:if>
		</main>
		</form>
	</div>
	<script>
		function closeModal() {
			document.getElementById("successModal").style.display = "none";
		}
	</script>
</body>
</html>