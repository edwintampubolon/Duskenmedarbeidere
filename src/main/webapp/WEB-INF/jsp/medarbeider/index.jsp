<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<section id="search">
    <h1>${medarbeidereheader}</h1>
    <form action="/medarbeidere/search" method="post">
        <label for="searchField">S&oslash;k:</label>
        <input type="search" name="search" id="searchField" value="${search}">
        <input type="submit" value="S&oslash;k">
    </form>

    <ul id="personresult">
        <c:forEach items="${people}" var="person">
            <%@ include file="include/personElement.jsp" %>
        </c:forEach>
    </ul>
    <ul id="personsByRoles">
        <c:forEach items="${roles}" var="role">
            <li class="role">
                <h2>${role.name}</h2>
                <c:forEach items="${role.persons}" var="person">
                    <%@ include file="include/personElement.jsp" %>
                </c:forEach>
            </li>
        </c:forEach>
    </ul>
</section>
<jsp:include page="../include/footer.jsp" />