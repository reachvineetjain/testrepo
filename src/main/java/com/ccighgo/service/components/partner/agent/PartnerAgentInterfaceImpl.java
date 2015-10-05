package com.ccighgo.service.components.partner.agent;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.Season;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonsRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAgentMessageConstants;
import com.ccighgo.service.components.errormessages.constants.PartnerSeasonMessageConstants;
import com.ccighgo.service.components.errormessages.constants.UserManagementMessageConstants;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentAddedSeason;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentAddedSeasons;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentSeason;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentSeasons;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonDepartment;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonProgramOption;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonProgramStatus;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;
import com.ccighgo.service.transport.utility.beans.department.Departments;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;

@Component
public class PartnerAgentInterfaceImpl implements PartnerAgentInterface {

   private static final Logger LOGGER = Logger.getLogger(PartnerAgentInterfaceImpl.class);
   
   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;
   @Autowired PartnerRepository partnerRepository;
   @Autowired PartnerSeasonsRepository partnerSeasonsRepository;
   @Autowired SeasonRepository seasonRepository;
   
   
   @Override
   @Transactional
   public PartnerAgentAddedSeasons getAddedSeasons(String partnerGoId) {

      PartnerAgentAddedSeasons partnerAgentAddedSeasons = new PartnerAgentAddedSeasons();
      if (partnerGoId == null) {
         partnerAgentAddedSeasons = setPartnerAgentAddedSeasonsStatus(partnerAgentAddedSeasons, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
               ErrorCode.INVALID_PARTNER_ID.getValue(), messageUtil.getMessage(PartnerAgentMessageConstants.INVALID_PARTNER_ID));
         LOGGER.error(messageUtil.getMessage(PartnerAgentMessageConstants.INVALID_PARTNER_ID));
         return partnerAgentAddedSeasons;
      }
      try {
         List<com.ccighgo.db.entities.PartnerSeason> partnerSeasonDBList = partnerSeasonsRepository.findPartnerSeasonByPartnerGoId(Integer.valueOf(partnerGoId));
         if (partnerSeasonDBList != null) {
            int count = 0;
            List<PartnerAgentAddedSeason> partnerSeasonsUIList = new ArrayList<PartnerAgentAddedSeason>();
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

                  SeasonStatus seasonStatus = new SeasonStatus();
                  seasonStatus.setSeasonStatusId(entity.getSeason().getSeasonStatus().getSeasonStatusId());
                  seasonStatus.setSeasonStatus(entity.getSeason().getSeasonStatus().getStatus());
                  seasonStatus.setActive(entity.getSeason().getSeasonStatus().getActive() == CCIConstants.ACTIVE ? true : false);

                  PartnerAgentAddedSeason partnerAgentAddedSeason = new PartnerAgentAddedSeason();

                  // partnerAgentAddedSeason.setParticipantAllocated("TODO:need clarification");
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_J1_HS)) {
                     partnerAgentAddedSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonJ1details().get(0).getProgramName());
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_F1)) {
                     partnerAgentAddedSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonF1details().get(0).getProgramName());
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_STP_IHP)) {
                     partnerAgentAddedSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonIhpdetails().get(0).getProgramName());
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_SUMMER)) {
                     partnerAgentAddedSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonWnTsummerDetails().get(0).getProgramName());
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_WINTER)) {
                     partnerAgentAddedSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonWnTwinterDetails().get(0).getProgramName());
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_SPRING)) {
                     partnerAgentAddedSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonWnTspringDetails().get(0).getProgramName());
                  }
                  if (entity.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_CAP)) {
                     partnerAgentAddedSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonCapdetails().get(0).getProgramName());
                  }
                  partnerAgentAddedSeason.setPartnerSeasonId(entity.getPartnerSeasonId());
                  partnerAgentAddedSeason.setPartnerId(partnerGoId);
                  partnerAgentAddedSeason.setPartnerStartDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonStartDate()));
                  partnerAgentAddedSeason.setPartnerEndDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonEndDate()));
                  partnerAgentAddedSeason.setPartnerApplicationDeadlineDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonAppDeadlineDate()));
                  partnerAgentAddedSeason.setPartnerSeasonDepartment(partnerSeasonDepartment);
                  partnerAgentAddedSeason.setPartnerProgramOption(partnerProgramOption);
                  partnerAgentAddedSeason.setSeasonProgramStatus(seasonProgramStatus);
                  partnerAgentAddedSeason.setSeasonStatus(seasonStatus);
                  partnerSeasonsUIList.add(partnerAgentAddedSeason);
               }
            }
            partnerAgentAddedSeasons.setCount(count);
            partnerAgentAddedSeasons.getPartnerAgentAddedSeasons().addAll(partnerSeasonsUIList);
            partnerAgentAddedSeasons = setPartnerAgentAddedSeasonsStatus(partnerAgentAddedSeasons, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.PARTNER_AGENT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         } else {
            partnerAgentAddedSeasons = setPartnerAgentAddedSeasonsStatus(partnerAgentAddedSeasons, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
                  ErrorCode.FAILED_GET_ADDED_SEASONS.getValue(), messageUtil.getMessage(CCIConstants.NO_RECORD));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         partnerAgentAddedSeasons = setPartnerAgentAddedSeasonsStatus(partnerAgentAddedSeasons, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
               ErrorCode.FAILED_GET_ADDED_SEASONS.getValue(), messageUtil.getMessage(PartnerAgentMessageConstants.FAILED_GET_ADDED_SEASONS));
         LOGGER.error(messageUtil.getMessage(PartnerAgentMessageConstants.FAILED_GET_ADDED_SEASONS));
      }
      return partnerAgentAddedSeasons;
   }
   
   @Override
   public PartnerAgentSeasons getAllSeasons() {
      PartnerAgentSeasons partnerAgentSeasons = new PartnerAgentSeasons();
      try {
         List<Season> seasonDbList = seasonRepository.findAll();
         if (seasonDbList.size() > 0) {
            List<PartnerAgentSeason> partnerAgentSeasonList = new ArrayList<PartnerAgentSeason>();
            for (Season season : seasonDbList) {
               PartnerAgentSeason partnerAgentSeason = new PartnerAgentSeason();
               partnerAgentSeason.setSeasonId(season.getSeasonId());
               partnerAgentSeason.setSeasonName(season.getSeasonName());
               partnerAgentSeason.setSeasonFullName(season.getSeasonFullName());
               partnerAgentSeasonList.add(partnerAgentSeason);
            }
            partnerAgentSeasons.getPartnerAgentSeasons().addAll(partnerAgentSeasonList);
            partnerAgentSeasons = setPartnerAgentSeasonsStatus(partnerAgentSeasons, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.PARTNER_AGENT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         } else {
            partnerAgentSeasons = setPartnerAgentSeasonsStatus(partnerAgentSeasons, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SEASONS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         partnerAgentSeasons = setPartnerAgentSeasonsStatus(partnerAgentSeasons, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SEASONS.getValue(),
               messageUtil.getMessage(PartnerAgentMessageConstants.FAILED_GET_SEASONS));
         LOGGER.error(messageUtil.getMessage(PartnerAgentMessageConstants.FAILED_GET_SEASONS));
      }
      return partnerAgentSeasons;
   }
   
   private PartnerAgentSeasons setPartnerAgentSeasonsStatus(PartnerAgentSeasons partnerAgentSeasons, String code, String type, int serviceCode, String message) {
      if (partnerAgentSeasons == null)
         partnerAgentSeasons = new PartnerAgentSeasons();
      partnerAgentSeasons.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return partnerAgentSeasons;
   }
   private PartnerAgentAddedSeasons setPartnerAgentAddedSeasonsStatus(PartnerAgentAddedSeasons partnerAgentAddedSeasons, String code, String type, int serviceCode, String message) {
      if (partnerAgentAddedSeasons == null)
         partnerAgentAddedSeasons = new PartnerAgentAddedSeasons();
      partnerAgentAddedSeasons.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return partnerAgentAddedSeasons;
   }
}
