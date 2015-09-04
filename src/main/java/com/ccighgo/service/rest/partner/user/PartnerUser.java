/**
 * 
 */
package com.ccighgo.service.rest.partner.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.user.PartnerUserInterface;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUsers;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;

/**
 * @author ravi
 *
 */
@Path("/partneruser/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerUser {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerUser.class);
   
   @Autowired PartnerUserInterface partnerUserInterface;
   
   @GET
   @Path("list/{partnerId}")
   @Produces("application/json")
   public PartnerUsers getAllPartnerUsers(@PathParam("partnerId") String partnerId) {
      LOGGER.debug("calling PartnerUser.getAllPartnerUsers for partner id {}",partnerId);
      return partnerUserInterface.getAllPartnerUsers(partnerId);
   }

}
