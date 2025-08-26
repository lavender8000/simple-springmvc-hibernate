<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>會員設定</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script>
        $(document).ready(function() {
            const contextPath = "${pageContext.request.contextPath}";
            let emailValid = false;

            // 輸入信箱時，檢查格式
            $('#email').blur(function() {
                let email = $(this).val();
                let regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                $('#emailMsg').text('');

                if (!regex.test(email)) {
                    $('#emailMsg').text('Email格式不正確');
                    emailValid = false;
                } else {
                    $('#emailMsg').text('');
                    emailValid = true;
                }
            });

        });
        
    </script>
</head>
<body>
    <div style="margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}">
            <button type="button">返回首頁</button>
        </a>
    </div>
    <h2>修改會員資料</h2>
    <form:form method="post" action="edit" modelAttribute="editForm">
        <!-- 隱藏欄位 -->
        <form:hidden path="id" />
        
        <div>
            <form:label path="username">帳號：</form:label>
            <form:input path="username" id="username" readonly="true" style="background-color: #e0e0e0;"/>
        </div>
        <div>
            <form:label path="role">權限：</form:label>
            <form:select path="role" id="role" required="required">
                <form:option value="" label="請選擇權限" />
                <form:option value="ROLE_USER" label="ROLE_USER" />
                <form:option value="ROLE_ADMIN" label="ROLE_ADMIN" />
            </form:select>
        </div>
        <div>
            <form:label path="email">Email：</form:label>
            <form:input path="email" id="email" required="required"/>
            <span id="emailMsg" style="color:red"></span>
        </div>
        <div>
            <form:label path="nickname">暱稱：</form:label>
            <form:input path="nickname"/>
        </div>
        <div style="margin-top: 20px;">
            <button type="submit">確認修改</button>
        </div>
    </form:form>
    <c:if test="${not empty editError}">
        <ul>
            <c:forEach var="entry" items="${editError}">
                <li>${entry.key}: ${entry.value}</li>
            </c:forEach>
        </ul>
    </c:if>
</body>
</html>