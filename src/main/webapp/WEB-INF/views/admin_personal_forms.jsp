<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="_header.jsp" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<h2>Управление анкетами пользователей</h2>
<table border="1" cellpadding="5" cellspacing="0" id="personalFormsTable">
    <tr>
        <th>ID</th>
        <th>Bio</th>
        <th>Age</th>
        <th>Birthdate</th>
        <th>Gender</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="form" items="${personalForms}">
        <tr id="row-${form.id}">
            <td>${form.id}</td>
            <td>${form.bio}</td>
            <td>${form.age}</td>
            <td>${form.birthdate}</td>
            <td>${form.gender}</td>
            <td>
                <button onclick="deletePersonalForm(${form.id})">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    function deletePersonalForm(id) {
        $.ajax({
            url: '${pageContext.request.contextPath}/admin/delete-personalform',
            type: 'POST',
            data: { id: id },
            success: function() {
                $('#row-' + id).remove();
            },
            error: function() {
                alert('Error deleting personal form.');
            }
        });
    }
</script>


<%@ include file="_footer.jsp" %>
