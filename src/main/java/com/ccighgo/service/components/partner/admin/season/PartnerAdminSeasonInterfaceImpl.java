/**
 * 
 */
package com.ccighgo.service.components.partner.admin.season;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
import com.ccighgo.service.transport.partner.beans.partner.season.admin.application.PartnerAdminSeasonApplication;
import com.ccighgo.service.transport.partner.beans.partner.season.admin.application.PartnerAdminSeasonApplicationList;
import com.ccighgo.service.transport.partner.beans.partner.season.application.PartnerSeasonApplication;
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
   
   private static final String SP_PARTNER_SEASON_APPLICATION_LIST = "call SPPartnerSeasonAplication(?)";

   @Override
   @Transactional(readOnly=true)
   public PartnerAdminSeasonList getPartnerAdminSeasons(String partnerGoId) {
      PartnerAdminSeasonList adminSeasonList = new PartnerAdminSeasonList();
      if (partnerGoId == null) {
         adminSeasonList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID));
         return adminSeasonList;
      }
      try {
         List<PartnerSeason> partnerSeasonDBList = partnerSeasonsRepository.findPartnerSeasonByPartnerGoId(Integer.valueOf(partnerGoId));
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
                  
                  pas.setPartnerGoId(partnerGoId);
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

   @Override
   @Transactional(readOnly=true)
   public PartnerAdminSeasonApplicationList getPartnerAdminSeasonApplicationList(String partnerGoId) {
      PartnerAdminSeasonApplicationList adminSeasonApplicationList = new PartnerAdminSeasonApplicationList();
      try{
         Query query = entityManager.createNativeQuery(SP_PARTNER_SEASON_APPLICATION_LIST);
         query.setParameter(1, Integer.valueOf(partnerGoId));
         List<Object[]> results = query.getResultList();
         if (results != null && results.size() > 0) {
            adminSeasonApplicationList.setPartnerId(Integer.valueOf(partnerGoId));
            List<PartnerAdminSeasonApplication> partnerSeasonApplication = new ArrayList<PartnerAdminSeasonApplication>();
            for (Object[] obj : results) {
               // position 0 : programName, position 1, position 2 seasonId: departmentProgramId
               PartnerAdminSeasonApplication application = new PartnerAdminSeasonApplication();
               application.setProgramName(obj[0].toString());
               application.setSeasonId(obj[1].toString());
               application.setDepartmentProgramId(obj[2].toString());
               partnerSeasonApplication.add(application);
            }
            adminSeasonApplicationList.getPartnerSeasonApplication().addAll(partnerSeasonApplication);
            adminSeasonApplicationList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            adminSeasonApplicationList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      }catch (CcighgoException e) {
         adminSeasonApplicationList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_LIST)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_LIST));
      }
      return adminSeasonApplicationList;
   }

}
