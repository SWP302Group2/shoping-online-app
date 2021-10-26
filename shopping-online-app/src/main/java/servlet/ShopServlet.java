/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import table.catgory.TblCategoryDAO;
import table.catgory.TblCategoryDTO;
import table.product.TblProductDAO;
import table.product.TblProductDTO;

/**
 *
 * @author hai
 */
public class ShopServlet extends HttpServlet {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ShopServlet.class);

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
        try {
            ServletContext context = getServletContext();
            TblProductDAO productDAO = new TblProductDAO();
            TblCategoryDAO categoryDAO = new TblCategoryDAO();
            ArrayList<TblProductDTO> productList = productDAO.selectTopProduct(100);
            ArrayList<TblCategoryDTO> categoryList;
            if (context.getAttribute("CATEGORY_LIST") == null) {
                categoryList = categoryDAO.getCategoryList();
                context.setAttribute("CATEGORY_LIST", categoryList);
            }
            if (!productList.isEmpty()) {
                request.setAttribute("SEARCH_RESULT", productList);
                RequestDispatcher dispatch = request.getRequestDispatcher(material.ServletMaterial.SHOP_PAGE.getUrl());
                dispatch.forward(request, response);
            } else {
                response.sendRedirect(material.ServletMaterial.SHOP_PAGE.getUrl());
            }
        } catch (NamingException | SQLException ex) {
            logger.error(ex.getMessage());
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
