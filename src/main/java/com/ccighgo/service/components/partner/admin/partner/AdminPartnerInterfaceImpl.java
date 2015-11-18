/**
 * 
 */
package com.ccighgo.service.components.partner.admin.partner;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.GoIdSequence;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerProgram;
import com.ccighgo.db.entities.PartnerReviewStatus;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.CCIStaffUsersRepository;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LoginUserTypeRepository;
import com.ccighgo.jpa.repositories.LookupDepartmentProgramRepository;
import com.ccighgo.jpa.repositories.PartnerProgramRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerUserRepository;
import com.ccighgo.jpa.repositories.SalutationRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.component.emailing.EmailServiceImpl;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminSeasonConstants;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.admin.add.partner.AdminAddPartner;
import com.ccighgo.service.transport.partner.beans.admin.add.partner.ProgramContacts;
import com.ccighgo.service.transport.partner.beans.admin.added.partner.AddedPartner;
import com.ccighgo.service.transport.partner.beans.admin.added.partner.AddedPartners;
import com.ccighgo.service.transport.partner.beans.admin.added.partner.PartnerCountry;
import com.ccighgo.service.transport.partner.beans.admin.added.partner.PartnerSeasons;
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

   @Override
   @Transactional
   public Response addPartner(AdminAddPartner partner, HttpServletRequest request) {
      Response resp = new Response();
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
         goIdSequence = goIdSequenceRepository.save(goIdSequence);
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
         loginUserType.setCreatedBy(login.getLoginId());
         loginUserType.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
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
         newPartner.setLookupCountry1(countryRepository.findOne(partner.getPartnerCountry().getCountryId()));
         newPartner.setLookupCountry2(countryRepository.findOne(partner.getPartnerCountry().getCountryId()));
         newPartner.setCreatedBy(partner.getLoginId());
         newPartner.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         newPartner.setModifiedBy(partner.getLoginId());
         newPartner.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         newPartner = partnerRepository.saveAndFlush(newPartner);

         PartnerUser pUser = new PartnerUser();
         pUser.setPartner(newPartner);
         pUser.setLogin(login);
         pUser.setSalutation(salutationRepositotry.findOne(partner.getSalutation().getSalutationId()));
         pUser.setFirstName(partner.getFirstName());
         pUser.setLastName(partner.getLastName());
         pUser.setEmail(partner.getEmail());
         pUser.setActive(CCIConstants.ACTIVE);
         pUser = partnerUserRepository.saveAndFlush(pUser);

         if (partner.getProgramContacts() != null) {
            List<PartnerProgram> partnerProgramList = new ArrayList<PartnerProgram>();
            for (ProgramContacts pCon : partner.getProgramContacts()) {
               PartnerProgram pp = new PartnerProgram();
               pp.setCcistaffUser(cciStaffUsersRepository.findOne(pCon.getCciUserId()));
               pp.setLookupDepartmentProgram(departmentProgramRepository.findOne(pCon.getDepartmentProgramId()));
               pp.setPartner(newPartner);
               pp.setHasApplied(CCIConstants.ACTIVE);
               pp.setIsEligible(CCIConstants.ACTIVE);
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
         }
         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SUP_REG_LIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return resp;
   }

   @Override
   @Transactional(readOnly=true)
   public AddedPartners getAddedPartnerList() {
      AddedPartners addedPartners = new AddedPartners();
      try {
         List<Partner> partnerList = partnerRepository.findAll();
         if (partnerList == null) {
            throw new CcighgoException("No Partners found.");
         }
         addedPartners.setCount(partnerList.size());
         List<AddedPartner> addedPartnersList = new ArrayList<AddedPartner>();
         for (Partner p : partnerList) {
            AddedPartner ap = new AddedPartner();
            PartnerUser puser = null;
            List<PartnerUser> partnerUserList = p.getPartnerUsers();
            if (partnerUserList != null) {
               for (PartnerUser pu : partnerUserList) {
                  if (pu.getIsPrimary() == CCIConstants.ACTIVE) {
                     puser = pu;
                     break;
                  }
               }
            }
            ap.setCompanyName(p.getCompanyName());
            ap.setType(p.getIsSubPartner() == CCIConstants.ACTIVE ? "Sub Partner" : "Partner");
            ap.setFirstName(puser.getFirstName());
            ap.setLastName(puser.getLastName());
            ap.setEmail(puser.getEmail());
            PartnerCountry pCountry = new PartnerCountry();
            pCountry.setCountryId(p.getLookupCountry1().getCountryId());
            pCountry.setCountryCode(p.getLookupCountry1().getCountryCode());
            pCountry.setCountryName(p.getLookupCountry1().getCountryName());
            pCountry.setCountryFlagUrl(p.getLookupCountry1().getCountryFlag());
            ap.setPartnerCountry(pCountry);
            ap.setActive(puser.getLogin().getActive() == CCIConstants.ACTIVE ? 1 : 0);
            ap.setPartnerLoginId(puser.getLogin().getLoginId());

            // partner status
            String status = null;
            List<PartnerReviewStatus> partnerReviewStatuses = p.getPartnerReviewStatuses();
            if (partnerReviewStatuses != null && !partnerReviewStatuses.isEmpty()) {
               status = partnerReviewStatuses.get(0).getPartnerStatus1().getPartnerStatusName();
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
            addedPartners.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (CcighgoException e) {
         addedPartners.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SUP_REG_LIST.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return addedPartners;
   }

   @Override
   @Transactional
   public Response toggleActiveStatus(String statusVal,String loggedinUserLoginId, String partnerLoginId) {
      Response resp = new Response();
     try{
        if(!(Integer.valueOf(statusVal)==0) || !(Integer.valueOf(statusVal)==1)){
           throw new CcighgoException("invalid status value, can be either 0 or 1");
        }
        if(loggedinUserLoginId ==null || Integer.valueOf(loggedinUserLoginId)==0 || Integer.valueOf(loggedinUserLoginId)<0){
           throw new CcighgoException("logged in user info is required to update the record");
        }
        if(partnerLoginId ==null || Integer.valueOf(partnerLoginId)==0 || Integer.valueOf(partnerLoginId)<0){
           throw new CcighgoException("login info of partner is required update the record");
        }
        Login partnerLogin = loginRepository.findOne(Integer.valueOf(partnerLoginId));
        if(partnerLogin==null){
           throw new CcighgoException("no record found to update status with the login info provided, please check request url");
        }
        Byte activeStatus = null;
        if(Integer.valueOf(statusVal)==1){
           activeStatus = CCIConstants.ACTIVE;
        }
        if(Integer.valueOf(statusVal)==0){
           activeStatus = CCIConstants.INACTIVE;
        }
        partnerLogin.setActive(activeStatus);
        loginRepository.saveAndFlush(partnerLogin);
        resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
              messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
     }catch (CcighgoException e) {
        resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SUP_REG_LIST.getValue(), e.getMessage()));
        LOGGER.error(e.getMessage());
     }
      return resp;
   }

   @Override
   public Response sendLogin(String partnerGoId, HttpServletRequest request) {
      Response response = new Response();
         try{
            if(partnerGoId ==null || Integer.valueOf(partnerGoId)==0 || Integer.valueOf(partnerGoId)<0){
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
            if(partnerUser!=null && partnerUser.getLogin()!=null){
                  String body = "<p>Ciao! </p>" 
                        + "<p>This email was sent automatically by Greenheart Online (GO) in response to your request for a new password. </p>" 
                        + "<p>"+ "Your username is : " + partnerUser.getLogin().getLoginName() + "</p>" 
                        + "<p>Please click on the link below to create a new password:</p> " 
                        + "<p>"+ formResetURL(request).concat(partnerUser.getLogin().getKeyValue()) + "</p>"
                        + "<p>If you didn't request a new password, please let us know.</p>"
                        + "<p>Thank you,</p>"
                        + "<p>CCI Greenheart.</p>";
                  email.send(partnerUser.getLogin().getEmail(), CCIConstants.RESET_PASSWORD_SUBJECT, body, true);
                  response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
                        "An email has been sent to address "+"\'"+partnerUser.getLogin().getEmail()+ "\'"+" for login name "+"\'"+partnerUser.getLogin().getLoginName()+"\'"+" with instructions to reset password"));
            }else{
               response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                     messageUtil.getMessage(CCIConstants.NO_RECORD)));
               LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
            }
         }catch (CcighgoException e) {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  e.getMessage()));
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


}
