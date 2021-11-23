package com.yr.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String url = req.getRequestURI();
		String name = (String) req.getSession().getAttribute("name");
		String password = (String) req.getSession().getAttribute("password");

		if (url.endsWith("login.jsp") || url.endsWith("randomServlet") || url.endsWith("accountlogin") || url.endsWith("accountadd") || url.endsWith("register.jsp")) {
			if ((name == null || name.equals("")) && (password == null || password.equals(""))) {
				chain.doFilter(req, resp);
			} else {
				resp.sendRedirect("index.jsp");
			}
		} else {
			if ((name == null || name.equals("")) && (password == null || password.equals(""))) {
				resp.sendRedirect("/mybatisspringspringmvc/login.jsp");
			} else {
				chain.doFilter(req, resp);
			}
		}
	}

}
