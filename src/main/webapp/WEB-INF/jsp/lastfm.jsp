<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul>
    <c:forEach items="${items}" var="item">
    <li>
        <a href="${item.link}">${item.title}</a>
        <span class="rssdate">${item.time}</span>
    </li>
    </c:forEach>
</ul>