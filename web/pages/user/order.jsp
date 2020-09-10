<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Management</title>

	<%@ include file="/pages/common/head.jsp"%>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.png" >
			<span class="wel_word">Order</span>

		<%--turn to success page--%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>Date</td>
				<td>Amont</td>
				<td>Details</td>
				<td>Shipping</td>
				
			</tr>		
			<tr>
				<td>2019.04.23</td>
				<td>90.00</td>
				<td><a href="#">View details</a></td>
				<td><a href="#">Click to ship</a></td>
			</tr>	
			
			<tr>
				<td>2020.04.20</td>
				<td>20.00</td>
				<td><a href="#">View details</a></td>
				<td>shipped</td>
			</tr>	
			
			<tr>
				<td>2014.01.23</td>
				<td>190.00</td>
				<td><a href="#">View details</a></td>
				<td>Waiting for delivery</td>
			</tr>		
		</table>
	</div>

	<%--static page footer--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>