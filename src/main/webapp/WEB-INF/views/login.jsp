<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="_header.jsp"%>
<style>
    <%@include file="/styles/Registration_Login.css"%>
</style>
<div class="wrapper">
    <div class="title">
        Log in
    </div>

<form action="<c:url value="login"/>" method="POST">
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
        <input type="submit" value="Login">
    </div>

    <div class="register-link">
        Not a member? <a href ="<c:url value ="/register"/>">Register.</a>
    </div>

</form>
</div>
<script><%@include file="/JS/hide_password.js"%></script>
<%@ include file="_footer.jsp"%>