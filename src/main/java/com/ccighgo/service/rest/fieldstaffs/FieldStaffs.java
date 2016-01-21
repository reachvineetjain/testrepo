package com.ccighgo.service.rest.fieldstaffs;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.fieldstaffs.FieldStaffsInterface;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.fieldstaff.beans.fieldstaffapplication.FieldStaffapplication;
import com.ccighgo.service.transport.fieldstaff.beans.pendingapplication.PendingApplication;
import com.ccighgo.service.transport.partner.beans.fieldstaff.addedfieldstaff.AddedFieldStaff;
import com.ccighgo.service.transport.partner.beans.fieldstaff.fieldstaffoverview.FieldStaffOverview;
import com.ccighgo.service.transport.partner.beans.fieldstaff.fieldstaffoverview.FieldStaffStatuses;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.quicklinks.FieldStaffDashboardQuickLinks;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.quickstatscategory.FieldStaffDashboardQuickStatsCategory;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.quickstatstitles.FieldStaffDashboardQuickStatsTitles;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.benchmarks.PartnerAdminDashboardBenchmarks;

/**
 * @author sinshaw.demisse
 *
 */

@Path("/fieldstaff/")
@Produces("application/json")
@Consumes("application/json")
public class FieldStaffs {

   @Context HttpServletRequest request;

   @Autowired FieldStaffsInterface fieldStaffsInterface;

   @GET
   @Path("/getByType/{fieldStaffTypeCode}")
   @Produces("application/json")
   public AddedFieldStaff getAddedFieldStaffByType(@PathParam("fieldStaffTypeCode") String fieldStaffTypeCode) {
      return fieldStaffsInterface.getAddedFieldStaffByType(fieldStaffTypeCode);
   }

   @GET
   @Path("/detail/{goId}")
   @Produces("application/json")
   public FieldStaffOverview getFieldStaffDetail(@PathParam("goId") String goId) {
      return fieldStaffsInterface.getFieldStaffDetail(Integer.valueOf(goId));
   }

   @GET
   @Path("/statuses")
   @Produces("application/json")
   public FieldStaffStatuses getAllFieldStaffStatuses() {
      return fieldStaffsInterface.getAllFieldStaffStatuses();

   }

   @GET
   @Path("quickstatsCategory/{typeId}/{categoryId}")
   @Produces("application/json")
   public FieldStaffDashboardQuickStatsCategory getQuickStatsCategory(@PathParam("typeId") String typeId, @PathParam("categoryId") String categoryId) {
      return null;
   }

   @GET
   @Path("quicklinks")
   @Produces("application/json")
   public FieldStaffDashboardQuickLinks getQuickLinks() {
      return null;
   }

   @GET
   @Path("quickstatsTitle")
   @Produces("application/json")
   public FieldStaffDashboardQuickStatsTitles getQuickStatsTitle() {
      return null;
   }

   @GET
   @Path("benchmark")
   @Produces("application/json")
   public PartnerAdminDashboardBenchmarks getBenchmark() {
      return null;
   }

   @GET
   @Path("SubmittedApplications/{typeId}/{categoryId}/{cciStaffUserId}/{roleType}")
   @Produces("application/json")
   public FieldStaffapplication getSubmittedApplications(@PathParam("typeId") String typeId, @PathParam("categoryId") String categoryId,
         @PathParam("cciStaffUserId") String staffUserId, @PathParam("roleType") String roleType) {
      return null;
   }
   @GET
   @Path("PendingApplication/{typeId}/{categoryId}/{cciStaffUserId}/{roleType}")
   @Produces("application/json")
   public PendingApplication getFSPendingApplication(@PathParam("typeId") String typeId, @PathParam("categoryId") String categoryId,
         @PathParam("cciStaffUserId") String staffUserId, @PathParam("roleType") String roleType) {
      return fieldStaffsInterface.getFSPendingApplication(Integer.parseInt(typeId),Integer.parseInt(categoryId),Integer.parseInt(staffUserId),roleType) ;
   }
   
   

   /**
    * @param partnerUserId
    * @return
    */
   @GET
   @Path("reset/access/{goId}")
   @Produces("application/json")
   public Response resetPassword(@PathParam("goId") String goId) {
      return fieldStaffsInterface.resetPassword(goId, request);
   }
}
