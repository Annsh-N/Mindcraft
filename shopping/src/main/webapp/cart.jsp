<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
</head>
<body>
<h2>Products in Cart:</h2>
<c:if test="${not empty cart}">
	<table border="1">
        <tr>
            <th>PID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Discount</th>
        </tr>
        <c:forEach items="${cart}" var="product">
            <tr>
                <td>${product.pid}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>${product.discount}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty cart}">
<h4>Cart is Empty</h4>
</c:if>
<form action="customerHome">
	<button type="submit">Return Home</button>
</form>
</body>
</html>