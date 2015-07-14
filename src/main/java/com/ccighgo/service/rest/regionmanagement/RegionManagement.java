/**
 * 
 */
package com.ccighgo.service.rest.regionmanagement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.regionmanagement.RegionManagementServices;

/**
 * @author ravi
 *
 */
@Path("/region/")
@Produces("application/json")
@Consumes("application/json")
public class RegionManagement {
   
   private static final Logger LOGGER = Logger.getLogger(RegionManagement.class);
   
   @Autowired RegionManagementServices regionManagementServices;
   
   @GET
   @Path("ping/region/{input}")
   @Produces("text/plain")
   public String ping(@PathParam("input") String input) {
      LOGGER.debug("Pinging !! ( value : " + input + " )");
      return input;
   }

}