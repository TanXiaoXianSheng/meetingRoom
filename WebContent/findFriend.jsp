<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/friends.js"></script>
</head>
<body>
	<table>
		<tr>
			<td><input type="text" name="findByUsername" id="findByUsername"></td>
			<td><input type="button" value="查找" name="find" id="find" onclick="findByUsername()"></td>
		</tr>
		<tr>
			<td colspan="2"><a href="#" title="查看资料"onclick="findResult('yincang')"><p name="findResult" id="findResult"></p></a></td>
		</tr>
		
	</table>
	<table id="yincang" style="display:none">
		<tr>
			<td>用户名：</td>
			<td><span id="username" name="username"></span></td>
		</tr>
		<tr>
			<td>手机号：</td>
			<td><span id="phone_number" name="phone_number"></span></td>
		</tr>
		<tr>
			<td>性别：</td>
			<td><span id="sex" name="sex"></span></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="button" name="addFriend" id="addFriend" value="添加成好友" onclick="addFriend()"></td>
		</tr>
		<tr id="yincangAdd">
			<td colspan="2"><span id="addMessage" name="addMessage"></span></td>
		</tr>
	</table>
</body>
</html>