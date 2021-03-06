/**
 * 
 */
package com.ccighgo.service.components.partner.admin.partner;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.CCIStaffUser;
import com.ccighgo.db.entities.GoIdSequence;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.db.entities.LookupDepartmentProgram;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerAgentInquiry;
import com.ccighgo.db.entities.PartnerPermission;
import com.ccighgo.db.entities.PartnerPermissionsCategory;
import com.ccighgo.db.entities.PartnerProgram;
import com.ccighgo.db.entities.PartnerReviewStatus;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerStatus;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.PartnerCodes;
import com.ccighgo.exception.SeasonCodes;
import com.ccighgo.jpa.repositories.CCIStaffUsersRepository;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LoginUserTypeRepository;
import com.ccighgo.jpa.repositories.LookupDepartmentProgramRepository;
import com.ccighgo.jpa.repositories.PartnerAgentInquiryRepository;
import com.ccighgo.jpa.repositories.PartnerPermissionCategoryRepository;
import com.ccighgo.jpa.repositories.PartnerPermissionRepository;
import com.ccighgo.jpa.repositories.PartnerProgramRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerReviewStatusRepository;
import com.ccighgo.jpa.repositories.PartnerStatusRepository;
import com.ccighgo.jpa.repositories.PartnerUserRepository;
import com.ccighgo.jpa.repositories.SalutationRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.component.emailing.EmailServiceImpl;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.admin.add.partner.AdminAddPartner;
import com.ccighgo.service.transport.partner.beans.admin.add.partner.ProgramContacts;
import com.ccighgo.service.transport.partner.beans.admin.added.partner.AddedPartner;
import com.ccighgo.service.transport.partner.beans.admin.added.partner.AddedPartners;
import com.ccighgo.service.transport.partner.beans.admin.added.partner.PartnerCountry;
import com.ccighgo.service.transport.partner.beans.admin.added.partner.PartnerSeasons;
import com.ccighgo.service.transport.partner.beans.admin.lead.partner.LeadCountry;
import com.ccighgo.service.transport.partner.beans.admin.lead.partner.LeadPartner;
import com.ccighgo.service.transport.partner.beans.admin.lead.partner.LeadPartners;
import com.ccighgo.service.transport.partner.beans.admin.lead.partner.status.LeadStatus;
import com.ccighgo.service.transport.partner.beans.admin.lead.partner.status.PartnerLeadStatus;
import com.ccighgo.service.transport.partner.beans.partner.user.details.Programs;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.PasscodeGenerator;
import com.ccighgo.utils.PasswordUtil;
import com.ccighgo.utils.UuidUtils;

/**
 * @author ravi
 *
 */
@Component
public class AdminPartnerInterfaceImpl implements AdminPartnerInterface {

   private static final int VALID_STATUS = 11;

   private static final Logger LOGGER = LoggerFactory.getLogger(AdminPartnerInterfaceImpl.class);

   @Autowired CommonComponentUtils componentUtils;
   @Autowired MessageUtils messageUtil;
   @Autowired PartnerRepository partnerRepository;
   @Autowired PartnerUserRepository partnerUserRepository;
   @Autowired LoginRepository loginRepository;
   @Autowired CountryRepository countryRepository;
   @Autowired PartnerProgramRepository partnerProgramRepository;
   @Autowired GoIdSequenceRepository goIdSequenceRepository;
   @Autowired LoginUserTypeRepository loginUserTypeRepository;
   @Autowired UserTypeRepository userTypeRepository;
   @Autowired SalutationRepository salutationRepositotry;
   @Autowired CCIStaffUsersRepository cciStaffUsersRepository;
   @Autowired LookupDepartmentProgramRepository departmentProgramRepository;
   @Autowired EmailServiceImpl email;
   @Autowired PartnerAgentInquiryRepository partnerAgentInquiryRepository;
   @Autowired PartnerReviewStatusRepository partnerReviewStatusRepository;
   @Autowired PartnerStatusRepository partnerStatusRepository;
   @Autowired EntityManager entityManager;
   @Autowired LookupDepartmentProgramRepository lookupDepartmentProgramRepository;
   @Autowired PartnerPermissionCategoryRepository partnerPermissionCategoryRepository;
   @Autowired PartnerPermissionRepository partnerPermissionRepository;

   public static final String SPPartnerWQCategoryAggregate = "call SPPartnerWQCategoryAggregate()";
   public static final String SPPartnerWQTypeAggregate = "call SPPartnerWQTypeAggregate()";

   @Override
   @Transactional
   public AdminAddPartner addPartner(AdminAddPartner partner, HttpServletRequest request) {
      try {
         if (partner == null) {
            throw new CcighgoException("null/empty data");
         }
         if (partner.getLoginId() == 0 || partner.getLoginId() < 0) {
            throw new CcighgoException("Id of user adding the partner is required.");
         }
         if (partner.getPartnerCountry() == null) {
            throw new CcighgoException("Country is required.");
         }
         Login checkLoginNameExists = loginRepository.findByLoginName(partner.getUserName());
         if (checkLoginNameExists != null) {
            throw new CcighgoException("A user with specified user/login name already exists, please specify different user/login name.");
         }
         Login checkEmailExists = loginRepository.findByEmail(partner.getEmail());
         if (checkEmailExists != null) {
            throw new CcighgoException("A user with specified email id already exists, please specify different email.");
         }
         // create login
         Login login = new Login();
         login.setActive(partner.isSendLogin() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         GoIdSequence goIdSequence = new GoIdSequence();
         goIdSequence = goIdSequenceRepository.saveAndFlush(goIdSequence);
         login.setGoIdSequence(goIdSequence);
         login.setLoginName(partner.getUserName());
         login.setKeyValue(UuidUtils.nextHexUUID());
         login.setEmail(partner.getEmail());
         login.setPassword(PasswordUtil.hashKey(PasscodeGenerator.generateRandomPasscode(8, 8, 1, 1, 1).toString()));
         login.setCreatedBy(partner.getLoginId());
         login.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         login.setModifiedBy(partner.getLoginId());
         login.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         login = loginRepository.saveAndFlush(login);

         // set login user type
         LoginUserType loginUserType = new LoginUserType();
         loginUserType.setLogin(login);
         loginUserType.setUserType(userTypeRepository.findOne(2));
         loginUserType.setDefaultUserType(CCIConstants.ACTIVE);
         loginUserType.setActive(CCIConstants.ACTIVE);
         if (login.getLoginId() != null)
            loginUserType.setCreatedBy(login.getLoginId());
         loginUserType.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         if (login.getLoginId() != null)
            loginUserType.setModifiedBy(login.getLoginId());
         loginUserType.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         loginUserTypeRepository.saveAndFlush(loginUserType);

         Partner newPartner = new Partner();
         newPartner.setPartnerGoId(goIdSequence.getGoId());
         newPartner.setPartnerLogo(partner.getCompanyLogo());
         newPartner.setCompanyName(partner.getCompanyName());
         newPartner.setQuickbooksCode(partner.getQuickbookCode());
         newPartner.setAcronym(partner.getAcronym());
         newPartner.setEmail(partner.getGeneralEmail());
         newPartner.setCanHaveSubPartner(partner.isCanHaveSubpartners() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         newPartner.setMultiCountrySender(partner.isMultiCountrySender() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         // initializing all flags to false
         newPartner.setReceiveAYPMails(CCIConstants.INACTIVE);
         newPartner.setSubscribeToCCINewsletter(CCIConstants.INACTIVE);
         newPartner.setHasSubPartners(CCIConstants.INACTIVE);
         // newPartner.setMultiCountrySender(CCIConstants.INACTIVE);
         newPartner.setIsSubPartner(CCIConstants.INACTIVE);
         newPartner.setPayGreenheartDirectly(CCIConstants.INACTIVE);
         newPartner.setDeliverDSForms(CCIConstants.INACTIVE);
         newPartner.setNeedPartnerReview(CCIConstants.INACTIVE);
         newPartner.setMailingAddressIsSameAsPhysicalAdress(CCIConstants.ACTIVE);
         newPartner.setParticipantMedicalReleaseRequired(CCIConstants.INACTIVE);
         newPartner.setParticipantSLEPRequired(CCIConstants.INACTIVE);
         newPartner.setParticipantTranscriptRequired(CCIConstants.INACTIVE);
         newPartner.setUnguaranteedFormRequired(CCIConstants.INACTIVE);
         newPartner.setParticipantELTISRequired(CCIConstants.INACTIVE);
         newPartner.setLookupCountry1(countryRepository.findOne(partner.getPartnerCountry().getCountryId()));
         newPartner.setLookupCountry2(countryRepository.findOne(partner.getPartnerCountry().getCountryId()));
         newPartner.setCreatedBy(partner.getLoginId());
         newPartner.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         newPartner.setModifiedBy(partner.getLoginId());
         newPartner.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         if (partner.getGeneralContact() != null)
            newPartner.setCcistaffUser(cciStaffUsersRepository.findOne(partner.getGeneralContact().getCciUserId()));
         newPartner = partnerRepository.saveAndFlush(newPartner);

         PartnerUser pUser = new PartnerUser();
         pUser.setPartner(newPartner);
         pUser.setLogin(login);
         pUser.setSalutation(salutationRepositotry.findOne(partner.getSalutation().getSalutationId()));
         pUser.setFirstName(partner.getFirstName());
         pUser.setLastName(partner.getLastName());
         pUser.setActive(login.getActive());
         pUser.setIsPrimary(CCIConstants.ACTIVE);
         pUser = partnerUserRepository.saveAndFlush(pUser);

         PartnerReviewStatus reviewStatus = new PartnerReviewStatus();
         reviewStatus.setPartner(newPartner);
         // set the default partnerLeadStatus to Valid
         reviewStatus.setPartnerStatus1(partnerStatusRepository.findOne(CCIConstants.VALID));
         // set the partnerAgentStatusId to Pending
         reviewStatus.setPartnerStatus2(partnerStatusRepository.findOne(CCIConstants.PENDING_STATUS));
         CCIStaffUser cciUser = null;
         Login cciLogin = loginRepository.findOne(partner.getLoginId());
         if (cciLogin != null) {
            cciUser = cciStaffUsersRepository.findOne(cciLogin.getGoIdSequence().getGoId());
         }
         reviewStatus.setCcistaffUser(cciUser);
         partnerReviewStatusRepository.saveAndFlush(reviewStatus);

         if (partner.getProgramContacts() != null) {
            List<PartnerProgram> partnerProgramList = new ArrayList<PartnerProgram>();
            for (ProgramContacts pCon : partner.getProgramContacts()) {
               PartnerProgram pp = new PartnerProgram();
               pp.setCcistaffUser(cciStaffUsersRepository.findOne(pCon.getCciUserId()));
               pp.setLookupDepartmentProgram(departmentProgramRepository.findOne(pCon.getDepartmentProgramId()));
               pp.setPartner(newPartner);
               pp.setHasApplied(CCIConstants.ACTIVE);
               pp.setIsEligible(pCon.getEligible());
               partnerProgramList.add(pp);
            }
            partnerProgramRepository.save(partnerProgramList);
         }
         if (partner.isSendLogin()) {
            Login loginEmail = loginRepository.findByEmail(login.getEmail());
            String body = "<p>This email was sent automatically by CCI Greenheart Online system to inform you that you an online account has been created for you.  </p></br>"
                  + "<p>Please go to the following page and follow the instructions to login to the system. </p> " + "<p>" + formResetURL(request).concat(loginEmail.getKeyValue())
                  + "</p></br>" + "<p>Thank you,</p><p>GO System Support.</p>";
            email.send(loginEmail.getEmail(), CCIConstants.CREATE_CCI_USER_SUBJECT, body, true);
            Query q1 = entityManager.createNativeQuery(SPPartnerWQCategoryAggregate);
            Query q2 = entityManager.createNativeQuery(SPPartnerWQTypeAggregate);
            q1.executeUpdate();
            q2.executeUpdate();
         }
         partner.setPartnerGoId(newPartner.getPartnerGoId());
         partner.setLoginId(login.getLoginId());
         partner.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE, messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         partner.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.FAILED_TO_ADD_PARTNER.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return partner;
   }

   @Override
   @Transactional(readOnly = true)
   public AddedPartners getAddedPartnerList() {
      AddedPartners addedPartners = new AddedPartners();
      try {
         List<PartnerReviewStatus> partnerReviewStatusList = partnerReviewStatusRepository.findAddedPartners(VALID_STATUS);
         if (partnerReviewStatusList == null) {
            throw new CcighgoException("No Active partners found.");
         }
         addedPartners.setCount(partnerReviewStatusList.size());

         for (PartnerReviewStatus prs : partnerReviewStatusList) {
            List<AddedPartner> addedPartnersList = new ArrayList<AddedPartner>();
            Partner p = partnerRepository.findOne(prs.getPartner().getPartnerGoId());
            PartnerUser puser = null;
            List<PartnerUser> partnerUserList = p.getPartnerUsers();
            if (partnerUserList != null) {
               for (PartnerUser pu : partnerUserList) {
                  if (pu.getIsPrimary().equals(CCIConstants.ACTIVE)) {
                     puser = pu;
                     break;
                  }
               }
            }
            if (puser != null) {
               AddedPartner ap = new AddedPartner();
               ap.setCompanyName(p.getCompanyName());
               ap.setGoId(p.getPartnerGoId());
               ap.setType(p.getIsSubPartner() == CCIConstants.ACTIVE ? "Sub Partner" : "Partner");
               ap.setFirstName(puser.getFirstName());
               ap.setLastName(puser.getLastName());
               if (puser.getLogin() != null)
                  ap.setEmail(puser.getLogin().getEmail());
               if (p.getLookupCountry1() != null) {
                  PartnerCountry pCountry = new PartnerCountry();
                  pCountry.setCountryId(p.getLookupCountry1().getCountryId());
                  pCountry.setCountryCode(p.getLookupCountry1().getCountryCode());
                  pCountry.setCountryName(p.getLookupCountry1().getCountryName());
                  pCountry.setCountryFlagUrl(p.getLookupCountry1().getCountryFlag());
                  ap.setPartnerCountry(pCountry);
               }
               ap.setActive(puser.getLogin().getActive().equals(CCIConstants.ACTIVE) ? 1 : 0);
               ap.setPartnerLoginId(puser.getLogin().getLoginId());

               // partner status
               String status = null;
               List<PartnerReviewStatus> partnerReviewStatuses = p.getPartnerReviewStatuses();
               if (prs.getPartnerStatus2() != null) {
                  if (!(prs.getPartnerStatus2().getPartnerStatusId() == CCIConstants.DELETED_STATUS)) {
                     if (partnerReviewStatuses != null && !partnerReviewStatuses.isEmpty() && partnerReviewStatuses.get(0).getPartnerStatus2() != null) {
                        status = partnerReviewStatuses.get(0).getPartnerStatus2().getPartnerStatusName();
                     }
                     ap.setStatus(status);
                     List<PartnerSeasons> psList = null;
                     List<PartnerSeason> seasonsList = p.getPartnerSeasons();
                     if (seasonsList != null) {
                        psList = new ArrayList<PartnerSeasons>();
                        for (PartnerSeason ps : seasonsList) {
                           PartnerSeasons pSeason = new PartnerSeasons();
                           pSeason.setSeasonName(ps.getSeason().getSeasonName());
                           psList.add(pSeason);
                        }
                     }
                     ap.getSeasons().addAll(psList);
                     addedPartnersList.add(ap);
                  }
               }
               addedPartners.getAddedPartners().addAll(addedPartnersList);
               addedPartners.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            }
         }
      } catch (CcighgoException e) {
         addedPartners.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.FAILED_TO_GET_ADDED_PARTNER_LIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return addedPartners;
   }

   @Override
   @Transactional
   public Response toggleActiveStatus(String statusVal, String loggedinUserLoginId, String partnerLoginId) {
      Response resp = new Response();
      try {
         if (!(Integer.valueOf(statusVal) == 0) && !(Integer.valueOf(statusVal) == 1)) {
            throw new CcighgoException("invalid status value, can be either 0 or 1");
         }
         if (loggedinUserLoginId == null || Integer.valueOf(loggedinUserLoginId) == 0 || Integer.valueOf(loggedinUserLoginId) < 0) {
            throw new CcighgoException("logged in user info is required to update the record");
         }
         if (partnerLoginId == null || Integer.valueOf(partnerLoginId) == 0 || Integer.valueOf(partnerLoginId) < 0) {
            throw new CcighgoException("login info of partner is required update the record");
         }
         
         Byte activeStatus = null;
         if (Integer.valueOf(statusVal) == 1) {
            activeStatus = CCIConstants.ACTIVE;
         }
         if (Integer.valueOf(statusVal) == 0) {
            activeStatus = CCIConstants.INACTIVE;
         }
         //first enable/disable login of the partner
         //Bug 1459
         Login login = loginRepository.findOne(Integer.valueOf(partnerLoginId));
         if(login!=null){
            login.setActive(activeStatus);
            login.setModifiedBy(Integer.parseInt(loggedinUserLoginId));
            loginRepository.saveAndFlush(login);
         }else{
            throw new CcighgoException("no record found to update status with the login info provided, please check request url");
         }
         
        // Get list of partner users and enable/disable their login
         List<Login> partnerLogin = loginRepository.findAllByGoId(login.getGoIdSequence().getGoId());
         if (partnerLogin == null) {
            throw new CcighgoException("no record found to update status with the login info provided, please check request url");
         }
         List<Login> logins = new ArrayList<Login>();
         if (partnerLogin != null && !partnerLogin.isEmpty()) {
            for (Login user : partnerLogin) {
               user.setActive(activeStatus);
               user.setModifiedBy(Integer.parseInt(loggedinUserLoginId));
               logins.add(user);
            }
            loginRepository.save(logins);
         }
         //Enable/disable login of subpartners
         List<Partner> subPartners = partnerRepository.findByIsSubPartnerAndParentId(login.getGoIdSequence().getGoId());
         if (subPartners != null && !subPartners.isEmpty()) {
            logins = new ArrayList<Login>();
            for (Partner partner : subPartners) {
               if(partner.getNeedPartnerReview().equals(CCIConstants.ACTIVE)){
                  partnerLogin = loginRepository.findAllByGoId(partner.getGoIdSequence().getGoId());
                  for (Login user : partnerLogin) {
                     user.setActive(activeStatus);
                     user.setModifiedBy(Integer.parseInt(loggedinUserLoginId));
                     logins.add(user);
                  }
                  loginRepository.save(logins);
               }
            }
         }

         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE, messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.FAILED_TOGGLE_ACTIVE_STATUS.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return resp;
   }

   @Override
   @Transactional(readOnly = true)
   public Response sendLogin(String partnerGoId, HttpServletRequest request) {
      Response response = new Response();
      try {
         if (partnerGoId == null || Integer.valueOf(partnerGoId) == 0 || Integer.valueOf(partnerGoId) < 0) {
            throw new CcighgoException("invalid partner info, cannot send login");
         }
         Partner partner = partnerRepository.findOne(Integer.valueOf(partnerGoId));
         PartnerUser partnerUser = null;
         List<PartnerUser> partnerUserList = partner.getPartnerUsers();
         if (partnerUserList != null) {
            for (PartnerUser pu : partnerUserList) {
               if (pu.getIsPrimary() == CCIConstants.ACTIVE) {
                  partnerUser = pu;
                  break;
               }
            }
         }
         if (partnerUser != null && partnerUser.getLogin() != null) {
            String body = "<p>Ciao! </p>" + "<p>This email was sent automatically by Greenheart Online (GO) in response to your request for a new password. </p>" + "<p>"
                  + "Your username is : " + partnerUser.getLogin().getLoginName() + "</p>" + "<p>Please click on the link below to create a new password:</p> " + "<p>"
                  + formResetURL(request).concat(partnerUser.getLogin().getKeyValue()) + "</p>" + "<p>If you didn't request a new password, please let us know.</p>"
                  + "<p>Thank you,</p>" + "<p>CCI Greenheart.</p>";
            email.send(partnerUser.getLogin().getEmail(), CCIConstants.RESET_PASSWORD_SUBJECT, body, true);
            response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE, "An email has been sent to address " + "\'"
                  + partnerUser.getLogin().getEmail() + "\'" + " for login name " + "\'" + partnerUser.getLogin().getLoginName() + "\'" + " with instructions to reset password"));
         } else {
            response.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD, CCIConstants.TYPE_INFO, CCIConstants.NO_DATA_CODE, messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_SENDING_LOGIN_INFO.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return response;
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
   @Transactional(readOnly = true)
   public LeadPartners getLeadPartnerList(String statusId) {
      LeadPartners leadPartners = new LeadPartners();
      try {
         // 4 is pending status
         List<PartnerReviewStatus> partnerReviewStatusList = partnerReviewStatusRepository.findReviewStatusByStatus(Integer.valueOf(statusId));
         if (partnerReviewStatusList == null) {
            throw new CcighgoException("No Leads found.");
         }
         leadPartners.setCount(partnerReviewStatusList.size());
         List<LeadPartner> leadPartnersList = new ArrayList<LeadPartner>();
         for (PartnerReviewStatus prs : partnerReviewStatusList) {
            PartnerAgentInquiry p = partnerAgentInquiryRepository.findPartnerByGoId(prs.getPartner().getPartnerGoId());
            if (p != null) {
               LeadPartner lp = new LeadPartner();
               lp.setCompanyName(p.getCompanyName());
               if (p.getRating() != null)
                  lp.setRating(p.getRating());
               lp.setFirstName(p.getFirstName());
               lp.setLastName(p.getLastName());
               lp.setPhone(p.getPhone());
               lp.setExtenstion("");
               lp.setWebsite(p.getWebsite());
               lp.setEmail(p.getEmail());
               lp.setGoId(p.getPartner() != null ? p.getPartner().getPartnerGoId() : 0);
               // Feature enhancement, allow user to search based on status
               lp.setLeadStatus(prs.getPartnerStatus1().getPartnerStatusName());
               if(p.getLookupCountry()!=null){
	            	   LeadCountry pCountry = new LeadCountry();
	               pCountry.setCountryId(p.getLookupCountry().getCountryId());
	               pCountry.setCountryCode(p.getLookupCountry().getCountryCode());
	               pCountry.setCountryName(p.getLookupCountry().getCountryName());
	               pCountry.setCountryFlagUrl(p.getLookupCountry().getCountryFlag());
	               lp.setLeadCountry(pCountry);
               }
               if (p.getPartner() != null && p.getPartner().getPartnerPrograms() != null) {
                  List<PartnerProgram> partnerProgramList = p.getPartner().getPartnerPrograms();
                  List<com.ccighgo.service.transport.partner.beans.admin.lead.partner.PartnerProgram> programs = null;
                  if (partnerProgramList != null) {
                     programs = new ArrayList<com.ccighgo.service.transport.partner.beans.admin.lead.partner.PartnerProgram>();
                     for (PartnerProgram pp : partnerProgramList) {
                        com.ccighgo.service.transport.partner.beans.admin.lead.partner.PartnerProgram ppr = new com.ccighgo.service.transport.partner.beans.admin.lead.partner.PartnerProgram();
                        ppr.setProgramId(pp.getLookupDepartmentProgram().getLookupDepartmentProgramId());
                        ppr.setProgramName(pp.getLookupDepartmentProgram().getProgramName());
                        programs.add(ppr);
                     }
                  }
                  lp.getPrograms().addAll(programs);
               }

               leadPartnersList.add(lp);
            }
         }
         leadPartners.getLeadPartners().addAll(leadPartnersList);
         leadPartners.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         leadPartners.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_GET_LEAD_PARTNER_LIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return leadPartners;
   }

   @Override
   public Response junkPartnerLead(String partnerGoId) {
      Response resp = new Response();
      try {
         PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findApplicationStatusByGoId(Integer.valueOf(partnerGoId));
         partnerReviewStatus.setPartnerStatus1(partnerStatusRepository.findOne(CCIConstants.JUNK));
         partnerReviewStatusRepository.saveAndFlush(partnerReviewStatus);
         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE, messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_SETTING_PARTNER_LEAD_AS_JUNK.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return resp;
   }

   @Override
   public Response blacklistPartnerLead(String partnerGoId) {
      Response resp = new Response();
      try {
         PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findApplicationStatusByGoId(Integer.valueOf(partnerGoId));
         partnerReviewStatus.setPartnerStatus1(partnerStatusRepository.findOne(CCIConstants.BLACKLIST));
         partnerReviewStatusRepository.saveAndFlush(partnerReviewStatus);
         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE, messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_SETTING_PARTNER_LEAD_TO_BLACKLIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return resp;
   }

   @Override
   public Response invalidatePartnerLead(String partnerGoId, String reason) {
      Response resp = new Response();
      try {
         PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findApplicationStatusByGoId(Integer.valueOf(partnerGoId));
         partnerReviewStatus.setPartnerStatus1(partnerStatusRepository.findOne(CCIConstants.INVALID));
         partnerReviewStatus.setPartnerStatusReason(reason);
         partnerReviewStatusRepository.saveAndFlush(partnerReviewStatus);
         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE, messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_SETTING_PARTNER_LEAD_TO_INVALID.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return resp;
   }

   @Override
   public Response partnerLeadSendLogin(String partnerGoId, String loginVal, String loginId, HttpServletRequest request) {
      Response resp = new Response();
      try {
         if (loginVal == null) {
            throw new CcighgoException("send login value is required");
         }
         if (loginId == null) {
            throw new CcighgoException("login id is required");
         }
         PartnerAgentInquiry p = partnerAgentInquiryRepository.findPartnerByGoId(Integer.valueOf(partnerGoId));
         if (p != null) {
            // create login
            Login login = new Login();
            login.setActive(Integer.valueOf(loginVal).equals(CCIConstants.SEND_LOGIN) ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            GoIdSequence goIdSequence = new GoIdSequence();
            goIdSequence.setGoId(Integer.valueOf(partnerGoId));
            login.setGoIdSequence(goIdSequence);
            login.setLoginName(PasscodeGenerator.generateRandomPasscode(8, 8, 1, 1, 1).toString());
            login.setKeyValue(UuidUtils.nextHexUUID());
            login.setEmail(p.getEmail());
            login.setPassword(PasswordUtil.hashKey(PasscodeGenerator.generateRandomPasscode(8, 8, 1, 1, 1).toString()));
            login.setCreatedBy(Integer.valueOf(loginId));
            login.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            login.setModifiedBy(Integer.valueOf(loginId));
            login.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            login = loginRepository.saveAndFlush(login);

            // set login user type
            LoginUserType loginUserType = new LoginUserType();
            loginUserType.setLogin(login);
            loginUserType.setUserType(userTypeRepository.findOne(2));
            loginUserType.setDefaultUserType(CCIConstants.ACTIVE);
            loginUserType.setActive(CCIConstants.ACTIVE);
            if (login.getLoginId() != null)
               loginUserType.setCreatedBy(login.getLoginId());
            loginUserType.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            if (login.getLoginId() != null)
               loginUserType.setModifiedBy(login.getLoginId());
            loginUserType.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            loginUserTypeRepository.saveAndFlush(loginUserType);

            Partner newPartner = new Partner();
            newPartner.setPartnerGoId(Integer.valueOf(partnerGoId));
            newPartner.setPartnerLogo(p.getLogo());
            newPartner.setCompanyName(p.getBusinessName());
            newPartner.setEmail(p.getEmail());
            newPartner.setCanHaveSubPartner(CCIConstants.ACTIVE);
            // initializing all flags to false
            newPartner.setReceiveAYPMails(CCIConstants.INACTIVE);
            newPartner.setSubscribeToCCINewsletter(CCIConstants.INACTIVE);
            newPartner.setHasSubPartners(CCIConstants.INACTIVE);
            newPartner.setMultiCountrySender(CCIConstants.INACTIVE);
            newPartner.setIsSubPartner(CCIConstants.INACTIVE);
            newPartner.setPayGreenheartDirectly(CCIConstants.INACTIVE);
            newPartner.setDeliverDSForms(CCIConstants.INACTIVE);
            newPartner.setNeedPartnerReview(CCIConstants.INACTIVE);
            newPartner.setMailingAddressIsSameAsPhysicalAdress(CCIConstants.ACTIVE);
            newPartner.setParticipantMedicalReleaseRequired(CCIConstants.INACTIVE);
            newPartner.setParticipantSLEPRequired(CCIConstants.INACTIVE);
            newPartner.setParticipantTranscriptRequired(CCIConstants.INACTIVE);
            newPartner.setUnguaranteedFormRequired(CCIConstants.INACTIVE);
            newPartner.setParticipantELTISRequired(CCIConstants.INACTIVE);
            newPartner.setLookupCountry1(countryRepository.findOne(p.getLookupCountry().getCountryId()));
            newPartner.setLookupCountry2(countryRepository.findOne(p.getLookupCountry().getCountryId()));
            newPartner.setCreatedBy(Integer.valueOf(loginId));
            newPartner.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            newPartner.setModifiedBy(Integer.valueOf(loginId));
            newPartner.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            newPartner = partnerRepository.saveAndFlush(newPartner);

            /**
             * Bug 1581 - disable adding partner contact 
             */
            PartnerUser pUser = new PartnerUser();
            pUser.setPartner(newPartner);
            pUser.setLogin(login);
            pUser.setSalutation(salutationRepositotry.findOne(p.getSalutation().getSalutationId()));
            pUser.setFirstName(p.getFirstName());
            pUser.setLastName(p.getLastName());
            pUser.setActive(CCIConstants.ACTIVE);
            pUser.setIsPrimary(CCIConstants.ACTIVE);
            pUser.setPhone(p.getPhone());
            
            pUser = partnerUserRepository.saveAndFlush(pUser);
            
            /**
             * Adding partner Permissions
             */
            List<PartnerProgram> partnerProgramList = p.getPartner().getPartnerPrograms();
            if(partnerProgramList!=null  &&! partnerProgramList.isEmpty())
            for (PartnerProgram partnerProgram : partnerProgramList) {
               LookupDepartmentProgram lookup = partnerProgram.getLookupDepartmentProgram();
               List<PartnerPermission> permissions = new ArrayList<PartnerPermission>();
               if (lookup != null) {
                     PartnerPermission pp1 = new PartnerPermission();
                     PartnerPermissionsCategory pc1 = partnerPermissionCategoryRepository.findOne(CCIConstants.Admin);
                     pp1.setLookupDepartmentProgram(lookup);
                     pp1.setPartnerPermissionsCategory(pc1);
                     pp1.setPartnerUser(pUser);
                     permissions.add(pp1);
                  
                     PartnerPermission pp2 = new PartnerPermission();
                     PartnerPermissionsCategory pc2= partnerPermissionCategoryRepository.findOne(CCIConstants.AccountingInsurance);
                     pp2.setLookupDepartmentProgram(lookup);
                     pp2.setPartnerPermissionsCategory(pc2);
                     pp2.setPartnerUser(pUser);
                     permissions.add(pp2);
                
                     PartnerPermission pp3 = new PartnerPermission();
                     PartnerPermissionsCategory pc3 = partnerPermissionCategoryRepository.findOne(CCIConstants.Applications);
                     pp3.setLookupDepartmentProgram(lookup);
                     pp3.setPartnerPermissionsCategory(pc3);
                     pp3.setPartnerUser(pUser);
                     permissions.add(pp3);
                  
                     PartnerPermission pp4 = new PartnerPermission();
                     PartnerPermissionsCategory pc4 = partnerPermissionCategoryRepository.findOne(CCIConstants.Contracting);
                     pp4.setLookupDepartmentProgram(lookup);
                     pp4.setPartnerPermissionsCategory(pc4);
                     pp4.setPartnerUser(pUser);
                     permissions.add(pp4);
                 
                     PartnerPermission pp5 = new PartnerPermission();
                     PartnerPermissionsCategory pc5 = partnerPermissionCategoryRepository.findOne(CCIConstants.Flights);
                     pp5.setLookupDepartmentProgram(lookup);
                     pp5.setPartnerPermissionsCategory(pc5);
                     pp5.setPartnerUser(pUser);
                     permissions.add(pp5);
                 
                     PartnerPermission pp6 = new PartnerPermission();
                     PartnerPermissionsCategory pc6 = partnerPermissionCategoryRepository.findOne(CCIConstants.Insurance);
                     pp6.setLookupDepartmentProgram(lookup);
                     pp6.setPartnerPermissionsCategory(pc6);
                     pp6.setPartnerUser(pUser);
                     permissions.add(pp6);
                 
                     PartnerPermission pp7 = new PartnerPermission();
                     PartnerPermissionsCategory pc7 = partnerPermissionCategoryRepository.findOne(CCIConstants.Monitoring);
                     pp7.setLookupDepartmentProgram(lookup);
                     pp7.setPartnerPermissionsCategory(pc7);
                     pp7.setPartnerUser(pUser);
                     permissions.add(pp7);
                 
                     PartnerPermission pp8 = new PartnerPermission();
                     PartnerPermissionsCategory pc8 = partnerPermissionCategoryRepository.findOne(CCIConstants.PlacementInfo);
                     pp8.setLookupDepartmentProgram(lookup);
                     pp8.setPartnerPermissionsCategory(pc8);
                     pp8.setPartnerUser(pUser);
                     permissions.add(pp8);
                  
                     PartnerPermission pp9 = new PartnerPermission();
                     PartnerPermissionsCategory pc9 = partnerPermissionCategoryRepository.findOne(CCIConstants.StudentPreProgram);
                     pp9.setLookupDepartmentProgram(lookup);
                     pp9.setPartnerPermissionsCategory(pc9);
                     pp9.setPartnerUser(pUser);
                     permissions.add(pp9);
                  
                  partnerPermissionRepository.save(permissions);
                  partnerPermissionRepository.flush();
               }
            }
         
            PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findApplicationStatusByGoId(Integer.valueOf(partnerGoId));
            if (Integer.valueOf(loginVal).equals(CCIConstants.SEND_LOGIN)) {
               sendLogin(String.valueOf(newPartner.getPartnerGoId()), request);
               partnerReviewStatus.setPartnerStatus1(partnerStatusRepository.findOne(CCIConstants.VALID));
               //BUG 1399
               partnerReviewStatus.setPartnerStatus2(partnerStatusRepository.findOne(CCIConstants.PENDING_STATUS));
            } else {
               partnerReviewStatus.setPartnerStatus1(partnerStatusRepository.findOne(CCIConstants.VALID));
               //Bug 1399
               partnerReviewStatus.setPartnerStatus2(partnerStatusRepository.findOne(CCIConstants.PENDING_STATUS));
            }
            partnerReviewStatusRepository.saveAndFlush(partnerReviewStatus);
            resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE, messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_SENDING_LOGIN_TO_PARTNER_LEAD.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return resp;
   }

   @Override
   @Transactional(readOnly = true)
   public PartnerLeadStatus getLeadPartnerStatuses() {
      PartnerLeadStatus pPeadStatuses = new PartnerLeadStatus();
      try {
         List<PartnerStatus> statuses = partnerStatusRepository.getPartnerLeadStatuses();
         if (statuses != null) {
            List<LeadStatus> leadStatuses = new ArrayList<LeadStatus>();
            for (PartnerStatus ps : statuses) {
               LeadStatus ls = new LeadStatus();
               ls.setStatusId(ps.getPartnerStatusId());
               ls.setStatus(ps.getPartnerStatusName());
               leadStatuses.add(ls);
            }
            pPeadStatuses.getLeadStatuses().addAll(leadStatuses);
            pPeadStatuses.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            pPeadStatuses.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         pPeadStatuses.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_GET_LEAD_STATUS_LIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return pPeadStatuses;
   }

}
