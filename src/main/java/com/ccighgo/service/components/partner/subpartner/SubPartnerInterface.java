/**
 * 
 */
package com.ccighgo.service.components.partner.subpartner;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.subpartner.PartnerSubPartners;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartner;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerDetail;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerDetails;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerScreeningNotes;
import com.ccighgo.utils.WSDefaultResponse;

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
   
   public com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail getSubPartnerDetail(String subPartnerId);
   
   public WSDefaultResponse UpdateSubPartnerDetail(com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail subPartner);
   public WSDefaultResponse createSubPartnerDetail(com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail subPartner);
   public WSDefaultResponse updatePartnerUserStatus(String partnerUserId,String statusVal);
   public WSDefaultResponse addSubPartnerScreenNote(SubPartnerScreeningNotes noteDetail)  ;
}
