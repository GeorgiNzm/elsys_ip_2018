<%--
  Created by IntelliJ IDEA.
  User: georgi
  Date: 06.11.17
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user page</title>
</head>
<body>
    <h2>Adding user page</h2>
    <form action="add" method="POST">
        Enter Name: <input type="text" name="name"/><br>
        Enter Email: <input type="text" name="email"/>
        <br>
        <input type="submit" value="Update User Information" />
    </form>
</body>
</html>
