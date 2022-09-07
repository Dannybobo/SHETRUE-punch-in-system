package servlet;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.MemoDao;

//刪除便箋用
@WebServlet("/MemoDeleteServlet")
public class MemoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //取得從表單request來的參數
        String mid = request.getParameter("id");
        int id = Integer.parseInt(mid);
        //執行Dao中的功能刪除
        MemoDao.delete(id);
        //轉回MemoServlet
        response.sendRedirect("Memo");
	}
}
