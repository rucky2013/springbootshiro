<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %> 
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
</head>
<body>
	错误信息：
	<h4 >${msg}</h4>
	<form action="" method="post">
		<p>帐号：<input type="text" name="username" value="admin" /></p>
		<p>密码：<input type="text" name="password" value="123456" /></p>
		<p><input type="checkbox" name="rememberMe" />记住我</p>
		<p><input type="submit" value="登录" /></p>
	</form>
</body>
</html>