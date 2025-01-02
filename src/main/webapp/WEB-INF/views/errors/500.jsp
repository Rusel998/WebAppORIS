<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>500</title>
    <link rel="stylesheet" href="<c:url value='/styles/Errors.css'/>" />
</head>
<body>
<div class="error-container">
    <h1>500</h1>
    <img src="<c:url value='/images/500.jpg'/>" alt="error500img" />
    <p>Дайте сайту отдохнуть :)</p>
    <a href="<c:url value='/'/>" class="btn-home">Вернуться на главную</a>
</div>
</body>
</html>