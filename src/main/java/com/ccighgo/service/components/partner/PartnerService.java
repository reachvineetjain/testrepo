/**
 * 
 */
package com.ccighgo.service.components.partner;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdmin;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.PartnerAdminDashboard;
import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerDetails;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.PartnerWorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.PartnerWorkQueueSubmittedApplications;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.PartnerWorkQueueType;
import com.ccighgo.utils.WSDefaultResponse;

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

   PartnerAdminDashboard getDashboard();
   
   WSDefaultResponse changePartnerApplicationStatus();

   WSDefaultResponse updatePartnerApplicationFollowUpDate();

   PartnerRecruitmentAdmin getAgentRecruitmentData(int agentId);

   PartnerWorkQueueType getWorkQueueType();

   PartnerWorkQueueSubmittedApplications getWorkQueueSubmittedApplications(int partnerAgentGoId);

   PartnerWorkQueueCategory getWorkQueueCategory();
   
}
