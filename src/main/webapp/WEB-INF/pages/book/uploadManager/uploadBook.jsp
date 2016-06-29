<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加书籍</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<style type="text/css">
body{
  margin: 0px;
  padding: 0px;
}
</style>
<script type="text/javascript">
//验证、提交表单
function validateForm(){
	$('#bookFileuploadForm').form('submit', {    
	    url:'<%=request.getContextPath()%>/book/uploadAction_upload.do',    
	    onSubmit: function(param){   
	    	var content=$("input[name='file']").val();
	    	if(content==null||content==''){
	    		$.messager.alert('警告','上传内容不能为空');
	    		return false;
	    	}
	    },    
	    success:function(msg){
	    	var data=eval("("+msg+")");
	    	if(data.success){
	    		$.messager.alert('提示','上传成功');
	    	}else{
	    		$.messager.alert('提示',data.msg);
	    	}
	    }    
	});  
}	
</script>
</head>
<body>
<h1 align="center"><font color="red">书籍上传</font></h1>
<form id="bookFileuploadForm" method="post" enctype="multipart/form-data">   
  <table align="center" style="height: 300px">
    <tr>
      <td><label>书籍名称:</label>  </td>
      <td><input class="easyui-textbox" name="bookName" data-options="iconCls:'icon-search'" style="width:300px"></td>
    </tr>
    <tr>
      <td><label>书籍作者:</label></td>
      <td><input class="easyui-textbox" name="bookAuthor" data-options="iconCls:'icon-man'" style="width:300px"></td>
    </tr>
    <tr>
      <td><label>书籍分类:</label></td>
      <td><input class="easyui-combotree" name="bookCategory" style="width:300px" data-options="url:'<%=request.getContextPath()%>/book/categoryManagerAction_getCategoryCombotree.do',required:false" /></td>
    </tr>
    <tr>
      <td align="center"><label>书籍内容:</label></td>
      <td><input id="file" class="easyui-filebox" name="file" style="width:300px" data-options="buttonIcon:'',buttonText: '选择文件', "></td>
    </tr>
    <tr>
      <td colspan="2" align="center"><input type="button" onclick="validateForm()" style="background-color: silver;" value="上传"> </td>
    </tr>
  </table>
</form>  

</body>
</html>