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
import javax.servlet.http.HttpSession;
import method.Method;
import org.apache.logging.log4j.LogManager;
import table.account.RegisterErr;
import table.account.TblAccountDAO;

/**
 *
 * @author hai
 */
public class UpdateProfileServlet extends HttpServlet {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(UpdateProfileServlet.class);

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
            String txtOldPass = request.getParameter("txtOldPass");
            String txtNewPass = request.getParameter("txtNewPass");
            String txtConfirm = request.getParameter("txtConfirm");
            String txtFirstname = request.getParameter("txtFirstname");
            String txtLastname = request.getParameter("txtLastname");
            String txtBirthday = request.getParameter("txtBirthday");
            String txtPhone = request.getParameter("txtPhone");
            boolean valid = true;
            int key;
            if (txtOldPass != null && txtConfirm != null && txtNewPass != null) {
                key = 1;
                request.setAttribute("UPDATE_KEY", key);
                RegisterErr err = new RegisterErr();
                HttpSession ss = request.getSession(false);
                String currentPass = (String) ss.getAttribute("CURRENT_USER_PASSWORD");
                if (!txtOldPass.equals(currentPass)) {
                    valid = false;
                    request.setAttribute("OLD_PASSWORD_INCORRECT", material.Material.INCORRECT_OLDPASSWORD.getText());
                }
                if (!txtNewPass.equals(txtConfirm)) {
                    valid = false;
                    err.setConfirmNotMatch(material.Material.INCORRECTCONFIRM_PASSWORD.getText());
                }
                if (Method.checkPassword(txtNewPass) == false) {
                    valid = false;
                    err.setPasswordFormatError(material.Material.INCORRECTFORMAT_PASSWORD.getText());
                }
                if (txtNewPass.equals(txtOldPass) && txtOldPass.equals(currentPass)) {
                    valid = false;
                    err.setPasswordFormatError(material.Material.PASSWORD_CHANGE.getText());
                }
                if (valid) {
                    int userId = (int) ss.getAttribute("CURRENT_USER_USERID");
                    TblAccountDAO dao = new TblAccountDAO();
                    if (dao.updatePassword(txtNewPass, userId)) {
                        ss.setAttribute("CURRENT_USER_PASSWORD", txtNewPass);
                        request.setAttribute("SUCCEED_MESSAGE", material.Material.SUCCEED_MESSAGE.getText());
                    } else {
                        request.setAttribute("FAIL_MESSAGE", material.Material.FAIL_MESSAGE.getText());
                    }
                } else {
                    request.setAttribute("ERRORS", err);
                }
                RequestDispatcher dispatch = request.getRequestDispatcher(material.ServletMaterial.PROFILE_PAGE.getUrl());
                dispatch.forward(request, response);
            } else if (txtFirstname != null && txtLastname != null) {
                key = 2;
                request.setAttribute("UPDATE_KEY", key);
                RegisterErr err = new RegisterErr();
                HttpSession ss = request.getSession(false);
                String currentFirstname = (String) ss.getAttribute("CURRENT_USER_FIRSTNAME");
                String currentLastname = (String) ss.getAttribute("CURRENT_USER_LASTNAME");
                boolean updateFirstname = false;
                boolean updateLastname = false;
                if (!txtFirstname.equals(currentFirstname)) {
                    if (Method.checkFirstname(txtFirstname)) {
                        updateFirstname = true;
                    } else {
                        valid = false;
                        err.setFirstnameLengthError(material.Material.INCORRECTFORMAT_FIRSTNAME.getText());
                    }
                } else {
                    valid = false;
                    err.setFirstnameLengthError(material.Material.UPDATE_FIRSTNAME.getText());
                }
                if (!txtLastname.equals(currentLastname)) {
                    if (Method.checkLastname(txtLastname)) {
                        updateLastname = true;
                    } else {
                        valid = false;
                        err.setLastnameLengthError(material.Material.INCORRECTFORMAT_LASTNAME.getText());
                    }
                } else {
                    valid = false;
                    err.setLastnameLengthError(material.Material.UPDATE_LASTNAME.getText());
                }
                if (valid) {
                    int userId = (int) ss.getAttribute("CURRENT_USER_USERID");
                    TblAccountDAO dao = new TblAccountDAO();
                    if (updateFirstname == true) {
                        if (dao.updateFirstname(txtFirstname, userId)) {
                            ss.setAttribute("CURRENT_USER_FIRSTNAME", txtFirstname);
                            request.setAttribute("SUCCEED_MESSAGE", material.Material.SUCCEED_MESSAGE.getText());
                        } else {
                            request.setAttribute("FAIL_MESSAGE", material.Material.FAIL_MESSAGE.getText());
                        }
                    }
                    if (updateLastname == true) {
                        if (dao.updateLastname(txtLastname, userId)) {
                            ss.setAttribute("CURRENT_USER_LASTNAME", txtLastname);
                            request.setAttribute("SUCCEED_MESSAGE", material.Material.SUCCEED_MESSAGE.getText());
                        } else {
                            request.setAttribute("FAIL_MESSAGE", material.Material.FAIL_MESSAGE.getText());
                        }
                    }
                } else {
                    request.setAttribute("ERRORS", err);
                }
                RequestDispatcher dispatch = request.getRequestDispatcher(material.ServletMaterial.PROFILE_PAGE.getUrl());
                dispatch.forward(request, response);
            } else if (txtBirthday != null) {
                key = 3;
                request.setAttribute("UPDATE_KEY", key);
                RegisterErr err = new RegisterErr();
                HttpSession ss = request.getSession(false);
                String currentBirthday = (String) ss.getAttribute("CURRENT_USER_BIRTHDAY");
                if (txtBirthday.equals(currentBirthday)) {
                    valid = false;
                    err.setBirthdayFormatError(material.Material.UPDATE_BIRTHDAY.getText());
                }
                if (Method.checkBirthday(txtBirthday) == false) {
                    valid = false;
                    err.setBirthdayFormatError(material.Material.INCORRECTFORMAT_BIRTHDAY.getText());
                }
                if (valid) {
                    int userId = (int) ss.getAttribute("CURRENT_USER_USERID");
                    TblAccountDAO dao = new TblAccountDAO();
                    if (dao.updateBirthday(txtBirthday, userId)) {
                        ss.setAttribute("CURRENT_USER_BIRTHDAY", txtBirthday);
                        request.setAttribute("SUCCEED_MESSAGE", material.Material.SUCCEED_MESSAGE.getText());
                    } else {
                        request.setAttribute("FAIL_MESSAGE", material.Material.FAIL_MESSAGE.getText());
                    }
                } else {
                    request.setAttribute("ERRORS", err);
                }
                RequestDispatcher dispatch = request.getRequestDispatcher(material.ServletMaterial.PROFILE_PAGE.getUrl());
                dispatch.forward(request, response);
            } else if (txtPhone != null) {
                key = 4;
                request.setAttribute("UPDATE_KEY", key);
                RegisterErr err = new RegisterErr();
                HttpSession ss = request.getSession(false);
                String currentPhone = (String) ss.getAttribute("CURRENT_USER_PHONE");
                if (!Method.checkPhone(txtPhone)) {
                    err.setPhoneFormatError("Wrong format");
                }
                if (currentPhone.equals(txtPhone)) {
                    valid = false;
                    err.setPhoneFormatError(material.Material.UPDATE_PHONE.getText());
                }
                if (valid) {
                    int userId = (int) ss.getAttribute("CURRENT_USER_USERID");
                    TblAccountDAO dao = new TblAccountDAO();
                    if (dao.updatePhone(txtPhone, userId)) {
                        ss.setAttribute("CURRENT_USER_PHONE", txtPhone);
                        request.setAttribute("SUCCEED_MESSAGE", material.Material.SUCCEED_MESSAGE);
                    } else {
                        request.setAttribute("FAIL_MESSAGE", material.Material.FAIL_MESSAGE);
                    }
                } else {
                    request.setAttribute("ERRORS", err);
                }
                RequestDispatcher dispatch = request.getRequestDispatcher(material.ServletMaterial.PROFILE_PAGE.getUrl());
                dispatch.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(material.ServletMaterial.PROFILE_PAGE.getUrl());
                rd.forward(request, response);
            }
        } catch (SQLException | NamingException | ParseException ex) {
            logger.error(ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> Method.
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
     * Handles the HTTP <code>POST</code> Method.
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
