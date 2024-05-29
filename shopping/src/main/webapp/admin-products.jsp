<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Products</title>
</head>
<body>
<h2>All Products:</h2>
<c:if test="${not empty products}">
	<table border="1">
        <tr>
            <th>PID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Discount</th>
        </tr>
        <c:forEach items="${products}" var="product">
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
<c:if test="${empty products}">
<h4>No Products to Display</h4>
</c:if>
<form action="adminHome">
	<button type="submit">Return Home</button>
</form>
</body>
</html>