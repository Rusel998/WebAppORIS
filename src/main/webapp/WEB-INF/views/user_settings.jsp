<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp" %>

<link rel="stylesheet" href="<c:url value='/styles/Settings.css'/>" />

<div class="settings-container">
    <h2>Настройки</h2>
    <h3>Измените ваши данные:</h3>

    <c:if test="${not empty error}">
        <p class="error-message">${error}</p>
    </c:if>

    <form action="<c:url value='/user-settings'/>" method="post" class="settings-form">

        <label>Никнейм:</label>
        <input type="text" name="username" value="${currentUser.username}" required />

        <label>Почта:</label>
        <input type="text" name="email" value="${currentUser.email}" required />

        <div class="button-row">
            <button type="submit" name="action" value="cancel" class="cancel-btn">
                Отменить
            </button>
            <button type="submit" name="action" value="save" class="save-btn">
                Сохранить
            </button>
        </div>

    </form>
</div>

<%@ include file="_footer.jsp" %>