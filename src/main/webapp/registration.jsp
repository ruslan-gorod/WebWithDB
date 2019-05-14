<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="style.css" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Registration form</title>
</head>
<body>
<form action="login" method="post" class="login-form">
    Login <input name="login" type="text" class="my-input" required><br>
    Password <input name="password" type="password" class="my-input" required><br>
    Confirm Password <input name="confirmPassword" type="password" class="my-input" required><br>
    Name <input name="nameUser" type="text" class="my-input" required><br>
    <input type="checkbox" style="font-size: medium;"> Agree<br>
    <input type="submit" class="button">
</form>
</body>
</html>