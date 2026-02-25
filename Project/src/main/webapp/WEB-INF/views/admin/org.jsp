<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>조직도 관리</title>
<link rel="stylesheet"
      href="<%=request.getContextPath()%>/css/admin/org.css?v=<%=System.currentTimeMillis()%>">
</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="adminOrg" />
		</jsp:include>
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

			<div class="card">
				<div class="admin-org-wrap">
					<div class="admin-org-header">
						<div class="admin-org-title">조직도(부서/직원)</div>
						<div class="admin-org-actions">
							<input id="adminOrgSearch" class="admin-org-input"
								placeholder="부서/직원 검색 (예: 개발부, 김부장)" />
							<button id="adminOrgReload" class="admin-org-btn" type="button">새로고침</button>
							<button id="adminOrgCollapseAll" class="admin-org-btn"
								type="button">전체 접기</button>
							<button id="adminOrgExpandAll" class="admin-org-btn"
								type="button">전체 펼치기</button>
						</div>
					</div>

					<div class="admin-org-card">
						<div class="admin-org-toolbar">
							<div>
								데이터: <span id="adminOrgStatus">로딩중…</span>
							</div>
							<div>
								API:
								<code>/admin/departments/tree?includeUsers=true</code>
							</div>
						</div>
						<div id="adminOrgRoot" class="admin-org-tree">
							<!-- Rendered Here -->
						</div>
					</div>
				</div>
			</div>
		</main>
	</div>
	<script>
  		window.APP_CTX = "<%=request.getContextPath()%>";
	</script>
	<script defer
        src="<%=request.getContextPath()%>/js/admin/org.js?v=<%=System.currentTimeMillis()%>"></script>

</body>
</html>