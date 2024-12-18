<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="_header.jsp"%>

    <p><strong>Username:</strong> ${user.get("username")}</p>
    <p><strong>Email:</strong> ${user.get("email")}</p>
    <form action="<c:url value="logout"/>" method="get">
        <button type="submit">Log Out</button>
    </form>

<%@include file="_footer.jsp"%>
