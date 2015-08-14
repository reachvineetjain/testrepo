/**
 * 
 */
package com.ccighgo.service.components.login;

import org.springframework.stereotype.Service;

import com.ccighgo.service.auth.beans.Auth;

/**
 * @author ravimishra
 *
 */
@Service
public interface LoginAction {
	
	public Auth login();

}
