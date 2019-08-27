package com.walle.upload.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(filterName = "fileUploadFilter", urlPatterns = "/upload/*")
public class FileUploadFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Map-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers","x-requested-with");
//        response.setHeader("Access-Control-Allow-Credentials", "true");//是否允许浏览器携带用户信息
        if (request.getMethod().equalsIgnoreCase("OPTIONS")){
            response.setStatus(200);
            return;
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
