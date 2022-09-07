<%@page import="model.Person"%>
<%@page import="java.util.*, model.Memo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>便箋</title>
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    
    <link rel="shortcut icon" href="favicon.ico?" type="image/x-icon" />
    
    <style> 
	textarea {
  		width: 100%;
  		height: 50px;
  		padding: 12px 20px;
  		box-sizing: border-box;
  		border: 2px solid #ccc;
  		border-radius: 5px;
  		font-size: 16px;
  		resize: vertical;
	}
	table td {
    	word-wrap: break-word;         /* All browsers since IE 5.5+ */
    	overflow-wrap: break-word;     /* Renamed property in CSS3 draft spec */
	}
</style>
</head>
<body>
<%
//防止直接透過網址訪問
	Person info = (Person) session.getAttribute("info");
	if (info != null){
%>
<button onclick="location.href='main.jsp'" class="button buttonBlue">主頁</button>
    <h1 class="textCenter" >便箋</h1>
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
 	<div class="divCenter"><div class="formBackground" style="width: 1200px">
 	<form action="MemoPostServlet" method="post">
 		訊息(最多500字元):<br><br> <textarea name="msg" placeholder="說些什麼" maxlength="500" required></textarea>
    	帳號: <input type="text" name="account" value="<%= account %>" style="width: 25%;" readonly>
        名字: <input type="text" name="name" value="<%= name%>" style="width: 25%;" readonly>
        時間: <input type="text" name="datetime" value="<%= datetime %>" style="width: 25%;" readonly>
        <input type="submit" value="發布" style="display: inline;width: 160px;">
    </form>
    </div></div>

<%
ArrayList<Memo> list = (ArrayList<Memo>)request.getAttribute("list");
if(!list.isEmpty()){
	out.print("<h4 class=\"tip\">由新到舊</h4>");
	int count = 1;
	
	out.print("<table style='width: 80%'>");  
    out.print("<tr><th>編號</th>"
    		+ "<th>帳號</th>"
    		+ "<th>姓名</th>"
    		+ "<th>訊息</th>"
    		+ "<th>發布時間</th>"
    		+ "<th>操作</th></tr>");
    for(Memo m:list){
    	out.print("<tr><td style='width: 35px'>" + count++ + "</td>"
    			+ "<td>"+m.getAccount()+"</td>"
    			+ "<td>"+m.getName()+"</td>"
    			+ "<td style='max-width:150px; width: 65%'>"+m.getMsg()+"</td>"
    			+ "<td style='width: 90px'>"+m.getDatetime()+"</td>");
    	if(m.getAccount().equals(account) || p.getPosition().equals("S")){
    		out.print("<td style='width: 35px'><a href='MemoDeleteServlet?id="+m.getId()+"' onClick=\"return confirm('確定要刪除嗎？')\">刪除</a>");
    	}else{
    		out.print("<td style='width: 35px'></a>");
    	}
    	out.print( "</td></tr>");  
    }  
    out.print("</table><br><br><br><br><br>");  
    out.close();
}else{
	out.println(list);
	out.print("<h3 class=\"warning\">Memo list is empty !</h3>"); 
}
 %>
 <%
	}else{
		response.sendRedirect("main.jsp");
	}
%>

</body>
</html>