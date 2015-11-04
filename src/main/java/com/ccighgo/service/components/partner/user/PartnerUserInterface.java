/**
 * 
 */
package com.ccighgo.service.components.partner.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.partner.user.details.PartnerUserDetails;
import com.ccighgo.service.transport.partner.beans.partner.user.office.PartnerUserOffices;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUsers;

/**
 * @author ravi
 *
 */
@Service
public interface PartnerUserInterface {

   /**
    * Get the list of all partner user for specified partner
    * 
    * @param partnerId
    * @return list of Partner users active or inactive
    */
   public PartnerUsers getAllPartnerUsers(String partnerId);

   /**
    * Updates status of Partner user as active or inactive
    * 
    * @param statusVal
    * @param partnerGoId
    * @return Success or failure response
    */
   public Response updatePartnerUserStatus(String statusVal, String partnerUserId);

   /**
    * View Partner user details and permissions
    * 
    * @param partnerUserId
    * @return
    */
   public PartnerUserDetails getPartnerUserDetails(String partnerUserId);

   /**
    * Service to pre-populate Partner offices before adding partner user
    * 
    * @param partnerGoId
    * @return
    */
   public PartnerUserOffices getPartnerUserOffices(String partnerGoId);

   /**
    * @param partnerUserDetails
    * @param request
    * @return
    */
   public PartnerUserDetails addPartnerUser(PartnerUserDetails partnerUserDetails, HttpServletRequest request);

   /**
    * @param partnerUserDetails
    * @param request
    * @return
    */
   public PartnerUserDetails updatePartnerUser(PartnerUserDetails partnerUserDetails, HttpServletRequest request);

}
