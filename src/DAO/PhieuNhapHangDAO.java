/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.PhieuNhapHangDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author LE NHAN
 */
public class PhieuNhapHangDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public PhieuNhapHangDAO(){}
    
    public ArrayList<PhieuNhapHangDTO> list()
    {
        ArrayList<PhieuNhapHangDTO> dspnh = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM phieunhaphang WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String IDPN = rs.getString("IDPHIEUNHAP");
                String IDNV = rs.getString("IDNHANVIEN");
                String IDNCC = rs.getString("IDNCC");
                String IDNL = rs.getString("IDNGUYENLIEU");
                Date NgayNhap = rs.getDate("NgayNhap");
                int sl = rs.getInt("SoLuong");
                int dg = rs.getInt("DonGia");
                int TongTien = rs.getInt("TongTien");
                PhieuNhapHangDTO ct = new PhieuNhapHangDTO(IDPN, IDNV, IDNCC,IDNL, NgayNhap,sl,dg, TongTien);
                dspnh.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dspnh;
    }
    public void set(PhieuNhapHangDTO pnh) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE phieunhaphang SET ";
            sql += "IDNHANVIEN'"+pnh.getIDNV()+"', ";
            sql += "IDNCC'"+pnh.getIDNCC()+"', ";
            sql += "IDNGUYENLIEU'"+pnh.getIDNL()+"', ";
            sql += "NgayNhap'"+pnh.getNgayNhap()+"', ";
            sql += "SoLuong'"+pnh.getSoLuong()+"', ";
            sql += "DonGia'"+pnh.getDonGia()+"', ";
            sql += "TongTien'"+pnh.getTongTien()+"'";
            sql += " WHERE IDPHIEUNHAP='"+pnh.getIDPN()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(PhieuNhapHangDTO pnh) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO phieunhaphang VALUES (";
                sql += "'"+pnh.getIDPN()+"',";
                sql += "'"+pnh.getIDNV()+"',";
                sql += "'"+pnh.getIDNCC()+"',";
                sql += "'"+pnh.getIDNL()+"',";
                sql += "'"+pnh.getNgayNhap()+"',";
                sql += "'"+pnh.getSoLuong()+"',";
                sql += "'"+pnh.getDonGia()+"',";
                sql += "'"+pnh.getTongTien()+"')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String IDPN)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM phieunhaphang WHERE IDPHIEUNHAP='"+IDPN+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public void ExportExcelDatabase(){
        try{
            String sql = "SELECT * FROM phieunhaphang WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("phieunhaphangdb");
            
                    
            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
        
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;
            
            cell = row.createCell(0);
            cell.setCellValue("IDPHIEUNHAP");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("IDNHANVIEN");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("IDNCC");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("IDNGUYENLIEU");
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue("NgayNhap");
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue("SoLuong");
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue("DonGia");
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue("TongTien");
            cell.setCellStyle(style);
            int i = 1;
        
        while(rs.next()){
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(rs.getString("IDPHIEUNHAP"));
            cell = row.createCell(1);
            cell.setCellValue(rs.getString("IDNHANVIEN"));
            cell = row.createCell(2);
            cell.setCellValue(rs.getString("IDNCC"));
            cell = row.createCell(3);
            cell.setCellValue(rs.getString("IDNGUYENLIEU"));
            cell = row.createCell(4);
            cell.setCellValue(rs.getString("NgayNhap"));
            cell = row.createCell(5);
            cell.setCellValue(rs.getInt("SoLuong"));
            cell = row.createCell(6);
            cell.setCellValue(rs.getInt("DonGia"));
            cell = row.createCell(7);
            cell.setCellValue(rs.getInt("TongTien"));
            
            i++;
        }
        
        for(int colNum = 0;colNum < row.getLastCellNum();colNum++) {
            sheet.autoSizeColumn((short) (colNum));
        }
        
        FileOutputStream out = new FileOutputStream(new File("./report/phieunhaphangdb.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("Xuat file thanh cong");
        
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PhieuNhapHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PhieuNhapHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void ImportExcelDatabase(File file){
        try{
            FileInputStream in = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(in);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row row;
            for(int i = 1; i <= sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);
                String IDPHIEUNHAP = row.getCell(0).getStringCellValue();
                String IDNHANVIEN = row.getCell(1).getStringCellValue();
                String IDNCC = row.getCell(2).getStringCellValue();
                String IDNL = row.getCell(3).getStringCellValue();
                String NgayNhap = row.getCell(4).getStringCellValue();
                int SoLuong = (int) row.getCell(5).getNumericCellValue();
                int DonGia = (int) row.getCell(6).getNumericCellValue();
                int TongTien = (int) row.getCell(7).getNumericCellValue();
                
                String sql_check = "SELECT * FROM monan WHERE IDMONAN='"+IDPHIEUNHAP+"'";
                ResultSet rs = mySQL.executeQuery(sql_check);
                if(!rs.next()){
                    String sql = "INSERT INTO phieunhaphang VALUES (";
                    sql += "'"+IDPHIEUNHAP+"',";
                    sql += "'"+IDNHANVIEN+"',";
                    sql += "'"+IDNCC+"',";
                    sql += "'"+IDNL+"',";
                    sql += "'"+NgayNhap+"',";
                    sql += "'"+SoLuong+"',";
                    sql += "'"+DonGia+"',";
                    sql += "'"+TongTien+"')";
                    System.out.println(sql);
                    mySQL.executeUpdate(sql);
                }
                else{
                    String sql = "UPDATE phieunhaphang SET ";
                    sql += "IDNHANVIEN='"+IDNHANVIEN+"', ";
                    sql += "IDNCC='"+IDNCC+"', ";
                    sql += "IDNGUYENLIEU='"+IDNL+"', ";
                    sql += "NgayNhap='"+NgayNhap+"', ";
                    sql += "SoLuong='"+SoLuong+"', ";
                    sql += "DonGia='"+DonGia+"', ";
                    sql += "TongTien='"+TongTien+"' ";
                    sql += "WHERE IDPHIEUNHAP='"+IDPHIEUNHAP+"'";
                    System.out.println(sql);    
                    mySQL.executeUpdate(sql);
                }
            }
            in.close();
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PhieuNhapHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(PhieuNhapHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
