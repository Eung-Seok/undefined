<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>권한 관리</title>
<style>
</style>
<link rel="stylesheet" href="/css/admin/user_edit.css">
</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="adminUsers" />
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




			<div class="card">
				<div class="admin-card">
					<div class="admin-card-head">
						<div>
							<h2 style="margin: 0;">사용자 수정</h2>
							<div style="opacity: .7; margin-top: 4px;">
								사번: <b>${user.empno}</b>
							</div>
						</div>
						<div>
							<a class="btn btn-light"
								href="<%=request.getContextPath()%>/admin/users">목록</a>
						</div>
					</div>

					<form method="post"
						action="<%=request.getContextPath()%>/admin/users/update"
						class="admin-form">
						<input type="hidden" name="empno" value="${user.empno}" />

						<div class="grid">
							<div class="field">
								<label>이메일</label> <input type="text" name="email"
									value="${user.email}" required />
							</div>

							<div class="field">
								<label>이름</label> <input type="text" name="name"
									value="${user.name}" />
							</div>

							<div class="field">
								<label>직급</label> <input type="text" name="position"
									value="${user.position}" />
							</div>

							<div class="field">
								<label>부서</label> <select name="deptno" required>
									<c:forEach var="d" items="${deptList}">
										<option value="${d.deptno}"
											<c:if test="${d.deptno == user.deptno}">selected</c:if>>
											${d.name} (DEPTNO ${d.deptno})</option>
									</c:forEach>
								</select>
							</div>

							<div class="field">
								<label>상태</label> <select name="status">
									<option value="ACTIVE"
										<c:if test="${user.status == 'ACTIVE'}">selected</c:if>>ACTIVE</option>
									<option value="INACTIVE"
										<c:if test="${user.status == 'INACTIVE'}">selected</c:if>>INACTIVE</option>
									<option value="LOCKED"
										<c:if test="${user.status == 'LOCKED'}">selected</c:if>>LOCKED</option>
								</select>
							</div>
						</div>

						<div class="field" style="margin-top: 14px;">
							<label>권한</label>
							<div class="chips">
								<c:forEach var="r" items="${roleList}">
									<label class="chip"> <input type="radio"
										name="role" value="${r.name}"
										<c:if test="${userRole == r.name}">checked</c:if>>
										<span>${r.name}</span></label>
								</c:forEach>
							</div>
							<div class="help">체크된 권한으로 덮어쓰기(저장 시 기존 권한은 교체)</div>
						</div>

						<div class="actions">
							<button type="submit" class="btn btn-primary">저장</button>
							<a class="btn btn-light"
								href="<%=request.getContextPath()%>/admin/users">취소</a>
						</div>
					</form>
				</div>
			</div>

		</main>
	</div>
	<script src="/js/admin/user_edit.js"></script>
	<script>
		
	</script>
</body>
</html>
