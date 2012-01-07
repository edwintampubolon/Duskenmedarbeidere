<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<section>
    <h1>Gallerier</h1>

    <ul>
        <jsp:useBean id="random" class="java.util.Random"/>
        <c:forEach items="${gallerier}" var="galleri">
            <c:set var="bilde" value="${random.nextInt(fn:length(galleri.bilder))}"/>
            <li>
                <article>
                    <h1><a href="/galleri/${galleri.id}">${galleri.navn}</a></h1>
                    <a href="/galleri/${galleri.id}"><img src="/image?path=galleri/${galleri.navn}/${galleri.bilder[bilder]}&width=${param.imagewidth}"></a>
                    <p id="ajaxkommentar"></p>
                    <script>
                        $('#ajaxkommentar').load('/kommentar/${galleri.navn}${bilde}?number=1 ul');
                    </script>
                </article>
            </li>
        </c:forEach>
    </ul>
</section>
