<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Customer</title>
</head>
<body>
<h2>Enter New Customer Details</h2>
<form action="addCustomer">
<table>
<tr>
<td>Username: </td>
<td><input type="text" name="username"></td>
</tr>
<tr>
<td>Password: </td>
<td><input type="text" name="password"></td>
</tr>
<tr>
<td><input type="submit" value="Add Customer"></td>
</tr>
<tr>
<td><%=request.getAttribute("result") != null ? request.getAttribute("result") : "" %></td>
</tr>
</table>
</form>
<form action="adminHome">
	<button type="submit">Return Home</button>
</form>
</body>
</html>