<#include "admin/header.ftl" parse=true encoding="utf-8">
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<form action="${ctx}/consumer/doLogin" method="post">
			手机号：<input type="text" name="mobileNum" value="">
			密码：<input type="password" name="password">
			<input type="submit" value="登录">
		</form>
	</body>
</html>