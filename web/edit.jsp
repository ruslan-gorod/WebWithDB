<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="style.css" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Registration form</title>
</head>
<body>
<form action="editServlet" method="post" class="login-form">
    Login <input name="login" type="text" class="my-input" required value="${login}"/><br>
    Password <input name="password" type="password" class="my-input" required value="${password}"/><br>
    Confirm Password <input name="confirmPassword" type="password" class="my-input" required value="${password}"/><br>
    Name <input name="nameUser" type="text" class="my-input" required value="${name}"/><br>
    Role <select class="my-input" name="roleUser">
        <option selected value="${role}">${role}</option>
            <c:if test="${role.equals(\"user\")}">
                 <option value="admin">admin</option>
            </c:if>
            <c:if test="${role.equals(\"admin\")}">
                 <option value="user">user</option>
            </c:if>
    </select>
    <br>
    <input hidden type="text" name="id"  value="${id}">
    <input type="submit" class="button">
</form>
</body>
</html>