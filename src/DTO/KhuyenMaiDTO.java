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
public class KhuyenMaiDTO {
    private String IDKM, TenKM;
    private String NgayBD, NgayKT;
    
    public KhuyenMaiDTO(){
        
    }
    
    public KhuyenMaiDTO(String IDKM, String TenKM, String NgayBD, String NgayKT){
        this.IDKM = IDKM;
        this.TenKM = TenKM;
        this.NgayBD = NgayBD;
        this.NgayKT = NgayKT;
    }

    public String getIDKM() {
        return IDKM;
    }

    public void setIDKM(String IDKM) {
        this.IDKM = IDKM;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String TenKM) {
        this.TenKM = TenKM;
    }

    public String getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(String NgayBD) {
        this.NgayBD = NgayBD;
    }

    public String getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(String NgayKT) {
        this.NgayKT = NgayKT;
    }
    
    
}
