/**
 * 
 */
package com.ccighgo.service.rest.partner.admin.season;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.admin.season.PartnerAdminSeasonInterface;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.PartnerAdminF1SeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.PartnerAdminIHPSeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Document;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.OperatingAgreement;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.PartnerAdminJ1SeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerAdminSeasonList;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.status.PartnerSeasonStatus;
import com.ccighgo.service.transport.partner.beans.partner.season.admin.application.PartnerAdminSeasonApplicationList;

/**
 * @author ravi
 *
 */
@Path("/partner/admin/season/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerAdminSeason {

   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerAdminSeason.class);

   @Autowired PartnerAdminSeasonInterface partnerAdminSeasonInterface;

   /**
    * @param partnerGoId
    * @return
    */
   @GET
   @Path("list/{partnerGoId}")
   @Produces("application/json")
   public PartnerAdminSeasonList getPartnerAdminSeasons(@PathParam("partnerGoId") String partnerGoId) {
      LOGGER.info("calling PartnerAdminSeason.getPartnerAdminSeasons ");
      return partnerAdminSeasonInterface.getPartnerAdminSeasons(partnerGoId);
   }

   /**
    * @param partnerGoId
    * @return
    */
   @GET
   @Path("apply/new/{partnerGoId}")
   @Produces("application/json")
   public PartnerAdminSeasonApplicationList getPartnerAdminSeasonApplicationList(@PathParam("partnerGoId") String partnerGoId) {
      return partnerAdminSeasonInterface.getPartnerAdminSeasonApplicationList(partnerGoId);
   }

   /**
    * @param partnerGoId
    * @return
    */
   @POST
   @Path("add/new")
   @Consumes("application/json")
   @Produces("application/json")
   public Response addNewSeasonsToPartner(PartnerAdminSeasonApplicationList partnerAdminSeasonApplicationList) {
      return partnerAdminSeasonInterface.addNewSeasonsToPartner(partnerAdminSeasonApplicationList);
   }

   /**
    * @param partnerGoId
    * @param partnerSeasonId
    * @return
    */
   @GET
   @Path("get/j1/details/{partnerGoId}/{partnerSeasonId}")
   @Produces("application/json")
   public PartnerAdminJ1SeasonDetails getPartnerAdminJ1Details(@PathParam("partnerGoId") String partnerGoId, @PathParam("partnerSeasonId") String partnerSeasonId) {
      return partnerAdminSeasonInterface.getPartnerAdminJ1Details(partnerGoId, partnerSeasonId);
   }

   /**
    * @param partnerGoId
    * @param partnerSeasonId
    * @return
    */
   @GET
   @Path("get/f1/details/{partnerGoId}/{partnerSeasonId}")
   @Produces("application/json")
   public PartnerAdminF1SeasonDetails getPartnerAdminF1Details(@PathParam("partnerGoId") String partnerGoId, @PathParam("partnerSeasonId") String partnerSeasonId) {
      return partnerAdminSeasonInterface.getPartnerAdminF1Details(partnerGoId, partnerSeasonId);
   }

   /**
    * @param partnerGoId
    * @param partnerSeasonId
    * @return
    */
   @GET
   @Path("get/ihp/details/{partnerGoId}/{partnerSeasonId}")
   @Produces("application/json")
   public PartnerAdminIHPSeasonDetails getPartnerAdminIHPDetails(@PathParam("partnerGoId") String partnerGoId, @PathParam("partnerSeasonId") String partnerSeasonId) {
      return partnerAdminSeasonInterface.getPartnerAdminIHPDetails(partnerGoId, partnerSeasonId);
   }

   /**
    * @param statusVal
    * @param partnerSeasonId
    * @return
    */
   @GET
   @Path("update/partner/season/active/status/{statusVal}/{partnerSeasonId}")
   @Produces("application/json")
   public Response updatePartnerSeasonActiveStatus(@PathParam("statusVal") String statusVal, @PathParam("partnerSeasonId") String partnerSeasonId) {
      return partnerAdminSeasonInterface.updatePartnerSeasonActiveStatus(statusVal, partnerSeasonId);
   }

   /**
    * @param statusVal
    * @param partnerSeasonId
    * @return
    */
   @GET
   @Path("update/season/active/status/{statusVal}/{partnerSeasonId}")
   @Produces("application/json")
   public Response updateSeasonActiveStatus(@PathParam("statusVal") String statusVal, @PathParam("partnerSeasonId") String partnerSeasonId) {
      return partnerAdminSeasonInterface.updateSeasonActiveStatus(statusVal, partnerSeasonId);
   }

   /**
    * @param partnerAdminJ1SeasonDetails
    * @return
    */
   @POST
   @Path("update/j1")
   @Consumes("application/json")
   @Produces("application/json")
   public PartnerAdminJ1SeasonDetails updateJ1AdminSeason(PartnerAdminJ1SeasonDetails partnerAdminJ1SeasonDetails) {
      return partnerAdminSeasonInterface.updateJ1AdminSeason(partnerAdminJ1SeasonDetails);
   }

   /**
    * @param partnerAdminJ1SeasonDetails
    * @return
    */
   @POST
   @Path("update/f1")
   @Consumes("application/json")
   @Produces("application/json")
   public PartnerAdminF1SeasonDetails updateF1AdminSeason(PartnerAdminF1SeasonDetails partnerAdminF1SeasonDetails) {
      return partnerAdminSeasonInterface.updateF1AdminSeason(partnerAdminF1SeasonDetails);
   }

   /**
    * @param partnerAdminIHPSeasonDetails
    * @return
    */
   @POST
   @Path("update/ihp")
   @Consumes("application/json")
   @Produces("application/json")
   public PartnerAdminIHPSeasonDetails updateIHPAdminSeason(PartnerAdminIHPSeasonDetails partnerAdminIHPSeasonDetails) {
      return partnerAdminSeasonInterface.updateIHPAdminSeason(partnerAdminIHPSeasonDetails);
   }

   /**
    * @param partnerAdminJ1SeasonDetails
    * @return
    */
   @POST
   @Path("add/document/{loginId}/{partnerSeasonId}")
   @Consumes("application/json")
   @Produces("application/json")
   public Response addAdminSeasonDocument(@PathParam("loginId") String loginId, @PathParam("partnerSeasonId") String partnerSeasonId, Document doc) {
      return partnerAdminSeasonInterface.addAdminSeasonDocument(loginId, partnerSeasonId, doc);
   }

   /**
    * @param partnerAdminJ1SeasonDetails
    * @return
    */
   @POST
   @Path("add/operating/agreement/{loginId}/{partnerSeasonId}")
   @Consumes("application/json")
   @Produces("application/json")
   public Response addSeasonOperatingAgreement(@PathParam("loginId") String loginId, @PathParam("partnerSeasonId") String partnerSeasonId, OperatingAgreement contract) {
      return partnerAdminSeasonInterface.addSeasonOperatingAgreement(loginId, partnerSeasonId, contract);
   }

   /**
    * @param partnerAdminJ1SeasonDetails
    * @return
    */
   @GET
   @Path("delete/document/{partnerSeasonDocumentId}")
   @Produces("application/json")
   public Response deleteAdminSeasonDocument(@PathParam("partnerSeasonDocumentId") String partnerSeasonDocumentId) {
      return partnerAdminSeasonInterface.deleteAdminSeasonDocument(partnerSeasonDocumentId);
   }

   /**
    * @param partnerAdminJ1SeasonDetails
    * @return
    */
   @GET
   @Path("delete/operating/agreement/{partnerSeasonContractId}")
   @Produces("application/json")
   public Response deleteAdminSeasonAgreement(@PathParam("partnerSeasonContractId") String partnerSeasonContractId) {
      return partnerAdminSeasonInterface.deleteAdminSeasonAgreement(partnerSeasonContractId);
   }

   /**
    * @return
    */
   @GET
   @Path("status/list")
   @Produces("application/json")
   public PartnerSeasonStatus getPartnerSeasonStatuses() {
      return partnerAdminSeasonInterface.getPartnerSeasonStatuses();
   }

}
