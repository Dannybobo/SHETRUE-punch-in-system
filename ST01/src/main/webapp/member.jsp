<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.Person"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/style.css">

<link rel="shortcut icon" href="favicon.ico?" type="image/x-icon" />
<title>人員列表</title>
</head>
<body>
<button onclick="location.href='main.jsp'" class="button buttonBlue">主頁</button>
<button onclick="location.href='member_add.jsp'" class="button buttonGreen">新增人員</button>

<h1 align="center">人員列表</h1>

<%
//防止直接透過網址訪問&確認管理員身分
Person info = (Person) session.getAttribute("info");
if (info != null && info.getPosition().equals("S")){
	ArrayList<Person> list = (ArrayList<Person>)request.getAttribute("list");
	int count = 1;
	
	out.print("<table border='1' width='100%'");  
    out.print("<tr><th>編號</th>"
    		+ "<th>姓名</th>"
    		+ "<th>帳號</th>"
    		+ "<th>職位</th>"
    		+ "<th>電話</th>"
    		+ "<th>電郵地址</th>"
    		+ "<th>操作</th></tr>");
    for(Person p:list){  
     out.print("<tr><td>"+ count++ +"</td>"
    		 + "<td>"+p.getName()+"</td>"
    		 + "<td>"+p.getAccount()+"</td>"
    		 + "<td>"+ (p.getPosition().equals("S") ? "管理員" : "一般職員") +"</td>"
    		 + "<td>"+p.getPhone()+"</td>"
    		 + "<td>"+p.getEmail()+"</td>"
    		 + "<td><a href='MemberEditServlet?id="+p.getId()+"'>編輯</a> | "
    		 + "<a href='MemberDeleteServlet?id="+p.getId()+"' onClick=\"return confirm('確定要刪除嗎？')\">刪除</a></td></tr>");  
    }  
    out.print("</table>");  
    out.close(); 
}else{
	response.sendRedirect("main.jsp");
}
%>

</body>
</html>