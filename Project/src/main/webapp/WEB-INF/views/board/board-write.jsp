<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시글 작성</title>

<link href="/css/board/board-view.css" rel="stylesheet">
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
				<h3>게시글 작성</h3>
				<div style="height: 12px"></div>
				<div class="grid" style="gap: 10px">
					<div>
						<div class="small">분류</div>
						<select
							style="width: 100%; padding: 10px 12px; border: 1px solid var(--line); border-radius: 12px; background: #fff">
							<option>공지</option>
							<option>사내활동</option>
							<option>부서게시판</option>
						</select>
					</div>
					<div>
						<div class="small">제목</div>
						<input
							style="width: 100%; padding: 10px 12px; border: 1px solid var(--line); border-radius: 12px"
							placeholder="제목을 입력하세요" />
					</div>
					<div>
						<div class="small">내용</div>
						<textarea
							style="width: 100%; min-height: 180px; padding: 10px 12px; border: 1px solid var(--line); border-radius: 12px"
							placeholder="내용을 입력하세요"></textarea>
					</div>
					<div style="display: flex; gap: 10px; justify-content: flex-end">
						<a class="btn" href="${pageContext.request.contextPath}/board">취소</a>
						<button class="btn primary" data-action="게시글 등록">등록</button>
					</div>
				</div>
			</div>

		</main>
	</div>
	
	<script src="/js/board/board-view.js"></script>
</body>
</html>
