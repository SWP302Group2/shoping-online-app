/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static method.Method.checkBirthday;
import static method.Method.checkEmail;
import static method.Method.checkFirstname;
import static method.Method.checkLastname;
import static method.Method.checkPassword;
import static method.Method.checkPhone;
import org.apache.logging.log4j.LogManager;
import table.account.RegisterErr;
import table.account.TblAccountDTO;

/**
 *
 * @author hai
 */
public class SignupFilter implements Filter {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(SignupFilter.class);
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public SignupFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("SignupFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("SignupFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    private final String SIGNUP_PAGE = "signupJSP";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        logger.info(material.Message.INITIALIZE.getMessage());
        boolean valid = true;
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPass");
        String confirm = request.getParameter("txtConfirm");
        String firstname = request.getParameter("txtFirstname");
        String lastname = request.getParameter("txtLastname");
        String birthDay = request.getParameter("txtBirthday");
        String phone = request.getParameter("txtPhone");
        String avatar = "/assets/images/default_avatar.png"; //default avatar
        RegisterErr ERRORS = new RegisterErr();
        HttpServletRequest wrapRequest = (HttpServletRequest) request;
        HttpServletResponse wrapResponse = (HttpServletResponse) response;
        if(wrapRequest.getSession(false) != null && wrapRequest.getSession(false).getAttribute("CURRENT_USER_USERID") != null) {
            wrapResponse.sendRedirect(material.ServletMaterial.HOME.getUrl());
        }else if (email != null && password != null && birthDay != null && phone != null) { //Prechecking signup validation
            if (checkEmail(email) == false) {
                ERRORS.setEmailFormatError(material.Material.INCORRECTFORMAT_EMAIL.getText());
                valid = false;
            }

            if (checkPassword(password) == false) {
                ERRORS.setPasswordFormatError(material.Material.INCORRECTFORMAT_PASSWORD.getText());
                valid = false;
            }
            if (!confirm.equals(password)) {
                ERRORS.setConfirmNotMatch(material.Material.INCORRECTCONFIRM_PASSWORD.getText());
                valid = false;
            }

            if (birthDay.equals("")) {
                ERRORS.setBirthdayFormatError(material.Material.ERROR_BIRTHDAY.getText());
                valid = false;
            } else if (checkBirthday(birthDay) == false) {
                ERRORS.setBirthdayFormatError(material.Material.INCORRECTFORMAT_BIRTHDAY.getText());
                valid = false;
            }

            if (checkFirstname(firstname) == false) {
                ERRORS.setFirstnameLengthError(material.Material.INCORRECTFORMAT_FIRSTNAME.getText());
                valid = false;
            }
            if (checkLastname(lastname) == false) {
                ERRORS.setLastnameLengthError(material.Material.INCORRECTFORMAT_LASTNAME.getText());
                valid = false;
            }
            if (checkPhone(phone) == false) {
                ERRORS.setPhoneFormatError(material.Material.INCORRECTFORMAT_PHONE.getText());
                valid = false;
            }

            if (valid) { //create user and call SignupServlet
                TblAccountDTO dto = new TblAccountDTO(0, email, password, lastname, firstname, birthDay, phone, avatar, 0, 1 , null , null); //default role = 0 //default status code  1 
                wrapRequest.setAttribute("USER", dto);
                chain.doFilter(request, response);
            } else { //forward to login.jsp and display error mess
                RequestDispatcher dispatch = wrapRequest.getRequestDispatcher(SIGNUP_PAGE);
                wrapRequest.setAttribute("ERRORS", ERRORS);
                dispatch.forward(request, response);
            }
        } else {
            RequestDispatcher dispatch = wrapRequest.getRequestDispatcher(SIGNUP_PAGE); //forward to signup page
            dispatch.forward(request, response);
        }

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy Method for this filter
     */
    public void destroy() {
    }

    /**
     * Init Method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("SignupFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SignupFilter()");
        }
        StringBuffer sb = new StringBuffer("SignupFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
