/**
 * 
 */
package com.ccighgo.service.rest.regionassignment;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.regionassignment.RegionAssignmentServices;
import com.ccighgo.service.transport.season.beans.assignedregion.AssignedRegion;
import com.ccighgo.service.transport.season.beans.assignedstates.AssignedStateInfo;
import com.ccighgo.service.transport.season.beans.assignedsuperregion.AssignedSuperRegion;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Path("/regionassignment/")
@Produces("application/json")
@Consumes("application/json")
public class RegionAssignment {

   private static final Logger LOGGER = Logger.getLogger(RegionAssignment.class);

   @Autowired
   RegionAssignmentServices regionAssignmentServices;

   @GET
   @Path("ping/regionassignment/{input}")
   @Produces("text/plain")
   public String ping(@PathParam("input") String input) {
      LOGGER.debug("Pinging !! ( value : " + input + " )");
      return input;
   }

   @GET
   @Path("list/assigned/superRegions")
   @Produces("application/json")
   public List<AssignedSuperRegion> getAssignedSuperRegionDetails() {
      LOGGER.debug("fun: getAssignedSuperRegionDetails");
      return regionAssignmentServices.getAssignedSuperRegionDetails();
   }

   @GET
   @Path("list/assigned/regions/{superRegionId}")
   @Produces("application/json")
   public List<AssignedRegion> getAssignedRegionsOfSuperRegion(@PathParam("superRegionId") String superRegionId) {
      LOGGER.debug("fun :getAssignedRegionsOfSuperRegion  [" + superRegionId + "]");
      return regionAssignmentServices.getAssignedRegionsOfSuperRegion(superRegionId);
   }

   @GET
   @Path("list/assigned/states/{superRegionId}/{regionId}")
   @Produces("application/json")
   public List<AssignedStateInfo> getAssignedStates(@PathParam("superRegionId") String superRegionId, @PathParam("regionId") String regionId) {
      LOGGER.debug("fun : getAssignedStates [" + superRegionId + "," + regionId + "]");
      return regionAssignmentServices.getAssignedStates(superRegionId, regionId);
   }

   @GET
   @Path("assign/erdFieldStaff/{fieldStaffId}/{superRegionId}")
   @Produces("application/json")
   public String assignERDFieldStaffToState(@PathParam("fieldStaffId") String fieldStaffId, @PathParam("superRegionId") String superRegionId) {
      LOGGER.debug("fun : assignERDFieldStaffToState [" + fieldStaffId + "," + superRegionId + "]");
      return regionAssignmentServices.assignERDFieldStaffToState(fieldStaffId, superRegionId);
   }

   @GET
   @Path("assign/rdFieldStaff/{fieldStaffId}/{regionId}")
   @Produces("application/json")
   public String assignRDFieldStaffToState(@PathParam("fieldStaffId") String fieldStaffId, @PathParam("regionId") String regionId) {
      LOGGER.debug("fun : assignRDFieldStaffToState[ " + fieldStaffId + "," + regionId + "]");
      return regionAssignmentServices.assignRDFieldStaffToState(fieldStaffId, regionId);
   }

   @GET
   @Path("assign/fieldStaff/{fieldStaffId}/{stateId}")
   @Produces("application/json")
   public String assignFieldStaffToState(@PathParam("fieldStaffId") String fieldStaffId, @PathParam("stateId") String stateId) {
      LOGGER.debug("fun : assignFieldStaffToState [" + fieldStaffId + "," + stateId + "]");
      return regionAssignmentServices.assignFieldStaffToState(fieldStaffId, stateId);
   }

   @GET
   @Path("find/fieldStaff/{fieldStaffName}")
   @Produces("application/json")
   public void findFieldStaffFromOtherAreas(@PathParam("fieldStaffName") String fieldStaffName) {
      LOGGER.debug("");
   }

}
