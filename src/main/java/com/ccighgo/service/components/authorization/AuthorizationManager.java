/**
 * 
 */
package com.ccighgo.service.components.authorization;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.service.auth.beans.Auth;
import com.ccighgo.service.auth.beans.LoginType;

/**
 * @author ravi
 *
 */
@Component
public class AuthorizationManager {
   
   @Autowired LoginRepository loginRepository;
   
   
   public Auth getUserLogin(String userName){
      Auth auth = new Auth();
      if(userName!=null && !(userName.isEmpty())){
         Login login =  loginRepository.findByLoginName(userName);
         if(login!=null){
            auth.setGoId(login.getGoIdSequence().getGoId());
            auth.setLoginId(login.getLoginId());
            auth.setLoginname(login.getLoginName());
            List<LoginType> loginTypeList = new ArrayList<LoginType>();
            for(LoginUserType loginUsrType:login.getLoginUserTypes()){
               LoginType lt = new LoginType();
               lt.setLoginTypeId(loginUsrType.getUserType().getUserTypeId());
               lt.setLoginType(loginUsrType.getUserType().getUserTypeName());
               lt.setDefault(loginUsrType.getDefaultUserType()==0?false:true);
               loginTypeList.add(lt);
            }
            auth.getLoginType().addAll(loginTypeList);
         } 
         return auth;
         
      }
      return null;
   }

}
