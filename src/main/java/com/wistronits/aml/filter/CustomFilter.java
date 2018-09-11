package com.wistronits.aml.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gzp
 * @date 2018/8/24 17:33
 */
@WebFilter(filterName = "customFilter", urlPatterns = "/token/*")
public class CustomFilter implements Filter {
	private final Logger logger = LogManager.getLogger(getClass());

	private FilterConfig filterConfig;
	private FilterChain chain;
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = null;
		System.out.println("过滤器初始化");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		System.out.println("CustomFilter指定过滤器操作......");
		chain = filterChain;
		request = (HttpServletRequest) servletRequest;
		response = (HttpServletResponse) servletResponse;
		// todo

		this.chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		this.filterConfig = filterConfig;
		System.out.println("过滤器销毁");
	}

}
