package com.ccighgo.service.rest.hf.participant.application.season;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.hf.participant.application.season.HfParticipantApplicationSeasonsInterface;
import com.ccighgo.service.transport.hostfamily.beans.seasons.list.HFParticipantSeasonList;

/**
 * This class implements the service used to list the seasons associated with
 * the host families
 * 
 * @author vijay
 * 
 */
@Path("/hf/application/season")
@Produces("application/json")
@Consumes("application/json")
public class HfParticipantApplicationSeasons {

   private static final Logger LOGGER = LoggerFactory.getLogger(HfParticipantApplicationSeasons.class);

   @Autowired HfParticipantApplicationSeasonsInterface hfSeasonsInterface;

   /**
    * @param goId
    * @return HFParticipantSeasonList
    */

   @GET
   @Path("list/season/{goId}")
   @Produces("application/json")
   public HFParticipantSeasonList getHFParticipantSeasonList(@PathParam("goId") String goId) {
      LOGGER.info("calling HfParticipantApplicationSeasons.HFParticipantSeasonList for goId {}", goId);
      return hfSeasonsInterface.getHFParticipantSeasonList(goId);

   }

}
