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
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.LoginHistoryRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.service.auth.beans.Auth;
import com.ccighgo.service.auth.beans.LoginType;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.AuthConstants;
import com.ccighgo.service.components.fieldstaffs.fieldstaffdashboard.FieldStaffDashboardInterface;
import com.ccighgo.service.components.partner.PartnerService;
import com.ccighgo.service.components.usermanagment.UserManagementService;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboard;
import com.ccighgo.service.transport.integration.thirdparty.beans.partnerLeadViewForPartnerInquiryData.PartnerRecruitmentLead;
import com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerDashboard;
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

   @Autowired CommonComponentUtils componentUtils;
   
   @Autowired FieldStaffDashboardInterface  fieldStaffDashboardInterface;

   @Override
   @Transactional(readOnly = true)
   public Auth getUserLogin(String userName) {
      Auth auth = new Auth();
      try
      {
         if (userName != null && !(userName.isEmpty())) {
         Login login = loginRepository.findByLoginName(userName);
         if (login != null && login.getActive() == CCIConstants.ACTIVE) {
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
               if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.PARTNER_AGENT)) {
                  lt.setUserDetailUrl("/authorize/partneragent/");
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
                  if (loginUsrType.getUserType() != null)
                     if (loginUsrType.getUserType().getUserTypeId() != null)
                        lt.setLoginTypeId(loginUsrType.getUserType().getUserTypeId());
                  lt.setLoginType(loginUsrType.getUserType().getUserTypeName());
                  if (loginUsrType.getUserType() != null)
                     lt.setDefault(loginUsrType.getDefaultUserType() == 0 ? false : true);
                  loginTypeList.add(lt);
            }
            auth.getLoginType().addAll(loginTypeList);
            auth.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            auth.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INACTIVE_LOGIN.getValue(),
                  messageUtil.getMessage(AuthConstants.LOGIN_DISABLED)));
            LOGGER.error(messageUtil.getMessage(AuthConstants.LOGIN_DISABLED));
         }
         return auth;
         }
      } catch (Exception e) {
         auth.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
               messageUtil.getMessage(AuthConstants.LOGIN_FAILED)));
         LOGGER.error(messageUtil.getMessage(AuthConstants.LOGIN_FAILED));
      }
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

   @Override
   @Transactional(readOnly = true)
   public User getCCIUserDetails(String userId) {
      return userManagementService.getUserById(userId);
   }

   @Override
   @Transactional(readOnly = true)
   public PartnerDashboard getPartnerDashboard(String partnerGoId) {
      return partnerService.getPartnerDashboard(partnerGoId);
   }

   @Override
   @Transactional(readOnly = true)
   public PartnerRecruitmentLead getPartnerAgentDashboard(int partnerGoId) {
      return partnerService.getPartnerInquiryLeadData(partnerGoId);
   }

   @Override
   public ErdDashboard getFSDashboard(String goId) {
      return fieldStaffDashboardInterface.getErdDashboardWorkQueues(goId);
   }

}
