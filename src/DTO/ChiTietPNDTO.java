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
public class ChiTietPNDTO {
    private String IDPN, IDNL;
    private int SoLuong, DonGia;
    
    public ChiTietPNDTO(){
        
    }
    
    public ChiTietPNDTO(String IDPN, String IDNL, int SoLuong, int DonGia){
        this.IDPN = IDPN;
        this.IDNL = IDNL;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
    }

    public String getIDPN() {
        return IDPN;
    }

    public void setIDPN(String IDPN) {
        this.IDPN = IDPN;
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
