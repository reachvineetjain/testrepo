package com.ccighgo.service.components.season;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.DepartmentProgramOption;
import com.ccighgo.db.entities.DocumentInformation;
import com.ccighgo.db.entities.FieldStaffLeadershipSeason;
import com.ccighgo.db.entities.LookupDepartment;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonCAPDetail;
import com.ccighgo.db.entities.SeasonDepartmentDocument;
import com.ccighgo.db.entities.SeasonDepartmentNote;
import com.ccighgo.db.entities.SeasonF1Detail;
import com.ccighgo.db.entities.SeasonGHTConfiguration;
import com.ccighgo.db.entities.SeasonGeographyConfiguration;
import com.ccighgo.db.entities.SeasonHSADetail;
import com.ccighgo.db.entities.SeasonHSPAllocation;
import com.ccighgo.db.entities.SeasonHSPConfiguration;
import com.ccighgo.db.entities.SeasonIHPDetail;
import com.ccighgo.db.entities.SeasonJ1Detail;
import com.ccighgo.db.entities.SeasonLSDetail;
import com.ccighgo.db.entities.SeasonStatus;
import com.ccighgo.db.entities.SeasonTADetail;
import com.ccighgo.db.entities.SeasonVADetail;
import com.ccighgo.db.entities.SeasonWADetail;
import com.ccighgo.db.entities.SeasonWPAllocation;
import com.ccighgo.db.entities.SeasonWPConfiguration;
import com.ccighgo.db.entities.SeasonWnTSpringDetail;
import com.ccighgo.db.entities.SeasonWnTSummerDetail;
import com.ccighgo.db.entities.SeasonWnTWinterDetail;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.SeasonCodes;
import com.ccighgo.jpa.repositories.DepartmentProgramOptionRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.DepartmentRepository;
import com.ccighgo.jpa.repositories.DocumentInformationRepository;
import com.ccighgo.jpa.repositories.DocumentTypeDocumentCategoryProcessRepository;
import com.ccighgo.jpa.repositories.DocumentTypeRepository;
import com.ccighgo.jpa.repositories.FieldStaffLeadershipSeasonRepository;
import com.ccighgo.jpa.repositories.GenderRepository;
import com.ccighgo.jpa.repositories.IHPRegionsRepository;
import com.ccighgo.jpa.repositories.SeasonCAPDetailsRepository;
import com.ccighgo.jpa.repositories.SeasonDepartmentDocumentRepository;
import com.ccighgo.jpa.repositories.SeasonDepartmentNotesRepository;
import com.ccighgo.jpa.repositories.SeasonF1DetailsRepository;
import com.ccighgo.jpa.repositories.SeasonGHTConfigurationRepository;
import com.ccighgo.jpa.repositories.SeasonGeographyConfigurationRepository;
import com.ccighgo.jpa.repositories.SeasonHSADetailsRepository;
import com.ccighgo.jpa.repositories.SeasonHSPAllocationRepository;
import com.ccighgo.jpa.repositories.SeasonHSPConfigurationRepsitory;
import com.ccighgo.jpa.repositories.SeasonIHPDetailRepository;
import com.ccighgo.jpa.repositories.SeasonIHPDetailsRegionApplicationRepository;
import com.ccighgo.jpa.repositories.SeasonJ1DetailsRepository;
import com.ccighgo.jpa.repositories.SeasonLSDetailsRepository;
import com.ccighgo.jpa.repositories.SeasonProgramDocumentRepository;
import com.ccighgo.jpa.repositories.SeasonProgramNotesRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.jpa.repositories.SeasonStatusRepository;
import com.ccighgo.jpa.repositories.SeasonTADetailsRepository;
import com.ccighgo.jpa.repositories.SeasonVADetailsRepository;
import com.ccighgo.jpa.repositories.SeasonWADetailsRepository;
import com.ccighgo.jpa.repositories.SeasonWPAllocationRepository;
import com.ccighgo.jpa.repositories.SeasonWPConfigurationRepository;
import com.ccighgo.jpa.repositories.SeasonWTSpringRepository;
import com.ccighgo.jpa.repositories.SeasonWTSummerRepository;
import com.ccighgo.jpa.repositories.SeasonWTWinterRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.SeasonMessageConstants;
import com.ccighgo.service.transport.season.beans.cloneseason.CloneSeason;
import com.ccighgo.service.transport.season.beans.cloneseason.ClonedDocuments;
import com.ccighgo.service.transport.season.beans.cloneseason.ClonedSeasonNotes;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection1Base;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection2Dates;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.SeasonGHTDetails;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.IHPDates;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.IHPNameAndStatus;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.IHPProgramConfiguration;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.SeasonHspStpIhpDetails;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSAugStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSFieldSettings;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSJanStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSProgramAllocations;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.SeasonHspJ1HSDetails;
import com.ccighgo.service.transport.season.beans.seasonprgdoc.SeasonProgramDocument;
import com.ccighgo.service.transport.season.beans.seasonprgnote.SeasonProgramNote;
import com.ccighgo.service.transport.season.beans.seasonprogram.SeasonProgram;
import com.ccighgo.service.transport.season.beans.seasonprogram.SeasonPrograms;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.SeasonWPDetails;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.WPBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.WPProgramAllocations;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.WPSectionOne;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.season.SeasonDepartmentNotes;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1Accounting;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1AugustStart1StSemesterDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1AugustStartFullYearDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1BasicDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1FieldSettings;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1JanuaryStart2NdSemesterDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1JanuaryStartFullYearDetail;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1ProgramAllocations;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.SeasonHSPF1Details;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonListObject;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.SeasonWPCAPDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPBasicDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPInternshipDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPProgramAllocations;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPTraineeDetails;
import com.ccighgo.service.transport.utility.beans.documenttype.DocumentType;
import com.ccighgo.service.transport.utility.beans.documenttype.DocumentTypes;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.ExceptionUtil;

/**
 * @author ravi
 *
 */
@Component
public class SeasonServiceInterfaceImpl implements SeasonServiceInterface {

   private static final Logger LOGGER = LoggerFactory.getLogger(SeasonServiceInterfaceImpl.class);
   @Autowired SeasonRepository seasonRepository;
   @Autowired SeasonServiceImplUtil seasonServiceImplUtil;
   @Autowired SeasonJ1DetailsRepository seasonJ1DetailsRepository;
   @Autowired SeasonStatusRepository seasonStatusRepository;
   @Autowired SeasonF1DetailsRepository seasonF1DetailsRepository;
   @Autowired SeasonHSADetailsRepository seasonHSADetailsRepository;
   @Autowired SeasonLSDetailsRepository seasonLSDetailsRepository;
   @Autowired SeasonTADetailsRepository seasonTADetailsRepository;
   @Autowired SeasonVADetailsRepository seasonVADetailsRepository;
   @Autowired SeasonWADetailsRepository seasonWADetailsRepository;
   @Autowired SeasonWTSummerRepository seasonWTSummerRepository;
   @Autowired SeasonWTSpringRepository seasonWTSpringRepository;
   @Autowired DepartmentRepository departmentRepository;
   @Autowired SeasonHSPConfigurationRepsitory hspConfigurationRepsitory;
   @Autowired SeasonHSPAllocationRepository seasonHSPAllocationRepository;
   @Autowired SeasonWPConfigurationRepository seasonWPConfigurationRepository;
   @Autowired SeasonWPAllocationRepository seasonWPAllocationRepository;
   @Autowired SeasonWTWinterRepository seasonWTWinterRepository;
   @Autowired SeasonCAPDetailsRepository seasonCAPDetailsRepository;
   @Autowired SeasonGHTConfigurationRepository seasonGHTConfigurationRepository;
   @Autowired SeasonCloningHelper seasonCloningHelper;
   @Autowired SeasonDepartmentNotesRepository seasonDepartmentNotesRepository;
   @Autowired DocumentTypeDocumentCategoryProcessRepository documentTypeDocumentCategoryProcessRepository;
   @Autowired DocumentInformationRepository documentInformationRepository;
   @Autowired SeasonDepartmentDocumentRepository seasonDepartmentDocumentRepository;
   @Autowired DepartmentProgramRepository departmentProgramRepository;
   @Autowired SeasonProgramNotesRepository seasonProgramNotesRepository;
   @Autowired SeasonProgramDocumentRepository seasonProgramDocumentRepository;
   @Autowired DepartmentProgramOptionRepository departmentProgramOptionRepository;
   @Autowired DocumentTypeRepository documentTypeRepository;
   @Autowired SeasonIHPProgramHelper ihpProgramHelper;
   @Autowired SeasonIHPDetailRepository seasonIHPDetailRepository;
   @Autowired CommonComponentUtils componentUtils;
   @Autowired MessageUtils messageUtil;
   @Autowired SeasonGeographyConfigurationRepository seasonGeographyConfigurationRepository;
   @Autowired FieldStaffLeadershipSeasonRepository fieldStaffLeadershipSeasonRepository;
   @Autowired SeasonIHPDetailRepository ihpDetailRepository;
   @Autowired GenderRepository genderRepository;
   @Autowired SeasonIHPDetailsRegionApplicationRepository seasonIHPDetailsRegionApplicationRepository;
   @Autowired IHPRegionsRepository ihpRegionsRepository;

   @Transactional(readOnly = true)
   public SeasonsList getAllSeasons() {
      SeasonsList seasonsList = new SeasonsList();
      try {
         List<Season> allseasons = seasonRepository.getAllSeasons();
         if (allseasons != null && !(allseasons.isEmpty())) {
            seasonsList.setRecordCount(allseasons.size());
            for (int i = 0; i < allseasons.size(); i++) {
               SeasonListObject seasonBean = new SeasonListObject();
               Season seasonEntity = allseasons.get(i);
               seasonServiceImplUtil.convertEntitySeasonToSeasonListObject(seasonBean, seasonEntity);
               seasonsList.getSeasons().add(seasonBean);
               seasonsList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            }
         } else {
            seasonsList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         seasonsList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_SEASON_LIST.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_SEASON_LIST)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_SEASON_LIST) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonsList;
   }

   @Transactional(readOnly = true)
   public SeasonBean editSeason(String id) {
      return viewSeason(id);
   }

   @Transactional(readOnly = true)
   public SeasonBean viewSeason(String seasonId) {
      SeasonBean seasonBean = new SeasonBean();
      if (seasonId == null || seasonId.isEmpty()) {
         seasonBean.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_SEASON_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_ID));
         return seasonBean;
      }
      try {
         Season seasonEntity = seasonRepository.findOne(Integer.parseInt(seasonId));
         if (seasonEntity != null) {
            seasonServiceImplUtil.convertEntitySeasonToBeanSeason(seasonBean, seasonEntity);
            seasonBean.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            seasonBean.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.ERROR_GET_SEASON_DETAILS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         seasonBean.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_SEASON_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_SEASON_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_SEASON_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonBean;
   }

   @Transactional
   public SeasonBean createSeason(SeasonBean seasonBean) {
      SeasonBean returnObject = new SeasonBean();
      if (seasonBean.getSeasonName() == null) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_CREATE_SEASON.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_NAME)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_NAME));
         return returnObject;
      }
      try {
         int seasonId = -1;
         Season season = seasonRepository.findBySeasonName(seasonBean.getSeasonName());
         if (season != null) {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_CREATE_SEASON.getValue(),
                  messageUtil.getMessage(SeasonMessageConstants.SEASON_ALREADY_EXISTS)));
            LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.SEASON_ALREADY_EXISTS));
         } else {
            Season seasonEntity = new Season();
            seasonServiceImplUtil.convertSeasonBeanToSeasonEntity(seasonBean, seasonEntity, false);
            seasonEntity = seasonRepository.saveAndFlush(seasonEntity);
            seasonServiceImplUtil.createSeasonConfiguration(seasonBean, seasonEntity);
            seasonServiceImplUtil.createSeasonDepartmentNotes(seasonBean, seasonEntity);
            seasonServiceImplUtil.createSeasonPrograms(seasonEntity, seasonBean);
            seasonId = seasonEntity.getSeasonId();
            returnObject = viewSeason(seasonId + CCIConstants.EMPTY_DATA);
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_CREATE_SEASON.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_CREATE_NEW_SEASON)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_CREATE_NEW_SEASON) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public SeasonBean updateSeason(SeasonBean seasonBean) {
      SeasonBean returnObject = new SeasonBean();
      if (seasonBean == null) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_SEASON.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_SEASON_VALUE_NULL)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_SEASON_VALUE_NULL));
         return returnObject;
      }
      try {
         Season seasonEntity = new Season();
         seasonServiceImplUtil.convertSeasonBeanToSeasonEntity(seasonBean, seasonEntity, true);
         seasonEntity = seasonRepository.saveAndFlush(seasonEntity);
         seasonServiceImplUtil.updateSeasonConfiguration(seasonBean, seasonEntity);
         seasonServiceImplUtil.updateSeasonDepartmentNotes(seasonBean, seasonEntity);
         seasonServiceImplUtil.updateSeasonDocuments(seasonBean, seasonEntity);
         returnObject = viewSeason(seasonEntity.getSeasonId() + CCIConstants.EMPTY_DATA);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_SEASON.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_SEASON)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_SEASON) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional(readOnly = true)
   public SeasonPrograms getSeasonPrograms(String seasonId) {
      SeasonPrograms seasonPrograms = new SeasonPrograms();
      if (seasonId == null || seasonId.isEmpty()) {
         seasonPrograms.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_SEASON_PROGRAMS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_ID));
         return seasonPrograms;
      }
      try {
         Season season = seasonRepository.findOne(Integer.valueOf(seasonId));
         if (season != null) {
            List<SeasonProgram> seasonProgramsList = new ArrayList<SeasonProgram>();
            seasonPrograms = new SeasonPrograms();
            LookupDepartment dept = season.getLookupDepartment();
            if (dept != null) {
               if (dept.getDepartmentName().equals(CCIConstants.DEPT_HIGH_SCHOOL_PROGRAMS)) {
                  if (season.getSeasonJ1details() != null && season.getSeasonJ1details().size() > 0) {
                     SeasonProgram sprg = new SeasonProgram();
                     sprg.setSeasonId(Integer.valueOf(seasonId));
                     sprg.setSeasonProgramId(season.getSeasonJ1details().get(0).getSeasonJ1DetailsId());
                     sprg.setProgramName(season.getSeasonJ1details().get(0).getProgramName());
                     sprg.setUrl(CCIConstants.HSP_J1_URL);
                     seasonProgramsList.add(sprg);
                  }
                  if (season.getSeasonF1details() != null && season.getSeasonF1details().size() > 0) {
                     SeasonProgram sprg = new SeasonProgram();
                     sprg.setSeasonId(Integer.valueOf(seasonId));
                     sprg.setSeasonProgramId(season.getSeasonF1details().get(0).getSeasonF1DetailsId());
                     sprg.setProgramName(season.getSeasonF1details().get(0).getProgramName());
                     sprg.setUrl(CCIConstants.HSP_F1_URL);
                     seasonProgramsList.add(sprg);
                  }
                  if (season.getSeasonIhpdetails() != null && season.getSeasonIhpdetails().size() > 0) {
                     SeasonProgram sprg = new SeasonProgram();
                     sprg.setSeasonId(Integer.valueOf(seasonId));
                     sprg.setSeasonProgramId(season.getSeasonIhpdetails().get(0).getSeasonIHPDetailsId());
                     sprg.setProgramName(season.getSeasonIhpdetails().get(0).getProgramName());
                     sprg.setUrl(CCIConstants.HSP_IHP_URL);
                     seasonProgramsList.add(sprg);
                  }
               }
               if (dept.getDepartmentName().equals(CCIConstants.DEPT_WORK_PROGRAMS)) {
                  if (season.getSeasonWnTsummerDetails() != null && season.getSeasonWnTsummerDetails().size() > 0) {
                     SeasonProgram sprg = new SeasonProgram();
                     sprg.setSeasonId(Integer.valueOf(seasonId));
                     sprg.setSeasonProgramId(season.getSeasonWnTsummerDetails().get(0).getSeasonWnTSummerDetailsId());
                     sprg.setProgramName(season.getSeasonWnTsummerDetails().get(0).getProgramName());
                     sprg.setUrl(CCIConstants.WP_SUMM_URL);
                     seasonProgramsList.add(sprg);
                  }
                  if (season.getSeasonWnTwinterDetails() != null && season.getSeasonWnTwinterDetails().size() > 0) {
                     SeasonProgram sprg = new SeasonProgram();
                     sprg.setSeasonId(Integer.valueOf(seasonId));
                     sprg.setSeasonProgramId(season.getSeasonWnTwinterDetails().get(0).getSeasonWnTWinterDetailsId());
                     sprg.setProgramName(season.getSeasonWnTwinterDetails().get(0).getProgramName());
                     sprg.setUrl(CCIConstants.WP_WINT_URL);
                     seasonProgramsList.add(sprg);
                  }
                  if (season.getSeasonWnTspringDetails() != null && season.getSeasonWnTspringDetails().size() > 0) {
                     SeasonProgram sprg = new SeasonProgram();
                     sprg.setSeasonId(Integer.valueOf(seasonId));
                     sprg.setSeasonProgramId(season.getSeasonWnTspringDetails().get(0).getSeasonWnTSpringDetailsId());
                     sprg.setProgramName(season.getSeasonWnTspringDetails().get(0).getProgramName());
                     sprg.setUrl(CCIConstants.WP_SPRING_URL);
                     seasonProgramsList.add(sprg);
                  }
                  if (season.getSeasonCapdetails() != null && season.getSeasonCapdetails().size() > 0) {
                     SeasonProgram sprg = new SeasonProgram();
                     sprg.setSeasonId(Integer.valueOf(seasonId));
                     sprg.setSeasonProgramId(season.getSeasonCapdetails().get(0).getSeasonCAPDetailsId());
                     sprg.setProgramName(season.getSeasonCapdetails().get(0).getProgramName());
                     sprg.setUrl(CCIConstants.WP_CAP_URL);
                     seasonProgramsList.add(sprg);
                  }
               }
               if (dept.getDepartmentName().equals(CCIConstants.DEPT_GREEN_HEART_TRAVEL)) {
                  if (season.getSeasonHsadetails() != null && season.getSeasonHsadetails().size() > 0) {
                     SeasonProgram sprg = new SeasonProgram();
                     sprg.setSeasonId(Integer.valueOf(seasonId));
                     sprg.setSeasonProgramId(season.getSeasonHsadetails().get(0).getSeasonHSADetailsId());
                     sprg.setProgramName(season.getSeasonHsadetails().get(0).getProgramName());
                     sprg.setUrl(CCIConstants.GHT_HSA_URL);
                     seasonProgramsList.add(sprg);
                  }
                  if (season.getSeasonLsdetails() != null && season.getSeasonLsdetails().size() > 0) {
                     SeasonProgram sprg = new SeasonProgram();
                     sprg.setSeasonId(Integer.valueOf(seasonId));
                     sprg.setSeasonProgramId(season.getSeasonLsdetails().get(0).getSeasonLSDetailsId());
                     sprg.setProgramName(season.getSeasonLsdetails().get(0).getProgramName());
                     sprg.setUrl(CCIConstants.GHT_LS_URL);
                     seasonProgramsList.add(sprg);
                  }
                  if (season.getSeasonTadetails() != null && season.getSeasonTadetails().size() > 0) {
                     SeasonProgram sprg = new SeasonProgram();
                     sprg.setSeasonId(Integer.valueOf(seasonId));
                     sprg.setSeasonProgramId(season.getSeasonTadetails().get(0).getSeasonTADetailsId());
                     sprg.setProgramName(season.getSeasonTadetails().get(0).getProgramName());
                     sprg.setUrl(CCIConstants.GHT_TA_URL);
                     seasonProgramsList.add(sprg);
                  }
                  if (season.getSeasonVadetails() != null && season.getSeasonVadetails().size() > 0) {
                     SeasonProgram sprg = new SeasonProgram();
                     sprg.setSeasonId(Integer.valueOf(seasonId));
                     sprg.setSeasonProgramId(season.getSeasonVadetails().get(0).getSeasonVADetailsId());
                     sprg.setProgramName(season.getSeasonVadetails().get(0).getProgramName());
                     sprg.setUrl(CCIConstants.GHT_VA_URL);
                     seasonProgramsList.add(sprg);
                  }
                  if (season.getSeasonWadetails() != null && season.getSeasonWadetails().size() > 0) {
                     SeasonProgram sprg = new SeasonProgram();
                     sprg.setSeasonId(Integer.valueOf(seasonId));
                     sprg.setSeasonProgramId(season.getSeasonWadetails().get(0).getSeasonWADetailsId());
                     sprg.setProgramName(season.getSeasonWadetails().get(0).getProgramName());
                     sprg.setUrl(CCIConstants.GHT_WA_URL);
                     seasonProgramsList.add(sprg);
                  }
               }
               seasonPrograms.getSeasonPrograms().addAll(seasonProgramsList);
               seasonPrograms.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               seasonPrograms.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_SEASON_PROGRAM_DEPT.getValue(),
                     messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_SEASON_DEPARTMENT_DETAILS)));
               LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_SEASON_DEPARTMENT_DETAILS));
            }
         } else {
            seasonPrograms.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         seasonPrograms.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_SEASON_PROGRAMS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_SEASON_PROGRAMS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_SEASON_PROGRAMS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonPrograms;
   }

   @Transactional(readOnly = true)
   public SeasonStatuses getSeasonStatus() {
      SeasonStatuses seasonStatuses = new SeasonStatuses();
      try {
         Sort sort = new Sort(Sort.Direction.ASC, "status");
         List<SeasonStatus> seasonStatusDBList = seasonStatusRepository.findAll(sort);
         if (seasonStatusDBList != null) {
            List<com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus> seasonStatusList = new ArrayList<com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus>();
            for (SeasonStatus ss : seasonStatusDBList) {
               com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus status = new com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus();
               status.setSeasonStatusId(ss.getSeasonStatusId());
               status.setSeasonStatus(ss.getStatus());
               status.setActive(ss.getActive() == CCIConstants.ACTIVE ? true : false);
               seasonStatusList.add(status);
            }
            seasonStatuses.getSeasonStatuses().addAll(seasonStatusList);
            seasonStatuses.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            seasonStatuses.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         seasonStatuses.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_SEASON_STATUS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_SEASON_STATUS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_SEASON_STATUS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonStatuses;
   }

   @Transactional(readOnly = true)
   public SeasonHspJ1HSDetails getHSPJ1HSSeasonDetails(String seasonProgramId) {
      SeasonHspJ1HSDetails seasonHspJ1HSDetails = new SeasonHspJ1HSDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         seasonHspJ1HSDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSPJ1_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return seasonHspJ1HSDetails;
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonJ1Detail != null) {
            seasonHspJ1HSDetails.setSeasonId(seasonJ1Detail.getSeason().getSeasonId());
            seasonHspJ1HSDetails.setSeasonProgramId(seasonJ1Detail.getSeasonJ1DetailsId());
            seasonHspJ1HSDetails.setDepartmentProgramId(CCIConstants.HSP_J1_HS_ID);
            seasonHspJ1HSDetails.setJ1HsBasicDetail(seasonServiceImplUtil.getJ1HSBasicDetail(seasonJ1Detail));
            seasonHspJ1HSDetails.setJ1HsJanStart(seasonServiceImplUtil.getJ1HSJanStart(seasonJ1Detail));
            seasonHspJ1HSDetails.setJ1HsAugStart(seasonServiceImplUtil.getJ1HSAugStart(seasonJ1Detail));
            seasonHspJ1HSDetails.setJ1HsFieldSettings(seasonServiceImplUtil.getJ1HSFieldSettings(seasonJ1Detail));
            seasonHspJ1HSDetails.setJ1HsProgramAllocations(getHSPJ1HSSeasonProgramAllocation(seasonProgramId));
            seasonHspJ1HSDetails.getJ1HsNotes().addAll(seasonServiceImplUtil.getJ1Notes(seasonJ1Detail.getSeason().getSeasonId(), seasonJ1Detail.getSeasonJ1DetailsId()));
            seasonHspJ1HSDetails.getJ1HsDocuments().addAll(seasonServiceImplUtil.getJ1Docs(seasonJ1Detail.getSeason().getSeasonId(), seasonJ1Detail.getSeasonJ1DetailsId()));
            seasonHspJ1HSDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            seasonHspJ1HSDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         seasonHspJ1HSDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_J1HS_SEASON_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_J1HS_SEASON_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonHspJ1HSDetails;
   }

   @Transactional(readOnly = true)
   public J1HSBasicDetail getHSPJ1HSSeasonNameAndStatus(String seasonProgramId) {
      J1HSBasicDetail j1hsBasicDetail = new J1HSBasicDetail();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         j1hsBasicDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSPJ1_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return j1hsBasicDetail;
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonJ1Detail != null) {
            j1hsBasicDetail = seasonServiceImplUtil.getJ1HSBasicDetail(seasonJ1Detail);
            j1hsBasicDetail.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            j1hsBasicDetail.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         j1hsBasicDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSPJ1_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_HSPJ1_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_HSPJ1_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return j1hsBasicDetail;
   }

   @Transactional(readOnly = true)
   public J1HSJanStart getHSPJ1HSSeasonJanStartDetails(String seasonProgramId) {
      J1HSJanStart j1hsJanStart = new J1HSJanStart();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         j1hsJanStart.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSPJ1_JAN_START_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return j1hsJanStart;
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonJ1Detail != null) {
            j1hsJanStart = seasonServiceImplUtil.getJ1HSJanStart(seasonJ1Detail);
            j1hsJanStart.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            j1hsJanStart.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         j1hsJanStart.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSPJ1_JAN_START_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_HSPJ1_JAN_START_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_HSPJ1_JAN_START_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return j1hsJanStart;
   }

   @Transactional(readOnly = true)
   public J1HSAugStart getHSPJ1HSSeasonAugStartDetails(String seasonProgramId) {
      J1HSAugStart j1hsAugStart = new J1HSAugStart();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         j1hsAugStart.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSPJ1_AUG_START_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return j1hsAugStart;
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonJ1Detail != null) {
            j1hsAugStart = seasonServiceImplUtil.getJ1HSAugStart(seasonJ1Detail);
            j1hsAugStart.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            j1hsAugStart.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         j1hsAugStart.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSPJ1_AUG_START_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_HSPJ1_AUG_START_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_HSPJ1_AUG_START_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return j1hsAugStart;
   }

   @Transactional(readOnly = true)
   public J1HSFieldSettings getHSPJ1HSSeasonFieldSettings(String seasonProgramId) {
      J1HSFieldSettings j1hsFieldSettings = new J1HSFieldSettings();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         j1hsFieldSettings.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSPJ1_FIELD_SETTINGS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return j1hsFieldSettings;
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonJ1Detail != null) {
            j1hsFieldSettings = seasonServiceImplUtil.getJ1HSFieldSettings(seasonJ1Detail);
            j1hsFieldSettings.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            j1hsFieldSettings.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         j1hsFieldSettings.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSPJ1_FIELD_SETTINGS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_HSPJ1_FIELD_SETTINGS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_HSPJ1_FIELD_SETTINGS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return j1hsFieldSettings;
   }

   @Transactional(readOnly = true)
   public J1HSProgramAllocations getHSPJ1HSSeasonProgramAllocation(String seasonProgramId) {
      J1HSProgramAllocations j1hsProgramAllocations = new J1HSProgramAllocations();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         j1hsProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSPJ1_PROGRAM_ALLOCATION.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return j1hsProgramAllocations;
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonJ1Detail != null) {
            List<SeasonHSPAllocation> hspAllocations = seasonHSPAllocationRepository.findSeasonHSPAllocationBySeasonId(seasonJ1Detail.getSeason().getSeasonId());
            if (hspAllocations != null) {
               int totalUnGuarant = 0;
               int totalGurant = 0;
               int augStartGuarnteedParticipants = 0;
               int augStartUnGuarnteedParticipants = 0;
               int janStartGuarnteedParticipants = 0;
               int janStartUnGuarnteedParticipants = 0;
               j1hsProgramAllocations = new J1HSProgramAllocations();
               j1hsProgramAllocations.setSeasonId(seasonJ1Detail.getSeason().getSeasonId());
               j1hsProgramAllocations.setSeasonProgramId(seasonJ1Detail.getSeasonJ1DetailsId());
               try {
                  for (SeasonHSPAllocation hspAllocation : hspAllocations) {
                     if (hspAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_J1_HS_ID) {
                        if (hspAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_J1)) {
                           augStartUnGuarnteedParticipants = hspAllocation.getMaxUnguaranteedPax() > 0 ? hspAllocation.getMaxUnguaranteedPax() : 0;
                           totalUnGuarant += augStartUnGuarnteedParticipants > 0 ? augStartUnGuarnteedParticipants : 0;
                           augStartGuarnteedParticipants = hspAllocation.getMaxGuaranteedPax() > 0 ? hspAllocation.getMaxGuaranteedPax() : 0;
                           totalGurant += augStartGuarnteedParticipants > 0 ? augStartGuarnteedParticipants : 0;
                        }
                        if (hspAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_J1)) {
                           janStartGuarnteedParticipants = hspAllocation.getMaxGuaranteedPax() > 0 ? hspAllocation.getMaxGuaranteedPax() : 0;
                           janStartUnGuarnteedParticipants = hspAllocation.getMaxUnguaranteedPax() > 0 ? hspAllocation.getMaxUnguaranteedPax() : 0;
                           totalGurant += janStartGuarnteedParticipants > 0 ? janStartGuarnteedParticipants : 0;
                           totalUnGuarant += janStartUnGuarnteedParticipants > 0 ? janStartUnGuarnteedParticipants : 0;
                        }
                     }
                  }
               } catch (Exception e) {
                  ExceptionUtil.logException(e, LOGGER);
               }
               j1hsProgramAllocations.setJanStartGuarnteedParticipants(janStartGuarnteedParticipants);
               j1hsProgramAllocations.setJanStartUnGuarnteedParticipants(janStartUnGuarnteedParticipants);
               j1hsProgramAllocations.setAugStartGuarnteedParticipants(augStartGuarnteedParticipants);
               j1hsProgramAllocations.setAugStartUnGuarnteedParticipants(augStartUnGuarnteedParticipants);
               j1hsProgramAllocations.setAugStartAcceptedParticipants(0);
               j1hsProgramAllocations.setAugStartRemainingParticpants(0);
               j1hsProgramAllocations.setJanStartAcceptedParticipants(0);
               j1hsProgramAllocations.setJanStartRemainingParticpants(0);
               j1hsProgramAllocations.setTotalAcceptedParticipants(0);
               j1hsProgramAllocations.setTotalRemainingParticpants(0);
               j1hsProgramAllocations.setTotalUnGuarnteedParticipants(totalUnGuarant);
               j1hsProgramAllocations.setTotalGuarnteedParticipants(totalGurant);
               j1hsProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               j1hsProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                     messageUtil.getMessage(CCIConstants.NO_RECORD)));
            }
         } else {
            j1hsProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         j1hsProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSPJ1_PROGRAM_ALLOCATION.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_HSPJ1_PROGRAM_ALLOCATION)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_HSPJ1_PROGRAM_ALLOCATION) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return j1hsProgramAllocations;
   }

   @Transactional
   public SeasonHspJ1HSDetails updateHSPJ1HSSeasonDetails(SeasonHspJ1HSDetails seasonHspJ1HSDetails) {
      SeasonHspJ1HSDetails returnObject = new SeasonHspJ1HSDetails();
      if (seasonHspJ1HSDetails == null || seasonHspJ1HSDetails.getJ1HsBasicDetail() == null || seasonHspJ1HSDetails.getJ1HsJanStart() == null
            || seasonHspJ1HSDetails.getJ1HsAugStart() == null || seasonHspJ1HSDetails.getJ1HsFieldSettings() == null || seasonHspJ1HSDetails.getJ1HsProgramAllocations() == null
            || seasonHspJ1HSDetails.getJ1HsNotes() == null || seasonHspJ1HSDetails.getJ1HsDocuments() == null) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_J1HS_SEASON_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(seasonHspJ1HSDetails.getSeasonProgramId()));
         if (seasonJ1Detail != null) {
            seasonServiceImplUtil.updateJ1BasicDetails(seasonHspJ1HSDetails.getJ1HsBasicDetail(), seasonJ1Detail);
            seasonServiceImplUtil.updateJ1JanStartDetails(seasonHspJ1HSDetails.getJ1HsJanStart(), seasonJ1Detail);
            seasonServiceImplUtil.updateJ1AugStartDetails(seasonHspJ1HSDetails.getJ1HsAugStart(), seasonJ1Detail);
            seasonServiceImplUtil.updateJ1FSSettings(seasonHspJ1HSDetails.getJ1HsFieldSettings(), seasonJ1Detail);
            updateHSPJ1HSSeasonProgramAllocation(seasonHspJ1HSDetails.getJ1HsProgramAllocations());
            seasonServiceImplUtil.updateJ1Notes(seasonHspJ1HSDetails, seasonJ1Detail.getSeason());
            seasonServiceImplUtil.updateJ1HSDocs(seasonHspJ1HSDetails, seasonJ1Detail.getSeason());
            seasonJ1Detail = seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
            returnObject = getHSPJ1HSSeasonDetails(String.valueOf(seasonJ1Detail.getSeasonJ1DetailsId()));
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_J1HS_SEASON_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_J1HS_SEASON_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_J1HS_SEASON_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public J1HSBasicDetail updateHSPJ1HSSeasonNameAndStatus(J1HSBasicDetail j1hsBasicDetail) {
      J1HSBasicDetail returnObject = new J1HSBasicDetail();
      if (j1hsBasicDetail == null || j1hsBasicDetail.getSeasonId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_HSPJ1_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(j1hsBasicDetail.getSeasonProgramId()));
         if (seasonJ1Detail != null) {
            seasonServiceImplUtil.updateJ1BasicDetails(j1hsBasicDetail, seasonJ1Detail);
            seasonJ1Detail = seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
            returnObject = seasonServiceImplUtil.getJ1HSBasicDetail(seasonJ1Detail);
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_HSPJ1_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_BASIC_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_BASIC_DATA) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public J1HSJanStart updateHSPJ1HSSeasonJanStartDetails(J1HSJanStart j1hsJanStart) {
      J1HSJanStart returnObject = new J1HSJanStart();
      if (j1hsJanStart == null || j1hsJanStart.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_HSPJ1_JAN_START.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(j1hsJanStart.getSeasonProgramId()));
         if (seasonJ1Detail != null) {
            seasonServiceImplUtil.updateJ1JanStartDetails(j1hsJanStart, seasonJ1Detail);
            seasonJ1Detail = seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
            returnObject = seasonServiceImplUtil.getJ1HSJanStart(seasonJ1Detail);
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_HSPJ1_JAN_START.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_HSPJ1_JAN_START)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_HSPJ1_JAN_START) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public J1HSAugStart updateHSPJ1HSSeasonAugStartDetails(J1HSAugStart j1hsAugStart) {
      J1HSAugStart returnObject = new J1HSAugStart();
      if (j1hsAugStart == null || j1hsAugStart.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_HSPJ1_AUG_START.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(j1hsAugStart.getSeasonProgramId()));
         if (seasonJ1Detail != null) {
            seasonServiceImplUtil.updateJ1AugStartDetails(j1hsAugStart, seasonJ1Detail);
            seasonJ1Detail = seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
            returnObject = seasonServiceImplUtil.getJ1HSAugStart(seasonJ1Detail);
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_HSPJ1_AUG_START.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_HSPJ1_AUG_START)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_HSPJ1_AUG_START) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public J1HSFieldSettings updateHSPJ1HSSeasonFieldSettings(J1HSFieldSettings j1hsFieldSettings) {
      J1HSFieldSettings returnObject = new J1HSFieldSettings();
      if (j1hsFieldSettings == null || j1hsFieldSettings.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_HSPJ1_FIELD_SETTINGS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(j1hsFieldSettings.getSeasonProgramId()));
         if (seasonJ1Detail != null) {
            seasonServiceImplUtil.updateJ1FSSettings(j1hsFieldSettings, seasonJ1Detail);
            seasonJ1Detail = seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
            returnObject = seasonServiceImplUtil.getJ1HSFieldSettings(seasonJ1Detail);
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_HSPJ1_FIELD_SETTINGS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_HSPJ1_FIELD_SETTINGS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_HSPJ1_FIELD_SETTINGS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public J1HSProgramAllocations updateHSPJ1HSSeasonProgramAllocation(J1HSProgramAllocations j1hsProgramAllocations) {
      J1HSProgramAllocations returnObject = new J1HSProgramAllocations();
      if (j1hsProgramAllocations == null || j1hsProgramAllocations.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_HSPJ1_PROGRAM_ALLOCATION.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         if (j1hsProgramAllocations != null && j1hsProgramAllocations.getSeasonId() > 0 && j1hsProgramAllocations.getSeasonProgramId() > 0) {
            List<SeasonHSPAllocation> hspAllocations = seasonHSPAllocationRepository.findSeasonHSPAllocationBySeasonId(j1hsProgramAllocations.getSeasonId());
            List<SeasonHSPAllocation> updatedList = new ArrayList<SeasonHSPAllocation>();
            for (SeasonHSPAllocation hspAllocation : hspAllocations) {
               if (hspAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_J1_HS_ID) {
                  if (hspAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_J1)) {
                     SeasonHSPAllocation allocation = new SeasonHSPAllocation();
                     allocation = hspAllocation;
                     allocation.setMaxGuaranteedPax(j1hsProgramAllocations.getAugStartGuarnteedParticipants());
                     allocation.setMaxUnguaranteedPax(j1hsProgramAllocations.getAugStartUnGuarnteedParticipants());
                     updatedList.add(allocation);
                  }
                  if (hspAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_J1)) {
                     SeasonHSPAllocation allocation = new SeasonHSPAllocation();
                     allocation = hspAllocation;
                     allocation.setMaxGuaranteedPax(j1hsProgramAllocations.getJanStartGuarnteedParticipants());
                     allocation.setMaxUnguaranteedPax(j1hsProgramAllocations.getJanStartUnGuarnteedParticipants());
                     updatedList.add(allocation);
                  }
               }
            }
            seasonHSPAllocationRepository.save(updatedList);
            seasonHSPAllocationRepository.flush();
            returnObject = getHSPJ1HSSeasonProgramAllocation(String.valueOf(j1hsProgramAllocations.getSeasonProgramId()));
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_HSPJ1_PROGRAM_ALLOCATION.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_HSPJ1_PROGRAM_ALLOCATION)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_HSPJ1_PROGRAM_ALLOCATION) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public void createJ1ProgramAllocation(Season season, int loginId) {
      try {
         List<SeasonHSPAllocation> seasonhspAllocations = new ArrayList<SeasonHSPAllocation>();
         SeasonHSPAllocation august_FY = new SeasonHSPAllocation();
         DepartmentProgramOption departmentProgramOption_AUG = departmentProgramOptionRepository.findOne(CCIConstants.AUGUST_FY_J1_ID);
         august_FY.setDepartmentProgramOption(departmentProgramOption_AUG);
         august_FY.setMaxGuaranteedPax(0);
         august_FY.setMaxUnguaranteedPax(0);
         august_FY.setSeason(season);
         august_FY.setCreatedBy(loginId);
         august_FY.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         august_FY.setModifiedBy(loginId);
         august_FY.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonhspAllocations.add(august_FY);

         SeasonHSPAllocation Jan_FY = new SeasonHSPAllocation();
         DepartmentProgramOption departmentProgramOption_JAN = departmentProgramOptionRepository.findOne(CCIConstants.JANUARY_FY_J1_ID);
         Jan_FY.setDepartmentProgramOption(departmentProgramOption_JAN);
         Jan_FY.setMaxGuaranteedPax(0);
         Jan_FY.setMaxUnguaranteedPax(0);
         Jan_FY.setSeason(season);
         Jan_FY.setCreatedBy(loginId);
         Jan_FY.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         Jan_FY.setModifiedBy(loginId);
         Jan_FY.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonhspAllocations.add(Jan_FY);
         seasonHSPAllocationRepository.save(seasonhspAllocations);
         seasonHSPAllocationRepository.flush();
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
   }

   @Transactional(readOnly = true)
   public SeasonHSPF1Details getSeasonHSPF1Details(String seasonProgramId) {
      SeasonHSPF1Details seasonHSPF1Details = new SeasonHSPF1Details();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         seasonHSPF1Details.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_F1_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return seasonHSPF1Details;
      }
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (allF1Details != null) {
            seasonHSPF1Details = new SeasonHSPF1Details();
            seasonHSPF1Details.setSeasonId(allF1Details.getSeason().getSeasonId());
            seasonHSPF1Details.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
            seasonHSPF1Details.setDepartmentProgramId(CCIConstants.HSP_F1_ID);
            seasonHSPF1Details.setDetails(seasonServiceImplUtil.getHSPF1NameAndStatus(allF1Details));
            seasonHSPF1Details.setJanuaryStart2NdSemesterDetails(seasonServiceImplUtil.getHSPF1JanuaryStart2NdSemesterDetails(allF1Details));
            seasonHSPF1Details.setJanuaryStartFullYearDetail(seasonServiceImplUtil.getHSPF1JanuaryStartFullYearDetails(allF1Details));
            seasonHSPF1Details.setAugustStart1StSemesterDetails(seasonServiceImplUtil.getHSPF1AugustStart1StSemesterDetails(allF1Details));
            seasonHSPF1Details.setAugustStartFullYearDetails(seasonServiceImplUtil.getHSPF1AugustStartFullYearDetails(allF1Details));
            seasonHSPF1Details.setAccounting(seasonServiceImplUtil.getHSPF1Accounting(allF1Details));
            seasonHSPF1Details.setFieldSettings(seasonServiceImplUtil.getHSPF1FieldSettings(allF1Details));
            seasonHSPF1Details.setProgramAllocations(getHSPF1ProgramAllocations(allF1Details.getSeasonF1DetailsId() + ""));
            seasonHSPF1Details.getNotes().addAll(seasonServiceImplUtil.getHSPF1Notes(allF1Details));
            seasonHSPF1Details.getDocuments().addAll(seasonServiceImplUtil.getHSPF1Documents(allF1Details, Integer.parseInt(seasonProgramId)));
            seasonHSPF1Details.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            seasonHSPF1Details.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         seasonHSPF1Details.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_F1_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonHSPF1Details;
   }

   @Transactional(readOnly = true)
   public HSPF1BasicDetails getHSPF1NameAndStatus(String seasonProgramId) {
      HSPF1BasicDetails hspf1BasicDetails = new HSPF1BasicDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         hspf1BasicDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_F1_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return hspf1BasicDetails;
      }
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (allF1Details != null) {
            hspf1BasicDetails = seasonServiceImplUtil.getHSPF1NameAndStatus(allF1Details);
            hspf1BasicDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            hspf1BasicDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         hspf1BasicDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_F1_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return hspf1BasicDetails;
   }

   @Transactional(readOnly = true)
   public HSPF1Accounting getHSPF1Accounting(String seasonProgramId) {
      HSPF1Accounting hspf1Accounting = new HSPF1Accounting();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         hspf1Accounting.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_F1_ACCOUNTING_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return hspf1Accounting;
      }
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (allF1Details != null) {
            hspf1Accounting = seasonServiceImplUtil.getHSPF1Accounting(allF1Details);
            hspf1Accounting.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            hspf1Accounting.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         hspf1Accounting.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_F1_ACCOUNTING_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_ACCOUNTING_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_ACCOUNTING_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return hspf1Accounting;
   }

   @Transactional(readOnly = true)
   public HSPF1JanuaryStart2NdSemesterDetails getHSPF1JanuaryStart2NdSemesterDetails(String seasonProgramId) {
      HSPF1JanuaryStart2NdSemesterDetails hspf1JanuaryStart2NdSemesterDetails = new HSPF1JanuaryStart2NdSemesterDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         hspf1JanuaryStart2NdSemesterDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
               SeasonCodes.ERROR_GET_F1_JAN_2ND_SEM_DETAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return hspf1JanuaryStart2NdSemesterDetails;
      }
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (allF1Details != null) {
            hspf1JanuaryStart2NdSemesterDetails = seasonServiceImplUtil.getHSPF1JanuaryStart2NdSemesterDetails(allF1Details);
            hspf1JanuaryStart2NdSemesterDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            hspf1JanuaryStart2NdSemesterDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         hspf1JanuaryStart2NdSemesterDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
               SeasonCodes.ERROR_GET_F1_JAN_2ND_SEM_DETAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_JAN_2ND_SEM_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_JAN_2ND_SEM_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return hspf1JanuaryStart2NdSemesterDetails;
   }

   @Transactional(readOnly = true)
   public HSPF1JanuaryStartFullYearDetail getHSPF1JanuaryStartFullYearDetails(String seasonProgramId) {
      HSPF1JanuaryStartFullYearDetail hSPF1JanuaryStartFullYearDetail = new HSPF1JanuaryStartFullYearDetail();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         hSPF1JanuaryStartFullYearDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_F1_JAN_FULL_YR_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return hSPF1JanuaryStartFullYearDetail;
      }
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (allF1Details != null) {
            hSPF1JanuaryStartFullYearDetail = seasonServiceImplUtil.getHSPF1JanuaryStartFullYearDetails(allF1Details);
            hSPF1JanuaryStartFullYearDetail.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            hSPF1JanuaryStartFullYearDetail.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         hSPF1JanuaryStartFullYearDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_F1_JAN_FULL_YR_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_JAN_2ND_SEM_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_JAN_2ND_SEM_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return hSPF1JanuaryStartFullYearDetail;
   }

   @Transactional(readOnly = true)
   public HSPF1AugustStart1StSemesterDetails getHSPF1AugustStart1StSemesterDetails(String seasonProgramId) {
      HSPF1AugustStart1StSemesterDetails hSPF1AugustStart1StSemesterDetails = new HSPF1AugustStart1StSemesterDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         hSPF1AugustStart1StSemesterDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
               SeasonCodes.ERROR_GET_F1_AUG_1ST_SEM_DETAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return hSPF1AugustStart1StSemesterDetails;
      }
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (allF1Details != null) {
            hSPF1AugustStart1StSemesterDetails = seasonServiceImplUtil.getHSPF1AugustStart1StSemesterDetails(allF1Details);
            hSPF1AugustStart1StSemesterDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            hSPF1AugustStart1StSemesterDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         hSPF1AugustStart1StSemesterDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
               SeasonCodes.ERROR_GET_F1_AUG_1ST_SEM_DETAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_AUG_1ST_SEM_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_AUG_1ST_SEM_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return hSPF1AugustStart1StSemesterDetails;
   }

   @Transactional(readOnly = true)
   public HSPF1AugustStartFullYearDetails getHSPF1AugustStartFullYearDetails(String seasonProgramId) {
      HSPF1AugustStartFullYearDetails hSPF1AugustStartFullYearDetails = new HSPF1AugustStartFullYearDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         hSPF1AugustStartFullYearDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_F1_AUG_FULL_YR_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return hSPF1AugustStartFullYearDetails;
      }
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (allF1Details != null) {
            hSPF1AugustStartFullYearDetails = seasonServiceImplUtil.getHSPF1AugustStartFullYearDetails(allF1Details);
            hSPF1AugustStartFullYearDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            hSPF1AugustStartFullYearDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         hSPF1AugustStartFullYearDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_F1_AUG_FULL_YR_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_AUG_FULL_YR_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_AUG_FULL_YR_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return hSPF1AugustStartFullYearDetails;
   }

   @Transactional(readOnly = true)
   public HSPF1FieldSettings getHSPF1FieldSettings(String seasonProgramId) {
      HSPF1FieldSettings hSPF1FieldSettings = new HSPF1FieldSettings();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         hSPF1FieldSettings.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_F1_FIELD_SETTING_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return hSPF1FieldSettings;
      }
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (allF1Details != null) {
            hSPF1FieldSettings = seasonServiceImplUtil.getHSPF1FieldSettings(allF1Details);
            hSPF1FieldSettings.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            hSPF1FieldSettings.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         hSPF1FieldSettings.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_F1_FIELD_SETTING_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_FIELD_SETTING_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_FIELD_SETTING_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return hSPF1FieldSettings;
   }

   @Transactional(readOnly = true)
   public HSPF1ProgramAllocations getHSPF1ProgramAllocations(String seasonProgramId) {
      HSPF1ProgramAllocations hspf1ProgramAllocations = new HSPF1ProgramAllocations();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         hspf1ProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_F1_PROGRAM_ALLOCATION.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return hspf1ProgramAllocations;
      }
      try {
         SeasonF1Detail allF1Detail = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (allF1Detail != null) {
            List<SeasonHSPAllocation> hspAllocations = seasonHSPAllocationRepository.findSeasonHSPAllocationBySeasonId(allF1Detail.getSeason().getSeasonId());
            if (hspAllocations != null) {
               hspf1ProgramAllocations = new HSPF1ProgramAllocations();
               int totalMaxParticipants = 0;
               int januaryStartMaximumParticipants = 0;
               int augustStartMaximumParticipants = 0;
               hspf1ProgramAllocations.setSeasonId(allF1Detail.getSeason().getSeasonId());
               hspf1ProgramAllocations.setSeasonProgramId(Integer.parseInt(seasonProgramId));
               try {
                  for (SeasonHSPAllocation seasonHSPAllocation : hspAllocations) {
                     Integer departmentProgramId = seasonHSPAllocation.getDepartmentProgramOption().getDepartmentProgramOptionId();
                     if (departmentProgramId == CCIConstants.AUGUST_FY_F1_ID || departmentProgramId == CCIConstants.JANUARY_FY_F1_ID) {
                        if (seasonHSPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_F1)) {
                           augustStartMaximumParticipants = seasonHSPAllocation.getMaxGuaranteedPax() > 0 ? seasonHSPAllocation.getMaxGuaranteedPax() : 0;
                           totalMaxParticipants += augustStartMaximumParticipants > 0 ? augustStartMaximumParticipants : 0;
                        }
                        if (seasonHSPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_F1)) {
                           januaryStartMaximumParticipants = seasonHSPAllocation.getMaxGuaranteedPax() > 0 ? seasonHSPAllocation.getMaxGuaranteedPax() : 0;
                           totalMaxParticipants += januaryStartMaximumParticipants > 0 ? januaryStartMaximumParticipants : 0;
                        }
                     }
                  }
               } catch (Exception e) {
                  ExceptionUtil.logException(e, LOGGER);
               }
               hspf1ProgramAllocations.setAugustStartMaximumParticipants(augustStartMaximumParticipants);
               hspf1ProgramAllocations.setJanuaryStartMaximumParticipants(januaryStartMaximumParticipants);
               hspf1ProgramAllocations.setAugustStartAcceptedParticipants(0);
               hspf1ProgramAllocations.setAugustStartRemainingParticipants(0);
               hspf1ProgramAllocations.setJanuaryStartAcceptedParticipants(0);
               hspf1ProgramAllocations.setJanuaryStartRemainingParticipants(0);
               hspf1ProgramAllocations.setTotalAcceptedParticipants(0);
               hspf1ProgramAllocations.setTotalRemainingParticipants(0);
               hspf1ProgramAllocations.setTotalMaximumParticipants(totalMaxParticipants);
               hspf1ProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               hspf1ProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                     messageUtil.getMessage(CCIConstants.NO_RECORD)));
            }
         } else {
            hspf1ProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         hspf1ProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_F1_PROGRAM_ALLOCATION.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_PROGRAM_ALLOCATION)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_F1_PROGRAM_ALLOCATION) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return hspf1ProgramAllocations;
   }

   @Transactional
   public SeasonHSPF1Details updateF1Details(SeasonHSPF1Details seasonHSPF1Details) {
      SeasonHSPF1Details returnObject = new SeasonHSPF1Details();
      if (seasonHSPF1Details == null || seasonHSPF1Details.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(seasonHSPF1Details.getSeasonProgramId());
         if (allF1Details != null) {
            returnObject = seasonServiceImplUtil.updateF1Details(allF1Details, seasonHSPF1Details);
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public HSPF1BasicDetails updateHSPF1NameAndStatus(HSPF1BasicDetails hspf1BasicDetails) {
      HSPF1BasicDetails returnObject = new HSPF1BasicDetails();
      if (hspf1BasicDetails == null || hspf1BasicDetails.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_BASE_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1BasicDetails.getSeasonProgramId());
         if (allF1Details != null) {
            returnObject = seasonServiceImplUtil.updateHSPF1NameAndStatus(allF1Details, hspf1BasicDetails);
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_BASE_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_BASE_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_BASE_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public HSPF1Accounting updateF1Accounting(HSPF1Accounting hspf1Accounting) {
      HSPF1Accounting returnObject = new HSPF1Accounting();
      if (hspf1Accounting == null) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_ACCOUNTING_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1Accounting.getSeasonProgramId());
         if (allF1Details != null) {
            returnObject = seasonServiceImplUtil.updateF1Accounting(allF1Details, hspf1Accounting);
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_ACCOUNTING_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_ACCOUNTING_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_ACCOUNTING_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public HSPF1JanuaryStart2NdSemesterDetails updateF1JanStart2NdSemesterDetails(HSPF1JanuaryStart2NdSemesterDetails hspf1JanuaryStart2NdSemesterDetails) {
      HSPF1JanuaryStart2NdSemesterDetails returnObject = new HSPF1JanuaryStart2NdSemesterDetails();
      if (hspf1JanuaryStart2NdSemesterDetails == null || hspf1JanuaryStart2NdSemesterDetails.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_JAN_2ND_SEM.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1JanuaryStart2NdSemesterDetails.getSeasonProgramId());
         if (allF1Details != null) {
            returnObject = seasonServiceImplUtil.updateF1JanStart2NdSemesterDetails(allF1Details, hspf1JanuaryStart2NdSemesterDetails);
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_JAN_2ND_SEM.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_JAN_2ND_SEM)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_JAN_2ND_SEM) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public HSPF1JanuaryStartFullYearDetail updateF1JanStartFullYearDetails(HSPF1JanuaryStartFullYearDetail hspf1JanuaryStartFullYearDetail) {
      HSPF1JanuaryStartFullYearDetail returnObject = new HSPF1JanuaryStartFullYearDetail();
      if (hspf1JanuaryStartFullYearDetail == null || hspf1JanuaryStartFullYearDetail.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_JAN_FULL_YR.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1JanuaryStartFullYearDetail.getSeasonProgramId());
         if (allF1Details != null) {
            returnObject = seasonServiceImplUtil.updateF1JanStartFullYearDetails(allF1Details, hspf1JanuaryStartFullYearDetail);
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_JAN_FULL_YR.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_JAN_FULL_YR)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_JAN_FULL_YR) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public HSPF1AugustStart1StSemesterDetails updateF1AugStart1StSemesterDetails(HSPF1AugustStart1StSemesterDetails hspf1AugustStart1StSemesterDetails) {
      HSPF1AugustStart1StSemesterDetails returnObject = new HSPF1AugustStart1StSemesterDetails();
      if (hspf1AugustStart1StSemesterDetails == null || hspf1AugustStart1StSemesterDetails.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_AUG_1ST_SEM.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1AugustStart1StSemesterDetails.getSeasonProgramId());
         if (allF1Details != null) {
            returnObject = seasonServiceImplUtil.updateF1AugStart1StSemesterDetails(allF1Details, hspf1AugustStart1StSemesterDetails);
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_AUG_1ST_SEM.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_AUG_1ST_SEM)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_AUG_1ST_SEM) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public HSPF1AugustStartFullYearDetails updateF1AugStartFullYearDetails(HSPF1AugustStartFullYearDetails hspf1AugustStartFullYearDetails) {
      HSPF1AugustStartFullYearDetails returnObject = new HSPF1AugustStartFullYearDetails();
      if (hspf1AugustStartFullYearDetails == null || hspf1AugustStartFullYearDetails.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_AUG_FULL_YR.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1AugustStartFullYearDetails.getSeasonProgramId());
         if (allF1Details != null) {
            returnObject = seasonServiceImplUtil.updateF1AugStartFullYearDetails(allF1Details, hspf1AugustStartFullYearDetails);
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_AUG_FULL_YR.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_AUG_FULL_YR)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_AUG_FULL_YR) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public HSPF1FieldSettings updateF1FieldSettings(HSPF1FieldSettings hspf1FieldSettings) {
      HSPF1FieldSettings returnObject = new HSPF1FieldSettings();
      if (hspf1FieldSettings == null || hspf1FieldSettings.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_FIELD_SETTINGS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1FieldSettings.getSeasonProgramId());
         if (allF1Details != null) {
            returnObject = seasonServiceImplUtil.updateF1FieldSettings(allF1Details, hspf1FieldSettings);
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_FIELD_SETTINGS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_FIELD_SETTINGS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_FIELD_SETTINGS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public HSPF1ProgramAllocations updateF1ProgramAllocation(HSPF1ProgramAllocations hspf1ProgramAllocations) {
      HSPF1ProgramAllocations returnObject = new HSPF1ProgramAllocations();
      if (hspf1ProgramAllocations == null) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         if (hspf1ProgramAllocations != null && hspf1ProgramAllocations.getSeasonId() > 0 && hspf1ProgramAllocations.getSeasonProgramId() > 0) {
            List<SeasonHSPAllocation> hspAllocations = seasonHSPAllocationRepository.findSeasonHSPAllocationBySeasonId(hspf1ProgramAllocations.getSeasonId());
            List<SeasonHSPAllocation> updatedList = new ArrayList<SeasonHSPAllocation>();
            for (SeasonHSPAllocation seasonHSPAllocation : hspAllocations) {
               Integer departmentProgramId = seasonHSPAllocation.getDepartmentProgramOption().getDepartmentProgramOptionId();
               if (departmentProgramId == CCIConstants.AUGUST_FY_F1_ID || departmentProgramId == CCIConstants.JANUARY_FY_F1_ID)
                  seasonServiceImplUtil.updateHSPF1ProgramAllocation(hspf1ProgramAllocations, updatedList, seasonHSPAllocation);
            }
            seasonHSPAllocationRepository.save(updatedList);
            seasonHSPAllocationRepository.flush();
            returnObject = getHSPF1ProgramAllocations(String.valueOf(hspf1ProgramAllocations.getSeasonProgramId()));
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_F1_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_ALLOCATIONS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_F1_ALLOCATIONS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public void createF1ProgramAllocation(Season season, int loginId) {
      try {
         List<SeasonHSPAllocation> seasonhspAllocations = new ArrayList<SeasonHSPAllocation>();
         SeasonHSPAllocation august_FY = new SeasonHSPAllocation();
         DepartmentProgramOption departmentProgramOption_AUG = departmentProgramOptionRepository.findOne(CCIConstants.AUGUST_FY_F1_ID);
         august_FY.setDepartmentProgramOption(departmentProgramOption_AUG);
         august_FY.setMaxGuaranteedPax(0);
         august_FY.setMaxUnguaranteedPax(0);
         august_FY.setSeason(season);
         august_FY.setCreatedBy(loginId);
         august_FY.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         august_FY.setModifiedBy(loginId);
         august_FY.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonhspAllocations.add(august_FY);

         SeasonHSPAllocation Jan_FY = new SeasonHSPAllocation();
         DepartmentProgramOption departmentProgramOption_JAN = departmentProgramOptionRepository.findOne(CCIConstants.JANUARY_FY_F1_ID);
         Jan_FY.setDepartmentProgramOption(departmentProgramOption_JAN);
         Jan_FY.setMaxGuaranteedPax(0);
         Jan_FY.setMaxUnguaranteedPax(0);
         Jan_FY.setSeason(season);
         Jan_FY.setCreatedBy(loginId);
         Jan_FY.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         Jan_FY.setModifiedBy(loginId);
         Jan_FY.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonhspAllocations.add(Jan_FY);
         seasonHSPAllocationRepository.save(seasonhspAllocations);
         seasonHSPAllocationRepository.flush();
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
   }

   public SeasonHspStpIhpDetails getIHPDetails(String seasonProgramId) {
      SeasonHspStpIhpDetails seasonHspStpIhpDetails = new SeasonHspStpIhpDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty() || Integer.valueOf(seasonProgramId) == 0 || Integer.valueOf(seasonProgramId) < 0) {
         seasonHspStpIhpDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_IHP_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return seasonHspStpIhpDetails;
      }
      try {
         SeasonIHPDetail seasonIHPDetail = ihpDetailRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonIHPDetail != null) {
            seasonHspStpIhpDetails.setSeasonId(seasonIHPDetail.getSeason().getSeasonId());
            seasonHspStpIhpDetails.setSeasonProgramId(seasonIHPDetail.getSeasonIHPDetailsId());
            seasonHspStpIhpDetails.setDepartmentProgramId(CCIConstants.HSP_STP_IHP_ID);
            seasonHspStpIhpDetails.setIhpNameAndStatus(getIHPNameAndStatus(seasonProgramId));
            seasonHspStpIhpDetails.setIhpDates(getIHPDates(seasonProgramId));
            seasonHspStpIhpDetails.setIhpProgramConfiguration(ihpProgramHelper.getIHPConfiguration(Integer.valueOf(seasonProgramId)));
            seasonHspStpIhpDetails.getIhpDocuments().addAll(ihpProgramHelper.getIHPDocs(seasonIHPDetail.getSeason().getSeasonId(), seasonIHPDetail.getSeasonIHPDetailsId()));
            seasonHspStpIhpDetails.getIhpNotes().addAll(ihpProgramHelper.getIHPNotes(seasonIHPDetail.getSeason().getSeasonId(), seasonIHPDetail.getSeasonIHPDetailsId()));
            seasonHspStpIhpDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            seasonHspStpIhpDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         seasonHspStpIhpDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_IHP_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_IHP_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_IHP_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonHspStpIhpDetails;
   }

   public IHPNameAndStatus getIHPNameAndStatus(String seasonProgramId) {
      IHPNameAndStatus ihpNameAndStatus = new IHPNameAndStatus();
      if (Integer.valueOf(seasonProgramId) == 0 || Integer.valueOf(seasonProgramId) < 0) {
         ihpNameAndStatus.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_IHP_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return ihpNameAndStatus;
      }
      try {
         ihpNameAndStatus = ihpProgramHelper.getIHPNameAndStatus(Integer.valueOf(seasonProgramId));
         ihpNameAndStatus.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         ihpNameAndStatus.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_IHP_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_IHP_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_IHP_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return ihpNameAndStatus;
   }

   public IHPDates getIHPDates(String seasonProgramId) {
      IHPDates ihpDates = new IHPDates();
      if (Integer.valueOf(seasonProgramId) == 0 || Integer.valueOf(seasonProgramId) < 0) {
         ihpDates.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_IHP_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return ihpDates;
      }
      try {
         ihpDates = ihpProgramHelper.getIHPDates(Integer.valueOf(seasonProgramId));
         ihpDates.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         ihpDates.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_IHP_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_IHP_DATES)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_IHP_DATES) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return ihpDates;
   }

   public IHPProgramConfiguration getIHPProgramConfigurationDetails(String seasonProgramId) {
      IHPProgramConfiguration ihpProgramConfiguration = new IHPProgramConfiguration();
      if (Integer.valueOf(seasonProgramId) == 0 || Integer.valueOf(seasonProgramId) < 0) {
         ihpProgramConfiguration.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_IHP_CONFIG.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return ihpProgramConfiguration;
      }
      try {
         ihpProgramConfiguration = ihpProgramHelper.getIHPConfiguration(Integer.valueOf(seasonProgramId));
         ihpProgramConfiguration.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         ihpProgramConfiguration.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_IHP_CONFIG.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_IHP_CONFIG)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_IHP_CONFIG) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return ihpProgramConfiguration;
   }

   public SeasonHspStpIhpDetails updateIHPDetails(SeasonHspStpIhpDetails seasonHspStpIhpDetails) {
      SeasonHspStpIhpDetails returnObject = new SeasonHspStpIhpDetails();
      if (seasonHspStpIhpDetails == null || seasonHspStpIhpDetails.getSeasonProgramId() == 0 || seasonHspStpIhpDetails.getSeasonProgramId() < 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_IHP_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = ihpProgramHelper.updateIHPDetails(seasonHspStpIhpDetails);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_IHP_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_IHP_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_IHP_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   public IHPNameAndStatus updateIHPNameAndStatus(IHPNameAndStatus ihpNameAndStatus) {
      IHPNameAndStatus returnObject = new IHPNameAndStatus();
      if (ihpNameAndStatus == null || ihpNameAndStatus.getSeasonProgramId() == 0 || ihpNameAndStatus.getSeasonProgramId() < 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_IHP_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = ihpProgramHelper.updateIHPNameAndStatus(ihpNameAndStatus);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_IHP_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_IHP_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_IHP_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   public IHPDates updateIHPDates(IHPDates ihpDates) {
      IHPDates returnObject = new IHPDates();
      if (ihpDates == null || ihpDates.getSeasonProgramId() == 0 || ihpDates.getSeasonProgramId() < 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_IHP_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = ihpProgramHelper.updateIHPDates(ihpDates);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_IHP_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_IHP_DATES)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_IHP_DATES) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   public IHPProgramConfiguration updateIHPProgramConfigurationDetails(IHPProgramConfiguration ihpProgramConfiguration) {
      IHPProgramConfiguration returnObject = new IHPProgramConfiguration();
      if (ihpProgramConfiguration == null || ihpProgramConfiguration.getSeasonProgramId() == 0 || ihpProgramConfiguration.getSeasonProgramId() < 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_IHP_CONFIG.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = ihpProgramHelper.updateIHPProgramConfiguration(ihpProgramConfiguration);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_IHP_CONFIG.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_IHP_CONFIG)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_IHP_CONFIG) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   // view, edit GHT Volunteer Abroad season details
   public SeasonGHTDetails getGHTVASeasonDetails(String seasonProgramId) {
      SeasonGHTDetails seasonGHTDetails = new SeasonGHTDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_VA_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return seasonGHTDetails;
      }
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonVADetail != null) {
            seasonGHTDetails = new SeasonGHTDetails();
            seasonGHTDetails.setSeasonId(seasonVADetail.getSeason().getSeasonId());
            seasonGHTDetails.setDepartmentProgramId(CCIConstants.GHT_VOL_ABRD_ID);
            seasonGHTDetails.setSeasonProgramId(seasonVADetail.getSeasonVADetailsId());
            seasonGHTDetails.setGhtBaseDetails(seasonServiceImplUtil.getVABasicDetail(seasonVADetail));
            seasonGHTDetails.setGhtDates(seasonServiceImplUtil.getVADates(seasonVADetail));
            seasonGHTDetails.getGhtNotes().addAll(seasonServiceImplUtil.getGHTVAProgramNotes(seasonVADetail.getSeason().getSeasonId(), seasonVADetail.getSeasonVADetailsId()));
            seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_VA_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_VA_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_VA_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonGHTDetails;
   }

   public GHTSection1Base getGHTVASeasonNameAndStatus(String seasonProgramId) {
      GHTSection1Base ghtSection1Base = new GHTSection1Base();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_VA_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return ghtSection1Base;
      }
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonVADetail != null) {
            ghtSection1Base = seasonServiceImplUtil.getVABasicDetail(seasonVADetail);
            ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_VA_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_VA_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_VA_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return ghtSection1Base;
   }

   public GHTSection2Dates getGHTVASeasonDateDetails(String seasonProgramId) {
      GHTSection2Dates ghtSection2Dates = new GHTSection2Dates();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_VA_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return ghtSection2Dates;
      }
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonVADetail != null) {
            ghtSection2Dates = seasonServiceImplUtil.getVADates(seasonVADetail);
            ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_VA_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_VA_DATES)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_VA_DATES) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return ghtSection2Dates;
   }

   // update GHT Volunteer Abroad season details

   @Transactional
   public SeasonGHTDetails updateGHTVASeasonDetails(SeasonGHTDetails seasonGHTDetails) {
      SeasonGHTDetails returnObject = new SeasonGHTDetails();
      if (seasonGHTDetails == null || seasonGHTDetails.getSeasonProgramId() == 0 || seasonGHTDetails.getGhtBaseDetails() == null || seasonGHTDetails.getGhtDates() == null
            || seasonGHTDetails.getGhtNotes() == null) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_VA_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findOne(Integer.valueOf(seasonGHTDetails.getSeasonProgramId()));
         if (seasonVADetail != null) {
            seasonServiceImplUtil.updateVABasicDetails(seasonGHTDetails.getGhtBaseDetails(), seasonVADetail);
            seasonServiceImplUtil.updateVADates(seasonGHTDetails.getGhtDates(), seasonVADetail);
            seasonServiceImplUtil.updateGHTNotes(seasonGHTDetails, seasonVADetail.getSeason(), CCIConstants.GHT_VOL_ABRD_ID);
            seasonVADetail = seasonVADetailsRepository.saveAndFlush(seasonVADetail);
            returnObject = seasonGHTDetails;
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_VA_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_VA_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_VA_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public GHTSection1Base updateGHTVASeasonNameAndStatus(GHTSection1Base ghtSection1Base) {
      GHTSection1Base returnObject = new GHTSection1Base();
      if (ghtSection1Base == null || ghtSection1Base.getSeasonId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_VA_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findOne(Integer.valueOf(ghtSection1Base.getSeasonProgramId()));
         if (seasonVADetail != null) {
            seasonServiceImplUtil.updateVABasicDetails(ghtSection1Base, seasonVADetail);
            seasonVADetail = seasonVADetailsRepository.saveAndFlush(seasonVADetail);
            returnObject = ghtSection1Base;
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_VA_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_VA_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_VA_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public GHTSection2Dates updateGHTVASeasonDateDetails(GHTSection2Dates ghtSection2Dates) {
      GHTSection2Dates returnObject = new GHTSection2Dates();
      if (ghtSection2Dates == null || ghtSection2Dates.getSeasonId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_VA_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {

         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findOne(Integer.valueOf(ghtSection2Dates.getSeasonProgramId()));
         if (seasonVADetail != null) {
            seasonServiceImplUtil.updateVADates(ghtSection2Dates, seasonVADetail);
            seasonVADetail = seasonVADetailsRepository.saveAndFlush(seasonVADetail);
            returnObject = ghtSection2Dates;
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_VA_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_VA_DATES)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_VA_DATES) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   // view, edit GHT Work Abroad season details

   public SeasonGHTDetails getGHTWASeasonDetails(String seasonProgramId) {
      SeasonGHTDetails seasonGHTDetails = new SeasonGHTDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WA_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return seasonGHTDetails;
      }
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWADetail != null) {
            seasonGHTDetails.setSeasonId(seasonWADetail.getSeason().getSeasonId());
            seasonGHTDetails.setSeasonProgramId(seasonWADetail.getSeasonWADetailsId());
            seasonGHTDetails.setDepartmentProgramId(CCIConstants.GHT_WRK_ABRD_ID);
            seasonGHTDetails.setGhtBaseDetails(seasonServiceImplUtil.getWABasicDetail(seasonWADetail));
            seasonGHTDetails.setGhtDates(seasonServiceImplUtil.getWADates(seasonWADetail));
            seasonGHTDetails.getGhtNotes().addAll(seasonServiceImplUtil.getGHTWAProgramNotes(seasonWADetail.getSeason().getSeasonId(), seasonWADetail.getSeasonWADetailsId()));
            seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WA_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WA_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WA_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonGHTDetails;
   }

   public GHTSection1Base getGHTWASeasonNameAndStatus(String seasonProgramId) {
      GHTSection1Base ghtSection1Base = new GHTSection1Base();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WA_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return ghtSection1Base;
      }
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWADetail != null) {
            ghtSection1Base = seasonServiceImplUtil.getWABasicDetail(seasonWADetail);
            ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WA_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WA_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WA_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return ghtSection1Base;
   }

   public GHTSection2Dates getGHTWASeasonDateDetails(String seasonProgramId) {
      GHTSection2Dates ghtSection2Dates = new GHTSection2Dates();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WA_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return ghtSection2Dates;
      }
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWADetail != null) {
            ghtSection2Dates = seasonServiceImplUtil.getWADates(seasonWADetail);
            ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WA_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WA_DATES)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WA_DATES) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return ghtSection2Dates;
   }

   // update GHT Work Abroad season details

   @Transactional
   public SeasonGHTDetails updateGHTWASeasonDetails(SeasonGHTDetails seasonGHTDetails) {
      SeasonGHTDetails returnObject = new SeasonGHTDetails();
      if (seasonGHTDetails == null || seasonGHTDetails.getSeasonProgramId() == 0 || seasonGHTDetails.getGhtBaseDetails() == null || seasonGHTDetails.getGhtDates() == null
            || seasonGHTDetails.getGhtNotes() == null) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WA_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(seasonGHTDetails.getSeasonProgramId()));
         if (seasonWADetail != null) {
            seasonServiceImplUtil.updateWABasicDetails(seasonGHTDetails.getGhtBaseDetails(), seasonWADetail);
            seasonServiceImplUtil.updateWADates(seasonGHTDetails.getGhtDates(), seasonWADetail);
            seasonServiceImplUtil.updateGHTNotes(seasonGHTDetails, seasonWADetail.getSeason(), CCIConstants.GHT_WRK_ABRD_ID);
            seasonWADetail = seasonWADetailsRepository.saveAndFlush(seasonWADetail);
            returnObject = seasonGHTDetails;
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WA_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WA_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WA_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public GHTSection1Base updateGHTWASeasonNameAndStatus(GHTSection1Base ghtSection1Base) {
      GHTSection1Base returnObject = new GHTSection1Base();
      if (ghtSection1Base == null || ghtSection1Base.getSeasonId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WA_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(ghtSection1Base.getSeasonProgramId()));
         if (seasonWADetail != null) {
            seasonServiceImplUtil.updateWABasicDetails(ghtSection1Base, seasonWADetail);
            seasonWADetail = seasonWADetailsRepository.saveAndFlush(seasonWADetail);
            returnObject = ghtSection1Base;
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WA_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WA_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WA_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public GHTSection2Dates updateGHTWASeasonDateDetails(GHTSection2Dates ghtSection2Dates) {
      GHTSection2Dates returnObject = new GHTSection2Dates();
      if (ghtSection2Dates == null || ghtSection2Dates.getSeasonId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WA_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WA_DATES)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WA_DATES));
         return returnObject;
      }
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(ghtSection2Dates.getSeasonProgramId()));
         if (seasonWADetail != null) {
            seasonServiceImplUtil.updateWADates(ghtSection2Dates, seasonWADetail);
            seasonWADetail = seasonWADetailsRepository.saveAndFlush(seasonWADetail);
            returnObject = ghtSection2Dates;
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WA_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WA_DATES)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WA_DATES) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   public SeasonGHTDetails getGHTHSAbroad(String seasonProgramId) {
      SeasonGHTDetails seasonGHTDetails = new SeasonGHTDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSA_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return seasonGHTDetails;
      }
      try {
         SeasonHSADetail seasonHSADetail = seasonHSADetailsRepository.findOne(Integer.parseInt(seasonProgramId));
         if (seasonHSADetail != null) {
            seasonGHTDetails = seasonServiceImplUtil.getGHTHSAbroad(seasonHSADetail);
            seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSA_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_HSA_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_HSA_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonGHTDetails;
   }

   public GHTSection1Base getGHTHSSection1BaseAbroad(String seasonProgramId) {
      GHTSection1Base ghtSection1Base = new GHTSection1Base();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSA_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return ghtSection1Base;
      }
      try {
         ghtSection1Base = seasonServiceImplUtil.getGHTHSSection1BaseAbroad(seasonProgramId);
         ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSA_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_HSA_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_HSA_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return ghtSection1Base;
   }

   public GHTSection2Dates getGHTHSSection2DatesAbroad(String seasonProgramId) {
      GHTSection2Dates ghtSection2Dates = new GHTSection2Dates();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSA_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return ghtSection2Dates;
      }
      try {
         ghtSection2Dates = seasonServiceImplUtil.getGHTHSSection2DatesAbroad(seasonProgramId);
         ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_HSA_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_HSA_DATES)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_HSA_DATES) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return ghtSection2Dates;
   }

   @Transactional
   public SeasonGHTDetails updateGHTHSAbroad(SeasonGHTDetails seasonGHTDetails) {
      SeasonGHTDetails returnObject = new SeasonGHTDetails();
      if (seasonGHTDetails == null || seasonGHTDetails.getSeasonId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_HSA_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateGHTHSAbroad(seasonGHTDetails);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_HSA_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_HSA_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_HSA_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public GHTSection1Base updateGHTHSSection1BaseAbroad(GHTSection1Base ghtSection1Base) {
      GHTSection1Base returnObject = new GHTSection1Base();
      if (ghtSection1Base == null || ghtSection1Base.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_HSA_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateGHTHSSection1BaseAbroad(ghtSection1Base);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_HSA_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_HSA_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_HSA_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return ghtSection1Base;
   }

   @Transactional
   public GHTSection2Dates updateGHTHSSection2DatesAbroad(GHTSection2Dates ghtSection2Dates) {
      GHTSection2Dates returnObject = new GHTSection2Dates();
      if (ghtSection2Dates == null || ghtSection2Dates.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_HSA_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateGHTHSSection2DatesAbroad(ghtSection2Dates);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_HSA_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_HSA_DATES)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_HSA_DATES) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   public SeasonGHTDetails getGHTLanguageSchool(String seasonProgramId) {
      SeasonGHTDetails seasonGHTDetails = new SeasonGHTDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_LS_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return seasonGHTDetails;
      }
      try {
         SeasonLSDetail seasonLSDetail = seasonLSDetailsRepository.findOne(Integer.parseInt(seasonProgramId));
         if (seasonLSDetail != null) {
            seasonGHTDetails = seasonServiceImplUtil.getGHTLanguageSchool(seasonLSDetail);
            seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_LS_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_LS_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_LS_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonGHTDetails;
   }

   public GHTSection1Base getGHTLanguageSchoolSection1(String seasonProgramId) {
      GHTSection1Base ghtSection1Base = new GHTSection1Base();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_LS_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return ghtSection1Base;
      }
      try {
         ghtSection1Base = seasonServiceImplUtil.getGHTLanguageSchoolSection1(seasonProgramId);
         ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_LS_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_LS_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_LS_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return ghtSection1Base;
   }

   public GHTSection2Dates getGHTLanguageSchoolSection2Dates(String seasonProgramId) {
      GHTSection2Dates ghtSection2Dates = new GHTSection2Dates();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_LS_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return ghtSection2Dates;
      }
      try {
         ghtSection2Dates = seasonServiceImplUtil.getGHTLanguageSchoolSection2Dates(seasonProgramId);
         ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_LS_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_LS_DATES)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_LS_DATES) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return ghtSection2Dates;
   }

   @Transactional
   public SeasonGHTDetails updateGHTLanguageSchool(SeasonGHTDetails seasonGHTDetails) {
      SeasonGHTDetails returnObject = new SeasonGHTDetails();
      if (seasonGHTDetails == null) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_LS_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateGHTLanguageSchool(seasonGHTDetails);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_LS_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_LS_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_LS_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public GHTSection1Base updateGHTLanguageSchoolSection1(GHTSection1Base ghtSection1Base) {
      GHTSection1Base returnObject = new GHTSection1Base();
      if (ghtSection1Base == null || ghtSection1Base.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_LS_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateGHTLanguageSchoolSection1(ghtSection1Base);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_LS_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_LS_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_LS_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return ghtSection1Base;
   }

   @Transactional
   public GHTSection2Dates updateGHTLanguageSchoolSection2Dates(GHTSection2Dates ghtSection2Dates) {
      GHTSection2Dates returnObject = new GHTSection2Dates();
      if (ghtSection2Dates == null || ghtSection2Dates.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_LS_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateGHTLanguageSchoolSection2Dates(ghtSection2Dates);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_LS_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_LS_DATES)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_LS_DATES) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   public SeasonGHTDetails getGHTTeachAbroad(String seasonProgramId) {
      SeasonGHTDetails seasonGHTDetails = new SeasonGHTDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_TA_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return seasonGHTDetails;
      }
      try {
         SeasonTADetail seasonTADetail = seasonTADetailsRepository.findOne(Integer.parseInt(seasonProgramId));
         if (seasonTADetail != null) {
            seasonGHTDetails = seasonServiceImplUtil.getGHTTeachAbroad(seasonTADetail);
            seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         seasonGHTDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_TA_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_TA_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_TA_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonGHTDetails;
   }

   public GHTSection1Base getGHTTeachAbroadSection1(String seasonProgramId) {
      GHTSection1Base ghtSection1Base = new GHTSection1Base();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_TA_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return ghtSection1Base;
      }
      try {
         ghtSection1Base = seasonServiceImplUtil.getGHTTeachAbroadSection1(seasonProgramId);
         ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         ghtSection1Base.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_TA_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_TA_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_TA_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return ghtSection1Base;
   }

   public GHTSection2Dates getGHTTeachAbroadSection2Dates(String seasonProgramId) {
      GHTSection2Dates ghtSection2Dates = new GHTSection2Dates();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_TA_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return ghtSection2Dates;
      }
      try {
         ghtSection2Dates = seasonServiceImplUtil.getGHTTeachAbroadSection2Dates(seasonProgramId);
         ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         ghtSection2Dates.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_TA_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_TA_DATES)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_TA_DATES) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return ghtSection2Dates;
   }

   @Transactional
   public SeasonGHTDetails updateGHTTeachAbroad(SeasonGHTDetails seasonGHTDetails) {
      SeasonGHTDetails returnObject = new SeasonGHTDetails();
      if (seasonGHTDetails == null || seasonGHTDetails.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_TA_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateGHTTeachAbroad(seasonGHTDetails);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_TA_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_TA_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_TA_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonGHTDetails;
   }

   @Transactional
   public GHTSection1Base updateGHTTeachAbroadSection1(GHTSection1Base ghtSection1Base) {
      GHTSection1Base returnObject = new GHTSection1Base();
      if (ghtSection1Base == null || ghtSection1Base.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_TA_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateGHTTeachAbroadSection1(ghtSection1Base);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_TA_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_TA_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_TA_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return ghtSection1Base;
   }

   @Transactional
   public GHTSection2Dates updateGHTTeachAbroadSection2Dates(GHTSection2Dates ghtSection2Dates) {
      GHTSection2Dates returnObject = new GHTSection2Dates();
      if (ghtSection2Dates == null || ghtSection2Dates.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_TA_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateGHTTeachAbroadSection2Dates(ghtSection2Dates);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_TA_DATES.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_TA_DATES)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_TA_DATES) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   // CAP
   @Transactional(readOnly = true)
   public SeasonWPCAPDetails getWPCAPDetails(String seasonProgramId) {
      SeasonWPCAPDetails seasonWPCAPDetails = new SeasonWPCAPDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         seasonWPCAPDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_CAP_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return seasonWPCAPDetails;
      }
      try {
         seasonWPCAPDetails = seasonServiceImplUtil.getWPCAPDetails(seasonProgramId);
         seasonWPCAPDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         seasonWPCAPDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_CAP_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_CAP_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_CAP_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonWPCAPDetails;
   }

   @Transactional(readOnly = true)
   public WPCAPBasicDetails getWPCAPBasicDetails(String seasonProgramId) {
      WPCAPBasicDetails wpcapBasicDetails = new WPCAPBasicDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         wpcapBasicDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_CAP_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return wpcapBasicDetails;
      }
      try {
         wpcapBasicDetails = seasonServiceImplUtil.getWPCAPBasicDetails(seasonProgramId);
         wpcapBasicDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         wpcapBasicDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_CAP_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_CAP_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_CAP_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return wpcapBasicDetails;
   }

   @Transactional(readOnly = true)
   public WPCAPInternshipDetails getWPCAPInternshipDetails(String seasonProgramId) {
      WPCAPInternshipDetails wpcapInternshipDetails = new WPCAPInternshipDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         wpcapInternshipDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_CAP_INTERNESHIP_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return wpcapInternshipDetails;
      }
      try {
         wpcapInternshipDetails = seasonServiceImplUtil.getWPCAPInternshipDetails(seasonProgramId);
         wpcapInternshipDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         wpcapInternshipDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_CAP_INTERNESHIP_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_CAP_INTERNESHIP_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_CAP_INTERNESHIP_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return wpcapInternshipDetails;
   }

   @Transactional(readOnly = true)
   public WPCAPTraineeDetails getWPCAPTraineeDetails(String seasonProgramId) {
      WPCAPTraineeDetails wpcapTraineeDetails = new WPCAPTraineeDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         wpcapTraineeDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_CAP_TRAINEE_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return wpcapTraineeDetails;
      }
      try {
         wpcapTraineeDetails = seasonServiceImplUtil.getWPCAPTraineeDetails(seasonProgramId);
         wpcapTraineeDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         wpcapTraineeDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_CAP_TRAINEE_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_CAP_TRAINEE_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_CAP_TRAINEE_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return wpcapTraineeDetails;
   }

   @Transactional(readOnly = true)
   public WPCAPProgramAllocations getWPCAPAllocationDetails(String seasonProgramId) {
      WPCAPProgramAllocations wpcapProgramAllocations = new WPCAPProgramAllocations();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         wpcapProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_CAP_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return wpcapProgramAllocations;
      }
      try {
         SeasonCAPDetail seasonCapDetail = seasonCAPDetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonCapDetail != null) {
            List<SeasonWPAllocation> wpAllocations = seasonWPAllocationRepository.findSeasonWPAllocationBySeasonId(seasonCapDetail.getSeason().getSeasonId());
            if (wpAllocations != null && !wpAllocations.isEmpty()) {
               int totalMaxParticipants = 0;
               wpcapProgramAllocations.setSeasonId(seasonCapDetail.getSeason().getSeasonId());
               wpcapProgramAllocations.setSeasonProgramId(Integer.parseInt(seasonProgramId));
               try {
                  for (SeasonWPAllocation seasonWPAllocation : wpAllocations) {
                     if (seasonWPAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_CAP_ID) {
                        if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.CAP_TRAINEE)) {
                           wpcapProgramAllocations.setTraineeMaximumParticipant(seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0);
                           totalMaxParticipants += seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0;
                        }
                        if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.CAP_INTERNSHIP)) {
                           wpcapProgramAllocations.setInternshipMaximumParticipant(seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0);
                           totalMaxParticipants += seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0;
                        }

                     }
                  }
               } catch (Exception e) {
                  ExceptionUtil.logException(e, LOGGER);
               }
               wpcapProgramAllocations.setInternshipAcceptedParticipant(0);
               wpcapProgramAllocations.setInternshipCCIReview(0);
               wpcapProgramAllocations.setInternshipExpectedParticipant(0);
               wpcapProgramAllocations.setInternshipPendingVerification(0);
               wpcapProgramAllocations.setInternshipRemainingParticipants(0);

               wpcapProgramAllocations.setTraineeAcceptedParticipant(0);
               wpcapProgramAllocations.setTraineeCCIReview(0);
               wpcapProgramAllocations.setTraineeExpectedParticipant(0);
               wpcapProgramAllocations.setTraineePendingVerification(0);
               wpcapProgramAllocations.setTraineeRemainingParticipants(0);

               wpcapProgramAllocations.setTotalAcceptedParticipant(0);
               wpcapProgramAllocations.setTotalCCIReview(0);
               wpcapProgramAllocations.setTotalExpectedParticipant(0);
               wpcapProgramAllocations.setTotalPendingVerification(0);
               wpcapProgramAllocations.setTotalRemainingParticipants(0);

               wpcapProgramAllocations.setTotalMaximumParticipant(totalMaxParticipants);
               wpcapProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               wpcapProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                     messageUtil.getMessage(CCIConstants.NO_RECORD)));
            }
         } else {
            wpcapProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         wpcapProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_CAP_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_CAP_ALLOCATIONS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_CAP_ALLOCATIONS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return wpcapProgramAllocations;
   }

   @Transactional
   public SeasonWPCAPDetails updateWPCAPDetails(SeasonWPCAPDetails seasonWPCAPDetails) {
      SeasonWPCAPDetails returnObject = new SeasonWPCAPDetails();
      if (seasonWPCAPDetails == null || seasonWPCAPDetails.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_CAP_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateWPCAPDetails(seasonWPCAPDetails);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_CAP_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_CAP_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_CAP_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public WPCAPBasicDetails updateWPCAPBasicDetails(WPCAPBasicDetails wpcapBasicDetails) {
      WPCAPBasicDetails returnObject = new WPCAPBasicDetails();
      if (wpcapBasicDetails == null || wpcapBasicDetails.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_CAP_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateWPCAPBasicDetails(wpcapBasicDetails);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_CAP_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_CAP_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_CAP_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public WPCAPInternshipDetails updateWPCAPInternshipDetails(WPCAPInternshipDetails wpcapInternshipDetails) {
      WPCAPInternshipDetails returnObject = new WPCAPInternshipDetails();
      if (wpcapInternshipDetails == null || wpcapInternshipDetails.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_CAP_INTERNESHIP_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateWPCAPInternshipDetails(wpcapInternshipDetails);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_CAP_INTERNESHIP_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_CAP_INTERNESHIP_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_CAP_INTERNESHIP_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public WPCAPTraineeDetails updateWPCAPTraineeDetails(WPCAPTraineeDetails wpcapTraineeDetails) {
      WPCAPTraineeDetails returnObject = new WPCAPTraineeDetails();
      if (wpcapTraineeDetails == null || wpcapTraineeDetails.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_CAP_TRAINEE_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateWPCAPTraineeDetails(wpcapTraineeDetails);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_CAP_TRAINEE_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_CAP_TRAINEE_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_CAP_TRAINEE_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public WPCAPProgramAllocations updateWPCAPAllocationDetails(WPCAPProgramAllocations wpcapProgramAllocations) {
      WPCAPProgramAllocations returnObject = new WPCAPProgramAllocations();
      if (wpcapProgramAllocations != null && wpcapProgramAllocations.getSeasonId() > 0 && wpcapProgramAllocations.getSeasonProgramId() > 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_CAP_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         List<SeasonWPAllocation> wpAllocations = seasonWPAllocationRepository.findSeasonWPAllocationBySeasonId(wpcapProgramAllocations.getSeasonId());
         if (wpAllocations != null) {
            List<SeasonWPAllocation> updatedList = new ArrayList<SeasonWPAllocation>();
            for (SeasonWPAllocation seasonWPAllocation : wpAllocations) {
               if (seasonWPAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_CAP_ID) {
                  seasonServiceImplUtil.updateWPProgramAllocation(wpcapProgramAllocations, updatedList, seasonWPAllocation);
               }
            }
            seasonWPAllocationRepository.save(updatedList);
            returnObject = getWPCAPAllocationDetails(String.valueOf(wpcapProgramAllocations.getSeasonProgramId()));
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_CAP_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_CAP_ALLOCATIONS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_CAP_ALLOCATIONS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   // Work Programs: Work and Travel summer season service implementations

   public SeasonWPDetails getWPSumDetails(String seasonProgramId) {
      SeasonWPDetails seasonWPDetails = new SeasonWPDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         seasonWPDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_SUMMER_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return seasonWPDetails;
      }
      try {
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSummerDetail != null) {
            seasonWPDetails = new SeasonWPDetails();
            seasonWPDetails.setSeasonId(seasonWnTSummerDetail.getSeason().getSeasonId());
            seasonWPDetails.setSeasonProgramId(seasonWnTSummerDetail.getSeasonWnTSummerDetailsId());
            seasonWPDetails.setDepartmentProgramId(CCIConstants.WP_WT_SUMMER_ID);
            seasonWPDetails.setWpBasicDetail(seasonServiceImplUtil.getWPSummerBaseDetails(seasonWnTSummerDetail));
            seasonWPDetails.setWpSectionOne(seasonServiceImplUtil.getWPSummerSection1Details(seasonWnTSummerDetail));
            seasonWPDetails.setWpProgramAllocations(getWPSumAllocationDetails(seasonProgramId));
            seasonWPDetails.getWpNotes().addAll(
                  seasonServiceImplUtil.getWPSummerNotes(seasonWnTSummerDetail.getSeason().getSeasonId(), seasonWnTSummerDetail.getSeasonWnTSummerDetailsId()));
            seasonWPDetails.getWpDocuments().addAll(
                  seasonServiceImplUtil.getWPDocs(seasonWnTSummerDetail.getSeason().getSeasonId(), seasonWnTSummerDetail.getSeasonWnTSummerDetailsId(), CCIConstants.WP_WT_SUMMER,
                        CCIConstants.WP_WT_SUMMER_ID));
            seasonWPDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            seasonWPDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         seasonWPDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_SUMMER_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_SUMMER_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_SUMMER_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonWPDetails;
   }

   public WPBasicDetail getWPSumBaseDetails(String seasonProgramId) {
      WPBasicDetail wpBasicDetail = new WPBasicDetail();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         wpBasicDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_SUMMER_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return wpBasicDetail;
      }
      try {
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSummerDetail != null) {
            wpBasicDetail = seasonServiceImplUtil.getWPSummerBaseDetails(seasonWnTSummerDetail);
            wpBasicDetail.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            wpBasicDetail.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         wpBasicDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_SUMMER_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_SUMMER_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_SUMMER_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return wpBasicDetail;
   }

   public WPSectionOne getWPSumSectionOneDetails(String seasonProgramId) {
      WPSectionOne wpSectionOne = new WPSectionOne();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         wpSectionOne.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_SUMMER_SECTION1.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return wpSectionOne;
      }
      try {
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSummerDetail != null) {
            wpSectionOne = seasonServiceImplUtil.getWPSummerSection1Details(seasonWnTSummerDetail);
            wpSectionOne.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            wpSectionOne.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         wpSectionOne.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_SUMMER_SECTION1.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_SUMMER_SECTION1)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_SUMMER_SECTION1) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return wpSectionOne;
   }

   @Transactional(readOnly = true)
   public WPProgramAllocations getWPSumAllocationDetails(String seasonProgramId) {
      WPProgramAllocations wpProgramAllocations = new WPProgramAllocations();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         wpProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_SUMMER_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return wpProgramAllocations;
      }
      try {
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSummerDetail != null) {
            List<SeasonWPAllocation> wpAllocations = seasonWPAllocationRepository.findSeasonWPAllocationBySeasonId(seasonWnTSummerDetail.getSeason().getSeasonId());
            if (wpAllocations != null) {
               wpProgramAllocations = new WPProgramAllocations();
               int totalMaxParticipants = 0;
               wpProgramAllocations.setSeasonId(seasonWnTSummerDetail.getSeason().getSeasonId());
               wpProgramAllocations.setSeasonProgramId(seasonWnTSummerDetail.getSeasonWnTSummerDetailsId());
               try {
                  for (SeasonWPAllocation seasonWPAllocation : wpAllocations) {
                     if (seasonWPAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_SUMMER_ID) {
                        if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JOB_FAIR_SUMMER)) {
                           wpProgramAllocations.setJobFairMaxParticipants(seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0);
                           totalMaxParticipants += seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0;
                        }
                        if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.SELF_PLACED_SUMMER)) {
                           wpProgramAllocations.setSelfPlacedMaxParticipants(seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0);
                           totalMaxParticipants += seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0;
                        }
                        if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.DIRECT_PLACEMENT_SUMMER)) {
                           wpProgramAllocations.setDirectPlcmntMaxParticipants(seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0);
                           totalMaxParticipants += seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0;
                        }
                     }
                  }
               } catch (Exception e) {
                  ExceptionUtil.logException(e, LOGGER);
               }
               wpProgramAllocations.setDirectPlcmntAcceptedParticipants(0);
               wpProgramAllocations.setDirectPlcmntRemainingParticipants(0);
               wpProgramAllocations.setDirectPlcmntCCIReview(0);
               wpProgramAllocations.setDirectPlcmntExpectedParticipants(0);
               wpProgramAllocations.setDirectPlcmntPendingVerification(0);
               wpProgramAllocations.setJobFairAcceptedParticipants(0);
               wpProgramAllocations.setJobFairCCIReview(0);
               wpProgramAllocations.setJobFairExpectedParticipants(0);
               wpProgramAllocations.setJobFairPendingVerification(0);
               wpProgramAllocations.setJobFairRemainingParticipants(0);
               wpProgramAllocations.setSelfPlacedAcceptedParticipants(0);
               wpProgramAllocations.setSelfPlacedCCIReview(0);
               wpProgramAllocations.setSelfPlacedExpectedParticipants(0);
               wpProgramAllocations.setSelfPlacedPendingVerification(0);
               wpProgramAllocations.setSelfPlacedRemainingParticipants(0);
               wpProgramAllocations.setTotalAcceptedParticipants(0);
               wpProgramAllocations.setTotalCCIReview(0);
               wpProgramAllocations.setTotalExpectedParticipants(0);
               wpProgramAllocations.setTotalPendingVerification(0);
               wpProgramAllocations.setTotalRemainingParticipants(0);
               wpProgramAllocations.setTotalMaxParticipants(totalMaxParticipants);
               wpProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               wpProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                     messageUtil.getMessage(CCIConstants.NO_RECORD)));
            }
         } else {
            wpProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         wpProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_SUMMER_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_SUMMER_ALLOCATIONS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_SUMMER_ALLOCATIONS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return wpProgramAllocations;
   }

   @Transactional
   public SeasonWPDetails updateWPSumDetails(SeasonWPDetails seasonWPDetails) {
      SeasonWPDetails returnObject = new SeasonWPDetails();
      if (seasonWPDetails == null || seasonWPDetails.getWpBasicDetail() == null || seasonWPDetails.getWpSectionOne() == null || seasonWPDetails.getWpProgramAllocations() == null) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_SUMMER_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(seasonWPDetails.getSeasonProgramId()));
         if (seasonWnTSummerDetail != null) {
            seasonServiceImplUtil.updateWPSummerBaseDetails(seasonWPDetails.getWpBasicDetail(), seasonWnTSummerDetail);
            seasonServiceImplUtil.updateWPSummerSection1Details(seasonWPDetails.getWpSectionOne(), seasonWnTSummerDetail);
            updateWPSumAllocationDetails(seasonWPDetails.getWpProgramAllocations());
            if (seasonWPDetails.getWpNotes() != null) {
               seasonServiceImplUtil.updateWPNotes(seasonWPDetails, seasonWnTSummerDetail.getSeason(), CCIConstants.WP_WT_SUMMER_ID);
            }
            if (seasonWPDetails.getWpDocuments() != null) {
               seasonServiceImplUtil.updateWPDocs(seasonWPDetails, seasonWnTSummerDetail.getSeason(), CCIConstants.WP_WT_SUMMER_ID);
            }
            seasonWnTSummerDetail = seasonWTSummerRepository.saveAndFlush(seasonWnTSummerDetail);
            returnObject = seasonWPDetails;
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_SUMMER_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_SUMMER_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_SUMMER_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public WPBasicDetail updateWPSumBaseDetails(WPBasicDetail wpBasicDetail) {
      WPBasicDetail returnObject = new WPBasicDetail();
      if (wpBasicDetail == null || wpBasicDetail.getSeasonId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_SUMMER_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(wpBasicDetail.getSeasonProgramId()));
         if (seasonWnTSummerDetail != null) {
            seasonServiceImplUtil.updateWPSummerBaseDetails(wpBasicDetail, seasonWnTSummerDetail);
            seasonWnTSummerDetail = seasonWTSummerRepository.saveAndFlush(seasonWnTSummerDetail);
            returnObject = wpBasicDetail;
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_SUMMER_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_SUMMER_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_SUMMER_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public WPSectionOne updateWPSumSectionOneDetails(WPSectionOne wpSectionOne) {
      WPSectionOne returnObject = new WPSectionOne();
      if (wpSectionOne == null || wpSectionOne.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_SUMMER_SECTION1.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(wpSectionOne.getSeasonProgramId()));
         if (seasonWnTSummerDetail != null) {
            seasonServiceImplUtil.updateWPSummerSection1Details(wpSectionOne, seasonWnTSummerDetail);
            seasonWnTSummerDetail = seasonWTSummerRepository.saveAndFlush(seasonWnTSummerDetail);
            returnObject = wpSectionOne;
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_SUMMER_SECTION1.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_SUMMER_SECTION1)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_SUMMER_SECTION1) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public WPProgramAllocations updateWPSumAllocationDetails(WPProgramAllocations wpProgramAllocations) {
      WPProgramAllocations returnObject = new WPProgramAllocations();
      if (wpProgramAllocations == null || wpProgramAllocations.getSeasonId() < 0 || wpProgramAllocations.getSeasonProgramId() < 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_SUMMER_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         List<SeasonWPAllocation> wpAllocations = seasonWPAllocationRepository.findSeasonWPAllocationBySeasonId(wpProgramAllocations.getSeasonId());
         if (wpAllocations != null) {
            List<SeasonWPAllocation> updatedList = new ArrayList<SeasonWPAllocation>();
            for (SeasonWPAllocation seasonWPAllocation : wpAllocations) {
               if (seasonWPAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_SUMMER_ID) {
                  seasonServiceImplUtil.updateWPSummerProgramAllocation(wpProgramAllocations, updatedList, seasonWPAllocation);
               }
            }
            seasonWPAllocationRepository.save(updatedList);
            returnObject = getWPSumAllocationDetails(String.valueOf(wpProgramAllocations.getSeasonProgramId()));
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_SUMMER_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_SUMMER_ALLOCATIONS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_SUMMER_ALLOCATIONS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public void createWPSumProgramAllocation(Season season, int loginId) {
      try {
         List<SeasonWPAllocation> seasonWpAllocations = new ArrayList<SeasonWPAllocation>();
         SeasonWPAllocation jobFairSummer = new SeasonWPAllocation();
         DepartmentProgramOption departmentProgramOption_JobFair = departmentProgramOptionRepository.findOne(CCIConstants.JOB_FAIR_SUMMER_ID);
         jobFairSummer.setDepartmentProgramOption(departmentProgramOption_JobFair);
         jobFairSummer.setMaxPax(0);
         jobFairSummer.setSeason(season);
         jobFairSummer.setCreatedBy(loginId);
         jobFairSummer.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         jobFairSummer.setModifiedBy(loginId);
         jobFairSummer.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));

         seasonWpAllocations.add(jobFairSummer);

         SeasonWPAllocation selfPlacedSummer = new SeasonWPAllocation();
         DepartmentProgramOption departmentProgramOption_SelfPlaceSpring = departmentProgramOptionRepository.findOne(CCIConstants.SELF_PLACED_SUMMER_ID);
         selfPlacedSummer.setDepartmentProgramOption(departmentProgramOption_SelfPlaceSpring);
         selfPlacedSummer.setMaxPax(0);
         selfPlacedSummer.setSeason(season);
         selfPlacedSummer.setCreatedBy(loginId);
         selfPlacedSummer.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         selfPlacedSummer.setModifiedBy(loginId);
         selfPlacedSummer.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWpAllocations.add(selfPlacedSummer);

         SeasonWPAllocation directPlacementSummer = new SeasonWPAllocation();
         DepartmentProgramOption departmentProgramOption_DirectPlacementSummer = departmentProgramOptionRepository.findOne(CCIConstants.DIRECT_PLACEMENT_SUMMER_ID);
         directPlacementSummer.setDepartmentProgramOption(departmentProgramOption_DirectPlacementSummer);
         directPlacementSummer.setMaxPax(0);
         directPlacementSummer.setSeason(season);
         directPlacementSummer.setCreatedBy(loginId);
         directPlacementSummer.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         directPlacementSummer.setModifiedBy(loginId);
         directPlacementSummer.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWpAllocations.add(directPlacementSummer);

         seasonWPAllocationRepository.save(seasonWpAllocations);
         seasonWPAllocationRepository.flush();

      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
   }

   @Transactional(readOnly = true)
   public SeasonWPDetails getWPSpringDetails(String seasonProgramId) {
      SeasonWPDetails seasonWPDetails = new SeasonWPDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         seasonWPDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_SPRING_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return seasonWPDetails;
      }
      try {
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSpringDetail != null) {
            seasonWPDetails.setSeasonId(seasonWnTSpringDetail.getSeason().getSeasonId());
            seasonWPDetails.setSeasonProgramId(seasonWnTSpringDetail.getSeasonWnTSpringDetailsId());
            seasonWPDetails.setDepartmentProgramId(CCIConstants.WP_WT_SPRING_ID);
            seasonWPDetails.setWpBasicDetail(seasonServiceImplUtil.getWPSpringBaseDetails(seasonWnTSpringDetail));
            seasonWPDetails.setWpSectionOne(seasonServiceImplUtil.getWPSpringSection1Details(seasonWnTSpringDetail));
            seasonWPDetails.setWpProgramAllocations(getWPSpringAllocationDetails(seasonProgramId));
            seasonWPDetails.getWpNotes().addAll(
                  seasonServiceImplUtil.getWPSpringNotes(seasonWnTSpringDetail.getSeason().getSeasonId(), seasonWnTSpringDetail.getSeasonWnTSpringDetailsId()));
            seasonWPDetails.getWpDocuments().addAll(
                  seasonServiceImplUtil.getWPDocs(seasonWnTSpringDetail.getSeason().getSeasonId(), seasonWnTSpringDetail.getSeasonWnTSpringDetailsId(), CCIConstants.WP_WT_SPRING,
                        CCIConstants.WP_WT_SPRING_ID));
            seasonWPDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            seasonWPDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         seasonWPDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_SPRING_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_SPRING_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_SPRING_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonWPDetails;
   }

   @Transactional(readOnly = true)
   public WPBasicDetail getWPSpringBaseDetails(String seasonProgramId) {
      WPBasicDetail wpBasicDetail = new WPBasicDetail();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         wpBasicDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_SPRING_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return wpBasicDetail;
      }
      try {
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSpringDetail != null) {
            wpBasicDetail = seasonServiceImplUtil.getWPSpringBaseDetails(seasonWnTSpringDetail);
            wpBasicDetail.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            wpBasicDetail.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         wpBasicDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_SPRING_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_SPRING_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_SPRING_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return wpBasicDetail;
   }

   @Transactional(readOnly = true)
   public WPSectionOne getWPSpringSectionOneDetails(String seasonProgramId) {
      WPSectionOne wpSectionOne = new WPSectionOne();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         wpSectionOne.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_SPRING_SECTION1.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return wpSectionOne;
      }
      try {
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(seasonProgramId));

         if (seasonWnTSpringDetail != null) {
            wpSectionOne = seasonServiceImplUtil.getWPSpringSection1Details(seasonWnTSpringDetail);
            wpSectionOne.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            wpSectionOne.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         wpSectionOne.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_SPRING_SECTION1.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_SPRING_SECTION1)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_SPRING_SECTION1) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return wpSectionOne;
   }

   @Transactional(readOnly = true)
   public WPProgramAllocations getWPSpringAllocationDetails(String seasonProgramId) {
      WPProgramAllocations wpProgramAllocations = new WPProgramAllocations();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         wpProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_SPRING_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return wpProgramAllocations;
      }
      try {
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSpringDetail != null) {
            List<SeasonWPAllocation> wpAllocations = seasonWPAllocationRepository.findSeasonWPAllocationBySeasonId(seasonWnTSpringDetail.getSeason().getSeasonId());
            if (wpAllocations != null) {
               wpProgramAllocations = new WPProgramAllocations();
               int totalMaxParticipants = 0;
               wpProgramAllocations.setSeasonId(seasonWnTSpringDetail.getSeason().getSeasonId());
               wpProgramAllocations.setSeasonProgramId(seasonWnTSpringDetail.getSeasonWnTSpringDetailsId());
               try {
                  for (SeasonWPAllocation seasonWPAllocation : wpAllocations) {
                     if (seasonWPAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_SPRING_ID) {
                        if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JOB_FAIR_SPRING)) {
                           wpProgramAllocations.setJobFairMaxParticipants(seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0);
                           totalMaxParticipants += seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0;
                        }
                        if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.SELF_PLACED_SPRING)) {
                           wpProgramAllocations.setSelfPlacedMaxParticipants(seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0);
                           totalMaxParticipants += seasonWPAllocation.getMaxPax();
                        }
                        if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.DIRECT_PLACEMENT_SPRING)) {
                           wpProgramAllocations.setDirectPlcmntMaxParticipants(seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0);
                           totalMaxParticipants += seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0;
                        }
                     }
                  }
               } catch (Exception e) {
                  ExceptionUtil.logException(e, LOGGER);
               }
               wpProgramAllocations.setDirectPlcmntAcceptedParticipants(0);
               wpProgramAllocations.setDirectPlcmntRemainingParticipants(0);
               wpProgramAllocations.setDirectPlcmntCCIReview(0);
               wpProgramAllocations.setDirectPlcmntExpectedParticipants(0);
               wpProgramAllocations.setDirectPlcmntPendingVerification(0);
               wpProgramAllocations.setJobFairAcceptedParticipants(0);
               wpProgramAllocations.setJobFairCCIReview(0);
               wpProgramAllocations.setJobFairExpectedParticipants(0);
               wpProgramAllocations.setJobFairPendingVerification(0);
               wpProgramAllocations.setJobFairRemainingParticipants(0);
               wpProgramAllocations.setSelfPlacedAcceptedParticipants(0);
               wpProgramAllocations.setSelfPlacedCCIReview(0);
               wpProgramAllocations.setSelfPlacedExpectedParticipants(0);
               wpProgramAllocations.setSelfPlacedPendingVerification(0);
               wpProgramAllocations.setSelfPlacedRemainingParticipants(0);
               wpProgramAllocations.setTotalAcceptedParticipants(0);
               wpProgramAllocations.setTotalCCIReview(0);
               wpProgramAllocations.setTotalExpectedParticipants(0);
               wpProgramAllocations.setTotalPendingVerification(0);
               wpProgramAllocations.setTotalRemainingParticipants(0);
               wpProgramAllocations.setTotalMaxParticipants(totalMaxParticipants);
               wpProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               wpProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                     messageUtil.getMessage(CCIConstants.NO_RECORD)));
            }
         } else {
            wpProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         wpProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_SPRING_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_SPRING_ALLOCATIONS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_SPRING_ALLOCATIONS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return wpProgramAllocations;
   }

   @Transactional
   public SeasonWPDetails updateWPSpringDetails(SeasonWPDetails seasonWPDetails) {
      SeasonWPDetails returnObject = new SeasonWPDetails();
      if (seasonWPDetails == null || seasonWPDetails.getSeasonProgramId() == 0 || seasonWPDetails.getWpBasicDetail() == null || seasonWPDetails.getWpSectionOne() == null
            || seasonWPDetails.getWpProgramAllocations() == null) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_SPRING_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(seasonWPDetails.getSeasonProgramId()));
         if (seasonWnTSpringDetail != null) {
            seasonServiceImplUtil.updateWPSpringBaseDetails(seasonWPDetails.getWpBasicDetail(), seasonWnTSpringDetail);
            seasonServiceImplUtil.updateWPSpringSection1Details(seasonWPDetails.getWpSectionOne(), seasonWnTSpringDetail);
            updateWPSpringAllocationDetails(seasonWPDetails.getWpProgramAllocations());
            if (seasonWPDetails.getWpNotes() != null) {
               seasonServiceImplUtil.updateWPNotes(seasonWPDetails, seasonWnTSpringDetail.getSeason(), CCIConstants.WP_WT_SPRING_ID);
            }
            if (seasonWPDetails.getWpDocuments() != null) {
               seasonServiceImplUtil.updateWPDocs(seasonWPDetails, seasonWnTSpringDetail.getSeason(), CCIConstants.WP_WT_SPRING_ID);
            }
            seasonWnTSpringDetail = seasonWTSpringRepository.saveAndFlush(seasonWnTSpringDetail);
            returnObject = seasonWPDetails;
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_SPRING_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_SPRING_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_SPRING_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public WPBasicDetail updateWPSpringBaseDetails(WPBasicDetail wpBasicDetail) {
      WPBasicDetail returnObject = new WPBasicDetail();
      if (wpBasicDetail == null || wpBasicDetail.getSeasonId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_SPRING_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(wpBasicDetail.getSeasonProgramId()));
         if (seasonWnTSpringDetail != null) {
            seasonServiceImplUtil.updateWPSpringBaseDetails(wpBasicDetail, seasonWnTSpringDetail);
            seasonWnTSpringDetail = seasonWTSpringRepository.saveAndFlush(seasonWnTSpringDetail);
            returnObject = wpBasicDetail;
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_SPRING_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_SPRING_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_SPRING_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public WPSectionOne updateWPSpringSectionOneDetails(WPSectionOne wpSectionOne) {
      WPSectionOne returnObject = new WPSectionOne();
      if (wpSectionOne == null || wpSectionOne.getSeasonProgramId() == 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_SPRING_SECTION1.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(wpSectionOne.getSeasonProgramId()));
         if (seasonWnTSpringDetail != null) {
            seasonServiceImplUtil.updateWPSpringSection1Details(wpSectionOne, seasonWnTSpringDetail);
            seasonWnTSpringDetail = seasonWTSpringRepository.saveAndFlush(seasonWnTSpringDetail);
            returnObject = wpSectionOne;
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_SPRING_SECTION1.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_SPRING_SECTION1)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_SPRING_SECTION1) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public WPProgramAllocations updateWPSpringAllocationDetails(WPProgramAllocations wpProgramAllocations) {
      WPProgramAllocations returnObject = new WPProgramAllocations();
      if (wpProgramAllocations == null || wpProgramAllocations.getSeasonId() < 0 || wpProgramAllocations.getSeasonProgramId() < 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_SPRING_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         List<SeasonWPAllocation> wpAllocations = seasonWPAllocationRepository.findSeasonWPAllocationBySeasonId(wpProgramAllocations.getSeasonId());
         if (wpAllocations != null) {
            List<SeasonWPAllocation> updatedList = new ArrayList<SeasonWPAllocation>();
            for (SeasonWPAllocation seasonWPAllocation : wpAllocations) {
               if (seasonWPAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_SPRING_ID) {
                  seasonServiceImplUtil.updateWPSpringProgramAllocation(wpProgramAllocations, updatedList, seasonWPAllocation);
               }
            }
            seasonWPAllocationRepository.save(updatedList);
            returnObject = getWPSpringAllocationDetails(String.valueOf(wpProgramAllocations.getSeasonProgramId()));
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_SPRING_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_SPRING_ALLOCATIONS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_SPRING_ALLOCATIONS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public void createWPSpringProgramAllocation(Season season, int loginId) {
      try {
         List<SeasonWPAllocation> seasonWpAllocations = new ArrayList<SeasonWPAllocation>();
         SeasonWPAllocation jobFairSpring = new SeasonWPAllocation();
         DepartmentProgramOption departmentProgramOption_JobFair = departmentProgramOptionRepository.findOne(CCIConstants.JOB_FAIR_SPRING_ID);
         jobFairSpring.setDepartmentProgramOption(departmentProgramOption_JobFair);
         jobFairSpring.setMaxPax(0);
         jobFairSpring.setSeason(season);
         jobFairSpring.setCreatedBy(loginId);
         jobFairSpring.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         jobFairSpring.setModifiedBy(loginId);
         jobFairSpring.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWpAllocations.add(jobFairSpring);

         SeasonWPAllocation selfPlacedSpring = new SeasonWPAllocation();
         DepartmentProgramOption departmentProgramOption_SelfPlaceSpring = departmentProgramOptionRepository.findOne(CCIConstants.SELF_PLACED_SPRING_ID);
         selfPlacedSpring.setDepartmentProgramOption(departmentProgramOption_SelfPlaceSpring);
         selfPlacedSpring.setMaxPax(0);
         selfPlacedSpring.setSeason(season);
         selfPlacedSpring.setCreatedBy(loginId);
         selfPlacedSpring.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         selfPlacedSpring.setModifiedBy(loginId);
         selfPlacedSpring.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWpAllocations.add(selfPlacedSpring);

         SeasonWPAllocation directPlacementSpring = new SeasonWPAllocation();
         DepartmentProgramOption departmentProgramOption_DirectPlacementSpring = departmentProgramOptionRepository.findOne(CCIConstants.DIRECT_PLACEMENT_SPRING_ID);
         directPlacementSpring.setDepartmentProgramOption(departmentProgramOption_DirectPlacementSpring);
         directPlacementSpring.setMaxPax(0);
         directPlacementSpring.setSeason(season);
         directPlacementSpring.setCreatedBy(loginId);
         directPlacementSpring.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         directPlacementSpring.setModifiedBy(loginId);
         directPlacementSpring.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWpAllocations.add(directPlacementSpring);

         seasonWPAllocationRepository.save(seasonWpAllocations);
         seasonWPAllocationRepository.flush();

      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
   }

   public SeasonWPDetails getWPWinterDetails(String seasonProgramId) {
      SeasonWPDetails seasonWPDetails = new SeasonWPDetails();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         seasonWPDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_WINTER_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return seasonWPDetails;
      }
      try {
         seasonWPDetails = seasonServiceImplUtil.getWPWinterDetails(seasonProgramId);
         seasonWPDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         seasonWPDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_WINTER_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_WINTER_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_WINTER_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return seasonWPDetails;
   }

   public WPBasicDetail getWPWinterBaseDetails(String seasonProgramId) {
      WPBasicDetail wpBasicDetail = new WPBasicDetail();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         wpBasicDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_WINTER_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return wpBasicDetail;
      }
      try {
         wpBasicDetail = seasonServiceImplUtil.getWPWinterBaseDetails(seasonProgramId);
         wpBasicDetail.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         wpBasicDetail.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_WINTER_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_WINTER_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_WINTER_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return wpBasicDetail;
   }

   public WPSectionOne getWPWinterSectionOneDetails(String seasonProgramId) {
      WPSectionOne wpSectionOne = new WPSectionOne();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         wpSectionOne.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_WINTER_SECTION1.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return wpSectionOne;
      }
      try {
         wpSectionOne = seasonServiceImplUtil.getWPWinterSectionOneDetails(seasonProgramId);
         wpSectionOne.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         wpSectionOne.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_WINTER_SECTION1.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_WINTER_SECTION1)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_WINTER_SECTION1) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return wpSectionOne;
   }

   public WPProgramAllocations getWPWinterAllocationDetails(String seasonProgramId) {
      WPProgramAllocations wpProgramAllocations = new WPProgramAllocations();
      if (seasonProgramId == null || seasonProgramId.isEmpty()) {
         wpProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_WINTER_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALD_SEASON_PROGRAM_ID));
         return wpProgramAllocations;
      }
      try {
         wpProgramAllocations = seasonServiceImplUtil.getWPWinterAllocationDetails(seasonProgramId);
         wpProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         wpProgramAllocations.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_WP_WINTER_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_WINTER_ALLOCATIONS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_WP_WINTER_ALLOCATIONS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return wpProgramAllocations;
   }

   @Transactional
   public SeasonWPDetails updateWPWinterDetails(SeasonWPDetails seasonWPDetails) {
      SeasonWPDetails returnObject = new SeasonWPDetails();
      if (seasonWPDetails == null) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_WINTER_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateWPWinterDetails(seasonWPDetails);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_WINTER_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_WINTER_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_WINTER_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public WPBasicDetail updateWPWinterBaseDetails(WPBasicDetail wpBasicDetail) {
      WPBasicDetail returnObject = new WPBasicDetail();
      if (wpBasicDetail == null) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_WINTER_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateWPWinterBaseDetails(wpBasicDetail);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_WINTER_BASIC_DETAILS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_WINTER_BASIC_DETAILS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_WINTER_BASIC_DETAILS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public WPSectionOne updateWPWinterSectionOneDetails(WPSectionOne wpSectionOne) {
      WPSectionOne returnObject = new WPSectionOne();
      if (wpSectionOne == null) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_WINTER_SECTION1.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateWPWinterSectionOneDetails(wpSectionOne);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_WINTER_SECTION1.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_WINTER_SECTION1)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_WINTER_SECTION1) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public WPProgramAllocations updateWPWinterAllocationDetails(WPProgramAllocations wpProgramAllocations) {
      WPProgramAllocations returnObject = new WPProgramAllocations();
      if (wpProgramAllocations == null) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_WINTER_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      try {
         returnObject = seasonServiceImplUtil.updateWPWinterAllocationDetails(wpProgramAllocations);
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_UPDATE_WP_WINTER_ALLOCATIONS.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_WINTER_ALLOCATIONS)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_UPDATE_WP_WINTER_ALLOCATIONS) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   @Transactional
   public CloneSeason cloneSeason(CloneSeason cloneSeason) {
      CloneSeason returnObject = new CloneSeason();
      if (cloneSeason.getSeasonId() == 0 || cloneSeason.getSeasonId() < 0 || cloneSeason.getDepartmentId() == 0 || cloneSeason.getDepartmentId() < 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_CLONE_SEASON.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
         return returnObject;
      }
      Season checkDuplicate = seasonRepository.findBySeasonName(cloneSeason.getNewCloneSeasonName());
      if (checkDuplicate == null) {
         try {
            Season existingSeason = seasonRepository.findOne(cloneSeason.getSeasonId());
            if (existingSeason != null) {
               LookupDepartment department = existingSeason.getLookupDepartment();
               if (department != null) {
                  List<SeasonDepartmentDocument> clonedSeasonDocs = null;
                  List<SeasonDepartmentDocument> existingSeasonDocs = existingSeason.getSeasonDepartmentDocuments();
                  List<com.ccighgo.db.entities.SeasonProgramDocument> clonedPrgDocs = null;
                  List<com.ccighgo.db.entities.SeasonProgramDocument> existingDocs = existingSeason.getSeasonProgramDocuments();
                  if (department.getDepartmentName().equals(CCIConstants.DEPT_HIGH_SCHOOL_PROGRAMS)) {
                     Season season = seasonCloningHelper.cloneHighLevelSeason(cloneSeason, existingSeason, department);
                     if (cloneSeason.getClonedSeasonNotes() != null && !(cloneSeason.getClonedSeasonNotes().isEmpty())) {
                        // create notes for newly cloned season
                        List<SeasonDepartmentNote> notesList = new ArrayList<SeasonDepartmentNote>();
                        for (ClonedSeasonNotes notes : cloneSeason.getClonedSeasonNotes()) {
                           SeasonDepartmentNote seasonNote = new SeasonDepartmentNote();
                           seasonNote.setDepartmentNote(notes.getNoteValue());
                           seasonNote.setActive(CCIConstants.ACTIVE);
                           seasonNote.setSeason(season);
                           seasonNote.setCreatedBy(cloneSeason.getLoginId());
                           seasonNote.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                           seasonNote.setModifiedBy(cloneSeason.getLoginId());
                           seasonNote.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                           notesList.add(seasonNote);
                        }
                        seasonDepartmentNotesRepository.save(notesList);
                        seasonDepartmentNotesRepository.flush();
                     }
                     if (cloneSeason.getClonedDocuments() != null && !(cloneSeason.getClonedDocuments().isEmpty())) {
                        List<SeasonDepartmentDocument> newDocList = new ArrayList<SeasonDepartmentDocument>();
                        for (ClonedDocuments doc : cloneSeason.getClonedDocuments()) {
                           SeasonDepartmentDocument sprgDoc = new SeasonDepartmentDocument();
                           DocumentInformation documentInformation = new DocumentInformation();
                           documentInformation.setFileName(doc.getFileName());
                           documentInformation.setDocumentName(doc.getDocName());
                           documentInformation.setUrl(doc.getDocUrl());
                           documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(doc.getDocType()));
                           documentInformation.setCreatedBy(cloneSeason.getLoginId());
                           documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                           documentInformation.setModifiedBy(cloneSeason.getLoginId());
                           documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                           documentInformation = documentInformationRepository.saveAndFlush(documentInformation);
                           sprgDoc.setActive(CCIConstants.ACTIVE);
                           sprgDoc.setSeason(season);
                           sprgDoc.setDocumentInformation(documentInformation);
                           sprgDoc.setCreatedBy(cloneSeason.getLoginId());
                           sprgDoc.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                           sprgDoc.setModifiedBy(cloneSeason.getLoginId());
                           sprgDoc.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                           newDocList.add(sprgDoc);
                        }
                        seasonDepartmentDocumentRepository.save(newDocList);
                        seasonDepartmentDocumentRepository.flush();
                     }
                     Season clonedHSPSeason = seasonRepository.saveAndFlush(season);
                     // clone season documents
                     if (existingSeasonDocs != null && existingSeasonDocs.size() > 0) {
                        clonedSeasonDocs = new ArrayList<SeasonDepartmentDocument>();
                        for (SeasonDepartmentDocument doc : existingSeasonDocs) {
                           if (doc.getSeason().getLookupDepartment().getDepartmentName().equals(CCIConstants.DEPT_HIGH_SCHOOL_PROGRAMS)) {
                              clonedSeasonDocs.add(seasonCloningHelper.getSeasonDepartmentDocument(doc, clonedHSPSeason, cloneSeason.getLoginId()));
                           }
                        }
                        seasonDepartmentDocumentRepository.save(clonedSeasonDocs);
                     }
                     // clone regions and assignments
                     if (existingSeason.getSeasonGeographyConfigurations() != null) {
                        List<SeasonGeographyConfiguration> existingRegions = existingSeason.getSeasonGeographyConfigurations();
                        List<SeasonGeographyConfiguration> clonedRegions = new ArrayList<SeasonGeographyConfiguration>();
                        for (SeasonGeographyConfiguration config : existingRegions) {
                           config.setSeason(clonedHSPSeason);
                           clonedRegions.add(config);
                        }
                        seasonGeographyConfigurationRepository.save(clonedRegions);
                        seasonGeographyConfigurationRepository.flush();
                     }
                     if (existingSeason.getFieldStaffLeadershipSeasons() != null) {
                        List<FieldStaffLeadershipSeason> existingAssignments = existingSeason.getFieldStaffLeadershipSeasons();
                        List<FieldStaffLeadershipSeason> clonedAssignments = new ArrayList<FieldStaffLeadershipSeason>();
                        for (FieldStaffLeadershipSeason fieldStaffLeadershipSeason : existingAssignments) {
                           fieldStaffLeadershipSeason.setSeason(clonedHSPSeason);
                           clonedAssignments.add(fieldStaffLeadershipSeason);
                        }
                        fieldStaffLeadershipSeasonRepository.save(clonedAssignments);
                        fieldStaffLeadershipSeasonRepository.flush();
                     }

                     List<SeasonHSPAllocation> seasonHspallocations = existingSeason.getSeasonHspallocations();
                     List<SeasonHSPAllocation> seasonHspallocationNewList = null;
                     if (seasonHspallocations != null && seasonHspallocations.size() > 0) {
                        seasonHspallocationNewList = seasonCloningHelper.cloneHSPAllocations(clonedHSPSeason, seasonHspallocations, cloneSeason.getLoginId());
                     }
                     SeasonHSPConfiguration seasonHSPConfiguration = seasonCloningHelper.cloneHSPConfiguration(cloneSeason, clonedHSPSeason);
                     SeasonJ1Detail seasonJ1Detail = seasonCloningHelper.cloneHSPJ1seasonProgram(existingSeason, clonedHSPSeason, cloneSeason.getLoginId());
                     SeasonF1Detail seasonF1Detail = seasonCloningHelper.cloneHSPF1SeasonProgram(existingSeason, clonedHSPSeason, cloneSeason.getLoginId());
                     SeasonIHPDetail seasonIHPDetail = seasonCloningHelper.cloneHSPIHPProgram(existingSeason, clonedHSPSeason, cloneSeason, cloneSeason.getLoginId());
                     if (seasonHspallocationNewList != null) {
                        seasonHSPAllocationRepository.save(seasonHspallocationNewList);
                     }
                     hspConfigurationRepsitory.save(seasonHSPConfiguration);
                     if (seasonJ1Detail != null) {
                        seasonJ1DetailsRepository.save(seasonJ1Detail);
                        if (existingDocs != null) {
                           clonedPrgDocs = new ArrayList<com.ccighgo.db.entities.SeasonProgramDocument>();
                           for (com.ccighgo.db.entities.SeasonProgramDocument doc : existingDocs) {
                              if (doc.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_J1_HS)) {
                                 clonedPrgDocs.add(seasonCloningHelper.getSeasonProgramDocument(doc, clonedHSPSeason, cloneSeason.getLoginId()));
                              }
                           }
                           seasonProgramDocumentRepository.save(clonedPrgDocs);
                        }
                     }
                     if (seasonF1Detail != null) {
                        seasonF1DetailsRepository.save(seasonF1Detail);
                        if (existingDocs != null) {
                           clonedPrgDocs = new ArrayList<com.ccighgo.db.entities.SeasonProgramDocument>();
                           for (com.ccighgo.db.entities.SeasonProgramDocument doc : existingDocs) {
                              if (doc.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_F1)) {
                                 clonedPrgDocs.add(seasonCloningHelper.getSeasonProgramDocument(doc, clonedHSPSeason, cloneSeason.getLoginId()));
                              }
                           }
                           seasonProgramDocumentRepository.save(clonedPrgDocs);
                        }
                     }
                     if (seasonIHPDetail != null) {
                        seasonIHPDetailRepository.save(seasonIHPDetail);
                        if (existingDocs != null) {
                           clonedPrgDocs = new ArrayList<com.ccighgo.db.entities.SeasonProgramDocument>();
                           for (com.ccighgo.db.entities.SeasonProgramDocument doc : existingDocs) {
                              if (doc.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_STP_IHP)) {
                                 clonedPrgDocs.add(seasonCloningHelper.getSeasonProgramDocument(doc, clonedHSPSeason, cloneSeason.getLoginId()));
                              }
                           }
                           seasonProgramDocumentRepository.save(clonedPrgDocs);
                        }
                     }
                     cloneSeason.setSeasonId(clonedHSPSeason.getSeasonId());
                     returnObject = cloneSeason;
                     returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                           messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
                  }
                  if (department.getDepartmentName().equals(CCIConstants.DEPT_WORK_PROGRAMS)) {
                     // clone high level WP season
                     Season season = seasonCloningHelper.cloneHighLevelSeason(cloneSeason, existingSeason, department);
                     Season clonedWPSeason = seasonRepository.saveAndFlush(season);
                     // clone high level season documents
                     if (existingSeasonDocs != null && existingSeasonDocs.size() > 0) {
                        clonedSeasonDocs = new ArrayList<SeasonDepartmentDocument>();
                        for (SeasonDepartmentDocument doc : existingSeasonDocs) {
                           if (doc.getSeason().getLookupDepartment().getDepartmentName().equals(CCIConstants.DEPT_WORK_PROGRAMS)) {
                              clonedSeasonDocs.add(seasonCloningHelper.getSeasonDepartmentDocument(doc, clonedWPSeason, cloneSeason.getLoginId()));
                           }
                        }
                        seasonDepartmentDocumentRepository.save(clonedSeasonDocs);
                     }
                     List<SeasonWPAllocation> seasonWPAllocations = existingSeason.getSeasonWpallocations();
                     List<SeasonWPAllocation> seasonWPAallocationCloneList = null;
                     if (seasonWPAllocations != null && seasonWPAllocations.size() > 0) {
                        seasonWPAallocationCloneList = seasonCloningHelper.cloneWPAllocations(clonedWPSeason, seasonWPAllocations, cloneSeason.getLoginId());
                     }
                     SeasonWPConfiguration seasonWPConfiguration = seasonCloningHelper.cloneWPConfigurations(cloneSeason, clonedWPSeason);
                     SeasonWnTSpringDetail seasonWnTSpringDetail = seasonCloningHelper.cloneWPSpringProgram(existingSeason, clonedWPSeason, cloneSeason.getLoginId());
                     SeasonWnTSummerDetail seasonWnTSummerDetail = seasonCloningHelper.cloneWPSummerProgram(existingSeason, clonedWPSeason, cloneSeason.getLoginId());
                     SeasonWnTWinterDetail seasonWnTWinterDetail = seasonCloningHelper.cloneWPWinterProgram(existingSeason, clonedWPSeason, cloneSeason.getLoginId());
                     SeasonCAPDetail seasonCAPDetail = seasonCloningHelper.cloneWPCapProgram(existingSeason, clonedWPSeason, cloneSeason.getLoginId());
                     seasonWPAllocationRepository.save(seasonWPAallocationCloneList);
                     seasonWPConfigurationRepository.save(seasonWPConfiguration);
                     if (seasonWnTSpringDetail != null) {
                        seasonWTSpringRepository.save(seasonWnTSpringDetail);
                        if (existingDocs != null) {
                           clonedPrgDocs = new ArrayList<com.ccighgo.db.entities.SeasonProgramDocument>();
                           for (com.ccighgo.db.entities.SeasonProgramDocument doc : existingDocs) {
                              if (doc.getDepartmentProgram().getProgramName().equals(CCIConstants.WP_WT_SPRING)) {
                                 clonedPrgDocs.add(seasonCloningHelper.getSeasonProgramDocument(doc, clonedWPSeason, cloneSeason.getLoginId()));
                              }
                           }
                           seasonProgramDocumentRepository.save(clonedPrgDocs);
                        }
                     }
                     if (seasonWnTSummerDetail != null) {
                        seasonWTSummerRepository.save(seasonWnTSummerDetail);
                        if (existingDocs != null) {
                           clonedPrgDocs = new ArrayList<com.ccighgo.db.entities.SeasonProgramDocument>();
                           for (com.ccighgo.db.entities.SeasonProgramDocument doc : existingDocs) {
                              if (doc.getDepartmentProgram().getProgramName().equals(CCIConstants.WP_WT_SUMMER)) {
                                 clonedPrgDocs.add(seasonCloningHelper.getSeasonProgramDocument(doc, clonedWPSeason, cloneSeason.getLoginId()));
                              }
                           }
                           seasonProgramDocumentRepository.save(clonedPrgDocs);
                        }
                     }
                     if (seasonWnTWinterDetail != null) {
                        seasonWTWinterRepository.save(seasonWnTWinterDetail);
                        if (existingDocs != null) {
                           clonedPrgDocs = new ArrayList<com.ccighgo.db.entities.SeasonProgramDocument>();
                           for (com.ccighgo.db.entities.SeasonProgramDocument doc : existingDocs) {
                              if (doc.getDepartmentProgram().getProgramName().equals(CCIConstants.WP_WT_WINTER)) {
                                 clonedPrgDocs.add(seasonCloningHelper.getSeasonProgramDocument(doc, clonedWPSeason, cloneSeason.getLoginId()));
                              }
                           }
                           seasonProgramDocumentRepository.save(clonedPrgDocs);
                        }
                     }
                     if (seasonCAPDetail != null) {
                        seasonCAPDetailsRepository.save(seasonCAPDetail);
                        if (existingDocs != null) {
                           clonedPrgDocs = new ArrayList<com.ccighgo.db.entities.SeasonProgramDocument>();
                           for (com.ccighgo.db.entities.SeasonProgramDocument doc : existingDocs) {
                              if (doc.getDepartmentProgram().getProgramName().equals(CCIConstants.WP_WT_CAP)) {
                                 clonedPrgDocs.add(seasonCloningHelper.getSeasonProgramDocument(doc, clonedWPSeason, cloneSeason.getLoginId()));
                              }
                           }
                           seasonProgramDocumentRepository.save(clonedPrgDocs);
                        }
                     }
                     cloneSeason.setSeasonId(clonedWPSeason.getSeasonId());
                     returnObject = cloneSeason;
                     returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                           messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
                  }
                  if (department.getDepartmentName().equals(CCIConstants.DEPT_GREEN_HEART_TRAVEL)) {
                     Season season = seasonCloningHelper.cloneHighLevelSeason(cloneSeason, existingSeason, department);
                     Season clonedGHTSeason = seasonRepository.saveAndFlush(season);
                     if (existingSeasonDocs != null && existingSeasonDocs.size() > 0) {
                        clonedSeasonDocs = new ArrayList<SeasonDepartmentDocument>();
                        for (SeasonDepartmentDocument doc : existingSeasonDocs) {
                           if (doc.getSeason().getLookupDepartment().getDepartmentName().equals(CCIConstants.DEPT_GREEN_HEART_TRAVEL)) {
                              clonedSeasonDocs.add(seasonCloningHelper.getSeasonDepartmentDocument(doc, clonedGHTSeason, cloneSeason.getLoginId()));
                           }
                        }
                        seasonDepartmentDocumentRepository.save(clonedSeasonDocs);
                     }
                     SeasonGHTConfiguration seasonGHTConfiguration = seasonCloningHelper.cloneGHTConfiguration(cloneSeason, clonedGHTSeason);
                     SeasonHSADetail seasonHSADetail = seasonCloningHelper.cloneGHTHSAProgram(existingSeason, clonedGHTSeason, cloneSeason.getLoginId());
                     SeasonLSDetail seasonLSDetail = seasonCloningHelper.cloneGHTLSProgram(existingSeason, clonedGHTSeason, cloneSeason.getLoginId());
                     SeasonTADetail seasonTADetail = seasonCloningHelper.cloneGHTTAProgram(existingSeason, clonedGHTSeason, cloneSeason.getLoginId());
                     SeasonVADetail seasonVADetail = seasonCloningHelper.cloneGHTVAProgram(existingSeason, clonedGHTSeason, cloneSeason.getLoginId());
                     SeasonWADetail seasonWADetail = seasonCloningHelper.cloneGHTWAProgram(existingSeason, clonedGHTSeason, cloneSeason.getLoginId());
                     seasonGHTConfigurationRepository.save(seasonGHTConfiguration);
                     if (seasonHSADetail != null) {
                        seasonHSADetailsRepository.save(seasonHSADetail);
                     }
                     if (seasonLSDetail != null) {
                        seasonLSDetailsRepository.save(seasonLSDetail);
                     }
                     if (seasonTADetail != null) {
                        seasonTADetailsRepository.save(seasonTADetail);
                     }
                     if (seasonVADetail != null) {
                        seasonVADetailsRepository.save(seasonVADetail);
                     }
                     if (seasonWADetail != null) {
                        seasonWADetailsRepository.save(seasonWADetail);
                     }
                     cloneSeason.setSeasonId(clonedGHTSeason.getSeasonId());
                     returnObject = cloneSeason;
                     returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                           messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

                  }
               } else {
                  returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_CLONE_SEASON.getValue(),
                        messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
                  LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
               }
            } else {
               returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_CLONE_SEASON.getValue(),
                     messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA)));
               LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID_SEASON_UPDATE_DATA));
            }

         } catch (CcighgoException e) {
            returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_CLONE_SEASON.getValue(),
                  messageUtil.getMessage(SeasonMessageConstants.ERROR_CLONE_SEASON)));
            LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_CLONE_SEASON) + CCIConstants.HYPHEN_SPACE + e.getMessage());
         }
      } else {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_CLONE_SEASON.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.DUPLICATE_NAME_CLONE_SEASON)));
      }
      return returnObject;
   }

   @Transactional
   public SeasonDepartmentNotes addSeasonDepartmentNote(SeasonDepartmentNotes seasonDepartmentNotes) {
      SeasonDepartmentNotes returnObject = new SeasonDepartmentNotes();
      if (seasonDepartmentNotes == null || seasonDepartmentNotes.getSeasonId() < 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_ADD_SEASON_NOTE.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_ADD_SEASON_NOTE)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_ADD_SEASON_NOTE));
         return returnObject;
      }
      try {
         SeasonDepartmentNote departmentNote = new SeasonDepartmentNote();
         departmentNote.setActive(CCIConstants.ACTIVE);
         departmentNote.setSeason(seasonRepository.findOne(seasonDepartmentNotes.getSeasonId()));
         departmentNote.setDepartmentNote(seasonDepartmentNotes.getNoteValue());
         departmentNote.setCreatedBy(seasonDepartmentNotes.getLoginId());
         departmentNote.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         departmentNote.setModifiedBy(seasonDepartmentNotes.getLoginId());
         departmentNote.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonDepartmentNotesRepository.saveAndFlush(departmentNote);
         returnObject = seasonDepartmentNotes;
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_ADD_SEASON_NOTE.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_ADD_SEASON_NOTE)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_ADD_SEASON_NOTE) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   public com.ccighgo.service.transport.season.beans.seasondepartdoc.SeasonDepartmentDocument addSeasonDepartmentDoc(
         com.ccighgo.service.transport.season.beans.seasondepartdoc.SeasonDepartmentDocument seasonDepartmentDocument) {
      com.ccighgo.service.transport.season.beans.seasondepartdoc.SeasonDepartmentDocument returnObject = new com.ccighgo.service.transport.season.beans.seasondepartdoc.SeasonDepartmentDocument();
      if (seasonDepartmentDocument == null || seasonDepartmentDocument.getSeasonId() < 0 || seasonDepartmentDocument.getDocUrl() == null
            || seasonDepartmentDocument.getDocName() == null) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_ADD_SEASON_DOCUMENT.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_ADD_SEASON_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_ADD_SEASON_DOCUMENT));
         return returnObject;
      }
      try {
         DocumentInformation documentInformation = new DocumentInformation();
         documentInformation.setFileName(seasonDepartmentDocument.getFileName() != null ? seasonDepartmentDocument.getFileName() : null);
         documentInformation.setDocumentName(seasonDepartmentDocument.getDocName() != null ? seasonDepartmentDocument.getDocName() : null);
         documentInformation.setUrl(seasonDepartmentDocument.getDocUrl());
         documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(seasonDepartmentDocument.getDocType()));
         documentInformation.setCreatedBy(seasonDepartmentDocument.getLoginId());
         documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         documentInformation.setModifiedBy(seasonDepartmentDocument.getLoginId());
         documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         documentInformation = documentInformationRepository.saveAndFlush(documentInformation);

         com.ccighgo.db.entities.SeasonDepartmentDocument departmentDocument = new com.ccighgo.db.entities.SeasonDepartmentDocument();
         departmentDocument.setActive(CCIConstants.ACTIVE);
         departmentDocument.setDocumentInformation(documentInformation);
         departmentDocument.setSeason(seasonRepository.findOne(seasonDepartmentDocument.getSeasonId()));
         departmentDocument.setCreatedBy(seasonDepartmentDocument.getLoginId());
         departmentDocument.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         departmentDocument.setModifiedBy(seasonDepartmentDocument.getLoginId());
         departmentDocument.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonDepartmentDocumentRepository.saveAndFlush(departmentDocument);
         returnObject = seasonDepartmentDocument;
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_ADD_SEASON_DOCUMENT.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_ADD_SEASON_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_ADD_SEASON_DOCUMENT) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   public SeasonProgramNote addSeasonProgramNote(SeasonProgramNote seasonProgramNote) {
      SeasonProgramNote returnObject = new SeasonProgramNote();
      if (seasonProgramNote == null || seasonProgramNote.getSeasonId() < 0 || seasonProgramNote.getNoteValue() == null) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_ADD_SEASON_PROGRRAM_NOTE.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_ADD_SEASON_PROGRRAM_NOTE)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_ADD_SEASON_PROGRRAM_NOTE));
         return returnObject;
      }
      try {
         com.ccighgo.db.entities.SeasonProgramNote programNote = new com.ccighgo.db.entities.SeasonProgramNote();
         programNote.setActive(CCIConstants.ACTIVE);
         programNote.setSeason(seasonRepository.findOne(seasonProgramNote.getSeasonId()));
         programNote.setDepartmentProgram(departmentProgramRepository.findOne(seasonProgramNote.getDepartmentProgramId()));
         programNote.setProgramNote(seasonProgramNote.getNoteValue());
         programNote.setCreatedBy(seasonProgramNote.getLoginId());
         programNote.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         programNote.setModifiedBy(seasonProgramNote.getLoginId());
         programNote.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonProgramNotesRepository.saveAndFlush(programNote);
         returnObject = seasonProgramNote;
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_ADD_SEASON_PROGRRAM_NOTE.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_ADD_SEASON_PROGRRAM_NOTE)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_ADD_SEASON_PROGRRAM_NOTE) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   public SeasonProgramDocument addSeasonProgramDoc(SeasonProgramDocument seasonProgramDocument) {
      SeasonProgramDocument returnObject = new SeasonProgramDocument();
      if (seasonProgramDocument == null || seasonProgramDocument.getSeasonId() < 0) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_ADD_SEASON_PROGRAM_DOCUMENT.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_ADD_SEASON_PROGRAM_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_ADD_SEASON_PROGRAM_DOCUMENT));
         return returnObject;
      }
      try {
         com.ccighgo.db.entities.SeasonProgramDocument programDocument = new com.ccighgo.db.entities.SeasonProgramDocument();
         DocumentInformation documentInformation = new DocumentInformation();
         documentInformation.setFileName(seasonProgramDocument.getFileName() != null ? seasonProgramDocument.getFileName() : null);
         documentInformation.setDocumentName(seasonProgramDocument.getDocName() != null ? seasonProgramDocument.getDocName() : null);
         documentInformation.setUrl(seasonProgramDocument.getDocUrl());
         documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(seasonProgramDocument.getDocType()));
         documentInformation.setCreatedBy(seasonProgramDocument.getLoginId());
         documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         documentInformation.setModifiedBy(seasonProgramDocument.getLoginId());
         documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         documentInformation = documentInformationRepository.saveAndFlush(documentInformation);

         programDocument.setActive(CCIConstants.ACTIVE);
         programDocument.setSeason(seasonRepository.findOne(seasonProgramDocument.getSeasonId()));
         programDocument.setDocumentInformation(documentInformation);
         programDocument.setDepartmentProgram(departmentProgramRepository.findOne(seasonProgramDocument.getDepartmentProgramId()));
         programDocument.setCreatedBy(seasonProgramDocument.getLoginId());
         programDocument.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         programDocument.setModifiedBy(seasonProgramDocument.getLoginId());
         programDocument.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonProgramDocumentRepository.saveAndFlush(programDocument);
         returnObject = seasonProgramDocument;
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         returnObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_ADD_SEASON_PROGRAM_DOCUMENT.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_ADD_SEASON_PROGRAM_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_ADD_SEASON_PROGRAM_DOCUMENT) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return returnObject;
   }

   public DocumentTypes getDocumentTypes() {
      DocumentTypes documentType = new DocumentTypes();
      try {
         List<com.ccighgo.db.entities.DocumentType> typeList = documentTypeRepository.findAll();
         if (typeList != null) {
            for (com.ccighgo.db.entities.DocumentType docType : typeList) {
               DocumentType dt = new DocumentType();
               dt.setDocumentTypeId(docType.getDocumentTypeId());
               dt.setDocumentTypeName(docType.getDocumentTypeName());
               documentType.getDocumentTypes().add(dt);
            }
            documentType.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            documentType.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, SeasonCodes.SUCCESS.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (CcighgoException e) {
         documentType.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, SeasonCodes.ERROR_GET_DOCYPE_LIST.getValue(),
               messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_DOCYPE_LIST)));
         LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ERROR_GET_DOCYPE_LIST) + CCIConstants.HYPHEN_SPACE + e.getMessage());
      }
      return documentType;
   }

}
