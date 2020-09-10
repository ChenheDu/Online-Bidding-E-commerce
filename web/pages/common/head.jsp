<%--
  Created by IntelliJ IDEA.
  User: DCH
  Date: 5/21/2020
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--static addr--%>
<%
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":"
            + request.getServerPort()
            + request.getContextPath() + "/";

    pageContext.setAttribute("basePath", basePath);
%>
<%--<%=basePath%>--%>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >

<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>