<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="_header.jsp" %>

<h2>Подать жалобу</h2>
<form action="<c:url value='/complaint-create'/>" method="post">
    <input type="hidden" name="offenderId" value="${offenderId}" />
    <label>Причина жалобы:</label><br>
    <textarea name="reason" required></textarea><br><br>
    <button type="submit">Отправить жалобу</button>
    <a href="<c:url value='/personal-forms'/>">Отмена</a>
</form>

<%@ include file="_footer.jsp" %>
