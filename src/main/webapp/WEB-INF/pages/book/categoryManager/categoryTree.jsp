<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类树</title>
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
	$('#categoryTree').datagrid({
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
				
			}
		},'-',{
			text:'更新',
			iconCls: 'icon-edit',
			handler: function(){
				
			}
		}],
		onDblClickCell: function(index,field,value){
		}
	});
	
	//插入节点
	function insertNode(){
		//获取选中的节点
		var node = $('#categoryTree').treegrid('getSelected');
		//如果存在选中节点,就添加子节点
        if(node){
        	$('#categoryTree').treegrid('append',{
        		parent: node.id,
    			data: [{
    				id: '073'+new Date().getTime(),
    				name: '新节点'
    			}]
    		});
        }else{//不存在 则添加到根节点
        	$('#categoryTree').treegrid('append',{
    			data: [{
    				id: '073'+new Date().getTime(),
    				name: '新节点'
    			}]
    		});
        }
		console.log(JSON.stringify($('#categoryTree').treegrid('getData')));
	}
});
</script>
</head>
<body>
<table id="categoryTree" class="easyui-treegrid" style="width:100%;height:100%;position: relative;"   
        data-options="idField:'id',treeField:'name',animate:true,url:'<%=request.getContextPath()%>/book/categoryManagerAction_getCategoryCombotree.do'">   
    <thead>   
        <tr>   
            <th data-options="field:'name'" style="width:20%">Task Name</th>   
            <th data-options="field:'persons'" style="width:20%">Persons</th>   
            <th data-options="field:'begin'" style="width:20%">Begin Date</th>   
            <th data-options="field:'end'" style="width:20%">End Date</th>   
            <th data-options="field:'end2'" style="width:20%">End Date</th>   
        </tr>   
    </thead>   
</table> 
</body>
</html>