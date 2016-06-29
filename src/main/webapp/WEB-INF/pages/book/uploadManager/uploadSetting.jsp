<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>书籍上传设置(支持上传的文件类型、上传大小、上传文件保存位置)</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/themes/icon.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
</head>
<style type="text/css">
body{
    margin:0px;
    padding:0px;
}
</style>
<body>
<div id="tt" class="easyui-tabs" data-options="closable:false,fit:true,border:false,narrow:true" style="width:100%;height:100%;position: relative">   
    <div title="基本设置" data-options="closable:false"> 
       <form id="ff" method="post">   
       		<table align="center" style="margin-top: 10%" border="false">
		      <tr>  
		        <td><label>上传文件大小:<font color="red"> 按M计算</font></label></td> 
		      </tr><tr>
		        <td><input type="text" class="easyui-numberbox" value="100" data-options="min:0,precision:0,max:100" style="width:300px"></input></td>  
		      </tr>   
		      <tr>    
		        <td><label>上传文件路径:</label></td>
		      </tr><tr>
		        <td><input class="easyui-textbox" name="uploadPath" data-options="iconCls:'icon-search'" style="width:300px"></td>
		      </tr> 
		      <tr>
		        <td align="center" colspan="2"><input type="button" value="保存"></td>
		      </tr>  
       		</table>
	   	</form>
    </div>   
    <div title="上传文件类型设置" data-options="closable:false" style="width:100%;height:100%;position: relative;">  
       <div style="float: left;width: 48%;height: 100%;">
          <ul id="noSelectedTypeUl" class="easyui-datalist" data-options="checkbox:false,lines:true,singleSelect:false" title="未选择文件类型" style="width:100%;height:100%"> 
		    <li value="AL">Alabama</li> 
		    <li value="AK">Alaska</li> 
		    <li value="AZ">Arizona</li> 
		    <li value="AR">Arkansas</li> 
		    <li value="CA">California</li> 
		    <li value="CO">Colorado</li>   
		  </ul>  
       </div>
       <div style="float: left;width: 4%;height: 80%;margin-top: 10%">
	       <ul style="margin: 0px;padding: 0px;list-style: none;width:100%">
	         <li><button style="height: 30px;width:100%;margin:20px 0px 0px 0px">->></button></li>
	         <li><button style="height: 30px;width:100%;margin:20px 0px 0px 0px">-></button></li>
	         <li><button style="height: 30px;width:100%;margin:20px 0px 0px 0px"><<-</button></li>
	         <li><button style="height: 30px;width:100%;margin:20px 0px 0px 0px"><-</button></li>
	       </ul>
       </div>
       <div style="float: left;width: 48%;height: 100%;">
          <ul id="selectedTypeUl" class="easyui-datalist" data-options="checkbox:false,url:'',method:'get',singleSelect:false,lines: true " title="已选择文件类型" style="width:100%;height:100%"> 
		    <li value="AL">Alabama</li> 
		    <li value="AK">Alaska</li> 
		    <li value="AZ">Arizona</li> 
		    <li value="AR">Arkansas</li> 
		    <li value="CA">California</li> 
		    <li value="CO">Colorado</li>   
		  </ul>  
       </div>
    </div>   
</div>  
</body>
</html>