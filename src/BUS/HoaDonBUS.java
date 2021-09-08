/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.HoaDonDAO;
import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author LE NHAN
 */
public class HoaDonBUS {
    private ArrayList<HoaDonDTO> dsHD ;
    public HoaDonBUS()
    {
        
    }
    public HoaDonBUS(int i)
    {
        list();
    }
    public void list()
    {
        HoaDonDAO hdDAO = new HoaDonDAO();
        dsHD = new ArrayList<>();
        dsHD = hdDAO.list();
    }
    public void add(HoaDonDTO hd)
    {
        dsHD.add(hd);
        HoaDonDAO hdDAO = new HoaDonDAO();
        hdDAO.add(hd);
    }

    public void delete(String IDHD)
    {
        for(HoaDonDTO hd : dsHD )
        {
            if(hd.getIDHD().equals(IDHD))
            {
                dsHD.remove(hd);
                HoaDonDAO hdDAO = new HoaDonDAO();
                hdDAO.delete(IDHD);
                return;
            }
        }
    }
    public int set(HoaDonDTO s)
    {
        for(int i = 0 ; i < dsHD.size() ; i++)
        {
            if(dsHD.get(i).getIDHD().equals(s.getIDHD()))
            {
                dsHD.set(i, s);
                HoaDonDAO hdDAO = new HoaDonDAO();
                hdDAO.set(s);
                return i;
            }
        }
        return -1;
    }
    public String remindIDHD()
    {
        int max = 0;
        String s ="";
        for(HoaDonDTO hd : dsHD)
        {
            int id = Integer.parseInt(hd.getIDHD());
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
    public ArrayList<HoaDonDTO> ListTime(Calendar from,Calendar to)
    {
        ArrayList<HoaDonDTO> list = new ArrayList<>();
        for(HoaDonDTO hd : dsHD)
        {
            Date date = Date.valueOf(hd.getNgayLap());
            Calendar time = Calendar.getInstance();
            time.setTimeInMillis(date.getTime());
            if(checkTime(from, to, time))
            {
                list.add(hd);
            }
        }
        return list;
    }
            
    public ArrayList<HoaDonDTO> search( int mm, int yyy,double max, double min,ArrayList<String> mahd)
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
        
        ArrayList<HoaDonDTO> ds = getListWidthArray(mahd);
        ArrayList<HoaDonDTO> search = new ArrayList<>();
        for(HoaDonDTO hd : ds)
        {
            Date time = Date.valueOf(hd.getNgayLap());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time.getTime());;
            
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            
            if( hd.getTongTien() >= min && hd.getTongTien() <= max 
                && (month >= mm1 && month <= mm2)
                && (year >= yyy1 && year <= yyy2))
            {
                search.add(hd);
            }
        }
        return search;
    }
    public HoaDonDTO search(String IDHD)
    {
        for(HoaDonDTO ct : dsHD)
        {
            if( ct.getIDHD().equals(IDHD))
            {
                return ct;
            }
        }
        return null;
    }
    public boolean check(String IDHD)
    {
        for(HoaDonDTO hd : dsHD)
        {
            if( hd.getIDHD().equals(IDHD))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<HoaDonDTO> getListWidthArray(ArrayList<String> s)
    {
        ArrayList<HoaDonDTO> ds = new ArrayList<>();
        if(s == null) return dsHD;
        for(HoaDonDTO hd : dsHD)
        {
            String mahd = hd.getIDHD();
            for(String a: s)
            {
                if(mahd.equals(a))
                {
                    ds.add(hd);
                }
            }
        }
        return ds;
    }
    public ArrayList<HoaDonDTO> getList() {
        return dsHD;
    }
    public void ExportExcelDatabase(){
        HoaDonDAO hdDAO = new HoaDonDAO();
        hdDAO.ExportExcelDatabase();
    }
    
    public void ImportExcelDatabase(File file){
        HoaDonDAO hdDAO = new HoaDonDAO();
        hdDAO.ImportExcelDatabase(file);
    }
}
