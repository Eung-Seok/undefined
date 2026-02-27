<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="card">
    <h3>📌 이슈 상세</h3>
    <table class="table">
        <tr><th>ID</th><td>${issue.id}</td></tr>
        <tr><th>제목</th><td>${issue.title}</td></tr>
        <tr><th>내용</th><td>${issue.content}</td></tr>
        <tr><th>작성자</th><td>${issue.reporterUserId}</td></tr>
        <tr><th>상태</th><td>${issue.status}</td></tr>
        <tr><th>작성일</th><td>${issue.createdAt}</td></tr>
        <tr><th>수정일</th><td>${issue.updatedAt}</td></tr>
    </table>
    <div class="card-footer">
        <a href="${pageContext.request.contextPath}/mypage/issue" class="btn small">← 목록</a>
        <a href="${pageContext.request.contextPath}/mypage/issue/edit/${issue.id}" class="btn small">수정</a>
        <a href="${pageContext.request.contextPath}/mypage/issue/delete/${issue.id}" class="btn small danger"
           onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
    </div>
</div>
</body>
</html>