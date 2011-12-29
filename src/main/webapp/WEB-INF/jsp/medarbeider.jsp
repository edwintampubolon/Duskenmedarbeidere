<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="include/header.jsp" />

<div id="person">
    <c:choose>
        <c:when test="${not person.new}">
            <c:set var="method" value="put"/>
            <c:set var="target" value="/medarbeidere/${person.id}" />
            <img src="/image/medarbeider_${person.username}.jpg" alt="${person.firstname}">
        </c:when>
        <c:otherwise>
            <c:set var="method" value="post"/>
            <c:set var="target" value="/medarbeidere/new" />
        </c:otherwise>
    </c:choose>
    <form:form commandName="person" modelAttribute="person" method="${method}" action="${target}">
        <fieldset>
            <form:label path="firstname">Fornavn</form:label>
            <form:input path="firstname" />
            <form:label path="surname">Etternavn</form:label>
            <form:input path="surname" />
            <form:label path="username">Brukernavn</form:label>
            <form:input path="username" readonly="${!person.new}" />
            <form:label path="emailAddress">Mailadresse (adressen duskenmail skal videresendes til)</form:label>
            <input id="emailAddress" name="emailAddress" type="email" value=""/>

            <form:label path="birthdate">Fødselsdag</form:label>
            <input id="birthdate" name="birthdate" type="date" value=""/>
            <form:label path="active">Aktiv</form:label>
            <form:checkbox path="active" />
        </fieldset>
        <fieldset>
            <form:label path="phoneNumber">Telefon</form:label>
            <input id="phoneNumber" name="phoneNumber" type="number" min="8" maxlength="8" value=""/>
            <form:label path="postalAddress">Postadresse</form:label>
            <form:input path="postalAddress" />
            <form:label path="postalCode">Postnummer</form:label>
            <input id="postalCode" name="postalCode" type="number" min="4" maxlength="4" value=""/>
        </fieldset>
        <fieldset>
            <form:label path="homePostalAddress">Postadresse, hjemme</form:label>
            <form:input path="homePostalAddress" />
            <form:label path="homePostalCode">Postnummer, hjemme</form:label>
            <input id="homePostalCode" name="homePostalCode" type="number" min="4" maxlength="4" value=""/>
        </fieldset>
        <fieldset>
            <form:label path="department">Avdeling</form:label>
            <form:select path="department" itemValue="id" itemLabel="name" items="${departments}" multiple="false" />
            <form:label path="roles">Roller</form:label>
            <form:select path="roles" itemValue="id" items="${roles}" multiple="true" />
        </fieldset>

        <input type="submit" value="Lagre">
    </form:form>

</div>

<jsp:include page="include/footer.jsp" />