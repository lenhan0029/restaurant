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
public class NhaCungCapDTO {
    private String IDNCC, TenNCC, DiaChi,IDNL, SDT;
    
    public NhaCungCapDTO(){
        
    }
    
    public NhaCungCapDTO(String IDNCC, String TenNCC,String IDNL, String SDT, String DiaChi){
        this.IDNCC = IDNCC;
        this.TenNCC = TenNCC;
        this.IDNL = IDNL;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
    }

    public String getIDNCC() {
        return IDNCC;
    }

    public void setIDNCC(String IDNCC) {
        this.IDNCC = IDNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getIDNL() {
        return IDNL;
    }

    public void setIDNL(String IDNL) {
        this.IDNL = IDNL;
    }
    
    
}
