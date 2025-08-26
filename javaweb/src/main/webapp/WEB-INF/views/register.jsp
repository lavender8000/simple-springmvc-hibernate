<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>註冊頁面</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script>
        $(document).ready(function() {
            const contextPath = "${pageContext.request.contextPath}";
            let usernameValid = false;
            let emailValid = false;

            // 輸入帳號時，檢查格式並發送AJAX請求
            $('#username').blur(function() {
                let username = $(this).val();
                $('#usernameMsg').text('');
                $('#usernameOk').text('');

                // 檢查格式
                if (username.length < 3 || username.length > 20) {
                    $('#usernameMsg').text('帳號長度必須在3到20個字元之間');
                    usernameValid = false;
                    return;
                }
                
                let csrfToken = "${_csrf.token}";
                let csrfHeader = "${_csrf.headerName}";
                // 發送AJAX請求檢查帳號是否存在
                $.ajax({
                    url: contextPath + '/checkUsername',
                    type: 'POST',
                    data: { username: username },
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader(csrfHeader, csrfToken);  // 加上 CSRF header
                    },
                    success: function(response) {
                        if (response.exists) {
                            $('#usernameMsg').text('此帳號已被使用');
                            usernameValid = false;
                        } else {
                            $('#usernameOk').text('此帳號可以註冊');
                            usernameValid = true;
                        }
                    },
                    error: function() {
                        $('#usernameMsg').text('檢查帳號時發生錯誤');
                        usernameValid = false;
                    }
                });
            });

            // 輸入信箱時，檢查格式
            $('#email').blur(function() {
                let email = $(this).val();
                let regex = /^[\w.%+-]+@[\w.-]+\.[a-zA-Z]{2,6}$/;
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
    <h2>註冊會員</h2>
    <form:form method="post" action="register" modelAttribute="userForm">
        <div>
            <form:label path="username">帳號：</form:label>
            <form:input path="username" id="username" required="required"/>
            <span id="usernameMsg" style="color:red"></span>
            <span id="usernameOk" style="color:green"></span>
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
            <form:label path="password">密碼：</form:label>
            <form:password path="password" required="required"/>
        </div>
        <div style="margin-top: 20px;">
            <button type="submit" id="registerBtn">註冊</button>
        </div>
    </form:form>
    <c:if test="${not empty registerError}">
        <ul>
            <c:forEach var="entry" items="${registerError}">
                <li>${entry.key}: ${entry.value}</li>
            </c:forEach>
        </ul>
    </c:if>
</body>
</html>