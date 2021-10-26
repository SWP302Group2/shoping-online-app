/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.catgory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import utils.DBOTools;
import utils.GeneralDAO;

/**
 *
 * @author hai
 */
public class TblCategoryDAO extends GeneralDAO  implements Serializable {

    public TblCategoryDAO() {
    }
    
    @Override
    protected Connection openConnection() throws NamingException, SQLException {
        return DBOTools.getConnectToDatabase();
    }
    
    //get CategoryList
    public ArrayList<TblCategoryDTO> getCategoryList() throws SQLException, NamingException {
        ArrayList<TblCategoryDTO> categoryList = new ArrayList<>();
        try{
            con = openConnection();
            String sql = "select id , name from category ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            
            while(rs.next()) {
                TblCategoryDTO dto = new TblCategoryDTO();
                dto.setId(rs.getString("id"));
                dto.setName(rs.getNString("name"));
                categoryList.add(dto);
            }
        }finally{
            closeConnection();
        }
        return categoryList;
    }
}
