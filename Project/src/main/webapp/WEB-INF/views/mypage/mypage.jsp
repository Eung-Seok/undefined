<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>마이페이지</title>

<!-- CSS 불러오기 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage/mypage.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/sidebar.css">

</head>
<body>

<div class="app">

	<!-- 사이드바 include -->
	<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
		<jsp:param name="activeMenu" value="mypage" />
	</jsp:include>

	<!-- 메인 콘텐츠 -->
	<div class="main mypage">

		<h2 class="grid cols-2">마이페이지</h2>

		<!-- 내프로필 -->
		<div class="card mypage-card">
			<div class="card-header">
				<h3>내프로필</h3>
				<button class="btn-edit" id="profileBtn">수정</button>
			</div>

			<div class="card-body">
				<p><span>이름</span> ${user.name}</p>
				<p><span>이메일</span> ${user.email}</p>
				<p><span>부서</span> ${user.deptno}</p>
				<p><span>직급</span> ${user.position}</p>
			</div>
		</div>

		<!-- 보안설정 -->
		<div class="card mypage-card">
			<div class="card-header">
				<h3>보안설정</h3>
				<button class="btn-edit" id="passwordBtn">변경</button>
			</div>

			<div class="card-body">
				<p>비밀번호 관리</p>
			</div>
		</div>

		<!-- 이력관리 -->
		<div class="card mypage-card">
			<div class="card-header">
				<h3>이력관리</h3>
			</div>

			<div class="card-body">
				<table class="history-table">
					<thead>
						<tr>
							<th>구분</th>
							<th>변경 전</th>
							<th>변경 후</th>
							<th>변경일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${historyList}" var="h">
							<tr>
								<td>${h.changeType}</td>
								<td>${h.beforeVal}</td>
								<td>${h.afterVal}</td>
								<td>${h.changeDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</div>
</div>

<!-- 내프로필 수정 모달 -->
<div id="profileModal" class="modal">
	<div class="modal-content">
		<h3>내프로필 수정</h3>

		<form action="/mypage/profile/update" method="post">
			<input type="hidden" name="empno" value="${user.empno}"/>

			<label>이메일</label>
			<input type="text" name="email" value="${user.email}"/>

			<label>부서</label>
			<input type="text" name="deptno" value="${user.deptno}"/>

			<label>직급</label>
			<input type="text" name="position" value="${user.position}"/>

			<div class="modal-btn-group">
				<button type="submit">저장</button>
				<button type="button" onclick="closeProfileModal()">취소</button>
			</div>
		</form>
	</div>
</div>

<!-- 비밀번호 변경 모달 -->
<div id="passwordModal" class="modal">
	<div class="modal-content">
		<h3>비밀번호 변경</h3>

		<form action="/mypage/security/password" method="post">
			<input type="hidden" name="empno" value="${user.empno}"/>

			<label>기존 비밀번호</label>
			<input type="password" name="oldPassword">

			<label>새 비밀번호</label>
			<input type="password" name="newPassword">

			<div class="modal-btn-group">
				<button type="submit">변경</button>
				<button type="button" onclick="closePasswordModal()">취소</button>
			</div>
		</form>
	</div>
</div>

<script>
document.addEventListener("DOMContentLoaded", function() {

  document.getElementById("profileBtn").addEventListener("click", function() {
	  console.log("버튼 클릭됨"); 
	  document.getElementById("profileModal").style.display = "flex";
  });

  document.getElementById("passwordBtn").addEventListener("click", function() {
	  console.log("버튼 클릭됨"); 
	  document.getElementById("passwordModal").style.display = "flex";
  });

});

function closeProfileModal() {
  document.getElementById("profileModal").style.display = "none";
}

function closePasswordModal() {
  document.getElementById("passwordModal").style.display = "none";
}
</script>

</body>
</html>