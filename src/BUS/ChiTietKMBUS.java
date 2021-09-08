/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietKMDAO;
import DTO.ChiTietKMDTO;
import java.util.ArrayList;

/**
 *
 * @author LE NHAN
 */
public class ChiTietKMBUS {
    private ArrayList<ChiTietKMDTO> dsChiTietKM ;
    public ChiTietKMBUS()
    {
        
    }
    public ChiTietKMBUS(int i)
    {
        list();
    }
    public void list()
    {
        ChiTietKMDAO loaiDAO = new ChiTietKMDAO();
        dsChiTietKM = new ArrayList<>();
        dsChiTietKM = loaiDAO.list();
    }
    public void add(ChiTietKMDTO loai)
    {
        dsChiTietKM.add(loai);
        ChiTietKMDAO loaiDAO = new ChiTietKMDAO();
        loaiDAO.add(loai);
    }

    public void delete(String IDKM, String IDMA)
    {
        for(ChiTietKMDTO ct : dsChiTietKM )
        {
            if(ct.getIDKM().equals(IDKM) && ct.getIDMA().equals(IDMA))
            {
                dsChiTietKM.remove(ct);
                ChiTietKMDAO loaiDAO = new ChiTietKMDAO();
                loaiDAO.delete(IDKM,IDMA);
                return;
            }
        }
    }
    
    public void set(ChiTietKMDTO s)
    {
        for(int i = 0 ; i < dsChiTietKM.size() ; i++)
        {
            if(dsChiTietKM.get(i).getIDKM().equals(s.getIDKM()))
            {
                dsChiTietKM.set(i, s);
//                ChiTietKMDAO loaiDAO = new ChiTietKMDAO();
//                loaiDAO.setChiTietKM(s);
                return;
            }
        }
    }
    public ChiTietKMDTO search(String IDKM)
    {
        for(ChiTietKMDTO ct : dsChiTietKM)
        {
            if( ct.getIDKM().equals(IDKM))
            {
                return ct;
            }
        }
        return null;
    }
    public ChiTietKMDTO search(String IDKM, String IDMA)
    {
        for(ChiTietKMDTO ct : dsChiTietKM)
        {
            if( ct.getIDKM().equals(IDKM) && ct.getIDMA().equals(IDMA))
            {
                return ct;
            }
        }
        return null;
    }
    public ArrayList<String> getKM(String IDMA)
    {
        ArrayList<String> s = new ArrayList<>();
        if(IDMA.isEmpty()) return null;
        for(ChiTietKMDTO ct : dsChiTietKM)
        {
            if(ct.getIDMA().equals(IDMA))
            {
                s.add(ct.getIDKM());
            }
        }
        return s;
    }
    public ArrayList<ChiTietKMDTO> getListHD(String IDHD)
    {
        ArrayList<ChiTietKMDTO> ds = new ArrayList<>();
        for(ChiTietKMDTO ct : dsChiTietKM)
        {
            if( ct.getIDKM().equals(IDHD))
            {
                ds.add(ct);
            }
        }
        return ds; 
    }
    public ArrayList<ChiTietKMDTO> getList() {
        return dsChiTietKM;
    }
}

