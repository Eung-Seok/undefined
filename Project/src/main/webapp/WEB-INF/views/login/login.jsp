<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- JSTL 사용을 위해 추가 --%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f4f7f6; }
        .login-box { max-width: 400px; margin: 100px auto; background: white; padding: 35px; border-radius: 12px; box-shadow: 0 8px 20px rgba(0,0,0,0.05); }
        .form-label { font-weight: bold; color: #333; }
        .btn-login { background-color: #0d6efd; color: white; border: none; padding: 12px; font-weight: bold; }
        /* 에러 메시지 스타일 */
        .error-msg { color: #dc3545; font-size: 0.85rem; text-align: center; margin-bottom: 15px; font-weight: bold; }
    </style>
</head>
<body>
<div class="login-box">
    <h4 class="text-center mb-4">로그인</h4>
    
    <form action="${pageContext.request.contextPath}/login" method="post">
        
        <%-- [수정된 부분] 컨트롤러에서 보낸 loginError가 있을 때만 표시됩니다 --%>
        <c:if test="${not empty loginError}">
            <div class="error-msg">${loginError}</div>
        </c:if>

        <div class="mb-3">
            <label class="form-label">사원번호</label>
            <input type="text" name="empno" class="form-control" placeholder="사원번호 입력" required>
        </div>

        <div class="mb-4">
            <label class="form-label">비밀번호</label>
            <input type="password" name="password" class="form-control" placeholder="비밀번호 입력" required>
        </div>
        
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="saveId" name="saveId">
            <label class="form-check-label" for="saveId">사원번호 저장</label>
        </div>

        <button type="submit" class="btn btn-login w-100 mb-3">로그인</button>
        
        <div class="text-center small">
            계정이 없으신가요? <a href="${pageContext.request.contextPath}/signup" class="text-decoration-none">회원가입</a>
        </div>
    </form>
</div>
</body>
</html>