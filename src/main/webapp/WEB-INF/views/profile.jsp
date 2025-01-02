<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp" %>

<link rel="stylesheet" href="<c:url value='/styles/Profile.css'/>" />

<div class="container">
    <div class="hero">

        <div class="image image-left">
            <img src="<c:url value='/images/profileImageLeft.jpeg'/>" alt="Left Photo" />
        </div>

        <div class="center-block">
            <h2>Профиль</h2>
            <p>Добро пожаловать! Здесь вы можете просматривать данные вашего аккаунта</p>
            <hr />
            <p><strong>Никнейм:</strong> ${username}</p>
            <p><strong>Почта:</strong> ${email}</p>

            <div class="profile-buttons">
                <form action="<c:url value='/user-settings'/>" method="get">
                    <button type="submit" class="profile-btn">Настройки</button>
                </form>
                <form action="<c:url value='/logout'/>" method="get">
                    <button type="submit" class="profile-btn logout-btn">Выйти</button>
                </form>
            </div>
        </div>

        <div class="image image-right">
            <img src="<c:url value='/images/profileImageRight.jpg'/>" alt="Right Photo" />
        </div>

    </div>
</div>

<%@ include file="_footer.jsp" %>

