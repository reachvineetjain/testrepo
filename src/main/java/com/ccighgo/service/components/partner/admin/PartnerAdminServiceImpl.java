/**
 * 
 */
package com.ccighgo.service.components.partner.admin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.AdminQuickStatsCategory;
import com.ccighgo.db.entities.AdminQuickStatsCategoryAggregate;
import com.ccighgo.db.entities.AdminQuickStatsType;
import com.ccighgo.db.entities.AdminQuickStatsTypeAggregate;
import com.ccighgo.db.entities.AdminWorkQueueCategory;
import com.ccighgo.db.entities.AdminWorkQueueCategoryAggregate;
import com.ccighgo.db.entities.AdminWorkQueueType;
import com.ccighgo.db.entities.CCIStaffUsersCCIStaffRole;
import com.ccighgo.db.entities.DocumentInformation;
import com.ccighgo.db.entities.LookupCountry;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerAgentInquiry;
import com.ccighgo.db.entities.PartnerContact;
import com.ccighgo.db.entities.PartnerDocument;
import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerNoteTopic;
import com.ccighgo.db.entities.PartnerOffice;
import com.ccighgo.db.entities.PartnerOfficeType;
import com.ccighgo.db.entities.PartnerProgram;
import com.ccighgo.db.entities.PartnerReferenceCheck;
import com.ccighgo.db.entities.PartnerReviewStatus;
import com.ccighgo.db.entities.PartnerStatus;
import com.ccighgo.db.entities.Salutation;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.AdminQuickStatsCategoriesAggregateRepository;
import com.ccighgo.jpa.repositories.AdminQuickStatsCategoriesRepository;
import com.ccighgo.jpa.repositories.AdminQuickStatsTypeAggregateRepository;
import com.ccighgo.jpa.repositories.AdminQuickStatsTypeRepository;
import com.ccighgo.jpa.repositories.AdminWorkQueueCategoryAggregateRepository;
import com.ccighgo.jpa.repositories.AdminWorkQueueCategoryRepository;
import com.ccighgo.jpa.repositories.AdminWorkQueueTypeRepository;
import com.ccighgo.jpa.repositories.CCIStaffUsersCCIStaffRolesRepository;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.DocumentInformationRepository;
import com.ccighgo.jpa.repositories.DocumentTypeDocumentCategoryProcessRepository;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LookupDepartmentProgramRepository;
import com.ccighgo.jpa.repositories.PartnerAgentInquiryRepository;
import com.ccighgo.jpa.repositories.PartnerContactRepository;
import com.ccighgo.jpa.repositories.PartnerDocumentsRepository;
import com.ccighgo.jpa.repositories.PartnerMessagesRepository;
import com.ccighgo.jpa.repositories.PartnerNoteRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTopicRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeTypeRepository;
import com.ccighgo.jpa.repositories.PartnerProgramRepository;
import com.ccighgo.jpa.repositories.PartnerReferenceCheckRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerReviewStatusRepository;
import com.ccighgo.jpa.repositories.PartnerStatusRepository;
import com.ccighgo.jpa.repositories.SalutationRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminMessageConstants;
import com.ccighgo.service.components.usermanagment.UserManagementService;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata.PartnerRecruitmentAdminLead;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata.PartnerRecruitmentAdminLeadScreeningDetail;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningAdditionalInfo;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.AdminPartnerHspSettings;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.AdminPartnerProgramsElgibilityAndCCIContact;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.AdminPartnerRecruitmentScreeningDetail;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.CCIInquiryFormPerson;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.DocumentUploadUser;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.NoteUserCreator;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdmin;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningContacts;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningDocuments;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningNotes;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningOffices;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningReferenceCheck;
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
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.benchmarks.PartnerAdminDashboardBenchmarks;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.benchmarks.PartnerAdminDashboardBenchmarksDetails;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quicklinks.PartnerAdminDashboardQuickLinks;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quicklinks.PartnerAdminDashboardQuickLinksDetails;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quickstatscategory.PartnerAdminDashboardQuickStatsCategory;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quickstatscategory.PartnerAdminDashboardQuickStatsCategoryDetail;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quickstatstitles.PartnerAdminDashboardQuickStatsTitles;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quickstatstitles.PartnerAdminDashboardQuickStatsTitlesDetails;
import com.ccighgo.service.transport.partner.beans.partnerdeadlinerequest.AdminPartnerWorkQueueDeadlineRequests;
import com.ccighgo.service.transport.partner.beans.partnerdeadlinerequest.AdminPartnerWorkQueueDeadlineRequestsDetail;
import com.ccighgo.service.transport.partner.beans.partnernotesreview.AdminPartnerWorkQueueNotesReview;
import com.ccighgo.service.transport.partner.beans.partnernotesreview.AdminPartnerWorkQueueNotesReviewDetail;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.AdminPartnerWorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.AdminPartnerWorkQueueCategoryDetail;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplications;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplicationsDetail;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.AdminPartnerWorkQueueType;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.AdminPartnerWorkQueueTypeDetail;
import com.ccighgo.service.transport.partner.beans.requestchangeinallocation.AdminPartnerWorkQueueRequestChangeInAllocation;
import com.ccighgo.service.transport.partner.beans.requestchangeinallocation.AdminPartnerWorkQueueRequestChangeInAllocationDetails;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUsers;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Component
public class PartnerAdminServiceImpl implements PartnerAdminService {

   private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PartnerAdminServiceImpl.class);

   @Autowired
   MessageUtils messageUtil;
   @Autowired
   CommonComponentUtils componentUtils;

   @Autowired
   PartnerRepository partnerRepository;
   @Autowired
   LookupDepartmentProgramRepository lookupDepartmentProgramRepository;

   @Autowired
   PartnerAgentInquiryRepository partnerAgentInquiryRepository;
   @Autowired
   PartnerProgramRepository partnerProgramRepository;
   @Autowired
   PartnerMessagesRepository partnerMessagesRepository;
   @Autowired
   PartnerOfficeRepository partnerOfficeRepository;
   @Autowired
   PartnerReviewStatusRepository partnerReviewStatusRepository;
   @Autowired
   PartnerReferenceCheckRepository partnerReferenceCheckRepository;
   @Autowired
   PartnerDocumentsRepository partnerDocumentsRepository;
   @Autowired
   PartnerNoteRepository partnerNoteRepository;
   @Autowired
   AdminWorkQueueTypeRepository adminWorkQueueTypeRepository;
   @Autowired
   AdminWorkQueueCategoryRepository adminWorkQueueCategoryRepository;
   @Autowired
   AdminWorkQueueCategoryAggregateRepository adminWorkQueueCategoryAggregateRepository;
   @Autowired
   AdminQuickStatsCategoriesAggregateRepository adminQuickStatsCategoriesAggregateRepository;
   @Autowired
   AdminQuickStatsCategoriesRepository adminQuickStatsCategoriesRepository;
   @Autowired
   AdminQuickStatsTypeAggregateRepository adminQuickStatsTypeAggregateRepository;
   @Autowired
   AdminQuickStatsTypeRepository adminQuickStatsTypeRepository;
   @Autowired
   PartnerStatusRepository partnerStatusRepository;
   @Autowired
   PartnerProgramRepository partnerProgRepository;
   @Autowired
   PartnerContactRepository partnerContactRepository;
   @Autowired
   LoginRepository loginRepository;
   @Autowired
   GoIdSequenceRepository goIdSequenceRepository;
   @Autowired
   CCIStaffUsersCCIStaffRolesRepository cciStaffRolesRepository;
   @Autowired
   DocumentInformationRepository documentInformationRepository;
   @Autowired
   DocumentTypeDocumentCategoryProcessRepository documentTypeDocumentCategoryProcessRepository;
   @Autowired
   CountryRepository lookupCountryRepository;
   @Autowired
   PartnerOfficeTypeRepository partnerOfficeTypeRepository;
   @Autowired
   SalutationRepository salutationRepository;
   @Autowired
   PartnerNoteTopicRepository partnerNoteTopicRepository;
   @Autowired
   UserManagementService userManagementService;
   @PersistenceContext
   EntityManager em;

   @Override
   public PartnerRecruitmentAdminLead getPartnerInquiryLeadData(int goId) {
      PartnerRecruitmentAdminLead pwt = new PartnerRecruitmentAdminLead();
      try {
         pwt.setGoId(goId);
         PartnerAgentInquiry partnerAgentInquiry = partnerAgentInquiryRepository.findPartnerByGoId(goId);
         if (partnerAgentInquiry == null) {
            pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_PARTNER_INQUIRY_LEAD_DETAIL.getValue(),
                  messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_LEAD_DETAIL)));
            logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_LEAD_DETAIL));
            logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_LEAD_DETAIL));
            return pwt;
         }
         try {
            PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findStatusByPartnerId(goId);
            if (partnerReviewStatus != null) {
               if (partnerReviewStatus.getPartnerStatus2() != null)
                  pwt.setLeadStatus(partnerReviewStatus.getPartnerStatus2().getPartnerStatusName());
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         pwt.setFollowUpDate(DateUtils.getDateAndTime(partnerAgentInquiry.getFollowUpDate()));

         /**
          * Details
          */
         try {
            PartnerRecruitmentAdminLeadScreeningDetail detail = new PartnerRecruitmentAdminLeadScreeningDetail();
            if (partnerAgentInquiry.getRating() != null)
               detail.setRating(partnerAgentInquiry.getRating());
            if (partnerAgentInquiry.getAdressLineOne() != null)
               detail.setAddress1(partnerAgentInquiry.getAdressLineOne());
            if (partnerAgentInquiry.getAdressLineTwo() != null)
               detail.setAddress2(partnerAgentInquiry.getAdressLineTwo());
            if (partnerAgentInquiry.getCity() != null)
               detail.setCity(partnerAgentInquiry.getCity());
            if (partnerAgentInquiry.getCompanyName() != null)
               detail.setCompanyName(partnerAgentInquiry.getCompanyName());
            if (partnerAgentInquiry.getLookupCountry() != null)
               detail.setCountry(partnerAgentInquiry.getLookupCountry().getCountryName());
            if (partnerAgentInquiry.getEmail() != null)
               detail.setEmail(partnerAgentInquiry.getEmail());
            if (partnerAgentInquiry.getFirstName() != null)
               detail.setFirstName(partnerAgentInquiry.getFirstName());
            if (partnerAgentInquiry.getLastName() != null)
               detail.setLastName(partnerAgentInquiry.getLastName());
            if (partnerAgentInquiry.getPhone() != null)
               detail.setPhone(partnerAgentInquiry.getPhone());
            if (partnerAgentInquiry.getSalutation() != null)
               detail.setSalutation(partnerAgentInquiry.getSalutation().getSalutationName());
            if (partnerAgentInquiry.getState() != null)
               detail.setStateOrProvince(partnerAgentInquiry.getState());
            if (partnerAgentInquiry.getWebsite() != null)
               detail.setWebsite(partnerAgentInquiry.getWebsite());
            pwt.setDetails(detail);
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }

         /**
          * Additional Data
          */
         try {
            PartnerRecruitmentAdminScreeningAdditionalInfo additional = new PartnerRecruitmentAdminScreeningAdditionalInfo();
            additional.setDescribeProgramsOrganizationOffers(partnerAgentInquiry.getCurrentlyOfferingPrograms());
            additional.setHearAboutUsFrom(partnerAgentInquiry.getHowDidYouHearAboutCCI());
            additional.setIsYourOrganizationSendingParticipantstoUSA(partnerAgentInquiry.getCurrentlySendingParticipantToUS() == 1);
            additional.setLikeToKnowMoreAboutAmbassadorScholarship(partnerAgentInquiry.getAmbassadorScholershipParticipants() == 1);
            additional.setProgramsYouOffer(partnerAgentInquiry.getCurrentlyOfferingPrograms());
            additional.setSendPartnersToUSA(partnerAgentInquiry.getCurrentlySendingParticipantToUS() == 1);
            additional.setYearsInBusiness(Integer.parseInt(partnerAgentInquiry.getBusinessYears()));
            pwt.setAdditionalInformation(additional);
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }

         /*
          * Notes
          */
         try {
            List<PartnerNote> partnerNotes = partnerNoteRepository.findAllPartnerNoteByPartnerId(goId);
            if (partnerNotes != null) {
               for (PartnerNote partnerNote : partnerNotes) {
                  com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningNotes note = new com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningNotes();
                  /*CCIStaffUsersCCIStaffRole staffUserAndRole = cciStaffRolesRepository.findOne(partnerNote.getCreatedBy());
                  if (staffUserAndRole != null) {
                     com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata.NoteUserCreator noteCreator = new com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata.NoteUserCreator();
                     noteCreator.setPhotoUrl(staffUserAndRole.getCcistaffUser().getPhoto());
                     noteCreator.setRole(staffUserAndRole.getCcistaffRole().getCciStaffRoleName());
                     noteCreator.setUserName(staffUserAndRole.getCcistaffUser().getFirstName() + " " + staffUserAndRole.getCcistaffUser().getLastName());
                     note.setCreatedBy(noteCreator);
                  }*/
                  note.setTopic(partnerNote.getPartnerNoteTopic().getPartnerNoteTopicName());
                  note.setPartnerNoteId(partnerNote.getPartnerNotesId());
                  note.setCreatedOn(DateUtils.getDateAndTime(partnerNote.getCreatedOn()));
                  note.setNoteValue(partnerNote.getPartnerNote());
                  pwt.getNotes().add(note);
               }
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         pwt.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.PARTNER_INQUIURY_LEAD.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_PARTNER_INQUIRY_LEAD_DETAIL.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_LEAD_DETAIL)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_LEAD_DETAIL));
      }
      return pwt;
   }

   @Override
   public PartnerRecruitmentAdminLead updatePartnerInquiryLeadData(PartnerRecruitmentAdminLead pwt) {
       try {
         PartnerAgentInquiry partnerAgentInquiry = partnerAgentInquiryRepository.findPartnerByGoId(pwt.getGoId());
         if (partnerAgentInquiry == null) {
            pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATING__WOEKQUEUE_PARTNER_INQUIRY_LEAD.getValue(),
                  messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_LEAD_UPDATE)));
            logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_LEAD_UPDATE));
            logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_LEAD_UPDATE));
            return pwt;
         }
         try {
            PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findStatusByPartnerId(pwt.getGoId());
            if (partnerReviewStatus != null) {
               PartnerStatus activeStatus = partnerStatusRepository.findStatusByName(pwt.getLeadStatus());
               partnerReviewStatus.setPartnerStatus1(activeStatus);
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            partnerAgentInquiry.setFollowUpDate(DateUtils.getDateFromString(pwt.getFollowUpDate()));
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            PartnerRecruitmentAdminLeadScreeningDetail detail = pwt.getDetails();
            partnerAgentInquiry.setCompanyName(detail.getCompanyName());
            partnerAgentInquiry.setRating(detail.getRating());
            partnerAgentInquiry.setAdressLineOne(detail.getAddress1());
            partnerAgentInquiry.setAdressLineTwo(detail.getAddress2());
            partnerAgentInquiry.setEmail(detail.getEmail());
            partnerAgentInquiry.setFirstName(detail.getFirstName());
            partnerAgentInquiry.setLastName(detail.getLastName());
            partnerAgentInquiry.setPhone(detail.getPhone());
            partnerAgentInquiry.setWebsite(detail.getWebsite());
            partnerAgentInquiry.setCity(detail.getCity());
            partnerAgentInquiry.setState(detail.getStateOrProvince());
            LookupCountry lookupCountry = lookupCountryRepository.findByCountryName(detail.getCountry());
            partnerAgentInquiry.setLookupCountry(lookupCountry);
            Salutation salutation = salutationRepository.findBySalutationName(detail.getSalutation());
            partnerAgentInquiry.setSalutation(salutation);

         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            //TODO
            PartnerRecruitmentAdminScreeningAdditionalInfo additionalInformation = pwt.getAdditionalInformation();
            partnerAgentInquiry.setCurrentlyOfferingPrograms(additionalInformation.getProgramsYouOffer());
            partnerAgentInquiry.setCurrentlySendingParticipantToUS((byte) (additionalInformation.isIsYourOrganizationSendingParticipantstoUSA()?1:0));
            partnerAgentInquiry.setBusinessYears(additionalInformation.getYearsInBusiness()+"");
            partnerAgentInquiry.setHowDidYouHearAboutCCI(additionalInformation.getHearAboutUsFrom());
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }

         partnerAgentInquiryRepository.saveAndFlush(partnerAgentInquiry);

         pwt.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.ERROR_UPDATING__WOEKQUEUE_PARTNER_INQUIRY_LEAD.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_PARTNER_INQUIRY_LEAD_UPDATE.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_LEAD_UPDATE)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_LEAD_UPDATE));
      }
      return pwt;
   }

   @Transactional
   @Override
   public PartnerRecruitmentAdmin updatePartnerInquiryOverViewData(PartnerRecruitmentAdmin pwt) {
      try {
         PartnerAgentInquiry partnerAgentInquiry = partnerAgentInquiryRepository.findPartnerByGoId(pwt.getGoId());
         List<PartnerProgram> partnerPrograms = partnerProgramRepository.findAllPartnerProgramsByPartnerId(pwt.getGoId());

         if (partnerAgentInquiry == null) {
            pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATING__WOEKQUEUE_PARTNER_INQUIRY_OVERVIEW.getValue(),
                  messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_OVERVIEW_UPDATE)));
            logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_OVERVIEW_UPDATE));
            logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_OVERVIEW_UPDATE));
            return pwt;
         }
         Partner partner = partnerAgentInquiry.getPartner();

         try {
            PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findStatusByPartnerId(pwt.getGoId());
            if (partnerReviewStatus != null) {
               if (pwt.isActive()) {
                  PartnerStatus activeStatus = partnerStatusRepository.findStatusByName("Valid");
                  partnerReviewStatus.setPartnerStatus1(activeStatus);
               }
               if (pwt.getLeadStatus() != null) {
                  PartnerStatus leadStatus = partnerStatusRepository.findStatusByName(pwt.getLeadStatus());
                  partnerReviewStatus.setPartnerStatus2(leadStatus);
               }
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }

         try {
            AdminPartnerRecruitmentScreeningDetail detail = pwt.getDetail();
            partnerAgentInquiry.setCompanyName(detail.getCompanyName());
            partnerAgentInquiry.setLogo(detail.getLogo());
            partnerAgentInquiry.setRating(detail.getRating());
            // TODO Ask phani !!!!!!
            partnerAgentInquiry.setBusinessName(detail.getUsername());
            partnerAgentInquiryRepository.saveAndFlush(partnerAgentInquiry);
            if (partner != null) {
               partner.setCompanyName(detail.getCompanyName());
               partner.setBillingNotes(detail.getBillingNotes());
               partner.setCanHaveSubPartner((byte) (detail.getCanHaveSubPartner() == "true" ? 1 : 0));
               partner.setEmail(detail.getGeneralEmail());
               partner.setInvoiceMail(detail.getInvoiceEmail());
               partner.setMultiCountrySender((byte) (detail.getMultiCountrySender() == "true" ? 1 : 0));
               partner.setQuickbooksCode(detail.getQuickbooksCode());
            }

         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            AdminPartnerHspSettings hsp = pwt.getHspSettings();
            partner.setParticipantTranscriptRequired((byte) (hsp.isAypParticipantTranscriptRequired() ? 1 : 0));
            partner.setParticipantELTISRequired((byte) (hsp.isHspParticipantEltisRequired() ? 1 : 0));
            partner.setParticipantMedicalReleaseRequired((byte) (hsp.isHspParticipantMedicalReleaseRequired() ? 1 : 0));
            partner.setParticipantSLEPRequired((byte) (hsp.isHspParticipantSlepRequired() ? 1 : 0));
            partner.setUnguaranteedFormRequired((byte) (hsp.isHspParticipantUnquaranteedFromRequired() ? 1 : 0));
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         partnerRepository.saveAndFlush(partner);

         pwt.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.PARTNER_INQUIURY_OVERVIEW_UPDATE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.WOEKQUEUE_PARTNER_INQUIRY_OVERVIEW_UPDATE.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_OVERVIEW_UPDATE)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_OVERVIEW_UPDATE));
      }
      return pwt;
   }

   @Override
   public PartnerRecruitmentAdmin getPartnerInquiryOverviewData(int goId) {
      PartnerRecruitmentAdmin pwt = new PartnerRecruitmentAdmin();
      try {
         pwt.setGoId(goId);
         PartnerAgentInquiry partnerAgentInquiry = partnerAgentInquiryRepository.findPartnerByGoId(goId);
         List<PartnerProgram> partnerPrograms = partnerProgramRepository.findAllPartnerProgramsByPartnerId(goId);
         if (partnerAgentInquiry == null) {
            pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_PARTNER_INQUIRY_OVERVIEW_DETAIL.getValue(),
                  messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_OVERVIEW_DETAIL)));
            logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_OVERVIEW_DETAIL));
            logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_OVERVIEW_DETAIL));
            return pwt;
         }
         try {
            PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findStatusByPartnerId(goId);
            if (partnerReviewStatus != null) {
               if (partnerReviewStatus.getPartnerStatus1() != null)
                  pwt.setActive(partnerReviewStatus.getPartnerStatus1().getPartnerStatusName().equalsIgnoreCase("Valid"));
               if (partnerReviewStatus.getPartnerStatus2() != null)
                  pwt.setLeadStatus(partnerReviewStatus.getPartnerStatus2().getPartnerStatusName());
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            AdminPartnerRecruitmentScreeningDetail partnerRecruitmentAdminScreeningDetail = new AdminPartnerRecruitmentScreeningDetail();
            if (partnerAgentInquiry.getCompanyName() != null)
               partnerRecruitmentAdminScreeningDetail.setCompanyName(partnerAgentInquiry.getCompanyName());
            if (partnerAgentInquiry.getPartner() != null) {
               partnerRecruitmentAdminScreeningDetail.setBillingNotes(partnerAgentInquiry.getPartner().getBillingNotes());
               partnerRecruitmentAdminScreeningDetail.setCanHaveSubPartner(partnerAgentInquiry.getPartner().getCanHaveSubPartner() == 1 ? "true" : "false");
               partnerRecruitmentAdminScreeningDetail.setGeneralEmail(partnerAgentInquiry.getPartner().getEmail());
               partnerRecruitmentAdminScreeningDetail.setInvoiceEmail(partnerAgentInquiry.getPartner().getInvoiceMail());
               partnerRecruitmentAdminScreeningDetail.setMultiCountrySender(partnerAgentInquiry.getPartner().getMultiCountrySender() == 1 ? "true" : "false");
               partnerRecruitmentAdminScreeningDetail.setQuickbooksCode(partnerAgentInquiry.getPartner().getQuickbooksCode());
            }
            try {
               for (PartnerProgram partnerProgram : partnerPrograms) {
                  CCIInquiryFormPerson cciContact = new CCIInquiryFormPerson();
                  cciContact.setUserName(partnerProgram.getCcistaffUser().getFirstName());
                  if (partnerProgram.getCcistaffUser().getCcistaffUsersCcistaffRoles() != null && !partnerProgram.getCcistaffUser().getCcistaffUsersCcistaffRoles().isEmpty())
                     cciContact.setRole(partnerProgram.getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole().getCciStaffRoleName());
                  cciContact.setImageUrl(partnerProgram.getCcistaffUser().getPhoto());
                  partnerRecruitmentAdminScreeningDetail.setCciContact(cciContact);
                  break;
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, logger);
            }
            if (partnerAgentInquiry.getLogo() != null)
               partnerRecruitmentAdminScreeningDetail.setLogo(partnerAgentInquiry.getLogo());

            if (partnerAgentInquiry.getRating() != null)
               partnerRecruitmentAdminScreeningDetail.setRating(partnerAgentInquiry.getRating());

            if (partnerAgentInquiry.getBusinessName() != null)
               partnerRecruitmentAdminScreeningDetail.setUsername(partnerAgentInquiry.getBusinessName());

            pwt.setDetail(partnerRecruitmentAdminScreeningDetail);
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }

         try {
            if (partnerPrograms != null) {
               for (PartnerProgram partnerProgram : partnerPrograms) {
                  AdminPartnerProgramsElgibilityAndCCIContact contact = new AdminPartnerProgramsElgibilityAndCCIContact();
                  contact.setCciContactProgramName(partnerProgram.getLookupDepartmentProgram().getProgramName());
                  contact.setMarked(false);
                  contact.setProgramName(partnerProgram.getLookupDepartmentProgram().getProgramName());
                  if (partnerProgram.getCcistaffUser() != null) {
                     CCIInquiryFormPerson cciContact = new CCIInquiryFormPerson();
                     cciContact.setUserName(partnerProgram.getCcistaffUser().getFirstName());
                     if (partnerProgram.getCcistaffUser().getCcistaffUsersCcistaffRoles() != null && !partnerProgram.getCcistaffUser().getCcistaffUsersCcistaffRoles().isEmpty())
                        cciContact.setRole(partnerProgram.getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole().getCciStaffRoleName());
                     cciContact.setImageUrl(partnerProgram.getCcistaffUser().getPhoto());
                     contact.setCciContact(cciContact);
                     pwt.getProgramEligibilityAndCCIContact().add(contact);
                  }
               }
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }

         try {
            AdminPartnerHspSettings adminPartnerHspSettings = new AdminPartnerHspSettings();
            adminPartnerHspSettings.setAypParticipantTranscriptRequired(partnerAgentInquiry.getPartner().getParticipantTranscriptRequired() == 1);
            adminPartnerHspSettings.setHspParticipantEltisRequired(partnerAgentInquiry.getPartner().getParticipantELTISRequired() == 1);
            adminPartnerHspSettings.setHspParticipantMedicalReleaseRequired(partnerAgentInquiry.getPartner().getParticipantMedicalReleaseRequired() == 1);
            adminPartnerHspSettings.setHspParticipantSlepRequired(partnerAgentInquiry.getPartner().getParticipantSLEPRequired() == 1);
            adminPartnerHspSettings.setHspParticipantUnquaranteedFromRequired(partnerAgentInquiry.getPartner().getUnguaranteedFormRequired() == 1);
            pwt.setHspSettings(adminPartnerHspSettings);
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }

         try {
            List<PartnerOffice> offices = partnerOfficeRepository.findPartnerOfficeByPartnerId(goId);
            if (offices != null) {
               for (PartnerOffice partnerOffice : offices) {
                  PartnerRecruitmentAdminScreeningOffices office = new PartnerRecruitmentAdminScreeningOffices();
                  office.setPartnerOfficeId(partnerOffice.getPartnerOfficeId());
                  office.setAddress1(partnerOffice.getAdressOne());
                  office.setAddress2(partnerOffice.getAdressTwo());
                  office.setCity(partnerOffice.getCity());
                  office.setCountry(partnerOffice.getLookupCountry().getCountryName());
                  office.setEmail(partnerOffice.getPartner().getEmail());
                  office.setFax(partnerOffice.getFaxNumber());
                  office.setPhone(partnerOffice.getPhoneNumber());
                  office.setWebsite(partnerOffice.getWebsite());
                  office.setZipCode(partnerOffice.getPostalCode());
                  office.setOfficeType(partnerOffice.getPartnerOfficeType().getPartnerOfficeType());
                  pwt.getOffices().add(office);
               }
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }

         try {
            List<PartnerContact> contacts = partnerContactRepository.findPartnerContactsByPartnerId(goId);
            if (contacts != null) {
               for (PartnerContact partnerContact : contacts) {
                  PartnerRecruitmentAdminScreeningContacts contact = new PartnerRecruitmentAdminScreeningContacts();
                  contact.setPartnerContactId(partnerContact.getPartnerContactId());
                  contact.setActive(partnerContact.getActive() == 1);
                  contact.setEmail(partnerContact.getEmail());
                  contact.setEmergencyPhone(partnerContact.getEmergencyPhone());
                  contact.setFax(partnerContact.getFax());
                  contact.setFirstName(partnerContact.getFirstName());
                  contact.setLastName(partnerContact.getLastName());
                  contact.setPhone(partnerContact.getPhone());
                  // contact.setPrograms(partnerContact.get);
                  contact.setSalutation(partnerContact.getSalutation().getSalutationName());
                  contact.setSkypeId(partnerContact.getSkypeId());
                  contact.setTitile(partnerContact.getTitle());
                  // contact.setUsername(partnerContact.get);
                  contact.setPrimaryContact(partnerContact.getIsPrimary() == 1);
                  pwt.getContacts().add(contact);
               }
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }

         try {
            List<PartnerReferenceCheck> partnerReferenceChecks = partnerReferenceCheckRepository.findAllPartnerReferenceCheckByPartnerId(goId);
            if (partnerReferenceChecks != null) {
               for (PartnerReferenceCheck partnerReferenceCheck : partnerReferenceChecks) {
                  PartnerRecruitmentAdminScreeningReferenceCheck refCheck = new PartnerRecruitmentAdminScreeningReferenceCheck();
                  refCheck.setPartnerCheckReferenceId(partnerReferenceCheck.getPartnerReferenceCheckId());
                  refCheck.setApprovedBy(partnerReferenceCheck.getReferenceApprovedBy());
                  refCheck.setApprovedOn(DateUtils.getDateAndTime(partnerReferenceCheck.getReferenceApprovedOn()));
                  refCheck.setCompletedBy(partnerReferenceCheck.getReferenceCompletedBy());
                  refCheck.setCompletedOn(DateUtils.getDateAndTime(partnerReferenceCheck.getReferenceCompletedOn()));
                  refCheck.setLatestCopyOfBusinessExpires(DateUtils.getDateAndTime(partnerReferenceCheck.getBusinessLicenseExpiryDate()));
                  refCheck.setNote(partnerReferenceCheck.getReferenceCheckNotes());
                  pwt.getReferenceCheck().add(refCheck);
               }
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            List<PartnerDocument> partnerDocuments = partnerDocumentsRepository.findAllPartnerDocumentByPartnerId(goId);
            if (partnerDocuments != null) {
               for (PartnerDocument p : partnerDocuments) {
                  PartnerRecruitmentAdminScreeningDocuments doc = new PartnerRecruitmentAdminScreeningDocuments();
                  doc.setPartnerDocumentId(p.getPartnerDocumentId());
                  doc.setActive(p.getDocumentInformation().getActive() == 1);
                  doc.setDescription("");
                  doc.setDocName(p.getDocumentInformation().getDocumentName());
                  doc.setDocType(p.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
                  doc.setDocUrl(p.getDocumentInformation().getUrl());
                  doc.setFileName(p.getDocumentInformation().getFileName());
                  doc.setFileType("");
                  doc.setUploadDate(DateUtils.getDateAndTime(p.getDocumentInformation().getCreatedOn()));
                  Integer createdBy = p.getDocumentInformation().getCreatedBy();
                 /* CCIStaffUsersCCIStaffRole staffUserAndRole = cciStaffRolesRepository.findOne(createdBy);
                  if (staffUserAndRole != null) {
                     DocumentUploadUser uploadedBy = new DocumentUploadUser();
                     uploadedBy.setPhotoUrl(staffUserAndRole.getCcistaffUser().getPhoto());
                     uploadedBy.setRole(staffUserAndRole.getCcistaffRole().getCciStaffRoleName());
                     uploadedBy.setUserName(staffUserAndRole.getCcistaffUser().getFirstName() + " " + staffUserAndRole.getCcistaffUser().getLastName());
                     doc.setUploadedBy(uploadedBy);
                  }*/
                  pwt.getDocuments().add(doc);
               }
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            List<PartnerNote> partnerNotes = partnerNoteRepository.findAllPartnerNoteByPartnerId(goId);
            if (partnerNotes != null) {
               for (PartnerNote partnerNote : partnerNotes) {
                  PartnerRecruitmentAdminScreeningNotes note = new PartnerRecruitmentAdminScreeningNotes();
                 /* CCIStaffUsersCCIStaffRole staffUserAndRole = cciStaffRolesRepository.findOne(partnerNote.getCreatedBy());
                  if (staffUserAndRole != null) {
                     NoteUserCreator noteCreator = new NoteUserCreator();
                     noteCreator.setPhotoUrl(staffUserAndRole.getCcistaffUser().getPhoto());
                     noteCreator.setRole(staffUserAndRole.getCcistaffRole().getCciStaffRoleName());
                     noteCreator.setUserName(staffUserAndRole.getCcistaffUser().getFirstName() + " " + staffUserAndRole.getCcistaffUser().getLastName());
                     note.setCreatedBy(noteCreator);
                  }*/
                  note.setTopic(partnerNote.getPartnerNoteTopic().getPartnerNoteTopicName());
                  note.setPartnerNoteId(partnerNote.getPartnerNotesId());
                  note.setCreatedOn(DateUtils.getDateAndTime(partnerNote.getCreatedOn()));
                  note.setNoteValue(partnerNote.getPartnerNote());
                  pwt.getNotes().add(note);
               }
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         pwt.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.PARTNER_INQUIURY_OVERVIEW.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_PARTNER_INQUIRY_OVERVIEW_DETAIL.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_OVERVIEW_DETAIL)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_OVERVIEW_DETAIL));
      }
      return pwt;
   }

   @Override
   public AdminPartnerWorkQueueType getWorkQueueType(String roleType) {
      AdminPartnerWorkQueueType pwt = new AdminPartnerWorkQueueType();
      try {
         List<AdminWorkQueueType> types = adminWorkQueueTypeRepository.findTypesByPartnerRole(roleType);
         if (types != null) {
            for (AdminWorkQueueType adminWorkQueueType : types) {
               AdminPartnerWorkQueueTypeDetail newType = new AdminPartnerWorkQueueTypeDetail();
               newType.setTypeId(adminWorkQueueType.getAdminWQTypeId());
               newType.setTypeName(adminWorkQueueType.getAdminWQTypeName());
               pwt.getWorkQueueType().add(newType);
            }
         }
         pwt.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.WORK_QUEUE_TYPE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_TYPE.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_TYPE)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_TYPE));
      }
      return pwt;
   }

   @Override
   public AdminPartnerWorkQueueCategory getWorkQueueCategory(int adminWorkQueueTypeId) {
      AdminPartnerWorkQueueCategory pwqc = new AdminPartnerWorkQueueCategory();
      try {
         List<AdminWorkQueueCategory> categories = adminWorkQueueCategoryRepository.findAllCategoriesByTypeId(adminWorkQueueTypeId);
         if (categories != null) {
            for (AdminWorkQueueCategory adminWorkQueueCategory : categories) {
               AdminPartnerWorkQueueCategoryDetail newCategory = new AdminPartnerWorkQueueCategoryDetail();
               newCategory.setAdminWorkQueueTypeId(adminWorkQueueCategory.getAdminWorkQueueType().getAdminWQTypeId());
               newCategory.setCategoryId(adminWorkQueueCategory.getAdminWorkQueueCategoryId());
               newCategory.setCategoryName(adminWorkQueueCategory.getAdminWorkQueueCategoryName());
               if (adminWorkQueueCategory.getAdminWorkQueueType().getAdminWQTypeName().equalsIgnoreCase("Application")) {
                  if (newCategory.getCategoryName().equals("Submitted")) {
                     newCategory.setServiceUrl(CCIConstants.SERVICE_URL_WORK_QUEUE_CATEGORY_SUBMITTED_TYPE_APPLICATION_1);
                  } else {
                     newCategory.setServiceUrl(CCIConstants.SERVICE_URL_NDY);
                  }
               } else {
                  newCategory.setServiceUrl(CCIConstants.SERVICE_URL_NDY);
               }
               AdminWorkQueueCategoryAggregate categoryAggregate = adminWorkQueueCategoryAggregateRepository.findAggregateValueForCategory(adminWorkQueueTypeId,
                     adminWorkQueueCategory.getAdminWorkQueueCategoryId());
               if (categoryAggregate != null) {
                  newCategory.setCategoryAggregate(categoryAggregate.getAdminWQCategoryAggregate());
               }
               pwqc.getAdminWorkQueueCategory().add(newCategory);
            }
         }
         pwqc.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.WORK_QUEUE_CATEGORY.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         pwqc.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_CATEGORY.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_CATEGORY)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_CATEGORY));
      }
      return pwqc;
   }

   @Override
   public AdminPartnerWorkQueueSubmittedApplications getWorkQueueSubmittedApplications(int typeId, int categoryId, int staffUserId, String roleType) {
      AdminPartnerWorkQueueSubmittedApplications pwqa = new AdminPartnerWorkQueueSubmittedApplications();
      try {
         // Admin work queue partner Search
         @SuppressWarnings("unchecked")
         List<Object[]> result = em.createNativeQuery("call SPAdminWQPartnerSearch(:typeId,:categoryId,:cciStaffUserId,:roleType)").setParameter("typeId", typeId)
               .setParameter("categoryId", categoryId).setParameter("cciStaffUserId", staffUserId).setParameter("roleType", roleType).getResultList();
         if (result != null) {
            for (Object[] wq : result) {
               AdminPartnerWorkQueueSubmittedApplicationsDetail pd = new AdminPartnerWorkQueueSubmittedApplicationsDetail();
               pd.setCompanyName(String.valueOf(wq[0]));
               pd.setFirstName(String.valueOf(wq[1]));
               pd.setLastName(String.valueOf(wq[2]));
               pd.setPhone(String.valueOf(wq[3]));
               pd.setEmail(String.valueOf(wq[4]));
               pd.setWebsite(String.valueOf(wq[5]));
               pd.setCountry(String.valueOf(wq[6]));
               if (wq[7] != null) {
                  String followUpdate = String.valueOf(wq[7]);
                  pd.setFollowUpDate(followUpdate.split("\\s+")[0]);
               }
               if (wq[8] != null) {
                  String submittedOn = String.valueOf(wq[8]);
                  pd.setSunmittedOn(submittedOn.split("\\s+")[0]);
               }
               pd.setFlagUrl(String.valueOf(wq[9]));
               pd.setPrograms(String.valueOf(wq[10]));
               if (wq[11] != null)
                  pd.setNotesCount(Integer.parseInt(String.valueOf(wq[11])));
               if (wq[12] != null)
                  pd.setGoId(Integer.parseInt(String.valueOf(wq[12])));

               PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findApplicationStatusByGoId(pd.getGoId());
               if (partnerReviewStatus != null) {
                  pd.setStatusOfInquiry(partnerReviewStatus.getPartnerStatus1().getPartnerStatusName());
               }

               pwqa.getWorkQueueSubmittedApplications().add(pd);
            }
            pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.WOEKQUEUE_SUBMITTED_APPLICATIONS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_WOEKQUEUE_SUBMITTED_APPLICATIONS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         pwqa.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_SUBMITTED_APPLICATIONS.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_SUBMITTED_APPLICATIONS)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_SUBMITTED_APPLICATIONS));
      }
      return pwqa;
   }
   @Override
   public AdminPartnerWorkQueueDeadlineRequests getWorkQueueDeadlineRequests(
		   int typeId, int categoryId, int staffUserId, String roleType) {
	   AdminPartnerWorkQueueDeadlineRequests adr =new AdminPartnerWorkQueueDeadlineRequests();
	   try
	   {

		   @SuppressWarnings("unchecked")
		   List<Object[]> result = em.createNativeQuery("call SPAdminWQPartnerDeadlineChange(:typeId,:categoryId,:cciStaffUserId,:roleType)").setParameter("typeId", typeId)
		   .setParameter("categoryId", categoryId).setParameter("cciStaffUserId", staffUserId).setParameter("roleType", roleType).getResultList();
		   if(result !=null)
		   {
			   for(Object[] dr:result)
			   {
				   AdminPartnerWorkQueueDeadlineRequestsDetail drd = new AdminPartnerWorkQueueDeadlineRequestsDetail();
				   drd.setCompanyName(String.valueOf(dr[0]));
				   if(dr[1]!=null)
					   drd.setGoId(Integer.valueOf(String.valueOf(dr[1])));
				   drd.setPartnerStatus(String.valueOf(dr[2]));
				   drd.setSeasonName(String.valueOf(dr[3]));
				   drd.setCountry(String.valueOf(dr[4]));
				   drd.setSunmittedOn(String.valueOf(dr[5]));
				   drd.setFlagUrl(String.valueOf(dr[6]));
				   /*
				    * 
				    * 
				    */ 
				   adr.getDeadlineRequests().add(drd);
			   }
			   adr.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.WOEKQUEUE_SUBMITTED_DEADLINE.getValue(),
		                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
		   }
		   else
		   {
			   adr.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_WOEKQUEUE_SUBMITTED_DEADLINE.getValue(),
		                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
		   }
	   }catch(Exception e)
	   {
		   ExceptionUtil.logException(e, logger);
		   adr.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_SUBMITTED_APPLICATIONS.getValue(),
				   messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_SUBMITTED_DEADLINE)));
		   logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_SUBMITTED_DEADLINE)); 
	   }
	   return adr;
   }
   
   @Override
   public AdminPartnerWorkQueueRequestChangeInAllocation getWorkQueueChangeInAllocationRequests(
   		int typeId, int categoryId, int staffUserId, String roleType) {
   
	   AdminPartnerWorkQueueRequestChangeInAllocation rca= new AdminPartnerWorkQueueRequestChangeInAllocation();
	   try
	   {
		   @SuppressWarnings("unchecked")
		   List<Object[]> result = em.createNativeQuery("call SPAdminWQPartnerAllocationChange(:typeId,:categoryId,:cciStaffUserId,:roleType)").setParameter("typeId", typeId)
		   .setParameter("categoryId", categoryId).setParameter("cciStaffUserId", staffUserId).setParameter("roleType", roleType).getResultList();
		   if(result !=null)
		   {
			   
			   for(Object[]dr :result)
			   {
				  
				   AdminPartnerWorkQueueRequestChangeInAllocationDetails ad=new AdminPartnerWorkQueueRequestChangeInAllocationDetails();
				   ad.setCompanyName(String.valueOf(dr[0]));
				   if(dr[1]!=null)
				   ad.setGoId(Integer.valueOf(String.valueOf(dr[1])));
				   ad.setPartnerStatus(String.valueOf(dr[2]));
				  ad.setSeasonName(String.valueOf(dr[3]));
				  //department program option id
				  //maxPax
				  //maxGuranteedPax
				 /*
				  * 
				  * 
				  * */
				  rca.getChangeInAllocation().add(ad);
			   }
			   rca.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.WOEKQUEUE_SUBMITTED_ALLOCATION_CHANGE.getValue(),
		                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
		   }
		   else
		   {
			   rca.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_WOEKQUEUE_SUBMITTED_ALLOCATION_CHANGE.getValue(),
		                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
		   }
			   
	   }catch(Exception e)
	   {
		   ExceptionUtil.logException(e, logger);
		   rca.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_SUBMITTED_APPLICATIONS.getValue(),
				   messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_SUBMITTED_ALLOCATION_CHANGE)));
		   logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_SUBMITTED_ALLOCATION_CHANGE)); 
	  
	   }
   	return rca;
   }
   @Override
   public AdminPartnerWorkQueueNotesReview getWorkQueuePartnerNoteReview(
   		int typeId, int categoryId, int staffUserId, String roleType) {
	   
	   AdminPartnerWorkQueueNotesReview nr=new AdminPartnerWorkQueueNotesReview();
	   try
	   {
		   @SuppressWarnings("unchecked")
		   List<Object[]> result = em.createNativeQuery("call SPAdminWQPartnerNotesReview(:typeId,:categoryId,:cciStaffUserId,:roleType)").setParameter("typeId", typeId)
		   .setParameter("categoryId", categoryId).setParameter("cciStaffUserId", staffUserId).setParameter("roleType", roleType).getResultList();
		   if(result !=null)
		   {
			   
			   for(Object[]dr :result)
			   {
				   AdminPartnerWorkQueueNotesReviewDetail nrd=new AdminPartnerWorkQueueNotesReviewDetail();
				  nrd.setCompanyName(String.valueOf(dr[0]));
				  if(dr[1]!=null)
				  nrd.setGoId(Integer.valueOf(String.valueOf(dr[1])));
				  nrd.setCountry(String.valueOf(dr[2]));
				  nrd.setFlagUrl(String.valueOf(dr[3]));
				  nrd.setPartnerStatus(String.valueOf(dr[4]));
				 //topic name
				  //is public
				  //created on
				 nr.getNotesReview().add(nrd);
			   }
			  nr.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.WOEKQUEUE_SUBMITTED_NOTE_REVIEW.getValue(),
		                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
		   }
		   else
		   {
			   nr.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_WOEKQUEUE_SUBMITTED_NOTE_REVIEW.getValue(),
		                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
		   }
	   }catch(Exception e)
	   {
		   ExceptionUtil.logException(e, logger);
		   nr.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_SUBMITTED_APPLICATIONS.getValue(),
				   messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_SUBMITTED_DEADLINE)));
		   logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_SUBMITTED_DEADLINE)); 
	  
	   }
   	return nr;
   }
   @Override
   public WSDefaultResponse updatePartnerApplicationFollowUpDate(int goId, String newFollowUpDate) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         PartnerAgentInquiry partnerAgentInquiry = partnerAgentInquiryRepository.findPartnerByGoId(goId);
         partnerAgentInquiry.setFollowUpDate(DateUtils.getDateFromString_followUpdate(newFollowUpDate));
         partnerAgentInquiryRepository.saveAndFlush(partnerAgentInquiry);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FOLLOW_UP_DATE_UPDATED.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.CANT_UPDATE_FOLLOW_UP_DATE.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_FOLLOW_UP_DATE)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_FOLLOW_UP_DATE));
      }
      return wsDefaultResponse;
   }

   @Override
   public WSDefaultResponse changePartnerApplicationStatus(int goId, String newStatus) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findApplicationStatusByGoId(goId);
         PartnerStatus partnerStatus = partnerStatusRepository.findStatusByName(newStatus);
         partnerReviewStatus.setPartnerStatus1(partnerStatus);
         partnerReviewStatusRepository.saveAndFlush(partnerReviewStatus);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.PARTNER_APPLICATION_STATUS_UPDATED.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.CANT_UPDATE_PARTNER_APPLICATION_STATUS.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_PARTNER_APPLICATION_STATUS)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_PARTNER_APPLICATION_STATUS));
      }
      return wsDefaultResponse;
   }

   @Override
   public WSDefaultResponse addNoteToPartnerApplication(int goId, String noteValue) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         // PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findApplicationStatusByGoId(goId);
         // PartnerStatus partnerStatus= partnerStatusRepository.findStatusByName(newStatus);
         // partnerReviewStatus.setPartnerStatus1(partnerStatus);
         // partnerReviewStatusRepository.saveAndFlush(partnerReviewStatus);
         // wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO,
         // ErrorCode.PARTNER_APPLICATION_STATUS_UPDATED.getValue(),
         // messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.CANT_UPDATE_PARTNER_APPLICATION_STATUS.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_PARTNER_APPLICATION_STATUS)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_PARTNER_APPLICATION_STATUS));
      }
      return wsDefaultResponse;
   }

   @Override
   public PartnerAdminDashboardQuickLinks getQuickLinks() {
      PartnerAdminDashboardQuickLinks pwt = new PartnerAdminDashboardQuickLinks();
      try {
         PartnerAdminDashboardQuickLinksDetails details = new PartnerAdminDashboardQuickLinksDetails();
         details.setValue("Quick Link 1");
         PartnerAdminDashboardQuickLinksDetails details2 = new PartnerAdminDashboardQuickLinksDetails();
         details2.setValue("Quick Link 2");
         PartnerAdminDashboardQuickLinksDetails details3 = new PartnerAdminDashboardQuickLinksDetails();
         details3.setValue("Quick Link 3");
         PartnerAdminDashboardQuickLinksDetails details4 = new PartnerAdminDashboardQuickLinksDetails();
         details4.setValue("Quick Link 4");
         pwt.getQuickLinks().add(details);
         pwt.getQuickLinks().add(details2);
         pwt.getQuickLinks().add(details3);
         pwt.getQuickLinks().add(details4);

         pwt.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.WORK_QUEUE_QUICK_LINKS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WORK_QUEUE_QUICK_LINKS.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORK_QUICK_LINKS)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORK_QUICK_LINKS));
      }
      return pwt;
   }

   @Override
   public PartnerAdminDashboardQuickStatsTitles getQuickStatsTitle() {
      PartnerAdminDashboardQuickStatsTitles pwt = new PartnerAdminDashboardQuickStatsTitles();
      try {
         List<AdminQuickStatsType> quickStatsRepo = adminQuickStatsTypeRepository.findAll();
         if (quickStatsRepo != null)
            for (AdminQuickStatsType adminQuickStatsType : quickStatsRepo) {
               PartnerAdminDashboardQuickStatsTitlesDetails details = new PartnerAdminDashboardQuickStatsTitlesDetails();
               details.setTitle(adminQuickStatsType.getAdminQSTypeName());
               AdminQuickStatsTypeAggregate adminQuickStatsTypeAggregate = adminQuickStatsTypeAggregateRepository.findTypeAggregateValueByAdminTypeId(adminQuickStatsType
                     .getAdminQSTypeId());
               if (adminQuickStatsTypeAggregate != null) {
                  details.setNum(adminQuickStatsTypeAggregate.getAdminQSTypeAggregate());
               }
               pwt.getQuickStatsTitles().add(details);
            }
         pwt.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.WORK_QUEUE_QUICK_STATS_TITLE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WORK_QUEUE_QUICK_STATS_TITLE.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORK_QUICK_STATS_TITLE)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORK_QUICK_STATS_TITLE));
      }
      return pwt;
   }

   @Override
   public PartnerAdminDashboardQuickStatsCategory getQuickStatsCategory(int quickStatsTypeID, String roleName) {
      PartnerAdminDashboardQuickStatsCategory pwt = new PartnerAdminDashboardQuickStatsCategory();
      try {
         // TODO add rolename to the query
         List<AdminQuickStatsCategory> adminQuickStatsCategories = adminQuickStatsCategoriesRepository.findAllCategoriesByTypeId(quickStatsTypeID);
         if (adminQuickStatsCategories != null) {
            for (AdminQuickStatsCategory adminQuickStatsCategory : adminQuickStatsCategories) {
               PartnerAdminDashboardQuickStatsCategoryDetail partnerAdminDashboardQuickStatsCategory = new PartnerAdminDashboardQuickStatsCategoryDetail();
               partnerAdminDashboardQuickStatsCategory.setTitle(adminQuickStatsCategory.getAdminQSCategoryName());
               AdminQuickStatsCategoryAggregate aggregates = adminQuickStatsCategoriesAggregateRepository.findAggregateValueForCategory(quickStatsTypeID,
                     adminQuickStatsCategory.getAdminQSCategoryId());
               if (aggregates != null) {
                  // TODO
                  partnerAdminDashboardQuickStatsCategory.setNum(50);
               }
               pwt.getQuickStatsDetail().add(partnerAdminDashboardQuickStatsCategory);
            }
         }
         pwt.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.WORK_QUEUE_QUICK_STATS_CATEGORY.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WORK_QUEUE_QUICK_STATS_CATEGORY.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORK_QUEUE_QUICK_STATS_CATEGORY)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORK_QUEUE_QUICK_STATS_CATEGORY));
      }
      return pwt;
   }

   @Override
   public PartnerAdminDashboardBenchmarks getBenchmark() {
      PartnerAdminDashboardBenchmarks benchMark = new PartnerAdminDashboardBenchmarks();
      try {
         PartnerAdminDashboardBenchmarksDetails partnerAdminDashboardBenchmarks = new PartnerAdminDashboardBenchmarksDetails();
         partnerAdminDashboardBenchmarks.setTitle("title1");
         partnerAdminDashboardBenchmarks.setTotal(80);
         benchMark.getBenchmarks().add(partnerAdminDashboardBenchmarks);
         PartnerAdminDashboardBenchmarksDetails partnerAdminDashboardBenchmarks2 = new PartnerAdminDashboardBenchmarksDetails();
         partnerAdminDashboardBenchmarks2.setTitle("title2");
         partnerAdminDashboardBenchmarks2.setTotal(90);
         benchMark.getBenchmarks().add(partnerAdminDashboardBenchmarks2);

         benchMark.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.WORK_QUEUE_BENCHMARKS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         benchMark.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WORK_QUEUE_BENCHMARKS.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORK_QUEUE_BENCHMARKS)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORK_QUEUE_BENCHMARKS));
      }
      return benchMark;
   }

   @Override
   public WSDefaultResponse getNotesOfPartnerApplication(int parseInt) {
      // TODO Auto-generated method stub
      return null;
   }

   /****************************** Partner Inquiry Overview *****************************/

   @Transactional
   @Override
   public PartnerAdminOverviewDocuments addNewPartnerInquiryDocument(PartnerAdminOverviewDocumentsDetails document) {
      PartnerAdminOverviewDocuments documents = new PartnerAdminOverviewDocuments();
      try {

         /**
          * Adding the Document
          */
         PartnerProgram partnerProgram = partnerProgramRepository.findOne(4); // TODO Ask Phani Why This Field is here
         Partner partner = partnerRepository.findOne(document.getGoId());

         DocumentInformation documentInformation = new DocumentInformation();
         documentInformation.setFileName(document.getFileName());
         documentInformation.setDocumentName(document.getDocName());
         documentInformation.setUrl(document.getDocUrl());
         documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(document.getDocType()));
         documentInformation.setCreatedBy(1);
         documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         documentInformation.setModifiedBy(1);
         documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
     
         documentInformation = documentInformationRepository.saveAndFlush(documentInformation);
         
         documentInformation.setActive(CCIConstants.ACTIVE);
         DocumentInformation d = documentInformationRepository.saveAndFlush(documentInformation);
         PartnerDocument p = new PartnerDocument();
         p.setDescription(document.getDescription());
         p.setDocumentInformation(d);
         p.setPartner(partner);
         p.setPartnerProgram(partnerProgram);

         partnerDocumentsRepository.saveAndFlush(p);
         /**
          * Fetching All Documents
          */
         List<PartnerDocument> partnerDocuments = partnerDocumentsRepository.findAllPartnerDocumentByPartnerId(document.getGoId());
         if (partnerDocuments != null) {
            for (PartnerDocument pd : partnerDocuments) {
               PartnerAdminOverviewDocumentsDetails doc = new PartnerAdminOverviewDocumentsDetails();
               doc.setPartnerDocumentId(pd.getPartnerDocumentId());
               doc.setActive(pd.getDocumentInformation().getActive() == CCIConstants.ACTIVE);
               doc.setDescription(pd.getDescription());
               doc.setDocName(pd.getDocumentInformation().getDocumentName());
               doc.setDocType(pd.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
               doc.setDocUrl(pd.getDocumentInformation().getUrl());
               doc.setFileName(pd.getDocumentInformation().getFileName());
               doc.setFileType("");
               doc.setUploadDate(DateUtils.getDateAndTime(pd.getDocumentInformation().getCreatedOn()));
               Integer createdBy = pd.getDocumentInformation().getCreatedBy();
//               CCIStaffUsersCCIStaffRole staffUserAndRole = cciStaffRolesRepository.findOne(createdBy);
//               if (staffUserAndRole != null) {
//                  com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewDocuments.DocumentUploadUser uploadedBy = new com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewDocuments.DocumentUploadUser();
//                  uploadedBy.setPhotoUrl(staffUserAndRole.getCcistaffUser().getPhoto());
//                  uploadedBy.setRole(staffUserAndRole.getCcistaffRole().getCciStaffRoleName());
//                  uploadedBy.setUserName(staffUserAndRole.getCcistaffUser().getFirstName() + " " + staffUserAndRole.getCcistaffUser().getLastName());
//                  doc.setUploadedBy(uploadedBy);
//               }
               documents.getDocuments().add(doc);
            }
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return documents;
   }

   @Transactional
   @Override
   public PartnerAdminOverviewDocuments removeNewPartnerInquiryDocument(PartnerAdminOverviewDeletedDocuments deletedItems) {
      PartnerAdminOverviewDocuments documents = new PartnerAdminOverviewDocuments();
      try {

         /**
          * Remove the Document
          */
         for (Integer item : deletedItems.getDocumentIds()) {
            partnerDocumentsRepository.delete(item);

         }
         partnerDocumentsRepository.flush();
         /**
          * Fetching All Documents
          */
         List<PartnerDocument> partnerDocuments = partnerDocumentsRepository.findAllPartnerDocumentByPartnerId(deletedItems.getGoId());
         if (partnerDocuments != null) {
            for (PartnerDocument pd : partnerDocuments) {
               PartnerAdminOverviewDocumentsDetails doc = new PartnerAdminOverviewDocumentsDetails();
               doc.setPartnerDocumentId(pd.getPartnerDocumentId());
               doc.setActive(pd.getDocumentInformation().getActive() == 1);
               doc.setDescription("");
               doc.setDocName(pd.getDocumentInformation().getDocumentName());
               doc.setDocType(pd.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
               doc.setDocUrl(pd.getDocumentInformation().getUrl());
               doc.setFileName(pd.getDocumentInformation().getFileName());
               doc.setFileType("");
               doc.setUploadDate(DateUtils.getDateAndTime(pd.getDocumentInformation().getCreatedOn()));
               /*Integer createdBy = pd.getDocumentInformation().getCreatedBy();
               CCIStaffUsersCCIStaffRole staffUserAndRole = cciStaffRolesRepository.findOne(createdBy);
               if (staffUserAndRole != null) {
                  com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewDocuments.DocumentUploadUser uploadedBy = new com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewDocuments.DocumentUploadUser();
                  uploadedBy.setPhotoUrl(staffUserAndRole.getCcistaffUser().getPhoto());
                  uploadedBy.setRole(staffUserAndRole.getCcistaffRole().getCciStaffRoleName());
                  uploadedBy.setUserName(staffUserAndRole.getCcistaffUser().getFirstName() + " " + staffUserAndRole.getCcistaffUser().getLastName());
                  doc.setUploadedBy(uploadedBy);
               }*/
               documents.getDocuments().add(doc);
            }
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return documents;
   }

   @Override
 
   public PartnerAdminOverviewOffices addNewPartnerInquiryOffice(PartnerAdminOverviewOfficesDetails officesDetails) {
      PartnerAdminOverviewOffices pOffices ;
      try {
         PartnerOffice po = new PartnerOffice();
         po.setAdressOne(officesDetails.getAddress1());
         po.setAdressTwo(officesDetails.getAddress2());
         po.setCity(officesDetails.getCity());
         po.setCreatedBy(1);
         po.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         po.setFaxNumber(officesDetails.getFax());
         LookupCountry country = lookupCountryRepository.findByCountryName(officesDetails.getCountry());
         po.setLookupCountry(country);
         Partner partner = partnerRepository.findOne(officesDetails.getGoId());
         po.setPartner(partner);
         PartnerOfficeType officeType = partnerOfficeTypeRepository.findByPartnerOfficeType(officesDetails.getOfficeType());
         po.setPartnerOfficeType(officeType);
         po.setModifiedBy(1);
         po.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         // partnerOffice.setOfficeNotes(officesDetails.geto);
         po.setPhoneNumber(officesDetails.getPhone());
         po.setPostalCode(officesDetails.getZipCode());
         // partnerOffice.setState(officesDetails.get);
         po.setWebsite(officesDetails.getWebsite());
         partnerOfficeRepository.saveAndFlush(po);

     /*    List<PartnerOffice> offices = partnerOfficeRepository.findPartnerOfficeByPartnerId(officesDetails.getGoId());
         if (offices != null) {
            for (PartnerOffice partnerOffice : offices) {
               PartnerAdminOverviewOfficesDetails office = new PartnerAdminOverviewOfficesDetails();
               office.setPartnerOfficeId(partnerOffice.getPartnerOfficeId());
               office.setAddress1(partnerOffice.getAdressOne());
               office.setAddress2(partnerOffice.getAdressTwo());
               office.setCity(partnerOffice.getCity());
               office.setCountry(partnerOffice.getLookupCountry().getCountryName());
               office.setEmail(partnerOffice.getPartner().getEmail());
               office.setFax(partnerOffice.getFaxNumber());
               office.setPhone(partnerOffice.getPhoneNumber());
               office.setWebsite(partnerOffice.getWebsite());
               office.setZipCode(partnerOffice.getPostalCode());
               office.setOfficeType(partnerOffice.getPartnerOfficeType().getPartnerOfficeType());
               pOffices.getOffices().add(office);
            }
         }*/
         
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      pOffices=getListofOffices(officesDetails.getGoId());
      return pOffices;
   }
   
   @Transactional
 private PartnerAdminOverviewOffices getListofOffices(int goId)
   {
	   PartnerAdminOverviewOffices pOffices = new PartnerAdminOverviewOffices();
 	  List<PartnerOffice> offices = partnerOfficeRepository.findPartnerOfficeByPartnerId(goId);
       if (offices != null) {
          for (PartnerOffice partnerOffice : offices) {
             PartnerAdminOverviewOfficesDetails office = new PartnerAdminOverviewOfficesDetails();
             office.setPartnerOfficeId(partnerOffice.getPartnerOfficeId());
             office.setAddress1(partnerOffice.getAdressOne());
             office.setAddress2(partnerOffice.getAdressTwo());
             office.setCity(partnerOffice.getCity());
            String countryName = partnerOffice.getLookupCountry().getCountryName();
            if(countryName!=null)
             office.setCountry(countryName);
             office.setEmail(partnerOffice.getPartner().getEmail());
             office.setFax(partnerOffice.getFaxNumber());
             office.setPhone(partnerOffice.getPhoneNumber());
             office.setWebsite(partnerOffice.getWebsite());
             office.setZipCode(partnerOffice.getPostalCode());
             office.setOfficeType(partnerOffice.getPartnerOfficeType().getPartnerOfficeType());
             pOffices.getOffices().add(office);
          }
          
   }
       return pOffices;
   }
   @Transactional
   @Override
   public PartnerAdminOverviewOffices removeNewPartnerInquiryOffice(PartnerAdminOverviewDeletedOffices deletedItems) {
      PartnerAdminOverviewOffices pOffices = new PartnerAdminOverviewOffices();
      try {
         for (Integer item : deletedItems.getOffices()) {
            partnerOfficeRepository.delete(item);
         }
         partnerOfficeRepository.flush();

         List<PartnerOffice> offices = partnerOfficeRepository.findPartnerOfficeByPartnerId(deletedItems.getGoId());
         if (offices != null) {
            for (PartnerOffice partnerOffice : offices) {
               PartnerAdminOverviewOfficesDetails office = new PartnerAdminOverviewOfficesDetails();
               office.setPartnerOfficeId(partnerOffice.getPartnerOfficeId());
               office.setAddress1(partnerOffice.getAdressOne());
               office.setAddress2(partnerOffice.getAdressTwo());
               office.setCity(partnerOffice.getCity());
               office.setCountry(partnerOffice.getLookupCountry().getCountryName());
               office.setEmail(partnerOffice.getPartner().getEmail());
               office.setFax(partnerOffice.getFaxNumber());
               office.setPhone(partnerOffice.getPhoneNumber());
               office.setWebsite(partnerOffice.getWebsite());
               office.setZipCode(partnerOffice.getPostalCode());
               office.setOfficeType(partnerOffice.getPartnerOfficeType().getPartnerOfficeType());
               pOffices.getOffices().add(office);
            }
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return pOffices;
   }

   @Override
   public PartnerAdminOverviewContacts addNewPartnerInquiryContact(PartnerAdminOverviewContactsDetails contactsDetails) {
      PartnerAdminOverviewContacts pContacts = new PartnerAdminOverviewContacts();
      try {
         PartnerContact pc = new PartnerContact();
         pc.setActive((byte) (contactsDetails.isActive() ? 1 : 0));
         pc.setCreatedBy(1);
         pc.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         pc.setEmail(contactsDetails.getEmail());
         pc.setEmergencyPhone(contactsDetails.getEmergencyPhone());
         pc.setFax(contactsDetails.getFax());
         pc.setFirstName(contactsDetails.getFirstName());
         pc.setIsPrimary((byte) (contactsDetails.isPrimaryContact() ? 1 : 0));
         pc.setLastName(contactsDetails.getLastName());
         pc.setModifiedBy(1);
         pc.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         Partner partner = partnerRepository.findOne(contactsDetails.getGoId());
         pc.setPartner(partner);
         // PartnerOffice partnerOffice = partnerOfficeRepository.find;
         // partnerContact.setPartnerOffice(partnerOffice);
         pc.setPhone(contactsDetails.getPhone());
         pc.setReceiveNotificationEmails(CCIConstants.INACTIVE);
         Salutation salutation = salutationRepository.findBySalutationName(contactsDetails.getSalutation());
         pc.setSalutation(salutation);
         pc.setSkypeId(contactsDetails.getSkypeId());
         pc.setTitle(contactsDetails.getTitile());
         // partnerContact.setWebsite(contactsDetails.get);
         partnerContactRepository.saveAndFlush(pc);

         List<PartnerContact> contacts = partnerContactRepository.findPartnerContactsByPartnerId(contactsDetails.getGoId());
         if (contacts != null) {
            for (PartnerContact partnerContact : contacts) {
               PartnerAdminOverviewContactsDetails contact = new PartnerAdminOverviewContactsDetails();
               contact.setPartnerContactId(partnerContact.getPartnerContactId());
               contact.setActive(partnerContact.getActive() == 1);
               contact.setEmail(partnerContact.getEmail());
               contact.setEmergencyPhone(partnerContact.getEmergencyPhone());
               contact.setFax(partnerContact.getFax());
               contact.setFirstName(partnerContact.getFirstName());
               contact.setLastName(partnerContact.getLastName());
               contact.setPhone(partnerContact.getPhone());
               // contact.setPrograms(partnerContact.get);
               contact.setSalutation(partnerContact.getSalutation().getSalutationName());
               contact.setSkypeId(partnerContact.getSkypeId());
               contact.setTitile(partnerContact.getTitle());
               // contact.setUsername(partnerContact.get);
               contact.setPrimaryContact(partnerContact.getIsPrimary() == 1);
               pContacts.getContacts().add(contact);
            }
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return pContacts;
   }

   @Override
   public PartnerAdminOverviewContacts removeNewPartnerInquiryContact(PartnerAdminOverviewDeletedContacts deletedItems) {
      PartnerAdminOverviewContacts pContacts = new PartnerAdminOverviewContacts();
      try {

         for (Integer item : deletedItems.getContacts()) {
            partnerContactRepository.delete(item);
         }
         partnerContactRepository.flush();

         List<PartnerContact> contacts = partnerContactRepository.findPartnerContactsByPartnerId(deletedItems.getGoId());
         if (contacts != null) {
            for (PartnerContact partnerContact : contacts) {
               PartnerAdminOverviewContactsDetails contact = new PartnerAdminOverviewContactsDetails();
               contact.setPartnerContactId(partnerContact.getPartnerContactId());
               contact.setActive(partnerContact.getActive() == 1);
               contact.setEmail(partnerContact.getEmail());
               contact.setEmergencyPhone(partnerContact.getEmergencyPhone());
               contact.setFax(partnerContact.getFax());
               contact.setFirstName(partnerContact.getFirstName());
               contact.setLastName(partnerContact.getLastName());
               contact.setPhone(partnerContact.getPhone());
               // contact.setPrograms(partnerContact.get);
               contact.setSalutation(partnerContact.getSalutation().getSalutationName());
               contact.setSkypeId(partnerContact.getSkypeId());
               contact.setTitile(partnerContact.getTitle());
               // contact.setUsername(partnerContact.get);
               contact.setPrimaryContact(partnerContact.getIsPrimary() == 1);
               pContacts.getContacts().add(contact);
            }
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return pContacts;
   }

   @Override
   public PartnerAdminOverviewNotes addNewPartnerInquiryNote(PartnerAdminOverviewNotesDetails notesDetails) {
      PartnerAdminOverviewNotes pn = new PartnerAdminOverviewNotes();
      try {
         PartnerNote note = new PartnerNote();
         note.setCreatedBy(1);
         note.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         note.setModifiedBy(1);
         note.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         Partner partner = partnerRepository.findOne(notesDetails.getGoId());
         note.setPartner(partner);
         note.setPartnerNote(notesDetails.getNoteValue());
         PartnerNoteTopic partnerNoteTopic = partnerNoteTopicRepository.findByPartnerNoteTopicName(notesDetails.getTopic());
         note.setPartnerNoteTopic(partnerNoteTopic);
         partnerNoteRepository.saveAndFlush(note);

         List<PartnerNote> partnerNotes = partnerNoteRepository.findAllPartnerNoteByPartnerId(notesDetails.getGoId());
         if (partnerNotes != null) {
            for (PartnerNote partnerNote : partnerNotes) {
               PartnerAdminOverviewNotesDetails nd = new PartnerAdminOverviewNotesDetails();
             /*  CCIStaffUsersCCIStaffRole staffUserAndRole = cciStaffRolesRepository.findOne(partnerNote.getCreatedBy());
               if (staffUserAndRole != null) {
                  com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewNotes.NoteUserCreator noteCreator = new com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewNotes.NoteUserCreator();
                  noteCreator.setPhotoUrl(staffUserAndRole.getCcistaffUser().getPhoto());
                  noteCreator.setRole(staffUserAndRole.getCcistaffRole().getCciStaffRoleName());
                  noteCreator.setUserName(staffUserAndRole.getCcistaffUser().getFirstName() + " " + staffUserAndRole.getCcistaffUser().getLastName());
                  nd.setCreatedBy(noteCreator);
               }*/
               nd.setPartnerNoteId(partnerNote.getPartnerNotesId());
               nd.setCreatedOn(DateUtils.getDateAndTime(partnerNote.getCreatedOn()));
               nd.setNoteValue(partnerNote.getPartnerNote());
               pn.getNotes().add(nd);
            }
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return pn;
   }

   @Override
   public PartnerAdminOverviewReferenceCheck addNewPartnerInquiryReferenceCheck(PartnerAdminOverviewReferenceCheckDetails referenceChecksDetails) {
      PartnerAdminOverviewReferenceCheck prc = new PartnerAdminOverviewReferenceCheck();
      try {
         PartnerReferenceCheck refcheck = new PartnerReferenceCheck();
         refcheck.setBusinessLicenseExpiryDate(DateUtils.getDateFromString(referenceChecksDetails.getLatestCopyOfBusinessExpires()));
         Partner partner = partnerRepository.findOne(referenceChecksDetails.getGoId());
         refcheck.setPartner(partner);
         refcheck.setReferenceApprovedOn(DateUtils.getDateFromString(referenceChecksDetails.getApprovedOn()));
         refcheck.setReferenceApprovedBy(referenceChecksDetails.getApprovedBy());
         refcheck.setReferenceCheckNotes(referenceChecksDetails.getNote());
         refcheck.setReferenceCompletedBy(referenceChecksDetails.getCompletedBy());
         refcheck.setReferenceCompletedOn(DateUtils.getDateFromString(referenceChecksDetails.getCompletedOn()));
        
         partnerReferenceCheckRepository.saveAndFlush(refcheck);

         List<PartnerReferenceCheck> partnerReferenceChecks = partnerReferenceCheckRepository.findAllPartnerReferenceCheckByPartnerId(referenceChecksDetails.getGoId());
         if (partnerReferenceChecks != null) {
            for (PartnerReferenceCheck partnerReferenceCheck : partnerReferenceChecks) {
               PartnerAdminOverviewReferenceCheckDetails refCheck = new PartnerAdminOverviewReferenceCheckDetails();
               refCheck.setPartnerCheckReferenceId(partnerReferenceCheck.getPartnerReferenceCheckId());
               refCheck.setApprovedBy(partnerReferenceCheck.getReferenceApprovedBy());
               refCheck.setApprovedOn(DateUtils.getDateAndTime(partnerReferenceCheck.getReferenceApprovedOn()));
               refCheck.setCompletedBy(partnerReferenceCheck.getReferenceCompletedBy());
               refCheck.setCompletedOn(DateUtils.getDateAndTime(partnerReferenceCheck.getReferenceCompletedOn()));
               refCheck.setLatestCopyOfBusinessExpires(DateUtils.getDateAndTime(partnerReferenceCheck.getBusinessLicenseExpiryDate()));
               refCheck.setNote(partnerReferenceCheck.getReferenceCheckNotes());
               prc.getReferenceCheck().add(refCheck);
            }
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return prc;
   }

   @Override
   public PartnerAdminOverviewReferenceCheck removeNewPartnerInquiryReferenceCheck(PartnerAdminOverviewDeletedReferenceCheck deletedItems) {
      PartnerAdminOverviewReferenceCheck prc = new PartnerAdminOverviewReferenceCheck();
      try {
         for (Integer item : deletedItems.getReferenceCheck()) {
            partnerReferenceCheckRepository.delete(item);
         }
         partnerReferenceCheckRepository.flush();

         List<PartnerReferenceCheck> partnerReferenceChecks = partnerReferenceCheckRepository.findAllPartnerReferenceCheckByPartnerId(deletedItems.getGoId());
         if (partnerReferenceChecks != null) {
            for (PartnerReferenceCheck partnerReferenceCheck : partnerReferenceChecks) {
               PartnerAdminOverviewReferenceCheckDetails refCheck = new PartnerAdminOverviewReferenceCheckDetails();
               refCheck.setPartnerCheckReferenceId(partnerReferenceCheck.getPartnerReferenceCheckId());
               refCheck.setApprovedBy(partnerReferenceCheck.getReferenceApprovedBy());
               refCheck.setApprovedOn(DateUtils.getDateAndTime(partnerReferenceCheck.getReferenceApprovedOn()));
               refCheck.setCompletedBy(partnerReferenceCheck.getReferenceCompletedBy());
               refCheck.setCompletedOn(DateUtils.getDateAndTime(partnerReferenceCheck.getReferenceCompletedOn()));
               refCheck.setLatestCopyOfBusinessExpires(DateUtils.getDateAndTime(partnerReferenceCheck.getBusinessLicenseExpiryDate()));
               refCheck.setNote(partnerReferenceCheck.getReferenceCheckNotes());
               prc.getReferenceCheck().add(refCheck);
            }
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return prc;
   }

   @Override
   public WSDefaultResponse sendLogin() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public CCIUsers getAllCCIUsers() {
      return userManagementService.findAllUsers();
   }






}