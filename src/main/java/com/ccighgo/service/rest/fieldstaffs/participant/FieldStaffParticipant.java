package com.ccighgo.service.rest.fieldstaffs.participant;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ccighgo.service.transport.fieldstaff.beans.fieldstaffparticipant.FieldStaffParticipants;

/**
 * @author sinshaw.demisse
 *
 */
@Path("/fieldstaff/participant")
@Produces("application/json")
@Consumes("application/json")
public class FieldStaffParticipant {
   
   /**
    * @return FieldStaffParticipants
    */
   @GET
   @Path("/listAll")
   @Produces("application/json")
   public FieldStaffParticipants getAll() { 
      return null;
   }
   
   /**
    * @return FieldStaffParticipants
    */
   @GET
   @Path("/listMyTeam")
   @Produces("application/json")
   public FieldStaffParticipants getMyTeam()
   {
      return null;
   }
}
