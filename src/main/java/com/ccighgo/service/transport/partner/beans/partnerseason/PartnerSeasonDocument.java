package com.ccighgo.service.transport.partner.beans.partnerseason;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerSeasonDocument", propOrder = {
    "partnerSeasonDocumentId",
    "fileName",
    "documentName",
    "uploadedBy",
    "uploadedDate",
    "documentType",
    "description"
})
public class PartnerSeasonDocument {
   protected Integer partnerSeasonDocumentId;
   protected String fileName;
   protected String documentName;
   protected String uploadedBy;
   protected String uploadedDate;
   protected String documentType;
   protected String description;
   
   public Integer getPartnerSeasonDocumentId() {
      return partnerSeasonDocumentId;
   }
   public void setPartnerSeasonDocumentId(Integer partnerSeasonDocumentId) {
      this.partnerSeasonDocumentId = partnerSeasonDocumentId;
   }
   public String getFileName() {
      return fileName;
   }
   public void setFileName(String fileName) {
      this.fileName = fileName;
   }
   public String getDocumentName() {
      return documentName;
   }
   public void setDocumentName(String documentName) {
      this.documentName = documentName;
   }
   public String getUploadedBy() {
      return uploadedBy;
   }
   public void setUploadedBy(String uploadedBy) {
      this.uploadedBy = uploadedBy;
   }
   public String getUploadedDate() {
      return uploadedDate;
   }
   public void setUploadedDate(String uploadedDate) {
      this.uploadedDate = uploadedDate;
   }
   public String getDocumentType() {
      return documentType;
   }
   public void setDocumentType(String documentType) {
      this.documentType = documentType;
   }
   public String getDescription() {
      return description;
   }
   public void setDescription(String description) {
      this.description = description;
   }

}
