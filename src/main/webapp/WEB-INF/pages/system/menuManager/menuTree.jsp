<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单树</title>
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
	var lastIndex; 
	$('#menuTree').treegrid({
		toolbar: [{
			text:'添加',
			iconCls: 'icon-add',
			handler: function(){
				insertNode();
			}
		},'-',{
			text:'删除',
			iconCls: 'icon-remove',
			handler: function(){
				var selections=$('#menuTree').treegrid("getSelections");
				if(selections&&selections.length>0){
					$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
					    if (r){    
					    	var menuId=selections[0].id;
							window.location.href="<%=request.getContextPath()%>/system/menuManagerAction_deleteTreeMenu.do?id="+menuId;
					    }    
					}); 
				}else{
					$.messager.alert('提示','请选择一条记录');    
				}
			}
		},'-',{
			text:'更新',
			iconCls: 'icon-edit',
			handler: function(){
				reload();
			}
		}],
		onClickRow:function(row){//当单击其他行 取消编辑
			 if (editingId != undefined&&row.id!=editingId){
		        	//取消编辑
		            $('#menuTree').treegrid('endEdit', editingId);
		            editingId = undefined;
			 }
		},  
		onDblClickRow: function(row){
			edit(); 
		},
		onAfterEdit:function(row,changes){
			for(var fieldName in row){
				if(changes.hasOwnProperty(fieldName)){
					row[fieldName]=changes[fieldName];
				}
			}
			save(row);
		}
	});
	
	//双击编辑行
	var editingId;//编辑的行id
	function edit(){
        if (editingId != undefined){
        	$('#menuTree').treegrid('endEdit', editingId);
        }
        var row = $('#menuTree').treegrid('getSelected');
        if (row){
            editingId = row.id
            $('#menuTree').treegrid('beginEdit', editingId);
        }
    }
	
	//保存行数据
    function save(row){
    	//ajax保存
        $.ajax({
			  url: "<%=request.getContextPath()%>/system/menuManagerAction_updateMenu.do",
			  cache: false,
			  type:'POST',
			  data: row,
			  success: function(html){
				  //更新树表格数据
		          reload();
			  }
		});
    }
    
	//插入节点
	function insertNode(){
		//获取选中的节点
		var node = $('#menuTree').treegrid('getSelected');
		//如果存在选中节点,就添加子节点
        if(node){
        	$('#menuTree').treegrid('append',{
        		parent: node.id,
    			data: [{
    				id: '073'+new Date().getTime(),
    				name: '新节点'
    			}]
    		});
        }else{//不存在 则添加到根节点
        	$('#menuTree').treegrid('append',{
    			data: [{
    				id: '073'+new Date().getTime(),
    				name: '新节点'
    			}]
    		});
        }
	}
	
	function reload(){
		$('#menuTree').treegrid("load");
	}
});
</script>
</head>
<body>
<table id="menuTree" class="easyui-treegrid" style="width:100%;height:100%;position: relative;"   
        data-options="title:'菜单树',idField:'id',treeField:'title',animate:true,rownumbers: true,method:'POST',url:'<%=request.getContextPath()%>/system/menuManagerAction_getMenuTree.do'">   
    <thead>   
        <tr>
			<th data-options="field:'id',hidden:true" style="width: 0%">菜单主键</th>
			<th data-options="field:'parentId',hidden:true" style="width: 0%">父菜单主键</th>
			<th data-options="field:'title',editor:'text'" style="width: 13%">菜单标题</th>
			<th data-options="field:'parentTitle'" style="width: 10%">父菜单标题</th>
			<th data-options="field:'code',editor:'text'" style="width: 12%">菜单代码</th>
			<th data-options="field:'parentCode'" style="width: 10%">父菜单代码</th>
			<th data-options="field:'src',editor:'text'" style="width: 20%">链接</th>
			<th data-options="field:'order',editor:'text'" style="width: 5%">菜单排序</th>
			<th data-options="field:'icon',editor:'text'" style="width: 10%">菜单图标</th>
			<th data-options="field:'state',editor:'text'" style="width: 4%">菜单状态</th>
			<th data-options="field:'autoShow',editor:'text'" style="width: 4%">自动显示</th>
			<th data-options="field:'createDate'" style="width: 10%">创建时间</th>
	    </tr> 		
    </thead> 
</table> 
</body>
</html>