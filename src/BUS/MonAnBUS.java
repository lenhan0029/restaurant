/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.MonAnDAO;
import DTO.MonAnDTO;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author LE NHAN
 */
public class MonAnBUS {
    private ArrayList<MonAnDTO> dsma ;
    public MonAnBUS()
    {
        
    }
    public void listMA()
    {
        MonAnDAO maDAO = new MonAnDAO();
        dsma = new ArrayList<>();
        dsma = maDAO.list();
    }
    public void addMA(MonAnDTO ma)
    {
        dsma.add(ma);
        MonAnDAO maDAO = new MonAnDAO();
        maDAO.add(ma);
    }

    public void deleteSP(String IDMA)
    {
        for(MonAnDTO ma : dsma )
        {
            if(ma.getIDMA().equals(IDMA))
            {
                dsma.remove(ma);
                MonAnDAO maDAO = new MonAnDAO();
                maDAO.delete(IDMA);
                return;
            }
        }
    }
    public void setMA(MonAnDTO s)
    {
        for(int i = 0 ; i < dsma.size() ; i++)
        {
            if(dsma.get(i).getIDMA().equals(s.getIDMA()))
            {
                dsma.set(i, s);
                MonAnDAO spDAO = new MonAnDAO();
                spDAO.set(s);
                return;
            }
        }
    }
    public boolean checkIDMA(String IDMA)
    {
        for(MonAnDTO ma : dsma)
        {
            if(ma.getIDMA().equals(IDMA))
            {
                return true;
            }
        }
        return false;
    }
    public MonAnDTO getMA(String IDMA)
    {
        for(MonAnDTO ma : dsma)
        {
            if(ma.getIDMA().equals(IDMA))
            {
                return ma;
            }
        }
        return null;
    }
    public boolean updateMA(String IDMA,int sl)
    {
        for(MonAnDTO ma : dsma)
         {
             if(ma.getIDMA().equals(IDMA))
             {
                
                int old = ma.getSoLuong();
                if(sl > old)
                {
                    JOptionPane.showMessageDialog(null, "Không đủ hàng");
                    return false;
                }
                old -= sl;
                ma.setSoLuong(old);
                MonAnDAO maDAO = new MonAnDAO();
                maDAO.set(ma);
                System.out.println(ma.getSoLuong());
                return true;
             }
         }
         return false;
    }
    public boolean checkSL(String IDMA , int sl)
    {
        for(MonAnDTO ma : dsma)
         {
             if(ma.getIDMA().equals(IDMA))
             {
                if(sl > ma.getSoLuong())
                {
                    JOptionPane.showMessageDialog(null, "Không đủ hàng");
                    return false;
                }
             }
         }
         return true;
    }
    public ArrayList<MonAnDTO> searchMonAn(String IDMA,String Loai,int max,int min)
    {
        ArrayList<MonAnDTO> search = new ArrayList<>();
        IDMA = IDMA.isEmpty()?IDMA = "": IDMA;
        Loai = Loai.isEmpty()?Loai = "": Loai;
        for(MonAnDTO ma : dsma)
        {
            if( ma.getIDMA().contains(IDMA) && 
                ma.getLoai().contains(Loai) &&
                ma.getDonGia() >= min && 
                ma.getDonGia() <= max)
            {
                search.add(ma);
            }
        }
        return search;
    }
    public ArrayList<MonAnDTO> getList() {
        return dsma;
    }
    
    public void ExportExcelDatabase(){
        MonAnDAO maDAO = new MonAnDAO();
        maDAO.ExportExcelDatabase();
    }
    
    public void ImportExcelDatabase(File file){
        MonAnDAO maDAO = new MonAnDAO();
        maDAO.ImportExcelDatabase(file);
    }
    public static void main(String []args){
        MonAnBUS ma = new MonAnBUS();
        ArrayList<MonAnDTO> lma = ma.getList();
        Object[] arr = lma.toArray();
        for (Object arr1 : arr) {
            System.out.println(arr1);
        }
    }
}
