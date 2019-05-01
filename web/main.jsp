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
    <table border="1" cellspacing="5" cellpadding="5" >
        <c:forEach var="user" items="${users}"><tr>
            <td><c:out value="${user.getName()}" /> </td>
            <td><a href="editServlet?login=${user.getLogin()}">edit</a> </td>
            <td><a href="delServlet?login=${user.getLogin()}">delete</a> </td></tr>
        </c:forEach>
    </>
    <form action="add" method="post">
        <input type="submit" value="Add" content="Add">
    </form>
</body>
</html>
