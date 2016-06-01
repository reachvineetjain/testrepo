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
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.DepartmentProgramOption;
import com.ccighgo.db.entities.PartnerAnnouncement;
import com.ccighgo.db.entities.PartnerSeasonAllocation;
import com.ccighgo.db.entities.SeasonF1Detail;
import com.ccighgo.db.entities.SeasonIHPDetail;
import com.ccighgo.db.entities.SeasonJ1Detail;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.exception.PartnerCodes;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTopicRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonAllocationRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonContractRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonDocumentRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonsRepository;
import com.ccighgo.jpa.repositories.PartnerStatusRepository;
import com.ccighgo.jpa.repositories.SeasonF1DetailsRepository;
import com.ccighgo.jpa.repositories.SeasonIHPDetailRepository;
import com.ccighgo.jpa.repositories.SeasonJ1DetailsRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.jpa.repositories.SeasonStatusRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminMessageConstants;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminSeasonConstants;
import com.ccighgo.service.components.errormessages.constants.PartnerSeasonMessageConstants;
import com.ccighgo.service.components.storedprocedure.PartnerStoredProcedure;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.newpartnerapplicationdeadlilne.NewPartnerApplicationDeadLineDate;
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
import com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.ApplicationDeadlilneDatesF1Allocations;
import com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.PartnerSeasonF1Detail;
import com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.PartnerSeasonF1ProgramAllocations;
import com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.PartnerSeasonIHPDetail;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author ravi
 *
 */
@Component
@SuppressWarnings("unchecked")
public class PartnerSeasonInterfaceImpl implements PartnerSeasonInterface {

   private static final Logger LOGGER = Logger.getLogger(PartnerSeasonInterfaceImpl.class);

   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;

   @Autowired PartnerRepository partnerRepository;
   @Autowired PartnerSeasonsRepository partnerSeasonsRepository;
   @Autowired PartnerSeasonAllocationRepository partnerSeasonAllocationRepository;
   @Autowired PartnerSeasonContractRepository partnerSeasonContractRepository;
   @Autowired PartnerSeasonDocumentRepository partnerSeasonDocumentRepository;
   @Autowired SeasonF1DetailsRepository seasonF1DetailsRepository;
   @Autowired SeasonJ1DetailsRepository seasonJ1DetailsRepository;
   @Autowired SeasonIHPDetailRepository seasonIHPDetailRepository;
   @Autowired EntityManager entityManager;
   @Autowired SeasonStatusRepository seasonStatusRepository;
   @Autowired SeasonRepository seasonRepository;
   @Autowired DepartmentProgramRepository departmentProgramRepository;
   @Autowired PartnerStatusRepository partnerStatusRepository;
   @Autowired LoginRepository loginRepository;
   @Autowired PartnerNoteTopicRepository partnerNoteTopicRepository;
   @Autowired PartnerSeasonHelper partnerSeasonHelper;
   @Autowired PartnerStoredProcedure partnerStoredProcedure;

   private static final String SP_PARTNER_SEASON_APPLICATION_LIST = "call SPPartnerSeasonAplication(?)";
   private static final String SP_PARTNER_SEASON_ALLOCATION = "call SPPartnerSeasonPaxAllocated(?,?,?)";

   @Override
   public PartnerSeasons getPartnerSeasons(String partnerId) {
      PartnerSeasons partnerSeasons = new PartnerSeasons();
      if (partnerId == null) {
         partnerSeasons.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, CCIConstants.NULL_PARAM,
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
                  // department program option table
                  partnerProgramOption.setPartnerProgramOptionId(entity.getDepartmentProgram().getDepartmentProgramId());
                  partnerProgramOption.setPartnerProgramOption(entity.getDepartmentProgram().getProgramName());

                  if (entity.getDepartmentProgram() != null && entity.getDepartmentProgram().getDepartmentProgramOptions() != null) {
                     StringBuilder st = new StringBuilder();
                     int i = 0;
                     for (DepartmentProgramOption o : entity.getDepartmentProgram().getDepartmentProgramOptions()) {
                        if (i++ > 0) {
                           st.append(",");
                        } else {
                           st.append(o.getProgramOptionCode());
                        }
                     }
                  }
                  PartnerSeasonDepartment partnerSeasonDepartment = new PartnerSeasonDepartment();
                  partnerSeasonDepartment.setPartnerSeasonDepartmentId(entity.getDepartmentProgram().getLookupDepartment().getDepartmentId());
                  partnerSeasonDepartment.setPartnerSeasonDepartmentCode(entity.getDepartmentProgram().getLookupDepartment().getAcronym());
                  partnerSeasonDepartment.setPartnerSeasonDepartmentName(entity.getDepartmentProgram().getLookupDepartment().getDepartmentName());

                  PartnerSeasonProgramStatus seasonProgramStatus = new PartnerSeasonProgramStatus();

                  PartnerSeason pSeason = new PartnerSeason();

                  if (entity.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_J1_HS)) {
                     SeasonJ1Detail j1detail = seasonJ1DetailsRepository.findJ1DetailsBySeasonId(entity.getSeason().getSeasonId());
                     if (j1detail != null) {
                        Query query = entityManager.createNativeQuery(SP_PARTNER_SEASON_ALLOCATION);
                        query.setParameter(1, Integer.valueOf(partnerId));
                        query.setParameter(2, entity.getSeason().getSeasonId());
                        query.setParameter(3, CCIConstants.HSP_J1_HS_ID);
                        String allocation = "none";
                        Object result = query.getResultList();
                        if (result != null) {
                           allocation = result.toString();
                        }
                        pSeason.setParticipantAllocated(allocation);
                        pSeason.setPartnerSeasonProgramName(j1detail.getProgramName());
                        pSeason.setDetailsUrl("/partner/season/view/j1hs/");

                        seasonProgramStatus.setPartnerSeasonProgramStatusId(j1detail.getSeasonStatus().getSeasonStatusId());
                        seasonProgramStatus.setPartnerSeasonProgramStatus(j1detail.getSeasonStatus().getStatus());

                        pSeason.setPartnerStartDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonStartDate()));
                        pSeason.setPartnerEndDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonEndDate()));
                        pSeason.setPartnerApplicationDeadlineDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonAppDeadlineDate()));
                     }
                  }
                  if (entity.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_F1)) {
                     SeasonF1Detail f1Detail = seasonF1DetailsRepository.getAllSeasonF1DetailById(entity.getSeason().getSeasonId());
                     if (f1Detail != null) {
                        Query query = entityManager.createNativeQuery(SP_PARTNER_SEASON_ALLOCATION);
                        query.setParameter(1, Integer.valueOf(partnerId));
                        query.setParameter(2, entity.getSeason().getSeasonId());
                        query.setParameter(3, CCIConstants.HSP_F1_ID);
                        String allocation = "none";
                        Object result = query.getResultList();
                        if (result != null) {
                           allocation = result.toString();
                        }
                        pSeason.setParticipantAllocated(allocation);
                        pSeason.setPartnerSeasonProgramName(f1Detail.getProgramName());
                        pSeason.setDetailsUrl("/partner/season/view/f1/");

                        seasonProgramStatus.setPartnerSeasonProgramStatusId(f1Detail.getSeasonStatus().getSeasonStatusId());
                        seasonProgramStatus.setPartnerSeasonProgramStatus(f1Detail.getSeasonStatus().getStatus());

                        pSeason.setPartnerStartDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonStartDate()));
                        pSeason.setPartnerEndDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonEndDate()));
                        pSeason.setPartnerApplicationDeadlineDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonAppDeadlineDate()));
                     }
                  }
                  if (entity.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_STP_IHP)) {
                     SeasonIHPDetail ihpSeason = seasonIHPDetailRepository.findIHPDetailsBySeasonId(entity.getSeason().getSeasonId());
                     if (ihpSeason != null) {
                        Query query = entityManager.createNativeQuery(SP_PARTNER_SEASON_ALLOCATION);
                        query.setParameter(1, Integer.valueOf(partnerId));
                        query.setParameter(2, entity.getSeason().getSeasonId());
                        query.setParameter(3, CCIConstants.HSP_STP_IHP_ID);
                        String allocation = "none";
                        Object result = query.getResultList();
                        if (result != null) {
                           allocation = result.toString();
                        }
                        pSeason.setParticipantAllocated(allocation);
                        pSeason.setParticipantAllocated(allocation);
                        pSeason.setPartnerSeasonProgramName(ihpSeason.getProgramName());
                        pSeason.setDetailsUrl("/partner/season/view/ihp/");

                        seasonProgramStatus.setPartnerSeasonProgramStatusId(ihpSeason.getSeasonStatus().getSeasonStatusId());
                        seasonProgramStatus.setPartnerSeasonProgramStatus(ihpSeason.getSeasonStatus().getStatus());

                        pSeason.setPartnerStartDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonStartDate()));
                        pSeason.setPartnerEndDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonEndDate()));
                        pSeason.setPartnerApplicationDeadlineDate("N/A");
                     }
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

                  pSeason.setPartnerSeasonDepartment(partnerSeasonDepartment);
                  pSeason.setPartnerProgramOption(partnerProgramOption);
                  pSeason.setSeasonProgramStatus(seasonProgramStatus);
                  partnerSeasonsUIList.add(pSeason);
               }
            }
            partnerSeasons.setPartnerSeasonsCount(count);
            partnerSeasons.getPartnerSeasons().addAll(partnerSeasonsUIList);
            partnerSeasons.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            partnerSeasons.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD_IN_DB, CCIConstants.TYPE_INFO, CCIConstants.NO_DATA_CODE,
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         partnerSeasons.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.EXCEPTION_FETCHING_PARTNER_SEASON_LIST.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_LIST)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_LIST));
      }
      return partnerSeasons;
   }

   @Override
   public PartnerSeasonDetail viewJ1HSPartnerSeason(String partnerSeasonId) {
      PartnerSeasonDetail partnersSeasonDetails = new PartnerSeasonDetail();
      if (partnerSeasonId == null || Integer.valueOf(partnerSeasonId) == 0 || Integer.valueOf(partnerSeasonId) < 0) {
         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, CCIConstants.INVALID_PARAM,
               messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_REQUEST_PARAMS)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_REQUEST_PARAMS));
      }
      try {
         com.ccighgo.db.entities.PartnerSeason seasonDetail = partnerSeasonsRepository.findOne(Integer.valueOf(partnerSeasonId));
         if (seasonDetail.getPartner().getPartnerAnnouncements() != null && seasonDetail.getPartner().getPartnerAnnouncements().size() > 0) {
            for (PartnerAnnouncement announcement : seasonDetail.getPartner().getPartnerAnnouncements()) {
               if (seasonDetail.getPartner().getPartnerGoId().equals(announcement.getPartner().getPartnerGoId())
                     && seasonDetail.getSeason().getSeasonId().equals(announcement.getSeason().getSeasonId())
                     && seasonDetail.getDepartmentProgram().getDepartmentProgramId().equals(announcement.getDepartmentProgram().getDepartmentProgramId())) {
                  PartnerSeasonAnnouncements seasonAnnouncement = new PartnerSeasonAnnouncements();
                  seasonAnnouncement.setAnnouncement(announcement.getAnnouncement());
                  seasonAnnouncement.setTimestamp(String.valueOf(announcement.getCreatedOn().getTime()));
                  partnersSeasonDetails.getAnnouncements().add(seasonAnnouncement);
               }
            }
         }

         PartnerSeasonStatus partnerSeasonStatus = new PartnerSeasonStatus();
         partnerSeasonStatus.setPartnerSeasonStatusId(seasonDetail.getPartnerStatus1().getPartnerStatusId());
         partnerSeasonStatus.setPartnerSeasonStatus(seasonDetail.getPartnerStatus1().getPartnerStatusName());

         com.ccighgo.service.transport.partner.beans.partnerseasondetail.SeasonStatus seasonStatus = new com.ccighgo.service.transport.partner.beans.partnerseasondetail.SeasonStatus();
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findJ1DetailsBySeasonId(seasonDetail.getSeason().getSeasonId());
         if (seasonJ1Detail != null) {
            seasonStatus.setSeasonStatusId(seasonJ1Detail.getSeasonStatus().getSeasonStatusId());
            seasonStatus.setSeasonStatus(seasonJ1Detail.getSeasonStatus().getStatus());
         }
         partnersSeasonDetails.setSeasonStatus(seasonStatus);
         partnersSeasonDetails.setPartnerActiveSeason(seasonDetail.getActive() == 1);

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

         ApplicationDeadlilneDatesAllocations dla = new ApplicationDeadlilneDatesAllocations();
         if (seasonDetail.getPartnerSeasonAppDeadlineDate() != null)
            dla.setAugStartDeadlineDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonAppDeadlineDate()));
         if (seasonDetail.getPartnerSeasonExtAppDeadlineDate() != null)
            dla.setAugStartDeadlineDateRequested(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonExtAppDeadlineDate()));
         if (seasonDetail.getPartnerStatus2() != null) {
            dla.setAugStartDeadlineStatus(seasonDetail.getPartnerStatus2().getPartnerStatusName());
            dla.setAugStartDeadlineStatusId(seasonDetail.getPartnerStatus2().getPartnerStatusId());
         }
         if (seasonDetail.getPartnerSeasonSecSemDeadlineDate() != null)
            dla.setJanStartDeadlineDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonSecSemDeadlineDate()));
         if (seasonDetail.getPartnerSeasonExtSecSemDeadlineDate() != null)
            dla.setJanStartDeadlineDateRequested(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonExtSecSemDeadlineDate()));
         if (seasonDetail.getPartnerStatus3() != null) {
            dla.setJanStartDeadlineStatus(seasonDetail.getPartnerStatus3().getPartnerStatusName());
            dla.setJanStartDeadlineStatusId(seasonDetail.getPartnerStatus3().getPartnerStatusId());
         }
         partnersSeasonDetails.setApplicationDeadlineDatesAllocations(dla);

         PartnerSeasonJ1HSProgramAllocations programAllocations = new PartnerSeasonJ1HSProgramAllocations();
         List<PartnerSeasonAllocation> partnerSeasonAllocationList = partnerSeasonAllocationRepository.findPartnerSeasonAllocation(Integer.valueOf(partnerSeasonId));
         if (partnerSeasonAllocationList != null) {
            for (PartnerSeasonAllocation unGuaranteedAllocation : partnerSeasonAllocationList) {

               if (unGuaranteedAllocation.getDepartmentProgramOption() != null
                     && unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_J1_HS_ID) {
                  if (unGuaranteedAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_J1)) {
                     programAllocations.setAugustStartMaxguaranteedParticipants(unGuaranteedAllocation.getMaxGuaranteedPax());
                     programAllocations.setAugustStartRequestedMaxguaranteedParticipants(unGuaranteedAllocation.getRequestedMaxGuaranteedPax());

                     programAllocations.setAugustStartMaxUnguaranteedParticipants(unGuaranteedAllocation.getMaxPax());
                     programAllocations.setAugustStartRequestedMaxUnguaranteedParticipants(unGuaranteedAllocation.getRequestedMaxPax());
                     programAllocations.setAugustStartAllocationId(unGuaranteedAllocation.getPartnerSeasonAllocationId());
                     if (unGuaranteedAllocation.getPartnerStatus() != null) {
                        programAllocations.setAugustStartStatus(unGuaranteedAllocation.getPartnerStatus().getPartnerStatusName());
                        programAllocations.setAugustStartStatusId(unGuaranteedAllocation.getPartnerStatus().getPartnerStatusId());
                     }
                     if (unGuaranteedAllocation.getPartnerSeason().getPartner() != null && unGuaranteedAllocation.getDepartmentProgramOption() != null
                           && unGuaranteedAllocation.getDepartmentProgramOption() != null) {
                        programAllocations.setAugustStartAcceptedParticipants(partnerStoredProcedure.partnerSeasonAcceptedPax(unGuaranteedAllocation.getPartnerSeason()
                              .getPartner().getPartnerGoId(), Integer.valueOf(partnerSeasonId), unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgram()
                              .getDepartmentProgramId(), unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgramOptionId()));
                        programAllocations.setAugustStartUnderCCIReview(partnerStoredProcedure.partnerSeasonCCIReviewPax(unGuaranteedAllocation.getPartnerSeason().getPartner()
                              .getPartnerGoId(), Integer.valueOf(partnerSeasonId), unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgram()
                              .getDepartmentProgramId(), unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgramOptionId()));
                        programAllocations.setAugustStartOpenings(partnerStoredProcedure.partnerSeasonPaxOpenings(unGuaranteedAllocation.getPartnerSeason().getPartner()
                              .getPartnerGoId(), Integer.valueOf(partnerSeasonId), unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgram()
                              .getDepartmentProgramId(), unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgramOptionId()));
                     }
                     programAllocations.setTotalMaxguaranteedParticipants(programAllocations.getTotalMaxguaranteedParticipants()
                           + programAllocations.getAugustStartMaxguaranteedParticipants());
                     programAllocations.setTotalMaxUnguaranteedParticipants(programAllocations.getTotalMaxUnguaranteedParticipants()
                           + programAllocations.getAugustStartMaxUnguaranteedParticipants());
                     unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgramOptionId();
                     programAllocations.setTotalAcceptedParticipants(programAllocations.getTotalAcceptedParticipants() + programAllocations.getAugustStartAcceptedParticipants());
                     programAllocations.setTotalUnderCCIReview(programAllocations.getTotalUnderCCIReview() + programAllocations.getAugustStartUnderCCIReview());
                     programAllocations.setTotalOpenings(programAllocations.getJanuaryStartOpenings() + programAllocations.getAugustStartOpenings());

                  }
                  if (unGuaranteedAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_J1)) {
                     programAllocations.setJanuaryStartMaxguaranteedParticipants(unGuaranteedAllocation.getMaxGuaranteedPax());
                     programAllocations.setJanuaryStartRequestedMaxguaranteedParticipants(unGuaranteedAllocation.getRequestedMaxGuaranteedPax());
                     programAllocations.setJanStartAllocationId(unGuaranteedAllocation.getPartnerSeasonAllocationId());
                     programAllocations.setJanuaryStartMaxUnguaranteedParticipants(unGuaranteedAllocation.getMaxPax());
                     programAllocations.setJanuaryStartRequestedMaxUnguaranteedParticipants(unGuaranteedAllocation.getRequestedMaxPax());
                     if (unGuaranteedAllocation.getPartnerStatus() != null) {
                        programAllocations.setJanuaryStartStatus(unGuaranteedAllocation.getPartnerStatus().getPartnerStatusName());
                        programAllocations.setJanuaryStartStatusId(unGuaranteedAllocation.getPartnerStatus().getPartnerStatusId());
                     }
                     if (unGuaranteedAllocation.getPartnerSeason().getPartner() != null && unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgram() != null
                           && unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgram() != null) {
                        programAllocations.setJanuaryStartAcceptedParticipants(partnerStoredProcedure.partnerSeasonAcceptedPax(unGuaranteedAllocation.getPartnerSeason()
                              .getPartner().getPartnerGoId(), Integer.valueOf(partnerSeasonId), unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgram()
                              .getDepartmentProgramId(), unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgramOptionId()));
                        programAllocations.setJanuaryStartUnderCCIReview(partnerStoredProcedure.partnerSeasonCCIReviewPax(unGuaranteedAllocation.getPartnerSeason().getPartner()
                              .getPartnerGoId(), Integer.valueOf(partnerSeasonId), unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgram()
                              .getDepartmentProgramId(), unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgramOptionId()));
                        programAllocations.setJanuaryStartOpenings(partnerStoredProcedure.partnerSeasonPaxOpenings(unGuaranteedAllocation.getPartnerSeason().getPartner()
                              .getPartnerGoId(), Integer.valueOf(partnerSeasonId), unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgram()
                              .getDepartmentProgramId(), unGuaranteedAllocation.getDepartmentProgramOption().getDepartmentProgramOptionId()));
                     }

                     programAllocations.setTotalMaxguaranteedParticipants(programAllocations.getTotalMaxguaranteedParticipants()
                           + programAllocations.getJanuaryStartMaxguaranteedParticipants());
                     programAllocations.setTotalMaxUnguaranteedParticipants(programAllocations.getTotalMaxUnguaranteedParticipants()
                           + programAllocations.getJanuaryStartMaxUnguaranteedParticipants());
                     programAllocations.setTotalAcceptedParticipants(programAllocations.getTotalAcceptedParticipants() + programAllocations.getJanuaryStartAcceptedParticipants());
                     programAllocations.setTotalUnderCCIReview(programAllocations.getTotalUnderCCIReview() + programAllocations.getJanuaryStartUnderCCIReview());
                     programAllocations.setTotalOpenings(programAllocations.getJanuaryStartOpenings() + programAllocations.getJanuaryStartOpenings());
                  }
               }

            }
            partnersSeasonDetails.setProgramAllocations(programAllocations);
         }

         partnersSeasonDetails.setPartnerSeasonId(seasonDetail.getPartnerSeasonId());
         partnersSeasonDetails.setPartnerSeasonProgramName(partnerSeasonProgramName);
         partnersSeasonDetails.setPartnerDepartment(partnerDepartment);
         partnersSeasonDetails.setPartnerProgram(partnerProgram);
         partnersSeasonDetails.setPartnerHLSeason(partnerHLSeason);
         partnersSeasonDetails.setPartnerSeasonStatus(partnerSeasonStatus);
         partnersSeasonDetails.setInsuranceProvidedBy(seasonDetail.getInsuranceProvidedByCCI() == CCIConstants.ACTIVE ? true : false);
         partnersSeasonDetails.setSevisFeesPaidBy(seasonDetail.getSevisFeesPaidByCCI() == CCIConstants.ACTIVE ? true : false);
         partnersSeasonDetails.setSeasonStartDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonStartDate()));
         partnersSeasonDetails.setSeasonEndDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonEndDate()));

         com.ccighgo.service.transport.partner.beans.partnerseasondetail.NoteTopics partnerSeasonNotes = partnerSeasonHelper.getJ1Notes(String.valueOf(seasonDetail.getPartner()
               .getPartnerGoId()));
         partnersSeasonDetails.setPartnerSeasonNotes(partnerSeasonNotes);
         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (CcighgoException e) {
         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_VIEWING_J1_PARTNER_SEASON_DETAILS.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_DETAILS)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_DETAILS));
      }
      return partnersSeasonDetails;
   }

   @Override
   public PartnerSeasonApplicationList getPartnerSeasonApplicationList(String partnerId) {
      PartnerSeasonApplicationList partnerSeasonApplicationList = new PartnerSeasonApplicationList();
      if (partnerId == null) {
         partnerSeasonApplicationList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, CCIConstants.NULL_PARAM,
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
               // position 0 : programName, position 1, position 2 seasonId:
               // departmentProgramId
               PartnerSeasonApplication application = new PartnerSeasonApplication();
               application.setProgramName(obj[0].toString());
               application.setSeasonId(Integer.valueOf(obj[1].toString()));
               application.setDepartmentProgramId(Integer.valueOf(obj[2].toString()));
               application.setAcronym(obj[3].toString());
               application.setDeptProgramName(obj[4].toString());
               application.setStartDate(obj[5].toString());
               application.setEndDate(obj[6].toString());
               application.setAppDeadlineDate(obj[7].toString());
               partnerSeasonApplication.add(application);
            }
            partnerSeasonApplicationList.getPartnerSeasonApplication().addAll(partnerSeasonApplication);
            partnerSeasonApplicationList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            partnerSeasonApplicationList.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD_IN_DB, CCIConstants.TYPE_INFO, CCIConstants.NO_DATA_CODE,
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         partnerSeasonApplicationList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
               PartnerCodes.ERROR_GET_PARTNER_SEASON_APPLICATION_LIST.getValue(), messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_DETAILS)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_DETAILS));
      }
      return partnerSeasonApplicationList;
   }

   @Override
   public PartnerSeasonF1Detail viewF1PartnerSeason(String partnerSeasonId) {
      PartnerSeasonF1Detail partnersSeasonDetails = new PartnerSeasonF1Detail();
      if (partnerSeasonId == null || Integer.valueOf(partnerSeasonId) == 0 || Integer.valueOf(partnerSeasonId) < 0) {
         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, CCIConstants.INVALID_PARAM,
               messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_REQUEST_PARAMS)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_REQUEST_PARAMS));
      }
      try {
         com.ccighgo.db.entities.PartnerSeason seasonDetail = partnerSeasonsRepository.findOne(Integer.valueOf(partnerSeasonId));
         if (seasonDetail.getPartner().getPartnerAnnouncements() != null && seasonDetail.getPartner().getPartnerAnnouncements().size() > 0) {
            for (PartnerAnnouncement announcement : seasonDetail.getPartner().getPartnerAnnouncements()) {
               if (seasonDetail.getPartner().getPartnerGoId() == announcement.getPartner().getPartnerGoId()
                     && seasonDetail.getSeason().getSeasonId() == announcement.getSeason().getSeasonId()
                     && seasonDetail.getDepartmentProgram().getDepartmentProgramId() == announcement.getDepartmentProgram().getDepartmentProgramId()) {
                  com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.PartnerSeasonAnnouncements seasonAnnouncement = new com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.PartnerSeasonAnnouncements();
                  seasonAnnouncement.setPartnerSeasonAnnouncement(announcement.getAnnouncement());
                  seasonAnnouncement.setAnnouncementDate(DateUtils.getMMddyyDate(announcement.getCreatedOn()));
                  partnersSeasonDetails.getPartnerSeasonAnnouncement().add(seasonAnnouncement);
               }
            }
         }
         // get partner season status
         com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.PartnerSeasonStatus partnerSeasonStatus = new com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.PartnerSeasonStatus();
         partnerSeasonStatus.setPartnerSeasonStatusId(seasonDetail.getPartnerStatus1().getPartnerStatusId());
         partnerSeasonStatus.setPartnerSeasonStatus(seasonDetail.getPartnerStatus1().getPartnerStatusName());

         com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.SeasonStatus seasonStatus = new com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.SeasonStatus();
         SeasonF1Detail seasonF1Detail = seasonF1DetailsRepository.getAllSeasonF1DetailById(seasonDetail.getSeason().getSeasonId());
         if (seasonF1Detail != null) {
            seasonStatus.setSeasonStatusId(seasonF1Detail.getSeasonStatus().getSeasonStatusId());
            seasonStatus.setSeasonStatus(seasonF1Detail.getSeasonStatus().getStatus());
         }
         partnersSeasonDetails.setSeasonStatus(seasonStatus);
         partnersSeasonDetails.setPartnerActiveSeason(seasonDetail.getActive() == 1);

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

         ApplicationDeadlilneDatesF1Allocations dla = new ApplicationDeadlilneDatesF1Allocations();

         if (seasonDetail.getPartnerSeasonAppDeadlineDate() != null)
            dla.setAugStartDeadlineDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonAppDeadlineDate()));
         if (seasonDetail.getPartnerSeasonExtAppDeadlineDate() != null)
            dla.setAugStartDeadlineDateRequested(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonExtAppDeadlineDate()));
         if (seasonDetail.getPartnerStatus2() != null)
            dla.setAugStartDeadlineStatus(seasonDetail.getPartnerStatus2().getPartnerStatusName());
         if (seasonDetail.getPartnerSeasonSecSemDeadlineDate() != null)
            dla.setJanStartDeadlineDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonSecSemDeadlineDate()));
         if (seasonDetail.getPartnerSeasonExtSecSemDeadlineDate() != null)
            dla.setJanStartDeadlineDateRequested(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonExtSecSemDeadlineDate()));
         if (seasonDetail.getPartnerStatus3() != null)
            dla.setJanStartDeadlineStatus(seasonDetail.getPartnerStatus3().getPartnerStatusName());
         partnersSeasonDetails.setApplicationDeadlineDatesAllocations(dla);

         PartnerSeasonF1ProgramAllocations programAllocations = new PartnerSeasonF1ProgramAllocations();
         List<PartnerSeasonAllocation> partnerSeasonAllocationList = partnerSeasonAllocationRepository.findPartnerSeasonAllocation(Integer.valueOf(partnerSeasonId));
         if (partnerSeasonAllocationList != null) {

            for (PartnerSeasonAllocation guaranteedAllocation : partnerSeasonAllocationList) {
               if (guaranteedAllocation.getDepartmentProgramOption() != null
                     && guaranteedAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_F1_ID) {
                  if (guaranteedAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_F1)) {
                     programAllocations.setAugustStartMaxguaranteedParticipants(guaranteedAllocation.getMaxGuaranteedPax());
                     programAllocations.setAugustStartRequestedMaxguaranteedParticipants(guaranteedAllocation.getRequestedMaxGuaranteedPax());

                     programAllocations.setAugustStartAllocationId(guaranteedAllocation.getPartnerSeasonAllocationId());
                     if (guaranteedAllocation.getPartnerStatus() != null)
                        programAllocations.setAugustStartStatus(guaranteedAllocation.getPartnerStatus().getPartnerStatusName());

                     programAllocations.setTotalMaxguaranteedParticipants(programAllocations.getTotalMaxguaranteedParticipants()
                           + programAllocations.getAugustStartMaxguaranteedParticipants());

                  }
                  if (guaranteedAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_F1)) {
                     programAllocations.setJanuaryStartMaxguaranteedParticipants(guaranteedAllocation.getMaxGuaranteedPax());
                     programAllocations.setJanuaryStartRequestedMaxguaranteedParticipants(guaranteedAllocation.getRequestedMaxGuaranteedPax());
                     programAllocations.setJanStartAllocationId(guaranteedAllocation.getPartnerSeasonAllocationId());
                     if (guaranteedAllocation.getPartnerStatus() != null)
                        programAllocations.setJanuaryStartStatus(guaranteedAllocation.getPartnerStatus().getPartnerStatusName());

                     programAllocations.setTotalMaxguaranteedParticipants(programAllocations.getTotalMaxguaranteedParticipants()
                           + programAllocations.getJanuaryStartMaxguaranteedParticipants());

                  }
               }
            }
            partnersSeasonDetails.setProgramAllocations(programAllocations);
         }

         partnersSeasonDetails.setPartnerSeasonId(seasonDetail.getPartnerSeasonId());
         partnersSeasonDetails.setPartnerSeasonProgramName(partnerSeasonProgramName);
         partnersSeasonDetails.setPartnerDepartment(partnerDepartment);
         partnersSeasonDetails.setPartnerProgram(partnerProgram);
         partnersSeasonDetails.setPartnerHLSeason(partnerHLSeason);
         partnersSeasonDetails.setPartnerSeasonStatus(partnerSeasonStatus);
         partnersSeasonDetails.setInsuranceProvidedBy(seasonDetail.getInsuranceProvidedByCCI() == CCIConstants.ACTIVE ? true : false);
         partnersSeasonDetails.setSevisFeesPaidBy(seasonDetail.getSevisFeesPaidByCCI() == CCIConstants.ACTIVE ? true : false);
         partnersSeasonDetails.setSeasonStartDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonStartDate()));
         partnersSeasonDetails.setSeasonEndDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonEndDate()));

         com.ccighgo.service.transport.partner.beans.partnerseasonf1detail.NoteTopics partnerSeasonNotes = partnerSeasonHelper.getF1ProgramNotes(String.valueOf(seasonDetail
               .getPartner().getPartnerGoId()));
         partnersSeasonDetails.setPartnerSeasonNotes(partnerSeasonNotes);

         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (CcighgoException e) {
         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_VIEWING_F1_PARTNER_SEASON_DETAILS.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_DETAILS)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_DETAILS));
      }
      return partnersSeasonDetails;
   }

   @Override
   public WSDefaultResponse createNewPartnerAllocationRequest(NewPartnerSeasonAllocationRequest newPartnerSeasonAllocationRequest) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {

         PartnerSeasonAllocation janAllocation = partnerSeasonAllocationRepository.findOne(newPartnerSeasonAllocationRequest.getJanAllocationId());
         if (janAllocation != null) {
            janAllocation.setRequestedMaxGuaranteedPax(newPartnerSeasonAllocationRequest.getJanuaryStartRequestedMaxguaranteedParticipants());
            janAllocation.setRequestedMaxPax(newPartnerSeasonAllocationRequest.getJanuaryStartRequestedMaxUnguaranteedParticipants());
            janAllocation.setPartnerStatus(partnerStatusRepository.findOne(CCIConstants.PENDING_STATUS));
            partnerSeasonAllocationRepository.saveAndFlush(janAllocation);
         }

         PartnerSeasonAllocation augustAllocation = partnerSeasonAllocationRepository.findOne(newPartnerSeasonAllocationRequest.getAugustAllocationId());
         if (augustAllocation != null) {
            augustAllocation.setRequestedMaxGuaranteedPax(newPartnerSeasonAllocationRequest.getAugustStartRequestedMaxguaranteedParticipants());
            augustAllocation.setRequestedMaxPax(newPartnerSeasonAllocationRequest.getAugustStartRequestedMaxUnguaranteedParticipants());
            augustAllocation.setPartnerStatus(partnerStatusRepository.findOne(CCIConstants.PENDING_STATUS));
            partnerSeasonAllocationRepository.saveAndFlush(augustAllocation);
         }
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.CANT_CREATE_NEWPARTNER_ALLOCATION.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_CREATING_NEWPARTNER_ALLOCATION)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_CREATING_NEWPARTNER_ALLOCATION));
      }
      return wsDefaultResponse;
   }

   @Override
   public WSDefaultResponse createNewDeadlineDateRequest(NewPartnerApplicationDeadLineDate newApplicationDeadlineDatesAllocations) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         com.ccighgo.db.entities.PartnerSeason partnerSeason = partnerSeasonsRepository.findOne(newApplicationDeadlineDatesAllocations.getPartnerSeasonId());
         boolean extAppDeadlineModified = false;
         boolean extSecSemDeadlineModified = false;

         if (newApplicationDeadlineDatesAllocations.getAugStartDeadlineDateRequested() != null
               && !newApplicationDeadlineDatesAllocations.getAugStartDeadlineDateRequested().isEmpty()) {
            partnerSeason.setPartnerSeasonExtAppDeadlineDate(DateUtils.getDateFromString(newApplicationDeadlineDatesAllocations.getAugStartDeadlineDateRequested()));
            extAppDeadlineModified = true;
            partnerSeason.setPartnerStatus2(partnerStatusRepository.findOne(CCIConstants.PENDING_STATUS));

         }

         if (newApplicationDeadlineDatesAllocations.getJanStartDeadlineDateRequested() != null
               && !newApplicationDeadlineDatesAllocations.getJanStartDeadlineDateRequested().isEmpty()) {
            partnerSeason.setPartnerSeasonExtSecSemDeadlineDate(DateUtils.getDateFromString(newApplicationDeadlineDatesAllocations.getJanStartDeadlineDateRequested()));
            extSecSemDeadlineModified = true;
            partnerSeason.setPartnerStatus3(partnerStatusRepository.findOne(CCIConstants.PENDING_STATUS));

         }

         if (extAppDeadlineModified || extSecSemDeadlineModified) {
            partnerSeasonsRepository.saveAndFlush(partnerSeason);
            wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.NO_UPDATE, CCIConstants.TYPE_INFO, PartnerCodes.NO_CHANGE_HAPPEN.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.CANT_CREATE_NEW_DEALINE_DATE_REQUEST.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_CREATING_NEW_DEALINE_DATE_REQUEST)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_CREATING_NEW_DEALINE_DATE_REQUEST));
      }
      return wsDefaultResponse;
   }

   @Override
   @Transactional
   public Response addNewSeasonsToPartner(PartnerSeasonApplicationList partnerSeasonApplicationList) {
      Response response = new Response();
      if (partnerSeasonApplicationList == null) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, CCIConstants.NULL_PARAM, "cannot add null values, please check the list"));
         LOGGER.error("empty list to add seasons");
         return response;
      } else {
         try {
            List<com.ccighgo.db.entities.PartnerSeason> partnerSeasonsList = new ArrayList<com.ccighgo.db.entities.PartnerSeason>();
            for (PartnerSeasonApplication season : partnerSeasonApplicationList.getPartnerSeasonApplication()) {
               // discussed with phani setting only season, department program
               // and boolean fields to false
               com.ccighgo.db.entities.PartnerSeason ps = new com.ccighgo.db.entities.PartnerSeason();
               ps.setPartner(partnerRepository.findOne(partnerSeasonApplicationList.getPartnerId()));
               ps.setSeason(seasonRepository.findOne(Integer.valueOf(season.getSeasonId())));
               ps.setDepartmentProgram(departmentProgramRepository.findOne(Integer.valueOf(season.getDepartmentProgramId())));
               ps.setPartnerStatus1(partnerStatusRepository.findOne(4));
               ps.setPartnerSeasonStartDate(DateUtils.getDateFromString(season.getStartDate()));
               ps.setPartnerSeasonEndDate(DateUtils.getDateFromString(season.getEndDate()));
               ps.setPartnerSeasonAppDeadlineDate(DateUtils.getDateFromString(season.getAppDeadlineDate()));
               ps.setInsuranceProvidedByCCI(CCIConstants.INACTIVE);
               ps.setSevisFeesPaidByCCI(CCIConstants.INACTIVE);
               ps.setQuestionaireRequired(CCIConstants.INACTIVE);
               ps.setDisableAddParticipant(CCIConstants.INACTIVE);
               ps.setParticipantPaysDeposit(CCIConstants.INACTIVE);
               ps.setCanAccessJobBoard(CCIConstants.INACTIVE);
               ps.setCanCreateSubPartner(CCIConstants.INACTIVE);
               ps.setIsSignedContract(CCIConstants.INACTIVE);
               ps.setActive(CCIConstants.ACTIVE);
               ps.setCreatedBy(partnerSeasonApplicationList.getLoggedInUserLoginId());
               ps.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               ps.setModifiedBy(partnerSeasonApplicationList.getLoggedInUserLoginId());
               ps.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               partnerSeasonsList.add(ps);
            }
            partnerSeasonsRepository.save(partnerSeasonsList);
            partnerSeasonsRepository.flush();
            response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } catch (CcighgoException e) {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_ADDING_PARTNER_TO_SEASON.getValue(),
                  messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS)));
            LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS));
         }
      }
      return response;
   }

   @Override
   public PartnerSeasonIHPDetail viewIHPPartnerSeason(String partnerSeasonId) {
      PartnerSeasonIHPDetail partnersSeasonDetails = new PartnerSeasonIHPDetail();
      if (partnerSeasonId == null || Integer.valueOf(partnerSeasonId) == 0 || Integer.valueOf(partnerSeasonId) < 0) {
         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, CCIConstants.INVALID_PARAM,
               messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_REQUEST_PARAMS)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_REQUEST_PARAMS));
      }
      try {
         com.ccighgo.db.entities.PartnerSeason seasonDetail = partnerSeasonsRepository.findOne(Integer.valueOf(partnerSeasonId));
         if (seasonDetail.getPartner().getPartnerAnnouncements() != null && seasonDetail.getPartner().getPartnerAnnouncements().size() > 0) {
            for (PartnerAnnouncement announcement : seasonDetail.getPartner().getPartnerAnnouncements()) {
               if (seasonDetail.getPartner().getPartnerGoId() == announcement.getPartner().getPartnerGoId()
                     && seasonDetail.getSeason().getSeasonId() == announcement.getSeason().getSeasonId()
                     && seasonDetail.getDepartmentProgram().getDepartmentProgramId() == announcement.getDepartmentProgram().getDepartmentProgramId()) {
                  com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.PartnerSeasonAnnouncements seasonAnnouncement = new com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.PartnerSeasonAnnouncements();
                  seasonAnnouncement.setPartnerSeasonAnnouncement(announcement.getAnnouncement());
                  seasonAnnouncement.setAnnouncementDate(DateUtils.getMMddyyDate(announcement.getCreatedOn()));
                  partnersSeasonDetails.getPartnerSeasonAnnouncement().add(seasonAnnouncement);
               }
            }
         }
         // get partner season status
         com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.PartnerSeasonStatus partnerSeasonStatus = new com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.PartnerSeasonStatus();
         partnerSeasonStatus.setPartnerSeasonStatusId(seasonDetail.getPartnerStatus1().getPartnerStatusId());
         partnerSeasonStatus.setPartnerSeasonStatus(seasonDetail.getPartnerStatus1().getPartnerStatusName());

         com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.SeasonStatus seasonStatus = new com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.SeasonStatus();
         SeasonIHPDetail seasonIHPDetail = seasonIHPDetailRepository.findIHPDetailsBySeasonId(seasonDetail.getSeason().getSeasonId());
         if (seasonIHPDetail != null) {
            seasonStatus.setSeasonStatusId(seasonIHPDetail.getSeasonStatus().getSeasonStatusId());
            seasonStatus.setSeasonStatus(seasonIHPDetail.getSeasonStatus().getStatus());
         }
         partnersSeasonDetails.setSeasonStatus(seasonStatus);
         partnersSeasonDetails.setPartnerActiveSeason(seasonDetail.getActive() == 1);

         // get department
         com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.PartnerDepartment partnerDepartment = new com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.PartnerDepartment();
         partnerDepartment.setPartnerSeasonDepartmentId(seasonDetail.getDepartmentProgram().getLookupDepartment().getDepartmentId());
         partnerDepartment.setPartnerSeasonDepartmentCode(seasonDetail.getDepartmentProgram().getLookupDepartment().getAcronym());
         partnerDepartment.setPartnerSeasonDepartmentName(seasonDetail.getDepartmentProgram().getLookupDepartment().getDepartmentName());

         // get partner program
         com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.PartnerProgram partnerProgram = new com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.PartnerProgram();
         partnerProgram.setPartnerProgramId(seasonDetail.getDepartmentProgram().getDepartmentProgramId());
         partnerProgram.setPartnerProgram(seasonDetail.getDepartmentProgram().getProgramName());

         // get partner high level season
         com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.PartnerHLSeason partnerHLSeason = new com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.PartnerHLSeason();
         partnerHLSeason.setPartnerHLSeasonId(seasonDetail.getSeason().getSeasonId());
         partnerHLSeason.setPartnerHLSeasonName(seasonDetail.getSeason().getSeasonName());

         // partner season program name
         String partnerSeasonProgramName = null;
         if (seasonDetail.getSeason().getSeasonIhpdetails() != null && seasonDetail.getSeason().getSeasonIhpdetails().size() > 0) {
            partnerSeasonProgramName = seasonDetail.getSeason().getSeasonIhpdetails().get(0).getProgramName();
         }
         partnersSeasonDetails.setPartnerSeasonId(seasonDetail.getPartnerSeasonId());
         partnersSeasonDetails.setPartnerSeasonProgramName(partnerSeasonProgramName);
         partnersSeasonDetails.setPartnerDepartment(partnerDepartment);
         partnersSeasonDetails.setPartnerProgram(partnerProgram);
         partnersSeasonDetails.setPartnerHLSeason(partnerHLSeason);
         partnersSeasonDetails.setPartnerSeasonStatus(partnerSeasonStatus);
         partnersSeasonDetails.setSeasonStartDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonStartDate()));
         partnersSeasonDetails.setSeasonEndDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonEndDate()));

         com.ccighgo.service.transport.partner.beans.partnerseasonihpdetail.NoteTopics partnerSeasonNotes = partnerSeasonHelper.getIHPProgramNotes(String.valueOf(seasonDetail
               .getPartner().getPartnerGoId()));
         partnersSeasonDetails.setPartnerSeasonNotes(partnerSeasonNotes);

         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.ERROR_VIEWING_IHP_PARTNER_SEASON.getValue(),
               "error getting ihp details"));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS));
      }
      return partnersSeasonDetails;
   }

}
