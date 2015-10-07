/**
 * 
 */
package com.ccighgo.service.components.partner.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerContact;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.PartnerContactRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.SalutationRepository;
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
   @Autowired LoginRepository loginRepository;
   @Autowired CountryRepository countryRepository;
   @Autowired PartnerContactRepository partnerContactRepository;
   @Autowired SalutationRepository salutationRepository;

   @Override
   @Transactional(readOnly = true)
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
         partnerCompanyDetails.setPartnerCompanyLogoUrl(partner.getPartnerLogo());
         partnerCompanyDetails.setPartnerCompanyName(partner.getCompanyName());
         partnerCompanyDetails.setPartnerCompanyAcronym(partner.getAcronym());
         partnerCompanyDetails.setCCIAccountingDesignation(partner.getQuickbooksCode());
         partnerCompanyDetails.setDAndBNumber(partner.getDandBNumber());
         partnerCompanyDetails.setPersonToSignContract(partner.getContractSigner());
         partnerCompanyDetails.setSubscribeCCINewsletter(partner.getSubscribeToCCINewsletter() == CCIConstants.ACTIVE ? true : false);
         partnerCompanyDetails.setRecieveHSPNotificationEmails(partner.getReceiveAYPMails() == CCIConstants.ACTIVE ? true : false);
         partnerCompanyDetails.setGeneralEmail(partner.getEmail());
         Login partnerLogin = null;
         for (Login login : partner.getGoIdSequence().getLogins()) {
            for (PartnerUser partUser : login.getPartnerUsers()) {
               if (partUser.getIsPrimary() == CCIConstants.ACTIVE) {
                  partnerLogin = login;
                  break;
               }
            }
         }
         if (partnerLogin != null) {
            partnerCompanyDetails.setUserName(partnerLogin.getLoginName());
         }
         partnerCompanyDetail.setPartnerCompanyDetails(partnerCompanyDetails);
         PartnerPrimaryContact partnerPrimaryContact = new PartnerPrimaryContact();
         PartnerContact partnerContact = null;
         for (PartnerContact contact : partner.getPartnerContacts()) {
            if (partner.getPartnerGoId() == contact.getPartner().getPartnerGoId()) {
               partnerContact = contact;
               break;
            }
         }
         if (partnerContact != null) {
            PrimaryContactSalutation primaryContactSalutation = new PrimaryContactSalutation();
            primaryContactSalutation.setSalutationId(partnerContact.getSalutationBean().getSalutationId());
            primaryContactSalutation.setSalutation(partnerContact.getSalutationBean().getSalutationName());
            partnerPrimaryContact.setPrimaryContactSalutation(primaryContactSalutation);
            partnerPrimaryContact.setPrimaryContactTitle(partnerContact.getTitle());
            partnerPrimaryContact.setPrimaryContactFirstName(partnerContact.getFirstName());
            partnerPrimaryContact.setPrimaryContactLastName(partnerContact.getLastName());
            partnerPrimaryContact.setPrimaryContactEmail(partnerContact.getEmail());
            partnerPrimaryContact.setPrimaryContactPhone(partnerContact.getPhone());
            partnerPrimaryContact.setPrimaryContactEmergencyPhone(partnerContact.getEmergencyPhone());
            partnerPrimaryContact.setPrimaryContactFax(partnerContact.getFax());
            if (partnerContact.getReceiveNotificationEmails() != null) {
               partnerPrimaryContact.setPrimaryContactShouldRecieveCCINotification(partnerContact.getReceiveNotificationEmails() == CCIConstants.ACTIVE ? true : false);
            }
            partnerPrimaryContact.setPrimaryContactSkypeId(partnerContact.getSkypeId());
            partnerPrimaryContact.setPrimaryContactWebsite(partnerContact.getWebsite());
            partnerCompanyDetail.setPartnerPrimaryContact(partnerPrimaryContact);
         }
         PartnerAddress physicalAddress = new PartnerAddress();

         PartnerAddressState physicalAddressState = new PartnerAddressState();
         physicalAddressState.setPartnerAddressStateName(partner.getPhysicalstate());
         PartnerAddressCountry physicalAddressCountry = new PartnerAddressCountry();
         physicalAddressCountry.setPartnerAddressCountryId(partner.getLookupCountry2().getCountryId());
         physicalAddressCountry.setPartnerAddressCountryISOCode(partner.getLookupCountry2().getCountryCode());
         physicalAddressCountry.setPartnerAddressCountryName(partner.getLookupCountry2().getCountryName());
         physicalAddress.setAddressLineOne(partner.getPhysicalAddressLineOne());
         physicalAddress.setAddressLineTwo(partner.getPhysicalAddressLineTwo());
         physicalAddress.setCity(partner.getPhysicalCity());
         physicalAddress.setZipCode(partner.getPhysicalZipcode());
         physicalAddress.setPartnerAddressState(physicalAddressState);
         physicalAddress.setPartnerAddressCountry(physicalAddressCountry);

         PartnerAddress mailingAddress = new PartnerAddress();
         PartnerAddressState mailingAddressState = new PartnerAddressState();
         mailingAddressState.setPartnerAddressStateName(partner.getState());
         PartnerAddressCountry mailingAddressCountry = new PartnerAddressCountry();
         mailingAddressCountry.setPartnerAddressCountryId(partner.getLookupCountry1().getCountryId());
         mailingAddressCountry.setPartnerAddressCountryISOCode(partner.getLookupCountry1().getCountryCode());
         mailingAddressCountry.setPartnerAddressCountryName(partner.getLookupCountry1().getCountryName());
         mailingAddress.setAddressLineOne(partner.getAddressLineOne());
         mailingAddress.setAddressLineTwo(partner.getAddressLineTwo());
         mailingAddress.setCity(partner.getCity());
         mailingAddress.setZipCode(partner.getZipcode());
         mailingAddress.setPartnerAddressState(mailingAddressState);
         mailingAddress.setPartnerAddressCountry(mailingAddressCountry);
         PartnerMailingAddress partnerMailingAddress = new PartnerMailingAddress();
         partnerMailingAddress.setPartnerMailingAddress(mailingAddress);

         PartnerPhysicalAddress partnerPhysicalAddress = new PartnerPhysicalAddress();
         partnerPhysicalAddress.setPartnerPhysicalAddress(physicalAddress);
         partnerCompanyDetail.setPartnerPhysicalAddress(partnerPhysicalAddress);
         if (partner.getMailingAddressIsSameAsPhysicalAdress() == CCIConstants.ACTIVE) {
            partnerCompanyDetail.setPartnerMailingAddressSame(true);
         } else {
            partnerCompanyDetail.setPartnerMailingAddressSame(false);
         }
         partnerCompanyDetail.setPartnerMailingAddress(partnerMailingAddress);
         partnerCompanyDetail.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         partnerCompanyDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_COMPANY_DETAIL.getValue(),
               messageUtil.getMessage(PartnerCompanyDetailsMessageConstants.ERROR_GET_PARTNER_COMPANY_DETAIL)));
         LOGGER.error(messageUtil.getMessage(PartnerCompanyDetailsMessageConstants.ERROR_GET_PARTNER_COMPANY_DETAIL), e);
      }
      return partnerCompanyDetail;
   }

   @Override
   @Transactional
   public PartnerCompanyDetail updatePartnerCompanyDetails(PartnerCompanyDetail partnerCompanyDetail) {
      PartnerCompanyDetail updatedObject = new PartnerCompanyDetail();
      if (partnerCompanyDetail.getPartnerGoId() == 0 || partnerCompanyDetail.getPartnerGoId() < 0) {
         partnerCompanyDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID));
         return partnerCompanyDetail;
      }
      try {
         Partner partner = partnerRepository.findOne(partnerCompanyDetail.getPartnerGoId());
         if (partner != null) {
            PartnerContact partnerContact = null;
            Login partnerLogin = null;
            for (Login login : partner.getGoIdSequence().getLogins()) {
               for (PartnerUser partUser : login.getPartnerUsers()) {
                  if (partUser.getIsPrimary() == CCIConstants.ACTIVE) {
                     partnerLogin = login;
                     break;
                  }
               }
            }
            for (PartnerContact contact : partner.getPartnerContacts()) {
               if (partner.getPartnerGoId().equals(contact.getPartner().getPartnerGoId())) {
                  partnerContact = contact;
                  break;
               }
            }
            // find out if login name is changed.
            // if login name changed and doesn't exists then update login name or else return error
            if (!(partnerLogin.getLoginName().equals(partnerCompanyDetail.getPartnerCompanyDetails().getUserName()))) {
               Login checkExists = loginRepository.findByLoginName(partnerCompanyDetail.getPartnerCompanyDetails().getUserName());
               if (checkExists == null) {
                  partnerLogin.setLoginName(partnerCompanyDetail.getPartnerCompanyDetails().getUserName());
                  partnerLogin.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                  loginRepository.saveAndFlush(partnerLogin);
               } else {
                  updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DUPLICATE_LOGINNAME.getValue(),
                        messageUtil.getMessage(PartnerCompanyDetailsMessageConstants.ERROR_DUPLICATE_LOGIN_NAME)));
                  LOGGER.error(messageUtil.getMessage(PartnerCompanyDetailsMessageConstants.ERROR_DUPLICATE_LOGIN_NAME));
                  return updatedObject;
               }
            }else{
               partnerContact.setSalutationBean(salutationRepository.findOne(partnerCompanyDetail.getPartnerPrimaryContact().getPrimaryContactSalutation().getSalutationId()));
               partnerContact.setTitle(partnerCompanyDetail.getPartnerPrimaryContact().getPrimaryContactTitle());
               partnerContact.setFirstName(partnerCompanyDetail.getPartnerPrimaryContact().getPrimaryContactFirstName());
               partnerContact.setLastName(partnerCompanyDetail.getPartnerPrimaryContact().getPrimaryContactLastName());
               partnerContact.setEmail(partnerCompanyDetail.getPartnerPrimaryContact().getPrimaryContactEmail());
               partnerContact.setPhone(partnerCompanyDetail.getPartnerPrimaryContact().getPrimaryContactPhone());
               partnerContact.setEmergencyPhone(partnerCompanyDetail.getPartnerPrimaryContact().getPrimaryContactEmergencyPhone());
               partnerContact.setFax(partnerCompanyDetail.getPartnerPrimaryContact().getPrimaryContactFax());
               partnerContact.setReceiveNotificationEmails(partnerCompanyDetail.getPartnerPrimaryContact().isPrimaryContactShouldRecieveCCINotification() ? CCIConstants.ACTIVE
                     : CCIConstants.INACTIVE);
               partnerContact.setSkypeId(partnerCompanyDetail.getPartnerPrimaryContact().getPrimaryContactSkypeId());
               partnerContact.setWebsite(partnerCompanyDetail.getPartnerPrimaryContact().getPrimaryContactWebsite());
               partnerContactRepository.saveAndFlush(partnerContact);
               partner.setPartnerLogo(partnerCompanyDetail.getPartnerCompanyDetails().getPartnerCompanyLogoUrl());
               partner.setCompanyName(partnerCompanyDetail.getPartnerCompanyDetails().getPartnerCompanyName());
               partner.setAcronym(partnerCompanyDetail.getPartnerCompanyDetails().getPartnerCompanyAcronym());
               partner.setQuickbooksCode(partnerCompanyDetail.getPartnerCompanyDetails().getCCIAccountingDesignation());
               partner.setDandBNumber(partnerCompanyDetail.getPartnerCompanyDetails().getDAndBNumber());
               partner.setContractSigner(partnerCompanyDetail.getPartnerCompanyDetails().getPersonToSignContract());
               partner.setSubscribeToCCINewsletter(partnerCompanyDetail.getPartnerCompanyDetails().isSubscribeCCINewsletter() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               partner.setReceiveAYPMails(partnerCompanyDetail.getPartnerCompanyDetails().isRecieveHSPNotificationEmails() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               partner.setEmail(partnerCompanyDetail.getPartnerCompanyDetails().getGeneralEmail());

               // physical address
               partner.setPhysicalAddressLineOne(partnerCompanyDetail.getPartnerPhysicalAddress().getPartnerPhysicalAddress().getAddressLineOne());
               partner.setPhysicalAddressLineTwo(partnerCompanyDetail.getPartnerPhysicalAddress().getPartnerPhysicalAddress().getAddressLineTwo());
               partner.setPhysicalCity(partnerCompanyDetail.getPartnerPhysicalAddress().getPartnerPhysicalAddress().getCity());
               partner.setPhysicalZipcode(partnerCompanyDetail.getPartnerPhysicalAddress().getPartnerPhysicalAddress().getZipCode());
               partner.setPhysicalstate(partnerCompanyDetail.getPartnerPhysicalAddress().getPartnerPhysicalAddress().getPartnerAddressState().getPartnerAddressStateName());
               partner.setLookupCountry2(countryRepository.findOne(partnerCompanyDetail.getPartnerPhysicalAddress().getPartnerPhysicalAddress().getPartnerAddressCountry()
                     .getPartnerAddressCountryId()));
               // mailing address
               partner.setMailingAddressIsSameAsPhysicalAdress(partnerCompanyDetail.isPartnerMailingAddressSame() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               partner.setAddressLineOne(partnerCompanyDetail.getPartnerMailingAddress().getPartnerMailingAddress().getAddressLineOne());
               partner.setAddressLineTwo(partnerCompanyDetail.getPartnerMailingAddress().getPartnerMailingAddress().getAddressLineTwo());
               partner.setCity(partnerCompanyDetail.getPartnerMailingAddress().getPartnerMailingAddress().getCity());
               partner.setZipcode(partnerCompanyDetail.getPartnerMailingAddress().getPartnerMailingAddress().getZipCode());
               partner.setState(partnerCompanyDetail.getPartnerMailingAddress().getPartnerMailingAddress().getPartnerAddressState().getPartnerAddressStateName());
               partner.setLookupCountry1(countryRepository.findOne(partnerCompanyDetail.getPartnerMailingAddress().getPartnerMailingAddress().getPartnerAddressCountry()
                     .getPartnerAddressCountryId()));
               partnerRepository.saveAndFlush(partner);
               updatedObject = getPartnerCompanyDetails(String.valueOf(partner.getPartnerGoId()));
            }
         } else {
            updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_COMPANY_DETAIL.getValue(),
               messageUtil.getMessage(PartnerCompanyDetailsMessageConstants.ERROR_GET_PARTNER_COMPANY_DETAIL)));
         LOGGER.error(messageUtil.getMessage(PartnerCompanyDetailsMessageConstants.ERROR_GET_PARTNER_COMPANY_DETAIL), e);
      }
      return updatedObject;
   }

}
