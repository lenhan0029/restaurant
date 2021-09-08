/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import DTO.TaiKhoanDTO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
public class TaiKhoanGUI extends JPanel {

    private TaiKhoanBUS usBUS = new TaiKhoanBUS();
    private DefaultTableModel model;
    private JTable tbl;
    private int DWIDTH, idLast;
    private JTextField txtidUser;
    private JTextField txtMaNV;
    private JTextField txtUser;
    private JTextField txtPass;
    private JComboBox cmbRole, cmbtt;
    private JButton btnConfirm;
    private JButton btnConfirma;
    private JButton btnDelete;
    private JButton btnEdit;
    private JButton btnBack;
    private JButton btnMaNV;

    public TaiKhoanGUI(int width) {
        this.DWIDTH = width;
        init();
    }

    public void init() {
        setSize(DWIDTH, 700);
        setLayout(null);

        Font ftitle = new Font("Segoe UI", Font.BOLD, 25);
        Font font0 = new Font("Segoe UI", Font.PLAIN, 14);
        Font font1 = new Font("Segoe UI", Font.BOLD, 13);

        //HEADER
        /**
         * *************** PHẦN HIỂN THỊ THÔNG TIN **************************
         */
        JPanel itemView = new JPanel(null);
        itemView.setBounds(new Rectangle(0, 0, this.DWIDTH, 700));
        itemView.setBackground(Color.WHITE);

        JLabel lbidUser = new JLabel("Mã TK");
        lbidUser.setFont(font0);
        lbidUser.setBounds(20, 70, 45, 30);
        txtidUser = new JTextField();
        txtidUser.setBounds(new Rectangle(75, 70, 95, 30));
        itemView.add(lbidUser);
        itemView.add(txtidUser);

        JLabel lbMaNV = new JLabel("Mã NV ");
        lbMaNV.setFont(font0);
        lbMaNV.setBounds(200, 70, 50, 30);
        txtMaNV = new JTextField();
        txtMaNV.setBounds(new Rectangle(255, 70, 95, 30));
        itemView.add(lbMaNV);
        itemView.add(txtMaNV);
        btnMaNV = new JButton("...");
        btnMaNV.setBounds(new Rectangle(360, 70, 30, 30));
        itemView.add(btnMaNV);

        JLabel lbUser = new JLabel("Tên đăng nhập");
        lbUser.setFont(font0);
        lbUser.setBounds(20, 120, 100, 30);
        txtUser = new JTextField();
        txtUser.setBounds(new Rectangle(120, 120, 250, 30));
        itemView.add(lbUser);
        itemView.add(txtUser);

        JLabel lbPass = new JLabel("Password");
        lbPass.setFont(font0);
        lbPass.setBounds(20, 170, 100, 30);
        txtPass = new JTextField("");
        txtPass.setBounds(new Rectangle(120, 170, 250, 30));
        itemView.add(lbPass);
        itemView.add(txtPass);

        JLabel lbPhai = new JLabel("Quyền ");
        lbPhai.setFont(font0);
        lbPhai.setBounds(20, 220, 50, 30);
        String[] role = {"Nhân Viên", "Admin"};
        cmbRole = new JComboBox(role);
        cmbRole.setBounds(new Rectangle(90, 220, 100, 30));
        itemView.add(lbPhai);
        itemView.add(cmbRole);

        JLabel lbtt = new JLabel("Status ");
        lbtt.setFont(font0);
        lbtt.setBounds(210, 220, 50, 30);
        String[] sta = {"thường", "khóa"};
        cmbtt = new JComboBox(sta);
        cmbtt.setBounds(new Rectangle(280, 220, 100, 30));
        itemView.add(lbtt);
        itemView.add(cmbtt);
        /**
         * ************** TẠO CÁC BTN XÓA, SỬA, VIEW, IN BILL
         * *******************
         */
        btnEdit = new JButton(new ImageIcon("./src/image/suabtn.png"));
        btnEdit.setBounds(new Rectangle(20, 310, 130, 45));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnDelete = new JButton(new ImageIcon("./src/image/xoabtn.png"));
        btnDelete.setBounds(new Rectangle(180, 310, 130, 45));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnConfirm = new JButton(new ImageIcon("./src/image/xacnhan.png"));
        btnConfirm.setBounds(new Rectangle(20, 310, 130, 45));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnConfirma = new JButton(new ImageIcon("./src/image/xacnhan.png"));
        btnConfirma.setBounds(new Rectangle(20, 310, 130, 45));
        btnConfirma.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnBack = new JButton(new ImageIcon("./src/image/trolai.png"));
        btnBack.setBounds(new Rectangle(180, 310, 130, 45));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton btnAdd = new JButton(new ImageIcon("./src/image/addbtn.png"));
        btnAdd.setBounds(new Rectangle(50, 400, 130, 45));
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (txtidUser.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần sửa !!!");
                    return;
                }
                txtidUser.setEditable(false);
                txtMaNV.setEditable(false);

                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                btnAdd.setVisible(false);

                btnConfirm.setVisible(true);
                btnConfirma.setVisible(false);
                btnBack.setVisible(true);

                tbl.setEnabled(false);
            }
        });
        btnAdd.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                txtidUser.setText("");
                txtMaNV.setText("");
                txtUser.setText("");
                txtPass.setText("");
                //txtidUser.setEditable(false);
                //txtMaNV.setEditable(false);
                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                btnAdd.setVisible(false);

                btnConfirm.setVisible(false);
                btnConfirma.setVisible(true);
                btnBack.setVisible(true);

                tbl.setEnabled(false);
                list();
                listTK();
            }
        });
        btnDelete.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tbl.getRowCount();
                if (row != -1) {
                    int i = JOptionPane.showConfirmDialog(null, "Xác nhận xoá tài khoản", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        String idtk = txtidUser.getText();
                        usBUS.deleteTK(idtk);
                         list();
                    }
                }
            }
        });

        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa tài khoản", "", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    //Lấy dữ liệu từ TextField
                    int status;
                    String iduser = txtidUser.getText();
                    String maNV = txtMaNV.getText();
                    String user = txtUser.getText();
                    String pass = txtPass.getText();
                    String role = String.valueOf(cmbRole.getSelectedItem());
                    String sta = String.valueOf(cmbtt.getSelectedItem());
                    if (sta == "thường") {
                        status = 1;
                    } else {
                        status = 0;
                    }
                    //Upload sản phẩm lên DAO và BUS
                    long millis = System.currentTimeMillis();
                    java.sql.Date date = new java.sql.Date(millis);
                    TaiKhoanDTO us = new TaiKhoanDTO(iduser, maNV, user, pass, role, date, status);
                    usBUS.set(us);

                    outModel(model, usBUS.getList());// Load lại table

//                        saveIMG();// Lưu hình ảnh 
                    JOptionPane.showMessageDialog(null, "Sửa thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        btnConfirma.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int status;
                String iduser = txtidUser.getText();
                String maNV = txtMaNV.getText();
                String user = txtUser.getText();
                String pass = txtPass.getText();
                if (maNV.equals("") || user.equals("") || pass.equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin!");
                } else {
                    int i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm tài khoản", "", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        //Lấy dữ liệu từ TextField

                        String role = String.valueOf(cmbRole.getSelectedItem());
                        String sta = String.valueOf(cmbtt.getSelectedItem());
                        if (sta == "thường") {
                            status = 1;
                        } else {
                            status = 0;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        long millis = System.currentTimeMillis();
                        java.sql.Date date = new java.sql.Date(millis);
                        TaiKhoanDTO us = new TaiKhoanDTO(iduser, maNV, user, pass, role, date, status);
                        int ktuser = usBUS.checkUser(user);
                        int ktmanv = usBUS.checkMaNV(maNV);
                        if (ktmanv == 0) {
                            JOptionPane.showMessageDialog(null, "MaNV đã tồn tại !");
                            txtMaNV.setText("");
                            txtMaNV.requestFocus();
                        } else {
                            if (ktuser == 1) {
                                usBUS.add(us);
                                JOptionPane.showMessageDialog(null, "thêm thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Đã tồn tại tài khoản !");
                                txtUser.setText("");
                                txtUser.requestFocus();
                            }
                        }
                        outModel(model, usBUS.getList());// Load lại table
//                        saveIMG();// Lưu hình ảnh 
                    }
                }
            }
        });

        btnBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cleanView();

                btnEdit.setVisible(true);
                btnDelete.setVisible(true);
                btnAdd.setVisible(true);

                btnConfirm.setVisible(false);
                btnConfirma.setVisible(false);
                btnBack.setVisible(false);
//                btnFile.setVisible(false);

                tbl.setEnabled(true);
            }
        });

        itemView.add(btnEdit);
        itemView.add(btnDelete);
        itemView.add(btnAdd);
        itemView.add(btnConfirm);
        itemView.add(btnConfirma);
        itemView.add(btnBack);
        /**
         * **********************************************************************
         */
        /**
         * ************** TẠO TABLE
         * ***********************************************************
         */

        /**
         * ************ TẠO MODEL VÀ HEADER ********************************
         */
        Vector header = new Vector();
        header.add("Mã TK");
        header.add("Mă NV");
        header.add("Username");
        header.add("Pass");
        header.add("Role");
        header.add("Status");
        model = new DefaultTableModel(header, 6);
        tbl = new JTable(model);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);
        tbl.setRowSorter(rowSorter);
        list();

        /**
         * ****************************************************************
         */
        /**
         * ****** CUSTOM TABLE ***************
         */
        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(30);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(30);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(60);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(5).setPreferredWidth(40);

        // Custom table
        tbl.setFocusable(false);
        tbl.setIntercellSpacing(new Dimension(0, 0));
        tbl.getTableHeader().setFont(font1);
        tbl.setRowHeight(30);
        tbl.setShowVerticalLines(false);
        tbl.getTableHeader().setOpaque(false);
        tbl.setFillsViewportHeight(true);
        tbl.getTableHeader().setBackground(new Color(255, 153, 0));
        tbl.getTableHeader().setForeground(Color.WHITE);
        tbl.setSelectionBackground(new Color(51, 204, 51));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbl);
        scroll.setBounds(new Rectangle(400, 20, DWIDTH - 650, 500));
        scroll.setBackground(null);

        itemView.add(scroll);

        add(itemView);
        /**
         * ***********************************
         */
        /**
         * **************************************************************************************
         */
        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = tbl.getSelectedRow();
                if (tbl.getRowSorter() != null) {
                    i = tbl.getRowSorter().convertRowIndexToModel(i);
                }
                txtidUser.setText(tbl.getModel().getValueAt(i, 0).toString());
                txtMaNV.setText(tbl.getModel().getValueAt(i, 1).toString());
                txtUser.setText(tbl.getModel().getValueAt(i, 2).toString());
                txtPass.setText(tbl.getModel().getValueAt(i, 3).toString());
                cmbRole.setSelectedItem(tbl.getModel().getValueAt(i, 4).toString());
                if (Integer.parseInt(tbl.getModel().getValueAt(i, 5).toString()) == 1) {
                    cmbtt.setSelectedItem("thường");
                } else {
                    cmbtt.setSelectedItem("khóa");
                }
            }
        });
        btnMaNV.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == btnMaNV) // Suggest Nhan Vien
                {
                    SuggestNhanVien rm = new SuggestNhanVien();
                    String s = rm.getTextFieldContent();
                    txtMaNV.setText(s);
                }
            }
        });
        /**
         * ******************************************************************
         */
    }

    public void outModel(DefaultTableModel model, ArrayList<TaiKhoanDTO> user) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for (TaiKhoanDTO us : user) {
            data = new Vector();
            data.add(us.getIDTK());
            data.add(us.getIDNV());
            data.add(us.getTenDN());
            data.add(us.getMatKhau());
            data.add(us.getQuyen());
            data.add(us.getTrangThai());
            model.addRow(data);
        }
        tbl.setModel(model);
    }

    public void list() // Chép ArrayList lên table
    {
        if (usBUS.getList() == null) {
            usBUS.list();
        }
        ArrayList<TaiKhoanDTO> nv = usBUS.getList();
//        model.setRowCount(0);
        System.out.println(nv);
        outModel(model, nv);
       
    }

    public void cleanView() //Xóa trắng các TextField
    {
        txtidUser.setEditable(false);
        txtMaNV.setEditable(true);
        txtidUser.setText("");
        txtMaNV.setText("");
        txtUser.setText("");
        txtPass.setText("");

    }

    public void listTK() {
        if (usBUS.getList() == null) {
            usBUS.list();
        }
        ArrayList<TaiKhoanDTO> tk = usBUS.getList();
        //        model.setRowCount(0);
        String id = tk.get(tk.size() - 1).getIDTK();
        idLast = Integer.parseInt(id) + 1;
        txtidUser.setText(String.valueOf(idLast));
        txtidUser.setEditable(false);
        ArrayList<TaiKhoanDTO> tk2 = new ArrayList<>();
        for (TaiKhoanDTO tk1 : tk) {
            if (tk1.getTrangThai() == 1) {
                tk2.add(tk1);
            }
        }
        outModel(model, tk2);
    }

}
