package servlet;

import java.io.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.PersonDao;
import model.Person;

//顯示所有人員用
@WebServlet("/Member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setCharacterEncoding("UTF-8");
        //response.setContentType("text/html;charset=UTF-8");
        //request.setCharacterEncoding("UTF-8");
        //PrintWriter out=response.getWriter();  
        //out.println("<a href='main.jsp'>主畫面</a>");  
        //out.println("<h1>員工列表</h1>");  

		//執行Dao中的功能(用陣列列表接收)
        List<Person> list=PersonDao.getAllPerson();
        
        //send to jsp
        System.out.println(list.toString());
        request.setAttribute("list", list);
        request.getRequestDispatcher("member.jsp").forward(request, response);
        
        //輸出樣板
        /*out.print("<table border='1' width='100%'");  
        out.print("<tr><th>編號</th>"
        		+ "<th>姓名</th>"
        		+ "<th>帳號</th>"
        		+ "<th>職位</th>"
        		+ "<th>電話</th>"
        		+ "<th>電郵地址</th>"
        		+ "<th>操作</th></tr>");
        for(Person p:list){  
         out.print("<tr><td>"+p.getId()+"</td><td>"+p.getName()+"</td><td>"+p.getAccount()+"</td><td>"+p.getPosition()+"</td><td>"+p.getPhone()+"</td><td>"+p.getEmail()+"</td><td><a href='EditServlet?id="+p.getId()+"'>編輯</a> | <a href='DeleteServlet?id="+p.getId()+"' onClick=\"return confirm('確定要刪除嗎？')\">刪除</a></td></tr>");  
        }  
        out.print("</table>");  
        out.close();  */
    }
}
