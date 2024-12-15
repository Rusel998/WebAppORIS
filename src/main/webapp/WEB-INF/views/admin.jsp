<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="_header.jsp"%>
<header>
    <h1>Admin Panel</h1>
    <nav>
        <a href="<c:url value="/logout">">Logout</a>
    </nav>
</header>
<main>
    <h2>Manage Complaints</h2>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Complaint Text</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="complaint" items="${complaints}">
            <tr>
                <td>${complaint.id}</td>
                <td>${complaint.text}</td>
                <td><a href="${pageContext.request.contextPath}/admin/deleteComplaint?id=${complaint.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
<%@include file="_footer.jsp"%>