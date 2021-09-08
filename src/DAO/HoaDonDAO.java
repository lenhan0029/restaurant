/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.HoaDonDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
public class HoaDonDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public HoaDonDAO(){}
    
    public ArrayList<HoaDonDTO> list()
    {
        ArrayList<HoaDonDTO> dshd = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM hoadon WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String IDHD = rs.getString("IDHD");
                String IDNV = rs.getString("IDNHANVIEN");
                String IDB = rs.getString("IDBAN");
                String IDK = rs.getString("IDKHACH");
                String IDKM = rs.getString("IDKM");
                String NgayLap = rs.getString("NgayLap");
                int TongTien = rs.getInt("TongTien");
                HoaDonDTO ct = new HoaDonDTO(IDHD, IDNV, IDB, IDK, IDKM, NgayLap, TongTien);
                dshd.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dshd;
    }
    public void set(HoaDonDTO hd) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE hoadon SET ";
            sql += "IDNHANVIEN='"+hd.getIDNV()+"', ";
            sql += "IDBAN='"+hd.getIDB()+"', ";
            sql += "IDKHACH='"+hd.getIDK()+"', ";
            sql += "IDKM='"+hd.getIDKM()+"', ";
            sql += "NgayLap='"+hd.getNgayLap()+"', ";
            sql += "TongTien='"+hd.getTongTien()+"' ";
            sql += " WHERE IDHD='"+hd.getIDHD()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(HoaDonDTO hd) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO hoadon VALUES (";
                sql += "'"+hd.getIDHD()+"',";
                sql += "'"+hd.getIDNV()+"',";
                sql += "'"+hd.getIDB()+"',";
                sql += "'"+hd.getIDK()+"',";
                sql += "'"+hd.getIDKM()+"',";
                sql += "'"+hd.getNgayLap()+"',";
                sql += "'"+hd.getTongTien()+"')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String IDHD)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM hoadon  WHERE IDHD='"+IDHD+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public void ExportExcelDatabase(){
        try{
            String sql = "SELECT * FROM hoadon WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Hoadondb");
            
                    
            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
        
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;
            
            cell = row.createCell(0);
            cell.setCellValue("IDHD");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("IDNHANVIEN");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("IDBAN");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("IDKHACH");
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue("IDKM");
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue("NgayLap");
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue("TongTien");
            cell.setCellStyle(style);
            int i = 1;
        
        while(rs.next()){
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(rs.getString("IDHD"));
            cell = row.createCell(1);
            cell.setCellValue(rs.getString("IDNHANVIEN"));
            cell = row.createCell(2);
            cell.setCellValue(rs.getString("IDBAN"));
            cell = row.createCell(3);
            cell.setCellValue(rs.getString("IDKHACH"));
            cell = row.createCell(4);
            cell.setCellValue(rs.getString("IDKM"));
            cell = row.createCell(5);
            cell.setCellValue(rs.getString("NgayLap"));
            cell = row.createCell(6);
            cell.setCellValue(rs.getInt("TongTien"));
            
            i++;
        }
        
        for(int colNum = 0;colNum < row.getLastCellNum();colNum++) {
            sheet.autoSizeColumn((short) (colNum));
        }
        
        FileOutputStream out = new FileOutputStream(new File("./report/hoadondb.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("Xuat file thanh cong");
        
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                String IDHD = row.getCell(0).getStringCellValue();
                String IDNHANVIEN = row.getCell(1).getStringCellValue();
                String IDBAN = row.getCell(2).getStringCellValue();
                String IDKHACH = row.getCell(3).getStringCellValue();
                String IDKM = row.getCell(4).getStringCellValue();
                String NgayLap = row.getCell(5).getStringCellValue();
                int TongTien = (int) row.getCell(6).getNumericCellValue();
                
                String sql_check = "SELECT * FROM hoadon WHERE IDHD='"+IDHD+"'";
                ResultSet rs = mySQL.executeQuery(sql_check);
                if(!rs.next()){
                    String sql = "INSERT INTO hoadon VALUES (";
                    sql += "'"+IDHD+"',";
                    sql += "N'"+IDNHANVIEN+"',";
                    sql += "'"+IDBAN+"',";
                    sql += "'"+IDKHACH+"',";
                    sql += "N'"+IDKM+"',";
                    sql += "'"+NgayLap+"',";
                    sql += "'"+TongTien+"')";
                    System.out.println(sql);
                    mySQL.executeUpdate(sql);
                }
                else{
                    String sql = "UPDATE hoadon SET ";
                    sql += "IDNHANVIEN='"+IDNHANVIEN+"', ";
                    sql += "IDBAN='"+IDBAN+"', ";
                    sql += "IDKHACH='"+IDKHACH+"', ";
                    sql += "IDKM='"+IDKM+"', ";
                    sql += "NgayLap='"+NgayLap+"', ";
                    sql += "TongTien='"+TongTien+"' ";
                    sql += "WHERE IDHD='"+IDHD+"'";
                    System.out.println(sql);    
                    mySQL.executeUpdate(sql);
                }
            }
            in.close();
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
