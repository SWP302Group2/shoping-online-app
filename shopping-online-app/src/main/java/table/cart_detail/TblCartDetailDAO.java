/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.cart_detail;

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
public class TblCartDetailDAO extends GeneralDAO implements Serializable {

    public TblCartDetailDAO() {
    }

    @Override
    protected Connection openConnection() throws NamingException, SQLException {
        return DBOTools.getConnectToDatabase();
    }
    
    //update or add new cart detail
    public boolean updateCartDetail(int cartId, String productId, int quantity, int type) throws NamingException, SQLException {
        try{
            con = openConnection();
            String query = "SET TRANSACTION ISOLATION LEVEL SERIALIZABLE "
                    +        "DECLARE @CART_ID INT "
                    +        "DECLARE @PRODUCT_ID VARCHAR(20) "
                    +        "DECLARE @QUANTITY INT "
                    +        "DECLARE @TYPE INT " //@TYPE = 1 -> UPDATE or ADD , @TYPE = 2 -> DELETE
                    +        "SET @CART_ID = ? "
                    +        "SET @PRODUCT_ID = ? "
                    +        "SET @QUANTITY = ? "
                    +        "SET @TYPE = ? "
                    +        "BEGIN TRAN "
                    +            "IF NOT EXISTS (SELECT CART_ID FROM CART_DETAIL WHERE PRODUCT_ID = @PRODUCT_ID AND CART_ID = @CART_ID) AND @TYPE = 1"
                    +                "BEGIN "
                    +                    "INSERT INTO CART_DETAIL(CART_ID,PRODUCT_ID,QUANTITY) VALUES(@CART_ID,@PRODUCT_ID,@QUANTITY) "
                    +                "END "
                    +            "ELSE "
                    +                "BEGIN "
                    +                    "IF @TYPE = 1 "
                    +                        "BEGIN "
                    +                            "UPDATE CART_DETAIL SET QUANTITY = @QUANTITY + (SELECT QUANTITY FROM CART_DETAIL WHERE CART_ID = @CART_ID AND PRODUCT_ID = @PRODUCT_ID) "
                    +                            "WHERE CART_ID = @CART_ID AND PRODUCT_ID = @PRODUCT_ID "
                    +                        "END "
                    +                    "IF @TYPE = 2 "
                    +                        "BEGIN "
                    +                            "DELETE CART_DETAIL WHERE CART_ID = @CART_ID AND PRODUCT_ID = @PRODUCT_ID "
                    +                        "END "
                    +                "END "
                    +        "COMMIT ";
            stm = con.prepareStatement(query);
            stm.setInt(1, cartId);
            stm.setString(2, productId);
            stm.setInt(3, quantity);
            stm.setInt(4, type);
            int result = stm.executeUpdate();
            if(result == 1) {
                return true;
            }
        }finally{
            closeConnection();
        }
        return false;
    }
    public ArrayList<String> getUserCartDetail(int cartId) throws NamingException, SQLException {
        try{
            ArrayList<String> productList = new ArrayList<>();
            openConnection();
            String query = "SELECT PRODUCT_ID FROM CART_DETAIL WHERE CART_ID = ?";
            stm = con.prepareStatement(query);
            stm.setInt(1, cartId);
            rs = stm.executeQuery();
            while(rs.next()) {
                productList.add(rs.getString("PRODUCT_ID"));
            }
            return productList;
        }finally{
            closeConnection();
        }
    }
    
}
