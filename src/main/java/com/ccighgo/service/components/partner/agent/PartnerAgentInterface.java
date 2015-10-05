package com.ccighgo.service.components.partner.agent;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentAddedSeasons;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentSeasons;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;

@Service
public interface PartnerAgentInterface {
   
   public PartnerAgentAddedSeasons getAddedSeasons(String partnerGoId);
   
   
   public PartnerAgentSeasons getAllSeasons();

}
