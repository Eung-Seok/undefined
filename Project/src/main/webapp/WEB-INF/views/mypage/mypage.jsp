<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mypage/mypage.css">
</head>
<body>
	<div class="app">

		<!-- 사이드바 -->
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp">
			<jsp:param name="activeMenu" value="mypage" />
		</jsp:include>

		<main class="main dashboard">

			<!-- ================== KPI 카드 ================== -->
			<div class="kpi-grid">
				<div class="kpi-card primary">
					<h4>오늘 할 일</h4>
					<div class="kpi-number">${todayTasks.size()}</div>
				</div>
				<div class="kpi-card success">

					<h4>프로젝트 공정율</h4>
					<div class="kpi-number">${projectProgress}%</div>
				</div>

				<div class="kpi-card info">
					<h4>주간 일정</h4>
					<div class="kpi-number">${weekScheduleCount}</div>
				</div>
				<div class="kpi-card success">
					<h4>보고서</h4>
					<div class="kpi-number">${reportCount}</div>
				</div>
				<div class="kpi-card warning">
					<h4>이슈</h4>
					<div class="kpi-number">${issueCount}</div>
				</div>
			</div>


			<!-- ================== 오늘 할 일 ================== -->
			<section class="card">
				<h3>오늘 내가 해야 할 일</h3>
				<c:choose>
					<c:when test="${not empty tasks}">
						<table class="table">
							<thead>
								<tr>
									<th>업무명</th>
									<th>상태</th>
									<th>시작일</th>
									<th>마감일</th>
									<th>진행률</th>
									<th>가중치 반영 진행률</th>
									<th>완료 처리</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="task" items="${tasks}">
									<tr>
										<td>${task.name}</td>
										<td>${task.status}</td>
										<td>${task.startDate}</td>
										<td>${task.dueDate}</td>
										<td>${task.progressPercent}%</td>
										<td>${task.weightedProgress}%</td>
										<td><c:if test="${task.status ne 'DONE'}">
												<form
													action="${pageContext.request.contextPath}/mypage/today/complete"
													method="post">
													<input type="hidden" name="taskId" value="${task.id}">
													<button type="submit" class="btn small success">완료
														처리</button>
												</form>
												<form
													action="${pageContext.request.contextPath}/mypage/project/updateProgress"
													method="post">
													<input type="hidden" name="projectId" value="1">
													<button type="submit" class="btn small primary">공정률
														갱신</button>
												</form>

											</c:if></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<p class="empty-text">오늘 해야 할 일이 없습니다.</p>
					</c:otherwise>
				</c:choose>
			</section>





			<!-- ================== 주간 캘린더 ================== -->
			<section class="card">
				<h3>🗓 주간 캘린더</h3>

				<div class="card-body">
					<div>
						오늘 일정 <b>${todayScheduleCount}</b>
					</div>
					<div>
						이번 주 총 <b>${weekScheduleCount}</b>
					</div>
				</div>

				<div class="card-footer">
					<a href="${pageContext.request.contextPath}/mypage/calendar/week"
						class="btn small">열기</a>
				</div>
			</section>


			<!-- ================== 주간 보고서 ================== -->
			<section class="card">
				<h3>📑 주간 보고서</h3>

				<div class="card-body">
					<div>
						총 보고서 수 <b>${reportCount}</b>
					</div>
				</div>

				<div class="card-footer">
					<a href="${pageContext.request.contextPath}/mypage/report/write"
						class="btn small primary">작성하기</a>
				</div>

				<c:choose>
					<c:when test="${not empty reports}">
						<table class="table">
							<thead>
								<tr>
									<th>제목</th>
									<th>기간</th>
									<th>작성자</th>
									<th>상세보기</th>
									<th>주간 공정율</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="report" items="${reports}">
									<tr>
										<td>${report.summary}</td>
										<td>${report.periodStart}~${report.periodEnd}</td>
										<td>${report.authorUserId}</td>
										<td><a
											href="${pageContext.request.contextPath}/mypage/report/${report.id}"
											class="btn small">보기</a></td>
										<td>${todayProgress}%</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<p class="empty-text">등록된 보고서가 없습니다.</p>
					</c:otherwise>
				</c:choose>
			</section>


			<!-- ================== 이슈 목록 ================== -->
			<section class="card">
				<h3>📌 이슈 목록</h3>

				<div class="card-footer">
					<a href="${pageContext.request.contextPath}/mypage/issue/write"
						class="btn small primary">이슈 작성</a>
				</div>

				<c:choose>
					<c:when test="${not empty issues}">
						<table class="table">
							<thead>
								<tr>
									<th>제목</th>
									<th>작성자</th>
									<th>상태</th>
									<th>상세보기</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="issue" items="${issues}">
									<tr>
										<td>${issue.title}</td>
										<td>${issue.reporterUserId}</td>
										<td>${issue.status}</td>
										<td><a
											href="${pageContext.request.contextPath}/mypage/issue/${issue.id}"
											class="btn small">보기</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<p class="empty-text">등록된 이슈가 없습니다.</p>
					</c:otherwise>
				</c:choose>
			</section>


			<!-- ================== 내 정보 ================== -->
			<section class="card">
				<h3>내 정보</h3>

				<table class="table info">
					<tr>
						<th>사번</th>
						<td>${sessionScope.loginUser.empno}</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${sessionScope.loginUser.name}</td>
					</tr>
					<tr>
						<th>직급</th>
						<td>${sessionScope.loginUser.position}</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${sessionScope.loginUser.email}</td>
					</tr>
				</table>
			</section>


			<!-- ================== 내 참여 업무 ================== -->
			<section class="card">
				<h3>내 참여 업무</h3>

				<c:choose>
					<c:when test="${not empty myAssignees}">
						<table class="table">
							<thead>
								<tr>
									<th>업무 ID</th>
									<th>상태</th>
									<th>참여일</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="a" items="${myAssignees}">
									<tr>
										<td>${a.taskId}</td>
										<td>${a.status}</td>
										<td>${a.joinedAt}</td>
										<td><c:if
												test="${sessionScope.loginUser.role eq 'ADMIN' or sessionScope.loginUser.role eq 'PM'}">
												<a
													href="${pageContext.request.contextPath}/mypage/task/assignees/delete/${a.id}"
													class="btn small danger"
													onclick="return confirm('삭제하시겠습니까?');"> 삭제 </a>
											</c:if></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<p class="empty-text">현재 참여 중인 업무가 없습니다.</p>
					</c:otherwise>
				</c:choose>

				<c:if
					test="${sessionScope.loginUser.role eq 'ADMIN' or sessionScope.loginUser.role eq 'PM'}">
					<div class="card-footer">
						<form
							action="${pageContext.request.contextPath}/mypage/task/assignees/add"
							method="post">
							<input type="hidden" name="taskId" value="123"> <input
								type="hidden" name="userId"
								value="${sessionScope.loginUser.empno}"> <input
								type="hidden" name="status" value="참여중">

							<button type="submit" class="btn small primary">담당자 등록</button>
						</form>
					</div>
				</c:if>
			</section>


			<!-- ================== 권한 테스트 ================== -->
			<section class="card">
				<h3>권한 테스트</h3>
				<p class="small">버튼은 role에 따라 숨김 처리 (프론트 데모)</p>

				<div class="btn-group">
					<button class="btn" data-action="MEMBER 기능">MEMBER 기능</button>
					<button class="btn" data-requires="PM,ADMIN"
						data-action="PM/ADMIN 기능">PM/ADMIN 기능</button>
				</div>
			</section>

		</main>
	</div>
	<script src="${pageContext.request.contextPath}/js/mypage/mypage.js"></script>
</body>
</html>
