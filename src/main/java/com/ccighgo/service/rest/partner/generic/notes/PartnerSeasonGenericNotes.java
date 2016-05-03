/**
 * 
 */
package com.ccighgo.service.rest.partner.generic.notes;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.component.partner.generic.PartnerGenericNoteInterface;
import com.ccighgo.service.transport.partner.beans.generic.deletenote.DeleteNote;
import com.ccighgo.service.transport.partner.beans.generic.partnerseason.notes.ScreenNote;
import com.ccighgo.service.transport.partner.beans.generic.partnerseason.topic.Topic;
import com.ccighgo.service.transport.partner.beans.generic.partnerseason.topic.Topics;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author Ahmed
 *
 */
@Path("/partnerSeasonGenericNotes/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerSeasonGenericNotes {

   @Autowired PartnerGenericNoteInterface partnerGenericNoteInterface;

   /**
    * @param note
    * @return
    */
   @POST
   @Path("add")
   @Produces("application/json")
   @Consumes("application/json")
   public WSDefaultResponse addNote(ScreenNote note) {
      return partnerGenericNoteInterface.addPartnerSeasonNote(note);
   }

   /**
    * @param deleteNote
    * @return
    */
   @POST
   @Path("delete")
   @Produces("application/json")
   @Consumes("application/json")
   public WSDefaultResponse deleteNote(DeleteNote deleteNote) {
      return partnerGenericNoteInterface.deletePartnerSeasonNote(deleteNote);
   }

   /**
    * @param partnerId
    * @return
    */
   @GET
   @Path("view/{partnerSeasonId}")
   @Produces("application/json")
   public Topics viewTopics(@PathParam("partnerSeasonId") String partnerSeasonId) {
      return partnerGenericNoteInterface.viewPartnerSeasonTopics(Integer.parseInt(partnerSeasonId));
   }

   /**
    * 
    * @param topic
    * @return
    */
   @POST
   @Path("tagTopic")
   @Produces("application/json")
   @Consumes("application/json")
   public WSDefaultResponse tagTopic(Topic topic) {
      return partnerGenericNoteInterface.tagPartnerSeasonTopic(topic);
   }

   @POST
   @Path("createTopic")
   @Produces("application/json")
   @Consumes("application/json")
   public WSDefaultResponse createTopic(Topic topic) {
      return partnerGenericNoteInterface.createPartnerSeasonTopic(topic);
   }

}
