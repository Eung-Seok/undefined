<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>업무 수정</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common/sidebar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/project/tasks.css">

<style>
.form-grid {
	display: grid;
	grid-template-columns: 1fr 1fr;
	gap: 14px;
}

.field label {
	display: block;
	font-size: 12px;
	color: #6b7280;
	margin-bottom: 6px;
}

.field input, .field select, .field textarea {
	width: 100%;
	padding: 10px 12px;
	border: 1px solid #e5e7eb;
	border-radius: 10px;
	background: #fff;
	outline: none;
}

.field textarea {
	min-height: 120px;
	resize: vertical;
}

.actions-row {
	display: flex;
	gap: 10px;
	justify-content: flex-end;
	margin-top: 14px;
}

.progress-row {
	display: flex;
	gap: 10px;
	align-items: center;
}

.progress-row input[type="range"] {
	width: 100%;
}

.helper {
	font-size: 12px;
	color: #9ca3af;
	margin-top: 6px;
}
</style>
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
				<a class="tab"
					href="${pageContext.request.contextPath}/project/overview?projectId=${project.id}">
					개요 </a> <a class="tab active"
					href="${pageContext.request.contextPath}/project/tasks?projectId=${project.id}">업무</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/calendar?projectId=${project.id}">프로젝트
					캘린더</a><a class="tab"
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
				<div
					style="display: flex; justify-content: space-between; align-items: center;">
					<h3>업무 수정</h3>
					<a class="btn"
						href="${pageContext.request.contextPath}/project/tasks/view?projectId=${project.id}&taskId=${task.id}">
						돌아가기 </a>
				</div>

				<div style="height: 12px;"></div>

				<form method="post"
					action="${pageContext.request.contextPath}/project/tasks/edit">
					<input type="hidden" name="projectId" value="${project.id}">
					<input type="hidden" name="id" value="${task.id}">

					<div class="form-grid">

						<div class="field" style="grid-column: 1/span 2;">
							<label>업무명</label> <input name="name" value="${task.name}"
								required>
						</div>

						<div class="field">
							<label>담당자</label> <select name="ownerUserId" required>
								<c:forEach var="u" items="${userList}">
									<option value="${u.empno}"
										${u.empno == task.ownerUserId ? 'selected' : ''}>
										${u.name}</option>
								</c:forEach>
							</select>
						</div>

						<div class="field">
							<label>상태</label> <select name="status" required>
								<option value="READY"
									${task.status == 'READY' ? 'selected' : ''}>대기</option>
								<option value="PROGRESS"
									${task.status == 'PROGRESS' ? 'selected' : ''}>진행중</option>
								<option value="DONE" ${task.status == 'DONE' ? 'selected' : ''}>완료</option>
							</select>
						</div>

						<div class="field">
							<label>중요도</label> <select name="priority" required>
								<option value="HIGHT"
									${task.priority == 'HIGHT' ? 'selected' : ''}>상</option>
								<option value="MEDIUMN"
									${task.priority == 'MEDIUMN' ? 'selected' : ''}>중</option>
								<option value="LOW" ${task.priority == 'LOW' ? 'selected' : ''}>하</option>
							</select>
						</div>

						<div class="field">
							<label>진행도</label>
							<div class="progress-row">
								<input id="progressRange" type="range" name="progressPercent" min="0"
									max="100"
									value="${task.progressPercent != null ? task.progressPercent : 0}">
								<span id="progressText"
									style="min-width: 42px; text-align: right;">
									${task.progressPercent != null ? task.progressPercent : 0}% </span>
							</div>
						</div>

						<div class="field">
							<label>시작일</label> <input type="date" id="startDate" name="startDate"
								value="${task.startDate}" max="${task.dueDate}" required>
						</div>

						<div class="field">
							<label>마감일</label> <input type="date" id="endDate" name="dueDate"
								value="${task.dueDate}" min="${task.startDate}" required>
						</div>

						<div class="field" style="grid-column: 1/span 2;">
							<label>설명</label>
							<textarea name="description"><c:out
									value="${task.description}" /></textarea>
						</div>

					</div>

					<div class="actions-row">
						<a class="btn"
							href="${pageContext.request.contextPath}/project/tasks/view?projectId=${project.id}&taskId=${task.id}">
							취소 </a>
						<button type="submit" class="btn primary">저장</button>
					</div>
				</form>
			</div>
		</main>
	</div>

	<script>
  const r = document.getElementById('progressRange');
  const t = document.getElementById('progressText');
  if (r && t) {
    r.addEventListener('input', () => t.textContent = r.value + '%');
  }
</script>
<script src="/js/projects/projects.js"></script>
</body>
</html>