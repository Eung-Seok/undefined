<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>

<link href="${pageContext.request.contextPath}/css/board/board-view.css"
	rel="stylesheet">

<style>
.card {
	padding: 30px;
}

.form-group {
	margin-bottom: 20px;
}

.form-group label {
	display: block;
	margin-bottom: 6px;
	font-weight: bold;
}

input, select, textarea {
	width: 100%;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 4px;
}

textarea {
	min-height: 250px;
	resize: vertical;
}

.btn-area {
	display: flex;
	justify-content: flex-end;
	gap: 10px;
}

.btn {
	padding: 8px 18px;
	border-radius: 4px;
	cursor: pointer;
}

.btn.primary {
	background: #007bff;
	color: white;
	border: none;
}
</style>
</head>

<body>
<div class="app">

	<jsp:include page="/WEB-INF/views/common/sidebar.jsp" />

	<main class="main">
		<div class="card">

			<h3>게시글 작성</h3>

			<form action="${pageContext.request.contextPath}/board/write"
				  method="post">

				<!-- 게시판 선택 -->
				<div class="form-group">
					<label>게시판 분류</label>
					<select name="boardId" required>
						<option value="">게시판을 선택하세요</option>
						<option value="1">공지사항</option>
						<option value="2">사내소통</option>
						<option value="3">자유게시판</option>
					</select>
				</div>

				<!-- 제목 -->
				<div class="form-group">
					<label>제목</label>
					<input type="text" name="title" required />
				</div>

				<!-- 내용 -->
				<div class="form-group">
					<label>내용</label>
					<textarea name="content" required></textarea>
				</div>

				<!-- 버튼 -->
				<div class="btn-area">
					<button type="submit" class="btn primary">등록</button>
				</div>

			</form>

		</div>
	</main>

</div>
</body>
</html>