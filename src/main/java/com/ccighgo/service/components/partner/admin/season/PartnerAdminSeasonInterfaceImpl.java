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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.DocumentInformation;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerSeasonContract;
import com.ccighgo.db.entities.PartnerSeasonDocument;
import com.ccighgo.db.entities.PartnerStatus;
import com.ccighgo.db.entities.Season;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.DocumentInformationRepository;
import com.ccighgo.jpa.repositories.DocumentTypeDocumentCategoryProcessRepository;
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
import com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.PartnerAdminIHPSeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Dates;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Document;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Documents;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.NoteTopics;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.OperatingAgreement;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.OperatingAgreements;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.PartnerAdminJ1SeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.PartnerSeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.ProgramAllocations;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerAdminSeason;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerAdminSeasonList;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerSeasonStatus;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.SeasonStatus;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.status.Status;
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
   @Autowired DocumentTypeDocumentCategoryProcessRepository documentTypeDocumentCategoryProcessRepository;
   @Autowired DocumentInformationRepository documentInformationRepository;

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
                  String departmentProgramName = null;
                  String programName = null;
                  if (ps.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_J1_HS)) {
                     departmentProgramId = CCIConstants.HSP_J1_HS_ID;
                     departmentProgramName = CCIConstants.HSP_J1_HS;
                     programName = ps.getSeason().getSeasonJ1details().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_F1)) {
                     departmentProgramId = CCIConstants.HSP_F1_ID;
                     departmentProgramName = CCIConstants.HSP_F1;
                     programName = ps.getSeason().getSeasonF1details().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_STP_IHP)) {
                     departmentProgramId = CCIConstants.HSP_STP_IHP_ID;
                     departmentProgramName = CCIConstants.HSP_STP_IHP;
                     programName = ps.getSeason().getSeasonIhpdetails().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getProgramName().equals(CCIConstants.WP_WT_SUMMER)) {
                     departmentProgramId = CCIConstants.WP_WT_SUMMER_ID;
                     departmentProgramName = CCIConstants.WP_WT_SUMMER;
                     programName = ps.getSeason().getSeasonWnTsummerDetails().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getProgramName().equals(CCIConstants.WP_WT_WINTER)) {
                     departmentProgramId = CCIConstants.WP_WT_WINTER_ID;
                     departmentProgramName = CCIConstants.WP_WT_WINTER;
                     programName = ps.getSeason().getSeasonWnTwinterDetails().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getProgramName().equals(CCIConstants.WP_WT_SPRING)) {
                     departmentProgramId = CCIConstants.WP_WT_SPRING_ID;
                     departmentProgramName = CCIConstants.WP_WT_SPRING;
                     programName = ps.getSeason().getSeasonWnTspringDetails().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getProgramName().equals(CCIConstants.WP_WT_CAP)) {
                     departmentProgramId = CCIConstants.WP_WT_CAP_ID;
                     departmentProgramName = CCIConstants.WP_WT_CAP;
                     programName = ps.getSeason().getSeasonCapdetails().get(0).getProgramName();
                  }
                  PartnerSeasonStatus partnerSeasonStatus = new PartnerSeasonStatus();
                  partnerSeasonStatus.setSeasonStatusId(ps.getPartnerStatus1().getPartnerStatusId());
                  partnerSeasonStatus.setSeasonStatus(ps.getPartnerStatus1().getPartnerStatusName());
                  pas.setPartnerSeasonStatus(partnerSeasonStatus);

                  SeasonStatus seasonStatus = new SeasonStatus();
                  seasonStatus.setSeasonStatusId(ps.getSeason().getSeasonStatus().getSeasonStatusId());
                  seasonStatus.setSeasonStatus(ps.getSeason().getSeasonStatus().getStatus());
                  pas.setSeasonStatus(seasonStatus);

                  pas.setPartnerGoId(partnerGoId);
                  pas.setPartnerSeasonId(ps.getPartnerSeasonId());
                  pas.setSeasonId(ps.getSeason().getSeasonId());
                  pas.setDepartmentId(ps.getDepartmentProgram().getLookupDepartment().getDepartmentId());
                  pas.setAcronym(ps.getDepartmentProgram().getLookupDepartment().getAcronym());
                  pas.setDepartmentName(ps.getDepartmentProgram().getLookupDepartment().getDepartmentName());
                  pas.setDepartmentProgramId(departmentProgramId);
                  pas.setDepartmentProgramName(departmentProgramName);
                  pas.setProgramName(programName);
                  pas.setPartnerActiveForSeason(ps.getActive() == CCIConstants.ACTIVE ? true : false);
                  pas.setProgramStartDate(DateUtils.getTimestamp(ps.getPartnerSeasonStartDate()));
                  pas.setProgramEndDate(DateUtils.getTimestamp(ps.getPartnerSeasonEndDate()));
                  pas.setAppDeadlineDate(DateUtils.getTimestamp(ps.getPartnerSeasonAppDeadlineDate()));
                  if(ps.getPartnerSeasonContracts()!=null && !ps.getPartnerSeasonContracts().isEmpty()){
                     pas.setSignedContract(ps.getPartnerSeasonContracts().get(0).getIsSigned() == CCIConstants.ACTIVE ? true : false); 
                  }
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
         @SuppressWarnings("unchecked")
         List<Object[]> results = query.getResultList();
         if (results != null && results.size() > 0) {
            adminSeasonApplicationList.setPartnerGoId(Integer.valueOf(partnerGoId));
            List<PartnerAdminSeasonApplication> partnerSeasonApplication = new ArrayList<PartnerAdminSeasonApplication>();
            for (Object[] obj : results) {
               PartnerAdminSeasonApplication application = new PartnerAdminSeasonApplication();
               application.setProgramName(obj[0].toString());
               application.setSeasonId(Integer.valueOf(obj[1].toString()));
               application.setDepartmentProgramId(Integer.valueOf(obj[2].toString()));
               application.setAcronym(obj[3].toString());
               application.setDeptProgramName(obj[4].toString());
              // application.setDeptName(obj[5].toString());
               application.setStartDate(obj[5].toString());
               application.setEndDate(obj[6].toString());
               application.setAppDeadlineDate(obj[7].toString());
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
            adminJ1SeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
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
            adminF1SeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
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
            partnerSeason.setPartnerStatus1(partnerStatus);
            partnerSeasonsRepository.saveAndFlush(partnerSeason);
            response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
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
            response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
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
               ps.setPartner(partnerRepository.findOne(partnerAdminSeasonApplicationList.getPartnerGoId()));
               ps.setSeason(seasonRepository.findOne(Integer.valueOf(season.getSeasonId())));
               ps.setDepartmentProgram(departmentProgramRepository.findOne(Integer.valueOf(season.getDepartmentProgramId())));
               ps.setPartnerSeasonStartDate(DateUtils.getDateFromString(season.getStartDate()));
               ps.setPartnerSeasonEndDate(DateUtils.getDateFromString(season.getEndDate()));
               ps.setPartnerSeasonAppDeadlineDate(DateUtils.getDateFromString(season.getAppDeadlineDate()));
               ps.setPartnerStatus1(partnerStatusRepository.findOne(4));
               ps.setInsuranceProvidedByCCI(CCIConstants.INACTIVE);
               ps.setSevisFeesPaidByCCI(CCIConstants.INACTIVE);
               ps.setQuestionaireRequired(CCIConstants.INACTIVE);
               ps.setDisableAddParticipant(CCIConstants.INACTIVE);
               ps.setParticipantPaysDeposit(CCIConstants.INACTIVE);
               ps.setCanAccessJobBoard(CCIConstants.INACTIVE);
               ps.setCanCreateSubPartner(CCIConstants.INACTIVE);
               ps.setIsSignedContract(CCIConstants.INACTIVE);
               ps.setActive(CCIConstants.ACTIVE);
               ps.setCreatedBy(partnerAdminSeasonApplicationList.getLoggedInUserLoginId());
               ps.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               ps.setModifiedBy(partnerAdminSeasonApplicationList.getLoggedInUserLoginId());
               ps.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               partnerSeasonsList.add(ps);
            }
            partnerSeasonsRepository.save(partnerSeasonsList);
            partnerSeasonsRepository.flush();
            response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
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
   public PartnerAdminJ1SeasonDetails updateJ1AdminSeason(PartnerAdminJ1SeasonDetails partnerAdminJ1SeasonDetails) {
      PartnerAdminJ1SeasonDetails updatedObject = new PartnerAdminJ1SeasonDetails();
      if (partnerAdminJ1SeasonDetails == null) {
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               "cannot add null values, please check the object"));
         LOGGER.error("empty list to add seasons");
         return updatedObject;
      } else {
         try {
            PartnerSeason partnerSeason = partnerSeasonsRepository.findOne(partnerAdminJ1SeasonDetails.getPartnerSeasonId());
            if (partnerSeason != null) {
               // update basic details
               partnerSeason.setQuestionaireRequired(partnerAdminJ1SeasonDetails.getPartnerSeasonDetails().isQuestionireRequired() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               partnerSeason.setDisableAddParticipant(partnerAdminJ1SeasonDetails.getPartnerSeasonDetails().isDisableAddParticipants() ? CCIConstants.ACTIVE
                     : CCIConstants.INACTIVE);
               partnerSeason.setCanCreateSubPartner(partnerAdminJ1SeasonDetails.getPartnerSeasonDetails().isCanCreateSubpartner() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               partnerSeason.setInsuranceCarrierName(partnerAdminJ1SeasonDetails.getPartnerSeasonDetails().getInsuranceCarrierName());
               partnerSeason.setInsurancePhoneNumber(partnerAdminJ1SeasonDetails.getPartnerSeasonDetails().getInsurancePhoneNumber());
               partnerSeason.setInsurancePolicyNumber(partnerAdminJ1SeasonDetails.getPartnerSeasonDetails().getInsurancePolicyNumber());
               // update partner date value
               partnerSeason.setPartnerSeasonStartDate(DateUtils.getDateFromString(partnerAdminJ1SeasonDetails.getDates().getPartValStartDate()));
               partnerSeason.setPartnerSeasonEndDate(DateUtils.getDateFromString(partnerAdminJ1SeasonDetails.getDates().getPartValEndDate()));
               partnerSeason.setPartnerSeasonAppDeadlineDate(DateUtils.getDateFromString(partnerAdminJ1SeasonDetails.getDates().getPartValAppDeadlineDate()));
               partnerSeason.setPartnerSeasonExtAppDeadlineDate(DateUtils.getDateFromString(partnerAdminJ1SeasonDetails.getDates().getPartValExtAppDeadlineDate()));
               partnerSeason.setPartnerSeasonExtSecSemDeadlineDate(DateUtils.getDateFromString(partnerAdminJ1SeasonDetails.getDates().getPartValExtSecondSemDeadlineDate()));
               partnerSeason.setPartnerSeasonSecSemDeadlineDate(DateUtils.getDateFromString(partnerAdminJ1SeasonDetails.getDates().getPartValSecondSemDeadlineDate()));
               partnerSeason = partnerSeasonsRepository.saveAndFlush(partnerSeason);
               updatedObject = getPartnerAdminJ1Details(String.valueOf(partnerSeason.getPartner().getPartnerGoId()), String.valueOf(partnerSeason.getPartnerSeasonId()));
               updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                     messageUtil.getMessage(CCIConstants.NO_RECORD)));
            }
         } catch (CcighgoException e) {
            updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  "error occured while updating j1 season details"));
            LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS));
         }
      }
      return updatedObject;
   }

   @Override
   @Transactional
   public PartnerAdminF1SeasonDetails updateF1AdminSeason(PartnerAdminF1SeasonDetails partnerAdminF1SeasonDetails) {
      PartnerAdminF1SeasonDetails updatedObject = new PartnerAdminF1SeasonDetails();
      if (partnerAdminF1SeasonDetails == null) {
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               "cannot add null values, please check the object"));
         LOGGER.error("empty list to add seasons");
         return updatedObject;
      } else {
         try {
            PartnerSeason partnerSeason = partnerSeasonsRepository.findOne(partnerAdminF1SeasonDetails.getPartnerSeasonId());
            if (partnerSeason != null) {
               // update basic details
               partnerSeason.setQuestionaireRequired(partnerAdminF1SeasonDetails.getPartnerSeasonDetails().isQuestionireRequired() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               partnerSeason.setDisableAddParticipant(partnerAdminF1SeasonDetails.getPartnerSeasonDetails().isDisableAddParticipants() ? CCIConstants.ACTIVE
                     : CCIConstants.INACTIVE);
               partnerSeason.setCanCreateSubPartner(partnerAdminF1SeasonDetails.getPartnerSeasonDetails().isCanCreateSubpartner() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               partnerSeason.setInsuranceCarrierName(partnerAdminF1SeasonDetails.getPartnerSeasonDetails().getInsuranceCarrierName());
               partnerSeason.setInsurancePhoneNumber(partnerAdminF1SeasonDetails.getPartnerSeasonDetails().getInsurancePhoneNumber());
               partnerSeason.setInsurancePolicyNumber(partnerAdminF1SeasonDetails.getPartnerSeasonDetails().getInsurancePolicyNumber());
               // update partner date value
               partnerSeason.setPartnerSeasonStartDate(DateUtils.getDateFromString(partnerAdminF1SeasonDetails.getDates().getPartValStartDate()));
               partnerSeason.setPartnerSeasonEndDate(DateUtils.getDateFromString(partnerAdminF1SeasonDetails.getDates().getPartValEndDate()));
               partnerSeason.setPartnerSeasonAppDeadlineDate(DateUtils.getDateFromString(partnerAdminF1SeasonDetails.getDates().getPartValAppDeadlineDate()));
               partnerSeason.setPartnerSeasonExtAppDeadlineDate(DateUtils.getDateFromString(partnerAdminF1SeasonDetails.getDates().getPartValExtAppDeadlineDate()));
               partnerSeason.setPartnerSeasonExtSecSemDeadlineDate(DateUtils.getDateFromString(partnerAdminF1SeasonDetails.getDates().getPartValExtSecondSemDeadlineDate()));
               partnerSeason.setPartnerSeasonSecSemDeadlineDate(DateUtils.getDateFromString(partnerAdminF1SeasonDetails.getDates().getPartValSecondSemDeadlineDate()));
               partnerSeason = partnerSeasonsRepository.saveAndFlush(partnerSeason);
               updatedObject = getPartnerAdminF1Details(String.valueOf(partnerSeason.getPartner().getPartnerGoId()), String.valueOf(partnerSeason.getPartnerSeasonId()));
               updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                     messageUtil.getMessage(CCIConstants.NO_RECORD)));
            }
         } catch (CcighgoException e) {
            updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  "error occured while updating f1 season details"));
            LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS));
         }
      }
      return updatedObject;
   }

   @Override
   @Transactional
   public Response addAdminSeasonDocument(String loginId, String partnerSeasonId, Document doc) {
      Response resp = new Response();
      if (loginId == null) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               "cannot add document without login details"));
         LOGGER.error("cannot add document without login details");
         return resp;
      }
      if (partnerSeasonId == null) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               "season id is mandatory to associate document with season"));
         LOGGER.error("season id is mandatory to associate document with season");
         return resp;
      }
      if (doc != null && doc.getDocumentType() == null) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(), "Document type is required"));
         LOGGER.error("Document type is required");
         return resp;
      }
      try {
         PartnerSeasonDocument seasonDocument = new PartnerSeasonDocument();
         DocumentInformation documentInformation = new DocumentInformation();
         documentInformation.setFileName(doc.getDocumentName() != null ? doc.getDocumentName() : null);
         documentInformation.setDocumentName(doc.getDocumentName() != null ? doc.getDocumentName() : null);
         documentInformation.setUrl(doc.getDocumentUrl());
         documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(doc.getDocumentType().getDocumentType()));
         documentInformation.setCreatedBy(Integer.valueOf(loginId));
         documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         documentInformation.setModifiedBy(Integer.valueOf(loginId));
         documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         documentInformation = documentInformationRepository.saveAndFlush(documentInformation);

         seasonDocument.setDocumentInformation(documentInformation);
         seasonDocument.setDescription(doc.getDocumentDescription());
         seasonDocument.setPartnerSeason(partnerSeasonsRepository.findOne(Integer.valueOf(partnerSeasonId)));
         partnerSeasonDocumentRepository.saveAndFlush(seasonDocument);
         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               "error occured while adding document"));
         LOGGER.error("error occured while adding document");
      }
      return resp;
   }

   @Override
   @Transactional
   public Response addSeasonOperatingAgreement(String loginId, String partnerSeasonId, OperatingAgreement contract) {
      Response resp = new Response();
      if (loginId == null) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               "cannot add document without login details"));
         LOGGER.error("cannot add document without login details");
         return resp;
      }
      if (partnerSeasonId == null) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               "season id is mandatory to associate document with season"));
         LOGGER.error("season id is mandatory to associate document with season");
         return resp;
      }
      if (contract == null) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(), "Document type is required"));
         LOGGER.error("Document type is required");
         return resp;
      }
      try {
         PartnerSeasonContract seasonContract = new PartnerSeasonContract();
         DocumentInformation documentInformation = new DocumentInformation();
         documentInformation.setFileName(contract.getOperatingAgreementdocumentName() != null ? contract.getOperatingAgreementdocumentName() : null);
         documentInformation.setDocumentName(contract.getOperatingAgreementdocumentName() != null ? contract.getOperatingAgreementdocumentName() : null);
         documentInformation.setUrl(contract.getOperatingAgreementdocumentUrl());
         documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findOne(6));
         documentInformation.setCreatedBy(Integer.valueOf(loginId));
         documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         documentInformation.setModifiedBy(Integer.valueOf(loginId));
         documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         documentInformation = documentInformationRepository.saveAndFlush(documentInformation);

         seasonContract.setDocumentInformation(documentInformation);
         seasonContract.setPartnerSeason(partnerSeasonsRepository.findOne(Integer.valueOf(partnerSeasonId)));
         seasonContract.setDescription("");
         seasonContract.setIsSigned(contract.isSigned() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         partnerSeasonContractRepository.saveAndFlush(seasonContract);
         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               "error occured while adding operating agreement"));
         LOGGER.error("error occured while adding operating agreement");
      }
      return resp;
   }

   @Override
   @Modifying
   @Transactional
   public Response deleteAdminSeasonDocument(String partnerSeasonDocumentId) {
      Response resp = new Response();
      if (partnerSeasonDocumentId == null) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               "partnerSeasonDocumentId is required"));
         LOGGER.error("cannot delete document without partnerSeasonDocumentId");
         return resp;
      }
      try {
         partnerSeasonDocumentRepository.delete(Integer.valueOf(partnerSeasonDocumentId));
         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               "error occured while deleting document"));
         LOGGER.error("error occured while deleting document");
      }
      return resp;
   }
   
   @Override
   @Modifying
   @Transactional
   public Response deleteAdminSeasonAgreement(String partnerSeasonContractId) {
      Response resp = new Response();
      if (partnerSeasonContractId == null) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               "partnerSeasonContractId is required"));
         LOGGER.error("cannot delete document without partnerSeasonContractId");
         return resp;
      }
      try {
         partnerSeasonDocumentRepository.delete(Integer.valueOf(partnerSeasonContractId));
         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               "error occured while deleting document"));
         LOGGER.error("error occured while deleting document");
      }
      return resp;
   }

   @Override
   @Transactional(readOnly=true)
   public PartnerAdminIHPSeasonDetails getPartnerAdminIHPDetails(String partnerGoId, String partnerSeasonId) {
      PartnerAdminIHPSeasonDetails adminIHPSeasonDetails = new PartnerAdminIHPSeasonDetails();
      if (partnerGoId == null || Integer.valueOf(partnerGoId) == 0 || Integer.valueOf(partnerGoId) < 0) {
         adminIHPSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_GO_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_GO_ID));
         return adminIHPSeasonDetails;
      }
      if (partnerSeasonId == null || Integer.valueOf(partnerSeasonId) == 0 || Integer.valueOf(partnerSeasonId) < 0) {
         adminIHPSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.INVALID_PARTNER_ADMIN_SEASON_ID));
         return adminIHPSeasonDetails;
      } else {
         try {
            PartnerSeason partnerSeason = partnerSeasonsRepository.findByGoIdandPartnerSeasoonId(Integer.valueOf(partnerGoId), Integer.valueOf(partnerSeasonId));
            adminIHPSeasonDetails.setPartnerSeasonId(partnerSeason.getPartnerSeasonId());
            adminIHPSeasonDetails.setPartnerAgencyName(partnerSeason.getPartner().getCompanyName());
            adminIHPSeasonDetails.setPartnerSeasonProgramName(partnerSeason.getSeason().getSeasonIhpdetails().get(0).getProgramName());
            // partner season status
            com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.PartnerSeasonStatus partnerSeasonStatus = new com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.PartnerSeasonStatus();
            partnerSeasonStatus.setPartnerSeasonStatusId(partnerSeason.getPartnerStatus1().getPartnerStatusId());
            partnerSeasonStatus.setPartnerSeasonStatus(partnerSeason.getPartnerStatus1().getPartnerStatusName());
            adminIHPSeasonDetails.setPartnerSeasonStatus(partnerSeasonStatus);
            // Season status
            com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.SeasonStatus seasonStatus = new com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.SeasonStatus();
            seasonStatus.setSeasonStatusId(partnerSeason.getSeason().getSeasonIhpdetails().get(0).getSeasonStatus().getSeasonStatusId());
            seasonStatus.setSeasonStatus(partnerSeason.getSeason().getSeasonIhpdetails().get(0).getSeasonStatus().getStatus());
            adminIHPSeasonDetails.setSeasonStatus(seasonStatus);

            adminIHPSeasonDetails.setPartnerActiveForSeason(partnerSeason.getActive() == CCIConstants.ACTIVE ? true : false);
            // partner season details
            com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.PartnerSeasonDetails partnerSeasonDetails = partnerAdminSeasonDetailsHelper
                  .getIHPProgramBasicDetails(partnerSeason);
            adminIHPSeasonDetails.setPartnerSeasonDetails(partnerSeasonDetails);

            // operating agreements
            com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.OperatingAgreements operatingAgreements = partnerAdminSeasonDetailsHelper
                  .getIHPOperatingAgreement(partnerSeason);
            adminIHPSeasonDetails.setOperatingAgreements(operatingAgreements);

            // program documents
            com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.Documents documents = partnerAdminSeasonDetailsHelper.getIHPProgramDocuments(partnerSeason);
            adminIHPSeasonDetails.setDocuments(documents);

            // Notes
            com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail.NoteTopics partnerSeasonNotes = partnerAdminSeasonDetailsHelper
                  .getIHPProgramNotes(partnerGoId);
            adminIHPSeasonDetails.setPartnerSeasonNotes(partnerSeasonNotes);
            adminIHPSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } catch (CcighgoException e) {
            adminIHPSeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_GET_PARTNER_ADMIN_F1_DETAILS)));
            LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_GET_PARTNER_ADMIN_F1_DETAILS));
         }
      }
      return adminIHPSeasonDetails;
   }

   @Override
   @Transactional
   public PartnerAdminIHPSeasonDetails updateIHPAdminSeason(PartnerAdminIHPSeasonDetails partnerAdminIHPSeasonDetails) {
      PartnerAdminIHPSeasonDetails updatedObject = new PartnerAdminIHPSeasonDetails();
      if (partnerAdminIHPSeasonDetails == null) {
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               "cannot add null values, please check the object"));
         LOGGER.error("ihp object is null");
         return updatedObject;
      } else {
         try {
            PartnerSeason partnerSeason = partnerSeasonsRepository.findOne(partnerAdminIHPSeasonDetails.getPartnerSeasonId());
            if (partnerSeason != null) {
               partnerSeason.setQuestionaireRequired(partnerAdminIHPSeasonDetails.getPartnerSeasonDetails().isQuestionireRequired() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               partnerSeason.setDisableAddParticipant(partnerAdminIHPSeasonDetails.getPartnerSeasonDetails().isDisableAddParticipants() ? CCIConstants.ACTIVE
                     : CCIConstants.INACTIVE);
               partnerSeason.setCanCreateSubPartner(partnerAdminIHPSeasonDetails.getPartnerSeasonDetails().isCanCreateSubpartner() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               partnerSeason.setInsuranceCarrierName(partnerAdminIHPSeasonDetails.getPartnerSeasonDetails().getInsuranceCarrierName());
               partnerSeason.setInsurancePhoneNumber(partnerAdminIHPSeasonDetails.getPartnerSeasonDetails().getInsurancePhoneNumber());
               partnerSeason.setInsurancePolicyNumber(partnerAdminIHPSeasonDetails.getPartnerSeasonDetails().getInsurancePolicyNumber());
               updatedObject = getPartnerAdminIHPDetails(String.valueOf(partnerSeason.getPartner().getPartnerGoId()), String.valueOf(partnerSeason.getPartnerSeasonId()));
               updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                     messageUtil.getMessage(CCIConstants.NO_RECORD)));
            }
         } catch (CcighgoException e) {
            updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  "error occured while updating ihp season details"));
            LOGGER.error(messageUtil.getMessage(PartnerAdminSeasonConstants.ERROR_UPDATE_PARTNER_ADMIN_SEASON_STATUS));
         }
      }
      return updatedObject;
   }

   @Override
   public com.ccighgo.service.transport.partner.beans.partner.admin.season.status.PartnerSeasonStatus getPartnerSeasonStatuses() {
      com.ccighgo.service.transport.partner.beans.partner.admin.season.status.PartnerSeasonStatus seasonStatusList = new com.ccighgo.service.transport.partner.beans.partner.admin.season.status.PartnerSeasonStatus();
      try{
         List<PartnerStatus> partnerStatusList = partnerStatusRepository.getPartnerSeasonStatus(CCIConstants.ACTIVE);
         if(partnerStatusList!=null && !(partnerStatusList.isEmpty())){
            List<Status> partnerSeasonStatuses = new ArrayList<Status>();
            for(PartnerStatus ps:partnerStatusList){
               Status s = new Status();
               s.setStatusId(ps.getPartnerStatusId());
               s.setStatusValue(ps.getPartnerStatusName());
               partnerSeasonStatuses.add(s);
            }
            seasonStatusList.getPartnerSeasonStatuses().addAll(partnerSeasonStatuses);
            seasonStatusList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      }catch (CcighgoException e) {
         seasonStatusList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               "error occured while getting status list"));
      }
      return seasonStatusList;
   }
   
}
