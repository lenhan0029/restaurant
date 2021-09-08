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
public class NguyenLieuDTO {
    private String IDNL, TenNL;
    private int SoLuong, DonGia;
    private int Slgnlchebien;
    
    public NguyenLieuDTO(){
        
    }
    
    public NguyenLieuDTO(String IDNL, String TenNL, int SoLuong, int DonGia){
        this.IDNL = IDNL;
        this.TenNL = TenNL;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
    }
    public NguyenLieuDTO(String IDNL, String TenNL, int SoLuong){
        this.IDNL = IDNL;
        this.TenNL = TenNL;
        this.Slgnlchebien = SoLuong;
    }
    public int getSlgnlchebien() {
        return Slgnlchebien;
    }

    public void setSlgnlchebien(int Slgnlchebien) {
        this.Slgnlchebien = Slgnlchebien;
    }
    public String getIDNL() {
        return IDNL;
    }

    public void setIDNL(String IDNL) {
        this.IDNL = IDNL;
    }

    public String getTenNL() {
        return TenNL;
    }

    public void setTenNL(String TenNL) {
        this.TenNL = TenNL;
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
