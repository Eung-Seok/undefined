<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f8f9fa;
}

.signup-container {
	max-width: 450px;
	margin: 80px auto;
	background: white;
	padding: 40px;
	border-radius: 12px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
}

.form-label {
	font-weight: 600;
	color: #495057;
}

.msg {
	font-size: 0.85rem;
	margin-top: 5px;
}

.error {
	color: #dc3545;
}

.success {
	color: #198754;
}
</style>
</head>
<body>

	<div class="signup-container">
		<h3 class="text-center mb-4">회원가입</h3>

		<form action="${pageContext.request.contextPath}/signup" method="post"
			id="signupForm">

			<div class="mb-3">
				<label for="email" class="form-label">사원번호</label>
				<div class="input-group">
					<input type="email" name="email" id="email" class="form-control"
						placeholder="example@email.com" required>
					<button type="button" class="btn btn-outline-primary"
						onclick="checkDuplicate()">중복확인</button>
				</div>
				<div id="emailMsg" class="msg"></div>
			</div>

			<div class="mb-3">
				<label for="password" class="form-label">비밀번호</label> <input
					type="password" name="password" id="password" class="form-control"
					placeholder="최소 8자 이상 입력" required>
				<div id="pwMsg" class="msg text-muted">영문, 숫자 포함 8자 이상 권장합니다.</div>
			</div>

			<div class="mb-3">
				<label for="name" class="form-label">이름</label> <input type="text"
					name="name" id="name" class="form-control" placeholder="성함 입력"
					required>
			</div>

			<button type="submit" class="btn btn-primary w-100 py-2 mt-3 fw-bold">가입하기</button>

			<div class="mt-4 text-center">
				<small class="text-muted">이미 계정이 있으신가요? <a
					href="${pageContext.request.contextPath}/login"
					class="text-decoration-none">로그인</a></small>
			</div>
		</form>
	</div>

	<script>
    // 폼 제출 시 검사
    document.getElementById('signupForm').onsubmit = function() {
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const name = document.getElementById('name').value;

        // 1. 비밀번호 길이 체크 (최소 8자)
        if (password.length < 8) {
            alert("비밀번호는 최소 8자 이상이어야 합니다.");
            document.getElementById('password').focus();
            return false;
        }

        // 2. 이름 길이 체크 (최소 2자)
        if (name.length < 2) {
            alert("이름을 정확히 입력해주세요 (최소 2자).");
            document.getElementById('name').focus();
            return false;
        }

        return true; // 모든 검사 통과 시 전송
    };

    // 중복 확인 버튼 (임시 로직)
    function checkDuplicate() {
        const email = document.getElementById('email').value;
        const emailMsg = document.getElementById('emailMsg');

        if(!email) {
            alert("이메일을 입력해주세요.");
            return;
        }

        // 실제로는 AJAX를 통해 서버(Controller)와 통신해야 합니다.
        // 현재는 UI 확인용으로 메시지만 띄웁니다.
        emailMsg.innerHTML = "사용 가능한 이메일입니다.";
        emailMsg.className = "msg success";