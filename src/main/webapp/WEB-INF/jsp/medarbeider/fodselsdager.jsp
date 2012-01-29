<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section>
    <h1>Fødselsdager</h1>
    <ul>
        <c:forEach items="${personer}" var="person">
            <li>
                <span class="name">${person.name}</span>
                <span class="date"><fmt:formatDate value="${person.birthdate.time}" pattern="dd MMMMMMMM" /></span>
                <span class="years">${person.age} år</span>
            </li>
        </c:forEach>
    </ul>
</section>