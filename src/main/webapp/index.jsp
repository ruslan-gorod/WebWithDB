<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="stylesheet" href="style.css" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Login form</title>

  </head>
  <body>
    <form action="login" method="post" class="login-form">
      Login <input name="login" type="text" class="my-input" required><br>
      Password <input name="password" type="password" class="my-input" required><br>
      <input type="submit" class="button">
    </form>
  </body>
</html>