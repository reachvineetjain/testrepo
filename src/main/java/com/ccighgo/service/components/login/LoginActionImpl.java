/**
 * 
 */
package com.ccighgo.service.components.login;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginHistory;
import com.ccighgo.jpa.repositories.LoginHistoryRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.service.auth.beans.Auth;

/**
 * @author ravimishra
 *
 */
@Component
public class LoginActionImpl implements LoginAction {

   @Autowired
   LoginRepository loginRepository;
   @Autowired
   LoginHistoryRepository loginHistoryRepository;
   @Autowired
   UserManager userManager;

   @Override
   public Auth login() {
      return null;
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
