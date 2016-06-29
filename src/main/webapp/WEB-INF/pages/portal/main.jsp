<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 临时用户搜索首页 -->
<title>书城搜索首页</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#search").click(function(){
		var content=$("#content").val();
		console.log(content.length);
		if(content==null||content==""||content.length==0){
			window.location.reload();
		}else{
			//搜索
			$("#searchForm").submit();
		}
	});
});
</script>
</head>
<body class="easyui-layout" style="margin:0px;padding: 0px;background: url(<%=request.getContextPath()%>/images/common/light.png);"> 
<div>
   <div data-options="region:'center'" style="height: 80%;width: 100%">  
       <div style="margin-left: 30%;margin-top: 2%;height: 10%">
            <img alt="" src="">
       </div>
       <div style="margin-left: 30%;margin-top:1%">
           <form id="searchForm" action="<%=request.getContextPath()%>/portal/searchAction_search.do" method="post">
               <input id="content" type="text" style="margin-left:0px 0px 0px 0px;height: 30px;width:40%;background-color: white;">
               <button type="button" id="search" title="搜索" style="margin-left:0px 0px 0px 0px;height: 37px;width:100px;box-shadow: rgba(0,0,0,0.2)"><font style="font-stretch: extra-expanded;">搜索</font></button>
           </form>
       </div>
   </div>   
</div>  
</body> 
</html>