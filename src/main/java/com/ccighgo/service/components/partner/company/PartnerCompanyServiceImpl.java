/**
 * 
 */
package com.ccighgo.service.components.partner.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.db.entities.Salutation;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeTypeRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerStatusRepository;
import com.ccighgo.jpa.repositories.PartnerUserRepository;
import com.ccighgo.jpa.repositories.SalutationRepository;
import com.ccighgo.jpa.repositories.StateRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerSeasonMessageConstants;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerCompanyDetail;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerCompanyDetails;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerCompanyStatus;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerPrimaryContact;
import com.ccighgo.service.transport.partner.beans.companydetail.PrimaryContactSalutation;
import com.ccighgo.utils.CCIConstants;

/**
 * @author ravi
 *
 */
public class PartnerCompanyServiceImpl implements PartnerCompanyService {

   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerCompanyServiceImpl.class);

   @Autowired MessageUtils messageUtil;
   @Autowired StateRepository stateRepository;
   @Autowired CountryRepository countryRepository;
   @Autowired SalutationRepository salutationRepository;
   @Autowired CommonComponentUtils componentUtils;

   @Autowired PartnerRepository partnerRepository;
   @Autowired PartnerUserRepository partnerUserRepository;
   @Autowired PartnerStatusRepository partnerStatusRepository;
   @Autowired PartnerOfficeRepository partnerOfficeRepository;
   @Autowired PartnerOfficeTypeRepository partnerOfficeTypeRepository;

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
         partnerCompanyDetails.setUserName(partner.getGoIdSequence().getLogin().get(0).getLoginName());
         partnerCompanyDetail.setPartnerCompanyDetails(partnerCompanyDetails);
         
         PartnerPrimaryContact partnerPrimaryContact = new PartnerPrimaryContact();
         PrimaryContactSalutation primaryContactSalutation = new PrimaryContactSalutation();
         Salutation salutation = null;
         PartnerUser partnerUser = null;
         for(PartnerUser puser:partner.getPartnerUsers()){
            if(partner.getPartnerGoId()==puser.getPartner().getPartnerGoId()){
               salutation=puser.getSalutation();
               partnerUser=puser;
            }
         }
         primaryContactSalutation.setSalutationId(salutation.getSalutationId());
         primaryContactSalutation.setSalutation(salutation.getSalutationName());
         partnerPrimaryContact.setPrimaryContactSalutation(primaryContactSalutation);
         partnerPrimaryContact.setPrimaryContactTitle(partnerUser.getTitle());

      } catch (CcighgoException e) {
         partnerCompanyDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_LIST)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_LIST), e);
      }
      return partnerCompanyDetail;
   }

}
