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
			<span class="wel_word">My order</span>

			<%@ include file="/pages/common/login_success_menu.jsp"%>

	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>DateTime</td>
				<td>Amount</td>
				<td>Status</td>
				<td>Details</td>
			</tr>
			<c:if test="${empty requestScope.orders}">
				<%--if the cart is empty--%>
				<tr>
					<td colspan="4"><a href="index.jsp">Your order is empty! Would you like to check out the product?</a> </td>

				</tr>
			</c:if>
			<c:if test="${not empty requestScope.orders}">
				<%--if the cart is not empty--%>
				<c:forEach items="${requestScope.orders}" var="order">
					<tr>
						<td>${order.createTime.toString().substring(0,19)}</td>
						<td>${order.price}</td>
						<td>${order.status == 0? "processing":"done"}</td>
						<td><a href="orderServlet?action=showOrderDetails&orderId=${order.orderId}">View details</a></td>
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