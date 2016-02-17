package com.ccighgo.service.components.generic.notes.fieldstaffs;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.generic.beans.fieldstaffs.notetopic.FieldStaffNote;
import com.ccighgo.service.transport.generic.beans.fieldstaffs.notetopic.FieldStaffTopic;
import com.ccighgo.service.transport.generic.beans.fieldstaffs.notetopic.FieldStaffTopics;

/**
 * @author sinshaw.demisse
 *
 */
@Service
public interface FieldStaffGenericNoteInterface {

   /**
    * @param note
    * @return
    */
   Response addNotes(FieldStaffNote note);

   /**
    * @param fieldStaffGoId
    * @return
    */
   FieldStaffTopics viewTopics(int fieldStaffGoId);

   /**
    * 
    * @param note
    * @return
    */
   Response updateNotes(FieldStaffNote note);

   /**
    * 
    * @param noteId
    * @return
    */
   Response removeNote(int noteId);
   
   /**
    * @param fieldStaffTopic
    * @return
    */
   FieldStaffTopic addNewTopic(FieldStaffTopic fieldStaffTopic);
}
