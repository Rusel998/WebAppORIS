<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp" %>

<link rel="stylesheet" href="<c:url value='/styles/MyForm.css'/>" />

<div class="container">
    <div class="hero">
        <div class="image image-left">
            <img src="<c:url value='/images/myform.png'/>" alt="Left Photo" class="blend-hearts" />
        </div>
        <div class="center-block">
            <h2>Моя анкета</h2>
            <hr />

            <div class="myform-top">
                <c:if test="${form.photo != null}">
                    <div class="myform-photo">
                        <img src="<c:url value='/photo?formId=${form.id}'/>" alt="My Form Photo"/>
                    </div>
                </c:if>

                <c:if test="${empty form}">
                    <p>У вас нет анкеты.
                        <a href="<c:url value='/personal-form-create'/>">Создать</a>
                    </p>
                </c:if>

                <c:if test="${not empty form}">
                    <div class="myform-data">
                        <p><strong>О себе:</strong> ${form.bio}</p>
                        <p><strong>Возраст:</strong> ${form.age}</p>
                        <p><strong>Пол:</strong> ${form.gender}</p>
                    </div>
                </c:if>
            </div>

            <c:if test="${not empty form}">
                <div class="myform-buttons">
                    <a href="<c:url value='/personal-form-edit?id=${form.id}'/>"
                       class="myform-btn edit-btn">
                        Редактировать мою анкету
                    </a>

                    <form action="<c:url value='/personal-form-delete'/>"
                          method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${form.id}"/>
                        <button type="submit" class="myform-btn delete-btn">
                            Удалить мою анкету
                        </button>
                    </form>
                </div>

                <hr/>
                <h3 class="myform-interests-title">Мои интересы</h3>
                <form action="<c:url value='/my-form-interests'/>" method="post" class="myform-interests-form">
                    <c:forEach var="interest" items="${allInterests}">
                        <c:set var="hasInterest" value="false"/>
                        <c:forEach var="userInterest" items="${userInterests}">
                            <c:if test="${userInterest.id == interest.id}">
                                <c:set var="hasInterest" value="true"/>
                            </c:if>
                        </c:forEach>
                        <label class="interest-label">
                            <input type="checkbox"
                                   name="interestId"
                                   value="${interest.id}"
                                   <c:if test="${hasInterest}">checked</c:if> />
                            <span class="interest-custom"></span>
                                ${interest.name}
                        </label><br/>
                    </c:forEach>
                    <button type="submit" class="myform-btn save-interests-btn">
                        Сохранить интересы
                    </button>
                </form>
            </c:if>
        </div>

        <div class="image image-right">
            <img src="<c:url value='/images/myform.png'/>" alt="Right Photo" class="blend-hearts"/>
        </div>

    </div>
</div>

<%@ include file="_footer.jsp" %>