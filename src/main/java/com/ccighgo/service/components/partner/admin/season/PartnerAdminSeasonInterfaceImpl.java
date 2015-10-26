/**
 * 
 */
package com.ccighgo.service.components.partner.admin.season;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.PartnerSeasonAllocationRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonContractRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonDocumentRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonsRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerSeasonMessageConstants;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerAdminSeason;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerAdminSeasonList;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerSeasonStatus;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.SeasonStatus;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;

/**
 * @author ravi
 *
 */
@Component
public class PartnerAdminSeasonInterfaceImpl implements PartnerAdminSeasonInterface {

   private static final Logger LOGGER = Logger.getLogger(PartnerAdminSeasonInterfaceImpl.class);

   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;

   @Autowired PartnerSeasonsRepository partnerSeasonsRepository;
   @Autowired PartnerSeasonAllocationRepository partnerSeasonAllocationRepository;
   @Autowired PartnerSeasonContractRepository partnerSeasonContractRepository;
   @Autowired PartnerSeasonDocumentRepository partnerSeasonDocumentRepository;

   @Autowired EntityManager entityManager;
   
   public static final String SP_ACTIVE_SEASON="call SPLookupActiveSeasonSearch()";

   @Override
   public PartnerAdminSeasonList getPartnerAdminSeasons() {
      PartnerAdminSeasonList adminSeasonList = new PartnerAdminSeasonList();
      try {
         List<PartnerSeason> partnerSeasonDBList = partnerSeasonsRepository.findAll();
         if (partnerSeasonDBList != null) {
            int count = 0;
            List<PartnerAdminSeason> partnerAdminSeasons = new ArrayList<PartnerAdminSeason>();
            for (PartnerSeason ps : partnerSeasonDBList) {
               if (ps.getDepartmentProgram() != null && ps.getSeason() != null) {
                  count += 1;
                  PartnerAdminSeason pas = new PartnerAdminSeason();
                  Integer departmentProgramId = 0;
                  String programName = null;
                  if (ps.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_J1_HS)) {
                     departmentProgramId = CCIConstants.HSP_J1_HS_ID;
                     programName = ps.getSeason().getSeasonJ1details().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_F1)) {
                     departmentProgramId = CCIConstants.HSP_F1_ID;
                     programName = ps.getSeason().getSeasonF1details().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_STP_IHP)) {
                     departmentProgramId = CCIConstants.HSP_STP_IHP_ID;
                     programName = ps.getSeason().getSeasonJ1details().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_SUMMER)) {
                     departmentProgramId = CCIConstants.WP_WT_SUMMER_ID;
                     programName = ps.getSeason().getSeasonF1details().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_WINTER)) {
                     departmentProgramId = CCIConstants.WP_WT_WINTER_ID;
                     programName = ps.getSeason().getSeasonJ1details().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_SPRING)) {
                     departmentProgramId = CCIConstants.WP_WT_SPRING_ID;
                     programName = ps.getSeason().getSeasonF1details().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_CAP)) {
                     departmentProgramId = CCIConstants.WP_WT_CAP_ID;
                     programName = ps.getSeason().getSeasonF1details().get(0).getProgramName();
                  }
                  PartnerSeasonStatus partnerSeasonStatus = new PartnerSeasonStatus();
                  partnerSeasonStatus.setSeasonStatusId(ps.getPartnerStatus().getPartnerStatusId());
                  partnerSeasonStatus.setSeasonStatus(ps.getPartnerStatus().getPartnerStatusName());
                  pas.setPartnerSeasonStatus(partnerSeasonStatus);

                  SeasonStatus seasonStatus = new SeasonStatus();
                  seasonStatus.setSeasonStatusId(ps.getSeason().getSeasonStatus().getSeasonStatusId());
                  seasonStatus.setSeasonStatus(ps.getSeason().getSeasonStatus().getStatus());
                  pas.setSeasonStatus(seasonStatus);

                  pas.setPartnerSeasonId(ps.getPartnerSeasonId());
                  pas.setSeasonId(ps.getSeason().getSeasonId());
                  pas.setDepartmentProgramId(departmentProgramId);
                  pas.setProgramName(programName);
                  pas.setPartnerActiveForSeason(ps.getActive() == CCIConstants.ACTIVE ? true : false);
                  pas.setProgramStartDate(DateUtils.getTimestamp(ps.getPartnerSeasonStartDate()));
                  pas.setProgramEndDate(DateUtils.getTimestamp(ps.getPartnerSeasonEndDate()));
                  pas.setAppDeadlineDate(DateUtils.getTimestamp(ps.getPartnerSeasonAppDeadlineDate()));
                 // pas.setSignedContract(ps.getPartnerSeasonContracts().get(0).getIsSigned() == CCIConstants.ACTIVE ? true : false);
                  partnerAdminSeasons.add(pas);
               }
            }
            adminSeasonList.setCount(count);
            adminSeasonList.getPartnerAdminSeasons().addAll(partnerAdminSeasons);
            adminSeasonList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (CcighgoException e) {
         adminSeasonList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_LIST)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_LIST));
      }
      return adminSeasonList;
   }

}
