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
import com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata.PartnerRecruitmentAdminLead;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdmin;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.benchmarks.PartnerAdminDashboardBenchmarks;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quicklinks.PartnerAdminDashboardQuickLinks;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quickstatscategory.PartnerAdminDashboardQuickStatsCategory;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quickstatstitles.PartnerAdminDashboardQuickStatsTitles;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.AdminPartnerWorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplications;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplicationsDetail;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.AdminPartnerWorkQueueType;
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
   
   //TODO
   @GET
   @Path("quicklinks")
   @Produces("application/json")
   public PartnerAdminDashboardQuickLinks getQuickLinks() {
      LOGGER.debug("fun : getQuickLinks []");
      return partnerAdminService.getQuickLinks();
   }
   
   @GET
   @Path("quickstatsTitle")
   @Produces("application/json")
   public PartnerAdminDashboardQuickStatsTitles getQuickStatsTitle() {
      LOGGER.debug("fun : getQuickStatsTitle []");
      return partnerAdminService.getQuickStatsTitle();
   }
   
   @GET
   @Path("quickstatsCategory/{typeId}")
   @Produces("application/json")
   public PartnerAdminDashboardQuickStatsCategory getQuickStatsCategory(@PathParam("typeId") String typeId) {
      LOGGER.debug("fun : getQuickStatsCategory []");
      return partnerAdminService.getQuickStatsCategory(Integer.parseInt(typeId));
   }
   
   @GET
   @Path("benchmark")
   @Produces("application/json")
   public PartnerAdminDashboardBenchmarks getBenchmark() {
      LOGGER.debug("fun : getBenchmark []");
      return partnerAdminService.getBenchmark();
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
   
   @GET
   @Path("changeApplicationStatus/{goId}/{newStatus}")
   @Produces("application/json")
   public WSDefaultResponse changePartnerApplicationStatus(@PathParam("goId") String goId,@PathParam("newStatus") String newStatus) {
      LOGGER.debug("fun : changePartnerApplicationStatus []");
      return partnerAdminService.changePartnerApplicationStatus(Integer.parseInt(goId),newStatus);
   }

   @GET
   @Path("updatePartnerApplicationFollowUpDate/{goId}/{followUpdate}")
   @Produces("application/json")
   public WSDefaultResponse updatePartnerApplicationFollowUpDate(@PathParam("goId") String goId,@PathParam("followUpdate") String followUpdate) {
      LOGGER.debug("fun : updatePartnerApplicationFollowUpDate");
      return partnerAdminService.updatePartnerApplicationFollowUpDate(Integer.parseInt(goId),followUpdate);
   }
   
   @GET
   @Path("addNoteToPartnerApplication/{goId}/{noteValue}")
   @Produces("application/json")
   public WSDefaultResponse addNoteToPartnerApplication(@PathParam("goId") String goId,@PathParam("noteValue") String noteValue) {
      LOGGER.debug("fun : addNoteToPartnerApplication");
      return partnerAdminService.addNoteToPartnerApplication(Integer.parseInt(goId),noteValue);
   }
   
   @GET
   @Path("getNotesOfPartnerApplication/{goId}")
   @Produces("application/json")
   public WSDefaultResponse getNotesOfPartnerApplication(@PathParam("goId") String goId) {
      LOGGER.debug("fun : getNotesOfPartnerApplication");
      return partnerAdminService.getNotesOfPartnerApplication(Integer.parseInt(goId));
   }
   
   
   @GET
   @Path("partnerInquiryOverViewData/{partnerAgentGoId}")
   @Produces("application/json")
   public PartnerRecruitmentAdmin getPartnerInquiryOverviewData(@PathParam("partnerAgentGoId") String partnerAgentGoId) {
      LOGGER.debug("fun : getPartnerInquiryOverviewData");
      return partnerAdminService.getPartnerInquiryOverviewData(Integer.parseInt(partnerAgentGoId));
   }
   
   @POST
   @Path("updatePartnerInquiryOverViewData")
   @Produces("application/json")
   public PartnerRecruitmentAdmin updatePartnerInquiryOverViewData(PartnerRecruitmentAdmin partnerAdmin) {
      LOGGER.debug("fun : updatePartnerInquiryOverViewData");
      return partnerAdminService.updatePartnerInquiryOverViewData(partnerAdmin);
   }
   
   @GET
   @Path("partnerInquiryLeadData/{partnerAgentGoId}")
   @Produces("application/json")
   public PartnerRecruitmentAdminLead getPartnerInquiryLeadData(@PathParam("partnerAgentGoId") String partnerAgentGoId) {
      LOGGER.debug("fun : getPartnerInquiryLeadData");
      return partnerAdminService.getPartnerInquiryLeadData(Integer.parseInt(partnerAgentGoId));
   }
   
   @POST
   @Path("updatePartnerInquiryLeadData")
   @Produces("application/json")
   public PartnerRecruitmentAdminLead updatePartnerInquiryLeadData(PartnerRecruitmentAdminLead partnerRecruitmentAdminLead) {
      LOGGER.debug("fun : getPartnerInquiryLeadData");
      return partnerAdminService.updatePartnerInquiryLeadData(partnerRecruitmentAdminLead);
   }

   
   


}