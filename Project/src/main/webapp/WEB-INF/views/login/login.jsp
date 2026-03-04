<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        .error-msg { color: #dc3545; font-size: 0.85rem; text-align: center; margin-bottom: 15px; font-weight: bold; border: 1px solid #f5c6cb; background-color: #f8d7da; padding: 10px; border-radius: 5px; }
    </style>
</head>
<body>
<div class="login-box">
    
    <%-- 메시지 표시 영역 (로그인 실패 혹은 비밀번호 찾기 결과) --%>
    <c:if test="${not empty loginError}">
        <div class="error-msg">${loginError}</div>
    </c:if>

    <div id="loginSection">
        <h4 class="text-center mb-4">로그인</h4>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="mb-3">
                <label class="form-label">이메일</label>
                <input type="text" name="email" class="form-control" value="${cookie.savedEmpno.value}" placeholder="이메일 입력" required>
            </div>
            <div class="mb-4">
                <label class="form-label">비밀번호</label>
                <input type="password" name="password" class="form-control" placeholder="비밀번호 입력" required>
            </div>
            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="saveId" name="saveId" ${not empty cookie.savedEmpno.value ? 'checked' : ''}>
                <label class="form-check-label" for="saveId">이메일 저장</label>
            </div>
            <button type="submit" class="btn btn-login w-100 mb-3">로그인</button>
            <div class="text-center small">
                <a href="javascript:void(0)" onclick="toggleSection('findPw')" class="text-secondary mt-2 d-inline-block">비밀번호 찾기</a>
            </div>
        </form>
    </div>

    <div id="findPwSection" style="display:none;">
        <h4 class="text-center mb-4">비밀번호 찾기</h4>
        <form action="${pageContext.request.contextPath}/find-password" method="post">
            <p class="text-muted small text-center mb-4">사원번호와 이메일을 입력하시면<br>비밀번호를 바로 조회해 드립니다.</p>
            <div class="mb-3">
                <label class="form-label">사원번호</label>
                <input type="text" name="empno" class="form-control" placeholder="사원번호 입력" required>
            </div>
            <div class="mb-3">
                <label class="form-label">이메일</label>
                <input type="email" name="email" class="form-control" placeholder="이메일 입력" required>
            </div>
            <button type="submit" class="btn btn-warning w-100 mb-3">비밀번호 찾기</button>
            <div class="text-center small">
                <a href="javascript:void(0)" onclick="toggleSection('login')" class="text-decoration-none">로그인으로 돌아가기</a>
            </div>
        </form>
    </div>
</div>

<script>
    // 비밀번호 찾기 
    function toggleSection(target) {
        const loginSec = document.getElementById('loginSection');
        const findPwSec = document.getElementById('findPwSection');
        
        if(target === 'findPw') {
            loginSec.style.display = 'none';
            findPwSec.style.display = 'block';
        } else {
            loginSec.style.display = 'block';
            findPwSec.style.display = 'none';
        }
    }
</script>
</body>
</html>