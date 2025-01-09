<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="_header.jsp" %>

<link rel="stylesheet" href="<c:url value='/styles/ComplaintCreate.css'/>" />

<div class="complaint-container">
    <h2>Подать жалобу</h2>
    <h3>Опишите причину жалобы</h3>

    <c:if test="${not empty error}">
        <p class="error-message">${error}</p>
    </c:if>

    <form action="<c:url value='/complaint-create'/>" method="post" class="complaint-form">
        <input type="hidden" name="offenderId" value="${offenderId}" />

        <label for="reason">Причина жалобы:</label>
        <textarea name="reason" id="reason" required></textarea>

        <div class="button-row">
            <button type="submit" class="submit-btn">Отправить жалобу</button>
            <a href="<c:url value='/personal-forms'/>" class="cancel-link">Отмена</a>
        </div>
    </form>
</div>

<%@ include file="_footer.jsp" %>

