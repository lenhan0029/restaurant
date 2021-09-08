/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietPNDAO;
import DTO.ChiTietPNDTO;
import java.util.ArrayList;

/**
 *
 * @author LE NHAN
 */
public class ChiTietPNBUS {
    private ArrayList<ChiTietPNDTO> dsChiTietPN ;
    public ChiTietPNBUS()
    {
        
    }
    public ChiTietPNBUS(int i)
    {
        list();
    }
    public void list()
    {
        ChiTietPNDAO loaiDAO = new ChiTietPNDAO();
        dsChiTietPN = new ArrayList<>();
        dsChiTietPN = loaiDAO.list();
    }
    public void add(ChiTietPNDTO loai)
    {
        dsChiTietPN.add(loai);
        ChiTietPNDAO loaiDAO = new ChiTietPNDAO();
        loaiDAO.add(loai);
    }

    public void delete(String IDPN, String IDNL)
    {
        for(ChiTietPNDTO ct : dsChiTietPN )
        {
            if(ct.getIDPN().equals(IDPN) && ct.getIDNL().equals(IDNL))
            {
                dsChiTietPN.remove(ct);
                ChiTietPNDAO loaiDAO = new ChiTietPNDAO();
                loaiDAO.delete(IDPN,IDNL);
                return;
            }
        }
    }
    
    public void set(ChiTietPNDTO s)
    {
        for(int i = 0 ; i < dsChiTietPN.size() ; i++)
        {
            if(dsChiTietPN.get(i).getIDPN().equals(s.getIDNL()))
            {
                dsChiTietPN.set(i, s);
//                ChiTietPNDAO loaiDAO = new ChiTietPNDAO();
//                loaiDAO.setChiTietPN(s);
                return;
            }
        }
    }
    public ChiTietPNDTO search(String IDPN)
    {
        for(ChiTietPNDTO ct : dsChiTietPN)
        {
            if( ct.getIDPN().equals(IDPN))
            {
                return ct;
            }
        }
        return null;
    }
    public ChiTietPNDTO search(String IDPN, String IDNL)
    {
        for(ChiTietPNDTO ct : dsChiTietPN)
        {
            if( ct.getIDPN().equals(IDPN) && ct.getIDNL().equals(IDNL))
            {
                return ct;
            }
        }
        return null;
    }
    public ArrayList<String> getPN(String IDNL)
    {
        ArrayList<String> s = new ArrayList<>();
        if(IDNL.isEmpty()) return null;
        for(ChiTietPNDTO ct : dsChiTietPN)
        {
            if(ct.getIDNL().equals(IDNL))
            {
                s.add(ct.getIDPN());
            }
        }
        return s;
    }
    public ArrayList<ChiTietPNDTO> getListHD(String IDPN)
    {
        ArrayList<ChiTietPNDTO> ds = new ArrayList<>();
        for(ChiTietPNDTO ct : dsChiTietPN)
        {
            if( ct.getIDPN().equals(IDPN))
            {
                ds.add(ct);
            }
        }
        return ds; 
    }
    public ArrayList<ChiTietPNDTO> getList() {
        return dsChiTietPN;
    }
}

