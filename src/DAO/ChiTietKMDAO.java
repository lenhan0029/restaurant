/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietKMDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LE NHAN
 */
public class ChiTietKMDAO {
     private MySQLConnect mySQL = new MySQLConnect();
    public ChiTietKMDAO(){}
    
    public ArrayList<ChiTietKMDTO> list()
    {
        ArrayList<ChiTietKMDTO> dsctkm = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM chitietkhuyenmai WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String IDKM = rs.getString("IDKM");
                String IDMA = rs.getString("IDMONAN");
                int GiamGia = rs.getInt("GiamGia");
                ChiTietKMDTO ct = new ChiTietKMDTO(IDKM, IDMA, GiamGia);
                dsctkm.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsctkm;
    }
    public void set(ChiTietKMDTO ctkm) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE chitietkhuyenmai SET ";
            sql += "GiamGia='"+ctkm.getGiamGia()+"', ";
            sql += " WHERE IDKM='"+ctkm.getIDKM()+"' AND IDMONAN='"+ctkm.getIDMA()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(ChiTietKMDTO ctkm) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO chitietkhuyenmai VALUES (";
                sql += "'"+ctkm.getIDKM()+"',";
                sql += "'"+ctkm.getIDMA()+"',";
                sql += "'"+ctkm.getGiamGia()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String IDKM, String IDMA)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE chitietkhuyenmai SET enable = 0 WHERE IDKM='"+IDKM+"' AND IDMONAN='"+IDMA+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
