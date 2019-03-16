package de.contentreich;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

public class Application implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println(new Date() + " context initialized");
        servletContextEvent.getServletContext().setAttribute("healthy", Boolean.TRUE);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println(new Date() + " context destroyed");
    }
}
