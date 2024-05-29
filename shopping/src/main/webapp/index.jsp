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
<td>Username:</td>
<td><input type="text" name="username"></td>
</tr>
<tr>
<td>Password:</td>
<td><input type="text" name="password"></td>
</tr>
<tr>
<td><input type="submit" value="login"></td>
</tr>
<tr>
<td><%=request.getAttribute("result") != null ? request.getAttribute("result") : "" %></td>
</tr>
</table>
</form>
</body>
</html>