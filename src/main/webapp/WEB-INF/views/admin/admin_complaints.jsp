<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>

<!-- Подключаем стили администратора -->
<link rel="stylesheet" href="<c:url value='/styles/Admin.css'/>" />

<div class="admin-container">
    <h2>Управление жалобами</h2>
    <table class="admin-table" id="complaintsTable">
        <tr>
            <th>ID</th>
            <th>Подал жалобу</th>
            <th>Получил жалобу</th>
            <th>Причина</th>
            <th>Время</th>
            <th>Статус</th>
            <th>Действия</th>
        </tr>
        <c:forEach var="complaint" items="${complaints}">
            <tr id="complaint-row-${complaint.id}">
                <td>${complaint.id}</td>
                <td>${userNames[complaint.complainantId]}</td>
                <td>${userNames[complaint.offenderId]}</td>
                <td>${complaint.reason}</td>
                <td>${complaint.datetime}</td>
                <td id="status-${complaint.id}">${complaint.status}</td>
                <td>
                    <c:if test="${complaint.status == 'pending'}">
                        <button class="admin-btn"
                                onclick="updateComplaintStatus(${complaint.id}, 'accepted')">
                            Принять
                        </button>
                        <button class="admin-btn danger-btn"
                                onclick="updateComplaintStatus(${complaint.id}, 'declined')">
                            Отклонить
                        </button>
                    </c:if>
                    <c:if test="${complaint.status != 'pending'}">
                        Рассмотрено.
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function updateComplaintStatus(id, status) {
        $.ajax({
            url: '${pageContext.request.contextPath}/admin/complaints',
            type: 'POST',
            data: { id: id, action: status },
            success: function() {
                $('#status-' + id).text(status);
                $('#complaint-row-' + id + ' td:last-child').text('Рассмотрено.');
            },
            error: function() {
                alert('Ошибка обновления статуса.');
            }
        });
    }
</script>

<%@ include file="../_footer.jsp" %>
