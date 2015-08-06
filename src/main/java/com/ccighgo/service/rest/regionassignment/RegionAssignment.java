/**
 * 
 */
package com.ccighgo.service.rest.regionassignment;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.regionassignment.RegionAssignmentServices;
import com.ccighgo.service.components.regionassignment.RegionRDs;
import com.ccighgo.service.components.regionassignment.StatesStaff;
import com.ccighgo.service.components.regionassignment.SuperRegionsERDs;
import com.ccighgo.service.transport.season.beans.assignedregion.AssignedRegion;
import com.ccighgo.service.transport.season.beans.assignedstates.AssignedStateInfo;
import com.ccighgo.service.transport.season.beans.assignedsuperregion.AssignedSuperRegion;
import com.ccighgo.service.transport.season.beans.assignerdstoregion.AssignedERDToSuperRegion;
import com.ccighgo.service.transport.season.beans.assignrdstoregion.AssignedRDToRegion;
import com.ccighgo.service.transport.season.beans.assignstafftostate.AssignedStaffToState;
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
      LOGGER.debug("fun :getAssignedRegionsOfSuperRegion  [" + superRegionId + "," + seasonId + "]");
      return regionAssignmentServices.getAssignedRegions(Integer.parseInt(superRegionId), Integer.parseInt(seasonId));
   }

   @GET
   @Path("list/assigned/regionsRDs/{superRegionId}/{seasonId}")
   @Produces("application/json")
   public RegionRDs getAllRDsForRegion(@PathParam("superRegionId") String superRegionId, @PathParam("seasonId") String seasonId) {
      LOGGER.debug("fun: getAllERDsForSuperRegion [" + superRegionId + "," + seasonId + "]");
      return regionAssignmentServices.getAllRDsForRegion(Integer.parseInt(superRegionId), Integer.parseInt(seasonId));
   }

   @GET
   @Path("list/assigned/states/{superRegionId}/{regionId}/{seasonId}")
   @Produces("application/json")
   public AssignedStateInfo getAssignedStates(@PathParam("superRegionId") String superRegionId, @PathParam("regionId") String regionId, @PathParam("seasonId") String seasonId) {
      LOGGER.debug("fun : getAssignedStates [" + superRegionId + "," + regionId + "," + seasonId + "]");
      return regionAssignmentServices.getAssignedStates(Integer.parseInt(superRegionId), Integer.parseInt(regionId), Integer.parseInt(seasonId));
   }

   @GET
   @Path("list/assigned/state/staff/{superRegionId}/{regionId}/{seasonId}")
   @Produces("application/json")
   public StatesStaff getAssignedStateStaff(@PathParam("superRegionId") String superRegionId, @PathParam("regionId") String regionId, @PathParam("seasonId") String seasonId) {
      LOGGER.debug("fun : getAssignedStateStaff [" + superRegionId + "," + regionId + "," + seasonId + "]");
      return regionAssignmentServices.getAssignedStateStaff(Integer.parseInt(superRegionId), Integer.parseInt(regionId), Integer.parseInt(seasonId));
   }

   @POST
   @Path("assign/erdFieldStaff")
   @Produces("application/json")
   public WSDefaultResponse assignERDFieldStaffToSuperRegion(AssignedERDToSuperRegion assignedERDToSuperRegion) {
      LOGGER.debug("fun : assignERDFieldStaffToState []");
      return regionAssignmentServices.assignERDFieldStaffToSuperRegion(assignedERDToSuperRegion);
   }

   @POST
   @Path("assign/rdFieldStaff")
   @Produces("application/json")
   public WSDefaultResponse assignRDFieldStaffToRegion(AssignedRDToRegion assignedRDsToRegion) {
      LOGGER.debug("fun : assignRDFieldStaffToState");
      return regionAssignmentServices.assignRDFieldStaffToRegion(assignedRDsToRegion);
   }

   @POST
   @Path("assign/fieldStaffToState")
   @Produces("application/json")
   public WSDefaultResponse assignFieldStaffToState(AssignedStaffToState assignedStaffToState) {
      LOGGER.debug("fun : assignFieldStaffToState []");
      return regionAssignmentServices.assignFieldStaffToState(assignedStaffToState);
   }

}
