<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>

<link rel="stylesheet" href="<c:url value='/styles/Admin.css'/>" />

<div class="admin-container">
    <h2>Управление анкетами пользователей</h2>

    <table class="admin-table" id="personalFormsTable">
        <tr>
            <th>ID</th>
            <th>О себе</th>
            <th>Возраст</th>
            <th>Пол</th>
            <th>Фото</th>
            <th>Действия</th>
        </tr>
        <c:forEach var="form" items="${personalForms}">
            <tr id="row-${form.id}">
                <td>${form.id}</td>
                <td>${form.bio}</td>
                <td>${form.age}</td>
                <td>${form.gender}</td>
                <td>
                    <c:choose>
                        <c:when test="${form.photo != null}">
                            <img src="<c:url value='/photo?formId=${form.id}'/>"
                                 alt="User Photo" width="100" />
                        </c:when>
                        <c:otherwise>
                            Нет фото
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <button class="admin-btn danger-btn" onclick="deletePersonalForm(${form.id})">
                        Удалить
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
                alert('Ошибка при удалении анкеты.');
            }
        });
    }
</script>

<%@ include file="../_footer.jsp" %>


