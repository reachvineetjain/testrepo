/**
 * 
 */
package com.ccighgo.service.components.partner.season;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.PartnerAnnouncement;
import com.ccighgo.db.entities.PartnerSeasonAllocation;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonAllocationRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonContractRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonDocumentRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonsRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminMessageConstants;
import com.ccighgo.service.components.errormessages.constants.PartnerSeasonMessageConstants;
import com.ccighgo.service.transport.partner.beans.newpartnerapplicationdeadlilne.NewApplicationDeadlilneDatesAllocations;
import com.ccighgo.service.transport.partner.beans.newpartnerseasonallocationrequest.NewPartnerSeasonAllocationRequest;
import com.ccighgo.service.transport.partner.beans.partner.season.application.PartnerSeasonApplication;
import com.ccighgo.service.transport.partner.beans.partner.season.application.PartnerSeasonApplicationList;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeason;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonDepartment;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonProgramOption;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonProgramStatus;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasons;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.ApplicationDeadlilneDatesAllocations;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerDepartment;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerHLSeason;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerProgram;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerSeasonAnnouncements;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerSeasonDetail;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerSeasonJ1HSProgramAllocations;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerSeasonStatus;
import com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.PartnerSeasonF1Detail;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author ravi
 *
 */
@Component
public class PartnerSeasonInterfaceImpl implements PartnerSeasonInterface {

   private static final Logger LOGGER = Logger.getLogger(PartnerSeasonInterfaceImpl.class);

   @Autowired
   MessageUtils messageUtil;
   @Autowired
   CommonComponentUtils componentUtils;

   @Autowired
   PartnerRepository partnerRepository;
   @Autowired
   PartnerSeasonsRepository partnerSeasonsRepository;
   @Autowired
   PartnerSeasonAllocationRepository partnerSeasonAllocationRepository;
   @Autowired
   PartnerSeasonContractRepository partnerSeasonContractRepository;
   @Autowired
   PartnerSeasonDocumentRepository partnerSeasonDocumentRepository;

   @Autowired
   EntityManager entityManager;

   private static final String SP_PARTNER_SEASON_APPLICATION_LIST = "call SPPartnerSeasonAplication(?)";

   @Override
   public PartnerSeasons getPartnerSeasons(String partnerId) {
      PartnerSeasons partnerSeasons = new PartnerSeasons();
      if (partnerId == null) {
         partnerSeasons.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID));
         return partnerSeasons;
      }
      try {
         List<com.ccighgo.db.entities.PartnerSeason> partnerSeasonDBList = partnerSeasonsRepository.findPartnerSeasonByPartnerGoId(Integer.valueOf(partnerId));
         if (partnerSeasonDBList != null) {
            int count = 0;
            List<PartnerSeason> partnerSeasonsUIList = new ArrayList<PartnerSeason>();
            for (com.ccighgo.db.entities.PartnerSeason entity : partnerSeasonDBList) {
               if (entity.getDepartmentProgram() != null && entity.getSeason() != null) {
                  count += 1;
                  PartnerSeasonProgramOption partnerProgramOption = new PartnerSeasonProgramOption();
                  partnerProgramOption.setPartnerProgramOptionId(entity.getDepartmentProgram().getDepartmentProgramId());
                  partnerProgramOption.setPartnerProgramOption(entity.getDepartmentProgram().getProgramName());

                  PartnerSeasonDepartment partnerSeasonDepartment = new PartnerSeasonDepartment();
                  partnerSeasonDepartment.setPartnerSeasonDepartmentId(entity.getDepartmentProgram().getLookupDepartment().getDepartmentId());
                  partnerSeasonDepartment.setPartnerSeasonDepartmentCode(entity.getDepartmentProgram().getLookupDepartment().getAcronym());
                  partnerSeasonDepartment.setPartnerSeasonDepartmentName(entity.getDepartmentProgram().getLookupDepartment().getDepartmentName());

                  PartnerSeasonProgramStatus seasonProgramStatus = new PartnerSeasonProgramStatus();
                  // TODO
                  // seasonProgramStatus.setPartnerSeasonProgramStatusId(entity.getPartnerStatus().getPartnerStatusId());
                  // seasonProgramStatus.setPartnerSeasonProgramStatus(entity.getPartnerStatus().getPartnerStatusName());

                  PartnerSeason pSeason = new PartnerSeason();
                  pSeason.setParticipantAllocated("TODO:need clarification");
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_J1_HS)) {
                     pSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonJ1details().get(0).getProgramName());
                     pSeason.setDetailsUrl("/partner/season/view/j1hs/");
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_F1)) {
                     pSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonF1details().get(0).getProgramName());
                     pSeason.setDetailsUrl("/partner/season/view/f1/");
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_STP_IHP)) {
                     pSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonIhpdetails().get(0).getProgramName());
                     pSeason.setDetailsUrl("comming soon");
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_SUMMER)) {
                     pSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonWnTsummerDetails().get(0).getProgramName());
                     pSeason.setDetailsUrl("comming soon");
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_WINTER)) {
                     pSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonWnTwinterDetails().get(0).getProgramName());
                     pSeason.setDetailsUrl("comming soon");
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_SPRING)) {
                     pSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonWnTspringDetails().get(0).getProgramName());
                     pSeason.setDetailsUrl("comming soon");
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_CAP)) {
                     pSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonCapdetails().get(0).getProgramName());
                     pSeason.setDetailsUrl("comming soon");
                  }
                  pSeason.setPartnerSeasonId(entity.getPartnerSeasonId());
                  pSeason.setPartnerId(partnerId);
                  pSeason.setPartnerStartDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonStartDate()));
                  pSeason.setPartnerEndDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonEndDate()));
                  pSeason.setPartnerApplicationDeadlineDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonAppDeadlineDate()));
                  pSeason.setPartnerSeasonDepartment(partnerSeasonDepartment);
                  pSeason.setPartnerProgramOption(partnerProgramOption);
                  pSeason.setSeasonProgramStatus(seasonProgramStatus);
                  partnerSeasonsUIList.add(pSeason);
               }
            }
            partnerSeasons.setPartnerSeasonsCount(count);
            partnerSeasons.getPartnerSeasons().addAll(partnerSeasonsUIList);
            partnerSeasons.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            partnerSeasons.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         partnerSeasons.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_LIST)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_LIST));
      }
      return partnerSeasons;
   }

   @Override
   public PartnerSeasonDetail viewJ1HSPartnerSeason(String partnerSeasonId) {
      PartnerSeasonDetail partnersSeasonDetails = new PartnerSeasonDetail();
      if (partnerSeasonId == null || Integer.valueOf(partnerSeasonId) == 0 || Integer.valueOf(partnerSeasonId) < 0) {
         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_REQUEST.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_REQUEST_PARAMS)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_REQUEST_PARAMS));
      }
      try {
         com.ccighgo.db.entities.PartnerSeason seasonDetail = partnerSeasonsRepository.findOne(Integer.valueOf(partnerSeasonId));
         // get announcements
         List<PartnerSeasonAnnouncements> partnerSeasonAnnouncement = null;
         if (seasonDetail.getPartner().getPartnerAnnouncements() != null && seasonDetail.getPartner().getPartnerAnnouncements().size() > 0) {
            partnerSeasonAnnouncement = new ArrayList<PartnerSeasonAnnouncements>();
            for (PartnerAnnouncement announcement : seasonDetail.getPartner().getPartnerAnnouncements()) {
               if (seasonDetail.getPartner().getParentPartnerGoId() == announcement.getPartner().getPartnerGoId()
                     && seasonDetail.getSeason().getSeasonId() == announcement.getSeason().getSeasonId()
                     && seasonDetail.getDepartmentProgram().getDepartmentProgramId() == announcement.getDepartmentProgram().getDepartmentProgramId()) {
                  PartnerSeasonAnnouncements seasonAnnouncement = new PartnerSeasonAnnouncements();
                  seasonAnnouncement.setPartnerSeasonAnnouncement(announcement.getAnnouncement());
                  seasonAnnouncement.setAnnouncementDate(DateUtils.getTimestamp(announcement.getCreatedOn()));
                  partnerSeasonAnnouncement.addAll(partnerSeasonAnnouncement);
               }
            }
         }
         // get season status
         PartnerSeasonStatus partnerSeasonStatus = new PartnerSeasonStatus();
         // TODO
         // partnerSeasonStatus.setPartnerSeasonStatusId(seasonDetail.getPartnerStatus().getPartnerStatusId());
         // partnerSeasonStatus.setPartnerSeasonStatus(seasonDetail.getPartnerStatus().getPartnerStatusName());

         // get department
         PartnerDepartment partnerDepartment = new PartnerDepartment();
         partnerDepartment.setPartnerSeasonDepartmentId(seasonDetail.getDepartmentProgram().getLookupDepartment().getDepartmentId());
         partnerDepartment.setPartnerSeasonDepartmentCode(seasonDetail.getDepartmentProgram().getLookupDepartment().getAcronym());
         partnerDepartment.setPartnerSeasonDepartmentName(seasonDetail.getDepartmentProgram().getLookupDepartment().getDepartmentName());

         // get partner program
         PartnerProgram partnerProgram = new PartnerProgram();
         partnerProgram.setPartnerProgramId(seasonDetail.getDepartmentProgram().getDepartmentProgramId());
         partnerProgram.setPartnerProgram(seasonDetail.getDepartmentProgram().getProgramName());

         // get partner high level season
         PartnerHLSeason partnerHLSeason = new PartnerHLSeason();
         partnerHLSeason.setPartnerHLSeasonId(seasonDetail.getSeason().getSeasonId());
         partnerHLSeason.setPartnerHLSeasonName(seasonDetail.getSeason().getSeasonName());

         // partner season program name
         String partnerSeasonProgramName = null;
         if (seasonDetail.getSeason().getSeasonJ1details() != null && seasonDetail.getSeason().getSeasonJ1details().size() > 0) {
            partnerSeasonProgramName = seasonDetail.getSeason().getSeasonJ1details().get(0).getProgramName();
         }

         ApplicationDeadlilneDatesAllocations deadlineDatesAllocations = new ApplicationDeadlilneDatesAllocations();

         PartnerSeasonJ1HSProgramAllocations j1Allocations = new PartnerSeasonJ1HSProgramAllocations();
         List<PartnerSeasonAllocation> partnerSeasonAllocationList = partnerSeasonAllocationRepository.findPartnerSeasonAllocation(Integer.valueOf(partnerSeasonId));
         if (partnerSeasonAllocationList != null) {
            int augStartMaxUnGuarnteedParticipants = 0;
            int janStartMaxUnGuarnteedParticipants = 0;

            int totalUnGuarant = 0;
            int totalGurant = 0;

            int augStartGuarnteedParticipants = 0;
            int janStartGuarnteedParticipants = 0;
            for (PartnerSeasonAllocation unGuaranteedAllocation : partnerSeasonAllocationList) {
               if (unGuaranteedAllocation.getDepartmentProgramOption() != null) {
                  if (unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_J1_HS_ID) {
                     if (unGuaranteedAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_J1)) {
                        augStartMaxUnGuarnteedParticipants = unGuaranteedAllocation.getMaxPax() > 0 ? unGuaranteedAllocation.getMaxPax() : 0;
                        totalUnGuarant += augStartMaxUnGuarnteedParticipants > 0 ? augStartMaxUnGuarnteedParticipants : 0;
                        augStartGuarnteedParticipants = unGuaranteedAllocation.getMaxGuaranteedPax() > 0 ? unGuaranteedAllocation.getMaxGuaranteedPax() : 0;
                        totalGurant += augStartGuarnteedParticipants > 0 ? augStartGuarnteedParticipants : 0;

                     }
                     if (unGuaranteedAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_J1)) {
                        janStartMaxUnGuarnteedParticipants = unGuaranteedAllocation.getMaxPax() > 0 ? unGuaranteedAllocation.getMaxPax() : 0;
                        totalUnGuarant += janStartMaxUnGuarnteedParticipants > 0 ? janStartMaxUnGuarnteedParticipants : 0;
                        janStartGuarnteedParticipants = unGuaranteedAllocation.getMaxGuaranteedPax() > 0 ? unGuaranteedAllocation.getMaxGuaranteedPax() : 0;
                        totalGurant += janStartGuarnteedParticipants > 0 ? janStartGuarnteedParticipants : 0;
                     }
                  }
               }
            }

            partnersSeasonDetails.setProgramAllocations(j1Allocations);
         }

         // TODO partner season notes fix once db is ready
         partnersSeasonDetails.setPartnerSeasonId(seasonDetail.getPartnerSeasonId());
         partnersSeasonDetails.setPartnerSeasonProgramName(partnerSeasonProgramName);
         partnersSeasonDetails.setPartnerDepartment(partnerDepartment);
         partnersSeasonDetails.setPartnerProgram(partnerProgram);
         partnersSeasonDetails.setPartnerHLSeason(partnerHLSeason);
         partnersSeasonDetails.setPartnerSeasonStatus(partnerSeasonStatus);
         // TODO
         // partnersSeasonDetails.setInsuranceProvidedBy(seasonDetail.getInsuranceProvidedByCCI() == CCIConstants.ACTIVE
         // ? true : false);
         // partnersSeasonDetails.setSevisFeesPaidBy(seasonDetail.getSevisFeesPaidByCCI() == CCIConstants.ACTIVE ? true
         // : false);
         partnersSeasonDetails.setSeasonStartDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonStartDate()));
         partnersSeasonDetails.setSeasonEndDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonEndDate()));

         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (CcighgoException e) {
         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON_DETAILS.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_DETAILS)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_DETAILS));
      }
      return partnersSeasonDetails;
   }

   @Override
   public PartnerSeasonApplicationList getPartnerSeasonApplicationList(String partnerId) {
      PartnerSeasonApplicationList partnerSeasonApplicationList = new PartnerSeasonApplicationList();
      if (partnerId == null) {
         partnerSeasonApplicationList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID));
         return partnerSeasonApplicationList;
      }
      try {
         Query query = entityManager.createNativeQuery(SP_PARTNER_SEASON_APPLICATION_LIST);
         query.setParameter(1, Integer.valueOf(partnerId));
         List<Object[]> results = query.getResultList();
         if (results != null && results.size() > 0) {
            partnerSeasonApplicationList.setPartnerId(Integer.valueOf(partnerId));
            List<PartnerSeasonApplication> partnerSeasonApplication = new ArrayList<PartnerSeasonApplication>();
            for (Object[] obj : results) {
               // position 0 : programName, position 1, position 2 seasonId: departmentProgramId
               PartnerSeasonApplication application = new PartnerSeasonApplication();
               application.setProgramName(obj[0].toString());
               application.setSeasonId(obj[1].toString());
               application.setDepartmentProgramId(obj[2].toString());
               partnerSeasonApplication.add(application);
            }
            partnerSeasonApplicationList.getPartnerSeasonApplication().addAll(partnerSeasonApplication);
            partnerSeasonApplicationList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            partnerSeasonApplicationList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         partnerSeasonApplicationList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON_DETAILS.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_DETAILS)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_DETAILS));
      }
      return partnerSeasonApplicationList;
   }

   @Override
   public PartnerSeasonF1Detail viewF1PartnerSeason(String partnerSeasonId) {
      PartnerSeasonF1Detail partnersSeasonDetails = new PartnerSeasonF1Detail();
      if (partnerSeasonId == null || Integer.valueOf(partnerSeasonId) == 0 || Integer.valueOf(partnerSeasonId) < 0) {
         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_REQUEST.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_REQUEST_PARAMS)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_REQUEST_PARAMS));
      }
      try {
         com.ccighgo.db.entities.PartnerSeason seasonDetail = partnerSeasonsRepository.findOne(Integer.valueOf(partnerSeasonId));
         // get announcements
         List<PartnerSeasonAnnouncements> partnerSeasonAnnouncement = null;
         if (seasonDetail.getPartner().getPartnerAnnouncements() != null && seasonDetail.getPartner().getPartnerAnnouncements().size() > 0) {
            partnerSeasonAnnouncement = new ArrayList<PartnerSeasonAnnouncements>();
            for (PartnerAnnouncement announcement : seasonDetail.getPartner().getPartnerAnnouncements()) {
               if (seasonDetail.getPartner().getParentPartnerGoId() == announcement.getPartner().getPartnerGoId()
                     && seasonDetail.getSeason().getSeasonId() == announcement.getSeason().getSeasonId()
                     && seasonDetail.getDepartmentProgram().getDepartmentProgramId() == announcement.getDepartmentProgram().getDepartmentProgramId()) {
                  PartnerSeasonAnnouncements seasonAnnouncement = new PartnerSeasonAnnouncements();
                  seasonAnnouncement.setPartnerSeasonAnnouncement(announcement.getAnnouncement());
                  seasonAnnouncement.setAnnouncementDate(DateUtils.getTimestamp(announcement.getCreatedOn()));
                  partnerSeasonAnnouncement.addAll(partnerSeasonAnnouncement);
               }
            }
         }
         // get season status
         com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.PartnerSeasonStatus partnerSeasonStatus = new com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.PartnerSeasonStatus();
         // TODO
         // partnerSeasonStatus.setPartnerSeasonStatusId(seasonDetail.getPartnerStatus().getPartnerStatusId());
         // partnerSeasonStatus.setPartnerSeasonStatus(seasonDetail.getPartnerStatus().getPartnerStatusName());

         // get department
         com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.PartnerDepartment partnerDepartment = new com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.PartnerDepartment();
         partnerDepartment.setPartnerSeasonDepartmentId(seasonDetail.getDepartmentProgram().getLookupDepartment().getDepartmentId());
         partnerDepartment.setPartnerSeasonDepartmentCode(seasonDetail.getDepartmentProgram().getLookupDepartment().getAcronym());
         partnerDepartment.setPartnerSeasonDepartmentName(seasonDetail.getDepartmentProgram().getLookupDepartment().getDepartmentName());

         // get partner program
         com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.PartnerProgram partnerProgram = new com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.PartnerProgram();
         partnerProgram.setPartnerProgramId(seasonDetail.getDepartmentProgram().getDepartmentProgramId());
         partnerProgram.setPartnerProgram(seasonDetail.getDepartmentProgram().getProgramName());

         // get partner high level season
         com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.PartnerHLSeason partnerHLSeason = new com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.PartnerHLSeason();
         partnerHLSeason.setPartnerHLSeasonId(seasonDetail.getSeason().getSeasonId());
         partnerHLSeason.setPartnerHLSeasonName(seasonDetail.getSeason().getSeasonName());

         // partner season program name
         String partnerSeasonProgramName = null;
         if (seasonDetail.getSeason().getSeasonF1details() != null && seasonDetail.getSeason().getSeasonF1details().size() > 0) {
            partnerSeasonProgramName = seasonDetail.getSeason().getSeasonF1details().get(0).getProgramName();
         }
         // F1ProgramAllocationsGuaranteed f1ProgramAllocationsGuaranteed = null;
         List<PartnerSeasonAllocation> partnerSeasonAllocationList = partnerSeasonAllocationRepository.findPartnerSeasonAllocation(Integer.valueOf(partnerSeasonId));
         if (partnerSeasonAllocationList != null) {
            int totalGurant = 0;
            int augStartGuarnteedParticipants = 0;
            int janStartGuarnteedParticipants = 0;
            for (PartnerSeasonAllocation guaranteedAllocation : partnerSeasonAllocationList) {
               if (guaranteedAllocation.getDepartmentProgramOption() != null) {
                  if (guaranteedAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_F1_ID) {
                     if (guaranteedAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_F1)) {
                        augStartGuarnteedParticipants = guaranteedAllocation.getMaxGuaranteedPax() > 0 ? guaranteedAllocation.getMaxGuaranteedPax() : 0;
                        totalGurant += augStartGuarnteedParticipants > 0 ? augStartGuarnteedParticipants : 0;

                     }
                     if (guaranteedAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_F1)) {
                        janStartGuarnteedParticipants = guaranteedAllocation.getMaxGuaranteedPax() > 0 ? guaranteedAllocation.getMaxGuaranteedPax() : 0;
                        totalGurant += janStartGuarnteedParticipants > 0 ? janStartGuarnteedParticipants : 0;
                     }
                  }
               }
            }

         }

         // TODO partner season notes fix once db is ready

         partnersSeasonDetails.setPartnerSeasonId(seasonDetail.getPartnerSeasonId());
         partnersSeasonDetails.setPartnerSeasonProgramName(partnerSeasonProgramName);
         partnersSeasonDetails.setPartnerDepartment(partnerDepartment);
         partnersSeasonDetails.setPartnerProgram(partnerProgram);
         partnersSeasonDetails.setPartnerHLSeason(partnerHLSeason);
         partnersSeasonDetails.setPartnerSeasonStatus(partnerSeasonStatus);
         // partnersSeasonDetails.setInsuranceProvidedBy(seasonDetail.getInsuranceProvidedByCCI() == CCIConstants.ACTIVE
         // ? true : false);
         // partnersSeasonDetails.setSevisFeesPaidBy(seasonDetail.getSevisFeesPaidByCCI() == CCIConstants.ACTIVE ? true
         // : false);
         partnersSeasonDetails.setSeasonStartDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonStartDate()));
         partnersSeasonDetails.setSeasonEndDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonEndDate()));
         // partnersSeasonDetails.setSeasonApplicationDeadlineDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonAppDeadlineDate()));
         // partnersSeasonDetails.setNewDeadlineRequest("TODO:need clarification");
         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (CcighgoException e) {
         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON_DETAILS.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_DETAILS)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_DETAILS));
      }
      return partnersSeasonDetails;
   }

   @Override
   public WSDefaultResponse createNewPartnerAllocationRequest(NewPartnerSeasonAllocationRequest newPartnerSeasonAllocationRequest) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.CREATE_NEWPARTNER_ALLOCATION.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.CANT_CREATE_NEWPARTNER_ALLOCATION.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_CREATING_NEWPARTNER_ALLOCATION)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_CREATING_NEWPARTNER_ALLOCATION));
      }
      return wsDefaultResponse;
   }

   @Override
   public WSDefaultResponse createNewDeadlineDateRequest(NewApplicationDeadlilneDatesAllocations newApplicationDeadlineDatesAllocations) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.CREATE_NEWDEADLINE_DATE_REQUEST.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.CANT_CREATE_NEW_DEALINE_DATE_REQUEST.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_CREATING_NEW_DEALINE_DATE_REQUEST)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_CREATING_NEW_DEALINE_DATE_REQUEST));
      }
      return wsDefaultResponse;
   }
}
