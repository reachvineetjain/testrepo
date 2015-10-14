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
import com.ccighgo.db.entities.PartnerCCIContact;
import com.ccighgo.db.entities.PartnerSeason;
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
import com.ccighgo.jpa.repositories.PartnerWorkQueueCategoryPKRepository;
import com.ccighgo.jpa.repositories.PartnerWorkQueueCategoryRepository;
import com.ccighgo.jpa.repositories.PartnerWorkQueueRepository;
import com.ccighgo.jpa.repositories.PartnerWorkQueueTypeAggregateRepository;
import com.ccighgo.jpa.repositories.PartnerWorkQueueTypeRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerDashboardMessageConstants;
import com.ccighgo.service.components.errormessages.constants.RegionManagementMessageConstants;
import com.ccighgo.service.transport.partner.beans.partnercapdetails.PartnerCAPDashboard;
import com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerDashboard;
import com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerF1Dashboard;
import com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerIHPDashboard;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSAnnouncement;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSCCIContact;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSDashboard;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSProgram;
import com.ccighgo.service.transport.partner.beans.partnerj1details.PartnerJ1HSWorkQueueType;
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
   @Autowired PartnerCCIContactRepository partnerCCIContactRepository;
   @Autowired PartnerWorkQueueRepository partnerWorkQueueRepository;
   @Autowired PartnerWorkQueueTypeRepository partnerWorkQueueTypeRepository;
   @Autowired PartnerWorkQueueTypeAggregateRepository partnerWorkQueueTypeAggregateRepository;
   @Autowired PartnerWorkQueueCategoryRepository partnerWorkQueueCategoryRepository;
   @Autowired PartnerWorkQueueCategoryAggregateRepository partnerWorkQueueCategoryAggregateRepository;
  // @Autowired PartnerWorkQueueCategoryPKRepository partnerWorkQueueCategoryPKRepository;
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
               partnerDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SUP_REG_LIST.getValue(),
                     messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST)));
               LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST));
            }
         } catch (CcighgoException e) {
            // error getting partner details
            partnerDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SUP_REG_LIST.getValue(),
                  messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST)));
            LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST));
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
         Partner partner = partnerRepository.findOne(Integer.valueOf(partnerGoId));
         if (partner != null) {
            j1hsDashboard.setPartnerGoId(partner.getPartnerGoId());
            j1hsDashboard.setPartnerCompany(partner.getCompanyName());
            j1hsDashboard.setPartnerLogo(partner.getPartnerLogo());
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
            List<PartnerJ1HSWorkQueueType> partnerWorkQueueTypesList = new ArrayList<PartnerJ1HSWorkQueueType>();

            List<PartnerJ1HSProgram> partnerJ1HSProgramsList = null;

            List<PartnerSeason> partnerSeasonList = partner.getPartnerSeasons();
            if (partnerSeasonList != null) {
               partnerJ1HSProgramsList = new ArrayList<PartnerJ1HSProgram>();
            }
         } else {
            // no details found
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
