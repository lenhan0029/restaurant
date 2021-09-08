/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.NhaCungCapBUS;
import BUS.NguyenLieuBUS;
import DTO.NhaCungCapDTO;
import DTO.NguyenLieuDTO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Shadow
 */
class SuggestNCC extends JDialog{
    private String maNCC;
    private NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    private JTextField txtIDNCC,txtTenNCC,txtIDNL,txtSDT,txtDiaChi;
    private DefaultTableModel model;
    private JTable tbl;
    private int DWIDTH = 1200;
    private JTextField txtSearch;
    private JComboBox cmbChoice;

    public SuggestNCC(String maNCC)
    {
        this.maNCC = maNCC;
        setModal(true);
        init();
    }
    public SuggestNCC()
    {
        this.maNCC ="";
        setModal(true);
        init();
    }
    public void init()
    {
        setTitle("Danh sách Nhà cung cấp");
        setSize(DWIDTH,700);
        getContentPane().setBackground(new Color(55, 63, 81));
        setLayout(null);
        setLocationRelativeTo(null);
        
        Font font0 = new Font("Segoe UI",Font.PLAIN,14);
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
        
        //HEADER
/***************** PHẦN HIỂN THỊ THÔNG TIN ***************************/
        JPanel itemView = new JPanel(null);
        itemView.setBounds(new Rectangle(0, 0,this.DWIDTH, 700));
        itemView.setBackground(Color.WHITE);
        
        JLabel lbMaNCC = new JLabel("Mã nhà cung cấp ");
        lbMaNCC.setFont(font0);
        lbMaNCC.setBounds(20,20,100,30);
        txtIDNCC = new JTextField();
        txtIDNCC.setBounds(new Rectangle(120,20,250,30));
        itemView.add(lbMaNCC);
        itemView.add(txtIDNCC);
        
        JLabel lbTenNCC = new JLabel("Tên nhà cung cấp");
        lbTenNCC.setFont(font0);
        lbTenNCC.setBounds(20,70,100,30);
        txtTenNCC = new JTextField();
        txtTenNCC.setBounds(new Rectangle(120,70,250,30));
        itemView.add(lbTenNCC);
        itemView.add(txtTenNCC);
        
        JLabel lbMaNL = new JLabel("Mã nguyên liệu");
        lbMaNL.setFont(font0);
        lbMaNL.setBounds(20,120,100,30);
        txtIDNL = new JTextField();
        txtIDNL.setBounds(new Rectangle(120,120,250,30));
        itemView.add(lbMaNL);
        itemView.add(txtIDNL);
        
        
/**************** TẠO CÁC BTN XÓA, SỬA, VIEW, IN BILL ********************/

        JButton btnConfirm = new JButton(new ImageIcon("./src/image/xacnhan.png"));
        btnConfirm.setBounds(new Rectangle(140,320,130,45));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirm.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                dispose();
            }
        });
              
//        JButton btnBack = new JButton(/*new ImageIcon("./src/image/btnBack_150px.png")*/"TRỞ LẠI");
//        btnBack.setBounds(new Rectangle(180,320,150,50));
//        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));    
//        btnBack.addMouseListener(new MouseAdapter(){
//            public void mouseClicked(MouseEvent e)
//            {
//                dispose();
//            }
//        });
        
        itemView.add(btnConfirm);
//        itemView.add(btnBack);
/*************************************************************************/

/**************** TẠO TABLE ************************************************************/

    /************** TẠO MODEL VÀ HEADER *********************************/
        Vector header = new Vector();
        header.add("Mă NCC");
        header.add("Tên NCC");
        header.add("Mã NL");
        model = new DefaultTableModel(header,5);
        tbl = new JTable(model);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);
        tbl.setRowSorter(rowSorter);
        listSP();
        if (maNCC.trim().length() != 0) {
            int select = searchModel(model, maNCC);
            tbl.setRowSelectionInterval(select, select);
            TabletoTXT(select);
        }
    /*******************************************************************/
        

    /******** CUSTOM TABLE ****************/
    
        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(50);
//        tbl.getColumnModel().getColumn(3).setPreferredWidth(50);

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
        scroll.setBounds(new Rectangle(400, 20, DWIDTH - 450 , 500));
        scroll.setBackground(null);
        
        itemView.add(scroll);
        
        add(itemView);
    /**************************************/
/*****************************************************************************************/
        tbl.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e)
             {
                int i = tbl.getSelectedRow();
                TabletoTXT(i);
             }
        });
/*********************************************************************/
/********************* THANH SEARCH ***********************************************/
        
//         Tạo Search Box
//        JPanel searchBox = new JPanel(null);
//        searchBox.setBackground(null);
//        searchBox.setBounds(new Rectangle(20,270,350, 30)); 
//        searchBox.setBorder(createLineBorder(Color.BLACK)); //Chỉnh viền 
//        
//        //PHẦN CHỌN SEARCH
//        cmbChoice = new JComboBox();
//        cmbChoice.setEditable(true);
//        cmbChoice.setFont(new Font("Segoe UI",Font.PLAIN,14));
//        cmbChoice.addItem("Mã NL");
//        cmbChoice.addItem("Tên NL");
//        cmbChoice.setBounds(new Rectangle(0,0,80,30));
//        
//        //Phần TextField 
//        txtSearch = new JTextField();
//        txtSearch.setBounds(new Rectangle(85,0,220,30));
//        txtSearch.setBorder(null);
//        txtSearch.setOpaque(false);
//        txtSearch.setFont(new Font("Segoe UI",Font.PLAIN,15));
//        
//        // Custem Icon search
//        JLabel searchIcon = new JLabel(new ImageIcon("./src/image/search_25px.png"));
//        searchIcon.setBounds(new Rectangle(305,-9,50,50));
//        searchIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        
//        // Add tất cả vào search box
//        searchBox.add(cmbChoice);
//        searchBox.add(txtSearch);
//        searchBox.add(searchIcon);
//
//        //bắt sự kiện Focus vào search box
//        txtSearch.addFocusListener(new FocusAdapter(){
//            @Override
//            public void focusGained(FocusEvent e) 
//            {
//                searchIcon.setIcon(new ImageIcon("./src/image/search_25px_focus.png")); //Đổi màu icon
//                searchBox.setBorder(createLineBorder(new Color(52,152,219))); // Đổi màu viền 
//            }
//            public void focusLost(FocusEvent e) //Trờ về như cũ
//            {
//                searchIcon.setIcon(new ImageIcon("./src/image/search_25px.png"));
//                searchBox.setBorder(createLineBorder(Color.BLACK));
//            }
//        });
//        txtSearch.getDocument().addDocumentListener(new DocumentListener(){
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                String text = txtSearch.getText();
//                int choice = cmbChoice.getSelectedIndex();
//                
//                if (text.trim().length() == 0) {
//                    rowSorter.setRowFilter(null);
//                } else {
//                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)^"+ text +".*", choice));
//                }
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                String text = txtSearch.getText();
//                int choice = cmbChoice.getSelectedIndex();
//                
//                if (text.trim().length() == 0) {
//                    rowSorter.setRowFilter(null);
//                } else {
//                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)^"+ text +".*", choice));
//                }
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//            
//        });
//        itemView.add(searchBox);
/*********************************************************************************/
        setVisible(true);
    }
    public void outModel(DefaultTableModel model , ArrayList<NhaCungCapDTO> sp) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for(NhaCungCapDTO s:sp)
        {
            data = new Vector();
            data.add(s.getIDNCC());
            data.add(s.getTenNCC());
            data.add(s.getIDNL());
//            data.add(s.getDVT());
//            data.add(s.getSoLuong());
//            data.add(s.getImg());
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public void listSP() // Chép ArrayList lên table
    {
        if(nccBUS.getList()== null)nccBUS.listNCC();
        ArrayList<NhaCungCapDTO> nv = nccBUS.getList();
        model.setRowCount(0);
        outModel(model,nv);
    }
    public String getMaNCC() 
    {
        return  txtIDNCC.getText();
    }
    public String getMaNL() 
    {
        return  txtIDNL.getText();
    }
    
    public int searchModel(DefaultTableModel model,String s)
    {
        for(int i = 0 ; i < model.getRowCount();i++)
        {
            if(model.getValueAt(i, 0).equals(s))
            {
                return i;
            }
        }
        return 0;
    }
    public void TabletoTXT(int i)
    {
        if(tbl.getRowSorter() != null)
        {
            i = tbl.getRowSorter().convertRowIndexToModel(i);
        }
        txtIDNCC.setText(tbl.getModel().getValueAt(i, 0).toString());
        txtTenNCC.setText(tbl.getModel().getValueAt(i, 1).toString());
        txtIDNL.setText(tbl.getModel().getValueAt(i, 2).toString()); 
//        txtDVT.setText(tbl.getModel().getValueAt(i, 3).toString()); 
//        txtSL.setText(tbl.getModel().getValueAt(i, 3).toString());
//        img = tbl.getModel().getValueAt(i, 5).toString();
    }
}
