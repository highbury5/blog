package com.highbury5.server.filters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import java.io.IOException;

public class EncodeFilter implements Filter{

    private static final Log log = LogFactory.getLog(EncodeFilter.class);

    private String encode;

    @Override
    public void destroy() {
        log.debug("encodeFilter destory");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        log.debug("encodeFilter doFilter");
        response.setContentType("text/html;charset=UTF8");
        chain.doFilter(request,response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        log.debug("encodeFilter init");
        encode = config.getInitParameter("encode");
        log.debug("filter encode is :" + encode);
    }

}