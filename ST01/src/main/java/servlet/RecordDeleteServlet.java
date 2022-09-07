package servlet;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;

import dao.RecordDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//刪除紀錄用
@WebServlet("/RecordDeleteServlet")
public class RecordDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //取得從表單request來的參數
        String rid = request.getParameter("id");
        int id = Integer.parseInt(rid);
        //執行Dao中的功能刪除
        RecordDao.delete(id);
        //轉回RecordServlet
        response.sendRedirect("Record");
	}

}
