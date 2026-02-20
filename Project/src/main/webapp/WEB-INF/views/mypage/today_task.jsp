<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>📌 오늘 할 일</h2>

<table border="1" width="100%">
    <thead>
        <tr>
            <th>프로젝트</th>
            <th>업무명</th>
            <th>상태</th>
            <th>우선순위</th>
            <th>마감일</th>
        </tr>
    </thead>

    <tbody>
        <c:forEach var="task" items="${list}">
            <tr>
                <td>${task.projectName}</td>
                <td>${task.name}</td>
                <td>${task.status}</td>
                <td>${task.priority}</td>
                <td>${task.dueDate}</td>
            </tr>
        </c:forEach>

        <c:if test="${empty list}">
            <tr>
                <td colspan="5" align="center">
                    오늘 할 일이 없습니다 🎉
                </td>
            </tr>
        </c:if>
    </tbody>
</table>