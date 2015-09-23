/**
 * 
 */
package com.ccighgo.service.components.partner.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerOffice;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.db.entities.Salutation;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerCompanyDetailsMessageConstants;
import com.ccighgo.service.components.errormessages.constants.PartnerSeasonMessageConstants;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerAddress;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerAddressCountry;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerAddressState;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerCompanyDetail;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerCompanyDetails;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerCompanyStatus;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerMailingAddress;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerPhysicalAddress;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerPrimaryContact;
import com.ccighgo.service.transport.partner.beans.companydetail.PrimaryContactSalutation;
import com.ccighgo.utils.CCIConstants;

/**
 * @author ravi
 *
 */
@Component
public class PartnerCompanyServiceImpl implements PartnerCompanyService {

   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerCompanyServiceImpl.class);

   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;
   @Autowired PartnerRepository partnerRepository;

   @Override
   public PartnerCompanyDetail getPartnerCompanyDetails(String partnerGoId) {
      PartnerCompanyDetail partnerCompanyDetail = new PartnerCompanyDetail();
      if (partnerGoId == null) {
         partnerCompanyDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID));
         return partnerCompanyDetail;
      }
      try {
         Partner partner = partnerRepository.findOne(Integer.valueOf(partnerGoId));
         partnerCompanyDetail.setPartnerGoId(partner.getPartnerGoId());
         partnerCompanyDetail.setPartnerCompanyNameHeader(partner.getCompanyName());

         PartnerCompanyStatus partnerCompanyStatus = new PartnerCompanyStatus();
         partnerCompanyStatus.setPartnerCompanyStatuId(partner.getPartnerStatus().getPartnerStatusId());
         partnerCompanyStatus.setPartnerCompanyStatus(partner.getPartnerStatus().getPartnerStatusName());
         partnerCompanyDetail.setPartnerCompanyStatus(partnerCompanyStatus);

         PartnerCompanyDetails partnerCompanyDetails = new PartnerCompanyDetails();
         partnerCompanyDetails.setPartnerCompanyLogoUrl("TODO:field not available now"/* partner.getPartnerLogo() */);
         partnerCompanyDetails.setPartnerCompanyName(partner.getCompanyName());
         partnerCompanyDetails.setPartnerCompanyAcronym(partner.getAcronym() != null ? partner.getAcronym() : null);
         partnerCompanyDetails.setCCIAccountingDesignation(partner.getQuickbooksCode());
         partnerCompanyDetails.setDAndBNumber(partner.getDandBNumber());
         partnerCompanyDetails.setPersonToSignContract(partner.getContractSigner());
         partnerCompanyDetails.setSubscribeCCINewsletter(partner.getSubscribeToCCINewsletter() == CCIConstants.ACTIVE ? true : false);
         partnerCompanyDetails.setRecieveHSPNotificationEmails(partner.getReceiveAYPMails() == CCIConstants.ACTIVE ? true : false);
         partnerCompanyDetails.setGeneralEmail(partner.getEmail());
         Login partnerLogin = null;
         for (Login login : partner.getGoIdSequence().getLogin()) {
            if (partner.getPartnerGoId() == login.getGoIdSequence().getGoId()) {
               partnerLogin=login;
               break;
            }
         }
         if(partnerLogin!=null){
            partnerCompanyDetails.setUserName(partnerLogin.getLoginName());
         }
         partnerCompanyDetail.setPartnerCompanyDetails(partnerCompanyDetails);
         PartnerPrimaryContact partnerPrimaryContact = new PartnerPrimaryContact();

         Salutation salutation = null;
         PartnerUser partnerUser = null;
         PartnerOffice partnerOffice = null;
         for (PartnerUser puser : partner.getPartnerUsers()) {
            if (partner.getPartnerGoId() == puser.getPartner().getPartnerGoId()) {
               salutation = puser.getSalutation();
               partnerUser = puser;
               break;
            }
         }
         for (PartnerOffice po : partner.getPartnerOffices()) {
            if (partner.getPartnerGoId() == po.getPartner().getPartnerGoId()) {
               partnerOffice = po;
               break;
            }
         }
         if (salutation != null) {
            PrimaryContactSalutation primaryContactSalutation = new PrimaryContactSalutation();
            primaryContactSalutation.setSalutationId(salutation.getSalutationId());
            primaryContactSalutation.setSalutation(salutation.getSalutationName());
            partnerPrimaryContact.setPrimaryContactSalutation(primaryContactSalutation);
         }
         if (partnerUser != null) {
            partnerPrimaryContact.setPrimaryContactTitle(partnerUser.getTitle());
            partnerPrimaryContact.setPrimaryContactFirstName(partnerUser.getFirstName());
            partnerPrimaryContact.setPrimaryContactLastName(partnerUser.getLastName() != null ? partnerUser.getLastName() : null);
            partnerPrimaryContact.setPrimaryContactEmail(partnerUser.getEmail() != null ? partnerUser.getEmail() : null);
            partnerPrimaryContact.setPrimaryContactPhone(partnerUser.getPhone() != null ? partnerUser.getPhone() : null);
            partnerPrimaryContact.setPrimaryContactEmergencyPhone(partnerUser.getEmergencyPhone() != null ? partnerUser.getEmergencyPhone() : null);
            partnerPrimaryContact.setPrimaryContactFax(partnerUser.getFax() != null ? partnerUser.getFax() : null);
            partnerPrimaryContact.setPrimaryContactShouldRecieveCCINotification(false/* "TODO:field not available now" */);
            partnerPrimaryContact.setPrimaryContactSkypeId(partnerUser.getSkypeId() != null ? partnerUser.getSkypeId() : null);
            partnerPrimaryContact.setPrimaryContactWebsite(partnerOffice.getWebsite() != null ? partnerOffice.getWebsite() : null);
            partnerCompanyDetail.setPartnerPrimaryContact(partnerPrimaryContact);
         }
         PartnerAddress physicalAddress = new PartnerAddress();
         
         PartnerAddressState physicalAddressState = new PartnerAddressState();
         PartnerAddressCountry physicalAddressCountry = new PartnerAddressCountry();
         physicalAddress.setAddressLineOne(partner.getPhysicalAddressLineOne()!=null?partner.getPhysicalAddressLineOne():null);
         physicalAddress.setAddressLineTwo(partner.getPhysicalAddressLineTwo()!=null?partner.getPhysicalAddressLineTwo():null);
         physicalAddress.setCity(partner.getPhysicalCity()!=null?partner.getPhysicalCity():null);
         physicalAddress.setZipCode(partner.getPhysicalZipcode()!=null?partner.getPhysicalZipcode():null);
         physicalAddress.setPartnerAddressState(physicalAddressState);
         physicalAddress.setPartnerAddressCountry(physicalAddressCountry);
         
         PartnerAddress mailingAddress = new PartnerAddress();
         PartnerAddressState mailingAddressState = new PartnerAddressState();
         PartnerAddressCountry mailingAddressCountry = new PartnerAddressCountry();
         mailingAddress.setAddressLineOne(partner.getAddressLineOne()!=null?partner.getAddressLineOne():null);
         mailingAddress.setAddressLineTwo(partner.getAddressLineTwo()!=null?partner.getAddressLineTwo():null);
         mailingAddress.setCity(partner.getCity()!=null?partner.getCity():null);
         mailingAddress.setZipCode(partner.getZipcode()!=null?partner.getZipcode():null);
         mailingAddress.setPartnerAddressState(mailingAddressState);
         mailingAddress.setPartnerAddressCountry(mailingAddressCountry);
         PartnerMailingAddress partnerMailingAddress = new PartnerMailingAddress();
         partnerMailingAddress.setPartnerMailingAddress(mailingAddress);
         
         PartnerPhysicalAddress partnerPhysicalAddress = new PartnerPhysicalAddress();
         partnerPhysicalAddress.setPartnerPhysicalAddress(physicalAddress);
         partnerCompanyDetail.setPartnerPhysicalAddress(partnerPhysicalAddress);
         if(partner.getMailingAddressIsSameAsPhysicalAdress()==CCIConstants.ACTIVE){
            partnerCompanyDetail.setPartnerMailingAddressSame(true);
            partnerCompanyDetail.setPartnerMailingAddress(null);
         }
         else{
            partnerCompanyDetail.setPartnerMailingAddress(partnerMailingAddress);
         }
         partnerCompanyDetail.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         partnerCompanyDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_COMPANY_DETAIL.getValue(),
               messageUtil.getMessage(PartnerCompanyDetailsMessageConstants.ERROR_GET_PARTNER_COMPANY_DETAIL)));
         LOGGER.error(messageUtil.getMessage(PartnerCompanyDetailsMessageConstants.ERROR_GET_PARTNER_COMPANY_DETAIL), e);
      }
      return partnerCompanyDetail;
   }

}
