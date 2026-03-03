<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>프로젝트</title>
<style>
</style>
<link rel="stylesheet" href="/css/projects/projects.css">
<link rel="stylesheet" href="/css/common/sidebar.css">
</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="projects" />
		</jsp:include>

		<main class="main">
			<div class="card">
				<h3>프로젝트 생성</h3>
				<div class="small">새로운 프로젝트 정보를 입력하세요.</div>
				<div style="height: 16px;"></div>

				<form action="${pageContext.request.contextPath}/project/save"
					method="post" class="form">

					<div class="form-group">
						<label>프로젝트명</label> <input type="text" name="name" required>
					</div>

					<div class="form-group">
						<label>설명</label>
						<textarea name="description" rows="4"></textarea>
					</div>
					<div class="form-group">
						<label>프로젝트 매니저 (PM)</label> <select name="pmUserId" required>
							<option value="">선택하세요</option>
							<c:forEach var="user" items="${userList}">
								<option value="${user.empno}">${user.name}
									(${user.position})</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-row">
						<div class="form-group">
							<label>시작일</label> <input type="date" id="startDate" name="startDate">
						</div>

						<div class="form-group">
							<label>종료일</label> <input type="date" id="endDate" name="endDate">
						</div>
					</div>

					<div class="form-group">
						<label>상태</label> <select name="status">
							<option value="READY">준비</option>
							<option value="PROGRESS">진행중</option>
							<option value="DONE">완료</option>
						</select>
					</div>

					<div style="text-align: right; margin-top: 20px;">
						<a class="btn" href="${pageContext.request.contextPath}/projects">취소</a>
						<button type="submit" class="btn primary">저장</button>
					</div>

				</form>
			</div>
		</main>
	</div>
	<script src="/js/projects/projects.js"></script>
	<script>
		
	</script>
</body>
</html>