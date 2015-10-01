/**
 * 
 */
package com.ccighgo.service.rest.partner.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.user.PartnerUserInterface;
import com.ccighgo.service.transport.common.beans.deletereq.DeleteRequest;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUserStatus;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUsers;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserDetailAndRoles;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserProgramsAndRoles;

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
      LOGGER.debug("calling PartnerUser.getAllPartnerUsers for partner id {}",partnerId);
      return partnerUserInterface.getAllPartnerUsers(partnerId);
   }
   
   @POST
   @Path("create")
   @Produces("application/json")
   public PartnerUserDetailAndRoles createNewPartnerUser(PartnerUserDetailAndRoles partnerUserDetailAndRoles) {
      LOGGER.debug("calling PartnerUser.addNewPartnerUser");
      return partnerUserInterface.addNewPartnerUser(partnerUserDetailAndRoles,request);
   }
   
   @GET
   @Path("view/{partnerUserId}")
   @Produces("application/json")
   public PartnerUserDetailAndRoles viewPartnerUser(@PathParam("partnerUserId") String partnerUserId) {
      LOGGER.debug("calling PartnerUser.viewPartnerUser");
      return partnerUserInterface.viewPartnerUser(partnerUserId);
   }
   
   @GET
   @Path("edit/{partnerUserId}")
   @Produces("application/json")
   public PartnerUserDetailAndRoles editPartnerUser(@PathParam("partnerUserId") String partnerUserId) {
      LOGGER.debug("calling PartnerUser.viewPartnerUser");
      return partnerUserInterface.viewPartnerUser(partnerUserId);
   }
   @POST
   @Path("update")
   @Produces("application/json")
   public PartnerUserDetailAndRoles updatePartnerUser(PartnerUserDetailAndRoles partnerUserDetailAndRoles) {
      LOGGER.debug("calling PartnerUser.updatePartnerUser");
      return partnerUserInterface.updatePartnerUser(partnerUserDetailAndRoles);
   }
   
   @GET
   @Path("get-program-access")
   @Produces("application/json")
   public PartnerUserProgramsAndRoles getProgramsAndRoles() {
      LOGGER.debug("calling PartnerUser.getProgramsAndRoles");
      return partnerUserInterface.getProgramsAndRoles();
   }
   
   /*@POST
   @Path("search")
   @Produces("application/json")
   public PartnerUsersDetailAndRoles searchPartnerUser(com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUser partnerUser) {
      LOGGER.debug("calling PartnerUser.searchPartnerUser");
      return partnerUserInterface.searchPartnerUser(partnerUser);
   }*/
   
   @GET
   @Path("delete-partner-user/{partnerUserId}")
   @Produces("application/json")
   public DeleteRequest deletePartnerUser(@PathParam("partnerUserId") String partnerUserId) {
      LOGGER.debug("calling PartnerUser.deletePartnerUser");
      return partnerUserInterface.deletePartnerUser(partnerUserId);
   }
   
   @GET
   @Path("partner-user-status")
   @Produces("application/json")
   public List<PartnerUserStatus> getPartnerUserStatuses() {
      LOGGER.debug("calling PartnerUser.getPartnerUserStatuses");
      return partnerUserInterface.getPartnerUserStatuses();
   }
   
   @GET
   @Path("get-partner-go-id/{loginName}")
   @Produces("application/json")
   public String getPartnerGoIdForPartnerUser(@PathParam("loginName") String loginName) {
      LOGGER.debug("calling PartnerUser.getPartnerGoIdForPartnerUser");
      return partnerUserInterface.getPartnerGoIdForPartnerUser(loginName);
   }
   
}
