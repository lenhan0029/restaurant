/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhanVienDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LE NHAN
 */
public class NhanVienDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public NhanVienDAO(){}
    
    public ArrayList<NhanVienDTO> list()
    {
        ArrayList<NhanVienDTO> dsnv = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM nhanvien WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String IDNV = rs.getString("IDNHANVIEN");
                String Ho = rs.getString("Ho");
                String Ten = rs.getString("Ten");
                String SDT = rs.getString("SĐT");
                String DiaChi = rs.getString("DiaChi");
                String ChucVu = rs.getString("ChucVu");
                int Luong = rs.getInt("Luong");
                int trangthai = rs.getInt("trangThai");
                String img = rs.getString("img");
                NhanVienDTO ct = new NhanVienDTO(IDNV, Ho, Ten, SDT, DiaChi, ChucVu, Luong,img,trangthai);
                dsnv.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsnv;
    }
    public void set(NhanVienDTO nv) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE nhanvien SET ";
            sql += "Ho='"+nv.getHo()+"', ";
            sql += "Ten='"+nv.getTen()+"', ";
            sql += "SĐT='"+nv.getSDT()+"', ";
            sql += "DiaChi='"+nv.getDiaChi()+"', ";
            sql += "ChucVu='"+nv.getChucVu()+"', ";
            sql += "Luong='"+nv.getLuong()+"', ";
            sql += "img='"+nv.getImg()+"', ";
            sql += " WHERE IDNHANVIEN='"+nv.getIDNV()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(NhanVienDTO nv) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO nhanvien VALUES (";
                sql += "'"+nv.getIDNV()+"',";
                sql += "'"+nv.getHo()+"',";
                sql += "'"+nv.getTen()+"',";
                sql += "'"+nv.getSDT()+"',";
                sql += "'"+nv.getDiaChi()+"',";
                sql += "'"+nv.getChucVu()+"',";
                sql += "'"+nv.getLuong()+"',";
                sql += "'"+nv.getImg()+"',";
                sql += "'0')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String IDNV)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE nhanvien SET trangThai = 1 WHERE IDNHANVIEN='"+IDNV+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
