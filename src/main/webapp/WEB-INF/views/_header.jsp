<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <title>Uzel'sDating</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link rel="stylesheet" href="<c:url value='/styles/Common.css'/>">
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
</head>

<body>
<header class="header">
  <div class="logo-container">
    <img src="<c:url value='/images/logo.webp'/>" alt="Logo" class="logo_of_the_site">
    <span class="site-name">Uzel'sDating</span>
  </div>
  <ul>
    <li><a href="<c:url value='/'/>">Главная</a></li>
    <li><a href="https://t.me/iblameuzel">Связь</a></li>
    <li><a href="<c:url value='/my-form'/>">Моя анкета</a></li>
    <li><a href="<c:url value='/personal-forms'/>">Просмотр анкет</a></li>
    <c:if test="${empty sessionScope.email}">
      <li><a href="<c:url value='/login'/>">Войти</a></li>
    </c:if>
    <c:if test="${not empty sessionScope.email}">
      <li><a href="<c:url value='/profile' />">Профиль</a></li>
    </c:if>
    <c:if test="${sessionScope.role == 'admin'}">
      <li>
        <a href="${pageContext.request.contextPath}/admin/dashboard">Вернуться на админ-каталог</a>
      </li>
    </c:if>
  </ul>
</header>
