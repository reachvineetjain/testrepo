/**
 * 
 */
package com.ccighgo.service.components.partner.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.CCIStaffUser;
import com.ccighgo.db.entities.PartnerAgentInquiry;
import com.ccighgo.db.entities.PartnerDocument;
import com.ccighgo.db.entities.PartnerMessage;
import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerOffice;
import com.ccighgo.db.entities.PartnerProgram;
import com.ccighgo.db.entities.PartnerReferenceCheck;
import com.ccighgo.db.entities.PartnerReviewStatus;
import com.ccighgo.exception.ErrorCode;
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
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdmin;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningAdditionalInfo;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningDetail;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningDocuments;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningMarkedByUser;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningMessagesToAgent;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningNotes;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningOffices;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningProgramOfferings;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningPrograms;
import com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata.PartnerRecruitmentAdminScreeningReferenceCheck;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.AdminPartnerWorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplications;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplicationsDetail;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.AdminPartnerWorkQueueType;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;


/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Component
public class PartnerAdminServiceImpl implements PartnerAdminService {

   private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PartnerAdminServiceImpl.class);

   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;

   @Autowired PartnerRepository partnerRepository;
   @Autowired LookupDepartmentProgramRepository lookupDepartmentProgramRepository;
  
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
   

 

   @Override
   public AdminPartnerWorkQueueSubmittedApplicationsDetail changePartnerApplicationStatus(int partnerAgentInquiryId) {
      // TODO Auto-generated method stub
      return null;
   }

  

   @Override
   public PartnerRecruitmentAdmin getAgentRecruitmentData(int partnerGoId) {
      PartnerRecruitmentAdmin pwt = new PartnerRecruitmentAdmin();
      try {
         PartnerAgentInquiry partnerAgentInquiry = partnerAgentInquiryRepository.findOne(partnerGoId);
         if(partnerAgentInquiry==null){
            pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_AGENT_DETAIL.getValue(),
                  messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_AGENT_DETAIL)));
            logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_AGENT_DETAIL));
            logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_AGENT_DETAIL));
            return pwt;
         }
         try {
            PartnerRecruitmentAdminScreeningDetail partnerRecruitmentAdminScreeningDetail = new PartnerRecruitmentAdminScreeningDetail();
            if(partnerAgentInquiry.getCompanyName()!=null)
            partnerRecruitmentAdminScreeningDetail.setCompanyName(partnerAgentInquiry.getCompanyName());
            if(partnerAgentInquiry.getLookupCountry()!=null)
            partnerRecruitmentAdminScreeningDetail.setCountry(partnerAgentInquiry.getLookupCountry().getCountryName());
            if(partnerAgentInquiry.getEmail()!=null)
            partnerRecruitmentAdminScreeningDetail.setEmail(partnerAgentInquiry.getEmail());
            if(partnerAgentInquiry.getFirstName()!=null)
            partnerRecruitmentAdminScreeningDetail.setFirstName(partnerAgentInquiry.getFirstName());
            if(partnerAgentInquiry.getLastName()!=null)
            partnerRecruitmentAdminScreeningDetail.setLastName(partnerAgentInquiry.getLastName());
            if(partnerAgentInquiry.getPhone()!=null)
            partnerRecruitmentAdminScreeningDetail.setPhone(partnerAgentInquiry.getPhone());
            if(partnerAgentInquiry.getWebsite()!=null)
            partnerRecruitmentAdminScreeningDetail.setWebsite(partnerAgentInquiry.getWebsite());
            if(partnerAgentInquiry.getAdressLineOne()!=null)
            partnerRecruitmentAdminScreeningDetail.setAddress1(partnerAgentInquiry.getAdressLineOne());
            if(partnerAgentInquiry.getAdressLineTwo()!=null)
            partnerRecruitmentAdminScreeningDetail.setAddress2(partnerAgentInquiry.getAdressLineTwo());
            if(partnerAgentInquiry.getCity()!=null)
            partnerRecruitmentAdminScreeningDetail.setCity(partnerAgentInquiry.getCity());
            // Rating value is static ?????
            partnerRecruitmentAdminScreeningDetail.setRating(0);
//            if(partnerAgentInquiry.getSalutation()!=null)
//            partnerRecruitmentAdminScreeningDetail.setSalutation(partnerAgentInquiry.getSalutation());
            if(partnerAgentInquiry.getState()!=null)
            partnerRecruitmentAdminScreeningDetail.setStateOrProvince(partnerAgentInquiry.getState());
            pwt.setDetail(partnerRecruitmentAdminScreeningDetail);
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }

         pwt.setFollowUpDate(DateUtils.getDateAndTime(partnerAgentInquiry.getFollowUpDate()));
         PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findStatusByPartnerId(partnerGoId);

         if (partnerReviewStatus != null) {
            pwt.setAgentStatus(partnerReviewStatus.getPartnerStatus1().getPartnerStatusName());
            pwt.setLeadStatus(partnerReviewStatus.getPartnerStatus2().getPartnerStatusName());
         }
         try {
            List<PartnerProgram> partnerPrograms = partnerProgramRepository.findAllPartnerProgramsByPartnerId(partnerGoId);
            if (partnerPrograms != null) {
               for (PartnerProgram partnerProgram : partnerPrograms) {
                  PartnerRecruitmentAdminScreeningPrograms partnerRecruitmentAdminScreeningPrograms = new PartnerRecruitmentAdminScreeningPrograms();
                  partnerRecruitmentAdminScreeningPrograms.setAction(partnerProgram.getIsEligible() == 1 ? "Eligible" : "Ineligible");
                  partnerRecruitmentAdminScreeningPrograms.setApplied(partnerProgram.getHasApplied() == 1);
                  CCIStaffUser user = partnerProgram.getCcistaffUser();
                  PartnerRecruitmentAdminScreeningMarkedByUser markedByUser = new PartnerRecruitmentAdminScreeningMarkedByUser();
                  markedByUser.setImageUrl(user.getPhoto());
                  markedByUser.setUserName(user.getFirstName() + " " + user.getLastName());
                  partnerRecruitmentAdminScreeningPrograms.setMarkedBy(markedByUser);
                  partnerRecruitmentAdminScreeningPrograms.setNotify(partnerProgram.getIsPDNotified() == 1);
//                  partnerRecruitmentAdminScreeningPrograms.setProgramName(partnerProgram.getLookupDepartmentProgram1().getProgramName());
                  pwt.getPrograms().add(partnerRecruitmentAdminScreeningPrograms);
               }
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            // ??????????????????? program offerings
            PartnerRecruitmentAdminScreeningProgramOfferings programOffering = new PartnerRecruitmentAdminScreeningProgramOfferings();
            // programOffering.setHighSchoolAbroad(partnerAgentInquiry.);
            // programOffering.setOther(value);
            // programOffering.setTeachAbroad(value);
            // programOffering.setVolunteerAbroad(value);
            pwt.setParticipantProgramOfferings(programOffering);
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         pwt.setDescriptionsOfPrograms(partnerAgentInquiry.getCurrentlyOfferingPrograms());
         try {
            PartnerRecruitmentAdminScreeningAdditionalInfo additionalInformation = new PartnerRecruitmentAdminScreeningAdditionalInfo();
            additionalInformation.setHearAboutUsFrom(partnerAgentInquiry.getHowDidYouHearAboutCCI());
            additionalInformation.setLikeToKnowMoreAboutAmbassadorScholarship(partnerAgentInquiry.getAmbassadorScholershipParticipants() == 1);
            additionalInformation.setSendPartnersToUSA(partnerAgentInquiry.getCurrentlySendingParticipantToUS() == 1);
            additionalInformation.setYearsInBusiness(Integer.parseInt(partnerAgentInquiry.getBusinessYears()));
            pwt.setAdditionalInformation(additionalInformation);
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            List<PartnerMessage> messages = partnerMessagesRepository.findAllParnerMessagesByPartnerId(partnerGoId);
            if (messages != null) {
               for (PartnerMessage partnerMessage : messages) {
                  PartnerRecruitmentAdminScreeningMessagesToAgent m = new PartnerRecruitmentAdminScreeningMessagesToAgent();
                  m.setDate(DateUtils.getDateAndTime(partnerMessage.getCreatedOn()));
                  m.setImageUrl(partnerMessage.getCcistaffUser().getPhoto());
                  m.setMessage(partnerMessage.getPartnerInquiryMessage());
                  // ?????? not used column
                  m.setReceived(false);
                  m.setSenderName(partnerMessage.getCcistaffUser().getFirstName() + " " + partnerMessage.getCcistaffUser().getLastName());
                  pwt.getMessagesToAgent().add(m);
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
   public AdminPartnerWorkQueueType getWorkQueueType(int partnerGoId) {
      AdminPartnerWorkQueueType pwt = new AdminPartnerWorkQueueType();
      try {
         
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_TYPE.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_TYPE)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_TYPE));
      }
      return pwt;
   }
   @Override
   public AdminPartnerWorkQueueCategory getWorkQueueCategory(int partnerGoId) {
      AdminPartnerWorkQueueCategory pwqc = new AdminPartnerWorkQueueCategory();
      try {
         
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         pwqc.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_CATEGORY.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_CATEGORY)));
         logger.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_CATEGORY));
      }
      return pwqc;
   }
   @Override
   public AdminPartnerWorkQueueSubmittedApplications getWorkQueueSubmittedApplications(int partnerAgentGoId) {
      AdminPartnerWorkQueueSubmittedApplications pwqa = new AdminPartnerWorkQueueSubmittedApplications();
      try {
         List<PartnerAgentInquiry> result = partnerAgentInquiryRepository.findPartnerByPartnerId(partnerAgentGoId);
         if (result != null) {
            for (PartnerAgentInquiry partnerAgentInquiry : result) {
               AdminPartnerWorkQueueSubmittedApplicationsDetail pd = new AdminPartnerWorkQueueSubmittedApplicationsDetail();
               pd.setCompanyId(partnerAgentInquiry.getPartnerAgentInquiriesId());
               pd.setCompanyName(partnerAgentInquiry.getCompanyName());
               pd.setCountry(partnerAgentInquiry.getLookupCountry().getCountryName());
               pd.setEmail(partnerAgentInquiry.getEmail());
               pd.setFirstName(partnerAgentInquiry.getFirstName());
               pd.setFlagUrl(partnerAgentInquiry.getCountryFlag());
               pd.setFollowUpDate(DateUtils.getDateAndTime(partnerAgentInquiry.getFollowUpDate()));
               pd.setLastName(partnerAgentInquiry.getLastName());
               pd.setPhone(partnerAgentInquiry.getPhone());
               pd.setPrograms(partnerAgentInquiry.getCurrentlyOfferingPrograms());
               pd.setSunmittedOn(DateUtils.getDateAndTime(partnerAgentInquiry.getSubmittedOn()));
               pd.setWebsite(partnerAgentInquiry.getWebsite());
               // what is the list of status ???????????????????
//                PartnerReviewStatus partnerReviewStatus =partnerReviewStatusRepository.findOne(partnerAgentInquiry.getPartner().getpartnerre));
               //???????????????? will be changes once i have clarification
               pd.setStatusOfInquiry("Valid");
               
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
   public AdminPartnerWorkQueueSubmittedApplicationsDetail updatePartnerApplicationFollowUpDate(int partnerAgentInquiryId, String newFollowUpDate) {
      AdminPartnerWorkQueueSubmittedApplicationsDetail pd =new AdminPartnerWorkQueueSubmittedApplicationsDetail();
      try {
         PartnerAgentInquiry partnerAgentInquiry= partnerAgentInquiryRepository.findOne(partnerAgentInquiryId);
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
//          PartnerReviewStatus partnerReviewStatus =partnerReviewStatusRepository.findOne(partnerAgentInquiry.getPartner().getpartnerre));
         //???????????????? will be changes once i have clarification
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
}
