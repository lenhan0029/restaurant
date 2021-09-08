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
public class PhieuNhapHangDTO {
    private String IDPN, IDNV, IDNCC, IDNL;
    private Date NgayNhap;
    private int SoLuong,DonGia,TongTien;
    
    public PhieuNhapHangDTO(){
        
    }
    
    public PhieuNhapHangDTO(String IDPN, String IDNV, String IDNCC,String IDNL, Date NgayNhap ,int SoLuong, int DonGia, int TongTien){
        this.IDPN =IDPN;
        this.IDNV = IDNV;
        this.IDNCC = IDNCC;
        this.IDNL = IDNL;
        this.NgayNhap = NgayNhap;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.TongTien = TongTien;
    }

    public String getIDPN() {
        return IDPN;
    }

    public void setIDPN(String IDPN) {
        this.IDPN = IDPN;
    }

    public String getIDNV() {
        return IDNV;
    }

    public void setIDNV(String IDNV) {
        this.IDNV = IDNV;
    }

    public String getIDNCC() {
        return IDNCC;
    }

    public void setIDNCC(String IDNCC) {
        this.IDNCC = IDNCC;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

    public String getIDNL() {
        return IDNL;
    }

    public void setIDNL(String IDNL) {
        this.IDNL = IDNL;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int DonGia) {
        this.DonGia = DonGia;
    }
    
    
}
