<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Balance</title>
</head>
<body>
<h2>Current Balance:</h2>
<table>
<tr>
<td>
<h4><%=request.getAttribute("balance") %></h4>
</td>
</tr>
<tr>
<td>
<form action="addBalance">
Enter amount: <input type="number" name="amount">
<button type="submit">Add Balance</button>
</form>
</td>
</table>
<%= request.getAttribute("result") != null ? request.getAttribute("result") : "" %>
<form action="customerHome">
		<button type="submit">Return Home</button>
</form>
</body>
</html>