package com.ccighgo.service.transport.partner.beans.subpartner;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnerNote", propOrder = {
      "subPartnerNotesId",
      "subpartnerNote",
      "subPartnerNoteTopic"
})
public class SubPartnerNote {

   protected Integer subPartnerNotesId;
   protected String subpartnerNote;
   protected SubPartnerNoteTopic SubPartnerNoteTopic;
   
   
   public Integer getSubPartnerNotesId() {
      return subPartnerNotesId;
   }
   public void setSubPartnerNotesId(Integer subPartnerNotesId) {
      this.subPartnerNotesId = subPartnerNotesId;
   }
   public String getSubpartnerNote() {
      return subpartnerNote;
   }
   public void setSubpartnerNote(String subpartnerNote) {
      this.subpartnerNote = subpartnerNote;
   }
   public SubPartnerNoteTopic getSubPartnerNoteTopic() {
      return SubPartnerNoteTopic;
   }
   public void setSubPartnerNoteTopic(SubPartnerNoteTopic subPartnerNoteTopic) {
      SubPartnerNoteTopic = subPartnerNoteTopic;
   }
}