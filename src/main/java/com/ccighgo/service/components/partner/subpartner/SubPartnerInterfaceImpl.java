/**
 * 
 */
package com.ccighgo.service.components.partner.subpartner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerContact;
import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.service.transport.partner.beans.subpartner.PartnerSubPartners;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartner;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerCountry;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerMailingAddress;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerNote;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerNoteTopic;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerNotes;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerPhysicalAddress;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerPrimaryContact;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerSeasons;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerStatus;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartners;
import com.ccighgo.utils.CCIConstants;

/**
 * @author ravi
 *
 */
@Component
public class SubPartnerInterfaceImpl implements SubPartnerInterface {
   
   @Autowired PartnerRepository partnerRepository;

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
         subPartnerPhysicalAddress.setSubPartnerCountry(subPartnerCountry1);
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
         subPartnerMailingAddress.setSubPartnerCountry(subPartnerCountry2);
         subPartner.setSubPartnerMailingAddress(subPartnerMailingAddress);

         // Status
         SubPartnerStatus subPartnerStatus = new SubPartnerStatus();
         subPartnerStatus.setSubPartnerStatus(partnerSubPartner.getPartnerStatus().getPartnerStatusName());
         subPartnerStatus.setSubPartnerStatusId(partnerSubPartner.getPartnerStatus().getPartnerStatusId());
         subPartner.setSubPartnerStatus(subPartnerStatus);

         // Notes
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
         subPartner.setSubPartnerNotes(subPartnerNotes);
      }
      return subPartner;
   }
   
   
   @Override
   @Transactional
   public SubPartner createSubPartner(SubPartner subPartner) {
   
      return null;
   }
   
   @Override
   @Transactional
   public SubPartner updateSubPartner(SubPartner subPartner) {
   
      return null;
   }
   

}
