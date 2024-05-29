<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>
<body>
	<h2>Choose an Admin Operation</h2>
	<form>
		<table>
			<tr>
				<td><input type="submit" formaction="displayAdminProducts" value="Display All Products"></td>
				<td><input type="submit" formaction="addProductPage" value="Add Product"></td>
				<td><input type="submit" formaction="addCustomerPage" value="Add Customer"></td>
				<td><input type="submit" formaction="removeCustomerPage" value="Remove Customer"></td>
			</tr>
		</table>
	</form>
</body>
</html>