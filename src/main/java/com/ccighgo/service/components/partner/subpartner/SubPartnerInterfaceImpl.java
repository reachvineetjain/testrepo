/**
 * 
 */
package com.ccighgo.service.components.partner.subpartner;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.GoIdSequence;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.db.entities.LookupCountry;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerContact;
import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerNoteTopic;
import com.ccighgo.db.entities.PartnerOffice;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerStatus;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LoginUserTypeRepository;
import com.ccighgo.jpa.repositories.PartnerContactRepository;
import com.ccighgo.jpa.repositories.PartnerNoteRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTopicRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeTypeRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.component.emailing.EmailServiceImpl;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.SubPartnerMessageConstants;
import com.ccighgo.service.components.errormessages.constants.UserManagementMessageConstants;
import com.ccighgo.service.components.usermanagment.UserManagementServiceImpl;
import com.ccighgo.service.transport.partner.beans.subpartner.PartnerSubPartners;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartner;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerAgency;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerCountry;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerCountryStatus;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerDetail;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerDetails;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerMailingAddress;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerNote;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerNoteTopic;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerNoteTopics;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerNotes;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerPhysicalAddress;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerPrimaryContact;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerSeasons;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerStatus;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartners;
import com.ccighgo.service.transport.usermanagement.beans.user.UserNotes;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.CCIUtils;
import com.ccighgo.utils.PasswordUtil;
import com.ccighgo.utils.UuidUtils;

/**
 * @author ravi
 *
 */
@Component
public class SubPartnerInterfaceImpl implements SubPartnerInterface {

   private static final Logger LOGGER = Logger.getLogger(SubPartnerInterfaceImpl.class);

   @Autowired
   PartnerRepository partnerRepository;

   @Autowired
   GoIdSequenceRepository goIdSequenceRepository;

   @Autowired
   LoginRepository loginRepository;

   @Autowired
   UserTypeRepository userTypeRepository;

   @Autowired
   LoginUserTypeRepository loginUserTypeRepository;

   @Autowired
   PartnerNoteTopicRepository partnerNoteTopicRepository;

   @Autowired
   PartnerNoteRepository partnerNoteRepository;

   @Autowired
   PartnerOfficeTypeRepository partnerOfficeTypeRepository;

   @Autowired
   PartnerOfficeRepository partnerOfficeRepository;

   @Autowired
   PartnerContactRepository partnerContactRepository;

   @Autowired
   CommonComponentUtils componentUtils;

   @Autowired
   MessageUtils messageUtil;

   @Autowired
   EmailServiceImpl email;

   @Override
   public PartnerSubPartners getSubPartnersOfpartners(String partnerId) {
      PartnerSubPartners psp = new PartnerSubPartners();
      psp.setCount(2);
      psp.setPartnerGoId(1111);
      List<SubPartners> subPartners = new ArrayList<SubPartners>();

      SubPartnerCountry subPartnerCountry1 = new SubPartnerCountry();
      subPartnerCountry1.setSubPartnerCountry("United States");
      subPartnerCountry1.setSubPartnerCountryId(233);

      SubPartnerCountry subPartnerCountry2 = new SubPartnerCountry();
      subPartnerCountry2.setSubPartnerCountry("Taiwan");
      subPartnerCountry2.setSubPartnerCountryId(228);

      SubPartnerCountry subPartnerCountry3 = new SubPartnerCountry();
      subPartnerCountry3.setSubPartnerCountry("Romania");
      subPartnerCountry3.setSubPartnerCountryId(189);

      SubPartnerCountry subPartnerCountry4 = new SubPartnerCountry();
      subPartnerCountry4.setSubPartnerCountry("New Zealand");
      subPartnerCountry4.setSubPartnerCountryId(171);

      SubPartnerCountry subPartnerCountry5 = new SubPartnerCountry();
      subPartnerCountry5.setSubPartnerCountry("Norway");
      subPartnerCountry5.setSubPartnerCountryId(167);

      SubPartnerStatus subPartnerStatus1 = new SubPartnerStatus();
      subPartnerStatus1.setSubPartnerStatusId(1);
      subPartnerStatus1.setSubPartnerStatus("Active");

      SubPartnerStatus subPartnerStatus2 = new SubPartnerStatus();
      subPartnerStatus2.setSubPartnerStatusId(2);
      subPartnerStatus2.setSubPartnerStatus("Inactive");

      List<SubPartnerSeasons> subPartnerSeasons = new ArrayList<SubPartnerSeasons>();
      SubPartnerSeasons partnerSeasons = new SubPartnerSeasons();
      partnerSeasons.setSubPartnerSeasonId(1);
      partnerSeasons.setSubPartnerSeasonProgramId(1);
      partnerSeasons.setSubPartnerSeasonProgram("J1HS");

      SubPartnerSeasons partnerSeasons1 = new SubPartnerSeasons();
      partnerSeasons1.setSubPartnerSeasonId(2);
      partnerSeasons1.setSubPartnerSeasonProgramId(2);
      partnerSeasons1.setSubPartnerSeasonProgram("F1");
      subPartnerSeasons.add(partnerSeasons);
      subPartnerSeasons.add(partnerSeasons1);

      SubPartners sPart = new SubPartners();
      sPart.setSubPartnerId(123);
      sPart.setSubPartnerFirstName("Super");
      sPart.setSubPartnerLastName("Man");
      sPart.setSubPartnerCountry(subPartnerCountry1);
      sPart.setSubPartnerStatus(subPartnerStatus1);
      sPart.getSubPartnerSeasons().addAll(subPartnerSeasons);

      SubPartners sPart1 = new SubPartners();
      sPart1.setSubPartnerId(1234);
      sPart1.setSubPartnerFirstName("Bat");
      sPart1.setSubPartnerLastName("Man");
      sPart1.setSubPartnerCountry(subPartnerCountry2);
      sPart1.setSubPartnerStatus(subPartnerStatus2);
      sPart1.getSubPartnerSeasons().addAll(subPartnerSeasons);

      SubPartners sPart2 = new SubPartners();
      sPart2.setSubPartnerId(1234);
      sPart2.setSubPartnerFirstName("Iron");
      sPart2.setSubPartnerLastName("Man");
      sPart2.setSubPartnerCountry(subPartnerCountry3);
      sPart2.setSubPartnerStatus(subPartnerStatus2);

      SubPartners sPart3 = new SubPartners();
      sPart3.setSubPartnerId(1234);
      sPart3.setSubPartnerFirstName("Ant");
      sPart3.setSubPartnerLastName("Man");
      sPart3.setSubPartnerCountry(subPartnerCountry4);
      sPart3.setSubPartnerStatus(subPartnerStatus1);

      SubPartners sPart4 = new SubPartners();
      sPart4.setSubPartnerId(1234);
      sPart4.setSubPartnerFirstName("Milk");
      sPart4.setSubPartnerLastName("Man");
      sPart4.setSubPartnerCountry(subPartnerCountry5);
      sPart4.setSubPartnerStatus(subPartnerStatus2);
      sPart4.getSubPartnerSeasons().addAll(subPartnerSeasons);

      subPartners.add(sPart);
      subPartners.add(sPart1);
      subPartners.add(sPart2);
      subPartners.add(sPart3);
      subPartners.add(sPart4);
      psp.getSubPartners().addAll(subPartners);
      return psp;
   }

   @Override
   @Transactional
   public SubPartnerDetails getAllSubPartners() {
      SubPartnerDetails subPartnerDetails = new SubPartnerDetails();
      try {
         List<Partner> subPartnerList = partnerRepository.findByIsSubPartner();
         if (subPartnerList == null) {
            subPartnerDetails = setSubPartnerDetailsStatus(subPartnerDetails, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_SUB_PARTNER_DETAILS.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
            return subPartnerDetails;
         }
         subPartnerDetails.setCount(subPartnerList.size());
         for (Partner subPartner : subPartnerList) {
            SubPartnerDetail subPartnerDetail = new SubPartnerDetail();
            subPartnerDetail.setSubPartnerId(subPartner.getPartnerGoId());
            if (subPartner.getPartnerContacts() != null && subPartner.getPartnerContacts().size() > 0) {
               subPartnerDetail.setSubPartnerFirstName(subPartner.getPartnerContacts().iterator().next().getFirstName());
               subPartnerDetail.setSubPartnerLastName(subPartner.getPartnerContacts().iterator().next().getLastName());
            }
            SubPartnerCountryStatus subPartnerCountryStatus = new SubPartnerCountryStatus();
            subPartnerCountryStatus.setSubPartnerCountryId(subPartner.getLookupCountry1().getCountryId());
            subPartnerCountryStatus.setSubPartnerCountryName(subPartner.getLookupCountry1().getCountryName());
            subPartnerCountryStatus.setSubPartnerCountryCode(subPartner.getLookupCountry1().getCountryCode());
            subPartnerDetail.setSubPartnerCountryStatus(subPartnerCountryStatus);

            SubPartnerStatus subPartnerStatus = new com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerStatus();
            subPartnerStatus.setSubPartnerStatusId(subPartner.getPartnerStatus().getPartnerStatusId());
            subPartnerStatus.setSubPartnerStatus(subPartner.getPartnerStatus().getPartnerStatusName());
            subPartnerDetail.setSubPartnerStatus(subPartnerStatus);

            List<SubPartnerSeasons> subPartnerSeasonsList = new ArrayList<SubPartnerSeasons>();
            if (subPartner.getPartnerSeasons() != null && subPartner.getPartnerSeasons().size() > 0) {
               for (PartnerSeason partnerSeason : subPartner.getPartnerSeasons()) {
                  SubPartnerSeasons SubPartnerSeasons = new com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerSeasons();
                  SubPartnerSeasons.setSubPartnerSeasonId(partnerSeason.getPartnerSeasonId());
                  SubPartnerSeasons.setSubPartnerSeasonProgramId(partnerSeason.getDepartmentProgram().getDepartmentProgramId());
                  SubPartnerSeasons.setSubPartnerSeasonProgram(partnerSeason.getDepartmentProgram().getProgramName());
                  subPartnerSeasonsList.add(SubPartnerSeasons);
               }
            }
            subPartnerDetail.getSubPartnerSeasons().addAll(subPartnerSeasonsList);
            subPartnerDetails.getSubPartnerDetails().add(subPartnerDetail);
            subPartnerDetails = setSubPartnerDetailsStatus(subPartnerDetails, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SUB_PARTNER_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
         subPartnerDetails = setSubPartnerDetailsStatus(subPartnerDetails, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_SUB_PARTNER_DETAILS.getValue(),
               messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
         LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
      }

      return subPartnerDetails;
   }

   @Override
   @Transactional
   public SubPartner viewSubPartners(String subPartnerId) {
      SubPartner subPartner = new SubPartner();
      try {
         if (subPartnerId == null && Integer.valueOf(subPartnerId) > 0) {
            subPartner = setSubPartnerStatus(subPartner, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_SUB_PARTNER_ID.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.INVALID_SUB_PARTNER_ID));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.INVALID_SUB_PARTNER_ID));
            return subPartner;
         }
         Partner partnerSubPartner = partnerRepository.findOne(Integer.valueOf(subPartnerId));

         if (partnerSubPartner == null) {
            subPartner = setSubPartnerStatus(subPartner, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_SUB_PARTNER_DETAILS.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
            return subPartner;
         }
         if (partnerSubPartner.getIsSubPartner() == CCIConstants.ACTIVE) {
            subPartner.setSubPartnerId(partnerSubPartner.getPartnerGoId());

            // Agency Details
            SubPartnerAgency subPartnerAgency = new SubPartnerAgency();
            subPartnerAgency.setCompanyName(partnerSubPartner.getCompanyName() == null ? CCIConstants.EMPTY_DATA : partnerSubPartner.getCompanyName());

            // Status
            SubPartnerStatus subPartnerStatus = new SubPartnerStatus();
            if (partnerSubPartner.getPartnerStatus() != null) {
               subPartnerStatus.setSubPartnerStatusId(partnerSubPartner.getPartnerStatus().getPartnerStatusId());
               subPartnerStatus.setSubPartnerStatus(partnerSubPartner.getPartnerStatus().getPartnerStatusName());
            }
            subPartnerAgency.setSubPartnerStatus(subPartnerStatus);
            subPartnerAgency.setNeedPartnerReview(partnerSubPartner.getNeedPartnerReview());
            subPartnerAgency.setDeliverDSForms(partnerSubPartner.getDeliverDSForms());
            subPartnerAgency.setPayGreenheartDirectly(partnerSubPartner.getPayGreenheartDirectly());
            subPartnerAgency.setUserName(partnerSubPartner.getGoIdSequence().getLogin().iterator().next().getLoginName());
            subPartner.setSubPartnerAgency(subPartnerAgency);

            // primary contact
            SubPartnerPrimaryContact subPartnerPrimaryContact = new SubPartnerPrimaryContact();
            if (partnerSubPartner.getPartnerContacts() != null && partnerSubPartner.getPartnerContacts().size() > 0) {
               PartnerContact partnerContact = partnerSubPartner.getPartnerContacts().iterator().next();
               subPartnerPrimaryContact.setSalutation(partnerContact.getSalutation());
               subPartnerPrimaryContact.setTitle(partnerContact.getTitle());
               subPartnerPrimaryContact.setFirstName(partnerContact.getFirstName());
               subPartnerPrimaryContact.setLastName(partnerContact.getLastName());
               subPartnerPrimaryContact.setEmail(partnerContact.getEmail());
               subPartnerPrimaryContact.setPhone(partnerContact.getPhone());
               subPartnerPrimaryContact.setEmergencyPhone(partnerContact.getEmergencyPhone());
               subPartnerPrimaryContact.setFax(partnerContact.getFax());
               subPartnerPrimaryContact.setReceiveNotificationEmailFromCCI(partnerContact.getReceiveNotificationEmails());
               subPartnerPrimaryContact.setSkypeId(partnerContact.getSkypeId());
               subPartnerPrimaryContact.setWebsite(partnerContact.getWebsite());
               subPartnerPrimaryContact.setTypeOfPartnerUser(partnerContact.getPartnerOffice().getPartnerOfficeType().getPartnerOfficeTypeId());
            }
            // TODO: // need to change type here if above code is not proper
            subPartner.setSubPartnerPrimaryContact(subPartnerPrimaryContact);

            // seasons
            List<SubPartnerSeasons> subPartnerSeasonsList = new ArrayList<SubPartnerSeasons>();
            if (partnerSubPartner.getPartnerSeasons() != null && partnerSubPartner.getPartnerSeasons().size() > 0) {
               for (PartnerSeason partnerSeason : partnerSubPartner.getPartnerSeasons()) {
                  SubPartnerSeasons SubPartnerSeasons = new com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerSeasons();
                  SubPartnerSeasons.setSubPartnerSeasonId(partnerSeason.getPartnerSeasonId());
                  SubPartnerSeasons.setSubPartnerSeasonProgramId(partnerSeason.getDepartmentProgram().getDepartmentProgramId());
                  SubPartnerSeasons.setSubPartnerSeasonProgram(partnerSeason.getDepartmentProgram().getProgramName());
                  subPartnerSeasonsList.add(SubPartnerSeasons);
               }
            }
            subPartner.getSubPartnerSeasons().addAll(subPartnerSeasonsList);

            // Physical Address
            SubPartnerPhysicalAddress subPartnerPhysicalAddress = new SubPartnerPhysicalAddress();
            subPartnerPhysicalAddress.setPhysicalAddressLineOne(partnerSubPartner.getPhysicalAddressLineOne());
            subPartnerPhysicalAddress.setPhysicalAddressLineTwo(partnerSubPartner.getPhysicalAddressLineTwo());
            subPartnerPhysicalAddress.setPhysicalCity(partnerSubPartner.getPhysicalCity());
            subPartnerPhysicalAddress.setPhysicalstate(partnerSubPartner.getPhysicalstate());
            subPartnerPhysicalAddress.setPhysicalZipcode(partnerSubPartner.getPhysicalZipcode());

            SubPartnerCountryStatus subPartnerCountryStatus1 = new SubPartnerCountryStatus();
            subPartnerCountryStatus1.setSubPartnerCountryName(partnerSubPartner.getLookupCountry1().getCountryName());
            subPartnerCountryStatus1.setSubPartnerCountryId(partnerSubPartner.getLookupCountry1().getCountryId());
            subPartnerCountryStatus1.setSubPartnerCountryCode(partnerSubPartner.getLookupCountry1().getCountryCode());
            subPartnerPhysicalAddress.setPhysicalSubPartnerCountryStatus(subPartnerCountryStatus1);
            subPartner.setSubPartnerPhysicalAddress(subPartnerPhysicalAddress);

            // Mailing Address
            SubPartnerMailingAddress subPartnerMailingAddress = new SubPartnerMailingAddress();
            subPartnerMailingAddress.setAddressLineOne(partnerSubPartner.getAddressLineOne());
            subPartnerMailingAddress.setAddressLineTwo(partnerSubPartner.getAddressLineTwo());
            subPartnerMailingAddress.setCity(partnerSubPartner.getCity());
            subPartnerMailingAddress.setState(partnerSubPartner.getState());
            subPartnerMailingAddress.setZipcode(partnerSubPartner.getZipcode());

            SubPartnerCountryStatus subPartnerCountryStatus2 = new SubPartnerCountryStatus();
            subPartnerCountryStatus2.setSubPartnerCountryName(partnerSubPartner.getLookupCountry2().getCountryName());
            subPartnerCountryStatus2.setSubPartnerCountryId(partnerSubPartner.getLookupCountry2().getCountryId());
            subPartnerCountryStatus2.setSubPartnerCountryCode(partnerSubPartner.getLookupCountry2().getCountryCode());
            subPartnerMailingAddress.setMailingSubPartnerCountryStatus(subPartnerCountryStatus2);
            subPartner.setSubPartnerMailingAddress(subPartnerMailingAddress);
            subPartner.setNoteTopicCount(partnerSubPartner.getPartnerNoteTopics().size());

            // Note Topics
            SubPartnerNoteTopics subPartnerNoteTopics = new SubPartnerNoteTopics();
            List<SubPartnerNoteTopic> subPartnerNoteTopicList = new ArrayList<SubPartnerNoteTopic>();
            if (partnerSubPartner.getPartnerNoteTopics() != null && partnerSubPartner.getPartnerNoteTopics().size() > 0) {
               List<PartnerNoteTopic> partnerNoteTopicDBList = partnerSubPartner.getPartnerNoteTopics();
               for (PartnerNoteTopic partnerNoteTopic : partnerNoteTopicDBList) {
                  SubPartnerNoteTopic subPartnerNoteTopic = new SubPartnerNoteTopic();
                  subPartnerNoteTopic.setSubPartnerNoteTopicId(partnerNoteTopic.getPartnerNoteTopicId());
                  subPartnerNoteTopic.setIsPublic(partnerNoteTopic.isPublic());
                  subPartnerNoteTopic.setCompetitorInfo(partnerNoteTopic.getCompetitorInfo());
                  subPartnerNoteTopic.setEmbassy_VisaInfo(partnerNoteTopic.getEmbassy_VisaInfo());
                  subPartnerNoteTopic.setF1(partnerNoteTopic.getF1());
                  subPartnerNoteTopic.setGht(partnerNoteTopic.getGht());
                  subPartnerNoteTopic.setIntern(partnerNoteTopic.getIntern());
                  subPartnerNoteTopic.setJ1(partnerNoteTopic.getJ1());
                  subPartnerNoteTopic.setMeeting_visit(partnerNoteTopic.getMeeting_visit());
                  subPartnerNoteTopic.setPartnerNoteTopicName(partnerNoteTopic.getPartnerNoteTopicName());
                  subPartnerNoteTopic.setSeasonInfo(partnerNoteTopic.getSeasonInfo());
                  subPartnerNoteTopic.setStInbound(partnerNoteTopic.getStInbound());
                  subPartnerNoteTopic.setTrainee(partnerNoteTopic.getTrainee());
                  subPartnerNoteTopic.setW_t(partnerNoteTopic.getW_t());
                  subPartnerNoteTopic.setNoteCount(partnerNoteTopic.getPartnerNotes().size());
                  subPartnerNoteTopic.setAuthor(partnerNoteTopic.getPartner().getGoIdSequence().getLogin().iterator().next().getLoginName());
                  subPartnerNoteTopic.setDesignation(CCIConstants.SUB_PARTNER);
                  // note
                  if (partnerNoteTopic.getPartnerNotes() != null && partnerNoteTopic.getPartnerNotes().size() > 0) {
                     for (PartnerNote partnerNote : partnerNoteTopic.getPartnerNotes()) {
                        SubPartnerNote subPartnerNote = new SubPartnerNote();
                        subPartnerNote.setSubPartnerNotesId(partnerNote.getPartnerNotesId());
                        subPartnerNote.setSubpartnerNote(partnerNote.getPartnerNote());
                        subPartnerNote.setCreatedBy(partnerNote.getPartner().getCreatedBy().toString());
                        subPartnerNote.setCreatedOn(partnerNote.getPartner().getCreatedOn().toString());
                        subPartnerNote.setModifiedBy(partnerNote.getModifiedBy().toString());
                        subPartnerNote.setModifiedOn(partnerNote.getModifiedOn().toString());
                        subPartnerNote.setAuthor(partnerNote.getPartner().getGoIdSequence().getLogin().iterator().next().getLoginName());
                        subPartnerNote.setDesignation(CCIConstants.SUB_PARTNER);
                        subPartnerNoteTopic.getSubPartnerNote().add(subPartnerNote);
                     }
                  }
                  subPartnerNoteTopicList.add(subPartnerNoteTopic);
               }
            }
            subPartnerNoteTopics.getSubPartnerNoteTopics().addAll(subPartnerNoteTopicList);
            subPartner.setSubPartnerNoteTopics(subPartnerNoteTopics);
            subPartner = setSubPartnerStatus(subPartner, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SUB_PARTNER_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));

         } else {
            subPartner = setSubPartnerStatus(subPartner, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_SUB_PARTNER_ID.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.INVALID_SUB_PARTNER_ID));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.INVALID_SUB_PARTNER_ID));
         }
      } catch (CcighgoException e) {
         subPartner = setSubPartnerStatus(subPartner, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_SUB_PARTNER_DETAILS.getValue(),
               messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
         LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
      }
      return subPartner;
   }

   @Override
   @Transactional
   public SubPartner createSubPartner(SubPartner subPartner, HttpServletRequest request) {
      SubPartner createdSubPartner = new SubPartner();
      try {
         if (subPartner == null) {
            createdSubPartner = setSubPartnerStatus(createdSubPartner, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SUB_PARTNER_DETAILS_NULL.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.FAILED_SUB_PARTNER_DETAILS_NULL));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.FAILED_SUB_PARTNER_DETAILS_NULL));
            return createdSubPartner;
         }
         // validate username
         if (loginRepository.findByLoginName(subPartner.getSubPartnerAgency().getUserName()) != null) {
            // return username already exsist
            createdSubPartner = setSubPartnerStatus(createdSubPartner, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.SUB_PARTNER_CREATE_USER_USERNAME_EXIST.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.SUB_PARTNER_CREATE_USER_USERNAME_EXIST));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.SUB_PARTNER_CREATE_USER_USERNAME_EXIST));
            return createdSubPartner;
         }
         // findByemail

         if (loginRepository.findByEmail(subPartner.getSubPartnerPrimaryContact().getEmail()) != null) {
            // return email already exist
            createdSubPartner = setSubPartnerStatus(createdSubPartner, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.SUB_PARTNER_CREATE_USER_EMAIL_EXIST.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.SUB_PARTNER_CREATE_USER_EMAIL_EXIST));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.SUB_PARTNER_CREATE_USER_EMAIL_EXIST));
            return createdSubPartner;
         }

         Partner subPartnerDetails = new Partner();
         String partnerGuid = UuidUtils.nextHexUUID();
         subPartnerDetails.setPartnerGuid(partnerGuid);
         // agency details
         SubPartnerAgency subPartnerAgency = null;
         if (subPartner.getSubPartnerAgency() != null) {
            subPartnerAgency = subPartner.getSubPartnerAgency();
            subPartnerDetails.setCompanyName(subPartnerAgency.getCompanyName());

            PartnerStatus partnerStatus = new PartnerStatus();
            partnerStatus.setPartnerStatusId(subPartner.getSubPartnerAgency().getSubPartnerStatus().getSubPartnerStatusId());
            partnerStatus.setPartnerStatusName(subPartner.getSubPartnerAgency().getSubPartnerStatus().getSubPartnerStatus());
            subPartnerDetails.setPartnerStatus(partnerStatus);
            subPartnerDetails.setNeedPartnerReview(subPartnerAgency.getNeedPartnerReview());
            subPartnerDetails.setDeliverDSForms(subPartnerAgency.getDeliverDSForms());
            subPartnerDetails.setPayGreenheartDirectly(subPartnerAgency.getPayGreenheartDirectly());
         }
         // Login And GoId
         GoIdSequence goIdSequence = new GoIdSequence();
         goIdSequence = goIdSequenceRepository.save(goIdSequence);

         com.ccighgo.db.entities.UserType partnerUserType = userTypeRepository.findOne(CCIConstants.PARTNER_USER_TYPE);
         if (partnerUserType == null) {
            partnerUserType = new com.ccighgo.db.entities.UserType();
         }
         List<Login> loginList = new ArrayList<Login>();
         Login login = new Login();
         login.setActive(subPartner.getSubPartnerAgency().getSubPartnerStatus().getSubPartnerStatusId() == 1 ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         login.setLoginName(subPartner.getSubPartnerAgency().getUserName());
         login.setPassword(PasswordUtil.hashKey("password"));
         login.setKeyValue(UuidUtils.nextHexUUID());
         login.setCreatedBy(goIdSequence.getGoId());
         login.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         login.setModifiedBy(goIdSequence.getGoId());
         login.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         login.setGoIdSequence(goIdSequence);
         login.setEmail(subPartner.getSubPartnerPrimaryContact().getEmail());
         login = loginRepository.save(login);
         loginList.add(login);
         goIdSequence.setLogin(loginList);
         subPartnerDetails.setGoIdSequence(goIdSequence);
         subPartnerDetails.setPartnerGoId(goIdSequence.getGoId());

         subPartnerDetails.setIsSubPartner(CCIConstants.ACTIVE);

         LoginUserType loginUserType = new LoginUserType();
         loginUserType.setActive(CCIConstants.ACTIVE);
         loginUserType.setUserType(partnerUserType);
         loginUserType.setCreatedBy(goIdSequence.getGoId());
         loginUserType.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         loginUserType.setModifiedBy(goIdSequence.getGoId());
         loginUserType.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         loginUserType.setDefaultUserType(CCIConstants.ACTIVE);
         loginUserType.setLogin(login);
         loginUserType = loginUserTypeRepository.save(loginUserType);

         // physical address
         SubPartnerPhysicalAddress subPartnerPhysicalAddress = null;
         if (subPartner.getSubPartnerPhysicalAddress() != null) {
            subPartnerPhysicalAddress = subPartner.getSubPartnerPhysicalAddress();
            subPartnerDetails.setPhysicalAddressLineOne(subPartnerPhysicalAddress.getPhysicalAddressLineOne());
            subPartnerDetails.setPhysicalAddressLineTwo(subPartnerPhysicalAddress.getPhysicalAddressLineTwo());
            subPartnerDetails.setPhysicalCity(subPartnerPhysicalAddress.getPhysicalCity());
            subPartnerDetails.setPhysicalstate(subPartnerPhysicalAddress.getPhysicalstate());
            subPartnerDetails.setPhysicalZipcode(subPartnerPhysicalAddress.getPhysicalZipcode());

            LookupCountry subPartnerCountry1 = new LookupCountry();
            subPartnerCountry1.setCountryCode(subPartner.getSubPartnerPhysicalAddress().getPhysicalSubPartnerCountryStatus().getSubPartnerCountryCode());
            subPartnerCountry1.setCountryId(subPartner.getSubPartnerPhysicalAddress().getPhysicalSubPartnerCountryStatus().getSubPartnerCountryId());
            subPartnerCountry1.setCountryName(subPartner.getSubPartnerPhysicalAddress().getPhysicalSubPartnerCountryStatus().getSubPartnerCountryName());
            subPartnerDetails.setLookupCountry1(subPartnerCountry1);
         }

         // mailing address
         SubPartnerMailingAddress subPartnerMailingAddress = null;
         if (subPartner.getSubPartnerMailingAddress() != null) {
            subPartnerMailingAddress = subPartner.getSubPartnerMailingAddress();
            subPartnerDetails.setAddressLineOne(subPartnerMailingAddress.getAddressLineOne());
            subPartnerDetails.setAddressLineTwo(subPartnerMailingAddress.getAddressLineTwo());
            subPartnerDetails.setCity(subPartnerMailingAddress.getCity());
            subPartnerDetails.setState(subPartnerMailingAddress.getState());
            subPartnerDetails.setZipcode(subPartnerMailingAddress.getZipcode());

            LookupCountry subPartnerCountry2 = new LookupCountry();
            subPartnerCountry2.setCountryCode(subPartner.getSubPartnerMailingAddress().getMailingSubPartnerCountryStatus().getSubPartnerCountryCode());
            subPartnerCountry2.setCountryId(subPartner.getSubPartnerMailingAddress().getMailingSubPartnerCountryStatus().getSubPartnerCountryId());
            subPartnerCountry2.setCountryName(subPartner.getSubPartnerMailingAddress().getMailingSubPartnerCountryStatus().getSubPartnerCountryName());
            subPartnerDetails.setLookupCountry2(subPartnerCountry2);

            subPartnerDetails.setCreatedBy(goIdSequence.getGoId());
            subPartnerDetails.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            subPartnerDetails.setModifiedBy(goIdSequence.getGoId());
            subPartnerDetails.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            subPartnerDetails = partnerRepository.save(subPartnerDetails);
         }

         // sub partner office
         SubPartnerPhysicalAddress subPartnerOfficeAddress = null;
         PartnerOffice partnerOffice = new PartnerOffice();
         if (subPartner.getSubPartnerPhysicalAddress() != null) {
            subPartnerOfficeAddress = subPartner.getSubPartnerPhysicalAddress();
            partnerOffice.setAdressOne(subPartnerOfficeAddress.getPhysicalAddressLineOne());
            partnerOffice.setAdressTwo(subPartnerOfficeAddress.getPhysicalAddressLineTwo());
            partnerOffice.setCity(subPartnerOfficeAddress.getPhysicalCity());
            partnerOffice.setCreatedBy(subPartnerDetails.getCreatedBy());
            partnerOffice.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            partnerOffice.setFaxNumber(subPartner.getSubPartnerPrimaryContact().getFax());

            LookupCountry subPartnerCountry3 = new LookupCountry();
            subPartnerCountry3.setCountryId(subPartnerOfficeAddress.getPhysicalSubPartnerCountryStatus().getSubPartnerCountryId());
            subPartnerCountry3.setCountryCode(subPartnerOfficeAddress.getPhysicalSubPartnerCountryStatus().getSubPartnerCountryCode());
            subPartnerCountry3.setCountryName(subPartnerOfficeAddress.getPhysicalSubPartnerCountryStatus().getSubPartnerCountryName());
            partnerOffice.setLookupCountry(subPartnerCountry3);

            partnerOffice.setModifiedBy(subPartnerDetails.getModifiedBy());
            partnerOffice.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            partnerOffice.setPartner(subPartnerDetails);
            partnerOffice.setPartnerOfficeType(partnerOfficeTypeRepository.findOne(Integer.valueOf(subPartner.getSubPartnerPrimaryContact().getTypeOfPartnerUser())));
            partnerOffice.setPhoneNumber(subPartner.getSubPartnerPrimaryContact().getPhone());
            partnerOffice.setPostalCode(subPartnerOfficeAddress.getPhysicalZipcode());
            partnerOffice.setState(subPartnerOfficeAddress.getPhysicalstate());
            partnerOffice = partnerOfficeRepository.save(partnerOffice);
         }
         
         // sub partner contact
         if (subPartner.getSubPartnerPrimaryContact() != null) {
            SubPartnerPrimaryContact subPartnerPrimaryContact = subPartner.getSubPartnerPrimaryContact();
            List<PartnerContact> partnerContactList = new ArrayList<PartnerContact>();
            PartnerContact partnerContact = new PartnerContact();
            partnerContact.setSalutation(subPartnerPrimaryContact.getSalutation());
            partnerContact.setTitle(subPartnerPrimaryContact.getTitle());
            partnerContact.setFirstName(subPartnerPrimaryContact.getFirstName());
            partnerContact.setLastName(subPartnerPrimaryContact.getLastName());
            partnerContact.setEmail(subPartnerPrimaryContact.getEmail());
            partnerContact.setPhone(subPartnerPrimaryContact.getPhone());
            partnerContact.setEmergencyPhone(subPartnerPrimaryContact.getEmergencyPhone());
            partnerContact.setFax(subPartnerPrimaryContact.getFax());
            partnerContact.setReceiveNotificationEmails(subPartnerPrimaryContact.getReceiveNotificationEmailFromCCI());
            partnerContact.setSkypeId(subPartnerPrimaryContact.getSkypeId());
            partnerContact.setWebsite(subPartnerPrimaryContact.getWebsite());
            partnerContact.setPartnerOffice(partnerOffice);
            partnerContact.setPartner(subPartnerDetails);
            partnerContactList.add(partnerContact);
            partnerContactList = partnerContactRepository.save(partnerContactList);
         }

         // Note Topics
         if (subPartner.getSubPartnerNoteTopics().getSubPartnerNoteTopics() != null && subPartner.getSubPartnerNoteTopics().getSubPartnerNoteTopics().size() > 0) {
            for (SubPartnerNoteTopic subPartnerNoteTopic : subPartner.getSubPartnerNoteTopics().getSubPartnerNoteTopics()) {
               PartnerNoteTopic partnerNoteTopic = new PartnerNoteTopic();
               partnerNoteTopic.setPartner(subPartnerDetails);
               partnerNoteTopic.setPublic(subPartnerNoteTopic.getIsPublic());
               partnerNoteTopic.setCompetitorInfo(subPartnerNoteTopic.getCompetitorInfo());
               partnerNoteTopic.setEmbassy_VisaInfo(subPartnerNoteTopic.getEmbassy_VisaInfo());
               partnerNoteTopic.setF1(subPartnerNoteTopic.getF1());
               partnerNoteTopic.setGht(subPartnerNoteTopic.getGht());
               partnerNoteTopic.setIntern(subPartnerNoteTopic.getIntern());
               partnerNoteTopic.setJ1(subPartnerNoteTopic.getJ1());
               partnerNoteTopic.setMeeting_visit(subPartnerNoteTopic.getMeeting_visit());
               partnerNoteTopic.setPartnerNoteTopicName(subPartnerNoteTopic.getPartnerNoteTopicName());
               partnerNoteTopic.setSeasonInfo(subPartnerNoteTopic.getSeasonInfo());
               partnerNoteTopic.setStInbound(subPartnerNoteTopic.getStInbound());
               partnerNoteTopic.setTrainee(subPartnerNoteTopic.getTrainee());
               partnerNoteTopic.setW_t(subPartnerNoteTopic.getW_t());
               partnerNoteTopic = partnerNoteTopicRepository.save(partnerNoteTopic);
               // notes
               List<PartnerNote> partnerNoteList = new ArrayList<PartnerNote>();
               if (subPartnerNoteTopic.getSubPartnerNote() != null && subPartnerNoteTopic.getSubPartnerNote().size() > 0) {
                  for (SubPartnerNote subPartnerNote : subPartnerNoteTopic.getSubPartnerNote()) {
                     PartnerNote partnerNote = new PartnerNote();
                     partnerNote.setPartnerNotesId(subPartnerNote.getSubPartnerNotesId());
                     partnerNote.setPartnerNote(subPartnerNote.getSubpartnerNote());
                     partnerNote.setCreatedBy(partnerNoteTopic.getPartner().getPartnerGoId());
                     partnerNote.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                     partnerNote.setModifiedBy(partnerNoteTopic.getPartner().getPartnerGoId());
                     partnerNote.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                     partnerNote.setPartnerNoteTopic(partnerNoteTopic);
                     partnerNote.setPartner(subPartnerDetails);
                     partnerNoteList.add(partnerNote);
                  }
                  partnerNoteList = partnerNoteRepository.save(partnerNoteList);
               }
            }
         }
         Login loginEmail = loginRepository.findByEmail(subPartner.getSubPartnerPrimaryContact().getEmail());
         if (loginEmail == null) {
            subPartner = setSubPartnerStatus(subPartner, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_EMAIL.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.FAILED_TO_GET_EMAIL));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.FAILED_TO_GET_EMAIL));
            return createdSubPartner;
         }

         String body = "<p>This email was sent automatically by CCI Greenheart Online system to inform you that you an online account has been created for you.  </p></br>"
               + "<p>Please go to the following page and follow the instructions to login to the system. </p> " + "<p>"
               + CCIUtils.formResetURL(request).concat(loginEmail.getKeyValue()) + "</p></br>" + "<p>Thank you,</p><p>GO System Support.</p>";
         email.send(loginEmail.getEmail(), CCIConstants.CREATE_CCI_USER_SUBJECT, body, true);
         createdSubPartner = setSubPartnerStatus(createdSubPartner, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SUB_PARTNER_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));

         // createdSubPartner = viewSubPartners(subPartnerDetails.getPartnerGoId().toString());
      } catch (CcighgoException e) {
         subPartner = setSubPartnerStatus(subPartner, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_CREATE_SUB_PARTNER.getValue(),
               messageUtil.getMessage(SubPartnerMessageConstants.FAILED_CREATE_SUB_PARTNER));
         LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.FAILED_CREATE_SUB_PARTNER));
      }
      return createdSubPartner;
   }

   @Override
   @Transactional
   public SubPartner updateSubPartner(SubPartner subPartner) {

      SubPartner updatedSubPartner = null;
      try {
         Partner subPartnerDetails = partnerRepository.findOne(subPartner.getSubPartnerId());

         if (subPartnerDetails == null) {
            subPartner = setSubPartnerStatus(subPartner, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_SUB_PARTNER_DETAILS.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
            return updatedSubPartner;
         }
         // agency details
         SubPartnerAgency SubPartnerAgency = null;
         if (subPartner.getSubPartnerAgency() != null) {
            SubPartnerAgency = subPartner.getSubPartnerAgency();
            subPartnerDetails.setCompanyName(SubPartnerAgency.getCompanyName());
            subPartnerDetails.getPartnerStatus().setPartnerStatusId(subPartner.getSubPartnerAgency().getSubPartnerStatus().getSubPartnerStatusId());
            subPartnerDetails.getPartnerStatus().setPartnerStatusName(subPartner.getSubPartnerAgency().getSubPartnerStatus().getSubPartnerStatus());
            subPartnerDetails.setNeedPartnerReview(SubPartnerAgency.getNeedPartnerReview());
            subPartnerDetails.setDeliverDSForms(SubPartnerAgency.getDeliverDSForms());
            subPartnerDetails.setPayGreenheartDirectly(SubPartnerAgency.getPayGreenheartDirectly());
         }
         // Login
         if (subPartnerDetails.getGoIdSequence().getLogin() != null && subPartnerDetails.getGoIdSequence().getLogin().size() > 0) {
            Login login = subPartnerDetails.getGoIdSequence().getLogin().iterator().next();
            if(!login.getLoginName().equalsIgnoreCase(subPartner.getSubPartnerAgency().getUserName()))
            {
            if(loginRepository.findByLoginName(subPartner.getSubPartnerAgency().getUserName()) != null)
            login.setLoginName(subPartner.getSubPartnerAgency().getUserName());
            }
            login.setModifiedBy(subPartner.getSubPartnerId());
            login.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            if(!login.getEmail().equalsIgnoreCase(subPartner.getSubPartnerPrimaryContact().getEmail()))
            {
            if(loginRepository.findByEmail(subPartner.getSubPartnerPrimaryContact().getEmail()) != null)
            login.setEmail(subPartner.getSubPartnerPrimaryContact().getEmail());
            }
         }
         // physical address
         SubPartnerPhysicalAddress subPartnerPhysicalAddress = null;
         if (subPartner.getSubPartnerPhysicalAddress() != null) {
            subPartnerPhysicalAddress = subPartner.getSubPartnerPhysicalAddress();
            subPartnerDetails.setPhysicalAddressLineOne(subPartnerPhysicalAddress.getPhysicalAddressLineOne());
            subPartnerDetails.setPhysicalAddressLineTwo(subPartnerPhysicalAddress.getPhysicalAddressLineTwo());
            subPartnerDetails.setPhysicalCity(subPartnerPhysicalAddress.getPhysicalCity());
            subPartnerDetails.setPhysicalstate(subPartnerPhysicalAddress.getPhysicalstate());
            subPartnerDetails.setPhysicalZipcode(subPartnerPhysicalAddress.getPhysicalZipcode());

            LookupCountry subPartnerCountry1 = new LookupCountry();
            subPartnerCountry1.setCountryCode(subPartner.getSubPartnerPhysicalAddress().getPhysicalSubPartnerCountryStatus().getSubPartnerCountryCode());
            subPartnerCountry1.setCountryId(subPartner.getSubPartnerPhysicalAddress().getPhysicalSubPartnerCountryStatus().getSubPartnerCountryId());
            subPartnerCountry1.setCountryName(subPartner.getSubPartnerPhysicalAddress().getPhysicalSubPartnerCountryStatus().getSubPartnerCountryName());
            subPartnerDetails.setLookupCountry1(subPartnerCountry1);
         }
         // mailing address
         SubPartnerMailingAddress subPartnerMailingAddress = null;
         if (subPartner.getSubPartnerMailingAddress() != null) {
            subPartnerMailingAddress = subPartner.getSubPartnerMailingAddress();
            subPartnerDetails.setAddressLineOne(subPartnerMailingAddress.getAddressLineOne());
            subPartnerDetails.setAddressLineTwo(subPartnerMailingAddress.getAddressLineTwo());
            subPartnerDetails.setCity(subPartnerMailingAddress.getCity());
            subPartnerDetails.setState(subPartnerMailingAddress.getState());
            subPartnerDetails.setZipcode(subPartnerMailingAddress.getZipcode());

            LookupCountry subPartnerCountry2 = new LookupCountry();
            subPartnerCountry2.setCountryCode(subPartner.getSubPartnerMailingAddress().getMailingSubPartnerCountryStatus().getSubPartnerCountryCode());
            subPartnerCountry2.setCountryId(subPartner.getSubPartnerMailingAddress().getMailingSubPartnerCountryStatus().getSubPartnerCountryId());
            subPartnerCountry2.setCountryName(subPartner.getSubPartnerMailingAddress().getMailingSubPartnerCountryStatus().getSubPartnerCountryName());
            subPartnerDetails.setLookupCountry2(subPartnerCountry2);

            subPartnerDetails.setModifiedBy(subPartner.getSubPartnerId());
            subPartnerDetails.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         }

         // sub partner office
         List<PartnerOffice> partnerOfficeList = new ArrayList<PartnerOffice>();
         SubPartnerPhysicalAddress subPartnerOfficeAddress = null;
         if (subPartner.getSubPartnerPhysicalAddress() != null) {
            subPartnerOfficeAddress = subPartner.getSubPartnerPhysicalAddress();
            PartnerOffice partnerOffice = subPartnerDetails.getPartnerOffices().iterator().next();
            partnerOffice.setAdressOne(subPartnerOfficeAddress.getPhysicalAddressLineOne());
            partnerOffice.setAdressTwo(subPartnerOfficeAddress.getPhysicalAddressLineTwo());
            partnerOffice.setCity(subPartnerOfficeAddress.getPhysicalCity());
            partnerOffice.setFaxNumber(subPartner.getSubPartnerPrimaryContact().getFax());

            LookupCountry subPartnerCountry3 = new LookupCountry();
            subPartnerCountry3.setCountryId(subPartnerOfficeAddress.getPhysicalSubPartnerCountryStatus().getSubPartnerCountryId());
            subPartnerCountry3.setCountryCode(subPartnerOfficeAddress.getPhysicalSubPartnerCountryStatus().getSubPartnerCountryCode());
            subPartnerCountry3.setCountryName(subPartnerOfficeAddress.getPhysicalSubPartnerCountryStatus().getSubPartnerCountryName());
            partnerOffice.setLookupCountry(subPartnerCountry3);

            partnerOffice.setModifiedBy(subPartner.getSubPartnerId());
            partnerOffice.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            partnerOffice.setPartnerOfficeType(partnerOfficeTypeRepository.findOne(Integer.valueOf(subPartner.getSubPartnerPrimaryContact().getTypeOfPartnerUser())));
            partnerOffice.setPhoneNumber(subPartner.getSubPartnerPrimaryContact().getPhone());
            partnerOffice.setPostalCode(subPartnerOfficeAddress.getPhysicalZipcode());
            partnerOffice.setState(subPartnerOfficeAddress.getPhysicalstate());

            partnerOfficeList.add(partnerOffice);
         }
         subPartnerDetails.setPartnerOffices(partnerOfficeList);

         // sub partner contact
         List<PartnerContact> partnerContactList = new ArrayList<PartnerContact>();
         SubPartnerPrimaryContact subPartnerPrimaryContact = null;
         if (subPartner.getSubPartnerPrimaryContact() != null) {
            subPartnerPrimaryContact = subPartner.getSubPartnerPrimaryContact();
            PartnerContact partnerContact = subPartnerDetails.getPartnerContacts().iterator().next();
            partnerContact.setSalutation(subPartnerPrimaryContact.getSalutation());
            partnerContact.setTitle(subPartnerPrimaryContact.getTitle());
            partnerContact.setFirstName(subPartnerPrimaryContact.getFirstName());
            partnerContact.setLastName(subPartnerPrimaryContact.getLastName());
            partnerContact.setEmail(subPartnerPrimaryContact.getEmail());
            partnerContact.setPhone(subPartnerPrimaryContact.getPhone());
            partnerContact.setEmergencyPhone(subPartnerPrimaryContact.getEmergencyPhone());
            partnerContact.setFax(subPartnerPrimaryContact.getFax());
            partnerContact.setReceiveNotificationEmails(subPartnerPrimaryContact.getReceiveNotificationEmailFromCCI());
            partnerContact.setSkypeId(subPartnerPrimaryContact.getSkypeId());
            partnerContact.setWebsite(subPartnerPrimaryContact.getWebsite());

            partnerContactList.add(partnerContact);
         }
         subPartnerDetails.setPartnerContacts(partnerContactList);

         // TODO: notes need to update if required
         partnerRepository.saveAndFlush(subPartnerDetails);
         updatedSubPartner = setSubPartnerStatus(updatedSubPartner, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SUB_PARTNER_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         updatedSubPartner = viewSubPartners(subPartnerDetails.getPartnerGoId().toString());
      } catch (CcighgoException e) {
         subPartner = setSubPartnerStatus(subPartner, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_SUB_PARTNER.getValue(),
               messageUtil.getMessage(SubPartnerMessageConstants.FAILED_UPDATE_SUB_PARTNER));
         LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.FAILED_UPDATE_SUB_PARTNER));
      }
      return updatedSubPartner;
   }

   private SubPartner setSubPartnerStatus(SubPartner subPartner, String code, String type, int serviceCode, String message) {
      if (subPartner == null)
         subPartner = new SubPartner();
      subPartner.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return subPartner;
   }

   private SubPartnerDetails setSubPartnerDetailsStatus(SubPartnerDetails subPartnerDetails, String code, String type, int serviceCode, String message) {
      if (subPartnerDetails == null)
         subPartnerDetails = new SubPartnerDetails();
      subPartnerDetails.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return subPartnerDetails;
   }

}
