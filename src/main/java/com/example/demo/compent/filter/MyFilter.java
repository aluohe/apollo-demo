package com.example.demo.compent.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("MyFilter: "+ req.getRequestURI());
		//这步使得请求能够继续传导下去，如果没有的话，请求就在此结束
		chain.doFilter(request, response);
		req.getInputStream();
	}
 
	@Override
	public void destroy() {
	
	}
}