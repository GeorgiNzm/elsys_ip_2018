<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
</head>
<body>
	<table>
		<tr>
			<th>ID</th>
			<th>Username</th>
			<th>Email</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.email}</td>
				<td>
					<form action="delete" method="POST">
						<input type="hidden" name= "name" value="${user.name}">
						<input type="submit" value="Delete User">
					</form>
				</td>
				<td><a href="user?name=${user.name}">Go to user page</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>

	<form action="add" method="GET">
		<input type="submit" value="Add User">
	</form>

	<form action="search" method="GET">
		<input type="submit" value="Open search page">
	</form>

	<form action="logout" method="POST">
		<input type="submit" value="Logout">
	</form>
</body>
</html>