<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>會員登入頁面</title>
</head>
<body>
    <div style="margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}">
            <button type="button">返回首頁</button>
        </a>
    </div>
    <h2>登入會員</h2>
    <form method="post" action="${pageContext.request.contextPath}/login">
        <!-- hidden CSRF -->
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div>
            <label for="username">帳號：</label>
            <input type="text" id="username" name="username" required="required"/>
        </div>
        <div>
            <label for="password">密碼：</label>
            <input type="password" id="password" name="password" required="required"/>
        </div>
        <div style="margin-top: 20px;">
            <button type="submit">登入</button>
            <a href="${pageContext.request.contextPath}/forgot">
                <button type="button">忘記密碼</button>
            </a>
        </div>
    </form>
    <c:if test="${param.error != null}">
        <div><p style="color:red">登入失敗：帳號或密碼錯誤</p></div>
    </c:if>

</body>
</html>