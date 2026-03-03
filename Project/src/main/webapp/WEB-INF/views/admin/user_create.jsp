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
<link rel="stylesheet" href="/css/common/sidebar.css">
<link rel="stylesheet" href="/css/admin/user_edit.css">

</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="adminUsers" />
		</jsp:include>

		<main class="main">
			<div class="card">
				<div class="admin-card">
					<div class="admin-card-head">
						<div>
							<h2 style="margin: 0;">사용자 생성</h2>
						</div>
						<div>
							<a class="btn btn-light"
								href="<%=request.getContextPath()%>/admin/users">목록</a>
						</div>
					</div>

					<form method="post"
						action="<%=request.getContextPath()%>/admin/users/create"
						class="admin-form">
						<div class="grid">

							<div class="field">
								<label>사번</label> <input type="text" name="empno" required />
							</div>

							<div class="field">
								<label>이메일</label> <input type="text" name="email" required />
							</div>

							<div class="field">
								<label>이름</label> <input type="text" name="name" />
							</div>

							<div class="field">
								<label>직급</label> <select name="position">
									<option value="부장">부장</option>
									<option value="차장">차장</option>
									<option value="과장">과장</option>
									<option value="대리">대리</option>
									<option value="주임">주임</option>
									<option value="사원">사원</option>
								</select>
							</div>

							<div class="field">
								<label>부서</label> <select name="deptno" required>
									<c:forEach var="d" items="${deptList}">
										<option value="${d.deptno}">${d.name}(DEPTNO
											${d.deptno})</option>
									</c:forEach>
								</select>
							</div>

							<div class="field">
								<label>상태</label> <select name="status">
									<option value="ACTIVE">ACTIVE</option>
									<option value="INACTIVE">INACTIVE</option>
									<option value="LOCKED">LOCKED</option>
								</select>
							</div>
						</div>

						<div class="field" style="margin-top: 14px;">
							<label>권한</label>
							<div class="chips">
								<c:forEach var="r" items="${roleList}">
									<label class="chip"> <input type="radio" name="role"
										value="${r.name}"> <span>${r.name}</span></label>
								</c:forEach>
							</div>
						</div>

						<div class="actions">
							<button type="submit" class="btn btn-primary">생성</button>
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