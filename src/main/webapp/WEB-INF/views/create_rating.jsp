<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="_header.jsp" %>

<h2>Оценить анкету</h2>

<form action="<c:url value='/rating-create'/>" method="post">
    <input type="hidden" name="ratedUserId" value="${ratedUserId}"/>
    <p>Выберите оценку:</p>
    <label>
        <input type="radio" name="rating" value="1" required/> 1
    </label><br>
    <label>
        <input type="radio" name="rating" value="2"/> 2
    </label><br>
    <label>
        <input type="radio" name="rating" value="3"/> 3
    </label><br>
    <label>
        <input type="radio" name="rating" value="4"/> 4
    </label><br>
    <label>
        <input type="radio" name="rating" value="5"/> 5
    </label><br><br>
    <button type="submit">Отправить</button>
    <a href="<c:url value='/personal-forms'/>">Отмена</a>
</form>

<%@ include file="_footer.jsp" %>
