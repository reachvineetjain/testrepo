/**
 * 
 */
package com.ccighgo.service.components.partner.subpartner;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.CCIStaffUsersCCIStaffRole;
import com.ccighgo.db.entities.GoIdSequence;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.db.entities.LookupCountry;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerContact;
import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerNoteTag;
import com.ccighgo.db.entities.PartnerNoteTopic;
import com.ccighgo.db.entities.PartnerOffice;
import com.ccighgo.db.entities.PartnerReviewStatus;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerStatus;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.db.entities.PartnerUserRole;
import com.ccighgo.db.entities.Salutation;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.CCIStaffRolesRepository;
import com.ccighgo.jpa.repositories.CCIStaffUsersCCIStaffRolesRepository;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LoginUserTypeRepository;
import com.ccighgo.jpa.repositories.PartnerContactRepository;
import com.ccighgo.jpa.repositories.PartnerNoteRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTagRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTopicRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeTypeRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerReviewStatusRepository;
import com.ccighgo.jpa.repositories.PartnerStatusRepository;
import com.ccighgo.jpa.repositories.PartnerUserRepository;
import com.ccighgo.jpa.repositories.SalutationRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.component.emailing.EmailServiceImpl;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminMessageConstants;
import com.ccighgo.service.components.errormessages.constants.RegionManagementMessageConstants;
import com.ccighgo.service.components.errormessages.constants.SubPartnerMessageConstants;
import com.ccighgo.service.components.utility.UtilityServices;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.NoteUserCreator;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningNotes;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerMailingAddress;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.Creator;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.Note;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.NoteTags;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.NoteTopics;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.Topic;
import com.ccighgo.service.transport.partner.beans.subpartner.PartnerSubPartners;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartner;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerAgency;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerCountry;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerCountryStatus;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerDetail;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerDetails;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerMailingAddress;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerPhysicalAddress;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerPrimaryContact;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerSeasons;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerStatus;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartners;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.Country;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.Details;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerScreeningNotes;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnersMailingAddress;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnersPhysicalAddress;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnersPrimaryContact;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.Topics;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.TypeofPartnerUser;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.CCIUtils;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.PasswordUtil;
import com.ccighgo.utils.UuidUtils;
import com.ccighgo.utils.WSDefaultResponse;

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
   PartnerNoteTagRepository partnerNoteTagRepository;

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

   @Autowired
   SalutationRepository salutationRepository;

   @Autowired
   UtilityServices utilityServices;

   @Autowired
   PartnerStatusRepository partnerStatusRepository;
   // TODO
   // @Autowired PartnerCCIContactRepository partnerCCIContactRepository;

   @Autowired
   PartnerReviewStatusRepository partnerReviewStatusRepository;

   @Autowired
   PartnerUserRepository partnerUserRepository;

   @Autowired
   CountryRepository countryRepository;

   public PartnerSubPartners getSubPartnersOfpartners2(String partnerId) {
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
   public PartnerSubPartners getSubPartnersOfpartners(String partnerId) {
      PartnerSubPartners subPartnerDetails = new PartnerSubPartners();
      try {
         List<Partner> subPartnerList = partnerRepository.findByIsSubPartnerAndParentId(Integer.parseInt(partnerId));
         if (subPartnerList == null) {
            subPartnerDetails = setSubPartnerDetailsStatus(subPartnerDetails, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_SUB_PARTNER_DETAILS.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
            return subPartnerDetails;
         }
         subPartnerDetails.setCount(subPartnerList.size());
         for (Partner subPartner : subPartnerList) {
            SubPartners sp = new SubPartners();
            sp.setSubPartnerId(subPartner.getPartnerGoId());
            if (subPartner.getPartnerContacts() != null && subPartner.getPartnerContacts().size() > 0) {
               sp.setSubPartnerFirstName(subPartner.getPartnerContacts().iterator().next().getFirstName());
               sp.setSubPartnerLastName(subPartner.getPartnerContacts().iterator().next().getLastName());
            }
            SubPartnerCountry subPartnerCountry2 = new SubPartnerCountry();
            subPartnerCountry2.setSubPartnerCountry(subPartner.getLookupCountry2().getCountryName());
            subPartnerCountry2.setSubPartnerCountryId(subPartner.getLookupCountry2().getCountryId());

            sp.setSubPartnerCountry(subPartnerCountry2);

            SubPartnerStatus subPartnerStatus = new com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerStatus();
            List<PartnerUser> partnerUsers = subPartner.getPartnerUsers();
            PartnerUser partnerUser = new PartnerUser();
            if (partnerUsers != null && partnerUsers.size() > 0) {
               for (PartnerUser puser : partnerUsers) {
                  if (puser.getPartner() != null)
                     if (puser.getPartner().getPartnerGoId() == subPartner.getGoIdSequence().getGoId()) {
                        partnerUser = puser;
                        break;
                     }
               }
               Login login = partnerUser.getLogin();
               if (login != null) {
                 subPartnerStatus.setSubPartnerStatus(login.getActive()==1?"Active":"Inactive");
                 subPartnerStatus.setSubPartnerStatusId(login.getLoginId());
               }
            }
            
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
            sp.getSubPartnerSeasons().addAll(subPartnerSeasonsList);
            subPartnerDetails.getSubPartners().add(sp);
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

   @Transactional
   public WSDefaultResponse UpdateSubPartnerDetail2(com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail subPartner) {
      WSDefaultResponse responce = new WSDefaultResponse();
      try {
         Partner subPartnerDetails = partnerRepository.findOne(Integer.parseInt(subPartner.getGoId()));
         if (subPartnerDetails == null) {
            responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_SUB_PARTNER_DETAILS.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS)));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
            return responce;
         }

         // agency details
         Details subPartnerDetailInfo = subPartner.getPartnerDetail();
         if (subPartnerDetailInfo != null) {
            subPartnerDetails.setCompanyName(subPartnerDetailInfo.getAgencyName());
            subPartnerDetails.setNeedPartnerReview((byte) (subPartnerDetailInfo.isNeedsPartnerReview() ? 1 : 0));
            subPartnerDetails.setDeliverDSForms((byte) (subPartnerDetailInfo.isRecivevisaforms() ? 1 : 0));
            subPartnerDetails.setPayGreenheartDirectly((byte) (subPartnerDetailInfo.isPayGreenHeartDirectly() ? 1 : 0));
         }
         /*
          * 
          * Primary Contact
          */

         PartnerContact partnerContact = null;
         SubPartnersPrimaryContact subPartnerPrimaryContact = subPartner.getSubPartnerPrimaryContact();
         if (subPartnerPrimaryContact != null) {
            List<PartnerContact> partnerContactList = partnerContactRepository.findPartnerContactsByPartnerId(Integer.parseInt(subPartner.getGoId()));

            if (partnerContactList != null && partnerContactList.size() > 0) {
               for (PartnerContact ptc : partnerContactList) {
                  if (ptc.getIsPrimary() == CCIConstants.ACTIVE ? true : false) {
                     partnerContact = ptc;
                     break;
                  }
               }
               if (partnerContact != null) {
                  com.ccighgo.service.transport.partner.beans.subpartnerdetail.Salutation slt = subPartnerPrimaryContact.getSalutation();
                  if (slt != null) {
                     Salutation s = salutationRepository.findBySalutationName(slt.getSalutationName());
                     partnerContact.setSalutation(s);
                  }
                  partnerContact.setTitle(subPartnerPrimaryContact.getTitle());
                  partnerContact.setFirstName(subPartnerPrimaryContact.getFirstName());
                  partnerContact.setLastName(subPartnerPrimaryContact.getLastName());
                  partnerContact.setEmail(subPartnerPrimaryContact.getEmail());
                  partnerContact.setPhone(subPartnerPrimaryContact.getPhone());
                  partnerContact.setEmergencyPhone(subPartnerPrimaryContact.getEmergencyPhone());
                  partnerContact.setReceiveNotificationEmails((byte) (subPartnerPrimaryContact.isReciveNotificationemailfromcc() ? 1 : 0));
                  partnerContact.setSkypeId(subPartnerPrimaryContact.getSkypeId());
                  partnerContact.setWebsite(subPartnerPrimaryContact.getWebsite());

               } else {
                  partnerContact = new PartnerContact();
                  com.ccighgo.service.transport.partner.beans.subpartnerdetail.Salutation slt = subPartnerPrimaryContact.getSalutation();
                  if (slt != null) {
                     Salutation s = salutationRepository.findBySalutationName(slt.getSalutationName());
                     partnerContact.setSalutation(s);
                  }
                  partnerContact.setTitle(subPartnerPrimaryContact.getTitle());
                  partnerContact.setFirstName(subPartnerPrimaryContact.getFirstName());
                  partnerContact.setLastName(subPartnerPrimaryContact.getLastName());
                  partnerContact.setEmail(subPartnerPrimaryContact.getEmail());
                  partnerContact.setPhone(subPartnerPrimaryContact.getPhone());
                  partnerContact.setEmergencyPhone(subPartnerPrimaryContact.getEmergencyPhone());
                  partnerContact.setReceiveNotificationEmails((byte) (subPartnerPrimaryContact.isReciveNotificationemailfromcc() ? 1 : 0));
                  partnerContact.setSkypeId(subPartnerPrimaryContact.getSkypeId());
                  partnerContact.setWebsite(subPartnerPrimaryContact.getWebsite());
                  partnerContact.setIsPrimary((byte) 1);
               }
            } else {
               partnerContact = new PartnerContact();
               com.ccighgo.service.transport.partner.beans.subpartnerdetail.Salutation slt = subPartnerPrimaryContact.getSalutation();
               if (slt != null) {
                  Salutation s = salutationRepository.findBySalutationName(slt.getSalutationName());
                  partnerContact.setSalutation(s);
               }
               partnerContact.setTitle(subPartnerPrimaryContact.getTitle());
               partnerContact.setFirstName(subPartnerPrimaryContact.getFirstName());
               partnerContact.setLastName(subPartnerPrimaryContact.getLastName());
               partnerContact.setEmail(subPartnerPrimaryContact.getEmail());
               partnerContact.setPhone(subPartnerPrimaryContact.getPhone());
               partnerContact.setEmergencyPhone(subPartnerPrimaryContact.getEmergencyPhone());
               partnerContact.setReceiveNotificationEmails((byte) (subPartnerPrimaryContact.isReciveNotificationemailfromcc() ? 1 : 0));
               partnerContact.setSkypeId(subPartnerPrimaryContact.getSkypeId());
               partnerContact.setWebsite(subPartnerPrimaryContact.getWebsite());
               partnerContact.setIsPrimary((byte) 1);
            }
            GoIdSequence goId = subPartnerDetails.getGoIdSequence();
            Login login = loginRepository.findByGoId(goId);
            if (login != null) {
               partnerContact.setCreatedBy(login.getLoginId());
               partnerContact.setModifiedBy(login.getLoginId());

            }
            subPartnerDetails.addPartnerContact(partnerContact);
            partnerContactRepository.saveAndFlush(partnerContact);

         }
         /*
          * SubPartnersPhysicalAddress
          */

         SubPartnersPhysicalAddress subPartnersPhysicalAddress = subPartner.getSubPartnerPhysicalAddress();
         if (subPartnersPhysicalAddress != null) {

            subPartnerDetails.setPhysicalAddressLineOne(subPartnersPhysicalAddress.getPhysicalAddress1());
            subPartnerDetails.setPhysicalAddressLineTwo(subPartnersPhysicalAddress.getPhysicalAddress2());
            subPartnerDetails.setPhysicalCity(subPartnersPhysicalAddress.getPhysicalAddressCity());
            subPartnerDetails.setPhysicalstate(subPartnersPhysicalAddress.getPhysicalAddressStateOrProvince());
            subPartnerDetails.setPhysicalZipcode(subPartnersPhysicalAddress.getPhysicalAddressZipCode());

            if (subPartnersPhysicalAddress.getPhysicalAddressCountry() != null) {
               LookupCountry subPartnerCountry1 = countryRepository.findByCountryName(subPartnersPhysicalAddress.getPhysicalAddressCountry().getCountryName());
               subPartnerDetails.setLookupCountry1(subPartnerCountry1);
            }

         }
         SubPartnersMailingAddress subPartnersMailingAddress = subPartner.getSubPartnerMailingAddress();
         if (subPartnersMailingAddress != null) {
            subPartnerDetails.setAddressLineOne(subPartnersMailingAddress.getMailingAddress1());
            subPartnerDetails.setAddressLineTwo(subPartnersMailingAddress.getMailingAddress2());

            subPartnerDetails.setCity(subPartnersMailingAddress.getMailingAddressCity());

            subPartnerDetails.setState(subPartnersMailingAddress.getMailingAddressStateOrProvince());
            Country c = subPartnersMailingAddress.getMailingAddressCountry();
            if (c != null) {
               LookupCountry lcm = countryRepository.findByCountryName(c.getCountryName());
               if (lcm != null) {
                  subPartnerDetails.setLookupCountry2(lcm);
               }
            }
            partnerRepository.saveAndFlush(subPartnerDetails);

            responce.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SUB_PARTNER_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (CcighgoException e) {
         responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_SUB_PARTNER.getValue(),
               messageUtil.getMessage(SubPartnerMessageConstants.FAILED_UPDATE_SUB_PARTNER)));
         LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.FAILED_UPDATE_SUB_PARTNER));
      }
      return responce;
   }

   private SubPartner setSubPartnerStatus(SubPartner subPartner, String code, String type, int serviceCode, String message) {
      if (subPartner == null)
         subPartner = new SubPartner();
      subPartner.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return subPartner;
   }

   private PartnerSubPartners setSubPartnerDetailsStatus(PartnerSubPartners subPartnerDetails, String code, String type, int serviceCode, String message) {
      if (subPartnerDetails == null)
         subPartnerDetails = new PartnerSubPartners();
      subPartnerDetails.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return subPartnerDetails;
   }

   private SubPartnerDetails setSubPartnerDetailsStatus(SubPartnerDetails subPartnerDetails, String code, String type, int serviceCode, String message) {
      if (subPartnerDetails == null)
         subPartnerDetails = new SubPartnerDetails();
      subPartnerDetails.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return subPartnerDetails;
   }

   @Override
   @Transactional
   public com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail getSubPartnerDetail(String subPartnerId) {
      com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail subPartnerDetail = new com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail();
      try {
         Partner partnerSubPartner = partnerRepository.findOne(Integer.valueOf(subPartnerId));
         if (partnerSubPartner == null) {
            subPartnerDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_PARTNER_GET_DETAILS.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.INVALID_SUB_PARTNER_ID)));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.INVALID_SUB_PARTNER_ID));
            return subPartnerDetail;
         }

         Details details = new Details();
         details.setAgencyName(partnerSubPartner.getCompanyName());
         details.setLogoImageURL(partnerSubPartner.getPartnerLogo());

         List<PartnerUser> partnerUsers = partnerSubPartner.getPartnerUsers();
         PartnerUser partnerUser = new PartnerUser();
         if (partnerUsers != null && partnerUsers.size() > 0) {
            for (PartnerUser puser : partnerUsers) {
               if (puser.getPartner() != null)
                  if (puser.getPartner().getPartnerGoId() == Integer.valueOf(subPartnerId)) {
                     partnerUser = puser;
                     break;
                  }
            }
            Login login = partnerUser.getLogin();
            if (login != null) {
               details.setUsername(login.getLoginName());
               details.setPassword(login.getPassword());
               subPartnerDetail.setActive(login.getActive() == 1);
            }
         }
         if (partnerSubPartner.getNeedPartnerReview() != null)
            details.setNeedsPartnerReview(partnerSubPartner.getNeedPartnerReview().equals(CCIConstants.ACTIVE) ? true : false);
         if (partnerSubPartner.getPayGreenheartDirectly() != null)
            details.setPayGreenHeartDirectly(partnerSubPartner.getPayGreenheartDirectly() == CCIConstants.ACTIVE ? true : false);
         if (partnerSubPartner.getDeliverDSForms() != null)
            details.setRecivevisaforms(partnerSubPartner.getDeliverDSForms() == CCIConstants.ACTIVE ? true : false);
         if (partnerSubPartner.getPayGreenheartDirectly() != null)
            details.setPayGreenHeartDirectly(partnerSubPartner.getPayGreenheartDirectly() == CCIConstants.ACTIVE ? true : false);

         PartnerContact partnerContact = new PartnerContact();
         SubPartnersPrimaryContact subPartnerPrimaryContact = new SubPartnersPrimaryContact();
         if (partnerSubPartner.getPartnerContacts() != null && partnerSubPartner.getPartnerContacts().size() > 0) {
            List<PartnerContact> partnerContactList = partnerSubPartner.getPartnerContacts();
            for (PartnerContact ptc : partnerContactList) {
               if (ptc.getIsPrimary() == CCIConstants.ACTIVE) {
                  partnerContact = ptc;
                  break;
               }
            }
            if (partnerContact != null) {
               Salutation subPartnerSalutation = partnerContact.getSalutation();
               com.ccighgo.service.transport.partner.beans.subpartnerdetail.Salutation salutation = new com.ccighgo.service.transport.partner.beans.subpartnerdetail.Salutation();

               if (subPartnerSalutation != null) {
                  salutation.setSalutationId(subPartnerSalutation.getSalutationId());
                  salutation.setSalutationName(subPartnerSalutation.getSalutationName());
               }
               subPartnerPrimaryContact.setSalutation(salutation);
               subPartnerPrimaryContact.setTitle(partnerContact.getTitle());
               subPartnerPrimaryContact.setFirstName(partnerContact.getFirstName());
               subPartnerPrimaryContact.setLastName(partnerContact.getLastName());
               subPartnerPrimaryContact.setEmail(partnerContact.getEmail());
               subPartnerPrimaryContact.setPhone(partnerContact.getPhone());
               subPartnerPrimaryContact.setEmergencyPhone(partnerContact.getEmergencyPhone());
               subPartnerPrimaryContact.setFax(partnerContact.getFax());
               if (partnerContact.getReceiveNotificationEmails() != null)
                  subPartnerPrimaryContact.setReciveNotificationemailfromcc(partnerContact.getReceiveNotificationEmails() == CCIConstants.ACTIVE);
               subPartnerPrimaryContact.setSkypeId(partnerContact.getSkypeId());
               subPartnerPrimaryContact.setWebsite(partnerContact.getWebsite());
            }
         }
         SubPartnersPhysicalAddress subPartnerPhysicalAddress = new SubPartnersPhysicalAddress();
         subPartnerPhysicalAddress.setPhysicalAddress1(partnerSubPartner.getPhysicalAddressLineOne());
         subPartnerPhysicalAddress.setPhysicalAddress2(partnerSubPartner.getPhysicalAddressLineTwo());
         subPartnerPhysicalAddress.setPhysicalAddressCity(partnerSubPartner.getPhysicalCity());
         subPartnerPhysicalAddress.setPhysicalAddressStateOrProvince(partnerSubPartner.getPhysicalstate());
         subPartnerPhysicalAddress.setPhysicalAddressZipCode(partnerSubPartner.getPhysicalZipcode());
         LookupCountry lc = partnerSubPartner.getLookupCountry1();
         if (lc != null) {
            Country cn = new Country();
            cn.setCountryId(lc.getCountryId());
            cn.setCountryName(lc.getCountryName());
            subPartnerPhysicalAddress.setPhysicalAddressCountry(cn);
         }
         SubPartnersMailingAddress subPartnersMailingAddress = new SubPartnersMailingAddress();
         if (partnerSubPartner.getMailingAddressIsSameAsPhysicalAdress() == CCIConstants.ACTIVE ? true : false) {
            subPartnersMailingAddress.setMailingAddress1(partnerSubPartner.getPhysicalAddressLineOne());
            subPartnersMailingAddress.setMailingAddress2(partnerSubPartner.getPhysicalAddressLineTwo());
            subPartnersMailingAddress.setMailingAddressCity(partnerSubPartner.getPhysicalCity());
            subPartnersMailingAddress.setMailingAddressZipCode(partnerSubPartner.getPhysicalZipcode());
            LookupCountry lcm = partnerSubPartner.getLookupCountry2();
            if (lcm != null) {
               Country cn = new Country();
               cn.setCountryId(lcm.getCountryId());
               cn.setCountryName(lcm.getCountryName());
               subPartnersMailingAddress.setMailingAddressCountry(cn);
            }

         } else {
            subPartnersMailingAddress.setMailingAddress1(partnerSubPartner.getAddressLineOne());
            subPartnersMailingAddress.setMailingAddress2(partnerSubPartner.getAddressLineTwo());
            subPartnersMailingAddress.setMailingAddressCity(partnerSubPartner.getCity());
            subPartnersMailingAddress.setMailingAddressStateOrProvince(partnerSubPartner.getState());
            LookupCountry lcm = partnerSubPartner.getLookupCountry2();
            if (lcm != null) {
               Country cn = new Country();
               cn.setCountryId(lcm.getCountryId());
               cn.setCountryName(lcm.getCountryName());
               subPartnersMailingAddress.setMailingAddressCountry(cn);
            }

         }
         subPartnerDetail.setGoId(subPartnerId);
         subPartnerDetail.setPartnerDetail(details);
         subPartnerDetail.setSubPartnerPrimaryContact(subPartnerPrimaryContact);
         subPartnerDetail.setSubPartnerPhysicalAddress(subPartnerPhysicalAddress);
         subPartnerDetail.setSubPartnerMailingAddress(subPartnersMailingAddress);

         subPartnerDetail.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (CcighgoException e) {
         subPartnerDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_PARTNER_GET_DETAILS.getValue(),
               messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS) + ":" + e);
      }
      return subPartnerDetail;
   }

   @Override
   public WSDefaultResponse addSubPartnerScreenNote(SubPartnerScreeningNotes noteDetail) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         PartnerNote note = new PartnerNote();
         note.setCreatedBy(noteDetail.getUserId());
         note.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         note.setModifiedBy(noteDetail.getUserId());

         note.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         Partner partner = partnerRepository.findOne(noteDetail.getPartnerId());
         note.setPartner(partner);
         note.setPartnerNote(noteDetail.getNoteValue());

         PartnerNoteTopic partnerNoteTopic = partnerNoteTopicRepository.findOne(noteDetail.getTopicId());
         note.setPartnerNoteTopic(partnerNoteTopic);

         partnerNoteRepository.saveAndFlush(note);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SUB_PARTNER_NOTE_CREATED.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);

         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_ADD_SUBPARTNER_NOTE.getValue(),
               messageUtil.getMessage(SubPartnerMessageConstants.FAILED_TO_ADD_SUBPARTNER_NOTE)));
         LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.FAILED_TO_ADD_SUBPARTNER_NOTE));

      }
      return wsDefaultResponse;
   }

   @Override
   @Transactional
   public WSDefaultResponse updatePartnerUserStatus(String partnerUserId, String statusVal) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findApplicationStatusByGoId(Integer.parseInt(partnerUserId));
         PartnerStatus partnerStatus2 = partnerReviewStatus.getPartnerStatus2();
         partnerStatus2.setPartnerStatusName(statusVal);
         partnerReviewStatusRepository.saveAndFlush(partnerReviewStatus);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.PARTNER_APPLICATION_STATUS_UPDATED.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.CANT_UPDATE_PARTNER_APPLICATION_STATUS.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_PARTNER_APPLICATION_STATUS)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_PARTNER_APPLICATION_STATUS));

      }
      return wsDefaultResponse;
   }

   @Override
   @Transactional
   public WSDefaultResponse createSubPartnerDetail(com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail subPartner) {
      WSDefaultResponse responce = new WSDefaultResponse();
      try {
         if (subPartner == null) {
            responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SUB_PARTNER_DETAILS_NULL.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.FAILED_SUB_PARTNER_DETAILS_NULL)));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.FAILED_SUB_PARTNER_DETAILS_NULL));
            return responce;
         }
         if (loginRepository.findByLoginName(subPartner.getPartnerDetail().getUsername()) != null) {
            responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.SUB_PARTNER_CREATE_USER_USERNAME_EXIST.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.SUB_PARTNER_CREATE_USER_USERNAME_EXIST)));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.SUB_PARTNER_CREATE_USER_USERNAME_EXIST));
            return responce;
         }

         if (loginRepository.findByEmail(subPartner.getSubPartnerPrimaryContact().getEmail()) != null) {
            responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.SUB_PARTNER_CREATE_USER_EMAIL_EXIST.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.SUB_PARTNER_CREATE_USER_EMAIL_EXIST)));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.SUB_PARTNER_CREATE_USER_EMAIL_EXIST));
            return responce;
         }
         Partner subPartnerDetails = new Partner();

         Details subPartnerDetailInfo = subPartner.getPartnerDetail();
         if (subPartnerDetailInfo != null) {
            subPartnerDetails.setPartnerLogo(subPartnerDetailInfo.getLogoImageURL());
            subPartnerDetails.setCompanyName(subPartnerDetailInfo.getAgencyName());
            subPartnerDetails.setNeedPartnerReview((byte) (subPartnerDetailInfo.isNeedsPartnerReview() ? 1 : 0));
            subPartnerDetails.setDeliverDSForms((byte) (subPartnerDetailInfo.isRecivevisaforms() ? 1 : 0));
            subPartnerDetails.setPayGreenheartDirectly((byte) (subPartnerDetailInfo.isPayGreenHeartDirectly() ? 1 : 0));
         }
         subPartnerDetails.setMailingAddressIsSameAsPhysicalAdress((byte) 1);
         SubPartnersPrimaryContact subPartnerPrimaryContact = subPartner.getSubPartnerPrimaryContact();
         SubPartnersMailingAddress subPartnersMailingAddress = subPartner.getSubPartnerMailingAddress();
         GoIdSequence goIdSequence = new GoIdSequence();
         Login login = new Login();
         try {
            subPartnerDetails.setParentPartnerGoId(Integer.parseInt(subPartner.getGoId()));
            subPartnerDetails.setIsSubPartner((byte) 1);

            List<Login> loginList = new ArrayList<Login>();
            goIdSequence = goIdSequenceRepository.save(goIdSequence);
            com.ccighgo.db.entities.UserType ParticipantUserType = userTypeRepository.findOne(CCIConstants.PARTICIPANT_USER_TYPE);
            if (ParticipantUserType == null) {
               ParticipantUserType = new com.ccighgo.db.entities.UserType();
            }
            login.setActive(subPartner.isActive() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            login.setLoginName(subPartnerDetailInfo.getUsername());
            login.setPassword(PasswordUtil.hashKey(subPartnerDetailInfo.getPassword()));
            login.setKeyValue(UuidUtils.nextHexUUID());
            login.setCreatedBy(Integer.parseInt(subPartner.getGoId()));
            login.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            login.setModifiedBy(Integer.parseInt(subPartner.getGoId()));
            login.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            login.setGoIdSequence(goIdSequence);
            login.setEmail(subPartnerPrimaryContact.getEmail());
            login = loginRepository.save(login);
            loginList.add(login);
            goIdSequence.setLogins(loginList);

            LoginUserType loginUserType = new LoginUserType();
            loginUserType.setActive(CCIConstants.ACTIVE);
            loginUserType.setUserType(ParticipantUserType);
            loginUserType.setCreatedBy(Integer.parseInt(subPartner.getGoId()));
            loginUserType.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            loginUserType.setModifiedBy(Integer.parseInt(subPartner.getGoId()));
            loginUserType.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            loginUserType.setDefaultUserType(CCIConstants.ACTIVE);
            loginUserType.setLogin(login);
            loginUserType = loginUserTypeRepository.save(loginUserType);

         } catch (Exception e) {
            ExceptionUtil.logException(e, LOGGER);
            LOGGER.error(e.getMessage());
         }

         SubPartnersPhysicalAddress subPartnersPhysicalAddress = subPartner.getSubPartnerPhysicalAddress();
         if (subPartnersPhysicalAddress != null) {
            subPartnerDetails.setPhysicalAddressLineOne(subPartnersPhysicalAddress.getPhysicalAddress1());
            subPartnerDetails.setPhysicalAddressLineTwo(subPartnersPhysicalAddress.getPhysicalAddress2());
            subPartnerDetails.setPhysicalCity(subPartnersPhysicalAddress.getPhysicalAddressCity());
            subPartnerDetails.setPhysicalstate(subPartnersPhysicalAddress.getPhysicalAddressStateOrProvince());
            subPartnerDetails.setPhysicalZipcode(subPartnersPhysicalAddress.getPhysicalAddressZipCode());

            if (subPartnersPhysicalAddress.getPhysicalAddressCountry() != null) {
               LookupCountry subPartnerCountry1 = countryRepository.findByCountryName(subPartnersPhysicalAddress.getPhysicalAddressCountry().getCountryName());
               subPartnerDetails.setLookupCountry1(subPartnerCountry1);
            }
         }

         if (subPartnersMailingAddress != null) {
            subPartnerDetails.setAddressLineOne(subPartnersMailingAddress.getMailingAddress1());
            subPartnerDetails.setAddressLineTwo(subPartnersMailingAddress.getMailingAddress2());
            subPartnerDetails.setCity(subPartnersMailingAddress.getMailingAddressCity());
            subPartnerDetails.setState(subPartnersMailingAddress.getMailingAddressStateOrProvince());
            subPartnerDetails.setCreatedBy(Integer.parseInt(subPartner.getGoId()));
            subPartnerDetails.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            subPartnerDetails.setModifiedBy(Integer.parseInt(subPartner.getGoId()));
            subPartnerDetails.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));

            Country c = subPartnersMailingAddress.getMailingAddressCountry();
            if (c != null) {
               LookupCountry lcm = countryRepository.findByCountryName(c.getCountryName());
               if (lcm != null) {
                  subPartnerDetails.setLookupCountry2(lcm);
               }
            }
         }
         subPartnerDetails.setPartnerGoId(goIdSequence.getGoId());
         partnerRepository.save(subPartnerDetails);

         try {
            PartnerContact partnerContact = null;

            if (subPartnerPrimaryContact != null) {
               partnerContact = new PartnerContact();
               com.ccighgo.service.transport.partner.beans.subpartnerdetail.Salutation slt = subPartnerPrimaryContact.getSalutation();
               if (slt != null) {
                  Salutation s = salutationRepository.findBySalutationName(slt.getSalutationName());
                  partnerContact.setSalutation(s);
               }
               partnerContact.setTitle(subPartnerPrimaryContact.getTitle());
               partnerContact.setFirstName(subPartnerPrimaryContact.getFirstName());
               partnerContact.setLastName(subPartnerPrimaryContact.getLastName());
               partnerContact.setEmail(subPartnerPrimaryContact.getEmail());
               partnerContact.setPhone(subPartnerPrimaryContact.getPhone());
               partnerContact.setEmergencyPhone(subPartnerPrimaryContact.getEmergencyPhone());
               partnerContact.setReceiveNotificationEmails((byte) (subPartnerPrimaryContact.isReciveNotificationemailfromcc() ? 1 : 0));
               partnerContact.setSkypeId(subPartnerPrimaryContact.getSkypeId());
               partnerContact.setWebsite(subPartnerPrimaryContact.getWebsite());
               partnerContact.setIsPrimary((byte) 1);
               partnerContact.setCreatedBy(Integer.parseInt(subPartner.getGoId()));
               partnerContact.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               partnerContact.setModifiedBy(Integer.parseInt(subPartner.getGoId()));
               partnerContact.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               partnerContact.setPartner(subPartnerDetails);

               partnerContactRepository.save(partnerContact);

            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, LOGGER);
         }

         PartnerUser partnerUser = new PartnerUser();
         partnerUser.setActive(CCIConstants.ACTIVE);
         partnerUser.setIsPrimary(CCIConstants.ACTIVE);
         partnerUser.setPartner(subPartnerDetails);
         partnerUser.setLogin(login);
         partnerUserRepository.saveAndFlush(partnerUser);

         responce.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SUB_PARTNER_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_CREATE_SUB_PARTNER.getValue(),
               messageUtil.getMessage(SubPartnerMessageConstants.FAILED_CREATE_SUB_PARTNER)));
         LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.FAILED_CREATE_SUB_PARTNER));
      }
      return responce;
   }

   @Transactional
   public WSDefaultResponse UpdateSubPartnerDetail3(com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail subPartner) {
      WSDefaultResponse responce = new WSDefaultResponse();
      try {
         Partner subPartnerDetails = partnerRepository.findOne(Integer.parseInt(subPartner.getGoId()));
         if (subPartnerDetails == null) {
            responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_SUB_PARTNER_DETAILS.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS)));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.ERROR_GET_SUB_PARTNER_DETAILS));
            return responce;
         }
         if (loginRepository.findByLoginName(subPartner.getPartnerDetail().getUsername()) != null) {
            responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.SUB_PARTNER_UPDATE_USER_USERNAME_EXIST.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.SUB_PARTNER_CREATE_USER_USERNAME_EXIST)));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.SUB_PARTNER_CREATE_USER_USERNAME_EXIST));
            return responce;
         }

         if (subPartner.getSubPartnerPrimaryContact() != null && loginRepository.findByEmail(subPartner.getSubPartnerPrimaryContact().getEmail()) != null) {
            responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.SUB_PARTNER_UPDATE_USER_EMAIL_EXIST.getValue(),
                  messageUtil.getMessage(SubPartnerMessageConstants.SUB_PARTNER_CREATE_USER_EMAIL_EXIST)));
            LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.SUB_PARTNER_CREATE_USER_EMAIL_EXIST));
            return responce;
         }
         try {
            GoIdSequence goIdSequence = goIdSequenceRepository.findOne(Integer.parseInt(subPartner.getGoId()));
            Login login = loginRepository.findByGoId(goIdSequence);
            // TODO password History
            // login.getPasswordHistories().add
            login.setPassword(PasswordUtil.hashKey(subPartner.getPartnerDetail().getPassword()));
            login.setLoginName(subPartner.getPartnerDetail().getUsername());
            loginRepository.saveAndFlush(login);
         } catch (Exception e) {
            ExceptionUtil.logException(e, LOGGER);
         }
         Details subPartnerDetailInfo = subPartner.getPartnerDetail();
         if (subPartnerDetailInfo != null) {
            subPartnerDetails.setCompanyName(subPartnerDetailInfo.getAgencyName());
            subPartnerDetails.setNeedPartnerReview((byte) (subPartnerDetailInfo.isNeedsPartnerReview() ? 1 : 0));
            subPartnerDetails.setDeliverDSForms((byte) (subPartnerDetailInfo.isRecivevisaforms() ? 1 : 0));
            subPartnerDetails.setPayGreenheartDirectly((byte) (subPartnerDetailInfo.isPayGreenHeartDirectly() ? 1 : 0));
         }
         List<PartnerContact> partnerContacts = subPartnerDetails.getPartnerContacts();
         PartnerContact partnerContact = null;
         for (PartnerContact contact : partnerContacts) {
            if (contact.getIsPrimary() == 1) {
               partnerContact = contact;
               break;
            }
         }
         if (partnerContact == null)
            partnerContact = new PartnerContact();

         SubPartnersPrimaryContact subPartnerPrimaryContact = subPartner.getSubPartnerPrimaryContact();
         if (subPartnerPrimaryContact != null) {
            com.ccighgo.service.transport.partner.beans.subpartnerdetail.Salutation slt = subPartnerPrimaryContact.getSalutation();
            if (slt != null) {
               Salutation s = salutationRepository.findBySalutationName(slt.getSalutationName());
               partnerContact.setSalutation(s);
            }
            partnerContact.setTitle(subPartnerPrimaryContact.getTitle());
            partnerContact.setFirstName(subPartnerPrimaryContact.getFirstName());
            partnerContact.setLastName(subPartnerPrimaryContact.getLastName());
            partnerContact.setEmail(subPartnerPrimaryContact.getEmail());
            partnerContact.setPhone(subPartnerPrimaryContact.getPhone());
            partnerContact.setEmergencyPhone(subPartnerPrimaryContact.getEmergencyPhone());
            partnerContact.setReceiveNotificationEmails((byte) (subPartnerPrimaryContact.isReciveNotificationemailfromcc() ? 1 : 0));
            partnerContact.setSkypeId(subPartnerPrimaryContact.getSkypeId());
            partnerContact.setWebsite(subPartnerPrimaryContact.getWebsite());
            partnerContact.setModifiedBy(Integer.parseInt(subPartner.getGoId()));
            partnerContactRepository.saveAndFlush(partnerContact);

         }
         SubPartnersMailingAddress subPartnersMailingAddress = subPartner.getSubPartnerMailingAddress();

         SubPartnersPhysicalAddress subPartnersPhysicalAddress = subPartner.getSubPartnerPhysicalAddress();
         if (subPartnersPhysicalAddress != null) {
            subPartnerDetails.setPhysicalAddressLineOne(subPartnersPhysicalAddress.getPhysicalAddress1());
            subPartnerDetails.setPhysicalAddressLineTwo(subPartnersPhysicalAddress.getPhysicalAddress2());
            subPartnerDetails.setPhysicalCity(subPartnersPhysicalAddress.getPhysicalAddressCity());
            subPartnerDetails.setPhysicalstate(subPartnersPhysicalAddress.getPhysicalAddressStateOrProvince());
            subPartnerDetails.setPhysicalZipcode(subPartnersPhysicalAddress.getPhysicalAddressZipCode());
            if (subPartnersPhysicalAddress.getPhysicalAddressCountry() != null) {
               LookupCountry subPartnerCountry1 = countryRepository.findByCountryName(subPartnersPhysicalAddress.getPhysicalAddressCountry().getCountryName());
               subPartnerDetails.setLookupCountry1(subPartnerCountry1);
            }
         }

         if (subPartnersMailingAddress != null) {
            subPartnerDetails.setAddressLineOne(subPartnersMailingAddress.getMailingAddress1());
            subPartnerDetails.setAddressLineTwo(subPartnersMailingAddress.getMailingAddress2());
            subPartnerDetails.setCity(subPartnersMailingAddress.getMailingAddressCity());
            subPartnerDetails.setState(subPartnersMailingAddress.getMailingAddressStateOrProvince());
            Country c = subPartnersMailingAddress.getMailingAddressCountry();
            if (c != null) {
               LookupCountry lcm = countryRepository.findByCountryName(c.getCountryName());
               if (lcm != null) {
                  subPartnerDetails.setLookupCountry2(lcm);
               }
            }
         }
         partnerRepository.saveAndFlush(subPartnerDetails);

         responce.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SUB_PARTNER_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_SUB_PARTNER.getValue(),
               messageUtil.getMessage(SubPartnerMessageConstants.FAILED_UPDATE_SUB_PARTNER)));
         LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.FAILED_UPDATE_SUB_PARTNER));
      }
      return responce;
   }

   @Override
   @Transactional
   public WSDefaultResponse UpdateSubPartnerDetail(com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail subPartner) {
      WSDefaultResponse responce = new WSDefaultResponse();
      try {
         Partner subPartnerDetails = partnerRepository.findOne(Integer.parseInt(subPartner.getGoId()));
         Details subPartnerDetailInfo = subPartner.getPartnerDetail();
         if (subPartnerDetailInfo != null) {
            subPartnerDetails.setPartnerLogo(subPartnerDetailInfo.getLogoImageURL());
            subPartnerDetails.setCompanyName(subPartnerDetailInfo.getAgencyName());
            subPartnerDetails.setNeedPartnerReview((byte) (subPartnerDetailInfo.isNeedsPartnerReview() ? 1 : 0));
            subPartnerDetails.setDeliverDSForms((byte) (subPartnerDetailInfo.isRecivevisaforms() ? 1 : 0));
            subPartnerDetails.setPayGreenheartDirectly((byte) (subPartnerDetailInfo.isPayGreenHeartDirectly() ? 1 : 0));
         }
         subPartnerDetails.setMailingAddressIsSameAsPhysicalAdress((byte) 1);
         SubPartnersPrimaryContact subPartnerPrimaryContact = subPartner.getSubPartnerPrimaryContact();
         SubPartnersMailingAddress subPartnersMailingAddress = subPartner.getSubPartnerMailingAddress();

         List<PartnerUser> partnerUsers = subPartnerDetails.getPartnerUsers();
         PartnerUser partnerUser = new PartnerUser();
         if (partnerUsers != null && partnerUsers.size() > 0) {
            for (PartnerUser puser : partnerUsers) {
               if (puser.getPartner() != null)
                  if (puser.getPartner().getPartnerGoId() == Integer.valueOf(subPartner.getGoId())) {
                     partnerUser = puser;
                     break;
                  }
            }
            Login login = partnerUser.getLogin();
            if (login != null) {
               login.setLoginName(subPartner.getPartnerDetail().getUsername());
               login.setPassword(subPartner.getPartnerDetail().getPassword());
               login.setActive(subPartner.isActive() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               loginRepository.save(login);
            }
         }

         SubPartnersPhysicalAddress subPartnersPhysicalAddress = subPartner.getSubPartnerPhysicalAddress();
         if (subPartnersPhysicalAddress != null) {
            subPartnerDetails.setPhysicalAddressLineOne(subPartnersPhysicalAddress.getPhysicalAddress1());
            subPartnerDetails.setPhysicalAddressLineTwo(subPartnersPhysicalAddress.getPhysicalAddress2());
            subPartnerDetails.setPhysicalCity(subPartnersPhysicalAddress.getPhysicalAddressCity());
            subPartnerDetails.setPhysicalstate(subPartnersPhysicalAddress.getPhysicalAddressStateOrProvince());
            subPartnerDetails.setPhysicalZipcode(subPartnersPhysicalAddress.getPhysicalAddressZipCode());

            if (subPartnersPhysicalAddress.getPhysicalAddressCountry() != null) {
               LookupCountry subPartnerCountry1 = countryRepository.findByCountryName(subPartnersPhysicalAddress.getPhysicalAddressCountry().getCountryName());
               subPartnerDetails.setLookupCountry1(subPartnerCountry1);
            }
         }

         if (subPartnersMailingAddress != null) {
            subPartnerDetails.setAddressLineOne(subPartnersMailingAddress.getMailingAddress1());
            subPartnerDetails.setAddressLineTwo(subPartnersMailingAddress.getMailingAddress2());
            subPartnerDetails.setCity(subPartnersMailingAddress.getMailingAddressCity());
            subPartnerDetails.setState(subPartnersMailingAddress.getMailingAddressStateOrProvince());
            subPartnerDetails.setCreatedBy(Integer.parseInt(subPartner.getGoId()));
            subPartnerDetails.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            subPartnerDetails.setModifiedBy(Integer.parseInt(subPartner.getGoId()));
            subPartnerDetails.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));

            Country c = subPartnersMailingAddress.getMailingAddressCountry();
            if (c != null) {
               LookupCountry lcm = countryRepository.findByCountryName(c.getCountryName());
               if (lcm != null) {
                  subPartnerDetails.setLookupCountry2(lcm);
               }
            }
         }
         partnerRepository.save(subPartnerDetails);

         List<PartnerContact> partnerContacts = subPartnerDetails.getPartnerContacts();
         PartnerContact partnerContact = null;
         for (PartnerContact contact : partnerContacts) {
            if (contact.getIsPrimary() == 1) {
               partnerContact = contact;
               break;
            }
         }
         if (partnerContact == null)
            partnerContact = new PartnerContact();

         if (subPartnerPrimaryContact != null) {
            com.ccighgo.service.transport.partner.beans.subpartnerdetail.Salutation slt = subPartnerPrimaryContact.getSalutation();
            if (slt != null) {
               Salutation s = salutationRepository.findBySalutationName(slt.getSalutationName());
               partnerContact.setSalutation(s);
            }
            partnerContact.setTitle(subPartnerPrimaryContact.getTitle());
            partnerContact.setFirstName(subPartnerPrimaryContact.getFirstName());
            partnerContact.setLastName(subPartnerPrimaryContact.getLastName());
            partnerContact.setEmail(subPartnerPrimaryContact.getEmail());
            partnerContact.setPhone(subPartnerPrimaryContact.getPhone());
            partnerContact.setEmergencyPhone(subPartnerPrimaryContact.getEmergencyPhone());
            partnerContact.setReceiveNotificationEmails((byte) (subPartnerPrimaryContact.isReciveNotificationemailfromcc() ? 1 : 0));
            partnerContact.setSkypeId(subPartnerPrimaryContact.getSkypeId());
            partnerContact.setWebsite(subPartnerPrimaryContact.getWebsite());
            partnerContact.setModifiedBy(Integer.parseInt(subPartner.getGoId()));
            partnerContactRepository.saveAndFlush(partnerContact);
         }

         responce.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SUB_PARTNER_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_SUB_PARTNER.getValue(),
               messageUtil.getMessage(SubPartnerMessageConstants.FAILED_UPDATE_SUB_PARTNER)));
         LOGGER.error(messageUtil.getMessage(SubPartnerMessageConstants.FAILED_UPDATE_SUB_PARTNER));
      }
      return responce;
   }

}
