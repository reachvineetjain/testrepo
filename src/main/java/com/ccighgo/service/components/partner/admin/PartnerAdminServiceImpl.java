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
import com.ccighgo.db.entities.CCIStaffUser;
import com.ccighgo.db.entities.CCIStaffUsersCCIStaffRole;
import com.ccighgo.db.entities.GoIdSequence;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerAgentInquiry;
import com.ccighgo.db.entities.PartnerContact;
import com.ccighgo.db.entities.PartnerDocument;
import com.ccighgo.db.entities.PartnerMessage;
import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerOffice;
import com.ccighgo.db.entities.PartnerProgram;
import com.ccighgo.db.entities.PartnerReferenceCheck;
import com.ccighgo.db.entities.PartnerReviewStatus;
import com.ccighgo.db.entities.PartnerStatus;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.AdminQuickStatsCategoriesAggregateRepository;
import com.ccighgo.jpa.repositories.AdminQuickStatsCategoriesRepository;
import com.ccighgo.jpa.repositories.AdminQuickStatsTypeAggregateRepository;
import com.ccighgo.jpa.repositories.AdminQuickStatsTypeRepository;
import com.ccighgo.jpa.repositories.AdminWorkQueueCategoryAggregateRepository;
import com.ccighgo.jpa.repositories.AdminWorkQueueCategoryRepository;
import com.ccighgo.jpa.repositories.AdminWorkQueueTypeRepository;
import com.ccighgo.jpa.repositories.CCIStaffUsersCCIStaffRolesRepository;
import com.ccighgo.jpa.repositories.CCIStaffUsersRepository;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LookupDepartmentProgramRepository;
import com.ccighgo.jpa.repositories.PartnerAgentInquiryRepository;
import com.ccighgo.jpa.repositories.PartnerContactRepository;
import com.ccighgo.jpa.repositories.PartnerDocumentsRepository;
import com.ccighgo.jpa.repositories.PartnerMessagesRepository;
import com.ccighgo.jpa.repositories.PartnerNoteRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeRepository;
import com.ccighgo.jpa.repositories.PartnerProgramRepository;
import com.ccighgo.jpa.repositories.PartnerReferenceCheckRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerReviewStatusRepository;
import com.ccighgo.jpa.repositories.PartnerStatusRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminMessageConstants;
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
   CCIStaffUsersRepository cciStaffUsersRepository;
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
                  CCIStaffUsersCCIStaffRole staffUserAndRole = cciStaffRolesRepository.findOne(partnerNote.getCreatedBy());
                  if (staffUserAndRole != null) {
                     com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata.NoteUserCreator noteCreator = new com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata.NoteUserCreator();
                     noteCreator.setPhotoUrl(staffUserAndRole.getCcistaffUser().getPhoto());
                     noteCreator.setRole(staffUserAndRole.getCcistaffRole().getCciStaffRoleName());
                     noteCreator.setUserName(staffUserAndRole.getCcistaffUser().getFirstName() + " " + staffUserAndRole.getCcistaffUser().getLastName());
                     note.setCreatedBy(noteCreator);
                  }
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
   public PartnerRecruitmentAdminLead updatePartnerInquiryLeadData(PartnerRecruitmentAdminLead partnerRecruitmentAdminLead) {
      PartnerRecruitmentAdminLead pwt = new PartnerRecruitmentAdminLead();
      try {

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
               if (pwt.isActive()){
                PartnerStatus activeStatus = partnerStatusRepository.findStatusByName("Valid");
                partnerReviewStatus.setPartnerStatus1(activeStatus);
               }
               if (pwt.getLeadStatus()!=null){
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
            //TODO Ask phani !!!!!!
            partnerAgentInquiry.setBusinessName(detail.getUsername());

            
            if (partner != null) {
               partner.setBillingNotes(detail.getBillingNotes());
               partner.setCanHaveSubPartner((byte) (detail.getCanHaveSubPartner()=="true"?1:0));
               partner.setEmail(detail.getGeneralEmail());
               partner.setInvoiceMail(detail.getInvoiceEmail());
               partner.setMultiCountrySender((byte) (detail.getMultiCountrySender()=="true"?1:0));
               partner.setQuickbooksCode(detail.getQuickbooksCode());
            }
            // TODO ask vivek
//            try {
//               for (PartnerProgram partnerProgram : partnerPrograms) {
//                  CCIInquiryFormPerson cciContact = new CCIInquiryFormPerson();
//                  cciContact.setUserName(partnerProgram.getCcistaffUser().getFirstName());
//                  if (partnerProgram.getCcistaffUser().getCcistaffUsersCcistaffRoles() != null && !partnerProgram.getCcistaffUser().getCcistaffUsersCcistaffRoles().isEmpty())
//                     cciContact.setRole(partnerProgram.getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole().getCciStaffRoleName());
//                  cciContact.setImageUrl(partnerProgram.getCcistaffUser().getPhoto());
//                  detail.setCciContact(cciContact);
//                  break;
//               }
//            } catch (Exception e) {
//               ExceptionUtil.logException(e, logger);
//            }

//            try {
//               if (partnerPrograms != null) {
//                  for (PartnerProgram partnerProgram : partnerPrograms) {
//                     AdminPartnerProgramsElgibilityAndCCIContact contact = new AdminPartnerProgramsElgibilityAndCCIContact();
//                     contact.setCciContactProgramName(partnerProgram.getLookupDepartmentProgram().getProgramName());
//                     contact.setMarked(false);
//                     contact.setProgramName(partnerProgram.getLookupDepartmentProgram().getProgramName());
//                     if (partnerProgram.getCcistaffUser() != null) {
//                        CCIInquiryFormPerson cciContact = new CCIInquiryFormPerson();
//                        cciContact.setUserName(partnerProgram.getCcistaffUser().getFirstName());
//                        if (partnerProgram.getCcistaffUser().getCcistaffUsersCcistaffRoles() != null && !partnerProgram.getCcistaffUser().getCcistaffUsersCcistaffRoles().isEmpty())
//                           cciContact.setRole(partnerProgram.getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole().getCciStaffRoleName());
//                        cciContact.setImageUrl(partnerProgram.getCcistaffUser().getPhoto());
//                        contact.setCciContact(cciContact);
//                        pwt.getProgramEligibilityAndCCIContact().add(contact);
//                     }
//                  }
//               }
//            } catch (Exception e) {
//               ExceptionUtil.logException(e, logger);
//            }
            
//            try {
//               List<PartnerReferenceCheck> partnerReferenceChecks = partnerReferenceCheckRepository.findAllPartnerReferenceCheckByPartnerId(goId);
//               if (partnerReferenceChecks != null) {
//                  for (PartnerReferenceCheck partnerReferenceCheck : partnerReferenceChecks) {
//                     PartnerRecruitmentAdminScreeningReferenceCheck refCheck = new PartnerRecruitmentAdminScreeningReferenceCheck();
//                     refCheck.setApprovedBy(partnerReferenceCheck.getReferenceApprovedBy());
//                     refCheck.setApprovedOn(DateUtils.getDateAndTime(partnerReferenceCheck.getReferenceApprovedOn()));
//                     refCheck.setCompletedBy(partnerReferenceCheck.getReferenceCompletedBy());
//                     refCheck.setCompletedOn(DateUtils.getDateAndTime(partnerReferenceCheck.getReferenceCompletedOn()));
//                     refCheck.setLatestCopyOfBusinessExpires(DateUtils.getDateAndTime(partnerReferenceCheck.getBusinessLicenseExpiryDate()));
//                     refCheck.setNote(partnerReferenceCheck.getReferenceCheckNotes());
//                     pwt.getReferenceCheck().add(refCheck);
//                  }
//               }
//            } catch (Exception e) {
//               ExceptionUtil.logException(e, logger);
//            }
            
            
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
                  doc.setActive(p.getDocumentInformation().getActive() == 1);
                  doc.setDescription("");
                  doc.setDocName(p.getDocumentInformation().getDocumentName());
                  doc.setDocType(p.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
                  doc.setDocUrl(p.getDocumentInformation().getUrl());
                  doc.setFileName(p.getDocumentInformation().getFileName());
                  doc.setFileType("");
                  doc.setUploadDate(DateUtils.getDateAndTime(p.getDocumentInformation().getCreatedOn()));
                  Integer createdBy = p.getDocumentInformation().getCreatedBy();
                  CCIStaffUsersCCIStaffRole staffUserAndRole = cciStaffRolesRepository.findOne(createdBy);
                  if (staffUserAndRole != null) {
                     DocumentUploadUser uploadedBy = new DocumentUploadUser();
                     uploadedBy.setPhotoUrl(staffUserAndRole.getCcistaffUser().getPhoto());
                     uploadedBy.setRole(staffUserAndRole.getCcistaffRole().getCciStaffRoleName());
                     uploadedBy.setUserName(staffUserAndRole.getCcistaffUser().getFirstName() + " " + staffUserAndRole.getCcistaffUser().getLastName());
                     doc.setUploadedBy(uploadedBy);
                  }
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
                
                  
                  CCIStaffUsersCCIStaffRole staffUserAndRole = cciStaffRolesRepository.findOne(partnerNote.getCreatedBy());
                  if (staffUserAndRole != null) {
                     NoteUserCreator noteCreator = new NoteUserCreator();
                     noteCreator.setPhotoUrl(staffUserAndRole.getCcistaffUser().getPhoto());
                     noteCreator.setRole(staffUserAndRole.getCcistaffRole().getCciStaffRoleName());
                     noteCreator.setUserName(staffUserAndRole.getCcistaffUser().getFirstName() + " " + staffUserAndRole.getCcistaffUser().getLastName());
                     note.setCreatedBy(noteCreator);
                  }
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

}
