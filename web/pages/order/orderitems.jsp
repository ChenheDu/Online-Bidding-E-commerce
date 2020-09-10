<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My order</title>

	<%@ include file="/pages/common/head.jsp"%>

<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">Order Items</span>

			<%@ include file="/pages/common/login_success_menu.jsp"%>

	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>OrderId</td>
				<td>ItemName</td>
				<td>Price</td>
				<td>PreviousOwner</td>
				<td>Status</td>
				<td>Shipping</td>
			</tr>
			<c:if test="${empty requestScope.orderItems}">
				<%--if the cart is empty--%>
				<tr>
					<td colspan="6"><a href="index.jsp">Your order item is empty! Would you like to check out the product?</a> </td>

				</tr>
			</c:if>
			<c:if test="${not empty requestScope.orderItems}">
				<%--if the cart is not empty--%>
				<c:forEach items="${requestScope.orderItems}" var="orderItem">
					<tr>
						<td>${orderItem.orderId}</td>
						<td>${orderItem.name}</td>
						<td>${orderItem.totalPrice}</td>
						<td>${orderItem.owner}</td>
<%--						<td>${orderItem.status == 0? "processing":"done"}</td>--%>
						<td>
							<c:if test="${orderItem.status == 0}">Not shipped</c:if>
							<c:if test="${orderItem.status == 1}">Waiting for delivery</c:if>
							<c:if test="${orderItem.status == 2}">Shipped</c:if>
						</td>
						<td><a href="orderServlet?action=receiveOrderItem&itemId=${orderItem.id}">Received</a></td>
					</tr>
				</c:forEach>
			</c:if>

<%--			<tr>--%>
<%--				<td>2020.01.23</td>--%>
<%--				<td>190.00</td>--%>
<%--				<td>Completed</td>--%>
<%--				<td><a href="#">View details</a></td>--%>
<%--			</tr>		--%>
		</table>
		
	
	</div>

	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>