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
    <h3>📌 이슈 작성</h3>
    <form action="${pageContext.request.contextPath}/mypage/issue/add" method="post">
        <table class="table">
            <tr><th>제목</th><td><input type="text" name="title" required></td></tr>
            <tr><th>내용</th><td><textarea name="content" required></textarea></td></tr>
            <tr><th>상태</th><td><input type="text" name="status" value="OPEN"></td></tr>
        </table>
        <button type="submit" class="btn primary">등록</button>
        <a href="${pageContext.request.contextPath}/mypage/issue" class="btn">취소</a>
    </form>
</div>

</body>
</html>