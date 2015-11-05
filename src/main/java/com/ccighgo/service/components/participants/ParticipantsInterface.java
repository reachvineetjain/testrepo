/**
 * 
 */
package com.ccighgo.service.components.participants;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.participant.beans.addedParticipantList.AddedParticipantsList;
import com.ccighgo.service.transport.participant.beans.availableprogramOptionsforparticipant.ProgramOptionsForParticipants;
import com.ccighgo.service.transport.participant.beans.availableseasonsforparticipant.SeasonsForParticipants;
import com.ccighgo.service.transport.participant.beans.availablesubpartnerforparticipant.SubPartnersForParticipants;
import com.ccighgo.service.transport.participant.beans.newmanualparticipant.NewManualParticipant;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantsActiveList;
import com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantsLeadList;
import com.ccighgo.utils.WSDefaultResponse;

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

   public NewManualParticipant addNewParticipant(NewManualParticipant newParticipant);

   public NewManualParticipant editNewParticipant(Integer participantId);

   public NewManualParticipant updateParticipant(NewManualParticipant participant);
   
   public SeasonsForParticipants getAllAvailableSeasons(int partnerId);

   public ProgramOptionsForParticipants getAllAvailableProgramOptions(int partnerId, int seasonId);

   public AddedParticipantsList getAddedParticipant(String partnerId);

   public SubPartnersForParticipants getAllAvailableSubPartners(int partnerId);

   public WSDefaultResponse assignSeasonToParticipant(String seasonId, String participantId);

   public WSDefaultResponse assignSubpartnerToParticipant(String subpartnerId, String participantId);

   public WSDefaultResponse assignEmailToParticipant(String email, String email2);

   public WSDefaultResponse changeParticipantStatus(String participantId, String status);

   /**
    * @param participantGoId
    * @param request
    * @return
    */
   public Response resetParticipantPassword(String participantGoId, HttpServletRequest request);


}
