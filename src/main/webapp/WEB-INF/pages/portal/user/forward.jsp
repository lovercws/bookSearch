<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>跳转到登录界面</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript">
function waitTime(){
	var extraTime=$("#extraTime").text();
	if(extraTime==0){
		clearInterval(intervalId);
		//跳转到首页
		window.location.href="<%=request.getContextPath()%>/portal/loginAction_goLogin.do";
	}else{
		$("#extraTime").text(extraTime-1);
	}
}

var intervalId=null;
$(document).ready(function(){
	//每隔一秒 调用一次
	intervalId=setInterval("waitTime()",1000);
});
</script>
</head>
<body style="margin:0px;padding: 0px;background: url(<%=request.getContextPath()%>/images/common/light.png);">
	<div style="margin-top: 20%">
		<table align="center">
			<tr>
				<td style="text-align: center;"> <h1><font color="red">${requestScope.forwardMsg}</font></h1></td>
			</tr>
			<tr>
				<td style="text-align: center;"> <h2><font color="green">调转到登录界面,还剩 <span id="extraTime">5</span>秒</font></h2></td>
			</tr>
		</table>
	</div>
</body>
</html>