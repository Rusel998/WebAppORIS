<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>

<link rel="stylesheet" href="<c:url value='/styles/Admin.css'/>" />

<div class="admin-container">
    <h2>Добавить новый интерес</h2>
    <c:if test="${not empty errorMessage}">
        <p class="error-message">${errorMessage}</p>
    </c:if>

    <form action='${pageContext.request.contextPath}/admin/addInterest' method="post" class="admin-form">
        <label for="interestName">Название интереса:</label>
        <input type="text" name="interestName" id="interestName" required />

        <button type="submit" class="admin-btn">Сохранить</button>
    </form>
</div>

<%@ include file="../_footer.jsp" %>


