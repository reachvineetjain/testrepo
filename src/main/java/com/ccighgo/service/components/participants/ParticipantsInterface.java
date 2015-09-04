/**
 * 
 */
package com.ccighgo.service.components.participants;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantsActiveList;
import com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantsLeadList;

/**
 * @author ravi
 *
 */
@Service
public interface ParticipantsInterface {
   
   /**
    * Gets the list of activated participants for the given partner
    * 
    * @param partnerId
    * @return
    */
   public ParticipantsActiveList getActiveParticipantsList(String partnerId);
   
   /**
    * Gets the list of participant leads for the given partner
    * 
    * @param partnerId
    * @return
    */
   public ParticipantsLeadList getLeadParticipantsList(String partnerId);

}