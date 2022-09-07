package servlet;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

import dao.RecordDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Person;
import model.Record;

//顯示打卡用
@WebServlet("/Record")
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//取得session中的info資料放入p: Person
		HttpSession session=request.getSession();
		Person p = (Person) session.getAttribute("info");
		
		//創建放打卡紀錄的list
		List<Record> list;
		
		//如果是S(管理員)取得全部人打卡紀錄
		if(p.getPosition().equals("S")) {
			list = RecordDao.getAllRecord();
		//如果不是,取得自己打卡紀錄
		}else {
			list = RecordDao.getRecordByAccount(p.getAccount());
		}
        
        System.out.println(list.toString());
        
        //send to jsp
        request.setAttribute("list", list);
        request.getRequestDispatcher("record.jsp").forward(request, response);

	}
}
