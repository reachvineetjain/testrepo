/**
 * 
 */
package com.ccighgo.service.components.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Login;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.service.auth.beans.Auth;

/**
 * @author ravi
 *
 */
@Component
public class AuthorizationManager {
   
   @Autowired LoginRepository loginRepository;
   
   
   public Auth getUserLogin(String userName){
      
      if(userName!=null && !(userName.isEmpty())){
         /* UserInfo userInfo = userManager.getUserInfo(traveller);
          * auth.setLoginname(userName);
          auth.setTravelerType(userInfo.getUsrTypeCode());
          auth.setTravelerLoginname(traveller);*/
         Login login =  loginRepository.findByLoginName(userName);
         if(login!=null){
            
         }
         
      }
      return null;
   }

}
