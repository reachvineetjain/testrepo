package com.ccighgo.service.components.partner.agent;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.partner.beans.partner.season.application.PartnerSeasonApplicationList;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentAddedSeasons;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentSeasons;

@Service
public interface PartnerAgentInterface {
   
   public PartnerAgentAddedSeasons getAddedSeasons(String partnerGoId);
   
   
   public PartnerAgentSeasons getAllSeasons();
   
   public PartnerAgentSeasons addSeasons(PartnerSeasonApplicationList partnerSeasonApplicationList);
   
   public PartnerAgentSeasons EditPartnerSeasons(PartnerSeasonApplicationList partnerSeasonApplicationList);

}
