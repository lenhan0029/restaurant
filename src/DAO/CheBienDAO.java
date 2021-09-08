/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.CheBienDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LE NHAN
 */
public class CheBienDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public CheBienDAO(){}
    
    public ArrayList<CheBienDTO> list()
    {
        ArrayList<CheBienDTO> dscb = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM chebien WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String IDMA = rs.getString("IDMONAN");
                String IDNL = rs.getString("IDNUYENLIEU");
                int SoLuongNL = rs.getInt("SoLuongNL");
                CheBienDTO ct = new CheBienDTO(IDMA, IDNL, SoLuongNL);
                dscb.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dscb;
    }
    public void set(CheBienDTO cb) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE chebien SET ";
            sql += "SoLuongNL='"+cb.getSoLuongNL()+"', ";
            sql += " WHERE IDNUYENLIEU='"+cb.getIDNL()+"' AND ";
            sql += " IDMONAN='"+cb.getIDMA()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(CheBienDTO cb) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO chebien VALUES (";
                sql += "'"+cb.getIDNL()+"',";
                sql += "'"+cb.getIDMA()+"',";
                sql += "'"+cb.getSoLuongNL()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String IDNL, String IDMA)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE chebien SET enable = 0 WHERE IDNGUYENLIEU='"+IDNL+"' AND IDMONAN='"+IDMA+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
