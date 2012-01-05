<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="no">
<head>
    <title>Medarbeidere i Under Dusken</title>
    <link href="/resources/css/main.css" media="screen" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="/resources/images/udlogo.png" />
    <script src="/resources/js/jquery.min.js"></script>
</head>
<body>
<header>
    <h1>Gallerier</h1>
    <nav>
        <ul>
            <li><a href="/">UDIntern</a></li>
            <li><a href="/galleri">Oversikt</a></li>
            <li><a title="Vis aktive" href="/medarbeidere/aktive">Vis aktive</a></li>
            <li><a title="Vis ikke-aktive" href="/medarbeidere/ikkeaktive">Vis ikke-aktive</a></li>
            <sec:authorize access="hasRole('ROLE_MASKINIST')">
            <li><a title="Ny medarbeider" href="/medarbeidere/rediger/ny">Ny medarbeider</a></li>
            </sec:authorize>
        </ul>
    </nav>
</header>