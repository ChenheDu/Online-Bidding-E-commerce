<%--
  Created by IntelliJ IDEA.
  User: DCH
  Date: 5/21/2020
  Time: 10:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <span>Welcome! <span class="um_span">${sessionScope.user.username}</span>back to OnlineBidding</span>
    <a href="orderServlet?action=showMyOrders">My order</a> |
    <a href="orderServlet?action=showMySoldItems">To delivery</a> |
    <a href="manager/bookServlet?action=updateBiddingBooks">Processing</a> |
    <a href="manager/bookServlet?action=listUploadBook">Upload</a> |
    <a href="cartServlet?action=addToCart">Cart</a> |
    <a href="userServlet?action=logout">Log out</a> |
    <a href="index.jsp">Back</a>
</div>