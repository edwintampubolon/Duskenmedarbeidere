<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="kommentarer">
    <h2>Kommentarer</h2>
    <form action="/kommentar/ny" method="post">
        <input type="hidden" value="${objectId}" name="objectId">
        <label for="kommentar">Kommentér</label>
        <textarea id="kommentar" name="kommentar"></textarea>
        <input type="submit" value="Oki" />
    </form>
    <ul>
        <c:forEach items="${kommentarer}" var="kommentar">
            <li><article>
                <c:if test="${not empty kommentar.tittel}"><h1>${kommentar.tittel}</h1></c:if>
                <p>${kommentar.kommentar}</p>
            </article></li>
        </c:forEach>
    </ul>
</div>