package com.ccighgo.service.transport.partner.beans.subpartner;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnerNote", propOrder = {
      "subPartnerNotesId",
      "subpartnerNote",
      "createdBy",
      "createdOn",
      "modifiedBy",
      "modifiedOn",
      "author",
      "designation"
      
})
public class SubPartnerNote {

   protected Integer subPartnerNotesId;
   protected String subpartnerNote;
   protected String createdBy;

   protected String createdOn;

   protected String modifiedBy;

   protected String modifiedOn;
   protected String author;
   protected String designation;
   
   
   
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
   public String getCreatedBy() {
      return createdBy;
   }
   public void setCreatedBy(String createdBy) {
      this.createdBy = createdBy;
   }
   public String getCreatedOn() {
      return createdOn;
   }
   public void setCreatedOn(String createdOn) {
      this.createdOn = createdOn;
   }
   public String getModifiedBy() {
      return modifiedBy;
   }
   public void setModifiedBy(String modifiedBy) {
      this.modifiedBy = modifiedBy;
   }
   public String getModifiedOn() {
      return modifiedOn;
   }
   public void setModifiedOn(String modifiedOn) {
      this.modifiedOn = modifiedOn;
   }
   public String getAuthor() {
      return author;
   }
   public void setAuthor(String author) {
      this.author = author;
   }
   public String getDesignation() {
      return designation;
   }
   public void setDesignation(String designation) {
      this.designation = designation;
   }
   
}
