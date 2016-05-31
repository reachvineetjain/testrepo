/**
 * 
 */
package com.ccighgo.service.components.partner.admin;

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

   /**
    * @param goId
    * @param newStatus
    * @return
    */
   public WSDefaultResponse changePartnerApplicationStatus(int goId, String newStatus);

   /**
    * @param goId
    * @param followUpdate
    * @return
    */
   public WSDefaultResponse updatePartnerApplicationFollowUpDate(int goId, String followUpdate);

   /**
    * @param typeId
    * @param categoryId
    * @param staffUserId
    * @param roleType
    * @return
    */
   public AdminPartnerWorkQueueSubmittedApplications getWorkQueueSubmittedApplications(int typeId, int categoryId, int staffUserId, String roleType);

   /**
    * @param typeId
    * @param categoryId
    * @param staffUserId
    * @param roleType
    * @return
    */
   public AdminPartnerWorkQueueDeadlineRequests getWorkQueueDeadlineRequests(int typeId, int categoryId, int staffUserId, String roleType);

   /**
    * @param typeId
    * @param categoryId
    * @param staffUserId
    * @param roleType
    * @return
    */
   public AdminPartnerWorkQueueRequestChangeInAllocation getWorkQueueChangeInAllocationRequests(int typeId, int categoryId, int staffUserId, String roleType);

   /**
    * @param typeId
    * @param categoryId
    * @param staffUserId
    * @param roleType
    * @return
    */
   public AdminPartnerWorkQueueNotesReview getWorkQueuePartnerNoteReview(int typeId, int categoryId, int staffUserId, String roleType);

   /**
    * @param roleType
    * @return
    */
   public AdminPartnerWorkQueueType getWorkQueueType(String roleType);

   /**
    * @param typeId
    * @param userId
    * @return
    */
   public AdminPartnerWorkQueueCategory getWorkQueueCategory(int typeId, int userId);

   /**
    * @param agentId
    * @return
    */
   public PartnerRecruitmentAdmin getPartnerInquiryOverviewData(int agentId);

   /**
    * @param parseInt
    * @return
    */
   public PartnerRecruitmentAdminLead getPartnerInquiryLeadData(int parseInt);

   /**
    * @param partnerAdmin
    * @return
    */
   public PartnerRecruitmentAdmin updatePartnerInquiryOverViewData(PartnerRecruitmentAdmin partnerAdmin);

   /**
    * @param partnerRecruitmentAdminLead
    * @return
    */
   public PartnerRecruitmentAdminLead updatePartnerInquiryLeadData(PartnerRecruitmentAdminLead partnerRecruitmentAdminLead);

   /**
    * @return
    */
   public PartnerAdminDashboardQuickLinks getQuickLinks();

   /**
    * @return
    */
   public PartnerAdminDashboardQuickStatsTitles getQuickStatsTitle();

   /**
    * @param quickStatsTypeID
    * @param categoryId
    * @return
    */
   public PartnerAdminDashboardQuickStatsCategory getApplicationQuickStatsCategory(int quickStatsTypeID, int categoryId);

   /**
    * @return
    */
   public PartnerAdminDashboardBenchmarks getBenchmark();

   /**
    * @param parseInt
    * @param noteValue
    * @return
    */
   public WSDefaultResponse addNoteToPartnerApplication(int parseInt, String noteValue);

   /**
    * @param parseInt
    * @return
    */
   public WSDefaultResponse getNotesOfPartnerApplication(int parseInt);

   /**
    * @param document
    * @return
    */
   public PartnerAdminOverviewDocuments addNewPartnerInquiryDocument(PartnerAdminOverviewDocumentsDetails document);

   /**
    * @param deletedItems
    * @return
    */
   public PartnerAdminOverviewDocuments removeNewPartnerInquiryDocument(PartnerAdminOverviewDeletedDocuments deletedItems);

   /**
    * @param officesDetails
    * @return
    */
   public PartnerAdminOverviewOffices addNewPartnerInquiryOffice(PartnerAdminOverviewOfficesDetails officesDetails);

   /**
    * @param officesDetails
    * @return
    */
   public PartnerAdminOverviewOffices updatePartnerInquiryOffice(PartnerAdminOverviewOfficesDetails officesDetails);

   /**
    * @param deletedItems
    * @return
    */
   public PartnerAdminOverviewOffices removeNewPartnerInquiryOffice(PartnerAdminOverviewDeletedOffices deletedItems);

   /**
    * @param contactsDetails
    * @return
    */
   public PartnerAdminOverviewContacts addNewPartnerInquiryContact(PartnerAdminOverviewContactsDetails contactsDetails);

   /**
    * @param contactsDetails
    * @return
    */
   public PartnerAdminOverviewContacts updatePartnerInquiryContact(PartnerAdminOverviewContactsDetails contactsDetails);

   /**
    * @param deletedItems
    * @return
    */
   public PartnerAdminOverviewContacts removeNewPartnerInquiryContact(PartnerAdminOverviewDeletedContacts deletedItems);

   /**
    * @param notesDetails
    * @return
    */
   public PartnerAdminOverviewNotes addNewPartnerInquiryNote(PartnerAdminOverviewNotesDetails notesDetails);

   /**
    * @param referenceChecksDetails
    * @return
    */
   public PartnerAdminOverviewReferenceCheck addNewPartnerInquiryReferenceCheck(PartnerAdminOverviewReferenceCheckDetails referenceChecksDetails);

   /**
    * @param deletedItems
    * @return
    */
   public PartnerAdminOverviewReferenceCheck removeNewPartnerInquiryReferenceCheck(PartnerAdminOverviewDeletedReferenceCheck deletedItems);

   /**
    * @return
    */
   public WSDefaultResponse sendLogin();

   /**
    * @return
    */
   public CCIUsers getAllCCIUsers();

   /**
    * @param parseInt
    * @param newStatus
    * @param note
    * @return
    */
   public WSDefaultResponse changePartnerApplicationStatus(int parseInt, String newStatus, String note);

   /**
    * @param noteId
    * @param loginId
    * @return
    */
   public WSDefaultResponse markNoteRead(String noteId, String loginId);

   /**
    * @return
    */
   public PartnerStatusAsPatterns getPartnerStatusAsPattern();

   /**
    * @param SeasonId
    * @param ProgramId
    * @param PartnerGoId
    * @param followUpdate
    * @return
    */
   public WSDefaultResponse updatePartnerDeadLineChangeFollowUpDate(int SeasonId, int ProgramId, int PartnerGoId, String followUpdate);

   /**
    * @param partnerSeasonAllocationId
    * @param followUpdate
    * @return
    */
   public WSDefaultResponse updatePartnerAllocationChangeFollowUpDate(int partnerSeasonAllocationId, String followUpdate);

   /**
    * @param partnerNotesId
    * @param followUpdate
    * @return
    */
   public WSDefaultResponse updatePartnerNotesReviewFollowUpDate(int partnerNotesId, String followUpdate);

   /**
    * @param seasonId
    * @param subpartnerId
    * @param departmentProgramId
    * @param loginId
    * @return
    */
   public WSDefaultResponse assignSeasonToSubPartner(String seasonId, String subpartnerId, String departmentProgramId, String loginId);

   /**
    * @return
    */
   public SeasonsForPartners getAllAvailableSeasons();

   /**
    * @param partnerId
    * @return
    */
   public SeasonsForPartners getAllAvailableSeasons(String partnerId);

   /**
    * @param assignSubpartnerToSeason
    * @return
    */
   public WSDefaultResponse assignSeasonToSubPartner(AssignSeasonToSubPartner assignSubpartnerToSeason);

   /**
    * @param partnerId
    * @return
    */
   public SeasonsForPartners getAllAvailableSeasons2(String partnerId);

   public WSDefaultResponse setPrimaryContact(String contactId, String primaryValue);

}
