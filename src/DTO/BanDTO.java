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
public class BanDTO {
    private String IDB, Khu, TinhTrang;
    
    public BanDTO(){
        
    }
    public BanDTO(String IDB, String Khu, String TinhTrang){
        this.IDB = IDB;
        this.Khu = Khu;
        this.TinhTrang = TinhTrang;
    }

    public String getIDB() {
        return IDB;
    }

    public void setIDB(String IDB) {
        this.IDB = IDB;
    }

    public String getKhu() {
        return Khu;
    }

    public void setKhu(String Khu) {
        this.Khu = Khu;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
    
}
