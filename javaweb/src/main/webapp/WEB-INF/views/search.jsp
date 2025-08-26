<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>搜尋會員</title>
</head>
<body>
    <div style="margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}">
            <button type="button">返回首頁</button>
        </a>
    </div>
    <h2>搜尋會員</h2>
    <form:form method="post" action="search" modelAttribute="searchForm">
        <div>
            <form:label path="username">Username：</form:label>
            <form:input path="username" id="username" />
        </div>
        <div style="margin-top: 20px;">
            <button type="submit">搜尋</button>
        </div>
    </form:form>
    <c:if test="${not empty searchError}">
        <ul>
            <c:forEach var="entry" items="${searchError}">
                <li>${entry.key}: ${entry.value}</li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${not empty userList}">
        <table border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>id</th>
                <th>role</th>
                <th>username</th>
                <th>password</th>
                <th>email</th>
                <th>nickname</th>
            </tr>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.role}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                    <td>${user.nickname}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>