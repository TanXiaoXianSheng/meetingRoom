<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人详细资料</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/friends.js"></script>
</head>
<body>
	<table>
		<tr>
			<td>用户名：</td>
			<td><p id="username" name="username"></p></td>
		</tr>
		<tr>
			<td>电话：</td>
			<td><p id="phone_number" name="phone_number"></p></td>
		</tr>
		<tr>
			<td>性别：</td>
			<td><p id="sex" name="sex"></p></td>
		</tr>
		
	</table>
</body>
</html>