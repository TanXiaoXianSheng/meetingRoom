<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
</head>
<body>
	<form action="register.action" method="post">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="username" id="username"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" id="password"></td>
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><input type="password" name="confirm_password" id="confim_password"></td>
			</tr>
			<tr>
				<td>手机号：</td>
				<td><input type="text" name="phone_number" id="phone_number"></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					 <input type="radio" name="sex" value="male" /> 男
  					 <input type="radio" name="sex" value="female" /> 女

				</td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="提交"></td>
				<td><input type="reset" name="reset" value="重置"></td>
			</tr>
		</table>
	</form>
</body>
</html>