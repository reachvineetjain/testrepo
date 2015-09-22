/**
 * 
 */
package com.ccighgo.service.components.partner.quick.stats;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.partner.beans.partnerquickstats.PartnerQuickStats;

/**
 * @author ravi
 *
 */
@Service
public interface PartnerQuickStatsInterface {

   PartnerQuickStats getPartnerQuickStats(String partnerId);

}
