package de.contentreich;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class ApplicationFilter implements Filter {
    public static final String PARAM_SLEEP = "sleep";
    public static final String PARAM_PASSTHROUGH = "passthrough";
    public static final String PARAM_HEALTHY = "set_healthy";
    public static final String ATTR_MESSAGE = "message";
    public static final String ATTR_HEALTHY = "healthy";
    public static final String ENV_POD_IP = "TEST_POD_IP";

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Long start = System.currentTimeMillis();
        String pSleep = servletRequest.getParameter(PARAM_SLEEP);
        String pPassThrough = servletRequest.getParameter(PARAM_PASSTHROUGH);
        String pHealth = servletRequest.getParameter(PARAM_HEALTHY);
        String envPodIp = System.getenv(ENV_POD_IP);
        ServletContext context = servletRequest.getServletContext();
        Boolean healthy;
        if (pHealth != null) {
            healthy = Boolean.parseBoolean(pHealth);
            context.setAttribute(ATTR_HEALTHY, healthy);
            System.out.println(new Date() + " Set healthy " + healthy);
        } else {
            healthy = (Boolean) context.getAttribute(ATTR_HEALTHY);
        }
        Long sleep = 0l;
        if (pSleep != null) {
            sleep = Long.parseLong(pSleep);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String pageMsg = PARAM_SLEEP + "=" + sleep + "ms"
                + ", " + PARAM_PASSTHROUGH + " = " + pPassThrough
                + ", pod ip = " + envPodIp
                + ", " + PARAM_HEALTHY + " = " + pHealth;
        servletRequest.setAttribute(ATTR_MESSAGE, pageMsg);
        filterChain.doFilter(servletRequest, servletResponse);
        Long duration = System.currentTimeMillis() - start;
        if (!healthy) {
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        String msg = pageMsg
                + ", duration = " + duration + "ms"
                + (!healthy ? ", status set to " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR : "");
        System.out.println(new Date() + " "
                + ((HttpServletRequest) servletRequest).getRequestURI()
                + " : " + msg);
    }

    public void destroy() {

    }
}
