<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="5">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录成功::获取在线列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
</head>
<body>
<table style="margin-left: 50px">
	<tr><td>当前在线用户：</td></tr>
	<%
		List onlineUserSet = (List)application.getAttribute("onlineUserSet");
		for(int i = 0;i < onlineUserSet.size();i ++){
			String onlineUsername = (String)onlineUserSet.get(i);
	%>
	<tr>
		<td><%=onlineUsername %></td>
	</tr>
	<%
		}
	%>
</table>
</body>
</html>