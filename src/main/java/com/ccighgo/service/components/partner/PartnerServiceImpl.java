/**
 * 
 */
package com.ccighgo.service.components.partner;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.CCIStaffUser;
import com.ccighgo.db.entities.LookupDepartmentProgram;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerAgentInquiry;
import com.ccighgo.db.entities.PartnerAnnouncement;
import com.ccighgo.db.entities.PartnerCCIContact;
import com.ccighgo.db.entities.PartnerDocument;
import com.ccighgo.db.entities.PartnerMessage;
import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerOffice;
import com.ccighgo.db.entities.PartnerProgram;
import com.ccighgo.db.entities.PartnerReferenceCheck;
import com.ccighgo.db.entities.PartnerReviewStatus;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerSeasonAllocation;
import com.ccighgo.db.entities.PartnerWorkQueueCategory;
import com.ccighgo.db.entities.PartnerWorkQueueCategoryAggregate;
import com.ccighgo.db.entities.PartnerWorkQueueType;
import com.ccighgo.db.entities.PartnerWorkQueueTypeAggregate;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.LookupDepartmentProgramRepository;
import com.ccighgo.jpa.repositories.PartnerAgentInquiryRepository;
import com.ccighgo.jpa.repositories.PartnerCCIContactRepository;
import com.ccighgo.jpa.repositories.PartnerDocumentsRepository;
import com.ccighgo.jpa.repositories.PartnerMessagesRepository;
import com.ccighgo.jpa.repositories.PartnerNoteRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeRepository;
import com.ccighgo.jpa.repositories.PartnerProgramRepository;
import com.ccighgo.jpa.repositories.PartnerQuickStatsCategoryAggregateRepository;
import com.ccighgo.jpa.repositories.PartnerQuickStatsCategoryRepository;
import com.ccighgo.jpa.repositories.PartnerQuickStatsTypeAggregateRepository;
import com.ccighgo.jpa.repositories.PartnerQuickStatsTypeRepository;
import com.ccighgo.jpa.repositories.PartnerReferenceCheckRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerReviewStatusRepository;
import com.ccighgo.jpa.repositories.PartnerWorkQueueCategoryAggregateRepository;
import com.ccighgo.jpa.repositories.PartnerWorkQueueCategoryRepository;
import com.ccighgo.jpa.repositories.PartnerWorkQueueRepository;
import com.ccighgo.jpa.repositories.PartnerWorkQueueTypeAggregateRepository;
import com.ccighgo.jpa.repositories.PartnerWorkQueueTypeRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminMessageConstants;
import com.ccighgo.service.components.errormessages.constants.PartnerDashboardMessageConstants;
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
import com.ccighgo.service.transport.partner.beans.partnercapdetails.PartnerCAPDashboard;
import com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerDashboard;
import com.ccighgo.service.transport.partner.beans.partnerf1details.F1Allocation;
import com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerF1Announcement;
import com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerF1CCIContact;
import com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerF1Dashboard;
import com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerF1Program;
import com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerF1WorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerF1WorkQueueType;
import com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerIHPAnnouncement;
import com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerIHPCCIContact;
import com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerIHPDashboard;
import com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerIHPProgram;
import com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerIHPWorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerIHPWorkQueueType;
import com.ccighgo.service.transport.partner.beans.partnerj1details.J1HSAllocation;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSAnnouncement;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSCCIContact;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSDashboard;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSProgram;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSWorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSWorkQueueType;
import com.ccighgo.service.transport.partner.beans.partnerwntdetails.PartnerWnTDashboard;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.PartnerWorkQueueSubmittedApplications;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.PartnerWorkQueueSubmittedApplicationsDetail;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;

/**
 * @author ravi
 *
 */
@Component
public class PartnerServiceImpl implements PartnerService {

   private static final Logger LOGGER = Logger.getLogger(PartnerServiceImpl.class);
   private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PartnerServiceImpl.class);

   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;

   @Autowired PartnerRepository partnerRepository;
   @Autowired LookupDepartmentProgramRepository lookupDepartmentProgramRepository;
   @Autowired PartnerCCIContactRepository partnerCCIContactRepository;
   @Autowired PartnerWorkQueueRepository partnerWorkQueueRepository;
   @Autowired PartnerWorkQueueTypeRepository partnerWorkQueueTypeRepository;
   @Autowired PartnerWorkQueueTypeAggregateRepository partnerWorkQueueTypeAggregateRepository;
   @Autowired PartnerWorkQueueCategoryRepository partnerWorkQueueCategoryRepository;
   @Autowired PartnerWorkQueueCategoryAggregateRepository partnerWorkQueueCategoryAggregateRepository;
   @Autowired PartnerQuickStatsTypeRepository partnerQuickStatsTypeRepository;
   @Autowired PartnerQuickStatsCategoryRepository partnerQuickStatsCategoryRepository;
   @Autowired PartnerQuickStatsTypeAggregateRepository partnerQuickStatsTypeAggregateRepository;
   @Autowired PartnerQuickStatsCategoryAggregateRepository partnerQuickStatsCategoryAggregateRepository;

   @Autowired PartnerAgentInquiryRepository partnerAgentInquiryRepository;
   @Autowired PartnerReviewStatusRepository partnerReviewStatusRepository;
   @Autowired PartnerProgramRepository partnerProgramRepository;
   @Autowired PartnerMessagesRepository partnerMessagesRepository;
   @Autowired PartnerOfficeRepository partnerOfficeRepository;
   @Autowired PartnerReferenceCheckRepository partnerReferenceCheckRepository;
   @Autowired PartnerDocumentsRepository partnerDocumentsRepository;
   @Autowired PartnerNoteRepository partnerNoteRepository;

   @Override
   public PartnerDashboard getPartnerDashboard(String partnerGoId) {
      PartnerDashboard partnerDashboard = new PartnerDashboard();
      if (partnerGoId == null || Integer.valueOf(partnerGoId) == 0 || Integer.valueOf(partnerGoId) < 0) {
         partnerDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerDashboardMessageConstants.INVALID_PARTNER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerDashboardMessageConstants.INVALID_PARTNER_ID));
         return partnerDashboard;
      } else {
         try {
            Partner partner = partnerRepository.findOne(Integer.valueOf(partnerGoId));
            if (partner != null) {
               partnerDashboard.setPartnerId(partner.getPartnerGoId());
               partnerDashboard.setPartnerCompany(partner.getCompanyName());
               List<PartnerSeason> partnerSeasons = partner.getPartnerSeasons();
               if (partnerSeasons != null && partnerSeasons.size() > 0) {
                  List<com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerProgram> partnerProgramsList = new ArrayList<com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerProgram>();
                  for (PartnerSeason partnerSeason : partnerSeasons) {
                     com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerProgram prg = new com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerProgram();
                     prg.setPartnerSeasonId(partnerSeason.getSeason().getSeasonId());
                     List<LookupDepartmentProgram> lkDeptPrgList = lookupDepartmentProgramRepository.findAll();
                     for (LookupDepartmentProgram deptPrg : lkDeptPrgList) {
                        if (deptPrg.getProgramName().equals(CCIConstants.HSP_J1_HS) && deptPrg.getLookupDepartmentProgramId() == CCIConstants.HSP_J1_HS_ID) {
                           prg.setPartnerDepartmentProgramId(CCIConstants.HSP_J1_HS_ID);
                           prg.setPartnerDepartmentProgramName(CCIConstants.HSP_J1_HS);
                           prg.setProgramDetailsUrl("partner/j1/program/details/");
                        }
                        if (deptPrg.getProgramName().equals(CCIConstants.HSP_F1) && deptPrg.getLookupDepartmentProgramId() == CCIConstants.HSP_F1_ID) {
                           prg.setPartnerDepartmentProgramId(CCIConstants.HSP_F1_ID);
                           prg.setPartnerDepartmentProgramName(CCIConstants.HSP_F1);
                           prg.setProgramDetailsUrl("partner/f1/program/details/");
                        }
                        if (deptPrg.getProgramName().equals(CCIConstants.HSP_STP_IHP) && deptPrg.getLookupDepartmentProgramId() == CCIConstants.HSP_STP_IHP_ID) {
                           prg.setPartnerDepartmentProgramId(CCIConstants.HSP_STP_IHP_ID);
                           prg.setPartnerDepartmentProgramName(CCIConstants.HSP_STP_IHP);
                           prg.setProgramDetailsUrl("partner/ihp/program/details/");
                        }
                        if (deptPrg.getProgramName().equals(CCIConstants.WP_WT_CAP) && deptPrg.getLookupDepartmentProgramId() == CCIConstants.WP_WT_CAP_ID) {
                           prg.setPartnerDepartmentProgramId(CCIConstants.WP_WT_CAP_ID);
                           prg.setPartnerDepartmentProgramName(CCIConstants.WP_WT_CAP);
                           prg.setProgramDetailsUrl("partner/cap/program/details/");
                        }
                        if (deptPrg.getProgramName().equals(CCIConstants.WP_WT_SUMMER) && deptPrg.getLookupDepartmentProgramId() == CCIConstants.WP_WT_SUMMER_ID) {
                           prg.setPartnerDepartmentProgramId(CCIConstants.WP_WT_SUMMER_ID);
                           prg.setPartnerDepartmentProgramName(CCIConstants.WP_WT_SUMMER);
                           prg.setProgramDetailsUrl("partner/wnt/summer/program/details/");
                        }
                        if (deptPrg.getProgramName().equals(CCIConstants.WP_WT_WINTER) && deptPrg.getLookupDepartmentProgramId() == CCIConstants.WP_WT_WINTER_ID) {
                           prg.setPartnerDepartmentProgramId(CCIConstants.WP_WT_WINTER_ID);
                           prg.setPartnerDepartmentProgramName(CCIConstants.WP_WT_WINTER);
                           prg.setProgramDetailsUrl("partner/wnt/winter/program/details/");
                        }
                     }
                     partnerProgramsList.add(prg);
                  }
                  partnerDashboard.getPartnerPrograms().addAll(partnerProgramsList);
               } else {
                  // no programs found for this partner
               }
            } else {
               // no partner found with the goid provided
               partnerDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_PROGRAM_DETAILS_FOUND.getValue(),
                     messageUtil.getMessage(PartnerDashboardMessageConstants.NO_PROGRAM_DETAILS_FOUND)));
               LOGGER.error(messageUtil.getMessage(PartnerDashboardMessageConstants.NO_PROGRAM_DETAILS_FOUND));
            }
         } catch (CcighgoException e) {
            // error getting partner details
            partnerDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_PROGRAM_DETAILS_FOUND.getValue(),
                  messageUtil.getMessage(PartnerDashboardMessageConstants.NO_PROGRAM_DETAILS_FOUND)));
            LOGGER.error(messageUtil.getMessage(PartnerDashboardMessageConstants.NO_PROGRAM_DETAILS_FOUND));
         }
      }
      return partnerDashboard;
   }

   @Override
   public PartnerJ1HSDashboard getJ1HSDashboard(String partnerGoId) {
      PartnerJ1HSDashboard j1hsDashboard = new PartnerJ1HSDashboard();
      if (partnerGoId == null || Integer.valueOf(partnerGoId) == 0 || Integer.valueOf(partnerGoId) < 0) {
         j1hsDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerDashboardMessageConstants.INVALID_PARTNER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerDashboardMessageConstants.INVALID_PARTNER_ID));
         return j1hsDashboard;
      } else {
         try {
            Partner partner = partnerRepository.findOne(Integer.valueOf(partnerGoId));
            if (partner != null) {
               j1hsDashboard.setPartnerGoId(partner.getPartnerGoId());
               j1hsDashboard.setPartnerCompany(partner.getCompanyName());
               j1hsDashboard.setPartnerLogo(partner.getPartnerLogo());

               // announcements
               List<PartnerAnnouncement> partnerAnnouncementList = partner.getPartnerAnnouncements();
               List<PartnerJ1HSAnnouncement> partnerJ1HSAnnouncements = null;
               if (partnerAnnouncementList != null && partnerAnnouncementList.size() > 0) {
                  partnerJ1HSAnnouncements = new ArrayList<PartnerJ1HSAnnouncement>();
                  for (PartnerAnnouncement ann : partnerAnnouncementList) {
                     PartnerJ1HSAnnouncement j1hsAnn = new PartnerJ1HSAnnouncement();
                     j1hsAnn.setAnnouncement(ann.getAnnouncement());
                     j1hsAnn.setTimestamp(DateUtils.getTimestamp(ann.getCreatedOn()));
                     partnerJ1HSAnnouncements.add(j1hsAnn);
                  }
               }
               j1hsDashboard.getPartnerAnnouncements().addAll(partnerJ1HSAnnouncements);

               // cci contact
               PartnerCCIContact partnerCCIJ1Contact = partnerCCIContactRepository.getCCIContactByDepartmentProgramId(partner.getPartnerGoId(), CCIConstants.HSP_J1_HS_ID);
               PartnerJ1HSCCIContact cciContact = null;
               if (partnerCCIJ1Contact != null) {
                  cciContact = new PartnerJ1HSCCIContact();
                  cciContact.setPartnerCCIContactName(partnerCCIJ1Contact.getCcistaffUser().getFirstName() + " " + partnerCCIJ1Contact.getCcistaffUser().getLastName());
                  cciContact.setPartnerProgramName(CCIConstants.HSP_J1_HS + " CCI Contact");
                  cciContact.setPartnerCCIContactDesignation(partnerCCIJ1Contact.getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole().getCciStaffRoleName());
                  cciContact.setPartnerCCIContactImageUrl(partnerCCIJ1Contact.getCcistaffUser().getPhoto());
                  cciContact.setPartnerCCIContactPhone(partnerCCIJ1Contact.getCcistaffUser().getPrimaryPhone());
                  cciContact.setPartnerCCIContactExtentionNo(partnerCCIJ1Contact.getCcistaffUser().getPhoneExtension());
               }
               j1hsDashboard.setCciContact(cciContact);

               // work queue types and categories
               List<PartnerJ1HSWorkQueueType> partnerWorkQueueTypesList = new ArrayList<PartnerJ1HSWorkQueueType>();
               List<PartnerWorkQueueType> partnerWorkQueueTypes = partnerWorkQueueTypeRepository.getPartnerWorkQueueTypesByDepartmentProgramId(CCIConstants.HSP_J1_HS_ID);
               if (partnerWorkQueueTypes != null) {
                  for (PartnerWorkQueueType pqType : partnerWorkQueueTypes) {
                     PartnerJ1HSWorkQueueType j1wqType = new PartnerJ1HSWorkQueueType();
                     j1wqType.setPartnerWorkQueueTypeName(pqType.getPartnerWQTypeName());
                     PartnerWorkQueueTypeAggregate typeAgg = partnerWorkQueueTypeAggregateRepository.getWorkQueueTypeAggregateByDepartmentProgramId(pqType.getPartnerWQTypeId(),
                           partner.getPartnerGoId(), CCIConstants.HSP_J1_HS_ID);
                     j1wqType.setPartnerWorkQueueTypeNo(typeAgg.getPartnerWQTypeAggregate());
                     List<PartnerWorkQueueCategory> caregoryList = partnerWorkQueueCategoryRepository.getWorkQueueCategoryForType(pqType.getPartnerWQTypeId());
                     if (caregoryList != null) {
                        List<PartnerJ1HSWorkQueueCategory> partnerWorkQueueCategories = new ArrayList<PartnerJ1HSWorkQueueCategory>();
                        for (PartnerWorkQueueCategory category : caregoryList) {
                           PartnerJ1HSWorkQueueCategory cat = new PartnerJ1HSWorkQueueCategory();
                           cat.setPartnerWorkQueueCategoryName(category.getPartnerWQCategoryName());
                           PartnerWorkQueueCategoryAggregate catAgg = partnerWorkQueueCategoryAggregateRepository.getCategoryAggregate(pqType.getPartnerWQTypeId(),
                                 category.getPartnerWQCategoryId(), partner.getPartnerGoId(), CCIConstants.HSP_J1_HS_ID);
                           cat.setPartnerWorkQueueCategoryNo(catAgg.getPartnerWQCategoryAggregate());
                           cat.setPartnerWorkQueueCategoryUrl("TBD");
                           partnerWorkQueueCategories.add(cat);
                        }
                        j1wqType.getPartnerWorkQueueCategories().addAll(partnerWorkQueueCategories);
                     }
                     partnerWorkQueueTypesList.add(j1wqType);
                  }
               }
               j1hsDashboard.getPartnerWorkQueueTypes().addAll(partnerWorkQueueTypesList);

               // programs and allocations
               List<PartnerJ1HSProgram> partnerJ1HSProgramsList = null;
               List<PartnerSeason> partnerSeasonList = partner.getPartnerSeasons();
               if (partnerSeasonList != null) {
                  partnerJ1HSProgramsList = new ArrayList<PartnerJ1HSProgram>();
                  for (PartnerSeason partSeason : partnerSeasonList) {
                     if (partSeason.getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_J1_HS_ID) {
                        PartnerJ1HSProgram prg = new PartnerJ1HSProgram();
                        prg.setProgramName(partSeason.getSeason().getSeasonName() + " -" + CCIConstants.HSP_J1_HS);
                        prg.setApplicationDeadlineDate(DateUtils.getTimestamp(partSeason.getSeason().getSeasonJ1details().get(0).getFirstSemAppDeadlineDate()));
                        prg.setSecondSemDeadlineDate(DateUtils.getTimestamp(partSeason.getSeason().getSeasonJ1details().get(0).getSecondSemAppDeadlineDate()));
                        prg.setSeasonStatus(partSeason.getSeason().getSeasonStatus().getStatus());
                        List<PartnerSeasonAllocation> j1Allocations = partSeason.getPartnerSeasonAllocations();
                        if (j1Allocations != null) {
                           J1HSAllocation allocation = new J1HSAllocation();
                           int totalUnGuarant = 0;
                           int augStartUnGuarnteedParticipants = 0;
                           int janStartUnGuarnteedParticipants = 0;
                           int totalGurant = 0;
                           int augStartGuarnteedParticipants = 0;
                           int janStartGuarnteedParticipants = 0;
                           for (PartnerSeasonAllocation psa : j1Allocations) {
                              if (psa.getDepartmentProgramOption() != null) {
                                 if (psa.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_J1_HS_ID) {
                                    if (psa.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_J1)) {
                                       augStartUnGuarnteedParticipants = psa.getMaxPax() > 0 ? psa.getMaxPax() : 0;
                                       totalUnGuarant += augStartUnGuarnteedParticipants > 0 ? augStartUnGuarnteedParticipants : 0;
                                       augStartGuarnteedParticipants = psa.getMaxGuaranteedPax() > 0 ? psa.getMaxGuaranteedPax() : 0;
                                       totalGurant += augStartGuarnteedParticipants > 0 ? augStartGuarnteedParticipants : 0;

                                    }
                                    if (psa.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_J1)) {
                                       janStartUnGuarnteedParticipants = psa.getMaxPax() > 0 ? psa.getMaxPax() : 0;
                                       totalUnGuarant += janStartUnGuarnteedParticipants > 0 ? janStartUnGuarnteedParticipants : 0;
                                       janStartGuarnteedParticipants = psa.getMaxGuaranteedPax() > 0 ? psa.getMaxGuaranteedPax() : 0;
                                       totalGurant += janStartGuarnteedParticipants > 0 ? janStartGuarnteedParticipants : 0;
                                    }
                                 }
                              }
                           }
                           allocation.setAugStartUnguaranteed(augStartUnGuarnteedParticipants);
                           allocation.setJanStartUnguaranteed(janStartUnGuarnteedParticipants);
                           allocation.setTotalUnguaranteed(totalUnGuarant);
                           allocation.setAugStartGuaranteed(augStartGuarnteedParticipants);
                           allocation.setJanStartGuaranteed(janStartGuarnteedParticipants);
                           allocation.setTotalGuaranteed(totalGurant);
                           prg.setAllocation(allocation);
                        }
                        partnerJ1HSProgramsList.add(prg);
                     }
                  }
               }
               j1hsDashboard.getPartnerJ1HSPrograms().addAll(partnerJ1HSProgramsList);
            } else {
               j1hsDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_PROGRAM_DETAILS_FOUND.getValue(),
                     messageUtil.getMessage(PartnerDashboardMessageConstants.NO_PROGRAM_DETAILS_FOUND)));
               LOGGER.error(messageUtil.getMessage(PartnerDashboardMessageConstants.NO_PROGRAM_DETAILS_FOUND));
               return j1hsDashboard;
            }
         } catch (CcighgoException e) {
            j1hsDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_PARTNER_GET_J1_DETAILS.getValue(),
                  messageUtil.getMessage(PartnerDashboardMessageConstants.ERROR_PARTNER_GET_J1_DETAILS)));
            LOGGER.error(messageUtil.getMessage(PartnerDashboardMessageConstants.ERROR_PARTNER_GET_J1_DETAILS));
         }
      }
      return j1hsDashboard;
   }

   @Override
   public PartnerF1Dashboard getF1Dashboard(String partnerGoId) {
      PartnerF1Dashboard f1Dashboard = new PartnerF1Dashboard();
      if (partnerGoId == null || Integer.valueOf(partnerGoId) == 0 || Integer.valueOf(partnerGoId) < 0) {
         f1Dashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerDashboardMessageConstants.INVALID_PARTNER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerDashboardMessageConstants.INVALID_PARTNER_ID));
         return f1Dashboard;
      } else {
         try {
            Partner partner = partnerRepository.findOne(Integer.valueOf(partnerGoId));
            if (partner != null) {
               f1Dashboard.setPartnerGoId(partner.getPartnerGoId());
               f1Dashboard.setPartnerCompany(partner.getCompanyName());
               f1Dashboard.setPartnerLogo(partner.getPartnerLogo());

               // announcements
               List<PartnerAnnouncement> partnerAnnouncementList = partner.getPartnerAnnouncements();
               List<PartnerF1Announcement> partnerF1Announcements = null;
               if (partnerAnnouncementList != null && partnerAnnouncementList.size() > 0) {
                  partnerF1Announcements = new ArrayList<PartnerF1Announcement>();
                  for (PartnerAnnouncement ann : partnerAnnouncementList) {
                     PartnerF1Announcement f1Ann = new PartnerF1Announcement();
                     f1Ann.setAnnouncement(ann.getAnnouncement());
                     f1Ann.setTimestamp(DateUtils.getTimestamp(ann.getCreatedOn()));
                     partnerF1Announcements.add(f1Ann);
                  }
               }
               f1Dashboard.getPartnerAnnouncements().addAll(partnerF1Announcements);
               // cci contact
               PartnerCCIContact partnerCCIF1Contact = partnerCCIContactRepository.getCCIContactByDepartmentProgramId(partner.getPartnerGoId(), CCIConstants.HSP_F1_ID);
               PartnerF1CCIContact cciContact = null;
               if (partnerCCIF1Contact != null) {
                  cciContact = new PartnerF1CCIContact();
                  cciContact.setPartnerCCIContactName(partnerCCIF1Contact.getCcistaffUser().getFirstName() + " " + partnerCCIF1Contact.getCcistaffUser().getLastName());
                  cciContact.setPartnerProgramName(CCIConstants.HSP_F1 + " CCI Contact");
                  cciContact.setPartnerCCIContactDesignation(partnerCCIF1Contact.getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole().getCciStaffRoleName());
                  cciContact.setPartnerCCIContactImageUrl(partnerCCIF1Contact.getCcistaffUser().getPhoto());
                  cciContact.setPartnerCCIContactPhone(partnerCCIF1Contact.getCcistaffUser().getPrimaryPhone());
                  cciContact.setPartnerCCIContactExtentionNo(partnerCCIF1Contact.getCcistaffUser().getPhoneExtension());
               }
               f1Dashboard.setCciContact(cciContact);

               // work queue types and categories
               List<PartnerF1WorkQueueType> partnerWorkQueueTypesList = new ArrayList<PartnerF1WorkQueueType>();
               List<PartnerWorkQueueType> partnerWorkQueueTypes = partnerWorkQueueTypeRepository.getPartnerWorkQueueTypesByDepartmentProgramId(CCIConstants.HSP_F1_ID);
               if (partnerWorkQueueTypes != null) {
                  for (PartnerWorkQueueType pqType : partnerWorkQueueTypes) {
                     PartnerF1WorkQueueType f1wqType = new PartnerF1WorkQueueType();
                     f1wqType.setPartnerWorkQueueTypeName(pqType.getPartnerWQTypeName());
                     PartnerWorkQueueTypeAggregate typeAgg = partnerWorkQueueTypeAggregateRepository.getWorkQueueTypeAggregateByDepartmentProgramId(pqType.getPartnerWQTypeId(),
                           partner.getPartnerGoId(), CCIConstants.HSP_F1_ID);
                     f1wqType.setPartnerWorkQueueTypeNo(typeAgg.getPartnerWQTypeAggregate());
                     List<PartnerWorkQueueCategory> caregoryList = partnerWorkQueueCategoryRepository.getWorkQueueCategoryForType(pqType.getPartnerWQTypeId());
                     if (caregoryList != null) {
                        List<PartnerF1WorkQueueCategory> partnerWorkQueueCategories = new ArrayList<PartnerF1WorkQueueCategory>();
                        for (PartnerWorkQueueCategory category : caregoryList) {
                           PartnerF1WorkQueueCategory cat = new PartnerF1WorkQueueCategory();
                           cat.setPartnerWorkQueueCategoryName(category.getPartnerWQCategoryName());
                           PartnerWorkQueueCategoryAggregate catAgg = partnerWorkQueueCategoryAggregateRepository.getCategoryAggregate(pqType.getPartnerWQTypeId(),
                                 category.getPartnerWQCategoryId(), partner.getPartnerGoId(), CCIConstants.HSP_F1_ID);
                           cat.setPartnerWorkQueueCategoryNo(catAgg.getPartnerWQCategoryAggregate());
                           cat.setPartnerWorkQueueCategoryUrl("TBD");
                           partnerWorkQueueCategories.add(cat);
                        }
                        f1wqType.getPartnerWorkQueueCategories().addAll(partnerWorkQueueCategories);
                     }
                     partnerWorkQueueTypesList.add(f1wqType);
                  }
               }

               // programs and allocations
               List<PartnerF1Program> partnerF1ProgramsList = null;
               List<PartnerSeason> partnerSeasonList = partner.getPartnerSeasons();
               if (partnerSeasonList != null) {
                  partnerF1ProgramsList = new ArrayList<PartnerF1Program>();
                  for (PartnerSeason partSeason : partnerSeasonList) {
                     if (partSeason.getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_J1_HS_ID) {
                        PartnerF1Program prg = new PartnerF1Program();
                        prg.setProgramName(partSeason.getSeason().getSeasonName() + " -" + CCIConstants.HSP_J1_HS);
                        prg.setApplicationDeadlineDate(DateUtils.getTimestamp(partSeason.getSeason().getSeasonF1details().get(0).getFirstSemAppDeadlineDate()));
                        prg.setSecondSemDeadlineDate(DateUtils.getTimestamp(partSeason.getSeason().getSeasonF1details().get(0).getSecondSemAppDeadlineDate()));
                        prg.setSeasonStatus(partSeason.getSeason().getSeasonStatus().getStatus());
                        List<PartnerSeasonAllocation> f1Allocations = partSeason.getPartnerSeasonAllocations();
                        if (f1Allocations != null) {
                           F1Allocation allocation = new F1Allocation();
                           int totalGurant = 0;
                           int augStartGuarnteedParticipants = 0;
                           int janStartGuarnteedParticipants = 0;
                           for (PartnerSeasonAllocation psa : f1Allocations) {
                              if (psa.getDepartmentProgramOption() != null) {
                                 if (psa.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_J1_HS_ID) {
                                    if (psa.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_J1)) {
                                       augStartGuarnteedParticipants = psa.getMaxGuaranteedPax() > 0 ? psa.getMaxGuaranteedPax() : 0;
                                       totalGurant += augStartGuarnteedParticipants > 0 ? augStartGuarnteedParticipants : 0;

                                    }
                                    if (psa.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_J1)) {
                                       janStartGuarnteedParticipants = psa.getMaxGuaranteedPax() > 0 ? psa.getMaxGuaranteedPax() : 0;
                                       totalGurant += janStartGuarnteedParticipants > 0 ? janStartGuarnteedParticipants : 0;
                                    }
                                 }
                              }
                           }
                           allocation.setAugStartGuaranteed(augStartGuarnteedParticipants);
                           allocation.setJanStartGuaranteed(janStartGuarnteedParticipants);
                           allocation.setTotalGuaranteed(totalGurant);
                           prg.setAllocation(allocation);
                        }
                        partnerF1ProgramsList.add(prg);
                     }
                  }
               }
               f1Dashboard.getPartnerF1Programs().addAll(partnerF1ProgramsList);
            } else {
               f1Dashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_PROGRAM_DETAILS_FOUND.getValue(),
                     messageUtil.getMessage(PartnerDashboardMessageConstants.NO_PROGRAM_DETAILS_FOUND)));
               LOGGER.error(messageUtil.getMessage(PartnerDashboardMessageConstants.NO_PROGRAM_DETAILS_FOUND));
               return f1Dashboard;
            }
         } catch (CcighgoException e) {
            f1Dashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_PARTNER_GET_F1_DETAILS.getValue(),
                  messageUtil.getMessage(PartnerDashboardMessageConstants.ERROR_PARTNER_GET_F1_DETAILS)));
            LOGGER.error(messageUtil.getMessage(PartnerDashboardMessageConstants.ERROR_PARTNER_GET_F1_DETAILS));
         }
      }
      return f1Dashboard;
   }

   @Override
   public PartnerIHPDashboard getIHPDashboard(String partnerGoId) {
      PartnerIHPDashboard ihpDashboard = new PartnerIHPDashboard();
      if (partnerGoId == null || Integer.valueOf(partnerGoId) == 0 || Integer.valueOf(partnerGoId) < 0) {
         ihpDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerDashboardMessageConstants.INVALID_PARTNER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerDashboardMessageConstants.INVALID_PARTNER_ID));
         return ihpDashboard;
      } else {
         try {
            Partner partner = partnerRepository.findOne(Integer.valueOf(partnerGoId));
            if (partner != null) {
               ihpDashboard.setPartnerGoId(partner.getPartnerGoId());
               ihpDashboard.setPartnerCompany(partner.getCompanyName());
               ihpDashboard.setPartnerLogo(partner.getPartnerLogo());
               // announcements
               List<PartnerAnnouncement> partnerAnnouncementList = partner.getPartnerAnnouncements();
               List<PartnerIHPAnnouncement> partnerIHPAnnouncements = null;
               if (partnerAnnouncementList != null && partnerAnnouncementList.size() > 0) {
                  partnerIHPAnnouncements = new ArrayList<PartnerIHPAnnouncement>();
                  for (PartnerAnnouncement ann : partnerAnnouncementList) {
                     PartnerIHPAnnouncement f1Ann = new PartnerIHPAnnouncement();
                     f1Ann.setAnnouncement(ann.getAnnouncement());
                     f1Ann.setTimestamp(DateUtils.getTimestamp(ann.getCreatedOn()));
                     partnerIHPAnnouncements.add(f1Ann);
                  }
               }
               ihpDashboard.getPartnerAnnouncements().addAll(partnerIHPAnnouncements);
               PartnerCCIContact partnerCCIIHPContact = partnerCCIContactRepository.getCCIContactByDepartmentProgramId(partner.getPartnerGoId(), CCIConstants.HSP_STP_IHP_ID);
               PartnerIHPCCIContact cciContact = null;
               if (partnerCCIIHPContact != null) {
                  cciContact = new PartnerIHPCCIContact();
                  cciContact.setPartnerCCIContactName(partnerCCIIHPContact.getCcistaffUser().getFirstName() + " " + partnerCCIIHPContact.getCcistaffUser().getLastName());
                  cciContact.setPartnerProgramName(CCIConstants.HSP_STP_IHP + " CCI Contact");
                  cciContact.setPartnerCCIContactDesignation(partnerCCIIHPContact.getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole().getCciStaffRoleName());
                  cciContact.setPartnerCCIContactImageUrl(partnerCCIIHPContact.getCcistaffUser().getPhoto());
                  cciContact.setPartnerCCIContactPhone(partnerCCIIHPContact.getCcistaffUser().getPrimaryPhone());
                  cciContact.setPartnerCCIContactExtentionNo(partnerCCIIHPContact.getCcistaffUser().getPhoneExtension());
               }
               ihpDashboard.setCciContact(cciContact);

               // work queue types and categories
               List<PartnerIHPWorkQueueType> partnerWorkQueueTypesList = new ArrayList<PartnerIHPWorkQueueType>();
               List<PartnerWorkQueueType> partnerWorkQueueTypes = partnerWorkQueueTypeRepository.getPartnerWorkQueueTypesByDepartmentProgramId(CCIConstants.HSP_STP_IHP_ID);
               if (partnerWorkQueueTypes != null) {
                  for (PartnerWorkQueueType pqType : partnerWorkQueueTypes) {
                     PartnerIHPWorkQueueType ihpWqType = new PartnerIHPWorkQueueType();
                     ihpWqType.setPartnerWorkQueueTypeName(pqType.getPartnerWQTypeName());
                     PartnerWorkQueueTypeAggregate typeAgg = partnerWorkQueueTypeAggregateRepository.getWorkQueueTypeAggregateByDepartmentProgramId(pqType.getPartnerWQTypeId(),
                           partner.getPartnerGoId(), CCIConstants.HSP_STP_IHP_ID);
                     ihpWqType.setPartnerWorkQueueTypeNo(typeAgg.getPartnerWQTypeAggregate());
                     List<PartnerWorkQueueCategory> caregoryList = partnerWorkQueueCategoryRepository.getWorkQueueCategoryForType(pqType.getPartnerWQTypeId());
                     if (caregoryList != null) {
                        List<PartnerIHPWorkQueueCategory> partnerWorkQueueCategories = new ArrayList<PartnerIHPWorkQueueCategory>();
                        for (PartnerWorkQueueCategory category : caregoryList) {
                           PartnerIHPWorkQueueCategory cat = new PartnerIHPWorkQueueCategory();
                           cat.setPartnerWorkQueueCategoryName(category.getPartnerWQCategoryName());
                           PartnerWorkQueueCategoryAggregate catAgg = partnerWorkQueueCategoryAggregateRepository.getCategoryAggregate(pqType.getPartnerWQTypeId(),
                                 category.getPartnerWQCategoryId(), partner.getPartnerGoId(), CCIConstants.HSP_STP_IHP_ID);
                           cat.setPartnerWorkQueueCategoryNo(catAgg.getPartnerWQCategoryAggregate());
                           cat.setPartnerWorkQueueCategoryUrl("TBD");
                           partnerWorkQueueCategories.add(cat);
                        }
                        ihpWqType.getPartnerWorkQueueCategories().addAll(partnerWorkQueueCategories);
                     }
                     partnerWorkQueueTypesList.add(ihpWqType);
                  }
               }

               // programs
               List<PartnerIHPProgram> partnerIHPProgramsList = null;
               List<PartnerSeason> partnerSeasonList = partner.getPartnerSeasons();
               if (partnerSeasonList != null) {
                  partnerIHPProgramsList = new ArrayList<PartnerIHPProgram>();
                  for (PartnerSeason partSeason : partnerSeasonList) {
                     if (partSeason.getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_STP_IHP_ID) {
                        PartnerIHPProgram prg = new PartnerIHPProgram();
                        prg.setProgramName(partSeason.getSeason().getSeasonName() + " - IHP");
                        prg.setSeasonStatus(partSeason.getSeason().getSeasonStatus().getStatus());
                        partnerIHPProgramsList.add(prg);
                     }
                  }
               }
            } else {
               ihpDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_PROGRAM_DETAILS_FOUND.getValue(),
                     messageUtil.getMessage(PartnerDashboardMessageConstants.NO_PROGRAM_DETAILS_FOUND)));
               LOGGER.error(messageUtil.getMessage(PartnerDashboardMessageConstants.NO_PROGRAM_DETAILS_FOUND));
               return ihpDashboard;
            }
         } catch (CcighgoException e) {
            ihpDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_PARTNER_GET_IHP_DETAILS.getValue(),
                  messageUtil.getMessage(PartnerDashboardMessageConstants.ERROR_PARTNER_GET_IHP_DETAILS)));
            LOGGER.error(messageUtil.getMessage(PartnerDashboardMessageConstants.ERROR_PARTNER_GET_IHP_DETAILS));
         }
      }
      return ihpDashboard;
   }

   @Override
   public PartnerCAPDashboard getWnTDashboard(String partnerGoId) {
      PartnerCAPDashboard wntDashboard = new PartnerCAPDashboard();
      if (partnerGoId == null || Integer.valueOf(partnerGoId) == 0 || Integer.valueOf(partnerGoId) < 0) {
         wntDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerDashboardMessageConstants.INVALID_PARTNER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerDashboardMessageConstants.INVALID_PARTNER_ID));
         return wntDashboard;
      } else {

      }
      return wntDashboard;
   }

   @Override
   public PartnerWnTDashboard getCAPDashboard(String partnerGoId) {
      PartnerWnTDashboard capDashboard = new PartnerWnTDashboard();
      if (partnerGoId == null || Integer.valueOf(partnerGoId) == 0 || Integer.valueOf(partnerGoId) < 0) {
         capDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerDashboardMessageConstants.INVALID_PARTNER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerDashboardMessageConstants.INVALID_PARTNER_ID));
         return capDashboard;
      } else {

      }
      return capDashboard;
   }

   @Override
   public PartnerAdminDashboard getDashboard() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public PartnerWorkQueueSubmittedApplicationsDetail changePartnerApplicationStatus(int partnerAgentInquiryId) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public PartnerRecruitmentAdmin getAgentRecruitmentData(int partnerGoId) {
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
            PartnerRecruitmentAdminScreeningDetail partnerRecruitmentAdminScreeningDetail = new PartnerRecruitmentAdminScreeningDetail();
            if (partnerAgentInquiry.getCompanyName() != null)
               partnerRecruitmentAdminScreeningDetail.setCompanyName(partnerAgentInquiry.getCompanyName());
            if (partnerAgentInquiry.getLookupCountry() != null)
               partnerRecruitmentAdminScreeningDetail.setCountry(partnerAgentInquiry.getLookupCountry().getCountryName());
            if (partnerAgentInquiry.getEmail() != null)
               partnerRecruitmentAdminScreeningDetail.setEmail(partnerAgentInquiry.getEmail());
            if (partnerAgentInquiry.getFirstName() != null)
               partnerRecruitmentAdminScreeningDetail.setFirstName(partnerAgentInquiry.getFirstName());
            if (partnerAgentInquiry.getLastName() != null)
               partnerRecruitmentAdminScreeningDetail.setLastName(partnerAgentInquiry.getLastName());
            if (partnerAgentInquiry.getPhone() != null)
               partnerRecruitmentAdminScreeningDetail.setPhone(partnerAgentInquiry.getPhone());
            if (partnerAgentInquiry.getWebsite() != null)
               partnerRecruitmentAdminScreeningDetail.setWebsite(partnerAgentInquiry.getWebsite());
            if (partnerAgentInquiry.getAdressLineOne() != null)
               partnerRecruitmentAdminScreeningDetail.setAddress1(partnerAgentInquiry.getAdressLineOne());
            if (partnerAgentInquiry.getAdressLineTwo() != null)
               partnerRecruitmentAdminScreeningDetail.setAddress2(partnerAgentInquiry.getAdressLineTwo());
            if (partnerAgentInquiry.getCity() != null)
               partnerRecruitmentAdminScreeningDetail.setCity(partnerAgentInquiry.getCity());
            // Rating value is static ?????
            partnerRecruitmentAdminScreeningDetail.setRating(0);
            // if(partnerAgentInquiry.getSalutation()!=null)
            // partnerRecruitmentAdminScreeningDetail.setSalutation(partnerAgentInquiry.getSalutation());
            if (partnerAgentInquiry.getState() != null)
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
                  // partnerRecruitmentAdminScreeningPrograms.setProgramName(partnerProgram.getLookupDepartmentProgram1().getProgramName());
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
   public com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.PartnerWorkQueueType getWorkQueueType(int partnerGoId) {
      com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.PartnerWorkQueueType pwt = new com.ccighgo.service.transport.partner.beans.partnerworkqueuetype.PartnerWorkQueueType();
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
   public com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.PartnerWorkQueueCategory getWorkQueueCategory(int partnerGoId) {
      com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.PartnerWorkQueueCategory pwqc = new com.ccighgo.service.transport.partner.beans.partnerworkqueuecategory.PartnerWorkQueueCategory();
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
   public PartnerWorkQueueSubmittedApplications getWorkQueueSubmittedApplications(int partnerAgentGoId) {
      PartnerWorkQueueSubmittedApplications pwqa = new PartnerWorkQueueSubmittedApplications();
      try {
         List<PartnerAgentInquiry> result = partnerAgentInquiryRepository.findPartnerByPartnerId(partnerAgentGoId);
         if (result != null) {
            for (PartnerAgentInquiry partnerAgentInquiry : result) {
               PartnerWorkQueueSubmittedApplicationsDetail pd = new PartnerWorkQueueSubmittedApplicationsDetail();
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
               // PartnerReviewStatus partnerReviewStatus
               // =partnerReviewStatusRepository.findOne(partnerAgentInquiry.getPartner().getpartnerre));
               // ???????????????? will be changes once i have clarification
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
   public PartnerWorkQueueSubmittedApplicationsDetail updatePartnerApplicationFollowUpDate(int partnerAgentInquiryId, String newFollowUpDate) {
      PartnerWorkQueueSubmittedApplicationsDetail pd = new PartnerWorkQueueSubmittedApplicationsDetail();
      try {
         PartnerAgentInquiry partnerAgentInquiry = partnerAgentInquiryRepository.findOne(partnerAgentInquiryId);
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
}
