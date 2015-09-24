/**
 * 
 */
package com.ccighgo.service.components.partner.subpartner;

import java.util.ArrayList;
import java.util.List;

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
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerStatus;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LoginUserTypeRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTopicRepository;
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
         
         //Agency Details
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
         
         //primary contact
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
         subPartnerPrimaryContact.setTypeOfPartnerUser(CCIConstants.EMPTY_DATA); // need to change type here
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

         // Notes
         List<PartnerNote> PartnerNoteDBList = partnerSubPartner.getPartnerNotes();
         SubPartnerNotes subPartnerNotes = new SubPartnerNotes();
         List<SubPartnerNote> subPartnerNoteList = new ArrayList<SubPartnerNote>();
         for (PartnerNote partnerNote : PartnerNoteDBList) {
            SubPartnerNote SubPartnerNote = new SubPartnerNote();
            SubPartnerNote.setSubPartnerNotesId(partnerNote.getPartnerNotesId());
            SubPartnerNote.setSubpartnerNote(partnerNote.getPartnerNote());
            subPartnerNoteList.add(SubPartnerNote);
         }
         subPartnerNotes.getSubPartnerNotes().addAll(subPartnerNoteList);
        // subPartner.setSubPartnerNotes(subPartnerNotes);
         
         //Note Topics
         List<PartnerNoteTopic> partnerNoteTopicDBList = partnerSubPartner.getPartnerNoteTopics();
         SubPartnerNoteTopics subPartnerNoteTopics = new SubPartnerNoteTopics();
         List<SubPartnerNoteTopic> subPartnerNoteTopicList = new ArrayList<SubPartnerNoteTopic>();
         for (PartnerNoteTopic partnerNoteTopic : partnerNoteTopicDBList) {
            SubPartnerNoteTopic subPartnerNoteTopic = new SubPartnerNoteTopic();
            subPartnerNoteTopic.setSubPartnerNoteTopicId(partnerNoteTopic.getPartnerNoteTopicId());
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
      if(subPartner == null){
         //TODO Status
         
      }
      Partner subPartnerDetails = new Partner(); 
      
      //agency details
      SubPartnerAgency SubPartnerAgency = subPartner.getSubPartnerAgency();
      subPartnerDetails.setCompanyName(SubPartnerAgency.getCompanyName());
      
      PartnerStatus partnerStatus = new PartnerStatus();
      partnerStatus.setPartnerStatusId(subPartner.getSubPartnerAgency().getSubPartnerStatus().getSubPartnerStatusId());
      partnerStatus.setPartnerStatusName(subPartner.getSubPartnerAgency().getSubPartnerStatus().getSubPartnerStatus());
      subPartnerDetails.setPartnerStatus(partnerStatus);
      subPartnerDetails.setNeedPartnerReview(SubPartnerAgency.getNeedPartnerReview());
      subPartnerDetails.setDeliverDSForms(SubPartnerAgency.getDeliverDSForms());
      subPartnerDetails.setPayGreenheartDirectly(SubPartnerAgency.getPayGreenheartDirectly());
      
      
      //Login And GoId
      GoIdSequence goIdSequence = new GoIdSequence();
      goIdSequence = goIdSequenceRepository.save(goIdSequence);
     
      com.ccighgo.db.entities.UserType partnerUserType = userTypeRepository.findOne(CCIConstants.PARTNER_USER_TYPE);
      if (partnerUserType == null) {
         partnerUserType = new com.ccighgo.db.entities.UserType();
      }
      List<Login> loginList = new ArrayList<Login>();
      Login login = new Login();
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
      
      //sub partner contact
      SubPartnerPrimaryContact subPartnerPrimaryContact = subPartner.getSubPartnerPrimaryContact();
      List<PartnerContact> partnerContactList =new ArrayList<PartnerContact>();
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
      partnerContactList.add(partnerContact);
      subPartnerDetails.setPartnerContacts(partnerContactList);
      
      //physical address
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
      
      //mailing address
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
      
      subPartnerDetails = partnerRepository.save(subPartnerDetails);
      List<PartnerNoteTopic> partnerNoteTopicList =  new ArrayList<PartnerNoteTopic>();
      
      for (SubPartnerNoteTopic subPartnerNoteTopic : subPartner.getSubPartnerNoteTopics().getSubPartnerNoteTopics()) {
         PartnerNoteTopic partnerNoteTopic = new PartnerNoteTopic();
         partnerNoteTopic.setPartner(subPartnerDetails);
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
         partnerNoteTopicList.add(partnerNoteTopic);
       //  partnerNoteTopicRepository.save
         
      }
      subPartnerDetails.setPartnerNoteTopics(partnerNoteTopicList);
   //   partnerNoteTopicRepository.save
      //notes
      List<PartnerNote> PartnerNoteEntityList = new ArrayList<PartnerNote>();
      
   
     /* for (SubPartnerNote subPartnerNote : subPartner.getSubPartnerNotes().getSubPartnerNotes()) {
         PartnerNote partnerNote = new PartnerNote();
         partnerNote.setPartnerNotesId(subPartnerNote.getSubPartnerNotesId());
         partnerNote.setPartnerNote(subPartnerNote.getSubpartnerNote());
         
         PartnerNoteEntityList.add(partnerNote);
      }
    //  subPartnerDetails.setPartnerN
      
      
      
      
      List<PartnerNote> PartnerNoteDBList = partnerSubPartner.getPartnerNotes();
      SubPartnerNotes subPartnerNotes = new SubPartnerNotes();
      for (PartnerNote partnerNote : PartnerNoteDBList) {
         SubPartnerNote SubPartnerNote = new SubPartnerNote();
         SubPartnerNote.setSubPartnerNotesId(partnerNote.getPartnerNotesId());
         SubPartnerNote.setSubpartnerNote(partnerNote.getPartnerNote());

         SubPartnerNoteTopic subPartnerNoteTopic = new SubPartnerNoteTopic();
         subPartnerNoteTopic.setSubPartnerNoteTopicId(partnerNote.getPartnerNoteTopic().getPartnerNoteTopicId());
         subPartnerNoteTopic.setCompetitorInfo(partnerNote.getPartnerNoteTopic().getCompetitorInfo());
         subPartnerNoteTopic.setEmbassy_VisaInfo(partnerNote.getPartnerNoteTopic().getEmbassy_VisaInfo());
         subPartnerNoteTopic.setF1(partnerNote.getPartnerNoteTopic().getF1());
         subPartnerNoteTopic.setGht(partnerNote.getPartnerNoteTopic().getGht());
         subPartnerNoteTopic.setIntern(partnerNote.getPartnerNoteTopic().getIntern());
         subPartnerNoteTopic.setJ1(partnerNote.getPartnerNoteTopic().getJ1());
         subPartnerNoteTopic.setMeeting_visit(partnerNote.getPartnerNoteTopic().getMeeting_visit());
         subPartnerNoteTopic.setPartnerNoteTopicName(partnerNote.getPartnerNoteTopic().getPartnerNoteTopicName());
         subPartnerNoteTopic.setSeasonInfo(partnerNote.getPartnerNoteTopic().getSeasonInfo());
         subPartnerNoteTopic.setStInbound(partnerNote.getPartnerNoteTopic().getStInbound());
         subPartnerNoteTopic.setTrainee(partnerNote.getPartnerNoteTopic().getTrainee());
         subPartnerNoteTopic.setW_t(partnerNote.getPartnerNoteTopic().getW_t());

         SubPartnerNote.setSubPartnerNoteTopic(subPartnerNoteTopic);
         subPartnerNotes.getSubPartnerNotes().add(SubPartnerNote);
      }
      subPartner.setSubPartnerNotes(subPartnerNotes);*/
      
      return createdSubPartner;
   }
   
   @Override
   @Transactional
   public SubPartner updateSubPartner(SubPartner subPartner) {
   
      return null;
   }
   

}
