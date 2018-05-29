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
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.3.js"/>"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门列表</title>
<base href="<%=basePath%>">
</head>
<body>


<c:if test="${!empty listDepartment}"> 
	<table class="tg">
	<tr>
		<th width="80">部门编号</th> 
		<th width="80">部门名称</th>
		<th width="80">部门地址</th>
		<th width="80">详细信息</th>
	</tr>
	<c:forEach items="${listDepartment}" var="department">
		<tr>
			<td>${department.id}</td>
			<td>${department.name}</td>
			<td>${department.address}</td>
			<td>${department.name}</td>
		</tr>
	</c:forEach>
	</table>
</c:if>

<script type="text/javascript">



</script>



</body>
</html>