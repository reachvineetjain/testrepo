/**
 * 
 */
package com.ccighgo.service.rest.regionassignment;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.regionassignment.RegionAssignmentServices;
import com.ccighgo.service.components.regionassignment.RegionRDs;
import com.ccighgo.service.components.regionassignment.SuperRegionsERDs;
import com.ccighgo.service.transport.season.beans.assignedregion.AssignedRegion;
import com.ccighgo.service.transport.season.beans.assignedstates.AssignedStateInfo;
import com.ccighgo.service.transport.season.beans.assignedsuperregion.AssignedSuperRegion;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Path("/regionAssignment/")
@Produces("application/json")
@Consumes("application/json")
public class RegionAssignment {

   private static final Logger LOGGER = Logger.getLogger(RegionAssignment.class);

   @Autowired
   RegionAssignmentServices regionAssignmentServices;

   @GET
   @Path("ping/regionAssignment/{input}")
   @Produces("text/plain")
   public String ping(@PathParam("input") String input) {
      LOGGER.debug("Pinging !! ( value : " + input + " )");
      return input;
   }

   @GET
   @Path("list/assigned/superRegions/{seasonId}")
   @Produces("application/json")
   public AssignedSuperRegion getAssignedSuperRegionDetails(@PathParam("seasonId") String seasonId) {
      LOGGER.debug("fun: getAssignedSuperRegionDetails");
      return regionAssignmentServices.getAssignedSuperRegionDetails(Integer.parseInt(seasonId));
   }

   @GET
   @Path("list/assigned/superRegionsERDs/{seasonId}")
   @Produces("application/json")
   public SuperRegionsERDs getAllERDsForSuperRegion(@PathParam("seasonId") String seasonId) {
      LOGGER.debug("fun: getAllERDsForSuperRegion");
      return regionAssignmentServices.getAllERDsForSuperRegion(Integer.parseInt(seasonId));
   }

   @GET
   @Path("list/assigned/regions/{superRegionId}/{seasonId}")
   @Produces("application/json")
   public AssignedRegion getAssignedRegionsDetails(@PathParam("superRegionId") String superRegionId, @PathParam("seasonId") String seasonId) {
      LOGGER.debug("fun :getAssignedRegionsOfSuperRegion  [" + superRegionId + "]");
      return regionAssignmentServices.getAssignedRegions(Integer.parseInt(superRegionId), Integer.parseInt(seasonId));
   }

   @GET
   @Path("list/assigned/regionsRDs/{superRegionId}/{seasonId}")
   @Produces("application/json")
   public RegionRDs getAllRDsForRegion(@PathParam("superRegionId") String superRegionId, @PathParam("seasonId") String seasonId) {
      LOGGER.debug("fun: getAllERDsForSuperRegion");
      return regionAssignmentServices.getAllRDsForRegion(Integer.parseInt(superRegionId), Integer.parseInt(seasonId));
   }

   @GET
   @Path("list/assigned/states/{superRegionId}/{regionId}/{seasonId}")
   @Produces("application/json")
   public AssignedStateInfo getAssignedStates(@PathParam("superRegionId") String superRegionId, @PathParam("regionId") String regionId, @PathParam("seasonId") String seasonId) {
      LOGGER.debug("fun : getAssignedStates [" + superRegionId + "," + regionId + "," + seasonId + "]");
      return regionAssignmentServices.getAssignedStates(Integer.parseInt(superRegionId), Integer.parseInt(regionId), Integer.parseInt(seasonId));
   }

   //
   //
   //
   //
   //
   //
   //

   @GET
   @Path("assign/erdFieldStaff/{fieldStaffId}/{superRegionId}")
   @Produces("application/json")
   public WSDefaultResponse assignERDFieldStaffToState(@PathParam("fieldStaffId") String fieldStaffId, @PathParam("superRegionId") String superRegionId) {
      LOGGER.debug("fun : assignERDFieldStaffToState [" + fieldStaffId + "," + superRegionId + "]");
      return regionAssignmentServices.assignERDFieldStaffToState(fieldStaffId, superRegionId);
   }

   @GET
   @Path("assign/rdFieldStaff/{fieldStaffId}/{regionId}")
   @Produces("application/json")
   public WSDefaultResponse assignRDFieldStaffToState(@PathParam("fieldStaffId") String fieldStaffId, @PathParam("regionId") String regionId) {
      LOGGER.debug("fun : assignRDFieldStaffToState[ " + fieldStaffId + "," + regionId + "]");
      return regionAssignmentServices.assignRDFieldStaffToState(fieldStaffId, regionId);
   }

   @GET
   @Path("assign/fieldStaff/{fieldStaffId}/{stateId}")
   @Produces("application/json")
   public WSDefaultResponse assignFieldStaffToState(@PathParam("fieldStaffId") String fieldStaffId, @PathParam("stateId") String stateId) {
      LOGGER.debug("fun : assignFieldStaffToState [" + fieldStaffId + "," + stateId + "]");
      return regionAssignmentServices.assignFieldStaffToState(fieldStaffId, stateId);
   }

}
