<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>404</title>
    <link rel="stylesheet" href="<c:url value='/styles/Errors.css'/>" />
</head>
<body>
<div class="error-container">
    <h1>404</h1>
    <img src="<c:url value='/images/404.jpg'/>" alt="error404img" />
    <p>страница не найдена</p>
    <a href="<c:url value='/'/>" class="btn-home">Вернуться на главную</a>
</div>
</body>
</html>
