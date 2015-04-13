package com.ccighgo.security;

import java.util.HashMap;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ExcurtAuthenticationFilter extends AuthenticatingFilter {

	private ReplayValidator replayValidator;
	
	@Autowired
	public void setReplayValidator(ReplayValidator replayValidator){
		this.replayValidator = replayValidator;
	}
	
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
		LOGGER.trace("enter createToken");
		HashMap<String, String> tokenData = getOauthData(request);
		HMACSignatureToken token =  new HMACSignatureToken(((HttpServletRequest)request).getRequestURI(), tokenData.get("login"), tokenData.get("timestamp"), tokenData.get("nonce"),tokenData.get("signature"), tokenData.get("version"));
		replayValidator.validate(token);
		LOGGER.trace("exit createToken");
		return token;
	}

	private HashMap<String, String> getOauthData(ServletRequest request) throws SecurityException{
		LOGGER.trace("enter getOauthData");
		
		String oauthHeader = ((HttpServletRequest)request).getHeader("OAUTH");
		if(oauthHeader == null) {
			throw new SecurityException("Request does not contain authentication data");
		}
		
		HashMap<String, String> data = new HashMap<String, String>();
		String[] parts = oauthHeader.split("&");
		for(String part: parts){
			String[] pair = part.split("=");
			if(pair!=null && pair.length>1)
				data.put(pair[0], pair[1]);
		}
		
		validateOauthData(data);
		LOGGER.trace("exit getOauthData");
		return data;
	}
	
	private void validateOauthData(HashMap<String, String> data) throws SecurityException{
		LOGGER.trace("enter validateOauthData");
		String[] requiredData = new String[] {"login", "timestamp", "nonce", "version", "signature"};
		
		for(String element: requiredData){
			if(!data.containsKey(element)){
				throw new SecurityException("Request does not contain required authentication data");
			}
		}
		LOGGER.trace("exit validateOauthData");
	}
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws Exception {
		
		boolean result = false;
		try {
			result = executeLogin(request, response);
		} catch (SecurityException e) {
			LOGGER.error("Error in Login", e);
		}

		if(!result){
			((HttpServletResponse)response).sendError(401, "Unauthorized");
		} else {
			LOGGER.debug("Login success");
		}
		return result;
	}

	@Override
	protected boolean isRememberMe(ServletRequest request) {
		return false;
	}
	
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		((HttpServletResponse)response).setHeader("cache-control", "no-cache"); 
		return super.onLoginSuccess(token, subject, request, response);
	}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcurtAuthenticationFilter.class); 
}
