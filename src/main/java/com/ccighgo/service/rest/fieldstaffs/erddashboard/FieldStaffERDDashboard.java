package com.ccighgo.service.rest.fieldstaffs.erddashboard;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.fieldstaffs.fieldstaffdashboard.FieldStaffDashboardInterface;
import com.ccighgo.service.transport.fieldstaff.beans.erddashboardtitles.ErdDashboardTitles;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.erddashboardcategories.ErdDashboardCategories;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.erddashboardtype.ErdDashboardTypes;

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
   @Path("workQueueType/{programId}/{fieldStaffGoId}")
   @Produces("application/json")
   public ErdDashboardTypes getWorkQueuesType(@PathParam("programId")String programId, @PathParam("fieldStaffGoId")String fieldStaffGoId) {
      return fieldStaffDashboardInterface.getErdDashboardWorkQueuesType(programId,fieldStaffGoId);
   }

   @GET
   @Path("workQueueCategory/{typeId}/{fieldStaffGoId}")
   @Produces("application/json")
   public ErdDashboardCategories getWorkQueuesCategories(@PathParam("typeId") String typeId, @PathParam("fieldStaffGoId")String fieldStaffGoId) {
      return fieldStaffDashboardInterface.getErdDashboardWorkQueuesCategories(typeId,fieldStaffGoId);
   }
   
   //public ErdDashboardTitles getWorkQueuesCategories()

}
