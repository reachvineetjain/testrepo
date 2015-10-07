package com.ccighgo.service.transport.partner.beans.partnerseason;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccighgo.service.transport.common.response.beans.Response;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerAgentSeasons", propOrder = {
    "partnerAgentSeasons"
})
public class PartnerAgentSeasons extends Response {
   
   @XmlElement(required = true)   
   protected List<PartnerAgentSeason> partnerAgentSeasons;
   
   public List<PartnerAgentSeason> getPartnerAgentSeasons() {
      if (partnerAgentSeasons == null) {
         partnerAgentSeasons = new ArrayList<PartnerAgentSeason>();
      }
      return this.partnerAgentSeasons;
  }

}
