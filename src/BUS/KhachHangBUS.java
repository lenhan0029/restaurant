/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author LE NHAN
 */
public class KhachHangBUS {
    private ArrayList<KhachHangDTO> dskh ;
    public KhachHangBUS(int i1)
    {
        list();
    }
    
    public KhachHangBUS()
    {
        
    }
    public KhachHangDTO get(String IDK)
    {
        for(KhachHangDTO kh : dskh )
        {
            if(kh.getIDK().equals(IDK))
            {
                return kh;
            }
        }
        return null;
    }
    public void list()
    {
        KhachHangDAO khDAO = new KhachHangDAO();
        dskh = new ArrayList<>();
        dskh = khDAO.list();
    }
    public void add(KhachHangDTO kh)
    {
        dskh.add(kh);
        KhachHangDAO khDAO = new KhachHangDAO();
        khDAO.add(kh);
    }

    public void delete(String IDK)
    {
        for(KhachHangDTO kh : dskh )
        {
            if(kh.getIDK().equals(IDK))
            {
                dskh.remove(kh);
                KhachHangDAO khDAO = new KhachHangDAO();
                khDAO.delete(IDK);
                return;
            }
        }
    }
    public void set(KhachHangDTO s)
    {
        for(int i = 0 ; i < dskh.size() ; i++)
        {
            if(dskh.get(i).getIDK().equals(s.getIDK()))
            {
                dskh.set(i, s);
                KhachHangDAO khDAO = new KhachHangDAO();
                khDAO.set(s);
                return;
            }
        }
    }
    public boolean check(String IDK)
    {
        for(KhachHangDTO kh : dskh)
        {
            if(kh.getIDK().equals(IDK))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<KhachHangDTO> search(String searchtxt,int Theo,String Cap)
    {
        String s =searchtxt;
        ArrayList<KhachHangDTO> search = new ArrayList<>();  
        if(Cap != "Cấp bậc") {   
            switch(Theo){
                case 0:
                    for(KhachHangDTO kh : dskh)
                    {
                        if(kh.getHo().contains(s) && kh.getCapBac().contains(Cap))
                        {
                            search.add(kh);
                        }
                    }
                break;
                case 1:
                    for(KhachHangDTO kh : dskh)
                    {
                        if(kh.getTen().contains(s) && kh.getCapBac().contains(Cap))
                        {
                            search.add(kh);
                        }
                    }
                break;
                case 2:
                    for(KhachHangDTO kh : dskh)
                    {
                        if(kh.getSDT().contains(s) && kh.getCapBac().contains(Cap))
                        {
                            search.add(kh);
                        }
                    }
                break;
            };
        }
        else{
            switch(Theo){
                case 0:
                    for(KhachHangDTO kh : dskh)
                    {
                        if(kh.getHo().contains(s))
                        {
                            search.add(kh);
                        }
                    }
                break;
                case 1:
                    for(KhachHangDTO kh : dskh)
                    {
                        if(kh.getTen().contains(s))
                        {
                            search.add(kh);
                        }
                    }
                break;
                case 2:
                    for(KhachHangDTO kh : dskh)
                    {
                        if(kh.getSDT().contains(s))
                        {
                            search.add(kh);
                        }
                    }
                break;
            };
        }
        return search;
    }
    public ArrayList<KhachHangDTO> getList() {
        return dskh;
    }
    public boolean checkNull(String MaKH,String HoKH,String TenKH,String SDT){
        boolean flag=true;
        if(check(MaKH)){
            JOptionPane.showMessageDialog(null, "Khách hàng đã tồn tại !!!");
            flag=false;
        }
        if(HoKH.isEmpty() || checkNumExist(HoKH)){
            JOptionPane.showMessageDialog(null, "Họ khách hàng không hợp lệ");
            flag=false;
        }
        if(TenKH.isEmpty() || checkNumExist(TenKH)){
            JOptionPane.showMessageDialog(null, "Tên khách hàng không hợp lệ");
            flag=false;
        }
        if(SDT.isEmpty() || !checkNumExist(SDT) || SDT.length()<10 || SDT.length()>11){
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");
            flag=false;
        }
        return flag;
    }
    public boolean checkNull1(String HoKH,String TenKH,String SDT){
        boolean flag=true;
        if(HoKH.isEmpty() || checkNumExist(HoKH)){
            JOptionPane.showMessageDialog(null, "Họ khách hàng không hợp lệ");
            flag=false;
        }
        if(TenKH.isEmpty() || checkNumExist(TenKH)){
            JOptionPane.showMessageDialog(null, "Tên khách hàng không hợp lệ");
            flag=false;
        }
        if(SDT.isEmpty() || !checkNumExist(SDT) || SDT.length()<10 || SDT.length()>11){
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");
            flag=false;
        }
        return flag;
    }
    public boolean checkNumExist(String s){
        char[] chars = s.toCharArray();
        for(char c : chars){
           if(Character.isDigit(c)){
              return true;
           }
        }
        return false; 
    }
}
