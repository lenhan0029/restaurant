/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author LE NHAN
 */
public class NhanVienBUS {
    private ArrayList<NhanVienDTO> dsnv ;
    public NhanVienBUS(int i1)
    {
        listNV();
    }
    
    public NhanVienBUS()
    {
        
    }
    public NhanVienDTO get(String IDNV)
    {
        for(NhanVienDTO nv : dsnv )
        {
            if(nv.getIDNV().equals(IDNV))
            {
                return nv;
            }
        }
        return null;
    }
    public void listNV()
    {
        NhanVienDAO nvDAO = new NhanVienDAO();
        dsnv = new ArrayList<>();
        dsnv = nvDAO.list();
    }
    public void addNV(NhanVienDTO sp)
    {
        dsnv.add(sp);
        NhanVienDAO nvDAO = new NhanVienDAO();
        nvDAO.add(sp);
    }

    public void deleteNV(String IDNV)
    {
        for(NhanVienDTO nv : dsnv )
        {
            if(nv.getIDNV().equals(IDNV))
            {
                dsnv.remove(nv);
                NhanVienDAO nvDAO = new NhanVienDAO();
                nvDAO.delete(IDNV);
                return;
            }
        }
    }
    public void setNV(NhanVienDTO s)
    {
        for(int i = 0 ; i < dsnv.size() ; i++)
        {
            if(dsnv.get(i).getIDNV().equals(s.getIDNV()))
            {
                dsnv.set(i, s);
                NhanVienDAO nvDAO = new NhanVienDAO();
                nvDAO.set(s);
                return;
            }
        }
    }
    public boolean check(String IDNV)
    {
        for(NhanVienDTO nv : dsnv)
        {
            if(nv.getIDNV().equals(IDNV))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<NhanVienDTO> search(String searchtxt,int Theo,String ChucVu)
    {   
        String s =searchtxt;
        ArrayList<NhanVienDTO> search = new ArrayList<>();
        if(ChucVu != "Chức vụ") {   
            switch(Theo){
                case 0:
                    for(NhanVienDTO nv : dsnv)
                    {
                        if(nv.getHo().contains(s) && nv.getChucVu().contains(ChucVu))
                        {
                            search.add(nv);
                        }
                    }
                break;
                case 1:
                    for(NhanVienDTO nv : dsnv)
                    {
                        if(nv.getTen().contains(s) && nv.getChucVu().contains(ChucVu))
                        {
                            search.add(nv);
                        }
                    }
                break;
                case 2:
                    for(NhanVienDTO nv : dsnv)
                    {
                        if(nv.getDiaChi().contains(s) && nv.getChucVu().contains(ChucVu))
                        {
                            search.add(nv);
                        }
                    }
                break;
            };
        }
        else{
            switch(Theo){
                case 0:
                    for(NhanVienDTO nv : dsnv)
                    {
                        if(nv.getHo().contains(s))
                        {
                            search.add(nv);
                        }
                    }
                break;
                case 1:
                    for(NhanVienDTO nv : dsnv)
                    {
                        if(nv.getTen().contains(s))
                        {
                            search.add(nv);
                        }
                    }
                break;
                case 2:
                    for(NhanVienDTO nv : dsnv)
                    {
                        if(nv.getDiaChi().contains(s))
                        {
                            search.add(nv);
                        }
                    }
                break;
            };
        }
        return search;
    }
    public ArrayList<NhanVienDTO> getList() {
        return dsnv;
    }
    public boolean checkNull(String MaNV,String HoNV,String TenNV,String SDT,String DiaChi,String ChucVu,String Luong){
        boolean flag=true;
        if(check(MaNV)){
            JOptionPane.showMessageDialog(null, "Nhân viên đã tồn tại dựa vào ID !!!");
            flag=false;
        }
        if(HoNV.isEmpty() || checkNumExist(HoNV)){
            JOptionPane.showMessageDialog(null, "Họ nhân viên không hợp lệ");
            flag=false;
        }
        if(TenNV.isEmpty() || checkNumExist(TenNV)){
            JOptionPane.showMessageDialog(null, "Tên nhân viên không hợp lệ");
            flag=false;
        }
        if(Luong.isEmpty()){
            JOptionPane.showMessageDialog(null, "Lương không được bỏ trống");
            flag=false;
        }
        if(DiaChi.isEmpty()){
            JOptionPane.showMessageDialog(null, "Địa chỉ không được bỏ trống");
            flag=false;
        }
        if(SDT.isEmpty() || !checkNumExist(SDT) || SDT.length()<10 || SDT.length()>11){
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");
            flag=false;
        }
        
        if(ChucVu.isEmpty() || ChucVu == "Chức vụ"){
            JOptionPane.showMessageDialog(null, "Chức vụ không được bỏ trống");
            return false;
        }
        return flag;
    }
    public boolean checkNull1(String HoNV,String TenNV,String SDT,String DiaChi,String ChucVu,String Luong){
        boolean flag=true;
        if(HoNV.isEmpty() || checkNumExist(HoNV)){
            JOptionPane.showMessageDialog(null, "Họ nhân viên không hợp lệ");
            flag=false;
        }
        if(TenNV.isEmpty() || checkNumExist(TenNV)){
            JOptionPane.showMessageDialog(null, "Tên nhân viên không hợp lệ");
            flag=false;
        }
        if(Luong.isEmpty()){
            JOptionPane.showMessageDialog(null, "Lương không được bỏ trống");
            flag=false;
        }
        if(DiaChi.isEmpty()){
            JOptionPane.showMessageDialog(null, "Địa chỉ không được bỏ trống");
            flag=false;
        }
        if(SDT.isEmpty() || !checkNumExist(SDT) || SDT.length()<10 || SDT.length()>11){
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");
            flag=false;
        }
        
        if(ChucVu.isEmpty() || ChucVu == "Chức vụ"){
            JOptionPane.showMessageDialog(null, "Chức vụ không được bỏ trống");
            return false;
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
