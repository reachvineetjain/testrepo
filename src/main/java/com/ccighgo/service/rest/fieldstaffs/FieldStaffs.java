package com.ccighgo.service.rest.fieldstaffs;

import java.util.List;

import javax.print.attribute.standard.Fidelity;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.fieldstaffs.FieldStaffsInterface;
import com.ccighgo.service.transport.beans.fieldstaff.fieldstaffoverview.FieldStaffDetails;
import com.ccighgo.service.transport.beans.fieldstaff.fieldstaffoverview.FieldStaffStatus;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.fieldstaff.beans.addedSchool.FSAddedSchool;
import com.ccighgo.service.transport.fieldstaff.beans.adminfieldstaffhostfamily.AdminFieldStaffHostFamily;
import com.ccighgo.service.transport.fieldstaff.beans.fieldstaffapplication.FieldStaffapplication;
import com.ccighgo.service.transport.fieldstaff.beans.pendingapplication.PendingApplication;
import com.ccighgo.service.transport.partner.beans.fieldstaff.addedfieldstaff.AddedFieldStaff;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.applicationstats.FieldStaffDashboardApplicationStats;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.programstats.FieldStaffDashboardProgramStats;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.quicklinks.FieldStaffDashboardQuickLinks;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.quickstatscategory.FieldStaffDashboardQuickStatsCategory;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.quickstatstitles.FieldStaffDashboardQuickStatsTitles;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.benchmarks.PartnerAdminDashboardBenchmarks;
import com.ccighgo.utils.WSDefaultResponse;

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
   public com.ccighgo.service.transport.beans.fieldstaff.fieldstaffoverview.FieldStaffOverview getFieldStaffDetail(@PathParam("goId") String goId) {
      return fieldStaffsInterface.getFieldStaffDetail(Integer.valueOf(goId));
   }

   @POST
   @Path("/update/")
   public Response updateFieldStaffDetail(FieldStaffDetails fieldStaffDetail) {
      return fieldStaffsInterface.updateFieldStaffDetail(fieldStaffDetail);
   }

   @GET
   @Path("/updatestatus/{fsGoId}/{loginId}/{statusId}")
   public Response updateFieldStaffStatus(@PathParam("fsGoId") String fsgoId, @PathParam("loginId") String loginId, @PathParam("statusId") String statusId) {
      return fieldStaffsInterface.updateFieldStaffStatus(fsgoId, loginId, statusId);
   }

   @GET
   @Path("/statuses")
   @Produces("application/json")
   public List<FieldStaffStatus> getAllFieldStaffStatuses() {
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
      return fieldStaffsInterface.getFSPendingApplication(Integer.parseInt(typeId), Integer.parseInt(categoryId), Integer.parseInt(staffUserId), roleType);
   }

   // Statistics
   // Application Stats
   @GET
   @Path("applicationStats/{typeId}/{categoryId}/")
   @Produces("application/json")
   public FieldStaffDashboardApplicationStats getFSApplicationStats(@PathParam("typeId") String typeId, @PathParam("categoryId") String categoryId) {
      return fieldStaffsInterface.getFSApplicationStats(Integer.parseInt(typeId), Integer.parseInt(categoryId));
   }

   @GET
   @Path("programStats/{categoryId}/")
   @Produces("application/json")
   public FieldStaffDashboardProgramStats getFSProgramStats(@PathParam("categoryId") String categoryId) {
      return fieldStaffsInterface.getFSProgramStats(Integer.parseInt(categoryId));
   }

   // Page 13
   @GET
   @Path("hostFamilies/{fieldStaffId}/{flagId}/{category}")
   @Produces("application/json")
   public AdminFieldStaffHostFamily getFSHostFamilies(@PathParam("fieldStaffId") String fieldStaffId, @PathParam("flagId") String flagId, @PathParam("category") String category) {
      return fieldStaffsInterface.getFSHostFamilies(Integer.parseInt(fieldStaffId), Integer.parseInt(flagId), category);
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

   /**
    * Normal Field Staff Page 7,8 screens
    */

   // 8
   // Schools (Page 8 of CCI_VD_3_ERD_flow_WIP3.pdf):

   // CALL SPFieldStaffSchoolList (fieldStaffId);
   // Ex: CALL SPFieldStaffSchoolList (50000);

   @GET
   @Path("addedSchools/{fieldStaffId}")
   @Produces("application/json")
   public FSAddedSchool getAddedSchools(@PathParam("fieldStaffId") String fieldStaffId) {
      return fieldStaffsInterface.getAddedSchools(Integer.parseInt(fieldStaffId));
   }

   // Page 7 My HostFamilies: CALL SPFieldStaffHostFamilyList(fieldStaffId
   // INT,flag INT);
   //
   // Ex: CALL SPFieldStaffHostFamilyList(50001,0);
   //
   // Page 7 HostFamilies : CALL SPFieldStaffHostFamilyList(fieldStaffId
   // INT,flag INT);
   //
   // Ex: CALL SPFieldStaffHostFamilyList(50001,1);

   @GET
   @Path("myHostFamilies/{fieldStaffId}/{category}")
   @Produces("application/json")
   public AdminFieldStaffHostFamily getFSMyHostFamilies(@PathParam("fieldStaffId") String fieldStaffId, @PathParam("category") String category) {
      /*
       * For MyHostFamily tab, flag is 1 and categories should not null. Changes
       * are made to call the common stored procedure i.e
       * SPFieldStaffHostFamilyList
       */
      int myHfFlag = 1;
      return fieldStaffsInterface.getFSHostFamilies(Integer.parseInt(fieldStaffId), myHfFlag, category);
   }

   @GET
   @Path("allHostFamilies/{fieldStaffId}")
   @Produces("application/json")
   public AdminFieldStaffHostFamily getFSAllHostFamilies(@PathParam("fieldStaffId") String fieldStaffId) {
      /*
       * For HostFamily tab flag is 0 and category should be null Changes are
       * made to call the common stored procedure i.e SPFieldStaffHostFamilyList
       */
      int allFsFlag = 0;
      String category = null;
      return fieldStaffsInterface.getFSHostFamilies(Integer.parseInt(fieldStaffId), allFsFlag, category);
   }

   @GET
   @Path("changeApplicationStatus/{goId}/{newStatus}/{note}")
   @Produces("application/json")
   public WSDefaultResponse changePartnerApplicationStatus(@PathParam("goId") String goId, @PathParam("newStatus") String newStatus, @PathParam("note") String note) {
      return fieldStaffsInterface.changePartnerApplicationStatus(Integer.parseInt(goId), newStatus, note);
   }

   @GET
   @Path("updateFieldStaffApplicationFollowUpDate/{goId}/{followUpdate}")
   @Produces("application/json")
   public WSDefaultResponse updatePartnerApplicationFollowUpDate(@PathParam("goId") String goId, @PathParam("followUpdate") String followUpdate) {
      return fieldStaffsInterface.updateFieldStaffApplicationFollowUpDate(Integer.parseInt(goId), followUpdate);
   }

}
