/**
 * 
 */
package com.ccighgo.service.rest.fieldstaff.network.details;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.fieldstaff.network.details.FSDetailsInterface;
import com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.categories.FieldStaffCategoriesList;
import com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.leadership.details.MyFieldStaffLeadership;
import com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.network.FieldStaffNetwork;

/**
 * @author ravi
 *
 */
@Path("/fieldstaffs/list/")
@Produces("application/json")
@Consumes("application/json")
public class FSDetails {

   private static final Logger LOGGER = LoggerFactory.getLogger(FSDetails.class);

   @Autowired FSDetailsInterface fsDetailsInterface;

   /**
    * @param fsGoId
    * @return
    */
   @GET
   @Path("my/leadership/{fsGoId}")
   @Produces("application/json")
   public MyFieldStaffLeadership getMyFieldStaffLeadershipList(@PathParam("fsGoId") String fsGoId) {
      LOGGER.info("calling FSDetails.getMyFieldStaffLeadershipList ");
      return fsDetailsInterface.getMyFieldStaffLeadershipList(fsGoId);
   }

   /**
    * @param fsGoId
    * @return
    */
   @GET
   @Path("network/{fsGoId}")
   @Produces("application/json")
   public FieldStaffNetwork getFieldStaffNetworkList(@PathParam("fsGoId") String fsGoId) {
      LOGGER.info("calling FSDetails.getFieldStaffNetworkList ");
      return fsDetailsInterface.getFieldStaffNetworkList(fsGoId);
   }

   /**
    * @param fsGoId
    * @param flag
    * @return
    */
   @GET
   @Path("placement/categories/{fsGoId}/{placementFlag}")
   @Produces("application/json")
   public FieldStaffCategoriesList getFieldStaffPlacementCategoriesList(@PathParam("fsGoId") String fsGoId, @PathParam("placementFlag") Integer placementFlag) {
      return fsDetailsInterface.getFieldStaffPlacementCategoriesList(fsGoId, placementFlag);
   }

   @GET
   @Path("monitoring/categories/{fsGoId}/{monitoringFlag}")
   @Produces("application/json")
   public FieldStaffCategoriesList getFieldStaffMonitoringCategoriesList(@PathParam("fsGoId") String fsGoId, @PathParam("monitoringFlag") Integer monitoringFlag) {
      return fsDetailsInterface.getFieldStaffMonitoringCategoriesList(fsGoId, monitoringFlag);
   }

   @GET
   @Path("networking/categories/{fsGoId}")
   @Produces("application/json")
   public FieldStaffCategoriesList getFieldStaffNetworkCategoriesList(@PathParam("fsGoId") String fsGoId) {
      return fsDetailsInterface.getFieldStaffNetworkCategoriesList(fsGoId);
   }
   
   @GET
   @Path("hf/categories/{fsGoId}")
   @Produces("application/json")
   public FieldStaffCategoriesList getFieldStaffHfCategoriesList(@PathParam("fsGoId") String fsGoId) {
      return fsDetailsInterface.getFieldStaffHfCategoriesList(fsGoId);
   }
}
