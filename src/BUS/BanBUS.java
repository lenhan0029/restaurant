/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.BanDAO;
import DTO.BanDTO;
import java.util.ArrayList;

/**
 *
 * @author LE NHAN
 */
public class BanBUS {
    private ArrayList<BanDTO> dsb;
    public BanBUS(){
        
    }
    public BanDTO get(String IDB){
        for(BanDTO b : dsb){
            if(b.getIDB().equals(IDB))
            {
                return b;
            }
        }
        return null;
    }
    public void listB()
    {
        BanDAO bDAO = new BanDAO();
        dsb = new ArrayList<>();
        dsb = bDAO.list();
    }
    public void addB(BanDTO b)
    {
        dsb.add(b);
        BanDAO bDAO = new BanDAO();
        bDAO.add(b);
    }

    public void deleteB(String IDB)
    {
        for(BanDTO b : dsb )
        {
            if(b.getIDB().equals(IDB))
            {
                dsb.remove(b);
                BanDAO bDAO = new BanDAO();
                bDAO.delete(IDB);
                return;
            }
        }
    }
    public void setB(BanDTO b)
    {
        for(int i = 0 ; i < dsb.size() ; i++)
        {
            if(dsb.get(i).getIDB().equals(b.getIDB()))
            {
                dsb.set(i, b);
                BanDAO bDAO = new BanDAO();
                bDAO.set(b);
                return;
            }
        }
    }
    public boolean check(String IDB)
    {
        for(BanDTO b : dsb)
        {
            if(b.getIDB().equals(IDB))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<BanDTO> search(String IDB,String Khu,String TinhTrang)
    {
        ArrayList<BanDTO> search = new ArrayList<>();
        IDB = IDB.isEmpty()?IDB = "": IDB;
        Khu = Khu.isEmpty()?Khu = "": Khu;
        TinhTrang = TinhTrang.isEmpty()?TinhTrang = "": TinhTrang;
        for(BanDTO b : dsb)
        {
            if( b.getIDB().contains(IDB) && 
                b.getKhu().contains(Khu) &&
                b.getTinhTrang().contains(TinhTrang))
            {
                search.add(b);
            }
        }
        return search;
    }
    public ArrayList<BanDTO> getList() {
        return dsb;
    }
    
}
