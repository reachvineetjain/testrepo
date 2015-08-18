/**
 * 
 */
package com.ccighgo.service.components.authorization;

import org.springframework.stereotype.Service;

import com.ccighgo.service.auth.beans.Auth;

/**
 * @author ravi
 *
 */
@Service
public interface AuthorizationManagerInterface {
   
   public Auth getUserLogin(String userName);

}
