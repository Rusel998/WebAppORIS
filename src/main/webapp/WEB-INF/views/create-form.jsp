<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp" %>

<link rel="stylesheet" href="<c:url value='/styles/CreateForm.css'/>" />

<div class="create-container">
    <h2>Создать анкету</h2>
    <h3>Введите данные для новой анкеты</h3>

    <form action="<c:url value='/personal-form-create'/>"
          method="post"
          enctype="multipart/form-data"
          class="create-form">

        <label for="bio">О себе:</label>
        <input type="text" name="bio" id="bio" required />

        <label for="age">Возраст:</label>
        <input type="number" name="age" id="age" required />

        <label for="gender">Пол:</label>
        <input type="text" name="gender" id="gender" required />

        <label for="photo">Фото:</label>
        <input type="file" name="photo" id="photo" accept="image/*" />

        <button type="submit" class="create-btn">Создать</button>
    </form>
</div>

<%@ include file="_footer.jsp" %>
