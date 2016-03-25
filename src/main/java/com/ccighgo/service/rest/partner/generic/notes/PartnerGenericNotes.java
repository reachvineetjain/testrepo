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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.component.partner.generic.PartnerGenericNoteInterface;
import com.ccighgo.service.transport.partner.beans.generic.deletenote.DeleteNote;
import com.ccighgo.service.transport.partner.beans.generic.notes.ScreenNote;
import com.ccighgo.service.transport.partner.beans.generic.topic.Topic;
import com.ccighgo.service.transport.partner.beans.generic.topic.Topics;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author Ahmed
 *
 */
@Path("/partnerGenericNotes/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerGenericNotes {

   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerGenericNotes.class);

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
      return partnerGenericNoteInterface.addNote(note);
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
      return partnerGenericNoteInterface.deleteNote(deleteNote);
   }

   /**
    * @param partnerId
    * @return
    */
   @GET
   @Path("view/{partnerId}")
   @Produces("application/json")
   public Topics viewTopics(@PathParam("partnerId") String partnerId) {
      return partnerGenericNoteInterface.viewTopics(Integer.parseInt(partnerId));
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
      return partnerGenericNoteInterface.tagTopic(topic);
   }

   @POST
   @Path("createTopic")
   @Produces("application/json")
   @Consumes("application/json")
   public WSDefaultResponse createTopic(Topic topic) {
      return partnerGenericNoteInterface.createTopic(topic);
   }

}
