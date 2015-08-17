/**
 * 
 */
package com.ccighgo.service.components.authentication;

import org.springframework.stereotype.Service;

import com.ccighgo.service.auth.beans.Auth;

/**
 * @author ravimishra
 *
 */
@Service
public interface AuthenticationService {
	
	public Auth login();

}
