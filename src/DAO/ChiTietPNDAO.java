/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietPNDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LE NHAN
 */
public class ChiTietPNDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public ChiTietPNDAO(){}
    
    public ArrayList<ChiTietPNDTO> list()
    {
        ArrayList<ChiTietPNDTO> dsctpn = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM chitietphieunhap WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String IDPN = rs.getString("IDPHIEUNHAP");
                String IDNL = rs.getString("IDNGUYENLIEU");
                int SoLuong = rs.getInt("SoLuong");
                int DonGia = rs.getInt("DonGia");
                ChiTietPNDTO ct = new ChiTietPNDTO(IDPN, IDNL, SoLuong, DonGia);
                dsctpn.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsctpn;
    }
    public void set(ChiTietPNDTO ctpn) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE chitiethd SET ";
            sql += "SoLuong'"+ctpn.getSoLuong()+"', ";
            sql += "DonGia='"+ctpn.getDonGia()+"', ";
            sql += " WHERE IDPHIEUNHAP='"+ctpn.getIDPN()+"' AND IDNGUYENLIEU='"+ctpn.getIDNL()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(ChiTietPNDTO ctpn) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO chitietphieunhap VALUES (";
                sql += "'"+ctpn.getIDPN()+"',";
                sql += "'"+ctpn.getIDNL()+"',";
                sql += "'"+ctpn.getSoLuong()+"',";
                sql += "'"+ctpn.getDonGia()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String IDPN, String IDNL)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE chitiethd SET enable = 0 WHERE IDPHIEUNHAP='"+IDPN+"' AND IDNGUYENLIEU='"+IDNL+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
