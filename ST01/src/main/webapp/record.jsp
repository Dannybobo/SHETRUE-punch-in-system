<%@page import="model.Person"%>
<%@page import="java.util.*, model.Record"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>打卡紀錄</title>
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    
    <link rel="shortcut icon" href="favicon.ico?" type="image/x-icon" />
</head>
<body>
<button onclick="location.href='main.jsp'" class="button buttonBlue">主頁</button>
<h1 align="center">打卡紀錄</h1>

<%
//防止直接透過網址訪問
	Person info = (Person) session.getAttribute("info");
	if (info != null){
%>
<%
ArrayList<Record> list = (ArrayList<Record>)request.getAttribute("list");
if(!list.isEmpty()){
	out.print("<h4 class=\"tip\">由新到舊</h4>");
	int count = 1;
	
	out.print("<table>");  
    out.print("<tr><th>編號</th>"
    		+ "<th>帳號</th>"
    		+ "<th>姓名</th>"
    		+ "<th>上班</th>"
    		+ "<th>下班</th>"
    		+ "<th>操作</th></tr>");
    for(Record r:list){
    	out.print("<tr><td>" + count++ + "</td>"
    			+ "<td>"+r.getAccount()+"</td>"
    			+ "<td>"+r.getName()+"</td>"
    			+ "<td>"+r.getTimein()+"</td>"
    			+ "<td>"+r.getTimeout()+"</td>"
    			+ "<td><a href='RecordDeleteServlet?id="+r.getId()+"' onClick=\"return confirm('確定要刪除嗎？')\">刪除</a>"
    			+ "</td></tr>");  
    }  
    out.print("</table>");  
    out.close();
}else{
	out.print("<h3 class=\"warning\">Punch record is empty !</h3>"); 
}
 %>
 <%
	}else{
		response.sendRedirect("main.jsp");
	}
%>

</body>
</html>