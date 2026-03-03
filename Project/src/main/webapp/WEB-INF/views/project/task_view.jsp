<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>업무 상세</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common/sidebar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/project/tasks.css">
<style>
.detail-grid {
	display: grid;
	grid-template-columns: 1fr 1fr;
	gap: 14px;
}

.detail-row .label {
	font-size: 12px;
	color: #6b7280;
	margin-bottom: 6px;
	display: block;
}

.detail-row .value {
	font-size: 14px;
	color: #111827;
}

.detail-box {
	margin-top: 14px;
	padding: 14px;
	border: 1px solid #e5e7eb;
	border-radius: 10px;
	background: #f9fafb;
	white-space: pre-line;
}

.card-head {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 14px;
}

.btn-row {
	display: flex;
	gap: 10px;
}

.assignee-wrap {
	display: flex;
	flex-wrap: wrap;
	gap: 6px;
	margin-top: 6px;
}

.assignee-chip {
	display: inline-flex;
	align-items: center;
	gap: 6px;
	padding: 2px 8px; /* ✅ 작게 */
	border-radius: 999px;
	font-size: 11px; /* ✅ 작게 */
	font-weight: 600;
	background: #ecfdf5;
	color: #065f46;
	border: 1px solid #a7f3d0;
}

.chip-x {
	border: 0;
	background: transparent;
	cursor: pointer;
	font-size: 12px; /* ✅ 작게 */
	line-height: 1;
	padding: 0 2px;
	color: #065f46;
	opacity: .6;
}

.chip-x:hover {
	opacity: 1;
}

.assignee-add-form {
	display: flex;
	gap: 8px;
	align-items: center;
	margin-top: 8px;
}

.assignee-select {
	height: 32px; /* ✅ 높이 줄이기 */
	padding: 0 10px;
	border: 1px solid #e5e7eb;
	border-radius: 10px;
	background: #fff;
	font-size: 12px;
}

.assignee-add-form .btn {
	height: 32px; /* ✅ 버튼도 맞추기 */
	padding: 0 12px;
	font-size: 12px;
	border-radius: 10px;
}

.muted {
	color: #9ca3af;
}
</style>
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
					개요 </a> <a class="tab active"
					href="${pageContext.request.contextPath}/project/tasks?projectId=${project.id}">업무</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/docs?projectId=${project.id}">문서</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/members?projectId=${project.id}">참여자</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/report?projectId=${project.id}">보고서</a>
				<c:if test="${canManageMembers}">
					<a class="tab"
						href="${pageContext.request.contextPath}/project/settings?projectId=${project.id}">설정</a>
				</c:if>
			</div>
			<div class="card">
				<div class="card-head">
					<h3 class="card-title">업무 상세</h3>
					<div>
						<h3>${task.name}</h3>
					</div>
					<div class="btn-row">
						<a class="btn"
							href="${pageContext.request.contextPath}/project/tasks?projectId=${project.id}">
							목록 </a>

						<!-- ADMIN/PM이면 수정/삭제 버튼 노출(페이지는 네가 만들면 됨) -->
						<c:if test="${canManageMembers}">
							<a class="btn"
								href="${pageContext.request.contextPath}/project/tasks/edit?projectId=${project.id}&taskId=${task.id}">수정</a>
							<form method="post"
								action="${pageContext.request.contextPath}/project/tasks/delete"
								style="display: inline;" onsubmit="return confirm('정말 삭제할까요?');">
								<input type="hidden" name="projectId" value="${project.id}" />
								<input type="hidden" name="taskId" value="${task.id}" />
								<button type="submit" class="btn"
									style="background-color: red; color: white">삭제</button>
							</form>
						</c:if>
					</div>
				</div>

				<div class="detail-grid">

					<div class="detail-row">
						<span class="label">상태</span>
						<div class="value">${task.status}</div>
					</div>

					<div class="detail-row">
						<span class="label">중요도</span>
						<div class="value">${task.priority}</div>
					</div>

					<div class="detail-row">
						<span class="label">시작일</span>
						<div class="value">${task.startDate}</div>
					</div>

					<div class="detail-row">
						<span class="label">마감일</span>
						<div class="value">${task.dueDate}</div>
					</div>

					<div class="detail-row">
						<span class="label">진행도</span>
						<div class="progress-wrapper">
							<div class="progress-bar">
								<div
									class="progress-fill
  ${task.progressPercent < 40 ? 'low-progress' :
    task.progressPercent < 80 ? 'mid-progress' :
    'high-progress'}"
									style="width:${task.progressPercent}%;"></div>
							</div>
							<span class="progress-text">${task.progressPercent}%</span>
						</div>
					</div>

					<div class="detail-row">
						<span class="label">담당자(들)</span>

						<div class="assignee-wrap">
							<c:choose>
								<c:when test="${empty userList}">
									<span class="muted">담당자 없음</span>
								</c:when>
								<c:otherwise>
									<c:forEach var="a" items="${taskUserList}">
										<span class="assignee-chip"> ${a.name} <c:if
												test="${canManageMembers}">
												<form method="post"
													action="${pageContext.request.contextPath}/project/tasks/assignees/delete"
													style="display: inline;" onclick="event.stopPropagation();">
													<input type="hidden" name="projectId" value="${project.id}" />
													<input type="hidden" name="taskId" value="${task.id}" /> <input
														type="hidden" name="empno" value="${a.empno}" />
													<button type="submit" class="chip-x" title="삭제">×</button>
												</form>
											</c:if>
										</span>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>

						<c:if test="${canManageMembers}">
							<form method="post"
								action="${pageContext.request.contextPath}/project/tasks/assignees/add"
								class="assignee-add-form" onclick="event.stopPropagation();">
								<input type="hidden" name="projectId" value="${project.id}" />
								<input type="hidden" name="taskId" value="${task.id}" /> <select
									name="empno" required class="assignee-select">
									<option value="">담당자 추가</option>
									<c:forEach var="u" items="${userList}">
										<option value="${u.empno}">${u.name}</option>
									</c:forEach>
								</select>

								<button type="submit" class="btn">추가</button>
							</form>
						</c:if>
					</div>
				</div>
				<div style="height: 16px;"></div>


				<c:if test="${not empty task.description}">
					<div style="margin-top: 14px;">
						<span class="label" style="font-size: 12px; color: #6b7280;">설명</span>
						<div class="detail-box">
							<c:out value="${task.description}" />
						</div>
					</div>
				</c:if>


			</div>
		</main>
	</div>
</body>
</html>