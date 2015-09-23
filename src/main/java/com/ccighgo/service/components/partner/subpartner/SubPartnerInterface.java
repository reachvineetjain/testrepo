/**
 * 
 */
package com.ccighgo.service.components.partner.subpartner;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.partner.beans.subpartner.PartnerSubPartners;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartner;

/**
 * @author ravi
 *
 */
@Service
public interface SubPartnerInterface {
   
   public PartnerSubPartners getSubPartnersOfpartners(String partnerId);

   public SubPartner viewSubPartners(String subPartnerId);
   
   public SubPartner createSubPartner(SubPartner subPartner);
   
   public SubPartner updateSubPartner(SubPartner subPartner);

}
