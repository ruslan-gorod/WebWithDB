<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
    <p>Welcome, ${name}!</p>
    <ul>
        <c:forEach var="user" items="${users}">
            <li><c:out value="${user.getName()}" /></li>
        </c:forEach>
    </ul>

</body>
</html>
