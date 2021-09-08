/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietHDDAO;
import DTO.ChiTietHDDTO;
import java.util.ArrayList;

/**
 *
 * @author LE NHAN
 */
public class ChiTietHDBUS {
    private ArrayList<ChiTietHDDTO> dsChiTietHD ;
    public ChiTietHDBUS()
    {
        
    }
    public ChiTietHDBUS(int i)
    {
        list();
    }
    public void list()
    {
        ChiTietHDDAO loaiDAO = new ChiTietHDDAO();
        dsChiTietHD = new ArrayList<>();
        dsChiTietHD = loaiDAO.list();
    }
    public void add(ChiTietHDDTO loai)
    {
        dsChiTietHD.add(loai);
        ChiTietHDDAO loaiDAO = new ChiTietHDDAO();
        loaiDAO.add(loai);
    }

    public void deletemahd(String IDHD, String IDMA)
    {
        for(ChiTietHDDTO ct : dsChiTietHD )
        {
            if(ct.getIDHD().equals(IDHD) && ct.getIDMA().equals(IDMA))
            {
                dsChiTietHD.remove(ct);
                ChiTietHDDAO loaiDAO = new ChiTietHDDAO();
                loaiDAO.delete(IDHD);
                return;
            }
        }
    }
    public void delete(String IDHD)
    {
        for(ChiTietHDDTO ct : dsChiTietHD )
        {
            if(ct.getIDHD().equals(IDHD))
            {
                dsChiTietHD.remove(ct);
                ChiTietHDDAO loaiDAO = new ChiTietHDDAO();
                loaiDAO.delete(IDHD);
                return;
            }
        }
    }
    public void set(ChiTietHDDTO s)
    {
        for(int i = 0 ; i < dsChiTietHD.size() ; i++)
        {
            if(dsChiTietHD.get(i).getIDHD().equals(s.getIDHD()))
            {
                dsChiTietHD.set(i, s);
//                ChiTietHDDAO loaiDAO = new ChiTietHDDAO();
//                loaiDAO.setChiTietHD(s);
                return;
            }
        }
    }
    public ChiTietHDDTO search(String IDHD)
    {
        for(ChiTietHDDTO ct : dsChiTietHD)
        {
            if( ct.getIDHD().equals(IDHD))
            {
                return ct;
            }
        }
        return null;
    }
    public ChiTietHDDTO search(String IDHD, String IDMA)
    {
        for(ChiTietHDDTO ct : dsChiTietHD)
        {
            if( ct.getIDHD().equals(IDHD) && ct.getIDMA().equals(IDMA))
            {
                return ct;
            }
        }
        return null;
    }
    public ArrayList<String> getHD(String IDMA)
    {
        ArrayList<String> s = new ArrayList<>();
        if(IDMA.isEmpty()) return null;
        for(ChiTietHDDTO ct : dsChiTietHD)
        {
            if(ct.getIDMA().equals(IDMA))
            {
                s.add(ct.getIDHD());
            }
        }
        return s;
    }
    public ArrayList<ChiTietHDDTO> getListHD(String IDHD)
    {
        ArrayList<ChiTietHDDTO> ds = new ArrayList<>();
        for(ChiTietHDDTO ct : dsChiTietHD)
        {
            if( ct.getIDHD().equals(IDHD))
            {
                ds.add(ct);
            }
        }
        return ds; 
    }
    public ArrayList<ChiTietHDDTO> getList() {
        return dsChiTietHD;
    }
}
