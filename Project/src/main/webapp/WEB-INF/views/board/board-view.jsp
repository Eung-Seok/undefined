<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
<link href="${pageContext.request.contextPath}/css/board/board-view.css" rel="stylesheet">

<style>
.app { align-items: flex-start !important; }
.card { padding: 30px; width: 100%; min-height: 550px; display: block !important; box-sizing: border-box; }
.content-body { margin-top: 15px; line-height: 1.6; white-space: pre-wrap; min-height: 350px; }
.btn-area { display: flex; justify-content: flex-end; gap: 10px; margin-top: 30px; border-top: 1px solid #eee; padding-top: 20px; }
.btn { text-decoration: none; padding: 8px 18px; border-radius: 4px; font-size: 14px; }
.btn-list { color: #333; border: 1px solid #ccc; }
.btn-edit { color: white; background: #007bff; border: none; cursor: pointer; }
.btn-delete { border: 1px solid #dc3545; color: #dc3545; background: white; cursor: pointer; }

.comment-card { margin-top: 20px; }
.comment-item { border-bottom: 1px solid #eee; padding: 15px 0; display: flex; justify-content: space-between; }

.comment-form { margin-bottom: 20px; display: flex; gap: 10px; }
.comment-textarea { flex-grow: 1; padding: 10px; border: 1px solid #ccc; border-radius: 4px; resize: none; height: 50px; }

.pagination { text-align: center; margin-top: 25px; font-size: 14px; }
.pagination a { padding: 6px 12px; text-decoration: none; color: #333; border: 1px solid #eee; margin: 0 2px; border-radius: 4px; }
.pagination a.active { background-color: #007bff; color: white; border: 1px solid #007bff; }
</style>
</head>

<body>
<div class="app">
<jsp:include page="/WEB-INF/views/common/sidebar.jsp" />

<main class="main">

<div class="card">
    <div style="display:flex; gap:10px;">
        <span style="background:#e7f0ff; color:#007bff; padding:5px 10px; border-radius:4px;">
            ${post.name}
        </span>
        <span style="color:#666;">${post.authorName} · ${post.createdAt}</span>
    </div>

    <h2 style="margin:20px 0;">${post.title}</h2>
    <hr>
    <div class="content-body">${post.content}</div>

    <div class="btn-area">
        <a href="${pageContext.request.contextPath}/board" class="btn btn-list">목록</a>

        <c:if test="${(loginUser.empno == post.authorUserId) || (loginUser.position == 'PM')}">
            <a href="${pageContext.request.contextPath}/board/edit?id=${post.id}" class="btn btn-edit">수정</a>

            <!-- 🔥 삭제 버튼 (confirm 적용) -->
            <button type="button"
                    class="btn btn-delete"
                    onclick="deletePost(${post.id})">
                삭제
            </button>
        </c:if>
    </div>
</div>


<!-- 댓글 영역 -->
<div class="card comment-card">

<h3>댓글 <span style="color:#007bff;">${totalComments}</span></h3>

<!-- 댓글 등록 -->
<form action="${pageContext.request.contextPath}/comment/create" method="post" class="comment-form">
    <input type="hidden" name="postId" value="${post.id}">
    <input type="hidden" name="authorUserId" value="${loginUser.empno}">
    <textarea name="content" placeholder="댓글을 입력하세요" class="comment-textarea" required></textarea>
    <button type="submit" class="btn btn-edit">등록</button>
</form>


<!-- 댓글 목록 -->
<c:forEach var="comment" items="${commentList}">
<div class="comment-item">

    <div style="flex:1;">
        <div>
            <span style="font-weight:bold;">${comment.authorName}</span>
            <span style="color:#666; font-size:13px;">${comment.createdAt}</span>
        </div>

        <div id="comment-view-${comment.id}" style="margin-top:8px;">
            ${comment.content}
        </div>

        <div id="comment-edit-${comment.id}" style="display:none; margin-top:8px;">
            <form action="${pageContext.request.contextPath}/comment/update" method="post">
                <input type="hidden" name="id" value="${comment.id}">
                <input type="hidden" name="postId" value="${post.id}">
                <textarea name="content" style="width:100%; height:60px;" required>${comment.content}</textarea>
                <button type="submit" class="btn btn-edit" style="font-size:12px;">저장</button>
                <button type="button" class="btn btn-list" style="font-size:12px;"
                        onclick="cancelEdit(${comment.id})">취소</button>
            </form>
        </div>
    </div>

    <c:if test="${(loginUser.empno == comment.authorUserId) || (loginUser.position == 'PM')}">
        <div style="display:flex; gap:5px;">
            <button type="button" class="btn btn-edit"
                    style="font-size:12px;"
                    onclick="editComment(${comment.id})">수정</button>

            <form action="${pageContext.request.contextPath}/comment/delete"
                  method="post"
                  onsubmit="return confirm('댓글을 삭제하시겠습니까?');">
                <input type="hidden" name="id" value="${comment.id}">
                <input type="hidden" name="postId" value="${post.id}">
                <button type="submit" class="btn btn-delete" style="font-size:12px;">삭제</button>
            </form>
        </div>
    </c:if>

</div>
</c:forEach>

<c:if test="${empty commentList}">
    <div style="text-align:center; padding:30px; color:#666;">
        등록된 댓글이 없습니다.
    </div>
</c:if>

<!-- 🔥 댓글 페이징 -->
<c:if test="${totalPages > 1}">
<div class="pagination">
    <c:forEach begin="1" end="${totalPages}" var="i">
        <a href="${pageContext.request.contextPath}/board/view?id=${post.id}&page=${i}"
           class="${i == currentPage ? 'active' : ''}">
            ${i}
        </a>
    </c:forEach>
</div>
</c:if>

</div>
</main>
</div>

<script>
function deletePost(id) {
    if (confirm("이 글을 정말 삭제하시겠습니까?")) {
        location.href = "${pageContext.request.contextPath}/board/delete?id=" + id;
    }
}

function editComment(id){
    document.getElementById("comment-view-" + id).style.display = "none";
    document.getElementById("comment-edit-" + id).style.display = "block";
}

function cancelEdit(id){
    document.getElementById("comment-view-" + id).style.display = "block";
    document.getElementById("comment-edit-" + id).style.display = "none";
}
</script>

</body>
</html>