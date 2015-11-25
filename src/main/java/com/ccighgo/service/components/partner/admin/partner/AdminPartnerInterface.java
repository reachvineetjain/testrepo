/**
 * 
 */
package com.ccighgo.service.components.partner.admin.partner;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.admin.add.partner.AdminAddPartner;
import com.ccighgo.service.transport.partner.beans.admin.added.partner.AddedPartners;
import com.ccighgo.service.transport.partner.beans.admin.lead.partner.LeadPartners;

/**
 * @author ravi
 *
 */
@Service
public interface AdminPartnerInterface {

   /**
    * @param partner
    * @param request
    * @return
    */
   public Response addPartner(AdminAddPartner partner, HttpServletRequest request);

   /**
    * @return
    */
   public AddedPartners getAddedPartnerList();

   /**
    * @param loggedinUserLoginId
    * @param partnerLoginId
    * @return
    */
   public Response toggleActiveStatus(String statusVal, String loggedinUserLoginId, String partnerLoginId);

   /**
    * @param partnerUserId
    * @param request
    * @return
    */
   public Response sendLogin(String partnerUserId, HttpServletRequest request);

   /**
    * @return
    */
   public LeadPartners getLeadPartnerList();

   /**
    * @param partnerGoId
    * @return
    */
   public Response junkPartnerLead(String partnerGoId);

   /**
    * @param partnerGoId
    * @return
    */
   public Response blacklistPartnerLead(String partnerGoId);

   /**
    * @param partnerGoId
    * @param reason
    * @return
    */
   public Response invalidatePartnerLead(String partnerGoId, String reason);

   /**
    * @param partnerGoId
    * @param loginVal
    * @return
    */
   public Response partnerLeadSendLogin(String partnerGoId, String loginVal, HttpServletRequest request);

}
