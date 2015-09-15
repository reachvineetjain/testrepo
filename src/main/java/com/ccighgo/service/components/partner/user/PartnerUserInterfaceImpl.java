/**
 * 
 */
package com.ccighgo.service.components.partner.user;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.tool.hbm2ddl.TableMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerPermission;
import com.ccighgo.db.entities.PartnerUser;
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
import com.ccighgo.service.components.errormessages.constants.PartnerUserMessageConstants;
import com.ccighgo.service.components.errormessages.constants.RegionManagementMessageConstants;
import com.ccighgo.service.components.errormessages.constants.UserManagementMessageConstants;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUserStatus;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUsers;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserDetailAndRoles;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserProgramAccess;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserProgramAndRole;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserProgramsAndRoles;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserRole;
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
   @Transactional(readOnly = true)
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
   @Transactional(readOnly = true)
   public PartnerUserDetailAndRoles addNewPartnerUser(PartnerUserDetailAndRoles partnerUserDetailAndRoles) {
      PartnerUserDetailAndRoles viewPartnerUser = null;
      if (partnerUserDetailAndRoles == null) {
         viewPartnerUser = setPartnerUserDetailAndRolesStatus(viewPartnerUser, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_PARTNER_USER.getValue(),
               messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER_USER));
         LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER_USER));
         return viewPartnerUser;
      }
      try {
        
         if (loginRepository.findByLoginName(partnerUserDetailAndRoles.getUsername()) != null) {
            // return username already exsist
            viewPartnerUser = setPartnerUserDetailAndRolesStatus(viewPartnerUser, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.PARTNER_USER_CREATE_USER_NAME_EXIST.getValue(),
                  messageUtil.getMessage(PartnerUserMessageConstants.PARTNER_USER_CREATE_USER_NAME_EXIST));
            LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.PARTNER_USER_CREATE_USER_NAME_EXIST));
            return viewPartnerUser;
         }
        
         if (loginRepository.findByEmail(partnerUserDetailAndRoles.getEmail()) != null) {
            // return Email already exsist
            viewPartnerUser = setPartnerUserDetailAndRolesStatus(viewPartnerUser, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.PARTNER_USER_CREATE_USER_EMAIL_EXIST.getValue(),
                  messageUtil.getMessage(PartnerUserMessageConstants.PARTNER_USER_CREATE_USER_EMAIL_EXIST));
            LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.PARTNER_USER_CREATE_USER_EMAIL_EXIST));
            return viewPartnerUser;
         }
         
         PartnerUser partnerUser = new PartnerUser();
         Partner partner = partnerRepository.findOne(Integer.valueOf(partnerUserDetailAndRoles.getPartnergoId()));
         if (partner == null) {
            viewPartnerUser = setPartnerUserDetailAndRolesStatus(viewPartnerUser, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_PARTNER.getValue(),
                  messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER));
            LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER));
            return viewPartnerUser;
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
         partnerUser.setPhone(partnerUserDetailAndRoles.getPhone());
         partnerUser.setSalutation(partnerUserDetailAndRoles.getSalutation());
         partnerUser.setSkypeId(partnerUserDetailAndRoles.getSkypeId());
         partnerUser.setTitle(partnerUserDetailAndRoles.getTitle());
         partnerUser = partnerUserRepository.save(partnerUser);
         if (partnerUserDetailAndRoles.getProgramsAccess() != null) {
            PartnerPermission partnerPermission = getPartnerPermission(partnerUserDetailAndRoles.getProgramsAccess());
            partnerPermission.setPartnerUser(partnerUser);
            partnerPermission =  partnerPermissionRepository.save(partnerPermission);
            partnerUser.setPartnerPermissions(partnerPermission);
         }
         
         viewPartnerUser = viewPartnerUser(partnerUser.getPartnerUserId().toString());
         if (viewPartnerUser == null) {
            viewPartnerUser = setPartnerUserDetailAndRolesStatus(viewPartnerUser, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_PARTNER_USER.getValue(),
                  messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER_USER));
            LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER_USER));
            return viewPartnerUser;
         }
         viewPartnerUser = setPartnerUserDetailAndRolesStatus(viewPartnerUser, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.PARTNER_USER_cODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      } catch (CcighgoException e) {
         viewPartnerUser = setPartnerUserDetailAndRolesStatus(viewPartnerUser, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_PARTNER.getValue(),
               messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER));
         LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER));
      }
      return viewPartnerUser;
   }
   
   
   private PartnerPermission getPartnerPermission(PartnerUserProgramAccess partnerUserProgramAccess) {

      PartnerPermission partnerPermission = new PartnerPermission();

      partnerPermission.setCapAccountingInsurance(partnerUserProgramAccess.getCapAccountingInsurance() != null ? partnerUserProgramAccess.getCapAccountingInsurance()
            : CCIConstants.INACTIVE);
      partnerPermission.setCapAdmin(partnerUserProgramAccess.getCapAdmin() != null ? partnerUserProgramAccess.getCapAdmin() : CCIConstants.INACTIVE);
      partnerPermission.setCapApplications(partnerUserProgramAccess.getCapApplications() != null ? partnerUserProgramAccess.getCapApplications() : CCIConstants.INACTIVE);
      partnerPermission.setCapContracting(partnerUserProgramAccess.getCapContracting() != null ? partnerUserProgramAccess.getCapContracting() : CCIConstants.INACTIVE);
      partnerPermission.setCapFlights(partnerUserProgramAccess.getCapFlights() != null ? partnerUserProgramAccess.getCapFlights() : CCIConstants.INACTIVE);
      partnerPermission.setCapInsurance(partnerUserProgramAccess.getCapInsurance() != null ? partnerUserProgramAccess.getCapInsurance() : CCIConstants.INACTIVE);
      partnerPermission.setCapMonitoring(partnerUserProgramAccess.getCapMonitoring() != null ? partnerUserProgramAccess.getCapMonitoring() : CCIConstants.INACTIVE);
      partnerPermission.setCapPlacementInfo(partnerUserProgramAccess.getCapPlacementInfo() != null ? partnerUserProgramAccess.getCapPlacementInfo() : CCIConstants.INACTIVE);
      partnerPermission.setCapStudentsPreProgram(partnerUserProgramAccess.getCapStudentsPreProgram() != null ? partnerUserProgramAccess.getCapStudentsPreProgram()
            : CCIConstants.INACTIVE);

      partnerPermission.setF1AccountingInsurance(partnerUserProgramAccess.getF1AccountingInsurance() != null ? partnerUserProgramAccess.getF1AccountingInsurance()
            : CCIConstants.INACTIVE);
      partnerPermission.setF1Admin(partnerUserProgramAccess.getF1Admin() != null ? partnerUserProgramAccess.getF1Admin() : CCIConstants.INACTIVE);
      partnerPermission.setF1Applications(partnerUserProgramAccess.getF1Applications() != null ? partnerUserProgramAccess.getF1Applications() : CCIConstants.INACTIVE);
      partnerPermission.setF1Contracting(partnerUserProgramAccess.getF1Contracting() != null ? partnerUserProgramAccess.getF1Contracting() : CCIConstants.INACTIVE);
      partnerPermission.setF1Flights(partnerUserProgramAccess.getF1Flights() != null ? partnerUserProgramAccess.getF1Flights() : CCIConstants.INACTIVE);
      partnerPermission.setF1Insurance(partnerUserProgramAccess.getF1Insurance() != null ? partnerUserProgramAccess.getF1Insurance() : CCIConstants.INACTIVE);
      partnerPermission.setF1Monitoring(partnerUserProgramAccess.getF1Monitoring() != null ? partnerUserProgramAccess.getF1Monitoring() : CCIConstants.INACTIVE);
      partnerPermission.setF1PlacementInfo(partnerUserProgramAccess.getF1PlacementInfo() != null ? partnerUserProgramAccess.getF1PlacementInfo() : CCIConstants.INACTIVE);
      partnerPermission.setF1StudentsPreProgram(partnerUserProgramAccess.getF1StudentsPreProgram() != null ? partnerUserProgramAccess.getF1StudentsPreProgram()
            : CCIConstants.INACTIVE);

      partnerPermission.setJ1AccountingInsurance(partnerUserProgramAccess.getJ1AccountingInsurance() != null ? partnerUserProgramAccess.getJ1AccountingInsurance()
            : CCIConstants.INACTIVE);
      partnerPermission.setJ1Admin(partnerUserProgramAccess.getJ1Admin() != null ? partnerUserProgramAccess.getJ1Admin() : CCIConstants.INACTIVE);
      partnerPermission.setJ1Applications(partnerUserProgramAccess.getJ1Applications() != null ? partnerUserProgramAccess.getJ1Applications() : CCIConstants.INACTIVE);
      partnerPermission.setJ1Contracting(partnerUserProgramAccess.getJ1Contracting() != null ? partnerUserProgramAccess.getJ1Contracting() : CCIConstants.INACTIVE);
      partnerPermission.setJ1Flights(partnerUserProgramAccess.getJ1Flights() != null ? partnerUserProgramAccess.getJ1Flights() : CCIConstants.INACTIVE);
      partnerPermission.setJ1Insurance(partnerUserProgramAccess.getJ1Insurance() != null ? partnerUserProgramAccess.getJ1Insurance() : CCIConstants.INACTIVE);
      partnerPermission.setJ1Monitoring(partnerUserProgramAccess.getJ1Monitoring() != null ? partnerUserProgramAccess.getJ1Monitoring() : CCIConstants.INACTIVE);
      partnerPermission.setJ1PlacementInfo(partnerUserProgramAccess.getJ1PlacementInfo() != null ? partnerUserProgramAccess.getJ1PlacementInfo() : CCIConstants.INACTIVE);
      partnerPermission.setJ1StudentsPreProgram(partnerUserProgramAccess.getJ1StudentsPreProgram() != null ? partnerUserProgramAccess.getJ1StudentsPreProgram()
            : CCIConstants.INACTIVE);

      partnerPermission.setWtAccoutingInsurance(partnerUserProgramAccess.getWtAccoutingInsurance() != null ? partnerUserProgramAccess.getWtAccoutingInsurance()
            : CCIConstants.INACTIVE);
      partnerPermission.setWtAdmin(partnerUserProgramAccess.getWtAdmin() != null ? partnerUserProgramAccess.getWtAdmin() : CCIConstants.INACTIVE);
      partnerPermission.setWtApplications(partnerUserProgramAccess.getWtApplications() != null ? partnerUserProgramAccess.getWtApplications() : CCIConstants.INACTIVE);
      partnerPermission.setWtContracting(partnerUserProgramAccess.getWtContracting() != null ? partnerUserProgramAccess.getWtContracting() : CCIConstants.INACTIVE);
      partnerPermission.setWtFlights(partnerUserProgramAccess.getWtFlights() != null ? partnerUserProgramAccess.getWtFlights() : CCIConstants.INACTIVE);
      partnerPermission.setWtInsurance(partnerUserProgramAccess.getWtInsurance() != null ? partnerUserProgramAccess.getWtInsurance() : CCIConstants.INACTIVE);
      partnerPermission.setWtMonitoring(partnerUserProgramAccess.getWtMonitoring() != null ? partnerUserProgramAccess.getWtMonitoring() : CCIConstants.INACTIVE);
      partnerPermission.setWtPlacementInfo(partnerUserProgramAccess.getWtPlacementInfo() != null ? partnerUserProgramAccess.getWtPlacementInfo() : CCIConstants.INACTIVE);
      partnerPermission.setWtStudentsPreProgram(partnerUserProgramAccess.getWtStudentsPreProgram() != null ? partnerUserProgramAccess.getWtStudentsPreProgram()
            : CCIConstants.INACTIVE);

      partnerPermission.setIhpAccountingInsurance(partnerUserProgramAccess.getIhpAccountingInsurance() != null ? partnerUserProgramAccess.getIhpAccountingInsurance()
            : CCIConstants.INACTIVE);
      partnerPermission.setIhpAdmin(partnerUserProgramAccess.getIhpAdmin() != null ? partnerUserProgramAccess.getIhpAdmin() : CCIConstants.INACTIVE);
      partnerPermission.setIhpApplications(partnerUserProgramAccess.getIhpApplications() != null ? partnerUserProgramAccess.getIhpApplications() : CCIConstants.INACTIVE);
      partnerPermission.setIhpContracting(partnerUserProgramAccess.getIhpContracting() != null ? partnerUserProgramAccess.getIhpContracting() : CCIConstants.INACTIVE);
      partnerPermission.setIhpFlights(partnerUserProgramAccess.getIhpFlights() != null ? partnerUserProgramAccess.getIhpFlights() : CCIConstants.INACTIVE);
      partnerPermission.setIhpInsurance(partnerUserProgramAccess.getIhpInsurance() != null ? partnerUserProgramAccess.getIhpInsurance() : CCIConstants.INACTIVE);
      partnerPermission.setIhpMonitoring(partnerUserProgramAccess.getIhpMonitoring() != null ? partnerUserProgramAccess.getIhpMonitoring() : CCIConstants.INACTIVE);
      partnerPermission.setIhpPlacementInfo(partnerUserProgramAccess.getIhpPlacementInfo() != null ? partnerUserProgramAccess.getIhpPlacementInfo() : CCIConstants.INACTIVE);
      partnerPermission.setIhpStudentsPreProgram(partnerUserProgramAccess.getIhpStudentsPreProgram() != null ? partnerUserProgramAccess.getIhpStudentsPreProgram()
            : CCIConstants.INACTIVE);

      return partnerPermission;
   }
   
   @Override
   @Transactional(readOnly = true)
   public PartnerUserDetailAndRoles updatePartnerUser(PartnerUserDetailAndRoles partnerUserDetailAndRoles) {

      PartnerUserDetailAndRoles viewPartnerUser = null;
      if (partnerUserDetailAndRoles == null) {
         viewPartnerUser = setPartnerUserDetailAndRolesStatus(viewPartnerUser, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_PARTNER_USER.getValue(),
               messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER_USER));
         LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER_USER));
         return viewPartnerUser;
      }
      try {
         PartnerUser partnerUser = partnerUserRepository.findOne(Integer.valueOf(partnerUserDetailAndRoles.getPartnerUserId()));

         if (partnerUser == null) {
            viewPartnerUser = setPartnerUserDetailAndRolesStatus(viewPartnerUser, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_PARTNER_USER.getValue(),
                  messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER_USER));
            LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER_USER));
            return viewPartnerUser;
         }

         partnerUser.getLogin().setLoginName(partnerUserDetailAndRoles.getUsername());
         partnerUser.getLogin().setEmail(partnerUserDetailAndRoles.getEmail());
         partnerUser.getLogin().setModifiedBy(partnerUser.getPartner().getPartnerGoId());
         partnerUser.getLogin().setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));

         partnerUser.setPartnerUserId(Integer.valueOf(partnerUserDetailAndRoles.getPartnerUserId()));
         partnerUser.setActive(partnerUserDetailAndRoles.getUserStatus() == true ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         partnerUser.setEmail(partnerUserDetailAndRoles.getEmail());
         partnerUser.setEmergencyPhone(partnerUserDetailAndRoles.getEmergencyPhone());
         partnerUser.setFax(partnerUserDetailAndRoles.getFax());
         partnerUser.setFirstName(partnerUserDetailAndRoles.getFirstName());
         partnerUser.setLastName(partnerUserDetailAndRoles.getLastName());
         partnerUser.setPhone(partnerUserDetailAndRoles.getPhone());
         partnerUser.setSalutation(partnerUserDetailAndRoles.getSalutation());
         partnerUser.setSkypeId(partnerUserDetailAndRoles.getSkypeId());
         partnerUser.setTitle(partnerUserDetailAndRoles.getTitle());

         if (partnerUserDetailAndRoles.getProgramsAccess() != null) {
            PartnerPermission partnerPermission = getPartnerPermission(partnerUserDetailAndRoles.getProgramsAccess());
            partnerPermission.setPartnerPermissionsId(partnerUser.getPartnerPermissions().getPartnerPermissionsId());
            partnerUser.setPartnerPermissions(partnerPermission);
            partnerPermission.setPartnerUser(partnerUser);
            /*partnerPermission =partnerPermissionRepository.save(partnerPermission);
            partnerPermissionRepository.flush();*/
         }
         // partnerUser.setPartnerUserRoles(new ArrayList<PartnerUserRole>());
         partnerUser = partnerUserRepository.save(partnerUser);
         partnerUserRepository.flush();
         viewPartnerUser = viewPartnerUser(partnerUser.getPartnerUserId().toString());
         if (viewPartnerUser == null) {
            viewPartnerUser = setPartnerUserDetailAndRolesStatus(viewPartnerUser, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_PARTNER_USER.getValue(),
                  messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER_USER));
            LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER_USER));
            return viewPartnerUser;
         }
         viewPartnerUser = setPartnerUserDetailAndRolesStatus(viewPartnerUser, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.PARTNER_USER_cODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      } catch (CcighgoException e) {
         partnerUserDetailAndRoles = setPartnerUserDetailAndRolesStatus(partnerUserDetailAndRoles, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
               ErrorCode.FAILED_UPDATE_PARTNER_USER.getValue(), messageUtil.getMessage(PartnerUserMessageConstants.FAILED_UPDATE_PARTNER_USER));
         LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.FAILED_UPDATE_PARTNER_USER));
      }
      return viewPartnerUser;

   }
   
   
   @Override
   @Transactional(readOnly = true)
   public PartnerUserDetailAndRoles viewPartnerUser(String partnerUserId) {
      PartnerUserDetailAndRoles partnerUserDetailAndRoles = null;
      if (partnerUserId == null) {
         partnerUserDetailAndRoles = setPartnerUserDetailAndRolesStatus(partnerUserDetailAndRoles, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
               ErrorCode.FAILED_GET_PARTNER_USER_ID_NULL.getValue(), messageUtil.getMessage(PartnerUserMessageConstants.FAILED_PARTNER_USER_NULL));
         LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.FAILED_PARTNER_USER_NULL));
         return partnerUserDetailAndRoles;
      }
      try {

         partnerUserDetailAndRoles = new PartnerUserDetailAndRoles();
         PartnerUser partnerUser = partnerUserRepository.findOne(Integer.valueOf(partnerUserId));
         if (partnerUser == null) {
            partnerUserDetailAndRoles = setPartnerUserDetailAndRolesStatus(partnerUserDetailAndRoles, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
                  ErrorCode.FAILED_GET_PARTNER_USER.getValue(), messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER_USER));
            LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER_USER));
            return partnerUserDetailAndRoles;
         }
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
         if (partnerUser.getPartnerPermissions() != null) {
            partnerUserDetailAndRoles.setProgramsAccess(getProgramAccessTO(partnerUser));
         }
         partnerUserDetailAndRoles = setPartnerUserDetailAndRolesStatus(partnerUserDetailAndRoles, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO,
               ErrorCode.PARTNER_USER_cODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      } catch (CcighgoException e) {
         partnerUserDetailAndRoles = setPartnerUserDetailAndRolesStatus(partnerUserDetailAndRoles, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
               ErrorCode.FAILED_GET_PARTNER_USER.getValue(), messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER_USER));
         LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER_USER));
      }

      return partnerUserDetailAndRoles;
   }
   
   
   private PartnerUserProgramAccess getProgramAccessTO(PartnerUser partnerUser) {
      PartnerUserProgramAccess partnerUserProgramAccess = new PartnerUserProgramAccess();
      // PartnerPermission PartnerPermission = partnerPermissionRepository.findByPartnerUserId(partnerUser);
      PartnerPermission PartnerPermission = partnerUser.getPartnerPermissions();

      partnerUserProgramAccess.setCapAccountingInsurance(PartnerPermission.getCapAccountingInsurance() != null ? PartnerPermission.getCapAccountingInsurance()
            : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setCapAdmin(PartnerPermission.getCapAdmin() != null ? PartnerPermission.getCapAdmin() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setCapApplications(PartnerPermission.getCapApplications() != null ? PartnerPermission.getCapApplications() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setCapContracting(PartnerPermission.getCapContracting() != null ? PartnerPermission.getCapContracting() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setCapFlights(PartnerPermission.getCapFlights() != null ? PartnerPermission.getCapFlights() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setCapInsurance(PartnerPermission.getCapInsurance() != null ? PartnerPermission.getCapInsurance() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setCapMonitoring(PartnerPermission.getCapMonitoring() != null ? PartnerPermission.getCapMonitoring() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setCapPlacementInfo(PartnerPermission.getCapPlacementInfo() != null ? PartnerPermission.getCapPlacementInfo() : CCIConstants.INACTIVE);
      partnerUserProgramAccess
            .setCapStudentsPreProgram(PartnerPermission.getCapStudentsPreProgram() != null ? PartnerPermission.getCapStudentsPreProgram() : CCIConstants.INACTIVE);

      partnerUserProgramAccess
            .setF1AccountingInsurance(PartnerPermission.getF1AccountingInsurance() != null ? PartnerPermission.getF1AccountingInsurance() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setF1Admin(PartnerPermission.getF1Admin() != null ? PartnerPermission.getF1Admin() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setF1Applications(PartnerPermission.getF1Applications() != null ? PartnerPermission.getF1Applications() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setF1Contracting(PartnerPermission.getF1Contracting() != null ? PartnerPermission.getF1Contracting() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setF1Flights(PartnerPermission.getF1Flights() != null ? PartnerPermission.getF1Flights() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setF1Insurance(PartnerPermission.getF1Insurance() != null ? PartnerPermission.getF1Insurance() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setF1Monitoring(PartnerPermission.getF1Monitoring() != null ? PartnerPermission.getF1Monitoring() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setF1PlacementInfo(PartnerPermission.getF1PlacementInfo() != null ? PartnerPermission.getF1PlacementInfo() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setF1StudentsPreProgram(PartnerPermission.getF1StudentsPreProgram() != null ? PartnerPermission.getF1StudentsPreProgram() : CCIConstants.INACTIVE);

      partnerUserProgramAccess
            .setJ1AccountingInsurance(PartnerPermission.getJ1AccountingInsurance() != null ? PartnerPermission.getJ1AccountingInsurance() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setJ1Admin(PartnerPermission.getJ1Admin() != null ? PartnerPermission.getJ1Admin() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setJ1Applications(PartnerPermission.getJ1Applications() != null ? PartnerPermission.getJ1Applications() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setJ1Contracting(PartnerPermission.getJ1Contracting() != null ? PartnerPermission.getJ1Contracting() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setJ1Flights(PartnerPermission.getJ1Flights() != null ? PartnerPermission.getJ1Flights() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setJ1Insurance(PartnerPermission.getJ1Insurance() != null ? PartnerPermission.getJ1Insurance() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setJ1Monitoring(PartnerPermission.getJ1Monitoring() != null ? PartnerPermission.getJ1Monitoring() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setJ1PlacementInfo(PartnerPermission.getJ1PlacementInfo() != null ? PartnerPermission.getJ1PlacementInfo() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setJ1StudentsPreProgram(PartnerPermission.getJ1StudentsPreProgram() != null ? PartnerPermission.getJ1StudentsPreProgram() : CCIConstants.INACTIVE);

      partnerUserProgramAccess.setWtAccoutingInsurance(PartnerPermission.getWtAccoutingInsurance() != null ? PartnerPermission.getWtAccoutingInsurance() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setWtAdmin(PartnerPermission.getWtAdmin() != null ? PartnerPermission.getWtAdmin() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setWtApplications(PartnerPermission.getWtApplications() != null ? PartnerPermission.getWtApplications() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setWtContracting(PartnerPermission.getWtContracting() != null ? PartnerPermission.getWtContracting() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setWtFlights(PartnerPermission.getWtFlights() != null ? PartnerPermission.getWtFlights() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setWtInsurance(PartnerPermission.getWtInsurance() != null ? PartnerPermission.getWtInsurance() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setWtMonitoring(PartnerPermission.getWtMonitoring() != null ? PartnerPermission.getWtMonitoring() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setWtPlacementInfo(PartnerPermission.getWtPlacementInfo() != null ? PartnerPermission.getWtPlacementInfo() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setWtStudentsPreProgram(PartnerPermission.getWtStudentsPreProgram() != null ? PartnerPermission.getWtStudentsPreProgram() : CCIConstants.INACTIVE);

      partnerUserProgramAccess.setIhpAccountingInsurance(PartnerPermission.getIhpAccountingInsurance() != null ? PartnerPermission.getIhpAccountingInsurance()
            : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setIhpAdmin(PartnerPermission.getIhpAdmin() != null ? PartnerPermission.getIhpAdmin() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setIhpApplications(PartnerPermission.getIhpApplications() != null ? PartnerPermission.getIhpApplications() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setIhpContracting(PartnerPermission.getIhpContracting() != null ? PartnerPermission.getIhpContracting() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setIhpFlights(PartnerPermission.getIhpFlights() != null ? PartnerPermission.getIhpFlights() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setIhpInsurance(PartnerPermission.getIhpInsurance() != null ? PartnerPermission.getIhpInsurance() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setIhpMonitoring(PartnerPermission.getIhpMonitoring() != null ? PartnerPermission.getIhpMonitoring() : CCIConstants.INACTIVE);
      partnerUserProgramAccess.setIhpPlacementInfo(PartnerPermission.getIhpPlacementInfo() != null ? PartnerPermission.getIhpPlacementInfo() : CCIConstants.INACTIVE);
      partnerUserProgramAccess
            .setIhpStudentsPreProgram(PartnerPermission.getIhpStudentsPreProgram() != null ? PartnerPermission.getIhpStudentsPreProgram() : CCIConstants.INACTIVE);

      return partnerUserProgramAccess;
   }
   
   @Override
   public PartnerUserProgramsAndRoles getProgramsAndRoles()
 {
      /*
       * TableMetadata metadata= partnerPermissionRepository.findByTabelName("PartnerPermissions");
       * System.out.println(metadata);
       */
      PartnerUserProgramsAndRoles partnerUserProgramsAndRoles = new PartnerUserProgramsAndRoles();
      List<PartnerUserProgramAndRole> partnerUserProgramAndRoleList = new ArrayList<PartnerUserProgramAndRole>();
      List<String> programList = getPrograms();
      List<String> rolesList = getRoles();
      // call the below developed method here and other transport classes are all already developed
      for (String program : programList) {
         PartnerUserProgramAndRole partnerUserProgramAndRole = new PartnerUserProgramAndRole();
         List<PartnerUserRole> partnerUserRoleList = new ArrayList<PartnerUserRole>();
         partnerUserProgramAndRole.setProgramName(program);
         for (String role : rolesList) {
            PartnerUserRole partnerUserRole = new PartnerUserRole();
            partnerUserRole.setRoleName(role);
            partnerUserRole.setEnabled(CCIConstants.INACTIVE);
            partnerUserRoleList.add(partnerUserRole);
         }
         partnerUserProgramAndRole.setPartnerUserRole(partnerUserRoleList);
         partnerUserProgramAndRoleList.add(partnerUserProgramAndRole);
      }

      partnerUserProgramsAndRoles.setPartnerUserProgramAndRole(partnerUserProgramAndRoleList);
      getMetaData("PartnerPermissions");
      return partnerUserProgramsAndRoles;
   }
   
   
   private void getMetaData(String tableName)
   {
      List<String> tableMetaData = partnerPermissionRepository.getTableMetaData(tableName);
      for (String string : tableMetaData) {
         //have to write the logic here to split the column name and create to lists(programs list and roles list)
         System.out.println(string);
      }
   }
   
   private List<String> getPrograms()
   {
      List<String> programList = new ArrayList<String>();
      programList.add("j1");
      programList.add("f1");
      programList.add("ihp");
      programList.add("wt");
      programList.add("cap");
      return programList;
   }
   
   private List<String> getRoles() {
      List<String> rolesList = new ArrayList<String>();
      rolesList.add("Admin");
      rolesList.add("Applications");
      rolesList.add("Flights");
      rolesList.add("PlacementInfo");
      rolesList.add("Monitoring");
      rolesList.add("AccountingInsurance");
      rolesList.add("StudentsPreProgram");
      rolesList.add("Contracting");
      rolesList.add("Insurance");
      return rolesList;
   }
   
   private PartnerUserDetailAndRoles setPartnerUserDetailAndRolesStatus(PartnerUserDetailAndRoles partnerUserDetailAndRoles, String code, String type, int serviceCode,
         String message) {
      if (partnerUserDetailAndRoles == null)
         partnerUserDetailAndRoles = new PartnerUserDetailAndRoles();
      partnerUserDetailAndRoles.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return partnerUserDetailAndRoles;
   }
  
}
