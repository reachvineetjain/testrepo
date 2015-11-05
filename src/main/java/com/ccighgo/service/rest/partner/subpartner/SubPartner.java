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

import org.jboss.logging.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.subpartner.SubPartnerInterface;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.subpartner.PartnerSubPartners;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerDetails;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerScreeningNotes;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author ravi
 *
 */
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
   public PartnerSubPartners getSubPartnersOfpartners(@PathParam("partnerId") String partnerId){
      LOGGER.debug("calling SubPartner.getSubPartnersOfpartners for partner id {}",partnerId);
      return subPartnerInterface.getSubPartnersOfpartners(partnerId);
   }

   @POST
   @Path("create")
   @Produces("application/json")
   public WSDefaultResponse createSubPartner(com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail subPartner){
      LOGGER.debug("calling SubPartner.createSubPartner",subPartner);
      return subPartnerInterface.createSubPartnerDetail(subPartner);
   }
   @POST
   @Path("update")
   @Produces("application/json")
   public WSDefaultResponse UpdateSubPartnerDetail(
			com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail subPartner){
      LOGGER.debug("calling SubPartner.updateSubPartner",subPartner);
      return subPartnerInterface.UpdateSubPartnerDetail(subPartner);
   }
   @GET
   @Path("get-all-sub-partners")
   @Produces("application/json")
   public SubPartnerDetails getAllSubPartners(){
      LOGGER.debug("calling SubPartner.getAllSubPartners");
      return subPartnerInterface.getAllSubPartners();
   }
   
   @GET
   @Path("getDetail/{subPartnerId}")
   @Produces("application/json")
   public com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail getSubPartnerDetail(@PathParam("subPartnerId") String subPartnerId)
   {
	   LOGGER.debug("calling subpartner.getSubpartnerDetail");
	   return subPartnerInterface.getSubPartnerDetail(subPartnerId) ;
   }
   
   @GET
   @Path("update/status/{partnerUserId}/{statusVal}")
   @Produces("application/json")
   public WSDefaultResponse updateSubPartnerUserStatus(@PathParam("partnerUserId") String partnerUserId, @PathParam("statusVal") String statusVal) {
    LOGGER.debug("calling PartnerUser.updatePartnerUserStatus");
    return subPartnerInterface.updatePartnerUserStatus(partnerUserId,statusVal);
   }
   
   @POST
   @Path("update/addSubPartnerNote")
   @Produces("application/json")
   public WSDefaultResponse addSubPartnerScreenNote(SubPartnerScreeningNotes noteDetail)
   {
	   LOGGER.debug("calling subPartnerInterface.addSubPartnerScreenNote");
	   return subPartnerInterface.addSubPartnerScreenNote(noteDetail);
   }
}
