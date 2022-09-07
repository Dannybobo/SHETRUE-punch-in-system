package servlet;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import dao.MemoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Memo;

//新增便箋用
@WebServlet("/MemoPostServlet")
public class MemoPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
    	
        //取得從表單request來的參數們
    	String account = request.getParameter("account");
        String name = request.getParameter("name");
        String datetime = request.getParameter("datetime");
        String msg = request.getParameter("msg");
        
        //寫入model
        Memo m = new Memo();
        m.setAccount(account);
        m.setName(name);
        m.setDatetime(datetime);
        m.setMsg(msg);
        
        //執行Dao中的功能
        int status = MemoDao.save(m);
        
        //成功
        if (status > 0) {
        	response.sendRedirect("Memo");
        	
        //失敗
        } else {
        	out.println("<head><title>發布失敗</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"></head>");
            out.println("<div align=center> <br><br>"
    				  + "<font color=red size=18>發布失敗, 請重新發布、登入、或聯繫管理員<br><br>"
    				  + "<button onclick=\"location.href='Memo'\" class=\"button buttonGray\">重新發布</button>"
    				  + "<button onclick=\"location.href='main.jsp'\" class=\"button buttonBlue\">主頁</button>"
    				  + "</div>");
        }
        out.close();
	}
}
