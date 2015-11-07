package com.ccighgo.service.component.partner.generic;

import com.ccighgo.service.transport.partner.beans.generic.deletenote.DeleteNote;
import com.ccighgo.utils.WSDefaultResponse;
import com.ccighgo.service.transport.partner.beans.generic.notes.ScreenNote;
import com.ccighgo.service.transport.partner.beans.generic.topic.*;



/**
 * @author sinshaw.demisse
 *
 */
public interface PartnerGenericNoteInterface {

   /**
    * Method {@code addNote} returns Default Response
    * 
    * @param note ScreeNote Object
    * @return WSDefaultResponse Object
    */
   public WSDefaultResponse addNote(ScreenNote note);

   /**
    * Method {@code deleteNote} returns Default Response
    * 
    * @param deleteNote
    * @return WSDefaultResponse Object
    */
   public WSDefaultResponse deleteNote(DeleteNote deleteNote);

   /**
    * Method {@code viewTopics} returns Default Response
    * 
    * @param partnerId
    * @return Topics object which has list topic 
    */
   public Topics viewTopics(int partnerId);
}
