/**
 * 
 */
package com.ccighgo.service.components.partner.season;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.newpartnerapplicationdeadlilne.NewPartnerApplicationDeadLineDate;
import com.ccighgo.service.transport.partner.beans.newpartnerseasonallocationrequest.NewPartnerSeasonAllocationRequest;
import com.ccighgo.service.transport.partner.beans.partner.season.application.PartnerSeasonApplicationList;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasons;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerSeasonDetail;
import com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.PartnerSeasonF1Detail;
import com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.PartnerSeasonIHPDetail;
import com.ccighgo.utils.WSDefaultResponse;


/**
 * @author ravi
 *
 */
@Service
public interface PartnerSeasonInterface {
   
   /**
    * @param partnerId
    * @return
    */
   public PartnerSeasons getPartnerSeasons(String partnerId);

   /**
    * @param partnerSeasonId
    * @return
    */
   public PartnerSeasonDetail viewJ1HSPartnerSeason(String partnerSeasonId);

   /**
    * @param partnerId
    * @return
    */
   public PartnerSeasonApplicationList getPartnerSeasonApplicationList(String partnerId);

   /**
    * @param partnerSeasonId
    * @return
    */
   public PartnerSeasonF1Detail viewF1PartnerSeason(String partnerSeasonId);
   
   /**
    * @param partnerSeasonApplicationList
    * @return
    */
   public Response addNewSeasonsToPartner(PartnerSeasonApplicationList partnerSeasonApplicationList);

   public WSDefaultResponse createNewPartnerAllocationRequest(NewPartnerSeasonAllocationRequest newPartnerSeasonAllocationRequest);

   public WSDefaultResponse createNewDeadlineDateRequest(NewPartnerApplicationDeadLineDate newApplicationDeadlineDatesAllocations);

   /**
    * @param partnerSeasonId
    * @return
    */
   public PartnerSeasonIHPDetail viewIHPPartnerSeason(String partnerSeasonId);

}
