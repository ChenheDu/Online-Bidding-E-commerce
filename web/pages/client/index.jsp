<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Bidding</title>

	<%--static include--%>
	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">

		// $(function () {
		// 	//bind a click action to add_to_cart button
		// 	$("button.addToCart").click(function () {
		// 		/**
		// 		 * this means the dom of current reaction to func
		// 		 * @type {jQuery}
		// 		 */
		// 		var bookId = $(this).attr("bookId");
		// 		location.href = "http://localhost:8080/bookStore/cartServlet?action=addItem&id=" + bookId;
		//
		//
		// 	});
		// });

		$(function () {
			//bind a click action to add_to_cart button
			$("button.bidding").click(function () {
				/**
				 * this means the dom of current reaction to func
				 * @type {jQuery}
				 */
				var bookId = $(this).attr("bookId");
				// location.href = "http://localhost:8080/bookStore/cartServlet?action=addItem&id=" + bookId;
				location.href = "http://localhost:8080/bookStore/manager/bookServlet?action=bidBook&id=" + bookId;

			});
		});

	</script>

</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.png" >
			<span class="wel_word">Online Bidding</span>
			<div>
				<%--if user havent registed show the login/regist manu--%>
				<c:if test="${empty sessionScope.user}">
					<a href="pages/user/login.jsp">Login</a> |
					<a href="pages/user/regist.jsp">Create an Account</a> |
				</c:if>
				<%--already login/regist show the page with user info--%>
				<c:if test="${not empty sessionScope.user}">
					<span>Welcome! <span class="um_span">${sessionScope.user.username}</span>back to OnlineBidding</span>
					<a href="orderServlet?action=showMyOrders">My order</a> |
					<a href="orderServlet?action=showMySoldItems">To delivery</a> |
					<a href="userServlet?action=logout">Log out</a> |
                    <a href="manager/bookServlet?action=updateBiddingBooks">Processing</a> |
				</c:if>
				<a href="manager/bookServlet?action=listUploadBook">Upload</a> |
<%--				<a href="pages/user/upload.jsp">Upload</a> |--%>
				<a href="cartServlet?action=addToCart">Cart </a>

<%--				<c:if test="${empty sessionScope.user || sessionScope.user.username != 'admin'}">--%>
<%--					| <a href="pages/manager/manager.jsp">Management</a>--%>
<%--				</c:if>--%>
				<c:if test="${sessionScope.user.username == 'admin'}">
					| <a href="pages/manager/manager.jsp">Management</a>
				</c:if>


			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/bookServlet" method="get">
					<input type="hidden" name="action" value="pageByPrice">
					Price：$ <input id="min" type="text" name="min" value="${param.min}"> -
						$ <input id="max" type="text" name="max" value="${param.max}">
						<input type="submit" value="Search"/>
				</form>
			</div>
			<div style="text-align: center">
				<c:if test="${empty sessionScope.cart.items}">
					<%--output if cart empty--%>
					<span> </span>
					<div>
						<span style="color: red">The cart is empty now !</span>
					</div>
				</c:if>

				<c:if test="${not empty sessionScope.cart.items}">
					<%--output if cart isn't empty--%>
					<span>There are ${sessionScope.cart.totalCount} goods in your cart.</span>
					<div>
						You just add <span style="color: red">${sessionScope.lastName}</span> to your shopping cart!
					</div>
				</c:if>

			</div>

			<c:forEach items="${requestScope.page.items}" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${book.imgPath}" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">Item:</span>
						<span class="sp2">${book.name}</span>
					</div>
					<div class="book_author">
						<span class="sp1">Owner:</span>
						<span class="sp2">${book.owner}</span>
					</div>
					<div class="book_price">
                            <span class="sp1">Price:</span>
						<span class="sp2">${book.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">Bidder:</span>
						<span class="sp2">${ empty book.bidder? "none":book.bidder }</span>
					</div>
					<%--<div class="book_amount">--%>
					<%--	<span class="sp1">Stock:</span>--%>
					<%--	<span class="sp2">${book.stock}</span>--%>
					<%--</div>--%>


					<div class="bidding_time"+${requestScope.book.name} bidding_time="${requestScope.book.name}">
						<span class="sp1">Deadline:</span>
						<%--<span class="sp2">${book.date}</span>--%>
						<span class="sp2">${fn:substring(book.date,0,19)}</span>
					</div>

					<div class="book_add">
						<button bookId="${book.id}" class="bidding">Bidding</button>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
		
		<%--static paging--%>
		<%@include file="/pages/common/page_nav.jsp"%>
	</div>
	<%--static page foot--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>