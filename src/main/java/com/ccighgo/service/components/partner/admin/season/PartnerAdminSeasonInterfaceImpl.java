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

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerNoteTopic;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerSeasonAllocation;
import com.ccighgo.db.entities.PartnerSeasonContract;
import com.ccighgo.db.entities.PartnerSeasonDocument;
import com.ccighgo.db.entities.PartnerStatus;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.db.entities.Season;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.PartnerNoteRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTagRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTopicRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonAllocationRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonContractRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonDocumentRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonsRepository;
import com.ccighgo.jpa.repositories.PartnerStatusRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.jpa.repositories.SeasonStatusRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminSeasonConstants;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.PartnerAdminF1SeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Creator;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Dates;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Document;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.DocumentType;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Documents;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Note;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.NoteTopics;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.OperatingAgreement;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.OperatingAgreements;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.PartnerAdminJ1SeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.PartnerSeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.ProgramAllocations;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Topic;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerAdminSeason;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerAdminSeasonList;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerSeasonStatus;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.SeasonStatus;
import com.ccighgo.service.transport.partner.beans.partner.season.admin.application.PartnerAdminSeasonApplication;
import com.ccighgo.service.transport.partner.beans.partner.season.admin.application.PartnerAdminSeasonApplicationList;
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
   @Autowired LoginRepository loginRepository;

   @Autowired PartnerSeasonsRepository partnerSeasonsRepository;
   @Autowired PartnerSeasonAllocationRepository partnerSeasonAllocationRepository;
   @Autowired PartnerSeasonContractRepository partnerSeasonContractRepository;
   @Autowired PartnerSeasonDocumentRepository partnerSeasonDocumentRepository;
   @Autowired PartnerNoteRepository partnerNoteRepository;
   @Autowired PartnerNoteTagRepository partnerNoteTagRepository;
   @Autowired PartnerNoteTopicRepository partnerNoteTopicRepository;
   @Autowired PartnerStatusRepository partnerStatusRepository;
   @Autowired SeasonStatusRepository seasonStatusRepository;
   @Autowired SeasonRepository seasonRepository;
   @Autowired PartnerRepository partnerRepository;
   @Autowired DepartmentProgramRepository departmentProgramRepository;
   @Autowired PartnerAdminSeasonDetailsHelper partnerAdminSeasonDetailsHelper;

   @Autowired EntityManager entityManager;

   private static final String SP_PARTNER_SEASON_APPLICATION_LIST = "call SPPartnerSeasonAplication(?)";

   @Override
   @Transactional(readOnly = true)
   public PartnerAdminSeasonList getPartnerAdminSeasons(String partnerGoId) {
      PartnerAdminSeasonList adminSeasonList = new PartnerAdminSeasonList();
      if (partnerGoId == null) {
         adminSeasonList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID));
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
                  // TODO
                  // partnerSeasonStatus.setSeasonStatusId(ps.getPartnerStatus().getPartnerStatusId());
                  // partnerSeasonStatus.setSeasonStatus(ps.getPartnerStatus().getPartnerStatusName());
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
                  // pas.setSignedContract(ps.getPartnerSeasonContracts().get(0).getIsSigned() == CCIConstants.ACTIVE ?
                  // true : false);
                  partnerAdminSeasons.add(pas);
               }
            }
            adminSeasonList.setCount(count);
            adminSeasonList.getPartnerAdminSeasons().addAll(partnerAdminSeasons);
            adminSeasonList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            adminSeasonList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(PartnerAdminSeasonConstants.NO_PARTNER_ADMIN_SEASON_FOUND)));
         }
      } catch (CcighgoException e) {
         adminSeasonList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_GET_PARTNER_ADMIN_SEASONS)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_GET_PARTNER_ADMIN_SEASONS));
      }
      return adminSeasonList;
   }

   @Override
   @Transactional(readOnly = true)
   public PartnerAdminSeasonApplicationList getPartnerAdminSeasonApplicationList(String partnerGoId) {
      PartnerAdminSeasonApplicationList adminSeasonApplicationList = new PartnerAdminSeasonApplicationList();
      if (partnerGoId == null || Integer.valueOf(partnerGoId) == 0 || Integer.valueOf(partnerGoId) < 0) {
         adminSeasonApplicationList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_RECORD.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_GO_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_GO_ID));
         return adminSeasonApplicationList;
      }
      try {
         Query query = entityManager.createNativeQuery(SP_PARTNER_SEASON_APPLICATION_LIST);
         query.setParameter(1, Integer.valueOf(partnerGoId));
         List<Object[]> results = query.getResultList();
         if (results != null && results.size() > 0) {
            adminSeasonApplicationList.setPartnerId(Integer.valueOf(partnerGoId));
            List<PartnerAdminSeasonApplication> partnerSeasonApplication = new ArrayList<PartnerAdminSeasonApplication>();
            for (Object[] obj : results) {
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
      } catch (CcighgoException e) {
         adminSeasonApplicationList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_GET_PARTNER_ADMIN_SEASON_APPLY_LIST)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_GET_PARTNER_ADMIN_SEASON_APPLY_LIST));
      }
      return adminSeasonApplicationList;
   }

   @Override
   public PartnerAdminJ1SeasonDetails getPartnerAdminJ1Details(String partnerGoId, String partnerSeasonId) {
      PartnerAdminJ1SeasonDetails adminJ1SeasonDetails = new PartnerAdminJ1SeasonDetails();
      if (partnerGoId == null || Integer.valueOf(partnerGoId) == 0 || Integer.valueOf(partnerGoId) < 0) {
         adminJ1SeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_GO_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_GO_ID));
         return adminJ1SeasonDetails;
      }
      if (partnerSeasonId == null || Integer.valueOf(partnerSeasonId) == 0 || Integer.valueOf(partnerSeasonId) < 0) {
         adminJ1SeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID));
         return adminJ1SeasonDetails;
      } else {
         try {
            PartnerSeason partnerSeason = partnerSeasonsRepository.findByGoIdandPartnerSeasoonId(Integer.valueOf(partnerGoId), Integer.valueOf(partnerSeasonId));
            adminJ1SeasonDetails.setPartnerSeasonId(partnerSeason.getPartnerSeasonId());
            adminJ1SeasonDetails.setPartnerAgencyName(partnerSeason.getPartner().getCompanyName());
            adminJ1SeasonDetails.setPartnerSeasonProgramName(partnerSeason.getSeason().getSeasonJ1details().get(0).getProgramName());
            // partner season status
            com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.PartnerSeasonStatus partnerSeasonStatus = new com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.PartnerSeasonStatus();
            partnerSeasonStatus.setPartnerSeasonStatusId(partnerSeason.getPartnerStatus1().getPartnerStatusId());
            partnerSeasonStatus.setPartnerSeasonStatus(partnerSeason.getPartnerStatus1().getPartnerStatusName());
            adminJ1SeasonDetails.setPartnerSeasonStatus(partnerSeasonStatus);
            // Season status
            com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.SeasonStatus seasonStatus = new com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.SeasonStatus();
            seasonStatus.setSeasonStatusId(partnerSeason.getSeason().getSeasonJ1details().get(0).getSeasonStatus().getSeasonStatusId());
            seasonStatus.setSeasonStatus(partnerSeason.getSeason().getSeasonJ1details().get(0).getSeasonStatus().getStatus());
            adminJ1SeasonDetails.setSeasonStatus(seasonStatus);

            adminJ1SeasonDetails.setPartnerActiveForSeason(partnerSeason.getActive() == CCIConstants.ACTIVE ? true : false);
            // partner season details
            PartnerSeasonDetails partnerSeasonDetails = partnerAdminSeasonDetailsHelper.getJ1BasicDetails(partnerSeason);
            adminJ1SeasonDetails.setPartnerSeasonDetails(partnerSeasonDetails);

            // program allocations
            ProgramAllocations programAllocations = partnerAdminSeasonDetailsHelper.getJ1Allocations(partnerSeasonId, partnerSeason);
            adminJ1SeasonDetails.setProgramAllocations(programAllocations);

            // dates
            Dates dates = partnerAdminSeasonDetailsHelper.getJ1Dates(partnerSeason);
            adminJ1SeasonDetails.setDates(dates);

            // operating agreements
            OperatingAgreements operatingAgreements = partnerAdminSeasonDetailsHelper.getJ1OperatingAgreements(partnerSeason);
            adminJ1SeasonDetails.setOperatingAgreements(operatingAgreements);

            // season docs
            Documents documents = partnerAdminSeasonDetailsHelper.getJ1Documents(partnerSeason);
            adminJ1SeasonDetails.setDocuments(documents);

            // Notes
            NoteTopics partnerSeasonNotes = partnerAdminSeasonDetailsHelper.getJ1Notes(partnerGoId);
            adminJ1SeasonDetails.setPartnerSeasonNotes(partnerSeasonNotes);
         } catch (CcighgoException e) {
            adminJ1SeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_GET_PARTNER_ADMIN_J1_DETAILS)));
            LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_GET_PARTNER_ADMIN_J1_DETAILS));
         }
      }
      return adminJ1SeasonDetails;
   }

   @Override
   public PartnerAdminF1SeasonDetails getPartnerAdminF1Details(String partnerGoId, String partnerSeasonId) {
      PartnerAdminF1SeasonDetails adminF1SeasonDetails = new PartnerAdminF1SeasonDetails();
      if (partnerGoId == null || Integer.valueOf(partnerGoId) == 0 || Integer.valueOf(partnerGoId) < 0) {
         adminF1SeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_GO_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_GO_ID));
         return adminF1SeasonDetails;
      }
      if (partnerSeasonId == null || Integer.valueOf(partnerSeasonId) == 0 || Integer.valueOf(partnerSeasonId) < 0) {
         adminF1SeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID));
         return adminF1SeasonDetails;
      } else {
         try {
            PartnerSeason partnerSeason = partnerSeasonsRepository.findByGoIdandPartnerSeasoonId(Integer.valueOf(partnerGoId), Integer.valueOf(partnerSeasonId));
            adminF1SeasonDetails.setPartnerSeasonId(partnerSeason.getPartnerSeasonId());
            adminF1SeasonDetails.setPartnerAgencyName(partnerSeason.getPartner().getCompanyName());
            adminF1SeasonDetails.setPartnerSeasonProgramName(partnerSeason.getSeason().getSeasonF1details().get(0).getProgramName());
            // partner season status
            com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.PartnerSeasonStatus partnerSeasonStatus = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.PartnerSeasonStatus();
            partnerSeasonStatus.setPartnerSeasonStatusId(partnerSeason.getPartnerStatus1().getPartnerStatusId());
            partnerSeasonStatus.setPartnerSeasonStatus(partnerSeason.getPartnerStatus1().getPartnerStatusName());
            adminF1SeasonDetails.setPartnerSeasonStatus(partnerSeasonStatus);
            // Season status
            com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.SeasonStatus seasonStatus = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.SeasonStatus();
            seasonStatus.setSeasonStatusId(partnerSeason.getSeason().getSeasonF1details().get(0).getSeasonStatus().getSeasonStatusId());
            seasonStatus.setSeasonStatus(partnerSeason.getSeason().getSeasonF1details().get(0).getSeasonStatus().getStatus());
            adminF1SeasonDetails.setSeasonStatus(seasonStatus);

            adminF1SeasonDetails.setPartnerActiveForSeason(partnerSeason.getActive() == CCIConstants.ACTIVE ? true : false);
            // partner season details
            com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.PartnerSeasonDetails partnerSeasonDetails = partnerAdminSeasonDetailsHelper
                  .getF1ProgramBasicDetails(partnerSeason);
            adminF1SeasonDetails.setPartnerSeasonDetails(partnerSeasonDetails);

            // program allocations
            com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.ProgramAllocations programAllocations = partnerAdminSeasonDetailsHelper
                  .getF1ProgramAlllocations(partnerSeasonId, partnerSeason);
            adminF1SeasonDetails.setProgramAllocations(programAllocations);

            // dates
            com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Dates dates = partnerAdminSeasonDetailsHelper.getF1ProgramDates(partnerSeason);
            adminF1SeasonDetails.setDates(dates);

            // operating agreements
            com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.OperatingAgreements operatingAgreements = partnerAdminSeasonDetailsHelper
                  .getF1OperatingAgreement(partnerSeason);
            adminF1SeasonDetails.setOperatingAgreements(operatingAgreements);

            // program documents
            com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Documents documents = partnerAdminSeasonDetailsHelper.getF1ProgramDocuments(partnerSeason);
            adminF1SeasonDetails.setDocuments(documents);

            // Notes
            com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.NoteTopics partnerSeasonNotes = partnerAdminSeasonDetailsHelper
                  .getF1ProgramNotes(partnerGoId);
            adminF1SeasonDetails.setPartnerSeasonNotes(partnerSeasonNotes);
         } catch (CcighgoException e) {
            adminF1SeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_GET_PARTNER_ADMIN_F1_DETAILS)));
            LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_GET_PARTNER_ADMIN_F1_DETAILS));
         }
      }
      return adminF1SeasonDetails;

   }

   @Override
   @Transactional
   public Response updatePartnerSeasonActiveStatus(String statusVal, String partnerSeasonId) {
      Response response = new Response();
      if (partnerSeasonId == null) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID));
         return response;
      }
      if (statusVal == null) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.PARTNER_ADMIN_SEASON_INVALID_STATUS_VALUE)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.PARTNER_ADMIN_SEASON_INVALID_STATUS_VALUE));
         return response;
      } else {
         try {
            PartnerSeason partnerSeason = partnerSeasonsRepository.findOne(Integer.valueOf(partnerSeasonId));
            PartnerStatus partnerStatus = partnerStatusRepository.findOne(Integer.valueOf(statusVal));
            // partnerSeason.setPartnerSeasStatus(partnerStatus);
            partnerSeasonsRepository.saveAndFlush(partnerSeason);

         } catch (CcighgoException e) {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_ACTIVE_STATUS)));
            LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_ACTIVE_STATUS));
         }
      }
      return response;
   }

   @Override
   @Transactional
   public Response updateSeasonActiveStatus(String statusVal, String partnerSeasonId) {
      Response response = new Response();
      if (partnerSeasonId == null) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID));
         return response;
      }
      if (statusVal == null) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.PARTNER_ADMIN_SEASON_INVALID_STATUS_VALUE)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.PARTNER_ADMIN_SEASON_INVALID_STATUS_VALUE));
         return response;
      } else {
         try {
            PartnerSeason partnerSeason = partnerSeasonsRepository.findOne(Integer.valueOf(partnerSeasonId));
            com.ccighgo.db.entities.SeasonStatus seasonStatus = seasonStatusRepository.findOne(Integer.valueOf(statusVal));
            Season season = partnerSeason.getSeason();
            season.setSeasonStatus(seasonStatus);
            seasonRepository.saveAndFlush(season);
         } catch (CcighgoException e) {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS)));
            LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS));
         }
      }
      return response;
   }

   @Override
   @Transactional
   public Response addNewSeasonsToPartner(PartnerAdminSeasonApplicationList partnerAdminSeasonApplicationList) {
      Response response = new Response();
      if (partnerAdminSeasonApplicationList == null) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               "cannot add null values, please check the list"));
         LOGGER.error("empty list to add seasons");
         return response;
      } else {
         try {
            List<PartnerSeason> partnerSeasonsList = new ArrayList<PartnerSeason>();
            for (PartnerAdminSeasonApplication season : partnerAdminSeasonApplicationList.getPartnerSeasonApplication()) {
               // discussed with phani setting only season, department program and boolean fields to false
               PartnerSeason ps = new PartnerSeason();
               ps.setPartner(partnerRepository.findOne(partnerAdminSeasonApplicationList.getPartnerId()));
               ps.setSeason(seasonRepository.findOne(Integer.valueOf(season.getSeasonId())));
               ps.setDepartmentProgram(departmentProgramRepository.findOne(Integer.valueOf(season.getDepartmentProgramId())));
               ps.setInsuranceProvidedByCCI(CCIConstants.INACTIVE);
               ps.setSevisFeesPaidByCCI(CCIConstants.INACTIVE);
               ps.setQuestionaireRequired(CCIConstants.INACTIVE);
               ps.setDisableAddParticipant(CCIConstants.INACTIVE);
               ps.setParticipantPaysDeposit(CCIConstants.INACTIVE);
               ps.setCanAccessJobBoard(CCIConstants.INACTIVE);
               ps.setCanCreateSubPartner(CCIConstants.INACTIVE);
               ps.setIsSignedContract(CCIConstants.INACTIVE);
               ps.setActive(CCIConstants.ACTIVE);
               partnerSeasonsList.add(ps);
            }
            partnerSeasonsRepository.save(partnerSeasonsList);
            partnerSeasonsRepository.flush();
         } catch (CcighgoException e) {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS)));
            LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS));
         }
      }
      return response;
   }
}
