<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="include/header.jsp" />

<article id="galleri">
    <h1>${galleri.navn}</h1>
    <a href="/galleri/${galleri.id}?bilde=${(param.bilde + 1) % (fn:length(galleri.bilder) )}"><img src="/image?path=galleri/${galleri.navn}/${galleri.bilder[param.bilde]}&width=900"></a>

    <ul id="thumbs">
        <c:forEach items="${galleri.bilder}" var="bilde" varStatus="status">
        <li>
                <a href="/galleri/${galleri.id}?bilde=${status.count - 1}"><img src="/image?path=galleri/${galleri.navn}/${bilde}&width=100"></a>
        </li>
        </c:forEach>
    </ul>
</article>
<div id="ajaxkommentar"></div>
<script>
    $('#ajaxkommentar').load('/kommentar/${galleri.navn}${param.bilde}');
</script>
<jsp:include page="../include/footer.jsp" />