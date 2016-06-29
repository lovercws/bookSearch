<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页头部分（显示天气状况、用户登陆状况、）</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<style type="text/css">
body {
	height: 100%;
	width: 100%;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	position:absolute;
}
a{
    text-decoration: none;
    cursor:pointer;
}
</style>
<script type="text/javascript">
function changeLocation(url){
	top.window.location.href=url;
}
</script>
</head>
<body>
<div style="margin-left: 80%;margin-top: 8px">
	<s:if test="#session.user!=null">
		<a style="margin-left: 30px" onclick='changeLocation("<%=request.getContextPath()%>/system/userManagerAction_toIndex.do")'>欢迎 ${user.name}</a>
		<a style="margin-left: 30px" onclick='changeLocation("<%=request.getContextPath()%>/portal/loginAction_logout.do")'>注销</a>
		<a style="margin-left: 30px" onclick='changeLocation("<%=request.getContextPath()%>/index.jsp")'>返回首页</a>
	</s:if>
	<s:else>
		<a href="" >热度排行榜</a>
		<a style="margin-left: 30px" onclick='changeLocation("<%=request.getContextPath()%>/portal/registryAction_goReg.do")'>注册</a>
		<a style="margin-left: 30px" onclick='changeLocation("<%=request.getContextPath()%>/portal/loginAction_goLogin.do")'>登陆</a>
	</s:else>
</div>
</body>
</html>