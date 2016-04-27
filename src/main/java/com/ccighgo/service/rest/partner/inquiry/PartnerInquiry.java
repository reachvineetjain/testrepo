/**
 * 
 */
package com.ccighgo.service.rest.partner.inquiry;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccighgo.service.components.partnerinquiry.PartnerInquiryService;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdmin;
import com.ccighgo.service.transport.integration.thirdparty.beans.agentViewForPartnerInquiryData.PartnerRecruitmentAgent;

/**
 * @author Ahmed
 *
 */
@Deprecated
@Path("/partnerInquiry/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerInquiry {

   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerInquiry.class);

   PartnerInquiryService partnerInquiryService;

   @GET
   @Path("ping/{input}")
   @Produces("text/plain")
   public String ping(@PathParam("input") String input) {
      return input;
   }

   @GET
   @Path("get/partnerForAgentScreen")
   @Produces("application/json")
   public PartnerRecruitmentAgent getAgentScreenForPartner() {
      LOGGER.debug("Calling getAgentScreenForPartner");
      return partnerInquiryService.getAgentScreenForPartner();
   }

   @POST
   @Path("update/partnerForAgentScreen")
   @Consumes("application/json")
   public void updateAgentScreenPartner() {
      LOGGER.debug("Calling updateAgentScreenPartner");
      partnerInquiryService.updateAgentScreenPartner();
   }

   // @POST
   // @Path("add/partnerNoteByAgent")
   // @Consumes("application/json")
   // @Produces("application/json")
   // public SeasonDepartmentNotes addSeasonDepartmentNote(SeasonDepartmentNotes
   // seasonDepartmentNotes) {
   // return seasonServices.addSeasonDepartmentNote(seasonDepartmentNotes);
   // }
   //
   // @POST
   // @Path("add/partnerNoteByAdmin")
   // @Consumes("application/json")
   // @Produces("application/json")
   // public SeasonDepartmentDocument
   // addSeasonDepartmentDoc(SeasonDepartmentDocument seasonDepartmentDocument)
   // {
   // return seasonServices.addSeasonDepartmentDoc(seasonDepartmentDocument);
   // }

   @GET
   @Path("get/partnerForAdminScreen")
   @Produces("application/json")
   public PartnerRecruitmentAdmin getAdminScreenForPartner() {
      LOGGER.debug("Calling getAdminScreenForPartner");
      return partnerInquiryService.getAdminScreenForPartner();
   }

   @POST
   @Path("update/partnerForAgentScreen")
   @Consumes("application/json")
   public void updateAdminScreenPartner() {
      LOGGER.debug("Calling updateAdminScreenPartner");
      partnerInquiryService.updateAdminScreenPartner();
   }

}
