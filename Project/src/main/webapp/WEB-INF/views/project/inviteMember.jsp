<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>참여자 추가</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/common/sidebar.css?v=5">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/project/project_member_add.css?v=5" />

</head>
<body>
	<div class="app">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="projects" />
		</jsp:include>

		<main class="main">
			<div class="card">
				<div class="card-head">
					<div>
						<h3 style="margin: 0;">참여자 추가</h3>
						<div class="muted">
							프로젝트: <b>${project.name}</b> (ID ${project.id})
						</div>
					</div>

					<div class="bulk">
						<select id="pmBulkRole">
							<option value="">(일괄 역할 지정)</option>
							<option value="MEMBER">MEMBER</option>
							<option value="PM">PM</option>
							<option value="VIEWER">VIEWER</option>
						</select>
						<button type="button" class="btn" id="pmApplyBulk">적용</button>
						<button type="button" class="btn" id="pmCheckAll">전체선택</button>
						<button type="button" class="btn" id="pmUncheckAll">해제</button>
					</div>
				</div>

				<form id="pmForm" method="post"
					action="<%=request.getContextPath()%>/project/members/add">
					<input type="hidden" name="projectId" value="${project.id}" />

					<div class="table-wrap">
						<table class="table">
							<thead>
								<tr>
									<th style="width: 44px;"></th>
									<th style="width: 110px;">사번</th>
									<th style="width: 160px;">이름</th>
									<th>이메일</th>
									<th style="width: 140px;">부서</th>
									<th style="width: 160px;">역할</th>
								</tr>
							</thead>
							<tbody id="pmTbody">
								<c:forEach var="u" items="${userList}">
									<tr class="pm-row" data-empno="${u.empno}"
										data-name="${u.name}" data-email="${u.email}"
										data-dept="${u.deptno}">
										<td><input class="pm-check" type="checkbox"
											name="selectedEmpnos" value="${u.empno}" /></td>
										<td>${u.empno}</td>
										<td>${u.name}</td>
										<td class="muted">${u.email}</td>
										<td class="muted">${deptMap[u.deptno]}</td>
										<td>
											<!-- 핵심: empno별 role 파라미터 --> <select class="pm-role"
											name="roleByEmpno[${u.empno}]" disabled>
												<option value="MEMBER" selected>MEMBER</option>
												<option value="PM">PM</option>
												<option value="VIEWER">VIEWER</option>
										</select>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<div class="footer">
						<div class="hint" id="pmHint">
							선택된 사용자: <b id="pmCount">0</b>명
						</div>
						<div class="actions">
							<button class="btn" type="button" id="pmCancel">취소</button>
							<button class="btn primary" type="submit" id="pmSubmit">선택
								추가</button>
						</div>
					</div>
				</form>
			</div>
		</main>
	</div>

	<script>
  // 캐시/컨텍스트 문제 방지
  window.APP_CTX = "<%=request.getContextPath()%>
		";
	</script>
	<script defer
		src="<%=request.getContextPath()%>/js/project/project_member_add.js?v=3"></script>
</body>
</html>