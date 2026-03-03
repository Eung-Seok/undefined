<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>보고서 작성</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common/sidebar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/project/report.css">
</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp" />

		<main class="main">
			<div class="card">
				<div class="card-head">
					<h3 class="card-title">보고서 작성</h3>
					<a class="btn"
						href="${pageContext.request.contextPath}/project/report?projectId=${project.id}">
						목록 </a>
				</div>

				<form method="post"
					action="${pageContext.request.contextPath}/project/report/write">
					<input type="hidden" name="projectId" value="${project.id}" />

					<div class="form-grid">
						<div class="field">
							<label>기간 유형</label> <select name="periodType" id="periodType"
								required>
								<option value="DAILY">일간</option>
								<option value="WEEKLY" selected>주간</option>
								<option value="MONTHLY">월간</option>
								<option value="ETC">기타</option>
							</select>
						</div>

						<div class="field">
							<label>기간 시작</label> <input type="date" name="periodStart"
								id="periodStart" required />
						</div>

						<div class="field">
							<label>기간 종료</label> <input type="date" name="periodEnd"
								id="periodEnd" required />
						</div>
					</div>

					<div class="field">
						<label>요약(내용)</label>
						<textarea name="summary" rows="10" maxlength="4000" required
							placeholder="진행 사항 / 완료한 업무 / 다음 계획 등을 요약해 작성하세요."></textarea>
					</div>

					<div class="field issue-toggle">
						<label class="checkbox"> <input type="checkbox"
							id="issueCheck"> 이슈 보고 추가(선택)
						</label>
					</div>

					<div class="field issue-section" id="issueSection"
						style="display: none;">
						<label>이슈</label>
						<textarea name="issue" rows="6" maxlength="4000"
							placeholder="발생한 문제, 영향도, 원인, 해결 계획/요청사항 등을 작성하세요."></textarea>
					</div>

					<div class="btn-row">
						<button type="submit" class="btn primary">등록</button>
						<a class="btn"
							href="${pageContext.request.contextPath}/project/overview?projectId=${project.id}">
							취소 </a>
					</div>
				</form>
			</div>
		</main>
	</div>
	<script src="/js/project/report.js"></script>
</body>
</html>