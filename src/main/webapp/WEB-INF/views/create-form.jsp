<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="_header.jsp"%>
<h2>Create Personal Form</h2>

<!-- ОБЯЗАТЕЛЬНО: enctype="multipart/form-data" -->
<form action="<c:url value='/personal-form-create'/>"
      method="post"
      enctype="multipart/form-data">

    <label>Bio:</label>
    <input type="text" name="bio" required><br><br>

    <label>Age:</label>
    <input type="number" name="age" required><br><br>

    <label>Gender:</label>
    <input type="text" name="gender" required><br><br>

    <!-- Добавляем поле для загрузки фото -->
    <label>Photo:</label>
    <input type="file" name="photo" accept="image/*"><br><br>

    <input type="submit" value="Create">
</form>
<%@ include file="_footer.jsp"%>
