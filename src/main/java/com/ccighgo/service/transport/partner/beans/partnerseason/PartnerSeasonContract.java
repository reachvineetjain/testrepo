package com.ccighgo.service.transport.partner.beans.partnerseason;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerSeasonContract", propOrder = {
    "partnerSeasonContractId",
    "active",
    "UploadedBy",
    "UploadedOn",
    "displayName",
    "fileName",
    "modifiedBy",
    "modifiedOn",
    "isSigned"
})
public class PartnerSeasonContract {
   
   protected Integer partnerSeasonContractId;
   protected Byte active;
   protected String UploadedBy;
   protected String UploadedOn;
   protected String displayName;
   protected String fileName;
   protected String modifiedBy;
   protected String modifiedOn;
   protected Byte isSigned;
   
   public Integer getPartnerSeasonContractId() {
      return partnerSeasonContractId;
   }
   public void setPartnerSeasonContractId(Integer partnerSeasonContractId) {
      this.partnerSeasonContractId = partnerSeasonContractId;
   }
   public Byte getActive() {
      return active;
   }
   public void setActive(Byte active) {
      this.active = active;
   }
   public String getUploadedBy() {
      return UploadedBy;
   }
   public void setUploadedBy(String uploadedBy) {
      UploadedBy = uploadedBy;
   }
   public String getUploadedOn() {
      return UploadedOn;
   }
   public void setUploadedOn(String uploadedOn) {
      UploadedOn = uploadedOn;
   }
   public String getDisplayName() {
      return displayName;
   }
   public void setDisplayName(String displayName) {
      this.displayName = displayName;
   }
   public String getFileName() {
      return fileName;
   }
   public void setFileName(String fileName) {
      this.fileName = fileName;
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
   public Byte getIsSigned() {
      return isSigned;
   }
   public void setIsSigned(Byte isSigned) {
      this.isSigned = isSigned;
   }

}
