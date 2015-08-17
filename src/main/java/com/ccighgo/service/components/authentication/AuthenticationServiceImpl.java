/**
 * 
 */
package com.ccighgo.service.components.authentication;

import java.sql.Timestamp;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginHistory;
import com.ccighgo.jpa.repositories.LoginHistoryRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.service.auth.beans.Auth;
import com.ccighgo.service.components.authorization.AuthorizationManager;

/**
 * @author ravimishra
 *
 */
@Component
public class AuthenticationServiceImpl implements AuthenticationService {
   
   @Autowired AuthorizationManager authorizationManager;
   
   @Autowired LoginRepository loginRepository;
   
   @Autowired LoginHistoryRepository loginHistoryRepository;

	@Override
	public Auth login() {
		String userName = SecurityUtils.getSubject().getPrincipal().toString();
		Auth auth = new Auth();
      auth.setLoginname(userName);
     /* UserInfo userInfo = userManager.getUserInfo(traveller);
      auth.setTravelerType(userInfo.getUsrTypeCode());
      auth.setTravelerLoginname(traveller);*/
      updateHistory(userName);
      return auth;
	}
	
	@Transactional
   private void updateHistory(String userName) {
      LoginHistory history = new LoginHistory();
      history.setLoggedOn(new Timestamp(System.currentTimeMillis()));
      Login loggedInUser = loginRepository.findByLoginName(userName);
      history.setLogin(loggedInUser);
      loginHistoryRepository.save(history);
   }

}
