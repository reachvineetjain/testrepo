/**
 * 
 */
package com.ccighgo.service.components.partner.subpartner;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.partner.beans.subpartner.PartnerSubPartners;

/**
 * @author ravi
 *
 */
@Service
public interface SubPartnerInterface {
   
   public PartnerSubPartners getSubPartnersOfpartners(String partnerId);

}
