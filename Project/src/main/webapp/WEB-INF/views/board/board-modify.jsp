<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<link
	href="${pageContext.request.contextPath}/css/board/board-write.css"
	rel="stylesheet">
	<link rel="stylesheet" href="/css/common/sidebar.css">
</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="board" />
		</jsp:include>

		<main class="main">
			<div class="card">
				<h2>게시글 수정</h2>
				<hr
					style="margin-bottom: 20px; border: 0; border-top: 1px solid #eee;">

				<form action="${pageContext.request.contextPath}/board/update"
					method="post">

					<input type="hidden" name="id" value="${post.id}">

					<div class="form-group" style="margin-bottom: 15px;">
						<label
							style="display: block; margin-bottom: 5px; font-weight: bold;">제목</label>
						<input type="text" name="title" value="${post.title}"
							style="width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px;"
							required>
					</div>

					<div class="form-group" style="margin-bottom: 20px;">
						<label
							style="display: block; margin-bottom: 5px; font-weight: bold;">내용</label>
						<textarea name="content" rows="15"
							style="width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px; resize: none;"
							required>${post.content}</textarea>
					</div>

					<div style="display: flex; justify-content: flex-end; gap: 10px;">
						<a
							href="${pageContext.request.contextPath}/board/view?id=${post.id}"
							class="btn"
							style="background: #eee; padding: 10px 20px; text-decoration: none; color: #333; border-radius: 4px;">취소</a>
						<button type="submit" class="btn primary"
							style="background: #007bff; color: white; border: none; padding: 10px 20px; border-radius: 4px; cursor: pointer;">수정
							완료</button>
					</div>
				</form>
			</div>
		</main>
	</div>
</body>
</html>