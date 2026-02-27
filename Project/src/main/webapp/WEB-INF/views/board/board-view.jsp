<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>

<link rel="stylesheet" href="/css/common/sidebar.css">
<link href="${pageContext.request.contextPath}/css/board/board-view.css"
	rel="stylesheet">
<style>
.app {
	align-items: flex-start !important;
}

.card {
	padding: 30px;
	width: 100%;
	min-height: 550px;
	display: block !important;
}

.content-body {
	margin-top: 15px;
	line-height: 1.6;
	white-space: pre-wrap;
	min-height: 350px;
}

.btn-area {
	display: flex;
	justify-content: flex-end;
	gap: 10px;
	margin-top: 30px;
	border-top: 1px solid #eee;
	padding-top: 20px;
}

.btn {
	text-decoration: none;
	padding: 8px 18px;
	border-radius: 4px;
	font-size: 14px;
}

.btn-list {
	color: #333;
	border: 1px solid #ccc;
}

.btn-edit {
	color: white;
	background: #007bff;
}

.btn-delete {
	border: 1px solid #dc3545;
	color: #dc3545;
	background: white;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="board" />
		</jsp:include>
		<main class="main">
			<div class="card">
				<div style="display: flex; gap: 10px; align-items: center;">
					<span
						style="background: #e7f0ff; color: #007bff; padding: 5px 10px; border-radius: 4px;">${post.name}</span>
					<span style="color: #666;">${post.authorName} ·
						${post.createdAt}</span>
				</div>
				<h2 style="margin: 20px 0; font-size: 24px; color: #333;">${post.title}</h2>
				<hr>
				<div class="content-body">${post.content}</div>

				<div class="btn-area">
					<a href="${pageContext.request.contextPath}/board"
						class="btn btn-list">목록</a>

					<%-- [핵심] 작성자 본인 사번 일치 혹은 PM 권한 체크 --%>
					<c:if
						test="${(loginUser.empno == post.authorUserId) || (loginUser.position == 'PM')}">
						<a
							href="${pageContext.request.contextPath}/board/edit?id=${post.id}"
							class="btn btn-edit">수정</a>
						<button type="button" class="btn btn-delete"
							onclick="deletePost(${post.id})">삭제</button>
					</c:if>
				</div>
			</div>
		</main>
	</div>
	<script>
    function deletePost(id) {
        if (confirm("이 글을 정말 삭제하시겠습니까?")) {
            location.href = "${pageContext.request.contextPath}/board/delete?id=" + id;
        }
    }
    </script>
</body>
</html>