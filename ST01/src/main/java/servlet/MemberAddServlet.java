package servlet;

import jakarta.servlet.http.HttpServlet;
import java.io.*;

import dao.PersonDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Person;

//新增人員用
@WebServlet("/MemberAddServlet")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        //取得從表單request來的參數們
        String name = request.getParameter("name");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String position = request.getParameter("position");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        //寫入model
        Person p = new Person();
        p.setName(name);
        p.setAccount(account);
        p.setPassword(password);
        p.setPosition(position);
        p.setPhone(Integer.parseInt(phone));
        p.setEmail(email);

        //執行Dao中的功能
        int status = PersonDao.save(p);
        //成功
        if (status > 0) {
            out.println("<head><title>新增人員成功</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"></head>");
            out.println("<div align=center> <br><br>"
    				  + "<font color=green size=18>新增人員成功<br><br>"
    				  + "<button onclick=\"location.href='main.jsp'\" class=\"button buttonBlue\">主頁</button>"
    				  + "<button onclick=\"location.href='Member'\" class=\"button buttonGray\">人員列表</button>"
    				  + "</div>");  
        //失敗
        } else {
        	out.println("<head><title>新增人員失敗</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"></head>");
            out.println("<div align=center> <br><br>"
    				  + "<font color=red size=18>新增人員失敗<br><br>"
    				  + "<button onclick=\"location.href='member_add.jsp'\" class=\"button buttonGray\">重新新增</button>"
    				  + "<button onclick=\"location.href='main.jsp'\" class=\"button buttonBlue\">主頁</button>"
    				  + "<button onclick=\"location.href='Member'\" class=\"button buttonGray\">人員列表</button>"
    				  + "</div>");
        }
        out.close();
    }
}
