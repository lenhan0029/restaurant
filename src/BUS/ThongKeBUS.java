/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ThongKeDAO;
import DTO.HoaDonDTO;
import DTO.PhieuNhapHangDTO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Shadow
 */
public class ThongKeBUS {
    private HoaDonBUS hdBUS = new HoaDonBUS();
    private PhieuNhapHangBUS nhBUS = new PhieuNhapHangBUS();
    public ThongKeBUS()
    {
        hdBUS.list();
        nhBUS.list();
    }
    public String StatisticSP(String Id,Calendar from,Calendar to)
    {
        ArrayList<HoaDonDTO> dsHD = new ArrayList<> ();
        dsHD = hdBUS.ListTime(from, to);
        
        ArrayList<PhieuNhapHangDTO> dsNhap = new ArrayList<> ();
        dsNhap = nhBUS.ListTime(from, to);
        
        ThongKeDAO tkDAO = new ThongKeDAO();
        return tkDAO.StatisticSP(dsHD,dsNhap, Id);
    }
    public String StatisticNV(String Id,Calendar from,Calendar to)
    {
        ArrayList<HoaDonDTO> dsHD = new ArrayList<> ();
        dsHD = hdBUS.ListTime(from, to);
        
        ThongKeDAO tkDAO = new ThongKeDAO();
        return tkDAO.StatisticNV(dsHD, Id);
    }
    public String StatisticKH(String Id,Calendar from,Calendar to)
    {
        ArrayList<HoaDonDTO> dsHD = new ArrayList<> ();
        dsHD = hdBUS.ListTime(from, to);
        
        ThongKeDAO tkDAO = new ThongKeDAO();
        return tkDAO.StatisticKH(dsHD, Id);
    }
    
    public ArrayList<String> StatisticTopSP(Calendar from,Calendar to)
    {
        ArrayList<HoaDonDTO> dsHD = new ArrayList<> ();
        dsHD = hdBUS.ListTime(from, to);
        
        ThongKeDAO tkDAO = new ThongKeDAO();
        return tkDAO.StatisticTopSP(dsHD);
    }
    
    public ArrayList<String> StatisticTopNV(Calendar from,Calendar to)
    {
        ArrayList<HoaDonDTO> dsHD = new ArrayList<> ();
        dsHD = hdBUS.ListTime(from, to);
        
        ThongKeDAO tkDAO = new ThongKeDAO();
        return tkDAO.StatisticTopNV(dsHD);
    }
    
    public ArrayList<String> StatisticTopKH(Calendar from,Calendar to)
    {
        ArrayList<HoaDonDTO> dsHD = new ArrayList<> ();
        dsHD = hdBUS.ListTime(from, to);
        
        ThongKeDAO tkDAO = new ThongKeDAO();
        return tkDAO.StatisticTopKH(dsHD);
    }
    public int getTongDoanhThu(Calendar from,Calendar to){
        if(hdBUS.getList()== null) hdBUS.list();
            ArrayList<HoaDonDTO> hd2 = new ArrayList<>();
            hd2 = hdBUS.ListTime(from,to);
            int count=0;
            for(HoaDonDTO n:hd2)
            {

                count+=n.getTongTien();

            }
            return count;
    }
    public int getTongChiPhi(Calendar from,Calendar to){
        if(nhBUS.getList()== null) nhBUS.list();
            ArrayList<PhieuNhapHangDTO> nh2 = new ArrayList<>();
            nh2 = nhBUS.ListTime(from,to);
            int count=0;
            for(PhieuNhapHangDTO n:nh2)
            {

                count+=n.getTongTien();

            }
            return count;
    }
}
