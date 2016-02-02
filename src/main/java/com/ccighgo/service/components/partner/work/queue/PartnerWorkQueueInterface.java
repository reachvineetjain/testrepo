/**
 * 
 */
package com.ccighgo.service.components.partner.work.queue;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.partner.beans.partneruser.PartnerWorkQueue;

 
/**
 * @author ravi
 *
 */
@Service
public interface PartnerWorkQueueInterface {

   PartnerWorkQueue getPartnerWorkQueues(String partnerId);

}
