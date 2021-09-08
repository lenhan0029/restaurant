/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author LE NHAN
 */
public class ChiTietHDDTO {
    private String IDHD, IDMA,TenMA;
    private int SoLuong, DonGia, ThanhTien;
    
    public ChiTietHDDTO(){
        
    }
    public ChiTietHDDTO(String IDHD, String IDMA,String TenMA, int SoLuong, int DonGia, int ThanhTien){
        this.IDHD = IDHD;
        this.IDMA = IDMA;
        this.TenMA = TenMA;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.ThanhTien = ThanhTien;
    }

    public String getIDHD() {
        return IDHD;
    }

    public void setIDHD(String IDHD) {
        this.IDHD = IDHD;
    }

    public String getIDMA() {
        return IDMA;
    }

    public void setIDMA(String IDMA) {
        this.IDMA = IDMA;
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

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public String getTenMA() {
        return TenMA;
    }

    public void setTenMA(String TenMA) {
        this.TenMA = TenMA;
    }
    
    
}
