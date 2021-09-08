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
public class CheBienDTO {
    private String IDMA, IDNL;
    private int SoLuongNL;
    
    public CheBienDTO(){
        
    }
    public CheBienDTO(String IDMA, String IDNL, int SoLuongNL){
        this.IDNL = IDNL;
        this.IDMA = IDMA;
        this.SoLuongNL = SoLuongNL;
    }

    public String getIDNL() {
        return IDNL;
    }

    public void setIDNL(String IDNL) {
        this.IDNL = IDNL;
    }

    public String getIDMA() {
        return IDMA;
    }

    public void setIDMA(String IDMA) {
        this.IDMA = IDMA;
    }

    public int getSoLuongNL() {
        return SoLuongNL;
    }

    public void setSoLuongNL(int SoLuongNL) {
        this.SoLuongNL = SoLuongNL;
    }
    
    
}
