package servlet;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//修改密碼用
@WebServlet("/Reset")
public class ResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/st?useUnicode=true&characterEncoding=utf8","root","337071");
			
			//取得從表單request來的參數們
			String account=request.getParameter("account");
			String password=request.getParameter("password");
			String newPassword=request.getParameter("newPassword");
			
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM person WHERE p_account=? and p_password=?");
			ps.setString(1, account);
			ps.setString(2, password);
			System.out.println(ps);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				PreparedStatement ps2 = con.prepareStatement("UPDATE person SET p_password=?WHERE p_account=?");
				ps2.setString(1, newPassword);
				ps2.setString(2, account);
				
				System.out.println(ps2);
				int status = ps2.executeUpdate();
				if (status > 0) {
					out.println("<head><title>修改成功</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"></head>");
		            out.println("<div align=center> <br><br>"
		    				  + "<font color=green size=18>修改密碼成功<br><br>"
		    				  + "<button onclick=\"location.href='main.jsp'\" class=\"button buttonBlue\">主頁</button>"
		    				  + "<button onclick=\"location.href='profile.jsp'\" class=\"button buttonGray\">個人資料</button>"
		    				  + "</div>");  
				}
				else {
					out.println("<head><title>修改密碼失敗</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"></head>");
					out.println("<div align=center> <br><br>"
	    				  + "<font color=red size=18>錯誤, 請重試或聯絡管理員<br><br>"
	    				  + "<button onclick=\"location.href='reset.jsp'\" class=\"button buttonGray\">重新修改</button>"
	    				  + "<button onclick=\"location.href='main.jsp'\" class=\"button buttonBlue\">主頁</button>"
	    				  + "</div>");
					
				}
			}
			else {
				out.println("<head><title>修改密碼失敗</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"></head>");
	            out.println("<div align=center> <br><br>"
	    				  + "<font color=red size=18>舊密碼錯誤<br><br>"
	    				  + "<button onclick=\"location.href='reset.jsp'\" class=\"button buttonGray\">重新修改</button>"
	    				  + "<button onclick=\"location.href='main.jsp'\" class=\"button buttonBlue\">主頁</button>"
	    				  + "</div>");
			}
			
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
