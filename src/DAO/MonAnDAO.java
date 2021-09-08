/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.MonAnDTO;
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

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
/**
 *
 * @author LE NHAN
 */
public class MonAnDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public MonAnDAO(){}
    
    public ArrayList<MonAnDTO> list()
    {
        ArrayList<MonAnDTO> dsma = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM monan";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String IDMA = rs.getString("IDMONAN");
                String Ten = rs.getString("Ten");
                String DVT = rs.getString("DVT");
                String Loai = rs.getString("Loai");
                String Img = rs.getString("Img");
                int SoLuong = rs.getInt("SoLuong");
                int DonGia = rs.getInt("DonGia");
                MonAnDTO ct = new MonAnDTO(IDMA, Ten, DVT, Loai, Img, SoLuong, DonGia);
                dsma.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsma;
    }
    public void set(MonAnDTO ma) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE monan SET ";
            sql += "Ten='"+ma.getTenMA()+"', ";
            sql += "DVT='"+ma.getDVT()+"', ";
            sql += "Loai='"+ma.getLoai()+"', ";
            sql += "Img='"+ma.getImg()+"', ";
            sql += "SoLuong='"+ma.getSoLuong()+"', ";
            sql += "DonGia='"+ma.getDonGia()+"'";
            sql += " WHERE IDMONAN='"+ma.getIDMA()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(MonAnDTO ma) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO monan VALUES (";
                sql += "'"+ma.getIDMA()+"',";
                sql += "'"+ma.getTenMA()+"',";
                sql += "'"+ma.getDVT()+"',";
                sql += "'"+ma.getLoai()+"',";
                sql += "'"+ma.getImg()+"',";
                sql += "'"+ma.getSoLuong()+"',";
                sql += "'"+ma.getDonGia()+"')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String IDMA)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE from monan WHERE IDMONAN='"+IDMA+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public void ExportExcelDatabase(){
        try{
            String sql = "SELECT * FROM monan";
            ResultSet rs = mySQL.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Monandb");
            
                    
            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
        
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;
            
            cell = row.createCell(0);
            cell.setCellValue("IDMonAn");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("Ten");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("DVT");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("Loai");
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue("Img");
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue("SoLuong");
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue("DonGia");
            cell.setCellStyle(style);
            int i = 1;
        
        while(rs.next()){
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(rs.getString("IDMonAn"));
            cell = row.createCell(1);
            cell.setCellValue(rs.getString("Ten"));
            cell = row.createCell(2);
            cell.setCellValue(rs.getString("DVT"));
            cell = row.createCell(3);
            cell.setCellValue(rs.getString("Loai"));
            cell = row.createCell(4);
            cell.setCellValue(rs.getString("Img"));
            cell = row.createCell(5);
            cell.setCellValue(rs.getInt("SoLuong"));
            cell = row.createCell(6);
            cell.setCellValue(rs.getInt("DonGia"));
            
            i++;
        }
        
        for(int colNum = 0;colNum < row.getLastCellNum();colNum++) {
            sheet.autoSizeColumn((short) (colNum));
        }
        
        FileOutputStream out = new FileOutputStream(new File("./report/monandb.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("Xuat file thanh cong");
        
        } catch (SQLException ex) {
            Logger.getLogger(MonAnDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MonAnDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MonAnDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                String IDMA = row.getCell(0).getStringCellValue();
                String TenMA = row.getCell(1).getStringCellValue();
                String DVT = row.getCell(2).getStringCellValue();
                String Loai = row.getCell(3).getStringCellValue();
                String Img = row.getCell(4).getStringCellValue();
                int SoLuong = (int) row.getCell(5).getNumericCellValue();
                int DonGia = (int) row.getCell(6).getNumericCellValue();
                
                String sql_check = "SELECT * FROM monan WHERE IDMONAN='"+IDMA+"'";
                ResultSet rs = mySQL.executeQuery(sql_check);
                if(!rs.next()){
                    String sql = "INSERT INTO monan VALUES (";
                    sql += "'"+IDMA+"',";
                    sql += "N'"+TenMA+"',";
                    sql += "'"+DVT+"',";
                    sql += "'"+Loai+"',";
                    sql += "N'"+Img+"',";
                    sql += "'"+SoLuong+"',";
                    sql += "'"+DonGia+"')";
                    System.out.println(sql);
                    mySQL.executeUpdate(sql);
                }
                else{
                    String sql = "UPDATE monan SET ";
                    sql += "Ten='"+TenMA+"', ";
                    sql += "DVT='"+DVT+"', ";
                    sql += "Loai='"+Loai+"', ";
                    sql += "Img='"+Img+"', ";
                    sql += "SoLuong='"+SoLuong+"', ";
                    sql += "DonGia='"+DonGia+"' ";
                    sql += "WHERE IDMONAN='"+IDMA+"'";
                    System.out.println(sql);    
                    mySQL.executeUpdate(sql);
                }
            }
            in.close();
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MonAnDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MonAnDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public static void main(String []args){
//        MonAnDAO ma = new MonAnDAO();
//        ArrayList<MonAnDTO> lma = ma.list();
//        for(MonAnDTO mat: lma){
//            System.out.println(mat.getTenMA());
//        }
//    }
}
