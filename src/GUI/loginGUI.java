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
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import keeptoo.KGradientPanel;
import keeptoo.KButton;
import static org.apache.poi.hemf.record.emf.HemfRecordType.header;
import static org.apache.poi.hemf.record.emfplus.HemfPlusRecordType.header;

/**
 *
 * @author LE NHAN
 */
public class loginGUI extends JFrame implements KeyListener{
//    private QuanLiNhaHang qlnh;
    private TaiKhoanBUS tk = new TaiKhoanBUS();
    private NhanVienBUS nv = new NhanVienBUS();
    private JTextField username;
    private JPasswordField pw;
    private JButton signinbtn;
    private int DEFAULT_WIDTH = 400, DEFAULT_HEIGHT = 600;
    private Home qlnh;
    public loginGUI(){
        init();
    }
    public void init(){
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font1 = new Font("Segoe UI",Font.BOLD,30);
        Font font2 = new Font("Segoe UI",Font.PLAIN,16);
        Font font3 = new Font("Segoe UI",Font.PLAIN,18);
        
        KGradientPanel bg = new KGradientPanel();
        bg.setLayout(null);
        bg.setkStartColor(new Color(0, 204, 0));
        bg.setkEndColor(new Color(90,170,200));
        bg.setkGradientFocus(1200);
        bg.setBounds(0,0,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        
        JLabel exit = new JLabel(new ImageIcon("./src/image/exit_25px.png"),JLabel.CENTER);
        exit.setBounds(DEFAULT_WIDTH - 40, 10, 30, 30);
        exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel icon = new JLabel(new ImageIcon("./src/image/loginicon.png"),JLabel.CENTER);
        icon.setBounds(40, 60, 70, 70);
        
        JLabel title = new JLabel("", JLabel.CENTER);
        title.setText("ĐĂNG NHẬP");
        title.setForeground(Color.WHITE);
        title.setFont(font1);
        title.setBounds(0, 70, DEFAULT_WIDTH, 50);
        
        JLabel usname = new JLabel(new ImageIcon("./src/image/user_30px.png"),JLabel.CENTER);
        usname.setBounds(50, 200, 50, 50);
        JLabel passwd = new JLabel(new ImageIcon("./src/image/pwd_30px.png"),JLabel.CENTER);
        passwd.setBounds(50, 300, 50, 50);
        
        username = new JTextField("Tên đăng nhập");
        username.setBounds(100, 200, 250,40);
        username.setFont(font2);
        pw = new JPasswordField("Mật khẩu");
        pw.setBounds(100, 300, 250,40);
        pw.setFont(font2);
        
        signinbtn = new JButton(new ImageIcon("./src/image/loginbtn.png"));
//        signinbtn.setText("Đăng nhập");
        signinbtn.setBounds(130, 450, 130, 45);
        signinbtn.setFont(font3);
        signinbtn.setBackground(Color.yellow);
        signinbtn.setForeground(Color.red);
        signinbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        signinbtn.setOpaque(true);
//        title.setSize(300, 50);
//        title.setHorizontalAlignment();
        add(bg);
        bg.add(exit);
        bg.add(icon);
        bg.add(title);
        bg.add(usname);
        bg.add(passwd);
        bg.add(username);
        bg.add(pw);
        bg.add(signinbtn);
        exit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                System.exit(0);
            }
        });
        setUndecorated(true);
        setVisible(false);
        setSize(DEFAULT_WIDTH ,DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        username.addMouseListener((new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                username.setText("");
            }
        }));
        username.addKeyListener(this);
        
        pw.addMouseListener((new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                pw.setText("");
            }
        }));
        pw.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e)
            {
                pw.setText("");
            }
        });
        pw.addKeyListener(this);
        signinbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tk.getList()== null)tk.list();
                String uname = username.getText();
                char[] passwd = pw.getPassword();
                TaiKhoanDTO user = tk.check(uname, passwd);
                if(user == null)
                {
                    JOptionPane.showMessageDialog(null, "Sai tên tài khoản hoặc mật khẩu");
                    return;
                }
                if(nv.getList() == null)nv.listNV();
                NhanVienDTO nvDTO = new NhanVienDTO();
                nvDTO = nv.get(user.getIDTK());
                qlnh = new Home(user.getIDTK(),nvDTO.getHo().concat(" "+nvDTO.getTen()),user.getQuyen());
                dispose();
            }
        });
        }
        public static void main(String[]args)
        {
            try{
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }catch(Exception e)
            {
            
            }
        loginGUI lg = new loginGUI();
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object a = e.getSource();
        if(a.equals(username) || a.equals(pw))
        {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                signinbtn.doClick();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
