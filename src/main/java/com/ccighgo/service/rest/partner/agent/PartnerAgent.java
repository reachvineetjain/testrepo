package com.ccighgo.service.rest.partner.agent;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.agent.PartnerAgentInterface;
import com.ccighgo.service.transport.partner.beans.partner.season.application.PartnerSeasonApplicationList;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentAddedSeasons;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentSeasons;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;

@Path("/partner/agent/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerAgent {
   
   @Autowired
   PartnerAgentInterface partnerAgentInterface;
   
   @GET
   @Path("get-added-seasons/{partnerGoId}")
   @Produces("application/json")
   public PartnerAgentAddedSeasons getAddedSeasons(@PathParam("partnerGoId") String partnerGoId) {
       return partnerAgentInterface.getAddedSeasons(partnerGoId);
   }
   
   
   @GET
   @Path("season")
   @Produces("application/json")
   public PartnerAgentSeasons getAllSeasons() {
      return partnerAgentInterface.getAllSeasons();
   }
   
   @POST
   @Path("add-seasons")
   @Produces("application/json")
   public PartnerAgentSeasons addSeasons(PartnerSeasonApplicationList partnerSeasonApplicationList) {
      return partnerAgentInterface.addSeasons(partnerSeasonApplicationList);
   }
   
   @POST
   @Path("add-seasons")
   @Produces("application/json")
   public PartnerAgentSeasons EditPartnerSeasons(PartnerSeasonApplicationList partnerSeasonApplicationList) {
      return partnerAgentInterface.EditPartnerSeasons(partnerSeasonApplicationList);
   }
}
