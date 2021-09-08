/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.MySQLConnect;
import DTO.HoaDonDTO;
import DTO.PhieuNhapHangDTO;
//import com.sun.javafx.binding.StringFormatter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shadow
 */
public class ThongKeDAO {
    public ThongKeDAO()
    {
        
    }
    public String StatisticSP( ArrayList<HoaDonDTO> listHd, ArrayList<PhieuNhapHangDTO> listNH,String MaSP)
    {
        String s = "";
        int slOut=0,sumOut = 0;
        int slIn=0,sumIn = 0;
        try {
            MySQLConnect mySQL = new MySQLConnect();
            // BÁN
            if(!listHd.isEmpty())
            {
                String sql1 = "SELECT SUM(SoLuong) AS SL,SUM(SoLuong*DonGia) AS TONGTIEN FROM chitiethd WHERE (";
                for(int i = 0 ; i < listHd.size() ; i++)
                {
                    String mahd = listHd.get(i).getIDHD();
                    if(i == (listHd.size() - 1))
                    {
                        sql1 += "IDHD ='"+ mahd +"') ";
                        break;
                    }
                    sql1 += "IDHD ='"+ mahd +"' OR ";
                }
                sql1+= "AND IDMONAN = '"+MaSP+"' ";
                sql1 += "GROUP BY IDHD";
                System.out.println(sql1);
                ResultSet rs1 = mySQL.executeQuery(sql1);
                while(rs1.next())
                {
                    slOut += rs1.getInt("SL");
                    sumOut += rs1.getInt("TONGTIEN");
                }
            }
            
            // NHẬP
//            if(!listNH.isEmpty())
//            {
//                String sql2 = "SELECT SUM(SoLuong) AS SL,SUM(SoLuong*DonGia) AS TONGTIEN FROM phieunhaphang WHERE (";
//                for(int i = 0 ; i < listNH.size() ; i++)
//                {
//                    String idNhap = listNH.get(i).getIDPN();
//                    if(i == (listNH.size() - 1))
//                    {
//                        sql2 += "IDPHIEUNHAP = '"+ idNhap +"') ";
//                        break;
//                    }
//                    sql2 += "IDPHIEUNHAP = '"+ idNhap +"' OR ";
//                }
//                sql2+= "AND IDNGUYENLIEU = '"+MaNL+"' ";
//                sql2 += "GROUP BY IDPHIEUNHAP";
//                System.out.println(sql2);
//                ResultSet rs2 = mySQL.executeQuery(sql2);
//                while(rs2.next())
//                {
//                    slIn += rs2.getInt("SL");
//                    sumIn += rs2.getInt("TONGTIEN");
//                }
//
//            }
            
            if(mySQL.isConnect()) mySQL.disConnect();
            
            s += String.format("Số lượng bán : %6d",slOut);
            s += String.format("Tổng tiền    : %5dđ",sumOut);
            s += "--------------------------------------------------- \n";
            s += "TỔNG THU NHẬP : "+(sumOut-sumIn)+"VNĐ"+"\n";      
            System.out.print(s);
            
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public String StatisticNV(ArrayList<HoaDonDTO> listHd,String MaNV)
    {
        String s ="";
        int sum = 0;
        String listItem = String.format("|%10s|%10s|\n","Mã SP","Số lượng");
        if(!listHd.isEmpty())
        {
            MySQLConnect mySQL = new MySQLConnect();
        try {
            // Tổng tiền 
            String sql1 = "SELECT SUM(TongTien) AS TONGTIEN FROM hoadon WHERE (";
            for(int i = 0 ; i < listHd.size() ; i++)
            {
                String mahd = listHd.get(i).getIDHD();
                if(i == (listHd.size() - 1))
                {
                    sql1 += "MAHD ='"+ mahd +"') ";
                    break;
                }
                sql1 += "IDHD ='"+ mahd +"' OR ";
            }
            sql1+= "AND IDNHANVIEN = '"+MaNV+"' ";
            sql1 += "GROUP BY IDNHANVIEN";
            System.out.println(sql1);
            ResultSet rs1 = mySQL.executeQuery(sql1);
            while(rs1.next())
            {
                sum += rs1.getInt("TONGTIEN");
                
            }
            
            // Mã SP || Số lượng 
            String sql2 = "SELECT MASP,SUM(chitiethd.SoLuong) AS SOLUONG FROM chitiethd WHERE chitiethd.IDHD IN (SELECT MAHD FROM hoadon WHERE (";
            for(int i = 0 ; i < listHd.size() ; i++)
            {
                String mahd = listHd.get(i).getIDHD();
                if(i == (listHd.size() - 1))
                {
                    sql2 += "IDHD ='"+ mahd +"') ";
                    break;
                }
                sql2 += "IDHD ='"+ mahd +"' OR ";
            }
            sql2+= "AND IDNHANVIEN = '"+MaNV+"' )";
            sql2 += "GROUP BY IDNHANVIEN";
            System.out.println(sql2);
            ResultSet rs2 = mySQL.executeQuery(sql2);
            while(rs2.next())
            {
                listItem += String.format("|%10s|%10s|\n",rs2.getString("IDMONAN"),rs2.getString("SoLuong"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        s += listItem;
        s += "--------------------------------------------------- \n";
        s += "TỔNG THU NHẬP : "+sum+"VNĐ"+"\n";      
        return s;
    }
    
    public String StatisticKH(ArrayList<HoaDonDTO> listHd,String MaKH)
    {
        String s ="";
        int sum = 0;
        String listItem = String.format("|%10s|%10s|\n","Mã SP","Số lượng");
        if(!listHd.isEmpty())
        {
            MySQLConnect mySQL = new MySQLConnect();
        try {
            // Tổng tiền 
            String sql1 = "SELECT SUM(TongTien) AS TONGTIEN FROM hoadon WHERE (";
            for(int i = 0 ; i < listHd.size() ; i++)
            {
                String mahd = listHd.get(i).getIDHD();
                if(i == (listHd.size() - 1))
                {
                    sql1 += "IDHD ='"+ mahd +"') ";
                    break;
                }
                sql1 += "IDHD ='"+ mahd +"' OR ";
            }
            sql1+= "AND IDKHACH = '"+MaKH+"' ";
            sql1 += "GROUP BY IDKHACH";
            System.out.println(sql1);
            ResultSet rs1 = mySQL.executeQuery(sql1);
            while(rs1.next())
            {
                sum += rs1.getInt("TONGTIEN");
                
            }
            
            // Mã SP || Số lượng 
            String sql2 = "SELECT MASP,SUM(chitiethd.SoLuong) AS SOLUONG FROM chitiethd WHERE chitiethd.IDHD IN (SELECT IDHD FROM hoadon WHERE (";
            for(int i = 0 ; i < listHd.size() ; i++)
            {
                String mahd = listHd.get(i).getIDHD();
                if(i == (listHd.size() - 1))
                {
                    sql2 += "IDHD ='"+ mahd +"') ";
                    break;
                }
                sql2 += "IDHD ='"+ mahd +"' OR ";
            }
            sql2+= "AND IDKHACH = '"+MaKH+"' )";
            sql2 += "GROUP BY IDMOANAN";
            System.out.println(sql2);
            ResultSet rs2 = mySQL.executeQuery(sql2);
            while(rs2.next())
            {
                listItem += String.format("|%10s|%10s|\n",rs2.getString("IDMONAN"),rs2.getString("SOLUONG"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        s += listItem;
        s += "--------------------------------------------------- \n";
        s += "TỔNG TIỀN : "+sum+"VNĐ"+"\n";      
        return s;
    }
    
    public ArrayList<String> StatisticTopSP(ArrayList<HoaDonDTO> listHd)
    {   
        ArrayList<String> kq = new ArrayList<>();
        if(!listHd.isEmpty())
        {
            try {
                MySQLConnect mySQL = new MySQLConnect();
                String sql = "SELECT IDHD,DonGia,IDMONAN,SUM(SoLuong) AS SOLUONG FROM chitiethd WHERE ";
                for(int i = 0 ; i < listHd.size() ; i++)
                {
                    String mahd = listHd.get(i).getIDHD();
                    if(i == (listHd.size() - 1))
                    {
                        sql += "IDHD ='"+ mahd +"' ";
                        break;
                    }
                    sql += "IDHD ='"+ mahd +"' OR ";
                }
                sql += "GROUP BY IDMONAN ";
                sql += "ORDER BY SUM(SoLuong) DESC ";
                sql += "LIMIT 5";
                System.out.println(sql);
                ResultSet rs = mySQL.executeQuery(sql);
                String sql1;
                while(rs.next())
                {
                    String maSP = rs.getString("IDMONAN");
//                    sql1 = "SELECT Ten FROM monan WEHRE IDMONAN='"+ maSP + "'";
//                    ResultSet rs1 = mySQL.executeQuery(sql1);
                    String tenSP = rs.getString("IDHD");
                    int dongia = rs.getInt("DonGia");
                    int sl = rs.getInt("SOLUONG");
                    String s = String.format("%6s_%20s_%5d_%6d",maSP,tenSP,sl,dongia);
                    kq.add(s);
                    sql1="";
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kq;
    }
    
    public ArrayList<String> StatisticTopNV(ArrayList<HoaDonDTO> listHd)
    {   
        ArrayList<String> kq = new ArrayList<>();
        if(!listHd.isEmpty())
        {
            try {
                MySQLConnect mySQL = new MySQLConnect();
                String sql = "SELECT nhanvien.IDNHANVIEN, CONCAT(nhanvien.Ho,' ',nhanvien.Ten) AS HOTEN ,SUM(TongTien) AS TONGTIEN ";
                       sql+= "FROM hoadon INNER JOIN nhanvien ON hoadon.IDNHANVIEN = nhanvien.IDNHANVIEN WHERE ";
                for(int i = 0 ; i < listHd.size() ; i++)
                {
                    String mahd = listHd.get(i).getIDHD();
                    if(i == (listHd.size() - 1))
                    {
                        sql += "IDHD ='"+ mahd +"' ";
                        break;
                    }
                    sql += "IDHD ='"+ mahd +"' OR ";
                }
                sql += "GROUP BY iDNHANVIEN ";
                sql += "ORDER BY SUM(TongTien) DESC ";
                sql += "LIMIT 5";
                System.out.println(sql);
                ResultSet rs = mySQL.executeQuery(sql);
                while(rs.next())
                {
                    String maNV = rs.getString("IDNHANVIEN");
                    String tenNV = rs.getString("HOTEN");
                    int tt = rs.getInt("TONGTIEN");
                    String s = String.format("%6s_%20s_%5d",maNV,tenNV,tt);
                    kq.add(s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kq;
    }
    
    public ArrayList<String> StatisticTopKH(ArrayList<HoaDonDTO> listHd)
    {   
        ArrayList<String> kq = new ArrayList<>();
        if(!listHd.isEmpty())
        {
            try {
                MySQLConnect mySQL = new MySQLConnect();
                String sql = "SELECT khachhang.IDKHACH, CONCAT(khachhang.Ho,' ',khachhang.Ten) AS HOTEN ,SUM(TongTien) AS TONGTIEN ";
                       sql+= "FROM hoadon INNER JOIN khachhang ON hoadon.IDKHACH = khachhang.IDKHACH WHERE ";
                for(int i = 0 ; i < listHd.size() ; i++)
                {
                    String mahd = listHd.get(i).getIDHD();
                    if(i == (listHd.size() - 1))
                    {
                        sql += "IDHD ='"+ mahd +"' ";
                        break;
                    }
                    sql += "IDHD ='"+ mahd +"' OR ";
                }
                sql += "GROUP BY IDKHACH ";
                sql += "ORDER BY SUM(TongTien) DESC ";
                sql += "LIMIT 5";
                System.out.println(sql);
                ResultSet rs = mySQL.executeQuery(sql);
                while(rs.next())
                {
                    String maNV = rs.getString("IDKHACH");
                    String tenNV = rs.getString("HOTEN");
                    int tt = rs.getInt("TONGTIEN");
                    String s = String.format("%6s_%20s_%5d",maNV,tenNV,tt);
                    kq.add(s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kq;
    }
}
