<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  // Demo session user (Map) - replace with real login later
  if (session.getAttribute("loginUser") == null) {
    java.util.Map<String,Object> u = new java.util.HashMap<>();
    u.put("name", "홍길동");
    u.put("position", "사원");
    u.put("role", "MEMBER"); // ADMIN / PM / MEMBER / VIEWER
    session.setAttribute("loginUser", u);
  }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>문서</title>
  <style>
:root{
  --bg:#f6f7fb; --card:#ffffff; --text:#111827; --muted:#6b7280; --line:#e5e7eb;
  --pri:#2563eb; --pri2:#1d4ed8; --good:#16a34a; --warn:#f59e0b; --bad:#ef4444;
  --shadow:0 10px 24px rgba(17,24,39,.08);
}
*{box-sizing:border-box}
html,body{height:100%}
body{
  margin:0;
  font-family:system-ui,-apple-system,"Segoe UI",Roboto,"Noto Sans KR",Arial,sans-serif;
  background:var(--bg); color:var(--text);
}
a{color:inherit; text-decoration:none}
button,input,select{font:inherit}
.container{max-width:1200px; margin:0 auto}
.app{display:flex; min-height:100vh}
.sidebar{
  width:260px; background:#fff; border-right:1px solid var(--line);
  padding:18px 14px; position:sticky; top:0; height:100vh;
}
.brand{display:flex; gap:10px; align-items:center; margin-bottom:16px}
.logo{
  width:42px; height:42px; border-radius:14px;
  background:linear-gradient(135deg,var(--pri),#22c55e);
  display:flex; align-items:center; justify-content:center;
  color:#fff; font-weight:900;
}
.brand-title{font-size:14px; font-weight:900}
.brand-sub{font-size:12px; color:var(--muted)}
.profile{
  display:flex; gap:12px; align-items:center;
  border:1px solid var(--line); border-radius:16px;
  padding:12px; background:#fff;
  margin:12px 0 14px;
}
.avatar{
  width:42px; height:42px; border-radius:999px;
  background:#e5e7eb; display:flex; align-items:center; justify-content:center;
  font-weight:800; color:#374151;
}
.profile-name{font-weight:800}
.profile-role{font-size:12px; color:var(--muted); margin-top:2px}
.nav{display:flex; flex-direction:column; gap:6px; margin-top:10px}
.nav a{
  display:flex; gap:10px; align-items:center;
  padding:10px 12px; border-radius:12px;
  color:#111827;
}
.nav a:hover{background:#f3f4f6}
.nav a.active{background:#eef2ff; color:#1e3a8a; font-weight:800}
.nav .section{margin:10px 10px 4px; font-size:11px; color:var(--muted); text-transform:uppercase; letter-spacing:.08em}

.main{flex:1; padding:22px 22px 40px}
.topbar{
  display:flex; align-items:center; justify-content:space-between;
  gap:12px; margin-bottom:18px;
}
.search{
  flex:1; display:flex; gap:10px; align-items:center;
  background:#fff; border:1px solid var(--line); border-radius:14px;
  padding:10px 12px; box-shadow:0 1px 0 rgba(0,0,0,.02);
}
.search input{border:0; outline:0; width:100%}
.actions{display:flex; gap:10px; align-items:center}
.btn{
  border:1px solid var(--line); background:#fff; padding:10px 12px;
  border-radius:12px; cursor:pointer;
}
.btn.primary{background:var(--pri); border-color:var(--pri); color:#fff}
.btn:hover{filter:brightness(.98)}
.grid{display:grid; gap:14px}
.grid.cols-4{grid-template-columns:repeat(4,minmax(0,1fr))}
.grid.cols-2{grid-template-columns:repeat(2,minmax(0,1fr))}
.card{
  background:var(--card); border:1px solid var(--line);
  border-radius:18px; padding:16px; box-shadow:var(--shadow);
}
.card h3{margin:0 0 10px; font-size:14px}
.kpi{display:flex; align-items:center; justify-content:space-between}
.kpi .value{font-size:20px; font-weight:900}
.kpi .label{font-size:12px; color:var(--muted)}
.table{width:100%; border-collapse:collapse}
.table th,.table td{padding:10px 10px; border-bottom:1px solid var(--line); text-align:left; font-size:13px}
.badge{display:inline-flex; align-items:center; gap:6px; padding:4px 10px; border-radius:999px; border:1px solid var(--line); font-size:12px}
.badge.good{background:#ecfdf5; border-color:#bbf7d0; color:#065f46}
.badge.warn{background:#fffbeb; border-color:#fde68a; color:#92400e}
.badge.bad{background:#fef2f2; border-color:#fecaca; color:#991b1b}
.tabs{display:flex; gap:8px; flex-wrap:wrap; margin:12px 0 16px}
.tab{padding:8px 12px; border:1px solid var(--line); background:#fff; border-radius:999px; font-size:13px}
.tab.active{background:#eef2ff; border-color:#c7d2fe; font-weight:800; color:#1e3a8a}
.small{font-size:12px; color:var(--muted)}
hr.line{border:0; border-top:1px solid var(--line); margin:14px 0}
.notice{padding:10px 12px; border-radius:14px; background:#eef2ff; border:1px solid #c7d2fe; color:#1e3a8a; font-size:13px}
</style>
</head>
<body data-role="${sessionScope.loginUser.role}">
  <div class="app">
    <aside class="sidebar">
      <div class="brand">
        <div class="logo">P</div>
        <div>
          <div class="brand-title">프로젝트 통합 일정/업무 관리</div>
          <div class="brand-sub">Project Scheduler · JSP Demo UI</div>
        </div>
      </div>

      <div class="profile">
        <div class="avatar">👤</div>
        <div>
          <div class="profile-name"><c:out value="${sessionScope.loginUser.name}"/></div>
          <div class="profile-role"><c:out value="${sessionScope.loginUser.position}"/> · <c:out value="${sessionScope.loginUser.role}"/></div>
        </div>
      </div>

      <div class="nav">
        <div class="section">Main</div>
        <a class="" href="${pageContext.request.contextPath}/dashboard"><span>🏠</span>대시보드</a>
        <a class="active" href="${pageContext.request.contextPath}/projects"><span>📁</span>프로젝트</a>
        <a class="" href="${pageContext.request.contextPath}/calendar"><span>🗓️</span>일정</a>
        <a class="" href="${pageContext.request.contextPath}/board"><span>📝</span>게시판</a>
        <a class="" href="${pageContext.request.contextPath}/employees"><span>👥</span>직원정보</a>
        <a class="" href="${pageContext.request.contextPath}/mypage"><span>⚙️</span>마이페이지</a>

        <div class="section">Admin</div>
        <a data-requires="ADMIN" href="${pageContext.request.contextPath}/admin/users">👮 사용자 관리</a>
        <a data-requires="ADMIN" href="${pageContext.request.contextPath}/admin/roles">🔐 권한 관리</a>
        <a data-requires="ADMIN" href="${pageContext.request.contextPath}/admin/org">🏢 조직도 관리</a>
        <a data-requires="ADMIN" href="${pageContext.request.contextPath}/admin/system">🧰 시스템</a>
      </div>

      <hr class="line">
      <div class="small">※ 데모 UI: 권한별 숨김은 프론트 처리입니다(보안X)</div>
    </aside>

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

      <div class="tabs"><a class="tab" href="${pageContext.request.contextPath}/project/overview">개요</a><a class="tab" href="${pageContext.request.contextPath}/project/tasks">업무</a><a class="tab" href="${pageContext.request.contextPath}/project/calendar">프로젝트 캘린더</a><a class="tab" href="${pageContext.request.contextPath}/project/wbs">WBS</a><a class="tab" href="${pageContext.request.contextPath}/project/issues">이슈</a><a class="tab active" href="${pageContext.request.contextPath}/project/docs">문서</a><a class="tab" href="${pageContext.request.contextPath}/project/members">참여자</a><a class="tab" href="${pageContext.request.contextPath}/project/settings">설정</a></div>

      
<div class="card">
  <h3>문서</h3>
  <div class="small">요구사항/회의록/설계서 업로드(데모)</div>
  <div style="height:12px"></div>
  <button class="btn" data-action="문서 업로드">문서 업로드</button>
  <div style="height:12px"></div>
  <table class="table">
    <thead><tr><th>문서명</th><th>분류</th><th>업로드</th></tr></thead>
    <tbody>
      <tr><td>요구사항_v1.pdf</td><td>요구사항</td><td>2026-02-12</td></tr>
      <tr><td>회의록_0212.md</td><td>회의록</td><td>2026-02-12</td></tr>
    </tbody>
  </table>
</div>

    </main>
  </div>

  <script>
(function(){
  // Simple toast
  function toast(msg){
    const t=document.createElement('div');
    t.textContent=msg;
    t.style.position='fixed';
    t.style.right='18px';
    t.style.bottom='18px';
    t.style.padding='10px 12px';
    t.style.background='#111827';
    t.style.color='white';
    t.style.borderRadius='12px';
    t.style.boxShadow='0 10px 24px rgba(0,0,0,.18)';
    t.style.zIndex='9999';
    document.body.appendChild(t);
    setTimeout(()=>{t.style.opacity='0'; t.style.transition='opacity .2s';}, 1400);
    setTimeout(()=>{t.remove();}, 1700);
  }
  window.__toast = toast;

  // Role-based UI (front demo only)
  const role = document.body.getAttribute('data-role') || 'MEMBER';
  document.querySelectorAll('[data-requires]').forEach(el=>{
    const need = el.getAttribute('data-requires').split(',').map(s=>s.trim());
    if(!need.includes(role)) el.style.display='none';
  });

  // Quick action buttons
  document.querySelectorAll('[data-action]').forEach(btn=>{
    btn.addEventListener('click', ()=>toast(btn.getAttribute('data-action')+' (데모)'));
  });
})();
</script>
</body>
</html>
