<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索结果</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<style type="text/css">
table{
    margin-top: 20px;
}
tr{
 	margin-top: 20px;
}
</style>
<script type="text/javascript">
//点击书名 显示书籍的详细介绍
function openSearchDetail(value){
	window.open ('<%=request.getContextPath()%>/portal/searchAction_showSearchDetail.do','','');
}
</script>
</head>
<body style="margin-top: 0px;z-index: 9999; padding: 0px;background: url(<%=request.getContextPath()%>/images/common/light.png);">
       <form id="searchForm" action="<%=request.getContextPath()%>/portal/searchAction_search.do" method="post">
               <select id="cc" class="easyui-combobox"  name="fieldName" style="margin-left:0px 0px 0px 0px;height: 36px;width:80px;">   
				    <option>全部</option>   
				    <option>书名</option>   
				    <option>作者</option>   
				    <option>ISBN</option>   
				    <option>eitem5</option>   
				</select>
               <input id="content" name="content" type="text" style="margin-left:0px 0px 0px 0px;height: 30px;width:40%;background-color: white;">
               <button id="search" title="搜索" style="margin-left:0px 0px 0px 0px;height: 37px;width:100px;box-shadow: rgba(0,0,0,0.2)"><font style="font-stretch: extra-expanded;">搜索</font></button>
         </form>    
         <div style="margin-left: 80px;margin-top: 10px">
           <label id="searchCount">读书搜索为您找到相关结果约0个</label>
         </div>
         <div style="margin-top: 10px;margin-left: 80px;position: relative;">
           <s:iterator value="{'1','2','3','4','5','6','7','8','9','10'}" id='value'> 
           <table>
              <tr>
                <td><a href="#" onclick="openSearchDetail()">书籍名称</a></td>
              </tr>
              <tr>
                <td>作者</td>
              </tr>
              <tr>
                <td>ISBN</td>
              </tr>
              <tr>
                <td>简介</td>
              </tr>
           </table>
           </s:iterator>
         </div>
         <div id="pageTool" class="easyui-pagination" data-options="total:2000,pageSize:10" style="background:white;margin-bottom: 20px;margin-top:10px; margin-left: 80px"></div> 
</body>
</html>