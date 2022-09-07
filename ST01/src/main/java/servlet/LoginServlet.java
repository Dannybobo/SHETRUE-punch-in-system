package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.PersonDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Person;

//登入用
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
		
		try {
			PrintWriter out = response.getWriter();
			//JDBC連線用
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/st?useUnicode=true&characterEncoding=utf8","root","337071");
			
			String account=request.getParameter("account");
			String password=request.getParameter("password");
			
			
			PreparedStatement ps = con.prepareStatement("SELECT p_account, p_name, p_position FROM person WHERE p_account=? and p_password=?");
			ps.setString(1, account);
			ps.setString(2, password);
			System.out.println(ps);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Person info = PersonDao.getPersonByAccount(account);
				//request.setAttribute("info", info);
				
				HttpSession session = request.getSession(); 
				session.setAttribute("info", info);//設置屬性 
				session.setMaxInactiveInterval(30*60);//過期時間 單位秒
				
				//以下是其他session功能
				//session.getCreationTime();//獲取session的創建時間 
				//session.getLastAccessedTime();//獲取上次與伺服器交互時間 
				//String id = session.getId();//獲取sessionId 
				//int timeout = session.getMaxInactiveInterval();//獲取session過期時間 
				//session.invalidate();//銷毀session

				response.sendRedirect("main.jsp");
			}
			//登入失敗
			else {
				out.println("<head><title>登入失敗</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"></head>");
	            out.println("<div align=center> <br><br>"
	    				  + "<font color=red size=18>登入失敗,請檢查您的帳號密碼<br><br>"
	    				  + "<button onclick=\"location.href='login.jsp'\" class=\"button buttonBlue\">重新登入</button>"
	    				  + "</div>");
			}
			
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }

}
