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
public class NhanVienDTO {
    private String IDNV, Ho, Ten, DiaChi, ChucVu,img,SDT;
    private int Luong,trangThai;
    
    public NhanVienDTO(){
        
    }
    
    public NhanVienDTO(String IDNV, String Ho, String Ten, String SDT, String DiaChi, String ChucVu, int Luong,String img,int trangThai){
        this.IDNV = IDNV;
        this.Ho = Ho;
        this.Ten = Ten;
        this.DiaChi = DiaChi;
        this.ChucVu = ChucVu;
        this.SDT = SDT;
        this.Luong = Luong;
        this.img = img;
        this.trangThai = trangThai;
    }

    public String getIDNV() {
        return IDNV;
    }

    public void setIDNV(String IDNV) {
        this.IDNV = IDNV;
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

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getLuong() {
        return Luong;
    }

    public void setLuong(int Luong) {
        this.Luong = Luong;
    }
    
    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }
    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getTrangThai() {
        return trangThai;
    }
}
