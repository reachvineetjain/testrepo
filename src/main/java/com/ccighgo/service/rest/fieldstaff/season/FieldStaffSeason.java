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
   @Path("list/{fsGoId}")
   @Produces("application/json")
   public FieldStaffSeasons getFieldStaffSeasons(@PathParam("fsGoId") String fsGoId){
      LOGGER.info("calling FieldStaffSeason.getFieldStaffLCList ");
      return fieldStaffSeasonService.getFieldStaffSeasons(Integer.valueOf(fsGoId));
   }

}
