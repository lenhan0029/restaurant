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
public class ChiTietKMDTO {
    private String IDKM, IDMA;
    private int GiamGia;
    
    public ChiTietKMDTO(){
        
    }
    
    public ChiTietKMDTO(String IDKM, String IDMA, int GiamGia){
        this.IDKM = IDKM;
        this.IDMA = IDMA;
        this.GiamGia = GiamGia;
    }

    public String getIDKM() {
        return IDKM;
    }

    public void setIDKM(String IDKM) {
        this.IDKM = IDKM;
    }

    public String getIDMA() {
        return IDMA;
    }

    public void setIDMA(String IDMA) {
        this.IDMA = IDMA;
    }

    public int getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(int GiamGia) {
        this.GiamGia = GiamGia;
    }
    
    
}
