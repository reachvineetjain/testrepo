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
    * @param partnerId
    * @return
    */
   public ParticipantsLeadList getLeadParticipantsList(String partnerId);

   /**
    * @param newParticipant
    * @return
    */
   public NewManualParticipant addNewParticipant(NewManualParticipant newParticipant);

   /**
    * @param participantId
    * @return
    */
   public NewManualParticipant editNewParticipant(Integer participantId);

   /**
    * @param participant
    * @return
    */
   public NewManualParticipant updateParticipant(NewManualParticipant participant);
   
   /**
    * @return
    */
   public SeasonsForParticipants getAllAvailableSeasons();

   /**
    * @param partnerId
    * @param seasonId
    * @param departmentProgramId
    * @return
    */
   public ProgramOptionsForParticipants getAllAvailableProgramOptions(int partnerId, int seasonId,int departmentProgramId);
   
   /**
    * @param partnerId
    * @param seasonId
    * @return
    */
   public ProgramOptionsForParticipants getAllAvailableProgramOptions(int partnerId, int seasonId);

   /**
    * @param partnerId
    * @return
    */
   public AddedParticipantsList getAddedParticipant(String partnerId);

   /**
    * @param partnerId
    * @return
    */
   public SubPartnersForParticipants getAllAvailableSubPartners(int partnerId);

   /**
    * @param seasonId
    * @param participantId
    * @param departmentProgramId
    * @return
    */
   public WSDefaultResponse assignSeasonToParticipant(String seasonId, String participantId, String departmentProgramId);

   /**
    * @param subpartnerId
    * @param participantId
    * @return
    */
   public WSDefaultResponse assignSubpartnerToParticipant(String subpartnerId, String participantId);

   /**
    * @param email
    * @param email2
    * @return
    */
   public WSDefaultResponse assignEmailToParticipant(String email, String email2);

   /**
    * @param participantId
    * @param status
    * @return
    */
   public WSDefaultResponse changeParticipantStatus(String participantId, String status);

   /**
    * @param participantGoId
    * @param request
    * @return
    */
   public Response resetParticipantPassword(String participantGoId, HttpServletRequest request);

   /**
    * @param partnerId
    * @return
    */
   public SeasonsForParticipants getAllAvailableSeasons(String partnerId);

   /**
    * @param participantGoId
    * @param request
    * @return
    */
   public Response sendLogin(String participantGoId, HttpServletRequest request);
   
   /**
    * @param partnerId
    * @return
    */
   public SeasonsForParticipants getAllAvailableSeasons2(String partnerId);


}
