package com.ccighgo.service.transport.partner.beans.partnerseason;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccighgo.service.transport.common.response.beans.Response;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerAgentAddedSeasons", propOrder = {
    "partnerAgentAddedSeasons"
})
public class PartnerAgentAddedSeasons extends Response {
   @XmlElement(required = true)
   protected List<PartnerAgentAddedSeason> partnerAgentAddedSeasons;
   protected int count;
   
   public List<PartnerAgentAddedSeason> getPartnerAgentAddedSeasons() {
      if (partnerAgentAddedSeasons == null) {
         partnerAgentAddedSeasons = new ArrayList<PartnerAgentAddedSeason>();
      }
      return this.partnerAgentAddedSeasons;
  }

   public int getCount() {
      return count;
   }

   public void setCount(int count) {
      this.count = count;
   }

}
