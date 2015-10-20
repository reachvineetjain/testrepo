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
   @Path("workQueueType/{roleType}")
   @Produces("application/json")
   public AdminPartnerWorkQueueType getWorkQueueType(@PathParam("roleType") String roleType) {
      LOGGER.debug("fun : getWorkQueueType []");
      return partnerAdminService.getWorkQueueType(roleType);
   }

   @GET
   @Path("workQueueCategory/{adminWorkQueueTypeId}")
   @Produces("application/json")
   public AdminPartnerWorkQueueCategory getWorkQueueCategory(@PathParam("adminWorkQueueTypeId") String adminWorkQueueTypeId) {
      LOGGER.debug("fun : getWorkQueueCategory []");
      return partnerAdminService.getWorkQueueCategory(Integer.parseInt(adminWorkQueueTypeId));
   }

   /**
    * will use SPAdminWQPartnerSearch stored procedure
    * 
    * @param typeId
    * @return
    */
   @GET
   @Path("workQueueSubmittedApplications/{typeId}/{categoryId}/{cciStaffUserId}/{roleType}")
   @Produces("application/json")
   public AdminPartnerWorkQueueSubmittedApplications getWorkQueueSubmittedApplications(@PathParam("typeId") String typeId, @PathParam("categoryId") String categoryId,
         @PathParam("cciStaffUserId") String staffUserId, @PathParam("roleType") String roleType) {
      LOGGER.debug("fun : getWorkQueueSubmittedApplications []");
      return partnerAdminService.getWorkQueueSubmittedApplications(Integer.parseInt(typeId),Integer.parseInt(categoryId),Integer.parseInt(staffUserId),roleType);
   }

   @POST
   @Path("changeApplicationStatus/{typeId}/{categoryId}/{cciStaffUserId}/{roleType}/{newStatus}")
   @Produces("application/json")
   public AdminPartnerWorkQueueSubmittedApplicationsDetail changePartnerApplicationStatus(@PathParam("typeId") String typeId, @PathParam("categoryId") String categoryId,
         @PathParam("cciStaffUserId") String staffUserId, @PathParam("roleType") String roleType,@PathParam("newStatus") String newStatus) {
      LOGGER.debug("fun : changePartnerApplicationStatus []");
      return partnerAdminService.changePartnerApplicationStatus(Integer.parseInt(typeId),Integer.parseInt(categoryId),Integer.parseInt(staffUserId),roleType,newStatus);
   }

   @GET
   @Path("updatePartnerApplicationFollowUpDate/{typeId}/{categoryId}/{cciStaffUserId}/{roleType}/{newStatus}/{newFollowUpDate}")
   @Produces("application/json")
   public AdminPartnerWorkQueueSubmittedApplicationsDetail updatePartnerApplicationFollowUpDate(@PathParam("typeId") String typeId, @PathParam("categoryId") String categoryId,
         @PathParam("cciStaffUserId") String staffUserId, @PathParam("roleType") String roleType,@PathParam("newFollowUpDate") String newFollowUpDate) {
      LOGGER.debug("fun : updatePartnerApplicationFollowUpDate");
      return partnerAdminService.updatePartnerApplicationFollowUpDate(Integer.parseInt(typeId),Integer.parseInt(categoryId),Integer.parseInt(staffUserId),roleType,newFollowUpDate);
   }

   @GET
   @Path("agentRecruitmentData/{partnerAgentGoId}")
   @Produces("application/json")
   public PartnerRecruitmentAdmin getAgentRecruitmentData(@PathParam("partnerAgentGoId") String partnerAgentGoId) {
      LOGGER.debug("fun : getAgentRecruitmentData");
      return partnerAdminService.getAdminAgentRecruitmentData(Integer.parseInt(partnerAgentGoId));
   }

}