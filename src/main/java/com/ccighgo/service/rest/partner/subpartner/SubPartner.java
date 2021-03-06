/**
 * 
 */
package com.ccighgo.service.rest.partner.subpartner;

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

import com.ccighgo.service.components.partner.subpartner.SubPartnerInterface;
import com.ccighgo.service.transport.partner.beans.allsalutation.AllSalutations;
import com.ccighgo.service.transport.partner.beans.subpartner.PartnerSubPartners;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerDetails;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerScreeningNotes;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author ravi
 *
 */
@Deprecated
@Path("/subpartner/")
@Produces("application/json")
@Consumes("application/json")
public class SubPartner {

   private static final Logger LOGGER = LoggerFactory.getLogger(SubPartner.class);

   @Autowired SubPartnerInterface subPartnerInterface;

   @Context HttpServletRequest request;

   @GET
   @Path("list/{partnerId}")
   @Produces("application/json")
   public PartnerSubPartners getSubPartnersOfpartners(@PathParam("partnerId") String partnerId) {
      LOGGER.debug("calling SubPartner.getSubPartnersOfpartners for partner id {}", partnerId);
      return subPartnerInterface.getSubPartnersOfpartners(partnerId);
   }

   @POST
   @Path("create")
   @Produces("application/json")
   public WSDefaultResponse createSubPartner(com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail subPartner) {
      LOGGER.debug("calling SubPartner.createSubPartner", subPartner);
      return subPartnerInterface.createSubPartnerDetail(subPartner);
   }

   @POST
   @Path("update")
   @Produces("application/json")
   public WSDefaultResponse updateSubPartnerDetail(com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail subPartner) {
      LOGGER.debug("calling SubPartner.updateSubPartner", subPartner);
      return subPartnerInterface.updateSubPartnerDetail(subPartner);
   }

   @GET
   @Path("get-all-sub-partners")
   @Produces("application/json")
   public SubPartnerDetails getAllSubPartners() {
      LOGGER.debug("calling SubPartner.getAllSubPartners");
      return subPartnerInterface.getAllSubPartners();
   }

   @GET
   @Path("getDetail/{subPartnerId}")
   @Produces("application/json")
   public com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail getSubPartnerDetail(@PathParam("subPartnerId") String subPartnerId) {
      LOGGER.debug("calling subpartner.getSubpartnerDetail");
      return subPartnerInterface.getSubPartnerDetail(subPartnerId);
   }

   @GET
   @Path("update/status/{partnerUserId}/{statusVal}")
   @Produces("application/json")
   public WSDefaultResponse updateSubPartnerUserStatus(@PathParam("partnerUserId") String partnerUserId, @PathParam("statusVal") String statusVal) {
      LOGGER.debug("calling PartnerUser.updatePartnerUserStatus");
      return subPartnerInterface.updatePartnerUserStatus(partnerUserId, statusVal);
   }

   @GET
   @Path("update/status/{goId}/{loginId}/{status}")
   @Produces("application/json")
   public WSDefaultResponse updateSubPartnerStatus(@PathParam("goId") String goId, @PathParam("loginId") String loginId, @PathParam("status") String status) {
      LOGGER.debug("calling PartnerUser.updatePartnerUserStatus");
      return subPartnerInterface.updatePartnerStatus(goId, loginId, status);
   }

   @POST
   @Path("update/addSubPartnerNote")
   @Produces("application/json")
   public WSDefaultResponse addSubPartnerScreenNote(SubPartnerScreeningNotes noteDetail) {
      LOGGER.debug("calling subPartnerInterface.addSubPartnerScreenNote");
      return subPartnerInterface.addSubPartnerScreenNote(noteDetail);
   }

   @GET
   @Path("getAllSalutation")
   @Produces("application/json")
   public AllSalutations getAllSalutation() {
      LOGGER.debug("calling SubPartner.getAllSubPartners");
      return subPartnerInterface.getAllSalutation();
   }

   @GET
   @Path("delete/{goId}")
   @Produces("application/json")
   public WSDefaultResponse deleteSubPartner(@PathParam("goId") String goId) {
      LOGGER.debug("calling SubPartner.delete", goId);
      return subPartnerInterface.deleteSubPartner(goId);
   }
}
