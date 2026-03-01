<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>문서</title>
<style>
</style>
<link rel="stylesheet" href="/css/project/docs.css">
<link rel="stylesheet" href="/css/common/sidebar.css">
</head>
<body>
	<div class="app">
		<c:set var="role" value="${sessionScope.loginUserRole}" />
		<c:set var="canManageMembers"
			value="${role == 'ADMIN' || role == 'PM'}" />
		<c:set var="notManageMembers" value="${role == 'MEMBER'}" />
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="projects" />
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

			<div class="tabs">
				<a class="tab"
					href="${pageContext.request.contextPath}/project/overview?projectId=${project.id}">
					개요 </a> <a class="tab"
					href="${pageContext.request.contextPath}/project/tasks?projectId=${project.id}">업무</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/calendar?projectId=${project.id}">프로젝트
					캘린더</a><a class="tab active"
					href="${pageContext.request.contextPath}/project/docs?projectId=${project.id}">문서</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/members?projectId=${project.id}">참여자</a><a
					class="tab"
					href="${pageContext.request.contextPath}/project/report?projectId=${project.id}">보고서</a>
				<c:if test="${canManageMembers}">
					<a class="tab"
						href="${pageContext.request.contextPath}/project/settings?projectId=${project.id}">설정</a>
				</c:if>
			</div>


			<div class="card">
				<h3>문서</h3>
				<div class="small">요구사항/회의록/설계서 업로드</div>
				<div style="height: 12px"></div>
				<button type="button" class="btn" id="openUploadModal">문서
					업로드</button>
				<div style="height: 12px"></div>
				<table class="table">
					<thead>
						<tr>
							<th>문서명</th>
							<th>분류</th>
							<th>업로드 일시</th>
							<th>관리</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty documentList}">
							<tr>
								<td colspan="4" style="text-align: center;">등록된 문서가 없습니다.</td>
							</tr>
						</c:if>
						<c:forEach var="doc" items="${documentList}">
							<tr>
								<td>📄 ${doc.originalFileName}</td>
								<td><span class="badge">${doc.category}</span></td>
								<td><fmt:formatDate value="${doc.createdAt}"
										pattern="yyyy-MM-dd" /></td>
								<td><a href="${doc.fileUrl}"
									download="${doc.originalFileName}"
									class="btn btn-sm btn-success"> 다운로드 </a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</main>
	</div>
	<!-- 문서 업로드 모달 -->
	<div id="uploadModal" class="modal hidden">
		<div class="modal-content">
			<h3>문서 업로드</h3>

			<form id="uploadForm"
				action="${pageContext.request.contextPath}/project/docs/upload"
				method="post" enctype="multipart/form-data" class="upload-form">

				<input type="hidden" name="projectId" value="${project.id}" />

				<div class="form-group">
					<label class="form-label">문서 분류</label> <select name="category"
						class="form-control">
						<option value="요구사항">요구사항</option>
						<option value="회의록">회의록</option>
						<option value="설계서">설계서</option>
					</select>
				</div>

				<div class="form-group">
					<label class="form-label">파일 선택</label> <input type="file"
						name="file" class="form-control file-input" required />
				</div>

				<div class="modal-actions">
					<button type="submit" class="btn primary">업로드</button>
					<button type="button" class="btn secondary" id="closeModal">취소</button>
				</div>

			</form>
		</div>
	</div>
	<script src="/js/project/docs.js"></script>
	<script>
		document.addEventListener("DOMContentLoaded", () => {
		
		 
		    document.querySelectorAll("select").forEach(select => {
		        select.addEventListener("change", function () {
		            this.blur();
		        });
		    });
		
		
		    document.querySelectorAll('input[type="file"]').forEach(file => {
		        file.addEventListener("change", function () {
		            this.blur();
		        });
		    });
		
		});
	</script>
</body>
</html>
