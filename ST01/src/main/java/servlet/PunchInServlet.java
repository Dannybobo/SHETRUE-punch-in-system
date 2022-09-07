package servlet;

import jakarta.servlet.http.HttpServlet;
import model.Record;

import java.io.IOException;
import java.io.PrintWriter;

import dao.RecordDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

//下班打卡用
@WebServlet("/PunchIn")
public class PunchInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        //取得從表單request來的參數們
        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String timein = request.getParameter("timein");
        
        //寫入model
        Record r = new Record();
        r.setAccount(account);
        r.setName(name);
        r.setTimein(timein);

        //執行Dao中的功能
        int status = RecordDao.save(r);
        //成功
        if (status > 0) {
            out.println("<head><title>打卡成功</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"></head>");
            out.println("<div align=center> <br><br>"
    				  + "<font color=green size=18>打卡成功, 祝您上般愉快<br><br>"
    				  + "<button onclick=\"location.href='main.jsp'\" class=\"button buttonBlue\">主頁</button>"
    				  + "<button onclick=\"location.href='Record'\" class=\"button buttonGray\">打卡紀錄</button>"
    				  + "</div>");  
        //失敗
        } else {
        	out.println("<head><title>打卡失敗</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"></head>");
            out.println("<div align=center> <br><br>"
    				  + "<font color=red size=18>打卡失敗, 請重新打卡、登入、或聯繫管理員<br><br>"
    				  + "<button onclick=\"location.href='punch_in.jsp'\" class=\"button buttonGray\">重新上班打卡</button>"
    				  + "<button onclick=\"location.href='main.jsp'\" class=\"button buttonBlue\">主頁</button>"
    				  + "<button onclick=\"location.href='Record'\" class=\"button buttonGray\">打卡紀錄</button>"
    				  + "</div>");
        }
        out.close();
    }
}
