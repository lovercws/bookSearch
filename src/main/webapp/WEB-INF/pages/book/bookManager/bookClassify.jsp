<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>书籍分类</title>
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
	$('#bookListTable').datagrid({
		toolbar: [{
			text:'编辑',
			iconCls: 'icon-edit',
			handler: function(){
				
			}
		},'-',{
			text:'删除',
			iconCls: 'icon-remove',
			handler: function(){
				
			}
		}]
	});
});
//通过书籍信息来查询
function queryBook(){
	$('#bookListTable').datagrid('load', {    
		categoryId: 1,
		bookName:1,
		bookAuthor:1,
		bookTitle:1
	});
}
</script>
</head>
<body>
		<div style="float: right;width:100%;height: 4%;position: relative;">
          <form id="queryForm" action="<%=request.getContextPath()%>/book/bookManagerAction_queryBook.do">
            <table>
               <tr>
                  <td><font>书籍名称:</font> <input class="easyui-textbox" name="bookName" data-options="" style="width:100px"></td>
                  <td style="width: 20px"></td>
                  <td>作者:<input class="easyui-textbox" name="bookAuthor" data-options="" style="width:100px"></td>
                  <td style="width: 20px"></td>
                  <td>标题:<input class="easyui-textbox" name="bookTitle" data-options="" style="width:100px"></td>
                  <td style="width: 20px"></td>
                  <td><input type="button" style="background-color: silver;" value="查询" onclick="queryBook()"></td>
               </tr>
            </table>
          </form>
       </div>
       <table id="bookListTable" class="easyui-datagrid" style="width:100%;height:92%"   
		        data-options="url:'<%=request.getContextPath()%>/book/bookManagerAction_queryBookByCategory.do',fitColumns:true,singleSelect:true">   
		    <thead>   
		        <tr>   
		            <th data-options="field:'code',width:100">书名</th>   
		            <th data-options="field:'name',width:100">分类</th>   
		            <th data-options="field:'price',width:100,align:'right'">价格</th>   
		        </tr>   
		    </thead>   
	   </table>
	   <div id="pp" class="easyui-pagination" data-options="total:2000,pageSize:10" style="background:#efefef;border:1px solid #ccc;"></div> 
</body>
</html>