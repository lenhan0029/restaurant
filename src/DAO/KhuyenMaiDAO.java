/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.KhuyenMaiDTO;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LE NHAN
 */
public class KhuyenMaiDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public KhuyenMaiDAO(){}
    
    public ArrayList<KhuyenMaiDTO> list()
    {
        ArrayList<KhuyenMaiDTO> dskm = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM khuyenmai WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String IDKM = rs.getString("IDKM");
                String TenKM = rs.getString("TenKM");
                String NgayBD = rs.getString("NgayBD");
                String NgayKT = rs.getString("NgayKT");
                KhuyenMaiDTO ct = new KhuyenMaiDTO(IDKM, TenKM, NgayBD, NgayKT);
                dskm.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dskm;
    }
    public void set(KhuyenMaiDTO km) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE khuyenmai SET ";
            sql += "TenKM'"+km.getTenKM()+"', ";
            sql += "NgayBD='"+km.getNgayBD()+"', ";
            sql += "NgayKT='"+km.getNgayKT()+"', ";
            sql += " WHERE IDKM='"+km.getIDKM()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(KhuyenMaiDTO km) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO khuyenmai VALUES (";
                sql += "'"+km.getIDKM()+"',";
                sql += "'"+km.getTenKM()+"',";
                sql += "'"+km.getNgayBD()+"',";
                sql += "'"+km.getNgayKT()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String IDKM)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE khuyenmai SET enable = 0 WHERE IDKM='"+IDKM+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
