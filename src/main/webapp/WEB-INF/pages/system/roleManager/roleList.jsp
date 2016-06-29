<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色列表</title>
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
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	//角色列表
	$('#roleList').datagrid({
		toolbar: [{
			text:'添加',
			iconCls: 'icon-add',
			handler: function(){
				$("#detailRoleWin").window("open");
			}
		},'-',{
			text:'编辑',
			iconCls: 'icon-edit',
			handler: function(){
				editRoleForm();
			}
		},'-',{
			text:'删除',
			iconCls: 'icon-remove',
			handler: function(){
				var selections=$("#roleList").datagrid('getSelections');
				if(selections&&selections.length>0){
					$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
					    if (r){    
					    	var roleId=selections[0].id;
							window.location.href="<%=request.getContextPath()%>/system/roleManagerAction_deleteRole.do?id="+roleId;
					    }    
					}); 
				}else{
					$.messager.alert('提示','请选择一条记录');    
				}
			}
		},'-',{
			text:'分配用户',
			iconCls: 'icon-edit',
			handler: function(){
				//$("#allowUserWin").window("open");
			}
		},'-',{
			text:'分配权限',
			iconCls: 'icon-edit',
			handler: function(){
				var selections=$("#roleList").datagrid('getSelections');
				if(selections&&selections.length>0){
					var roleId=selections[0].id;
					/* loadMenuTree();
					$("#allowMenuWin").window("open"); */
					
               		var iWidth=500; //弹出窗口的宽度;
					var iHeight=800; //弹出窗口的高度;
					var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
					var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
					window.open('<%=request.getContextPath()%>/system/roleMenuAction_getZTree.do?roleId='+roleId,'newwindow','height='+iHeight+',width='+iWidth+',left='+iLeft+',top='+iTop+'toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no') 
				}else{
					$.messager.alert('提示','请选择一条记录');
				}
			}
		}],
		onDblClickRow:function(index,row){
			editRoleForm();
		}
	});
	
	//编辑菜单表单
	function editRoleForm(){
		var selections=$("#roleList").datagrid('getSelections');
		if(selections&&selections.length>0){
			console.log(selections[0]);
			$('#detailRoleForm').form('load',selections[0]);
			$('#detailRoleWin').window('open');
		}else{
			$.messager.alert('提示','请选择一条记录');  
		}
	}
	//加载菜单树
	function loadMenuTree(){
		var selections=$("#roleList").datagrid('getSelections');
		var roleId=selections[0].id;
		
		//菜单树
		$('#allowMenuTree').tree({    
			url:'<%=request.getContextPath()%>/system/roleMenuAction_getAuthSelectedTree.do?roleId='+roleId,
			method:'POST',
			formatter:function(node){
				if(node.checked){
					console.log(node);
				}
				//node.checked=false;
				return node.text;
			}
		});
	}
	
	//默认关闭 弹框
	$("#detailRoleWin").window("close");
	$("#allowMenuWin").window("close");
	
});

//验证添加角色表单
function validateRoleForm(){
	var validated=$('#importMenuForm').form('validate');
	if(validated){
		$('#detailRoleForm').submit();
	}
}

//关闭角色详情window
function closeRoleForm(){
	$('#detailRoleForm').form('clear');
	$('#detailRoleWin').window('close');
}

//对角色分配权限
function allocateAuth(){
	//获取选中的节点
	var menuIds=null;
	var nodes = $('#allowMenuTree').tree('getChecked',['checked','indeterminate']);
	for(var i=0,len=nodes.length;i<len;i++){
		if(menuIds==null){
			menuIds=nodes[i].id;
		}else{
			menuIds=menuIds+","+nodes[i].id;
		}
	}
	
	//获取选择的角色
	var selections=$("#roleList").datagrid('getSelections');
	var roleId=selections[0].id;
	
	//发送ajax请求 保存权限
	$.ajax({
		url: "<%=request.getContextPath()%>/system/roleMenuAction_allocateAuth.do",
		cache: false,
		type:'POST',
		data: 'roleId='+roleId+'&menuIds='+menuIds,
		success: function(html){
			$.messager.alert('提示','权限分配成功!');  
			$("#allowMenuWin").window("close");
		}
	});
	
}
</script>
</head>
<body>
	<table id="roleList" class="easyui-datagrid" style="width:100%;height:96%;position: relative"   
		data-options="title:'角色列表',fitColumns:true,singleSelect:true,rownumbers:true">   
		<thead>   
			 <tr>   
			    <th data-options="field:'id',width:30,hidden:true">角色id</th>   
			    <th data-options="field:'code',width:30">角色代码</th>   
			    <th data-options="field:'name',width:30">角色名称</th>   
			    <th data-options="field:'issystem',width:30">系统内置</th>   
			    <th data-options="field:'createDate',width:30">创建日期</th>   
			    <th data-options="field:'remark',width:100">角色备注</th>   
			  </tr>   
		</thead>   
		<tbody>
		    <s:iterator value="#request.roleList" var="role">
		      <tr>
				<td><s:property value="#role.id"/></td>
				<td><s:property value="#role.code"/></td>
				<td><s:property value="#role.name"/></td>
				<td><s:property value="#role.issystem"/></td>
				<td><s:date name="#role.createDate" format="yyyy-MM-dd HH:mm:ss"/>  </td>
				<td><s:property value="#role.remark"/></td>
		      </tr>
		    </s:iterator>
		</tbody>
	</table>
	<!-- 添加角色 -->
	<div id="detailRoleWin" class="easyui-window" title="添加角色" style="width:600px;height:400px"   
        data-options="iconCls:'icon-save',modal:true,collapsible:false,inline:true">   
	    <div class="easyui-layout" data-options="fit:true">   
	           <form id="detailRoleForm" method="post" style="height: 400px" action="<%=request.getContextPath()%>/system/roleManagerAction_saveRole.do">
		           <input type="hidden" name="id">
		           <table align="center" style="margin-top: 20px">
		              <tr>
		                <td><label>角色代码</label></td>
		                <td><input class="easyui-textbox" name="code" data-options="required:true" style="width:400px"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>角色名称</label></td>
		                <td><input class="easyui-textbox" name="name" data-options="required:true" style="width:400px"></td>
		              </tr>
		              <tr>
		                <td><label>系统内置</label></td>
		                <td>是<input type="radio" name="issystem" value="true" checked="checked"> 否<input type="radio"  value="false" name="issystem"> </td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>角色备注</label></td>
		                <td><textarea name="remark" rows="20" cols="" style="width: 400px;height: 200px"></textarea>  </td>
		              </tr>
		              <tr>
		                <td colspan="2" align="center">
		                	<button type="button" style="background-color: silver" onclick="validateRoleForm()">保存</button>
		                	<button type="button" style="background-color: silver" onclick="closeRoleForm()">关闭</button>
		                </td>
		              </tr>
		           </table>   
			   </form>  
	    </div>   
	</div>  
	<!-- 分配权限 -->
	<div id="allowMenuWin" class="easyui-window" title="分配权限 " style="width:500px;height:740px"   
        data-options="iconCls:'icon-save',modal:true,collapsible:false,inline:true">
        <div>
        	<ul id="allowMenuTree" class="easyui-tree" data-options="lines:true,checkbox:true,animate:true"></ul>
        </div>
        
        <div style="height: 20px;margin-left: 230px">
        	<input type="button" value="保存" onclick="allocateAuth()">
        </div>
	</div>  
<div id="pp" class="easyui-pagination" data-options="total:2000,pageSize:10" style="background:#efefef;border:1px solid #ccc;"></div> 		
</body>
</html>