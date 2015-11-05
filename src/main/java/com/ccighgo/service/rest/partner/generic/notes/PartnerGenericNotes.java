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
import com.ccighgo.service.components.partnerinquiry.PartnerInquiryService;
import com.ccighgo.service.transport.partner.beans.generic.deletenote.DeleteNote;
import com.ccighgo.service.transport.partner.beans.generic.notes.ScreeNote;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author Ahmed
 *
 */
@Path("/partnerGenericNotes/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerGenericNotes {

	@Autowired
	PartnerGenericNoteInterface partnerGenericNoteInterface;
	
   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerGenericNotes.class);

   PartnerInquiryService partnerInquiryService;

   @GET
   @Path("ping/{input}")
   @Produces("text/plain")
   public String ping(@PathParam("input") String input) {
       return input;
   }
  @POST
  @Path("add")
  @Produces("application/json")
  @Consumes("application/json")
   public WSDefaultResponse addNote(ScreeNote note)
   {
	   return partnerGenericNoteInterface.addNote(note);
   }
  
  @POST
  @Path("delete")
  @Produces("application/json")
  @Consumes("application/json")
  public WSDefaultResponse deleteNote(DeleteNote deleteNote)
  {
	  return partnerGenericNoteInterface.deleteNote(deleteNote);
  }
   
}
