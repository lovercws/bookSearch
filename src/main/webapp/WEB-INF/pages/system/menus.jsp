<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accordion+tree</title>
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
#bodyStyle{
   background-image: url(<%=request.getContextPath()%>/images/common/light.png); 
   background-repeat: no-repeat; 
   background-position: center top; 
   overflow: hidden;
}
</style>
<script type="text/javascript">
var menuData='${requestScope.menuData}';
menuData=eval("("+menuData+")");

$(document).ready(function(){
	var menuBar=$("#menuBar");
	var selected=true;
	for(var i=0,len=menuData.length;i<len;i++){
		if(i>0){
			selected=false;
		}
		var managerBar=menuData[i];
		var managerPanel=menuBar.accordion('add', {
			title: managerBar.text,
			selected: selected,
			iconCls:'icon-save',
			bodyCls:'bodyStyle',
			content:"<ul id="+managerBar.id+" class='easyui-tree' data-options=''></ul>"
		});
		$("#"+managerBar.id).tree({
			data:managerBar.children,
			onClick:function(node){
				treeClick(node.src);
			}
		});
	}
	
	//树点击事件
	function treeClick(src){
		if(src){
    		window.open('<%=request.getContextPath()%>/'+src,'menuFunction');
    	}
	}
});
</script>
</head>
<body>
<div id="menuBar" class="easyui-accordion" style="margin:0px;padding:0px;width:100%;height:100%;position: relative;">   
</div>  
</body>
</html>