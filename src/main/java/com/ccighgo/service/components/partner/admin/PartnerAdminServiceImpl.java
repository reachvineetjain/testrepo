/**
 * 
 */
package com.ccighgo.service.components.partner.admin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.AdminQuickStatsCategory;
import com.ccighgo.db.entities.AdminQuickStatsCategoryAggregate;
import com.ccighgo.db.entities.AdminQuickStatsType;
import com.ccighgo.db.entities.AdminQuickStatsTypeAggregate;
import com.ccighgo.db.entities.AdminWorkQueueCategory;
import com.ccighgo.db.entities.AdminWorkQueueCategoryAggregate;
import com.ccighgo.db.entities.AdminWorkQueueType;
import com.ccighgo.db.entities.PartnerAgentInquiry;
import com.ccighgo.db.entities.PartnerDocument;
import com.ccighgo.db.entities.PartnerMessage;
import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerOffice;
import com.ccighgo.db.entities.PartnerProgram;
import com.ccighgo.db.entities.PartnerReferenceCheck;
import com.ccighgo.db.entities.PartnerReviewStatus;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.AdminQuickStatsCategoriesAggregateRepository;
import com.ccighgo.jpa.repositories.AdminQuickStatsCategoriesRepository;
import com.ccighgo.jpa.repositories.AdminQuickStatsTypeAggregateRepository;
import com.ccighgo.jpa.repositories.AdminQuickStatsTypeRepository;
import com.ccighgo.jpa.repositories.AdminWorkQueueCategoryAggregateRepository;
import com.ccighgo.jpa.repositories.AdminWorkQueueCategoryRepository;
import com.ccighgo.jpa.repositories.AdminWorkQueueTypeRepository;
import com.ccighgo.jpa.repositories.LookupDepartmentProgramRepository;
import com.ccighgo.jpa.repositories.PartnerAgentInquiryRepository;
import com.ccighgo.jpa.repositories.PartnerDocumentsRepository;
import com.ccighgo.jpa.repositories.PartnerMessagesRepository;
import com.ccighgo.jpa.repositories.PartnerNoteRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeRepository;
import com.ccighgo.jpa.repositories.PartnerProgramRepository;
import com.ccighgo.jpa.repositories.PartnerReferenceCheckRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerReviewStatusRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminMessageConstants;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata.PartnerRecruitmentAdminLead;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningAdditionalInfo;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdmin;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningDocuments;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningNotes;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningOffices;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningReferenceCheck;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.benchmarks.PartnerAdminDashboardBenchmarks;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.benchmarks.PartnerAdminDashboardBenchmarksDetails;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quicklinks.PartnerAdminDashboardQuickLinks;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quicklinks.PartnerAdminDashboardQuickLinksDetails;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quickstatscategory.PartnerAdminDashboardQuickStatsCategory;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quickstatscategory.PartnerAdminDashboardQuickStatsCategoryDetail;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quickstatstitles.PartnerAdminDashboardQuickStatsTitles;
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.quickstatstitles.PartnerAdminDashboardQuickStatsTitlesDetails;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.AdminPartnerWorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.AdminPartnerWorkQueueCategoryDetail;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplications;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplicationsDetail;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.AdminPartnerWorkQueueType;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.AdminPartnerWorkQueueTypeDetail;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
import com.sun.org.omg.CORBA.ParameterMode;

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

   @PersistenceContext
   EntityManager em;

   @Override
   public PartnerRecruitmentAdmin getPartnerInquiryOverviewData(int partnerGoId) {
      PartnerRecruitmentAdmin pwt = new PartnerRecruitmentAdmin();
      try {
         PartnerAgentInquiry partnerAgentInquiry = partnerAgentInquiryRepository.findOne(partnerGoId);
         if (partnerAgentInquiry == null) {
            pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_AGENT_DETAIL.getValue(),
                  messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_AGENT_DETAIL)));
            logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_AGENT_DETAIL));
            logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_AGENT_DETAIL));
            return pwt;
         }
         try {
            // PartnerRecruitmentAdminScreeningDetail partnerRecruitmentAdminScreeningDetail = new
            // PartnerRecruitmentAdminScreeningDetail();
            // if (partnerAgentInquiry.getCompanyName() != null)
            // partnerRecruitmentAdminScreeningDetail.setCompanyName(partnerAgentInquiry.getCompanyName());
            // if (partnerAgentInquiry.getLookupCountry() != null)
            // partnerRecruitmentAdminScreeningDetail.setCountry(partnerAgentInquiry.getLookupCountry().getCountryName());
            // if (partnerAgentInquiry.getEmail() != null)
            // partnerRecruitmentAdminScreeningDetail.setEmail(partnerAgentInquiry.getEmail());
            // if (partnerAgentInquiry.getFirstName() != null)
            // partnerRecruitmentAdminScreeningDetail.setFirstName(partnerAgentInquiry.getFirstName());
            // if (partnerAgentInquiry.getLastName() != null)
            // partnerRecruitmentAdminScreeningDetail.setLastName(partnerAgentInquiry.getLastName());
            // if (partnerAgentInquiry.getPhone() != null)
            // partnerRecruitmentAdminScreeningDetail.setPhone(partnerAgentInquiry.getPhone());
            // if (partnerAgentInquiry.getWebsite() != null)
            // partnerRecruitmentAdminScreeningDetail.setWebsite(partnerAgentInquiry.getWebsite());
            // if (partnerAgentInquiry.getAdressLineOne() != null)
            // partnerRecruitmentAdminScreeningDetail.setAddress1(partnerAgentInquiry.getAdressLineOne());
            // if (partnerAgentInquiry.getAdressLineTwo() != null)
            // partnerRecruitmentAdminScreeningDetail.setAddress2(partnerAgentInquiry.getAdressLineTwo());
            // if (partnerAgentInquiry.getCity() != null)
            // partnerRecruitmentAdminScreeningDetail.setCity(partnerAgentInquiry.getCity());
            // // Rating value is static ?????
            // partnerRecruitmentAdminScreeningDetail.setRating(0);
            // // if(partnerAgentInquiry.getSalutation()!=null)
            // // partnerRecruitmentAdminScreeningDetail.setSalutation(partnerAgentInquiry.getSalutation());
            // if (partnerAgentInquiry.getState() != null)
            // partnerRecruitmentAdminScreeningDetail.setStateOrProvince(partnerAgentInquiry.getState());
            // pwt.setDetail(partnerRecruitmentAdminScreeningDetail);
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }

         // pwt.setFollowUpDate(DateUtils.getDateAndTime(partnerAgentInquiry.getFollowUpDate()));
         PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findStatusByPartnerId(partnerGoId);

         if (partnerReviewStatus != null) {
            // pwt.setAgentStatus(partnerReviewStatus.getPartnerStatus1().getPartnerStatusName());
            pwt.setLeadStatus(partnerReviewStatus.getPartnerStatus2().getPartnerStatusName());
         }
         try {
            List<PartnerProgram> partnerPrograms = partnerProgramRepository.findAllPartnerProgramsByPartnerId(partnerGoId);
            if (partnerPrograms != null) {
               for (PartnerProgram partnerProgram : partnerPrograms) {
                  // PartnerRecruitmentAdminScreeningPrograms partnerRecruitmentAdminScreeningPrograms = new
                  // PartnerRecruitmentAdminScreeningPrograms();
                  // partnerRecruitmentAdminScreeningPrograms.setAction(partnerProgram.getIsEligible() == 1 ? "Eligible"
                  // : "Ineligible");
                  // partnerRecruitmentAdminScreeningPrograms.setApplied(partnerProgram.getHasApplied() == 1);
                  // CCIStaffUser user = partnerProgram.getCcistaffUser();
                  // PartnerRecruitmentAdminScreeningMarkedByUser markedByUser = new
                  // PartnerRecruitmentAdminScreeningMarkedByUser();
                  // markedByUser.setImageUrl(user.getPhoto());
                  // markedByUser.setUserName(user.getFirstName() + " " + user.getLastName());
                  // partnerRecruitmentAdminScreeningPrograms.setMarkedBy(markedByUser);
                  // partnerRecruitmentAdminScreeningPrograms.setNotify(partnerProgram.getIsPDNotified() == 1);
                  // //
                  // partnerRecruitmentAdminScreeningPrograms.setProgramName(partnerProgram.getLookupDepartmentProgram1().getProgramName());
                  // pwt.getPrograms().add(partnerRecruitmentAdminScreeningPrograms);
               }
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            // ??????????????????? program offerings
            // PartnerRecruitmentAdminScreeningProgramOfferings programOffering = new
            // PartnerRecruitmentAdminScreeningProgramOfferings();
            // programOffering.setHighSchoolAbroad(partnerAgentInquiry.);
            // programOffering.setOther(value);
            // programOffering.setTeachAbroad(value);
            // programOffering.setVolunteerAbroad(value);
            // pwt.setParticipantProgramOfferings(programOffering);
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         // pwt.setDescriptionsOfPrograms(partnerAgentInquiry.getCurrentlyOfferingPrograms());
         try {
            PartnerRecruitmentAdminScreeningAdditionalInfo additionalInformation = new PartnerRecruitmentAdminScreeningAdditionalInfo();
            additionalInformation.setHearAboutUsFrom(partnerAgentInquiry.getHowDidYouHearAboutCCI());
            additionalInformation.setLikeToKnowMoreAboutAmbassadorScholarship(partnerAgentInquiry.getAmbassadorScholershipParticipants() == 1);
            additionalInformation.setSendPartnersToUSA(partnerAgentInquiry.getCurrentlySendingParticipantToUS() == 1);
            additionalInformation.setYearsInBusiness(Integer.parseInt(partnerAgentInquiry.getBusinessYears()));
            // pwt.setAdditionalInformation(additionalInformation);
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            List<PartnerMessage> messages = partnerMessagesRepository.findAllParnerMessagesByPartnerId(partnerGoId);
            if (messages != null) {
               for (PartnerMessage partnerMessage : messages) {
                  // PartnerRecruitmentAdminScreeningMessagesToAgent m = new
                  // PartnerRecruitmentAdminScreeningMessagesToAgent();
                  // m.setDate(DateUtils.getDateAndTime(partnerMessage.getCreatedOn()));
                  // m.setImageUrl(partnerMessage.getCcistaffUser().getPhoto());
                  // m.setMessage(partnerMessage.getPartnerInquiryMessage());
                  // // ?????? not used column
                  // m.setReceived(false);
                  // m.setSenderName(partnerMessage.getCcistaffUser().getFirstName() + " " +
                  // partnerMessage.getCcistaffUser().getLastName());
                  // pwt.getMessagesToAgent().add(m);
               }
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }

         try {
            List<PartnerOffice> offices = partnerOfficeRepository.findPartnerOfficeByPartnerId(partnerGoId);
            if (offices != null) {
               for (PartnerOffice partnerOffice : offices) {
                  PartnerRecruitmentAdminScreeningOffices office = new PartnerRecruitmentAdminScreeningOffices();
                  office.setAddress1(partnerOffice.getAdressOne());
                  office.setAddress2(partnerOffice.getAdressTwo());
                  office.setCity(partnerOffice.getCity());
                  office.setCountry(partnerOffice.getLookupCountry().getCountryName());
                  office.setEmail(partnerOffice.getPartner().getEmail());
                  office.setFax(partnerOffice.getFaxNumber());
                  office.setPhone(partnerOffice.getPhoneNumber());
                  office.setWebsite(partnerOffice.getWebsite());
                  office.setZipCode(partnerOffice.getPostalCode());
                  pwt.getOffices().add(office);
               }
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            List<PartnerReferenceCheck> partnerReferenceChecks = partnerReferenceCheckRepository.findAllPartnerReferenceCheckByPartnerId(partnerGoId);
            if (partnerReferenceChecks != null) {
               for (PartnerReferenceCheck partnerReferenceCheck : partnerReferenceChecks) {
                  PartnerRecruitmentAdminScreeningReferenceCheck refCheck = new PartnerRecruitmentAdminScreeningReferenceCheck();
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
            List<PartnerDocument> partnerDocuments = partnerDocumentsRepository.findAllPartnerDocumentByPartnerId(partnerGoId);
            if (partnerDocuments != null) {
               for (PartnerDocument p : partnerDocuments) {
                  PartnerRecruitmentAdminScreeningDocuments doc = new PartnerRecruitmentAdminScreeningDocuments();
                  doc.setActive(p.getDocumentInformation().getActive() == 1);
                  doc.setDescription("");
                  doc.setDocName(p.getDocumentInformation().getDocumentName());
                  doc.setDocType(p.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
                  doc.setDocUrl(p.getDocumentInformation().getUrl());
                  doc.setFileName(p.getDocumentInformation().getFileName());
                  doc.setFileType("");
                  doc.setUploadDate(DateUtils.getDateAndTime(p.getDocumentInformation().getCreatedOn()));
                  // ??????????? upload by should be Object
                  // doc.setUploadedBy(p.getPartner().);
                  pwt.getDocuments().add(doc);
               }
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            List<PartnerNote> partnerNotes = partnerNoteRepository.findAllPartnerNoteByPartnerId(partnerGoId);
            if (partnerNotes != null) {
               for (PartnerNote partnerNote : partnerNotes) {
                  PartnerRecruitmentAdminScreeningNotes note = new PartnerRecruitmentAdminScreeningNotes();
                  // ???????????? not used
                  note.setActive(false);
                  note.setCreatedBy(partnerNote.getPartner().getCompanyName());
                  note.setCreatedOn(DateUtils.getDateAndTime(partnerNote.getCreatedOn()));
                  note.setNoteValue(partnerNote.getPartnerNote());
                  pwt.getNotes().add(note);
               }
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_AGENT_DETAIL.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_AGENT_DETAIL)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_AGENT_DETAIL));
      }
      return pwt;
   }

   @Override
   public PartnerRecruitmentAdminLead getPartnerInquiryLeadData(int parseInt) {
      // TODO Auto-generated method stub
      return null;
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
         List result = em
               .createNativeQuery("call SPAdminWQPartnerSearch(:typeId,:categoryId,:cciStaffUserId,:roleType)")
               .setParameter("typeId", typeId)
               .setParameter("categoryId", categoryId)
               .setParameter("cciStaffUserId", staffUserId)
               .setParameter("roleType", roleType)
               .getResultList();
//         List<PartnerAgentInquiry> result = null; // partnerAgentInquiryRepository.findPartnerByPartnerId(partnerAgentGoId);
         if (result != null) {
//            for (PartnerAgentInquiry partnerAgentInquiry : result) {
//               AdminPartnerWorkQueueSubmittedApplicationsDetail pd = new AdminPartnerWorkQueueSubmittedApplicationsDetail();
//               pd.setCompanyId(partnerAgentInquiry.getPartnerAgentInquiriesId());
//               pd.setCompanyName(partnerAgentInquiry.getCompanyName());
//               pd.setCountry(partnerAgentInquiry.getLookupCountry().getCountryName());
//               pd.setEmail(partnerAgentInquiry.getEmail());
//               pd.setFirstName(partnerAgentInquiry.getFirstName());
//               pd.setFlagUrl(partnerAgentInquiry.getCountryFlag());
//               pd.setFollowUpDate(DateUtils.getDateAndTime(partnerAgentInquiry.getFollowUpDate()));
//               pd.setLastName(partnerAgentInquiry.getLastName());
//               pd.setPhone(partnerAgentInquiry.getPhone());
//               pd.setPrograms(partnerAgentInquiry.getCurrentlyOfferingPrograms());
//               pd.setSunmittedOn(DateUtils.getDateAndTime(partnerAgentInquiry.getSubmittedOn()));
//               pd.setWebsite(partnerAgentInquiry.getWebsite());
//               // what is the list of status ???????????????????
//               // PartnerReviewStatus partnerReviewStatus
//               // =partnerReviewStatusRepository.findOne(partnerAgentInquiry.getPartner().getpartnerre));
//               // ???????????????? will be changes once i have clarification
//               pd.setStatusOfInquiry("Valid");
//
//               pwqa.getWorkQueueSubmittedApplications().add(pd);
//            }
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
   public AdminPartnerWorkQueueSubmittedApplicationsDetail updatePartnerApplicationFollowUpDate(int typeId, int categoryId, int staffUserId, String roleType, String newFollowUpDate) {
      AdminPartnerWorkQueueSubmittedApplicationsDetail pd = new AdminPartnerWorkQueueSubmittedApplicationsDetail();
      try {
         PartnerAgentInquiry partnerAgentInquiry = null;// partnerAgentInquiryRepository.findOne(partnerAgentInquiryId);
         partnerAgentInquiry.setFollowUpDate(DateUtils.getDateFromString_followUpdate(newFollowUpDate));
         PartnerAgentInquiry updatedPartnerAgentInquiry = partnerAgentInquiryRepository.saveAndFlush(partnerAgentInquiry);

         pd.setCompanyId(updatedPartnerAgentInquiry.getPartnerAgentInquiriesId());
         pd.setCompanyName(updatedPartnerAgentInquiry.getCompanyName());
         pd.setCountry(updatedPartnerAgentInquiry.getLookupCountry().getCountryName());
         pd.setEmail(updatedPartnerAgentInquiry.getEmail());
         pd.setFirstName(updatedPartnerAgentInquiry.getFirstName());
         pd.setFlagUrl(updatedPartnerAgentInquiry.getCountryFlag());
         pd.setFollowUpDate(DateUtils.getDateAndTime(updatedPartnerAgentInquiry.getFollowUpDate()));
         pd.setLastName(updatedPartnerAgentInquiry.getLastName());
         pd.setPhone(updatedPartnerAgentInquiry.getPhone());
         pd.setPrograms(updatedPartnerAgentInquiry.getCurrentlyOfferingPrograms());
         pd.setSunmittedOn(DateUtils.getDateAndTime(updatedPartnerAgentInquiry.getSubmittedOn()));
         pd.setWebsite(updatedPartnerAgentInquiry.getWebsite());
         // what is the list of status ???????????????????
         // PartnerReviewStatus partnerReviewStatus
         // =partnerReviewStatusRepository.findOne(partnerAgentInquiry.getPartner().getpartnerre));
         // ???????????????? will be changes once i have clarification
         pd.setStatusOfInquiry("Valid");
         pd.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FOLLOW_UP_DATE_UPDATED.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         pd.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.CANT_UPDATE_FOLLOW_UP_DATE.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_FOLLOW_UP_DATE)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_FOLLOW_UP_DATE));
      }
      return pd;
   }

   @Override
   public AdminPartnerWorkQueueSubmittedApplicationsDetail changePartnerApplicationStatus(int typeId, int categoryId, int staffUserId, String roleType, String newStatus) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public PartnerRecruitmentAdmin updatePartnerInquiryOverViewData(PartnerRecruitmentAdmin partnerAdmin) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public PartnerRecruitmentAdminLead updatePartnerInquiryLeadData(PartnerRecruitmentAdminLead partnerRecruitmentAdminLead) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public PartnerAdminDashboardQuickLinks getQuickLinks() {
      PartnerAdminDashboardQuickLinks pwt =new PartnerAdminDashboardQuickLinks();
      try{
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
   public PartnerAdminDashboardQuickStatsCategory getQuickStatsCategory(int quickStatsTypeID) {
      PartnerAdminDashboardQuickStatsCategory pwt = new PartnerAdminDashboardQuickStatsCategory();
      try {
         List<AdminQuickStatsCategory> adminQuickStatsCategories= adminQuickStatsCategoriesRepository.findAllCategoriesByTypeId(quickStatsTypeID);
         if(adminQuickStatsCategories!=null){
            for (AdminQuickStatsCategory adminQuickStatsCategory : adminQuickStatsCategories) {
               PartnerAdminDashboardQuickStatsCategoryDetail partnerAdminDashboardQuickStatsCategory=new PartnerAdminDashboardQuickStatsCategoryDetail();
               partnerAdminDashboardQuickStatsCategory.setTitle(adminQuickStatsCategory.getAdminQSCategoryName());
              AdminQuickStatsCategoryAggregate aggregates = adminQuickStatsCategoriesAggregateRepository.findAggregateValueForCategory(quickStatsTypeID, adminQuickStatsCategory.getAdminQSCategoryId());
              if(aggregates!=null){
                 //TODO
                 partnerAdminDashboardQuickStatsCategory.setNum(0);
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
      try{
         PartnerAdminDashboardBenchmarksDetails partnerAdminDashboardBenchmarks=new PartnerAdminDashboardBenchmarksDetails();
         partnerAdminDashboardBenchmarks.setTitle("title1");
         partnerAdminDashboardBenchmarks.setTotal(80);
         benchMark.getBenchmarks().add(partnerAdminDashboardBenchmarks);
         PartnerAdminDashboardBenchmarksDetails partnerAdminDashboardBenchmarks2=new PartnerAdminDashboardBenchmarksDetails();
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

}
