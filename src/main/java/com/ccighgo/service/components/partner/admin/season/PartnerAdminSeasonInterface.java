/**
 * 
 */
package com.ccighgo.service.components.partner.admin.season;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerAdminSeasonList;
import com.ccighgo.service.transport.partner.beans.partner.season.admin.application.PartnerAdminSeasonApplicationList;

/**
 * @author ravi
 *
 */
@Service
public interface PartnerAdminSeasonInterface {
   
   public PartnerAdminSeasonList getPartnerAdminSeasons(String partnerGoId);

   public PartnerAdminSeasonApplicationList getPartnerAdminSeasonApplicationList(String partnerGoId);

}
