package com.ccighgo.utils;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.generic.topic.NoteUserCreator;

public class WSDefaultResponse extends Response {

   protected NoteUserCreator createdBy;
   private Integer topicOfNotesId;
   private int goId;
   private ExtraData extraData;
   private boolean booleanResult ;

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

   public int getGoId() {
      return goId;
   }

   public void setGoId(int goId) {
      this.goId = goId;
   }

   public ExtraData getExtraData() {
      return extraData;
   }

   public void setExtraData(ExtraData extraData) {
      this.extraData = extraData;
   }

   public boolean isBooleanResult() {
      return booleanResult;
   }

   public void setBooleanResult(boolean booleanResult) {
      this.booleanResult = booleanResult;
   }

   
   
}
