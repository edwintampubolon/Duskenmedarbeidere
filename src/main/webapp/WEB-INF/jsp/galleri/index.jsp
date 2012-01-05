<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="include/header.jsp" />

<article id="galleri">
    <h1>${galleri.navn}</h1>
    <a href="/galleri/${galleri.id}?bilde=${(param.bilde + 1) % (fn:length(galleri.bilder) )}&width=900"><img src="/image?path=galleri/${galleri.navn}/${galleri.bilder[param.bilde]}&width=900"></a>

    <ul id="thumbs">
        <c:forEach items="${galleri.bilder}" var="bilde" varStatus="status">
        <li>
                <img src="/image?path=galleri/${galleri.navn}/${bilde}&width=100">
        </li>
        </c:forEach>
    </ul>
</article>

<jsp:include page="../include/footer.jsp" />