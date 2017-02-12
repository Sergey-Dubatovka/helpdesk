package com.pvt.filter;

import com.pvt.dao.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class HibernateSessionFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(HibernateSessionFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
        LOG.trace("Realize hibernate Session.");
        HibernateUtil.getHibernateUtil().realizeSession();
    }

    @Override
    public void destroy() {

    }
}