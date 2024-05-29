<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product</title>
</head>
<body>
<h2>Enter New Product Details</h2>
<form action="addProduct">
<table>
<tr>
<td>PID: </td>
<td><input type="number" name="pid"></td>
</tr>
<tr>
<td>Name: </td>
<td><input type="text" name="name"></td>
</tr>
<tr>
<td>Price: </td>
<td><input type="number" name="price"></td>
</tr>
<tr>
<td>Quantity: </td>
<td><input type="number" name="quantity"></td>
</tr>
<tr>
<td>Discount: </td>
<td><input type="number" name="discount"></td>
</tr>
<tr>
<td><input type="submit" value="Add Product"></td>
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