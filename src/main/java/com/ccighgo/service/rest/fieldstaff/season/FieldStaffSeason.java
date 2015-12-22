/**
 * 
 */
package com.ccighgo.service.rest.fieldstaff.season;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.fieldstaff.season.FieldStaffSeasonService;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.fieldstaff.beans.seasons.FieldStaffSeasons;

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
      return fieldStaffSeasonService.updateSignedContract(fslSeasonId,seasonId,deparmentProgramId,statusVal);
   }

}
