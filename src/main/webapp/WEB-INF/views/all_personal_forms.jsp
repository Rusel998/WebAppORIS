<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="_header.jsp" %>

<!-- Подключаем стили -->
<link rel="stylesheet" href="<c:url value='/styles/AllForms.css'/>" />

<div class="allforms-container">
    <h2>Все анкеты</h2>

    <div class="filter-bar">
        <h3>Фильтр по интересу:</h3>
        <a href="<c:url value='/personal-forms'/>" class="interest-link">Все</a>
        <c:forEach var="interest" items="${allInterests}">
            <a href="<c:url value='/personal-forms?interestId=${interest.id}'/>" class="interest-link">
                    ${interest.name}
            </a>
        </c:forEach>
    </div>

    <div class="forms-grid">
        <c:forEach var="form" items="${allForms}">
            <c:set var="user" value="${usersMap[form.userId]}"/>
            <c:set var="avgRating" value="${avgRatingsMap[form.userId]}"/>
            <c:set var="roundedRating" value="${roundedRatingsMap[form.userId]}"/>

            <div class="form-card">
                <h4>${user.username}, ${form.age}</h4>
                <h5>Пол: ${form.gender}</h5>
                <p class="bio-text">${form.bio}</p>

                <c:if test="${form.photo != null}">
                    <div class="photo-wrapper">
                        <img src="<c:url value='/photo?formId=${form.id}'/>" alt="User Photo"/>
                    </div>
                </c:if>

                <p class="email-text">${user.email}</p>

                <div class="rating-block">
                    <c:choose>
                        <c:when test="${roundedRating == 0}">
                            <span class="no-rating">Не оценивали</span>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="i" begin="1" end="5">
                                <c:if test="${i <= roundedRating}">
                                    &#9733;
                                </c:if>
                                <c:if test="${i > roundedRating}">
                                    &#9734;
                                </c:if>
                            </c:forEach>
                            <p class="avg-rating">Average: ${avgRating}</p>
                        </c:otherwise>
                    </c:choose>
                </div>

                <c:if test="${not empty sessionScope.email}">
                    <div class="links-row">
                        <a href="<c:url value='/rating-create?ratedUserId=${form.userId}'/>">Оценить</a>
                        <a href="<c:url value='/complaint-create?id=${form.userId}'/>">Пожаловаться</a>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>

<%@ include file="_footer.jsp" %>