/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import table.account.TblAccountDAO;
import table.account.TblAccountDTO;
import utils.AES256;

/**
 *
 * @author hai
 */
public class LoginServlet extends HttpServlet {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(LoginServlet.class);

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
        logger.info(material.Message.INITIALIZE.getMessage());
        PrintWriter out = response.getWriter();
        try {
            String errMess = "";
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPass");
            TblAccountDAO dao = new TblAccountDAO();
            boolean result = false;
            int statusCode = dao.checkLogin(email, password);
            if (statusCode == 0 || statusCode == -1) {
                errMess = material.Material.INVALID_EMAILORPASSWORD.getText();
            } else if (statusCode == 2) {
                errMess = material.Material.BANNED_MESSAGE.getText();
            } else if (statusCode == 1) {
                result = true;
            }
            if (result) { //forward to home if login success
                String accessToken = String.valueOf(Math.random());
                dao.newAccessToken(email, accessToken, request.getRemoteAddr());
                 try {
                Cookie emailCookie = new Cookie("EMAIL", AES256.encrypt(email));
                emailCookie.setMaxAge(24*60*60);
                Cookie accessTokenCookie = new Cookie("ACCESS_TOKEN", AES256.encrypt(accessToken));
                accessTokenCookie.setMaxAge(24*60*60);
                response.addCookie(emailCookie);
                response.addCookie(accessTokenCookie);
                } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | InvalidKeySpecException | IllegalBlockSizeException | BadPaddingException ex) {
                    logger.error(ex.getMessage());
                }
                TblAccountDTO dto = dao.getUserInfo(email);
                org.apache.logging.log4j.ThreadContext.put("USERID", Integer.toString(dto.getUserId())); //set userId for log
                logger.warn(material.Message.IP_ADDRESS.getMessage() + request.getRemoteAddr() + material.Message.LOGIN_ACTION.getMessage()); //log user ip address
                int role = dto.getRole();
                HttpSession ss = request.getSession(true);
                ss.setAttribute("CURRENT_USER_USERID", dto.getUserId());
                ss.setAttribute("CURRENT_USER_PASSWORD", dto.getPassword());
                ss.setAttribute("CURRENT_USER_FIRSTNAME", dto.getFirstname());
                ss.setAttribute("CURRENT_USER_LASTNAME", dto.getLastname());
                ss.setAttribute("CURRENT_USER_EMAIL", email);
                ss.setAttribute("CURRENT_USER_BIRTHDAY", dto.getBirthday());
                ss.setAttribute("CURRENT_USER_PHONE", dto.getPhone());
                ss.setAttribute("CURRENT_USER_AVATAR", dto.getAvatar());
                ss.setAttribute("CURRENT_USER_ROLE", role);
                ss.setAttribute("CURRENT_ACCESS_TOKEN", dto.getAccessToken());
                String action = null;
                if (role == 0) {
                    action = "CUSTOMER";
                } else if (role == 1 || role == 2) {
                    action = "ADMIN";
                }
                ss.setAttribute("ROLE", action);
                response.sendRedirect(material.ServletMaterial.HOME.getUrl());
            } else { //forward to login_jsp if login fail and display err message
                RequestDispatcher dispatch = request.getRequestDispatcher(material.ServletMaterial.LOGIN_PAGE.getUrl());
                request.setAttribute("ERROR_MESSAGE", errMess);
                dispatch.forward(request, response);
            }

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
