<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>프로젝트 업무</title>
<style>
</style>
<link rel="stylesheet" href="/css/project/report.css">
<link rel="stylesheet" href="/css/common/sidebar.css">
</head>
<body>
	<div class="app">
		<c:set var="role" value="${sessionScope.loginUserRole}" />
		<c:set var="canManageMembers"
			value="${role == 'ADMIN' || role == 'PM'}" />
		<c:set var="notManageMembers" value="${role == 'MEMBER'}" />
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="projects" />
		</jsp:include>

		<main class="main">
			<div class="tabs">
				<a class="tab"
					href="${pageContext.request.contextPath}/project/overview?projectId=${project.id}">
					개요 </a> <a class="tab"
					href="${pageContext.request.contextPath}/project/tasks?projectId=${project.id}">업무</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/calendar?projectId=${project.id}">프로젝트
					캘린더</a><a class="tab"
					href="${pageContext.request.contextPath}/project/docs?projectId=${project.id}">문서</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/members?projectId=${project.id}">참여자</a><a
					class="tab active"
					href="${pageContext.request.contextPath}/project/report?projectId=${project.id}">보고서</a>
				<c:if test="${canManageMembers}">
					<a class="tab"
						href="${pageContext.request.contextPath}/project/settings?projectId=${project.id}">설정</a>
				</c:if>
			</div>
			<div class="card">
				<div class="card-head">
					<h3>보고서 상세</h3>
					<div>
						<c:if test="${user.empno == report.authorUserId}">
							<a class="btn"
								href="${pageContext.request.contextPath}/project/report/edit?projectId=${projectId}&reportId=${report.id}">수정</a>
						</c:if>
						<a class="btn"
							href="${pageContext.request.contextPath}/project/report?projectId=${project.id}">목록</a>
					</div>
				</div>

				<div class="report-detail">

					<div class="row">
						<span class="label">작성자</span> <span>${userName[report.authorUserId]}</span>
					</div>

					<div class="row">
						<span class="label">기간</span> <span> <fmt:formatDate
								value="${report.periodStart}" pattern="yyyy-MM-dd" /> ~ <fmt:formatDate
								value="${report.periodEnd}" pattern="yyyy-MM-dd" />
						</span>
					</div>

					<div class="row">
						<span class="label">유형</span> <span>${report.periodType}</span>
					</div>

					<div class="row">
						<span class="label">요약</span>
						<div class="content-box">
							<c:out value="${report.summary}" escapeXml="false" />
						</div>
					</div>

					<div class="row">
						<span class="label">이슈</span>

						<div class="content-box issue-box">
							<c:choose>
								<c:when test="${not empty report.issue}">
									<c:out value="${report.issue}" />
								</c:when>
								<c:otherwise>
									<span style="color: #9ca3af;">등록된 이슈 없음</span>
								</c:otherwise>
							</c:choose>
						</div>
					</div>

				</div>
			</div>



		</main>
	</div>
	<script src="/js/project/report.js"></script>
</body>
</html>