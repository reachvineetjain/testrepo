/**
 * 
 */
package com.ccighgo.service.components.partner.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.PartnerOffice;
import com.ccighgo.db.entities.PartnerPermission;
import com.ccighgo.db.entities.PartnerUser;
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
import com.ccighgo.service.components.errormessages.constants.PartnerAdminSeasonConstants;
import com.ccighgo.service.components.errormessages.constants.RegionManagementMessageConstants;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.partner.user.details.PartnerUserDetails;
import com.ccighgo.service.transport.partner.beans.partner.user.details.Permissions;
import com.ccighgo.service.transport.partner.beans.partner.user.details.Programs;
import com.ccighgo.service.transport.partner.beans.partner.user.details.UserAddressCountry;
import com.ccighgo.service.transport.partner.beans.partner.user.details.UserOffice;
import com.ccighgo.service.transport.partner.beans.partner.user.details.UserSalutation;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUsers;
import com.ccighgo.utils.CCIConstants;

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

            // partner office
            List<UserOffice> userOfficesList = null;
            if (partnerUser.getPartner().getPartnerOffices() != null) {
               userOfficesList = new ArrayList<UserOffice>();
               for (PartnerOffice pOffice : partnerUser.getPartner().getPartnerOffices()) {
                  UserOffice usrOffice = new UserOffice();
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
                  if (pOffice.getPartnerOfficeType().equals(CCIConstants.PRIMARY_OFFICE)) {
                     usrOffice.setIsPrimary(true);
                  } else {
                     usrOffice.setIsPrimary(false);
                  }
                  userOfficesList.add(usrOffice);
               }
            }
            partnerUserDetails.getUserOffices().addAll(userOfficesList);

            List<Programs> userProgramsAndPermissions = new ArrayList<Programs>();
            PartnerPermission partnerPermission = partnerPermissionRepository.findByPartnerUserId(partnerUser.getPartnerUserId());
            if (partnerPermission != null) {
               Programs j1Program = new Programs();
               j1Program.setProgramName(CCIConstants.HSP_J1_HS);
               Permissions j1Permissions = new Permissions();
               j1Permissions.setAccounting(partnerPermission.getJ1AccountingInsurance() == CCIConstants.ACTIVE ? true : false);
               j1Permissions.setAdmin(partnerPermission.getJ1Admin()== CCIConstants.ACTIVE ? true : false);
               j1Permissions.setApplications(partnerPermission.getJ1Applications()== CCIConstants.ACTIVE ? true : false);
               j1Permissions.setContracting(partnerPermission.getJ1Contracting()== CCIConstants.ACTIVE ? true : false);
               j1Permissions.setFlights(partnerPermission.getJ1Flights()== CCIConstants.ACTIVE ? true : false);
               j1Permissions.setInsurance(partnerPermission.getJ1Insurance()== CCIConstants.ACTIVE ? true : false);
               j1Permissions.setMonitoring(partnerPermission.getJ1Monitoring()== CCIConstants.ACTIVE ? true : false);
               j1Permissions.setPlacementInfo(partnerPermission.getJ1PlacementInfo()== CCIConstants.ACTIVE ? true : false);
               j1Permissions.setStudentsPreProgram(partnerPermission.getJ1StudentsPreProgram()== CCIConstants.ACTIVE ? true : false);
               j1Program.setPermissions(j1Permissions);
               userProgramsAndPermissions.add(j1Program);
               
               Programs f1Program = new Programs();
               f1Program.setProgramName(CCIConstants.HSP_F1);
               Permissions f1Permissions = new Permissions();
               f1Permissions.setAccounting(partnerPermission.getF1AccountingInsurance() == CCIConstants.ACTIVE ? true : false);
               f1Permissions.setAdmin(partnerPermission.getF1Admin()== CCIConstants.ACTIVE ? true : false);
               f1Permissions.setApplications(partnerPermission.getF1Applications()== CCIConstants.ACTIVE ? true : false);
               f1Permissions.setContracting(partnerPermission.getF1Contracting()== CCIConstants.ACTIVE ? true : false);
               f1Permissions.setFlights(partnerPermission.getF1Flights()== CCIConstants.ACTIVE ? true : false);
               f1Permissions.setInsurance(partnerPermission.getF1Insurance()== CCIConstants.ACTIVE ? true : false);
               f1Permissions.setMonitoring(partnerPermission.getF1Monitoring()== CCIConstants.ACTIVE ? true : false);
               f1Permissions.setPlacementInfo(partnerPermission.getF1PlacementInfo()== CCIConstants.ACTIVE ? true : false);
               f1Permissions.setStudentsPreProgram(partnerPermission.getF1StudentsPreProgram()== CCIConstants.ACTIVE ? true : false);
               f1Program.setPermissions(f1Permissions);
               userProgramsAndPermissions.add(f1Program);
               
               Programs ihpProgram = new Programs();
               j1Program.setProgramName("IHP");
               Permissions ihpPermissions = new Permissions();
               ihpPermissions.setAccounting(partnerPermission.getIhpAccountingInsurance() == CCIConstants.ACTIVE ? true : false);
               ihpPermissions.setAdmin(partnerPermission.getIhpAdmin()== CCIConstants.ACTIVE ? true : false);
               ihpPermissions.setApplications(partnerPermission.getIhpApplications()== CCIConstants.ACTIVE ? true : false);
               ihpPermissions.setContracting(partnerPermission.getIhpContracting()== CCIConstants.ACTIVE ? true : false);
               ihpPermissions.setFlights(partnerPermission.getIhpFlights()== CCIConstants.ACTIVE ? true : false);
               ihpPermissions.setInsurance(partnerPermission.getIhpInsurance()== CCIConstants.ACTIVE ? true : false);
               ihpPermissions.setMonitoring(partnerPermission.getIhpMonitoring()== CCIConstants.ACTIVE ? true : false);
               ihpPermissions.setPlacementInfo(partnerPermission.getIhpPlacementInfo()== CCIConstants.ACTIVE ? true : false);
               ihpPermissions.setStudentsPreProgram(partnerPermission.getIhpStudentsPreProgram()== CCIConstants.ACTIVE ? true : false);
               ihpProgram.setPermissions(ihpPermissions);
               userProgramsAndPermissions.add(ihpProgram);
               
               Programs wntProgram = new Programs();
               wntProgram.setProgramName("W&T");
               Permissions wntPermissions = new Permissions();
               wntPermissions.setAccounting(partnerPermission.getWtAccountingInsurance() == CCIConstants.ACTIVE ? true : false);
               wntPermissions.setAdmin(partnerPermission.getWtAdmin()== CCIConstants.ACTIVE ? true : false);
               wntPermissions.setApplications(partnerPermission.getWtApplications()== CCIConstants.ACTIVE ? true : false);
               wntPermissions.setContracting(partnerPermission.getWtContracting()== CCIConstants.ACTIVE ? true : false);
               wntPermissions.setFlights(partnerPermission.getWtFlights()== CCIConstants.ACTIVE ? true : false);
               wntPermissions.setInsurance(partnerPermission.getWtInsurance()== CCIConstants.ACTIVE ? true : false);
               wntPermissions.setMonitoring(partnerPermission.getWtMonitoring()== CCIConstants.ACTIVE ? true : false);
               wntPermissions.setPlacementInfo(partnerPermission.getWtPlacementInfo()== CCIConstants.ACTIVE ? true : false);
               wntPermissions.setStudentsPreProgram(partnerPermission.getWtStudentsPreProgram()== CCIConstants.ACTIVE ? true : false);
               wntProgram.setPermissions(wntPermissions);
               userProgramsAndPermissions.add(wntProgram);
               
               Programs capProgram = new Programs();
               capProgram.setProgramName(CCIConstants.WP_WT_CAP);
               Permissions capPermissions = new Permissions();
               capPermissions.setAccounting(partnerPermission.getCapAccountingInsurance() == CCIConstants.ACTIVE ? true : false);
               capPermissions.setAdmin(partnerPermission.getCapAdmin()== CCIConstants.ACTIVE ? true : false);
               capPermissions.setApplications(partnerPermission.getCapApplications()== CCIConstants.ACTIVE ? true : false);
               capPermissions.setContracting(partnerPermission.getCapContracting()== CCIConstants.ACTIVE ? true : false);
               capPermissions.setFlights(partnerPermission.getCapFlights()== CCIConstants.ACTIVE ? true : false);
               capPermissions.setInsurance(partnerPermission.getCapInsurance()== CCIConstants.ACTIVE ? true : false);
               capPermissions.setMonitoring(partnerPermission.getCapMonitoring()== CCIConstants.ACTIVE ? true : false);
               capPermissions.setPlacementInfo(partnerPermission.getCapPlacementInfo()== CCIConstants.ACTIVE ? true : false);
               capPermissions.setStudentsPreProgram(partnerPermission.getCapStudentsPreProgram()== CCIConstants.ACTIVE ? true : false);
               capProgram.setPermissions(j1Permissions);
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

}
