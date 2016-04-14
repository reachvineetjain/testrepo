/**
 * 
 */
package com.ccighgo.service.components.partner.subpartner;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.partner.beans.allsalutation.AllSalutations;
import com.ccighgo.service.transport.partner.beans.subpartner.PartnerSubPartners;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerDetails;
import com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerScreeningNotes;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author ravi
 *
 */
@Service
public interface SubPartnerInterface {

   /**
    * @param partnerId
    * @return
    */
   public PartnerSubPartners getSubPartnersOfpartners(String partnerId);

   /**
    * @return
    */
   public SubPartnerDetails getAllSubPartners();

   /**
    * @param subPartnerId
    * @return
    */
   public com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail getSubPartnerDetail(String subPartnerId);

   /**
    * @param subPartner
    * @return
    */
   public WSDefaultResponse updateSubPartnerDetail(com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail subPartner);

   /**
    * @param subPartner
    * @return
    */
   public WSDefaultResponse createSubPartnerDetail(com.ccighgo.service.transport.partner.beans.subpartnerdetail.SubPartnerDetail subPartner);

   /**
    * @param partnerUserId
    * @param statusVal
    * @return
    */
   public WSDefaultResponse updatePartnerUserStatus(String partnerUserId, String statusVal);

   /**
    * @param noteDetail
    * @return
    */
   public WSDefaultResponse addSubPartnerScreenNote(SubPartnerScreeningNotes noteDetail);

   /**
    * @return
    */
   public AllSalutations getAllSalutation();

   /**
    * @param goId
    * @param loginId
    * @param status
    * @return
    */
   public WSDefaultResponse updatePartnerStatus(String goId, String loginId, String status);

   /**
    * @param goId
    * @return
    */
   public WSDefaultResponse deleteSubPartner(String goId);

}
