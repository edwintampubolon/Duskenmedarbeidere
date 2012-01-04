<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />

<article id="galleri">
    <h1>${galleri.navn}</h1>
    ${galleri.bilder[0]}
    <c:set var="imageurl" value="/image?path=galleri/${galleri.navn}/${galleri.bilder[0]}"/>
    ${imageurl}
    <img src="${imageurl}">
</article>

<jsp:include page="../include/footer.jsp" />