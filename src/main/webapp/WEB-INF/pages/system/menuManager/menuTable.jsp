<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单列表</title>
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
	$('#menuTable').datagrid({
		toolbar: [{
			text:'添加',
			iconCls: 'icon-add',
			handler: function(){
				$('#detailMenuWin').window('open');
			}
		},'-',{
			text:'删除',
			iconCls: 'icon-remove',
			handler: function(){
				var selections=$("#menuTable").datagrid('getSelections');
				if(selections&&selections.length>0){
					$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
					    if (r){    
					    	var menuId=selections[0].id;
							window.location.href="<%=request.getContextPath()%>/system/menuManagerAction_deleteMenu.do?id="+menuId;
					    }    
					}); 
				}else{
					$.messager.alert('提示','请选择一条记录');    
				}
			}
		},'-',{
			text:'删除所有',
			iconCls: 'icon-remove',
			handler: function(){
				$.messager.confirm('确认','您确认想要删除所有的记录吗？',function(r){    
				    if (r){    
						window.location.href="<%=request.getContextPath()%>/system/menuManagerAction_deleteAllMenu.do";
				    }    
				});
			}
		},'-',{
			text:'编辑',
			iconCls: 'icon-edit',
			handler: function(){
				editMenuForm();
			}
		},'-',{
			text:'更新',
			iconCls: 'icon-edit',
			handler: function(){
				window.location.reload();
			}
		},'-',{
			text:'导入EXCEL',
			iconCls: 'icon-edit',
			handler: function(){
				$('#importMenuWin').window('open');
			}
		},'-',{
			text:'导出EXCEL',
			iconCls: 'icon-edit',
			handler: function(){
				window.location.href="<%=request.getContextPath()%>/system/menuManagerAction_exportMenu.do";
			}
		}],
		onDblClickCell: function(index,field,value){
			editMenuForm();
		}
	});
	//编辑菜单表单
	function editMenuForm(){
		var selections=$("#menuTable").datagrid('getSelections');
		if(selections&&selections.length>0){
			console.log(selections[0]);
			$('#detailMenuForm').form('load',selections[0]);
			$('#detailMenuWin').window('open');
		}else{
			$.messager.alert('提示','请选择一条记录');  
		}
	}
	
	//默认关闭 添加菜单window、导入菜单window
	$('#detailMenuWin').window('close');
	$('#importMenuWin').window('close');
	
	//菜单 下拉树
	var data='${requestScope.menuComboTree}';
	data=eval("("+data+")");
	$("#menuComboTree").combotree({
		data:data
	});
});

//验证菜单 表单
function validateMenuForm(){
	var validated=$('#detailMenuForm').form('validate');
	if(validated){
		$('#detailMenuForm').submit();
	}
}

//验证菜单 导入表单
function validateImportForm(){
	var validated=$('#importMenuForm').form('validate');
	if(validated){
		$('#importMenuForm').submit();
	}
}

//关闭分类详情弹框
function closeMenuForm(){
	$('#detailMenuForm').form('clear');
	$('#detailMenuWin').window('close');
}
</script>
</head>
<body>
	<table id="menuTable" class="easyui-datagrid" data-options="title:'菜单列表',plain:true,fitColumns:true,rownumbers:true,singleSelect:true,halign:'center',align:'center'" style="width: 100%;height: 96%;position: relative;">
		<thead>
			<tr>
				<th data-options="field:'id',hidden:true" style="width: 0%">菜单主键</th>
				<th data-options="field:'parentId',hidden:true" style="width: 0%">父菜单主键</th>
				<th data-options="field:'code'" style="width: 10%">菜单代码</th>
				<th data-options="field:'title'" style="width: 10%">菜单标题</th>
				<th data-options="field:'parentCode'" style="width: 10%">父菜单代码</th>
				<th data-options="field:'parentTitle'" style="width: 10%">父菜单标题</th>
				<th data-options="field:'src'" style="width: 20%">链接</th>
				<th data-options="field:'order'" style="width: 5%">菜单排序</th>
				<th data-options="field:'icon'" style="width: 10%">菜单图标</th>
				<th data-options="field:'state'" style="width: 5%">菜单状态</th>
				<th data-options="field:'autoShow'" style="width: 9%">自动显示</th>
				<th data-options="field:'createDate'" style="width: 10%">创建时间</th>
			</tr>
		</thead>
		<tbody>
		    <s:iterator value="#request.menuList" var="menu">
		      <tr>
				<td><s:property value="#menu.id"/></td>
				<td><s:property value="#menu.parentId"/></td>
				<td><s:property value="#menu.code"/></td>
				<td><s:property value="#menu.title"/></td>
				<td><s:property value="#menu.parentCode"/></td>
				<td><s:property value="#menu.parentTitle"/></td>
				<td><s:property value="#menu.src"/></td>
				<td><s:property value="#menu.order"/></td>
				<td><s:property value="#menu.icon"/></td>
				<td><s:property value="#menu.state"/></td>
				<td><s:property value="#menu.autoShow"/></td>
				<td><s:date name="#menu.createDate" format="yyyy-MM-dd HH:mm:ss"/>  </td>
		      </tr>
		    </s:iterator>
		</tbody>
	</table>
	
	<!-- 添加菜单 -->
	<div id="detailMenuWin" class="easyui-window" title="添加菜单 " style="width:600px;height:400px"   
        data-options="iconCls:'icon-save',modal:true,collapsible:false,inline:true">   
	    <div class="easyui-layout" data-options="fit:true">   
	           <form id="detailMenuForm" method="post" style="height: 400px" action="<%=request.getContextPath()%>/system/menuManagerAction_saveMenu.do">
		           <input type="hidden" name="id">
		           <table align="center" style="margin-top: 20px">
		              <tr>
		                <td><label>菜单代码</label></td>
		                <td><input class="easyui-textbox" name="code" data-options="required:true" style="width:300px"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>菜单标题</label></td>
		                <td><input class="easyui-textbox" name="title" data-options="required:true" style="width:300px"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>父菜单标题标题</label></td>
		                <td><select id="menuComboTree" name="parentId"  class="easyui-combotree" style="width:300px;" data-options="valueField:'id',textField: 'title'"></select> </td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>菜单链接</label></td>
		                <td><input class="easyui-textbox" name="src" style="width:300px"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>菜单排序</label></td>
		                <td><input class="easyui-numberspinner" name="order" value="1" data-options="min:1,max:1000,editable:true" style="width:300px"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>菜单图标</label></td>
		                <td><input class="easyui-textbox" name="icon" style="width:300px"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>菜单状态</label></td>
		                <td><input class="easyui-combobox" name="state" data-options="valueField: 'label',textField: 'value',data: [{label: 'open',value: 'open'},{label: 'closed',value: 'closed'}],required:true"  style="width:300px"/></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td><label>自动显示</label></td>
		                <td><input class="easyui-switchbutton" name="autoShow" data-options="onText:'true',offText:'false',value:'true'"></td>
		              </tr>
		              <tr></tr>
		              <tr>
		                <td colspan="2" align="center">
		                	<button type="button" style="background-color: silver" onclick="validateMenuForm()">保存</button>
		                	<button type="button" style="background-color: silver" onclick="closeMenuForm()">关闭</button>
		                </td>
		              </tr>
		           </table>   
			   </form>  
	    </div>   
	</div>  
	
	<!-- 导入菜单 -->
	<div id="importMenuWin" class="easyui-window" title="导入菜单 " style="width:400px;height:100px"   
        data-options="iconCls:'icon-save',modal:true,collapsible:false,inline:true">
        <div class="easyui-layout" data-options="fit:true">   
	           <form id="importMenuForm" enctype="multipart/form-data" method="post" style="height: 200px" action="<%=request.getContextPath()%>/system/menuManagerAction_importMenu.do">
	              <table align="center" style="margin-top: 5px;">
	              <tr>
	              	<td><input class="easyui-filebox" data-options="required:true,buttonText:'选择EXCEL'" name="file" style="width:300px"></td>
	              </tr>
	              <tr>
	              	<td align="center"><input type="button" onclick="validateImportForm()" style="background-color: silver;" value="导入"></td>
	              </tr>
	              </table>
	           </form>
	    </div>
    </div>
        
	<div id="pp" class="easyui-pagination" data-options="total:2000,pageSize:10" style="background:#efefef;border:1px solid #ccc;"></div> 
</body>
</html>