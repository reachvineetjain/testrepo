/**
 * 
 */
package com.ccighgo.service.rest.partner.user;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.user.PartnerUserInterface;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.partner.user.details.PartnerUserDetails;
import com.ccighgo.service.transport.partner.beans.partner.user.office.PartnerUserOffices;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUsers;

/**
 * <p>
 * Rest service interface exposes list of services for Partner User management.
 * <P>
 * See {@link com.ccighgo.service.components.partner.user.PartnerUserInterface} for service interface injected for
 * business logic and {@link com.ccighgo.service.components.partner.user.PartnerUserInterfaceImpl} for actual business
 * logic.
 * </p>
 * 
 * @see com.ccighgo.service.components.partner.user.PartnerUserInterface
 * @see com.ccighgo.service.components.partner.user.PartnerUserInterfaceImpl
 * 
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

   /**
    * REST service to return list of partner user for specified partner
    * 
    * @param partnerId
    * @return
    */
   @GET
   @Path("list/{partnerId}")
   @Produces("application/json")
   public PartnerUsers getAllPartnerUsers(@PathParam("partnerId") String partnerId) {
      LOGGER.debug("calling PartnerUser.getAllPartnerUsers for partner id {}", partnerId);
      return partnerUserInterface.getAllPartnerUsers(partnerId);
   }

   /**
    * REST service updates status of partner for the season as in active or inactive
    * 
    * @param statusVal
    * @param partnerUserId
    * @return
    */
   @GET
   @Path("update/status/{statusVal}/{partnerUserId}")
   @Produces("application/json")
   public Response updatePartnerUserStatus(@PathParam("statusVal") String statusVal, @PathParam("partnerUserId") String partnerUserId) {
      LOGGER.debug("calling PartnerUser.updatePartnerUserStatus for partnerGoId id {}", partnerUserId);
      return partnerUserInterface.updatePartnerUserStatus(statusVal, partnerUserId);
   }

   /**
    * 
    * 
    * @param partnerUserId
    * @return
    */
   @GET
   @Path("view/user/details/{partnerUserId}")
   @Produces("application/json")
   public PartnerUserDetails getPartnerUserDetails(@PathParam("partnerUserId") String partnerUserId) {
      LOGGER.debug("calling PartnerUser.getPartnerUserDetails for partnerUserId id {}", partnerUserId);
      return partnerUserInterface.getPartnerUserDetails(partnerUserId);
   }

   /**
    * @param partnerUserId
    * @return
    */
   @GET
   @Path("edit/user/details/{partnerUserId}")
   @Produces("application/json")
   public PartnerUserDetails editPartnerUserDetails(@PathParam("partnerUserId") String partnerUserId) {
      LOGGER.debug("calling PartnerUser.getPartnerUserDetails for partnerUserId id {}", partnerUserId);
      return partnerUserInterface.getPartnerUserDetails(partnerUserId);
   }

   /**
    * @param partnerGoId
    * @return
    */
   @GET
   @Path("get/offices/{partnerGoId}")
   @Produces("application/json")
   public PartnerUserOffices getPartnerUserOffices(@PathParam("partnerGoId") String partnerGoId) {
      LOGGER.debug("calling PartnerUser.getPartnerUserOffice for partnerGoId id {}", partnerGoId);
      return partnerUserInterface.getPartnerUserOffices(partnerGoId);
   }

   /**
    * @param partnerUserDetails
    * @return
    */
   @POST
   @Path("add/user")
   @Produces("application/json")
   public PartnerUserDetails addPartnerUser(PartnerUserDetails partnerUserDetails) {
      LOGGER.debug("calling PartnerUser.addPartnerUser for partnerGoId id {}", partnerUserDetails.getPartnerGoId());
      return partnerUserInterface.addPartnerUser(partnerUserDetails, request);
   }

}
