<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>登出會員</title>
</head>
<body>
    <div style="margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}">
            <button type="button">返回首頁</button>
        </a>
    </div>
    <h2>登出會員</h2>
    <form:form method="post" action="${pageContext.request.contextPath}/logout" modelAttribute="logoutForm">
        <div>
            <form:label path="username">帳號：</form:label>
            <form:input path="username" id="username" readonly="true" style="background-color: #e0e0e0;"/>
        </div>
        <div style="margin-top: 20px;">
            <button type="submit">確認登出</button>
        </div>
    </form:form>
</body>
</html>