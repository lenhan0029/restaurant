/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NguyenLieuDAO;
import DTO.NguyenLieuDTO;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author LE NHAN
 */
public class NguyenLieuBUS {
    private ArrayList<NguyenLieuDTO> dsnl ;
    public NguyenLieuBUS()
    {
        
    }
    public void listNL()
    {
        NguyenLieuDAO nlDAO = new NguyenLieuDAO();
        dsnl = new ArrayList<>();
        dsnl = nlDAO.list();
    }
    public void listNLchebien(int IDMA)
    {
        NguyenLieuDAO nlDAO = new NguyenLieuDAO();
        dsnl = new ArrayList<>();
        dsnl = nlDAO.congthucmonan(IDMA);
    }
    public void addNL(NguyenLieuDTO nl)
    {
        dsnl.add(nl);
        NguyenLieuDAO nlDAO = new NguyenLieuDAO();
        nlDAO.add(nl);
    }
    public String remindIDNL()
    {
        int max = 0;
        for(NguyenLieuDTO nl : dsnl)
        {
            int id = Integer.parseInt(nl.getIDNL());
            if(id > max)
            {
                max = id;
            }
        }
        return (max+1)+"";
    }
    public void deleteNL(String IDNL)
    {
        for(NguyenLieuDTO nl : dsnl )
        {
            if(nl.getIDNL().equals(IDNL))
            {
                dsnl.remove(nl);
                NguyenLieuDAO nlDAO = new NguyenLieuDAO();
                nlDAO.delete(IDNL);
                return;
            }
        }
    }
    public void setNL(NguyenLieuDTO s)
    {
        for(int i = 0 ; i < dsnl.size() ; i++)
        {
            if(dsnl.get(i).getIDNL().equals(s.getIDNL()))
            {
                dsnl.set(i, s);
                NguyenLieuDAO nlDAO = new NguyenLieuDAO();
                nlDAO.set(s);
                return;
            }
        }
    }
    public boolean checkIDNL(String IDNL)
    {
        for(NguyenLieuDTO nl : dsnl)
        {
            if(nl.getIDNL().equals(IDNL))
            {
                return true;
            }
        }
        return false;
    }
    public NguyenLieuDTO getNL(String IDNL)
    {
        for(NguyenLieuDTO nl : dsnl)
        {
            if(nl.getIDNL().equals(IDNL))
            {
                return nl;
            }
        }
        return null;
    }
    public boolean updateNL(String IDNL,int sl)
    {
        for(NguyenLieuDTO nl : dsnl)
         {
             if(nl.getIDNL().equals(IDNL))
             {
                
                int old = nl.getSoLuong();
                if(sl > old)
                {
                    JOptionPane.showMessageDialog(null, "Không đủ hàng");
                    return false;
                }
                old -= sl;
                nl.setSoLuong(old);
                NguyenLieuDAO nlDAO = new NguyenLieuDAO();
                nlDAO.set(nl);
                System.out.println(nl.getSoLuong());
                return true;
             }
         }
         return false;
    }
    public boolean checkSL(String IDNL , int sl)
    {
        for(NguyenLieuDTO nl : dsnl)
         {
             if(nl.getIDNL().equals(IDNL))
             {
                if(sl > nl.getSoLuong())
                {
                    JOptionPane.showMessageDialog(null, "Không đủ hàng");
                    return false;
                }
             }
         }
         return true;
    }
    public ArrayList<NguyenLieuDTO> searchNL(String IDNL,String TenNL)
    {
        ArrayList<NguyenLieuDTO> search = new ArrayList<>();
        IDNL = IDNL.isEmpty()?IDNL = "": IDNL;
        TenNL = TenNL.isEmpty()?TenNL = "": TenNL;
        for(NguyenLieuDTO nl : dsnl)
        {
            if( nl.getIDNL().contains(IDNL) && 
                nl.getTenNL().contains(TenNL))
            {
                search.add(nl);
            }
        }
        return search;
    }
    public ArrayList<NguyenLieuDTO> getList() {
        return dsnl;
    }
    
    public void ExportExcelDatabase(){
        NguyenLieuDAO nlDAO = new NguyenLieuDAO();
        nlDAO.ExportExcelDatabase();
    }
    
    public void ImportExcelDatabase(File file){
        NguyenLieuDAO nlDAO = new NguyenLieuDAO();
        nlDAO.ImportExcelDatabase(file);
    }
}
