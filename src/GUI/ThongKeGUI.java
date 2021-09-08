/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.ChiTietHDBUS;
import BUS.ChiTietPNBUS;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.MonAnBUS;
import BUS.ThongKeBUS;
import BUS.HoaDonBUS;
import BUS.PhieuNhapHangBUS;
import DTO.ChiTietHDDTO;
import DTO.ChiTietPNDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.MonAnDTO;
import DTO.HoaDonDTO;
import DTO.PhieuNhapHangDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;
//import javafx.scene.control.ToggleButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Shadow
 */
public final class ThongKeGUI extends JPanel implements ActionListener,ItemListener,ChangeListener{
    private MonAnBUS spBUS = new MonAnBUS();
    private NhanVienBUS nvBUS = new NhanVienBUS();
    private KhachHangBUS khBUS = new KhachHangBUS();
    private HoaDonBUS hdBus = new HoaDonBUS();
    private PhieuNhapHangBUS pnhBus = new PhieuNhapHangBUS();
    private ChiTietHDBUS cthdBus = new ChiTietHDBUS();
    private ChiTietPNBUS ctpnBus = new ChiTietPNBUS();
    
    private JPanel paneTime = new JPanel() ;
    private JPanel paneTrimester = new JPanel() ;
    private JPanel panePeriod = new JPanel();
    private JPanel form;
    
    private JLabel lbFromDate;
    private JLabel lbToDate;
    private JRadioButton ckTongThu, ckTongChi, ckLoiNhuan ,ckDate, ckTrimester;
    private JRadioButton ckYear = new JRadioButton("Năm");
    private JLabel lbMa = new JLabel();
    private JTextField txtMa = new JTextField();
    private JLabel lbMaKH = new JLabel();
    private JLabel lbMaNV = new JLabel();
    private JTextField txtMaKH = new JTextField("");
    private JTextField txtMaNV = new JTextField("");
    private JTextArea viewStatistic;
    private JButton btnStatistic;
    private JButton btnStatistic1 = new JButton("Thống kê nâng cao");
    JButton btnClear = new JButton("Hủy X");
    private Vector headerNangCao = new Vector();
    private Vector headerChiTiet;
    private int DEFALUT_WIDTH;
    
    private JComboBox<String> cmbFromDate = new JComboBox<>();
    private JComboBox<String> cmbFromMonth = new JComboBox<>();
    private JComboBox<String> cmbFromYear = new JComboBox<>();
    private JComboBox<String> cmbToDate = new JComboBox<>();
    private JComboBox<String> cmbToMonth = new JComboBox<>();
    private JComboBox<String> cmbToYear = new JComboBox<>();
    private JComboBox<String> cmbTrimester = new JComboBox<>();
    private JComboBox<String> cmbPeriod = new JComboBox<>();
    private JLabel lbTrimester;
    private JComboBox<String> cmbYearTrimester = new JComboBox<>();
    private JComboBox<String> cmbYearPeriod = new JComboBox<>();
    private JLabel lbPeriod;
    private JLabel lbYearPeriod;
    private JLabel lbYearTrimester;
    private JButton btnSuggest = new JButton("...");
    private JButton btnSuggestKH = new JButton("...");
    private JButton btnSuggestNV = new JButton("...");
    JLabel Title = new JLabel("----------------------------------------- Thống kê nâng cao ------------------------------------",JLabel.CENTER);
    
    private JButton onOffButton;
    private JPanel Toggle;
    private boolean OnOff = true; // TRUE is ALL || FALSE is TOP
    private JScrollPane scrollViewTable;
    private JTable tbl,tblChiTiet;
    private JTable tblNangCao = new JTable();
    private DefaultTableModel model,modelChiTiet;
    private DefaultTableModel modelNangCao = new DefaultTableModel();
    private JPanel view;
    private JScrollPane scrollViewALL;
    private boolean flag = true; // show chi tiet Hoa don va nguoc lai
    
    
    public ThongKeGUI(int width)
    {
        spBUS.listMA();
        nvBUS.listNV();
        khBUS.list();
        DEFALUT_WIDTH = width;
        init();
    }
    public void init()
    {
        setLayout(null);
        setBackground(null);
        setBounds(new Rectangle(50, 0, this.DEFALUT_WIDTH - 220, 730));
        Font font0 = new Font("Segoe UI",Font.PLAIN,13);
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
/************** PHẦN KIỄM KÊ *****************************************/
        JPanel control = new JPanel(null);
        control.setBackground(null);
        control.setBounds(new Rectangle(0,0,(DEFALUT_WIDTH - 220)/2,730));
        
        // Chuyển đổi ALL và TOP
        Toggle = new JPanel(null);
        Toggle.setBackground(Color.GRAY);
        Toggle.setBounds(new Rectangle(0,10,120,30));
        
        onOffButton = new JButton("ALL");
        onOffButton.setBounds(new Rectangle(0,0,60,30));
        Toggle.add(onOffButton);
        
        onOffButton.addActionListener(this);   
        
        //add(Toggle);
        
        
        JPanel controlAll = new JPanel(new GridLayout(2,5));
        controlAll.setBounds(new Rectangle(0,150,(DEFALUT_WIDTH - 220)/2 - 10,150));
        controlAll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // CHỌN MÃ CẦN THỐNG KÊ
        ButtonGroup id = new ButtonGroup();
        ckTongThu = new JRadioButton("Tổng thu");
        ckTongThu.setFont(font0);
        ckTongThu.setSelected(true);
        ckTongThu.addItemListener(this);
        id.add(ckTongThu);
        ckTongChi = new JRadioButton("Tổng chi");
        ckTongChi.addItemListener(this);
        ckTongChi.setFont(font0);
        id.add(ckTongChi);
        ckLoiNhuan = new JRadioButton("Lợi nhuận");
        ckLoiNhuan.addItemListener(this);
        ckLoiNhuan.setFont(font0);
        id.add(ckLoiNhuan);
        
        // CHỌN KIỂU THỜI GIAN
        ButtonGroup Time = new ButtonGroup();
        ckDate = new JRadioButton("DD/MM/YYY");
        ckDate.addItemListener(this);
        ckDate.setFont(font0);
        ckDate.setSelected(true);
        Time.add(ckDate);
        ckTrimester = new JRadioButton("Quý");
        ckTrimester.setFont(font0);
        ckTrimester.addItemListener(this);
        Time.add(ckTrimester);

        ckYear.setFont(font0);
        ckYear.addItemListener(this);
        Time.add(ckYear);
        
        JLabel lbId = new JLabel("Thống kê");
        lbId.setFont(font1);
        controlAll.add(lbId);
        controlAll.add(ckTongThu);
        controlAll.add(ckTongChi);
        controlAll.add(ckLoiNhuan);
        
        JLabel lbTime = new JLabel("Chọn thời gian");
        lbTime.setFont(font1);
        controlAll.add(lbTime);
        controlAll.add(ckDate);
        controlAll.add(ckTrimester);
        controlAll.add(ckYear);
        
        control.add(controlAll);
        
        //*********************** Panel điền thông tin ***********************//
        form = new JPanel(null);
        form.setBackground(null);
        form.setBounds(new Rectangle(0,330,(DEFALUT_WIDTH - 220)/2 ,500));
  
        Title.setFont(new Font("Segoe UI",Font.PLAIN,12));
        Title.setForeground(Color.red);
        Title.setBounds(new Rectangle(0,80,(DEFALUT_WIDTH - 220)/2 - 10,20));
        //form.add(Title);
        
        //mã sản phẩm
        lbMa.setBounds(new Rectangle(0,110,100,30));
        lbMa.setFont(font0);
        txtMa.setBounds(new Rectangle(110,110,80,30));
        txtMa.setFont(font0);
        
        lbMaKH.setBounds(new Rectangle(250,110,100,30));
        lbMaKH.setFont(font0);
        txtMaKH.setBounds(new Rectangle(350,110,80,30));
        txtMaKH.setFont(font0);
        btnSuggestKH.setBounds(new Rectangle(430,110,30,30));
        
        lbMaNV.setBounds(new Rectangle(0,150,100,30));
        lbMaNV.setFont(font0);
        txtMaNV.setBounds(new Rectangle(110,150,80,30));
        txtMaNV.setFont(font0);
        btnSuggestNV.setBounds(new Rectangle(190,150,30,30));
        

        txtMa.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyChar() == KeyEvent.VK_ENTER)
                {
                    btnStatistic.doClick();
                }
            }
        });
        
        btnSuggest = new JButton("...");
        btnSuggest.setBounds(new Rectangle(190,110,30,30));
        btnSuggest.addActionListener(this);
        btnSuggestKH.addActionListener(this);
        btnSuggestNV.addActionListener(this);
        
        /*form.add(lbMa);
        form.add(txtMa);
        form.add(btnSuggest);
        form.add(lbMaKH);
        form.add(txtMaKH);
        form.add(btnSuggestKH);
        form.add(lbMaNV);
        form.add(txtMaNV);
        form.add(btnSuggestNV);*/
        
        /**************** CHỌN TIME ********************************/
        paneTime = new JPanel(null);
        paneTime.setBackground(null);
        paneTime.setBounds(new Rectangle(0,60,350,80));
        
        // FROM
        lbFromDate = new JLabel("Từ");
        lbFromDate.setFont(font0);
        lbFromDate.setBounds(new Rectangle(0,0,60,30));
        
        cmbFromDate.setBounds(new Rectangle(60,0,80,30));
        cmbFromDate.setFont(font0);
        listDate(cmbFromDate,true);
        JLabel sepTime0 = new JLabel("/");
        sepTime0.setFont(font0);
        sepTime0.setBounds(new Rectangle(145,0,10,30));
        
        cmbFromMonth.addActionListener(this);
        cmbFromMonth.setBounds(new Rectangle(155,0,80,30));
        cmbFromMonth.setFont(font0);
        listMonth(cmbFromMonth);
        JLabel sepTime1 = new JLabel("/");
        sepTime1.setFont(font0);
        sepTime1.setBounds(new Rectangle(240,0,10,30));
        
        cmbFromYear.addActionListener(this);
        cmbFromYear.setBounds(new Rectangle(250,0,80,30));
        cmbFromYear.setFont(font0);
        listYear(cmbFromYear);
        
        System.out.print(cmbFromYear.getSelectedIndex());
        
        paneTime.add(lbFromDate);
        paneTime.add(cmbFromDate);
        paneTime.add(sepTime0);
        paneTime.add(cmbFromMonth);
        paneTime.add(sepTime1);
        paneTime.add(cmbFromYear);
        
        // TO
        lbToDate = new JLabel("Đến");
        lbToDate.setFont(font0);
        lbToDate.setBounds(new Rectangle(0,40,60,30));
        
        cmbToDate.setBounds(new Rectangle(60,40,80,30));
        cmbToDate.setFont(font0);
        listDate(cmbToDate,false);
        JLabel sepTime2 = new JLabel("/");
        sepTime2.setFont(font0);
        sepTime2.setBounds(new Rectangle(145,40,10,30));
        
        cmbToMonth.addActionListener(this);
        cmbToMonth.setBounds(new Rectangle(155,40,80,30));
        cmbToMonth.setFont(font0);
        listMonth(cmbToMonth);
        JLabel sepTime3 = new JLabel("/");
        sepTime3.setFont(font0);
        sepTime3.setBounds(new Rectangle(240,40,10,30));
        
        cmbToYear.addActionListener(this);
        cmbToYear.setBounds(new Rectangle(250,40,80,30));
        cmbToYear.setFont(font0);
        listYear(cmbToYear);
        
        
        paneTime.add(lbFromDate);
        paneTime.add(cmbFromDate);
        paneTime.add(sepTime0);
        paneTime.add(cmbFromMonth);
        paneTime.add(sepTime1);
        paneTime.add(cmbFromYear);
        
        paneTime.add(lbToDate);
        paneTime.add(cmbToDate);
        paneTime.add(sepTime2);
        paneTime.add(cmbToMonth);
        paneTime.add(sepTime3);
        paneTime.add(cmbToYear);
        
        form.add(paneTime);
        /***********************************************************/   
        
        /*************** CHỌN THEO QUÝ *****************************/
        paneTrimester = new JPanel(null);
        paneTrimester.setBackground(null);
        paneTrimester.setBounds(new Rectangle(0,60,350,80));
        
        lbTrimester = new JLabel("Quý");
        lbTrimester.setFont(font0);
        lbTrimester.setBounds(new Rectangle(0,0,100,30));
        
        cmbTrimester.setBounds(new Rectangle(110,0,160,30));
        cmbTrimester.setFont(font0);
        for(int i = 1 ; i <=12  ; i+=3)
        {
            cmbTrimester.addItem("Quý "+(i+2)/3+" ( tháng "+i+" - "+(i+2)+" )");
        }
        
        lbYearTrimester = new JLabel("Năm",JLabel.LEFT);
        lbYearTrimester.setFont(font0);
        lbYearTrimester.setBounds(new Rectangle(0,40,100,30));
        
        cmbYearTrimester.setBounds(new Rectangle(110,40,160,30));
        cmbYearTrimester.setFont(font0);
        listYear(cmbYearTrimester);
        cmbYearTrimester.removeItemAt(0);
        
        paneTrimester.add(lbTrimester);
        paneTrimester.add(cmbTrimester);
        paneTrimester.add(lbYearTrimester);
        paneTrimester.add(cmbYearTrimester);
        
        paneTrimester.setVisible(false);
        
        form.add(paneTrimester);
        /***********************************************************/
        
        /*************** CHỌN THEO NĂM *****************************/
        panePeriod = new JPanel(null);
        panePeriod.setBackground(null);
        panePeriod.setBounds(new Rectangle(0,60,350,80));
        
        lbPeriod = new JLabel("Năm");
        lbPeriod.setFont(font0);
        lbPeriod.setBounds(new Rectangle(0,0,100,30));
        
        cmbPeriod.setBounds(new Rectangle(110,0,160,30));
        cmbPeriod.setFont(font0);
        for(int i = 1 ; i <=12  ; i+=4)
        {
            cmbPeriod.addItem("Kỳ "+(i+3)/4+" ( tháng "+i+" - "+(i+3)+" )");
        }
        
        lbYearPeriod = new JLabel("Năm",JLabel.CENTER);
        lbYearPeriod.setFont(font0);
        lbYearPeriod.setBounds(new Rectangle(270,0,40,30));
        
        cmbYearPeriod.setBounds(new Rectangle(110,0,160,30));
        cmbYearPeriod.setFont(font0);
        listYear(cmbYearPeriod);
        cmbYearPeriod.removeItemAt(0);
        
        panePeriod.add(lbPeriod);
        //panePeriod.add(cmbPeriod);
        //panePeriod.add(lbYearPeriod);
        panePeriod.add(cmbYearPeriod);
        
        panePeriod.setVisible(false);
        form.add(panePeriod);
        /***********************************************************/
        
        btnClear.setFont(new Font("Segoe UI",Font.PLAIN,15));
        btnClear.setForeground(Color.RED);
        btnClear.setBounds(new Rectangle(250,150,80,30));
        btnClear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cleanSuggest();
            }
        });
        btnStatistic = new JButton("Thống kê");
        btnStatistic.setFont(new Font("Segoe UI",Font.PLAIN,15));
        btnStatistic.setBounds(new Rectangle(350,80,(DEFALUT_WIDTH - 220)/2 - 370,30));
        
        btnStatistic1.setFont(new Font("Segoe UI",Font.PLAIN,15) {
        });
        btnStatistic1.setBounds(new Rectangle(350,150,(DEFALUT_WIDTH - 220)/2 - 370,30));
        
        btnStatistic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStaticticAction(e);
            }
        });
        btnStatistic1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStaticticActionUpgrade(e);
            }
        });

        tblNangCao.setModel(modelNangCao);


        // Custom table
        tblNangCao.setFocusable(false);
        tblNangCao.setIntercellSpacing(new Dimension(0,0));     
        tblNangCao.setRowHeight(30);
        tblNangCao.setShowVerticalLines(false);              
        tblNangCao.getTableHeader().setOpaque(false);
        tblNangCao.setFillsViewportHeight(true);
        tblNangCao.getTableHeader().setBackground(new Color(255,153,0 ));
        tblNangCao.getTableHeader().setForeground(Color.WHITE);
        tblNangCao.getTableHeader().setFont(new Font("Segoe UI",Font.PLAIN,12));
        tblNangCao.setFont(new Font("Segoe UI",Font.PLAIN,11));
        tblNangCao.setSelectionBackground(new Color(51,204,51));
        JScrollPane scrollNangCao = new JScrollPane(tblNangCao);
        scrollNangCao.setBounds(new Rectangle(0, 430, this.DEFALUT_WIDTH - 290 , 500));
        scrollNangCao.setBackground(null);
        
        
        form.add(btnStatistic);
        //form.add(btnStatistic1);
        //form.add(btnClear);
        
        control.add(form);
        
        //
        
        //add(scrollNangCao);
        add(control);
/*********************************************************************/

/*************** PHẦN HIỆN THÔNG TIN *********************************/
        
        viewStatistic = new JTextArea();
        viewStatistic.setEditable(false);
        
        scrollViewALL = new JScrollPane(viewStatistic);
        scrollViewALL.setBounds(new Rectangle(570,110,(DEFALUT_WIDTH - 220)/2 - 100 ,500));
        
        add(scrollViewALL);
/*********************************************************************/

/*************** PHẦN HIỆN THÔNG TIN *********************************/
        Vector header = new Vector();
        header.add("STT");
        header.add("Mã SP");
        header.add("Tên SP");
        header.add("SL Bán");

        model = new DefaultTableModel(header,5);
        tbl = new JTable(model);

        
        // CUSTOM TABLE
        tbl.getColumnModel().getColumn(0).setPreferredWidth(15);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(20);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(140);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(50);
        
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
        tbl.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int vitri = tbl.getSelectedRow();
                if(flag == true){
                    String ID = tbl.getModel().getValueAt(vitri,1).toString();
                    Vector header = new Vector();
                    header.add("STT");
                    header.add("Mã HĐ");
                    header.add("Mã món ăn");
                    header.add("Số lượng");
                    header.add("Đơn giá");
                    header.add("Thành tiền");
                    modelChiTiet = new DefaultTableModel(header,0);
                    System.out.println("Chạy dc ne");
                    listChiTietHD(ID);
                }
                else{
                    String ID = tbl.getModel().getValueAt(vitri,1).toString();
                    Vector header = new Vector();
                    header.add("STT");
                    header.add("Mã Phiếu Nhập");
                    header.add("Mã Nguyên Liệu");
                    header.add("Số lượng");
                    header.add("Đơn giá");
                    modelChiTiet = new DefaultTableModel(header,0);
                    listChiTietPN(ID);
                }
            }
        });
        
        scrollViewTable = new JScrollPane(tbl);
        scrollViewTable.setBounds(new Rectangle(570,110,(DEFALUT_WIDTH - 220)/2 - 100 ,500));
        scrollViewTable.setVisible(false);
        
        add(scrollViewTable);
        
        //Hien chi tiet tbl
        modelChiTiet = new DefaultTableModel(headerChiTiet,0);
        tblChiTiet = new JTable(modelChiTiet);


        // Custom table
        tblChiTiet.setFocusable(false);
        tblChiTiet.setIntercellSpacing(new Dimension(0,0));     
        tblChiTiet.getTableHeader().setFont(font1);
        tblChiTiet.setRowHeight(30);
        tblChiTiet.setShowVerticalLines(false);              
        tblChiTiet.getTableHeader().setOpaque(false);
        tblChiTiet.setFillsViewportHeight(true);
        tblChiTiet.getTableHeader().setBackground(new Color(255,153,0));
        tblChiTiet.getTableHeader().setForeground(Color.WHITE);
        tblChiTiet.setSelectionBackground(new Color(51,204,51));
        JScrollPane scrollChiTiet = new JScrollPane(tblChiTiet);
        scrollChiTiet.setBounds(new Rectangle(570,320,(DEFALUT_WIDTH - 220)/2 - 100 ,500));
        scrollChiTiet.setBackground(null);
//        add(scrollChiTiet);
/*********************************************************************/

    }
    public static void main(String[]args)
    {
        JFrame frame = new JFrame();
        frame.setSize(1080, 730);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ThongKeGUI(1300));
        frame.setVisible(true);
    }
    public void listDate(JComboBox cmb,boolean flag) // TRUE is FROM - FALSE is TO
    {
        cmb.addItem("Ngày");
        int thisMonth = 12 , thisDate = 31 ,thisYear = Calendar.getInstance().get(Calendar.YEAR);
        if( cmbFromYear.getSelectedIndex() > 0 || cmbToYear.getSelectedIndex() > 0)
        {
            thisYear = flag ? Integer.parseInt(cmbFromYear.getSelectedItem().toString()) : Integer.parseInt(cmbToYear.getSelectedItem().toString());
//            System.out.println(thisYear);
        }
        if( cmbFromMonth.getSelectedIndex() > 0 || cmbToMonth.getSelectedIndex() > 0)
        {
            thisMonth = flag?cmbFromMonth.getSelectedIndex():cmbToMonth.getSelectedIndex();
//            System.out.println(thisMonth);
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(thisYear, thisMonth - 1, 1);
//        System.out.println(calendar.getTime());
        thisDate = calendar.getActualMaximum(Calendar.DATE);
//        System.out.println(thisDate);
        
        for(int i = 1 ; i <= thisDate ; i++)
        {
            cmb.addItem(i);
        }
    }
    public void listMonth(JComboBox cmb)
    {
        cmb.addItem("Tháng");
        for(int i = 1 ; i <= 12 ; i++ )
        {
            cmb.addItem(i);
        }
    }
    public void listYear(JComboBox cmb)
    {
        cmb.addItem("Năm");
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for(int i = thisYear ; i >= thisYear - 20 ; i-- )
        {
            cmb.addItem(i);
        }
    }
    
    public void btnStaticticAction (ActionEvent e)
    {
        ThongKeBUS tk = new ThongKeBUS();
        
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        
        // THÔNG KÊ THEO NGÀY
        if(ckDate.isSelected())
        {
            int fYear = cmbFromYear.getSelectedIndex()>0 ? Integer.parseInt(cmbFromYear.getSelectedItem().toString()) : 2000;
            int fMonth = cmbFromMonth.getSelectedIndex()>0 ? cmbFromMonth.getSelectedIndex()-1 : 0;
            int fDate =  cmbFromDate.getSelectedIndex()>0 ? Integer.parseInt(cmbFromDate.getSelectedItem().toString()) : 1;
            from.set(fYear, fMonth, fDate, 0, 0, 0);

            int tYear = cmbToYear.getSelectedIndex()>0 ? Integer.parseInt(cmbToYear.getSelectedItem().toString()) : Calendar.getInstance().get(Calendar.YEAR);
            int tMonth = cmbToMonth.getSelectedIndex()>0 ? cmbToMonth.getSelectedIndex()-1 : 11;
            int maxDate = cmbToDate.getItemCount();
            System.out.println(maxDate);
            int tDate =  cmbToDate.getSelectedIndex()>0 ? Integer.parseInt(cmbToDate.getSelectedItem().toString()) : maxDate-1;
            to.set(tYear, tMonth, tDate,23,0,0);
        }
        // THỐNG KÊ THEO QUÝ
        else if(ckTrimester.isSelected())
        {
            int year = Integer.parseInt(cmbYearTrimester.getSelectedItem().toString());
            int fMonth = (cmbTrimester.getSelectedIndex()+1)*3-2;
            int tMonth = fMonth + 2;
            
            from.set(year,fMonth-1,1,0,0,0);
            to.set(year, tMonth-1, 1,23,0,0);
            int dateOfMonth = to.getActualMaximum(Calendar.DAY_OF_MONTH);
            to.set(Calendar.DATE, dateOfMonth);
        }
        else if(ckYear.isSelected()) 
        {
            int year = Integer.parseInt(cmbYearPeriod.getSelectedItem().toString());
            System.out.println(String.valueOf(year));
            from.set(year,0,1,0,0,0);
            to.set(year,11,31,23,0,0);

        }
                
        
        System.out.println(from.getTime()+" va ");
        System.out.println(to.getTime());
        
        if(to.before(from))
        {
            JOptionPane.showMessageDialog(null,"Lỗi thời gian");
            return;
        }
        
        
        if(ckTongThu.isSelected())
        {
            ArrayList<HoaDonDTO> hd = new ArrayList<>();
            Vector header = new Vector();
            header.add("STT");
            header.add("Mã HĐ");
            header.add("Mã NV");
            header.add("Mã Khách");
            header.add("Ngày Lập");
            header.add("Tổng tiền");
            model = new DefaultTableModel(header,0);
            flag = true;
            tblChiTiet.getTableHeader().setBackground(new Color(232,57,99));

            listHD(from,to);
        }
        else if(ckTongChi.isSelected())
        {
            Vector header = new Vector();
            header.add("STT");
            header.add("Mã phiếu nhập");
            header.add("Mã NV");
            header.add("Ngày nhập");
            header.add("Tổng tiền(VNĐ)");
            model = new DefaultTableModel(header,0);
            flag = false;
            tblChiTiet.getTableHeader().setBackground(new Color(255,153,0));

            listPN(from,to);
        }
        else if(ckLoiNhuan.isSelected())
        {
            flag = false;
            modelChiTiet.setRowCount(0);
            tblChiTiet.setModel(modelChiTiet);
            tblChiTiet.getTableHeader().setBackground(Color.WHITE);

            outStatistic(tk.getTongChiPhi(from,to),from, to,tk.getTongDoanhThu(from, to));
        }
        
    }
    public void btnStaticticActionUpgrade (ActionEvent e)
    {
        ThongKeBUS tk = new ThongKeBUS();
        
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        
        // THÔNG KÊ THEO NGÀY
        if(ckDate.isSelected())
        {
            int fYear = cmbFromYear.getSelectedIndex()>0 ? Integer.parseInt(cmbFromYear.getSelectedItem().toString()) : 2000;
            int fMonth = cmbFromMonth.getSelectedIndex()>0 ? cmbFromMonth.getSelectedIndex()-1 : 0;
            int fDate =  cmbFromDate.getSelectedIndex()>0 ? Integer.parseInt(cmbFromDate.getSelectedItem().toString()) : 1;
            from.set(fYear, fMonth, fDate, 0, 0, 0);

            int tYear = cmbToYear.getSelectedIndex()>0 ? Integer.parseInt(cmbToYear.getSelectedItem().toString()) : Calendar.getInstance().get(Calendar.YEAR);
            int tMonth = cmbToMonth.getSelectedIndex()>0 ? cmbToMonth.getSelectedIndex()-1 : 11;
            int maxDate = cmbToDate.getItemCount();
            System.out.println(maxDate);
            int tDate =  cmbToDate.getSelectedIndex()>0 ? Integer.parseInt(cmbToDate.getSelectedItem().toString()) : maxDate-1;
            to.set(tYear, tMonth, tDate,23,0,0);
        }
        // THỐNG KÊ THEO QUÝ
        else if(ckTrimester.isSelected())
        {
            int year = Integer.parseInt(cmbYearTrimester.getSelectedItem().toString());
            int fMonth = (cmbTrimester.getSelectedIndex()+1)*3-2;
            int tMonth = fMonth + 2;
            
            from.set(year,fMonth-1,1,0,0,0);
            to.set(year, tMonth-1, 1,23,0,0);
            int dateOfMonth = to.getActualMaximum(Calendar.DAY_OF_MONTH);
            to.set(Calendar.DATE, dateOfMonth);
        }
        else if(ckYear.isSelected()) 
        {
            int year = Integer.parseInt(cmbYearPeriod.getSelectedItem().toString());
            System.out.println(String.valueOf(year));
            from.set(year,0,1,0,0,0);
            to.set(year,11,31,23,0,0);

        }
                
        
        System.out.println(from.getTime()+" va ");
        System.out.println(to.getTime());
        
        if(to.before(from))
        {
            JOptionPane.showMessageDialog(null,"Lỗi thời gian");
            return;
        }
        
        
        if(ckTongThu.isSelected())
        {   
            if(txtMa != null){
                String id = txtMa.getText();
                ArrayList<MonAnDTO> hd = new ArrayList<>();
                Vector header = new Vector();
                header.add("Mã hóa đơn");
                header.add("Tên món");
                header.add("Đã bán");
                header.add("Doanh Thu");
                modelNangCao = new DefaultTableModel(header,0);
                listSP(from,to,id);
            }
            else if(txtMaKH != null){
                ArrayList<HoaDonDTO> hd = new ArrayList<>();
                Vector header = new Vector();
                header.add("Mã khách hàng");
                header.add("Họ");
                header.add("Tên");
                header.add("Cấp");
                header.add("Số điện thoại");
                header.add("Số lần ghé");
                header.add("Đã chi trả");
                modelNangCao = new DefaultTableModel(header,0);

                //listKH(from,to);
            }
            else if(txtMaNV != null){
                ArrayList<HoaDonDTO> hd = new ArrayList<>();
                Vector header = new Vector();
                header.add("Mã nhân viên");
                header.add("Họ");
                header.add("Tên");
                header.add("Chức vụ");
                header.add("Doanh Thu");
                modelNangCao = new DefaultTableModel(header,0);

                //listNV(from,to);
            }
        }
        
    }
    public void outStatistic(int ChiPhi,Calendar fromDate, Calendar toDate, int DoanhThu)
    {   SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        String formattedFrom = format1.format(fromDate.getTime());
        String formattedTo = format1.format(toDate.getTime());
        int LoiNhuani = DoanhThu - ChiPhi;
        double LoiNhuan = Math.abs(DoanhThu - ChiPhi) ;
        DecimalFormat formatter = new DecimalFormat("#,###");
        String s = "Từ : "+formattedFrom+"\n";
        s += "Đến : "+formattedTo+"\n";
        s += "--------------------------------------------- \n";
        s+= "Doanh thu của nhà hàng : " + formatter.format(DoanhThu)+ " đồng\n";
        s+= "Chi phí nhập hàng của nhà hàng : " + formatter.format(ChiPhi)+ " đồng\n";
        s+="--------------------------------------------- \n";
        if(LoiNhuani > 0){
            s+="Lợi nhuận lời "+formatter.format(LoiNhuan)+" đồng\n";
        }
        else if(LoiNhuani == 0){
            s+="Lợi nhuận bằng 0\n";
        }
        else{
            s+="Nhà hàng lỗ "+formatter.format(LoiNhuan)+" đồng\n";
        }
        viewStatistic.setText(s);
        scrollViewALL.setVisible(true);
        scrollViewTable.setVisible(false);
    }
    public void outStatistic(ArrayList<String> sp)
    {
        model.setRowCount(0);
        for(int i = 0 ; i < sp.size() ; i++)
        {
            System.out.print(sp.get(i));
            String[] s = sp.get(i).split("_");
//            System.out.println(s[1]);
            /**********/
            String maSP = s[0].trim();
            String tenSP = s[1].trim();
            String sl = s[2].trim();
            System.out.println(maSP);
            Vector data = new Vector();
            data.add(i+1);
            data.add(maSP);
            data.add(tenSP);
            data.add(sl);
            model.addRow(data);
        }
        tbl.setModel(model);
        tbl.getColumnModel().getColumn(0).setPreferredWidth(15);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(20);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(140);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(50);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        
        if(obj.equals(cmbFromMonth) || obj.equals(cmbFromYear))
        {
            cmbFromDate.removeAllItems();
            listDate(cmbFromDate,true);
        }
        if(obj.equals(cmbToMonth) || obj.equals(cmbToYear))
        {
            cmbToDate.removeAllItems();
            listDate(cmbToDate,false);
        }   
        if(ckTongThu.isSelected()&& !ckYear.isSelected()){
            if(obj.equals(btnSuggest))
            {
                SuggestMonAn sp = new SuggestMonAn(txtMa.getText());
                String s = sp.getTextFieldContent();
                txtMa.setText(s.split("%")[0]);
                txtMaNV.setEditable(false); 
                txtMaKH.setEditable(false); 
                btnSuggestNV.setEnabled(false);
                btnSuggestKH.setEnabled(false);
            }
            if(obj.equals(btnSuggestNV))
            {
                SuggestNhanVien sp = new SuggestNhanVien();
                String s = sp.getTextFieldContent();
                txtMaNV.setText(s);
                txtMa.setEditable(false); 
                txtMaKH.setEditable(false); 
                btnSuggestKH.setEnabled(false);
                btnSuggest.setEnabled(false);
            }
            if(obj.equals(btnSuggestKH))
            {
                SuggestKhachHang sp = new SuggestKhachHang();
                String s = sp.getTextFieldContent();
                txtMaKH.setText(s);
                txtMaNV.setEditable(false); 
                txtMa.setEditable(false); 
                btnSuggestNV.setEnabled(false);
                btnSuggest.setEnabled(false);
            }
        }
        else{
            cleanSuggest();
            if(obj.equals(btnSuggest))
            {
                SuggestMonAn sp = new SuggestMonAn(txtMa.getText());
                String s = sp.getTextFieldContent();
                txtMa.setText(s.split("%")[0]);
            }
            if(obj.equals(btnSuggestNV))
            {
                SuggestNhanVien sp = new SuggestNhanVien();
                String s = sp.getTextFieldContent();
                txtMaNV.setText(s);
            }
            if(obj.equals(btnSuggestKH))
            {
                SuggestKhachHang sp = new SuggestKhachHang();
                String s = sp.getTextFieldContent();
                txtMaKH.setText(s);
            }
        }
    }
    public void cleanSuggest(){
        txtMaNV.setEditable(true); 
        txtMa.setEditable(true);
        txtMaKH.setEditable(true);
        txtMa.setText("");
        txtMaNV.setText("");
        txtMaKH.setText("");
        btnSuggest.setEnabled(true);
        btnSuggestNV.setEnabled(true);
        btnSuggestKH.setEnabled(true);
    }
    @Override
    public void itemStateChanged(ItemEvent e) 
    {
        // Chọn Control TIME
        if(ckDate.isSelected())
        {
            paneTime.setVisible(true);
            paneTrimester.setVisible(false);
            panePeriod.setVisible(false);
        }
        else if(ckTrimester.isSelected())
        {
            paneTime.setVisible(false);
            paneTrimester.setVisible(true);
            panePeriod.setVisible(false);           
        }
        else
        {
            paneTime.setVisible(false);
            paneTrimester.setVisible(false);
            panePeriod.setVisible(true);
            cleanSuggest();
            
        }
        // Chọn Control MĂ
        if(ckTongThu.isSelected())
        {
            lbMa.setVisible(true);
            txtMa.setVisible(true);
            lbMa.setText("Mă Sản Phẩm");
            btnSuggest.setVisible(true);
            lbMaKH.setVisible(true);
            lbMaNV.setVisible(true);
            lbMaKH.setText("Mă Khách Hàng");
            btnSuggestKH.setVisible(true);
            txtMaKH.setVisible(true);
            txtMaNV.setVisible(true);
            lbMaNV.setText("Mă Nhân Viên");
            btnSuggestNV.setVisible(true);
            btnStatistic1.setVisible(true);
            btnClear.setVisible(true);
            Title.setVisible(true);
            if(ckYear.isSelected()){
                lbMaKH.setVisible(false);
                btnSuggestKH.setVisible(false);
                txtMaKH.setVisible(false);
            }
        }
        else
        {
            lbMa.setVisible(false);
            txtMa.setVisible(false);
            btnSuggest.setVisible(false);
            lbMaKH.setVisible(false);
            lbMaNV.setVisible(false);
            btnSuggestKH.setVisible(false);
            txtMaKH.setVisible(false);
            txtMaNV.setVisible(false);
            btnSuggestNV.setVisible(false);
            btnStatistic1.setVisible(false);
            btnClear.setVisible(false);
            Title.setVisible(false);
            cleanSuggest();
        }
    }

    // ChangeListener
    @Override
    public void stateChanged(ChangeEvent e) {
        txtMa.setVisible(false);
        
    }
    public void fillModel(DefaultTableModel model , ArrayList<HoaDonDTO> hd) // Xuất ra Table từ ArrayList
    {   
        DecimalFormat formatter = new DecimalFormat("#,###");
        Vector data;
        model.setRowCount(0);
        int i=1;
        int count=0;
        for(HoaDonDTO n:hd)
        {
            data = new Vector();
            data.add(i++);
            data.add(n.getIDHD());
            data.add(n.getIDNV());
            data.add(n.getIDK());
            data.add(n.getNgayLap());
            data.add(formatter.format(n.getTongTien()));
            count+=n.getTongTien();
            model.addRow(data);
        }
        data = new Vector();
        data.add("Tổng doanh thu : ");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add(formatter.format(count));
        model.addRow(data);
        tbl.setModel(model);
        tbl.getColumnModel().getColumn(0).setPreferredWidth(75);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(50);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(43);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(62);
        scrollViewTable.setVisible(true);
        scrollViewALL.setVisible(false);
    }
    public void listHD(Calendar from,Calendar to) {
        if(hdBus.getList()== null) hdBus.list();
            ArrayList<HoaDonDTO> hd2 = new ArrayList<>();
            hd2 = hdBus.ListTime(from,to);
    //        model.setRowCount(0);

        fillModel(model,hd2);
    }
    public void fillChiTietHD(DefaultTableModel model , ArrayList<ChiTietHDDTO> hd) // Xuất ra Table từ ArrayList
    {   
        DecimalFormat formatter = new DecimalFormat("#,###");
        Vector data;
        model.setRowCount(0);
        int i=1;
        for(ChiTietHDDTO n:hd)
        {   
            data = new Vector();
            data.add(i++);
            data.add(n.getIDHD());
            data.add(n.getIDMA());
            data.add(n.getSoLuong());
            data.add(formatter.format(n.getDonGia()));
            data.add(formatter.format(n.getThanhTien()));
            model.addRow(data);
            System.out.println(n.toString());
        }
        tblChiTiet.setModel(model);
        tblChiTiet.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblChiTiet.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblChiTiet.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblChiTiet.getColumnModel().getColumn(3).setPreferredWidth(60);
        tblChiTiet.getColumnModel().getColumn(4).setPreferredWidth(60);
        tblChiTiet.getColumnModel().getColumn(5).setPreferredWidth(70);
    }
    public void listChiTietHD(String ID) {
        if(cthdBus.getList()== null) cthdBus.list();
            ArrayList<ChiTietHDDTO> cthd = new ArrayList<>();
            cthd = cthdBus.getList();
            ArrayList<ChiTietHDDTO> cthd2 = new ArrayList<>();
            for(ChiTietHDDTO n:cthd)
            {
                if(n.getIDHD().equals(ID)){
                    cthd2.add(n);
                }
            }
            
        fillChiTietHD(modelChiTiet,cthd2);
    }
    public void fillChiTietPN(DefaultTableModel model , ArrayList<ChiTietPNDTO> hd) // Xuất ra Table từ ArrayList
    {   
        DecimalFormat formatter = new DecimalFormat("#,###");
        Vector data;
        model.setRowCount(0);
        int i=1;
        for(ChiTietPNDTO n:hd)
        {   
            data = new Vector();
            data.add(i++);
            data.add(n.getIDPN());
            data.add(n.getIDNL());
            data.add(n.getSoLuong());
            data.add(formatter.format(n.getDonGia()));
            model.addRow(data);
            System.out.println(n.toString());
        }
        tblChiTiet.setModel(model);
        tblChiTiet.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblChiTiet.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblChiTiet.getColumnModel().getColumn(2).setPreferredWidth(90);
        tblChiTiet.getColumnModel().getColumn(3).setPreferredWidth(60);
        tblChiTiet.getColumnModel().getColumn(4).setPreferredWidth(70);
    }
    public void listChiTietPN(String ID) {
        if(ctpnBus.getList()== null) ctpnBus.list();
            ArrayList<ChiTietPNDTO> ctpn = new ArrayList<>();
            ctpn = ctpnBus.getList();
            ArrayList<ChiTietPNDTO> ctpn2 = new ArrayList<>();
            for(ChiTietPNDTO n:ctpn)
            {
                if(n.getIDPN().equals(ID)){
                    ctpn2.add(n);
                }
            }
            
        fillChiTietPN(modelChiTiet,ctpn2);
    }
    
    public void fillModel1(DefaultTableModel model , ArrayList<PhieuNhapHangDTO> pn) // Xuất ra Table từ ArrayList
    {   
        DecimalFormat formatter = new DecimalFormat("#,###");
        Vector data;
        model.setRowCount(0);
        int i=1;
        int count = 0;
        for(PhieuNhapHangDTO n:pn)
        {
            data = new Vector();
            data.add(i++);
            data.add(n.getIDPN());
            data.add(n.getIDNV());
            data.add(n.getNgayNhap());
            data.add(formatter.format(n.getTongTien()));
            count+=n.getTongTien();
            model.addRow(data);
        }
        data = new Vector();
        data.add("Tổng chi phí : ");
        
        data.add("");
        
        data.add("");
        
        data.add("");
        
        data.add(formatter.format(count));
        model.addRow(data);
        tbl.setModel(model);
        tbl.getColumnModel().getColumn(0).setPreferredWidth(35);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(75);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(20);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(45);
        scrollViewTable.setVisible(true);
        scrollViewALL.setVisible(false);
    }
    public void listPN(Calendar from,Calendar to) {
        if(pnhBus.getList()== null) pnhBus.list();
            ArrayList<PhieuNhapHangDTO> hd2 = new ArrayList<>();
            hd2 = pnhBus.ListTime(from,to);
    //        model.setRowCount(0);

        fillModel1(model,hd2);
    }
    public void fillModelNangCaoSP(DefaultTableModel model , ArrayList<String> sp) // Xuất ra Table từ ArrayList
    {   
        model.setRowCount(0);
        DecimalFormat formatter = new DecimalFormat("#,###");
        for(int i = 0 ; i < sp.size() ; i++)
        {
            String[] s = sp.get(i).split("_");
//            System.out.println(s[1]);
            /**********/
            String maHD = s[0].trim();
            String tenSP = s[1].trim();
            String sl = s[2].trim();
            String dongia = s[3].trim();
            int ThanhTien = Integer.parseInt(sl) * Integer.parseInt(dongia);
            Vector data = new Vector();
            data.add(maHD);
            data.add(tenSP);
            data.add(sl);
            data.add(formatter.format(ThanhTien));
            model.addRow(data);
        }
        tblNangCao.setModel(model);
    }
    public void listSP(Calendar from,Calendar to,String i) {
        ThongKeBUS tk = new ThongKeBUS();
        
    //        model.setRowCount(0);

        fillModelNangCaoSP(modelNangCao,tk.StatisticTopSP(from, to));
    }
    public void outStatistic1(ArrayList<String> sp)
    {
        model.setRowCount(0);
        for(int i = 0 ; i < sp.size() ; i++)
        {
            System.out.print(sp.get(i));
            String[] s = sp.get(i).split("_");
//            System.out.println(s[1]);
            /**********/
            String maSP = s[0].trim();
            String tenSP = s[1].trim();
            String sl = s[2].trim();
            System.out.println(maSP);
            Vector data = new Vector();
            data.add(i+1);
            data.add(maSP);
            data.add(tenSP);
            data.add(sl);
            model.addRow(data);
        }
        tbl.setModel(model);
        tbl.getColumnModel().getColumn(0).setPreferredWidth(15);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(20);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(140);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(50);
    }
}
