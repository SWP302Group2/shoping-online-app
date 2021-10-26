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
import javax.servlet.http.HttpSession;
import method.Method;
import org.apache.logging.log4j.LogManager;
import table.account.RegisterErr;
import table.account.TblAccountDAO;

/**
 *
 * @author hai
 */
public class UpdateAccountServlet extends HttpServlet {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(UpdateAccountServlet.class);

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
        try {
            String txtNewPass = request.getParameter("txtPass");
            String txtConfirm = request.getParameter("txtConfirm");
            String txtFirstname = request.getParameter("txtFirstname");
            String txtLastname = request.getParameter("txtLastname");
            String txtBirthday = request.getParameter("txtBirthday");
            String txtPhone = request.getParameter("txtPhone");
            String txtSearchValue = request.getParameter("txtSearchValue");
            String txtRole = request.getParameter("txtRole");
            boolean valid = true;
            int key;
            int userId = 0;
            userId = Integer.parseInt(request.getParameter("txtUserId"));
            if (txtConfirm != null && txtNewPass != null && userId != 0) {
                key = 1;
                request.setAttribute("UPDATE_KEY", key);
                RegisterErr err = new RegisterErr();
                HttpSession ss = request.getSession(false);
                if (!txtNewPass.equals(txtConfirm)) {
                    valid = false;
                    err.setConfirmNotMatch(material.Material.INCORRECTCONFIRM_PASSWORD.getText());
                }
                if (Method.checkPassword(txtNewPass) == false) {
                    valid = false;
                    err.setPasswordFormatError(material.Material.INCORRECTFORMAT_PASSWORD.getText());
                }
                if (valid) {
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
                request.setAttribute("txtSearchValue", txtSearchValue);
                RequestDispatcher dispatch = request.getRequestDispatcher(material.ServletMaterial.MANAGE_ACCOUNT.getUrl());  // forward to ManageAccountServlet
                dispatch.forward(request, response);
            } else if (txtFirstname != null && txtLastname != null && userId != 0) {
                key = 2;
                request.setAttribute("UPDATE_KEY", key);
                RegisterErr err = new RegisterErr();
                HttpSession ss = request.getSession(false);
                boolean updateFirstname = false;
                boolean updateLastname = false;
                if (Method.checkFirstname(txtFirstname)) {
                    updateFirstname = true;
                } else {
                    valid = false;
                    err.setFirstnameLengthError(material.Material.INCORRECTFORMAT_FIRSTNAME.getText());
                }
                if (Method.checkLastname(txtLastname)) {
                    updateLastname = true;
                } else {
                    valid = false;
                    err.setLastnameLengthError(material.Material.INCORRECTFORMAT_LASTNAME.getText());
                }
                if (valid) {
                    TblAccountDAO dao = new TblAccountDAO();
                    if (updateFirstname == true) {
                        if (dao.updateFirstname(txtFirstname, userId)) {
                            request.setAttribute("SUCCEED_MESSAGE", material.Material.SUCCEED_MESSAGE.getText());
                        } else {
                            request.setAttribute("FAIL_MESSAGE", material.Material.FAIL_MESSAGE.getText());
                        }
                    }
                    if (updateLastname == true) {
                        if (dao.updateLastname(txtLastname, userId)) {
                            request.setAttribute("SUCCEED_MESSAGE", material.Material.SUCCEED_MESSAGE.getText());
                        } else {
                            request.setAttribute("FAIL_MESSAGE", material.Material.FAIL_MESSAGE.getText());
                        }
                    }
                } else {
                    request.setAttribute("ERRORS", err);
                }
                request.setAttribute("txtSearchValue", txtSearchValue);
                RequestDispatcher dispatch = request.getRequestDispatcher(material.ServletMaterial.MANAGE_ACCOUNT.getUrl()); // forward to ManageAccountServlet
                dispatch.forward(request, response);
            } else if (txtBirthday != null && userId != 0) {
                key = 3;
                request.setAttribute("UPDATE_KEY", key);
                RegisterErr err = new RegisterErr();
                if (Method.checkBirthday(txtBirthday) == false) {
                    valid = false;
                    err.setBirthdayFormatError(material.Material.INCORRECTFORMAT_BIRTHDAY.getText());
                }
                if (valid) {
                    TblAccountDAO dao = new TblAccountDAO();
                    if (dao.updateBirthday(txtBirthday, userId)) {
                        request.setAttribute("SUCCEED_MESSAGE", material.Material.SUCCEED_MESSAGE.getText());
                    } else {
                        request.setAttribute("FAIL_MESSAGE", material.Material.FAIL_MESSAGE.getText());
                    }
                } else {
                    request.setAttribute("ERRORS", err);
                }
                request.setAttribute("txtSearchValue", txtSearchValue);
                RequestDispatcher dispatch = request.getRequestDispatcher(material.ServletMaterial.MANAGE_ACCOUNT.getUrl()); // forward to ManageAccountServlet
                dispatch.forward(request, response);
            } else if (txtPhone != null && userId != 0) {
                key = 4;
                request.setAttribute("UPDATE_KEY", key);
                RegisterErr err = new RegisterErr();

                if (!Method.checkPhone(txtPhone)) {
                    err.setPhoneFormatError("Wrong format");
                }
                if (valid) {
                    TblAccountDAO dao = new TblAccountDAO();
                    if (dao.updatePhone(txtPhone, userId)) {
                        request.setAttribute("SUCCEED_MESSAGE", material.Material.SUCCEED_MESSAGE);
                    } else {
                        request.setAttribute("FAIL_MESSAGE", material.Material.FAIL_MESSAGE);
                    }
                } else {
                    request.setAttribute("ERRORS", err);
                }
                request.setAttribute("txtSearchValue", txtSearchValue);
                RequestDispatcher rd = request.getRequestDispatcher(material.ServletMaterial.MANAGE_ACCOUNT.getUrl());
                rd.forward(request, response);
            } else if (txtRole != null && userId != 0) {
                key = 5;
                request.setAttribute("UPDATE_KEY", key);
                RegisterErr err = new RegisterErr();
                if (valid) {
                    int role = Integer.parseInt(txtRole);
                    TblAccountDAO dao = new TblAccountDAO();
                    if (dao.updateRole(userId, role)) {
                        request.setAttribute("SUCCEED_MESSAGE", material.Material.SUCCEED_MESSAGE);
                    } else {
                        request.setAttribute("FAIL_MESSAGE", material.Material.FAIL_MESSAGE);
                    }
                } else {
                    request.setAttribute("ERRORS", err);
                }
                request.setAttribute("txtSearchValue", txtSearchValue);
                RequestDispatcher rd = request.getRequestDispatcher(material.ServletMaterial.MANAGE_ACCOUNT.getUrl());
                rd.forward(request, response);
            } else {
                request.setAttribute("txtSearchValue", txtSearchValue);
                RequestDispatcher rd = request.getRequestDispatcher(material.ServletMaterial.MANAGE_ACCOUNT.getUrl());
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
