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