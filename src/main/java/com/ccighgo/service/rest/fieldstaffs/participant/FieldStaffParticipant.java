package com.ccighgo.service.rest.fieldstaffs.participant;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.component.fieldstaffs.participant.FieldStaffParticipantInterface;
import com.ccighgo.service.transport.fieldstaff.beans.fieldstaffparticipant.FieldStaffParticipants;

/**
 * @author sinshaw.demisse
 *
 */
@Path("/fieldstaff/participant")
@Produces("application/json")
@Consumes("application/json")
public class FieldStaffParticipant {
   
   @Autowired FieldStaffParticipantInterface fieldStaffParticipantInterface;
   
   /**
    * @return FieldStaffParticipants
    */
   @GET
   @Path("/listAll/{fsGoId}")
   @Produces("application/json")
   public FieldStaffParticipants getAllParticipant(@PathParam("fsGoId")String fsGoId) { 
      
      return fieldStaffParticipantInterface.getAll(fsGoId);
   }
   
   /**
    * @return FieldStaffParticipants
    */
   @GET
   @Path("/listMyTeam/{fsGoId}")
   @Produces("application/json")
   public FieldStaffParticipants getMyTeam(@PathParam("fsGoId") String fsGoId)
   {
      return fieldStaffParticipantInterface.getMyTeam(fsGoId);
   }
}
