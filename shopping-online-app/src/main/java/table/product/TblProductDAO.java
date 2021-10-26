/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import utils.DBOTools;
import utils.GeneralDAO;

/**
 *
 * @author hai
 */
public class TblProductDAO extends GeneralDAO implements Serializable {

    public TblProductDAO() {
    }

    @Override
    protected Connection openConnection() throws NamingException, SQLException {
        return DBOTools.getConnectToDatabase();
    }

    //Add to list method
    private static void dtoHelpper(TblProductDTO dto, ResultSet rs) throws SQLException {
        dto.setId(rs.getString("product_id"));
        dto.setName(rs.getNString("name"));
        dto.setImage(rs.getString("image"));
        dto.setPrice(rs.getFloat("price"));
        dto.setDescription(rs.getNString("description"));
        dto.setUnit(rs.getNString("unit"));
        dto.setQuantity(rs.getInt("quantity"));
    }

    //Select top product
    public ArrayList<TblProductDTO> selectTopProduct(int range) throws NamingException, SQLException {
        ArrayList<TblProductDTO> productList = new ArrayList<>();
        try {
            con = openConnection();
            String query = "select top (?) "
                    + "pd.product_id , pd.name , pd.quantity, pd.description , pd.image , pl.price , pl.unit "
                    + "from product as pd , PRICE_LIST as pl "
                    + "where pd.product_id = pl.product_id";
            stm = con.prepareStatement(query);
            stm.setInt(1, range);
            rs = stm.executeQuery();
            while (rs.next()) {
                TblProductDTO dto = new TblProductDTO();
                dtoHelpper(dto, rs);
                productList.add(dto);
            }
        } finally {
            closeConnection();
        }
        return productList;
    }

    //Search product
    // 0 = ORDER BY ID 1= ORDER_PRICE, 2 RESERVE_PRICE_ORDER , 3 NAME_ORDER , 4 RESERVE_NAME_ORDER
    public ArrayList<TblProductDTO> searchProdutct(String searhValue, String categoryId, float minPrice, float maxPrice, int type) throws NamingException, SQLException {
        ArrayList<TblProductDTO> productList = new ArrayList<>();
        try {
            con = openConnection();
            String query = "{CALL SEARCH_PRODUCT (?,?,?,?,?)}";
            stm = con.prepareStatement(query);
            stm.setNString(1, searhValue);
            stm.setString(2, categoryId);
            stm.setFloat(3, minPrice);
            stm.setFloat(4, maxPrice);
            stm.setInt(5, type);
            rs = stm.executeQuery();
            while (rs.next()) {
                TblProductDTO dto = new TblProductDTO();
                dtoHelpper(dto, rs);
                productList.add(dto);
            }
        } finally {
            closeConnection();
        }
        return productList;
    }

    //get list of product in user cart
    public ArrayList<TblProductDTO> getListProduct(int cartId) throws NamingException, SQLException {
        try {
            ArrayList<TblProductDTO> productList = new ArrayList<>();;
            con = openConnection();
            String query = "SET TRANSACTION ISOLATION LEVEL SERIALIZABLE  "
                    + "BEGIN TRAN "
                    + "DECLARE @PRODUCT_ID_TABLE TABLE(PRODUCT_ID VARCHAR(20)) "
                    + "INSERT INTO @PRODUCT_ID_TABLE SELECT PRODUCT_ID FROM CART_DETAIL WHERE CART_ID = ? "
                    + "SELECT	PD.PRODUCT_ID, PD.NAME, PL.PRICE, PL.UNIT, PD.QUANTITY, PD.DESCRIPTION, PD.IMAGE, PD.CATEGORY_ID, CG.NAME "
                    + "FROM CATEGORY AS CG, PRODUCT PD, PRICE_LIST AS PL "
                    + "WHERE (CG.ID = PD.CATEGORY_ID AND PD.PRODUCT_ID = PL.PRODUCT_ID) AND PD.PRODUCT_ID IN (SELECT PRODUCT_ID FROM @PRODUCT_ID_TABLE) "
                    + "COMMIT ";
            stm = con.prepareStatement(query);
            stm.setInt(1, cartId);
            rs = stm.executeQuery();
            while (rs.next()) {
                TblProductDTO dto = new TblProductDTO();
                dtoHelpper(dto, rs);
                productList.add(dto);
            }
            return productList;
        } finally {
            closeConnection();
        }
    }
}
