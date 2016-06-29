<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户统计</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/echarts-all.js"></script>
<style type="text/css">
body{
   margin: 0px;
   padding: 0px;
   width: 100%;
   height: 100%;
   position: absolute;
   background: url(<%=request.getContextPath()%>/images/common/light.png);
}
a{
   text-decoration: none;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	var bodyWidth=document.body.clientWidth;
	var bodyHeight=document.body.clientHeight;
	
	//注册统计图
	var registryChart = echarts.init(document.getElementById('registryMain')); 
	var registryOption = {
		    title : {
		        text: '近一周用户注册量'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['一周注册量']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : ['周一','周二','周三','周四','周五','周六','周日']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLabel : {
		                formatter: '{value}'
		            }
		        }
		    ],
		    series : [
		        {
		            name:'一周注册量',
		            type:'line',
		            data:[1, 2, 3, 4, 5, 6, 10],
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            }
		        }
		    ]
	};
	registryChart.setOption(registryOption); 
	
	//访问量统计图
	document.getElementById("accessMain").style.width=bodyWidth+"px";
	document.getElementById("accessMain").style.height=bodyHeight+"px";
	
	var accessChart = echarts.init(document.getElementById('accessMain')); 
	var accessOption = {
		    title : {
		        text: '近一周用户访问量'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['一周访问量']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : ['周一','周二','周三','周四','周五','周六','周日']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLabel : {
		                formatter: '{value}'
		            }
		        }
		    ],
		    series : [
		        {
		            name:'一周访问量',
		            type:'line',
		            data:[1, 2, 3, 4, 5, 6, 10],
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            }
		        }
		    ]
	};
	accessChart.setOption(accessOption); 
});
</script>
</head>
<body>
<div id="tt" class="easyui-tabs" data-options="narrow:true,fit:true" style="width:100%;height:100%;position: relative;">   
    <div title="用户注册量" data-options="closable:false,fit:true"> 
       <div id="registryMain" style="height:100%;width: 100%;position: absolute;"></div>
    </div>   
    <div title="用户访问量" data-options="closable:false,fit:true">   
       <div id="accessMain" style="position: absolute;"></div>
    </div>   
</div>  
</body>
</html>