<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 临时用户搜索首页 -->
<title>书城搜索首页</title>
</head>
<frameset rows="30px,*,25px" border="0px" frameborder="yes">
   <frame title="搜索头" src="<%=request.getContextPath()%>/portal/searchAction_header.do"/>
   <frame title="搜索体" src="<%=request.getContextPath()%>/portal/searchAction_main.do"/>
   <frame title="搜索尾" src="<%=request.getContextPath()%>/portal/searchAction_bottom.do"/>
</frameset>
</html>