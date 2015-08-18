/**
 * 
 */
package com.ccighgo.service.components.authorization;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginHistory;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.jpa.repositories.LoginHistoryRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.service.auth.beans.Auth;
import com.ccighgo.service.auth.beans.LoginType;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.usermanagment.UserManagementService;
import com.ccighgo.service.transport.usermanagement.beans.user.User;
import com.ccighgo.utils.CCIConstants;

/**
 * @author ravi
 *
 */
@Component
public class AuthorizationManager implements AuthorizationManagerInterface {
   
   private static final Logger LOGGER = Logger.getLogger(AuthorizationManager.class);

   @Autowired LoginRepository loginRepository;

   @Autowired LoginHistoryRepository loginHistoryRepository;

   @Autowired MessageUtils messageUtil;
   
   @Autowired UserManagementService userManagementService;

   @Override
   @Transactional(readOnly = true)
   public Auth getUserLogin(String userName) {
      Auth auth = new Auth();
      if (userName != null && !(userName.isEmpty())) {
         Login login = loginRepository.findByLoginName(userName);
         if (login != null) {
            auth.setGoId(login.getGoIdSequence().getGoId());
            auth.setLoginId(login.getLoginId());
            auth.setLoginname(login.getLoginName());
            LOGGER.info("User with login name :" + login.getLoginName() + " logged in at :" + new Timestamp(System.currentTimeMillis()));
            updateHistory(login.getLoginName());
            List<LoginType> loginTypeList = new ArrayList<LoginType>();
            for (LoginUserType loginUsrType : login.getLoginUserTypes()) {
               LoginType lt = new LoginType();
               if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.CCI_USR)) {
                  lt.setUserDetailUrl("/authorize/cciusr/");
               }
               if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.PARTNER_USER)) {
                  lt.setUserDetailUrl("coming soon");
               }
               if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.FIELD_STAFF_USER)) {
                  lt.setUserDetailUrl("coming soon");
               }
               if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.HOST_FAMILY_USER)) {
                  lt.setUserDetailUrl("coming soon");
               }
               if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.EMPLOYEE_USER)) {
                  lt.setUserDetailUrl("coming soon");
               }
               if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.PARTICPANT_USER)) {
                  lt.setUserDetailUrl("coming soon");
               }
               lt.setLoginTypeId(loginUsrType.getUserType().getUserTypeId());
               lt.setLoginType(loginUsrType.getUserType().getUserTypeName());
               lt.setDefault(loginUsrType.getDefaultUserType() == 0 ? false : true);
               loginTypeList.add(lt);
            }
            auth.getLoginType().addAll(loginTypeList);
         }
         return auth;
      }
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

   @Override
   public User getCCIUserDetails(String userId) {
      return userManagementService.getUserById(userId);
   }

}
