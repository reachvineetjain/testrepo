package com.ccighgo.service.transport.partner.beans.subpartner;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnerDetails", propOrder = {
    "subPartnerDetails"
})
public class SubPartnerDetails {
   @XmlElement(required = true)
   
   protected List<SubPartnerDetail> subPartnerDetails;

   public List<SubPartnerDetail> getSubPartnerDetails() {
      if (subPartnerDetails == null) {
         subPartnerDetails = new ArrayList<SubPartnerDetail>();
      }
      return this.subPartnerDetails;
  }
}
