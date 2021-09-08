/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import BUS.PhieuNhapHangBUS;
import DTO.PhieuNhapHangDTO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Shadow
 */
public class PhieuNhapHangGUI extends JPanel implements ActionListener,FocusListener,MouseListener{
    private PhieuNhapHangBUS nhBUS = new PhieuNhapHangBUS();
    
    private String id ;
    private int DWIDTH;
    private JTextField txtMaPN,txtMaNCC,txtMaNV;
    private JTextField txtMaNL;
    private JTextField txtNgayNhap;
    
    private DefaultTableModel model;
    private JTable tbl;
    private JTextField txtGiaNhap;
    private JTextField txtSoLuong;
    private JTextField txtTongTien;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnConfirm;
    private JButton btnBack;
    private JButton btnSuggestNL, btnSuggestNV;
    private JButton btnSuggestNCC;
    
    private boolean EditOrAdd = false;//Cờ cho button Cofirm  True:ADD || False:Edit
    public PhieuNhapHangGUI(int width)
    {
        this.DWIDTH = width;
        init();
    }
    public void init()
    {
        setSize(DWIDTH,700);
        setLayout(null);
        
        Font ftitle = new Font("Segoe UI",Font.BOLD,25);
        Font font0 = new Font("Segoe UI",Font.PLAIN,14);
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
        
        //HEADER
/***************** PHẦN HIỂN THỊ THÔNG TIN ***************************/
        JPanel itemView = new JPanel(null);
        itemView.setBounds(new Rectangle(0, 0,this.DWIDTH, 700));
        itemView.setBackground(Color.WHITE);
        
//        JLabel lbMaPN = new JLabel("Mã PN");
//        lbMaPN.setFont(font0);
//        lbMaPN.setBounds(20,20,100,30);
//        txtMaPN = new JTextField();
//        txtMaPN.setFont(font0);
//        txtMaPN.setBounds(new Rectangle(120,20,220,30));
//        itemView.add(lbMaPN);
//        itemView.add(txtMaPN);
        
        JLabel lbMaNCC = new JLabel("Mã NCC");
        lbMaNCC.setFont(font0);
        lbMaNCC.setBounds(20,70,100,30);
        txtMaNCC = new JTextField();
        txtMaNCC.setFont(font0);
        txtMaNCC.setBounds(new Rectangle(120,70,220,30));
        btnSuggestNCC = new JButton("...");
        btnSuggestNCC.setFont(font0);
        btnSuggestNCC.addActionListener(this);
        btnSuggestNCC.setBounds(new Rectangle(340,70,30,30));
        itemView.add(lbMaNCC);
        itemView.add(txtMaNCC);
        itemView.add(btnSuggestNCC);
        
        JLabel lbMaNV = new JLabel("Mã NV");
        lbMaNV.setFont(font0);
        lbMaNV.setBounds(20,120,100,30);
        txtMaNV = new JTextField();
        txtMaNV.setFont(font0);
        txtMaNV.setBounds(new Rectangle(120,120,220,30));
        btnSuggestNV = new JButton("...");
        btnSuggestNV.setFont(font0);
        btnSuggestNV.addActionListener(this);
        btnSuggestNV.setBounds(new Rectangle(340,120,30,30));
        itemView.add(lbMaNV);
        itemView.add(txtMaNV);
        itemView.add(btnSuggestNV);
        
        JLabel lbMaNL = new JLabel("Mã NL");
        lbMaNL.setFont(font0);
        lbMaNL.setBounds(20,170,100,30);
        txtMaNL = new JTextField();
        txtMaNL.setFont(font0);
        txtMaNL.setBounds(new Rectangle(120,170,220,30));
//        btnSuggestNL = new JButton("...");
//        btnSuggestNL.setFont(font0);
//        btnSuggestNL.addActionListener(this);
//        btnSuggestNL.setBounds(new Rectangle(340,170,30,30));
        itemView.add(lbMaNL);
        itemView.add(txtMaNL);
//        itemView.add(btnSuggestNL);
//        
        JLabel lbNgayNhap = new JLabel("Ngày Nhập");
        lbNgayNhap.setFont(font0);
        lbNgayNhap.setBounds(20,220,100,30);
        txtNgayNhap = new JTextField();
        txtNgayNhap.setFont(font0);
        txtNgayNhap.addFocusListener(this);
        txtNgayNhap.setBounds(new Rectangle(120,220,250,30));
        itemView.add(lbNgayNhap);
        itemView.add(txtNgayNhap);
        
        JLabel lbGiaNhap = new JLabel("Giá nhập");
        lbGiaNhap.setFont(font0);
        lbGiaNhap.setBounds(20,270,100,30);
        txtGiaNhap = new JTextField();
        txtGiaNhap.setFont(font0);
        txtGiaNhap.setBounds(new Rectangle(120,270,250,30));
        itemView.add(lbGiaNhap);
        itemView.add(txtGiaNhap);
        
        JLabel lbSoLuong = new JLabel("Số lượng");
        lbSoLuong.setFont(font0);
        lbSoLuong.setBounds(20,320,100,30);
        txtSoLuong = new JTextField();
        txtSoLuong.setFont(font0);
        txtSoLuong.setBounds(new Rectangle(120,320,250,30));
        itemView.add(lbSoLuong);
        itemView.add(txtSoLuong);
        
        JLabel lbTongTien = new JLabel("Tổng tiền");
        lbTongTien.setFont(font0);
        lbTongTien.setBounds(20,370,100,30);
        txtTongTien = new JTextField();
        txtTongTien.setFont(font0);
        txtTongTien.addFocusListener(this);
        txtTongTien.setBounds(new Rectangle(120,370,250,30));
        itemView.add(lbTongTien);
        itemView.add(txtTongTien);
        
/**************** TẠO CÁC BTN XÓA, SỬA, VIEW, IN BILL ********************/

        btnAdd = new JButton(new ImageIcon("./src/image/addbtn.png"));
        btnAdd.setBounds(new Rectangle(60,450,130,45));
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdd.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                isAddSet(true);
                EditOrAdd = true;
            }
        });
              
//        btnDelete = new JButton(new ImageIcon("./src/image/xoabtn.png"));
//        btnDelete.setBounds(new Rectangle(180,450,130,45));
//        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));    
//        btnDelete.addMouseListener(this);
        
        btnConfirm = new JButton(new ImageIcon("./src/image/xacnhan.png"));
        btnConfirm.setBounds(new Rectangle(20,450,130,45));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirm.setVisible(false);
        btnConfirm.addMouseListener(this);
        btnConfirm.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                
            }
        });
              
        btnBack = new JButton(new ImageIcon("./src/image/trolai.png"));
        btnBack.setBounds(new Rectangle(180,450,130,45));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));  
        btnBack.setVisible(false);
        btnBack.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                isAddSet(false);
                EditOrAdd = false;
            }
        });
        
        itemView.add(btnAdd);
//        itemView.add(btnDelete);
        
        itemView.add(btnConfirm);
        itemView.add(btnBack);
        
/*************************************************************************/
/**************** TẠO TABLE ************************************************************/

    /************** TẠO MODEL VÀ HEADER *********************************/
        Vector header = new Vector();
        header.add("IDPN");
        header.add("IDNCC");
        header.add("IDNV");
        header.add("IDNL");
        header.add("Ngày nhập");
        header.add("Số Lượng");
        header.add("Đơn giá");
        header.add("Tổng tiền");
        model = new DefaultTableModel(header,8);
        tbl = new JTable(model);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);
        tbl.setRowSorter(rowSorter);
        list();
        
    /*******************************************************************/
        

    /******** CUSTOM TABLE ****************/
    
        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(5).setPreferredWidth(50);
        tbl.getColumnModel().getColumn(6).setPreferredWidth(50);
        tbl.getColumnModel().getColumn(7).setPreferredWidth(50);

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
        scroll.setBounds(new Rectangle(400, 20, DWIDTH - 650 , 500));
        scroll.setBackground(null);
        
        itemView.add(scroll);
        
        add(itemView);
    /**************************************/
/*****************************************************************************************/
        tbl.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e)
             {
                int i = tbl.getSelectedRow();
                if(tbl.getRowSorter() != null)
                {
                    i = tbl.getRowSorter().convertRowIndexToModel(i);
                }
                id = tbl.getModel().getValueAt(i, 0).toString();
                txtMaNCC.setText(tbl.getModel().getValueAt(i, 1).toString());
                txtMaNV.setText(tbl.getModel().getValueAt(i, 2).toString());
                txtMaNL.setText(tbl.getModel().getValueAt(i, 3).toString());
                txtNgayNhap.setText(tbl.getModel().getValueAt(i, 4).toString()); 
                txtSoLuong.setText(tbl.getModel().getValueAt(i, 5).toString()); 
                txtGiaNhap.setText(tbl.getModel().getValueAt(i, 6).toString()); 
                txtTongTien.setText(tbl.getModel().getValueAt(i, 7).toString());             
             }
        });
        btnSuggestNV.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == btnSuggestNV) // Suggest Nhan Vien
                {
                    SuggestNhanVien rm = new SuggestNhanVien();
                    String s = rm.getTextFieldContent();
                    txtMaNV.setText(s);
                }
            }
        });
        btnSuggestNCC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == btnSuggestNCC) // Suggest NCC
                {
                    SuggestNCC rm = new SuggestNCC();
                    String s = rm.getMaNCC();
                    String t = rm.getMaNL();
                    txtMaNCC.setText(s);
                    txtMaNL.setText(t);
                }
            }
        });
/*********************************************************************/
    }
    public void outModel(DefaultTableModel model , ArrayList<PhieuNhapHangDTO> nhaphang) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for(PhieuNhapHangDTO nh : nhaphang)
        {
            data = new Vector();
            data.add(nh.getIDPN());
            data.add(nh.getIDNCC());
            data.add(nh.getIDNV());
            data.add(nh.getIDNL());
            data.add(nh.getNgayNhap());
            data.add(nh.getDonGia());
            data.add(nh.getSoLuong());
            data.add(nh.getTongTien());
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public void list() // Chép ArrayList lên table
    {
        if(nhBUS.getList() == null)nhBUS.list();
        ArrayList<PhieuNhapHangDTO> nh = nhBUS.getList();
//        model.setRowCount(0);
        outModel(model,nh);
    }
    public void isAddSet(boolean flag)
    {
        btnAdd.setVisible(!flag);
//        btnDelete.setVisible(!flag);
        
        btnConfirm.setVisible(flag);
        btnBack.setVisible(flag);
        
        clean();
    }

    public void clean()
    {
        txtMaNCC.setText("");
        txtMaNV.setText("");
        txtMaNL.setText("");
        txtNgayNhap.setText("");
        txtGiaNhap.setText("");
        txtSoLuong.setText("");
        txtTongTien.setText("");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj.equals(btnSuggestNL))
        {
            NguyenLieuGUI sp = new NguyenLieuGUI(txtMaNL.getText());
            String s = sp.getTextFieldContent();
            txtMaNL.setText(s.split("%")[0]);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        Object obj = e.getSource();
        if(obj.equals(txtNgayNhap))
        {
            if(EditOrAdd)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar date = Calendar.getInstance();
                txtNgayNhap.setText(sdf.format(date.getTime()));
            }
        }
        if(obj.equals(txtTongTien))
        {
            if(EditOrAdd)
            {
                try{
                    int sl = Integer.parseInt(txtSoLuong.getText());
                    int gia = Integer.parseInt(txtGiaNhap.getText());
                    int sum = sl*gia;
                    txtTongTien.setText(String.valueOf(sum));
                }catch(NumberFormatException ex)
                {
                    txtGiaNhap.requestFocus();
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập giá và số lượng ");
                }
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if(obj.equals(btnConfirm))
        {
            if(EditOrAdd)
            {
//                String maPN = txtMaPN.getText();
                String maNCC = txtMaNCC.getText();
                String maNV = txtMaNV.getText();
                String maNL = txtMaNL.getText();
                Date ngayNhap = Date.valueOf(txtNgayNhap.getText());
                int giaNhap = Integer.parseInt(txtGiaNhap.getText());
                int soLuong = Integer.parseInt(txtSoLuong.getText());
                int tongTien = Integer.parseInt(txtTongTien.getText());
                PhieuNhapHangDTO nh = new PhieuNhapHangDTO("", maNCC, maNV,maNL, ngayNhap, giaNhap, soLuong, tongTien);
                nhBUS.add(nh);
                outModel(model, nhBUS.getList());
                JOptionPane.showMessageDialog(null, "Nhập hàng thành công");
            }
            
            isAddSet(false);
            EditOrAdd = false;
        }
//        if(obj.equals(btnDelete))
//        {
////                String maPN = txtMaPN.getText();
//                String maNCC = txtMaNCC.getText();
//                String maNV = txtMaNV.getText();
//                String maNL = txtMaNL.getText();
//                Date ngayNhap = Date.valueOf(txtNgayNhap.getText());
//                int giaNhap = Integer.parseInt(txtGiaNhap.getText());
//                int soLuong = Integer.parseInt(txtSoLuong.getText());
//                int tongTien = Integer.parseInt(txtTongTien.getText());
//                PhieuNhapHangDTO nh = new PhieuNhapHangDTO("", maNCC, maNV,maNL, ngayNhap, giaNhap, soLuong, tongTien);
////            nhBUS.delete(id);
//            outModel(model, nhBUS.getList());
//            JOptionPane.showMessageDialog(null, "Xóa thành công");
//        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
