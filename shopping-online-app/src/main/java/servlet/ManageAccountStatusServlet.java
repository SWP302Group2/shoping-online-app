/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import table.account.TblAccountDAO;

/**
 *
 * @author hai
 */
public class ManageAccountStatusServlet extends HttpServlet {
 private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ManageAccountStatusServlet.class);
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
        try {
            logger.info(material.Message.INITIALIZE.getMessage());
            response.setContentType("text/html;charset=UTF-8");
            String txtUserId = request.getParameter("txtUserId");
            int userId = Integer.parseInt(txtUserId);
            TblAccountDAO dao = new TblAccountDAO();
            int statusCode = 1;
            statusCode = Integer.parseInt(request.getParameter("txtStatus"));
            boolean result = dao.updateStatusCodeAccount(userId, statusCode);
            if(result) {
                request.setAttribute("SUCCEED_MESSAGE", material.Material.SUCCEED_MESSAGE);
            }else{
                request.setAttribute("FAIL_MESSAGE", material.Material.FAIL_MESSAGE);
            }
            RequestDispatcher dispatch = request.getRequestDispatcher(material.ServletMaterial.MANAGE_ACCOUNT.getUrl());
            dispatch.forward(request, response);
        } catch (SQLException | NamingException | ParseException ex) {
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
