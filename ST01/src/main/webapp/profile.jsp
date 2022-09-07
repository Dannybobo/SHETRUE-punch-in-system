<%@page import="model.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>個人資料</title>
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
<h1 class="textCenter" >個人資料</h1>
	<div class="divCenter"><div class="profileBackground"  style="width: 50%"><%
	response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
	response.addHeader("Pragma", "no-cache");
	response.addDateHeader("Expires", 0);

	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	Person p = (Person) session.getAttribute("info");
	
	if (p == null) {
		response.sendRedirect("login.jsp");
	}
	else{
		out.println("<h3>帳號: " + p.getAccount() + "</h3><br>"
				  + "<h3>姓名: " + p.getName() + "</h3><br>"
				  + "<h3>職位: " + (p.getPosition().equals("S") ? "管理員" : "一般職員") + "</h3><br>"
				  + "<h3>電話: " + p.getPhone() + "</h3><br>"
				  + "<h3>電子郵件: " + p.getEmail() + "</h3>");
	}
	%>
	<br><div class='divCenter'><button onclick="location.href='reset.jsp'" class="button buttonGray">修改密碼</button></div>
	</div></div>
<%
	}else{
		response.sendRedirect("main.jsp");
	}
%>

</body>
</html>