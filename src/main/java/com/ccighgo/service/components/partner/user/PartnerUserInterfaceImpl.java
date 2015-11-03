/**
 * 
 */
package com.ccighgo.service.components.partner.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.GoIdSequence;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerOffice;
import com.ccighgo.db.entities.PartnerPermission;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.GenderRepository;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LoginUserTypeRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeRepository;
import com.ccighgo.jpa.repositories.PartnerPermissionRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerUserRepository;
import com.ccighgo.jpa.repositories.SalutationRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.component.emailing.EmailServiceImpl;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminSeasonConstants;
import com.ccighgo.service.components.errormessages.constants.RegionManagementMessageConstants;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.partner.user.details.PartnerUserDetails;
import com.ccighgo.service.transport.partner.beans.partner.user.details.Permissions;
import com.ccighgo.service.transport.partner.beans.partner.user.details.Programs;
import com.ccighgo.service.transport.partner.beans.partner.user.details.UserAddressCountry;
import com.ccighgo.service.transport.partner.beans.partner.user.details.UserGender;
import com.ccighgo.service.transport.partner.beans.partner.user.details.UserOffice;
import com.ccighgo.service.transport.partner.beans.partner.user.details.UserSalutation;
import com.ccighgo.service.transport.partner.beans.partner.user.office.PartnerUserOffices;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUsers;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.CCIUtils;
import com.ccighgo.utils.PasscodeGenerator;
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
   @Autowired PartnerOfficeRepository partnerOfficeRepository;

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
               partnerUsers.setPartnerGoId(Integer.valueOf(partnerId));
               List<com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUser> partnerUsersUIList = new ArrayList<com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUser>();
               for (PartnerUser user : partnerUsersDBList) {
                  com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUser puser = new com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUser();
                  puser.setPartnerUserId(user.getPartnerUserId());
                  puser.setPartnerUserFirstName(user.getFirstName());
                  puser.setPartnerUserLastName(user.getLastName());
                  puser.setPartnerUserLoginName(user.getLogin().getLoginName());
                  puser.setPartnerUserEmail(user.getEmail());
                  puser.setPartnerUserStatus(user.getActive() == CCIConstants.ACTIVE ? 1 : 0);
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
   @Transactional
   public Response updatePartnerUserStatus(String statusVal, String partnerUserId) {
      Response response = new Response();
      if (partnerUserId == null) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID));
         return response;
      }
      if (statusVal == null || !(Integer.valueOf(statusVal) == 0 || Integer.valueOf(statusVal) == 1)) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.PARTNER_ADMIN_SEASON_INVALID_STATUS_VALUE)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.PARTNER_ADMIN_SEASON_INVALID_STATUS_VALUE));
         return response;
      } else {
         try {
            PartnerUser partnerUser = partnerUserRepository.findOne(Integer.valueOf(partnerUserId));
            if (Integer.valueOf(statusVal) == 1) {
               partnerUser.setActive(CCIConstants.ACTIVE);
            }
            if (Integer.valueOf(statusVal) == 0) {
               partnerUser.setActive(CCIConstants.INACTIVE);
            }
            partnerUserRepository.saveAndFlush(partnerUser);
            response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } catch (CcighgoException e) {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS)));
            LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS));
         }
      }
      return response;
   }

   @Override
   @Transactional(readOnly = true)
   public PartnerUserDetails getPartnerUserDetails(String partnerUserId) {
      PartnerUserDetails partnerUserDetails = new PartnerUserDetails();
      if (partnerUserId == null) {
         partnerUserDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID));
         return partnerUserDetails;
      } else {
         try {
            PartnerUser partnerUser = partnerUserRepository.findOne(Integer.valueOf(partnerUserId));
            partnerUserDetails.setPartnerGoId(partnerUser.getPartner().getPartnerGoId());
            partnerUserDetails.setPartnerUserId(partnerUser.getPartnerUserId());
            partnerUserDetails.setUserActiveStatus(partnerUser.getActive() == CCIConstants.ACTIVE ? 1 : 0);
            partnerUserDetails.setUserFirstName(partnerUser.getFirstName());
            partnerUserDetails.setUserLastName(partnerUser.getLastName());
            // salutation
            UserSalutation salutation = new UserSalutation();
            salutation.setSalutationId(partnerUser.getSalutation().getSalutationId());
            salutation.setSalutation(partnerUser.getSalutation().getSalutationName());
            partnerUserDetails.setUserSalutation(salutation);

            partnerUserDetails.setUserDesignation(partnerUser.getTitle());
            partnerUserDetails.setUserEmail(partnerUser.getEmail());
            partnerUserDetails.setUserPhone(partnerUser.getPhone());
            partnerUserDetails.setUserEmergencyPhone(partnerUser.getEmergencyPhone());
            partnerUserDetails.setUserFax(partnerUser.getFax());
            partnerUserDetails.setUserLoginName(partnerUser.getLogin().getLoginName());
            partnerUserDetails.setUserSkypeId(partnerUser.getSkypeId());
            partnerUserDetails.setUserPictureUrl(partnerUser.getPhoto());
            // gender
            UserGender gender = new UserGender();
            gender.setGenderId(partnerUser.getLookupGender().getGenderId());
            gender.setGender(partnerUser.getLookupGender().getGenderName());
            partnerUserDetails.setUserGender(gender);

            // partner office
            List<UserOffice> userOfficesList = null;
            if (partnerUser.getPartner().getPartnerOffices() != null) {
               userOfficesList = new ArrayList<UserOffice>();
               for (PartnerOffice pOffice : partnerUser.getPartner().getPartnerOffices()) {
                  UserOffice usrOffice = new UserOffice();
                  usrOffice.setUserOfficeId(pOffice.getPartnerOfficeId());
                  usrOffice.setOfficeAddressLineOne(pOffice.getAdressOne());
                  usrOffice.setOfficeAddressLineTwo(pOffice.getAdressTwo());
                  usrOffice.setCity(pOffice.getCity());
                  usrOffice.setZipCode(pOffice.getPostalCode());

                  UserAddressCountry userAddressCountry = new UserAddressCountry();
                  userAddressCountry.setOfficeAddressCountryId(pOffice.getLookupCountry().getCountryId());
                  userAddressCountry.setOfficeAddressCountryISOCode(pOffice.getLookupCountry().getCountryCode());
                  userAddressCountry.setOfficeAddressCountryName(pOffice.getLookupCountry().getCountryName());
                  usrOffice.setOfficeAddressCountry(userAddressCountry);

                  usrOffice.setOfficePhone(pOffice.getPhoneNumber());
                  usrOffice.setOfficeFax(pOffice.getFaxNumber());
                  usrOffice.setOfficeEmail(pOffice.getPartner().getEmail());
                  usrOffice.setOfficeWebsite(pOffice.getWebsite());
                  if (partnerUser.getPartnerOffice() != null) {
                     if (partnerUser.getPartnerOffice().getPartnerOfficeId() == pOffice.getPartnerOfficeId()) {
                        usrOffice.setIsPrimary(true);
                     } else {
                        usrOffice.setIsPrimary(false);
                     }
                  }
                  userOfficesList.add(usrOffice);
               }
            }
            partnerUserDetails.getUserOffices().addAll(userOfficesList);

            List<Programs> userProgramsAndPermissions = new ArrayList<Programs>();
            PartnerPermission partnerPermission = partnerPermissionRepository.findByPartnerUserId(partnerUser.getPartnerUserId());
            if (partnerPermission != null) {
               Programs j1Program = new Programs();
               j1Program.setProgramName("J1HS");
               Permissions j1Permissions = new Permissions();
               j1Permissions.setAccounting(partnerPermission.getJ1AccountingInsurance() == CCIConstants.ACTIVE ? true : false);
               j1Permissions.setAdmin(partnerPermission.getJ1Admin() == CCIConstants.ACTIVE ? true : false);
               j1Permissions.setApplications(partnerPermission.getJ1Applications() == CCIConstants.ACTIVE ? true : false);
               j1Permissions.setContracting(partnerPermission.getJ1Contracting() == CCIConstants.ACTIVE ? true : false);
               j1Permissions.setFlights(partnerPermission.getJ1Flights() == CCIConstants.ACTIVE ? true : false);
               j1Permissions.setInsurance(partnerPermission.getJ1Insurance() == CCIConstants.ACTIVE ? true : false);
               j1Permissions.setMonitoring(partnerPermission.getJ1Monitoring() == CCIConstants.ACTIVE ? true : false);
               j1Permissions.setPlacementInfo(partnerPermission.getJ1PlacementInfo() == CCIConstants.ACTIVE ? true : false);
               j1Permissions.setStudentsPreProgram(partnerPermission.getJ1StudentsPreProgram() == CCIConstants.ACTIVE ? true : false);
               j1Program.setPermissions(j1Permissions);
               userProgramsAndPermissions.add(j1Program);

               Programs f1Program = new Programs();
               f1Program.setProgramName(CCIConstants.HSP_F1);
               Permissions f1Permissions = new Permissions();
               f1Permissions.setAccounting(partnerPermission.getF1AccountingInsurance() == CCIConstants.ACTIVE ? true : false);
               f1Permissions.setAdmin(partnerPermission.getF1Admin() == CCIConstants.ACTIVE ? true : false);
               f1Permissions.setApplications(partnerPermission.getF1Applications() == CCIConstants.ACTIVE ? true : false);
               f1Permissions.setContracting(partnerPermission.getF1Contracting() == CCIConstants.ACTIVE ? true : false);
               f1Permissions.setFlights(partnerPermission.getF1Flights() == CCIConstants.ACTIVE ? true : false);
               f1Permissions.setInsurance(partnerPermission.getF1Insurance() == CCIConstants.ACTIVE ? true : false);
               f1Permissions.setMonitoring(partnerPermission.getF1Monitoring() == CCIConstants.ACTIVE ? true : false);
               f1Permissions.setPlacementInfo(partnerPermission.getF1PlacementInfo() == CCIConstants.ACTIVE ? true : false);
               f1Permissions.setStudentsPreProgram(partnerPermission.getF1StudentsPreProgram() == CCIConstants.ACTIVE ? true : false);
               f1Program.setPermissions(f1Permissions);
               userProgramsAndPermissions.add(f1Program);

               Programs ihpProgram = new Programs();
               j1Program.setProgramName("IHP");
               Permissions ihpPermissions = new Permissions();
               ihpPermissions.setAccounting(partnerPermission.getIhpAccountingInsurance() == CCIConstants.ACTIVE ? true : false);
               ihpPermissions.setAdmin(partnerPermission.getIhpAdmin() == CCIConstants.ACTIVE ? true : false);
               ihpPermissions.setApplications(partnerPermission.getIhpApplications() == CCIConstants.ACTIVE ? true : false);
               ihpPermissions.setContracting(partnerPermission.getIhpContracting() == CCIConstants.ACTIVE ? true : false);
               ihpPermissions.setFlights(partnerPermission.getIhpFlights() == CCIConstants.ACTIVE ? true : false);
               ihpPermissions.setInsurance(partnerPermission.getIhpInsurance() == CCIConstants.ACTIVE ? true : false);
               ihpPermissions.setMonitoring(partnerPermission.getIhpMonitoring() == CCIConstants.ACTIVE ? true : false);
               ihpPermissions.setPlacementInfo(partnerPermission.getIhpPlacementInfo() == CCIConstants.ACTIVE ? true : false);
               ihpPermissions.setStudentsPreProgram(partnerPermission.getIhpStudentsPreProgram() == CCIConstants.ACTIVE ? true : false);
               ihpProgram.setPermissions(ihpPermissions);
               userProgramsAndPermissions.add(ihpProgram);

               Programs wntProgram = new Programs();
               wntProgram.setProgramName("W&T");
               Permissions wntPermissions = new Permissions();
               wntPermissions.setAccounting(partnerPermission.getWtAccountingInsurance() == CCIConstants.ACTIVE ? true : false);
               wntPermissions.setAdmin(partnerPermission.getWtAdmin() == CCIConstants.ACTIVE ? true : false);
               wntPermissions.setApplications(partnerPermission.getWtApplications() == CCIConstants.ACTIVE ? true : false);
               wntPermissions.setContracting(partnerPermission.getWtContracting() == CCIConstants.ACTIVE ? true : false);
               wntPermissions.setFlights(partnerPermission.getWtFlights() == CCIConstants.ACTIVE ? true : false);
               wntPermissions.setInsurance(partnerPermission.getWtInsurance() == CCIConstants.ACTIVE ? true : false);
               wntPermissions.setMonitoring(partnerPermission.getWtMonitoring() == CCIConstants.ACTIVE ? true : false);
               wntPermissions.setPlacementInfo(partnerPermission.getWtPlacementInfo() == CCIConstants.ACTIVE ? true : false);
               wntPermissions.setStudentsPreProgram(partnerPermission.getWtStudentsPreProgram() == CCIConstants.ACTIVE ? true : false);
               wntProgram.setPermissions(wntPermissions);
               userProgramsAndPermissions.add(wntProgram);

               Programs capProgram = new Programs();
               capProgram.setProgramName(CCIConstants.WP_WT_CAP);
               Permissions capPermissions = new Permissions();
               capPermissions.setAccounting(partnerPermission.getCapAccountingInsurance() == CCIConstants.ACTIVE ? true : false);
               capPermissions.setAdmin(partnerPermission.getCapAdmin() == CCIConstants.ACTIVE ? true : false);
               capPermissions.setApplications(partnerPermission.getCapApplications() == CCIConstants.ACTIVE ? true : false);
               capPermissions.setContracting(partnerPermission.getCapContracting() == CCIConstants.ACTIVE ? true : false);
               capPermissions.setFlights(partnerPermission.getCapFlights() == CCIConstants.ACTIVE ? true : false);
               capPermissions.setInsurance(partnerPermission.getCapInsurance() == CCIConstants.ACTIVE ? true : false);
               capPermissions.setMonitoring(partnerPermission.getCapMonitoring() == CCIConstants.ACTIVE ? true : false);
               capPermissions.setPlacementInfo(partnerPermission.getCapPlacementInfo() == CCIConstants.ACTIVE ? true : false);
               capPermissions.setStudentsPreProgram(partnerPermission.getCapStudentsPreProgram() == CCIConstants.ACTIVE ? true : false);
               capProgram.setPermissions(capPermissions);
               userProgramsAndPermissions.add(capProgram);
            }
            partnerUserDetails.getUserProgramsAndPermissions().addAll(userProgramsAndPermissions);
            partnerUserDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } catch (CcighgoException e) {
            partnerUserDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS)));
            LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS));
         }
      }
      return partnerUserDetails;
   }

   @Override
   @Transactional(readOnly = true)
   public PartnerUserOffices getPartnerUserOffices(String partnerGoId) {
      PartnerUserOffices partnerUserOffices = new PartnerUserOffices();
      if (partnerGoId == null || Integer.valueOf(partnerGoId) < 0 || Integer.valueOf(partnerGoId) == 0) {
         partnerUserOffices.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
      } else {
         try {
            List<PartnerUser> partnerUsersDBList = partnerUserRepository.findByPartnerGoId(Integer.valueOf(partnerGoId));
            PartnerUser partnerUser = null;
            if (partnerUsersDBList != null) {
               for (PartnerUser pUser : partnerUsersDBList) {
                  if (Integer.valueOf(partnerGoId) == pUser.getPartner().getPartnerGoId() && pUser.getIsPrimary() == CCIConstants.ACTIVE) {
                     partnerUser = pUser;
                     break;
                  }
               }
            }
            List<com.ccighgo.service.transport.partner.beans.partner.user.office.UserOffice> userOfficesList = null;
            if (partnerUser.getPartner().getPartnerOffices() != null) {
               partnerUserOffices.setPartnerGoId(partnerUser.getPartner().getPartnerGoId());
               partnerUserOffices.setCount(partnerUser.getPartner().getPartnerOffices().size());
               userOfficesList = new ArrayList<com.ccighgo.service.transport.partner.beans.partner.user.office.UserOffice>();
               for (PartnerOffice pOffice : partnerUser.getPartner().getPartnerOffices()) {
                  com.ccighgo.service.transport.partner.beans.partner.user.office.UserOffice usrOffice = new com.ccighgo.service.transport.partner.beans.partner.user.office.UserOffice();
                  usrOffice.setUserOfficeId(pOffice.getPartnerOfficeId());
                  usrOffice.setOfficeAddressLineOne(pOffice.getAdressOne());
                  usrOffice.setOfficeAddressLineTwo(pOffice.getAdressTwo());
                  usrOffice.setCity(pOffice.getCity());
                  usrOffice.setZipCode(pOffice.getPostalCode());

                  com.ccighgo.service.transport.partner.beans.partner.user.office.UserAddressCountry userAddressCountry = new com.ccighgo.service.transport.partner.beans.partner.user.office.UserAddressCountry();
                  userAddressCountry.setOfficeAddressCountryId(pOffice.getLookupCountry().getCountryId());
                  userAddressCountry.setOfficeAddressCountryISOCode(pOffice.getLookupCountry().getCountryCode());
                  userAddressCountry.setOfficeAddressCountryName(pOffice.getLookupCountry().getCountryName());
                  usrOffice.setOfficeAddressCountry(userAddressCountry);

                  usrOffice.setOfficePhone(pOffice.getPhoneNumber());
                  usrOffice.setOfficeFax(pOffice.getFaxNumber());
                  usrOffice.setOfficeEmail(pOffice.getPartner().getEmail());
                  usrOffice.setOfficeWebsite(pOffice.getWebsite());
                  if (pOffice.getPartnerOfficeType().equals(CCIConstants.PRIMARY_OFFICE)) {
                     usrOffice.setIsPrimary(true);
                  } else {
                     usrOffice.setIsPrimary(false);
                  }
                  userOfficesList.add(usrOffice);
               }
               partnerUserOffices.getUserOffices().addAll(userOfficesList);
               partnerUserOffices.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            }
         } catch (CcighgoException e) {
            partnerUserOffices.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS)));
            LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS));
         }
      }
      return partnerUserOffices;
   }

   @Override
   @Transactional
   public PartnerUserDetails addPartnerUser(PartnerUserDetails partnerUserDetails, HttpServletRequest request) {
      PartnerUserDetails newUser = new PartnerUserDetails();
      if (partnerUserDetails == null) {
      } else {
         try {
            String access = PasscodeGenerator.generateRandomPasscode(8, 8, 1, 1, 1).toString();
            Login checkLoginNameExists = loginRepository.findByLoginName(partnerUserDetails.getUserLoginName());
            Login checkEmailExists = loginRepository.findByEmail(partnerUserDetails.getUserEmail());
            // proceed only if no login found by email or selected login name
            if (checkLoginNameExists == null && checkEmailExists == null) {
               // find partner login who is creating the user basically.
               Partner partner = partnerRepository.findOne(partnerUserDetails.getPartnerGoId());
               Login partnerLogin = null;
               List<PartnerUser> partnerUserList = partner.getPartnerUsers();
               for (PartnerUser pu : partnerUserList) {
                  if (partner.getPartnerGoId() == pu.getPartner().getPartnerGoId() && pu.getIsPrimary() == CCIConstants.ACTIVE) {
                     partnerLogin = pu.getLogin();
                     break;
                  }
               }
               // save login
               Login newUserLogin = new Login();
               newUserLogin.setActive(CCIConstants.ACTIVE);
               GoIdSequence goId = new GoIdSequence();
               goId.setGoId(partnerUserDetails.getPartnerGoId());
               newUserLogin.setGoIdSequence(goId);
               newUserLogin.setLoginName(partnerUserDetails.getUserLoginName());
               newUserLogin.setKeyValue(UuidUtils.nextHexUUID());
               newUserLogin.setEmail(partnerUserDetails.getUserEmail());
               newUserLogin.setPassword(PasswordUtil.hashKey(access));
               newUserLogin.setCreatedBy(partnerLogin.getLoginId());
               newUserLogin.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               newUserLogin.setModifiedBy(partnerLogin.getLoginId());
               newUserLogin.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               Login partnerUserLogin = loginRepository.saveAndFlush(newUserLogin);
               // save partner user details
               PartnerUser pUser = new PartnerUser();
               pUser.setPartner(partner);
               pUser.setLogin(partnerUserLogin);
               pUser.setLookupGender(genderRepository.findOne(partnerUserDetails.getUserGender().getGenderId()));
               pUser.setSalutation(salutationRepositotry.findOne(partnerUserDetails.getUserSalutation().getSalutationId()));
               pUser.setTitle(partnerUserDetails.getUserDesignation());
               pUser.setFirstName(partnerUserDetails.getUserFirstName());
               pUser.setLastName(partnerUserDetails.getUserLastName());
               pUser.setEmail(partnerUserDetails.getUserEmail());
               pUser.setPhoto(partnerUserDetails.getUserPictureUrl());
               pUser.setPhone(partnerUserDetails.getUserPhone());
               pUser.setEmergencyPhone(partnerUserDetails.getUserEmergencyPhone());
               pUser.setFax(partnerUserDetails.getUserFax());
               pUser.setIsPrimary(CCIConstants.INACTIVE);
               pUser.setActive(CCIConstants.ACTIVE);
               PartnerUser patUser = partnerUserRepository.saveAndFlush(pUser);
               // save permissions
               PartnerPermission partnerUserPermission = new PartnerPermission();
               partnerUserPermission.setPartnerUser(patUser);
               if (partnerUserDetails.getUserProgramsAndPermissions() != null) {
                  for (Programs p : partnerUserDetails.getUserProgramsAndPermissions()) {
                     if (p.getProgramName().equals("J1HS")) {
                        partnerUserPermission.setJ1AccountingInsurance(p.getPermissions().isAccounting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setJ1Admin(p.getPermissions().isAdmin() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setJ1Applications(p.getPermissions().isApplications() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setJ1Contracting(p.getPermissions().isContracting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setJ1Flights(p.getPermissions().isFlights() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setJ1Insurance(p.getPermissions().isInsurance() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setJ1Monitoring(p.getPermissions().isMonitoring() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setJ1PlacementInfo(p.getPermissions().isPlacementInfo() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setJ1StudentsPreProgram(p.getPermissions().isStudentsPreProgram() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     }
                     if (p.getProgramName().equals(CCIConstants.HSP_F1)) {
                        partnerUserPermission.setF1AccountingInsurance(p.getPermissions().isAccounting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setF1Admin(p.getPermissions().isAdmin() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setF1Applications(p.getPermissions().isApplications() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setF1Contracting(p.getPermissions().isContracting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setF1Flights(p.getPermissions().isFlights() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setF1Insurance(p.getPermissions().isInsurance() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setF1Monitoring(p.getPermissions().isMonitoring() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setF1PlacementInfo(p.getPermissions().isPlacementInfo() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setF1StudentsPreProgram(p.getPermissions().isStudentsPreProgram() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     }
                     if (p.getProgramName().equals("IHP")) {
                        partnerUserPermission.setIhpAccountingInsurance(p.getPermissions().isAccounting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setIhpAdmin(p.getPermissions().isAdmin() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setIhpApplications(p.getPermissions().isApplications() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setIhpContracting(p.getPermissions().isContracting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setIhpFlights(p.getPermissions().isFlights() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setIhpInsurance(p.getPermissions().isInsurance() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setIhpMonitoring(p.getPermissions().isMonitoring() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setIhpPlacementInfo(p.getPermissions().isPlacementInfo() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setIhpStudentsPreProgram(p.getPermissions().isStudentsPreProgram() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     }
                     if (p.getProgramName().equals("W&T")) {
                        partnerUserPermission.setWtAccountingInsurance(p.getPermissions().isAccounting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setWtAdmin(p.getPermissions().isAdmin() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setWtApplications(p.getPermissions().isApplications() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setWtContracting(p.getPermissions().isContracting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setWtFlights(p.getPermissions().isFlights() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setWtInsurance(p.getPermissions().isInsurance() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setWtMonitoring(p.getPermissions().isMonitoring() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setWtPlacementInfo(p.getPermissions().isPlacementInfo() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setWtStudentsPreProgram(p.getPermissions().isStudentsPreProgram() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     }
                     if (p.getProgramName().equals(CCIConstants.WP_WT_CAP)) {
                        partnerUserPermission.setCapAccountingInsurance(p.getPermissions().isAccounting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setCapAdmin(p.getPermissions().isAdmin() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setCapApplications(p.getPermissions().isApplications() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setCapContracting(p.getPermissions().isContracting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setCapFlights(p.getPermissions().isFlights() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setCapInsurance(p.getPermissions().isInsurance() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setCapMonitoring(p.getPermissions().isMonitoring() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setCapPlacementInfo(p.getPermissions().isPlacementInfo() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        partnerUserPermission.setCapStudentsPreProgram(p.getPermissions().isStudentsPreProgram() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     }
                  }
               }
               partnerPermissionRepository.saveAndFlush(partnerUserPermission);
               // send email to user
               Login loginEmail = loginRepository.findByEmail(partnerUserLogin.getEmail());
               String body = "<p>This email was sent automatically by CCI Greenheart Online system to inform you that you an online account has been created for you.  </p></br>"
                     + "<p>Please go to the following page and follow the instructions to login to the system. </p> " + "<p>"
                     + formResetURL(request).concat(loginEmail.getKeyValue()) + "</p></br>" + "<p>Thank you,</p><p>GO System Support.</p>";
               email.send(loginEmail.getEmail(), CCIConstants.CREATE_CCI_USER_SUBJECT, body, true);
               newUser = getPartnerUserDetails(String.valueOf(patUser.getPartnerUserId()));
               newUser.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               newUser.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                     messageUtil.getMessage("User with same login name or email already exists")));
               LOGGER.error(messageUtil.getMessage("User with same login name or email already exists"));
            }
         } catch (CcighgoException e) {
            newUser.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS)));
            LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS));
         }
      }
      return newUser;
   }

   private String formResetURL(HttpServletRequest request) {
      String url = "";
      try {
         url = request.getHeader("Origin") + CCIConstants.RESET_PASSWORD_LINK;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return url;
   }

   @Override
   public PartnerUserDetails updatePartnerUser(PartnerUserDetails partnerUserDetails, HttpServletRequest request) {
      PartnerUserDetails updatedUser = new PartnerUserDetails();
      if (partnerUserDetails == null) {
         updatedUser.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_REQUEST.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS));
         return updatedUser;
      } else {
         try {
            PartnerUser partnerUser = partnerUserRepository.findOne(partnerUserDetails.getPartnerUserId());
            if (partnerUserDetails.getUserSalutation() != null) {
               partnerUser.setSalutation(salutationRepositotry.findOne(partnerUserDetails.getUserSalutation().getSalutationId()));
            }
            partnerUser.setActive(partnerUserDetails.getUserActiveStatus() == 1 ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            partnerUser.setTitle(partnerUserDetails.getUserDesignation());
            partnerUser.setFirstName(partnerUserDetails.getUserFirstName());
            partnerUser.setLastName(partnerUserDetails.getUserLastName());
            partnerUser.setPhone(partnerUserDetails.getUserPhone());
            partnerUser.setEmergencyPhone(partnerUserDetails.getUserEmergencyPhone());
            partnerUser.setFax(partnerUserDetails.getUserFax());
            partnerUser.setSkypeId(partnerUserDetails.getUserSkypeId());
            if (partnerUserDetails.getUserOffices() != null) {
               for (UserOffice uo : partnerUserDetails.getUserOffices()) {
                  if (uo.isIsPrimary()) {
                     partnerUser.setPartnerOffice(partnerOfficeRepository.findOne(uo.getUserOfficeId()));
                  }
               }
            }
            partnerUserRepository.saveAndFlush(partnerUser);

            PartnerPermission partnerUserPermission = partnerPermissionRepository.findByPartnerUserId(partnerUser.getPartnerUserId());
            if (partnerUserDetails.getUserProgramsAndPermissions() != null) {
               for (Programs p : partnerUserDetails.getUserProgramsAndPermissions()) {
                  if (p.getProgramName().equals("J1HS")) {
                     partnerUserPermission.setJ1AccountingInsurance(p.getPermissions().isAccounting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setJ1Admin(p.getPermissions().isAdmin() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setJ1Applications(p.getPermissions().isApplications() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setJ1Contracting(p.getPermissions().isContracting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setJ1Flights(p.getPermissions().isFlights() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setJ1Insurance(p.getPermissions().isInsurance() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setJ1Monitoring(p.getPermissions().isMonitoring() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setJ1PlacementInfo(p.getPermissions().isPlacementInfo() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setJ1StudentsPreProgram(p.getPermissions().isStudentsPreProgram() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                  }
                  if (p.getProgramName().equals(CCIConstants.HSP_F1)) {
                     partnerUserPermission.setF1AccountingInsurance(p.getPermissions().isAccounting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setF1Admin(p.getPermissions().isAdmin() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setF1Applications(p.getPermissions().isApplications() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setF1Contracting(p.getPermissions().isContracting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setF1Flights(p.getPermissions().isFlights() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setF1Insurance(p.getPermissions().isInsurance() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setF1Monitoring(p.getPermissions().isMonitoring() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setF1PlacementInfo(p.getPermissions().isPlacementInfo() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setF1StudentsPreProgram(p.getPermissions().isStudentsPreProgram() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                  }
                  if (p.getProgramName().equals("IHP")) {
                     partnerUserPermission.setIhpAccountingInsurance(p.getPermissions().isAccounting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setIhpAdmin(p.getPermissions().isAdmin() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setIhpApplications(p.getPermissions().isApplications() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setIhpContracting(p.getPermissions().isContracting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setIhpFlights(p.getPermissions().isFlights() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setIhpInsurance(p.getPermissions().isInsurance() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setIhpMonitoring(p.getPermissions().isMonitoring() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setIhpPlacementInfo(p.getPermissions().isPlacementInfo() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setIhpStudentsPreProgram(p.getPermissions().isStudentsPreProgram() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                  }
                  if (p.getProgramName().equals("W&T")) {
                     partnerUserPermission.setWtAccountingInsurance(p.getPermissions().isAccounting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setWtAdmin(p.getPermissions().isAdmin() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setWtApplications(p.getPermissions().isApplications() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setWtContracting(p.getPermissions().isContracting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setWtFlights(p.getPermissions().isFlights() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setWtInsurance(p.getPermissions().isInsurance() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setWtMonitoring(p.getPermissions().isMonitoring() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setWtPlacementInfo(p.getPermissions().isPlacementInfo() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setWtStudentsPreProgram(p.getPermissions().isStudentsPreProgram() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                  }
                  if (p.getProgramName().equals(CCIConstants.WP_WT_CAP)) {
                     partnerUserPermission.setCapAccountingInsurance(p.getPermissions().isAccounting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setCapAdmin(p.getPermissions().isAdmin() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setCapApplications(p.getPermissions().isApplications() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setCapContracting(p.getPermissions().isContracting() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setCapFlights(p.getPermissions().isFlights() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setCapInsurance(p.getPermissions().isInsurance() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setCapMonitoring(p.getPermissions().isMonitoring() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setCapPlacementInfo(p.getPermissions().isPlacementInfo() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                     partnerUserPermission.setCapStudentsPreProgram(p.getPermissions().isStudentsPreProgram() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                  }
               }
            }
            partnerPermissionRepository.saveAndFlush(partnerUserPermission);
            updatedUser = getPartnerUserDetails(String.valueOf(partnerUser.getPartnerUserId()));
            updatedUser.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } catch (CcighgoException e) {
            updatedUser.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS)));
            LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS));
         }
      }
      return updatedUser;
   }
}
