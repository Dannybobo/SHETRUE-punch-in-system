package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Record;

//關於增刪改查都統一在Dao
public class RecordDao {
	private static String tbName = "record";

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
    //新增紀錄(上班)
    public static int save(Record r) {
        int status = 0;
        try {
            Connection con = PersonDao.getConnection();
            String sql = "INSERT INTO " + tbName + "(Person_account,Person_name,r_timein) values (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, r.getAccount());
            ps.setString(2, r.getName());
            ps.setString(3, r.getTimein());

            status = ps.executeUpdate();

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }
    //更新紀錄(下班)
    public static int update(Record r) {
        int status = 0;
        try {
            String sql = "UPDATE " + tbName + " SET r_timeout=? WHERE Person_account=? ORDER BY r_id DESC LIMIT 1";
            Connection con = PersonDao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, r.getTimeout());
            ps.setString(2, r.getAccount());

            status = ps.executeUpdate();

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }
    //刪除記路
    public static int delete(int id) {
        int status = 0;
        try {
            String sql = "DELETE FROM " + tbName + " WHERE r_id=?";
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
    //查詢自己紀錄(一般職員)
    public static List<Record> getRecordByAccount(String account) {
    	List<Record> list = new ArrayList<Record>();

        try {
            String sql = "SELECT * FROM " + tbName + " WHERE Person_account=? ORDER BY r_id DESC";
            Connection con = RecordDao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, account);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	Record r = new Record();
                r.setId(rs.getInt("r_id"));
                r.setAccount(rs.getString("Person_account"));
                r.setName(rs.getString("Person_name"));
                r.setTimein(rs.getString("r_timein"));
                r.setTimeout(rs.getString("r_timeout"));
                list.add(r);
                System.out.println(ps.toString());
                System.out.println(list.toString());
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }
    //查詢所有紀錄(管理員)
    public static List<Record> getAllRecord() {
        List<Record> list = new ArrayList<Record>();

        try {
            String sql = "SELECT * FROM " + tbName +" ORDER BY r_id DESC";
            Connection con = PersonDao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Record r = new Record();
                r.setId(rs.getInt("r_id"));
                r.setAccount(rs.getString("Person_account"));
                r.setName(rs.getString("Person_name"));
                r.setTimein(rs.getString("r_timein"));
                r.setTimeout(rs.getString("r_timeout"));
                list.add(r);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
