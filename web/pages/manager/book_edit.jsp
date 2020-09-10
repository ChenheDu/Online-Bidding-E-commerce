<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit</title>

	<%--static base label--%>
	<%@ include file="/pages/common/head.jsp"%>

	<%
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nowDate = dateFormat.format(new Date());
		request.setAttribute("nowDate", nowDate);
	%>

<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.png" >
			<span class="wel_word">Item Edit</span>

			<%--static include manager_menu module--%>
			<%@ include file="/pages/common/manager_menu.jsp"%>
		</div>

		<div id="main">
			<form action="manager/bookServlet" method="get">
				<input type="hidden" name="pageNo" value="${param.pageNo}">
				<input type="hidden" name="action" value="${ empty param.id? "add":"update" }">
				<input type="hidden" name="id" value="${ requestScope.book.id }">
				<table>
					<tr>
						<td>Item</td>
						<td>Basic price</td>
						<td>Owner</td>
						<td>Picture</td>
						<td>Deadline</td>
						<td>Operate</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${requestScope.book.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.book.price}"/></td>
						<td><input name="owner" type="text" value="${requestScope.book.owner}"/></td>
						<td><input name="imgPath" type="text" value="${requestScope.book.imgPath}"/></td>
						<td><input name="date" type="text" value="${nowDate}"/></td>
						<td><input type="submit" value="Submit"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>

		<%--page foot--%>
		<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>