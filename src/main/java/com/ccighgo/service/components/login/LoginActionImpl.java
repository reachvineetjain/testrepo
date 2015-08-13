/**
 * 
 */
package com.ccighgo.service.components.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.service.auth.beans.Auth;

/**
 * @author ravimishra
 *
 */
@Component
public class LoginActionImpl implements LoginAction {
   
   @Autowired LoginManager loginManager;

	@Override
	public Auth login() {
		return null;
	}

}
