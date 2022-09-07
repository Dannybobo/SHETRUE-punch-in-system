package servlet;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

import dao.MemoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Memo;

//顯示memo用
@WebServlet("/Memo")
public class MemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//創建放便箋的list
		List<Memo> list = MemoDao.getAllMemo();
		
		System.out.println(list.toString());
        
		//send to jsp
        request.setAttribute("list", list);
        request.getRequestDispatcher("memo.jsp").forward(request, response);
	}
}
