<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>참여자 수정</title>
  <link rel="stylesheet" href="/css/common/sidebar.css">
  <link rel="stylesheet" href="/css/project/members.css">
</head>
<body>
<div class="app">
  <jsp:include page="/WEB-INF/views/common/sidebar.jsp" />

  <main class="main">
    <div class="card">
      <div class="card-head">
        <h3 class="card-title">참여자 수정</h3>
      </div>

      <form method="post" action="${pageContext.request.contextPath}/project/members/edit">
        <input type="hidden" name="projectId" value="${projectId}" />
        <input type="hidden" name="empno" value="${empno}" />

        <div style="margin: 12px 0;">
          <div style="font-size: 14px; margin-bottom: 6px;">이름</div>
          <input type="text" value="${name}" readonly
                 style="width: 320px; padding: 10px; border: 1px solid #e5e7eb; border-radius: 10px;" />
        </div>

        <div style="margin: 12px 0;">
          <div style="font-size: 14px; margin-bottom: 6px;">역할</div>
          <select name="projectRole"
                  style="width: 320px; padding: 10px; border: 1px solid #e5e7eb; border-radius: 10px;">
            <option value="MEMBER" ${projectRole == 'MEMBER' ? 'selected' : ''}>MEMBER</option>
            <option value="PM" ${projectRole == 'PM' ? 'selected' : ''}>PM</option>
          </select>
        </div>

        <div style="display:flex; gap:10px; margin-top: 18px;">
          <button type="submit" class="btn primary">저장</button>
          <a class="btn" href="${pageContext.request.contextPath}/project/members?projectId=${projectId}">취소</a>
        </div>
      </form>
    </div>
  </main>
</div>
</body>
</html>