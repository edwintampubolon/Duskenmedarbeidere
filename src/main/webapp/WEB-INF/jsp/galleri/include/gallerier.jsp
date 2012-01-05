<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<section>
    <h1>Gallerier</h1>

    <ul>
        <c:forEach items="${gallerier}" var="galleri">
            <li>
                <a href="/galleri/${galleri.id}"><img src="/image?path=galleri/${galleri.navn}/${galleri.bilder[0]}&width=${param.imagewidth}"></a>
                <a href="/galleri/${galleri.id}">${galleri.navn}</a>
            </li>
        </c:forEach>
    </ul>
</section>
