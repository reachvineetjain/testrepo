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
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerPermission;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.db.entities.UserType;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LoginUserTypeRepository;
import com.ccighgo.jpa.repositories.PartnerPermissionRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerUserRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
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
   
   @Autowired LoginUserTypeRepository loginUserTypeRepository;
   
   @Autowired PartnerRepository partnerRepository;
   
   @Autowired UserTypeRepository userTypeRepository;
   
   @Autowired PartnerPermissionRepository partnerPermissionRepository;

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

      PartnerUser partnerUser = new PartnerUser();
      Partner partner = partnerRepository.findOne(Integer.valueOf(partnerUserDetailAndRoles.getPartnergoId()));

      if (partnerUserDetailAndRoles == null) {

      }
      com.ccighgo.db.entities.UserType partnerUserType = userTypeRepository.findOne(CCIConstants.PARTNER_USER_TYPE);
      if (partnerUserType == null) {
         partnerUserType = new com.ccighgo.db.entities.UserType();
      }
      // Login Details
      Login login = new Login();
      login.setLoginName(partnerUserDetailAndRoles.getUsername());
      login.setPassword(PasswordUtil.hashKey("password"));
      login.setKeyValue(UuidUtils.nextHexUUID());
      login.setCreatedBy(partner.getPartnerGoId());
      login.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      login.setModifiedBy(partner.getPartnerGoId());
      login.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      login.setGoIdSequence(partner.getGoIdSequence());
      login.setEmail(partnerUserDetailAndRoles.getEmail());
      login = loginRepository.save(login);

      // Login User Type
      LoginUserType loginUserType = new LoginUserType();
      loginUserType.setActive(CCIConstants.ACTIVE);
      loginUserType.setUserType(partnerUserType);
      loginUserType.setCreatedBy(partner.getPartnerGoId());
      loginUserType.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      loginUserType.setModifiedBy(partner.getPartnerGoId());
      loginUserType.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      loginUserType.setLogin(login);
      loginUserType = loginUserTypeRepository.save(loginUserType);

      partnerUser.setActive(partnerUserDetailAndRoles.getUserStatus() == true ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
      partnerUser.setEmail(partnerUserDetailAndRoles.getEmail());
      partnerUser.setEmergencyPhone(partnerUserDetailAndRoles.getEmergencyPhone());
      partnerUser.setFax(partnerUserDetailAndRoles.getFax());
      partnerUser.setFirstName(partnerUserDetailAndRoles.getFirstName());
      partnerUser.setLastName(partnerUserDetailAndRoles.getLastName());
      partnerUser.setLogin(login);
      partnerUser.setPartner(partner);
      // partnerUser.setPartnerPermissions();
      // partnerUser.setPartnerUserRoles(partnerUserRoles);

      partnerUser.setPhone(partnerUserDetailAndRoles.getPhone());
      partnerUser.setSalutation(partnerUserDetailAndRoles.getSalutation());
      partnerUser.setSkypeId(partnerUserDetailAndRoles.getSkypeId());
      partnerUser.setTitle(partnerUserDetailAndRoles.getTitle());
      partnerUser = partnerUserRepository.save(partnerUser);
      return viewPartnerUser(partnerUser.getPartnerUserId().toString());
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
      partnerUserDetailAndRoles.setPartnergoId(partnerUser.getPartner().getPartnerGoId().toString());
      partnerUserDetailAndRoles.setPartnerUserId(partnerUser.getPartnerUserId().toString());
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
      partnerUserDetailAndRoles.setUserStatus(partnerUser.getActive() == CCIConstants.ACTIVE ? true : false);
      partnerUserDetailAndRoles.setProgramsAccess(getProgramAccessTO(partnerUser));
     
      }catch (CcighgoException e) {
         partnerUserDetailAndRoles.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SUP_REG_LIST.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST));
      }
         
      return partnerUserDetailAndRoles;
   }
   
   
   private PartnerUserProgramAccess getProgramAccessTO(PartnerUser partnerUser) {
      PartnerUserProgramAccess partnerUserProgramAccess = new PartnerUserProgramAccess();
     // PartnerPermission PartnerPermission = partnerPermissionRepository.findByPartnerUserId(partnerUser);
      PartnerPermission PartnerPermission = partnerUser.getPartnerPermissions();
      
      partnerUserProgramAccess.setCapAccountingInsurance(PartnerPermission.getCapAccountingInsurance());
      partnerUserProgramAccess.setCapAdmin(PartnerPermission.getCapAdmin());
      partnerUserProgramAccess.setCapApplications(PartnerPermission.getCapApplications());
      partnerUserProgramAccess.setCapContracting(PartnerPermission.getCapContracting());
      partnerUserProgramAccess.setCapFlights(PartnerPermission.getCapFlights());
      partnerUserProgramAccess.setCapInsurance(PartnerPermission.getCapInsurance());
      partnerUserProgramAccess.setCapMonitoring(PartnerPermission.getCapMonitoring());
      partnerUserProgramAccess.setCapPlacementInfo(PartnerPermission.getCapPlacementInfo());
      partnerUserProgramAccess.setCapStudentsPreProgram(PartnerPermission.getCapStudentsPreProgram());
      
      partnerUserProgramAccess.setF1AccountingInsurance(PartnerPermission.getF1AccountingInsurance());
      partnerUserProgramAccess.setF1Admin(PartnerPermission.getF1Admin());
      partnerUserProgramAccess.setF1Applications(PartnerPermission.getF1Applications());
      partnerUserProgramAccess.setF1Contracting(PartnerPermission.getF1Contracting());
      partnerUserProgramAccess.setF1Flights(PartnerPermission.getF1Flights());
      partnerUserProgramAccess.setF1Insurance(PartnerPermission.getF1Insurance());
      partnerUserProgramAccess.setF1Monitoring(PartnerPermission.getF1Monitoring());
      partnerUserProgramAccess.setF1PlacementInfo(PartnerPermission.getF1PlacementInfo());
      partnerUserProgramAccess.setF1StudentsPreProgram(PartnerPermission.getF1StudentsPreProgram());
      
      
      partnerUserProgramAccess.setJ1AccountingInsurance(PartnerPermission.getJ1AccountingInsurance());
      partnerUserProgramAccess.setJ1Admin(PartnerPermission.getJ1Admin());
      partnerUserProgramAccess.setJ1Applications(PartnerPermission.getJ1Applications());
      partnerUserProgramAccess.setJ1Contracting(PartnerPermission.getJ1Contracting());
      partnerUserProgramAccess.setJ1Flights(PartnerPermission.getJ1Flights());
      partnerUserProgramAccess.setJ1Insurance(PartnerPermission.getJ1Insurance());
      partnerUserProgramAccess.setJ1Monitoring(PartnerPermission.getJ1Monitoring());
      partnerUserProgramAccess.setJ1PlacementInfo(PartnerPermission.getJ1PlacementInfo());
      partnerUserProgramAccess.setJ1StudentsPreProgram(PartnerPermission.getJ1StudentsPreProgram());
      
      partnerUserProgramAccess.setWtAccoutingInsurance(PartnerPermission.getWtAccoutingInsurance());
      partnerUserProgramAccess.setWtAdmin(PartnerPermission.getWtAdmin());
      partnerUserProgramAccess.setWtApplications(PartnerPermission.getWtApplications());
      partnerUserProgramAccess.setWtContracting(PartnerPermission.getWtContracting());
      partnerUserProgramAccess.setWtFlights(PartnerPermission.getWtFlights());
      partnerUserProgramAccess.setWtInsurance(PartnerPermission.getWtInsurance());
      partnerUserProgramAccess.setWtMonitoring(PartnerPermission.getWtMonitoring());
      partnerUserProgramAccess.setWtPlacementInfo(PartnerPermission.getWtPlacementInfo());
      partnerUserProgramAccess.setWtStudentsPreProgram(PartnerPermission.getWtStudentsPreProgram());
      
      partnerUserProgramAccess.setIhpAccountingInsurance(PartnerPermission.getIhpAccountingInsurance());
      partnerUserProgramAccess.setIhpAdmin(PartnerPermission.getIhpAdmin());
      partnerUserProgramAccess.setIhpApplications(PartnerPermission.getIhpApplications());
      partnerUserProgramAccess.setIhpContracting(PartnerPermission.getIhpContracting());
      partnerUserProgramAccess.setIhpFlights(PartnerPermission.getIhpFlights());
      partnerUserProgramAccess.setIhpInsurance(PartnerPermission.getIhpInsurance());
      partnerUserProgramAccess.setIhpMonitoring(PartnerPermission.getIhpMonitoring());
      partnerUserProgramAccess.setIhpPlacementInfo(PartnerPermission.getIhpPlacementInfo());
      partnerUserProgramAccess.setIhpStudentsPreProgram(PartnerPermission.getIhpStudentsPreProgram());

      return partnerUserProgramAccess;
   }
   
  
}
