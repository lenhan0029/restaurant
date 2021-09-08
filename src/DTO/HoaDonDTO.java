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
public class HoaDonDTO {
    private String IDHD, IDNV, IDB,IDK, IDKM;
    private String NgayLap;
    private int TongTien;
    
    public HoaDonDTO(){
        
    }

    public HoaDonDTO(String IDHD, String IDNV, String IDB, String IDK,String IDKM, String NgayLap, int TongTien){
        this.IDHD = IDHD;
        this.IDNV = IDNV;
        this.IDB = IDB;
        this.IDK = IDK;
        this.IDKM = IDKM;
        this.NgayLap = NgayLap;
        this.TongTien = TongTien;
    }

    public String getIDHD() {
        return IDHD;
    }

    public void setIDHD(String IDHD) {
        this.IDHD = IDHD;
    }

    public String getIDNV() {
        return IDNV;
    }

    public void setIDNV(String IDNV) {
        this.IDNV = IDNV;
    }

    public String getIDB() {
        return IDB;
    }

    public void setIDB(String IDB) {
        this.IDB = IDB;
    }

    public String getIDK() {
        return IDK;
    }

    public void setIDK(String IDK) {
        this.IDK = IDK;
    }

    public String getIDKM() {
        return IDKM;
    }

    public void setIDKM(String IDKM) {
        this.IDKM = IDKM;
    }

    public String getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(String NgayLap) {
        this.NgayLap = NgayLap;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }
    
    
}
