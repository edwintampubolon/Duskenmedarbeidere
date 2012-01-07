<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="kommentarer">
    <h2>Kommentarer</h2>
    <form action="/kommentar/ny" method="post" id="kommentarform">
        <input type="hidden" value="${objectId}" name="kommentarTil">
        <input type="hidden" value="Derpderpderp" name="forfatter">
        <label for="kommentar">Komment&eacute;r</label>
        <textarea id="kommentar" name="kommentar"></textarea>
        <input type="submit" value="Oki" />
    </form>
    <c:if test="${count > fn:length(kommentarer)}">
        <a id="lastkommentarer" href="">Last alle kommentarene</a>
        <script>
            $('#lastkommentarer').click(function(event){
                $('#ajaxkommentar').load('/kommentar/UDIntern');
                return false;
            });
        </script>
    </c:if>
    <script>
        $('#kommentarform').submit(function(event){
            event.preventDefault();
            $.post($(this).attr('action'), $(this).serializeArray(), function(data, textStatus, jqXHR){
                if(textStatus == 'success'){
                    $('#ajaxkommentar').load('/kommentar/${objectId}');
                }else{
                    alert('Ups. Noe skjedde. SÅe. Fikk ikke lagra kommentaren. Baklager.')
                }
            });
            return false;
        });
    </script>
    <ul>
        <c:forEach items="${kommentarer}" var="kommentar">
            <li><article>
                <c:if test="${not empty kommentar.tittel}"><h1>${kommentar.tittel}</h1></c:if>
                <p>${kommentar.kommentar}</p>
            </article></li>
        </c:forEach>
    </ul>
</div>