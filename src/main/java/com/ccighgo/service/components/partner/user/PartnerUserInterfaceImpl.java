/**
 * 
 */
package com.ccighgo.service.components.partner.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.GoIdSequence;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.db.entities.PartnerPermission;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.PartnerUserRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.RegionManagementMessageConstants;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUserStatus;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUsers;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserDetailAndRoles;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserProgramAccess;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.PasswordUtil;
import com.ccighgo.utils.UuidUtils;

/**
 * @author ravi
 *
 */
@Component
public class PartnerUserInterfaceImpl implements PartnerUserInterface {

   private static final Logger LOGGER = Logger.getLogger(PartnerUserInterfaceImpl.class);

   @Autowired PartnerUserRepository partnerUserRepository;
   
   @Autowired CommonComponentUtils componentUtils;
   
   @Autowired MessageUtils messageUtil;
   
   @Autowired GoIdSequenceRepository goIdSequenceRepository;
   
   @Autowired LoginRepository loginRepository;

   @Override
   public PartnerUsers getAllPartnerUsers(String partnerId) {
      PartnerUsers partnerUsers = new PartnerUsers();
      if (partnerId == null || Integer.valueOf(partnerId) < 0 || Integer.valueOf(partnerId) == 0) {
         partnerUsers.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_SEASON_ID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
      } else {
         try {
            List<PartnerUser> partnerUsersDBList = partnerUserRepository.findByPartnerGoId(Integer.valueOf(partnerId));
            if (partnerUsersDBList != null) {
               partnerUsers.setPartnerUserCount(partnerUsersDBList.size());
               List<com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUser> partnerUsersUIList = new ArrayList<com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUser>();
               for (PartnerUser user : partnerUsersDBList) {
                  com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUser puser = new com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUser();
                  puser.setPartnerUserId(user.getPartnerUserId());
                  puser.setPartnerUserFirstName(user.getFirstName());
                  puser.setPartnerUserLastName(user.getLastName());
                  puser.setPartnerUserEmail(user.getEmail());
                  PartnerUserStatus partnerUserStatus = new PartnerUserStatus();
                  partnerUserStatus.setPartnerUserStatusId(0);
                  partnerUserStatus.setPartnerUserStatus(user.getActive() == CCIConstants.ACTIVE ? "Active" : "Inactive");
                  puser.setPartnerUserStatus(partnerUserStatus);
                  partnerUsersUIList.add(puser);
               }
               partnerUsers.getPartnerUsers().addAll(partnerUsersUIList);
               partnerUsers.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               partnerUsers.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                     messageUtil.getMessage(CCIConstants.NO_RECORD)));
               LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
            }
         } catch (CcighgoException e) {
            partnerUsers.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SUP_REG_LIST.getValue(),
                  messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST)));
            LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST));
         }
      }
      return partnerUsers;
   }

   @Override
   public PartnerUserDetailAndRoles addNewPartnerUser(PartnerUserDetailAndRoles partnerUserDetailAndRoles) {
      
      PartnerUser partnerUser=new PartnerUser();
      
      if(partnerUserDetailAndRoles == null){
         
      }
     // Partner
      //generating GoID 
     /* GoIdSequence goIdSequence = new GoIdSequence();
      goIdSequence = goIdSequenceRepository.save(goIdSequence);
      
      
      Login login = new Login();
      login.setLoginName(partnerUserDetailAndRoles.getUsername());
      login.setPassword(PasswordUtil.hashKey("password"));
      login.setKeyValue(UuidUtils.nextHexUUID());
      login.setCreatedBy(goIdSequence.getGoId());
      login.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      login.setModifiedBy(goIdSequence.getGoId());
      login.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      login.setGoIdSequence(goIdSequence);
      login.setEmail(partnerUserDetailAndRoles.getEmail());
      // login.setUserTypeId(1);
      login = loginRepository.save(login);
      // byte active = 1;
      goIdSequence.setLogin(login);
      partnerUser.setP(goIdSequence);
      cciUser.setCciStaffUserId(goIdSequence.getGoId());
      LoginUserType loginUserType = new LoginUserType();
      loginUserType.setActive(CCIConstants.ACTIVE);
      loginUserType.setUserType(cciUserType);
      loginUserType.setCreatedBy(goIdSequence.getGoId());
      loginUserType.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      loginUserType.setModifiedBy(goIdSequence.getGoId());
      loginUserType.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      loginUserType.setLogin(login);
      loginUserType = loginUserTypeRepository.save(loginUserType);*/
      
     //PartnerUser.setActive(partnerUserDetailAndRoles.getUserStatus() != null ? CCIConstants.ACTIVE: CCIConstants.INACTIVE);
      partnerUser.setEmail(partnerUserDetailAndRoles.getEmail());
      partnerUser.setEmergencyPhone(partnerUserDetailAndRoles.getEmergencyPhone());
      partnerUser.setFax(partnerUserDetailAndRoles.getFax());
      partnerUser.setFirstName(partnerUserDetailAndRoles.getFirstName());
      partnerUser.setLastName(partnerUserDetailAndRoles.getLastName());
     // partnerUser.setLogin(login);
      //partnerUser.setPartner(partner);
      
      
      
      return partnerUserDetailAndRoles;
   }

   @Override
   public PartnerUserDetailAndRoles viewPartnerUser(String partnerUserId) {
      PartnerUserDetailAndRoles partnerUserDetailAndRoles = null;
      if(partnerUserId == null){
         partnerUserDetailAndRoles.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SUP_REG_LIST.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST));
         return partnerUserDetailAndRoles;
      }
      try
      {
          
      partnerUserDetailAndRoles = new PartnerUserDetailAndRoles();
      PartnerUser partnerUser = partnerUserRepository.findOne(Integer.valueOf(partnerUserId));
      
      partnerUserDetailAndRoles.setEmail(partnerUser.getEmail());
      partnerUserDetailAndRoles.setEmergencyPhone(partnerUser.getEmergencyPhone());
      partnerUserDetailAndRoles.setFax(partnerUser.getFax());
      partnerUserDetailAndRoles.setFirstName(partnerUser.getFirstName());
      partnerUserDetailAndRoles.setLastName(partnerUser.getLastName());
      partnerUserDetailAndRoles.setLogoImageURL("");
      partnerUserDetailAndRoles.setLogoUserName("");
      partnerUserDetailAndRoles.setPhone(partnerUser.getPhone());
      partnerUserDetailAndRoles.setSalutation(partnerUser.getSalutation());
      partnerUserDetailAndRoles.setSkypeId(partnerUser.getSkypeId());
      partnerUserDetailAndRoles.setTitle(partnerUser.getTitle());
      partnerUserDetailAndRoles.setUsername(partnerUser.getLogin().getLoginName());
      partnerUserDetailAndRoles.setUserStatus(partnerUser.getActive() == CCIConstants.ACTIVE ? CCIConstants.STATUS_ACTIVE : CCIConstants.STATUS_INACTIVE);
     // partnerUserDetailAndRoles.getProgramsAccess().addAll(partnerUser.getPartnerPermissions());
      partnerUser.getPartnerPermissions();
      
      
      }catch (CcighgoException e) {
         partnerUserDetailAndRoles.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SUP_REG_LIST.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST));
      }
         
      return partnerUserDetailAndRoles;
   }
   
   
   private List<PartnerUserProgramAccess> getProgramAccessTO(PartnerUser partnerUser) {

      List<PartnerUserProgramAccess> partnerUserProgramAccessList = new ArrayList<PartnerUserProgramAccess>();
      List<PartnerPermission> partnerPermissions = partnerUser.getPartnerPermissions();
      
      for (PartnerPermission partnerPermission : partnerPermissions) {
         PartnerUserProgramAccess partnerUserProgramAccess = new PartnerUserProgramAccess();
         
       //  partnerUserProgramAccess.setProgramName(partnerPermission.getP);
         
         partnerUserProgramAccessList.add(partnerUserProgramAccess);
      }

      return partnerUserProgramAccessList;
   }

}
