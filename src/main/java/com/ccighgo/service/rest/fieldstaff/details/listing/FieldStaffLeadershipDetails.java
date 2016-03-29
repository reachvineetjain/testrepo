package com.ccighgo.service.rest.fieldstaff.details.listing;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.fieldstaff.details.listing.FieldStaffLeadershipDetailsInterface;
import com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.network.details.FieldStaffNetworkList;
import com.ccighgo.service.transport.fieldstaff.beans.myfieldstaff.leadership.details.MyFieldStaffLeadershipList;

@Path("/fsl/details/")
@Produces("application/json")
@Consumes("application/json")
public class FieldStaffLeadershipDetails {

   private static final Logger LOGGER = LoggerFactory.getLogger(FieldStaffLeadershipDetails.class);

   @Autowired FieldStaffLeadershipDetailsInterface fieldStaffLeadershipDetailsInterface;

   /**
    * @param fsGoId
    * @return
    */
   @GET
   @Path("my/leadership/list/{fsGoId}")
   @Produces("application/json")
   public MyFieldStaffLeadershipList getMyFieldStaffLeadershipList(@PathParam("fsGoId") String fsGoId) {
      LOGGER.info("calling FieldStaffLeadershipDetails.getMyFieldStaffLeadershipList ");
      return fieldStaffLeadershipDetailsInterface.getMyFieldStaffLeadershipList(fsGoId);
   }

   /**
    * @param fsGoId
    * @return
    */
   @GET
   @Path("network/list/{fsGoId}")
   @Produces("application/json")
   public FieldStaffNetworkList getFieldStaffNetworkList(@PathParam("fsGoId") String fsGoId) {
      LOGGER.info("calling FieldStaffLeadershipDetails.getFieldStaffNetworkList ");
      return fieldStaffLeadershipDetailsInterface.getFieldStaffNetworkList(fsGoId);
   }

}
