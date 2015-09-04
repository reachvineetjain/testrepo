/**
 * 
 */
package com.ccighgo.service.components.partner.season;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasons;


/**
 * @author ravi
 *
 */
@Service
public interface PartnerSeasonInterface {
   
   public PartnerSeasons getPartnerSeasons(String partnerId);

}