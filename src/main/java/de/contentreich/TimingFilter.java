package de.contentreich;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class TimingFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Long start = System.currentTimeMillis();
        String responseTime = servletRequest.getParameter("sleep");
        if (responseTime != null) {
            Long delay = Long.parseLong(responseTime);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
        Long duration = System.currentTimeMillis() - start;
        System.out.println(new Date() + " " + ((HttpServletRequest) servletRequest).getRequestURI() + " processed in " + duration + "ms, sleep = " + responseTime);
    }

    public void destroy() {

    }
}
