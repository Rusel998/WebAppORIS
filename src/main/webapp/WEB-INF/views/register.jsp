<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_header.jsp"%>
<style>
    <%@include file="/styles/Registration_Login.css"%>
</style>
<div class="wrapper">
    <div class="title">
        Registration
    </div>
<form action="<c:url value="register"/>" method="POST">
    <div class="field">
        <input type="text" name="username" required>
        <label>Username</label><br><br>
    </div>
    <div class="field">
        <input type="text" name="email" required>
        <label>Email Address</label><br><br>
    </div>
    <div class="field">
        <input type="password" name="password" required>
        <label>Password</label><br><br>
    </div>
    <div class="show-password-container">
        <input type="checkbox" id="show-password" onclick="togglePasswordVisibility()">
        <label for="show-password">Показать пароль</label>
    </div>
    <div class="field">
        <input type="submit" value="Register">
    </div>

    <div class="login-link">
        Already have an acc? <a href ="<c:url value ="/login"/>">Log in.</a>
    </div>
</form>
</div>
<script><%@include file="/JS/hide_password.js"%></script>
<%@include file="_footer.jsp"%>
