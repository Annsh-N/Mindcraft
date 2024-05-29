<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Statement</title>
</head>
<body>
<h3>Last 5 Transactions:</h3>
<i style="white-space: pre-line"><%=request.getAttribute("statement") %></i>
<form>
<button style="margin-top: 2%" type="submit" formaction="customerHome">Return Home</button>
</form>
</body>
</html>