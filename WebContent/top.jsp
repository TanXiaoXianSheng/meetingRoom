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

<script src="${pageContext.request.contextPath}/js/friends.js"></script>
</head>
<body>
	<table>
		<tr>
			<td><a href="findFriend.jsp" target="friends">添加好友</a></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td><a href="friends.jsp" target="friends" onclick="readFriendsList()">好友列表</a></td>
		</tr>
	</table>
</body>
</html>	
		
		
		
