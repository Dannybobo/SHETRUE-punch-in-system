package servlet;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import dao.PersonDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Person;

//編輯人員用
@WebServlet("/MemberEditServlet")
public class MemberEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//載入編輯畫面(舊資料)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        //執行Dao中的功能
        Person p = PersonDao.getPersonById(id);
        
        //創建被編輯人員的資料session
        HttpSession session = request.getSession(); 
		session.setAttribute("editId", p);
		//傳至編輯人員頁
		response.sendRedirect("member_edit.jsp");
	}
	//執行編輯
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        //取得從表單request來的參數們
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String position = request.getParameter("position");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        //寫入model
        Person p = new Person();
        p.setId(Integer.parseInt(id));
        p.setName(name);
        p.setAccount(account);
        p.setPassword(password);
        p.setPosition(position);
        p.setPhone(Integer.parseInt(phone));
        p.setEmail(email);

        //執行Dao中的功能
        int status = PersonDao.update(p);
        
        //成功
        if (status > 0) {
            out.println("<head><title>修改人員資料成功</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"></head>");
            out.println("<div align=center> <br><br>"
    				  + "<font color=green size=18>修改人員資料成功<br><br>"
    				  + "<button onclick=\"location.href='main.jsp'\" class=\"button buttonBlue\">主頁</button>"
    				  + "<button onclick=\"location.href='Member'\" class=\"button buttonGray\">人員列表</button>"
    				  + "</div>"); 
        //失敗
        } else {
        	out.println("<head><title>修改人員資料失敗</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"></head>");
            out.println("<div align=center> <br><br>"
    				  + "<font color=red size=18>修改人員資料失敗<br><br>"
    				  + "<button onclick=\"location.href='MemberEditServlet?id=" + p.getId() + " class=\"button buttonGray\">重新修改</button>"
    				  + "<button onclick=\"location.href='main.jsp'\" class=\"button buttonBlue\">主頁</button>"
    				  + "<button onclick=\"location.href='Member'\" class=\"button buttonGray\">人員列表</button>"
    				  + "</div>");
        }
        out.close();
	}
}
