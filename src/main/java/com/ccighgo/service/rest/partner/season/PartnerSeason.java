
/**
 * 
 */
package com.ccighgo.service.rest.partner.season;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.season.PartnerSeasonInterface;
import com.ccighgo.service.transport.partner.beans.partner.season.application.PartnerSeasonApplicationList;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasons;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerSeasonDetail;

/**
 * <h1>PartnerSeason</h1> The PartnerSeason class is the REST service front of
 * all partner season actions in the user interface.
 * <p>
 * The class uses JAX-RX api provided by Apache CXF for RESTful web services @link
 * http://cxf.apache.org/ *
 *
 * @author ravimishra
 * 
 * @version 1.0
 *
 */
@Path("/partner/season/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerSeason {

   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerSeason.class);

   @Autowired PartnerSeasonInterface partnerSeasonInterface;
   
   /**
    * The method {@code ping(@PathParam("input"))} returns user
    * input string back. The purpose of the method is to test if PartnerSeason
    * services are up and running
    * 
    * @param input
    * @return input string back as text
    */
   @GET
   @Path("ping/{input}")
   @Produces("text/plain")
   public String ping(@PathParam("input") String input) {
       return input;
   }

   /**
    * Lists all available seasons for partner
    * 
    * @param partnerId
    * @return List of partner seasons in JSON format
    */
   @GET
   @Path("list/{partnerId}")
   @Produces("application/json")
   public PartnerSeasons getPartnerSeasons(@PathParam("partnerId") String partnerId) {
      LOGGER.info("calling PartnerSeason.getPartnerSeasons for partner id {}", partnerId);
      return partnerSeasonInterface.getPartnerSeasons(partnerId);
   }
   
   /**
    * Get details of partner season
    * 
    * @param partnerId
    * @param seasonId
    * @return
    */
   @GET
   @Path("view/{partnerSeasonId}")
   @Produces("application/json")
   public PartnerSeasonDetail viewPartnerSeason(@PathParam("partnerSeasonId") String partnerSeasonId) {
      LOGGER.debug("calling PartnerUser.viewPartnerSeason");
      return partnerSeasonInterface.viewPartnerSeason(partnerSeasonId);
   }
   
   @GET
   @Path("apply/new/{partnerId}")
   public PartnerSeasonApplicationList getPartnerSeasonApplicationList(@PathParam("partnerId") String partnerId){
      return partnerSeasonInterface.getPartnerSeasonApplicationList(partnerId);
   }
}
