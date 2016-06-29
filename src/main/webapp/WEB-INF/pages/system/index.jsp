<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户功能界面</title>
</head>
<frameset rows="38px,*" frameborder="no" border="0px">
   <frame title="顶部" src="<%=request.getContextPath()%>/portal/layoutManagerAction_header.do"/>
   <frameset cols="200px,*" frameborder="no" border="0px">
      <frame title="功能按钮" src="<%=request.getContextPath()%>/portal/layoutManagerAction_getAllMenu.do"/>
      <frame title="功能" name="menuFunction">
   </frameset>
</frameset>
</html>