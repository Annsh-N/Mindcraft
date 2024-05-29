<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer</title>
</head>
<body>
<h2>Choose a Customer Operation</h2>
	<form>
		<table>
			<tr>
				<td><input type="submit" formaction="displayCustomerProducts" value="Display All Products"></td>
				<td><input type="submit" formaction="displayCart" value="Display Cart"></td>
				<td><input type="submit" formaction="displayBill" value="Display Bill"></td>
				<td><input type="submit" formaction="displayBalance" value="Add Balance to Wallet"></td>
			</tr>
		</table>
	</form>
</body>
</html>