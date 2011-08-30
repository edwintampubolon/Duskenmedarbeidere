<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="people">
    <h1>${header}</h1>
    <ul>
        <c:forEach var="person" items="${people}">
            <li>${person.name}</li>
        </c:forEach>
    </ul>
</div>