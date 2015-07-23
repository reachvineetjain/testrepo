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
import com.ccighgo.service.transport.season.beans.regionmanagementdetails.RegionManagementDetails;
import com.ccighgo.service.transport.season.beans.regionmanagementdetails.SuperRegion;

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
   @Path("super/region/edit/{superRegionId}/{seasonId}")
   @Produces("application/json")
   public SuperRegion editSuperRegion(@PathParam("superRegionId") String superRegionId, @PathParam("seasonId") String seasonId) {
      LOGGER.debug("Calling 'editSuperRegion'");
      return regionManagementServices.getSuperRegion(superRegionId, seasonId);
   }

   /**
    * @param superRegion
    * @return newly added super region
    */
   @POST
   @Path("super/region/add/{seasonId}")
   @Consumes("application/json")
   @Produces("application/json")
   public SuperRegion addSuperRegion(@PathParam("seasonId") String seasonId, SuperRegion superRegion) {
      LOGGER.debug("Calling 'addSuperRegion'");
      return regionManagementServices.addSuperRegion(seasonId,superRegion);
   }

}
