package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Person;

//關於增刪改查都統一在Dao
public class PersonDao {
	private static String tbName = "person";

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
    //新增person
    public static int save(Person p) {
    	//狀態(用以回傳至Controller(Servlet)來確認是否成功寫入或修改資料庫)
        int status = 0;
        try {
        	//帶入上面自身的JDBC連線
            Connection con = PersonDao.getConnection();
            //產生SQL語法
            String sql = "INSERT INTO " + tbName + "(p_name,p_account,p_password,p_position,p_phone,p_email) values (?,?,?,?,?,?)";
            //帶入ps
            PreparedStatement ps = con.prepareStatement(sql);

            //將參數照順序填入語法
            ps.setString(1, p.getName());
            ps.setString(2, p.getAccount());
            ps.setString(3, p.getPassword());
            ps.setString(4, p.getPosition());
            ps.setInt(5, p.getPhone());
            ps.setString(6, p.getEmail());

            //執行新增(修改)
            status = ps.executeUpdate();

            //關閉連線
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }
    //修改某person資料
    public static int update(Person p) {
        int status = 0;
        try {
            String sql = "UPDATE " + tbName + " SET p_name=?,p_account=?,p_password=?,p_position=?,p_phone=?,p_email=? WHERE p_id=?";
            Connection con = PersonDao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getAccount());
            ps.setString(3, p.getPassword());
            ps.setString(4, p.getPosition());
            ps.setInt(5, p.getPhone());
            ps.setString(6, p.getEmail());
            ps.setInt(7, p.getId());

            //執行新增(修改)
            status = ps.executeUpdate();

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }
    //刪除person
    public static int delete(int id) {
        int status = 0;
        try {
            String sql = "DELETE FROM " + tbName + " WHERE p_id=?";
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
    //查詢某人(以id)
    public static Person getPersonById (int id) {
        Person p = new Person();

        try {
            String sql = "SELECT * FROM " + tbName + " WHERE p_id=?";
            Connection con = PersonDao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p.setId(rs.getInt("p_id"));
                p.setName(rs.getString("p_name"));
                p.setAccount(rs.getString("p_account"));
                p.setPassword(rs.getString("p_password"));
                p.setPosition(rs.getString("p_position"));
                p.setPhone(rs.getInt("p_phone"));
                p.setEmail(rs.getString("p_email"));
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return p;
    }
    //查詢某人(以帳號)
    public static Person getPersonByAccount (String account) {
    	Person p = new Person();

        try {
            String sql = "SELECT * FROM " + tbName + " WHERE p_account=?";
            Connection con = PersonDao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, account);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p.setId(rs.getInt("p_id"));
                p.setName(rs.getString("p_name"));
                p.setAccount(rs.getString("p_account"));
                p.setPassword(rs.getString("p_password"));
                p.setPosition(rs.getString("p_position"));
                p.setPhone(rs.getInt("p_phone"));
                p.setEmail(rs.getString("p_email"));
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return p;
    }
    //查詢所有人
    public static List<Person> getAllPerson() {
        List<Person> list = new ArrayList<Person>();

        try {
            String sql = "SELECT * FROM " + tbName +" ORDER BY p_id DESC";
            Connection con = PersonDao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            //用model.person去接收資料
            while (rs.next()) {
                Person p = new Person();
                p.setId(rs.getInt("p_id"));
                p.setName(rs.getString("p_name"));
                p.setAccount(rs.getString("p_account"));
                p.setPosition(rs.getString("p_position"));
                p.setPhone(rs.getInt("p_phone"));
                p.setEmail(rs.getString("p_email"));
                list.add(p);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
