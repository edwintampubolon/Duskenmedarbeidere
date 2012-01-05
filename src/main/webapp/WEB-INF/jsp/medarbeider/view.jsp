<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="include/header.jsp" />

<article id="person">
        <img src="/image?path=medarbeidere/${person.username}.jpg" alt="${person.firstname} ${person.surname}">
        <h1>${person.firstname} ${person.surname}</h1>

            <label for="username">Brukernavn</label>
            <span id="username">${person.username}</span>

            <label for="emailAddress">Epost</label>
            <span id="emailAddress"><a href="mailto:${person.emailAddress}">${person.emailAddress}</a></span>

            <label for="birthdate">Fødselsdag</label>
            <span id="birthdate" ><fmt:formatDate value="${person.birthdate}" type="date" pattern="yyyy-MM-dd" /></span>
            <label for="active">Aktiv</label>
            <span id="active">${person.active}</span>

            <label for="phoneNumber">Telefon</label>
            <span id="phoneNumber"><a href="tel:${person.phoneNumber}">${person.phoneNumber}</a></span>

            <label for="postalAddress">Postadresse</label>
            <span id="postalAddress">${person.postalAddress}</span>

            <label for="postalCode">Postnummer</label>
            <span id="postalCode">${person.postalCode}</span>

            <label for="homePostalAddress">Postadresse, hjemme</label>
            <span id="homePostalAddress">${person.homePostalAddress}</span>

            <label for="homePostalCode">Postnummer, hjemme</label>
            <span id="homePostalCode">${person.homePostalCode}</span>

            <label for="department">Avdeling</label>
            <span id="department">${person.department.name}</span>

           <label for="roles">Roller</label>
            <ul id="roles">
                <c:forEach var="role" items="${person.roles}" varStatus="status">
                <li>${role.name}</li>
            </c:forEach>
            </ul>

            <a href="/medarbeidere/rediger/${person.username}">Redigér</a>
</article>

<jsp:include page="../include/footer.jsp" />