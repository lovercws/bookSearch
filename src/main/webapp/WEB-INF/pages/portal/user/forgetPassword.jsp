<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>忘记密码(通过验证邮箱,重新设置密码)</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/themes/icon.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<style type="text/css">
.bodycolor {
    background: #ffffff url("http://localhost:8002/theme/3/table-bg.jpg") repeat-x scroll center top;
}
a{
    text-decoration: none;
}
</style>
<script type="text/javascript">
//重新加载验证码
function reload(obj){
	obj.src="<%=request.getContextPath()%>/portal/verifyCodeAction_imageCode.do?nocache="+new Date().getTime();
}
$(document).ready(function(){
	$("#verifyEmail").click(function(){
		//验证 邮箱
		$('#validForm').form('submit', {  
			url:'<%=request.getContextPath()%>/portal/verifyCodeAction_verifyEmail.do',
			novalidate:true,
			ajax:true,
		    onSubmit: function(){
		    	var isValid = $(this).form('validate');
				return isValid;
		    },    
		    success:function(data){ 
		    }    
		}); 
	});
});
</script>
</head>
<body style="margin:0px;padding: 0px;background: url(<%=request.getContextPath()%>/images/common/light.png);">
	<div style="margin-left: 90%;padding:0px;">
	   <a href="<%=request.getContextPath()%>/index.jsp">返回首页</a>
	</div>
	<form id="validForm" method="post">
	<table width="600" cellspacing="0" cellpadding="3" border="0" align="center">
		<tbody>
			<tr>
				<td colspan="2"><b><font color="#000000">通过绑定的邮箱重置您的密码：</font></b><br>
				</td>
			</tr>
			<tr>
				<td colspan="2"><hr width="100%" color="#000000" align="center" height="1"></td>
			</tr>
			<tr>
				<td width="80">绑定邮箱：</td>
				<td><input size="25" name="email" class="easyui-validatebox" data-options="required:true,validType:'email',missingMessage:'请输入邮箱'"> </td>
			</tr>
			<tr>
				<td>验证码：</td>
				<td><input size="16" name="imageCode" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入验证码'"><img onclick="reload(this)" style="vertical-align:middle" src="<%=request.getContextPath()%>/portal/verifyCodeAction_imageCode.do">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="发送验证邮件" id="verifyEmail" size="20" style="margin-left: 110px;margin-top: 20px;background-color: silver;"></td>
			</tr>
		</tbody>
	</table>
	</form>
</body>
</html>