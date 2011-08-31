<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="people">
    <h1>${header}</h1>
    <ul>
        <c:forEach var="person" items="${people}">
            <li><a href="/person/${person.username}">${person.name}</a></li>
        </c:forEach>
    </ul>
</div>