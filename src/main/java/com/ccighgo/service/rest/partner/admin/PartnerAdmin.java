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

import com.ccighgo.service.components.partner.PartnerService;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdmin;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.PartnerAdminDashboard;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.PartnerWorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.PartnerWorkQueueSubmittedApplications;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.PartnerWorkQueueType;
import com.ccighgo.utils.WSDefaultResponse;

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
   PartnerService partnerService;

   @GET
   @Path("ping/{input}")
   @Produces("text/plain")
   public String ping(@PathParam("input") String input) {
      LOGGER.debug("Pinging !! ( value : " + input + " )");
      return input;
   }


   @POST
   @Path("dashboard")
   @Produces("application/json")
   public PartnerAdminDashboard getDashboard() {
      LOGGER.debug("fun : getDashboard []");
      return partnerService.getDashboard();
   }

   @GET
   @Path("workQueueType")
   @Produces("application/json")
   public PartnerWorkQueueType getWorkQueueType() {
      LOGGER.debug("fun : getWorkQueueType []");
      return partnerService.getWorkQueueType();
   }
   @GET
   @Path("workQueueCategory")
   @Produces("application/json")
   public PartnerWorkQueueCategory getWorkQueueCategory() {
      LOGGER.debug("fun : getWorkQueueCategory []");
      return partnerService.getWorkQueueCategory();
   }
   
   @GET
   @Path("workQueueSubmittedApplications/{partnerAgentGoId}")
   @Produces("application/json")
   public PartnerWorkQueueSubmittedApplications getWorkQueueSubmittedApplications(@PathParam("partnerAgentGoId") String partnerAgentGoId) {
      LOGGER.debug("fun : getWorkQueueSubmittedApplications []");
      return partnerService.getWorkQueueSubmittedApplications(Integer.parseInt(partnerAgentGoId));
   }
   
   @POST
   @Path("changeApplicationStatus")
   @Produces("application/json")
   public WSDefaultResponse changePartnerApplicationStatus() {
      LOGGER.debug("fun : changePartnerApplicationStatus []");
      return partnerService.changePartnerApplicationStatus();
   }
   
   
   @POST
   @Path("updatePartnerApplicationFollowUpDate")
   @Produces("application/json")
   public WSDefaultResponse updatePartnerApplicationFollowUpDate() {
      LOGGER.debug("fun : updatePartnerApplicationFollowUpDate");
      return partnerService.updatePartnerApplicationFollowUpDate();
   }
   
   @GET
   @Path("agentRecruitmentData/{partnerAgentGoId}")
   @Produces("application/json")
   public PartnerRecruitmentAdmin getAgentRecruitmentData(@PathParam("partnerAgentGoId") String partnerAgentGoId) {
      LOGGER.debug("fun : getAgentRecruitmentData");
      return partnerService.getAgentRecruitmentData(Integer.parseInt(partnerAgentGoId));
   }
   
}
