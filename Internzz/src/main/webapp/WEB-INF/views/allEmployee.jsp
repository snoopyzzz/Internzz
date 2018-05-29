<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>" type="text/css">
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.3.min.js"/>"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<base href="<%=basePath%>">
</head>
<body>   
<h1>通过EL表达式显示的</h1>      

<c:if test="${!empty listEmployee}"> 
	<table class="tg">
	<tr>
		<th width="80">工号</th>
		<th width="80">姓名</th>
		<th width="80">电话</th>
		<th width="80">入职时间</th>
		<th width="80">部门</th>
		<th width="80">等级</th>
	</tr>
	<c:forEach items="${listEmployee}" var="employee">
		<tr>
			<td>${employee.id}</td>
			<td>${employee.name}</td>
			<td>${employee.phone}</td>
			<td>${employee.entry_time}</td>
			<td>${employee.department.id}</td>
			<td>${employee.level}</td>
		</tr>
	</c:forEach>
	</table>
</c:if>
 




</body>
</html>