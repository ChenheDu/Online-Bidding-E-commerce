<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bidding cart</title>

    <%@ include file="/pages/common/head.jsp"%>
    <script type="text/javascript">

        $(function () {
            //cast a click action for delete
            $("a.deleteClass").click(function () {
                //在事件的function函数中，有一个this对象，这个this对象是当前正在响应事件的dom对象
                /**
                 * confirm是确认提示框函数
                 * 参数是它的提示内容
                 * 有两个按钮，一个确认，一个取消
                 * 返回true表示点击了确认，返回false表示点击取消
                 */
                return confirm("Delete Confirmation【" + $(this).parent().parent().find("td:first").text() + "】?");
            });
        });
    </script>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.png" >
    <span class="wel_word">Bidding Items</span>

    <%--turn to success page--%>
    <%@ include file="/pages/common/login_success_menu.jsp"%>

</div>

<div id="main">
    <table>
        <tr>
            <td>Item</td>
            <td>Current price</td>
            <td>Owner</td>
            <td>Bidder</td>
            <td>Picture</td>
            <td>Deadline</td>
        </tr>

        <c:if test="${empty requestScope.books}">
            <%--if the processing is empty--%>
            <tr>
                <td colspan="6"><a href="index.jsp">The processing is empty! Would you like to check out the product?</a> </td>

            </tr>
        </c:if>
        <c:if test="${not empty requestScope.books}">
            <%--if the processing is not empty--%>
            <c:forEach items="${requestScope.books}" var="book">
                <tr>
                    <td>${book.name}</td>
                    <td>${book.price}</td>
                    <td>${book.owner}</td>
                    <td>${book.bidder}</td>
                    <td><img class="book_img" height="60" width="60" alt="" src="${book.imgPath}" /></td>
                    <td>${fn:substring(book.date,0,19)}</td>

                </tr>
            </c:forEach>
        </c:if>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <%--<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">Add goods</a></td>--%>
        </tr>
    </table>

    <%--        &lt;%&ndash;static paging&ndash;%&gt;--%>
    <%--        <%@include file="/pages/common/page_nav.jsp"%>--%>

</div>

<br><br>
<%--static page foot--%>
<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>