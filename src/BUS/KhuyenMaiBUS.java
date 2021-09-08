/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author LE NHAN
 */
public class KhuyenMaiBUS {
    private ArrayList<KhuyenMaiDTO> dskm ;
    public KhuyenMaiBUS()
    {
        
    }
    public void listKM()
    {
        KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
        dskm = new ArrayList<>();
        dskm = kmDAO.list();
    }
    public void addKM(KhuyenMaiDTO km)
    {
        dskm.add(km);
        KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
        kmDAO.add(km);
    }

    public void deleteKM(String IDKM)
    {
        for(KhuyenMaiDTO km : dskm )
        {
            if(km.getIDKM().equals(IDKM))
            {
                dskm.remove(km);
                KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
                kmDAO.delete(IDKM);
                return;
            }
        }
    }
    public void setKM(KhuyenMaiDTO s)
    {
        for(int i = 0 ; i < dskm.size() ; i++)
        {
            if(dskm.get(i).getIDKM().equals(s.getIDKM()))
            {
                dskm.set(i, s);
                KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
                kmDAO.set(s);
                return;
            }
        }
    }
    public boolean checkIDKM(String IDKM)
    {
        for(KhuyenMaiDTO sp : dskm)
        {
            if(sp.getIDKM().equals(IDKM))
            {
                return true;
            }
        }
        return false;
    }
    public KhuyenMaiDTO getKM(String IDKM)
    {
        for(KhuyenMaiDTO km : dskm)
        {
            if(km.getIDKM().equals(IDKM))
            {
                return km;
            }
        }
        return null;
    }
//    public boolean updateKM(String IDKM,int GiamGia)
//    {
//        for(KhuyenMaiDTO km : dskm)
//         {
//             if(km.getIDKM().equals(IDKM))
//             {
//                
//                int old = km.getGiamGia();
//                if(sl > old)
//                {
//                    JOptionPane.showMessageDialog(null, "Không đủ hàng");
//                    return false;
//                }
//                old -= sl;
//                sp.setSl(old);
//                KhuyenMaiDAO spDAO = new KhuyenMaiDAO();
//                spDAO.set(sp);
//                System.out.println(sp.getSl());
//                return true;
//             }
//         }
//         return false;
//    }
//    public boolean checkKM(String IDKM)
//    {
//        for(KhuyenMaiDTO sp : dssp)
//         {
//             if(sp.getMaSP().equals(masp))
//             {
//                if(sl > sp.getSl())
//                {
//                    JOptionPane.showMessageDialog(null, "Không đủ hàng");
//                    return false;
//                }
//             }
//         }
//         return true;
//    }
//    public ArrayList<KhuyenMaiDTO> searchSP(String masp,String maloai,String mansx,int max,int min)
//    {
//        ArrayList<KhuyenMaiDTO> search = new ArrayList<>();
//        masp = masp.isEmpty()?masp = "": masp;
//        maloai = maloai.isEmpty()?maloai = "": maloai;
//        mansx = mansx.isEmpty()?mansx = "": mansx;
//        for(KhuyenMaiDTO sp : dssp)
//        {
//            if( sp.getMaSP().contains(masp) && 
//                sp.getMaLoai().contains(maloai) &&
//                sp.getMaNsx().contains(mansx) &&
//                sp.getGia() >= min && 
//                sp.getGia() <= max)
//            {
//                search.add(sp);
//            }
//        }
//        return search;
//    }
    public ArrayList<KhuyenMaiDTO> getList() {
        return dskm;
    }
    
//    public void ExportExcelDatabase(){
//        KhuyenMaiDAO spDAO = new KhuyenMaiDAO();
//        spDAO.ExportExcelDatabase();
//    }
//    
//    public void ImportExcelDatabase(File file){
//        KhuyenMaiDAO spDAO = new KhuyenMaiDAO();
//        spDAO.ImportExcelDatabase(file);
//    }
}
