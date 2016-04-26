/*
 * Copyright (c) 2015, 2016, Creospan Solutions Pvt Ltd. All rights reserved.
 * CREOSPAN PROPRIETARY/CONFIDENTIAL.
 *
 *
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
import com.ccighgo.service.transport.season.beans.assignerdstosuperregion.AssignedERDToSuperRegion;
import com.ccighgo.service.transport.season.beans.assignrdstoregion.AssignedRDToRegion;
import com.ccighgo.service.transport.season.beans.assignstafftostate.AssignedStaffToState;
import com.ccighgo.service.transport.season.beans.deleteregionmember.DeleteRegionMember;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * This class contains all the methods used to assign the different types of
 * Field Staffs(i.e ERD,RD,AC,LC,RM etc) to the Super Region ,Regions and States
 * It's methods are exposed as RESTful services
 * 
 * @author Ahmed Abdelmaaboud
 *
 */

@Path("/regionAssignment/")
@Produces("application/json")
@Consumes("application/json")
public class RegionAssignment {

   private static final Logger LOGGER = Logger.getLogger(RegionAssignment.class);

   @Autowired RegionAssignmentServices regionAssignmentServices;

   @GET
   @Path("ping/regionAssignment/{input}")
   @Produces("text/plain")
   public String ping(@PathParam("input") String input) {
      LOGGER.debug("Pinging !! ( value : " + input + " )");
      return input;
   }

   /**
    * @param seasonId
    *           .The number used to uniquely identify the season
    * @return the element AssignedSuperRegion. It contains the details of all
    *         the Super Regions associated with given season.
    */
   @GET
   @Path("list/assigned/superRegions/{seasonId}")
   @Produces("application/json")
   public AssignedSuperRegion getAssignedSuperRegionDetails(@PathParam("seasonId") String seasonId) {
      LOGGER.debug("fun: getAssignedSuperRegionDetails");
      return regionAssignmentServices.getAssignedSuperRegionDetails(Integer.parseInt(seasonId));
   }

   /**
    * @param seasonId
    *           . The number used to uniquely identify the season
    * @param superRegionId
    *           . The number used to uniquely identify the Super Region
    * @return the element SuperRegionsERDs. It is the list of all the ERDs
    *         associated with given Super Region in the given season.
    */
   @GET
   @Path("list/assigned/superRegionsERDs/{seasonId}/{superRegionId}")
   @Produces("application/json")
   public SuperRegionsERDs getAllERDsForSuperRegion(@PathParam("seasonId") String seasonId, @PathParam("superRegionId") String superRegionId) {
      LOGGER.debug("fun: getAllERDsForSuperRegion");
      return regionAssignmentServices.getAllERDsForSuperRegion(Integer.parseInt(seasonId), Integer.parseInt(superRegionId));
   }

   /**
    * 
    * @param superRegionId
    *           . The number used to uniquely identify the Super Region
    * @param seasonId
    *           . The number used to uniquely identify the season
    * @return the element AssignedRegion. It contains the details of all the
    *         Regions present in the given Super Region.
    */

   @GET
   @Path("list/assigned/regions/{superRegionId}/{seasonId}")
   @Produces("application/json")
   public AssignedRegion getAssignedRegionsDetails(@PathParam("superRegionId") String superRegionId, @PathParam("seasonId") String seasonId) {
      LOGGER.debug("fun :getAssignedRegionsOfSuperRegion  [" + superRegionId + "," + seasonId + "]");
      return regionAssignmentServices.getAssignedRegions(Integer.parseInt(superRegionId), Integer.parseInt(seasonId));
   }

   /**
    * 
    * @param superRegionId
    *           . The number used to uniquely identify the Super Region
    * @param seasonId
    *           . The number used to uniquely identify the season
    * @param regionId
    *           . The number used to uniquely identify the Region
    * @return the element RegionRDs. It is the list of all the RDs associated
    *         with given Region.
    */
   @GET
   @Path("list/assigned/regionsRDs/{superRegionId}/{seasonId}/{regionId}")
   @Produces("application/json")
   public RegionRDs getAllRDsForRegion(@PathParam("superRegionId") String superRegionId, @PathParam("seasonId") String seasonId, @PathParam("regionId") String regionId) {
      LOGGER.debug("fun: getAllERDsForSuperRegion [" + superRegionId + "," + seasonId + "]");
      return regionAssignmentServices.getAllRDsForRegion(Integer.parseInt(superRegionId), Integer.parseInt(seasonId), Integer.parseInt(regionId));
   }

   /**
    * 
    * @param superRegionId
    *           . The number used to uniquely identify the Super Region
    * @param regionId
    *           . The number used to uniquely identify the Region
    * @param seasonId
    *           . The number used to uniquely identify the season
    * @return the element AssignedStateInfo. It contains the details of all the
    *         States associated with given Region and it's Super Region.
    */
   @GET
   @Path("list/assigned/states/{superRegionId}/{regionId}/{seasonId}")
   @Produces("application/json")
   public AssignedStateInfo getAssignedStates(@PathParam("superRegionId") String superRegionId, @PathParam("regionId") String regionId, @PathParam("seasonId") String seasonId) {
      LOGGER.debug("fun : getAssignedStates [" + superRegionId + "," + regionId + "," + seasonId + "]");
      return regionAssignmentServices.getAssignedStates(Integer.parseInt(superRegionId), Integer.parseInt(regionId), Integer.parseInt(seasonId));
   }

   /**
    * 
    * @param superRegionId
    *           . The number used to uniquely identify the Super Region
    * @param regionId
    *           . The number used to uniquely identify the Region
    * @param seasonId
    *           . The number used to uniquely identify the Season
    * @param stateId
    *           . The number used to uniquely identify the State
    * @return the element StatesStaff. It is the list of all the RDs,ACs,and RMs
    *         associated with the State which is present under given Super
    *         Region and Region.
    */
   @GET
   @Path("list/assigned/state/staff/{superRegionId}/{regionId}/{seasonId}/{stateId}")
   @Produces("application/json")
   public StatesStaff getAssignedStateStaff(@PathParam("superRegionId") String superRegionId, @PathParam("regionId") String regionId, @PathParam("seasonId") String seasonId,
         @PathParam("stateId") String stateId) {
      LOGGER.debug("fun : getAssignedStateStaff [" + superRegionId + "," + regionId + "," + seasonId + "]");
      return regionAssignmentServices.getAssignedStateStaff(Integer.parseInt(superRegionId), Integer.parseInt(regionId), Integer.parseInt(seasonId), Integer.parseInt(stateId));
   }

   /**
    * 
    * @param assignedERDToSuperRegion
    *           .The object containing the information about the ERD and Super
    *           Region to which it has to be assigned.
    * @return the response.The generic service response object having the header
    *         and status.The status shows whether the service has success or
    *         failure along with proper id.
    * 
    */
   @POST
   @Path("assign/erdFieldStaff")
   @Produces("application/json")
   public WSDefaultResponse assignERDFieldStaffToSuperRegion(AssignedERDToSuperRegion assignedERDToSuperRegion) {
      LOGGER.debug("fun : assignERDFieldStaffToState []");
      return regionAssignmentServices.assignERDFieldStaffToSuperRegion(assignedERDToSuperRegion);
   }

   /**
    * 
    * @param assignedRDsToRegion
    *           .The object containing the information about the RD and the
    *           Region to which it has to be assigned.
    * @return the response.The generic service response object having the header
    *         and status.The status shows whether the service has success or
    *         failure along with proper id.
    * 
    */
   @POST
   @Path("assign/rdFieldStaff")
   @Produces("application/json")
   public WSDefaultResponse assignRDFieldStaffToRegion(AssignedRDToRegion assignedRDsToRegion) {
      LOGGER.debug("fun : assignRDFieldStaffToRegion");
      return regionAssignmentServices.assignRDFieldStaffToRegion(assignedRDsToRegion);
   }

   /**
    * 
    * @param assignedStaffToState
    *           .The object containing the information about the Field
    *           Staffs(i.e RDs,ACs and RMs) and State to which it has to be
    *           assigned.
    * @return the response.The generic service response object having the header
    *         and status.The status shows whether the service has success or
    *         failure along with proper id.
    * 
    */
   @POST
   @Path("assign/fieldStaffToState")
   @Produces("application/json")
   public WSDefaultResponse assignFieldStaffToState(AssignedStaffToState assignedStaffToState) {
      LOGGER.debug("fun : assignFieldStaffToState []");
      return regionAssignmentServices.assignFieldStaffToState(assignedStaffToState);
   }

   /**
    * 
    * @param deleteRegionMember
    *           .The object containing the details of the Field Staff whose
    *           assignments need to be removed from respective Super Region
    *           ,Region or States
    * @return the response.The generic service response object having the header
    *         and status.The status shows whether the service has success or
    *         failure along with proper id.
    * 
    */
   @POST
   @Path("delete/member")
   @Produces("application/json")
   public WSDefaultResponse deleteMember(DeleteRegionMember deleteRegionMember) {
      LOGGER.debug("fun : deleteMember");
      return regionAssignmentServices.deleteMember(deleteRegionMember);
   }

}
