<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp" %>

<!-- Подключаем стили, аналогичные createForm.css, но для редактирования -->
<link rel="stylesheet" href="<c:url value='/styles/EditForm.css'/>" />

<div class="edit-container">
    <h2>Изменение анкеты</h2>
    <h3>Отредактируйте поля анкеты и сохраните</h3>

    <!-- Возможное сообщение об ошибке, если есть -->
    <c:if test="${not empty error}">
        <p class="error-message">${error}</p>
    </c:if>

    <form action="<c:url value='/personal-form-edit'/>"
          method="post"
          enctype="multipart/form-data"
          class="edit-form">

        <input type="hidden" name="id" value="${form.id}"/>

        <label for="bio">О себе:</label>
        <input type="text" name="bio" id="bio" value="${form.bio}" required />

        <label for="age">Возраст:</label>
        <input type="number" name="age" id="age" value="${form.age}" required />

        <label for="gender">Пол:</label>
        <input type="text" name="gender" id="gender" value="${form.gender}" required />

        <label for="photo">Изменить фото:</label>
        <input type="file" name="photo" id="photo" accept="image/*" />

        <button type="submit" class="edit-btn">Сохранить</button>
    </form>
</div>

<%@ include file="_footer.jsp" %>
