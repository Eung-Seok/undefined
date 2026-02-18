<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f8f9fa;
}

.login-container {
	max-width: 400px;
	margin: 100px auto;
	background: white;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>
	<div class="login-container">
		<h3 class="text-center mb-4">로그인</h3>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div class="mb-3">
				<label class="form-label">사원번호</label> <input type="email"
					name="email" class="form-control" required>
			</div>
			<div class="mb-3">
				<label class="form-label">비밀번호</label> <input type="password"
					name="password" class="form-control" required>
			</div>
			<button type="submit" class="btn btn-primary w-100 mb-3">로그인</button>
			
			<div class="mb-3 form-check">
				<input type="checkbox" class="form-check-input" id="rememberEmail"
					name="rememberEmail"> <label class="form-check-label"
					for="rememberEmail">아이디 저장</label>
			</div>
			
			<div class="text-center">
				<p class="mb-0 text-muted">계정이 없으신가요?</p>
				<a href="${pageContext.request.contextPath}/signup"
					class="text-decoration-none">회원가입</a>
			</div>

			
		</form>
	</div>
</body>
</html>