<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>오늘 할 일</title>
</head>
<body>
    <h2>✅ 오늘 할 일</h2>

    <!-- 등록 폼 -->
    <form action="${pageContext.request.contextPath}/mypage/today/add" method="post">
        <input type="text" name="name" placeholder="작업명"/>
        <input type="text" name="description" placeholder="설명"/>
        <input type="date" name="dueDate"/>
        <button type="submit">추가</button>
    </form>

    <!-- 목록 -->
    <c:if test="${not empty tasks}">
        <table border="1">
            <tr>
                <th>작업명</th>
                <th>상태</th>
                <th>마감일</th>
                <th>액션</th>
            </tr>
            <c:forEach var="task" items="${tasks}">
                <tr>
                    <td>${task.name}</td>
                    <td>${task.status}</td>
                    <td>${task.dueDate}</td>
                    <td>
                        <!-- 상태 변경 -->
                        <form action="${pageContext.request.contextPath}/mypage/today/updateStatus" method="post" style="display:inline;">
                            <input type="hidden" name="taskId" value="${task.id}"/>
                            <select name="status">
                                <option value="TODO" <c:if test="${task.status eq 'TODO'}">selected</c:if>>TODO</option>
                                <option value="DOING" <c:if test="${task.status eq 'DOING'}">selected</c:if>>DOING</option>
                                <option value="DONE" <c:if test="${task.status eq 'DONE'}">selected</c:if>>DONE</option>
                            </select>
                            <button type="submit">변경</button>
                        </form>
                        <!-- 삭제 -->
                        <a href="${pageContext.request.contextPath}/mypage/today/delete?id=${task.id}">삭제</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty tasks}">
        <p>오늘 할 일이 없습니다.</p>
    </c:if>
</body>
</html>