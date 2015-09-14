/**
 * 
 */
package com.ccighgo.service.components.participants;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.db.entities.Participant;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.service.transport.participant.beans.newparticipant.NewParticipant;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantsActiveList;
import com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantsLeadList;

/**
 * @author ravi
 *
 */
 
public class CopyOfParticipantsInterfaceImpl  {

   @Autowired
   ParticipantRepository participantRepository;
   private static final Logger LOGGER = LoggerFactory.getLogger(CopyOfParticipantsInterfaceImpl.class);

    
   public ParticipantsActiveList getActiveParticipantsList(String partnerId) {
      ParticipantsActiveList participantsActiveList = new ParticipantsActiveList();
      //TODO consider status when query
      List<Participant> activeParticipants = participantRepository.findActiveParticipantByPartnerId(partnerId);
      for (Participant participant : activeParticipants) {
         // Filling Participant Active List with DB data --> DB table not completed yet
      }
      return participantsActiveList;
   }

    
   public ParticipantsLeadList getLeadParticipantsList(String partnerId) {
      ParticipantsLeadList participantsLeadList = new ParticipantsLeadList();
      List<Participant> activeParticipants = participantRepository.findLeadParticipantByPartnerId(partnerId);
    //TODO consider status when query
      for (Participant participant : activeParticipants) {
         // Filling Participant Active List with DB data --> DB table not completed yet
      }
      return participantsLeadList;
   }

    
   public NewParticipant addNewParticipant(NewParticipant newParticipant) {
      // persist object to DB
      return newParticipant;
   }

}
