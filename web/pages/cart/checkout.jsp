<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout page</title>

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
			<span class="wel_word">Checkout</span>

			<%@ include file="/pages/common/login_success_menu.jsp"%>

	</div>
	
	<div id="main">
		
		<h1>Your order has been settled, the order number is ${sessionScope.orderId} , payment create card is ${sessionScope.cardnumber}</h1>
		
	
	</div>

	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>