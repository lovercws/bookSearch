<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色权限</title>
</head>
<script type="text/javascript">
function closeMethod(){
	opener.location.reload();
    window.close();
}

var topWin=window.top;
if(topWin!=window){
	topWin.location.href=window.location.href;
}
</script>
<body>
没有权限操作
</body>
</html>