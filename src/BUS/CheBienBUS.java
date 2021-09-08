/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.CheBienDAO;
import DTO.CheBienDTO;
import java.util.ArrayList;

/**
 *
 * @author LE NHAN
 */
public class CheBienBUS {
    private ArrayList<CheBienDTO> dscb;
    public CheBienBUS(){
        
    }
    public void list(){
        CheBienDAO cbDAO = new CheBienDAO();
        dscb = new ArrayList<>();
        dscb = cbDAO.list();
    }
    public void add(CheBienDTO loai)
    {
        dscb.add(loai);
        CheBienDAO loaiDAO = new CheBienDAO();
        loaiDAO.add(loai);
    }

    public void delete(String IDMA, String IDNL)
    {
        for(CheBienDTO ct : dscb )
        {
            if(ct.getIDMA().equals(IDMA))
            {
                dscb.remove(ct);
                CheBienDAO cbDAO = new CheBienDAO();
                cbDAO.delete(IDMA, IDNL);
                return;
            }
        }
    }
    
    public void set(CheBienDTO s)
    {
        for(int i = 0 ; i < dscb.size() ; i++)
        {
            if(dscb.get(i).getIDMA().equals(s.getIDMA()) && dscb.get(i).getIDNL().equals(s.getIDNL()))
            {
                dscb.set(i, s);
//                CheBienDAO cbDAO = new CheBienDAO();
//                cbDAO.setChiTietHD(s);
                return;
            }
        }
    }
    public CheBienDTO search(String IDMA)
    {
        for(CheBienDTO ct : dscb)
        {
            if( ct.getIDMA().equals(IDMA))
            {
                return ct;
            }
        }
        return null;
    }
    public CheBienDTO search(String IDMA, String IDNL)
    {
        for(CheBienDTO ct : dscb)
        {
            if( ct.getIDMA().equals(IDMA) && ct.getIDNL().equals(IDNL))
            {
                return ct;
            }
        }
        return null;
    }
    public ArrayList<String> getNL(String IDNL)
    {
        ArrayList<String> s = new ArrayList<>();
        if(IDNL.isEmpty()) return null;
        for(CheBienDTO ct : dscb)
        {
            if(ct.getIDNL().equals(IDNL))
            {
                s.add(ct.getIDMA());
            }
        }
        return s;
    }
    public ArrayList<CheBienDTO> getListMA(String IDMA)
    {
        ArrayList<CheBienDTO> ds = new ArrayList<>();
        for(CheBienDTO ct : dscb)
        {
            if( ct.getIDMA().equals(IDMA))
            {
                ds.add(ct);
            }
        }
        return ds; 
    }
    public ArrayList<CheBienDTO> getList() {
        return dscb;
    }
}
