/**
 * 
 */
package com.ccighgo.service.components.partner.admin.partner;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.admin.add.partner.AdminAddPartner;

/**
 * @author ravi
 *
 */
@Service
public interface AdminPartnerInterface {

   public Response addPartner(AdminAddPartner partner);

}
