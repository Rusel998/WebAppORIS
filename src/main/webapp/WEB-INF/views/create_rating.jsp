<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="_header.jsp" %>

<link rel="stylesheet" href="<c:url value='/styles/RatingCreate.css'/>" />

<div class="rating-container">
    <h2>Оценить анкету</h2>
    <h3>Пожалуйста, выберите оценку</h3>

    <c:if test="${not empty error}">
        <p class="error-message">${error}</p>
    </c:if>

    <form action="<c:url value='/rating-create'/>" method="post" class="rating-form">
        <input type="hidden" name="ratedUserId" value="${ratedUserId}"/>

        <label class="rating-option">
            <input type="radio" name="rating" value="1" required/>
            <span>1</span>
        </label>
        <label class="rating-option">
            <input type="radio" name="rating" value="2"/>
            <span>2</span>
        </label>
        <label class="rating-option">
            <input type="radio" name="rating" value="3"/>
            <span>3</span>
        </label>
        <label class="rating-option">
            <input type="radio" name="rating" value="4"/>
            <span>4</span>
        </label>
        <label class="rating-option">
            <input type="radio" name="rating" value="5"/>
            <span>5</span>
        </label>

        <div class="button-row">
            <button type="submit" class="submit-btn">Отправить</button>
            <a href="<c:url value='/personal-forms'/>" class="cancel-link">Отмена</a>
        </div>
    </form>
</div>

<%@ include file="_footer.jsp" %>
