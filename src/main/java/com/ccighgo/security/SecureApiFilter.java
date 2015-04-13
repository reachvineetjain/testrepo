package com.ccighgo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;

public class SecureApiFilter implements javax.servlet.Filter{
	private String keyHeader;
	private String keyValue;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		if(keyHeader==null || keyValue == null){
//			logger.error("API key configurations missing");
			((HttpServletResponse)response).sendError(403, "Forbidden");
			return;
		}
		
		String header = ((HttpServletRequest)request).getHeader(keyHeader);
		if(header == null || !header.equals(keyValue)){
//			logger.error("Invalid Service API Key");
//			logger.debug(keyHeader + " = " + header);
			((HttpServletResponse)response).sendError(403, "Forbidden");
			return;
		}
		filterChain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		keyHeader = config.getInitParameter("APIKEY_NAME");
		keyValue = config.getInitParameter("APIKEY_VALUE");
	}

	public void destroy() {
		
		
	}
//	private static final Logger logger = Logger.getLogger(SecureApiFilter.class); 
}
