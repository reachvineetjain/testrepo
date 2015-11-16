package com.ccighgo.service.transport.partner.beans.partnerseason;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerSeasonAgentDates", propOrder = {
    "partnerSeasonAgentDates"
})
public class PartnerSeasonAgentDates {
   @XmlElement(required = true)
   protected List<PartnerSeasonAgentDate> partnerSeasonAgentDates;
   
   public List<PartnerSeasonAgentDate> getPartnerSeasonAgentDates() {
      if (partnerSeasonAgentDates == null) {
         partnerSeasonAgentDates = new ArrayList<PartnerSeasonAgentDate>();
      }
      return this.partnerSeasonAgentDates;
  }
}
