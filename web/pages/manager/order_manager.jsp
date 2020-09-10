<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			<span class="wel_word">Order Management</span>

			<%--static menu--%>
			<%@ include file="/pages/common/manager_menu.jsp"%>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>DateTime</td>
				<td>Amount</td>
				<td>Status</td>
				<td>Details</td>
				<td>Operate</td>
			</tr>
			<c:forEach items="${requestScope.allOrders}" var="order">
				<tr>
					<td>${order.createTime.toString().substring(0,19)}</td>
					<td>${order.price}</td>
					<td>${order.status == 0? "processing":"done"}</td>
					<td><a href="orderServlet?action=showOrderDetails&orderId=${order.orderId}">View details</a></td>
					<td><a class="deleteOrder" href="orderServlet?action=deleteOrder&orderId=${order.orderId}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<%--static page footer--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>