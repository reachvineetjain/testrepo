/**
 * 
 */
package com.ccighgo.service.components.partner.admin;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdmin;
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

 
   AdminPartnerWorkQueueSubmittedApplicationsDetail changePartnerApplicationStatus(int typeId, int categoryId, int staffUserId, String roleType,String newStatus);

   AdminPartnerWorkQueueSubmittedApplicationsDetail updatePartnerApplicationFollowUpDate(int typeId, int categoryId, int staffUserId, String roleType,String newFollowUpDate);

   AdminPartnerWorkQueueSubmittedApplications getWorkQueueSubmittedApplications(int typeId, int categoryId, int staffUserId, String roleType);

   AdminPartnerWorkQueueType getWorkQueueType(String roleType);
   AdminPartnerWorkQueueCategory getWorkQueueCategory(int typeId);

   
   
   PartnerRecruitmentAdmin getAdminAgentRecruitmentData(int agentId);
   

}
