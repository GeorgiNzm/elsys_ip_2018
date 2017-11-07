<%--
  Created by IntelliJ IDEA.
  User: georgi
  Date: 06.11.17
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserPage</title>
</head>
<body>
    <table style="width: 60%">
        <%--Add user properties--%>
        <tr>
            <th>UserId</th>
            <th>UserName</th>
            <th>UserEmail</th>
        </tr>
        <%--Add more user information--%>
        <tr>
            <td>${User.id}</td>
            <td>${User.name}</td>
            <td>${User.email}</td>
        </tr>
    </table>

    <form action="update" method="POST" style="padding-top: 30px">
        <%--Send old username hidden --%>
        <input type="hidden" name="oldName" value="${User.name}">
        New Name: <input type="text" name="updatedName"/><br>
        New Email: <input type="email" name="updatedEmail"/>
        <br>
        <input type="submit" value="Update User Information" />
    </form>
</body>
</html>
