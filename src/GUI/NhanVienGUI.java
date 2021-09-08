/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import DTO.NhanVienDTO;
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
public class NhanVienGUI extends JPanel{
    private NhanVienBUS nvBus = new NhanVienBUS();
    private String imgName = "null";
    private DefaultTableModel model;
    private Choice sortChucVu;
    private JComboBox cmbChucVu,cmbChucVu1,cmbTheo;
    private JTable tbl;
    private BufferedImage i;
    private int default_width;
    private JLabel ava,lbSearch,lbTheo,btnSearch;
    private String avaURL;
    private JTextField txtMaNV,txtHoNV,txtTenNV,txtMucLuong,txtDiaChi,txtSDT,txtChucVu,txtSearch;
    private JButton btnThem,btnSua,btnXoa,btnUpload,btnClear,btnHuySearch;
    int idLast;
    private boolean tblClicked = false;
    public NhanVienGUI(int width){
        default_width = width;
        init();
    }
    public void init(){
        setLayout(null);
        setBackground(null);
        setBounds(new Rectangle(50, 0, this.default_width - 220, 1000));
        // tạo phần hiển thị thông tin
        JPanel NvView = new JPanel(null);
        NvView.setBounds(new Rectangle(30, 20, this.default_width - 220 , 250));
        NvView.setBackground(Color.WHITE);
        
        //tạo component trong panel
        
        ava = new JLabel("");
        ava.setBorder(createLineBorder(Color.BLACK));
        ava.setBounds(new Rectangle(0,0,200,230));
        JLabel lbMaNV = new JLabel("Mă nhân viên");
        lbMaNV.setFont(new Font("Segoe UI",Font.PLAIN,14));
        txtMaNV = new JTextField("");
        lbMaNV.setBounds(new Rectangle(230,0,200,30));
        txtMaNV.setBounds(new Rectangle(330,0,220,30));
        
        JLabel lbHoNV = new JLabel("Họ");
        lbHoNV.setFont(new Font("Segoe UI",Font.PLAIN,14));
        txtHoNV = new JTextField("");
        lbHoNV.setBounds(new Rectangle(230,40,200,30));
        txtHoNV.setBounds(new Rectangle(330,40,220,30));
        
        JLabel lbTenNV = new JLabel("Tên nhân viên");
        lbTenNV.setFont(new Font("Segoe UI",Font.PLAIN,14));
        txtTenNV = new JTextField("");
        lbTenNV.setBounds(new Rectangle(230,80,200,30));
        txtTenNV.setBounds(new Rectangle(330,80,220,30)); 
  
        JLabel lbMucLuong = new JLabel("Mức lương");
        lbMucLuong.setFont(new Font("Segoe UI",Font.PLAIN,14));
        txtMucLuong = new JTextField("");
        lbMucLuong.setBounds(new Rectangle(230,120,200,30));
        txtMucLuong.setBounds(new Rectangle(330,120,220,30));
        
        JLabel lbDiaChi = new JLabel("Địa chỉ");
        lbDiaChi.setFont(new Font("Segoe UI",Font.PLAIN,14));
        txtDiaChi = new JTextField("");
        lbDiaChi.setBounds(new Rectangle(230,160,200,30));
        txtDiaChi.setBounds(new Rectangle(330,160,220,30));
        
        JLabel lbSDT = new JLabel("Số điện thoại");
        lbSDT.setFont(new Font("Segoe UI",Font.PLAIN,14));
        txtSDT = new JTextField("");
        lbSDT.setBounds(new Rectangle(230,200,200,30));
        txtSDT.setBounds(new Rectangle(330,200,100,30));
        txtSDT.setFont(new Font("Segoe UI",Font.PLAIN,11));
        
        String []ChucVu = {"Chức vụ","phục vụ","kế toán","quản lí"};
        cmbChucVu = new JComboBox(ChucVu);
        cmbChucVu.setFont(new Font("Segoe UI",Font.PLAIN,14));
        cmbChucVu.setBounds(new Rectangle(440,200,110,30));
        
        btnThem = new JButton(new ImageIcon("./src/image/addbtn.png"));
        btnThem.setFont(new Font("Segoe UI",Font.PLAIN,14));
        btnThem.setBounds(new Rectangle(770,0,130,43));
        btnThem.setFocusable(false);
        btnThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnSua = new JButton(new ImageIcon("./src/image/suabtn.png"));
        btnSua.setFont(new Font("Segoe UI",Font.PLAIN,14));
        btnSua.setBounds(new Rectangle(770,70,130,43));
        btnSua.setFocusable(false);
        btnSua.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnXoa = new JButton(new ImageIcon("./src/image/xoabtn.png"));
        btnXoa.setFont(new Font("Segoe UI",Font.PLAIN,14));
        btnXoa.setBounds(new Rectangle(770,140,130,43));
        btnXoa.setFocusable(false);
        btnXoa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnUpload = new JButton(new ImageIcon("./src/image/chonanh.png"));
        btnUpload.setFont(new Font("Segoe UI",Font.PLAIN,16));
        btnUpload.setBounds(new Rectangle(570,110,130,43));
        btnUpload.setFocusable(false);
        btnUpload.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnClear = new JButton(new ImageIcon("./src/image/resetbtn.png"));
        btnClear.setFont(new Font("Segoe UI",Font.PLAIN,16));
        btnClear.setBounds(new Rectangle(570,30,130,43));
        btnClear.setFocusable(false);
        btnClear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        
        btnThem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nvBus.checkNull(txtMaNV.getText(),txtHoNV.getText(),txtTenNV.getText(),txtSDT.getText(),txtDiaChi.getText(),cmbChucVu.getSelectedItem().toString(),txtMucLuong.getText())){
                int in = JOptionPane.showConfirmDialog(null, "Xác nhận thêm nhân viên ?","",JOptionPane.YES_NO_OPTION);
                if(in==0){
                    NhanVienDTO nv = new NhanVienDTO(txtMaNV.getText(),txtHoNV.getText(),txtTenNV.getText(),txtSDT.getText(),txtDiaChi.getText(),cmbChucVu.getSelectedItem().toString(),Integer.parseInt(txtMucLuong.getText()),imgName,0);
                    nvBus.addNV(nv);
                    saveIMG();
                    JOptionPane.showMessageDialog(null, "Đã thêm thành công");
                    cleanView();
                    listNV();
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
                if(row != -1 && nvBus.checkNull1(txtHoNV.getText(),txtTenNV.getText(),txtSDT.getText(),txtDiaChi.getText(),cmbChucVu.getSelectedItem().toString(),txtMucLuong.getText())){
                    int in = JOptionPane.showConfirmDialog(null, "Xác nhận xóa nhân viên ?","",JOptionPane.YES_NO_OPTION);
                    if(in==0){
                        
                        nvBus.deleteNV(txtMaNV.getText());
                        cleanView();
                        JOptionPane.showMessageDialog(null, "Đã xóa thành công");
                        listNV();
                    }
                    else{
                        return;
                    }
                }
                else if(row == -1){
                    JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên để xóa");
                }
            }
        }
        );
        
        btnSua.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int row =tbl.getSelectedRow();
                if(row != -1 && nvBus.checkNull1(txtHoNV.getText(),txtTenNV.getText(),txtSDT.getText(),txtDiaChi.getText(),cmbChucVu.getSelectedItem().toString(),txtMucLuong.getText())){
                    int in = JOptionPane.showConfirmDialog(null, "Xác nhận sửa nhân viên ?","",JOptionPane.YES_NO_OPTION);
                    if(in==0){
                        NhanVienDTO nv = new NhanVienDTO(txtMaNV.getText(),txtHoNV.getText(),txtTenNV.getText(),txtSDT.getText(),txtDiaChi.getText(),cmbChucVu.getSelectedItem().toString(),Integer.parseInt(txtMucLuong.getText()),imgName,0);
                        nvBus.setNV(nv);
                        saveIMG();
                        cleanView();
                        JOptionPane.showMessageDialog(null, "Đã sửa thành công");
                        listNV();
                    }
                    else{
                        return;
                    }
                }
                else if(row == -1){
                    JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên để sửa");
                }
            }
        }
        );
        
        btnUpload.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) 
                {
                    try {
                        File file = fc.getSelectedFile(); //Lấy URL hình
                        i = ImageIO.read(file); // Lấy hình
                        imgName = txtMaNV.getText().concat(".jpg"); //Tên hình
                        
                        // Thay đổi hình hiển thị
                        ava.setText("");
                        ava.setIcon(new ImageIcon(i.getScaledInstance(200, 230, Image.SCALE_DEFAULT)));
                        revalidate();
                        repaint();
                    } catch (IOException ex) {
                        //Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        );
        
        btnClear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanView();
            }
        }
        );
        
        //component cua Table
        Vector header = new Vector();
        header.add("Mã NV");
        header.add("Họ NV");
        header.add("Tên NV");
        header.add("Lương NV");
        header.add("Địa chỉ");
        header.add("Số điện thoại");
        header.add("Chức vụ");
        header.add("Hình ảnh");
        model = new DefaultTableModel(header,0);
        tbl = new JTable(model);
        
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(50);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(5).setPreferredWidth(60);
        tbl.getColumnModel().getColumn(6).setPreferredWidth(60);
        tbl.getColumnModel().getColumn(7).setPreferredWidth(60);

        DefaultTableCellRenderer leftAlign = new DefaultTableCellRenderer();
        leftAlign.setHorizontalAlignment(JLabel.LEFT);
        tbl.getColumnModel().getColumn(3).setCellRenderer(leftAlign);
        tbl.getColumnModel().getColumn(5).setCellRenderer(leftAlign);
        
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
        listNV();
        JScrollPane scroll = new JScrollPane(tbl);
        scroll.setBounds(new Rectangle(30, 360, this.default_width - 400 , 300));
        scroll.setBackground(null);
        
        tbl.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int vitri = tbl.getSelectedRow();
                avaURL = tbl.getValueAt(vitri, 7).toString();
                Image newImg;
                newImg = new ImageIcon("./src/image/NhanVienAva/"+avaURL).getImage().getScaledInstance(200, 230, Image.SCALE_DEFAULT);
                txtMaNV.setText(tbl.getModel().getValueAt(vitri,0).toString());
                txtHoNV.setText(tbl.getModel().getValueAt(vitri,1).toString());
                txtTenNV.setText(tbl.getModel().getValueAt(vitri,2).toString());
                txtMucLuong.setText(tbl.getModel().getValueAt(vitri,3).toString());
                txtDiaChi.setText(tbl.getModel().getValueAt(vitri,4).toString());
                txtSDT.setText(tbl.getModel().getValueAt(vitri,5).toString());
                cmbChucVu.setSelectedItem(tbl.getModel().getValueAt(vitri,6).toString());
                ava.setText("");
                ava.setIcon(new ImageIcon(newImg));
            }
        }
        );
        
        //component of Search
        JPanel search = new JPanel(null);
        search.setBackground(null);
        search.setBounds(30,265,this.default_width - 400,100);

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

        String []theo = {"Họ NV","Tên NV","Địa chỉ"};
        cmbTheo = new JComboBox(theo);
        cmbTheo.setFont(new Font("Segoe UI",Font.PLAIN,14));
        cmbTheo.setBounds(new Rectangle(290,40,100,30));
        search.add(cmbTheo);
        
        cmbChucVu1 = new JComboBox(ChucVu);
        cmbChucVu1.setFont(new Font("Segoe UI",Font.PLAIN,14));
        cmbChucVu1.setBounds(new Rectangle(480,40,100,30));
        search.add(cmbChucVu1);
        
        ImageIcon searchImg = new ImageIcon("./src/image/btnSearch_45px.png");
        btnSearch = new JLabel(searchImg);
        btnSearch.setBounds(new Rectangle(650,30,50,50));
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        search.add(btnSearch);
        btnSearch.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                ArrayList<NhanVienDTO> nvSearch = nvBus.search(txtSearch.getText(),cmbTheo.getSelectedIndex(),cmbChucVu1.getSelectedItem().toString());
                fillModel(model,nvSearch);
                txtSearch.setText("");
                cmbTheo.setSelectedIndex(0);
                cmbChucVu1.setSelectedIndex(0);
            }
        });
        
        btnHuySearch = new JButton("Hủy search");
        btnHuySearch.setFont(new Font("Segoe UI",Font.PLAIN,14));
        btnHuySearch.setBounds(new Rectangle(730,40,120,30));
        search.add(btnHuySearch);
        btnHuySearch.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                listNV();
            }
        }
        );
        
        NvView.add(lbMaNV);
        NvView.add(txtMaNV);
        NvView.add(lbHoNV);
        NvView.add(txtHoNV);
        NvView.add(lbTenNV);
        NvView.add(txtTenNV);
        NvView.add(lbSDT);
        NvView.add(txtSDT);
        NvView.add(lbMucLuong);
        NvView.add(txtMucLuong);
        NvView.add(lbDiaChi);
        NvView.add(txtDiaChi);
        NvView.add(cmbChucVu);
        NvView.add(ava);
        NvView.add(btnThem);
        NvView.add(btnSua);
        NvView.add(btnXoa);
        NvView.add(btnUpload);
        NvView.add(btnClear);
        add(NvView);
        add(scroll);
        add(search);
    }
    public void cleanView(){
        txtMaNV.setEditable(false);

        txtMaNV.setText(String.valueOf(idLast));
        txtHoNV.setText("");
        txtTenNV.setText("");
        txtSDT.setText("");
        txtMucLuong.setText("");
        txtDiaChi.setText("");
        cmbChucVu.setSelectedIndex(0);
        ava.setIcon(null);
        ava.setText("");
        
        imgName = "null";
    } 
    public void saveIMG()
    {
        try {
            if(i != null)
            {
                File save = new File("src/image/NhanVienAva/"+ imgName);// Tạo file
                ImageIO.write(i,"jpg",save); // Lưu hình i vào đường dẫn file save

                i = null; //Xóa hình trong bộ nhớ 
            }
        } catch (IOException ex) {
            Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fillModel(DefaultTableModel model , ArrayList<NhanVienDTO> nv) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for(NhanVienDTO n:nv)
        {
            data = new Vector();
            data.add(n.getIDNV());
            data.add(n.getHo());
            data.add(n.getTen());
            data.add(n.getLuong());
            data.add(n.getDiaChi());
            data.add(n.getSDT());
            data.add(n.getChucVu());
            data.add(n.getImg());
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public void listNV() {
        if(nvBus.getList()== null) nvBus.listNV();
            ArrayList<NhanVienDTO> nv = nvBus.getList();
    //        model.setRowCount(0);
        String id = nv.get(nv.size()-1).getIDNV();
        idLast = Integer.parseInt(id)+1;
        txtMaNV.setText(String.valueOf(idLast));
        txtMaNV.setEditable(false);
        ArrayList<NhanVienDTO> nv2 = new ArrayList<>(); 
        for(NhanVienDTO nv1 : nv)
        {
            if( nv1.getTrangThai() == 0)
            {
                nv2.add(nv1);
            }
        }
        fillModel(model,nv2);
    }
    
}
