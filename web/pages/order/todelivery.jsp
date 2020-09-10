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
			<span class="wel_word">To delivery</span>

			<%@ include file="/pages/common/login_success_menu.jsp"%>

	</div>
	
	<div id="main">

		<table>
			<tr>
				<td>OrderId</td>
				<td>ItemName</td>
				<td>Price</td>
<%--				<td>DeliverTo</td>--%>
				<td>Status</td>
				<td>Shipping</td>
			</tr>
			<c:if test="${empty requestScope.soldItems}">
				<%--if the soldItems is empty--%>
				<tr>
					<td colspan="6"><a href="manager/bookServlet?action=listUploadBook">Your sold item is empty! Would you like to upload the product?</a> </td>

				</tr>
			</c:if>
			<c:if test="${not empty requestScope.soldItems}">
				<%--if the cart is not empty--%>
				<c:forEach items="${requestScope.soldItems}" var="soldItem">
					<tr>
						<td>${soldItem.orderId}</td>
						<td>${soldItem.name}</td>
						<td>${soldItem.totalPrice}</td>
							<%--						<td>${orderItem.status == 0? "processing":"done"}</td>--%>
						<td>
							<c:if test="${soldItem.status == 0}">Not shipped</c:if>
							<c:if test="${soldItem.status == 1}">Waiting for delivery</c:if>
							<c:if test="${soldItem.status == 2}">Received</c:if>
						</td>
						<td><a href="orderServlet?action=sendOrderItem&itemId=${soldItem.id}">Shipped</a></td>
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