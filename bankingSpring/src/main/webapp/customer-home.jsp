<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer</title>
</head>
<body>
<h3>Welcome <%=request.getAttribute("name")%></h3>
<h3>Current Balance: <%=request.getAttribute("balance")%></h3>
	<form>
		<table  style="width: 95%; margin: 0 auto; border-spacing: 10px">
			<tr>
				<td><table>
				<tr><td align="right">Amount: <input type="number" name="amountDeposit"></td></tr>
				<tr><td align="center"><input type="submit" formaction="deposit" value="Deposit"></td></tr>
				</table></td>
				<td><table>
				<tr><td align="right">Amount: <input type="number" name="amountWithdraw"></td></tr>
				<tr><td align="center"><input type="submit" formaction="withdraw" value="Withdraw"></td></tr>
				</table></td>
				<td><table>
				<tr><td align="right">Account Number: <input type="number" name="accNoTransfer"></td></tr>
				<tr><td align="right">Amount: <input type="number" name="amountTransfer"></td></tr>
				<tr><td align="center"><input type="submit" formaction="transfer" value="Transfer"></td></tr>
				</table></td>
				<td><table>
				<tr><td align="center"><input type="submit" formaction="statement" value="Print Statement"></td></tr>
				</table></td>
			</tr>
		</table>
	</form>
	<div style="width: 15%; margin-left: auto; margin-right: auto; margin-top: 5%">
	<b style="white-space: pre-line"><%=request.getAttribute("result") != null ? request.getAttribute("result") : "" %></b>
	</div>
</body>