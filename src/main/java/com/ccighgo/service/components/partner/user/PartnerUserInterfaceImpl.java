/**
 * 
 */
package com.ccighgo.service.components.partner.user;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.tool.hbm2ddl.TableMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.CCIStaffUser;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.db.entities.LookupGender;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerPermission;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.db.entities.Salutation;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.GenderRepository;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LoginUserTypeRepository;
import com.ccighgo.jpa.repositories.PartnerPermissionRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerUserRepository;
import com.ccighgo.jpa.repositories.SalutationRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.component.emailing.EmailServiceImpl;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerUserMessageConstants;
import com.ccighgo.service.components.errormessages.constants.RegionManagementMessageConstants;
import com.ccighgo.service.components.errormessages.constants.UserManagementMessageConstants;
import com.ccighgo.service.transport.common.beans.deletereq.DeleteRequest;
import com.ccighgo.service.transport.common.response.beans.Header;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUserStatus;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUsers;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserDetailAndRoles;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserProgramAccess;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserProgramAndRole;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserProgramsAndRoles;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserRole;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUsersDetailAndRoles;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUser;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUsers;
import com.ccighgo.service.transport.utility.beans.gender.Gender;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.CCIUtils;
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
   
   @Autowired GenderRepository genderRepository;
   
   @Autowired SalutationRepository salutationRepositotry;
   
   @Autowired EmailServiceImpl email;

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
   public PartnerUserDetailAndRoles addNewPartnerUser(PartnerUserDetailAndRoles partnerUserDetailAndRoles,HttpServletRequest request) {
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
            // return Email already exist
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
         login.setActive(partnerUserDetailAndRoles.getUserStatus() == true ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
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
         
         if (partnerUserDetailAndRoles.getSalutation() != null) {
            Salutation salutation = salutationRepositotry.findOne(partnerUserDetailAndRoles.getSalutation().getSalutationId());
            if (salutation != null)
               partnerUser.setSalutation(salutation);
         }
         partnerUser.setSkypeId(partnerUserDetailAndRoles.getSkypeId());
         partnerUser.setTitle(partnerUserDetailAndRoles.getTitle());
         if (partnerUserDetailAndRoles.getGender() != null) {
            LookupGender gender = genderRepository.findOne(partnerUserDetailAndRoles.getGender().getGenderId());
            if (gender != null)
               partnerUser.setLookupGender(gender);
         }
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
         Login loginEmail = loginRepository.findByEmail(partnerUserDetailAndRoles.getEmail());
         
         if(loginEmail == null){
            viewPartnerUser = setPartnerUserDetailAndRolesStatus(viewPartnerUser, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_PARTNER.getValue(),
                  messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER));
            LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER));
            return viewPartnerUser;
         }

         String body = "<p>This email was sent automatically by CCI Greenheart Online system to inform you that you an online account has been created for you.  </p></br>"
               + "<p>Please go to the following page and follow the instructions to login to the system. </p> " + "<p>"
               + CCIUtils.formResetURL(request).concat(loginEmail.getKeyValue()) + "</p></br>" + "<p>Thank you,</p><p>GO System Support.</p>";
         email.send(loginEmail.getEmail(), CCIConstants.CREATE_CCI_USER_SUBJECT, body, true);
         
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

      partnerPermission.setWtAccountingInsurance(partnerUserProgramAccess.getWtAccountingInsurance() != null ? partnerUserProgramAccess.getWtAccountingInsurance()
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
         if(!partnerUser.getLogin().getLoginName().equalsIgnoreCase(partnerUserDetailAndRoles.getUsername())){
            if (loginRepository.findByLoginName(partnerUserDetailAndRoles.getUsername()) != null) {
               // return username already exsist
               viewPartnerUser = setPartnerUserDetailAndRolesStatus(viewPartnerUser, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.PARTNER_USER_UPDATE_USER_NAME_EXIST.getValue(),
                     messageUtil.getMessage(PartnerUserMessageConstants.PARTNER_USER_UPDATE_USER_NAME_EXIST));
               LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.PARTNER_USER_UPDATE_USER_NAME_EXIST));
               return viewPartnerUser;
            }
         }
         
         if (!partnerUser.getLogin().getEmail().equalsIgnoreCase(partnerUserDetailAndRoles.getEmail())) {
            if (loginRepository.findByEmail(partnerUserDetailAndRoles.getEmail()) != null) {
               // return Email already exist
               viewPartnerUser = setPartnerUserDetailAndRolesStatus(viewPartnerUser, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
                     ErrorCode.PARTNER_USER_UPDATE_USER_EMAIL_EXIST.getValue(), messageUtil.getMessage(PartnerUserMessageConstants.PARTNER_USER_UPDATE_USER_EMAIL_EXIST));
               LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.PARTNER_USER_UPDATE_USER_EMAIL_EXIST));
               return viewPartnerUser;
            }
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
         
         if (partnerUserDetailAndRoles.getSalutation() != null) {
            Salutation salutation = salutationRepositotry.findOne(partnerUserDetailAndRoles.getSalutation().getSalutationId());
            if (salutation != null)
               partnerUser.setSalutation(salutation);
         }
         
         //partnerUser.setSalutation(partnerUserDetailAndRoles.getSalutation());
         partnerUser.setSkypeId(partnerUserDetailAndRoles.getSkypeId());
         partnerUser.setTitle(partnerUserDetailAndRoles.getTitle());
         
         //update gender 
         if (partnerUserDetailAndRoles.getGender() != null) {
            LookupGender gender = genderRepository.findOne(partnerUserDetailAndRoles.getGender().getGenderId());
            if (gender != null)
               partnerUser.setLookupGender(gender);
         }

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
         partnerUserDetailAndRoles.setPartnergoId(String.valueOf(partnerUser.getPartner().getPartnerGoId()));
         partnerUserDetailAndRoles.setPartnerUserId(partnerUser.getPartnerUserId().toString());
         partnerUserDetailAndRoles.setEmail(partnerUser.getEmail());
         partnerUserDetailAndRoles.setEmergencyPhone(partnerUser.getEmergencyPhone());
         partnerUserDetailAndRoles.setFax(partnerUser.getFax());
         partnerUserDetailAndRoles.setFirstName(partnerUser.getFirstName());
         partnerUserDetailAndRoles.setLastName(partnerUser.getLastName());
         partnerUserDetailAndRoles.setLogoImageURL("");
         partnerUserDetailAndRoles.setLogoUserName("");
         partnerUserDetailAndRoles.setPhone(partnerUser.getPhone());
        // 
         com.ccighgo.service.transport.utility.beans.gender.Salutation salutation = new com.ccighgo.service.transport.utility.beans.gender.Salutation();
         if (partnerUser.getSalutation() != null) {
            salutation.setSalutationId(partnerUser.getSalutation().getSalutationId());
            salutation.setSalutationCode(partnerUser.getSalutation().getSalutationName());
            salutation.setActive(partnerUser.getSalutation().getActive());
         }
         partnerUserDetailAndRoles.setSalutation(salutation);
         partnerUserDetailAndRoles.setSkypeId(partnerUser.getSkypeId());
         partnerUserDetailAndRoles.setTitle(partnerUser.getTitle());
         partnerUserDetailAndRoles.setUsername(partnerUser.getLogin().getLoginName());
         partnerUserDetailAndRoles.setUserStatus(partnerUser.getActive() == CCIConstants.ACTIVE ? true : false);
         Gender gender = new Gender();
         if (partnerUser.getLookupGender() != null) {
            gender.setGenderId(partnerUser.getLookupGender().getGenderId());
            gender.setGenderCode(partnerUser.getLookupGender().getGenderName());
         }
         partnerUserDetailAndRoles.setGender(gender);
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

      partnerUserProgramAccess.setWtAccountingInsurance(PartnerPermission.getWtAccountingInsurance() != null ? PartnerPermission.getWtAccountingInsurance() : CCIConstants.INACTIVE);
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
   
   /*@Override
   @Transactional
   public PartnerUsersDetailAndRoles searchPartnerUser(com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUser partnerUser)
   {
      PartnerUsersDetailAndRoles partnerUsersDetailAndRoles = new PartnerUsersDetailAndRoles();
      List<PartnerUserDetailAndRoles> partnerUserDetailAndRolesList = new ArrayList<PartnerUserDetailAndRoles>();
      // firstName,  lastName ,  email, userName , active
      List<Object> partnerUsers = partnerUserRepository.searchPartnerUser(partnerUser.getPartnerUserFirstName(),partnerUser.getPartnerUserLastName(),partnerUser.getPartnerUserEmail(),partnerUser.getPartnerUserLoginName(),partnerUser.getStatus());
      
      if (partnerUsers != null) {
         for (Object object : partnerUsers) {
            PartnerUserDetailAndRoles partnerUserDetailAndRoles = viewPartnerUser(object.toString());
            partnerUserDetailAndRolesList.add(partnerUserDetailAndRoles);
         }
         partnerUsersDetailAndRoles.getPartnerUsersDetailAndRoles().addAll(partnerUserDetailAndRolesList);
      }
      
      return partnerUsersDetailAndRoles;
   }*/
   
 
   
   @Override
   @Transactional
   public DeleteRequest deletePartnerUser(String partnerUserId) {
      DeleteRequest request=new DeleteRequest();
     try{

      if (Integer.valueOf(partnerUserId) > 0) {
         PartnerUser partneruser = partnerUserRepository.findOne(Integer.valueOf(partnerUserId));
         if (partneruser == null) {
            request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
                  messageUtil.getMessage(PartnerUserMessageConstants.FAILED_PARTNER_USER_NULL)));
            LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.FAILED_PARTNER_USER_NULL));
            return request;
         }
         partneruser.setActive(CCIConstants.INACTIVE);
         partnerUserRepository.saveAndFlush(partneruser);
         request.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.PARTNER_USER_cODE.getValue(),
               messageUtil.getMessage((CCIConstants.SERVICE_SUCCESS))));
      } else {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_ID.getValue(),
               messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID));
      }
      }catch (CcighgoException e) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_ID.getValue(),
               messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID));
     }
     
      return request;
   }
   
   @Override
   public List<PartnerUserStatus> getPartnerUserStatuses() {
      List<PartnerUserStatus> partnerUserStatusList = new ArrayList<PartnerUserStatus>();
      List<String> statusList = new ArrayList<String>();
      statusList.add("Active");
      statusList.add("InActive");

      for (String status : statusList) {
         PartnerUserStatus partnerUserStatus = new PartnerUserStatus();
         partnerUserStatus.setPartnerUserStatusId(statusList.indexOf(status) + 1);
         partnerUserStatus.setPartnerUserStatus(status);
         partnerUserStatus.setPartnerUserStatusCode(status == "Active" ? true : false);
         partnerUserStatusList.add(partnerUserStatus);
      }
      return partnerUserStatusList;
   }
   
   @Override
   public Response getPartnerGoIdForPartnerUser(String loginName) {
      Response response = new Response();
      try{
      if (loginName != null) {
         Login login = loginRepository.findByLoginName(loginName);
         if (login != null) {
            if (login.getGoIdSequence().getPartner() != null) {
               Header header = new Header();
               header.setSubject(String.valueOf(login.getGoIdSequence().getPartner().getPartnerGoId()));
               response.setHeader(header);
               response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.PARTNER_USER_cODE.getValue(),
                     messageUtil.getMessage((CCIConstants.SERVICE_SUCCESS))));
            } else {               
               response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_ID.getValue(),
                     messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID)));
               LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID));
            }
         } else {           
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_ID.getValue(),
                  messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID)));
            LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID));
         }
      }
      }catch (CcighgoException e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_ID.getValue(),
               messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID));
     }     
      return response;
   }
   
   @Override
   public PartnerUserProgramsAndRoles getProgramsAndRoles() {

      PartnerUserProgramsAndRoles partnerUserProgramsAndRoles = new PartnerUserProgramsAndRoles();
      List<PartnerUserProgramAndRole> partnerUserProgramAndRoleList = new ArrayList<PartnerUserProgramAndRole>();
      List<String> programList = getPrograms(CCIConstants.PARTNER_PERMISSIONS_TABLE_NAME);
      List<String> rolesList = getRoles(CCIConstants.PARTNER_PERMISSIONS_TABLE_NAME);
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
         partnerUserProgramAndRole.getPartnerUserRole().addAll(partnerUserRoleList);
         partnerUserProgramAndRoleList.add(partnerUserProgramAndRole);
      }

      partnerUserProgramsAndRoles.getPartnerUserProgramAndRole().addAll(partnerUserProgramAndRoleList);
      return partnerUserProgramsAndRoles;
   }
   
  
   private List<String> getPrograms(String tableName) {
      List<String> programList = new ArrayList<String>();
      List<String> tableMetaData = partnerPermissionRepository.getTableMetaData(tableName);
      for (String columnName : tableMetaData) {
         if (!(columnName.equalsIgnoreCase("partnerPermissionsId") || columnName.equalsIgnoreCase("partnerUserId"))) 
         {
            int index = getIndex(columnName);
            if (!(programList.contains(columnName.substring(0, index)))) {
               programList.add(columnName.substring(0, index));
            }
         }
      }
      return programList;
   }
   
   private List<String> getRoles(String tableName) {
      List<String> rolesList = new ArrayList<String>();
      List<String> tableMetaData = partnerPermissionRepository.getTableMetaData(tableName);
      for (String columnName : tableMetaData) {
         if (!(columnName.equalsIgnoreCase("partnerPermissionsId") || columnName.equalsIgnoreCase("partnerUserId"))) 
         {
            int index = getIndex(columnName);
            if (!(rolesList.contains(columnName.substring(index, columnName.length())))) {
               rolesList.add(columnName.substring(index, columnName.length()));
            }
         }
      }
      return rolesList;
   }
   
   private int getIndex(String columnName)
   {
      char[] colChar = columnName.toCharArray();
      int index = 0;
      for (int i = 0; i < colChar.length; i++) {
         if (Character.isUpperCase(colChar[i])) {
            index = i;
            break;
         }
      }
      return index;
   }
   
   private PartnerUserDetailAndRoles setPartnerUserDetailAndRolesStatus(PartnerUserDetailAndRoles partnerUserDetailAndRoles, String code, String type, int serviceCode,
         String message) {
      if (partnerUserDetailAndRoles == null)
         partnerUserDetailAndRoles = new PartnerUserDetailAndRoles();
      partnerUserDetailAndRoles.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return partnerUserDetailAndRoles;
   }
  
}
