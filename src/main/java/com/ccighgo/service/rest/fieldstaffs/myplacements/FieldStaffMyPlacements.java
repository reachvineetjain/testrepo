package com.ccighgo.service.rest.fieldstaffs.myplacements;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.fieldstaffs.placements.FieldStaffMyPlacementsInterface;
import com.ccighgo.service.transport.partner.beans.fieldstaff.placement.myplacement.MyPlacements;

/**
 * @author sinshaw.demisse
 *
 */
@Path("/fieldstaff/placement")
@Produces("application/json")
@Consumes("application/json")
public class FieldStaffMyPlacements {

   @Autowired
   FieldStaffMyPlacementsInterface fieldStaffMyPlacementsInterface;

   @Path("list/all")
   public MyPlacements getAllMyPlacements() {
      return fieldStaffMyPlacementsInterface.getAll();
   }
}
