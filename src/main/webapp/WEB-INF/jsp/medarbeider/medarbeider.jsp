<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../include/header.jsp" />

<div id="person">
    <c:if test="${not person.new}">
        <img src="/image/medarbeider_${person.username}.jpg" alt="${person.firstname} ${person.surname}">
    </c:if>
    <form:form commandName="person" modelAttribute="person" method="post" action="/medarbeidere/rediger/${person.username}">
        <fieldset>
            <form:label path="firstname">Fornavn</form:label>
            <form:errors path="firstname" />
            <form:input path="firstname" />
            <form:label path="surname">Etternavn</form:label>
            <form:errors path="surname" />
            <form:input path="surname" />
            <form:label path="username">Brukernavn</form:label>
            <form:errors path="username" />
            <form:input path="username" readonly="${!person.new}" />
            <form:label path="emailAddress">Mailadresse (adressen duskenmail skal videresendes til)</form:label>
            <form:errors path="emailAddress" />
            <input id="emailAddress" name="emailAddress" type="email" value="${person.emailAddress}"/>

            <form:label path="birthdate">Fødselsdag</form:label>
            <form:errors path="birthdate" />
            <input id="birthdate" name="birthdate" type="date" value="<fmt:formatDate value="${person.birthdate}" type="date" pattern="yyyy-MM-dd" />"/>
            <form:label path="active">Aktiv</form:label>
            <form:checkbox id="active" path="active" />
        </fieldset>
        <fieldset>
            <form:label path="phoneNumber">Telefon</form:label>
            <form:errors path="phoneNumber" />
            <input id="phoneNumber" name="phoneNumber" type="number" maxlength="8" value="${person.phoneNumber}"/>
            <form:label path="postalAddress">Postadresse</form:label>
            <form:errors path="postalAddress" />
            <form:input path="postalAddress" />
            <form:label path="postalCode">Postnummer</form:label>
            <form:errors path="postalCode" />
            <input id="postalCode" name="postalCode" type="number" maxlength="4" value="${person.postalCode}"/>
        </fieldset>
        <fieldset>
            <form:label path="homePostalAddress">Postadresse, hjemme</form:label>
            <form:errors path="homePostalAddress" />
            <form:input path="homePostalAddress" />
            <form:label path="homePostalCode">Postnummer, hjemme</form:label>
            <form:errors path="homePostalCode" />
            <input id="homePostalCode" name="homePostalCode" type="number" maxlength="4" value="${person.homePostalCode}"/>
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

<jsp:include page="../include/footer.jsp" />