<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册用户</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
	function register(){
		$('#registerForm').form('submit', {
			novalidate:true,
			ajax:true,
			url: '<%=request.getContextPath()%>/portal/registryAction_register.do',
			onSubmit: function(){
				var isValid = $(this).form('validate');
				return isValid;
			},
			success: function(data){
				//parent.window.locattion.href='<%=request.getContextPath()%>/portal/loginAction_register.do';
			}
		});
	}
</script>
</head>
<body style="padding:0px;margin:0px;background-image: url(<%=request.getContextPath()%>/images/common/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">
<jsp:include page="/WEB-INF/pages/portal/header.jsp"/>
<div style="margin-top: 5%;padding: 0px;background-image: url(<%=request.getContextPath()%>/images/common/logininfo.png);background-repeat: no-repeat; background-position: center top; overflow: hidden;height:800px">
   <form id="registerForm" method="post">   
	  <table align="center" style="margin-top: 80px;position: relative;">
	    <tr>
	       <td>用户名: </td>
	       <td><input name="userName" style="width:300px" class="easyui-validatebox" data-options="required:true"></td>
	    </tr>
	    <tr style="position: relative;margin-top: 20px">
	       <td>密码:</td>
	       <td><input name="password" style="width:300px" class="easyui-validatebox" data-options="required:true"></td>
	    </tr>
	    <tr>
	       <td>确认密码:</td>
	       <td><input name="rePassword" style="width:300px" class="easyui-validatebox" data-options="required:true"></td>
	    </tr>
	    <tr style="margin-top: 20px">
	       <td>邮箱:</td>
	       <td><input name="email" style="width:300px" class="easyui-validatebox" data-options="required:true,validType:'email'"></td>
	    </tr>
	    <tr style="margin-top: 20px">
	       <td colspan="2" style="text-align: center;"><button type="submit" style="width: 120px;background-color: red;height: 30px;vertical-align: middle;" onclick="register()">注册</button></td>
	    </tr>
	  </table>
   </form>
</div>
</body>
</html>