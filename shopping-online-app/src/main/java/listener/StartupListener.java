/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Web application lifecycle listener.
 *
 * @author nguye
 */
public class StartupListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(StartupListener.class);
    private static void setup(ServletContextEvent sce)
        throws FileNotFoundException, IOException {
        ServletContext context = sce.getServletContext();
        String realPath = context.getRealPath("/WEB-INF/startup.txt");
        Map<String, String> map = null;
        FileReader file = null;
        BufferedReader rd = null;
        try {
            
            file = new FileReader(realPath);
            rd = new BufferedReader(file);
            String line ;
            while ((line = rd.readLine()) != null) {
                if (line.contains("=")) {
                    String[] array = line.split("=");
                    
                    if (map == null) {
                        map = new HashMap();
                    }
                    map.put(array[0], array[1]);
                }
            }
            context.setAttribute("MAP", map);
        }
        finally {
            if (rd != null) {
                rd.close();
            }
            if (file != null) {
                file.close();
            }
        }
    }
    
    private static void setRole(ServletContextEvent sce)
        throws FileNotFoundException, IOException {
        ServletContext context = sce.getServletContext();
        String realPath = context.getRealPath("/");
        Map<String, String> map = null;
        FileReader file = null;
        BufferedReader rd = null;
        try {
            String[] roles = {"GUEST", "CUSTOMER", "ADMIN"};
            for (String role : roles) {
                List<String> actionList = null;
                file = new FileReader(realPath + "/WEB-INF/"+role+".txt");
                rd = new BufferedReader(file);
                String line = null;
                while ((line = rd.readLine()) != null) {
                    if (actionList == null) actionList = new ArrayList();
                    actionList.add(line);
                }
                context.setAttribute(role, actionList);
                
            }
        }
        finally {
            if (rd != null) {
                rd.close();
            }
            if (file != null) {
                file.close();
            }
        }
    }
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        try {
            logger.info(material.Message.INITIALIZE.getMessage());
            setup(sce);
            setRole(sce);
            
        }
        catch(FileNotFoundException e) {
            context.log("StartupListener _FileNotFound " + e.getMessage());
        }
        catch(IOException e ){
            context.log("StartupListener _IOException " + e.getMessage());
        }
        finally {
        }
        
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ///DO NOTHING
    }
}