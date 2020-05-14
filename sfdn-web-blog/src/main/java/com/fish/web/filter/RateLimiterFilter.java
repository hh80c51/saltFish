package com.fish.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;

public class RateLimiterFilter implements Filter {

    private static Logger logger = Logger.getLogger(RateLimiterFilter.class);

    private RateLimiter limiter = null;

    public void init(FilterConfig config) throws ServletException {
        limiter = RateLimiter.create(100); //100 request per second
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if(limiter.tryAcquire()) {
            if(logger.isTraceEnabled()){
                logger.trace("get access: ");
            }
            chain.doFilter(request, response)
        } else {
            logger.info("system limitation reached!");
            req.getRequestDispatcher("/WEB-INF/jsp/error/429.jsp").forward(req,res);
        }
    }
}