/**
 * 
 */
package com.ccighgo.service.components.partnerinquiry;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdmin;
import com.ccighgo.service.transport.integration.thirdparty.beans.agentViewForPartnerInquiryData.PartnerRecruitmentAgent;

/**
 * @author Ahmed
 *
 */
@Service
public interface PartnerInquiryService {

   PartnerRecruitmentAgent getAgentScreenForPartner();

   void updateAgentScreenPartner();

   void updateAdminScreenPartner();

   PartnerRecruitmentAdmin getAdminScreenForPartner();
   

}
