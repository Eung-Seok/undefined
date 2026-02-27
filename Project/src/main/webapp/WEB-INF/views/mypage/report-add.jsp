<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>📑 주간 보고서 작성</title>
    <link rel="stylesheet" href="/css/mypage/mypage.css">
</head>
<body>
<div class="app">
    <jsp:include page="/WEB-INF/views/common/sidebar.jsp">
        <jsp:param name="activeMenu" value="mypage" />
    </jsp:include>

    <main class="main">
        <div class="card">
            <h3>📑 주간 보고서 작성</h3>
            <form action="${pageContext.request.contextPath}/mypage/report/add" method="post">
                <table class="table">
                    <tr><th>프로젝트 ID</th><td><input type="text" name="projectId" required></td></tr>
                    <tr><th>기간 유형</th><td><input type="text" name="periodType" required></td></tr>
                    <tr><th>기간 시작</th><td><input type="date" name="periodStart" required></td></tr>
                    <tr><th>기간 종료</th><td><input type="date" name="periodEnd" required></td></tr>
                    <tr><th>요약</th><td><textarea name="summary" required></textarea></td></tr>
                </table>
                <button type="submit" class="btn primary">등록</button>
                <a href="${pageContext.request.contextPath}/mypage" class="btn">취소</a>
            </form>
        </div>
    </main>
</div>
</body>
</html>