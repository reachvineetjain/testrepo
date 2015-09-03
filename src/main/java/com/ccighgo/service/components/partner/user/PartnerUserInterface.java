/**
 * 
 */
package com.ccighgo.service.components.partner.user;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUsers;

/**
 * @author ravi
 *
 */
@Service
public interface PartnerUserInterface {

   /**
    * @return
    */
  public PartnerUsers getAllPartnerUsers(String partnerId);

}
