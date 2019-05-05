<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
    <c:if test="${name!=null}">
        <p>Welcome, ${name}!</p>
    </c:if>
    <c:if test="${role.equals(\"admin\")}">
        <table border="1" cellspacing="5" cellpadding="5" >
            <td style="text-align: center; font-size: medium; border: black">Name</td>
            <td style="text-align: center; font-size: medium; border: black">Role</td>
            <td style="text-align: center; font-size: medium; border: black">Login</td>
            <td style="text-align: center; font-size: medium; border: black"> </td>
            <td style="text-align: center; font-size: medium; border: black"> </td>
            <c:forEach var="user" items="${users}"><tr>
                <td><c:out value="${user.getName()}" /> </td>
                <td><c:out value="${user.getRole()}" /> </td>
                <td><c:out value="${user.getLogin()}" /> </td>
                <td><a href="editServlet?login=${user.getLogin()}">edit</a> </td>
                <td><a href="delServlet?login=${user.getLogin()}">delete</a> </td></tr>
            </c:forEach>
        </>
        <form action="add" method="post">
            <input type="submit" value="Add" content="Add">
        </form>
    </c:if>
</body>
</html>
