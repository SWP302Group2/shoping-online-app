/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.cart;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import utils.DBOTools;
import utils.GeneralDAO;

/**
 *
 * @author hai
 */
public class TblCartDAO extends GeneralDAO implements Serializable{

    public TblCartDAO() {
    }
    
    @Override
    protected Connection openConnection() throws NamingException, SQLException {
        return DBOTools.getConnectToDatabase();
    }
    
    //get CartId
    public int getCartId(int userId) throws NamingException, SQLException {
        try{
            con = openConnection();
            String query =  "SET TRANSACTION ISOLATION LEVEL SERIALIZABLE "
                    +       "DECLARE @USERID INT "
                    +       "SET @USERID = ? "
                    +           "BEGIN TRAN "
                    +               "IF NOT EXISTS (SELECT CART_ID FROM CART WHERE USERID = @USERID) "
                    +                   "BEGIN "
                    +                       "INSERT INTO CART(USERID) VALUES (@USERID) "
                    +                       "SELECT CART_ID FROM CART WHERE USERID = @USERID "
                    +                   "END "
                    +               "ELSE "
                    +                   "BEGIN "
                    +                       "SELECT CART_ID FROM CART WHERE USERID = @USERID "
                    +                   "END "
                    +           "COMMIT ";
            stm = con.prepareStatement(query);
            stm.setInt(1, userId);
            rs = stm.executeQuery();
            if(rs.next()) {
                return rs.getInt("CART_ID");
            }
        }finally{
            closeConnection();
        }
        return 0;
    }
    //delete a row from Cart
    public boolean deleteCart(int userId) throws NamingException, SQLException {
        try{
            con = openConnection();
            String query = "SET TRANSACTION ISOLATION LEVEL SERIALIZABLE "
                    +           "DELETE FROM CART WHERE USERID = ?";
            stm = con.prepareStatement(query);
            stm.setInt(1, userId);
            int result = stm.executeUpdate();
            if(result == 1) {
                return true;
            }
            
        }finally{
            closeConnection();
        }
        return false;
    }
}
