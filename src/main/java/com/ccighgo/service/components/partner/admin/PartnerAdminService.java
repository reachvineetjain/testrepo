/**
 * 
 */
package com.ccighgo.service.components.partner.admin;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata.PartnerRecruitmentAdminLead;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdmin;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.benchmarks.PartnerAdminDashboardBenchmarks;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quicklinks.PartnerAdminDashboardQuickLinks;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quickstatscategory.PartnerAdminDashboardQuickStatsCategory;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quickstatstitles.PartnerAdminDashboardQuickStatsTitles;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.AdminPartnerWorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplications;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplicationsDetail;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.AdminPartnerWorkQueueType;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Service
public interface PartnerAdminService {

 
   WSDefaultResponse changePartnerApplicationStatus(int goId, String newStatus);

   WSDefaultResponse updatePartnerApplicationFollowUpDate(int goId, String followUpdate);

   AdminPartnerWorkQueueSubmittedApplications getWorkQueueSubmittedApplications(int typeId, int categoryId, int staffUserId, String roleType);

   AdminPartnerWorkQueueType getWorkQueueType(String roleType);
   AdminPartnerWorkQueueCategory getWorkQueueCategory(int typeId);

   
   
   PartnerRecruitmentAdmin getPartnerInquiryOverviewData(int agentId);

   PartnerRecruitmentAdminLead getPartnerInquiryLeadData(int parseInt);

   PartnerRecruitmentAdmin updatePartnerInquiryOverViewData(PartnerRecruitmentAdmin partnerAdmin);

   PartnerRecruitmentAdminLead updatePartnerInquiryLeadData(PartnerRecruitmentAdminLead partnerRecruitmentAdminLead);

   PartnerAdminDashboardQuickLinks getQuickLinks();

   PartnerAdminDashboardQuickStatsTitles getQuickStatsTitle();

   PartnerAdminDashboardQuickStatsCategory getQuickStatsCategory(int quickStatsTypeID, String roleName);

   PartnerAdminDashboardBenchmarks getBenchmark();

   WSDefaultResponse addNoteToPartnerApplication(int parseInt, String noteValue);

   WSDefaultResponse getNotesOfPartnerApplication(int parseInt);
   

}
