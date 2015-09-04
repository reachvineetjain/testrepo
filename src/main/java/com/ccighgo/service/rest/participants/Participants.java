/**
 * 
 */
package com.ccighgo.service.rest.participants;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.participants.ParticipantsInterface;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantsActiveList;
import com.ccighgo.service.transport.participant.beans.participantsleadlist.ParticipantsLeadList;

/**
 * @author ravi
 *
 */
@Path("/participant/")
@Produces("application/json")
@Consumes("application/json")
public class Participants {

   private static final Logger LOGGER = LoggerFactory.getLogger(Participants.class);

   @Autowired ParticipantsInterface participantsInterface;
   
   @GET
   @Path("active/list/{partnerId}")
   @Produces("application/json")
   public ParticipantsActiveList getActiveParticipantsList(@PathParam("partnerId") String partnerId){
      LOGGER.info("calling Participants.getActiveParticipantsList for partner id {}",partnerId);
      return participantsInterface.getActiveParticipantsList(partnerId);
      
   }
   
   @GET
   @Path("lead/list/{partnerId}")
   @Produces("application/json")
   public ParticipantsLeadList getLeadParticipantsList(@PathParam("partnerId") String partnerId){
      LOGGER.info("calling Participants.getLeadParticipantsList for partner id {}",partnerId);
      return participantsInterface.getLeadParticipantsList(partnerId);
   }

}
