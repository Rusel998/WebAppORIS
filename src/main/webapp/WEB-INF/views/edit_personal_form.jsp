<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp"%>

<h2>Edit Personal Form</h2>

<!-- enctype="multipart/form-data" -->
<form action="<c:url value='/personal-form-edit'/>"
      method="post"
      enctype="multipart/form-data">

    <input type="hidden" name="id" value="${form.id}"/>

    <label>Bio:</label>
    <input type="text" name="bio" value="${form.bio}" required><br><br>

    <label>Age:</label>
    <input type="number" name="age" value="${form.age}" required><br><br>


    <label>Gender:</label>
    <input type="text" name="gender" value="${form.gender}" required><br><br>

    <!-- Поле для загрузки нового фото -->
    <label>New Photo:</label>
    <input type="file" name="photo" accept="image/*"><br><br>

    <input type="submit" value="Save Changes">
</form>

<%@ include file="_footer.jsp"%>

