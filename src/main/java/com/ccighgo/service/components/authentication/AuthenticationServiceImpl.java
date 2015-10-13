/**
 * 
 */
package com.ccighgo.service.components.authentication;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.service.auth.beans.Auth;
import com.ccighgo.service.components.authorization.AuthorizationManagerInterface;

/**
 * @author ravimishra
 *
 */
@Component
public class AuthenticationServiceImpl implements AuthenticationService {
   
   @Autowired AuthorizationManagerInterface authorizationManager;

	@Override
	public Auth login() {
		System.out.println("login");
		String userName = SecurityUtils.getSubject().getPrincipal().toString();
		System.out.println("UserName is"+userName);
		LOGGER.info("User with login name :"+userName+" attempting login");
      return authorizationManager.getUserLogin(userName);
	}
	
	private static final Logger LOGGER = Logger.getLogger(AuthenticationServiceImpl.class);

}
