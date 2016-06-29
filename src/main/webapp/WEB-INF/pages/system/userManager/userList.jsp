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
			text:'添加',
			iconCls: 'icon-add',
			handler: function(){
				$("#detailUserWin").window("open");
			}
		},'-',{
			text:'编辑',
			iconCls: 'icon-edit',
			handler: function(){
				var selections=$("#userListTable").datagrid("getSelections");
				if(selections&&selections.length>0){
					editUserForm(selections[0]);
				}else{
					$.messager.alert('提示','请选择一个用户');
				}
			}
		},'-',{
			text:'删除',
			iconCls: 'icon-remove',
			handler: function(){
				//获取选择 要删除的行
		        var selections=$("#userListTable").datagrid("getSelections");
				if(selections&&selections.length>0){
					$.messager.confirm('确认','您确认想要删除记录吗？',function(r){  
						var userId=selections[0].id;
					    if (r){    
					    	window.location.href="<%=request.getContextPath()%>/system/userManagerAction_deleteUser.do?id="+userId;
					    } 
					});
				}else{
					$.messager.alert('提示','请选择一个用户');
				}
			}
		},'-',{
			text:'分配角色',
			iconCls: 'icon-remove',
			handler: function(){
				 var selections=$("#userListTable").datagrid("getSelections");
					if(selections&&selections.length>0){
						allocateUserRole(selections[0].id);
						$("#userRoleWin").window("open");
					}else{
						$.messager.alert('提示','请选择一个用户');
					}
			}
		},'-',{
			text:'初始化密码',
			iconCls: 'icon-remove',
			handler: function(){
				//获取选择 要初始化密码的用户
		        var selections=$("#userListTable").datagrid("getSelections");
				if(selections&&selections.length>0){
					var userId=selections[0].id;
					$.messager.confirm('确认','您确认想要初始化该用户的密码吗？',function(r){
						if(r){
				    		window.location.href="<%=request.getContextPath()%>/system/userManagerAction_initPassword.do?id="+userId;
						}
					});
				}else{
					$.messager.alert('提示','请选择一个用户');
				}
			}
		},'-',{
			text:'导出',
			iconCls: 'icon-remove',
			handler: function(){
				window.location.href="<%=request.getContextPath()%>/system/userManagerAction_exportUser.do";
			}
		}],
		onDblClickRow:function(index,row){
			editUserForm(row);
		}
	});
	
	$("#detailUserWin").window("close");
	$("#userRoleWin").window("close");
	
	//编辑用户表单
	function editUserForm(node){
		for(var i in node){
			node[i]=node[i].trim();
		}
		
		$("#detailUserForm").form("load",node);
		$("#detailUserWin").window("open");
	}
	//selectedRoleList 绑定双击事件
	$('#selectedRoleList').datagrid({
		onDblClickRow:function(index,row){
			var selections=$("#userListTable").datagrid("getSelections");
			var userId=selections[0].id;
			deleteRoleFromUser(userId,row.id);
		}
	});
	
	//unSelectedRoleList 绑定双击事件
	$('#unSelectedRoleList').datagrid({
		onDblClickRow:function(index,row){
			var selections=$("#userListTable").datagrid("getSelections");
			var userId=selections[0].id;
			addRoleToUser(userId,row.id);
		}
	});
	
	//分配角色
	function allocateUserRole(userId){
		$.ajax({
			url:'<%=request.getContextPath()%>/system/userRoleAction_getUserRole.do?userId='+userId,
		    method:'POST',
		    async: false,
		    success:function(msg){
		    	handleResponse(msg);
		    }
		});
	}
	
	//处理返回结果
	function handleResponse(msg){
		var json=eval("("+msg+")");
    	var selectedRoleList=json['selectedRoleList'];
		var unSelectedRoleList=json['unSelectedRoleList'];
		
		$('#selectedRoleList').datagrid({
			data:selectedRoleList
		});
		$('#unSelectedRoleList').datagrid({
			data:unSelectedRoleList
		});
	}
	
	//添加用户的角色
	function addRoleToUser(userId,roleId){
		$.ajax({
			url:'<%=request.getContextPath()%>/system/userRoleAction_addRoleToUser.do',
		    method:'POST',
		    data:"userId="+userId+"&roleIds="+roleId,
		    async: false,
		    success:function(msg){
		    	handleResponse(msg);
		    }
		});
	}
	
	//删除用户的角色
	function deleteRoleFromUser(userId,roleId){
		$.ajax({
			url:'<%=request.getContextPath()%>/system/userRoleAction_deleteRoleFromUser.do',
		    method:'POST',
		    data:"userId="+userId+"&roleIds="+roleId,
		    async: false,
		    success:function(msg){
		    	handleResponse(msg);
		    }
		});
	}
});
//通过书籍信息来查询
function queryUser(){
	var validate=$('#queryForm').form('validate');
	if(validate){
		$('#queryForm').submit();
	}
}
function queryUserKeyUp(){
}

//关闭添加用户window
function closeUserForm(){
	$("#detailUserForm").form("reset");
	$("#detailUserWin").window("close");
}
//添加用户表单验证
function validateUserForm(){
	var vaildate=$("#detailUserForm").form("validate");
	if(vaildate){
		$("#detailUserForm").submit();
	}
}
</script>
</head>
<body>
    <!-- 查询用户 -->
	<div style="float: right;width:100%;height: 4%;position: relative;">
	   <form id="queryForm" action="<%=request.getContextPath()%>/system/userManagerAction_userList.do">
	        <table align="left">
	          <tr>
	              <td><font>用户名称:</font><input onkeyup="queryUserKeyUp()" class="easyui-textbox" name="name" value="${requestScope.name }" data-options="required:false" style="width:200px"></td>
	              <td style="width: 20px"></td>
	              <td><input type="button" style="background-color: silver;" value="查询" onclick="queryUser()"></td>
	          </tr>
	         </table>
	    </form>      
	</div>
	<!-- 用户列表展示 -->
	<table id="userListTable" class="easyui-datagrid" style="width:100%;height:92%;position: relative"   
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

	<!-- 添加用户 -->
	<div id="detailUserWin" class="easyui-window" title="添加用户 " style="width:600px;height:600px"   
        data-options="iconCls:'icon-save',modal:true,collapsible:false,inline:true">   
	    <div class="easyui-layout" data-options="fit:true">   
	           <form id="detailUserForm" method="post" style="height: 400px" action="<%=request.getContextPath()%>/system/userManagerAction_saveUser.do">
		           <input type="hidden" name="id">
		           <table align="center" style="margin-top: 20px">
		              <tr>
		                <td><label>用户名称</label></td>
		                <td><input class="easyui-textbox" name="name" data-options="required:true" style="width:300px"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>用户昵称</label></td>
		                <td><input class="easyui-textbox" name="nickname" data-options="required:true" style="width:300px"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>用户性别</label></td>
		                <td>&nbsp;&nbsp;男<input type="radio" name="sex" checked="checked" value="1"> &nbsp;&nbsp;女<input type="radio" name="sex" value="2"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>手机号码</label></td>
		                <td><input class="easyui-textbox" data-options="required:true" name="mobile" style="width:300px"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>座机号码</label></td>
		                <td><input class="easyui-textbox" name="contactTel" style="width:300px"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>用户图像</label></td>
		                <td><input class="easyui-textbox" name="headerImage" style="width:300px"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>邮箱</label></td>
		                <td><input class="easyui-validatebox" name="email" data-options="required:true,validType:'email'" style="width:300px"/></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>生日</label></td>
		                <td><input class="easyui-datebox" name="birthday" data-options="required:true,showSeconds:true" style="width:300px"> </td>
		              </tr>
		              <tr>
		                <td><label>联系地址</label></td>
		                <td><input class="easyui-textbox" name="address" data-options="" style="width:300px"></td>
		              </tr>
		              <tr>
		                <td><label>备注</label></td>
		                <td><textarea rows="20" cols="" name="remark" style="height: 200px;width:300px" ></textarea></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td colspan="2" align="center">
		                	<button type="button" style="background-color: silver" onclick="validateUserForm()">保存</button>
		                	<button type="button" style="background-color: silver" onclick="closeUserForm()">关闭</button>
		                </td>
		              </tr>
		           </table>   
			   </form>  
	    </div>   
	</div> 
	<!-- 分配角色 -->
	<div id="userRoleWin" class="easyui-window" title="分配角色 " style="width:800px;height:600px;padding: 0px;margin: 0px"   
        data-options="iconCls:'icon-save',modal:true,collapsible:false,inline:true">   
	    <div class="easyui-layout" data-options="fit:true,collapsible:false,border:false" style="padding: 0px;margin: 0px">
	    	<div data-options="region:'west',title:'未选择角色',collapsible:false,border:false" style="width:50%;padding: 0px;margin: 0px" >
	    		<table id="unSelectedRoleList" class="easyui-datagrid" style="width:100%;height:100%;position: relative"   
					data-options="fitColumns:true,singleSelect:true">   
					<thead>   
						 <tr>   
						    <th data-options="field:'id',hidden:true">角色id</th>   
						    <th data-options="field:'name',width:100">角色名称</th>   
						    <th data-options="field:'remark',width:150">角色备注</th>   
						  </tr>   
					</thead>   
				</table>
	    	</div>   
    		<div data-options="region:'center',title:'已选择角色'" style="padding: 0px;margin: 0px">
    			<table id="selectedRoleList" class="easyui-datagrid" style="width:100%;height:100%;position: relative"   
					data-options="fitColumns:true,singleSelect:true">   
					<thead>   
						 <tr>   
						    <th data-options="field:'id',hidden:true">角色id</th>   
						    <th data-options="field:'name',width:100">角色名称</th>   
						    <th data-options="field:'remark',width:150">角色备注</th>   
						 </tr>   
					</thead>   
				</table>
    		</div>      
	    </div> 
	</div>
<div id="pp" class="easyui-pagination" data-options="total:2000,pageSize:10" style="background:#efefef;border:1px solid #ccc;"></div> 		
</body>
</html>