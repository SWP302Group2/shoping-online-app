/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import table.cart.TblCartDAO;
import table.cart_detail.TblCartDetailDAO;
import table.product.TblProductDAO;
import table.product.TblProductDTO;

/**
 *
 * @author hai
 */
public class ViewCartServlet extends HttpServlet {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ViewCartServlet.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info(material.Message.INITIALIZE.getMessage());
        response.setContentType("text/html;charset=UTF-8");
        int cartId;
        try {
            HttpSession ss = request.getSession(false);
            if (ss.getAttribute("CURRENT_CARTID") != null) { //try to get CartId from session first
                cartId = (int) ss.getAttribute("CURRENT_CARTID");
            } else {    //then get cartid in db or create a new one
                TblCartDAO cartDAO = new TblCartDAO();
                int userId = (int) ss.getAttribute("CURRENT_USER_USERID");
                cartId = cartDAO.getCartId(userId);
                ss.setAttribute("CURRENT_CARTID", cartId);
            }
            TblProductDAO productDAO = new TblProductDAO();
            ArrayList<TblProductDTO> productList = productDAO.getListProduct(cartId);
            if (!productList.isEmpty()) {
                request.setAttribute("CART_PRODUCT_LIST", productList);
            }
            RequestDispatcher dispatch = request.getRequestDispatcher(material.ServletMaterial.CART_PAGE.getUrl());
            dispatch.forward(request, response);
        } catch (NamingException | SQLException ex) {
            logger.error(ex.getMessage());
        } finally {

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
