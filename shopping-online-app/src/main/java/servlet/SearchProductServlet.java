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
import java.util.Comparator;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import listener.Log4j;
import org.apache.logging.log4j.LogManager;
import table.product.TblProductDAO;
import table.product.TblProductDTO;

/**
 *
 * @author hai
 */
public class SearchProductServlet extends HttpServlet {
    
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(SearchProductServlet.class);

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            logger.info(material.Message.INITIALIZE);
            String txtMin = request.getParameter("txtMin"); // min price
            String txtMax = request.getParameter("txtMax"); // max price
            String txtOrder = request.getParameter("txtOrder"); // 1 = order-price , 2 = order-reserve-price , 3 = order-name , 4 = search-name , 5 min-max , 6 search-by-category
            String txtCategoryId = request.getParameter("txtCategoryId");
            String txtSearchValue = request.getParameter("txtSearchValue");
            float min;
            float max;
            int order;
            if (txtMin.equals("")) {
                min = 0;
            } else {
                min = Float.parseFloat(txtMin);
            }
            if (txtMax.equals("")) {
                max = 0;
            } else {
                max = Float.parseFloat(txtMax);
            }
            if (txtOrder == null ) {
                order = 0;
            } else {
                if (txtOrder.equals("")) order = 0;
                else order = Integer.parseInt(txtOrder);
            }
            if (txtCategoryId.equals("")) {
                txtCategoryId = null;
            } else {
                
            }
            if (txtSearchValue.equals("")) {
                txtSearchValue = null;
            }
            TblProductDAO productDAO = new TblProductDAO();
            ArrayList<TblProductDTO> productList = productDAO.searchProdutct(txtSearchValue, txtCategoryId, min, max, order);
            if (!productList.isEmpty()) {
                if(order == 3) {
                    productList.sort(Comparator.comparing(t -> t.getName()));
                }
                request.setAttribute("SEARCH_RESULT", productList);
            }
            RequestDispatcher dispatch = request.getRequestDispatcher(material.ServletMaterial.SHOP_PAGE.getUrl());
            dispatch.forward(request, response);
        } catch (SQLException | NamingException ex) {
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
