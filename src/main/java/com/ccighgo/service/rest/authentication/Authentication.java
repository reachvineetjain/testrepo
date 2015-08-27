/**
 * 
 */
package com.ccighgo.service.rest.authentication;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
   @POST
	@Path("login")
	public Auth login(){
		return authAction.login();
	}
	
	@GET
	@Path("logout")
	public void logout(){
	   //TODO clear session
		LOGGER.debug("User logged out");
	}

}
