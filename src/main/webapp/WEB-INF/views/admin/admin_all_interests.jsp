<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>

<link rel="stylesheet" href="<c:url value='/styles/Admin.css'/>" />

<div class="admin-container">
  <h2>Список интересов</h2>
  <table class="admin-table">
    <tr>
      <th>ID</th>
      <th>Название</th>
      <th>Действие</th>
    </tr>
    <c:forEach var="interest" items="${allInterests}">
      <tr>
        <td>${interest.id}</td>
        <td>${interest.name}</td>
        <td>
          <form action="${pageContext.request.contextPath}/admin/deleteInterest"
                method="post" style="display:inline;">
            <input type="hidden" name="interestId" value="${interest.id}" />
            <button type="submit" class="admin-btn danger-btn">
              Удалить
            </button>
          </form>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>

<%@ include file="../_footer.jsp" %>
