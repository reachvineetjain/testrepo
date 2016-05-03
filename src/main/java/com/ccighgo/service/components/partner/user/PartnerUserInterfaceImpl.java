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
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.db.entities.LookupDepartmentProgram;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerOffice;
import com.ccighgo.db.entities.PartnerPermission;
import com.ccighgo.db.entities.PartnerPermissionsCategory;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.exception.PartnerCodes;
import com.ccighgo.jpa.repositories.GenderRepository;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LoginUserTypeRepository;
import com.ccighgo.jpa.repositories.LookupDepartmentProgramRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeRepository;
import com.ccighgo.jpa.repositories.PartnerPermissionCategoryRepository;
import com.ccighgo.jpa.repositories.PartnerPermissionRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerUserRepository;
import com.ccighgo.jpa.repositories.SalutationRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.component.emailing.EmailServiceImpl;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminSeasonConstants;
import com.ccighgo.service.components.errormessages.constants.PartnerUserMessageConstants;
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
import com.ccighgo.utils.PasscodeGenerator;
import com.ccighgo.utils.PasswordUtil;
import com.ccighgo.utils.UuidUtils;
import org.springframework.data.jpa.repository.Modifying;

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
   @Autowired LookupDepartmentProgramRepository lookupDepartmentProgramRepository;
   @Autowired PartnerPermissionCategoryRepository partnerPermissionCategoryRepository;

   @Override
   @Transactional(readOnly = true)
   public PartnerUsers getAllPartnerUsers(String partnerId) {
      PartnerUsers partnerUsers = new PartnerUsers();
      if (partnerId == null || Integer.valueOf(partnerId) < 0 || Integer.valueOf(partnerId) == 0) {
         partnerUsers.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.NULL_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID)));
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
                  puser.setPartnerUserPhotoUrl(user.getPhoto());
                  puser.setPartnerUserFirstName(user.getFirstName());
                  puser.setPartnerUserLastName(user.getLastName());
                  puser.setIsPrimary(user.getIsPrimary() == CCIConstants.ACTIVE);
                  puser.setPartnerUserLoginName(user.getLogin().getLoginName());
                  if (user.getLogin() != null)
                     puser.setPartnerUserEmail(user.getLogin().getEmail());
                  puser.setPartnerUserStatus(user.getLogin().getActive() == CCIConstants.ACTIVE ? 1 : 0);
                  partnerUsersUIList.add(puser);
               }
               partnerUsers.getPartnerUsers().addAll(partnerUsersUIList);
               partnerUsers.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               partnerUsers.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD_IN_DB, CCIConstants.TYPE_INFO, CCIConstants.NO_DATA_CODE,
                     messageUtil.getMessage(CCIConstants.NO_RECORD)));
               LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
            }
         } catch (CcighgoException e) {
            partnerUsers.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.EXCEPTION_IN_FETCHING_PARTNER_USER_LIST.getValue(),
                  messageUtil.getMessage(PartnerUserMessageConstants.FAILED_GET_PARTNER_USER_LIST)));
            LOGGER.error(e);
         }
      }
      return partnerUsers;
   }

   @Override
   @Transactional
   public Response updatePartnerUserStatus(String statusVal, String partnerUserId) {
      Response response = new Response();
      if (partnerUserId == null) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, CCIConstants.INVALID_PARTNER_ID,
               messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID)));
         return response;
      }
      if (statusVal == null || !(Integer.valueOf(statusVal) == 0 || Integer.valueOf(statusVal) == 1)) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.PARTNER_USER_STATUS_NULL.getValue(),
               messageUtil.getMessage(PartnerUserMessageConstants.FAILED_UPDATE_PARTNER_USER_STATUS)));
         return response;
      } else {
         try {
            PartnerUser partnerUser = partnerUserRepository.findOne(Integer.valueOf(partnerUserId));
            Login login = partnerUser.getLogin();
            if (Integer.valueOf(statusVal) == 1) {
               partnerUser.setActive(CCIConstants.ACTIVE);
               login.setActive(CCIConstants.ACTIVE);
            }
            if (Integer.valueOf(statusVal) == 0) {
               partnerUser.setActive(CCIConstants.INACTIVE);
               login.setActive(CCIConstants.INACTIVE);
            }
            partnerUserRepository.saveAndFlush(partnerUser);
            loginRepository.saveAndFlush(login);
            response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } catch (CcighgoException e) {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_UPDATE_PARTNER_USER_STATUS.getValue(),
                  messageUtil.getMessage(PartnerUserMessageConstants.EXCEPTION_UPDATING_PARTNER_USER_STATUS)));
            LOGGER.error(e);
         }
      }
      return response;
   }

   @Override
   public Response resetPartnerUserPassword(String partnerUserId, HttpServletRequest request) {
      Response response = new Response();
      if (partnerUserId == null) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, CCIConstants.INVALID_PARTNER_ID,
               messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID));
         return response;
      } else {
         try {
            PartnerUser partnerUser = partnerUserRepository.findOne(Integer.valueOf(partnerUserId));
            if (partnerUser != null && partnerUser.getLogin() != null) {
               String body = "<p>Ciao! </p>" + "<p>This email was sent automatically by Greenheart Online (GO) in response to your request for a new password. </p>" + "<p>"
                     + "Your username is : " + partnerUser.getLogin().getLoginName() + "</p>" + "<p>Please click on the link below to create a new password:</p> " + "<p>"
                     + formResetURL(request).concat(partnerUser.getLogin().getKeyValue()) + "</p>" + "<p>If you didn't request a new password, please let us know.</p>"
                     + "<p>Thank you,</p>" + "<p>CCI Greenheart.</p>";
               email.send(partnerUser.getLogin().getEmail(), CCIConstants.RESET_PASSWORD_SUBJECT, body, true);
               response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
                     "An email has been sent to address " + "\'" + partnerUser.getLogin().getEmail() + "\'" + " for login name " + "\'" + partnerUser.getLogin().getLoginName()
                           + "\'" + " with instructions to reset password"));
            } else {
               response.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD_IN_DB, CCIConstants.TYPE_INFO, CCIConstants.NO_DATA_CODE,
                     messageUtil.getMessage(CCIConstants.NO_RECORD)));
               LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
            }
         } catch (CcighgoException e) {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_RESETING_PARTNER_USER_PASSWORD.getValue(),
                  messageUtil.getMessage(PartnerUserMessageConstants.ERROR_RESETTING_PARTNER_USER_PASSWORD)));
            LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.ERROR_RESETTING_PARTNER_USER_PASSWORD));
         }
      }
      return response;
   }

   @Override
   @Transactional(readOnly = true)
   public PartnerUserDetails getPartnerUserDetails(String partnerUserId) {
      PartnerUserDetails partnerUserDetails = new PartnerUserDetails();
      if (partnerUserId == null) {
         partnerUserDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, CCIConstants.INVALID_PARTNER_ID,
               messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID));
         return partnerUserDetails;
      } else {
         try {
            PartnerUser partnerUser = partnerUserRepository.findOne(Integer.valueOf(partnerUserId));
            partnerUserDetails.setPartnerGoId(partnerUser.getPartner().getPartnerGoId());
            partnerUserDetails.setPartnerUserId(partnerUser.getPartnerUserId());
            partnerUserDetails.setUserActiveStatus(partnerUser.getActive() == CCIConstants.ACTIVE ? 1 : 0);
            partnerUserDetails.setUserFirstName(partnerUser.getFirstName());
            partnerUserDetails.setUserLastName(partnerUser.getLastName());
            partnerUserDetails.setIsPrimary(partnerUser.getIsPrimary() == CCIConstants.ACTIVE);
            // salutation
            UserSalutation salutation = new UserSalutation();
            salutation.setSalutationId(partnerUser.getSalutation().getSalutationId());
            salutation.setSalutation(partnerUser.getSalutation().getSalutationName());
            partnerUserDetails.setUserSalutation(salutation);

            partnerUserDetails.setUserDesignation(partnerUser.getTitle());
            if (partnerUser.getLogin() != null)
               partnerUserDetails.setUserEmail(partnerUser.getLogin().getEmail());
            partnerUserDetails.setUserPhone(partnerUser.getPhone());
            partnerUserDetails.setUserEmergencyPhone(partnerUser.getEmergencyPhone());
            partnerUserDetails.setUserFax(partnerUser.getFax());
            partnerUserDetails.setUserLoginName(partnerUser.getLogin().getLoginName());
            partnerUserDetails.setUserSkypeId(partnerUser.getSkypeId());
            partnerUserDetails.setUserPictureUrl(partnerUser.getPhoto());
            // gender
            if (partnerUser.getLookupGender() != null) {
               UserGender gender = new UserGender();
               gender.setGenderId(partnerUser.getLookupGender().getGenderId());
               gender.setGender(partnerUser.getLookupGender().getGenderName());
               partnerUserDetails.setUserGender(gender);
            }
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
            Programs j1Program = new Programs();
            j1Program.setProgramName("J1HS");
            Permissions j1Permissions = new Permissions();
            Programs f1Program = new Programs();
            f1Program.setProgramName("F1");
            Permissions f1Permissions = new Permissions();
            Programs ihpProgram = new Programs();
            ihpProgram.setProgramName("IHP");
            Permissions ihpPermissions = new Permissions();
            Programs wtProgram = new Programs();
            wtProgram.setProgramName("W&T");
            Permissions wtPermissions = new Permissions();
            Programs capProgram = new Programs();
            capProgram.setProgramName("CAP");
            Permissions capPermissions = new Permissions();
            List<PartnerPermission> j1ProgramPermissions = partnerPermissionRepository.findByPartnerUserIdAndProgramId(partnerUser.getPartnerUserId(),
                  CCIConstants.HSP_J1_HS_ID);
            if (j1ProgramPermissions != null && !(j1ProgramPermissions.isEmpty())) {
               for (PartnerPermission pp : j1ProgramPermissions) {
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Admin) {
                     j1Permissions.setAdmin(true);
                  } else {
                     j1Permissions.setAdmin(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Applications) {
                     j1Permissions.setApplications(true);
                  } else {
                     j1Permissions.setApplications(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Flights) {
                     j1Permissions.setFlights(true);
                  } else {
                     j1Permissions.setFlights(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.PlacementInfo) {
                     j1Permissions.setPlacementInfo(true);
                  } else {
                     j1Permissions.setPlacementInfo(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Monitoring) {
                     j1Permissions.setMonitoring(true);
                  } else {
                     j1Permissions.setMonitoring(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.AccountingInsurance) {
                     j1Permissions.setAccounting(true);
                  } else {
                     j1Permissions.setAccounting(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.StudentPreProgram) {
                     j1Permissions.setStudentsPreProgram(true);
                  } else {
                     j1Permissions.setStudentsPreProgram(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Contracting) {
                     j1Permissions.setContracting(true);
                  } else {
                     j1Permissions.setContracting(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Insurance) {
                     j1Permissions.setInsurance(true);
                  } else {
                     j1Permissions.setInsurance(false);
                  }
               }
            } else {
               j1Permissions.setAdmin(false);
               j1Permissions.setApplications(false);
               j1Permissions.setFlights(false);
               j1Permissions.setPlacementInfo(false);
               j1Permissions.setMonitoring(false);
               j1Permissions.setAccounting(false);
               j1Permissions.setStudentsPreProgram(false);
               j1Permissions.setContracting(false);
               j1Permissions.setInsurance(false);
            }

            List<PartnerPermission> f1ProgramPermissions = partnerPermissionRepository.findByPartnerUserIdAndProgramId(partnerUser.getPartnerUserId(),
                  CCIConstants.HSP_F1_ID);
            if (f1ProgramPermissions != null && !(f1ProgramPermissions.isEmpty())) {
               for (PartnerPermission pp : f1ProgramPermissions) {
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Admin) {
                     f1Permissions.setAdmin(true);
                  } else {
                     f1Permissions.setAdmin(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Applications) {
                     f1Permissions.setApplications(true);
                  } else {
                     f1Permissions.setApplications(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Flights) {
                     f1Permissions.setFlights(true);
                  } else {
                     f1Permissions.setFlights(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.PlacementInfo) {
                     f1Permissions.setPlacementInfo(true);
                  } else {
                     f1Permissions.setPlacementInfo(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Monitoring) {
                     f1Permissions.setMonitoring(true);
                  } else {
                     f1Permissions.setMonitoring(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.AccountingInsurance) {
                     f1Permissions.setAccounting(true);
                  } else {
                     f1Permissions.setAccounting(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.StudentPreProgram) {
                     f1Permissions.setStudentsPreProgram(true);
                  } else {
                     f1Permissions.setStudentsPreProgram(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Contracting) {
                     f1Permissions.setContracting(true);
                  } else {
                     f1Permissions.setContracting(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Insurance) {
                     f1Permissions.setInsurance(true);
                  } else {
                     f1Permissions.setInsurance(false);
                  }
               }
            } else {
               f1Permissions.setAdmin(false);
               f1Permissions.setApplications(false);
               f1Permissions.setFlights(false);
               f1Permissions.setPlacementInfo(false);
               f1Permissions.setMonitoring(false);
               f1Permissions.setAccounting(false);
               f1Permissions.setStudentsPreProgram(false);
               f1Permissions.setContracting(false);
               f1Permissions.setInsurance(false);
            }

            List<PartnerPermission> ihpProgramPermissions = partnerPermissionRepository.findByPartnerUserIdAndProgramId(partnerUser.getPartnerUserId(),
                  CCIConstants.HSP_STP_IHP_ID);
            if (ihpProgramPermissions != null && !(ihpProgramPermissions.isEmpty())) {
               for (PartnerPermission pp : ihpProgramPermissions) {
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Admin) {
                     ihpPermissions.setAdmin(true);
                  } else {
                     ihpPermissions.setAdmin(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Applications) {
                     ihpPermissions.setApplications(true);
                  } else {
                     ihpPermissions.setApplications(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Flights) {
                     ihpPermissions.setFlights(true);
                  } else {
                     ihpPermissions.setFlights(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.PlacementInfo) {
                     ihpPermissions.setPlacementInfo(true);
                  } else {
                     ihpPermissions.setPlacementInfo(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Monitoring) {
                     ihpPermissions.setMonitoring(true);
                  } else {
                     ihpPermissions.setMonitoring(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.AccountingInsurance) {
                     ihpPermissions.setAccounting(true);
                  } else {
                     ihpPermissions.setAccounting(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.StudentPreProgram) {
                     ihpPermissions.setStudentsPreProgram(true);
                  } else {
                     ihpPermissions.setStudentsPreProgram(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Contracting) {
                     ihpPermissions.setContracting(true);
                  } else {
                     ihpPermissions.setContracting(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Insurance) {
                     ihpPermissions.setInsurance(true);
                  } else {
                     ihpPermissions.setInsurance(false);
                  }
               }
            } else {
               ihpPermissions.setAdmin(false);
               ihpPermissions.setApplications(false);
               ihpPermissions.setFlights(false);
               ihpPermissions.setPlacementInfo(false);
               ihpPermissions.setMonitoring(false);
               ihpPermissions.setAccounting(false);
               ihpPermissions.setStudentsPreProgram(false);
               ihpPermissions.setContracting(false);
               ihpPermissions.setInsurance(false);
            }

            List<PartnerPermission> wtProgramPermissions = partnerPermissionRepository.findByPartnerUserIdAndProgramId(partnerUser.getPartnerUserId(), 6);
            if (wtProgramPermissions != null && !(wtProgramPermissions.isEmpty())) {
               for (PartnerPermission pp : wtProgramPermissions) {
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Admin) {
                     wtPermissions.setAdmin(true);
                  } else {
                     wtPermissions.setAdmin(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Applications) {
                     wtPermissions.setApplications(true);
                  } else {
                     wtPermissions.setApplications(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Flights) {
                     wtPermissions.setFlights(true);
                  } else {
                     wtPermissions.setFlights(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.PlacementInfo) {
                     wtPermissions.setPlacementInfo(true);
                  } else {
                     wtPermissions.setPlacementInfo(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Monitoring) {
                     wtPermissions.setMonitoring(true);
                  } else {
                     wtPermissions.setMonitoring(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.AccountingInsurance) {
                     wtPermissions.setAccounting(true);
                  } else {
                     wtPermissions.setAccounting(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.StudentPreProgram) {
                     wtPermissions.setStudentsPreProgram(true);
                  } else {
                     wtPermissions.setStudentsPreProgram(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Contracting) {
                     wtPermissions.setContracting(true);
                  } else {
                     wtPermissions.setContracting(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Insurance) {
                     wtPermissions.setInsurance(true);
                  } else {
                     wtPermissions.setInsurance(false);
                  }
               }
            } else {
               wtPermissions.setAdmin(false);
               wtPermissions.setApplications(false);
               wtPermissions.setFlights(false);
               wtPermissions.setPlacementInfo(false);
               wtPermissions.setMonitoring(false);
               wtPermissions.setAccounting(false);
               wtPermissions.setStudentsPreProgram(false);
               wtPermissions.setContracting(false);
               wtPermissions.setInsurance(false);
            }

            List<PartnerPermission> capProgramPermissions = partnerPermissionRepository.findByPartnerUserIdAndProgramId(partnerUser.getPartnerUserId(), 7);
            if (capProgramPermissions != null && !(capProgramPermissions.isEmpty())) {
               for (PartnerPermission pp : capProgramPermissions) {
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Admin) {
                     capPermissions.setAdmin(true);
                  } else {
                     capPermissions.setAdmin(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Applications) {
                     capPermissions.setApplications(true);
                  } else {
                     capPermissions.setApplications(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Flights) {
                     capPermissions.setFlights(true);
                  } else {
                     capPermissions.setFlights(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.PlacementInfo) {
                     capPermissions.setPlacementInfo(true);
                  } else {
                     capPermissions.setPlacementInfo(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Monitoring) {
                     capPermissions.setMonitoring(true);
                  } else {
                     capPermissions.setMonitoring(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.AccountingInsurance) {
                     capPermissions.setAccounting(true);
                  } else {
                     capPermissions.setAccounting(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.StudentPreProgram) {
                     capPermissions.setStudentsPreProgram(true);
                  } else {
                     capPermissions.setStudentsPreProgram(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Contracting) {
                     capPermissions.setContracting(true);
                  } else {
                     capPermissions.setContracting(false);
                  }
                  if (pp.getPartnerPermissionsCategory().getPartnerPermissionsCategoryId() == CCIConstants.Insurance) {
                     capPermissions.setInsurance(true);
                  } else {
                     capPermissions.setInsurance(false);
                  }
               }
            } else {
               capPermissions.setAdmin(false);
               capPermissions.setApplications(false);
               capPermissions.setFlights(false);
               capPermissions.setPlacementInfo(false);
               capPermissions.setMonitoring(false);
               capPermissions.setAccounting(false);
               capPermissions.setStudentsPreProgram(false);
               capPermissions.setContracting(false);
               capPermissions.setInsurance(false);
            }
            j1Program.setPermissions(j1Permissions);
            userProgramsAndPermissions.add(j1Program);
            f1Program.setPermissions(f1Permissions);
            userProgramsAndPermissions.add(f1Program);
            ihpProgram.setPermissions(ihpPermissions);
            userProgramsAndPermissions.add(ihpProgram);
            wtProgram.setPermissions(wtPermissions);
            userProgramsAndPermissions.add(wtProgram);
            capProgram.setPermissions(capPermissions);
            userProgramsAndPermissions.add(capProgram);
            
            partnerUserDetails.getUserProgramsAndPermissions().addAll(userProgramsAndPermissions);
            partnerUserDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } catch (CcighgoException e) {
            partnerUserDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_VIEWING_PARTNER_USER_DATA.getValue(),
                  messageUtil.getMessage(PartnerUserMessageConstants.ERROR_VIEWING_PARTNER_USER_DATA)));
            LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.ERROR_VIEWING_PARTNER_USER_DATA));
         }
      }
      return partnerUserDetails;
   }

   @Override
   @Transactional(readOnly = true)
   public PartnerUserOffices getPartnerUserOffices(String partnerGoId) {
      PartnerUserOffices partnerUserOffices = new PartnerUserOffices();
      if (partnerGoId == null || Integer.valueOf(partnerGoId) < 0 || Integer.valueOf(partnerGoId) == 0) {
         partnerUserOffices.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, CCIConstants.INVALID_PARTNER_ID,
               messageUtil.getMessage(PartnerUserMessageConstants.INVALID_PARTNER_USER_ID)));
      } else {
         try {
            List<PartnerUser> partnerUsersDBList = partnerUserRepository.findByPartnerGoId(Integer.valueOf(partnerGoId));
            PartnerUser partnerUser = null;
            if (partnerUsersDBList != null) {
               for (PartnerUser pUser : partnerUsersDBList) {
                  if (Integer.valueOf(partnerGoId).equals(pUser.getPartner().getPartnerGoId()) && pUser.getIsPrimary().equals(CCIConstants.ACTIVE)) {
                     partnerUser = pUser;
                     break;
                  }
               }
            }
            List<com.ccighgo.service.transport.partner.beans.partner.user.office.UserOffice> userOfficesList = null;
            if (partnerUser != null) {
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
                  partnerUserOffices.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
                        messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
               }
            } else {
               throw new CcighgoException("no partner user found");
            }
         } catch (CcighgoException e) {
            partnerUserOffices.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_FETCHING_PARTNER_USER_OFFICE.getValue(),
                  e.getMessage()));
            LOGGER.error(e.getMessage());
         }
      }
      return partnerUserOffices;
   }

   @Override
   @Transactional
   public PartnerUserDetails addPartnerUser(PartnerUserDetails partnerUserDetails, HttpServletRequest request) {
      PartnerUserDetails newUser = new PartnerUserDetails();
      if (partnerUserDetails == null) {
         newUser.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, CCIConstants.NULL_PARAM,
               messageUtil.getMessage(PartnerUserMessageConstants.ERROR_ADDING_PARTNERUSER)));
      } else {
         try {
            String access = PasscodeGenerator.generateRandomPasscode(8, 8, 1, 1, 1).toString();
            Login checkLoginNameExists = loginRepository.findByLoginName(partnerUserDetails.getUserLoginName());
            Login checkEmailExists = loginRepository.findByEmail(partnerUserDetails.getUserEmail());
            // proceed only if no login found by email or selected login
            // name
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

               // set login user type
               LoginUserType loginUserType = new LoginUserType();
               loginUserType.setLogin(partnerUserLogin);
               loginUserType.setUserType(userTypeRepository.findOne(2));// UserType
               // 2
               // is
               // Partner
               // user
               loginUserType.setDefaultUserType(CCIConstants.ACTIVE);
               loginUserType.setActive(CCIConstants.ACTIVE);
               loginUserType.setCreatedBy(partnerLogin.getLoginId());
               loginUserType.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               loginUserType.setModifiedBy(partnerLogin.getLoginId());
               loginUserType.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               loginUserTypeRepository.saveAndFlush(loginUserType);

               // save partner user details
               PartnerUser pUser = new PartnerUser();
               pUser.setPartner(partner);
               pUser.setLogin(partnerUserLogin);
               pUser.setLookupGender(genderRepository.findOne(partnerUserDetails.getUserGender().getGenderId()));
               pUser.setSalutation(salutationRepositotry.findOne(partnerUserDetails.getUserSalutation().getSalutationId()));
               pUser.setTitle(partnerUserDetails.getUserDesignation());
               pUser.setFirstName(partnerUserDetails.getUserFirstName());
               pUser.setLastName(partnerUserDetails.getUserLastName());
               pUser.setPhoto(partnerUserDetails.getUserPictureUrl());
               pUser.setPhone(partnerUserDetails.getUserPhone());
               pUser.setEmergencyPhone(partnerUserDetails.getUserEmergencyPhone());
               pUser.setFax(partnerUserDetails.getUserFax());
               pUser.setIsPrimary(CCIConstants.INACTIVE);
               pUser.setActive(CCIConstants.ACTIVE);
               if (partnerUserDetails.getUserOffices() != null) {
                  for (UserOffice uo : partnerUserDetails.getUserOffices()) {
                     if (uo.isIsPrimary()) {
                        pUser.setPartnerOffice(partnerOfficeRepository.findOne(uo.getUserOfficeId()));
                     }
                  }
               }
               PartnerUser partnerUser = partnerUserRepository.saveAndFlush(pUser);
               // save permissions
               PartnerPermission partnerUserPermission = new PartnerPermission();
               partnerUserPermission.setPartnerUser(partnerUser);
               if (partnerUserDetails.getUserProgramsAndPermissions() != null) {
                  for (Programs p : partnerUserDetails.getUserProgramsAndPermissions()) {
                     LookupDepartmentProgram lookup = null;
                     if (p.getProgramName().equals("J1HS")) {
                        lookup = lookupDepartmentProgramRepository.findOne(CCIConstants.HSP_J1_HS_ID);
                     } else if (p.getProgramName().equals(CCIConstants.HSP_F1)) {
                        lookup = lookupDepartmentProgramRepository.findOne(CCIConstants.HSP_F1_ID);
                     } else if (p.getProgramName().equals("IHP")) {
                        lookup = lookupDepartmentProgramRepository.findOne(CCIConstants.HSP_STP_IHP_ID);
                     } else if (p.getProgramName().equals("W&T")) {
                        lookup = lookupDepartmentProgramRepository.findOne(CCIConstants.WP_WT_SPRING_ID);
                     } else if (p.getProgramName().equals(CCIConstants.WP_WT_CAP)) {
                        lookup = lookupDepartmentProgramRepository.findOne(CCIConstants.WP_WT_CAP_ID);
                     }

                     List<PartnerPermission> permissions = new ArrayList<PartnerPermission>();
                     if (lookup != null) {
                        if (p.getPermissions().isAdmin()) {
                           PartnerPermission pPermission = new PartnerPermission();
                           PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.Admin);
                           pPermission.setLookupDepartmentProgram(lookup);
                           pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                           pPermission.setPartnerUser(partnerUser);
                           permissions.add(pPermission);
                        }
                        if (p.getPermissions().isAccounting()) {
                           PartnerPermission pPermission = new PartnerPermission();
                           PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.AccountingInsurance);
                           pPermission.setLookupDepartmentProgram(lookup);
                           pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                           pPermission.setPartnerUser(partnerUser);
                           permissions.add(pPermission);
                        }
                        if (p.getPermissions().isApplications()) {
                           PartnerPermission pPermission = new PartnerPermission();
                           PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.Applications);
                           pPermission.setLookupDepartmentProgram(lookup);
                           pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                           pPermission.setPartnerUser(partnerUser);
                           permissions.add(pPermission);
                        }
                        if (p.getPermissions().isContracting()) {
                           PartnerPermission pPermission = new PartnerPermission();
                           PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.Contracting);
                           pPermission.setLookupDepartmentProgram(lookup);
                           pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                           pPermission.setPartnerUser(partnerUser);
                           permissions.add(pPermission);
                        }
                        if (p.getPermissions().isFlights()) {
                           PartnerPermission pPermission = new PartnerPermission();
                           PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.Flights);
                           pPermission.setLookupDepartmentProgram(lookup);
                           pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                           pPermission.setPartnerUser(partnerUser);
                           permissions.add(pPermission);
                        }
                        if (p.getPermissions().isInsurance()) {
                           PartnerPermission pPermission = new PartnerPermission();
                           PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.Insurance);
                           pPermission.setLookupDepartmentProgram(lookup);
                           pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                           pPermission.setPartnerUser(partnerUser);
                           permissions.add(pPermission);
                        }
                        if (p.getPermissions().isMonitoring()) {
                           PartnerPermission pPermission = new PartnerPermission();
                           PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.Monitoring);
                           pPermission.setLookupDepartmentProgram(lookup);
                           pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                           pPermission.setPartnerUser(partnerUser);
                           permissions.add(pPermission);
                        }
                        if (p.getPermissions().isPlacementInfo()) {
                           PartnerPermission pPermission = new PartnerPermission();
                           PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.PlacementInfo);
                           pPermission.setLookupDepartmentProgram(lookup);
                           pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                           pPermission.setPartnerUser(partnerUser);
                           permissions.add(pPermission);
                        }
                        if (p.getPermissions().isStudentsPreProgram()) {
                           PartnerPermission pPermission = new PartnerPermission();
                           PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.StudentPreProgram);
                           pPermission.setLookupDepartmentProgram(lookup);
                           pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                           pPermission.setPartnerUser(partnerUser);
                           permissions.add(pPermission);
                        }
                        partnerPermissionRepository.save(permissions);
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
               newUser = getPartnerUserDetails(String.valueOf(partnerUser.getPartnerUserId()));
               newUser.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               if (checkLoginNameExists != null) {
                  newUser.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_USER_WITH_THE_SAME_LOGINNAME_EXIST.getValue(),
                        messageUtil.getMessage(PartnerUserMessageConstants.ERROR_USERNAME_DUPLICATE)));
                  LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.ERROR_USERNAME_DUPLICATE));
               }
               if (checkEmailExists != null) {
                  newUser.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_USER_WITH_THE_SAME_EMAIL_EXIST.getValue(),
                        messageUtil.getMessage(PartnerUserMessageConstants.ERROR_PARTNER_USER_EMAIL_DUPLICATE)));
                  LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.ERROR_PARTNER_USER_EMAIL_DUPLICATE));
               }
            }
         } catch (CcighgoException e) {
            newUser.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_ADDING_PARTNERUSER.getValue(),
                  messageUtil.getMessage(PartnerUserMessageConstants.ERROR_ADDING_PARTNERUSER)));
            LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.ERROR_ADDING_PARTNERUSER));
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
   @Transactional
   @Modifying
   public PartnerUserDetails updatePartnerUser(PartnerUserDetails partnerUserDetails, HttpServletRequest request) {
      PartnerUserDetails updatedUser = new PartnerUserDetails();
      if (partnerUserDetails == null) {
         updatedUser.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, CCIConstants.NULL_PARAM,
               messageUtil.getMessage(PartnerUserMessageConstants.ERROR_UPDATED_PARTNER_USER_DATA_IS_NULL)));
         LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.ERROR_UPDATED_PARTNER_USER_DATA_IS_NULL));
         return updatedUser;
      } else {
         try {
            PartnerUser partnerUser = partnerUserRepository.findOne(partnerUserDetails.getPartnerUserId());
            // check if user name has changed
            Login partnerUserLogin = partnerUser.getLogin();
            if (partnerUserLogin.getLoginName().equals(partnerUserDetails.getUserLoginName())) {
               // just update email
               String email = partnerUserDetails.getUserEmail();
               if (partnerUserLogin.getEmail().equals(email)) {
                  partnerUserLogin.setEmail(email);
               }
               if (!(partnerUserLogin.getEmail().equals(email))) {
                  Login checkExisting = loginRepository.findByEmail(email);
                  if (checkExisting != null) {
                     throw new CcighgoException("Please select different email, a user already exists with specified email");
                  } else {
                     partnerUserLogin.setEmail(email);
                  }
               }
            } else {
               // check if login name selected is available
               Login checkExistingLoginName = loginRepository.findByLoginName(partnerUserDetails.getUserLoginName().toLowerCase());
               if (checkExistingLoginName == null) {
                  partnerUserLogin.setEmail(partnerUserDetails.getUserEmail());
                  partnerUserLogin.setLoginName(partnerUserDetails.getUserLoginName().toLowerCase());
               } else {
                  throw new CcighgoException("Please select different login name, a user already exists with specified login name");
               }
            }
            loginRepository.saveAndFlush(partnerUserLogin);
            if (partnerUserDetails.getUserSalutation() != null) {
               partnerUser.setSalutation(salutationRepositotry.findOne(partnerUserDetails.getUserSalutation().getSalutationId()));
            }
            partnerUser.setActive(partnerUserDetails.getUserActiveStatus() == 1 ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            partnerUser.setTitle(partnerUserDetails.getUserDesignation());
            partnerUser.setFirstName(partnerUserDetails.getUserFirstName());
            partnerUser.setLastName(partnerUserDetails.getUserLastName());
            partnerUser.setPhoto(partnerUserDetails.getUserPictureUrl());
            partnerUser.setPhone(partnerUserDetails.getUserPhone());
            partnerUser.setEmergencyPhone(partnerUserDetails.getUserEmergencyPhone());
            partnerUser.setFax(partnerUserDetails.getUserFax());
            partnerUser.setSkypeId(partnerUserDetails.getUserSkypeId());
            partnerUser.setLookupGender(genderRepository.findOne(partnerUserDetails.getUserGender().getGenderId()));
            if (partnerUserDetails.getUserOffices() != null) {
               for (UserOffice uo : partnerUserDetails.getUserOffices()) {
                  if (uo.isIsPrimary()) {
                     partnerUser.setPartnerOffice(partnerOfficeRepository.findOne(uo.getUserOfficeId()));
                  }
               }
            }
            partnerUserRepository.saveAndFlush(partnerUser);

            // PartnerPermission partnerUserPermission =
            // partnerPermissionRepository.findByPartnerUserId(partnerUser.getPartnerUserId());
            if (partnerUserDetails.getUserProgramsAndPermissions() != null) {
               for (Programs p : partnerUserDetails.getUserProgramsAndPermissions()) {
                  LookupDepartmentProgram lookup = null;
                  if (p.getProgramName().equals("J1HS")) {
                     lookup = lookupDepartmentProgramRepository.findOne(CCIConstants.HSP_J1_HS_ID);
                  } else if (p.getProgramName().equals(CCIConstants.HSP_F1)) {
                     lookup = lookupDepartmentProgramRepository.findOne(CCIConstants.HSP_F1_ID);
                  } else if (p.getProgramName().equals("IHP")) {
                     lookup = lookupDepartmentProgramRepository.findOne(CCIConstants.HSP_STP_IHP_ID);
                  } else if (p.getProgramName().equals("W&T")) {
                     lookup = lookupDepartmentProgramRepository.findOne(CCIConstants.WP_WT_SPRING_ID);
                  } else if (p.getProgramName().equals(CCIConstants.WP_WT_CAP)) {
                     lookup = lookupDepartmentProgramRepository.findOne(CCIConstants.WP_WT_CAP_ID);
                  }

                  List<PartnerPermission> permissions = new ArrayList<PartnerPermission>();
                  if (lookup != null) {
                     partnerPermissionRepository.deleteAllPermissionOfPartnerUserInCertainDepartmentProgram(partnerUser.getPartnerUserId(), lookup.getLookupDepartmentProgramId());
                     if (p.getPermissions().isAdmin()) {
                        PartnerPermission pPermission = new PartnerPermission();
                        PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.Admin);
                        pPermission.setLookupDepartmentProgram(lookup);
                        pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                        pPermission.setPartnerUser(partnerUser);
                        permissions.add(pPermission);
                     }
                     if (p.getPermissions().isAccounting()) {
                        PartnerPermission pPermission = new PartnerPermission();
                        PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.AccountingInsurance);
                        pPermission.setLookupDepartmentProgram(lookup);
                        pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                        pPermission.setPartnerUser(partnerUser);
                        permissions.add(pPermission);
                     }
                     if (p.getPermissions().isApplications()) {
                        PartnerPermission pPermission = new PartnerPermission();
                        PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.Applications);
                        pPermission.setLookupDepartmentProgram(lookup);
                        pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                        pPermission.setPartnerUser(partnerUser);
                        permissions.add(pPermission);
                     }
                     if (p.getPermissions().isContracting()) {
                        PartnerPermission pPermission = new PartnerPermission();
                        PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.Contracting);
                        pPermission.setLookupDepartmentProgram(lookup);
                        pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                        pPermission.setPartnerUser(partnerUser);
                        permissions.add(pPermission);
                     }
                     if (p.getPermissions().isFlights()) {
                        PartnerPermission pPermission = new PartnerPermission();
                        PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.Flights);
                        pPermission.setLookupDepartmentProgram(lookup);
                        pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                        pPermission.setPartnerUser(partnerUser);
                        permissions.add(pPermission);
                     }
                     if (p.getPermissions().isInsurance()) {
                        PartnerPermission pPermission = new PartnerPermission();
                        PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.Insurance);
                        pPermission.setLookupDepartmentProgram(lookup);
                        pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                        pPermission.setPartnerUser(partnerUser);
                        permissions.add(pPermission);
                     }
                     if (p.getPermissions().isMonitoring()) {
                        PartnerPermission pPermission = new PartnerPermission();
                        PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.Monitoring);
                        pPermission.setLookupDepartmentProgram(lookup);
                        pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                        pPermission.setPartnerUser(partnerUser);
                        permissions.add(pPermission);
                     }
                     if (p.getPermissions().isPlacementInfo()) {
                        PartnerPermission pPermission = new PartnerPermission();
                        PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.PlacementInfo);
                        pPermission.setLookupDepartmentProgram(lookup);
                        pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                        pPermission.setPartnerUser(partnerUser);
                        permissions.add(pPermission);
                     }
                     if (p.getPermissions().isStudentsPreProgram()) {
                        PartnerPermission pPermission = new PartnerPermission();
                        PartnerPermissionsCategory partnerPermissionsCategory = partnerPermissionCategoryRepository.findOne(CCIConstants.StudentPreProgram);
                        pPermission.setLookupDepartmentProgram(lookup);
                        pPermission.setPartnerPermissionsCategory(partnerPermissionsCategory);
                        pPermission.setPartnerUser(partnerUser);
                        permissions.add(pPermission);
                     }
                     partnerPermissionRepository.save(permissions);
                  }
               }
            }
            // partnerPermissionRepository.saveAndFlush(partnerUserPermission);
            updatedUser = getPartnerUserDetails(String.valueOf(partnerUser.getPartnerUserId()));
            updatedUser.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } catch (CcighgoException e) {
            updatedUser.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_UPDATING_PARTNERUSER_DATA.getValue(), e.getMessage()));
            LOGGER.error(messageUtil.getMessage(PartnerUserMessageConstants.ERROR_UPDATING_PARTNERUSER_DATA));
         }
      }
      return updatedUser;
   }

}
