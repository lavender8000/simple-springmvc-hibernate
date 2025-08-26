<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>忘記密碼</title>
</head>
<body>
    <div style="margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}">
            <button type="button">返回首頁</button>
        </a>
    </div>
    <h2>忘記密碼</h2>
    <form:form method="post" action="forgot" modelAttribute="forgotForm">
        <div>
            <form:label path="username">帳號：</form:label>
            <form:input path="username" id="username" required="required"/>
        </div>
        <div style="margin-top: 20px;">
            <button type="submit">發送新密碼</button>
        </div>
    </form:form>
    <c:if test="${not empty forgotError}">
        <ul>
            <c:forEach var="entry" items="${forgotError}">
                <li>${entry.key}: ${entry.value}</li>
            </c:forEach>
        </ul>
    </c:if>

</body>
</html>