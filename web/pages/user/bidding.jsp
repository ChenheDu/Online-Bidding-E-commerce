<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bidding</title>

    <%--static base label--%>
    <%@ include file="/pages/common/head.jsp"%>

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

    <script type="text/javascript">

        // 未来时间
        // 当前时间
        // 时间差
        // 转换天：时：分：秒
        function tm() {
            var date = "${fn:substring(requestScope.book.date,0,19)}";
            // var timeEnd = new Date("2020-8-27 16:08:00");
            var timeEnd = new Date(date);
            var timeStart = new Date($.ajax({async: false}).getResponseHeader("Date")); //server time
            // var timeStart = new Date();
            // 时间差单位是毫秒、1秒=1000毫秒
            var t = Math.floor((timeEnd - timeStart) / 1000);
            // console.log(t);
            // var d = Math.floor(t / 60 / 60 / 24);
            var d = Math.floor(t / 60 / 60 / 24);
            var h = Math.floor(t / 60 / 60 % 24);
            var m = Math.floor(t / 60 % 60);
            var s = Math.floor(t % 60);
            if (t > 0) {
                // var str = d + "天" + h + "时" + m + "分" + s + "秒";
                var str = d + " d " + h + " h " + m + " m " + s + " s ";
                document.querySelector(".box").innerHTML = str;
            }else{
                document.querySelector(".box").innerHTML = "End of bidding!";
            }
        }
        // 倒计时
        setInterval(function () {
            tm()
        }, 1000)

        $(function () {
        	//bind a click action to add_to_cart button
        	$("button.addToCart").click(function () {
        		/**
        		 * this means the dom of current reaction to func
        		 * @type {jQuery}
        		 */
        		var bookId = $(this).attr("bookId");
        		location.href = "http://localhost:8080/bookStore/cartServlet?action=addItem&id=" + bookId;


        	});
        });

    </script>
</head>
<body>
    <div id="header">
        <img class="logo_img" alt="" src="../../static/img/logo.png" >
        <span class="wel_word">Bidding</span>

        <%--turn to success page--%>
        <%@ include file="/pages/common/login_success_menu.jsp"%>
    </div>

    <div id="main">

        <form action="manager/bookServlet" method="get">
            <input type="hidden" name="pageNo" value="${param.pageNo}">
            <%--<input type="hidden" name="action" value="${ empty param.id? "add":"update" }">--%>
            <input type="hidden" name="action" value="updateBiddingBooks">
            <input type="hidden" name="id" value="${ requestScope.book.id }">
            <table>
                <tr>
                    <td>Item</td>
                    <td>Price</td>
                    <td>Owner</td>
                    <td>Picture</td>
                    <td>Bidder</td>
                    <td>Countdown</td>
                    <td>Operate</td>
                </tr>
                <tr>
                    <td><input name="name" type="text" value="${requestScope.book.name}" readonly="readonly"/></td>
                    <td><input name="price" type="number" value="${requestScope.book.price}" min="${requestScope.book.price}"/></td>
                    <td><input name="owner" type="text" value="${requestScope.book.owner}" readonly="readonly"/></td>
                    <td><img class="book_img" height="60" width="60" alt="" src="${book.imgPath}" /></td>
                    <td><input name="bidder" type="text" value="${sessionScope.user.username}" readonly="readonly"/></td>
                    <td><div class="box"></div></td>
                    <td><input type="submit" value="Submit"/></td>
                    <input name="date" type="hidden" value="${requestScope.book.date}" />
                    <input name="imgPath" type="hidden" value="${requestScope.book.imgPath}" />
                </tr>
            </table>
        </form>
    <%--    <table>--%>
    <%--        --%>

    <%--&lt;%&ndash;        <c:forEach items="${requestScope.page.items}" var="book">&ndash;%&gt;--%>
    <%--            <tr>--%>
    <%--                <td>${requestScope.book.name}</td>--%>
    <%--                &lt;%&ndash;<td>${book.name}</td>&ndash;%&gt;--%>
    <%--                <td>${book.price}</td>--%>
    <%--                <td>${book.author}</td>--%>
    <%--                <td>${book.sales}</td>--%>
    <%--                <td>${book.stock}</td>--%>
    <%--                <td><img class="book_img" height="60" width="60" alt="" src="${book.imgPath}" /></td>--%>
    <%--                <td><div class="box"></div></td>--%>
    <%--            </tr>--%>
    <%--&lt;%&ndash;        </c:forEach>&ndash;%&gt;--%>

    <%--        <tr>--%>
    <%--            <td></td>--%>
    <%--            <td></td>--%>
    <%--            <td></td>--%>
    <%--            <td></td>--%>
    <%--            <td></td>--%>
    <%--            <td></td>--%>
    <%--            &lt;%&ndash;<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">addToCart</a></td>&ndash;%&gt;--%>
    <%--            <td><button bookId="${book.id}" class="addToCart">addToCart</button></td>--%>
    <%--        </tr>--%>

    <%--    </table>--%>

        <%--        &lt;%&ndash;static paging&ndash;%&gt;--%>
        <%--        <%@include file="/pages/common/page_nav.jsp"%>--%>

    </div>


<%--page foot--%>
<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>