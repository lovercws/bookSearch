<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>书籍分类统计</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/echarts-all.js"></script>
<style type="text/css">
body{
   margin: 0px;
   padding: 0px;
   width: 100%;
   height: 100%;
   position: absolute;
}
a{
   text-decoration: none;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	var myChart = echarts.init(document.getElementById('main')); 
	var option = {
		    title : {
		        text: '书籍分类统计'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['书籍分配量']
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
		            data : ['古籍·国学','文学艺术','人文社科','经济管理','生活时尚','科学技术','教育·教材·教辅','少儿读物']
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
		            name:'书籍分配量',
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
	myChart.setOption(option); 
});

//统计上传量
function statisticsUpload(orderby){
	
}
</script>
</head>
<body>
<div id="main" style="height:100%;width: 100%;position: relative;"></div>
</body>
</html>