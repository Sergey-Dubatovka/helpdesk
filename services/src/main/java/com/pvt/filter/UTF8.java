package com.pvt.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"}, initParams = {@WebInitParam(name = "encodinf", value = "UTF8", description = "Encoding Param")})

public class UTF8 implements Filter {
    private String code;

    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("encoding");

    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String codeResponse = response.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeResponse)) {
            response.setCharacterEncoding(code);
            String codeRequest = request.getCharacterEncoding();
            if (code!=null&&!code.equalsIgnoreCase(codeRequest))
            request.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        code = null;
    }
}
