package com.ccighgo.utils;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.generic.topic.NoteUserCreator;

public class WSDefaultResponse extends Response {

   protected NoteUserCreator createdBy;
   private Integer topicOfNotesId;

   
   public NoteUserCreator getCreatedBy() {
      return createdBy;
   }

   public void setCreatedBy(NoteUserCreator createdBy) {
      this.createdBy = createdBy;
   }

   public Integer getTopicOfNotesId() {
      return topicOfNotesId;
   }

   public void setTopicOfNotesId(Integer topicOfNotesId) {
      this.topicOfNotesId = topicOfNotesId;
   }

}
