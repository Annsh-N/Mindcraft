<html>
<body>
<h2>Add Laptop</h2>
<form action="insert">
PID: <input type="text" name="pid">
Make: <input type="text" name="make">
Cost: <input type="text" name="cost">
<input type="submit">
<%-- <%=request.getAttribute("result") %> --%>
</form>
<form action="display">
<input type="submit" value="Display all Laptops">
</form>
</body>
</html>
