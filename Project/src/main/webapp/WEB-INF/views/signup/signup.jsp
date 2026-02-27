<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사원 등록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f4f7f6; }
        .signup-container { max-width: 450px; margin: 40px auto; background: #fff; padding: 35px; border-radius: 12px; box-shadow: 0 5px 15px rgba(0,0,0,0.08); }
        .form-label { font-weight: bold; color: #333; }
        .btn-reg { background-color: #2c3e50; color: white; border: none; padding: 12px; font-weight: bold; }
        .pw-msg { font-size: 0.85rem; margin-top: 5px; display: block; }
        .error { color: #dc3545; }
        .success { color: #198754; }
    </style>
</head>
<body>

<div class="signup-container">
    <h3 class="text-center mb-4">사원 가입</h3>
    <form action="${pageContext.request.contextPath}/signup" method="post" id="regForm">
        
        <div class="mb-3">
            <label class="form-label">사원번호</label>
            <input type="text" name="empno" id="empno" class="form-control" placeholder="사원번호" required>
        </div>

        <div class="mb-3">
            <label class="form-label">이메일</label>
            <input type="email" name="email" class="form-control" placeholder="example@company.com" required>
        </div>

        <div class="mb-3">
            <label class="form-label">비밀번호</label>
            <input type="password" name="password" id="password" class="form-control" placeholder="8자 이상 입력" required>
        </div>

        <div class="mb-3">
            <label class="form-label">비밀번호 확인</label>
            <input type="password" id="passwordConfirm" class="form-control" placeholder="비밀번호 다시 입력" required>
            <span id="pwConfirmMsg" class="pw-msg"></span>
        </div>

        <div class="mb-3">
            <label class="form-label">이름</label>
            <input type="text" name="name" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">부서번호</label>
            <input type="text" name="deptno" class="form-control" placeholder="부서코드 입력" required>
        </div>

        <button type="submit" class="btn btn-reg w-100 mt-2">사원 등록 완료</button>
    </form>
</div>

<script>
    const pwInput = document.getElementById('password');
    const pwConfirmInput = document.getElementById('passwordConfirm');
    const msgArea = document.getElementById('pwConfirmMsg');

    // 사용자가 입력할 때마다 실시간으로 일치 여부 확인
    function checkPasswordMatch() {
        if (pwConfirmInput.value === "") {
            msgArea.innerText = "";
            return;
        }

        if (pwInput.value === pwConfirmInput.value) {
            msgArea.innerText = "비밀번호가 일치합니다.";
            msgArea.className = "pw-msg success";
        } else {
            msgArea.innerText = "비밀번호가 일치하지 않습니다.";
            msgArea.className = "pw-msg error";
        }
    }

    pwInput.addEventListener('keyup', checkPasswordMatch);
    pwConfirmInput.addEventListener('keyup', checkPasswordMatch);

    // 폼 전송 시 최종 검사
    document.getElementById('regForm').onsubmit = function() {
        const pw = pwInput.value;
        const pwConfirm = pwConfirmInput.value;

        if(pw.length < 8) {
            alert("비밀번호는 최소 8자 이상이어야 합니다.");
            pwInput.focus();
            return false;
        }

        if(pw !== pwConfirm) {
            alert("비밀번호 확인이 일치하지 않습니다.");
            pwConfirmInput.focus();
            return false;
        }

        return true;
    };
</script>

</body>
</html>