/**
 * 
 */
package com.ccighgo.service.rest.partner;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.PartnerService;
import com.ccighgo.service.transport.integration.thirdparty.beans.partnerLeadViewForPartnerInquiryData.PartnerRecruitmentLead;
import com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerF1Dashboard;
import com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerIHPDashboard;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSDashboard;

/**
 * @author ravi
 *
 */
@Path("/partner/")
@Produces("application/json")
@Consumes("application/json")
public class Partner {

   private static final Logger LOGGER = LoggerFactory.getLogger(Partner.class);

   @Autowired PartnerService partnerService;

   @GET
   @Path("j1/program/details/{partnerGoId}")
   @Produces("application/json")
   public PartnerJ1HSDashboard getJ1HSDashboard(@PathParam("partnerGoId") String partnerGoId) {
      LOGGER.info("calling Partner.getJ1HSDashboard for partner id {}", partnerGoId);
      return partnerService.getJ1HSDashboard(partnerGoId);

   }

   @GET
   @Path("f1/program/details/{partnerGoId}")
   @Produces("application/json")
   public PartnerF1Dashboard getActiveParticipangetF1DashboardtsList(@PathParam("partnerGoId") String partnerGoId) {
      LOGGER.info("calling Partner.getF1Dashboard for partner id {}", partnerGoId);
      return partnerService.getF1Dashboard(partnerGoId);

   }

   @GET
   @Path("ihp/program/details/{partnerGoId}")
   @Produces("application/json")
   public PartnerIHPDashboard getIHPDashboard(@PathParam("partnerGoId") String partnerGoId) {
      LOGGER.info("calling Partner.getIHPDashboard for partner id {}", partnerGoId);
      return partnerService.getIHPDashboard(partnerGoId);

   }

   @GET
   @Path("partnerInquiryLeadData/{partnerAgentGoId}")
   @Produces("application/json")
   public PartnerRecruitmentLead getPartnerInquiryLeadData(@PathParam("partnerAgentGoId") String partnerAgentGoId) {
      LOGGER.debug("fun : getPartnerInquiryLeadData");
      return partnerService.getPartnerInquiryLeadData(partnerAgentGoId);
   }

}
