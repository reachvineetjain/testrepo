/**
 * 
 */
package com.ccighgo.service.rest.partner.admin.season;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.admin.season.PartnerAdminSeasonInterface;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerAdminSeasonList;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSDashboard;

/**
 * @author ravi
 *
 */
@Path("/partner/admin/season")
@Produces("application/json")
@Consumes("application/json")
public class PartnerAdminSeason {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerAdminSeason.class);
   
   @Autowired PartnerAdminSeasonInterface partnerAdminSeasonInterface;
   
   @GET
   @Path("list")
   @Produces("application/json")
   public PartnerAdminSeasonList getPartnerAdminSeasons(){
      LOGGER.info("calling PartnerAdminSeason.getPartnerAdminSeasons ");
      return partnerAdminSeasonInterface.getPartnerAdminSeasons();
   }

}
