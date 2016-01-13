package com.ccighgo.service.transport.partner.beans.partnerseason;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerSeasonDocuments", propOrder = {
    "partnerSeasonDocuments"
})
public class PartnerSeasonDocuments {
   @XmlElement(required = true)
   protected List<PartnerSeasonDocument> partnerSeasonDocuments;
   
   public List<PartnerSeasonDocument> getPartnerSeasonDocuments() {
      if (partnerSeasonDocuments == null) {
         partnerSeasonDocuments = new ArrayList<PartnerSeasonDocument>();
      }
      return this.partnerSeasonDocuments;
  }
}
