<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <div class="profile-name">
                <c:out value="${sessionScope.loginUser.name}" />
            </div>
            <div class="profile-role">
                <c:out value="${sessionScope.loginUser.position}" />
            </div>
        </div>
    </div>

    <div class="nav">
        <div class="section">Main</div>
        <a class="${param.activeMenu == 'dashboard' ? 'active' : ''}" href="${pageContext.request.contextPath}/dashboard"><span>🏠</span>대시보드</a>
        <a class="${param.activeMenu == 'projects' ? 'active' : ''}" href="${pageContext.request.contextPath}/projects"><span>📁</span>프로젝트</a>
        <a class="${param.activeMenu == 'calendar' ? 'active' : ''}" href="${pageContext.request.contextPath}/calendar"><span>🗓️</span>일정</a>
        <a class="${param.activeMenu == 'board' ? 'active' : ''}" href="${pageContext.request.contextPath}/board"><span>📝</span>게시판</a>
        <a class="${param.activeMenu == 'employees' ? 'active' : ''}" href="${pageContext.request.contextPath}/employees"><span>👥</span>직원정보</a>
        <a class="${param.activeMenu == 'mypage' ? 'active' : ''}" href="${pageContext.request.contextPath}/mypage"><span>⚙️</span>마이페이지</a>

        <div class="section">Admin</div>
        <a class="${param.activeMenu == 'adminUsers' ? 'active' : ''}" data-requires="ADMIN"
            href="${pageContext.request.contextPath}/admin/users">👮 사용자 관리</a>
        <a class="${param.activeMenu == 'adminRoles' ? 'active' : ''}" data-requires="ADMIN"
            href="${pageContext.request.contextPath}/admin/roles">🔐 권한 관리</a>
        <a class="${param.activeMenu == 'adminOrg' ? 'active' : ''}" data-requires="ADMIN"
            href="${pageContext.request.contextPath}/admin/org">🏢 조직도 관리</a>
        <a class="${param.activeMenu == 'adminSystem' ? 'active' : ''}" data-requires="ADMIN"
            href="${pageContext.request.contextPath}/admin/system">🧰 시스템</a>
    </div>

    <hr class="line">
    <div class="small">※ 데모 UI: 권한별 숨김은 프론트 처리입니다(보안X)</div>
</aside>