package com.ccighgo.service.components.partner.agent;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.partner.beans.partner.season.application.PartnerSeasonApplication;
import com.ccighgo.service.transport.partner.beans.partner.season.application.PartnerSeasonApplicationList;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentAddedSeasons;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentSeasonDetails;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentSeasons;

@Service
public interface PartnerAgentInterface {
   
   public PartnerAgentAddedSeasons getAddedSeasons(String partnerGoId);
   
   
   public PartnerAgentSeasons getAllSeasons();
   
   public PartnerAgentSeasons addSeasons(PartnerSeasonApplicationList partnerSeasonApplicationList);
   
   public PartnerAgentSeasonDetails EditPartnerSeasons(PartnerAgentSeasonDetails partnerAgentSeasonDetails);
   
   public PartnerAgentSeasonDetails viewPartnerSeason(PartnerSeasonApplication partnerSeasonApplication);

}
