<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ztree.excheck.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
//树的配置信息
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	},
	view: {
		showLine: true,
		selectedMulti: false
	},
	check:{
		enable : true,
		chkStyle: "checkbox"
	},
	
};

$(document).ready(function(){
	$.ajax({
		url: '<%=request.getContextPath()%>/system/roleMenuAction_getZTree.do?roleId='+7,
		cache: false,
		type:'POST',
		success: function(json){
			console.log(json);
			$.fn.zTree.init($("#menuTree"), setting, json);
		}
	});
	
});

function allocateAuth(){
	var treeObj = $.fn.zTree.getZTreeObj("menuTree");
	var nodes = treeObj.getCheckedNodes(true);
	
	var menuIds=null;
	var roleId='${param.roleId}';
	console.log('roleId-->'+roleId);
	for(var i=0,len=nodes.length;i<len;i++){
		if(menuIds==null){
			menuIds=nodes[i].id;
		}else{
			menuIds=menuIds+","+nodes[i].id;
		}
	}
	//发送ajax请求 保存权限
	$.ajax({
		url: "<%=request.getContextPath()%>/system/roleMenuAction_allocateAuth.do",
		cache: false,
		type:'POST',
		data: 'roleId='+roleId+'&menuIds='+menuIds,
		success: function(html){
			$.messager.alert('提示','权限分配成功');    
			window.close();
		}
	});
}
</script>
</head>
<body>
	<div style="height: 660px;position: relative;">
	   <ul id="menuTree" class="ztree"></ul>
	   <input style="margin-left: 45%" type="button" value="保存" onclick="allocateAuth()">
	</div>
</body>
</html>