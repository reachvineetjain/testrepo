/**
 * 
 */
package com.ccighgo.service.components.partner.subpartner;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.partner.beans.subpartner.PartnerSubPartners;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartner;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerDetails;

/**
 * @author ravi
 *
 */
@Service
public interface SubPartnerInterface {
   
   public PartnerSubPartners getSubPartnersOfpartners(String partnerId);

   public SubPartner viewSubPartners(String subPartnerId);
   
   public SubPartner createSubPartner(SubPartner subPartner,HttpServletRequest request);
   
   public SubPartner updateSubPartner(SubPartner subPartner);
   
   public SubPartnerDetails getAllSubPartners();
   

}
