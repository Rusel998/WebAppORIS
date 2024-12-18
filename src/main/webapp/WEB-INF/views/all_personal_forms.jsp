<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="_header.jsp" %>

<h2>Все анкеты</h2>

<!-- Фильтр по интересам -->
<h3>Фильтр по интересам:</h3>
<a href="<c:url value='/personal-forms'/>">Все</a>
<c:forEach var="interest" items="${allInterests}">
    <a href="<c:url value='/personal-forms?interestId=${interest.id}'/>">${interest.name}</a>
</c:forEach>

<!-- Отображение анкет (без фото) -->
<div style="display:flex; flex-wrap: wrap; gap:20px;">
    <c:forEach var="form" items="${allForms}">
        <c:set var="user" value="${usersMap[form.userId]}"/>
        <div style="border:1px solid #ccc; width:200px; padding:10px; text-align:center;">
            <h4>${user.username}, age: ${form.age}</h4>
            <p>BIO: ${form.bio}</p>
            <p>Contact Email: ${user.email}</p>
        </div>
    </c:forEach>
</div>

<%@ include file="_footer.jsp" %>
