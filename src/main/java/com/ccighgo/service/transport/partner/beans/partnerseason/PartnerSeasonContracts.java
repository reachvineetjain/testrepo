package com.ccighgo.service.transport.partner.beans.partnerseason;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerSeasonContracts", propOrder = {
    "partnerSeasonContracts"
})
public class PartnerSeasonContracts {
   @XmlElement(required = true)
   protected List<PartnerSeasonContract> partnerSeasonContracts;
   
   public List<PartnerSeasonContract> getPartnerSeasonContracts() {
      if (partnerSeasonContracts == null) {
         partnerSeasonContracts = new ArrayList<PartnerSeasonContract>();
      }
      return this.partnerSeasonContracts;
  }
}
