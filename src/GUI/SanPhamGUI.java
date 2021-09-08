/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


//import BUS.LoaiBUS;
//import BUS.NsxBUS;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane; 
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import DTO.MonAnDTO;
import BUS.MonAnBUS;
//import DTO.LoaiDTO;
//import DTO.NsxDTO;
import java.awt.Choice;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Shadow
 */
public class SanPhamGUI extends JPanel implements KeyListener{
    private MonAnBUS manBUS = new MonAnBUS();
    private JTable tbl;
    private BufferedImage i = null;//Hình ảnh chọn từ file
    private JLabel img;
    private String imgName = "null";
    private JTextField txtId,txtTenSP,txtSl,txtGia,txtDVT,txtNSX,txtLoai,txtSearch;
    private DefaultTableModel model;
    private int DEFALUT_WIDTH;
    private boolean EditOrAdd = true;//Cờ cho button Cofirm True:ADD || False:Edit
    private JTextField sortTenSP;
    private JTextField txtMaxPrice;
    private JTextField sortMaSP;
    private JTextField txtMinPrice;
    private JTextField sortMaLoai;
    private JButton    productInfo;
    private JComboBox cmbLoai;
    private JComboBox cmbNSX;
    private JComboBox cmbSortLoai;
    private JComboBox cmbSortNSX;
    private JComboBox cmbDVT;
    
    
    //        

    public SanPhamGUI(int width)
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
/****************************** PHẦN HIỂN THỊ THÔNG TIN ******************************************/

        JPanel ItemView = new JPanel(null);
        ItemView.setBounds(new Rectangle(30, 20, this.DEFALUT_WIDTH - 220 , 250));
        ItemView.setBackground(Color.WHITE);
        
        /******** Tao Cac Label & TextField ************************/
        JLabel lbId = new JLabel("Mă Sản Phẩm");
        lbId.setBounds(new Rectangle(250,0,200,30));
        lbId.setFont(font0);
        txtId = new JTextField("");
        txtId.setBounds(new Rectangle(350,0,220,30));
        txtId.setFont(font0);

        JLabel lbName = new JLabel("Tên Sản Phẩm");
        lbName.setBounds(new Rectangle(250,40,200,30));
        lbName.setFont(font0);
        txtTenSP = new JTextField("");
        txtTenSP.setBounds(new Rectangle(350,40,220,30));
        txtTenSP.setFont(font0);

        JLabel lbSl = new JLabel("Số lượng");
        lbSl.setBounds(new Rectangle(250,80,200,30));
        lbSl.setFont(font0);
        txtSl = new JTextField("");
        txtSl.setBounds(new Rectangle(350,80,220,30));
        txtSl.setFont(font0);

        JLabel lbGia = new JLabel("Đơn giá (VNĐ)");
        lbGia.setBounds(new Rectangle(250,120,200,30));
        lbGia.setFont(font0);
        txtGia = new JTextField("");
        txtGia.setBounds(new Rectangle(350,120,220,30));
        txtGia.setFont(font0);

        JLabel lbDVT = new JLabel("Đơn vị tính");
        lbDVT.setBounds(new Rectangle(250,160,200,30));
        lbDVT.setFont(font0);
        String[] DVT ={"dĩa","tô","con"};
        cmbDVT = new JComboBox(DVT);
        cmbDVT.setBounds(new Rectangle(350,160,220,30));
        cmbDVT.setFont(font0);
        
        txtDVT= new JTextField("");
//        txtDVT.setBounds(new Rectangle(400,200,220,30));
//        txtDVT.setFont(font0);


//        txtNSX = new JTextField("");
//        txtNSX.setBounds(new Rectangle(370,250,80,30));
//        txtNSX.setFont(font0);

        JLabel lbLoai = new JLabel("Loại");
        lbLoai.setBounds(new Rectangle(250,200,50,30));
        lbLoai.setFont(font0);
        String[] monan ={"món chính","ăn kèm"};
        cmbLoai = new JComboBox(monan);
//        cmbLoai.setEditable(true);
        cmbLoai.setFont(font0);
        cmbLoai.setBounds(new Rectangle(350,200,100,30));
//        txtLoai = new JTextField("");
//        txtLoai.setBounds(new Rectangle(540,250,80,30));
//        txtLoai.setFont(font0);
        JButton productInfo = new JButton("Chi tiết món");
        productInfo.setBounds(new Rectangle(450,200,120,30));
        productInfo.setFont(font0);
        
        img = new JLabel("Image");
        img.setBorder(createLineBorder(Color.BLACK));
        img.setBounds(new Rectangle(0,0,200,230));
        
        // THÊM VÀO PHẦN HIỂN THỊ
        ItemView.add(img);
        ItemView.add(lbId);
        ItemView.add(txtId);
        ItemView.add(lbName);
        ItemView.add(txtTenSP);
        ItemView.add(lbSl);
        ItemView.add(txtSl);
        ItemView.add(lbGia);
        ItemView.add(txtGia);
        ItemView.add(lbDVT);
        ItemView.add(cmbDVT);
        ItemView.add(productInfo);
//        ItemView.add(txtDVT);
        //ItemView.add(lbNSX);
//        ItemView.add(txtNSX);
        ItemView.add(lbLoai);
        ItemView.add(cmbLoai);
//        ItemView.add(txtLoai);
        /************************************************************/
        
        /**************** TẠO CÁC BTN THÊM ,XÓA, SỬA ********************/
        JButton btnAdd = new JButton(new ImageIcon("./src/image/addbtn.png"));
        btnAdd.setBounds(new Rectangle(620,0,130,43));
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        JButton btnEdit = new JButton(new ImageIcon("./src/image/suabtn.png"));
        btnEdit.setBounds(new Rectangle(620,70,130,43));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
       
        
        JButton btnDelete = new JButton(new ImageIcon("./src/image/xoabtn.png"));
        btnDelete.setBounds(new Rectangle(620,140,130,43));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        ItemView.add(btnAdd);
        ItemView.add(btnEdit);
        ItemView.add(btnDelete);
        
        
        
        JButton btnConfirm= new JButton(new ImageIcon("./src/image/xacnhan.png"));
        btnConfirm.setVisible(false);
        btnConfirm.setBounds(new Rectangle(620,0,130,43));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnBack = new JButton(new ImageIcon("./src/image/trolai.png"));
        btnBack.setVisible(false);
        btnBack.setBounds(new Rectangle(620,70,130,43));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnFile = new JButton(new ImageIcon("./src/image/chonanh.png"));
        btnFile.setBounds(new Rectangle(620,140,130,43));
        btnFile.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnXuatExcel = new JButton(new ImageIcon("./src/image/xuatexcel.png"));
        btnXuatExcel.setBounds(new Rectangle(820,0,130,43));
        btnXuatExcel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnNhapExcel = new JButton(new ImageIcon("./src/image/nhapexcel.png"));
        btnNhapExcel.setBounds(new Rectangle(820,60,130,43));
        btnNhapExcel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        ItemView.add(btnConfirm);
        ItemView.add(btnBack);
        ItemView.add(btnFile);
        ItemView.add(btnXuatExcel);
        ItemView.add(btnNhapExcel);
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
                btnFile.setVisible(true);
                
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
                    manBUS.deleteSP(txtId.getText());
                    cleanView();
                    tbl.clearSelection();
                    outModel(model, manBUS.getList());
                }
            }
        });
        
        // MouseClick btnEdit
        btnEdit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                if(txtId.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần sửa !!!");
                    return;
                }
                
                EditOrAdd = false;
                
                
                txtId.setEditable(false);
                
                btnAdd.setVisible(false);
                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                
                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
                btnFile.setVisible(true);
                
//                tbl.clearSelection();
                tbl.setEnabled(false);
            }
        });
        
        // MouseClick btnFile
        btnFile.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG images", "jpg", "png");
                fc.setFileFilter(filter);
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) 
                {
                    try {
                        File file = fc.getSelectedFile(); //Lấy URL hình
                        i = ImageIO.read(file); // Lấy hình
                        imgName = txtId.getText().concat(".jpg"); //Tên hình
                        
                        // Thay đổi hình hiển thị
                        img.setText("");
                        img.setIcon(new ImageIcon(i.getScaledInstance(200, 230, Image.SCALE_DEFAULT)));
                        
                        revalidate();
                        repaint();
                    } catch (IOException ex) {
                        Logger.getLogger(SanPhamGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
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
                btnFile.setVisible(false);
                
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
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm sản phẩm","",JOptionPane.YES_NO_OPTION);
                    if(i == 0)
                    {
                        //Lấy dữ liệu từ TextField
                        String maSP = txtId.getText();
                        String tenSP = txtTenSP.getText();
                        int sl = Integer.parseInt(txtSl.getText());
                        int gia = Integer.parseInt(txtGia.getText());
                        String DVT = cmbDVT.getSelectedItem().toString();
                        String Loai = cmbLoai.getSelectedItem().toString();
                        String IMG = imgName;
                        if(manBUS.checkIDMA(maSP))
                        {
                            JOptionPane.showMessageDialog(null, "Mã sản phẩm đă tồn tại !!!");
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        MonAnDTO sp = new MonAnDTO(maSP, tenSP, DVT, Loai, IMG, sl, gia);
                        manBUS.addMA(sp);

                        outModel(model, manBUS.getList());// Load lại table

                        saveIMG();// Lưu hình ảnh 

                        cleanView();
                    }
                }
                else    // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa sản phẩm","",JOptionPane.YES_NO_OPTION);
                    if(i == 0)
                    {
                        //Lấy dữ liệu từ TextField
                        String maSP = txtId.getText();
                        String tenSP = txtTenSP.getText();
                        int sl = Integer.parseInt(txtSl.getText());
                        int gia = Integer.parseInt(txtGia.getText());
                        String DVT = cmbDVT.getSelectedItem().toString();
                        String Loai = cmbLoai.getSelectedItem().toString();
                        String IMG = imgName;

                        //Upload sản phẩm lên DAO và BUS
                        MonAnDTO sp = new MonAnDTO(maSP, tenSP, DVT, Loai, IMG, sl, gia);
                        manBUS.setMA(sp);
                        
                        outModel(model, manBUS.getList());// Load lại table
                        
                        saveIMG();// Lưu hình ảnh 
                        
                        JOptionPane.showMessageDialog(null, "Sửa thành công","Thành công",JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                }
            }
        });
        
        
        btnXuatExcel.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {   
                manBUS.ExportExcelDatabase();
                JOptionPane.showMessageDialog(null, "Xuat file excel thanh cong");
            }
        });

         btnNhapExcel.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {   
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Excel", "xlsx");
                fc.setFileFilter(filter);
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) 
                {
                    File file = fc.getSelectedFile(); //Lấy URL
                    manBUS.ImportExcelDatabase(file);
                    manBUS.listMA();
                    outModel(model, manBUS.getList());
                    JOptionPane.showMessageDialog(null, "Nhap file excel thanh cong");
                }
            }
        });
        
        /****************************************************************/
        
/************************* PHẦN TABLE *************************************/
/************** TẠO MODEL VÀ HEADER *********************/
        Vector header = new Vector();
        header.add("Mă Sản Phẩm");
        header.add("Tên Sản Phẩm");
        header.add("Số lượng");
        header.add("Đơn Giá");
        header.add("Đơn Vị Tính");
        header.add("Loại");
        header.add("IMG"); 
        model = new DefaultTableModel(header,0)
        {
             public Class getColumnClass(int column)
             {
                 switch(column){
                     case 2:
                         return Integer.class;
                     case 3:
                         return Integer.class;
                     default:
                         return String.class;
                 }
             }
                        
        };
        tbl = new JTable(model);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);
        tbl.setRowSorter(rowSorter);
        listSP(); //Đọc từ database lên table 
/*********************************************************/
        
/**************** TẠO TABLE ************************************************************/

        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(140);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(50);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(5).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(6).setPreferredWidth(40);

        DefaultTableCellRenderer leftAlign = new DefaultTableCellRenderer();
        leftAlign.setHorizontalAlignment(JLabel.LEFT);
        tbl.getColumnModel().getColumn(2).setCellRenderer(leftAlign);
        tbl.getColumnModel().getColumn(3).setCellRenderer(leftAlign);
        
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
        scroll.setBounds(new Rectangle(30, 360, this.DEFALUT_WIDTH - 400 , 300));
        scroll.setBackground(null);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(5,100));
        add(scroll);
        add(ItemView);
/*****************************************************************************************/

        productInfo.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
               int i = tbl.getSelectedRow();
               if(tbl.getRowSorter() != null)
                {
                    i = tbl.getRowSorter().convertRowIndexToModel(i);
                }
               String manl = tbl.getModel().getValueAt(i,0).toString();
               NguyenLieuGUI nl = new NguyenLieuGUI(manl);
            }
        });
        tbl.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e)
             {
                int i = tbl.getSelectedRow();
                if(tbl.getRowSorter() != null)
                {
                    i = tbl.getRowSorter().convertRowIndexToModel(i);
                }
                imgName = tbl.getModel().getValueAt(i, 6).toString();
                Image newImage ;
                try{
                    newImage = new ImageIcon("./src/image/SanPham/"+imgName).getImage().getScaledInstance(200, 230, Image.SCALE_DEFAULT);
                }catch(NullPointerException E)
                {
                    newImage = new ImageIcon("./src/image/SanPham/NoImage.jpg").getImage().getScaledInstance(200, 230, Image.SCALE_DEFAULT); 
                }
                txtId.setText(tbl.getModel().getValueAt(i, 0).toString());
                txtTenSP.setText(tbl.getModel().getValueAt(i, 1).toString());
                txtSl.setText(tbl.getModel().getValueAt(i, 2).toString()); 
                txtGia.setText(tbl.getModel().getValueAt(i, 3).toString());
                cmbDVT.setSelectedItem(tbl.getModel().getValueAt(i, 4).toString());
                cmbLoai.setSelectedItem(tbl.getModel().getValueAt(i,5).toString());
                img.setText("");
                img.setIcon(new ImageIcon(newImage));
                
             }
        });
/********************* THANH SEARCH ***********************************************/
        
//         Tạo Search Box
        JPanel searchBox = new JPanel(null);
        searchBox.setBackground(null);
        searchBox.setBounds(new Rectangle(620,200,250, 30)); 
        searchBox.setBorder(createLineBorder(Color.BLACK)); //Chỉnh viền 
        
        //Phần TextField 
        txtSearch = new JTextField();
        txtSearch.setBounds(new Rectangle(5,0,180,30));
        txtSearch.setBorder(null);
        txtSearch.setOpaque(false);
        txtSearch.setFont(new Font("Segoe UI",Font.PLAIN,15));
        
        // Custem Icon search
        JButton searchIcon = new JButton("Tìm");
        searchIcon.setBounds(new Rectangle(200,-9,50,50));
        searchIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add tất cả vào search box
        searchBox.add(searchIcon);
        searchBox.add(txtSearch);

        //bắt sự kiện Focus vào search box
        txtSearch.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e) 
            {
                searchBox.setBorder(createLineBorder(new Color(52,152,219))); // Đổi màu viền 
            }
            public void focusLost(FocusEvent e) //Trờ về như cũ
            {
                searchBox.setBorder(createLineBorder(Color.BLACK));
            }
        });
        txtSearch.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)^"+ text +".*", 1));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)^"+ text +".*", 1));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        ItemView.add(searchBox);
/*********************************************************************************/
/*********************** PHẦN SEARCH TABLE *****************************/
        JPanel sort = new JPanel(null);
        sort.setBackground(null);
        sort.setBounds(30,265,this.DEFALUT_WIDTH - 400,100);

        JLabel sortTitle = new JLabel("--------------------------------------------------------------------------- TÌM KIẾM THÔNG TIN ---------------------------------------------------------------------------",JLabel.CENTER); // Mỗi bên 74 dấu ( - )
        sortTitle.setFont(font1);
        sortTitle.setBounds(new Rectangle(0,0,this.DEFALUT_WIDTH - 400,30));
        sort.add(sortTitle);

        /******** SORT MAKH **************/
        JLabel lbSortMaSP = new JLabel("Mă SP :");
        lbSortMaSP.setFont(font0);
        lbSortMaSP.setBounds(0,40,50,30);
        sort.add(lbSortMaSP);

        sortMaSP = new JTextField();
        sortMaSP.setFont(font0);
        sortMaSP.setBounds(new Rectangle(50,42,100,30));
        sortMaSP.addKeyListener(this);
        sort.add(sortMaSP);
        /*************************************/

        /******** SORT MALOAI **************/
        JLabel lbSortMaLoai = new JLabel("Loại :");
//        cmbLoai.setEditable(true);
        cmbLoai.setFont(font0);
        lbSortMaLoai.setFont(font0);
        lbSortMaLoai.setBounds(170,40,40,30);
        sort.add(lbSortMaLoai);
        
        cmbSortLoai = new JComboBox();
//        cmbSortLoai.setEditable(true);
        cmbSortLoai.setFont(font0);
        cmbSortLoai.setBounds(new Rectangle(210,42,110,30));
        cmbSortLoai.addItem("Không");
        cmbSortLoai.addItem("món chính");
        cmbSortLoai.addItem("ăn kèm");
        cmbSortLoai.addKeyListener(this);
        sort.add(cmbSortLoai);
        
        /*************************************/
        
        
        /************ SORT THEO GIÁ ***************/
        JLabel sortPrice = new JLabel("Giá (VNĐ) :");
        sortPrice.setFont(font0);
        sortPrice.setBounds(350,40,70,30);
        sort.add(sortPrice);

        txtMinPrice = new JTextField();
        txtMinPrice.setFont(font0);
        txtMinPrice.setBounds(new Rectangle(450,42,100,30));
        txtMinPrice.addKeyListener(this);
        sort.add(txtMinPrice);

        JSeparator sepPrice = new JSeparator(JSeparator.HORIZONTAL);
        sepPrice.setBounds(570, 56, 10, 6);
        sort.add(sepPrice);

        txtMaxPrice = new JTextField();
        txtMaxPrice.setFont(font0);
        txtMaxPrice.setBounds(new Rectangle(600,42,100,30));
        txtMaxPrice.addKeyListener(this);
        sort.add(txtMaxPrice);
        /******************************************/

        JButton btnSearch = new JButton("Tìm");
        btnSearch.setBounds(new Rectangle(720,42,80,30));
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSearch.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e)
           {
               search();
           }
        });
        sort.add(btnSearch);
        
        add(sort);
/*******************************************************************/

    }

    public void saveIMG()
    {
        try {
            if(i != null)
            {
                File save = new File("src/image/product/"+ imgName);//Tạo file
                ImageIO.write(i,"jpg",save); // Lưu hình i vào đường dẫn file save

                i = null; //Xóa hình trong bộ nhớ 
            }
        } catch (IOException ex) {
            Logger.getLogger(SanPhamGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void outModel(DefaultTableModel model , ArrayList<MonAnDTO> sp) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for(MonAnDTO s:sp)
        {
            data = new Vector();
            data.add(s.getIDMA());
            data.add(s.getTenMA());
            data.add(s.getSoLuong());
            data.add(s.getDonGia());
            data.add(s.getDVT());
            data.add(s.getLoai());
            data.add(s.getImg());
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public void cleanView() //Xóa trắng các TextField
    {
        txtId.setEditable(true);

        txtId.setText("");
        txtTenSP.setText("");
        txtSl.setText("");
        txtGia.setText("");
        txtDVT.setText("");
//        txtNSX.setText("");
//        txtLoai.setText("");
        
        img.setIcon(null);
        img.setText("Image");
        
        imgName = "null";
    }
    public void listSP() // Chép ArrayList lên table
    {
        if(manBUS.getList()== null)manBUS.listMA();
        ArrayList<MonAnDTO> sp = manBUS.getList();
        model.setRowCount(0);
        outModel(model,sp);
    }
    public void listLoai(JComboBox cmb)
    {
    }
    public void addCombo(JComboBox cmb,ArrayList list)
    {
        for(Object a:list)
        {
            cmb.addItem(a);
        }
    }

    public void search()
    {
        String masp = sortMaSP.getText();
        String maloai = "";
        if(cmbSortLoai.getSelectedIndex() != 0)
        {
             String Loaimon = cmbSortLoai.getSelectedItem().toString();
             maloai = Loaimon;
             System.out.println(Loaimon);
        }
        int max = txtMaxPrice.getText().equals("") ? 999999 : Integer.parseInt(txtMaxPrice.getText());
        int min = txtMinPrice.getText().equals("") ? 0      : Integer.parseInt(txtMinPrice.getText());

        outModel(model,manBUS.searchMonAn(masp, maloai, max, min));
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        Object a = e.getSource();
        if(a.equals(sortMaSP) || a.equals(txtMinPrice) || a.equals(txtMaxPrice) 
                || a.equals(cmbSortNSX)
                || a.equals(cmbSortLoai))
        {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                search();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
