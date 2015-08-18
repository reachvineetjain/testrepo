/**
 * 
 */
package com.ccighgo.service.rest.authentication;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.auth.beans.Auth;
import com.ccighgo.service.components.authentication.AuthenticationService;

/**
 * @author ravimishra
 *
 */
@Path("/auth/")
@Produces("application/json")
public class Authentication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Authentication.class); 
	
	@Autowired private AuthenticationService authAction;
    
    @GET
	@Path("login")
	public Auth login(){
		String user = SecurityUtils.getSubject().getPrincipal().toString();
		LOGGER.debug("User '{}' logged in", user);
		MDC.put("uid", user);
		return authAction.login();
	}
	
	@GET
	@Path("logout")
	public void logout(){
		String user = SecurityUtils.getSubject().getPrincipal().toString();
		SecurityUtils.getSubject().logout();
		LOGGER.debug("User '{}' logged out", user);
	}
	
	@GET
   @Path("reset/password")
   public void resetPassword(){
      String user = SecurityUtils.getSubject().getPrincipal().toString();
      SecurityUtils.getSubject().logout();
      LOGGER.debug("User '{}' logged out", user);
   }

}
