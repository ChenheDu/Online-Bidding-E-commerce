<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping cart</title>

	<%@ include file="/pages/common/head.jsp"%>

</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.png" >
			<span class="wel_word">Shopping Cart</span>
		<%--static include--%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>
		<script type="text/javascript">
			$(function () {
				//bind a delete alert to the click button
				$("a.deleteItem").click(function () {
					return  confirm("Are you sure to delete " + $(this).parent().parent().find("td:first").text() + " ?");
				});

				//bind an alert to the clear Cart button
				$("#clearCart").click(function () {
					return confirm("Are you sure to clear the Cart ?");
				});

				//bind <<blue action>> to input form  == onchange action
				$(".updateCount").change(function () {
					//get the item name
					var name = $(this).parent().parent().find("td:first").text();

					var id = $(this).attr("bookId");
					//get the item count
					var count = this.value;
					if(confirm("Are you sure to change the number of " + name + " to " + count + " ?")){
						//request to server to change the modification
						location.href = "http://localhost:8080/bookStore/cartServlet?action=updateCount&count=" + count + "&id=" + id;
					} else {
						//defaultValue attr is the default value of form Dom obj
						this.value = this.defaultValue;
					}
				});

			});
		</script>

	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>Item</td>
				<td>Quantity</td>
				<td>Auction price</td>
				<td>Amount</td>
				<td>Operate</td>
			</tr>		

			<c:if test="${empty sessionScope.cart.items}">
				<%--if the cart is empty--%>
				<tr>
					<td colspan="5"><a href="index.jsp">The cart is empty! Would you like to check out the product?</a> </td>

				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<%--if the cart is not empty--%>
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="updateCount" style="width: 25px;"
									bookId = "${entry.value.id}"
								   type="text" value="${entry.value.count}">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</c:if>

			
		</table>

		<%--only output when cart is not empty--%>
		<c:if test="${not empty sessionScope.cart.items}">
		<div class="cart_info">
			<span class="cart_span">Total <span class="b_count">${sessionScope.cart.totalCount}</span> goods in cart</span>
			<span class="cart_span">Total amount $ <span class="b_price">${sessionScope.cart.totalPrice}</span></span>
			<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">Clear shopping cart</a></span>
			<%--<span class="cart_span"><a href="orderServlet?action=createOrder">Checkout</a></span>--%>
			<span class="cart_span"><a href="orderServlet?action=createOrder">Checkout</a></span>
		</div>
		</c:if>
	
	</div>

	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>