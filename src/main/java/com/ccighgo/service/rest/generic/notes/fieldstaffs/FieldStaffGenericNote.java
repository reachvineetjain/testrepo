package com.ccighgo.service.rest.generic.notes.fieldstaffs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.generic.notes.fieldstaffs.FieldStaffGenericNoteInterface;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.generic.beans.fieldstaffs.notetopic.FieldStaffNote;
import com.ccighgo.service.transport.generic.beans.fieldstaffs.notetopic.FieldStaffTopic;
import com.ccighgo.service.transport.generic.beans.fieldstaffs.notetopic.FieldStaffTopics;

/**
 * @author sinshaw.demisse
 *
 */

@Path("/fieldstaffgenericnote/")
@Produces("application/json")
@Consumes("application/json")
public class FieldStaffGenericNote {

   @Autowired
   FieldStaffGenericNoteInterface fieldStaffGenericNoteInterface;

   /**
    * @param note
    * @return
    */
   @POST
   @Path("add")
   @Produces("application/json")
   @Consumes("application/json")
   public Response addNotes(FieldStaffNote note) {
      return fieldStaffGenericNoteInterface.addNotes(note);
   }

   /**
    * @param note
    * @return
    */
   @POST
   @Path("update")
   @Produces("application/json")
   @Consumes("application/json")
   public Response updateNotes(FieldStaffNote note) {
      return fieldStaffGenericNoteInterface.updateNotes(note);
   }

   /**
    * @param fieldStaffGoId
    * @return
    */
   @GET
   @Path("topic/{fieldStaffGoId}")
   @Produces("application/json")
   public FieldStaffTopics viewTopics(@PathParam("fieldStaffGoId") String fieldStaffGoId) {
      return fieldStaffGenericNoteInterface.viewTopics(Integer.valueOf(fieldStaffGoId));
   }
   /**
    * @param noteId
    * @return
    */
   @GET
   @Path("remove/{noteId}")
   @Produces("application/json")
   public Response removeNote(@PathParam("noteId")String noteId)
   {
      return fieldStaffGenericNoteInterface.removeNote(Integer.valueOf(noteId));
   }
   
   @POST
   @Path("addtopic")
   @Produces("application/json")
   @Consumes("application/json")
   public Response addNewTopic(FieldStaffTopic fieldStaffTopic)
   {
      return fieldStaffGenericNoteInterface.addNewTopic(fieldStaffTopic);
   }
}
