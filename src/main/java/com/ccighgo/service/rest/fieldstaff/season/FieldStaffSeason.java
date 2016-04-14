/**
 * 
 */
package com.ccighgo.service.rest.fieldstaff.season;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.fieldstaff.season.FieldStaffSeasonService;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.fieldstaff.beans.season.details.FieldStaffAdminSeasonDetails;
import com.ccighgo.service.transport.fieldstaff.beans.seasons.FieldStaffSeasons;
import com.ccighgo.service.transport.utility.beans.fs.status.list.FieldStaffStatusList;
import com.ccighgo.service.transport.utility.beans.payment.schedule.PaymentScheduleList;

/**
 * @author ravi
 *
 */
@Path("/fs/season/")
@Produces("application/json")
@Consumes("application/json")
public class FieldStaffSeason {

   private static final Logger LOGGER = LoggerFactory.getLogger(FieldStaffSeason.class);

   @Autowired FieldStaffSeasonService fieldStaffSeasonService;

   @GET
   @Path("list/{fieldStaffGoId}")
   @Produces("application/json")
   public FieldStaffSeasons getFieldStaffSeasons(@PathParam("fieldStaffGoId") String fieldStaffGoId) {
      LOGGER.info("calling FieldStaffSeason.getFieldStaffLCList ");
      return fieldStaffSeasonService.getFieldStaffSeasons(fieldStaffGoId);
   }

   @GET
   @Path("signed/contract/{fslSeasonId}/{seasonId}/{deparmentProgramId}/{statusVal}")
   @Produces("application/json")
   public Response updateSignedContract(@PathParam("fslSeasonId") String fslSeasonId, @PathParam("seasonId") String seasonId,
         @PathParam("deparmentProgramId") String deparmentProgramId, @PathParam("statusVal") String statusVal) {
      LOGGER.info("calling FieldStaffSeason.updateSignedContract ");
      return fieldStaffSeasonService.updateSignedContract(fslSeasonId, seasonId, deparmentProgramId, statusVal);
   }

   @GET
   @Path("details/{fieldStaffGoId}/{fsSeasonId}")
   @Produces("application/json")
   public FieldStaffAdminSeasonDetails getFSSeasonDetails(@PathParam("fieldStaffGoId") String fieldStaffGoId, @PathParam("fsSeasonId") String fsSeasonId) {
      LOGGER.info("calling FieldStaffSeason.getFSSeasonDetails ");
      return fieldStaffSeasonService.getFSSeasonDetails(fieldStaffGoId, fsSeasonId);
   }

   @GET
   @Path("status/list/{fsSeasonStatus}")
   @Produces("application/json")
   public FieldStaffStatusList getFieldStaffStatusList(@PathParam("fsSeasonStatus") Byte fsSeasonStatus) {
      LOGGER.info("calling FieldStaffSeason.getFieldStaffStatusList ");
      return fieldStaffSeasonService.getFieldStaffStatusList(fsSeasonStatus);
   }

   @GET
   @Path("payment/schedule/list")
   @Produces("application/json")
   public PaymentScheduleList getPaymentScheduleList() {
      LOGGER.info("calling FieldStaffSeason.getPaymentScheduleList ");
      return fieldStaffSeasonService.getPaymentScheduleList();
   }

   @POST
   @Path("update")
   @Produces("application/json")
   public FieldStaffAdminSeasonDetails updateFieldStaffAdminSeasonDetails(FieldStaffAdminSeasonDetails details) {
      LOGGER.info("calling FieldStaffSeason.updateFieldStaffAdminSeasonDetails ");
      return fieldStaffSeasonService.updateFieldStaffAdminSeasonDetails(details);
   }

   @GET
   @Path("delete/{fsSeasonId}")
   @Produces("application/json")
   public Response deleteFSAdminSeason(@PathParam("fsSeasonId") String fsSeasonId) {
      LOGGER.info("calling FieldStaffSeason.deleteFSAdminSeason ");
      return fieldStaffSeasonService.deleteFSAdminSeason(fsSeasonId);
   }

   @GET
   @Path("deactivate/{fsSeasonId}")
   @Produces("application/json")
   public Response deactivateFSAdminSeason(@PathParam("fsSeasonId") String fsSeasonId) {
      LOGGER.info("calling FieldStaffSeason.deactivateFSAdminSeason ");
      return fieldStaffSeasonService.deactivateFSAdminSeason(fsSeasonId);
   }

}
