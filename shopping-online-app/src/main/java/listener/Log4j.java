/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Web application lifecycle listener.
 *
 * @author hai
 */
public class Log4j implements ServletContextListener {
 private static final Logger logger = LogManager.getLogger(Log4j.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
       ServletContext context = sce.getServletContext();
       String log4j2config = context.getInitParameter("log4j-config");
       String realPath = context.getRealPath("/") +File.separator + log4j2config;
       System.setProperty("PATH", context.getRealPath("/"));
       System.setProperty("log4j.configurationFile", realPath); //Set location for config file
       
       
       logger.info(material.Message.INITIALIZE.getMessage());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
