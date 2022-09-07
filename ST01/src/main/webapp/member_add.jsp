<%@page import="model.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增人員</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">

<link rel="shortcut icon" href="favicon.ico?" type="image/x-icon" />
</head>
<body>
<%
//防止直接透過網址訪問&確認管理員身分
	Person info = (Person) session.getAttribute("info");
	if (info != null && info.getPosition().equals("S")){
	
%>

<button onclick="location.href='main.jsp'" class="button buttonBlue">主頁</button>
<button onclick="location.href='Member'" class="button buttonPurple">人員列表</button>
    <h1 class="textCenter" >新增人員</h1>
    <div class="divCenter"><div class="formBackground" style="width: 650px">
	<form action="MemberAddServlet" method="post">
    	帳號(最多8位英數字): <input type="text" name="account" maxlength="8" required><br>
        名字: <input type="text" name="name" maxlength="50" required><br>
        密碼(最多20位英數字): <input type="text" name="password" maxlength="20" required><br>
        職位:<br> 
        <input type="radio" name="position" value="S">管理員
        <input type="radio" name="position" value="E">一般職員
        <br>
        電話: <input type="text" name="phone" maxlength="10" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');" required><br>
        電子郵件: <input type="text" name="email" maxlength="300" required><br>
        <br>
        <input type="submit" value="新增">
    </form>
    </div></div>
<%
	}else{
		response.sendRedirect("main.jsp");
	}
%>
</body>
</html>