<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录头页面</title>
<style type="text/css">
body{
   margin: 0px;
   padding: 0px;
   width: 100%;
   height: 100%;
   position: absolute;
   background-image: url(<%=request.getContextPath()%>/images/common/light.png); 
   background-repeat: no-repeat; 
   background-position: center top; 
   overflow: hidden;
}

table,tr,td,img{
	padding:0px;
	margin:0px;
}
a{
    text-decoration: none;
}
</style>
<script type="text/javascript">
function logout(){
	parent.window.location.href="<%=request.getContextPath()%>/portal/loginAction_logout.do";
}

function home(){
	parent.window.location.href="<%=request.getContextPath()%>/index.jsp";
}

function startTime()     {     
    var today=new Date();//定义日期对象     
    var yyyy = today.getFullYear();//通过日期对象的getFullYear()方法返回年      
    var MM = today.getMonth()+1;//通过日期对象的getMonth()方法返回年      
    var dd = today.getDate();//通过日期对象的getDate()方法返回年       
    var hh=today.getHours();//通过日期对象的getHours方法返回小时     
    var mm=today.getMinutes();//通过日期对象的getMinutes方法返回分钟     
    var ss=today.getSeconds();//通过日期对象的getSeconds方法返回秒     
    // 如果分钟或小时的值小于10，则在其值前加0，比如如果时间是下午3点20分9秒的话，则显示15：20：09     
    MM=checkTime(MM);  
    dd=checkTime(dd);  
    mm=checkTime(mm);     
    ss=checkTime(ss);      
    var day; //用于保存星期（getDay()方法得到星期编号）  
    if(today.getDay()==0)   day   =   "星期日 "   
    if(today.getDay()==1)   day   =   "星期一 "   
    if(today.getDay()==2)   day   =   "星期二 "   
    if(today.getDay()==3)   day   =   "星期三 "   
    if(today.getDay()==4)   day   =   "星期四 "   
    if(today.getDay()==5)   day   =   "星期五 "   
    if(today.getDay()==6)   day   =   "星期六 "   
    document.getElementById('nowDateTimeSpan').innerHTML=yyyy+"-"+MM +"-"+ dd +" " + hh+":"+mm+":"+ss+"   " + day;     
    setTimeout('startTime()',1000);//每一秒中重新加载startTime()方法   
}     
  
function checkTime(i){     
    if (i<10){  
        i="0" + i;  
    }     
    return i;  
}
</script>
<script type="text/javascript">
</script>
</head>
<body onload="startTime()">
<table style="margin-top: 5px;margin-left: -2px" align="right">
 <tr>
   <td width="120px" style="vertical-align: middle;">欢迎 <font color="#33FFFF">${user.name}</font></td>
   <td width="300px" style="vertical-align: middle;">当前时间: <font color="#33FFFF"><span id="nowDateTimeSpan"></span></font> </td>
   <td width="80px"><a href="#" onclick="home()">返回首页</a></td>
   <td width="80px"><a href="#" onclick="logout()">注销</a></td>
 </tr>
</table>
</body>
</html>