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
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import table.account.TblAccountDAO;

/**
 *
 * @author hai
 */
public class DashboardFilter implements Filter {
 private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(DashboardFilter.class);
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public DashboardFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("DashboardFilter:DoBeforeProcessing");
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
            log("DashboardFilter:DoAfterProcessing");
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
 @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        logger.info(material.Message.INITIALIZE.getMessage());
        String btAction = request.getParameter("btAction");
        boolean valid = true;
        String url = material.ServletMaterial.DASHBOARD_PAGE.getUrl();
        String searchValue = request.getParameter("txtSearchValue");
        String txtUserId = request.getParameter("txtUserId");
        String txtRole = request.getParameter("txtRole");
        if(txtRole!=null){
            try{
            int check = Integer.parseInt(txtRole);
            if(check <0 || check >1) {
                valid = false;
            }
            }catch(NumberFormatException ex) {
                valid = false;
                logger.error(ex.getMessage());
            }
        }
        if(txtUserId!=null ){
            try {
                HttpServletRequest req = (HttpServletRequest) request;
                int userId = Integer.parseInt(txtUserId);
                TblAccountDAO dao = new TblAccountDAO();
                int userRole = dao.getRole(userId);
                HttpSession ss = req.getSession(false);
                int currentRole = (int) ss.getAttribute("CURRENT_USER_ROLE");
                if(currentRole <= userRole) {
                    valid = false;
                }
            } catch (SQLException | NamingException | NumberFormatException ex) {
                valid = false;
                logger.error(ex.getMessage());
            }
        }
        if (btAction != null && valid == true) {
            if (btAction.equals("Search") && searchValue != null) { 
                url = material.ServletMaterial.SEARCH_NAME.getUrl(); //forward to SearchNameServlet
                RequestDispatcher dispatch = request.getRequestDispatcher(url);
                dispatch.forward(request, response);
            } else if (btAction.equals("Manage account")) {
                url = material.ServletMaterial.MANAGE_ACCOUNT.getUrl(); //forward to ManageAccountServlet
                RequestDispatcher dispatch = request.getRequestDispatcher(url);
                dispatch.forward(request, response);
            } else if(btAction.equals("Update")){
                url = material.ServletMaterial.UPDATE_ACCOUNT.getUrl(); //forward to UpdateAccountServlet
                RequestDispatcher dispatch = request.getRequestDispatcher(url);
                dispatch.forward(request, response);
            } else if(btAction.equals("Change Status")) {
                url = material.ServletMaterial.MANAGE_ACCOUNT_STATUS.getUrl(); //forward to ManageAccountStatusServlet
                RequestDispatcher dispatch = request.getRequestDispatcher(url);
                dispatch.forward(request, response);
            }
            else{
                chain.doFilter(request, response); 
            }
        } else if(valid == false){
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendError(401);
        }
        else {
            chain.doFilter(request, response);
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
                log("DashboardFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("DashboardFilter()");
        }
        StringBuffer sb = new StringBuffer("DashboardFilter(");
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
