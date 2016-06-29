<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js//easyui-lang-zh_CN.js" ></script>
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
	$('#userListTable').datagrid({
		toolbar: [{
			text:'Telphone',
			hidden:true,
			iconCls: 'icon-add',
			handler: function(){
			}
		},'-',{
			text:'Email',
			hide:true,
			iconCls: 'icon-edit',
			handler: function(){
			}
		}]
	});
});

</script>
</head>
<body>
	<!-- 用户列表展示 -->
	<table id="userListTable" class="easyui-datagrid" style="width:100%;height:98%;position: relative"   
		data-options="fitColumns:true,rownumbers:true,singleSelect:true">   
		<thead>   
			 <tr>   
			    <th data-options="field:'id',hidden:true">用户ID</th>   
			    <th data-options="field:'sex',hidden:true">用户ID</th>   
			    <th data-options="field:'name'" style="width: 100px">用户名称</th>   
			    <th data-options="field:'nickname'" style="width: 100px">用户别名</th>   
			    <th data-options="field:'sex2'" style="width: 100px">用户性别</th>   
			    <th data-options="field:'mobile'"  style="width: 150px">手机号码</th>   
			    <th data-options="field:'contactTel'"  style="width: 150px">座机号码</th>   
			    <th data-options="field:'headerImage'"  style="width: 200px">用户头像</th>
			    <th data-options="field:'email'"  style="width: 150px">Email地址</th>   
			    <th data-options="field:'birthday'"  style="width: 150px">生日</th>   
			    <th data-options="field:'address'"  style="width: 200px">联系地址</th>   
			    <th data-options="field:'remark'"  style="width:20%">备注</th>   
			  </tr>   
		</thead> 
		<tbody>
		  <s:iterator value="#request.userList" var="user">
		  	<tr>
		  		<td><s:property value="#user.id"/> </td>
		  		<td><s:property value="#user.sex"/> </td>
		  		<td><s:property value="#user.name"/> </td>
		  		<td><s:property value="#user.nickname"/> </td>
		  		<td><s:if test="#user.sex==1">男</s:if><s:else>女</s:else> </td>
		  		<td><s:property value="#user.mobile"/> </td>
		  		<td><s:property value="#user.contactTel"/> </td>
		  		<td><s:property value="#user.headerImage"/> </td>
		  		<td><s:property value="#user.email"/> </td>
		  		<td><s:date name="#user.birthday" format="yyyy-MM-dd"/> </td>
		  		<td><s:property value="#user.address"/> </td>
		  		<td><s:property value="#user.remark"/> </td>
		  	</tr>
		  </s:iterator>
		</tbody>  
	</table>
<div id="pp" class="easyui-pagination" data-options="total:2000,pageSize:10" style="background:#efefef;border:1px solid #ccc;"></div> 		
</body>
</html>