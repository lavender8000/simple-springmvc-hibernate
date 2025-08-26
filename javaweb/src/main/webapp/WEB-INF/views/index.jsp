<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>會員系統</title>
    <style>
        .btn {
            margin-top: 10px;
        }
        .other {
            margin-top: 40px;
        }
    </style>
</head>
<body>
    <h2>會員系統</h2>
    <div class="auth">
        <div class="btn">
            <sec:authorize access="isAnonymous()">
                <a href="${pageContext.request.contextPath}/login">
                    <button type="button">登入會員</button>
                </a>
                <a href="${pageContext.request.contextPath}/register">
                    <button type="button">註冊會員</button>
                </a>
                <a href="${pageContext.request.contextPath}/forgot">
                    <button type="button">忘記密碼</button>
                </a>
            </sec:authorize>
        </div>
    </div>
    <div class="other">
        <div class="btn">
            <sec:authorize access="isAuthenticated()">
                <a href="${pageContext.request.contextPath}/profile">
                    <button type="button">會員設定</button>
                </a>
            </sec:authorize>
        </div>
        <div class="btn">
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a href="${pageContext.request.contextPath}/search">
                    <button type="button">查詢學生</button>
                </a>
            </sec:authorize>
        </div>
        <div class="btn">
            <sec:authorize access="isAuthenticated()">
                <a href="${pageContext.request.contextPath}/logout">
                    <button type="button">登出會員</button>
                </a>
            </sec:authorize>
        </div>
    </div>

    <c:if test="${param.login != null}">
        <div><p style="color:green">登入成功</p></div>
    </c:if>
    <c:if test="${param.logout != null}">
        <div><p style="color:green">登出成功</p></div>
    </c:if>
    <c:if test="${param.create == 'user'}">
        <div><p style="color:green">註冊成功</p></div>
    </c:if>
</body>
</html>