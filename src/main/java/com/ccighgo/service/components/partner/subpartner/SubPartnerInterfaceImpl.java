/**
 * 
 */
package com.ccighgo.service.components.partner.subpartner;

import java.util.ArrayList;
import java.util.List;

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
import com.ccighgo.service.transport.partner.beans.subpartner.PartnerSubPartners;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartner;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerAgency;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerCountry;
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
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.PasswordUtil;
import com.ccighgo.utils.UuidUtils;

/**
 * @author ravi
 *
 */
@Component
public class SubPartnerInterfaceImpl implements SubPartnerInterface {
   
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
      
      SubPartners sPart= new SubPartners();
      sPart.setSubPartnerId(123);
      sPart.setSubPartnerFirstName("Super");
      sPart.setSubPartnerLastName("Man");
      sPart.setSubPartnerCountry(subPartnerCountry1);
      sPart.setSubPartnerStatus(subPartnerStatus1);
      sPart.getSubPartnerSeasons().addAll(subPartnerSeasons);
      
      SubPartners sPart1= new SubPartners();
      sPart1.setSubPartnerId(1234);
      sPart1.setSubPartnerFirstName("Bat");
      sPart1.setSubPartnerLastName("Man");
      sPart1.setSubPartnerCountry(subPartnerCountry2);
      sPart1.setSubPartnerStatus(subPartnerStatus2);
      sPart1.getSubPartnerSeasons().addAll(subPartnerSeasons);
      
      SubPartners sPart2= new SubPartners();
      sPart2.setSubPartnerId(1234);
      sPart2.setSubPartnerFirstName("Iron");
      sPart2.setSubPartnerLastName("Man");
      sPart2.setSubPartnerCountry(subPartnerCountry3);
      sPart2.setSubPartnerStatus(subPartnerStatus2);
      
      SubPartners sPart3= new SubPartners();
      sPart3.setSubPartnerId(1234);
      sPart3.setSubPartnerFirstName("Ant");
      sPart3.setSubPartnerLastName("Man");
      sPart3.setSubPartnerCountry(subPartnerCountry4);
      sPart3.setSubPartnerStatus(subPartnerStatus1);
      
      SubPartners sPart4= new SubPartners();
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
   public SubPartner viewSubPartners(String subPartnerId) {
      SubPartner subPartner = new SubPartner();
      Partner partnerSubPartner = partnerRepository.findOne(Integer.valueOf(subPartnerId));
      if (partnerSubPartner.getIsSubPartner() == CCIConstants.ACTIVE) {
         subPartner.setSubPartnerId(partnerSubPartner.getPartnerGoId());

         // Agency Details
         SubPartnerAgency subPartnerAgency = new SubPartnerAgency();
         subPartnerAgency.setCompanyName(partnerSubPartner.getCompanyName());

         SubPartnerStatus subPartnerStatus = new SubPartnerStatus();
         subPartnerStatus.setSubPartnerStatusId(partnerSubPartner.getPartnerStatus().getPartnerStatusId());
         subPartnerStatus.setSubPartnerStatus(partnerSubPartner.getPartnerStatus().getPartnerStatusName());
         subPartnerAgency.setSubPartnerStatus(subPartnerStatus);
         subPartnerAgency.setNeedPartnerReview(partnerSubPartner.getNeedPartnerReview());
         subPartnerAgency.setDeliverDSForms(partnerSubPartner.getDeliverDSForms());
         subPartnerAgency.setPayGreenheartDirectly(partnerSubPartner.getPayGreenheartDirectly());
         subPartnerAgency.setUserName(partnerSubPartner.getGoIdSequence().getLogin().iterator().next().getLoginName());
         subPartner.setSubPartnerAgency(subPartnerAgency);

         // primary contact
         SubPartnerPrimaryContact subPartnerPrimaryContact = new SubPartnerPrimaryContact();
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
         // TODO: // need to change type here
         subPartner.setSubPartnerPrimaryContact(subPartnerPrimaryContact);
         // TODO: need to add sub partner seasons

         // Physical Address
         SubPartnerPhysicalAddress subPartnerPhysicalAddress = new SubPartnerPhysicalAddress();
         subPartnerPhysicalAddress.setPhysicalAddressLineOne(partnerSubPartner.getPhysicalAddressLineOne());
         subPartnerPhysicalAddress.setPhysicalAddressLineTwo(partnerSubPartner.getPhysicalAddressLineTwo());
         subPartnerPhysicalAddress.setPhysicalCity(partnerSubPartner.getPhysicalCity());
         subPartnerPhysicalAddress.setPhysicalstate(partnerSubPartner.getPhysicalstate());
         subPartnerPhysicalAddress.setPhysicalZipcode(partnerSubPartner.getPhysicalZipcode());

         SubPartnerCountry subPartnerCountry1 = new SubPartnerCountry();
         subPartnerCountry1.setSubPartnerCountry(partnerSubPartner.getLookupCountry1().getCountryName());
         subPartnerCountry1.setSubPartnerCountryId(partnerSubPartner.getLookupCountry1().getCountryId());
         subPartnerPhysicalAddress.setPhysicalSubPartnerCountry(subPartnerCountry1);
         subPartner.setSubPartnerPhysicalAddress(subPartnerPhysicalAddress);

         // Mailing Address
         SubPartnerMailingAddress subPartnerMailingAddress = new SubPartnerMailingAddress();
         subPartnerMailingAddress.setAddressLineOne(partnerSubPartner.getAddressLineOne());
         subPartnerMailingAddress.setAddressLineTwo(partnerSubPartner.getAddressLineTwo());
         subPartnerMailingAddress.setCity(partnerSubPartner.getCity());
         subPartnerMailingAddress.setState(partnerSubPartner.getState());
         subPartnerMailingAddress.setZipcode(partnerSubPartner.getZipcode());

         SubPartnerCountry subPartnerCountry2 = new SubPartnerCountry();
         subPartnerCountry2.setSubPartnerCountry(partnerSubPartner.getLookupCountry2().getCountryName());
         subPartnerCountry2.setSubPartnerCountryId(partnerSubPartner.getLookupCountry2().getCountryId());
         subPartnerMailingAddress.setMailingSubPartnerCountry(subPartnerCountry2);
         subPartner.setSubPartnerMailingAddress(subPartnerMailingAddress);
         subPartner.setNoteTopicCount(partnerSubPartner.getPartnerNoteTopics().size());

         // Note Topics
         List<PartnerNoteTopic> partnerNoteTopicDBList = partnerSubPartner.getPartnerNoteTopics();
         SubPartnerNoteTopics subPartnerNoteTopics = new SubPartnerNoteTopics();
         List<SubPartnerNoteTopic> subPartnerNoteTopicList = new ArrayList<SubPartnerNoteTopic>();
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
            subPartnerNoteTopicList.add(subPartnerNoteTopic);
         }
         subPartnerNoteTopics.getSubPartnerNoteTopics().addAll(subPartnerNoteTopicList);
         subPartner.setSubPartnerNoteTopics(subPartnerNoteTopics);

      }
      return subPartner;
   }
   
   
   @Override
   @Transactional
   public SubPartner createSubPartner(SubPartner subPartner) {
      SubPartner createdSubPartner = new SubPartner();
      if (subPartner == null) {
         // TODO Status

      }
      Partner subPartnerDetails = new Partner();
      String partnerGuid = UuidUtils.nextHexUUID();
      subPartnerDetails.setPartnerGuid(partnerGuid);
      // agency details
      SubPartnerAgency SubPartnerAgency = subPartner.getSubPartnerAgency();
      subPartnerDetails.setCompanyName(SubPartnerAgency.getCompanyName());

      PartnerStatus partnerStatus = new PartnerStatus();
      partnerStatus.setPartnerStatusId(subPartner.getSubPartnerAgency().getSubPartnerStatus().getSubPartnerStatusId());
      partnerStatus.setPartnerStatusName(subPartner.getSubPartnerAgency().getSubPartnerStatus().getSubPartnerStatus());
      subPartnerDetails.setPartnerStatus(partnerStatus);
      subPartnerDetails.setNeedPartnerReview(SubPartnerAgency.getNeedPartnerReview());
      subPartnerDetails.setDeliverDSForms(SubPartnerAgency.getDeliverDSForms());
      subPartnerDetails.setPayGreenheartDirectly(SubPartnerAgency.getPayGreenheartDirectly());

      // Login And GoId
      GoIdSequence goIdSequence = new GoIdSequence();
      goIdSequence = goIdSequenceRepository.save(goIdSequence);

      com.ccighgo.db.entities.UserType partnerUserType = userTypeRepository.findOne(CCIConstants.PARTNER_USER_TYPE);
      if (partnerUserType == null) {
         partnerUserType = new com.ccighgo.db.entities.UserType();
      }
      List<Login> loginList = new ArrayList<Login>();
      Login login = new Login();
      // need to set active field
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
      SubPartnerPhysicalAddress subPartnerPhysicalAddress = subPartner.getSubPartnerPhysicalAddress();
      subPartnerDetails.setPhysicalAddressLineOne(subPartnerPhysicalAddress.getPhysicalAddressLineOne());
      subPartnerDetails.setPhysicalAddressLineTwo(subPartnerPhysicalAddress.getPhysicalAddressLineTwo());
      subPartnerDetails.setPhysicalCity(subPartnerPhysicalAddress.getPhysicalCity());
      subPartnerDetails.setPhysicalstate(subPartnerPhysicalAddress.getPhysicalstate());
      subPartnerDetails.setPhysicalZipcode(subPartnerPhysicalAddress.getPhysicalZipcode());

      LookupCountry subPartnerCountry1 = new LookupCountry();
      subPartnerCountry1.setCountryCode(subPartner.getSubPartnerPhysicalAddress().getPhysicalSubPartnerCountry().getSubPartnerCountry());
      subPartnerCountry1.setCountryId(subPartner.getSubPartnerPhysicalAddress().getPhysicalSubPartnerCountry().getSubPartnerCountryId());
      subPartnerDetails.setLookupCountry1(subPartnerCountry1);

      // mailing address
      SubPartnerMailingAddress subPartnerMailingAddress = subPartner.getSubPartnerMailingAddress();
      subPartnerDetails.setAddressLineOne(subPartnerMailingAddress.getAddressLineOne());
      subPartnerDetails.setAddressLineTwo(subPartnerMailingAddress.getAddressLineTwo());
      subPartnerDetails.setCity(subPartnerMailingAddress.getCity());
      subPartnerDetails.setState(subPartnerMailingAddress.getState());
      subPartnerDetails.setZipcode(subPartnerMailingAddress.getZipcode());

      LookupCountry subPartnerCountry2 = new LookupCountry();
      subPartnerCountry2.setCountryCode(subPartner.getSubPartnerMailingAddress().getMailingSubPartnerCountry().getSubPartnerCountry());
      subPartnerCountry2.setCountryId(subPartner.getSubPartnerMailingAddress().getMailingSubPartnerCountry().getSubPartnerCountryId());
      subPartnerDetails.setLookupCountry2(subPartnerCountry2);

      subPartnerDetails.setCreatedBy(goIdSequence.getGoId());
      subPartnerDetails.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      subPartnerDetails.setModifiedBy(goIdSequence.getGoId());
      subPartnerDetails.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      subPartnerDetails = partnerRepository.save(subPartnerDetails);

      // sub partner office
      SubPartnerPhysicalAddress subPartnerOfficeAddress = subPartner.getSubPartnerPhysicalAddress();
      PartnerOffice partnerOffice = new PartnerOffice();
      partnerOffice.setAdressOne(subPartnerOfficeAddress.getPhysicalAddressLineOne());
      partnerOffice.setAdressTwo(subPartnerOfficeAddress.getPhysicalAddressLineTwo());
      partnerOffice.setCity(subPartnerPhysicalAddress.getPhysicalCity());
      partnerOffice.setCreatedBy(subPartnerDetails.getCreatedBy());
      partnerOffice.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      partnerOffice.setFaxNumber(subPartner.getSubPartnerPrimaryContact().getFax());

      LookupCountry subPartnerCountry3 = new LookupCountry();
      subPartnerCountry3.setCountryId(subPartnerOfficeAddress.getPhysicalSubPartnerCountry().getSubPartnerCountryId());
      subPartnerCountry3.setCountryCode(subPartnerOfficeAddress.getPhysicalSubPartnerCountry().getSubPartnerCountry());
      partnerOffice.setLookupCountry(subPartnerCountry3);

      partnerOffice.setModifiedBy(subPartnerDetails.getModifiedBy());
      partnerOffice.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      partnerOffice.setPartner(subPartnerDetails);
      partnerOffice.setPartnerOfficeType(partnerOfficeTypeRepository.findOne(Integer.valueOf(subPartner.getSubPartnerPrimaryContact().getTypeOfPartnerUser())));
      partnerOffice.setPhoneNumber(subPartner.getSubPartnerPrimaryContact().getPhone());
      partnerOffice.setPostalCode(subPartnerOfficeAddress.getPhysicalZipcode());
      partnerOffice.setState(subPartnerOfficeAddress.getPhysicalstate());
      partnerOffice = partnerOfficeRepository.save(partnerOffice);

      // sub partner contact
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

      // Note Topics
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
      // createdSubPartner = viewSubPartners(subPartnerDetails.getPartnerGoId().toString());
      return createdSubPartner;
   }
   
   @Override
   @Transactional
   public SubPartner updateSubPartner(SubPartner subPartner) {

      SubPartner updatedSubPartner = null;
      Partner subPartnerDetails = partnerRepository.findOne(subPartner.getSubPartnerId());

      // agency details
      SubPartnerAgency SubPartnerAgency = subPartner.getSubPartnerAgency();
      subPartnerDetails.setCompanyName(SubPartnerAgency.getCompanyName());
      subPartnerDetails.getPartnerStatus().setPartnerStatusId(subPartner.getSubPartnerAgency().getSubPartnerStatus().getSubPartnerStatusId());
      subPartnerDetails.getPartnerStatus().setPartnerStatusName(subPartner.getSubPartnerAgency().getSubPartnerStatus().getSubPartnerStatus());
      subPartnerDetails.setNeedPartnerReview(SubPartnerAgency.getNeedPartnerReview());
      subPartnerDetails.setDeliverDSForms(SubPartnerAgency.getDeliverDSForms());
      subPartnerDetails.setPayGreenheartDirectly(SubPartnerAgency.getPayGreenheartDirectly());

      // Login
      subPartnerDetails.getGoIdSequence().getLogin().iterator().next().setLoginName(subPartner.getSubPartnerAgency().getUserName());
      subPartnerDetails.getGoIdSequence().getLogin().iterator().next().setModifiedBy(subPartner.getSubPartnerId());
      subPartnerDetails.getGoIdSequence().getLogin().iterator().next().setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      subPartnerDetails.getGoIdSequence().getLogin().iterator().next().setEmail(subPartner.getSubPartnerPrimaryContact().getEmail());

      // physical address
      SubPartnerPhysicalAddress subPartnerPhysicalAddress = subPartner.getSubPartnerPhysicalAddress();
      subPartnerDetails.setPhysicalAddressLineOne(subPartnerPhysicalAddress.getPhysicalAddressLineOne());
      subPartnerDetails.setPhysicalAddressLineTwo(subPartnerPhysicalAddress.getPhysicalAddressLineTwo());
      subPartnerDetails.setPhysicalCity(subPartnerPhysicalAddress.getPhysicalCity());
      subPartnerDetails.setPhysicalstate(subPartnerPhysicalAddress.getPhysicalstate());
      subPartnerDetails.setPhysicalZipcode(subPartnerPhysicalAddress.getPhysicalZipcode());

      LookupCountry subPartnerCountry1 = new LookupCountry();
      subPartnerCountry1.setCountryCode(subPartner.getSubPartnerPhysicalAddress().getPhysicalSubPartnerCountry().getSubPartnerCountry());
      subPartnerCountry1.setCountryId(subPartner.getSubPartnerPhysicalAddress().getPhysicalSubPartnerCountry().getSubPartnerCountryId());
      subPartnerDetails.setLookupCountry1(subPartnerCountry1);

      // mailing address
      SubPartnerMailingAddress subPartnerMailingAddress = subPartner.getSubPartnerMailingAddress();
      subPartnerDetails.setAddressLineOne(subPartnerMailingAddress.getAddressLineOne());
      subPartnerDetails.setAddressLineTwo(subPartnerMailingAddress.getAddressLineTwo());
      subPartnerDetails.setCity(subPartnerMailingAddress.getCity());
      subPartnerDetails.setState(subPartnerMailingAddress.getState());
      subPartnerDetails.setZipcode(subPartnerMailingAddress.getZipcode());

      LookupCountry subPartnerCountry2 = new LookupCountry();
      subPartnerCountry2.setCountryCode(subPartner.getSubPartnerMailingAddress().getMailingSubPartnerCountry().getSubPartnerCountry());
      subPartnerCountry2.setCountryId(subPartner.getSubPartnerMailingAddress().getMailingSubPartnerCountry().getSubPartnerCountryId());
      subPartnerDetails.setLookupCountry2(subPartnerCountry2);

      subPartnerDetails.setModifiedBy(subPartner.getSubPartnerId());
      subPartnerDetails.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));

      // sub partner office
      List<PartnerOffice> partnerOfficeList = new ArrayList<PartnerOffice>();
      SubPartnerPhysicalAddress subPartnerOfficeAddress = subPartner.getSubPartnerPhysicalAddress();
      PartnerOffice partnerOffice = subPartnerDetails.getPartnerOffices().iterator().next();
      partnerOffice.setAdressOne(subPartnerOfficeAddress.getPhysicalAddressLineOne());
      partnerOffice.setAdressTwo(subPartnerOfficeAddress.getPhysicalAddressLineTwo());
      partnerOffice.setCity(subPartnerPhysicalAddress.getPhysicalCity());
      partnerOffice.setFaxNumber(subPartner.getSubPartnerPrimaryContact().getFax());

      LookupCountry subPartnerCountry3 = new LookupCountry();
      subPartnerCountry3.setCountryId(subPartnerOfficeAddress.getPhysicalSubPartnerCountry().getSubPartnerCountryId());
      subPartnerCountry3.setCountryCode(subPartnerOfficeAddress.getPhysicalSubPartnerCountry().getSubPartnerCountry());
      partnerOffice.setLookupCountry(subPartnerCountry3);

      partnerOffice.setModifiedBy(subPartner.getSubPartnerId());
      partnerOffice.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      partnerOffice.setPartnerOfficeType(partnerOfficeTypeRepository.findOne(Integer.valueOf(subPartner.getSubPartnerPrimaryContact().getTypeOfPartnerUser())));
      partnerOffice.setPhoneNumber(subPartner.getSubPartnerPrimaryContact().getPhone());
      partnerOffice.setPostalCode(subPartnerOfficeAddress.getPhysicalZipcode());
      partnerOffice.setState(subPartnerOfficeAddress.getPhysicalstate());

      partnerOfficeList.add(partnerOffice);
      subPartnerDetails.setPartnerOffices(partnerOfficeList);

      // sub partner contact
      List<PartnerContact> partnerContactList = new ArrayList<PartnerContact>();
      SubPartnerPrimaryContact subPartnerPrimaryContact = subPartner.getSubPartnerPrimaryContact();
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
      subPartnerDetails.setPartnerContacts(partnerContactList);

      // TODO: notes need to update if required
      partnerRepository.saveAndFlush(subPartnerDetails);
      updatedSubPartner = viewSubPartners(subPartnerDetails.getPartnerGoId().toString());

      return updatedSubPartner;
   }
   

}
