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
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.naming.NamingException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import table.account.TblAccountDAO;
import table.account.TblAccountDTO;
import utils.AES256;

/**
 *
 * @author hai
 */
public class SessionManagementFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(SessionManagementFilter.class);
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public SessionManagementFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("SessionManagementFilter:DoBeforeProcessing");
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
            log("SessionManagementFilter:DoAfterProcessing");
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
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            log("SessionManagementFilter run");
            log("1");
            logger.info(material.Message.INITIALIZE);
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            Cookie[] cookie = req.getCookies();
            String email = null;
            String accessToken = null;
            String lastAccessIp = req.getRemoteAddr();
            TblAccountDAO dao = new TblAccountDAO();
            if(cookie != null) {
                for (Cookie ck : cookie) {
                if (ck.getName().equals("EMAIL")) {
                    try {
                        log("2");
                        email = AES256.decrypt(ck.getValue());
                    } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeySpecException ex) {
                        logger.info(ex.getMessage());
                    }
                }
                if (ck.getName().equals("ACCESS_TOKEN")) {
                    try {
                        log("3");
                        accessToken = AES256.decrypt(ck.getValue());
                    } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeySpecException ex) {
                        logger.info(ex.getMessage());
                    }
                }
                }
            }
            
            if (email != null && accessToken != null) { //if have cookies info
                log("4");
                boolean result = dao.checkAccessToken(email, accessToken, lastAccessIp);
                if (result) { //if AccessToken and IP valid
                    log("4.1");
                HttpSession ss = req.getSession(true);
                TblAccountDTO dto = dao.getUserInfo(email);
                int role = dto.getRole();
                String roleName = null;
                ss.setAttribute("CURRENT_USER_USERID", dto.getUserId());
                ss.setAttribute("CURRENT_USER_PASSWORD", dto.getPassword());
                ss.setAttribute("CURRENT_USER_FIRSTNAME", dto.getFirstname());
                ss.setAttribute("CURRENT_USER_LASTNAME", dto.getLastname());
                ss.setAttribute("CURRENT_USER_EMAIL", email);
                ss.setAttribute("CURRENT_USER_BIRTHDAY", dto.getBirthday());
                ss.setAttribute("CURRENT_USER_PHONE", dto.getPhone());
                ss.setAttribute("CURRENT_USER_AVATAR", dto.getAvatar());
                ss.setAttribute("CURRENT_USER_ROLE", role);
                ss.setAttribute("CURRENT_ACCESS_TOKEN", accessToken);
                 if (role == 0) {
                    roleName = "CUSTOMER";
                } else if (role== 1 || role == 2) {
                    roleName = "ADMIN";
                }
                log(roleName);
                ss.setAttribute("ROLE", roleName);
                }else{
                    log("4.2");
                    HttpSession ss = req.getSession(false);
                    if(ss!=null) ss.invalidate();
                }
            } else { //if dont have cookies info , check AccessToken and IP in the session
                log("5");
                HttpSession ss = req.getSession(false);
                if (ss != null) {
                    log("6");
                    accessToken = (String) ss.getAttribute("CURRENT_ACCESS_TOKEN");
                    email = (String) ss.getAttribute("CURRENT_USER_EMAIL");
                    if (accessToken != null && email != null) {
                        if (dao.checkAccessToken(email, accessToken, lastAccessIp)) { // check AccessToken to be sure it still valid
                        } else { //if not invalidate ssession
                            ss.invalidate();
                        }
                    }
                }
            }
            chain.doFilter(request, response);
        } catch (NamingException | SQLException ex) {
            logger.error(ex.getMessage());
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
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("SessionManagementFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SessionManagementFilter()");
        }
        StringBuffer sb = new StringBuffer("SessionManagementFilter(");
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
