<%@page import="model.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">

<link rel="shortcut icon" href="favicon.ico?" type="image/x-icon" />
</head>
<body>
<%
//防止直接透過網址訪問&確認管理員身分
	Person p = (Person) session.getAttribute("editId");
	Person info = (Person) session.getAttribute("info");
	if (info != null && info.getPosition().equals("S")){
	
%>
<button onclick="location.href='main.jsp'" class="button buttonBlue">主頁</button>
<button onclick="location.href='Member'" class="button buttonPurple">人員列表</button>
    <h1 class="textCenter" >修改人員資料</h1>
    <div class="divCenter"><div class="formBackground" style="width: 650px">
	<form action="MemberEditServlet" method="post">
		<input type="hidden" name="id" value="<%=p.getId()%>">
    	帳號(最多8位英數字): <input type="text" name="account" maxlength="8" value="<%=p.getAccount() %>" required><br>
        名字: <input type="text" name="name" maxlength="50" value="<%=p.getName() %>" required><br>
        密碼(最多20位英數字): <input type="text" name="password" maxlength="20" value="<%=p.getPassword() %>" required><br>
        職位:<br>
        <%if(p.getPosition().equals("S")){
        	out.print("<input type='radio' name='position' value='S' checked='checked'>管理員");
        	out.print("<input type='radio' name='position' value='E'>一般職員");
        }else{
        	out.print("<input type='radio' name='position' value='S'>管理員");
        	out.print("<input type='radio' name='position' value='E' checked='checked'>一般職員");
        }
        %>
        <br>
        電話: <input type="text" name="phone" maxlength="10" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');" value="<%=p.getPhone() %>" required><br>
        電子郵件: <input type="text" name="email" maxlength="300" value="<%=p.getEmail() %>" required><br>
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