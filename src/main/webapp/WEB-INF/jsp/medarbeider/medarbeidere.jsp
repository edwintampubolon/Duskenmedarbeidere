<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="include/header.jsp" />

<div id="people">
    <h1>${medarbeidereheader}</h1>
    <ul>
        <c:forEach var="person" items="${people}">
            <%@ include file="include/personElement.jsp" %>
        </c:forEach>
    </ul>
</div>

<jsp:include page="../include/footer.jsp" />