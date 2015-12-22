/**
 * 
 */
package com.ccighgo.service.components.partner.admin;

import javax.ws.rs.PathParam;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata.PartnerRecruitmentAdminLead;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdmin;
import com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewContacts.PartnerAdminOverviewContacts;
import com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewContacts.PartnerAdminOverviewContactsDetails;
import com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewDocuments.PartnerAdminOverviewDocuments;
import com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewDocuments.PartnerAdminOverviewDocumentsDetails;
import com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewNotes.PartnerAdminOverviewNotes;
import com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewNotes.PartnerAdminOverviewNotesDetails;
import com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewOffices.PartnerAdminOverviewOffices;
import com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewOffices.PartnerAdminOverviewOfficesDetails;
import com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewReferenceCheck.PartnerAdminOverviewReferenceCheck;
import com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewReferenceCheck.PartnerAdminOverviewReferenceCheckDetails;
import com.ccighgo.service.transport.partner.beans.availableseasonsforpartner.SeasonsForPartners;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.benchmarks.PartnerAdminDashboardBenchmarks;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quicklinks.PartnerAdminDashboardQuickLinks;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quickstatscategory.PartnerAdminDashboardQuickStatsCategory;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quickstatstitles.PartnerAdminDashboardQuickStatsTitles;
import com.ccighgo.service.transport.partner.beans.partnerdeadlinerequest.AdminPartnerWorkQueueDeadlineRequests;
import com.ccighgo.service.transport.partner.beans.partnernotesreview.AdminPartnerWorkQueueNotesReview;
import com.ccighgo.service.transport.partner.beans.partnerstatusaspattern.PartnerStatusAsPatterns;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.AdminPartnerWorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplications;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplicationsDetail;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.AdminPartnerWorkQueueType;
import com.ccighgo.service.transport.partner.beans.requestchangeinallocation.AdminPartnerWorkQueueRequestChangeInAllocation;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUsers;
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

   AdminPartnerWorkQueueDeadlineRequests getWorkQueueDeadlineRequests(int typeId, int categoryId, int staffUserId, String roleType);

   AdminPartnerWorkQueueRequestChangeInAllocation getWorkQueueChangeInAllocationRequests(int typeId, int categoryId, int staffUserId, String roleType);

   AdminPartnerWorkQueueNotesReview getWorkQueuePartnerNoteReview(int typeId, int categoryId, int staffUserId, String roleType);

   AdminPartnerWorkQueueType getWorkQueueType(String roleType);

   AdminPartnerWorkQueueCategory getWorkQueueCategory(int typeId);

   PartnerRecruitmentAdmin getPartnerInquiryOverviewData(int agentId);

   PartnerRecruitmentAdminLead getPartnerInquiryLeadData(int parseInt);

   PartnerRecruitmentAdmin updatePartnerInquiryOverViewData(PartnerRecruitmentAdmin partnerAdmin);

   PartnerRecruitmentAdminLead updatePartnerInquiryLeadData(PartnerRecruitmentAdminLead partnerRecruitmentAdminLead);

   PartnerAdminDashboardQuickLinks getQuickLinks();

   PartnerAdminDashboardQuickStatsTitles getQuickStatsTitle();

   PartnerAdminDashboardQuickStatsCategory getApplicationQuickStatsCategory(int quickStatsTypeID, int categoryId);

   PartnerAdminDashboardBenchmarks getBenchmark();

   WSDefaultResponse addNoteToPartnerApplication(int parseInt, String noteValue);

   WSDefaultResponse getNotesOfPartnerApplication(int parseInt);

   PartnerAdminOverviewDocuments addNewPartnerInquiryDocument(PartnerAdminOverviewDocumentsDetails document);

   PartnerAdminOverviewDocuments removeNewPartnerInquiryDocument(PartnerAdminOverviewDeletedDocuments deletedItems);

   PartnerAdminOverviewOffices addNewPartnerInquiryOffice(PartnerAdminOverviewOfficesDetails officesDetails);

   PartnerAdminOverviewOffices updatePartnerInquiryOffice(PartnerAdminOverviewOfficesDetails officesDetails);

   PartnerAdminOverviewOffices removeNewPartnerInquiryOffice(PartnerAdminOverviewDeletedOffices deletedItems);

   PartnerAdminOverviewContacts addNewPartnerInquiryContact(PartnerAdminOverviewContactsDetails contactsDetails);

   PartnerAdminOverviewContacts updatePartnerInquiryContact(PartnerAdminOverviewContactsDetails contactsDetails);

   PartnerAdminOverviewContacts removeNewPartnerInquiryContact(PartnerAdminOverviewDeletedContacts deletedItems);

   PartnerAdminOverviewNotes addNewPartnerInquiryNote(PartnerAdminOverviewNotesDetails notesDetails);

   PartnerAdminOverviewReferenceCheck addNewPartnerInquiryReferenceCheck(PartnerAdminOverviewReferenceCheckDetails referenceChecksDetails);

   PartnerAdminOverviewReferenceCheck removeNewPartnerInquiryReferenceCheck(PartnerAdminOverviewDeletedReferenceCheck deletedItems);

   WSDefaultResponse sendLogin();

   CCIUsers getAllCCIUsers();

   WSDefaultResponse changePartnerApplicationStatus(int parseInt, String newStatus, String note);

   WSDefaultResponse markNoteRead(String noteId, String loginId);
   
   PartnerStatusAsPatterns getPartnerStatusAsPattern();

   WSDefaultResponse updatePartnerDeadLineChangeFollowUpDate(int SeasonId, int ProgramId, int PartnerGoId, String followUpdate);

   WSDefaultResponse updatePartnerAllocationChangeFollowUpDate(int partnerSeasonAllocationId, String followUpdate);

   WSDefaultResponse updatePartnerNotesReviewFollowUpDate(int partnerNotesId, String followUpdate);

   WSDefaultResponse assignSeasonToSubPartner(String seasonId, String subpartnerId, String departmentProgramId, String loginId);

   SeasonsForPartners getAllAvailableSeasons();

   SeasonsForPartners getAllAvailableSeasons(String partnerId);

   WSDefaultResponse assignSeasonToSubPartner(AssignSeasonToSubPartner assignSubpartnerToSeason);
   
   public SeasonsForPartners getAllAvailableSeasons2(String partnerId);

}
