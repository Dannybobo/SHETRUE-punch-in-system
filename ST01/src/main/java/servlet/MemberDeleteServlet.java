package servlet;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;

import dao.PersonDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//刪除成員用
@WebServlet("/MemberDeleteServlet")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //取得從表單request來的參數
        String mid = request.getParameter("id");
        int id = Integer.parseInt(mid);
        //執行Dao中的功能刪除
        PersonDao.delete(id);
        //轉回MemberServlet
        response.sendRedirect("Member");
	}

}