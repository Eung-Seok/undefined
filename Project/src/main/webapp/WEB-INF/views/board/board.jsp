<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시판</title>

<link rel="stylesheet" href="/css/common/sidebar.css">
<link href="/css/board/board.css" rel="stylesheet">
<style>
/* 탭 버튼 강조를 위한 추가 CSS */
.board-filter-tabs .btn {
	background-color: white;
	color: #333;
	transition: all 0.2s;
	display: inline-block;
	text-align: center;
}

/* 클릭된(활성화된) 버튼 스타일 */
.board-filter-tabs .btn.active {
	background-color: #1a73e8 !important; /* 파란색 배경 */
	color: white !important; /* 흰색 글자 */
	border-color: #1a73e8 !important;
	font-weight: bold;
}

.board-filter-tabs .btn:hover:not(.active) {
	background-color: #f8f9fa;
	border-color: #bbb;
}
</style>
</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="board" />
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
				<h3>게시판</h3>
				<div class="board-filter-tabs"
					style="margin-bottom: 20px; display: flex; gap: 10px;">
					<a href="/board?boardId=0"
						class="btn ${empty selectedBoardId || selectedBoardId == 0 ? 'active' : ''}"
						style="padding: 10px 20px; border-radius: 20px; border: 1px solid #ddd; text-decoration: none;">전체게시판</a>

					<a href="/board?boardId=1"
						class="btn ${selectedBoardId == 1 ? 'active' : ''}"
						style="padding: 10px 20px; border-radius: 20px; border: 1px solid #ddd; text-decoration: none;">공지사항</a>

					<a href="/board?boardId=2"
						class="btn ${selectedBoardId == 2 ? 'active' : ''}"
						style="padding: 10px 20px; border-radius: 20px; border: 1px solid #ddd; text-decoration: none;">사내소통</a>

					<a href="/board?boardId=3"
						class="btn ${selectedBoardId == 3 ? 'active' : ''}"
						style="padding: 10px 20px; border-radius: 20px; border: 1px solid #ddd; text-decoration: none;">자유게시판</a>
				</div>

				<div class="small">공지/사내소통/부서게시판 (데모)</div>
				<div style="height: 12px"></div>
				<div
					style="display: flex; justify-content: space-between; gap: 10px; align-items: center">
					<div class="small">총${postList.size()}건</div>
					<a class="btn primary"
						href="${pageContext.request.contextPath}/board/write">글쓰기</a>
				</div>
				<div style="height: 12px"></div>
				<table class="table">
					<thead>
						<tr>
							<th>분류</th>
							<th>제목</th>
							<th>작성자</th>
							<th>일자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="post" items="${postList}">
							<tr class="clickable-row"
										data-href="${pageContext.request.contextPath}/board/view?id=${post.id}">
								<td>${post.name}</td>
								<td>${post.title}</td>
								<td>${post.authorName}</td>
								<td>${post.createdAt}</td>
							</tr>
						</c:forEach>

						<c:if test="${empty postList}">
							<tr>
								<td colspan="4" style="text-align: center;">등록된 게시글이 없습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</main>
	</div>
	<script src="${pageContext.request.contextPath}/js/board/board.js"></script>
</body>
</html>
