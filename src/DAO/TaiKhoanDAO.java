/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TaiKhoanDTO;
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
public class TaiKhoanDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public TaiKhoanDAO() {
    }

    public ArrayList<TaiKhoanDTO> list() {
        ArrayList<TaiKhoanDTO> dstk = new ArrayList<>();
        try {

            String sql = "SELECT * FROM taikhoan WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String IDTK = rs.getString("IDTAIKHOAN");
                String IDNV = rs.getString("IDNHANVIEN");
                String TenDN = rs.getString("TenDN");
                String MatKhau = rs.getString("MatKhau");
                String Quyen = rs.getString("Quyen");
                Date NgayDN = rs.getDate("NgayDN");
                int trangThai = rs.getInt("trangThai");
                /*int GioVao = rs.getInt("GioVao");
                int GioRa = rs.getInt("GioRa");*/
                TaiKhoanDTO ct = new TaiKhoanDTO(IDTK, IDNV, TenDN, MatKhau, Quyen, NgayDN, trangThai/*, GioVao, GioRa*/);
                dstk.add(ct);
            }
            rs.close();
            mySQL.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dstk;
    }

    public void set(TaiKhoanDTO tk) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE taikhoan SET ";
        sql += "IDNHANVIEN='" + tk.getIDNV() + "', ";
        sql += "TenDN='" + tk.getTenDN() + "', ";
        sql += "MatKhau='" + tk.getMatKhau() + "', ";
        sql += "NgayDN='" + tk.getNgayDN() + "', ";
        /*sql += "GioVao'"+tk.getGioVao()+"', ";
            sql += "GioRa'"+tk.getGioRa()+"', ";*/
        sql += "Quyen='" + tk.getQuyen() + "',";
        sql += "trangThai='" + tk.getTrangThai() + "'";
        sql += " WHERE IDTAIKHOAN='" + tk.getIDTK() + "'";
        System.out.println(sql);

        mySQL.executeUpdate(sql);
    }

    public void add(TaiKhoanDTO tk) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO taikhoan(IDTAIKHOAN, IDNHANVIEN, TenDN, MatKhau, Quyen, NgayDN, trangThai) VALUES (";
        sql += "'" + tk.getIDTK() + "',";
        sql += "'" + tk.getIDNV() + "',";
        sql += "'" + tk.getTenDN() + "',";
        sql += "'" + tk.getMatKhau() + "',";
        sql += "'" + tk.getQuyen() + "',";
        sql += "'" + tk.getNgayDN() + "',";
        sql += "'" + tk.getTrangThai() + "')";
        /*sql += "'"+tk.getGioVao()+"',";
                sql += "'"+tk.getGioRa()+"',";*/
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void deleteTK(String IDTK) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM taikhoan WHERE IDTAIKHOAN='" + IDTK + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
   

    public boolean checkUser(String username) {
        boolean kt = false;
        ResultSet rs = null;
        MySQLConnect mySQL = new MySQLConnect();
        try {
            String sql = "SELECT * FROM taikhoan WHERE TenDN='" + username + "'";
            System.out.println(sql);
            rs = mySQL.executeQuery(sql);
            kt = rs.next();
            rs.close();
            mySQL.disConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kt;
    }
        public boolean checkMaNV(String id) {
        boolean kt = false;
        ResultSet rs = null;
        MySQLConnect mySQL = new MySQLConnect();
        try {
            String sql = "SELECT * FROM taikhoan WHERE IDNHANVIEN='" + id + "'";
            System.out.println(sql);
            rs = mySQL.executeQuery(sql);
            kt = rs.next();
            rs.close();
            mySQL.disConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kt;
    }
    
}
