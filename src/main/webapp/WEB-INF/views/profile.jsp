<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="_header.jsp"%>

<header>
    <h1>Welcome, ${user.username}!</h1>
</header>
<main>
    <section>
        <h2>Your Profile</h2>
        <p>Email: ${user.email}</p>
        <p>Profile Views: ${personalForm.profileviews}</p>
    </section>
    <section>
        <h2>Your Personal Form</h2>
        <form action="/personalForm" method="post">
            <label for="bio">Bio:</label>
            <textarea id="bio" name="bio">${personalForm.bio}</textarea>

            <label for="age">Age:</label>
            <input type="number" id="age" name="age" value="${personalForm.age}">

            <label for="gender">Gender:</label>
            <select id="gender" name="gender">
                <option value="male" <c:if test="${personalForm.gender == 'male'}">selected</c:if>>Male</option>
                <option value="female" <c:if test="${personalForm.gender == 'female'}">selected</c:if>>Female</option>
                <option value="other" <c:if test="${personalForm.gender == 'other'}">selected</c:if>>Other</option>
            </select>

            <button type="submit">Update</button>
        </form>
    </section>
</main>

<%@include file="_footer.jsp"%>
