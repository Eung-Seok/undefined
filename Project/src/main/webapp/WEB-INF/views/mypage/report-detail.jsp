<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>📑 보고서 상세</title>
    <link rel="stylesheet" href="/css/mypage/mypage.css">
</head>
<body>
<div class="app">
    <jsp:include page="/WEB-INF/views/common/sidebar.jsp">
        <jsp:param name="activeMenu" value="mypage" />
    </jsp:include>

    <main class="main">
        <div class="card">
            <h3>📑 보고서 상세</h3>
            <table class="table">
                <tr><th>보고서 ID</th><td>${report.id}</td></tr>
                <tr><th>프로젝트 ID</th><td>${report.projectId}</td></tr>
                <tr><th>작성자</th><td>${report.authorUserId}</td></tr>
                <tr><th>기간 유형</th><td>${report.periodType}</td></tr>
                <tr><th>기간</th><td>${report.periodStart} ~ ${report.periodEnd}</td></tr>
                <tr><th>요약</th><td>${report.summary}</td></tr>
                <tr><th>작성일</th><td>${report.createdAt}</td></tr>
            </table>

            <div class="card-footer">
                <!-- 돌아가기 -->
                <a href="${pageContext.request.contextPath}/mypage" class="btn small">← 돌아가기</a>

                <!-- 수정 버튼 -->
                <a href="${pageContext.request.contextPath}/mypage/report/edit/${report.id}" 
                   class="btn small">수정</a>

                <!-- 삭제 버튼 -->
                <a href="${pageContext.request.contextPath}/mypage/report/delete/${report.id}" 
                   class="btn small danger"
                   onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
            </div>
        </div>
    </main>
</div>
</body>
</html>