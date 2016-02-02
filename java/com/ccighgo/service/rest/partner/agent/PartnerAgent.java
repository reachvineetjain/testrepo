package com.ccighgo.service.rest.partner.agent;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.agent.PartnerAgentInterface;
import com.ccighgo.service.transport.partner.beans.partner.season.application.PartnerSeasonApplication;
import com.ccighgo.service.transport.partner.beans.partner.season.application.PartnerSeasonApplicationList;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentAddedSeasons;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentSeasonDetails;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentSeasons;

@Path("/partner/agent/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerAgent {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerAgent.class);
   @Autowired
   PartnerAgentInterface partnerAgentInterface;
   
   @GET
   @Path("get-added-seasons/{partnerGoId}")
   @Produces("application/json")
   public PartnerAgentAddedSeasons getAddedSeasons(@PathParam("partnerGoId") String partnerGoId) {
      LOGGER.debug("calling PartnerAgent.getAddedSeasons");
       return partnerAgentInterface.getAddedSeasons(partnerGoId);
   }
   
   
   @GET
   @Path("season")
   @Produces("application/json")
   public PartnerAgentSeasons getAllSeasons() {
      LOGGER.debug("calling PartnerAgent.getAllSeasons");
      return partnerAgentInterface.getAllSeasons();
   }
   
   @POST
   @Path("add-seasons")
   @Produces("application/json")
   public PartnerAgentSeasons addSeasons(PartnerSeasonApplicationList partnerSeasonApplicationList) {
      LOGGER.debug("calling PartnerAgent.addSeasons");
      return partnerAgentInterface.addSeasons(partnerSeasonApplicationList);
   }
   
   @POST
   @Path("edit-partner-seasons")
   @Produces("application/json")
   public PartnerAgentSeasonDetails EditPartnerSeasons(PartnerAgentSeasonDetails partnerAgentSeasonDetails) {
      LOGGER.debug("calling PartnerAgent.EditPartnerSeasons");
      return partnerAgentInterface.EditPartnerSeasons(partnerAgentSeasonDetails);
   }
   
   @GET
   @Path("view-partner-season-detail/{partnerSeasonId}")
   @Produces("application/json")
   public PartnerAgentSeasonDetails viewPartnerSeason(@PathParam("partnerSeasonId") String partnerSeasonId) {
      LOGGER.debug("calling PartnerAgent.viewPartnerSeason");
      return partnerAgentInterface.viewPartnerSeason(partnerSeasonId);
   }
}
