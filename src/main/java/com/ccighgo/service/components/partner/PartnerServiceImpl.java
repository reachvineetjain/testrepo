/**
 * 
 */
package com.ccighgo.service.components.partner;

import java.util.ArrayList;
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
import com.ccighgo.db.entities.PartnerWorkQueueCategory;
import com.ccighgo.db.entities.PartnerWorkQueueType;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.PartnerAgentInquiryRepository;
import com.ccighgo.jpa.repositories.PartnerDocumentsRepository;
import com.ccighgo.jpa.repositories.PartnerMessagesRepository;
import com.ccighgo.jpa.repositories.PartnerNoteRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeRepository;
import com.ccighgo.jpa.repositories.PartnerProgramRepository;
import com.ccighgo.jpa.repositories.PartnerReferenceCheckRepository;
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
import com.ccighgo.service.transport.partner.beans.partneradmindashboard.PartnerAdminDashboard;
import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerDashboardSections;
import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerDetails;
import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerPrograms;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.PartnerWorkQueueSubmittedApplications;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.PartnerWorkQueueSubmittedApplicationsDetail;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author ravi
 *
 */
@Component
public class PartnerServiceImpl implements PartnerService {

   private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PartnerServiceImpl.class);
   @Autowired
   MessageUtils messageUtil;
   @Autowired
   CommonComponentUtils componentUtils;
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
   public PartnerDetails getPartnerDetails(String userId) {
      PartnerDetails partnerDetails = new PartnerDetails();
      partnerDetails.setPartnerFirstName("Super");
      partnerDetails.setPartnerLastName("Man");
      partnerDetails.setPartnerAddress("Planet Krypton");
      partnerDetails.setPartnerCountry("Doesn't matter");
      partnerDetails.setPartnerCompany("Save the world");
      partnerDetails.setPartnerEmail("superman@planetkrypton.com");
      partnerDetails.setPartnerId(007);
      partnerDetails.setPartnerLogo("Logo stolen by batman");
      List<PartnerPrograms> partnerPrograms = new ArrayList<PartnerPrograms>();
      PartnerPrograms pp = new PartnerPrograms();
      pp.setPartnerDepartmentId("1");
      pp.setPartnerDepartmentName("Save the world");
      pp.setPartnerProgramId("1");
      pp.setPartnerProgramName("Help Joker");
      PartnerPrograms pp1 = new PartnerPrograms();
      pp1.setPartnerDepartmentId("1");
      pp1.setPartnerDepartmentName("Save the world");
      pp1.setPartnerProgramId("1");
      pp1.setPartnerProgramName("Help Joker");
      partnerPrograms.add(pp);
      partnerPrograms.add(pp1);
      List<PartnerDashboardSections> dashboardSections = new ArrayList<PartnerDashboardSections>();
      return partnerDetails;
   }

   @Override
   public PartnerAdminDashboard getDashboard() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public WSDefaultResponse changePartnerApplicationStatus() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public WSDefaultResponse updatePartnerApplicationFollowUpDate() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public PartnerRecruitmentAdmin getAgentRecruitmentData(int agentId) {
      PartnerRecruitmentAdmin pwt = new PartnerRecruitmentAdmin();
      try {
         PartnerAgentInquiry partnerAgentInquiry = partnerAgentInquiryRepository.findOne(agentId);
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
         PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findStatusByPartnerId(agentId);

         if (partnerReviewStatus != null) {
            pwt.setAgentStatus(partnerReviewStatus.getPartnerStatus1().getPartnerStatusName());
            pwt.setLeadStatus(partnerReviewStatus.getPartnerStatus2().getPartnerStatusName());
         }
         try {
            List<PartnerProgram> partnerPrograms = partnerProgramRepository.findAllPartnerProgramsByPartnerId(agentId);
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
            List<PartnerMessage> messages = partnerMessagesRepository.findAllParnerMessagesByPartnerId(agentId);
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
            List<PartnerOffice> offices = partnerOfficeRepository.findPartnerOfficeByPartnerId(agentId);
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
            List<PartnerReferenceCheck> partnerReferenceChecks = partnerReferenceCheckRepository.findAllPartnerReferenceCheckByPartnerId(agentId);
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
            List<PartnerDocument> partnerDocuments = partnerDocumentsRepository.findAllPartnerDocumentByPartnerId(agentId);
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
            List<PartnerNote> partnerNotes = partnerNoteRepository.findAllPartnerNoteByPartnerId(agentId);
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
   public com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.PartnerWorkQueueType getWorkQueueType() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public PartnerWorkQueueSubmittedApplications getWorkQueueSubmittedApplications(int partnerAgentGoId) {
      PartnerWorkQueueSubmittedApplications pwqa = new PartnerWorkQueueSubmittedApplications();
      try {
         List<PartnerAgentInquiry> result = partnerAgentInquiryRepository.findPartnerByPartnerId(partnerAgentGoId);
         if (result != null) {
            for (PartnerAgentInquiry partnerAgentInquiry : result) {
               PartnerWorkQueueSubmittedApplicationsDetail pd = new PartnerWorkQueueSubmittedApplicationsDetail();
               pd.setCompanyName(partnerAgentInquiry.getCompanyName());
               pd.setCountry(partnerAgentInquiry.getLookupCountry().getCountryName());
               pd.setEmail(partnerAgentInquiry.getEmail());
               pd.setFirstName(partnerAgentInquiry.getFirstName());
               pd.setFlagUrl(partnerAgentInquiry.getCountryFlag());
               pd.setFollowUpDate(DateUtils.getDateAndTime(partnerAgentInquiry.getFollowUpDate()));
               pd.setLastName(partnerAgentInquiry.getLastName());
               pd.setPhone(partnerAgentInquiry.getPhone());
//               pd.setPrograms(getPartnerPrograms(partnerAgentInquiry.getPartnerAgentPrograms()));
               pd.setSunmittedOn(DateUtils.getDateAndTime(partnerAgentInquiry.getSubmittedOn()));
               pd.setWebsite(partnerAgentInquiry.getWebsite());
               // what is the list of status ???????????????????
               // PartnerReviewStatus partnerReviewStatus =
               // partnerReviewStatusRepository.findOne(partnerAgentInquiry.getPartner().getpartnerre));
               // pd.setStatus();
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

   private String getPartnerPrograms(List<PartnerProgram> partnerAgentPrograms) {
      StringBuilder st = new StringBuilder();
      int index = 0;
      if (partnerAgentPrograms != null)
         for (PartnerProgram partnerAgentProgram : partnerAgentPrograms) {
            if (index++ > 0)
               st.append(",");
            st.append(partnerAgentProgram.getLookupDepartmentProgram().getProgramName());
         }
      return null;
   }

   @Override
   public com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.PartnerWorkQueueCategory getWorkQueueCategory() {
      // TODO Auto-generated method stub
      return null;
   }

}