/**
 * 
 */
package com.ccighgo.service.rest.partner.admin;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.admin.PartnerAdminService;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdmin;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.AdminPartnerWorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplications;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplicationsDetail;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.AdminPartnerWorkQueueType;

/**
 * @author Ahmed
 *
 */
@Path("/partnerAdmin/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerAdmin {

   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerAdmin.class);

   @Autowired
   PartnerAdminService partnerAdminService;

   @GET
   @Path("ping/{input}")
   @Produces("text/plain")
   public String ping(@PathParam("input") String input) {
      LOGGER.debug("Pinging !! ( value : " + input + " )");
      return input;
   }

   @GET
   @Path("workQueueType/{roleId}")
   @Produces("application/json")
   public AdminPartnerWorkQueueType getWorkQueueType(@PathParam("roleId") String roleId) {
      LOGGER.debug("fun : getWorkQueueType []");
      return partnerAdminService.getWorkQueueType(Integer.parseInt(roleId));
   }

   @GET
   @Path("workQueueCategory/{adminWorkQueueTypeId}")
   @Produces("application/json")
   public AdminPartnerWorkQueueCategory getWorkQueueCategory(@PathParam("adminWorkQueueTypeId") String adminWorkQueueTypeId) {
      LOGGER.debug("fun : getWorkQueueCategory []");
      return partnerAdminService.getWorkQueueCategory(Integer.parseInt(adminWorkQueueTypeId));
   }

   @GET
   @Path("workQueueSubmittedApplications/{partnerAgentGoId}")
   @Produces("application/json")
   public AdminPartnerWorkQueueSubmittedApplications getWorkQueueSubmittedApplications(@PathParam("partnerAgentGoId") String partnerAgentGoId) {
      LOGGER.debug("fun : getWorkQueueSubmittedApplications []");
      return partnerAdminService.getWorkQueueSubmittedApplications(Integer.parseInt(partnerAgentGoId));
   }

   @POST
   @Path("changeApplicationStatus/{partnerAgentInquiryId}")
   @Produces("application/json")
   public AdminPartnerWorkQueueSubmittedApplicationsDetail changePartnerApplicationStatus(@PathParam("partnerAgentInquiryId") String partnerAgentInquiryId) {
      LOGGER.debug("fun : changePartnerApplicationStatus []");
      return partnerAdminService.changePartnerApplicationStatus(Integer.parseInt(partnerAgentInquiryId));
   }

   @GET
   @Path("updatePartnerApplicationFollowUpDate/{partnerAgentInquiryId}/{newFollowUpDate}")
   @Produces("application/json")
   public AdminPartnerWorkQueueSubmittedApplicationsDetail updatePartnerApplicationFollowUpDate(@PathParam("partnerAgentInquiryId") String partnerAgentInquiryId,
         @PathParam("newFollowUpDate") String newFollowUpDate) {
      LOGGER.debug("fun : updatePartnerApplicationFollowUpDate");
      return partnerAdminService.updatePartnerApplicationFollowUpDate(Integer.parseInt(partnerAgentInquiryId), newFollowUpDate);
   }

   @GET
   @Path("agentRecruitmentData/{partnerAgentGoId}")
   @Produces("application/json")
   public PartnerRecruitmentAdmin getAgentRecruitmentData(@PathParam("partnerAgentGoId") String partnerAgentGoId) {
      LOGGER.debug("fun : getAgentRecruitmentData");
      return partnerAdminService.getAgentRecruitmentData(Integer.parseInt(partnerAgentGoId));
   }

}