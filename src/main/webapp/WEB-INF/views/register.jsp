<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Регистрация</title>
    <link rel="stylesheet" href="<c:url value='/styles/Registration_Login.css' />" />
</head>

<body>
<div class="wrapper">
    <div class="title">Регистрация</div>

    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <form action="<c:url value='/register'/>" method="POST">
        <div class="field">
            <input type="text" name="username" required>
            <label>Никнейм</label>
        </div>

        <div class="field">
            <input type="text" name="email" required>
            <label>Почта</label>
        </div>

        <div class="field">
            <input type="password" name="password" required>
            <label>Пароль</label>
        </div>

        <div class="show-password-container">
            <input type="checkbox" id="show-password" onclick="togglePasswordVisibility()">
            <label for="show-password">Показать пароль</label>
        </div>

        <div class="field">
            <input type="submit" value="Отправить">
        </div>

        <div class="login-link">
            Есть аккаунт? <a href="<c:url value='/login'/>">Войти.</a>
        </div>
    </form>
</div>

<script src="<c:url value='/JS/hide_password.js'/>"></script>
</body>
</html>

