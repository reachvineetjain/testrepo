package com.ccighgo.service.rest.fieldstaffs.erddashboard;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.fieldstaffs.fieldstaffdashboard.FieldStaffDashboardInterface;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboard;
import com.ccighgo.service.transport.common.response.beans.Response;
/**
 * @author sinshaw.demisse
 *
 */
@Path("/erddashboard/")
@Produces("application/json")
@Consumes("application/json")
public class FieldStaffERDDashboard {

   @Autowired
   FieldStaffDashboardInterface fieldStaffDashboardInterface;
   @Context HttpServletRequest request;
   
   @GET
   @Path("ping/{name}")
  public String ping( @PathParam("name")String name)
   {
      return name;
   }

   /**
    * @return
    */
   @GET
   @Path("workQueue/{fieldStaffGoId}")
   @Produces("application/json")
   public ErdDashboard getWorkQueuesType( @PathParam("fieldStaffGoId")String fieldStaffGoId) {
      return fieldStaffDashboardInterface.getErdDashboardWorkQueues(fieldStaffGoId);
   }
   
   /**
    * @param partnerUserId
    * @return
    */
   @GET
   @Path("reset/access/{fsGoId}")
   @Produces("application/json")
   public Response resetPartnerUserPassword(@PathParam("fsGoId") String fsGoId) {
      return fieldStaffDashboardInterface.resetFieldStaffUserPassword(fsGoId, request);
   }
   
   
}
