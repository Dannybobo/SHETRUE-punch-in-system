<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社True登入</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">

<link rel="shortcut icon" href="favicon.ico?" type="image/x-icon" />
</head>
<body>
	<div class="divCenter"><div class="formBackground" style="width: 350px">
		<h1 class="textCenter" >歡迎使用<br>「社TRUE」打卡系統</h1><br>

		<form action=Login method=post >
		
			<h4 class="tip">請登入</h4>
			帳號: <input type=text name=account>
			密碼: <input type=password name=password>
			<br>
			<br>
			<input type=submit value=登入><br>
			<input type=reset  value=清除><br>
		</form>
	</div></div>
</body>
</html>