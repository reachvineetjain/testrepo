/**
 * 
 */
package com.ccighgo.service.components.partner;

import org.springframework.stereotype.Service;

import com.ccighgo.db.entities.PartnerWorkQueueCategory;
import com.ccighgo.db.entities.PartnerWorkQueueType;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdmin;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.PartnerAdminDashboard;
import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerDetails;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.PartnerWorkQueueSubmittedApplications;
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

   com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.PartnerWorkQueueType getWorkQueueType();

   PartnerWorkQueueSubmittedApplications getWorkQueueSubmittedApplications(int partnerAgentGoId);

   com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.PartnerWorkQueueCategory getWorkQueueCategory();
   
}