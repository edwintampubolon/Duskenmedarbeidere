<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="include/header.jsp" />

<div class="mainColumn">
<section>
    <h2>Interne nyheter</h2>
    <div id="ajaxkommentar"></div>
    <script>
        $('#ajaxkommentar').load('/kommentar/UDIntern');
    </script>
</section>
<section>
    <h1><a href="http://www.last.fm/user/duskenbaren">Spilles i baren</a></h1>
    <div id="ajaxlastfm"></div>
    <script>
        $('#ajaxlastfm').load('/lastfm');
    </script>
</section>
</div>

<jsp:include page="include/footer.jsp" />