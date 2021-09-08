/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author LE NHAN
 */
public class TaiKhoanDTO {
    private String IDTK, IDNV, TenDN, MatKhau, Quyen;
    private Date NgayDN;
    private int GioVao, GioRa, trangThai;
    
    public TaiKhoanDTO(){
        
    }
    
    public TaiKhoanDTO(String IDTK, String IDNV, String TenDN, String MatKhau, String Quyen, Date NgayDN,int trangThai/*, int GioVao, int GioRa*/){
        this.IDTK = IDTK;
        this.IDNV = IDNV;
        this.TenDN = TenDN;
        this.MatKhau = MatKhau;
        this.Quyen = Quyen;
        this.NgayDN = NgayDN;
        this.trangThai = trangThai;
        /*this.GioVao = GioVao;
        this.GioRa = GioRa;*/
    }

    public String getIDTK() {
        return IDTK;
    }

    public void setIDTK(String IDTK) {
        this.IDTK = IDTK;
    }

    public String getIDNV() {
        return IDNV;
    }

    public void setIDNV(String IDNV) {
        this.IDNV = IDNV;
    }

    public String getTenDN() {
        return TenDN;
    }

    public void setTenDN(String TenDN) {
        this.TenDN = TenDN;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getQuyen() {
        return Quyen;
    }

    public void setQuyen(String Quyen) {
        this.Quyen = Quyen;
    }

    public Date getNgayDN() {
        return NgayDN;
    }

    public void setNgayDN(Date NgayDN) {
        this.NgayDN = NgayDN;
    }

    /*public int getGioVao() {
        return GioVao;
    }

    public void setGioVao(int GioVao) {
        this.GioVao = GioVao;
    }

    public int getGioRa() {
        return GioRa;
    }

    public void setGioRa(int GioRa) {
        this.GioRa = GioRa;
    }*/

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
