<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration page</title>

<%@ include file="/pages/common/head.jsp"%>

<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>

<script type="text/javascript">
	$(function(){

		//click action for verifation code
		$("#code_img").click(function () {
			//在事件响应的function函数中有一个this对象，这个this对象，是当前正在相应事件的dom对象
			//src属性表示验证码img标签的 图片路径，它可读可写
			this.src = "${basePath}kaptcha.jpg?d=" + new Date();
		});

		//bind click action to submit
		$("#sub_btn").click(function(){
			//verify the submit info
			//1.verify the username
			var usernameText = $("#username").val();
			//generate RegExp Obj
			var usernamePatt = /^\w{5,12}$/;
			//verify
			if(!usernamePatt.test(usernameText)){
				$("span.errorMsg").text("illegal username!");
				return false;
			}

			//2.verify the password
			var passwordText = $("#password").val();
			//generate RegExp Obj
			var passwordPatt = /^\w{5,12}$/;
			//verify
			if(!passwordPatt.test(passwordText)){
				$("span.errorMsg").text("illegal password!");
				return false;
			}

			//confirm pwd
			var repwdText = $("#repwd").val();
			//compare
			if(repwdText != passwordText){
				$("span.errorMsg").text("inconsistent password!");
				return false;
			}

			//verify email addr
			var emailText = $("#email").val();
			//generate RegExp Obj
			var emailPatt = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			//verify
			if(!emailPatt.test(emailText)){
				$("span.errorMsg").text("illegal email!");
				return false;
			}

			//Captcha varify
			var codeText = $("#code").val();

			//delete the space of Captcha
			codeText = $.trim(codeText);
			if(codeText == null ||codeText == ""){
				$("span.errorMsg").text("Captcha needed!");
				return false;
			}




			$("span.errorMsg").text("");
			
		});

	})
</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.png" >
		</div>
		
			<div class="login_banner">
			
				<%--<div id="l_content">--%>
				<%--	<span class="login_word">Welcome!</span>--%>
				<%--</div>--%>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>Registration</h1>
								<span class="errorMsg">
									<%--<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
									${ requestScope.msg }
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>Username:</label>
									<input class="itxt" type="text" placeholder="Please enter user name"
									autocomplete="off" tabindex="1" name="username" id="username"
									value="${ requestScope.username }" />
									<br />
									<br />
									<label>Password :</label>
									<input class="itxt" type="password" placeholder="Please enter password"
									autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>Confirm &nbsp&nbsp:</label>
									<input class="itxt" type="password" placeholder="Confirm password"
									autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>Mailbox &nbsp&nbsp:</label>
									<input class="itxt" type="text" placeholder="Please enter email address"
									autocomplete="off" tabindex="1" name="email" id="email"
									value="${ requestScope.email }" />
									<br />
									<br />
									<label>Verify &nbsp:</label>
									<input class="itxt" type="text" name="code" style="width: 150px;" id="code"/>
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right;
										 margin-right: 40px; width: 100px; height: 40px">
									<br />
									<br />
									<input type="submit" value="Register" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>

		<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>