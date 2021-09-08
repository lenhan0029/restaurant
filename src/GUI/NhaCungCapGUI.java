/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.NguyenLieuBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import DTO.NhaCungCapDTO;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
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
public class NhaCungCapGUI extends JPanel implements ActionListener,KeyListener{        
    private NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    private JTable tbl;
    private JTextField txtMaNCC,txtTenNCC,txtDiaChi,txtDienThoai,txtIDNL/*,txtSoFax*/;
    private JTextField sortMaNCC,sortTenNCC;
    private DefaultTableModel model;
    private int DEFALUT_WIDTH;
    private boolean EditOrAdd = true;//Cờ cho button Cofirm True:ADD || False:Edit
    private JButton btnNL;
//    private NguyenLieuBUS nlBUS = new NguyenLieuBUS();
    
    public NhaCungCapGUI (int width)
    {
        DEFALUT_WIDTH = width;
        nccBUS.listNCC();
        init();
    }
    public void init()
    {
        setLayout(null);
        setBackground(null);
        setBounds(new Rectangle(50, 0, this.DEFALUT_WIDTH - 220, 1000));
        
        Font font0 = new Font("Segoe UI",Font.PLAIN,13);
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
/****************************** PHẦN HIỂN THỊ THÔNG TIN ******************************************/

        JPanel itemView = new JPanel(null);
        itemView.setBounds(new Rectangle(30, 40, this.DEFALUT_WIDTH - 220 , 180));
        itemView.setBackground(null);
        
        /******** Tao Cac Label & TextField ************************/
        JLabel lbMaNCC = new JLabel("Mă NCC");
        txtMaNCC = new JTextField("");
        lbMaNCC.setBounds(new Rectangle(50,0,200,30));
        lbMaNCC.setFont(font0);
        txtMaNCC.setBounds(new Rectangle(130,0,240,30));
        
        JLabel lbTenNCC = new JLabel("Tên NCC");
        txtTenNCC = new JTextField("");
        lbTenNCC.setBounds(new Rectangle(400,0,100,30));
        lbTenNCC.setFont(font0);
        txtTenNCC.setBounds(new Rectangle(500,0,220,30));
     
        JLabel lbIDNL = new JLabel("Mã NL");
        txtIDNL = new JTextField("");
        lbIDNL.setBounds(new Rectangle(50,40,100,30));
        lbIDNL.setFont(font0);
        txtIDNL.setBounds(new Rectangle(130,40,200,30));
        btnNL = new JButton("...");
        btnNL.setBounds(new Rectangle(340,40,30,30));
        btnNL.addActionListener(this);
        itemView.add(btnNL);
        
        JLabel lbDienThoai = new JLabel("Số Điện thoại");
        txtDienThoai = new JTextField("");
        lbDienThoai.setBounds(new Rectangle(410,40,100,30));
        lbDienThoai.setFont(font0);
        txtDienThoai.setBounds(new Rectangle(500,40,220,30));
       
//        JLabel lbSoFax = new JLabel("Số Fax");
//        txtSoFax = new JTextField("");
//        lbSoFax.setBounds(new Rectangle(400,40,200,30));
//        lbSoFax.setFont(font0);
//        txtSoFax.setBounds(new Rectangle(500,40,220,30));
        
        JLabel lbDiaChi = new JLabel("Địa chỉ");
        txtDiaChi = new JTextField("");
        lbDiaChi.setBounds(new Rectangle(50,80,200,30));
        lbDiaChi.setFont(font0);
        txtDiaChi.setBounds(new Rectangle(130,80,500,30));
        
        // THÊM VÀO PHẦN HIỂN THỊ
        itemView.add(lbMaNCC);
        itemView.add(txtMaNCC);
        itemView.add(lbTenNCC);
        itemView.add(txtTenNCC);
        itemView.add(lbDiaChi);
        itemView.add(txtDiaChi);
        itemView.add(lbDienThoai);
        itemView.add(txtDienThoai);
        itemView.add(txtIDNL);
        itemView.add(lbIDNL);
//        itemView.add(lbSoFax);
//        itemView.add(txtSoFax);
        
        add(itemView);
        
        /**************** TẠO CÁC BTN THÊM ,XÓA, SỬA ********************/
        JButton btnAdd = new JButton(new ImageIcon("./src/image/addbtn.png"));
        btnAdd.setBounds(new Rectangle(750,0,130,45));
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        JButton btnEdit = new JButton(new ImageIcon("./src/image/suabtn.png"));
        btnEdit.setBounds(new Rectangle(750,55,130,45));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
       
        
        JButton btnDelete = new JButton(new ImageIcon("./src/image/xoabtn.png"));
        btnDelete.setBounds(new Rectangle(750,110,130,45));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        itemView.add(btnAdd);
        itemView.add(btnEdit);
        itemView.add(btnDelete);
        
        
        
        JButton btnConfirm= new JButton(new ImageIcon("./src/image/xacnhan.png"));
        btnConfirm.setVisible(false);
        btnConfirm.setBounds(new Rectangle(750,0,130,45));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnBack = new JButton(new ImageIcon("./src/image/trolai.png"));
        btnBack.setVisible(false);
        btnBack.setBounds(new Rectangle(750,55,130,45));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        itemView.add(btnConfirm);
        itemView.add(btnBack);
        
        // MouseClick btnADD
        btnAdd.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                EditOrAdd = true;
                
                cleanView();
                
                btnAdd.setVisible(false);
                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                
                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
//                btnFile.setVisible(true);
                
                tbl.clearSelection();
                tbl.setEnabled(false);
            }
        });
        
        // MouseClick btnDelete
        btnDelete.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {   
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa","Alert",JOptionPane.YES_NO_OPTION);
                if(i == 0)
                {
                    nccBUS.deleteNCC(txtMaNCC.getText());
                    cleanView();
                    tbl.clearSelection();
                    outModel(model, nccBUS.getList());
                }
            }
        });
        
        // MouseClick btnEdit
        btnEdit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                
                if(txtMaNCC.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần sửa !!!");
                    return;
                }
                
                EditOrAdd = false;
                
                
                txtMaNCC.setEditable(false);
                
                btnAdd.setVisible(false);
                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                
                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
//                btnFile.setVisible(true);
                
//                tbl.clearSelection();
                tbl.setEnabled(false);
            }
        });
        
        
        //MouseClick btnBack
        btnBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                cleanView();
                
                btnAdd.setVisible(true);
                btnEdit.setVisible(true);
                btnDelete.setVisible(true);
                
                btnConfirm.setVisible(false);
                btnBack.setVisible(false);
//                btnFile.setVisible(false);
                
                tbl.setEnabled(true);
            }
        });
        
        //MouseClick btnConfirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                int i;
                if(EditOrAdd) //Thêm Sản Phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm NCC","",JOptionPane.YES_NO_OPTION);
                    if(i == 0)
                    {
                        //Lấy dữ liệu từ TextField
                        String maNCC = txtMaNCC.getText();
                        String tenNCC = txtTenNCC.getText();
                        String maNL = txtIDNL.getText();
                        String diaChi = txtDiaChi.getText();
                        String dienThoai = txtDienThoai.getText();
//                        String soFax = txtSoFax.getText();
                        if(nccBUS.checkMancc(maNCC))
                        {
                            JOptionPane.showMessageDialog(null, "Mã NCC đă tồn tại !!!");
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        NhaCungCapDTO ncc = new NhaCungCapDTO(maNCC, tenNCC,maNL, dienThoai, diaChi/*, soFax*/);
                        nccBUS.addNCC(ncc);

                        outModel(model, nccBUS.getList());// Load lại table

//                        saveIMG();// Lưu hình ảnh 

                        cleanView();
                    }
                }
                else    // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa thông tin NCC","",JOptionPane.YES_NO_OPTION);
                    if(i == 0)
                    {
                        //Lấy dữ liệu từ TextField
                        String maNCC = txtMaNCC.getText();
                        String tenNCC = txtTenNCC.getText();
                        String maNL = txtIDNL.getText();
                        String diaChi = txtDiaChi.getText();
                        String dienThoai = txtDienThoai.getText();
//                        String soFax = txtSoFax.getText();

                        //Upload sản phẩm lên DAO và BUS
                        NhaCungCapDTO ncc = new NhaCungCapDTO(maNCC, tenNCC,maNL, dienThoai, diaChi/*, soFax*/);
                        nccBUS.setNCC(ncc);
                        
                        outModel(model, nccBUS.getList());// Load lại table
                        
//                        saveIMG();// Lưu hình ảnh 
                        
                        JOptionPane.showMessageDialog(null, "Sửa thành công","Thành công",JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                }
                
            }
        });
/***************************************************************/
/************************************************************************************************************/       

/************** TẠO MODEL VÀ HEADER *********************/
        Vector header = new Vector();
        header.add("Mã NCC");
        header.add("Tên NCC");
        header.add("Mã NL");
        header.add("Địa chỉ");
        header.add("SĐT");
//        header.add("Số Fax");
        model = new DefaultTableModel(header,5);
        tbl = new JTable(model);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);
        tbl.setRowSorter(rowSorter);
        listNCC(); //Đọc từ database lên table 
        
/*********************************************************/
        
/**************** TẠO TABLE ************************************************************/

        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(50);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(60);


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
        scroll.setBounds(new Rectangle(30, 220, this.DEFALUT_WIDTH - 400 , 450));
        scroll.setBackground(null);
        
        add(scroll);
/*****************************************************************************************/

        
        tbl.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e)
             {
                int i = tbl.getSelectedRow();
                txtMaNCC.setText(tbl.getModel().getValueAt(i, 0).toString());
                txtTenNCC.setText(tbl.getModel().getValueAt(i, 1).toString());
                txtIDNL.setText(tbl.getModel().getValueAt(i, 2).toString()); 
                txtDiaChi.setText(tbl.getModel().getValueAt(i, 3).toString());
                txtDienThoai.setText( tbl.getModel().getValueAt(i, 4).toString());        
             }
        });
        
        
/*********************** SORT TABLE *****************************/
        JPanel searchBox = new JPanel(null);
        searchBox.setBackground(null);
        searchBox.setBounds(new Rectangle(50,120,530,30)); 
        searchBox.setBorder(createLineBorder(Color.BLACK)); //Chỉnh viền 
        //PHẦN CHỌN SEARCH
        JComboBox cmbChoice = new JComboBox();
        cmbChoice.setEditable(true);
        cmbChoice.setFont(new Font("Segoe UI",Font.PLAIN,14));
        cmbChoice.addItem("Mã NCC");
        cmbChoice.addItem("Tên NCC");
        cmbChoice.addItem("Mã NL");
        cmbChoice.addItem("Địa chỉ");
        cmbChoice.addItem("SĐT");
//        cmbChoice.addItem("FAX");
        cmbChoice.setBounds(new Rectangle(0,0,120,30));
        
        //Phần TextField
        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(new Rectangle(125,0,400,30));
        txtSearch.setBorder(null);
        txtSearch.setOpaque(false);
        txtSearch.setFont(new Font("Segoe UI",Font.PLAIN,15));
        
        // Custem Icon search
        JLabel searchIcon = new JLabel(new ImageIcon("./src/image/search_25px.png"));
        searchIcon.setBounds(new Rectangle(485,-10,50,50));
        searchIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add tất cả vào search box
        searchBox.add(cmbChoice);
        searchBox.add(txtSearch);
        searchBox.add(searchIcon);

        //bắt sự kiện Focus vào search box
        txtSearch.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e) 
            {
                searchIcon.setIcon(new ImageIcon("./src/image/search_25px_focus.png")); //Đổi màu icon
                searchBox.setBorder(createLineBorder(new Color(52,152,219))); // Đổi màu viền 
            }
            public void focusLost(FocusEvent e) //Trờ về như cũ
            {
                searchIcon.setIcon(new ImageIcon("./src/image/search_25px.png"));
                searchBox.setBorder(createLineBorder(Color.BLACK));
            }
        });
        txtSearch.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearch.getText();
                int choice = cmbChoice.getSelectedIndex();
                
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+ text +"", choice));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();
                int choice = cmbChoice.getSelectedIndex();
                
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+ text +"", choice));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        itemView.add(searchBox);
/******************************************************************/
    }
    public void cleanView() //Xóa trắng các TextField
    {
        txtMaNCC.setEditable(false);
        txtMaNCC.setText(nccBUS.remindIDNCC());
        txtTenNCC.setText("");
        txtDiaChi.setText("");
        txtDienThoai.setText("");
        txtIDNL.setText("");
 
    }
    public void outModel(DefaultTableModel model , ArrayList<NhaCungCapDTO> ncc) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for(NhaCungCapDTO n:ncc)
        {
            data = new Vector();
            data.add(n.getIDNCC());
            data.add(n.getTenNCC());
            data.add(n.getIDNL());
            data.add(n.getDiaChi());
            data.add(n.getSDT());
//            data.add(n.getSoFax());
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public void listNCC() // Chép ArrayList lên table
    {
        if(nccBUS.getList()== null)nccBUS.listNCC();
        ArrayList<NhaCungCapDTO> ncc = nccBUS.getList();
//        model.setRowCount(0);
        outModel(model,ncc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnNL) // Suggest Nhan Vien
        {
            SuggestNguyenLieu rm = new SuggestNguyenLieu();
            String s = rm.getTextFieldContent();
            txtIDNL.setText(s);
        }//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

