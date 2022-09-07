package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

//登出用
@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();
        
        //取得全session
        HttpSession session=request.getSession();
        //刪除session
        session.invalidate();  
        
        out.println("<head><title>登出成功</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"></head>");
          
        out.println("<div align=center> <br><br>"
				  + "<font color=red size=18>您已經登出成功<br><br>"
				  + "<button onclick=\"location.href='login.jsp'\" class=\"button buttonBlue\">回到登入畫面</button>"
				  + "</div>");  
        out.close();  
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }
}
