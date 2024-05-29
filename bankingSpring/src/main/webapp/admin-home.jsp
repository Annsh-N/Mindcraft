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
		<table  style="width: 95%; margin: 0 auto; border-spacing: 10px">
			<tr>
				<td><table>
				<tr><td align="right">Account Number: <input type="number" name="accNoAdd"></td></tr>
				<tr><td align="right">Name: <input type="text" name="nameAdd"></td></tr>
				<tr><td align="center"><input type="submit" formaction="addCustomer" value="Add Customer"></td></tr>
				</table></td>
				<td><table>
				<tr><td align="right">Account Number: <input type="number" name="accNoSearch"></td></tr>
				<tr><td align="center"><input type="submit" formaction="searchByNo" value="Search"></td></tr>
				</table></td>
				<td><table>
				<tr><td align="right">Name: <input type="text" name="nameSearch"></td></tr>
				<tr><td align="center"><input type="submit" formaction="searchByName" value="Search"></td></tr>
				</table></td>
				<td><table>
				<tr><td align="right">Account Number: <input type="number" name="accNoModify"></td></tr>
				<tr><td align="right">Name: <input type="text" name="nameModify"></td></tr>
				<tr><td align="right">Balance: <input type="number" name="balanceModify"></td></tr>
				<tr><td align="center"><input type="submit" formaction="modifyCustomer" value="Modify Customer"></td></tr>
				</table></td>
				<td><table>
				<tr><td align="right">Account Number: <input type="number" name="accNoDelete"></td></tr>
				<tr><td align="center"><input type="submit" formaction="deleteCustomer" value="Delete Customer"></td></tr>
				</table></td>
			</tr>
		</table>
	</form>
	<div style="width: 15%; margin-left: auto; margin-right: auto; margin-top: 5%">
	<b style="white-space: pre-line"><%=request.getAttribute("result") != null ? request.getAttribute("result") : "" %></b>
	</div>
</body>
</html>