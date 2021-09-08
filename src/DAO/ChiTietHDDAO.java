/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietHDDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LE NHAN
 */
public class ChiTietHDDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public ChiTietHDDAO(){}
    
    public ArrayList<ChiTietHDDTO> list()
    {
        ArrayList<ChiTietHDDTO> dscthd = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM chitiethd WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String IDHD = rs.getString("IDHD");
                String IDMA = rs.getString("IDMONAN");
                String TenMA = rs.getString("TenMA");
                int SoLuong = rs.getInt("SoLuong");
                int DonGia = rs.getInt("DonGia");
                int ThanhTien = rs.getInt("ThanhTien");
                ChiTietHDDTO ct = new ChiTietHDDTO(IDHD, IDMA,TenMA, SoLuong, DonGia, ThanhTien);
                dscthd.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dscthd;
    }
    public void set(ChiTietHDDTO cthd) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE chitiethd SET ";
            sql += "TenMA'"+cthd.getTenMA()+"', ";
            sql += "SoLuong'"+cthd.getSoLuong()+"', ";
            sql += "DonGia='"+cthd.getDonGia()+"', ";
            sql += "ThanhTien='"+cthd.getThanhTien()+"', ";
            sql += " WHERE IDHD='"+cthd.getIDHD()+"' AND IDMONAN='"+cthd.getIDMA()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(ChiTietHDDTO cthd) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO chitiethd VALUES (";
                sql += "'"+cthd.getIDHD()+"',";
                sql += "'"+cthd.getIDMA()+"',";
                sql += "'"+cthd.getTenMA()+"',";
                sql += "'"+cthd.getSoLuong()+"',";
                sql += "'"+cthd.getDonGia()+"',";
                sql += "'"+cthd.getThanhTien()+"')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void deletemahd(String IDHD, String IDMA)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM chitiethd WHERE IDHD='"+IDHD+"' AND IDMONAN='"+IDMA+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public void delete(String IDHD)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM chitiethd WHERE IDHD='"+IDHD+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
