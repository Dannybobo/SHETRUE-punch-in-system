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
		
		Person p = (Person) session.getAttribute("info");
  		String account = p.getAccount();
	
%>
<button onclick="location.href='main.jsp'" class="button buttonBlue">主頁</button>
<button onclick="location.href='profile.jsp'" class="button buttonGray">個人資料</button>
<h1 class="textCenter" >修改密碼</h1>

<div class="divCenter"><div class="formBackground">
	<form action="Reset" method="post">
    	帳號: <input type="text" name="account" value="<%= account %>" readonly><br>
    	舊密碼: <input type="text" name="password" maxlength="20" required><br>
        新密碼(最多20位英數字): <input type="text" name="newPassword" maxlength="20" required><br>
        <h4 class="tip">要修改其餘資料請聯絡管理員</h4>
        <br>
        <input type="submit" value="修改">
    </form>
    </div></div>

<%
	}else{
		response.sendRedirect("main.jsp");
	}
%>
</body>
</html>