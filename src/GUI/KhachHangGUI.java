/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import BUS.KhachHangBUS;
import BUS.TaiKhoanBUS;
import DTO.KhachHangDTO;
import DTO.TaiKhoanDTO; 
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author phucthinh0211
 */
public class KhachHangGUI extends JPanel{
    private KhachHangBUS khBus = new KhachHangBUS();
    private DefaultTableModel model;
    private JComboBox cmbSearch,cmbCap,cmbCap1;
    private JTable tbl;
    private int default_width;
    private JLabel lbSearch,lbTheo,btnSearch;
    private JTextField txtMaKH,txtHoKH,txtTenKH,txtCap,txtSDT,txtSearch;
    private JButton btnThem,btnSua,btnXoa,btnClear,btnHuySearch;
    int idLast;
    public KhachHangGUI(int width){
        default_width = width;
        init();
    }
    public void init(){
        setLayout(null);
        setBackground(null);
        setBounds(new Rectangle(50, 0, this.default_width - 220, 1000));
        // tạo phần hiển thị thông tin
        JPanel KhView = new JPanel(null);
        KhView.setBounds(new Rectangle(30, 20, this.default_width - 220 , 250));
        KhView.setBackground(Color.WHITE);
        //tạo component trong panel

        JLabel lbMaKH = new JLabel("Mă khách hàng :");
        lbMaKH.setFont(new Font("Segoe UI",Font.PLAIN,15));
        txtMaKH = new JTextField("");
        lbMaKH.setBounds(new Rectangle(40,40,120,40));
        txtMaKH.setBounds(new Rectangle(170,40,200,40));
        txtMaKH.setFont(new Font("Segoe UI",Font.PLAIN,14));
        
        JLabel lbHoKH = new JLabel("Họ khách hàng :");
        lbHoKH.setFont(new Font("Segoe UI",Font.PLAIN,15));
        txtHoKH = new JTextField("");
        lbHoKH.setBounds(new Rectangle(40,110,120,40));
        txtHoKH.setBounds(new Rectangle(170,110,200,40));
        txtHoKH.setFont(new Font("Segoe UI",Font.PLAIN,14));
        
        JLabel lbTenKH = new JLabel("Tên khách hàng :");
        lbTenKH.setFont(new Font("Segoe UI",Font.PLAIN,15));
        txtTenKH = new JTextField("");
        lbTenKH.setBounds(new Rectangle(40,180,120,40));
        txtTenKH.setBounds(new Rectangle(170,180,200,40)); 
        txtTenKH.setFont(new Font("Segoe UI",Font.PLAIN,14));
         
        JLabel lbCap = new JLabel("Cấp :",SwingConstants.CENTER);
        lbCap.setFont(new Font("Segoe UI",Font.PLAIN,15));
        String []cap = {"vip","thường"};
        cmbCap = new JComboBox(cap);
        lbCap.setBounds(new Rectangle(390,40,100,40));
        cmbCap.setBounds(new Rectangle(490,40,180,40));
        cmbCap.setFont(new Font("Segoe UI",Font.PLAIN,14));
        
        JLabel lbSDT = new JLabel("Số điện thoại");
        lbSDT.setFont(new Font("Segoe UI",Font.PLAIN,15));
        txtSDT = new JTextField("");
        lbSDT.setBounds(new Rectangle(390,110,100,40));
        txtSDT.setBounds(new Rectangle(490,110,180,40));
        txtSDT.setFont(new Font("Segoe UI",Font.PLAIN,14));
        
        
        btnThem = new JButton(new ImageIcon("./src/image/addbtn.png"));
        btnThem.setFont(new Font("Segoe UI",Font.PLAIN,15));
        btnThem.setBounds(new Rectangle(750,20,130,43));
        btnThem.setBackground(new Color(52,152,219));
        btnThem.setFocusable(false);
        btnThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnThem.setForeground(new Color(52,152,219));
        
        btnSua = new JButton(new ImageIcon("./src/image/suabtn.png"));
        btnSua.setFont(new Font("Segoe UI",Font.PLAIN,15));
        btnSua.setBounds(new Rectangle(750,90,130,43));
        btnSua.setFocusable(false);
        btnSua.setBackground(new Color(52,152,219));
        btnSua.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSua.setForeground(new Color(52,152,219));
        
        btnXoa = new JButton(new ImageIcon("./src/image/xoabtn.png"));
        btnXoa.setFont(new Font("Segoe UI",Font.PLAIN,15));
        btnXoa.setBounds(new Rectangle(750,160,130,43));
        btnXoa.setBackground(new Color(52,152,219));
        btnXoa.setFocusable(false);
        btnXoa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnXoa.setForeground(new Color(52,152,219));
        
        
        btnClear = new JButton(new ImageIcon("./src/image/resetbtn.png"));
        btnClear.setFont(new Font("Segoe UI",Font.PLAIN,16));
        btnClear.setBounds(new Rectangle(490,180,130,40));
        btnClear.setBackground(new Color(52,152,219));
        btnClear.setFocusable(false);
        btnClear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnClear.setForeground(new Color(52,152,219));
        
        btnClear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanView();
            }
        }
        );
        
        btnThem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(khBus.checkNull(txtMaKH.getText(),txtHoKH.getText(),txtTenKH.getText(),txtSDT.getText())){
                int in = JOptionPane.showConfirmDialog(null, "Xác nhận thêm khách hàng ?","",JOptionPane.YES_NO_OPTION);
                if(in==0){
                    KhachHangDTO kh = new KhachHangDTO(txtMaKH.getText(),txtHoKH.getText(),txtTenKH.getText(),txtSDT.getText(),cmbCap.getSelectedItem().toString(),0);
                    khBus.add(kh);
                    JOptionPane.showMessageDialog(null, "Đã thêm thành công");
                    cleanView();
                    listKH();
                }
                else{
                    return;
                }
                }
            }
        }
        );
        
        btnXoa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int row =tbl.getSelectedRow();
                if(row != -1 && khBus.checkNull1(txtHoKH.getText(),txtTenKH.getText(),txtSDT.getText())){
                    int in = JOptionPane.showConfirmDialog(null, "Xác nhận xóa khách hàng ?","",JOptionPane.YES_NO_OPTION);
                    if(in==0){
                        
                        khBus.delete(txtMaKH.getText());
                        cleanView();
                        JOptionPane.showMessageDialog(null, "Đã xóa thành công");
                        listKH();
                    }
                    else{
                        return;
                    }
                }
                else if(row == -1){
                    JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng để xóa");
                }
            }
        }
        );
        
        btnSua.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int row =tbl.getSelectedRow();
                if(row != -1 && khBus.checkNull1(txtHoKH.getText(),txtTenKH.getText(),txtSDT.getText())){
                    int in = JOptionPane.showConfirmDialog(null, "Xác nhận sửa khách hàng ?","",JOptionPane.YES_NO_OPTION);
                    if(in==0){
                        KhachHangDTO kh = new KhachHangDTO(txtMaKH.getText(),txtHoKH.getText(),txtTenKH.getText(),txtSDT.getText(),cmbCap.getSelectedItem().toString(),0);
                        khBus.set(kh);
                        cleanView();
                        JOptionPane.showMessageDialog(null, "Đã sửa thành công");
                        listKH();
                    }
                    else{
                        return;
                    }
                }
                else if(row == -1){
                    JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng để sửa");
                }
            }
        }
        );
        
        //component of Search
        JPanel search = new JPanel(null);
        search.setBackground(null);
        search.setBounds(30,265,this.default_width - 400,80);

        JLabel searchTitle = new JLabel("------------------------------------------------------------------------------ TÌM KIẾM THÔNG TIN ------------------------------------------------------------------------------",JLabel.CENTER); // Mỗi bên 78 dấu ( - )
        searchTitle.setFont(new Font("Segoe UI",Font.PLAIN,12));
        searchTitle.setBounds(new Rectangle(0,0,this.default_width - 400,30));
        search.add(searchTitle);

        /******** SEARCH TXT **************/
        lbSearch = new JLabel("Tìm kiếm :");
        lbSearch.setBounds(20,40,70,30);
        lbSearch.setFont(new Font("Segoe UI",Font.PLAIN,14));
        search.add(lbSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(new Rectangle(100,42,100,30));
        txtSearch.setFont(new Font("Segoe UI",Font.PLAIN,14));
        search.add(txtSearch);
        
        lbTheo = new JLabel("Theo :");
        lbTheo.setBounds(240,40,40,30);
        lbTheo.setFont(new Font("Segoe UI",Font.PLAIN,14));
        search.add(lbTheo);

        String []theo = {"Họ KH","Tên KH","Số điện thoại"};
        cmbSearch = new JComboBox(theo);
        cmbSearch.setFont(new Font("Segoe UI",Font.PLAIN,14));
        cmbSearch.setBounds(new Rectangle(290,40,120,30));
        search.add(cmbSearch);
        
        String []cap1 = {"Cấp bậc","vip","thường"};
        cmbCap1 = new JComboBox(cap1);
        cmbCap1.setFont(new Font("Segoe UI",Font.PLAIN,14));
        cmbCap1.setBounds(new Rectangle(480,40,100,30));
        cmbCap1.setFont(new Font("Segoe UI",Font.PLAIN,14));
        search.add(cmbCap1);
        
        ImageIcon searchImg = new ImageIcon("./src/image/btnSearch_45px.png");
        btnSearch = new JLabel(searchImg);
        btnSearch.setBounds(new Rectangle(650,30,50,50));
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        search.add(btnSearch);
        btnSearch.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                ArrayList<KhachHangDTO> khSearch = khBus.search(txtSearch.getText(),cmbSearch.getSelectedIndex(),cmbCap1.getSelectedItem().toString());
                fillModel(model,khSearch);
                txtSearch.setText("");
                cmbCap.setSelectedIndex(0);
                cmbCap1.setSelectedIndex(0);
            }
        });
        
        btnHuySearch = new JButton("Hủy search");
        btnHuySearch.setFont(new Font("Segoe UI",Font.PLAIN,14));
        btnHuySearch.setBounds(new Rectangle(730,40,120,30));
        search.add(btnHuySearch);
        btnHuySearch.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                listKH();
            }
        }
        );
        
        //component cua Table
        Vector header = new Vector();
        header.add("Mã KH");
        header.add("Họ KH");
        header.add("Tên KH");
        header.add("Số điện thoại");
        header.add("Cấp bậc");
        model = new DefaultTableModel(header,0);
        tbl = new JTable(model);
        
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(50);

        
        // Custom table
        tbl.setFocusable(false);
        tbl.setIntercellSpacing(new Dimension(0,0));     
        tbl.setRowHeight(30);
        tbl.setShowVerticalLines(false);              
        tbl.getTableHeader().setOpaque(false);
        tbl.setFillsViewportHeight(true);
        tbl.getTableHeader().setBackground(new Color(255,153,0 ));
        tbl.getTableHeader().setForeground(Color.WHITE);
        tbl.getTableHeader().setFont(new Font("Segoe UI",Font.PLAIN,14));
        tbl.setFont(new Font("Segoe UI",Font.PLAIN,12));
        tbl.setSelectionBackground(new Color(51,204,51));
        listKH();
        JScrollPane scroll = new JScrollPane(tbl);
        scroll.setBounds(new Rectangle(30, 360, this.default_width - 400 , 300));
        scroll.setBackground(null);
        
        tbl.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int vitri = tbl.getSelectedRow();
                txtMaKH.setText(tbl.getModel().getValueAt(vitri,0).toString());
                txtHoKH.setText(tbl.getModel().getValueAt(vitri,1).toString());
                txtTenKH.setText(tbl.getModel().getValueAt(vitri,2).toString());
                txtSDT.setText(tbl.getModel().getValueAt(vitri,3).toString());
                cmbCap.setSelectedItem(tbl.getModel().getValueAt(vitri,4).toString());
            }
        }
        );
        
        KhView.add(lbMaKH);
        KhView.add(lbHoKH);
        KhView.add(lbTenKH);
        KhView.add(lbSDT);
        KhView.add(lbCap);
        KhView.add(txtMaKH);
        KhView.add(txtHoKH);
        KhView.add(txtTenKH);
        KhView.add(txtSDT);
        KhView.add(cmbCap);
        KhView.add(btnThem);
        KhView.add(btnSua);
        KhView.add(btnXoa);
        KhView.add(btnClear);
        
        add(search);
        add(KhView);
        add(scroll);
    }
    public void cleanView(){
        txtMaKH.setEditable(false);
        txtMaKH.setText(String.valueOf(idLast));
        txtHoKH.setText("");
        txtTenKH.setText("");
        txtSDT.setText("");
        cmbCap.setSelectedIndex(0);

    }
    public void fillModel(DefaultTableModel model , ArrayList<KhachHangDTO> kh) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for(KhachHangDTO n:kh)
        {
            data = new Vector();
            data.add(n.getIDK());
            data.add(n.getHo());
            data.add(n.getTen());
            data.add(n.getSDT());
            data.add(n.getCapBac());
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public void listKH() {
        if(khBus.getList()== null) khBus.list();
            ArrayList<KhachHangDTO> kh = khBus.getList();
    //        model.setRowCount(0);
        String id = kh.get(kh.size()-1).getIDK();
        idLast = Integer.parseInt(id)+1;
        txtMaKH.setText(String.valueOf(idLast));
        txtMaKH.setEditable(false);
        ArrayList<KhachHangDTO> kh2 = new ArrayList<>(); 
        for(KhachHangDTO kh1 : kh)
        {
            if( kh1.getTrangThai() == 0)
            {
                kh2.add(kh1);
            }
        }
        fillModel(model,kh2);
    }
}
