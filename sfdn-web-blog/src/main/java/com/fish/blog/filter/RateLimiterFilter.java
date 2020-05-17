package com.fish.blog.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RateLimiterFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(RateLimiterFilter.class);

    private RateLimiter limiter = null;

    @Override
    public void init(FilterConfig config) throws ServletException {
        limiter = RateLimiter.create(100); //100 request per second
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if(limiter.tryAcquire()) {
            if(logger.isTraceEnabled()){
                logger.trace("get access: ");
            }
            chain.doFilter(request, response);
        } else {
            logger.info("system limitation reached!");
            req.getRequestDispatcher("/WEB-INF/jsp/error/429.jsp").forward(req,res);
        }
    }
}