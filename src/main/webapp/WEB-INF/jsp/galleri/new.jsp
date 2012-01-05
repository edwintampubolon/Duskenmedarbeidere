<jsp:include page="include/header.jsp" />
<form method="post" action="<%=request.getContextPath() %>/galleri/nytt" enctype="multipart/form-data">
    <label for="name">Navn</label>
    <input id="name" name="navn" type="text">
    <label for="files">Velg filer</label>
    <input name="files" id="files" type="file" multiple="" />
    <input type="submit" value="Lagre">
</form>

<jsp:include page="../include/footer.jsp" />