/**
 * 
 */
package com.ccighgo.service.components.participants;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ccighgo.service.transport.participant.beans.participantsactivelist.ActiveParticipant;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantApplicationStatus;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantCountry;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantPlacementStatus;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantProgramOption;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantSeason;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantSubPartner;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantType;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantsActiveList;
import com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantLead;
import com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantsLeadList;

/**
 * @author ravi
 *
 */
@Component
public class ParticipantsInterfaceImpl implements ParticipantsInterface {

   @Override
   public ParticipantsActiveList getActiveParticipantsList(String partnerId) {
      ParticipantsActiveList participantsActiveList = new ParticipantsActiveList();
      participantsActiveList.setParticipantCount(2);
      List<ActiveParticipant> participantsList = new ArrayList<ActiveParticipant>();

      ParticipantProgramOption participantProgramOption = new ParticipantProgramOption();
      participantProgramOption.setParticipantProgramOptionId(1);
      participantProgramOption.setParticipantProgramOption("J1HS");

      ParticipantType participantType = new ParticipantType();
      participantType.setParticipantTypeId(1);
      participantType.setParticipantType("Activated");

      ParticipantSeason participantSeason = new ParticipantSeason();
      participantSeason.setParticipantSeasonId(1);
      participantSeason.setParticipantSeasonProgramId(1);
      participantSeason.setParticipantSeasonProgram("J1HS");

      ParticipantCountry participantCountry = new ParticipantCountry();
      participantCountry.setParticipantCountryId(1);
      participantCountry.setParticipantCountryCode("US");
      participantCountry.setParticipantCountry("America");

      ParticipantSubPartner participantSubPartner = new ParticipantSubPartner();
      participantSubPartner.setParticipantSubPartnerId(1);
      participantSubPartner.setPartnerGoId(1111);
      participantSubPartner.setParticipantSubPartner("Sub Partner Dude");

      ParticipantPlacementStatus participantPlacementStatus = new ParticipantPlacementStatus();
      participantPlacementStatus.setParticipantPlacementStatusId(1);
      participantPlacementStatus.setParticipantPlacementStatus("Active");

      ParticipantApplicationStatus participantApplicationStatus = new ParticipantApplicationStatus();
      participantApplicationStatus.setParticipantApplicationStatusId(1);
      participantApplicationStatus.setParticipantApplicationStatus("Accepted");

      ActiveParticipant aPart = new ActiveParticipant();
      aPart.setParticipantId("666");
      aPart.setParticipantEmailId("activeparticipant@partner.com");
      aPart.setParticipantStartDate("09/09/2015");
      aPart.setParticipantEndDate("09/09/2016");
      aPart.setParticipantFirstName("Active");
      aPart.setParticipantlastName("Participant Dude");
      aPart.setParticipantPicUrl("www.google.com");
      aPart.setParticipantType(participantType);
      aPart.setParticipantSeason(participantSeason);
      aPart.setParticipantCountry(participantCountry);
      aPart.setParticipantProgramOption(participantProgramOption);
      aPart.setParticipantSubPartner(participantSubPartner);
      aPart.setParticipantPlacementStatus(participantPlacementStatus);
      aPart.setParticipantApplicationStatus(participantApplicationStatus);
      aPart.setParticipantSubmittedFlightInfo(false);
      aPart.setParticipantGuranteed(false);
      participantsList.add(aPart);
      participantsList.add(aPart);
      participantsActiveList.getParticipants().addAll(participantsList);
      return participantsActiveList;
   }

   @Override
   public ParticipantsLeadList getLeadParticipantsList(String partnerId) {
      ParticipantsLeadList participantsLeadList = new ParticipantsLeadList();
      participantsLeadList.setParticipantCount(2);
      List<ParticipantLead> participants = new ArrayList<ParticipantLead>();

      com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantType participantType = new com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantType();
      participantType.setParticipantTypeId(1);
      participantType.setParticipantType("Activated");

      com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantSeason participantSeason = new com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantSeason();
      participantSeason.setParticipantSeasonId(1);
      participantSeason.setParticipantSeasonProgramId(1);
      participantSeason.setParticipantSeasonProgram("J1HS");

      com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantProgramOption participantProgramOption = new com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantProgramOption();
      participantProgramOption.setParticipantProgramOptionId(1);
      participantProgramOption.setParticipantProgramOption("J1HS");

      ParticipantLead lead = new ParticipantLead();
      lead.setParticipantId("234");
      lead.setParticipantFirstName("Participant Lead");
      lead.setParticipantlastName("Dude");
      lead.setParticipantEmailId("leadParticipant@partner.com");
      lead.setParticipantPicUrl("www.google.com");
      lead.setParticipantGuranteed(false);
      lead.setParticipantType(participantType);
      lead.setParticipantSeason(participantSeason);
      lead.setParticipantProgramOption(participantProgramOption);
      participants.add(lead);
      participants.add(lead);
      participantsLeadList.getParticipants().addAll(participants);
      return participantsLeadList;
   }

}