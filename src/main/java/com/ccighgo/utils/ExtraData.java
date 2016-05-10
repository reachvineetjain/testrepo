package com.ccighgo.utils;

import java.util.List;

import com.ccighgo.service.transport.generic.beans.documents.partner.PartnerGenericDocuments;

public class ExtraData {

   private List<PartnerGenericDocuments> partnerGenericDocuments;

   public List<PartnerGenericDocuments> getPartnerGenericDocuments() {
      return partnerGenericDocuments;
   }

   public void setPartnerGenericDocuments(List<PartnerGenericDocuments> partnerGenericDocuments) {
      this.partnerGenericDocuments = partnerGenericDocuments;
   }
   
}
