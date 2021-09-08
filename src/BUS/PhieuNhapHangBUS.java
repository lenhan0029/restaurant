/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.PhieuNhapHangDAO;
import DTO.PhieuNhapHangDTO;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author LE NHAN
 */
public class PhieuNhapHangBUS {
    private ArrayList<PhieuNhapHangDTO> dsPNH ;
    public PhieuNhapHangBUS()
    {
        
    }
    public PhieuNhapHangBUS(int i)
    {
        list();
    }
    public void list()
    {
        PhieuNhapHangDAO pnhDAO = new PhieuNhapHangDAO();
        dsPNH = new ArrayList<>();
        dsPNH = pnhDAO.list();
    }
    public void add(PhieuNhapHangDTO nh)
    {
        int id = 0;
        if(!dsPNH.isEmpty())
        {
            id = Integer.parseInt(dsPNH.get(dsPNH.size()-1).getIDPN());
        }
        nh.setIDPN(String.valueOf(id+1));
        dsPNH.add(nh);
        PhieuNhapHangDAO nhDAO = new PhieuNhapHangDAO();
        nhDAO.add(nh);
    }

    public void delete(String IDPN)
    {
        for(PhieuNhapHangDTO pnh : dsPNH )
        {
            if(pnh.getIDPN().equals(IDPN))
            {
                dsPNH.remove(pnh);
                PhieuNhapHangDAO hdDAO = new PhieuNhapHangDAO();
                hdDAO.delete(IDPN);
                return;
            }
        }
    }
    public int set(PhieuNhapHangDTO s)
    {
        for(int i = 0 ; i < dsPNH.size() ; i++)
        {
            if(dsPNH.get(i).getIDPN().equals(s.getIDPN()))
            {
                dsPNH.set(i, s);
                PhieuNhapHangDAO pnhDAO = new PhieuNhapHangDAO();
                pnhDAO.set(s);
                return i;
            }
        }
        return -1;
    }
    public String remindIDPNH()
    {
        int max = 0;
        String s ="";
        for(PhieuNhapHangDTO pnh : dsPNH)
        {
            int id = Integer.parseInt(pnh.getIDPN());
            if(id > max)
            {
                max = id;
            }
        }
        for(int i = 0 ; i < 3-String.valueOf(max+1).length();i++ )
        {
            s+="0";
        }
        return s+(max+1);
    }
    public boolean checkTime(Calendar from,Calendar to,Calendar time)
    {
//        System.err.print(from.getTime()+" ");
//        System.err.print(to.getTime()+" ");
//        System.err.println(time.getTime());
        if(time.after(from) && time.before(to))
        {
            return true;
        }
        return false;
    }
    public ArrayList<PhieuNhapHangDTO> ListTime(Calendar from,Calendar to)
    {
        ArrayList<PhieuNhapHangDTO> list = new ArrayList<>();
        for(PhieuNhapHangDTO pnh : dsPNH)
        {
            Date date = pnh.getNgayNhap();
            Calendar time = Calendar.getInstance();
            time.setTimeInMillis(date.getTime());
            if(checkTime(from, to, time))
            {
                list.add(pnh);
            }
        }
        return list;
    }
            
    public ArrayList<PhieuNhapHangDTO> search( int mm, int yyy,double max, double min,ArrayList<String> IDPNH)
    {
        int mm1 = 0, mm2 = 12;
        int yyy1 = 0, yyy2 = Calendar.getInstance().get(Calendar.YEAR);
        
        if(mm != -1)
        {
            mm1 = mm;
            mm2 = mm;
        }
        if(yyy != 0)
        {
            yyy1 = yyy;
            yyy2 = yyy;
        }
        
        ArrayList<PhieuNhapHangDTO> ds = getListWidthArray(IDPNH);
        ArrayList<PhieuNhapHangDTO> search = new ArrayList<>();
        for(PhieuNhapHangDTO pnh : ds)
        {
            Date time = pnh.getNgayNhap();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time.getTime());;
            
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            
            if( pnh.getTongTien() >= min && pnh.getTongTien() <= max 
                && (month >= mm1 && month <= mm2)
                && (year >= yyy1 && year <= yyy2))
            {
                search.add(pnh);
            }
        }
        return search;
    }
    public boolean check(String IDPNH)
    {
        for(PhieuNhapHangDTO pnh : dsPNH)
        {
            if( pnh.getIDPN().equals(IDPNH))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<PhieuNhapHangDTO> getListWidthArray(ArrayList<String> s)
    {
        ArrayList<PhieuNhapHangDTO> ds = new ArrayList<>();
        if(s == null) return dsPNH;
        for(PhieuNhapHangDTO pnh : dsPNH)
        {
            String IDPNH = pnh.getIDPN();
            for(String a: s)
            {
                if(IDPNH.equals(a))
                {
                    ds.add(pnh);
                }
            }
        }
        return ds;
    }
    public ArrayList<PhieuNhapHangDTO> getList() {
        return dsPNH;
    }
    public void ExportExcelDatabase(){
        PhieuNhapHangDAO pnhDAO = new PhieuNhapHangDAO();
        pnhDAO.ExportExcelDatabase();
    }
    
    public void ImportExcelDatabase(File file){
        PhieuNhapHangDAO pnhDAO = new PhieuNhapHangDAO();
        pnhDAO.ImportExcelDatabase(file);
    }
}
