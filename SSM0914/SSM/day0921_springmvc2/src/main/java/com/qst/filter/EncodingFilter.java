//package com.qst.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: XiPeng
 * @Description:
 * @Date: Create in 18:37 2020/9/23
 * @Modified by:
 */
//@WebFilter("/*")
//public class EncodingFilter implements Filter {
//    String encoding;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        encoding="UTF-8";
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        servletRequest.setCharacterEncoding(encoding);
//        servletResponse.setCharacterEncoding(encoding);
//        servletResponse.setContentType("text/html;charset="+encoding);
//        filterChain.doFilter(servletRequest,servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//        encoding="";
//    }
//}
