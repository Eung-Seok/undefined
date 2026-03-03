<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>보고서 목록</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common/sidebar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/project/report.css">
</head>
<body>
	<div class="app">
		<c:set var="role" value="${sessionScope.loginUserRole}" />
		<c:set var="canManageMembers"
			value="${role == 'ADMIN' || role == 'PM'}" />
		<c:set var="notManageMembers" value="${role == 'MEMBER'}" />
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp" />

		<main class="main">
			<div class="tabs">
				<a class="tab"
					href="${pageContext.request.contextPath}/project/overview?projectId=${project.id}">
					개요 </a> <a class="tab"
					href="${pageContext.request.contextPath}/project/tasks?projectId=${project.id}">업무</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/docs?projectId=${project.id}">문서</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/members?projectId=${project.id}">참여자</a>
				<a class="tab active"
					href="${pageContext.request.contextPath}/project/report?projectId=${project.id}">보고서</a>
				<c:if test="${canManageMembers}">
					<a class="tab"
						href="${pageContext.request.contextPath}/project/settings?projectId=${project.id}">설정</a>
				</c:if>
			</div>

			<div class="card">
				<div class="card-head">
					<h3 class="card-title">보고서</h3>

					<a class="btn primary"
						href="${pageContext.request.contextPath}/project/report/write?projectId=${project.id}">
						보고서 작성 </a>
				</div>



				<table class="table">
					<thead>
						<tr>
							<th>작성자</th>
							<th>요약</th>
							<th>이슈</th>
							<th>유형</th>
							<th>기간</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<!-- 					어드민이나 pm이 들어왔을 때  -->
						<c:if test="${canManageMembers}">
							<c:choose>
								<c:when test="${empty reportList}">
									<tr>
										<td colspan="6"
											style="text-align: center; padding: 18px; color: #6b7280;">
											아직 등록된 보고서가 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="r" items="${reportList}">
										<tr class="clickable-row"
											onclick="location.href='${pageContext.request.contextPath}/project/report/view?projectId=${project.id}&reportId=${r.id}'">
											<td>${userName[r.authorUserId]}</td>
											<td><c:out value="${r.summary}" /></td>
											<td><c:choose>
													<c:when test="${not empty r.issue}">
														<span class="badge badge-issue">이슈있음</span>
													</c:when>
													<c:otherwise>
														<span class="badge badge-ok">이슈없음</span>
													</c:otherwise>
												</c:choose></td>
											<td>${r.periodType == 'DAILY' ? '일일' : r.periodType == 'WEEKLY' ? '주간' : r.periodType == 'MONTHLY' ? '월간' : ''}</td>
											<td><fmt:formatDate value="${r.periodStart}"
													pattern="yyyy-MM-dd" /> ~ <fmt:formatDate
													value="${r.periodEnd}" pattern="yyyy-MM-dd" /></td>



											<td><fmt:formatDate value="${r.createdAt}"
													pattern="yyyy-MM-dd" /></td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</c:if>
						<!-- 						일반 유저의 화면 -->
						<c:if test="${notManageMembers}">
							<c:choose>
								<c:when test="${empty userReportList}">
									<tr>
										<td colspan="6"
											style="text-align: center; padding: 18px; color: #6b7280;">
											아직 등록된 보고서가 없습니다.</td>
									</tr>
								</c:when>

								<c:otherwise>
									<c:forEach var="r" items="${userReportList}">
										<tr class="clickable-row"
											onclick="location.href='${pageContext.request.contextPath}/project/report/view?projectId=${project.id}&reportId=${r.id}'">
											<td>${userName[r.authorUserId]}</td>
											<td><c:out value="${r.summary}" /></td>
											<td><c:choose>
													<c:when test="${not empty r.issue}">
														<span class="badge badge-issue">이슈있음</span>
													</c:when>
													<c:otherwise>
														<span class="badge badge-ok">이슈없음</span>
													</c:otherwise>
												</c:choose></td>
											<td>${r.periodType == 'DAILY' ? '일일' : r.periodType == 'WEEKLY' ? '주간' : r.periodType == 'MONTHLY' ? '월간' : ''}</td>
											<td><fmt:formatDate value="${r.periodStart}"
													pattern="yyyy-MM-dd" /> ~ <fmt:formatDate
													value="${r.periodEnd}" pattern="yyyy-MM-dd" /></td>



											<td><fmt:formatDate value="${r.createdAt}"
													pattern="yyyy-MM-dd" /></td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</c:if>
					</tbody>
				</table>
			</div>
		</main>
	</div>
</body>
</html>