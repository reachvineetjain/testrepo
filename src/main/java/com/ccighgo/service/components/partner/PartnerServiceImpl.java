package com.ccighgo.service.components.partner;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerAgentInquiry;
import com.ccighgo.db.entities.PartnerAnnouncement;
import com.ccighgo.db.entities.PartnerPermission;
import com.ccighgo.db.entities.PartnerProgram;
import com.ccighgo.db.entities.PartnerQuickStatsCategoryAggregate;
import com.ccighgo.db.entities.PartnerReviewStatus;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerSeasonAllocation;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.db.entities.PartnerWorkQueueCategory;
import com.ccighgo.db.entities.PartnerWorkQueueCategoryAggregate;
import com.ccighgo.db.entities.PartnerWorkQueueType;
import com.ccighgo.db.entities.PartnerWorkQueueTypeAggregate;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.LookupDepartmentProgramRepository;
import com.ccighgo.jpa.repositories.ParticipantRepository;
import com.ccighgo.jpa.repositories.PartnerAgentInquiryRepository;
import com.ccighgo.jpa.repositories.PartnerDocumentsRepository;
import com.ccighgo.jpa.repositories.PartnerMessagesRepository;
import com.ccighgo.jpa.repositories.PartnerNoteRepository;
import com.ccighgo.jpa.repositories.PartnerOfficeRepository;
import com.ccighgo.jpa.repositories.PartnerPermissionRepository;
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
import com.ccighgo.service.transport.integration.thirdparty.beans.partnerLeadViewForPartnerInquiryData.PartnerRecruitmentLead;
import com.ccighgo.service.transport.integration.thirdparty.beans.partnerLeadViewForPartnerInquiryData.PartnerRecruitmentLeadScreeningDetail;
import com.ccighgo.service.transport.partner.beans.partnercapdetails.PartnerCAPDashboard;
import com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerDashboard;
import com.ccighgo.service.transport.partner.beans.partnerdashboard.Permissions;
import com.ccighgo.service.transport.partner.beans.partnerdashboard.Programs;
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
import com.ccighgo.utils.ExceptionUtil;

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
   @Autowired PartnerPermissionRepository partnerPermissionRepository;
   @Autowired ParticipantRepository participantRepository;

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
            PartnerUser partnerUser = null;
            if (partner != null) {
               partnerDashboard.setPartnerId(partner.getPartnerGoId());
               partnerDashboard.setPartnerCompany(partner.getCompanyName());
               partnerDashboard.setPartnerCompanyLogo(partner.getPartnerLogo());
               partnerDashboard.setIsSubpartner(partner.getIsSubPartner().equals(CCIConstants.ACTIVE) ? true : false);
               if (partner.getCanHaveSubPartner() != null) {
                  partnerDashboard.setCanHaveSubpartners(partner.getCanHaveSubPartner().equals(CCIConstants.ACTIVE) ? true : false);
               }
               List<PartnerUser> partnerUsers = partner.getPartnerUsers();
               for (PartnerUser pu : partnerUsers) {
                  if (partner.getPartnerGoId() == pu.getPartner().getPartnerGoId() && pu.getIsPrimary() == CCIConstants.ACTIVE) {
                     partnerUser = pu;
                     partnerDashboard.setFirstName(partnerUser.getFirstName());
                     partnerDashboard.setLastName(partnerUser.getLastName());
                     partnerDashboard.setUsername(partnerUser.getLogin().getLoginName());
                     partnerDashboard.setPartnerEmail(partnerUser.getLogin().getEmail());
                     partnerDashboard.setPhotoPath(partnerUser.getPhoto());
                     break;
                  }
               }
               List<PartnerSeason> partnerSeasons = partner.getPartnerSeasons();
               if (partnerSeasons != null && partnerSeasons.size() > 0) {
                  List<com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerProgram> partnerProgramsList = new ArrayList<com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerProgram>();
                  for (PartnerSeason partnerSeason : partnerSeasons) {
                     com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerProgram prg = new com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerProgram();
                     prg.setPartnerSeasonId(partnerSeason.getSeason().getSeasonId());
                     if (partnerSeason.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_J1_HS)
                           && partnerSeason.getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_J1_HS_ID) {
                        prg.setPartnerDepartmentProgramId(CCIConstants.HSP_J1_HS_ID);
                        prg.setPartnerDepartmentProgramName("J1HS");
                        prg.setProgramDetailsUrl("partner/j1/program/details/");
                     }
                     if (partnerSeason.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_F1)
                           && partnerSeason.getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_F1_ID) {
                        prg.setPartnerDepartmentProgramId(CCIConstants.HSP_F1_ID);
                        prg.setPartnerDepartmentProgramName("F1");
                        prg.setProgramDetailsUrl("partner/f1/program/details/");
                     }
                     if (partnerSeason.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_STP_IHP)
                           && partnerSeason.getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_STP_IHP_ID) {
                        prg.setPartnerDepartmentProgramId(CCIConstants.HSP_STP_IHP_ID);
                        prg.setPartnerDepartmentProgramName("IHP");
                        prg.setProgramDetailsUrl("partner/ihp/program/details/");
                     }
                     if (partnerSeason.getDepartmentProgram().getProgramName().equals(CCIConstants.WP_WT_CAP)
                           && partnerSeason.getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_CAP_ID) {
                        prg.setPartnerDepartmentProgramId(CCIConstants.WP_WT_CAP_ID);
                        prg.setPartnerDepartmentProgramName(CCIConstants.WP_WT_CAP);
                        prg.setProgramDetailsUrl("partner/cap/program/details/");
                     }
                     if (partnerSeason.getDepartmentProgram().getProgramName().equals(CCIConstants.WP_WT_SUMMER)
                           && partnerSeason.getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_SUMMER_ID) {
                        prg.setPartnerDepartmentProgramId(CCIConstants.WP_WT_SUMMER_ID);
                        prg.setPartnerDepartmentProgramName(CCIConstants.WP_WT_SUMMER);
                        prg.setProgramDetailsUrl("partner/wnt/summer/program/details/");
                     }
                     if (partnerSeason.getDepartmentProgram().getProgramName().equals(CCIConstants.WP_WT_WINTER)
                           && partnerSeason.getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_WINTER_ID) {
                        prg.setPartnerDepartmentProgramId(CCIConstants.WP_WT_WINTER_ID);
                        prg.setPartnerDepartmentProgramName(CCIConstants.WP_WT_WINTER);
                        prg.setProgramDetailsUrl("partner/wnt/winter/program/details/");
                     }
                     partnerProgramsList.add(prg);
                  }
                  partnerDashboard.getPartnerPrograms().addAll(partnerProgramsList);

                  List<Programs> userProgramsAndPermissions = new ArrayList<Programs>();
                  PartnerPermission partnerPermission = partnerPermissionRepository.findByPartnerUserId(partnerUser.getPartnerUserId());
                  if (partnerPermission != null) {
                     Programs j1Program = new Programs();
                     j1Program.setProgramName("J1HS");
                     Permissions j1Permissions = new Permissions();
                     j1Permissions.setAccounting(partnerPermission.getJ1AccountingInsurance() == CCIConstants.ACTIVE ? true : false);
                     j1Permissions.setAdmin(partnerPermission.getJ1Admin() == CCIConstants.ACTIVE ? true : false);
                     j1Permissions.setApplications(partnerPermission.getJ1Applications() == CCIConstants.ACTIVE ? true : false);
                     j1Permissions.setContracting(partnerPermission.getJ1Contracting() == CCIConstants.ACTIVE ? true : false);
                     j1Permissions.setFlights(partnerPermission.getJ1Flights() == CCIConstants.ACTIVE ? true : false);
                     j1Permissions.setInsurance(partnerPermission.getJ1Insurance() == CCIConstants.ACTIVE ? true : false);
                     j1Permissions.setMonitoring(partnerPermission.getJ1Monitoring() == CCIConstants.ACTIVE ? true : false);
                     j1Permissions.setPlacementInfo(partnerPermission.getJ1PlacementInfo() == CCIConstants.ACTIVE ? true : false);
                     j1Permissions.setStudentsPreProgram(partnerPermission.getJ1StudentsPreProgram() == CCIConstants.ACTIVE ? true : false);
                     j1Program.setPermissions(j1Permissions);
                     userProgramsAndPermissions.add(j1Program);

                     Programs f1Program = new Programs();
                     f1Program.setProgramName(CCIConstants.HSP_F1);
                     Permissions f1Permissions = new Permissions();
                     f1Permissions.setAccounting(partnerPermission.getF1AccountingInsurance() == CCIConstants.ACTIVE ? true : false);
                     f1Permissions.setAdmin(partnerPermission.getF1Admin() == CCIConstants.ACTIVE ? true : false);
                     f1Permissions.setApplications(partnerPermission.getF1Applications() == CCIConstants.ACTIVE ? true : false);
                     f1Permissions.setContracting(partnerPermission.getF1Contracting() == CCIConstants.ACTIVE ? true : false);
                     f1Permissions.setFlights(partnerPermission.getF1Flights() == CCIConstants.ACTIVE ? true : false);
                     f1Permissions.setInsurance(partnerPermission.getF1Insurance() == CCIConstants.ACTIVE ? true : false);
                     f1Permissions.setMonitoring(partnerPermission.getF1Monitoring() == CCIConstants.ACTIVE ? true : false);
                     f1Permissions.setPlacementInfo(partnerPermission.getF1PlacementInfo() == CCIConstants.ACTIVE ? true : false);
                     f1Permissions.setStudentsPreProgram(partnerPermission.getF1StudentsPreProgram() == CCIConstants.ACTIVE ? true : false);
                     f1Program.setPermissions(f1Permissions);
                     userProgramsAndPermissions.add(f1Program);

                     Programs ihpProgram = new Programs();
                     ihpProgram.setProgramName("IHP");
                     Permissions ihpPermissions = new Permissions();
                     ihpPermissions.setAccounting(partnerPermission.getIhpAccountingInsurance() == CCIConstants.ACTIVE ? true : false);
                     ihpPermissions.setAdmin(partnerPermission.getIhpAdmin() == CCIConstants.ACTIVE ? true : false);
                     ihpPermissions.setApplications(partnerPermission.getIhpApplications() == CCIConstants.ACTIVE ? true : false);
                     ihpPermissions.setContracting(partnerPermission.getIhpContracting() == CCIConstants.ACTIVE ? true : false);
                     ihpPermissions.setFlights(partnerPermission.getIhpFlights() == CCIConstants.ACTIVE ? true : false);
                     ihpPermissions.setInsurance(partnerPermission.getIhpInsurance() == CCIConstants.ACTIVE ? true : false);
                     ihpPermissions.setMonitoring(partnerPermission.getIhpMonitoring() == CCIConstants.ACTIVE ? true : false);
                     ihpPermissions.setPlacementInfo(partnerPermission.getIhpPlacementInfo() == CCIConstants.ACTIVE ? true : false);
                     ihpPermissions.setStudentsPreProgram(partnerPermission.getIhpStudentsPreProgram() == CCIConstants.ACTIVE ? true : false);
                     ihpProgram.setPermissions(ihpPermissions);
                     userProgramsAndPermissions.add(ihpProgram);

                     Programs wntProgram = new Programs();
                     wntProgram.setProgramName("W&T");
                     Permissions wntPermissions = new Permissions();
                     wntPermissions.setAccounting(partnerPermission.getWtAccountingInsurance() == CCIConstants.ACTIVE ? true : false);
                     wntPermissions.setAdmin(partnerPermission.getWtAdmin() == CCIConstants.ACTIVE ? true : false);
                     wntPermissions.setApplications(partnerPermission.getWtApplications() == CCIConstants.ACTIVE ? true : false);
                     wntPermissions.setContracting(partnerPermission.getWtContracting() == CCIConstants.ACTIVE ? true : false);
                     wntPermissions.setFlights(partnerPermission.getWtFlights() == CCIConstants.ACTIVE ? true : false);
                     wntPermissions.setInsurance(partnerPermission.getWtInsurance() == CCIConstants.ACTIVE ? true : false);
                     wntPermissions.setMonitoring(partnerPermission.getWtMonitoring() == CCIConstants.ACTIVE ? true : false);
                     wntPermissions.setPlacementInfo(partnerPermission.getWtPlacementInfo() == CCIConstants.ACTIVE ? true : false);
                     wntPermissions.setStudentsPreProgram(partnerPermission.getWtStudentsPreProgram() == CCIConstants.ACTIVE ? true : false);
                     wntProgram.setPermissions(wntPermissions);
                     userProgramsAndPermissions.add(wntProgram);

                     Programs capProgram = new Programs();
                     capProgram.setProgramName(CCIConstants.WP_WT_CAP);
                     Permissions capPermissions = new Permissions();
                     capPermissions.setAccounting(partnerPermission.getCapAccountingInsurance() == CCIConstants.ACTIVE ? true : false);
                     capPermissions.setAdmin(partnerPermission.getCapAdmin() == CCIConstants.ACTIVE ? true : false);
                     capPermissions.setApplications(partnerPermission.getCapApplications() == CCIConstants.ACTIVE ? true : false);
                     capPermissions.setContracting(partnerPermission.getCapContracting() == CCIConstants.ACTIVE ? true : false);
                     capPermissions.setFlights(partnerPermission.getCapFlights() == CCIConstants.ACTIVE ? true : false);
                     capPermissions.setInsurance(partnerPermission.getCapInsurance() == CCIConstants.ACTIVE ? true : false);
                     capPermissions.setMonitoring(partnerPermission.getCapMonitoring() == CCIConstants.ACTIVE ? true : false);
                     capPermissions.setPlacementInfo(partnerPermission.getCapPlacementInfo() == CCIConstants.ACTIVE ? true : false);
                     capPermissions.setStudentsPreProgram(partnerPermission.getCapStudentsPreProgram() == CCIConstants.ACTIVE ? true : false);
                     capProgram.setPermissions(capPermissions);
                     userProgramsAndPermissions.add(capProgram);
                  }
                  partnerDashboard.getUserProgramsAndPermissions().addAll(userProgramsAndPermissions);

               } else {
                  LOGGER.info("NO Partner Seasons Exist! ");
               }
               partnerDashboard.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.PARTNER_DASHBOARD.getValue(),
                     messageUtil.getMessage(PartnerDashboardMessageConstants.FETCH_DATA_SUCCESSFULLY)));
            } else {
               // no partner found with the goid provided
               partnerDashboard.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD_IN_DB, CCIConstants.TYPE_INFO, CCIConstants.NO_DATA_CODE,
                     messageUtil.getMessage(PartnerDashboardMessageConstants.NO_PROGRAM_DETAILS_FOUND)));
            }
         } catch (CcighgoException e) {
            partnerDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_PROGRAM_DETAILS_FOUND.getValue(),
                  messageUtil.getMessage(PartnerDashboardMessageConstants.NO_PROGRAM_DETAILS_FOUND)));
            LOGGER.error(messageUtil.getMessage(PartnerDashboardMessageConstants.NO_PROGRAM_DETAILS_FOUND));
         }
      }
      return partnerDashboard;
   }

   @Override
   public PartnerJ1HSDashboard getJ1HSDashboard(String partnerGoId) {
      LOGGER.info("Partner GoId : " + partnerGoId);
      PartnerJ1HSDashboard j1hsDashboard = new PartnerJ1HSDashboard();
      if (partnerGoId == null || Integer.valueOf(partnerGoId) == 0 || Integer.valueOf(partnerGoId) < 0) {
         j1hsDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_J1_PARTNER_ID.getValue(),
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

               List<PartnerJ1HSAnnouncement> partnerJ1HSAnnouncements = new ArrayList<PartnerJ1HSAnnouncement>();
               if (partner.getPartnerAnnouncements() != null && !partner.getPartnerAnnouncements().isEmpty()) {
                  for (PartnerAnnouncement ann : partner.getPartnerAnnouncements()) {
                     if (ann.getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_J1_HS_ID) {
                        PartnerJ1HSAnnouncement j1hsAnn = new PartnerJ1HSAnnouncement();
                        j1hsAnn.setAnnouncement(ann.getAnnouncement());
                        j1hsAnn.setTimestamp(DateUtils.getTimestamp(ann.getCreatedOn()));
                        partnerJ1HSAnnouncements.add(j1hsAnn);
                     }
                  }
               }
               j1hsDashboard.getPartnerAnnouncements().addAll(partnerJ1HSAnnouncements);

               // partner j1 cci contact
               PartnerProgram partnerCCIJ1Contact = partnerProgramRepository.findByPartnerIdAndDepartmentProgramId(partner.getPartnerGoId(), CCIConstants.HSP_J1_HS_ID);
               PartnerJ1HSCCIContact cciContact = null;
               if (partnerCCIJ1Contact != null) {
                  cciContact = new PartnerJ1HSCCIContact();
                  cciContact.setPartnerCCIContactName(partnerCCIJ1Contact.getCcistaffUser().getFirstName() + " " + partnerCCIJ1Contact.getCcistaffUser().getLastName());
                  cciContact.setPartnerProgramName(CCIConstants.HSP_J1_HS + " Contact");
                  if (partnerCCIJ1Contact.getCcistaffUser().getCcistaffUsersCcistaffRoles() != null
                        && partnerCCIJ1Contact.getCcistaffUser().getCcistaffUsersCcistaffRoles().size() > 0) {
                     cciContact.setPartnerCCIContactDesignation(partnerCCIJ1Contact.getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole()
                           .getCciStaffRoleName());
                  }
                  cciContact.setPartnerCCIContactImageUrl(partnerCCIJ1Contact.getCcistaffUser().getPhoto());
                  cciContact.setPartnerCCIContactPhone(partnerCCIJ1Contact.getCcistaffUser().getPrimaryPhone());
                  cciContact.setPartnerCCIContactEmail(partnerCCIJ1Contact.getCcistaffUser().getGoIdSequence().getLogins().get(0).getEmail());
                  cciContact.setPartnerCCIContactExtentionNo(partnerCCIJ1Contact.getCcistaffUser().getPhoneExtension());
               }
               j1hsDashboard.setCciContact(cciContact);

               // work queue types and categories
               List<PartnerJ1HSWorkQueueType> partnerWorkQueueTypesList = new ArrayList<PartnerJ1HSWorkQueueType>();
               List<PartnerWorkQueueType> partnerWorkQueueTypes = partnerWorkQueueTypeRepository.getPartnerWorkQueueTypesByDepartmentProgramId(CCIConstants.HSP_J1_HS_ID);
               if (partnerWorkQueueTypes != null) {
                  for (PartnerWorkQueueType pqType : partnerWorkQueueTypes) {
                     PartnerJ1HSWorkQueueType j1wqType = new PartnerJ1HSWorkQueueType();
                     j1wqType.setPartnerWorkQueueTypeName(pqType.getPartnerWQTypeName() != null ? pqType.getPartnerWQTypeName() : "");
                     PartnerWorkQueueTypeAggregate typeAgg = partnerWorkQueueTypeAggregateRepository.getWorkQueueTypeAggregateByDepartmentProgramId(pqType.getPartnerWQTypeId(),
                           partner.getPartnerGoId(), CCIConstants.HSP_J1_HS_ID);
                     j1wqType.setPartnerWorkQueueTypeNo(typeAgg.getPartnerWQTypeAggregate() != null ? typeAgg.getPartnerWQTypeAggregate() : 0);
                     List<PartnerWorkQueueCategory> caregoryList = partnerWorkQueueCategoryRepository.getWorkQueueCategoryForType(pqType.getPartnerWQTypeId());
                     if (caregoryList != null) {
                        List<PartnerJ1HSWorkQueueCategory> partnerWorkQueueCategories = new ArrayList<PartnerJ1HSWorkQueueCategory>();
                        for (PartnerWorkQueueCategory category : caregoryList) {
                           PartnerJ1HSWorkQueueCategory cat = new PartnerJ1HSWorkQueueCategory();
                           cat.setPartnerWorkQueueCategoryName(category.getPartnerWQCategoryName() != null ? category.getPartnerWQCategoryName() : "");
                           PartnerWorkQueueCategoryAggregate catAgg = partnerWorkQueueCategoryAggregateRepository.getCategoryAggregate(pqType.getPartnerWQTypeId(),
                                 category.getPartnerWQCategoryId(), partner.getPartnerGoId(), CCIConstants.HSP_J1_HS_ID);
                           cat.setPartnerWorkQueueCategoryNo(catAgg.getPartnerWQCategoryAggregate() != null ? catAgg.getPartnerWQCategoryAggregate() : 0);
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
                  List<PartnerApplicationStats> statsList = new ArrayList<PartnerApplicationStats>();
                  PartnerProgramStats programStats = new PartnerProgramStats();
                  for (PartnerQuickStatsCategoryAggregate categoryAggregate : partnerStatsDetails) {
                     PartnerApplicationStats applicationStats = new PartnerApplicationStats();
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.SUBMITTED)) {
                        applicationStats.setKey(CCIConstants.SUBMITTED);
                        applicationStats.setValue(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.PARTNER_REVIEW)) {
                        applicationStats.setKey(CCIConstants.PARTNER_REVIEW);
                        applicationStats.setValue(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.GREENHEART_REVIEW)) {
                        applicationStats.setKey(CCIConstants.GREENHEART_REVIEW);
                        applicationStats.setValue(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.APPROVED)) {
                        applicationStats.setKey(CCIConstants.APPROVED);
                        applicationStats.setValue(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.NOT_APPROVED)) {
                        applicationStats.setKey(CCIConstants.NOT_APPROVED);
                        applicationStats.setValue(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     statsList.add(applicationStats);
                  }
                  partnerStatistics.getApplicationStats().addAll(statsList);
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
                        prg.setProgramName(partSeason.getSeason().getSeasonJ1details().get(0).getProgramName());
                        prg.setApplicationDeadlineDate(DateUtils.getDateAndTime(partSeason.getPartnerSeasonAppDeadlineDate()));
                        prg.setSecondSemDeadlineDate(DateUtils.getDateAndTime((partSeason.getPartnerSeasonExtAppDeadlineDate())));
                        prg.setSeasonStatus(partSeason.getSeason().getSeasonJ1details().get(0).getSeasonStatus().getStatus());
                        List<PartnerSeasonAllocation> j1Allocations = partSeason.getPartnerSeasonAllocations();
                        if (j1Allocations != null) {
                           J1HSAllocation allocation = new J1HSAllocation();

                           int totalUnGuarantNumerator = 0;
                           int augStartUnGuarnteedParticipantsNumerator = 0;
                           int janStartUnGuarnteedParticipantsNumerator = 0;
                           int totalGurantNumerator = 0;
                           int augStartGuarnteedParticipantsNumerator = 0;
                           int janStartGuarnteedParticipantsNumerator = 0;
                           int totalUnGuarantDenominator = 0;
                           int augStartUnGuarnteedParticipantsDenominator = 0;
                           int janStartUnGuarnteedParticipantsDenominator = 0;
                           int totalGurantDenominator = 0;
                           int augStartGuarnteedParticipantsDenominator = 0;
                           int janStartGuarnteedParticipantsDenominator = 0;
                           for (PartnerSeasonAllocation psa : j1Allocations) {
                              if (psa.getDepartmentProgramOption() != null
                                    && psa.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_J1_HS_ID) {
                                 if (psa.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_J1)) {
                                    augStartUnGuarnteedParticipantsNumerator = participantRepository.getUnGurantJ1AugParticipantCount(partner.getPartnerGoId(), partSeason
                                          .getSeason().getSeasonId());
                                    totalUnGuarantNumerator += augStartUnGuarnteedParticipantsNumerator;
                                    augStartUnGuarnteedParticipantsDenominator = psa.getMaxPax() > 0 ? psa.getMaxPax() : 0;
                                    totalUnGuarantDenominator += augStartUnGuarnteedParticipantsDenominator > 0 ? augStartUnGuarnteedParticipantsDenominator : 0;
                                    augStartGuarnteedParticipantsNumerator = participantRepository.getGurantJ1AugParticipantCount(partner.getPartnerGoId(), partSeason.getSeason()
                                          .getSeasonId());
                                    totalGurantNumerator += augStartGuarnteedParticipantsNumerator;
                                    augStartGuarnteedParticipantsDenominator = psa.getMaxGuaranteedPax() > 0 ? psa.getMaxGuaranteedPax() : 0;
                                    totalGurantDenominator += augStartGuarnteedParticipantsDenominator > 0 ? augStartGuarnteedParticipantsDenominator : 0;
                                 }
                                 if (psa.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_J1)) {
                                    janStartUnGuarnteedParticipantsNumerator = participantRepository.getUnGurantJ1JanParticipantCount(partner.getPartnerGoId(), partSeason
                                          .getSeason().getSeasonId());
                                    totalUnGuarantNumerator += janStartUnGuarnteedParticipantsNumerator;
                                    janStartUnGuarnteedParticipantsDenominator = psa.getMaxPax() > 0 ? psa.getMaxPax() : 0;
                                    totalUnGuarantDenominator += janStartUnGuarnteedParticipantsDenominator > 0 ? janStartUnGuarnteedParticipantsDenominator : 0;
                                    janStartGuarnteedParticipantsNumerator = participantRepository.getGurantJ1JanParticipantCount(partner.getPartnerGoId(), partSeason.getSeason()
                                          .getSeasonId());
                                    totalGurantNumerator += janStartGuarnteedParticipantsNumerator;
                                    janStartGuarnteedParticipantsDenominator = psa.getMaxGuaranteedPax() > 0 ? psa.getMaxGuaranteedPax() : 0;
                                    totalGurantDenominator += janStartGuarnteedParticipantsDenominator > 0 ? janStartGuarnteedParticipantsDenominator : 0;
                                 }
                              }

                           }
                           allocation.setAugStartUnguaranteedNumerator(augStartUnGuarnteedParticipantsNumerator);
                           allocation.setJanStartUnguaranteedNumerator(janStartUnGuarnteedParticipantsNumerator);
                           allocation.setTotalUnguaranteedNumerator(totalUnGuarantNumerator);
                           allocation.setAugStartGuaranteedNumerator(augStartGuarnteedParticipantsNumerator);
                           allocation.setJanStartGuaranteedNumerator(janStartGuarnteedParticipantsNumerator);
                           allocation.setTotalGuaranteedNumerator(totalGurantNumerator);

                           allocation.setAugStartUnguaranteedDenominator(augStartUnGuarnteedParticipantsDenominator);
                           allocation.setJanStartUnguaranteedDenominator(janStartUnGuarnteedParticipantsDenominator);
                           allocation.setTotalUnguaranteedDenominator(totalUnGuarantDenominator);
                           allocation.setAugStartGuaranteedDenominator(augStartGuarnteedParticipantsDenominator);
                           allocation.setJanStartGuaranteedDenominator(janStartGuarnteedParticipantsDenominator);
                           allocation.setTotalGuaranteedDenominator(totalGurantDenominator);
                           prg.setAllocation(allocation);
                        }
                        partnerJ1HSProgramsList.add(prg);
                     }
                  }
               }
               j1hsDashboard.getPartnerJ1HSPrograms().addAll(partnerJ1HSProgramsList);
               j1hsDashboard.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
                     messageUtil.getMessage(PartnerDashboardMessageConstants.FETCH_DATA_SUCCESSFULLY)));
            } else {
               j1hsDashboard.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD_IN_DB, CCIConstants.TYPE_INFO, CCIConstants.NO_DATA_CODE,
                     messageUtil.getMessage(PartnerDashboardMessageConstants.NO_PROGRAM_DETAILS_FOUND)));
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
         f1Dashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_F1_PARTNER_ID.getValue(),
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
               List<PartnerF1Announcement> partnerF1Announcements = new ArrayList<PartnerF1Announcement>();
               if (partner.getPartnerAnnouncements() != null && !(partner.getPartnerAnnouncements().isEmpty())) {
                  for (PartnerAnnouncement ann : partner.getPartnerAnnouncements()) {
                     if (ann.getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_F1_ID) {
                        PartnerF1Announcement f1Ann = new PartnerF1Announcement();
                        f1Ann.setAnnouncement(ann.getAnnouncement());
                        f1Ann.setTimestamp(DateUtils.getTimestamp(ann.getCreatedOn()));
                        partnerF1Announcements.add(f1Ann);
                     }
                  }
               }
               f1Dashboard.getPartnerAnnouncements().addAll(partnerF1Announcements);

               // partner f1 cci contact
               PartnerProgram partnerCCIF1Contact = partnerProgramRepository.findByPartnerIdAndDepartmentProgramId(partner.getPartnerGoId(), CCIConstants.HSP_F1_ID);
               PartnerF1CCIContact cciContact = null;
               if (partnerCCIF1Contact != null) {
                  cciContact = new PartnerF1CCIContact();
                  cciContact.setPartnerCCIContactName(partnerCCIF1Contact.getCcistaffUser().getFirstName() + " " + partnerCCIF1Contact.getCcistaffUser().getLastName());
                  cciContact.setPartnerProgramName(CCIConstants.HSP_F1 + " Contact");
                  if (partnerCCIF1Contact.getCcistaffUser().getCcistaffUsersCcistaffRoles() != null
                        && partnerCCIF1Contact.getCcistaffUser().getCcistaffUsersCcistaffRoles().size() > 0) {
                     cciContact.setPartnerCCIContactDesignation(partnerCCIF1Contact.getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole()
                           .getCciStaffRoleName());
                  }
                  cciContact.setPartnerCCIContactImageUrl(partnerCCIF1Contact.getCcistaffUser().getPhoto());
                  cciContact.setPartnerCCIContactPhone(partnerCCIF1Contact.getCcistaffUser().getPrimaryPhone());
                  cciContact.setPartnerCCIContactEmail(partnerCCIF1Contact.getCcistaffUser().getGoIdSequence().getLogins().get(0).getEmail());
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
               f1Dashboard.getPartnerWorkQueueTypes().addAll(partnerWorkQueueTypesList);

               // Statistics
               com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerStatistics partnerStatistics = null;
               List<PartnerQuickStatsCategoryAggregate> partnerStatsDetails = partnerQuickStatsCategoryAggregateRepository.getStats(CCIConstants.APPL_F1, partner.getPartnerGoId(),
                     CCIConstants.HSP_F1_ID);
               if (partnerStatsDetails != null && partnerStatsDetails.size() > 0) {
                  partnerStatistics = new com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerStatistics();
                  List<com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerApplicationStats> statsList = new ArrayList<com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerApplicationStats>();
                  com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerProgramStats programStats = new com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerProgramStats();
                  for (PartnerQuickStatsCategoryAggregate categoryAggregate : partnerStatsDetails) {
                     com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerApplicationStats applicationStats = new com.ccighgo.service.transport.partner.beans.partnerf1details.PartnerApplicationStats();
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.SUBMITTED)) {
                        applicationStats.setKey(CCIConstants.SUBMITTED);
                        applicationStats.setValue(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.PARTNER_REVIEW)) {
                        applicationStats.setKey(CCIConstants.PARTNER_REVIEW);
                        applicationStats.setValue(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.GREENHEART_REVIEW)) {
                        applicationStats.setKey(CCIConstants.GREENHEART_REVIEW);
                        applicationStats.setValue(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.APPROVED)) {
                        applicationStats.setKey(CCIConstants.APPROVED);
                        applicationStats.setValue(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.NOT_APPROVED)) {
                        applicationStats.setKey(CCIConstants.NOT_APPROVED);
                        applicationStats.setValue(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     statsList.add(applicationStats);
                  }
                  partnerStatistics.getApplicationStats().addAll(statsList);
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
                        prg.setProgramName(partSeason.getSeason().getSeasonF1details().get(0).getProgramName());
                        prg.setApplicationDeadlineDate(DateUtils.getDateAndTime(partSeason.getPartnerSeasonAppDeadlineDate()));
                        prg.setSecondSemDeadlineDate(DateUtils.getDateAndTime(partSeason.getPartnerSeasonExtAppDeadlineDate()));
                        prg.setSeasonStatus(partSeason.getSeason().getSeasonF1details().get(0).getSeasonStatus().getStatus());
                        List<PartnerSeasonAllocation> f1Allocations = partSeason.getPartnerSeasonAllocations();
                        if (f1Allocations != null) {
                           F1Allocation allocation = new F1Allocation();
                           int totalGurantNumerator = 0;
                           int augStartGuarnteedParticipantsNumerator = 0;
                           int janStartGuarnteedParticipantsNumerator = 0;
                           int totalGurantDenominator = 0;
                           int augStartGuarnteedParticipantsDenominator = 0;
                           int janStartGuarnteedParticipantsDenominator = 0;
                           for (PartnerSeasonAllocation psa : f1Allocations) {
                              if (psa.getDepartmentProgramOption() != null
                                    && psa.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_F1_ID) {
                                 if (psa.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_F1)) {
                                    augStartGuarnteedParticipantsNumerator = participantRepository.getGurantF1AugParticipantCount(partner.getPartnerGoId(), partSeason.getSeason()
                                          .getSeasonId());
                                    totalGurantNumerator += augStartGuarnteedParticipantsNumerator;
                                    augStartGuarnteedParticipantsDenominator = psa.getMaxGuaranteedPax() > 0 ? psa.getMaxGuaranteedPax() : 0;
                                    totalGurantDenominator += augStartGuarnteedParticipantsDenominator > 0 ? augStartGuarnteedParticipantsDenominator : 0;

                                 }
                                 if (psa.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_F1)) {
                                    janStartGuarnteedParticipantsNumerator = participantRepository.getGurantF1JanParticipantCount(partner.getPartnerGoId(), partSeason.getSeason()
                                          .getSeasonId());
                                    totalGurantNumerator += janStartGuarnteedParticipantsNumerator;
                                    janStartGuarnteedParticipantsDenominator = psa.getMaxGuaranteedPax() > 0 ? psa.getMaxGuaranteedPax() : 0;
                                    totalGurantDenominator += janStartGuarnteedParticipantsDenominator > 0 ? janStartGuarnteedParticipantsDenominator : 0;
                                 }
                              }

                           }
                           allocation.setAugStartGuaranteedNumerator(augStartGuarnteedParticipantsNumerator);
                           allocation.setJanStartGuaranteedNumerator(janStartGuarnteedParticipantsNumerator);
                           allocation.setTotalGuaranteedNumerator(totalGurantNumerator);
                           allocation.setAugStartGuaranteedDenominator(augStartGuarnteedParticipantsDenominator);
                           allocation.setJanStartGuaranteedDenominator(janStartGuarnteedParticipantsDenominator);
                           allocation.setTotalGuaranteedDenominator(totalGurantDenominator);
                           prg.setAllocation(allocation);
                        }
                        partnerF1ProgramsList.add(prg);
                     }
                  }
               }
               f1Dashboard.getPartnerF1Programs().addAll(partnerF1ProgramsList);
               f1Dashboard.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
                     messageUtil.getMessage(PartnerDashboardMessageConstants.FETCH_DATA_SUCCESSFULLY)));
            } else {
               f1Dashboard.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD_IN_DB, CCIConstants.TYPE_INFO, CCIConstants.NO_DATA_CODE,
                     messageUtil.getMessage(PartnerDashboardMessageConstants.NO_PROGRAM_DETAILS_FOUND)));
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
         ihpDashboard.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_IHP_PARTNER_ID.getValue(),
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
               List<PartnerIHPAnnouncement> partnerIHPAnnouncements = new ArrayList<PartnerIHPAnnouncement>();
               if (partner.getPartnerAnnouncements() != null && !(partner.getPartnerAnnouncements().isEmpty())) {
                  for (PartnerAnnouncement ann : partner.getPartnerAnnouncements()) {
                     if (ann.getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_STP_IHP_ID) {
                        PartnerIHPAnnouncement f1Ann = new PartnerIHPAnnouncement();
                        f1Ann.setAnnouncement(ann.getAnnouncement());
                        f1Ann.setTimestamp(DateUtils.getTimestamp(ann.getCreatedOn()));
                        partnerIHPAnnouncements.add(f1Ann);
                     }
                  }
               }
               ihpDashboard.getPartnerAnnouncements().addAll(partnerIHPAnnouncements);

               // partner ihp cci contact
               PartnerProgram partnerCCIIHPContact = partnerProgramRepository.findByPartnerIdAndDepartmentProgramId(partner.getPartnerGoId(), CCIConstants.HSP_STP_IHP_ID);
               PartnerIHPCCIContact cciContact = null;
               if (partnerCCIIHPContact != null) {
                  cciContact = new PartnerIHPCCIContact();
                  cciContact.setPartnerCCIContactName(partnerCCIIHPContact.getCcistaffUser().getFirstName() + " " + partnerCCIIHPContact.getCcistaffUser().getLastName());
                  cciContact.setPartnerProgramName(CCIConstants.HSP_STP_IHP + " Contact");
                  if (partnerCCIIHPContact.getCcistaffUser().getCcistaffUsersCcistaffRoles() != null
                        && partnerCCIIHPContact.getCcistaffUser().getCcistaffUsersCcistaffRoles().size() > 0) {
                     cciContact.setPartnerCCIContactDesignation(partnerCCIIHPContact.getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole()
                           .getCciStaffRoleName());
                  }
                  cciContact.setPartnerCCIContactImageUrl(partnerCCIIHPContact.getCcistaffUser().getPhoto());
                  cciContact.setPartnerCCIContactPhone(partnerCCIIHPContact.getCcistaffUser().getPrimaryPhone());
                  cciContact.setPartnerCCIContactEmail(partnerCCIIHPContact.getCcistaffUser().getGoIdSequence().getLogins().get(0).getEmail());
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
               ihpDashboard.getPartnerWorkQueueTypes().addAll(partnerWorkQueueTypesList);
               // Statistics
               com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerStatistics partnerStatistics = null;
               List<PartnerQuickStatsCategoryAggregate> partnerStatsDetails = partnerQuickStatsCategoryAggregateRepository.getStats(CCIConstants.APPL_IHP,
                     partner.getPartnerGoId(), CCIConstants.HSP_STP_IHP_ID);
               if (partnerStatsDetails != null && partnerStatsDetails.size() > 0) {
                  partnerStatistics = new com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerStatistics();
                  List<com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerApplicationStats> statList = new ArrayList<com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerApplicationStats>();
                  com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerProgramStats programStats = new com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerProgramStats();
                  for (PartnerQuickStatsCategoryAggregate categoryAggregate : partnerStatsDetails) {
                     com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerApplicationStats applicationStats = new com.ccighgo.service.transport.partner.beans.partnerihpdetails.PartnerApplicationStats();
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.SUBMITTED)) {
                        applicationStats.setKey(CCIConstants.SUBMITTED);
                        applicationStats.setValue(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.PARTNER_REVIEW)) {
                        applicationStats.setKey(CCIConstants.PARTNER_REVIEW);
                        applicationStats.setValue(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.GREENHEART_REVIEW)) {
                        applicationStats.setKey(CCIConstants.GREENHEART_REVIEW);
                        applicationStats.setValue(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.APPROVED)) {
                        applicationStats.setKey(CCIConstants.APPROVED);
                        applicationStats.setValue(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     if (categoryAggregate.getPartnerQSCategoryName().equals(CCIConstants.NOT_APPROVED)) {
                        applicationStats.setKey(CCIConstants.NOT_APPROVED);
                        applicationStats.setValue(categoryAggregate.getPartnerQSCategoryAggregate());
                     }
                     statList.add(applicationStats);
                  }
                  partnerStatistics.getApplicationStats().addAll(statList);
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
                        prg.setProgramName(partSeason.getSeason().getSeasonIhpdetails().get(0).getProgramName());
                        prg.setApplicationDeadlineDate(DateUtils.getMMddyyDate((partSeason.getPartnerSeasonAppDeadlineDate())));
                        prg.setSeasonStatus(partSeason.getSeason().getSeasonStatus().getStatus());
                        partnerIHPProgramsList.add(prg);
                     }
                  }
                  ihpDashboard.getPartnerIHPPrograms().addAll(partnerIHPProgramsList);
               }
               ihpDashboard.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
                     messageUtil.getMessage(PartnerDashboardMessageConstants.FETCH_DATA_SUCCESSFULLY)));
            } else {
               ihpDashboard.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD_IN_DB, CCIConstants.TYPE_INFO, CCIConstants.NO_DATA_CODE,
                     messageUtil.getMessage(PartnerDashboardMessageConstants.NO_PROGRAM_DETAILS_FOUND)));
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

   // TODO
   @Override
   public PartnerCAPDashboard getWnTDashboard(String partnerGoId) {
      LOGGER.info("partner goId : " + partnerGoId);
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

   // TODO
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
   public PartnerRecruitmentLead getPartnerInquiryLeadData(String id) {
      PartnerRecruitmentLead pwt = new PartnerRecruitmentLead();
      try {
         if (id == null || Integer.valueOf(id) == 0 || Integer.valueOf(id) < 0) {
            pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_INQUIRY_LEAD_ID.getValue(),
                  messageUtil.getMessage(PartnerDashboardMessageConstants.INVALID_PARTNER_ID)));
            LOGGER.error(messageUtil.getMessage(PartnerDashboardMessageConstants.INVALID_PARTNER_ID));
            return pwt;
         }
         int goId = Integer.parseInt(id);
         pwt.setGoId(goId);
         PartnerAgentInquiry partnerAgentInquiry = partnerAgentInquiryRepository.findPartnerByGoId(goId);
         if (partnerAgentInquiry == null) {
            pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_PARTNER_INQUIRY_LEAD_DETAIL.getValue(),
                  messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_LEAD_DETAIL)));
            LOGGER.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_LEAD_DETAIL));
            return pwt;
         }
         try {
            PartnerReviewStatus partnerReviewStatus = partnerReviewStatusRepository.findStatusByPartnerId(goId);
            if (partnerReviewStatus != null && partnerReviewStatus.getPartnerStatus1() != null)
               pwt.setLeadStatus(partnerReviewStatus.getPartnerStatus1().getPartnerStatusName());

         } catch (Exception e) {
            ExceptionUtil.logException(e, LOGGER);
         }
         pwt.setFollowUpDate(DateUtils.getDateAndTime(partnerAgentInquiry.getFollowUpDate()));

         /**
          * Details
          */
         try {
            PartnerRecruitmentLeadScreeningDetail detail = new PartnerRecruitmentLeadScreeningDetail();
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
            ExceptionUtil.logException(e, LOGGER);
         }

         /**
          * Additional Data
          */
         try {
            com.ccighgo.service.transport.integration.thirdparty.beans.partnerLeadViewForPartnerInquiryData.PartnerRecruitmentAdminScreeningAdditionalInfo additional = new com.ccighgo.service.transport.integration.thirdparty.beans.partnerLeadViewForPartnerInquiryData.PartnerRecruitmentAdminScreeningAdditionalInfo();
            List<PartnerProgram> partnerPrograms = partnerProgramRepository.findAllPartnerProgramsByPartnerId(goId);
            if (partnerPrograms != null) {
               StringBuilder st = new StringBuilder();
               int i = 0;
               for (PartnerProgram partnerProgram : partnerPrograms) {
                  if (i++ > 0) {
                     st.append(",");
                  }
                  st.append(partnerProgram.getLookupDepartmentProgram().getProgramName());
               }
               additional.setProgramsYouLikeToParticipate(st.toString());
            }
            additional.setSendPartnersToUSA(partnerAgentInquiry.getCurrentlySendingParticipantToUS() == 1);
            additional.setIsYourOrganizationSendingParticipantstoUSA(partnerAgentInquiry.getCurrentlySendingParticipantToUS() == 1);
            additional.setLikeToKnowMoreAboutAmbassadorScholarship(partnerAgentInquiry.getAmbassadorScholershipParticipants() == 1);
            if (partnerAgentInquiry.getBusinessYears() != null)
               additional.setYearsInBusiness(Integer.parseInt(partnerAgentInquiry.getBusinessYears()));
            additional.setHearAboutUsFrom(partnerAgentInquiry.getHowDidYouHearAboutCCI());
            additional.setDescribeProgramsOrganizationOffers(partnerAgentInquiry.getCurrentlyOfferingPrograms());

            additional.setInterestedInHighSchoolAbroad(partnerAgentInquiry.getHighSchoolAbroad() == 1);
            additional.setInterestedInTeachAbroad(partnerAgentInquiry.getTeachAbroad() == 1);
            additional.setInterestedInVolunteerAbroad(partnerAgentInquiry.getVolunteerAbroad() == 1);

            // additional.setProgramsYouOffer(partnerAgentInquiry.getCurrentlyOfferingPrograms());
            pwt.setAdditionalInformation(additional);
         } catch (Exception e) {
            ExceptionUtil.logException(e, LOGGER);
         }

         pwt.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE, messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         pwt.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_PARTNER_INQUIRY_LEAD_DETAIL.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_LEAD_DETAIL)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_PARTNER_INQUIRY_LEAD_DETAIL));
      }
      return pwt;
   }
}
