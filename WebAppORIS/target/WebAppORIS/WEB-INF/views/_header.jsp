<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>W page</title>
      <link rel="stylesheet" href="<c:url value="/styles/Registration_Login.css"/>">

  </head>
  <body>
      <nav class="top">
            <a href="<c:url value="/"/>">Main page</a>
            <c:if test="${empty user}">
                <a href ="<c:url value ="/register"/>">Register</a>
                <a href ="<c:url value ="/login"/>">Log in</a>
            </c:if>
            <c:if test="${not empty user}">
                <a href = "<c:url value ="/profile"/>">Profile</a>
                <a href = "<c:url value ="/logout"/>">Sign out </a>
            </c:if>
      </nav>
      <div class="contents">