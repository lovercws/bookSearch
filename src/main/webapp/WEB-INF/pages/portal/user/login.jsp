<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/themes/icon.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var topWin=top.window;
	if(self!=topWin){
		topWin.location=window.location;
	}
	//提交表单 用户登录
	function submit(){
		var form = document.getElementById('loginForm');
		var username = $("input[name=name]").val();
		var password = $("input[name=password]").val();
		var verifyCode = $("input[name=verifyCode]").val();
		if(username == "" ){
			$.messager.alert('提示','登录名不能为空！');
			return false;
		}
		if(password == ""){
			$.messager.alert('提示','密码不能为空！');
			return false;
		}
		if(verifyCode==""){
			$.messager.alert('提示','验证码不能为空！');
			return false;
		}
		form.submit();
	}
	
	//重新加载验证码
	function reload(obj){
		obj.src="<%=request.getContextPath()%>/portal/verifyCodeAction_imageCode.do?type=login&nocache="+new Date().getTime();
	}
	
	//键盘 enter提交表单
	$(function(){
		$("body").keydown(function(event){
			if(event.keyCode==13){
				submit();
			}
		});
	});
</script>
<style type="text/css">
body{
	font-family:"微软雅黑";
	text-align:left;
	margin:0 auto;
	padding:0;
	background:#FFF;
	font-size:14px;
	color:#333;
}
.login{
    cursor: pointer;margin-left: 90px;background: url(<%=request.getContextPath()%>/images/common/loginbutton1_11.gif) no-repeat center;float:left; display:block;width:60px; height:38px;line-height:38px;text-align:center
}
.registory{
    cursor: pointer;margin-left: 90px;background: url(<%=request.getContextPath()%>/images/common/loginbutton1_11.gif) no-repeat center;float:left; display:block;width:60px; height:38px;line-height:38px;text-align:center
}
.forgetPassword{
    cursor: pointer;margin-left: 90px;background: url(<%=request.getContextPath()%>/images/common/loginbutton1_11.gif) no-repeat center;float:left; display:block;width:60px; height:38px;line-height:38px;text-align:center
}
a{
    text-decoration: none;
}
</style>
</head>
<body style="padding:0px;margin:0px;background-image: url(<%=request.getContextPath()%>/images/common/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">
	<div style="margin-left: 90%;padding:0px;">
	   <a href="<%=request.getContextPath()%>/index.jsp">返回首页</a>
	</div>
	<div id="login" style="margin-top: 5%">
		<div class="blur"><h1 align="center"><font size="24">读书搜索管理平台</font></h1></div>
		<form id="loginForm" action="<%=request.getContextPath()%>/portal/loginAction_login.do" method="post" style="text-align: center">
			<div class="center" style="text-align: center;margin-left: 30%;margin-top: 5%;font-size: 18">
				<div class="loginTitle"></div>
				<div class="loginBody" style="text-align: left;">
					<div class="subland">
						<p style="margin-top: 30px">用&nbsp;户&nbsp;名 &nbsp;:
							<input class="bj1" name="name" style="vertical-align: middle; line-height: 30px; height: 30px;width:40%;margin-left: 20px" />
						</p>
						<p style="margin-top: 30px">密&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;&nbsp;: 
							<input class="bj2" type="password" name="password" style="vertical-align: middle; line-height: 30px;width:40%; height: 30px;margin-left: 24px" />
						</p>
						<p style="position: relative;margin-top: 30px">验&nbsp;证&nbsp;码&nbsp;:
							<input type="text" name="verifyCode" class="bj3" style="vertical-align: middle; line-height: 30px;width:40%; height: 30px;margin-left: 20px" />
							<img style="margin-left: 10px; position: absolute; top: 1px; left: 40%;" alt="验证码" onclick="reload(this)"
								src="<%=request.getContextPath()%>/portal/verifyCodeAction_imageCode.do?type=login" width="75" height="35" />
						</p>
						<p style="margin-top: 30px">
							<span><a class="login" href="#" onClick="submit()">登录</a></span>
							<span><a class="registory" href="<%=request.getContextPath()%>/portal/registryAction_goReg.do">注册</a></span>
							<span><a class="forgetPassword" href="<%=request.getContextPath()%>/portal/registryAction_forgetPassword.do">忘记密码</a></span>
						</p>
					</div>
				</div>
				<div align="left" style="float: left;margin-top: 50px"><font color="red">${requestScope.errorMsg}</font></div>
			</div>
		</form>
	</div>
</body>
</html>