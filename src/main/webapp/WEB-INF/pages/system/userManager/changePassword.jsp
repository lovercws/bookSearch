<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改登录用户的额密码</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<style type="text/css">
body{
   margin: 0px;
   padding: 0px;
   width: 100%;
   height: 100%;
   position: absolute;
   background: url(<%=request.getContextPath()%>/images/common/light.png);
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#changePasswordBtn").click(function(){
		var password=$("input[name=password]").val();
		var newPassword=$("input[name=newPassword]").val();
		var newPassword2=$("input[name=newPassword2]").val();
		if(newPassword!=newPassword2){
			$.messager.alert('警告','你输入的新密码不同');
		}else{
			$.ajax({
				url:'<%=request.getContextPath()%>/system/userManagerAction_changePwd.do',
				type:'POST',
				data:'password='+password+"&newPassword="+newPassword,
				success:function(html){
					var json=eval("("+html+")");
					if(json.success){
						$.messager.alert('提示','密码修改成功!');
					}else{
						$.messager.alert('提示','密码修改失败!');
					}
				}
			});
		}
	});
});
</script>
</head>
<body>
	<form id="form" method="post" title="修改密码">
		<table align="center" style="margin-top: 20%;">
			<tr>
				<td>输入原密码</td>
				<td><input name="password" class="easyui-textbox" data-options="iconCls:'icon-search',required:true,type:'password'" style="width:300px"> </td>
			</tr>
			<tr>
				<td>输入新密码:</td>
				<td><input name="newPassword" class="easyui-textbox" data-options="iconCls:'icon-search',required:true,type:'password'" style="width:300px"> </td>
			</tr>
			<tr>
				<td>再次输入新密码:</td>
				<td><input name="newPassword2" class="easyui-textbox" data-options="iconCls:'icon-search',required:true,type:'password'" style="width:300px"> </td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" id="changePasswordBtn" value="修改"></td>
			</tr>
			<tr>
				<td align="center">${requestScope.changePasswordMsg}</td>
			</tr>
		</table>
	</form>
</body>
</html>