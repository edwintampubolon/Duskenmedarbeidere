<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="no">
<head>
    <title>${medarbeider.name} | Medarbeidere i Under Dusken</title>
    <link href="/resources/css/main.css" media="screen" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="/resources/images/udlogo.png" />
</head>
<body>
<header>
    <h1>${medarbeider.name}</h1>
    <nav>
        <ul>
            <li><a href="/">Oversikt</a></li>
            <li><a title="Vis aktive" href="/aktive">Vis aktive</a></li>
            <li><a title="Vis ikke-aktive" href="/ikkeaktive">Vis ikke-aktive</a></li>
            <li><a title="Vis roller" href="/roller">Vis roller</a></li>
            <li><a title="Ny medarbeider" href="/ny">Ny medarbeider</a></li>
        </ul>
    </nav>
</header>

<div id="person">
    <form:form commandName="person" >
        <fieldset>
            <form:label path="firstname">Fornavn</form:label>
            <form:input path="firstname" />
            <form:label path="surname">Etternavn</form:label>
            <form:input path="surname" />
            <form:label path="username">Brukernavn</form:label>
            <form:input path="username" readonly="${!person.isNew}" />
            <form:label path="birthdate">Fødselsdag</form:label>
            <form:input path="birthdate" />
            <form:label path="active">Aktiv</form:label>
            <form:checkbox path="active" />
            <form:label path="activePang">Aktiv pang</form:label>
            <form:checkbox path="activePang" />
        </fieldset>
        <fieldset>
            <form:label path="firstname">Telefon</form:label>
            <form:input path="phoneNumber" />
            <form:label path="postalAddress">Postadresse</form:label>
            <form:input path="postalAddress" />
            <form:label path="postalCode">Postnummer</form:label>
            <form:input path="postalCode" />
        </fieldset>
        <fieldset>
            <form:label path="homePhone">Hjemmetelefon</form:label>
            <form:input path="homePhone" />
            <form:label path="homePostalAddress">Postadresse, hjemme</form:label>
            <form:input path="homePostalAddress" />
            <form:label path="homePostalCode">Postnummer, hjemme</form:label>
            <form:input path="homePostalCode" />
        </fieldset>
        <input type="submit" value="Lagre">
    </form:form>

</div>

<footer>Under Dusken</footer>
</body>
</html>
