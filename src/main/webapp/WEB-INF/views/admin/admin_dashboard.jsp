<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../_header.jsp" %>

<link rel="stylesheet" href="<c:url value='/styles/Admin.css'/>" />

<div class="admin-container">
    <h1>Добро пожаловать в админ-панель!</h1>

    <nav class="admin-nav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/admin/personal-forms">Управление анкетами пользователей</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/complaints">Актуальные жалобы</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/addInterest">Добавить интерес</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/allInterests">Список всех интересов</a></li>
        </ul>
    </nav>
</div>

<%@ include file="../_footer.jsp" %>
