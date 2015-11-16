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
import com.ccighgo.service.components.errormessages.constants.RegionManagementMessageConstants;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.admin.add.partner.AdminAddPartner;
import com.ccighgo.service.transport.partner.beans.admin.add.partner.ProgramContacts;
import com.ccighgo.service.transport.partner.beans.partner.user.details.UserOffice;
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
         login.setActive(CCIConstants.ACTIVE);
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