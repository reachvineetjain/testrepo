/**
 * 
 */
package com.ccighgo.service.rest.partner.user;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.user.PartnerUserInterface;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.partner.user.details.PartnerUserDetails;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUsers;

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

   @Context HttpServletRequest request;

   @GET
   @Path("list/{partnerId}")
   @Produces("application/json")
   public PartnerUsers getAllPartnerUsers(@PathParam("partnerId") String partnerId) {
      LOGGER.debug("calling PartnerUser.getAllPartnerUsers for partner id {}", partnerId);
      return partnerUserInterface.getAllPartnerUsers(partnerId);
   }

   @GET
   @Path("update/status/{statusVal}/{partnerUserId}")
   @Produces("application/json")
   public Response updatePartnerUserStatus(@PathParam("statusVal") String statusVal, @PathParam("partnerUserId") String partnerUserId) {
      LOGGER.debug("calling PartnerUser.updatePartnerUserStatus for partnerGoId id {}", partnerUserId);
      return partnerUserInterface.updatePartnerUserStatus(statusVal, partnerUserId);
   }

   @GET
   @Path("view/user/details/{partnerUserId}")
   @Produces("application/json")
   public PartnerUserDetails getPartnerUserDetails(@PathParam("partnerUserId") String partnerUserId) {
      LOGGER.debug("calling PartnerUser.getPartnerUserDetails for partnerUserId id {}", partnerUserId);
      return partnerUserInterface.getPartnerUserDetails(partnerUserId);
   }
}
