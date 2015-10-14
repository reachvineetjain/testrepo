/**
 * 
 */
package com.ccighgo.service.components.partneradmin;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdmin;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.PartnerAdminDashboard;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.AdminPartnerWorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplications;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplicationsDetail;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.AdminPartnerWorkQueueType;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Service
public interface PartnerAdminService {

   PartnerAdminDashboard getDashboard();

   AdminPartnerWorkQueueSubmittedApplicationsDetail changePartnerApplicationStatus(int partnerAgentInquiryId);

   AdminPartnerWorkQueueSubmittedApplicationsDetail updatePartnerApplicationFollowUpDate(int partnerAgentInquiryId, String newFollowUpDate);

   PartnerRecruitmentAdmin getAgentRecruitmentData(int agentId);

   AdminPartnerWorkQueueType getWorkQueueType(int partnerGoId);

   AdminPartnerWorkQueueSubmittedApplications getWorkQueueSubmittedApplications(int partnerAgentGoId);

   AdminPartnerWorkQueueCategory getWorkQueueCategory(int partnerGoId);

}
