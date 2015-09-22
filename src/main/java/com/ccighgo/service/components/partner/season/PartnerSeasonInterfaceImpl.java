/**
 * 
 */
package com.ccighgo.service.components.partner.season;

import java.util.ArrayList;
import java.util.List;

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
import com.ccighgo.service.components.errormessages.constants.PartnerSeasonMessageConstants;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeason;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonDepartment;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonProgramOption;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonProgramStatus;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasons;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerDepartment;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerHLSeason;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerProgram;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerSeasonAnnouncements;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerSeasonDetail;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerSeasonNotes;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerSeasonStatus;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.ProgramAllocationsGuaranteed;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.ProgramAllocationsUnguranteed;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;

/**
 * @author ravi
 *
 */
@Component
public class PartnerSeasonInterfaceImpl implements PartnerSeasonInterface {

   private static final Logger LOGGER = Logger.getLogger(PartnerSeasonInterfaceImpl.class);

   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;

   @Autowired PartnerRepository partnerRepository;
   @Autowired PartnerSeasonsRepository partnerSeasonsRepository;
   @Autowired PartnerSeasonAllocationRepository partnerSeasonAllocationRepository;
   @Autowired PartnerSeasonContractRepository partnerSeasonContractRepository;
   @Autowired PartnerSeasonDocumentRepository partnerSeasonDocumentRepository;

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
                  seasonProgramStatus.setPartnerSeasonProgramStatusId(entity.getPartnerStatus().getPartnerStatusId());
                  seasonProgramStatus.setPartnerSeasonProgramStatus(entity.getPartnerStatus().getPartnerStatusName());

                  PartnerSeason pSeason = new PartnerSeason();
                  pSeason.setParticipantAllocated("TODO:need clarification");
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_J1_HS)) {
                     pSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonJ1details().get(0).getProgramName());
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_F1)) {
                     pSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonF1details().get(0).getProgramName());
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_STP_IHP)) {
                     pSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonIhpdetails().get(0).getProgramName());
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_SUMMER)) {
                     pSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonWnTsummerDetails().get(0).getProgramName());
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_WINTER)) {
                     pSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonWnTwinterDetails().get(0).getProgramName());
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_SPRING)) {
                     pSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonWnTspringDetails().get(0).getProgramName());
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_CAP)) {
                     pSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonCapdetails().get(0).getProgramName());
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
   public PartnerSeasonDetail viewPartnerSeason(String partnerSeasonId) {
      PartnerSeasonDetail partnersSeasonDetails = new PartnerSeasonDetail();
      if (partnerSeasonId == null || Integer.valueOf(partnerSeasonId) == 0 || Integer.valueOf(partnerSeasonId) < 0) {
         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_REQUEST.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_REQUEST_PARAMS)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_REQUEST_PARAMS));
      }
      try {
         com.ccighgo.db.entities.PartnerSeason seasonDetail = partnerSeasonsRepository.findOne(Integer.valueOf(partnerSeasonId));
         //get announcements
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
         //get season status
         PartnerSeasonStatus partnerSeasonStatus = new PartnerSeasonStatus();
         partnerSeasonStatus.setPartnerSeasonStatusId(seasonDetail.getPartnerStatus().getPartnerStatusId());
         partnerSeasonStatus.setPartnerSeasonStatus(seasonDetail.getPartnerStatus().getPartnerStatusName());
         
         //get department
         PartnerDepartment partnerDepartment = new PartnerDepartment();
         partnerDepartment.setPartnerSeasonDepartmentId(seasonDetail.getDepartmentProgram().getLookupDepartment().getDepartmentId());
         partnerDepartment.setPartnerSeasonDepartmentCode(seasonDetail.getDepartmentProgram().getLookupDepartment().getAcronym());
         partnerDepartment.setPartnerSeasonDepartmentName(seasonDetail.getDepartmentProgram().getLookupDepartment().getDepartmentName());
         
         //get partner program
         PartnerProgram partnerProgram = new PartnerProgram();
         partnerProgram.setPartnerProgramId(seasonDetail.getDepartmentProgram().getDepartmentProgramId());
         partnerProgram.setPartnerProgram(seasonDetail.getDepartmentProgram().getProgramName());
         
         //get partner high level season
         PartnerHLSeason partnerHLSeason = new PartnerHLSeason();
         partnerHLSeason.setPartnerHLSeasonId(seasonDetail.getSeason().getSeasonId());
         partnerHLSeason.setPartnerHLSeasonName(seasonDetail.getSeason().getSeasonName());
         
         //partner season program name
         String partnerSeasonProgramName =null;
         if (seasonDetail.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_J1_HS)) {
            partnerSeasonProgramName = seasonDetail.getSeason().getSeasonJ1details().get(0).getProgramName();
         }
         if (seasonDetail.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_F1)) {
            partnerSeasonProgramName = seasonDetail.getSeason().getSeasonF1details().get(0).getProgramName();
         }
         if (seasonDetail.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_STP_IHP)) {
            partnerSeasonProgramName = seasonDetail.getSeason().getSeasonIhpdetails().get(0).getProgramName();
         }
         if (seasonDetail.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_SUMMER)) {
            partnerSeasonProgramName = seasonDetail.getSeason().getSeasonWnTsummerDetails().get(0).getProgramName();
         }
         if (seasonDetail.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_WINTER)) {
            partnerSeasonProgramName = seasonDetail.getSeason().getSeasonWnTwinterDetails().get(0).getProgramName();
         }
         if (seasonDetail.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_SPRING)) {
            partnerSeasonProgramName = seasonDetail.getSeason().getSeasonWnTspringDetails().get(0).getProgramName();
         }
         if (seasonDetail.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_CAP)) {
            partnerSeasonProgramName = seasonDetail.getSeason().getSeasonCapdetails().get(0).getProgramName();
         }
         
         //partner season allocation
         ProgramAllocationsUnguranteed programAllocationsUnguranteed = null;
         ProgramAllocationsGuaranteed programAllocationsGuaranteed = null;
         List<PartnerSeasonAllocation> partnerSeasonAllocationList = partnerSeasonAllocationRepository.findByPartnerSeasonId(Integer.valueOf(partnerSeasonId));
         if(partnerSeasonAllocationList!=null){
             programAllocationsUnguranteed = new ProgramAllocationsUnguranteed();
             programAllocationsGuaranteed = new ProgramAllocationsGuaranteed();
         }
         
         //partner season notes
         PartnerSeasonNotes partnerSeasonNotes = new PartnerSeasonNotes();
         
         
         partnersSeasonDetails.setPartnerSeasonId(seasonDetail.getPartnerSeasonId());
         partnersSeasonDetails.setPartnerSeasonProgramName(partnerSeasonProgramName);
         partnersSeasonDetails.setPartnerDepartment(partnerDepartment);
         partnersSeasonDetails.setPartnerProgram(partnerProgram);
         partnersSeasonDetails.setPartnerHLSeason(partnerHLSeason);
         partnersSeasonDetails.setInsuranceProvidedBy(seasonDetail.getInsuranceCarrierName());
         partnersSeasonDetails.setSevisFeesPaidBy("TODO:need clarification");
         partnersSeasonDetails.setSeasonStartDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonStartDate()));
         partnersSeasonDetails.setSeasonEndDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonEndDate()));
         partnersSeasonDetails.setSeasonApplicationDeadlineDate(DateUtils.getMMddyyDate(seasonDetail.getPartnerSeasonAppDeadlineDate()));
         partnersSeasonDetails.setNewDeadlineRequest("TODO:need clarification");
         partnersSeasonDetails.setProgramAllocationsUnguranteed(programAllocationsUnguranteed);
         partnersSeasonDetails.setProgramAllocationsGuaranteed(programAllocationsGuaranteed);
         partnersSeasonDetails.setPartnerSeasonNotes(partnerSeasonNotes);

      } catch (CcighgoException e) {
         partnersSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON_DETAILS.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_DETAILS)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_DETAILS));
      }
      return partnersSeasonDetails;
   }
}
