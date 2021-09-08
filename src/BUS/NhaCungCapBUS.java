/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;

/**
 *
 * @author LE NHAN
 */
public class NhaCungCapBUS {
    public ArrayList<NhaCungCapDTO> dsncc;
    public NhaCungCapBUS(){}
    
    public void listNCC(){
        NhaCungCapDAO nccDAO = new NhaCungCapDAO();
        dsncc = new ArrayList<>();
        dsncc = nccDAO.list();
    }
    
       public void addNCC(NhaCungCapDTO ncc)
    {
        dsncc.add(ncc);
        NhaCungCapDAO nccDAO = new NhaCungCapDAO();
        nccDAO.add(ncc);
    }

    public void deleteNCC(String IDNCC)
    {
        for(NhaCungCapDTO ncc : dsncc )
        {
            if(ncc.getIDNCC().equals(IDNCC))
            {
                dsncc.remove(ncc);
                NhaCungCapDAO nccDAO = new NhaCungCapDAO();
                nccDAO.delete(IDNCC);
                return;
            }
        }
    }
    public String remindIDNCC()
    {
        int max = 0;
        for(NhaCungCapDTO ncc : dsncc)
        {
            int id = Integer.parseInt(ncc.getIDNCC());
            if(id > max)
            {
                max = id;
            }
        }
        max = max+1;
        return max +"";
    }
    public void setNCC(NhaCungCapDTO s)
    {
        for(int i = 0 ; i < dsncc.size() ; i++)
        {
            if(dsncc.get(i).getIDNCC().equals(s.getIDNCC()))
            {
                dsncc.set(i, s);
                NhaCungCapDAO nccDAO = new NhaCungCapDAO();
                nccDAO.set(s);
                return;
            }
        }
    }
       public boolean checkMancc(String IDNCC)
    {
        for(NhaCungCapDTO ncc : dsncc)
        {
            if(ncc.getIDNCC().equals(IDNCC))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<NhaCungCapDTO> getList() {
        return dsncc;
    }
}
