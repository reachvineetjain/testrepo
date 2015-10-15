/**
 * 
 */
package com.ccighgo.service.components.authorization;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

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
import com.ccighgo.service.components.partner.PartnerService;
import com.ccighgo.service.components.usermanagment.UserManagementService;
import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerDashboardSections;
import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerDetails;
import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerPrograms;
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
   
   @Autowired PartnerService partnerService;

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
                  lt.setUserDetailUrl("/authorize/partner/");
               }
               if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.FIELD_STAFF_USER)) {
                  lt.setUserDetailUrl("/authorize/fs/");
               }
               if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.HOST_FAMILY_USER)) {
                  lt.setUserDetailUrl("/authorize/hf/");
               }
               if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.EMPLOYEE_USER)) {
                  lt.setUserDetailUrl("/authorize/emp/");
               }
               if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.PARTICPANT_USER)) {
                  lt.setUserDetailUrl("/authorize/ptcpnt/");
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
   @Transactional(readOnly = true)
   public User getCCIUserDetails(String userId) {
	   return userManagementService.getUserById(userId);
   }

   @Override
   @Transactional(readOnly = true)
   public PartnerDetails getPartnerDetails(String userId) {
      return partnerService.getPartnerDetails(userId);
   }

}
