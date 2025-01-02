<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="_header.jsp" %>

<h1>Добро пожаловать в админ-панель!</h1>
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/admin/personal-forms">Управление анкетами пользователей</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/complaints">Актуальные жалобы</a></li>
    </ul>
</nav>
<%@ include file="_footer.jsp" %>
