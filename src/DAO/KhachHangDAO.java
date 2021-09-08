/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.KhachHangDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LE NHAN
 */
public class KhachHangDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public KhachHangDAO(){}
    
    public ArrayList<KhachHangDTO> list()
    {
        ArrayList<KhachHangDTO> dskh = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM khachhang WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String IDK = rs.getString("IDKHACH");
                String Ho = rs.getString("Ho");
                String Ten = rs.getString("Ten");
                String CapBac = rs.getString("CapBac");
                String SDT = rs.getString("SĐT");
                int trangThai = rs.getInt("trangThai");
                KhachHangDTO ct = new KhachHangDTO(IDK, Ho, Ten, SDT, CapBac,trangThai);
                dskh.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dskh;
    }
    public void set(KhachHangDTO kh) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE khachhang SET ";
            sql += "Ho'"+kh.getHo()+"', ";
            sql += "Ten'"+kh.getTen()+"', ";
            sql += "CapBac='"+kh.getCapBac()+"', ";
            sql += "SĐT='"+kh.getSDT()+"', ";
            sql += " WHERE IDKHACH='"+kh.getIDK()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(KhachHangDTO kh) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO khachhang VALUES (";
                sql += "'"+kh.getIDK()+"',";
                sql += "'"+kh.getHo()+"',";
                sql += "'"+kh.getTen()+"',";
                sql += "'"+kh.getSDT()+"',";
                sql += "'"+kh.getCapBac()+"',";
                sql += "'0')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String IDKH)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE khachhang SET trangThai = 1 WHERE IDKHACH='"+IDKH+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
