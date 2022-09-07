package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Memo;


//關於增刪改查都統一在Dao
public class MemoDao {
	private static String tbName = "memo";

	//JDBC連線
    public static Connection getConnection() {
        Connection con = null;
        try {
        	//JDBC 驅動
            Class.forName("com.mysql.jdbc.Driver");
            //資料庫URL
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/st?useUnicode=true&characterEncoding=utf8","root","337071");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    //新增memo
    public static int save(Memo m) {
    	//狀態(用以回傳至Controller(Servlet)來確認是否成功寫入或修改資料庫)
        int status = 0;
        try {
        	//帶入上面自身的JDBC連線
            Connection con = PersonDao.getConnection();
            //產生SQL語法
            String sql = "INSERT INTO " + tbName + "(Person_account,Person_name,m_datetime,m_msg) values (?,?,?,?)";
            //帶入ps
            PreparedStatement ps = con.prepareStatement(sql);

            //將參數照順序填入語法
            ps.setString(1, m.getAccount());
            ps.setString(2, m.getName());
            ps.setString(3, m.getDatetime());
            ps.setString(4, m.getMsg());
            
            //執行新增(修改)
            status = ps.executeUpdate();

            //關閉連線
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }
    //刪除memo
    public static int delete(int id) {
        int status = 0;
        try {
            String sql = "DELETE FROM " + tbName + " WHERE m_id=?";
            Connection con = PersonDao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            status = ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    //只查詢取得某人的memo(未實作)
    public static List<Memo> getMemoByAccount(String account) {
    	List<Memo> list = new ArrayList<Memo>();

        try {
            String sql = "SELECT * FROM " + tbName + " WHERE Person_account=? ORDER BY m_id DESC";
            Connection con = RecordDao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, account);
            //執行查詢
            ResultSet rs = ps.executeQuery();
            
            //用model.memo去接收資料
            while (rs.next()) {
            	//重新宣告一個memo
            	Memo m = new Memo();
                m.setId(rs.getInt("m_id"));
                m.setAccount(rs.getString("Person_account"));
                m.setName(rs.getString("Person_name"));
                m.setDatetime(rs.getString("r_datetime"));
                m.setMsg(rs.getString("m_msg"));
                //將一筆m加入至陣列list
                list.add(m);
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //回傳取得的資料陣列
        return list;
    }
    //查詢全部memo
    public static List<Memo> getAllMemo() {
        List<Memo> list = new ArrayList<Memo>();

        try {
            String sql = "SELECT * FROM " + tbName +" ORDER BY m_id DESC";
            Connection con = PersonDao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	Memo m = new Memo();
                m.setId(rs.getInt("m_id"));
                m.setAccount(rs.getString("Person_account"));
                m.setName(rs.getString("Person_name"));
                m.setDatetime(rs.getString("m_datetime"));
                m.setMsg(rs.getString("m_msg"));
                list.add(m);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
