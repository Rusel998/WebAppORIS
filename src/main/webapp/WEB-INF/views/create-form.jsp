<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_header.jsp"%>
<h2>Create Personal Form</h2>
<form action="<c:url value="personal-form-create"/>" method="post">
    <label>Bio:</label>
    <input type="text" name="bio" required><br><br>

    <label>Age:</label>
    <input type="number" name="age" required><br><br>

    <label>Birthdate (yyyy-mm-dd):</label>
    <input type="date" name="birthdate" required><br><br>

    <label>Gender:</label>
    <input type="text" name="gender" required><br><br>

    <input type="submit" value="Create">
</form>
<%@ include file="_footer.jsp"%>
