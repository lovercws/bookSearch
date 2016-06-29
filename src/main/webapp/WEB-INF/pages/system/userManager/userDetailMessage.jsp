<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户详细信息</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js" charset="UTF-8"></script>
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
/* $.fn.datebox.defaults.formatter = function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'m+'-'+d;
} */
$(document).ready(function(){
	var data=eval("("+"${requestScope.userDetailJson}"+")");
	$("#userDetailMessageForm").form("load",data);
	
	$("#changeUserDetail").click(function(){
		var validate=$('#ff').form("validate");
		if(validate){
			$("#userDetailMessageForm").submit();
		}
	});
});
</script>
</head>
<body>
     <h2 align="center"><font color="silver">用户信息</font></h2>
     <form id="userDetailMessageForm" action="<%=request.getContextPath()%>/system/userManagerAction_changeDetailMessage.do" method="post">
        <input type="hidden" name="id">
		<table align="center" style="margin-top: 5%;">
			<tr>
				<td>用户名</td>
				<td><input name="name" class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:500px"> </td>
			</tr>
			<tr>
				<td>昵称</td>
				<td><input name="nickname" class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:500px"> </td>
			</tr>
			<tr>
				<td>用户性别</td>
				<td><input type="radio" name="sex"  value="1" >男&nbsp;&nbsp;<input type="radio" name="sex" value="2">女</td>
			</tr>
			<tr>
				<td>手机号码</td>
				<td><input name="mobile" class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:500px"> </td>
			</tr>
			<tr>
				<td>座机号码</td>
				<td><input name="contactTel" class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:500px"> </td>
			</tr>
			<tr>
				<td>生日</td>
				<td>
					<input name="birthday" style="width:496px" onClick="WdatePicker()"> 
				</td>
			</tr>
			<tr>
				<td>用户头像</td>
				<td><input name="headerImage" class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:500px"> </td>
			</tr>
			<tr>
				<td>Email地址</td>
				<td><input name="email" class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:500px"> </td>
			</tr>
			<tr>
				<td>备注</td>
				<td><textarea name="remark" rows="10" cols="" style="width:500px"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input id="changeUserDetail" type="button" value="修改"></td>
			</tr>
		</table>
	</form>
</body>
</html>