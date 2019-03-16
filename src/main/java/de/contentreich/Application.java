package de.contentreich;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

public class Application implements ServletContextListener {
    public static final String ENV_STARTUP_DELAY = "TEST_STARTUP_TIME";
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println(new Date() + " context destroyed");
        String startupDelay = System.getenv(ENV_STARTUP_DELAY);
        if (startupDelay != null) {
            System.out.println(new Date() + " waiting " + startupDelay + "ms");
            Long delay = Long.parseLong(startupDelay);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(new Date() + " Application up");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println(new Date() + " context destroyed");
    }
}
