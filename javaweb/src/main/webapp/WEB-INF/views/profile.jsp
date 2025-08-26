<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>會員個人頁面</title>
</head>
<body>
<div class="profile-container">
    <div style="margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}">
            <button type="button">返回首頁</button>
        </a>
    </div>
    <h2>會員個人資料</h2>
    <table class="profile-table" border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>權限</th>
            <th>帳號</th>
            <th>密碼</th>
            <th>Email</th>
            <th>暱稱</th>
        </tr>
        <tr>
            <td><c:out value="${user.id}" /></td>
            <td>
                <c:forEach var="auth" items="${user.authorities}">
                    <div><c:out value="${auth.authority}" /></div>
                </c:forEach>
            </td>
            <td><c:out value="${user.username}" /></td>
            <td><c:out value="${user.password}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td><c:out value="${user.nickname}" default="尚未設定"/></td>
        </tr>
    </table>
    <div style="margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/edit">
            <button type="button">修改資料</button>
        </a>
        <a href="${pageContext.request.contextPath}/logout">
            <button type="button">登出會員</button>
        </a>
    </div>

</div>
</body>
</html>