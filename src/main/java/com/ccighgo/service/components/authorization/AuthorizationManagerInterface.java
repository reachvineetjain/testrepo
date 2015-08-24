/**
 * 
 */
package com.ccighgo.service.components.authorization;

import org.springframework.stereotype.Service;

import com.ccighgo.service.auth.beans.Auth;
import com.ccighgo.service.transport.usermanagement.beans.user.User;

/**
 * @author ravi
 *
 */
@Service
public interface AuthorizationManagerInterface {
   
   public Auth getUserLogin(String userName);

   public User getCCIUserDetails(String userId);

}
