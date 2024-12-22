<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="_header.jsp"%>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<h2>Complaints Management</h2>
<table border="1" cellpadding="5" cellspacing="0" id="complaintsTable">
    <tr>
        <th>ID</th>
        <th>Complainant</th>
        <th>Offender</th>
        <th>Reason</th>
        <th>DateTime</th>
        <th>Status</th>
        <th>Actions</th>
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
                    <button onclick="updateComplaintStatus(${complaint.id}, 'accepted')">Accept</button>
                    <button onclick="updateComplaintStatus(${complaint.id}, 'declined')">Decline</button>
                </c:if>
                <c:if test="${complaint.status != 'pending'}">
                    No actions
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    function updateComplaintStatus(id, status) {
        $.ajax({
            url: '${pageContext.request.contextPath}/admin/complaints',
            type: 'POST',
            data: { id: id, action: status },
            success: function() {
                // Обновляем статус в таблице
                $('#status-' + id).text(status);
                // Удаляем кнопки действий
                $('#complaint-row-' + id + ' td:last-child').text('No actions');
            },
            error: function() {
                alert('Error updating complaint status.');
            }
        });
    }
</script>


<%@ include file="_footer.jsp" %>

