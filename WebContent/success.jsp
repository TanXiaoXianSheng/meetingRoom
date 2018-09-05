<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录成功::获取在线列表</title>
<!-- 自定义css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/success.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>


</head>
<frameset rows="20%,70%,10%">
	<frame src="header.jsp" />
	<frameset cols="20%,*,15%">
		<frame src="onlineUser.jsp">
		<frame src="main.jsp">
		<frameset rows="10%,*">
			<frame src="top.jsp">
			<frame src="findFriend.jsp" name="friends">
		</frameset>
	</frameset>
	<frame src="footer.jsp" />
</frameset>

</html>