<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp" %>

<h2>Моя анкета</h2>
<c:if test="${empty form}">
    <p>У вас нет анкеты. <a href="<c:url value='/personal-form-create'/>">Создать</a></p>
</c:if>

<c:if test="${not empty form}">
    <p><strong>Bio:</strong> ${form.bio}</p>
    <p><strong>Age:</strong> ${form.age}</p>
    <p><strong>Gender:</strong> ${form.gender}</p>

    <!-- Если есть фото, выведем его -->
    <c:if test="${form.photo != null}">
        <img src="${pageContext.request.contextPath}/photo?formId=${form.id}"
             style="max-width: 200px; height:auto;" />
    </c:if>

    <a href="<c:url value='/personal-form-edit?id=${form.id}'/>">Редактировать мою анкету</a>
    <form action="<c:url value='/personal-form-delete'/>" method="post" style="display:inline;">
        <input type="hidden" name="id" value="${form.id}"/>
        <button type="submit">Удалить мою анкету</button>
    </form>

    <hr>
    <h3>Мои интересы</h3>
    <form action="<c:url value='/my-form-interests'/>" method="post">
        <c:forEach var="interest" items="${allInterests}">
            <!-- Отметим чекбокс, если у пользователя есть этот интерес -->
            <c:set var="hasInterest" value="false"/>
            <c:forEach var="userInterest" items="${userInterests}">
                <c:if test="${userInterest.id == interest.id}">
                    <c:set var="hasInterest" value="true"/>
                </c:if>
            </c:forEach>
            <label>
                <input type="checkbox" name="interestId" value="${interest.id}" <c:if test="${hasInterest}">checked</c:if>/>
                    ${interest.name}
            </label><br>
        </c:forEach>
        <button type="submit">Сохранить интересы</button>
    </form>
</c:if>

<%@ include file="_footer.jsp" %>

