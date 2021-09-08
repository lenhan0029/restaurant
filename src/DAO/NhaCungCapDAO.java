/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhaCungCapDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LE NHAN
 */
public class NhaCungCapDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public NhaCungCapDAO(){}
    
    public ArrayList<NhaCungCapDTO> list()
    {
        ArrayList<NhaCungCapDTO> dsncc = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM nhacungcap WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String IDNCC = rs.getString("IDNCC");
                String TenNCC = rs.getString("TenNCC");
                String IDNL = rs.getString("IDNGUYENLIEU");
                String SDT = rs.getString("SDT");
                String DiaChi = rs.getString("DiaChi");
                NhaCungCapDTO ct = new NhaCungCapDTO(IDNCC, TenNCC,IDNL, SDT, DiaChi);
                dsncc.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsncc;
    }
    public void set(NhaCungCapDTO ncc) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE nhacungcap SET ";
            sql += "TenNCC='"+ncc.getTenNCC()+"', ";
            sql += "IDNGUYENLIEU='"+ncc.getIDNL()+"', ";
            sql += "SDT='"+ncc.getSDT()+"', ";
            sql += "DiaChi='"+ncc.getDiaChi()+"'";
            sql += " WHERE IDNCC='"+ncc.getIDNCC()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(NhaCungCapDTO ncc) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO nhacungcap VALUES (";
                sql += "'"+ncc.getIDNCC()+"',";
                sql += "'"+ncc.getTenNCC()+"',";
                sql += "'"+ncc.getIDNL()+"',";
                sql += "'"+ncc.getSDT()+"',";
                sql += "'"+ncc.getDiaChi()+"')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String IDNCC)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM nhacungcap WHERE IDNCC='"+IDNCC+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
