/**
 * 
 */
package com.ccighgo.service.components.partner.company;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
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
import com.ccighgo.jpa.repositories.PartnerOfficeRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeTypeRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerUserRepository;
import com.ccighgo.jpa.repositories.SalutationRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerCompanyDetailsMessageConstants;
import com.ccighgo.service.components.errormessages.constants.PartnerSeasonMessageConstants;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.add.partner.office.NewPartnerOffice;
import com.ccighgo.service.transport.partner.beans.companydetail.OfficeAddressCountry;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerAddress;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerAddressCountry;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerAddressState;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerCompanyDetail;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerCompanyDetails;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerCompanyStatus;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerMailingAddress;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerOffice;
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
   @Autowired PartnerOfficeRepository partnerOfficeRepository;
   @Autowired PartnerOfficeTypeRepository partnerOfficeTypeRepository;
   @Autowired SalutationRepository salutationRepository;
   @Autowired PartnerUserRepository partnerUserRepository;

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
         /*
          * partnerCompanyStatus.setPartnerCompanyStatuId(partner.getPartnerReviewStatus().getPartnerStatus2().
          * getPartnerStatusId());
          * partnerCompanyStatus.setPartnerCompanyStatus(partner.getPartnerReviewStatus().getPartnerStatus2
          * ().getPartnerStatusName());
          */
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

         // Partner Offices
         List<PartnerOffice> partnerOfficeList = null;
         if (partner.getPartnerOffices() != null && partner.getPartnerOffices().size() > 0) {
            partnerOfficeList = new ArrayList<PartnerOffice>();
            for (com.ccighgo.db.entities.PartnerOffice pOffice : partner.getPartnerOffices()) {
               PartnerOffice partOffice = new PartnerOffice();
               partOffice.setPartnerOfficeId(pOffice.getPartnerOfficeId());
               partOffice.setOfficeAddressLineOne(pOffice.getAdressOne());
               partOffice.setOfficeAddressLineTwo(pOffice.getAdressTwo());
               partOffice.setCity(pOffice.getCity());
               partOffice.setZipCode(pOffice.getPostalCode());
               OfficeAddressCountry officeAddressCountry = new OfficeAddressCountry();
               officeAddressCountry.setOfficeAddressCountryId(pOffice.getLookupCountry().getCountryId());
               officeAddressCountry.setOfficeAddressCountryISOCode(pOffice.getLookupCountry().getCountryCode());
               officeAddressCountry.setOfficeAddressCountryName(pOffice.getLookupCountry().getCountryName());
               partOffice.setOfficeAddressCountry(officeAddressCountry);
               partOffice.setOfficePhone(pOffice.getPhoneNumber());
               partOffice.setOfficeFax(pOffice.getFaxNumber());
               partOffice.setOfficeEmail(pOffice.getPartner().getEmail());
               partOffice.setOfficeWebsite(pOffice.getWebsite());
               if (pOffice.getPartnerOfficeType().equals(CCIConstants.PRIMARY_OFFICE)) {
                  partOffice.setIsPrimary(true);
               } else {
                  partOffice.setIsPrimary(false);
               }
               partnerOfficeList.add(partOffice);
            }
         }
         partnerCompanyDetail.getPartnerOffices().addAll(partnerOfficeList);

         if (partnerContact != null) {
            PrimaryContactSalutation primaryContactSalutation = new PrimaryContactSalutation();
            primaryContactSalutation.setSalutationId(partnerContact.getSalutation().getSalutationId());
            primaryContactSalutation.setSalutation(partnerContact.getSalutation().getSalutationName());
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
               if (partner.getPartnerGoId() == contact.getPartner().getPartnerGoId()) {
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
            } else {
               partnerContact.setSalutation(salutationRepository.findOne(partnerCompanyDetail.getPartnerPrimaryContact().getPrimaryContactSalutation().getSalutationId()));
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

   @Override
   @Transactional
   public Response addNewPartnerOffice(String partnerGoId, NewPartnerOffice newPartnerOffice) {
      Response resp = new Response();
      if (partnerGoId == null || Integer.valueOf(partnerGoId) == 0 || Integer.valueOf(partnerGoId) < 0 || newPartnerOffice == null) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID));
         return resp;
      } else {
         try {
            com.ccighgo.db.entities.PartnerOffice partnerOffice = new com.ccighgo.db.entities.PartnerOffice();
            partnerOffice.setAdressOne(newPartnerOffice.getOfficeAddressLineOne());
            partnerOffice.setAdressTwo(newPartnerOffice.getOfficeAddressLineTwo());
            partnerOffice.setCity(newPartnerOffice.getCity());
            partnerOffice.setCreatedBy(1000);
            partnerOffice.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            partnerOffice.setModifiedBy(1000);
            partnerOffice.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            partnerOffice.setFaxNumber(newPartnerOffice.getOfficeFax());
            partnerOffice.setPartner(partnerRepository.findOne(Integer.valueOf(partnerGoId)));
            partnerOffice.setLookupCountry(countryRepository.findOne(newPartnerOffice.getOfficeAddressCountry().getOfficeAddressCountryId()));
            partnerOffice.setPhoneNumber(newPartnerOffice.getOfficePhone());
            partnerOffice.setPostalCode(newPartnerOffice.getZipCode());
            partnerOffice.setWebsite(newPartnerOffice.getOfficeWebsite());
            partnerOffice.setPartnerOfficeType(newPartnerOffice.isIsPrimary() ? partnerOfficeTypeRepository.findOne(1) : partnerOfficeTypeRepository.findOne(3));
            partnerOfficeRepository.saveAndFlush(partnerOffice);
            resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } catch (CcighgoException e) {
            resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_COMPANY_DETAIL.getValue(),
                  messageUtil.getMessage(PartnerCompanyDetailsMessageConstants.ERROR_GET_PARTNER_COMPANY_DETAIL)));
            LOGGER.error(messageUtil.getMessage(PartnerCompanyDetailsMessageConstants.ERROR_GET_PARTNER_COMPANY_DETAIL), e);
         }
      }
      return resp;
   }

   @Override
   @Modifying
   @Transactional
   public Response deletePartnerOffice(String partnerOfficeId) {
      Response resp = new Response();
      if (partnerOfficeId == null || Integer.valueOf(partnerOfficeId) == 0 || Integer.valueOf(partnerOfficeId) < 0 || partnerOfficeId == null) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID));
         return resp;
      } else {
         try {
            com.ccighgo.db.entities.PartnerOffice partnerOffice = partnerOfficeRepository.findOne(Integer.valueOf(partnerOfficeId));
            if (partnerOffice != null) {
               // remove references of the office to be deleted
               if(partnerOffice.getPartnerContacts()!=null){
                  for (PartnerContact contact : partnerOffice.getPartnerContacts()) {
                     contact.setPartnerOffice(null);
                     partnerContactRepository.saveAndFlush(contact);
                  }
               }
               if(partnerOffice.getPartnerUsers()!=null){
                  for(PartnerUser user:partnerOffice.getPartnerUsers()){
                     user.setPartnerOffice(null);
                     partnerUserRepository.saveAndFlush(user);
                  }
               }
               //finally delete the office
               partnerOfficeRepository.delete(partnerOffice.getPartnerOfficeId());
               resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            }
         } catch (CcighgoException e) {
            resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_COMPANY_DETAIL.getValue(),
                  messageUtil.getMessage(PartnerCompanyDetailsMessageConstants.ERROR_GET_PARTNER_COMPANY_DETAIL)));
            LOGGER.error(messageUtil.getMessage(PartnerCompanyDetailsMessageConstants.ERROR_GET_PARTNER_COMPANY_DETAIL), e);
         }
      }
      return resp;
   }
}
