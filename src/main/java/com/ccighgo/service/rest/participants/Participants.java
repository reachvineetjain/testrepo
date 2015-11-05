/**
 * 
 */
package com.ccighgo.service.rest.participants;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.participants.ParticipantsInterface;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.participant.beans.addedParticipantList.AddedParticipantsList;
import com.ccighgo.service.transport.participant.beans.availableprogramOptionsforparticipant.ProgramOptionsForParticipants;
import com.ccighgo.service.transport.participant.beans.availableseasonsforparticipant.SeasonsForParticipants;
import com.ccighgo.service.transport.participant.beans.availablesubpartnerforparticipant.SubPartnersForParticipants;
import com.ccighgo.service.transport.participant.beans.newmanualparticipant.NewManualParticipant;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantsActiveList;
import com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantsLeadList;
import com.ccighgo.utils.WSDefaultResponse;

@Path("/participant/")
@Produces("application/json")
@Consumes("application/json")
public class Participants {

   private static final Logger LOGGER = LoggerFactory.getLogger(Participants.class);

   @Autowired ParticipantsInterface participantsInterface;
   
   @Context HttpServletRequest request;
   
   @GET
   @Path("active/list/{partnerId}")
   @Produces("application/json")
   public ParticipantsActiveList getActiveParticipantsList(@PathParam("partnerId") String partnerId){
      LOGGER.info("calling Participants.getActiveParticipantsList for partner id {}",partnerId);
      return participantsInterface.getActiveParticipantsList(partnerId);
      
   }
   @GET
   @Path("list/AddedParticipant/{partnerId}")
   @Produces("application/json")
   public AddedParticipantsList getAddedParticipant(@PathParam("partnerId") String partnerId){
      LOGGER.info("calling Participants.getAddedParticipant for partner id {}",partnerId);
      return participantsInterface.getAddedParticipant(partnerId);
      
   }
   
   @GET
   @Path("lead/list/{partnerId}")
   @Produces("application/json")
   public ParticipantsLeadList getLeadParticipantsList(@PathParam("partnerId") String partnerId){
      LOGGER.info("calling Participants.getLeadParticipantsList for partner id {}",partnerId);
      return participantsInterface.getLeadParticipantsList(partnerId);
   }
   
   @POST
   @Path("create/participant")
   @Produces("application/json")
   public NewManualParticipant addNewParticipant(NewManualParticipant newParticipant){
      LOGGER.info("calling Participants.addNewParticipant ");
      return participantsInterface.addNewParticipant(newParticipant);
   }

   @GET
   @Path("view/participants/{seasonId}/{programOptionId}")
   @Produces("application/json")
   public NewManualParticipant viewParticipant(@PathParam("seasonId") String seasonId,@PathParam("programOptionId") String programOptionId){
      LOGGER.info("calling Participants.editNewParticipant ");
      return null; //participantsInterface.editNewParticipant(Integer.parseInt(participantId));
   }
   
   @POST
   @Path("update/participant")
   @Produces("application/json")
   public NewManualParticipant updateParticipant(NewManualParticipant participant){
      LOGGER.info("calling Participants.updateParticipant");
      return participantsInterface.updateParticipant(participant);
   }

   @GET
   @Path("allSeasons/{partnerId}")
   @Produces("application/json")
   public SeasonsForParticipants getAllAvailableSeasons(@PathParam("partnerId") String partnerId){
      LOGGER.info("calling Participants.getAllAvailableSeasons ");
      return participantsInterface.getAllAvailableSeasons(Integer.parseInt(partnerId));
   }
   
   @GET
   @Path("allProgramOptions/{partnerId}/{seasonId}")
   @Produces("application/json")
   public ProgramOptionsForParticipants getAllAvailableProgramOptions(@PathParam("partnerId")  String partnerId, @PathParam("seasonId") String seasonId){
      LOGGER.info("calling Participants.getAllAvailableProgramOptions ");
      return participantsInterface.getAllAvailableProgramOptions(Integer.parseInt(partnerId),Integer.parseInt(seasonId));
   }
   
   @GET
   @Path("allSubPartners/{partnerId}")
   @Produces("application/json")
   public SubPartnersForParticipants getAllAvailableSubPartners(@PathParam("partnerId")  String partnerId){
      LOGGER.info("calling Participants.getAllAvailableSubPartners ");
      return participantsInterface.getAllAvailableSubPartners(Integer.parseInt(partnerId));
   }
   
   @GET
   @Path("assignSeason/{seasonId}/{participantId}")
   @Produces("application/json")
   public WSDefaultResponse assignSeasonToParticipant(@PathParam("seasonId") String seasonId,@PathParam("participantId")String participantId){
      LOGGER.info("calling Participants.assignSeasonToParticipant ");
      return participantsInterface.assignSeasonToParticipant(seasonId,participantId);
   }
   @GET
   @Path("assignSubPartner/{subpartnerId}/{participantId}")
   @Produces("application/json")
   public WSDefaultResponse assignSubpartnerToParticipant(@PathParam("subpartnerId") String subpartnerId,@PathParam("participantId")String participantId){
      LOGGER.info("calling Participants.assignSubpartnerToParticipant ");
      return participantsInterface.assignSubpartnerToParticipant(subpartnerId,participantId);
   }
   
   @GET
   @Path("assignEmailToParticipant/{participantId}/{email}")
   @Produces("application/json")
   public WSDefaultResponse assignEmailToParticipant(@PathParam("participantId") String participantId,@PathParam("email")String email){
      LOGGER.info("calling Participants.assignEmailToParticipant ");
      return participantsInterface.assignEmailToParticipant(participantId,email);
   }
   
   @GET
   @Path("changeParticipantStatus/{participantId}/{status}")
   @Produces("application/json")
   public WSDefaultResponse changeParticipantStatus(@PathParam("participantId") String participantId,@PathParam("status")String status){
      LOGGER.info("calling Participants.changeParticipantStatus ");
      return participantsInterface.changeParticipantStatus(participantId,status);
   }
   
   /**
    * @param participantGoId
    * @return
    */
   @GET
   @Path("reset/access/{participantGoId}")
   @Produces("application/json")
   public Response resetParticipantPassword(@PathParam("participantGoId") String participantGoId) {
      return participantsInterface.resetParticipantPassword(participantGoId, request);
   }

}