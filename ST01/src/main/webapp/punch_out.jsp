<%@page import="model.Person"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>打卡下班</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">

<link rel="shortcut icon" href="favicon.ico?" type="image/x-icon" />
</head>
<body>
<%
//防止直接透過網址訪問
	Person info = (Person) session.getAttribute("info");
	if (info != null){
%>
<button onclick="location.href='main.jsp'" class="button buttonBlue">主頁</button>
	<h1 class="textCenter" >打卡-下班</h1>
	<%
		//獲取日期時間
		Date today = new Date();
		//設定格式
		SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//格式化日期時間
		String datetime = fDate.format(today);
		
		Person p = (Person) session.getAttribute("info");
		String account = p.getAccount();
		String name = p.getName();
	%>
	<div class="divCenter"><div class="formBackground">
	<form action="PunchOut" method="post">
		帳號: <input type="text" name="account" value="<%= account %>" readonly><br>
		名字: <input type="text" name="name" value="<%= name%>" readonly><br>
		時間: <input type="text" name="time_out" value="<%=datetime%>" readonly><br>
		<br>
		<input type="submit" value="打卡">
	</form>
	</div></div>
<%
	}else{
		response.sendRedirect("main.jsp");
	}
%>
</body>
</html>