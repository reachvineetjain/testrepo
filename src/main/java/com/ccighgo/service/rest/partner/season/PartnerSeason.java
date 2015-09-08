/**
 * 
 */
package com.ccighgo.service.rest.partner.season;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.season.PartnerSeasonInterface;
import com.ccighgo.service.transport.participant.beans.participantsactivelist.ParticipantsActiveList;
import com.ccighgo.service.transport.partner.beans.PartnersSeasons.PartnersSeasons;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasons;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserDetailAndRoles;

/**
 * @author ravi
 *
 */
@Path("/partner/season/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerSeason {

   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerSeason.class);

   @Autowired PartnerSeasonInterface partnerSeasonInterface;

   @GET
   @Path("list/{partnerId}")
   @Produces("application/json")
   public PartnerSeasons getPartnerSeasons(@PathParam("partnerId") String partnerId) {
      LOGGER.info("calling PartnerSeason.getPartnerSeasons for partner id {}", partnerId);
      return partnerSeasonInterface.getPartnerSeasons(partnerId);
   }
   @POST
   @Path("view/{partnerId}/{seasonId}")
   @Produces("application/json")
   public PartnersSeasons viewPartnerSeason(@PathParam("partnerId") String partnerId,@PathParam("seasonId") String seasonId) {
      LOGGER.debug("calling PartnerUser.viewPartnerSeason");
      return partnerSeasonInterface.viewPartnerSeason(partnerId,seasonId);
   }
}
