/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.BanDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LE NHAN
 */
public class BanDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public BanDAO(){}
    
    public ArrayList<BanDTO> list()
    {
        ArrayList<BanDTO> dsb = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM ban WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String IDB = rs.getString("IDBAN");
                String Khu = rs.getString("Khu");
                String TinhTrang = rs.getString("TinhTrang");
                BanDTO ct = new BanDTO(IDB, Khu, TinhTrang);
                dsb.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsb;
    }
    public void set(BanDTO b) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE ban SET ";
            sql += "Khu='"+b.getKhu()+"', ";
            sql += "TinhTrang='"+b.getTinhTrang()+"', ";
            sql += " WHERE IDBAN='"+b.getIDB()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(BanDTO b) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO ban VALUES (";
                sql += "'"+b.getIDB()+"',";
                sql += "'"+b.getKhu()+"',";
                sql += "'"+b.getTinhTrang()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String IDB)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE ban SET enable = 0 WHERE IDBAN='"+IDB+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
