/**
 * 
 */
package com.ccighgo.service.components.partner;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerDetails;

/**
 * @author ravi
 *
 */
@Service
public interface PartnerService {
   
   /**
    * Service to load partner dashboard
    * 
    * @param userId
    * @return
    */
   PartnerDetails getPartnerDetails(String userId);

}
