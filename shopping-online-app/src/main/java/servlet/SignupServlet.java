/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import table.account.RegisterErr;
import table.account.TblAccountDAO;
import table.account.TblAccountDTO;

/**
 *
 * @author hai
 */
public class SignupServlet extends HttpServlet {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(SignupServlet.class);

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
        PrintWriter out = response.getWriter();
        try {
            TblAccountDTO user = (TblAccountDTO) request.getAttribute("USER");
            if (user != null) {
                TblAccountDAO dao = new TblAccountDAO();
                if (dao.register(user)) {
                    response.sendRedirect(material.ServletMaterial.LOGIN_PAGE.getUrl());
                } else {
                    RegisterErr err = new RegisterErr();
                    err.setEmailAlreadyExist(material.Material.EXIST_EMAIL.getText());
                    request.setAttribute("ERRORS", err);
                    RequestDispatcher dispatch = request.getRequestDispatcher(material.ServletMaterial.SIGNUP_PAGE.getUrl());
                    dispatch.forward(request, response);
                }
            } else {
                response.sendRedirect(material.ServletMaterial.SIGNUP_PAGE.getUrl());
            }
        } catch (SQLException | ParseException | NamingException ex) {
            logger.error(ex.getMessage());
        } finally {
            out.close();
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
