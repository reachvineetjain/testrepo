/**
 * 
 */
package com.ccighgo.service.components.partner;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.LookupDepartmentProgram;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerAnnouncement;
import com.ccighgo.db.entities.PartnerProgram;
import com.ccighgo.db.entities.PartnerQuickStatsCategoryAggregate;
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
import com.ccighgo.jpa.repositories.PartnerContactRepository;
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
import com.ccighgo.service.components.errormessages.constants.PartnerDashboardMessageConstants;
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
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerApplicationStats;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSAnnouncement;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSCCIContact;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSDashboard;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSProgram;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSWorkQueueCategory;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSWorkQueueType;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerProgramStats;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerStatistics;
import com.ccighgo.service.transport.partner.beans.partnerwntdetails.PartnerWnTDashboard;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;

/**
 * @author ravi
 *
 */
@Component
public class PartnerServiceImpl implements PartnerService {

   private static final Logger LOGGER = Logger.getLogger(PartnerServiceImpl.class);

   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;

   @Autowired PartnerRepository partnerRepository;
   @Autowired LookupDepartmentProgramRepository lookupDepartmentProgramRepository;
   @Autowired PartnerContactRepository partnerContactRepository;
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
                List<PartnerUser> partnerUsers = partner.getPartnerUsers();
               for(PartnerUser pu:partnerUsers){
                  if(partner.getPartnerGoId()==pu.getPartner().getPartnerGoId() && pu.getIsPrimary()==CCIConstants.ACTIVE){
                     partnerDashboard.setPartnerPhotoUrl(pu.getPhoto());
                  }
               }
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
                           prg.setPartnerDepartmentProgramName("J1HS");
                           prg.setProgramDetailsUrl("partner/j1/program/details/");
                        }
                        if (deptPrg.getProgramName().equals(CCIConstants.HSP_F1) && deptPrg.getLookupDepartmentProgramId() == CCIConstants.HSP_F1_ID) {
                           prg.setPartnerDepartmentProgramId(CCIConstants.HSP_F1_ID);
                           prg.setPartnerDepartmentProgramName("F1");
                           prg.setProgramDetailsUrl("partner/f1/program/details/");
                        }
                        if (deptPrg.getProgramName().equals(CCIConstants.HSP_STP_IHP) && deptPrg.getLookupDepartmentProgramId() == CCIConstants.HSP_STP_IHP_ID) {
                           prg.setPartnerDepartmentProgramId(CCIConstants.HSP_STP_IHP_ID);
                           prg.setPartnerDepartmentProgramName("IHP");
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

               // partner j1 cci contact
               PartnerProgram partnerCCIJ1Contact = partnerProgramRepository.findByPartnerIdAndDepartmentProgramId(partner.getPartnerGoId(), CCIConstants.HSP_J1_HS_ID);
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

               // Statistics
               PartnerStatistics partnerStatistics = null;
               List<PartnerQuickStatsCategoryAggregate> partnerStatsDetails = partnerQuickStatsCategoryAggregateRepository.getStats(CCIConstants.APPL_J1, partner.getPartnerGoId(),
                     CCIConstants.HSP_J1_HS_ID);
               if (partnerStatsDetails != null && partnerStatsDetails.size() > 0) {
                  partnerStatistics = new PartnerStatistics();
                  PartnerApplicationStats applicationStats = new PartnerApplicationStats();
                  PartnerProgramStats programStats = new PartnerProgramStats();
                  for (PartnerQuickStatsCategoryAggregate categoryAggregate : partnerStatsDetails) {
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.SUBMITTED)) {
                        applicationStats.setSubmitted(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.PARTNER_REVIEW)) {
                        applicationStats.setPartnerReview(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.GREENHEART_REVIEW)) {
                        applicationStats.setGreenheartReview(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.APPROVED)) {
                        applicationStats.setApproved(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.NOT_APPROVED)) {
                        applicationStats.setNotApproved(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                  }
                  partnerStatistics.setApplicationStats(applicationStats);
                  partnerStatistics.setProgramStats(programStats);
               }
               j1hsDashboard.setPartnerStatistics(partnerStatistics);

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

               // partner f1 cci contact
               PartnerProgram partnerCCIF1Contact = partnerProgramRepository.findByPartnerIdAndDepartmentProgramId(partner.getPartnerGoId(), CCIConstants.HSP_J1_HS_ID);
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

               // Statistics
               com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerStatistics partnerStatistics = null;
               List<PartnerQuickStatsCategoryAggregate> partnerStatsDetails = partnerQuickStatsCategoryAggregateRepository.getStats(CCIConstants.APPL_F1, partner.getPartnerGoId(),
                     CCIConstants.HSP_F1_ID);
               if (partnerStatsDetails != null && partnerStatsDetails.size() > 0) {
                  partnerStatistics = new com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerStatistics();
                  com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerApplicationStats applicationStats = new com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerApplicationStats();
                  com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerProgramStats programStats = new com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerProgramStats();
                  for (PartnerQuickStatsCategoryAggregate categoryAggregate : partnerStatsDetails) {
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.SUBMITTED)) {
                        applicationStats.setSubmitted(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.PARTNER_REVIEW)) {
                        applicationStats.setPartnerReview(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.GREENHEART_REVIEW)) {
                        applicationStats.setGreenheartReview(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.APPROVED)) {
                        applicationStats.setApproved(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.NOT_APPROVED)) {
                        applicationStats.setNotApproved(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                  }
                  partnerStatistics.setApplicationStats(applicationStats);
                  partnerStatistics.setProgramStats(programStats);
               }
               f1Dashboard.setPartnerStatistics(partnerStatistics);

               // programs and allocations
               List<PartnerF1Program> partnerF1ProgramsList = null;
               List<PartnerSeason> partnerSeasonList = partner.getPartnerSeasons();
               if (partnerSeasonList != null) {
                  partnerF1ProgramsList = new ArrayList<PartnerF1Program>();
                  for (PartnerSeason partSeason : partnerSeasonList) {
                     if (partSeason.getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_F1_ID) {
                        PartnerF1Program prg = new PartnerF1Program();
                        prg.setProgramName(partSeason.getSeason().getSeasonName() + " -" + CCIConstants.HSP_F1);
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
                                 if (psa.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_F1_ID) {
                                    if (psa.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_F1)) {
                                       augStartGuarnteedParticipants = psa.getMaxGuaranteedPax() > 0 ? psa.getMaxGuaranteedPax() : 0;
                                       totalGurant += augStartGuarnteedParticipants > 0 ? augStartGuarnteedParticipants : 0;

                                    }
                                    if (psa.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_F1)) {
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

               // partner ihp cci contact
               PartnerProgram partnerCCIIHPContact = partnerProgramRepository.findByPartnerIdAndDepartmentProgramId(partner.getPartnerGoId(), CCIConstants.HSP_STP_IHP_ID);
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

               // Statistics
               com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerStatistics partnerStatistics = null;
               List<PartnerQuickStatsCategoryAggregate> partnerStatsDetails = partnerQuickStatsCategoryAggregateRepository.getStats(CCIConstants.APPL_IHP,
                     partner.getPartnerGoId(), CCIConstants.HSP_STP_IHP_ID);
               if (partnerStatsDetails != null && partnerStatsDetails.size() > 0) {
                  partnerStatistics = new com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerStatistics();
                  com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerApplicationStats applicationStats = new com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerApplicationStats();
                  com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerProgramStats programStats = new com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerProgramStats();
                  for (PartnerQuickStatsCategoryAggregate categoryAggregate : partnerStatsDetails) {
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.SUBMITTED)) {
                        applicationStats.setSubmitted(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.PARTNER_REVIEW)) {
                        applicationStats.setPartnerReview(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.GREENHEART_REVIEW)) {
                        applicationStats.setGreenheartReview(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.APPROVED)) {
                        applicationStats.setApproved(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.NOT_APPROVED)) {
                        applicationStats.setNotApproved(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                  }
                  partnerStatistics.setApplicationStats(applicationStats);
                  partnerStatistics.setProgramStats(programStats);
               }
               ihpDashboard.setPartnerStatistics(partnerStatistics);

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

}
