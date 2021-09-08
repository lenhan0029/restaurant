/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NguyenLieuDTO;
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
public class NguyenLieuDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public NguyenLieuDAO(){}
    
    public ArrayList<NguyenLieuDTO> list()
    {
        ArrayList<NguyenLieuDTO> dsnl = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM nguyenlieu WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String IDNL = rs.getString("IDNGUYENLIEU");
                String TenNL = rs.getString("TenNL");
                int SoLuong = rs.getInt("SoLuong");
                int DonGia = rs.getInt("DonGia");
                NguyenLieuDTO ct = new NguyenLieuDTO(IDNL, TenNL, SoLuong, DonGia);
                dsnl.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsnl;
    }
    public ArrayList<NguyenLieuDTO> congthucmonan(int idmon)
    {
        ArrayList<NguyenLieuDTO> dsnl = new ArrayList<>();
        try {
            
            String sql = "SELECT nguyenlieu.IDNGUYENLIEU,nguyenlieu.TenNL,chebien.SoluongNL";
                   sql+= " FROM chebien,nguyenlieu,monan";
                   sql+= " WHERE chebien.IDMONAN=monan.IDMONAN";
                   sql+= " and nguyenlieu.IDNGUYENLIEU=chebien.IDNGUYENLIEU";
                   sql+= " and monan.IDMONAN = "+idmon;
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String IDNL = rs.getString("IDNGUYENLIEU");
                String TenNL = rs.getString("TenNL");
                int SoLuong = rs.getInt("SoLuongNL");
                NguyenLieuDTO ct = new NguyenLieuDTO(IDNL, TenNL, SoLuong);
                dsnl.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsnl;
    }
    public void set(NguyenLieuDTO nl) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE nguyenlieu SET ";
            sql += "TenNL='"+nl.getTenNL()+"', ";
            sql += "SoLuong='"+nl.getSoLuong()+"', ";
            sql += "DonGia='"+nl.getDonGia()+"', ";
            sql += " WHERE IDNUYENLIEUL='"+nl.getIDNL()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(NguyenLieuDTO nl) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO nguyenlieu VALUES (";
                sql += "'"+nl.getIDNL()+"',";
                sql += "'"+nl.getTenNL()+"',";
                sql += "'"+nl.getSoLuong()+"',";
                sql += "'"+nl.getDonGia()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String IDNL)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE nguyenlieu SET enable = 0 WHERE IDNGUYENLIEU='"+IDNL+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public void ExportExcelDatabase(){
        try{
            String sql = "SELECT * FROM nguyenlieu WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Nguyenlieudb");
            
                    
            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
        
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;
            
            cell = row.createCell(0);
            cell.setCellValue("IDNGUYENLIEU");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("TenNL");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("SoLuong");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("DonGia");
            cell.setCellStyle(style);
            int i = 1;
        
        while(rs.next()){
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(rs.getString("IDNGUYENLIEU"));
            cell = row.createCell(1);
            cell.setCellValue(rs.getString("TenNL"));
            cell = row.createCell(2);
            cell.setCellValue(rs.getInt("SoLuong"));
            cell = row.createCell(3);
            cell.setCellValue(rs.getInt("DonGia"));
            
            i++;
        }
        
        for(int colNum = 0;colNum < row.getLastCellNum();colNum++) {
            sheet.autoSizeColumn((short) (colNum));
        }
        
        FileOutputStream out = new FileOutputStream(new File("./report/nguyenlieudb.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("Xuat file thanh cong");
        
        } catch (SQLException ex) {
            Logger.getLogger(NguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                String IDNL = row.getCell(0).getStringCellValue();
                String TenNL = row.getCell(1).getStringCellValue();
                int SoLuong = (int) row.getCell(5).getNumericCellValue();
                int DonGia = (int) row.getCell(6).getNumericCellValue();
                
                String sql_check = "SELECT * FROM nguyenlieu WHERE IDNGUYENLIEU='"+IDNL+"'";
                ResultSet rs = mySQL.executeQuery(sql_check);
                if(!rs.next()){
                    String sql = "INSERT INTO nguyenlieu VALUES (";
                    sql += "'"+IDNL+"',";
                    sql += "N'"+TenNL+"',";
                    sql += "'"+SoLuong+"',";
                    sql += "'"+DonGia+"')";
                    System.out.println(sql);
                    mySQL.executeUpdate(sql);
                }
                else{
                    String sql = "UPDATE nguyenlieu SET ";
                    sql += "Ten='"+TenNL+"', ";
                    sql += "SoLuong='"+SoLuong+"', ";
                    sql += "DonGia='"+DonGia+"' ";
                    sql += "WHERE IDNGUYENLIEU='"+IDNL+"'";
                    System.out.println(sql);    
                    mySQL.executeUpdate(sql);
                }
            }
            in.close();
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(NguyenLieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
