<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Products</title>
</head>
<body>
<h2>All Products:</h2>
<c:if test="${not empty products}">
<table border="1">
        <tr>
            <th>PID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity Available</th>
            <th>Discount</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.pid}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>${product.discount}</td>
                <td>
                	<form action="addToCart">
                        <input type="number" name="quantity" value="1" min="1" max="${product.quantity}" style="width: 80px"/>
                        <input type="hidden" name="pid" value="${product.pid}" />
                        <button type="submit">Add to Cart</button>
                    </form>
               </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty products}">
<h4>No Products to Display</h4>
</c:if>
<%=request.getAttribute("result") != null ? request.getAttribute("result") : "" %>
<form action="customerHome">
	<button type="submit">Return Home</button>
</form>
</body>
</html>