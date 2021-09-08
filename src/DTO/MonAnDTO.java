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
public class MonAnDTO {
    
    private String IDMA, TenMA, DVT, Loai, Img;
    private int SoLuong, DonGia;
    
    public MonAnDTO(){
        
    }
    
    public MonAnDTO(String IDMA, String TenMA, String DVT, String Loai, String Img, int SoLuong, int DonGia){
        this.IDMA = IDMA;
        this.TenMA = TenMA;
        this.DVT = DVT;
        this.Loai = Loai;
        this.Img = Img;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
    }

    public String getIDMA() {
        return IDMA;
    }

    public void setIDMA(String IDMA) {
        this.IDMA = IDMA;
    }

    public String getTenMA() {
        return TenMA;
    }

    public void setTenMA(String TenMA) {
        this.TenMA = TenMA;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String Loai) {
        this.Loai = Loai;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
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
