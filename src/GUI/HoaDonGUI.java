/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.ChiTietHDBUS;
import BUS.HoaDonBUS;
import BUS.outBill;
import DTO.ChiTietHDDTO;
//import DTO.HoaDonDAO;
import DTO.HoaDonDTO;
import com.toedter.calendar.JDateChooser;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Shadow
 */
public class HoaDonGUI extends JPanel{
    private ChiTietHDBUS ctBUS = new ChiTietHDBUS(1);
    private HoaDonBUS hdBUS = new HoaDonBUS();
    private JTable tbl;
    private JTextField txtMaHD,txtMaKH,txtMaNV,txtNgayHD,txtTongTien,txtMaB, txtMaKM;
    private JTextField txtMinPrice,txtMaxPrice;
    private DefaultTableModel model;
    private Choice yearChoice,monthChoice;
    private int DEFALUT_WIDTH;
    private JTextField txtMaSP;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnView;
    private JButton btnBill;
    private JButton btnConfirm;
    private JButton btnBack;
    private JDateChooser dateChoice;
    
    public HoaDonGUI(int width)
    {
        DEFALUT_WIDTH = width;
        init();

    }
    public void init()
    {        
        setLayout(null);
        setBackground(null);
        setBounds(new Rectangle(50, 0, this.DEFALUT_WIDTH - 220, 1000));
        Font font0 = new Font("Segoe UI",Font.PLAIN,13);
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
       
/*********************** PHẦN VIEW THÔNG TIN *****************************/
        JPanel itemView = new JPanel(null);
        itemView.setBackground(null);
        itemView.setBounds(new Rectangle(30,40,this.DEFALUT_WIDTH - 200,120));

        JLabel lbMaHD = new JLabel("Mã HD");
        lbMaHD.setFont(font0);
        lbMaHD.setBounds(0,0,55,30);
        txtMaHD = new JTextField();
        txtMaHD.setFont(font0);
        txtMaHD.setBounds(new Rectangle(55,0,80,30));
        itemView.add(lbMaHD);
        itemView.add(txtMaHD);

        JLabel lbMaKH = new JLabel("Mã KH");
        lbMaKH.setFont(font0);
        lbMaKH.setBounds(155,0,60,30);
        txtMaKH = new JTextField();
        txtMaKH.setFont(font0);
        txtMaKH.setBounds(new Rectangle(215,0,80,30));
        itemView.add(lbMaKH);
        itemView.add(txtMaKH);

        JLabel lbMaNV = new JLabel("Mã NV");
        lbMaNV.setFont(font0);
        lbMaNV.setBounds(315,0,60,30);
        txtMaNV = new JTextField();
        txtMaNV.setFont(font0);
        txtMaNV.setBounds(new Rectangle(375,0,80,30));
        itemView.add(lbMaNV);
        itemView.add(txtMaNV);

        JLabel lbNgayHD = new JLabel("Ngày HD");
        lbNgayHD.setFont(font0);
        lbNgayHD.setBounds(0,40,60,30);
        txtNgayHD = new JTextField();
        txtNgayHD.setFont(font0);        
        txtNgayHD.setBounds(new Rectangle(80,40,375,30));
        itemView.add(lbNgayHD);
        itemView.add(txtNgayHD);
        
        dateChoice = new JDateChooser();
        dateChoice.setBounds(new Rectangle(80,40,375,30));
        dateChoice.setVisible(false);
        itemView.add(dateChoice);

        JLabel lbTongTien = new JLabel("Tổng Tiền");
        lbTongTien.setFont(font0);
        lbTongTien.setBounds(0,80,60,30);
        txtTongTien = new JTextField();
        txtTongTien.setFont(font0);
        txtTongTien.setBounds(new Rectangle(80,80,375,30));
        itemView.add(lbTongTien);
        itemView.add(txtTongTien);

        add(itemView);
        /**************** TẠO CÁC BTN XÓA, SỬA, VIEW, IN BILL ********************/

        btnEdit = new JButton(new ImageIcon("./src/image/suabtn.png"));
        btnEdit.setBounds(new Rectangle(500,0,130,45));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
       
        
        btnDelete = new JButton(new ImageIcon(("./src/image/xoabtn.png")));
        btnDelete.setBounds(new Rectangle(500,60,130,45));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnView = new JButton(new ImageIcon(("./src/image/chitietbtn.png")));
        btnView.setBounds(new Rectangle(730,0,130,45));
        btnView.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnBill = new JButton(new ImageIcon(("./src/image/billbtn.png")));
        btnBill.setBounds(new Rectangle(730,60,130,45));
        btnBill.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnConfirm= new JButton(new ImageIcon("./src/image/xacnhan.png"));
        btnConfirm.setVisible(false);
        btnConfirm.setBounds(new Rectangle(500,0,130,45));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnBack = new JButton(new ImageIcon("./src/image/trolai.png"));
        btnBack.setVisible(false);
        btnBack.setBounds(new Rectangle(500,60,130,45));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        itemView.add(btnEdit);
        itemView.add(btnDelete);
        itemView.add(btnView);
        itemView.add(btnBill);
        
        itemView.add(btnConfirm);
        itemView.add(btnBack);
        
        // MouseClick btnDelete
        btnDelete.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {   
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa","Alert",JOptionPane.YES_NO_OPTION);
                if(i == 0)
                {
                    ctBUS.delete(txtMaHD.getText());
                    hdBUS.delete(txtMaHD.getText());
                    cleanView();
                    tbl.clearSelection();
                    outModel(model, hdBUS.getList());
                }
            }
        });
        
        // Xem Chi Tiết HD
        btnView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                ChiTietHDGUI chitiet = new ChiTietHDGUI(txtMaHD.getText());
            }
        });
        
        // In Bill
        btnBill.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                String maHD = txtMaHD.getText();
                String maKH = txtMaKH.getText();
                String maNV = txtMaNV.getText();
                String ngayHD = txtNgayHD.getText();
                String MaB = "1";
                String MaKM = "1";
                int tongTien = Integer.parseInt(txtTongTien.getText());
                HoaDonDTO hd = new HoaDonDTO(maHD, maNV,MaB, maKH,MaKM, ngayHD, tongTien);
                ChiTietHDBUS ctBUS = new ChiTietHDBUS(1);
                ArrayList<ChiTietHDDTO> cthd = ctBUS.getListHD(maHD);
                outBill bill = new outBill(hd, cthd);
                bill.print();
            }
        });
        
        //Edit hóa đơn
        btnEdit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(txtMaHD.getText().equals(""))
                {   
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn !");
                    return;
                }
                isEdit(true);
                
            }   
        });
        
        btnConfirm.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                String maHD = txtMaHD.getText();
                String maKH = txtMaKH.getText();
                String maNV = txtMaNV.getText();
                String ngayHD = txtNgayHD.getText();
                String MaB = "1";
                String MaKM = "1";
                int tongTien = Integer.parseInt(txtTongTien.getText());

                if(dateChoice.getCalendar() != null)
                {
                    Date time = Timestamp.valueOf(txtNgayHD.getText());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(time.getTime());
                    int dd = dateChoice.getCalendar().get(Calendar.DATE);
                    int mm = dateChoice.getCalendar().get(Calendar.MONTH);
                    int yyy = dateChoice.getCalendar().get(Calendar.YEAR);
                    calendar.set(yyy,mm, dd);
                    Timestamp newTime = new Timestamp(calendar.getTime().getTime());
                    
                    ngayHD = newTime.toString();
                }        
                
                HoaDonDTO hd = new HoaDonDTO(maHD, maNV,MaB, maKH,MaKM, ngayHD, tongTien);
                int i = hdBUS.set(hd);
                outModel(model, hdBUS.getList());
                tbl.setRowSelectionInterval(i, i);
                isEdit(false);
            }   
        });
        
        btnBack.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                
                isEdit(false);
                
                String date = txtNgayHD.getText();
                try {
                    Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(date);
                } catch (ParseException ex) {
                    Logger.getLogger(HoaDonGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                Calendar calendar = Calendar.getInstance();
//                calendar.setTimeInMillis(date1.getTime());

                if(dateChoice.getCalendar() != null)
                {
                    int dd = dateChoice.getCalendar().get(Calendar.DATE);
                    int mm = dateChoice.getCalendar().get(Calendar.MONTH);
                    int yyyy = dateChoice.getCalendar().get(Calendar.YEAR);
                    calendar.set(yyyy,mm, dd);
                    Date newTime = new Date(calendar.getTime().getTime());
                    System.out.println(newTime);
                    txtNgayHD.setText(newTime.toString());
                }
            }
        });
        /*************************************************************************/
/****************** TẠO MODEL VÀ HEADER *********************************************/
        Vector header = new Vector();
        header.add("Mă Hóa Đơn");
        header.add("Mă KH");
        header.add("Mã NV");
        header.add("Ngay Hóa Đơn");
        header.add("Tổng Tiền");
        model = new DefaultTableModel(header,5)
        {
            public Class getColumnClass(int column)
            {
                switch(column){
                    case 4:
                        return Integer.class;
                    default:
                        return String.class;
                }
            }              
        };
        tbl = new JTable(model);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);
        tbl.setRowSorter(rowSorter);
        list(); //Đọc từ database lên table 
        
/*********************************************************/
        
/**************** TẠO TABLE ************************************************************/

        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(40);

        DefaultTableCellRenderer leftAlign = new DefaultTableCellRenderer();
        leftAlign.setHorizontalAlignment(JLabel.LEFT);
        tbl.getColumnModel().getColumn(4).setCellRenderer(leftAlign);
        
        // Custom table
        tbl.setFocusable(false);
        tbl.setIntercellSpacing(new Dimension(0,0));     
        tbl.getTableHeader().setFont(font1);
        tbl.setRowHeight(30);
        tbl.setShowVerticalLines(false);              
        tbl.getTableHeader().setOpaque(false);
        tbl.setFillsViewportHeight(true);
        tbl.getTableHeader().setBackground(new Color(255,153,0));
        tbl.getTableHeader().setForeground(Color.WHITE);
        tbl.setSelectionBackground(new Color(51,204,51));          
        
        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbl);
        scroll.setBounds(new Rectangle(30, 270, this.DEFALUT_WIDTH - 400 , 400));
        scroll.setBackground(null);
        
        add(scroll);
/*****************************************************************************************/

        
        tbl.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e)
             {
                int i = tbl.getSelectedRow();
                if(tbl.getRowSorter() != null)
                {
                    i = tbl.getRowSorter().convertRowIndexToModel(i);
                }
                txtMaHD.setText(tbl.getModel().getValueAt(i, 0).toString());
                try
                {
                    txtMaKH.setText(tbl.getModel().getValueAt(i, 1).toString());
                }
                catch(NullPointerException ex)
                {
                    txtMaKH.setText("");
                }
                txtMaNV.setText(tbl.getModel().getValueAt(i, 2).toString()); 
                txtNgayHD.setText(tbl.getModel().getValueAt(i, 3).toString());
                txtTongTien.setText( tbl.getModel().getValueAt(i, 4).toString());
             }
        });
/*********************** SORT TABLE *****************************/
        JPanel sort = new JPanel(null);
        sort.setBackground(null);
        sort.setBounds(30,170,this.DEFALUT_WIDTH - 400,140);
        
        JLabel sortTitle = new JLabel("------------------------------------------------------------------------------------ BỘ LỌC ------------------------------------------------------------------------------------",JLabel.CENTER); // Mỗi bên 84 dấu ( - )
        sortTitle.setFont(font1);
        sortTitle.setBounds(new Rectangle(0,0,this.DEFALUT_WIDTH - 400,30));
        sort.add(sortTitle);
        
        /******** SORT THỜI GIAN **************/
        JLabel sortTime = new JLabel("Thời gian :");
        sortTime.setFont(font0);
        sortTime.setBounds(0,40,80,30);
        sort.add(sortTime);
        // Choice Tháng
        int month = Calendar.getInstance().get(Calendar.MONTH);// Lấy tháng hiện tại
        monthChoice = new Choice();
        monthChoice.setFont(font0);
        monthChoice.add("Không");
        for(int i = 1 ; i <= 12 ; i++ )
        {
            monthChoice.add("Tháng "+i);
        }
        monthChoice.select(0);
        monthChoice.setBounds(new Rectangle(80,42,80,40));
        sort.add(monthChoice);
        // Choice Năm
        int year = Calendar.getInstance().get(Calendar.YEAR);//Lấy năm hiện tại
        yearChoice = new Choice();
        yearChoice.setFont(font0);
        yearChoice.add("Không");
        for(int i = year ; i >= 1999 ; i--)
        {
            yearChoice.add(String.valueOf(i));
        }
        yearChoice.select(0);
        yearChoice.setBounds(new Rectangle(170,42,80,40));
        sort.add(yearChoice);
        /*************************************/
        
        /************ SORT THEO GIÁ ***************/
        JLabel sortPrice = new JLabel("Giá (VNĐ) :");
        sortPrice.setFont(font0);
        sortPrice.setBounds(300,40,80,30);
        sort.add(sortPrice);
        
        txtMinPrice = new JTextField();
        txtMinPrice.setFont(font0);
        txtMinPrice.setBounds(new Rectangle(380,42,100,26));
        sort.add(txtMinPrice);
        
        JSeparator sepPrice = new JSeparator(JSeparator.HORIZONTAL);
        sepPrice.setBounds(490, 56, 10, 6);
        sort.add(sepPrice);
        
        txtMaxPrice = new JTextField();
        txtMaxPrice.setFont(font0);
        txtMaxPrice.setBounds(new Rectangle(510,42,100,26));
        sort.add(txtMaxPrice);
          
        /******************************************/
        /************ SORT MÃ SP ***************/
        JLabel sortSP = new JLabel("Mã SP :");
        sortSP.setFont(font0);
        sortSP.setBounds(650,40,60,30);
        sort.add(sortSP);

        txtMaSP = new JTextField();
        txtMaSP.setFont(font0);
        txtMaSP.setBounds(new Rectangle(700,42,100,26));  
        sort.add(txtMaSP);
//        /******************************************/
        JLabel btnSearch = new JLabel(new ImageIcon("./src/image/btnSearch_45px.png"));
        btnSearch.setBounds(new Rectangle(840,20,63,63));
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSearch.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e)
           {
               search();
           }
        });
        sort.add(btnSearch);
        
        add(sort);

/****************************************************************/
        
    }
    public void search()
    {
        
        
        int mm = monthChoice.getSelectedIndex()-1;
        int yyy ;
        try{
            yyy = Integer.parseInt(yearChoice.getSelectedItem());
        }catch(NumberFormatException ex)
        {
            yyy = 0;
        }
        double max = txtMaxPrice.getText().equals("") ? 99999999 : Double.parseDouble(txtMaxPrice.getText());
        double min = txtMinPrice.getText().equals("") ? 0      : Double.parseDouble(txtMinPrice.getText());

        outModel(model,hdBUS.search(mm, yyy, max, min,ctBUS.getHD(txtMaSP.getText())));
    }
    public void cleanView()
    {
        txtMaHD.setText("");
        txtMaKH.setText("");
        txtMaNV.setText("");
        txtNgayHD.setText("");
        txtTongTien.setText("");
    }
    public void outModel(DefaultTableModel model , ArrayList<HoaDonDTO> hd) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for(HoaDonDTO h:hd)
        {
            data = new Vector();
            data.add(h.getIDHD());
            data.add(h.getIDK());
            data.add(h.getIDNV());
            data.add(h.getNgayLap());
            data.add(h.getTongTien());
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public void list() // Chép ArrayList lên table
    {
        if(hdBUS.getList()== null)hdBUS.list();
        ArrayList<HoaDonDTO> hd = hdBUS.getList();
        model.setRowCount(0);
        outModel(model,hd);
    }
    public void isEdit(boolean flag)
    {
        txtNgayHD.setVisible(!flag);
        btnEdit.setVisible(!flag);
        btnDelete.setVisible(!flag);
        btnBill.setVisible(!flag);
        btnView.setVisible(!flag);
        tbl.setEnabled(!flag);
        
        btnConfirm.setVisible(flag);
        btnBack.setVisible(flag);
        dateChoice.setVisible(flag);
        
    }
}
