<%@page import="model.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社True主頁</title>

<link rel="stylesheet" type="text/css" href="./css/style.css">

<link rel="shortcut icon" href="favicon.ico?" type="image/x-icon" />
</head>
<body>
	<%
	
	//防止快取
	response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
	response.addHeader("Pragma", "no-cache"); 
	response.addDateHeader ("Expires", 0);
	
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	Person p = (Person) session.getAttribute("info");
	
	//防止直接透過網址訪問
	if (p == null) {
		response.sendRedirect("login.jsp");
	} else {
		out.println("<div class=\"divCenter\"><h2>阿囉哈 " + p.getName() + " ,<br>歡迎使用「社TRUE」打卡系統</h2></div>");
		out.println("<div class=\"divCenter\">目前身分: " + (p.getPosition().equals("S") ? "管理員" : "一般職員") + "</div>");
		out.println("<br>");
		//Check if p is empty or not
		out.println("<span style=\"color:white;\">" + p + "</span><br>");
	}
	%>
<div class="divCenter"><div class="navBackground">
	<button onclick="location.href='main.jsp'" class="button buttonBlue">主頁</button>
	<button onclick="location.href='punch_in.jsp'" class="button buttonGray">上班</button>
	<button onclick="location.href='punch_out.jsp'" class="button buttonGray">下班</button>
	<button onclick="location.href='Record'" class="button buttonGray">打卡紀錄</button>
	<button onclick="location.href='Memo'" class="button buttonGray">便箋</button>
	<button onclick="location.href='profile.jsp'" class="button buttonGray">個人資料</button>
	
	<%
	//如果是管理員, 才會顯示人員列表
	if (p != null && p.getPosition().equals("S")){
		out.println("<button onclick=\"location.href='Member'\" class=\"button buttonPurple\">人員列表</button>");
	}%>

	<button onclick="location.href='Logout'" class="button buttonRed">登出</button>
</div></div>
</body>
</html>