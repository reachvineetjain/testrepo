/**
 * 
 */
package com.ccighgo.service.rest.partner.admin.partner;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.admin.partner.AdminPartnerInterface;
import com.ccighgo.service.components.partner.season.PartnerSeasonInterface;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.admin.add.partner.AdminAddPartner;
import com.ccighgo.service.transport.partner.beans.admin.added.partner.AddedPartners;
import com.ccighgo.service.transport.partner.beans.partner.season.application.PartnerSeasonApplicationList;

/**
 * @author ravi
 *
 */
@Path("/admin/partner/")
@Produces("application/json")
@Consumes("application/json")
public class AdminPartner {

   private static final Logger LOGGER = LoggerFactory.getLogger(AdminPartner.class);

   @Autowired AdminPartnerInterface adminPartnerInterface;
   @Autowired PartnerSeasonInterface partnerSeasonInterface;
   @Context HttpServletRequest request;

   @POST
   @Path("/add/partner/")
   @Produces("application/json")
   @Consumes("application/json")
   public Response addPartner(AdminAddPartner partner) {
      return adminPartnerInterface.addPartner(partner, request);
   }

   /**
    * @param partnerAdminJ1SeasonDetails
    * @return
    */
   @GET
   @Path("added/partner/list/")
   @Consumes("application/json")
   @Produces("application/json")
   public AddedPartners getAddedPartnerList() {
      return adminPartnerInterface.getAddedPartnerList();
   }

   /**
    * @param partnerAdminJ1SeasonDetails
    * @return
    */
   @GET
   @Path("change/active/status/{statusVal}/{loggedinUserLoginId}/{partnerLoginId}")
   @Produces("application/json")
   public Response toggleActiveStatus(@PathParam("statusVal") String statusVal, @PathParam("loggedinUserLoginId") String loggedinUserLoginId,
         @PathParam("partnerLoginId") String partnerLoginId) {
      return adminPartnerInterface.toggleActiveStatus(statusVal, loggedinUserLoginId, partnerLoginId);
   }
   
   /**
    * @param partnerId
    * @return
    */
   @GET
   @Path("apply/new/{partnerGoId}")
   public PartnerSeasonApplicationList getPartnerSeasonApplicationList(@PathParam("partnerGoId") String partnerGoId){
      return partnerSeasonInterface.getPartnerSeasonApplicationList(partnerGoId);
   }
   
   /**
    * @param partnerGoId
    * @return
    */
   @POST
   @Path("add/new/")
   @Produces("application/json")
   public Response addNewSeasonsToPartner(PartnerSeasonApplicationList partnerSeasonApplicationList) {
      return partnerSeasonInterface.addNewSeasonsToPartner(partnerSeasonApplicationList);
   }
   
   /**
    * @param partnerUserId
    * @return
    */
   @GET
   @Path("reset/access/{partnerGoId}")
   @Produces("application/json")
   public Response sendLogin(@PathParam("partnerGoId") String partnerUserId) {
      return adminPartnerInterface.sendLogin(partnerUserId, request);
   }
}
