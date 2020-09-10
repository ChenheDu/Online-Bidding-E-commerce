<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment page</title>

    <%@ include file="/pages/common/head.jsp"%>

    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>

    <script type="text/javascript">
        $(function () {
            $("#checkout_btn").click(function () {

                location.href("../pages/cart/checkout.jsp");
            });
        });

    </script>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif" >
    <span class="wel_word">Payment</span>

    <%@ include file="/pages/common/login_success_menu.jsp"%>

</div>

<div id="main">

    <div id="content">
        <div class="payment_form">
            <div class="payment_box">
                <div class="tit">
                    <h1>Payment</h1>
                    <span class="errorMsg">
									<%--<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
									${ requestScope.msg }
								</span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="pay">
                        <label>Credit card number:</label>
                        <input class="itxt" type="text" placeholder="Please enter credit card number"
                               autocomplete="off" tabindex="1" name="cardnumber" id="cardnumber" />
                        <br />
                        <br />
                        <label>Cardholder's Name:</label>
                        <input class="itxt" type="password" placeholder="Please enter cardholder's Name"
                               autocomplete="off" tabindex="1" name="holdername" id="holdername" />
                        <br />
                        <br />
                        <label>Payment code: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
                        <input class="itxt" type="password" placeholder="Please enter payment code"
                               autocomplete="off" tabindex="1" name="paymentcode" id="paymentcode" />
                        <br />
                        <br />
                        <label>Credit card validity:&nbsp</label>
                        <input class="itxt" type="text" placeholder="Please enter card validity"
                               autocomplete="off" tabindex="1" name="cardvalidity" id="cardvalidity" />
                        <br />
                        <br />

                        <input type="submit" value="Checkout" id="checkout_btn" />

                    </form>
                </div>

            </div>
        </div>
    </div>


</div>

<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>