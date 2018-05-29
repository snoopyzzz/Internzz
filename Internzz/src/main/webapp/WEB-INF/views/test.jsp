<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
    <%
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>


<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.3.min.js"/>"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<base href="<%=basePath%>">    
</head>
<body> 

<!-- ================================================================== -->
<h1>升职人员：</h1>
 <div id="promotedContent"> 
     
</div>
<!-- ================================================================== -->
<h1>所有员工：</h1>
输入部门编号，鼠标离开焦点可查询：<input type="text" id="text1" onblur="checkDepID();getItem();">    
 <div id="allEmployeeContent">
 
</div> 
<br>
<!-- ================================================================== -->         
   
请输入部门编号：<input type="text" id="text2">
 <input type="button" id="btn2" value="按部门编号查询"><br><br>   
<!-- ================================================================== -->     
请输入员工姓名关键字（模糊查找）：<input type="text" id="text3">
 <input type="button" id="btn3" value="按员工姓名模糊查找">  

<!-- ================================================================== -->  
   

  
</body>

<script type="text/javascript"> 

//1、每天凌晨1点自动检测员工信息，判断是否晋升级别，完成级别评定；
//设置一个60秒的自动定时，当h==1 && m==0，执行任务
setInterval(function(){
	var d = new Date();
	var h = d.getHours();
	var m = d.getMinutes();
	      

	if( h== 1 && m == 0){	//1:00进行提拔操作
		//alert(h+":"+m);
		task();
	}
},60000);       //当刷新时间<60秒时，升职人员的信息会被 “今天没有人升职”覆盖
						//>60秒则有可能错误升职操作

//任务：通过ajax异步刷新，
//返回data 并对data进行判断
//如果data.promotedList=="null"表示没有符合升职条件的职员，调用noPeoplePromoted();方法
//否则表示有符合升职条件的人，并已对他们修改，调用refresh(data);
function task(){
	$.ajax({
		url					:	'promotedAll', 
		contentType	:	"application/json;charset=UTF-8",
		type					:	"post",
		dataType		:	"json",
		success			:	function(data){ 
			if(data.promotedList=="null"){
				noPeoplePromoted();
			}else{
				refresh(data);
			}	

		}
	});
}
//当有人升职时，调用此函数
function refresh(data){
	var html = "";
  $.each(data.promotedList, function(index, item) {
		//alert(item.name)
  	html += "<p>工号:" + item.id + " 姓名：" + item.name + " 当前级别:" + item.level + "</p>";
  });
  $("#promotedContent").html(html);
}
//当无人升职时，调用此函数
function noPeoplePromoted(){
	var html = "";
  html += "<p>今天没有人升职哦！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！</p>";

  $("#promotedContent").html(html);
}

//通过ajax把要查询的id传到后台,成功调用display(data);
function getItem(){

		$.ajax({
			url					:	'all', 
			data					:	$("#text1").val(),
			contentType	:	"application/json;charset=UTF-8",
			type					:	"post",
			dataType		:	"json",
			success			:	function(data){ 
				display(data);
			}
			
		});
}
        

//把查询到的员工们显示出来
function display(data){
	var html = ""; 
    $.each(data.员工, function(i, item) {
   		html += "<p>姓名：" + item.name + " 电话：" + item.phone  + " 入职时间：" + item.entry_time + " 职级：" + item.level + "</p>";
    });
    
    $("#allEmployeeContent").html(html);
}

//判断该部门ID是否存在 
function checkDepID(){
	$.ajax({
		url					:	'checkDep', 
		data					:	$("#text1").val(),
		contentType	:	"application/json;charset=UTF-8",
		type					:	"post",
		dataType		:	"text",
		success			:	function(data){ 
			checkResult(data);				
		}
	});															//这里有一个新的问题..........
}																//因为"确认"和"显示"这两方法都是onblur事件
																//因此，如果输入的id不存在，虽然是显示出“没有该部门了”，但是却输出了一个空参数（报错），该怎么解决呢？
//判断结果																
function checkResult(data){
	if(data=="N"){
		$("#allEmployeeContent").html("<b style='color: red'>没有该部门！</b>");		
	}
}

 
//跳转到想要查询的部门（通过部门id）
$(function(){   
	$("#btn2").click(function(){ 
		
		$.ajax({
			url					:	'allEmpOnEl',
			data					:	$("#text2").val(),
			contentType	:	"application/json;charset=UTF-8",
			type					:	"post",
			success			:	function(data){
				window.location.href=data; 
			}
		});
		  
	});   
});     

//跳转到想要查询的部门（通过部门id）
$(function(){   
	$("#btn3").click(function(){ 
		
		$.ajax({
			url					:	'byLikeName',
			data					:	$("#text3").val(),
			contentType	:	"application/json;charset=UTF-8",
			type					:	"post",
			success			:	function(data){
				window.location.href=data;   
			}
		});  
		  
	});     
});   

   

 


</script>

</html>