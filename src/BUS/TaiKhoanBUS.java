/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Son
 */
public class TaiKhoanBUS {

    private ArrayList<TaiKhoanDTO> dsTK;

    public TaiKhoanBUS() {

    }

    public void list() {
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        dsTK = new ArrayList<>();
        dsTK = tkDAO.list();
    }

    public void add(TaiKhoanDTO tk) {
        dsTK.add(tk);
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        tkDAO.add(tk);
    }

    public void add(TaiKhoanDTO tk, int i) {
//        dsUS.add(hd);
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        tkDAO.add(tk);
    }

    public void set(TaiKhoanDTO s) {
        for (int i = 0; i < dsTK.size(); i++) {
            if (dsTK.get(i).getIDTK().equals(s.getIDTK())) {
                System.out.println("ABC");
                dsTK.set(i, s);
                TaiKhoanDAO tkDAO = new TaiKhoanDAO();
                tkDAO.set(s);
                return;
            }
        }
    }

    public TaiKhoanDTO check(String userName, char[] pass) {

        for (TaiKhoanDTO tk : dsTK) {
            char[] correctPass = tk.getMatKhau().toCharArray();
            if (tk.getTenDN().equals(userName) && Arrays.equals(pass, correctPass) && tk.getQuyen().equals("admin") && (tk.getTrangThai() == 1)) {
                return tk;
            }
        }
        return null;
    }

    public ArrayList<TaiKhoanDTO> getList() {
        list();
        return dsTK;
    }

    public void deleteTK(String idtk) {
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        tkDAO.deleteTK(idtk);
    }

    public int checkUser(String username) {
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        boolean rs = tkDAO.checkUser(username);
        if (rs == false) {
            return 1;
        } else {
            return 0;
        }
    }

    public int checkMaNV(String id) {
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        boolean rs = tkDAO.checkMaNV(id);
        if (rs == false) {
            return 1;
        } else {
            return 0;
        }
    }
}
