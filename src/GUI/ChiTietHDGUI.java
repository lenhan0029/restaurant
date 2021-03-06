/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.ChiTietHDBUS;
import BUS.HoaDonBUS;
import DTO.ChiTietHDDTO;
import DTO.HoaDonDTO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Shadow
 */
public class ChiTietHDGUI extends JFrame{
    private ChiTietHDBUS ctBUS = new ChiTietHDBUS();
    private HoaDonBUS hdBUS = new HoaDonBUS();
    private String mahd;
    private JTextField txtMaSP,txtSL,txtDonGia;
    private DefaultTableModel model;
    private JTable tbl;
    private int DWIDTH = 840;
    private JTextField txtTenSP;
    public ChiTietHDGUI()
    {
        init();
    }
    public ChiTietHDGUI(String mahd)
    {
        this.mahd = mahd;
        init();
    }
    public void init()
    {
        setTitle("Chi tiết hóa đơn");
        setSize(DWIDTH,450);
        getContentPane().setBackground(new Color(0, 204, 0));
        setLayout(null);
        setLocation(250, 150);
                
        Font ftitle = new Font("Segoe UI",Font.BOLD,25);
        Font font0 = new Font("Segoe UI",Font.PLAIN,13);
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
        
        //HEADER
        JLabel title = new JLabel("CHI TIẾT HÓA ĐƠN : "+mahd,JLabel.CENTER);
        title.setFont(ftitle);
        title.setForeground(Color.WHITE);
        title.setBounds(0, 0, DWIDTH, 60);
        add(title);
/***************** PHẦN HIỂN THỊ THÔNG TIN ***************************/
        JPanel itemView = new JPanel(null);
        itemView.setBounds(new Rectangle(0, 60,this.getSize().width, this.getSize().height - 150));
        itemView.setBackground(Color.WHITE);
        
        JLabel lbMaSP = new JLabel("Mã sản phẩm ");
        lbMaSP.setFont(font0);
        lbMaSP.setBounds(20,20,100,30);
        txtMaSP = new JTextField();
        txtMaSP.setBounds(new Rectangle(120,20,210,30));
        itemView.add(lbMaSP);
        itemView.add(txtMaSP);
        
        JLabel lbTenSP = new JLabel("Tên sản phẩm ");
        lbTenSP.setFont(font0);
        lbTenSP.setBounds(20,60,100,30);
        txtTenSP = new JTextField();
        txtTenSP.setBounds(new Rectangle(120,60,210,30));
        itemView.add(lbTenSP);
        itemView.add(txtTenSP);
        
        JLabel lbSL = new JLabel("Số lượng ");
        lbSL.setFont(font0);
        lbSL.setBounds(20,100,100,30);
        txtSL = new JTextField();
        txtSL.setBounds(new Rectangle(120,100,210,30));
        itemView.add(lbSL);
        itemView.add(txtSL);
        
        JLabel lbDonGia = new JLabel("Đơn giá ");
        lbDonGia.setFont(font0);
        lbDonGia.setBounds(20,140,100,30);
        txtDonGia = new JTextField();
        txtDonGia.setBounds(new Rectangle(120,140,210,30));
        itemView.add(lbDonGia);
        itemView.add(txtDonGia);
/**************** TẠO CÁC BTN XÓA, SỬA, VIEW, IN BILL ********************/

//        JLabel btnEdit = new JLabel(new ImageIcon("./src/image/btnEdit_150px.png"));
//        btnEdit.setBounds(new Rectangle(20,180,150,50));
//        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
              
//        JLabel btnDelete = new JLabel(new ImageIcon("./src/image/btnDelete_150px.png"));
//        btnDelete.setBounds(new Rectangle(180,180,150,50));
//        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));    
//        
////        itemView.add(btnEdit);
//        itemView.add(btnDelete);
/*************************************************************************/

/**************** TẠO TABLE ************************************************************/

    /************** TẠO MODEL VÀ HEADER *********************************/
        Vector header = new Vector();
        header.add("Mă SP");
        header.add("Tên SP");
        header.add("Số lượng");
        header.add("Đơn giá");
        model = new DefaultTableModel(header,5);
        tbl = new JTable(model);
        list(); //Đọc từ database lên table 
        
    /*******************************************************************/
        

    /******** CUSTOM TABLE ****************/
    
        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(120);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(30);

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
        scroll.setBounds(new Rectangle(350, 20, this.getSize().width - 450 , this.getSize().height - 180));
        scroll.setBackground(null);
        
        itemView.add(scroll);
        
        add(itemView);
    /**************************************/
    tbl.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e)
             {
                int i = tbl.getSelectedRow();
                txtMaSP.setText(tbl.getModel().getValueAt(i, 0).toString());
                txtTenSP.setText(tbl.getModel().getValueAt(i, 1).toString());
                txtSL.setText(tbl.getModel().getValueAt(i, 2).toString()); 
                txtDonGia.setText(tbl.getModel().getValueAt(i, 3).toString());
             }
        });
//    btnEdit.addMouseListener(new MouseAdapter(){
//        public void mouseClicked(MouseEvent e)
//        {
//            if(txtMaSP.getText().equals(""))
//            {   
//                    JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn !");
//                    return;
//            }else{
//                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa","Alert",JOptionPane.YES_NO_OPTION);
//                if(i == 0)
//                {
//                    ChiTietHDDTO ct = new ChiTietHDDTO();
//                    ct=ctBUS.search(txtMaSP.getText());
//                    int sl = Integer.parseInt(txtSL.getText());
//                    if(ct.getSoLuong() != sl){
//                        ct.setSoLuong(sl);
//                    
//                    ctBUS.set(ct);
//                    HoaDonDTO hd = new HoaDonDTO();
//                    hd=hdBUS.search(ct.getIDHD());
//                    int tt = ct.getSoLuong()* ct.getDonGia();
//                    hd.setTongTien(tt);
//                    hdBUS.set(hd);
//                    }
//                    tbl.clearSelection();
//                    outModel(model, ctBUS.getList());
//                }
//            }
//            
//        }
//    });
/*****************************************************************************************/
/*********************************************************************/
        
        setVisible(true);
    }
    public void outModel(DefaultTableModel model , ArrayList<ChiTietHDDTO> ct) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for(ChiTietHDDTO c:ct)
        {
            data = new Vector();
            data.add(c.getIDMA());
            data.add(c.getTenMA());
            data.add(c.getSoLuong());
            data.add(c.getDonGia());
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public void list() // Chép ArrayList lên table
    {
        if(ctBUS.getList()== null)ctBUS.list();
        ArrayList<ChiTietHDDTO> ct = ctBUS.getListHD(mahd);
        model.setRowCount(0);
        outModel(model,ct);
    }
}

 