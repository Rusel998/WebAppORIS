<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp"%>

<h2>Edit Personal Form</h2>
<form action="<c:url value='/personal-form-edit'/>" method="post">
    <input type="hidden" name="id" value="${form.id}"/>

    <label>Bio:</label>
    <input type="text" name="bio" value="${form.bio}" required><br><br>

    <label>Age:</label>
    <input type="number" name="age" value="${form.age}" required><br><br>

    <label>Birthdate (yyyy-mm-dd):</label>
    <input type="date" name="birthdate" value="${form.birthdate}" required><br><br>

    <label>Gender:</label>
    <input type="text" name="gender" value="${form.gender}" required><br><br>

    <label>Profile Views:</label>
    <input type="number" name="profileviews" value="${form.profileViews}" required><br><br>

    <input type="submit" value="Save Changes">
</form>

<%@ include file="_footer.jsp"%>
