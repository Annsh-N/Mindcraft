<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bill</title>
</head>
<body>
<body>
<h2>Pending Bill:</h2>
<table>
<tr>
<td>
<h4><%=request.getAttribute("bill") %></h4>
</td>
</tr>
<c:if test="${bill != 0}">
<tr>
<td>
<form action="payBill">
<button type="submit">Pay Bill</button>
</form>
</td>
</tr>
</c:if>
</table>
<%= request.getAttribute("result") != null ? request.getAttribute("result") : "" %>
<table>
     <c:forEach items="${unordered}" var="product">
         <tr>
             <td>${product.name}</td>
         </tr>
     </c:forEach>
</table>
<%= request.getAttribute("paid") != null ? "Total Paid: " + request.getAttribute("paid") : "" %>
<form action="customerHome">
		<button type="submit">Return Home</button>
</form>
</body>
</body>
</html>