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

   /**
    * @return
    */
   @GET
   @Path("workQueueType/{programId}")
   @Produces("application/json")
   public ErdDashboardTypes getWorkQueuesType(@PathParam("programId")String programId) {
      return fieldStaffDashboardInterface.getErdDashboardWorkQueuesType(programId);
   }

   @GET
   @Path("workQueueCategory/{typeId}")
   @Produces("application/json")
   public ErdDashboardCategories getWorkQueuesCategories(@PathParam("typeId") String typeId) {
      return fieldStaffDashboardInterface.getErdDashboardWorkQueuesCategories(typeId);
   }
   
   //public ErdDashboardTitles getWorkQueuesCategories()

}
