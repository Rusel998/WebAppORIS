<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="_header.jsp" %>

<h2>Все анкеты</h2>

<!-- Фильтр по интересам -->
<div>
    <h3>Фильтр по интересам:</h3>
    <a href="<c:url value='/personal-forms'/>">Все</a>
    <c:forEach var="interest" items="${allInterests}">
        <a href="<c:url value='/personal-forms?interestId=${interest.id}'/>">${interest.name}</a>
    </c:forEach>
</div>

<div style="display:flex; flex-wrap: wrap; gap:20px;">
    <c:forEach var="form" items="${allForms}">
        <c:set var="user" value="${usersMap[form.userId]}"/>
        <c:set var="avgRating" value="${avgRatingsMap[form.userId]}"/>
        <c:set var="roundedRating" value="${roundedRatingsMap[form.userId]}"/>

        <div style="border:1px solid #ccc; width:200px; padding:10px; text-align:center;">
            <h4>${user.username}, ${form.age}</h4>
            <p>${form.bio}</p>
            <p>${user.email}</p>

            <c:choose>
                <c:when test="${roundedRating == 0}">No rating yet</c:when>
                <c:otherwise>
                    <c:forEach var="i" begin="1" end="5">
                        <c:if test="${i <= roundedRating}">
                            &#9733;
                        </c:if>
                        <c:if test="${i > roundedRating}">
                            &#9734;
                        </c:if>
                    </c:forEach>
                    <p>Average: ${avgRating}</p>
                </c:otherwise>
            </c:choose>

            <!-- Если пользователь авторизован, показываем ссылки "Оценить" и "Пожаловаться" -->
            <c:if test="${not empty sessionScope.email}">
                <a href="<c:url value='/rating-create?ratedUserId=${form.userId}'/>">Оценить</a> |
                <a href="<c:url value='/complaint-create?id=${form.userId}'/>">Пожаловаться</a>
            </c:if>
        </div>

    </c:forEach>
</div>

<%@ include file="_footer.jsp" %>
