<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="_header.jsp"%>

    <p><strong>Username:</strong> ${username}</p>
    <p><strong>Email:</strong> ${email}</p>
    <form action="<c:url value="logout"/>" method="get">
        <button type="submit">Log Out</button>
    </form>

<%@include file="_footer.jsp"%>
