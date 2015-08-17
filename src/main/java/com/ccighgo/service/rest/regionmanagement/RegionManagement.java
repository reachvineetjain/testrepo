/**
 * 
 */
package com.ccighgo.service.rest.regionmanagement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.regionmanagement.RegionManagementServices;
import com.ccighgo.service.transport.common.beans.deletereq.DeleteRequest;
import com.ccighgo.service.transport.region.beans.mvregion.MoveRegions;
import com.ccighgo.service.transport.region.beans.regionmanagementdetails.Region;
import com.ccighgo.service.transport.region.beans.regionmanagementdetails.RegionManagementDetails;
import com.ccighgo.service.transport.region.beans.regionmanagementdetails.SuperRegion;
import com.ccighgo.service.transport.region.beans.regionsuperregion.RegionSuperRegionsMap;
import com.ccighgo.service.transport.region.beans.stateregion.StateRegions;

/**
 * <p>
 * Rest service interface exposes list of services for Season Region management.
 * <P>
 * See {@link com.ccighgo.service.components.regionmanagement.RegionManagementServices} for service interface injected
 * for business logic and {@link com.ccighgo.service.components.regionmanagement.RegionManagementServicesImpl} for
 * actual business logic.
 * </p>
 * 
 * @see com.ccighgo.service.components.regionmanagement.RegionManagementServices
 * @see com.ccighgo.service.components.regionmanagement.RegionManagementServicesImpl
 * 
 * @author ravi
 *
 */
@Path("/region/")
@Produces("application/json")
@Consumes("application/json")
public class RegionManagement {

   private static final Logger LOGGER = Logger.getLogger(RegionManagement.class);

   @Autowired
   RegionManagementServices regionManagementServices;

   @GET
   @Path("ping/region/{input}")
   @Produces("text/plain")
   public String ping(@PathParam("input") String input) {
      LOGGER.debug("Pinging !! ( value : " + input + " )");
      return input;
   }

   /**
    * REST Service to get the region management details for the season. Service returns JSON object containing list of
    * super regions and regions and states contained within or returns empty object if details not found.
    * 
    * @param seasonId
    * @return RegionManagementDetails JSON object
    */
   @GET
   @Path("list/all/{seasonId}")
   @Produces("application/json")
   public RegionManagementDetails getCompleteRegionDetails(@PathParam("seasonId") String seasonId) {
      LOGGER.debug("Calling 'getCompleteRegionDetails'");
      return regionManagementServices.getCompleteRegionDetails(seasonId);
   }

   /**
    * @param superRegionId
    * @param seasonId
    * @return
    */
   @GET
   @Path("superregion/edit/{superRegionId}")
   @Produces("application/json")
   public SuperRegion editSuperRegion(@PathParam("superRegionId") String superRegionId) {
      LOGGER.debug("Calling 'editSuperRegion'");
      return regionManagementServices.getSuperRegion(superRegionId);
   }

   /**
    * @param superRegion
    * @return newly added super region
    */
   @POST
   @Path("add/superregion/{seasonId}")
   @Consumes("application/json")
   @Produces("application/json")
   public SuperRegion addSuperRegionToSeason(@PathParam("seasonId") String seasonId, SuperRegion superRegion) {
      LOGGER.debug("Calling 'addSuperRegion'");
      return regionManagementServices.addSuperRegionToSeason(seasonId, superRegion);
   }

   /**
    * @param superRegion
    * @return newly added super region
    */
   @POST
   @Path("superregion/update")
   @Consumes("application/json")
   @Produces("application/json")
   public SuperRegion updateSuperRegion(SuperRegion superRegion) {
      LOGGER.debug("Calling 'updateSuperRegion'");
      return regionManagementServices.updateSuperRegion(superRegion);
   }

   /**
    * @param superRegionId
    * @param seasonId
    * @return
    */
   @GET
   @Path("superregion/delete/{superRegionId}/{seasonId}")
   @Produces("application/json")
   public DeleteRequest deleteSuperRegion(@PathParam("superRegionId") String superRegionId, @PathParam("seasonId") String seasonId) {
      LOGGER.debug("Calling 'deleteSuperRegion'");
      return regionManagementServices.deleteSuperRegion(superRegionId, seasonId);
   }

   /**
    * @param regionId
    * @return
    */
   @GET
   @Path("edit/{regionId}")
   @Produces("application/json")
   public Region editRegion(@PathParam("regionId") String regionId) {
      LOGGER.debug("Calling 'editSuperRegion'");
      return regionManagementServices.getRegion(regionId);
   }

   /**
    * @param region
    * @return
    */
   @POST
   @Path("add/{superRegionId}/{seasonId}")
   @Produces("application/json")
   public Region addRegion(@PathParam("superRegionId") String superRegionId, @PathParam("seasonId") String seasonId, Region region) {
      LOGGER.debug("Calling 'addRegion'");
      return regionManagementServices.addRegion(superRegionId, seasonId, region);
   }

   /**
    * @param region
    * @return
    */
   @POST
   @Path("update")
   @Produces("application/json")
   public Region updateRegion(Region region) {
      LOGGER.debug("Calling 'updateRegion'");
      return regionManagementServices.updateRegion(region);
   }

   /**
    * @param superRegionId
    * @param seasonId
    * @return
    */
   @GET
   @Path("delete/{regionId}/{superRegionId}/{seasonId}")
   @Produces("application/json")
   public DeleteRequest deleteRegion(@PathParam("regionId") String regionId, @PathParam("superRegionId") String superRegionId, @PathParam("seasonId") String seasonId) {
      LOGGER.debug("Calling 'deleteSuperRegion'");
      return regionManagementServices.deleteRegion(regionId, superRegionId, seasonId);
   }

   /**
    * @param superRegionId
    * @param seasonId
    * @return
    */
   @GET
   @Path("state/region/{superRegionId}/{seasonId}")
   @Produces("application/json")
   public StateRegions getStateRegions(@PathParam("superRegionId") String superRegionId, @PathParam("seasonId") String seasonId) {
      LOGGER.debug("Calling 'getStateRegions'");
      return regionManagementServices.getStateRegions(superRegionId, seasonId);
   }

   /**
    * @param superRegionId
    * @param seasonId
    * @param stateRegions
    * @return
    */
   @POST
   @Path("move/state/region/")
   @Produces("application/json")
   public StateRegions updateStateRegions(StateRegions stateRegions) {
      LOGGER.debug("Calling 'updateStateRegions' moving states and adding it to different regions");
      return regionManagementServices.updateStateRegions(stateRegions);
   }

   /**
    * @param superRegionId
    * @param seasonId
    * @param region
    * @return
    */
   @POST
   @Path("add/state/region/{superRegionId}/{seasonId}")
   @Produces("application/json")
   public Region addStateRegions(@PathParam("superRegionId") String superRegionId, @PathParam("seasonId") String seasonId, Region region) {
      LOGGER.debug("Calling 'updateStateRegions' moving states and adding it to different regions");
      return regionManagementServices.addStateRegions(superRegionId, seasonId, region);
   }

   @POST
   @Path("move/region/")
   @Produces("application/json")
   public RegionManagementDetails moveRegions(MoveRegions mvRegions) {
      LOGGER.debug("Calling 'moveRegions' moving region and adding it to different super region");
      return regionManagementServices.moveRegions(mvRegions);
   }
   
   /**
    * @param superRegionId
    * @param seasonId
    * @return
    */
   @GET
   @Path("delete/state/{seasonId}/{superRegionId}/{regionId}/{stateId}")
   @Produces("application/json")
   public DeleteRequest deleteState(@PathParam("seasonId") String seasonId, @PathParam("superRegionId") String superRegionId, @PathParam("regionId") String regionId,@PathParam("stateId") String stateId) {
      LOGGER.debug("Calling 'deleteState'");
      return regionManagementServices.deleteState(seasonId, superRegionId, regionId,stateId);
   }

   
   
}
