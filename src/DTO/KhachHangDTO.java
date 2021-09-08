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
public class KhachHangDTO {
    private String IDK,Ho, Ten, CapBac,SDT;
    private int trangThai;
    
    public KhachHangDTO(){
        
    }
    
    public KhachHangDTO(String IDK, String Ho, String Ten, String SDT ,String CapBac,int tt){
        this.IDK = IDK;
        this.Ho = Ho;
        this.Ten = Ten;
        this.CapBac = CapBac;
        this.SDT = SDT;
        trangThai = tt;
    }

    public String getIDK() {
        return IDK;
    }

    public void setIDK(String IDK) {
        this.IDK = IDK;
    }

    public String getHo() {
        return Ho;
    }

    public void setHo(String Ho) {
        this.Ho = Ho;
    }
    
    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public String getCapBac() {
        return CapBac;
    }

    public void setCapBac(String CapBac) {
        this.CapBac = CapBac;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
