<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="login">
<table>
<tr>
<td>Account Number:</td>
<td><input type="number" name="accNo"></td>
</tr>
<tr>
<td><input type="submit" value="Login"></td>
</tr>
<tr>
<td><%=request.getAttribute("result") != null ? request.getAttribute("result") : "" %></td>
</tr>
</table>
</form>
</body>
</html>