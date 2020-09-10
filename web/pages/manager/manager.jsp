<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Management</title>

	<%--static page head--%>
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
			<img class="logo_img" alt="" src="../../static/img/logo.png" >
			<span class="wel_word">Management System</span>

			<%--static menu--%>
			<%@ include file="/pages/common/manager_menu.jsp"%>

	</div>
	
	<div id="main">
		<h1>Welcome to the backstage management system.</h1>
	</div>

	<%--static page footer--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>