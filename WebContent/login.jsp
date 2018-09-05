<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1 user-scalable=0"/>  
<title>登录页面</title>
<!-- 自定义css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/meeting.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function freshen(){
		document.getElementById("image").src="<%=request.getContextPath() %>/imageServlet?date="+new Date().getTime();
		$("#checkcode").val("");   // 将验证码清空
	}
</script>
</head>
<body class="body1">
	<%
		String username = null;
		String password = null;
		Cookie[] cookies = request.getCookies();
		for (int i = 0; cookies != null && i < cookies.length; i++) {
			if (cookies[i].getName().equals("userNameAndPassword")) {
				username = cookies[i].getValue().split("-")[0];
				password = cookies[i].getValue().split("-")[1];
			}
		}
		if (username == null) {
			username = "";
		}
		if (password == null) {
			password = "";
		}
	%>
	<div class="container">
		<div class="row">
			<div class="col-md-7" style="text-align: center"></div>
			<div class="col-md-5" >
				<form action="login.action" method="post" class="formLogin">
					<table class="table1">
						<tr class="tr">
							<td>用户名：</td>
							<td><input type="text" name="username" class="form-control"
								value=<%=username%>></td>
							<td><p style="color: red">${errors.username[0] }</p></td>
						</tr>
						<tr class="tr">
							<td>密&nbsp;&nbsp;&nbsp;码：</td>
							<td><input type="password" name="password"
								class="form-control" value=<%=password%>></td>
							<td><p style="color: red">${errors.password[0] }</p></td>
						</tr>
						<tr class="tr">
							<td>验证码：</td>
							<td><input type="text" name="code" class="form-control" id="checkcode"></td>
							<td><img src="<%=request.getContextPath()%>/imageServlet"
								alt="验证码" id="image" onclick="freshen()" /></td>
							<td><p style="color: red">${errors.code[0] }</p></td>
						</tr>
						<tr class="tr">
							<td><input type="checkbox" name="remember" checked="true"
								value="remember-me" />记住密码</td>
	
						</tr>
						<tr class="tr">
							<td width="30px"></td>
							<td width="80px"><input type="submit" name="submit"
								value="登录" class="btn btn-default" onclick="verificationcode()"></td>
							<td width="80px"><input type="reset" name="reset" value="重置"
								class="btn btn-default"></td>
							<td width="80px"><a href="register.jsp"
								class="btn btn-default">注册</a></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>