/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 *
 * @author Shadow
 */
public class Page404 extends JPanel{
    private int DEFALUT_WIDTH;
    private String title = "HỆ THỐNG ĐANG BẢO TRÌ";
    
    public Page404(int width)
    {
        DEFALUT_WIDTH = width;
        init();
    }
    public Page404(int width,String title)
    {
        this.title = title;
        DEFALUT_WIDTH = width;
        init();
    }
    public void init()
    {
        setLayout(null);
        setBackground(null);
        setBounds(new Rectangle(100, 0, this.DEFALUT_WIDTH - 220, 1000));
        
        JLabel icon = new JLabel(new ImageIcon("./src/image/order.png"));
        icon.setBounds(0,300,200,200);
       
        Font font = new Font("Segoe UI",Font.BOLD,60);
        JLabel lb = new JLabel(title);
        lb.setForeground(new Color(0,204,0));
        lb.setFont(font);
        lb.setBounds(new Rectangle(250,330,800,100));
        
        
        JSeparator sep = new JSeparator();
        sep.setBackground(new Color(0,153,51));
        sep.setBounds(new Rectangle(250,420,600,10));
        
        add(icon);
        add(lb);
        add(sep);
        
        
    }
}
