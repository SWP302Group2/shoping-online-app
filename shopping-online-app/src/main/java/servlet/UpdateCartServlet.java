/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import table.cart_detail.TblCartDetailDTO;

/**
 *
 * @author hai
 */
public class UpdateCartServlet extends HttpServlet {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(UpdateCartServlet.class);

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

        try {
            HttpSession ss = request.getSession(false);
            if (ss != null) {
                String txtId = request.getParameter("txtId"); //productId
                String btAction = request.getParameter("btAction");
                String txtQuantity = request.getParameter("txtQuantity");
                String url = material.ServletMaterial.VIEW_CART.getUrl();
                int quantity = 1;
                int type = 1;
                if (btAction.equals("Update Quantity")) {
                    quantity = Integer.parseInt(txtQuantity);
                }
                if (btAction.equals("Remove")) {
                    type = 2;
                }
                if(btAction.equals("Add To Cart")) {
                    url = material.ServletMaterial.SEARCH_PRODUCT.getUrl();
                }
                TblCartDAO cartDAO = new TblCartDAO();
                TblCartDetailDAO cartDetailDAO = new TblCartDetailDAO();
                int userId = (int) ss.getAttribute("CURRENT_USER_USERID");
                int cartId;
                if (ss.getAttribute("CURRENT_CARTID") != null) { //Try to get current user cart id from session
                    cartId = (int) ss.getAttribute("CURRENT_CARTID");
                } else {
                    cartId = cartDAO.getCartId(userId);
                    ss.setAttribute("CURRENT_CARTID", cartId);
                }
                if (cartDetailDAO.updateCartDetail(cartId, txtId, quantity, type)) {   // 1 = update or add , 2 = delete
                    RequestDispatcher dispatch = request.getRequestDispatcher(url); //forward to SearchProductServlet 
                    dispatch.forward(request, response);
                } else {
                    response.sendError(404);
                }

            } else {
                RequestDispatcher dispatch = request.getRequestDispatcher(material.ServletMaterial.SEARCH_PRODUCT.getUrl()); // forward to SearchProductServlet
                dispatch.forward(request, response);
            }
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
