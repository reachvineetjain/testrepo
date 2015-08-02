package com.ccighgo.service.components.season;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.DocumentInformation;
import com.ccighgo.db.entities.LookupDepartment;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonCAPDetail;
import com.ccighgo.db.entities.SeasonDepartmentDocument;
import com.ccighgo.db.entities.SeasonDepartmentNote;
import com.ccighgo.db.entities.SeasonF1Detail;
import com.ccighgo.db.entities.SeasonGHTConfiguration;
import com.ccighgo.db.entities.SeasonHSADetail;
import com.ccighgo.db.entities.SeasonHSPAllocation;
import com.ccighgo.db.entities.SeasonHSPConfiguration;
import com.ccighgo.db.entities.SeasonIHPDetail;
import com.ccighgo.db.entities.SeasonJ1Detail;
import com.ccighgo.db.entities.SeasonLSDetail;
import com.ccighgo.db.entities.SeasonTADetail;
import com.ccighgo.db.entities.SeasonVADetail;
import com.ccighgo.db.entities.SeasonWADetail;
import com.ccighgo.db.entities.SeasonWPAllocation;
import com.ccighgo.db.entities.SeasonWPConfiguration;
import com.ccighgo.db.entities.SeasonWnTSpringDetail;
import com.ccighgo.db.entities.SeasonWnTSummerDetail;
import com.ccighgo.db.entities.SeasonWnTWinterDetail;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.CcighgoServiceException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.exception.InvalidServiceConfigurationException;
import com.ccighgo.jpa.repositories.DepartmentProgramOptionRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.DepartmentRepository;
import com.ccighgo.jpa.repositories.DocumentInformationRepository;
import com.ccighgo.jpa.repositories.DocumentTypeDocumentCategoryProcessRepository;
import com.ccighgo.jpa.repositories.DocumentTypeRepository;
import com.ccighgo.jpa.repositories.SeasonCAPDetailsRepository;
import com.ccighgo.jpa.repositories.SeasonDepartmentDocumentRepository;
import com.ccighgo.jpa.repositories.SeasonDepartmentNotesRepository;
import com.ccighgo.jpa.repositories.SeasonF1DetailsRepository;
import com.ccighgo.jpa.repositories.SeasonGHTConfigurationRepository;
import com.ccighgo.jpa.repositories.SeasonHSADetailsRepository;
import com.ccighgo.jpa.repositories.SeasonHSPAllocationRepository;
import com.ccighgo.jpa.repositories.SeasonHSPConfigurationRepsitory;
import com.ccighgo.jpa.repositories.SeasonIHPDetailRepository;
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
import com.ccighgo.service.components.errormessages.constants.UserManagementMessageConstants;
import com.ccighgo.service.transport.season.beans.cloneseason.CloneSeason;
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
import com.ccighgo.service.transport.usermanagement.beans.user.User;
import com.ccighgo.service.transport.utility.beans.documenttype.DocumentType;
import com.ccighgo.service.transport.utility.beans.documenttype.DocumentTypes;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.ValidationUtils;

/**
 * @author ravi
 *
 */
@Component
public class SeasonServiceInterfaceImpl implements SeasonServiceInterface {

   private static final Logger LOGGER = LoggerFactory.getLogger(SeasonServiceInterfaceImpl.class);
   @Autowired
   SeasonRepository seasonRepository;
   @Autowired
   SeasonServiceImplUtil seasonServiceImplUtil;
   @Autowired
   SeasonJ1DetailsRepository seasonJ1DetailsRepository;
   @Autowired
   SeasonStatusRepository seasonStatusRepository;
   @Autowired
   SeasonF1DetailsRepository seasonF1DetailsRepository;
   @Autowired
   SeasonHSADetailsRepository seasonHSADetailsRepository;
   @Autowired
   SeasonLSDetailsRepository seasonLSDetailsRepository;
   @Autowired
   SeasonTADetailsRepository seasonTADetailsRepository;
   @Autowired
   SeasonVADetailsRepository seasonVADetailsRepository;
   @Autowired
   SeasonWADetailsRepository seasonWADetailsRepository;
   @Autowired
   SeasonWTSummerRepository seasonWTSummerRepository;
   @Autowired
   SeasonWTSpringRepository seasonWTSpringRepository;
   @Autowired
   DepartmentRepository departmentRepository;
   @Autowired
   SeasonHSPConfigurationRepsitory hspConfigurationRepsitory;
   @Autowired
   SeasonHSPAllocationRepository seasonHSPAllocationRepository;
   @Autowired
   SeasonWPConfigurationRepository seasonWPConfigurationRepository;
   @Autowired
   SeasonWPAllocationRepository seasonWPAllocationRepository;
   @Autowired
   SeasonWTWinterRepository seasonWTWinterRepository;
   @Autowired
   SeasonCAPDetailsRepository seasonCAPDetailsRepository;
   @Autowired
   SeasonGHTConfigurationRepository seasonGHTConfigurationRepository;
   @Autowired
   SeasonCloningHelper seasonCloningHelper;
   @Autowired
   SeasonDepartmentNotesRepository seasonDepartmentNotesRepository;
   @Autowired
   DocumentTypeDocumentCategoryProcessRepository documentTypeDocumentCategoryProcessRepository;
   @Autowired
   DocumentInformationRepository documentInformationRepository;
   @Autowired
   SeasonDepartmentDocumentRepository seasonDepartmentDocumentRepository;
   @Autowired
   DepartmentProgramRepository departmentProgramRepository;
   @Autowired
   SeasonProgramNotesRepository seasonProgramNotesRepository;
   @Autowired
   SeasonProgramDocumentRepository seasonProgramDocumentRepository;
   @Autowired
   DepartmentProgramOptionRepository departmentProgramOptionRepository;
   @Autowired
   DocumentTypeRepository documentTypeRepository;
   @Autowired
   SeasonIHPProgramHelper ihpProgramHelper;
   @Autowired
   SeasonIHPDetailRepository seasonIHPDetailRepository;
   @Autowired
   CommonComponentUtils componentUtils;
   @Autowired
   MessageUtils messageUtil;
   

   SeasonServiceInterfaceImpl() {
   }

   @Override
   @Transactional(readOnly = true)
   public SeasonsList getAllSeasons() {
	   SeasonsList seasonsList =null;
	   try {
         List<Season> allseasons = seasonRepository.getAllSeasons();
         seasonsList = new SeasonsList();
         if (allseasons != null && !(allseasons.isEmpty())) {
            seasonsList.setRecordCount(allseasons.size());
            for (int i = 0; i < allseasons.size(); i++) {
               SeasonListObject seasonBean = new SeasonListObject();
               Season seasonEntity = allseasons.get(i);
               seasonServiceImplUtil.convertEntitySeasonToSeasonListObject(seasonBean, seasonEntity);
               seasonsList.getSeasons().add(seasonBean);
            }
         }
         seasonsList = setseasonsListStatus(seasonsList, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return seasonsList;
      } catch (Exception e) {
    	  seasonsList = setseasonsListStatus(seasonsList,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_ALL_SEASONS.getValue(), messageUtil.getMessage(SeasonMessageConstants.SEASON_GET_ALL_SEASONS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.SEASON_GET_ALL_SEASONS));
         return seasonsList;
      }
   }

   @Override
   @Transactional
   public SeasonBean createSeason(SeasonBean seasonBean) {
      try {
         int seasonId = -1;
         if (seasonBean.getSeasonName() != null) {
            Season season = seasonRepository.findBySeasonName(seasonBean.getSeasonName());
            if (season != null) {
               LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.SEASON_NAME_ALREADY_EXIT));
            } else {
               Season seasonEntity = new Season();
               seasonServiceImplUtil.convertSeasonBeanToSeasonEntity(seasonBean, seasonEntity, false);
               seasonEntity = seasonRepository.saveAndFlush(seasonEntity);
               seasonServiceImplUtil.createSeasonConfiguration(seasonBean, seasonEntity);
               seasonServiceImplUtil.createSeasonDepartmentNotes(seasonBean, seasonEntity);
               seasonServiceImplUtil.createSeasonPrograms(seasonEntity, seasonBean);
               seasonId = seasonEntity.getSeasonId();
            }
         }
         seasonBean = setSeasonBeanStatus(seasonBean, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return viewSeason(seasonId + CCIConstants.EMPTY_DATA);
      } catch (Exception e) {
    	  seasonBean = setSeasonBeanStatus(seasonBean,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_CREATE_SEASON.getValue(), messageUtil.getMessage(SeasonMessageConstants.CREATE_SEASON));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.CREATE_SEASON));
         return seasonBean;
      }

      /*
       * try { int seasonId = createSeasonLogic(seasonBean); if(seasonId==-1){ throw new
       * ValidationException(ErrorCode.DUPLICATE_SEASON_NAME,
       * "season with same name already exists, please select different name"); }else{ return viewSeason(seasonId +
       * CCIConstants.EMPTY_DATA); } } catch (Exception e) { ExceptionUtil.logException(e, LOGGER); } return null;
       */
   }

   @Transactional
   private int createSeasonLogic(SeasonBean seasonBean) {
      int seasonId = -1;
      try {
         if (seasonBean.getSeasonName() != null) {
            Season season = seasonRepository.findBySeasonName(seasonBean.getSeasonName());
            if (season != null) {
               LOGGER.error("season with same name already exists");
            } else {
               Season seasonEntity = new Season();
               seasonServiceImplUtil.convertSeasonBeanToSeasonEntity(seasonBean, seasonEntity, false);
               seasonEntity = seasonRepository.saveAndFlush(seasonEntity);
               seasonServiceImplUtil.createSeasonConfiguration(seasonBean, seasonEntity);
               seasonServiceImplUtil.createSeasonDepartmentNotes(seasonBean, seasonEntity);
               seasonServiceImplUtil.createSeasonPrograms(seasonEntity, seasonBean);
               seasonId = seasonEntity.getSeasonId();
            }
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return seasonId;
   }

   @Override
   @Transactional
   public String deleteSeason(String id) {
      try {
         Season seasonEntity = seasonRepository.findOne(Integer.parseInt(id));
         if (seasonEntity != null) {
            // seasonEntity.setActive(CCIConstants.INACTIVE);
            seasonRepository.saveAndFlush(seasonEntity);
            return "Season Deactivated";
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return "Error Deactivating Season";

   }

   @Override
   @Transactional(readOnly = true)
   public SeasonBean editSeason(String id) {
	   SeasonBean seasonBean = null;
      try {
    	  seasonBean=viewSeason(id);
    	  seasonBean = setSeasonBeanStatus(seasonBean, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return seasonBean;
      } catch (Exception e) {
    	  seasonBean = setSeasonBeanStatus(seasonBean,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_EDIT_SEASON.getValue(), messageUtil.getMessage(SeasonMessageConstants.EDIT_SEASON));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.EDIT_SEASON));
         return seasonBean;
      }
   }

   @Override
   @Transactional(readOnly = true)
   public SeasonBean viewSeason(String id) {
	   SeasonBean seasonBean = null;
      try {
         ValidationUtils.isValidSeasonId(id);
         Season seasonEntity = seasonRepository.findOne(Integer.parseInt(id));
         if (seasonEntity != null) {
            seasonBean = new SeasonBean();
            seasonServiceImplUtil.convertEntitySeasonToBeanSeason(seasonBean, seasonEntity);
         }
         seasonBean = setSeasonBeanStatus(seasonBean, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return seasonBean;
      } catch (Exception e) {
    	  seasonBean = setSeasonBeanStatus(seasonBean,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_VIEW_SEASON.getValue(), messageUtil.getMessage(SeasonMessageConstants.VIEW_SEASON));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.VIEW_SEASON));
         return seasonBean;
      }
   }

   @Override
   public SeasonBean updateSeason(SeasonBean seasonBean) {
      try {
         int seasonId = updateSeasonLogic(seasonBean);
         seasonBean = setSeasonBeanStatus(seasonBean, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return viewSeason(seasonId + CCIConstants.EMPTY_DATA);
      } catch (Exception e) {
    	  seasonBean = setSeasonBeanStatus(seasonBean,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_SEASON.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_SEASON));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_SEASON));
         return seasonBean;
      }
   }

   @Transactional
   private int updateSeasonLogic(SeasonBean seasonBean) {
      try {
         Season seasonEntity = new Season();
         seasonServiceImplUtil.convertSeasonBeanToSeasonEntity(seasonBean, seasonEntity, true);
         seasonRepository.saveAndFlush(seasonEntity);
         seasonServiceImplUtil.updateSeasonConfiguration(seasonBean, seasonEntity);
         seasonServiceImplUtil.updateSeasonDepartmentNotes(seasonBean, seasonEntity);
         seasonServiceImplUtil.updateSeasonDocuments(seasonBean, seasonEntity);
         return seasonEntity.getSeasonId();
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return -1;
      }
   }

   @Transactional(readOnly = true)
   public SeasonPrograms getSeasonPrograms(String seasonId) {
      SeasonPrograms seasonPrograms = null;
      try {
         Season season = seasonRepository.findOne(Integer.valueOf(seasonId));
         if (season != null) {
            List<SeasonProgram> seasonProgramsList = new ArrayList<SeasonProgram>();
            seasonPrograms = new SeasonPrograms();
            LookupDepartment dept = season.getLookupDepartment();
            if (dept != null) {
               try {
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
                     // TODO implement when STP tables are available
                  }
               } catch (Exception e) {
            	   seasonPrograms = setSeasonProgramsStatus(seasonPrograms,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_SEASON_PROGRAMS.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_DEPT_HIGH_SCHOOL_PROGRAMS));
                   LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_DEPT_HIGH_SCHOOL_PROGRAMS));
               }
               try {
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
               } catch (Exception e) {
            	   seasonPrograms = setSeasonProgramsStatus(seasonPrograms,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_SEASON_PROGRAMS.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_DEPT_WORK_PROGRAMS));
                   LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_DEPT_WORK_PROGRAMS));
               }
               try {
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
               } catch (Exception e) {
            	   seasonPrograms = setSeasonProgramsStatus(seasonPrograms,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_SEASON_PROGRAMS.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_DEPT_GREEN_HEART_TRAVEL_PROGRAM));
                   LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_DEPT_GREEN_HEART_TRAVEL_PROGRAM));
               }
            }
            seasonPrograms.getSeasonPrograms().addAll(seasonProgramsList);
         }
      } catch (CcighgoException e) {
    	  seasonPrograms = setSeasonProgramsStatus(seasonPrograms,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_SEASON_PROGRAMS.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_PROGRAM));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_PROGRAM));
      }
      seasonPrograms = setSeasonProgramsStatus(seasonPrograms, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      return seasonPrograms;
   }

   @Transactional(readOnly = true)
   public SeasonStatuses getSeasonStatus() {
      SeasonStatuses seasonStatuses = null;
      try {
         LOGGER.info("SeasonStatus: fetch");
         seasonStatuses = seasonServiceImplUtil.getSeasonStatus();
         seasonStatuses = setSeasonStatusesStatus(seasonStatuses, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      } catch (CcighgoException e) {
    	  seasonStatuses = setSeasonStatusesStatus(seasonStatuses,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_SEASON_STATUS.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_STATUS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_STATUS));
      }
     
      return seasonStatuses;
   }

   @Transactional(readOnly = true)
   public SeasonHspJ1HSDetails getHSPJ1HSSeasonDetails(String seasonProgramId) {
      SeasonHspJ1HSDetails seasonHspJ1HSDetails = null;
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonJ1Detail != null) {
            seasonHspJ1HSDetails = new SeasonHspJ1HSDetails();
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
            seasonHspJ1HSDetails = setSeasonHspJ1HSDetailsStatus(seasonHspJ1HSDetails, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  seasonHspJ1HSDetails = setSeasonHspJ1HSDetailsStatus(seasonHspJ1HSDetails,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_HSP_J1_HS_SEASON_DETAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_HSP_J1_HS_SEASON_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_HSP_J1_HS_SEASON_DETAILS));
      }
      
      return seasonHspJ1HSDetails;
   }

   @Transactional(readOnly = true)
   public J1HSBasicDetail getHSPJ1HSSeasonNameAndStatus(String seasonProgramId) {
      J1HSBasicDetail j1hsBasicDetail = null;
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonJ1Detail != null) {
            j1hsBasicDetail = seasonServiceImplUtil.getJ1HSBasicDetail(seasonJ1Detail);
            j1hsBasicDetail = setJ1HSBasicDetailStatus(j1hsBasicDetail, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  j1hsBasicDetail = setJ1HSBasicDetailStatus(j1hsBasicDetail,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_J1_HS_BASIC_DETAIL.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_J1_HS_BASIC_DETAIL));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_J1_HS_BASIC_DETAIL));
      }
      return j1hsBasicDetail;
   }

   @Transactional(readOnly = true)
   public J1HSJanStart getHSPJ1HSSeasonJanStartDetails(String seasonProgramId) {
      J1HSJanStart j1hsJanStart = null;
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonJ1Detail != null) {
            j1hsJanStart = seasonServiceImplUtil.getJ1HSJanStart(seasonJ1Detail);
            j1hsJanStart = setJ1HSJanStartStatus(j1hsJanStart, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  j1hsJanStart = setJ1HSJanStartStatus(j1hsJanStart,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_J1_HS_JAN_START.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_J1_HS_JAN_START_DETAIL));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_J1_HS_JAN_START_DETAIL));
      }
      return j1hsJanStart;
   }

   @Transactional(readOnly = true)
   public J1HSAugStart getHSPJ1HSSeasonAugStartDetails(String seasonProgramId) {
      J1HSAugStart j1hsAugStart = null;
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonJ1Detail != null) {
            j1hsAugStart = seasonServiceImplUtil.getJ1HSAugStart(seasonJ1Detail);
            j1hsAugStart = setJ1HSAugStartStatus(j1hsAugStart, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  j1hsAugStart = setJ1HSAugStartStatus(j1hsAugStart,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_J1_HS_AUG_START.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_J1_HS_JAN_START_DETAIL));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_J1_HS_JAN_START_DETAIL));
      }
      return j1hsAugStart;
   }

   @Transactional(readOnly = true)
   public J1HSFieldSettings getHSPJ1HSSeasonFieldSettings(String seasonProgramId) {
      J1HSFieldSettings j1hsFieldSettings = null;
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonJ1Detail != null) {
            j1hsFieldSettings = seasonServiceImplUtil.getJ1HSFieldSettings(seasonJ1Detail);
            j1hsFieldSettings = setJ1HSFieldSettingsStatus(j1hsFieldSettings, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  j1hsFieldSettings = setJ1HSFieldSettingsStatus(j1hsFieldSettings,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_HSP_J1_HS_SEASON_FIELD_SETTINGS.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_J1_HS_SEASON_FIELD_SETTINGS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_J1_HS_SEASON_FIELD_SETTINGS));
      }
      
      return j1hsFieldSettings;
   }

   @Transactional(readOnly = true)
   public J1HSProgramAllocations getHSPJ1HSSeasonProgramAllocation(String seasonProgramId) {
      J1HSProgramAllocations j1hsProgramAllocations = null;
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
            	   j1hsProgramAllocations = setJ1HSProgramAllocationsStatus(j1hsProgramAllocations,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_HSP_J1_HS_PROGRAM_ALLOCATION.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_J1_HS_SEASON_FIELD_SETTINGS));
                   LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_J1_HS_SEASON_FIELD_SETTINGS));
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
               j1hsProgramAllocations = setJ1HSProgramAllocationsStatus(j1hsProgramAllocations, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
            }
         }
      } catch (CcighgoException e) {
    	  
    	  j1hsProgramAllocations = setJ1HSProgramAllocationsStatus(j1hsProgramAllocations,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_HSP_J1_HS_PROGRAM_ALLOCATION.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_HSP_J1_HS_PROGRAM_ALLOCATION));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_HSP_J1_HS_PROGRAM_ALLOCATION));
      }
      
      return j1hsProgramAllocations;
   }

   @Override
   @Transactional
   public SeasonHspJ1HSDetails updateHSPJ1HSSeasonDetails(SeasonHspJ1HSDetails seasonHspJ1HSDetails) {
      SeasonHspJ1HSDetails returnObject = null;
      if (seasonHspJ1HSDetails == null) {
         return returnObject;
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(seasonHspJ1HSDetails.getSeasonProgramId()));
         if (seasonJ1Detail != null) {
            try {
               if (seasonHspJ1HSDetails.getJ1HsBasicDetail() != null) {
                  seasonServiceImplUtil.updateJ1BasicDetails(seasonHspJ1HSDetails.getJ1HsBasicDetail(), seasonJ1Detail);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }
            try {
               if (seasonHspJ1HSDetails.getJ1HsJanStart() != null) {
                  seasonServiceImplUtil.updateJ1JanStartDetails(seasonHspJ1HSDetails.getJ1HsJanStart(), seasonJ1Detail);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }
            try {
               if (seasonHspJ1HSDetails.getJ1HsAugStart() != null) {
                  seasonServiceImplUtil.updateJ1AugStartDetails(seasonHspJ1HSDetails.getJ1HsAugStart(), seasonJ1Detail);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }
            try {
               if (seasonHspJ1HSDetails.getJ1HsFieldSettings() != null) {
                  seasonServiceImplUtil.updateJ1FSSettings(seasonHspJ1HSDetails.getJ1HsFieldSettings(), seasonJ1Detail);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }
            try {
               if (seasonHspJ1HSDetails.getJ1HsProgramAllocations() != null) {
                  updateHSPJ1HSSeasonProgramAllocation(seasonHspJ1HSDetails.getJ1HsProgramAllocations());
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }
            try {
               if (seasonHspJ1HSDetails.getJ1HsNotes() != null) {
                  seasonServiceImplUtil.updateJ1Notes(seasonHspJ1HSDetails, seasonJ1Detail.getSeason());
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }
            try {
               if (seasonHspJ1HSDetails.getJ1HsDocuments() != null) {
                  seasonServiceImplUtil.updateJ1HSDocs(seasonHspJ1HSDetails, seasonJ1Detail.getSeason());
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }

            seasonJ1Detail = seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
            returnObject = getHSPJ1HSSeasonDetails(String.valueOf(seasonJ1Detail.getSeasonJ1DetailsId()));
            returnObject = setSeasonHspJ1HSDetailsStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  returnObject = setSeasonHspJ1HSDetailsStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_HSP_J1_HS_SEASON_DETAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_J1_HS_SEASON_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_J1_HS_SEASON_DETAILS));
      }
      return returnObject;
   }

   @Override
   @Transactional
   public J1HSBasicDetail updateHSPJ1HSSeasonNameAndStatus(J1HSBasicDetail j1hsBasicDetail) {
      J1HSBasicDetail returnObject = null;
      try {
         if (j1hsBasicDetail == null || j1hsBasicDetail.getSeasonId() == 0) {
        	 returnObject = setJ1HSBasicDetailStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_ID.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_J1_HS_SEASON_NAME_AND_STATUS));
             LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_J1_HS_SEASON_NAME_AND_STATUS));
            return returnObject;
         }
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(j1hsBasicDetail.getSeasonProgramId()));
         if (seasonJ1Detail != null) {
            seasonServiceImplUtil.updateJ1BasicDetails(j1hsBasicDetail, seasonJ1Detail);
            seasonJ1Detail = seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
            returnObject = setJ1HSBasicDetailStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
            returnObject = j1hsBasicDetail;
         }
      } catch (CcighgoException e) {
    	  returnObject = setJ1HSBasicDetailStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_HSP_J1_HS_SEASON_NAME_AND_STATUS.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_J1_HS_SEASON_NAME_AND_STATUS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_J1_HS_SEASON_NAME_AND_STATUS));
      }
      return returnObject;
   }

   @Override
   @Transactional
   public J1HSJanStart updateHSPJ1HSSeasonJanStartDetails(J1HSJanStart j1hsJanStart) {
      J1HSJanStart returnObject = null;
      try {
         if (j1hsJanStart == null) {
        	 returnObject = setJ1HSJanStartStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_UPDATE_J1_HS_JAN_START.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_J1_HS_JAN_START_DETAIL));
             LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_J1_HS_JAN_START_DETAIL));
         }
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(j1hsJanStart.getSeasonProgramId()));
         if (seasonJ1Detail != null) {
            seasonServiceImplUtil.updateJ1JanStartDetails(j1hsJanStart, seasonJ1Detail);
            seasonJ1Detail = seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
            returnObject = setJ1HSJanStartStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
            returnObject = j1hsJanStart;
         }
      } catch (CcighgoException e) {
    	  returnObject = setJ1HSJanStartStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_UPDATE_J1_HS_JAN_START.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_J1_HS_JAN_START_DETAIL));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_J1_HS_JAN_START_DETAIL));
      }
      return returnObject;
   }

   @Override
   @Transactional
   public J1HSAugStart updateHSPJ1HSSeasonAugStartDetails(J1HSAugStart j1hsAugStart) {
      J1HSAugStart returnObject = null;
      try {
         if (j1hsAugStart == null) {
            // throw exception
         }
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(j1hsAugStart.getSeasonProgramId()));
         if (seasonJ1Detail != null) {
            seasonServiceImplUtil.updateJ1AugStartDetails(j1hsAugStart, seasonJ1Detail);
            seasonJ1Detail = seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
            returnObject = j1hsAugStart;
            returnObject = setJ1HSAugStartStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  returnObject = setJ1HSAugStartStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_UPDATE_J1_HS_JAN_START.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_J1_HS_JAN_START_DETAIL));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_J1_HS_JAN_START_DETAIL));
      }
      return returnObject;
   }

   @Override
   @Transactional
   public J1HSFieldSettings updateHSPJ1HSSeasonFieldSettings(J1HSFieldSettings j1hsFieldSettings) {
      J1HSFieldSettings returnObject = null;
      try {
         if (j1hsFieldSettings == null) {
        	 returnObject = setJ1HSFieldSettingsStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_J1_HS_DETAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.INVALID__J1_HS_FIELD_SETTINGS));
             LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.INVALID__J1_HS_FIELD_SETTINGS));
         }
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(j1hsFieldSettings.getSeasonProgramId()));
         if (seasonJ1Detail != null) {
            seasonServiceImplUtil.updateJ1FSSettings(j1hsFieldSettings, seasonJ1Detail);
            seasonJ1Detail = seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
            returnObject = j1hsFieldSettings;
            returnObject = setJ1HSFieldSettingsStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  returnObject = setJ1HSFieldSettingsStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_UPDATE_HSP_J1_HS_SEASON_FIELD_SETTINGS.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_J1_HS_SEASON_FIELD_SETTINGS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_J1_HS_SEASON_FIELD_SETTINGS));
      }
      return returnObject;
   }

   @Override
   @Transactional
   public J1HSProgramAllocations updateHSPJ1HSSeasonProgramAllocation(J1HSProgramAllocations j1hsProgramAllocations) {
      J1HSProgramAllocations returnObject = null;
      try {
         if (j1hsProgramAllocations != null && j1hsProgramAllocations.getSeasonId() > 0 && j1hsProgramAllocations.getSeasonProgramId() > 0) {
            List<SeasonHSPAllocation> hspAllocations = seasonHSPAllocationRepository.findSeasonHSPAllocationBySeasonId(j1hsProgramAllocations.getSeasonId());
            List<SeasonHSPAllocation> updatedList = new ArrayList<SeasonHSPAllocation>();
            for (SeasonHSPAllocation hspAllocation : hspAllocations) {
               if (hspAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_J1_HS_ID) {
                  try {
                     if (hspAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_J1)) {
                        SeasonHSPAllocation allocation = new SeasonHSPAllocation();
                        allocation = hspAllocation;
                        allocation.setMaxGuaranteedPax(j1hsProgramAllocations.getAugStartGuarnteedParticipants());
                        allocation.setMaxUnguaranteedPax(j1hsProgramAllocations.getAugStartUnGuarnteedParticipants());
                        updatedList.add(allocation);
                     }
                  } catch (Exception e) {
                     ExceptionUtil.logException(e, LOGGER);
                  }
                  try {
                     if (hspAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_J1)) {
                        SeasonHSPAllocation allocation = new SeasonHSPAllocation();
                        allocation = hspAllocation;
                        allocation.setMaxGuaranteedPax(j1hsProgramAllocations.getJanStartGuarnteedParticipants());
                        allocation.setMaxUnguaranteedPax(j1hsProgramAllocations.getJanStartUnGuarnteedParticipants());
                        updatedList.add(allocation);
                     }
                  } catch (Exception e) {
                     ExceptionUtil.logException(e, LOGGER);
                  }

               }
            }
            seasonHSPAllocationRepository.save(updatedList);
            returnObject = getHSPJ1HSSeasonProgramAllocation(String.valueOf(j1hsProgramAllocations.getSeasonProgramId()));
            returnObject = setJ1HSProgramAllocationsStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  returnObject = setJ1HSProgramAllocationsStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_UPDATE_HSP_J1_HS_PROGRAM_ALLOCATION.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_J1_HS_PROGRAM_ALLOCATION));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_J1_HS_PROGRAM_ALLOCATION));
      }
      return returnObject;
   }

   @Transactional(readOnly = true)
   public HSPF1ProgramAllocations getHSPF1ProgramAllocations(String seasonProgramId) {
      HSPF1ProgramAllocations hspf1ProgramAllocations = null;
      try {
         SeasonF1Detail allF1Detail = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (allF1Detail != null) {
            List<SeasonHSPAllocation> hspAllocations = seasonHSPAllocationRepository.findSeasonHSPAllocationBySeasonId(allF1Detail.getSeason().getSeasonId());
            if (hspAllocations != null) {
               hspf1ProgramAllocations = new HSPF1ProgramAllocations();
               // TODO update other values once participants and partners modules are integrated
               int totalMaxParticipants = 0;
               int januaryStartMaximumParticipants = 0;
               int augustStartMaximumParticipants = 0;
               hspf1ProgramAllocations.setSeasonId(allF1Detail.getSeason().getSeasonId());
               hspf1ProgramAllocations.setSeasonProgramId(Integer.parseInt(seasonProgramId));
               try {
                  for (SeasonHSPAllocation seasonWPAllocation : hspAllocations) {
                     if (seasonWPAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_F1_ID) {
                        if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_F1)) {
                           augustStartMaximumParticipants = seasonWPAllocation.getMaxGuaranteedPax() > 0 ? seasonWPAllocation.getMaxGuaranteedPax() : 0;
                           totalMaxParticipants += augustStartMaximumParticipants > 0 ? augustStartMaximumParticipants : 0;

                        } else if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_F1)) {
                           augustStartMaximumParticipants = seasonWPAllocation.getMaxGuaranteedPax() > 0 ? seasonWPAllocation.getMaxGuaranteedPax() : 0;
                           totalMaxParticipants += augustStartMaximumParticipants > 0 ? augustStartMaximumParticipants : 0;
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
               hspf1ProgramAllocations = setHSPF1ProgramAllocationsStatus(hspf1ProgramAllocations, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
            }
         }
      } catch (CcighgoException e) {
    	  hspf1ProgramAllocations = setHSPF1ProgramAllocationsStatus(hspf1ProgramAllocations,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_HSP_F1_PROGRAM_ALLOCATION.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_PROGRAM_ALLOCATION));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_PROGRAM_ALLOCATION));
      }
      return hspf1ProgramAllocations;
   }

   public HSPF1FieldSettings getHSPF1FieldSettings(String seasonProgramId) {
	   HSPF1FieldSettings hSPF1FieldSettings=null;
      try {
    	  hSPF1FieldSettings=new HSPF1FieldSettings();
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         hSPF1FieldSettings=seasonServiceImplUtil.getHSPF1FieldSettings(allF1Details);
         hSPF1FieldSettings = setHSPF1FieldSettingsStatus(hSPF1FieldSettings, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return hSPF1FieldSettings;
      } catch (CcighgoException e) {
    	  hSPF1FieldSettings = setHSPF1FieldSettingsStatus(hSPF1FieldSettings,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_HSP_F1_FIELD_SETTINGS.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_FIELD_SETTINGS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_FIELD_SETTINGS));
         return hSPF1FieldSettings;
      }
   }

   

public HSPF1AugustStart1StSemesterDetails getHSPF1AugustStart1StSemesterDetails(String seasonProgramId) {
	HSPF1AugustStart1StSemesterDetails hSPF1AugustStart1StSemesterDetails=null;
      try {
    	  hSPF1AugustStart1StSemesterDetails=new HSPF1AugustStart1StSemesterDetails();
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         hSPF1AugustStart1StSemesterDetails=seasonServiceImplUtil.getHSPF1AugustStart1StSemesterDetails(allF1Details);
         hSPF1AugustStart1StSemesterDetails = setHSPF1AugustStart1StSemesterDetailsStatus(hSPF1AugustStart1StSemesterDetails, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return hSPF1AugustStart1StSemesterDetails;
      } catch (CcighgoException e) {
    	  hSPF1AugustStart1StSemesterDetails = setHSPF1AugustStart1StSemesterDetailsStatus(hSPF1AugustStart1StSemesterDetails,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_HSP_F1_AUGUST_START_1ST_SEMISTER.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_AUGUST_1ST_SEMISTER));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_AUGUST_1ST_SEMISTER));
         return hSPF1AugustStart1StSemesterDetails;
      }
   }

 

public HSPF1AugustStartFullYearDetails getHSPF1AugustStartFullYearDetails(String seasonProgramId) {
	HSPF1AugustStartFullYearDetails hSPF1AugustStartFullYearDetails=null;
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         hSPF1AugustStartFullYearDetails=seasonServiceImplUtil.getHSPF1AugustStartFullYearDetails(allF1Details);
         hSPF1AugustStartFullYearDetails = setHSPF1AugustStartFullYearDetailsStatus(hSPF1AugustStartFullYearDetails, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return hSPF1AugustStartFullYearDetails;
      } catch (CcighgoException e) {
    	  hSPF1AugustStartFullYearDetails = setHSPF1AugustStartFullYearDetailsStatus(hSPF1AugustStartFullYearDetails,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_HSP_F1_AUGUST_FULL_YEAR_DETAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_AUGUST_FULL_YEAR_DETAIL));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_AUGUST_FULL_YEAR_DETAIL));
         return hSPF1AugustStartFullYearDetails;
      }
   }

public HSPF1JanuaryStart2NdSemesterDetails getHSPF1JanuaryStart2NdSemesterDetails(String seasonProgramId) {
	HSPF1JanuaryStart2NdSemesterDetails hSPF1JanuaryStart2NdSemesterDetails=null;
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         hSPF1JanuaryStart2NdSemesterDetails=seasonServiceImplUtil.getHSPF1JanuaryStart2NdSemesterDetails(allF1Details);
         hSPF1JanuaryStart2NdSemesterDetails = setHSPF1JanuaryStart2NdSemesterDetailsStatus(hSPF1JanuaryStart2NdSemesterDetails, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return hSPF1JanuaryStart2NdSemesterDetails;
      } catch (CcighgoException e) {
    	  hSPF1JanuaryStart2NdSemesterDetails = setHSPF1JanuaryStart2NdSemesterDetailsStatus(hSPF1JanuaryStart2NdSemesterDetails,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_HSP_F1_JAN_2ND_SEM_DETAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_JAN_SECOND_SEM_DETAIL));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_JAN_SECOND_SEM_DETAIL));
         return hSPF1JanuaryStart2NdSemesterDetails;
      }
   }


public HSPF1JanuaryStartFullYearDetail getHSPF1JanuaryStartFullYearDetails(String seasonProgramId) {
	HSPF1JanuaryStartFullYearDetail hSPF1JanuaryStartFullYearDetail=null;
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         hSPF1JanuaryStartFullYearDetail = new HSPF1JanuaryStartFullYearDetail();
         hSPF1JanuaryStartFullYearDetail = seasonServiceImplUtil.getHSPF1JanuaryStartFullYearDetails(allF1Details);
         hSPF1JanuaryStartFullYearDetail = setHSPF1JanuaryStartFullYearDetailStatus(hSPF1JanuaryStartFullYearDetail, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return hSPF1JanuaryStartFullYearDetail;
      } catch (CcighgoException e) {
    	  hSPF1JanuaryStartFullYearDetail = setHSPF1JanuaryStartFullYearDetailStatus(hSPF1JanuaryStartFullYearDetail,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_HSP_F1.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_JAN_FULL_YEAR_START_DETAIL));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_JAN_FULL_YEAR_START_DETAIL));
         return hSPF1JanuaryStartFullYearDetail;
      }
   }


public HSPF1BasicDetails getHSPF1NameAndStatus(String seasonProgramId) {
	   HSPF1BasicDetails hSPF1BasicDetails=null;
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         hSPF1BasicDetails=seasonServiceImplUtil.getHSPF1NameAndStatus(allF1Details);
         hSPF1BasicDetails = setHSPF1BasicDetailsStatus(hSPF1BasicDetails, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return hSPF1BasicDetails;
      } catch (CcighgoException e) {
    	  hSPF1BasicDetails = setHSPF1BasicDetailsStatus(hSPF1BasicDetails,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_HSP_F1.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_JAN_FULL_YEAR_START_DETAIL));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_JAN_FULL_YEAR_START_DETAIL));
        
         return hSPF1BasicDetails;
      }
   }


public HSPF1Accounting getHSPF1Accounting(String seasonProgramId) {
	   HSPF1Accounting hSPF1Accounting = null;
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         hSPF1Accounting =new HSPF1Accounting(); 
         hSPF1Accounting = seasonServiceImplUtil.getHSPF1Accounting(allF1Details);
         hSPF1Accounting = setHSPF1AccountingStatus(hSPF1Accounting, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return hSPF1Accounting;
      } catch (CcighgoException e) {
    	  hSPF1Accounting = setHSPF1AccountingStatus(hSPF1Accounting,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_HSP_F1.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_DETAILS));
         return hSPF1Accounting;
      }
   }


public SeasonHSPF1Details getSeasonHSPF1Details(String seasonProgramId) {
	   SeasonHSPF1Details seasonHSPF1Details=null;
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
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
         seasonHSPF1Details.setProgramAllocations(getHSPF1ProgramAllocations(allF1Details.getSeason().getSeasonId() + ""));
         seasonHSPF1Details.getNotes().addAll(seasonServiceImplUtil.getHSPF1Notes(allF1Details));
         seasonHSPF1Details.getDocuments().addAll(seasonServiceImplUtil.getHSPF1Documents(allF1Details, Integer.parseInt(seasonProgramId)));
         seasonHSPF1Details = setseasonHSPF1DetailsStatus(seasonHSPF1Details, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return seasonHSPF1Details;
      } catch (CcighgoException e) {
    	  seasonHSPF1Details = setseasonHSPF1DetailsStatus(seasonHSPF1Details,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_GET_HSP_F1_SEASON_DETAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_SEASON_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_HSP_F1_SEASON_DETAILS));
         return seasonHSPF1Details;
      }
   }


@Transactional
   public SeasonHSPF1Details updateF1Details(SeasonHSPF1Details seasonHSPF1Details) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(seasonHSPF1Details.getSeasonProgramId());
         if (allF1Details != null) {
            SeasonHSPF1Details updatedSeasonHSPF1Details = seasonServiceImplUtil.updateF1Details(allF1Details, seasonHSPF1Details);
            seasonHSPF1Details = seasonServiceImplUtil.updateF1Details(allF1Details, seasonHSPF1Details);
            seasonHSPF1Details = setseasonHSPF1DetailsStatus(seasonHSPF1Details, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }

         return seasonHSPF1Details;
      } catch (CcighgoException e) {
    	  seasonHSPF1Details = setseasonHSPF1DetailsStatus(seasonHSPF1Details,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.UPDATE_F1_DETAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_SEASON_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_SEASON_DETAILS));
         return seasonHSPF1Details;
      }
   }

   @Transactional
   public HSPF1BasicDetails updateHSPF1NameAndStatus(HSPF1BasicDetails hspf1BasicDetails) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1BasicDetails.getSeasonProgramId());
         if (allF1Details != null) {
            hspf1BasicDetails = seasonServiceImplUtil.updateHSPF1NameAndStatus(allF1Details, hspf1BasicDetails);
            hspf1BasicDetails = setHSPF1BasicDetailsStatus(hspf1BasicDetails, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
         return hspf1BasicDetails;
      } catch (CcighgoException e) {
    	  hspf1BasicDetails = setHSPF1BasicDetailsStatus(hspf1BasicDetails,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_HSP_F1_DETAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_DETAILS));
         return hspf1BasicDetails;
      }
   }

   @Transactional
   public HSPF1Accounting updateF1Accounting(HSPF1Accounting hspf1Accounting) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1Accounting.getSeasonProgramId());
         if (allF1Details != null) {
            hspf1Accounting = seasonServiceImplUtil.updateF1Accounting(allF1Details, hspf1Accounting);
            hspf1Accounting = setHSPF1AccountingStatus(hspf1Accounting, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
         return hspf1Accounting;
      } catch (CcighgoException e) {
    	  hspf1Accounting = setHSPF1AccountingStatus(hspf1Accounting,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.UPDATE_F1.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_ACCOUNT_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_DETAILS));
         return hspf1Accounting;
      }
   }

   @Transactional
   public HSPF1JanuaryStart2NdSemesterDetails updateF1JanStart2NdSemesterDetails(HSPF1JanuaryStart2NdSemesterDetails hspf1JanuaryStart2NdSemesterDetails) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1JanuaryStart2NdSemesterDetails.getSeasonProgramId());
         if (allF1Details != null) {
            hspf1JanuaryStart2NdSemesterDetails = seasonServiceImplUtil.updateF1JanStart2NdSemesterDetails(allF1Details, hspf1JanuaryStart2NdSemesterDetails);
            hspf1JanuaryStart2NdSemesterDetails = setHSPF1JanuaryStart2NdSemesterDetailsStatus(hspf1JanuaryStart2NdSemesterDetails, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
         return hspf1JanuaryStart2NdSemesterDetails;
      } catch (CcighgoException e) {
    	  hspf1JanuaryStart2NdSemesterDetails = setHSPF1JanuaryStart2NdSemesterDetailsStatus(hspf1JanuaryStart2NdSemesterDetails,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.UPDATE_F1.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_JAN_SECOND_SEM_DETAIL));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_JAN_SECOND_SEM_DETAIL));
         return hspf1JanuaryStart2NdSemesterDetails;
      }
   }

   @Transactional
   public HSPF1JanuaryStartFullYearDetail updateF1JanStartFullYearDetails(HSPF1JanuaryStartFullYearDetail hspf1JanuaryStartFullYearDetail) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1JanuaryStartFullYearDetail.getSeasonProgramId());
         if (allF1Details != null) {
            hspf1JanuaryStartFullYearDetail = seasonServiceImplUtil.updateF1JanStartFullYearDetails(allF1Details, hspf1JanuaryStartFullYearDetail);
            hspf1JanuaryStartFullYearDetail = setHSPF1JanuaryStartFullYearDetailStatus(hspf1JanuaryStartFullYearDetail, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
         return hspf1JanuaryStartFullYearDetail;
      } catch (CcighgoException e) {
    	  hspf1JanuaryStartFullYearDetail = setHSPF1JanuaryStartFullYearDetailStatus(hspf1JanuaryStartFullYearDetail,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.UPDATE_F1.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_JAN_FULL_YEAR_START_DETAIL));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_JAN_FULL_YEAR_START_DETAIL));
         return hspf1JanuaryStartFullYearDetail;
      }
   }

   @Transactional
   public HSPF1AugustStart1StSemesterDetails updateF1AugStart1StSemesterDetails(HSPF1AugustStart1StSemesterDetails hspf1AugustStart1StSemesterDetails) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1AugustStart1StSemesterDetails.getSeasonProgramId());
         if (allF1Details != null) {
            hspf1AugustStart1StSemesterDetails = seasonServiceImplUtil.updateF1AugStart1StSemesterDetails(allF1Details, hspf1AugustStart1StSemesterDetails);
            hspf1AugustStart1StSemesterDetails = setHSPF1AugustStart1StSemesterDetailsStatus(hspf1AugustStart1StSemesterDetails, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
         return hspf1AugustStart1StSemesterDetails;
      } catch (CcighgoException e) {
    	  hspf1AugustStart1StSemesterDetails = setHSPF1AugustStart1StSemesterDetailsStatus(hspf1AugustStart1StSemesterDetails,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.UPDATE_F1.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_AUGUST_1ST_SEMISTER));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_AUGUST_1ST_SEMISTER));
         return hspf1AugustStart1StSemesterDetails;
      }
   }

   @Transactional
   public HSPF1AugustStartFullYearDetails updateF1AugStartFullYearDetails(HSPF1AugustStartFullYearDetails hspf1AugustStartFullYearDetails) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1AugustStartFullYearDetails.getSeasonProgramId());
         if (allF1Details != null) {
            hspf1AugustStartFullYearDetails = seasonServiceImplUtil.updateF1AugStartFullYearDetails(allF1Details, hspf1AugustStartFullYearDetails);
            hspf1AugustStartFullYearDetails = setHSPF1AugustStartFullYearDetailsStatus(hspf1AugustStartFullYearDetails, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
         return hspf1AugustStartFullYearDetails;
      } catch (CcighgoException e) {
    	  hspf1AugustStartFullYearDetails = setHSPF1AugustStartFullYearDetailsStatus(hspf1AugustStartFullYearDetails,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.UPDATE_F1.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_AUGUST_FULL_YEAR_DETAIL));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_AUGUST_FULL_YEAR_DETAIL));
         return hspf1AugustStartFullYearDetails;
      }
   }

   @Transactional
   public HSPF1FieldSettings updateF1FieldSettings(HSPF1FieldSettings hspf1FieldSettings) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1FieldSettings.getSeasonProgramId());
         if (allF1Details != null) {
            hspf1FieldSettings = seasonServiceImplUtil.updateF1FieldSettings(allF1Details, hspf1FieldSettings);
            hspf1FieldSettings = setHSPF1FieldSettingsStatus(hspf1FieldSettings, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
         return hspf1FieldSettings;
      } catch (CcighgoException e) {
    	  hspf1FieldSettings = setHSPF1FieldSettingsStatus(hspf1FieldSettings,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.UPDATE_F1.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_FIELD_SETTINGS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_FIELD_SETTINGS));
         return hspf1FieldSettings;
      }
   }

   @Transactional
   public HSPF1ProgramAllocations updateF1ProgramAllocation(HSPF1ProgramAllocations hspf1ProgramAllocations) {
      HSPF1ProgramAllocations returnObject = null;
      try {
         if (hspf1ProgramAllocations != null && hspf1ProgramAllocations.getSeasonId() > 0 && hspf1ProgramAllocations.getSeasonProgramId() > 0) {
            List<SeasonHSPAllocation> hspAllocations = seasonHSPAllocationRepository.findSeasonHSPAllocationBySeasonId(hspf1ProgramAllocations.getSeasonId());
            List<SeasonHSPAllocation> updatedList = new ArrayList<SeasonHSPAllocation>();
            for (SeasonHSPAllocation seasonHSPAllocation : hspAllocations) {
               if (seasonHSPAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_F1_ID) {
                  seasonServiceImplUtil.updateHSPF1ProgramAllocation(hspf1ProgramAllocations, updatedList, seasonHSPAllocation);
               }
            }
            seasonHSPAllocationRepository.save(updatedList);
            returnObject = getHSPF1ProgramAllocations(String.valueOf(hspf1ProgramAllocations.getSeasonProgramId()));
            returnObject = setHSPF1ProgramAllocationsStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  returnObject = setHSPF1ProgramAllocationsStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.UPDATE_F1.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_PROGRAM_ALLOCATION));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_HSP_F1_PROGRAM_ALLOCATION));
      }
      return returnObject;
   }

   // view, edit GHT Volunteer Abroad season details

   public SeasonGHTDetails getGHTVASeasonDetails(String seasonProgramId) {
      SeasonGHTDetails seasonGHTDetails = null;
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
            seasonGHTDetails = setSeasonGHTDetailsStatus(seasonGHTDetails, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  seasonGHTDetails = setSeasonGHTDetailsStatus(seasonGHTDetails,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SEASON_GHT_DEAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_GHT_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_GHT_DETAILS));

      }
      return seasonGHTDetails;
   }

public GHTSection1Base getGHTVASeasonNameAndStatus(String seasonProgramId) {
      GHTSection1Base ghtSection1Base = null;
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonVADetail != null) {
            ghtSection1Base = seasonServiceImplUtil.getVABasicDetail(seasonVADetail);
            ghtSection1Base = setGHTSection1BaseStatus(ghtSection1Base, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  ghtSection1Base = setGHTSection1BaseStatus(ghtSection1Base,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GHTVA_SEASON_NAME_STATUS.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_GHTV_SEASON_NAME_STATUS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_GHTV_SEASON_NAME_STATUS));
      }
      return ghtSection1Base;
   }

public GHTSection2Dates getGHTVASeasonDateDetails(String seasonProgramId) {
      GHTSection2Dates ghtSection2Dates = null;
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonVADetail != null) {
            ghtSection2Dates = seasonServiceImplUtil.getVADates(seasonVADetail);
            ghtSection2Dates = setGHTSection2DatesStatus(ghtSection2Dates, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  ghtSection2Dates = setGHTSection2DatesStatus(ghtSection2Dates,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GHT_SECTION_2DATES.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_GHTVA_SEASON_DATE_DEAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_GHTVA_SEASON_DATE_DEAILS));
      }
      return ghtSection2Dates;
   }

   // update GHT Volunteer Abroad season details

@Transactional
   public SeasonGHTDetails updateGHTVASeasonDetails(SeasonGHTDetails seasonGHTDetails) {
      SeasonGHTDetails returnObject = null;
      try {
         if (seasonGHTDetails == null) {
            return returnObject;
         }
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findOne(Integer.valueOf(seasonGHTDetails.getSeasonProgramId()));
         if (seasonVADetail != null) {
            try {
               if (seasonGHTDetails.getGhtBaseDetails() != null) {
                  seasonServiceImplUtil.updateVABasicDetails(seasonGHTDetails.getGhtBaseDetails(), seasonVADetail);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }
            try {
               if (seasonGHTDetails.getGhtDates() != null) {
                  seasonServiceImplUtil.updateVADates(seasonGHTDetails.getGhtDates(), seasonVADetail);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }
            try {
               if (seasonGHTDetails.getGhtNotes() != null) {
                  seasonServiceImplUtil.updateGHTNotes(seasonGHTDetails, seasonVADetail.getSeason(), CCIConstants.GHT_VOL_ABRD_ID);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }

            seasonVADetail = seasonVADetailsRepository.saveAndFlush(seasonVADetail);
            returnObject = seasonGHTDetails;
            returnObject = setSeasonGHTDetailsStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  returnObject = setSeasonGHTDetailsStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SEASON_GHTVA_DEAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_SEASON_GHT_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_SEASON_GHT_DETAILS));
      }
      return returnObject;
   }

   @Transactional
   public GHTSection1Base updateGHTVASeasonNameAndStatus(GHTSection1Base ghtSection1Base) {
      GHTSection1Base returnObject = null;
      try {
         if (ghtSection1Base == null || ghtSection1Base.getSeasonId() == 0) {
            return returnObject;
         }
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findOne(Integer.valueOf(ghtSection1Base.getSeasonProgramId()));
         if (seasonVADetail != null) {
            seasonServiceImplUtil.updateVABasicDetails(ghtSection1Base, seasonVADetail);
            seasonVADetail = seasonVADetailsRepository.saveAndFlush(seasonVADetail);
            returnObject = ghtSection1Base;
            returnObject = setGHTSection1BaseStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  ghtSection1Base = setGHTSection1BaseStatus(ghtSection1Base,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GHTVA_SEASON_NAME_STATUS.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDAE_GHTV_SEASON_NAME_STATUS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDAE_GHTV_SEASON_NAME_STATUS));
      }
      return returnObject;
   }

   @Transactional
   public GHTSection2Dates updateGHTVASeasonDateDetails(GHTSection2Dates ghtSection2Dates) {
      GHTSection2Dates returnObject = null;
      try {
         if (ghtSection2Dates == null || ghtSection2Dates.getSeasonId() == 0) {
            return returnObject;
         }
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findOne(Integer.valueOf(ghtSection2Dates.getSeasonProgramId()));
         if (seasonVADetail != null) {
            seasonServiceImplUtil.updateVADates(ghtSection2Dates, seasonVADetail);
            seasonVADetail = seasonVADetailsRepository.saveAndFlush(seasonVADetail);
            returnObject = ghtSection2Dates;
            returnObject = setGHTSection2DatesStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  ghtSection2Dates = setGHTSection2DatesStatus(ghtSection2Dates,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GHT_SECTION_2DATES.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_GHTVA_SEASON_DATE_DEAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_GHTVA_SEASON_DATE_DEAILS));
      }
      return returnObject;
   }

   // view, edit GHT Work Abroad season details

   public SeasonGHTDetails getGHTWASeasonDetails(String seasonProgramId) {
      SeasonGHTDetails seasonGHTDetails = null;
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWADetail != null) {
            seasonGHTDetails = new SeasonGHTDetails();
            seasonGHTDetails.setSeasonId(seasonWADetail.getSeason().getSeasonId());
            seasonGHTDetails.setSeasonProgramId(seasonWADetail.getSeasonWADetailsId());
            seasonGHTDetails.setDepartmentProgramId(CCIConstants.GHT_WRK_ABRD_ID);
            seasonGHTDetails.setGhtBaseDetails(seasonServiceImplUtil.getWABasicDetail(seasonWADetail));
            seasonGHTDetails.setGhtDates(seasonServiceImplUtil.getWADates(seasonWADetail));
            seasonGHTDetails.getGhtNotes().addAll(seasonServiceImplUtil.getGHTWAProgramNotes(seasonWADetail.getSeason().getSeasonId(), seasonWADetail.getSeasonWADetailsId()));
            seasonGHTDetails = setSeasonGHTDetailsStatus(seasonGHTDetails, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  seasonGHTDetails = setSeasonGHTDetailsStatus(seasonGHTDetails,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SEASON_GHTWA_DEAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_GHT_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_GHT_DETAILS));
      }
      return seasonGHTDetails;
   }

   public GHTSection1Base getGHTWASeasonNameAndStatus(String seasonProgramId) {
      GHTSection1Base ghtSection1Base = null;
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWADetail != null) {
            ghtSection1Base = seasonServiceImplUtil.getWABasicDetail(seasonWADetail);
            ghtSection1Base = setGHTSection1BaseStatus(ghtSection1Base, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  ghtSection1Base = setGHTSection1BaseStatus(ghtSection1Base,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GHTWA_SEASON_NAME_STATUS.getValue(), messageUtil.getMessage(SeasonMessageConstants.get_GHTWA_SEASON_NAME_STATUS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.get_GHTWA_SEASON_NAME_STATUS));
      }
      return ghtSection1Base;
   }

   public GHTSection2Dates getGHTWASeasonDateDetails(String seasonProgramId) {
      GHTSection2Dates ghtSection2Dates = null;
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWADetail != null) {
            ghtSection2Dates = seasonServiceImplUtil.getWADates(seasonWADetail);
            ghtSection2Dates = setGHTSection2DatesStatus(ghtSection2Dates, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  ghtSection2Dates = setGHTSection2DatesStatus(ghtSection2Dates,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GHT_SECTION_2DATES.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_GHTWA_SEASON_DATE_DEAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_GHTWA_SEASON_DATE_DEAILS));

      }
      return ghtSection2Dates;
   }

   // update GHT Work Abroad season details

   @Transactional
   public SeasonGHTDetails updateGHTWASeasonDetails(SeasonGHTDetails seasonGHTDetails) {
      SeasonGHTDetails returnObject = null;
      try {
         if (seasonGHTDetails == null) {
        	 returnObject = setSeasonGHTDetailsStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SEASON_GHTWA_DEAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_GHT_DETAILS));
             LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_GHT_DETAILS));
         }
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(seasonGHTDetails.getSeasonProgramId()));
         if (seasonWADetail != null) {
            try {
               if (seasonGHTDetails.getGhtBaseDetails() != null) {
                  seasonServiceImplUtil.updateWABasicDetails(seasonGHTDetails.getGhtBaseDetails(), seasonWADetail);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }
            try {
               if (seasonGHTDetails.getGhtDates() != null) {
                  seasonServiceImplUtil.updateWADates(seasonGHTDetails.getGhtDates(), seasonWADetail);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }
            try {
               if (seasonGHTDetails.getGhtNotes() != null) {
                  seasonServiceImplUtil.updateGHTNotes(seasonGHTDetails, seasonWADetail.getSeason(), CCIConstants.GHT_WRK_ABRD_ID);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }
            seasonWADetail = seasonWADetailsRepository.saveAndFlush(seasonWADetail);
            returnObject = seasonGHTDetails;
            returnObject = setSeasonGHTDetailsStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  returnObject = setSeasonGHTDetailsStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_SEASON_GHTWA_DEAILS.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_GHTWA_SEASON_DATE_DEAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_GHTWA_SEASON_DATE_DEAILS));
      }
      return returnObject;
   }

   @Transactional
   public GHTSection1Base updateGHTWASeasonNameAndStatus(GHTSection1Base ghtSection1Base) {
      GHTSection1Base returnObject = null;
      try {
         if (ghtSection1Base == null || ghtSection1Base.getSeasonId() == 0) {
            return returnObject;
         }
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(ghtSection1Base.getSeasonProgramId()));
         if (seasonWADetail != null) {
            seasonServiceImplUtil.updateWABasicDetails(ghtSection1Base, seasonWADetail);
            seasonWADetail = seasonWADetailsRepository.saveAndFlush(seasonWADetail);
            returnObject = ghtSection1Base;
            returnObject = setGHTSection1BaseStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         
         }
      } catch (CcighgoException e) {
    	  returnObject = setGHTSection1BaseStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GHTWA_SEASON_NAME_STATUS.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_GHTWA_SEASON_NAME_STATUS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_GHTWA_SEASON_NAME_STATUS));
      }
      return returnObject;
   }

   @Transactional
   public GHTSection2Dates updateGHTWASeasonDateDetails(GHTSection2Dates ghtSection2Dates) {
      GHTSection2Dates returnObject = null;
      try {
         if (ghtSection2Dates == null || ghtSection2Dates.getSeasonId() == 0) {
        	 returnObject = setGHTSection2DatesStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GHTWA_SEASON_NAME_STATUS.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_GHTWA_SEASON_DATE_DEAILS));
             LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_GHTWA_SEASON_DATE_DEAILS));
           return returnObject;
         }
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(ghtSection2Dates.getSeasonProgramId()));
         if (seasonWADetail != null) {
            seasonServiceImplUtil.updateWADates(ghtSection2Dates, seasonWADetail);
            seasonWADetail = seasonWADetailsRepository.saveAndFlush(seasonWADetail);
            returnObject = ghtSection2Dates;
            returnObject = setGHTSection2DatesStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  returnObject = setGHTSection2DatesStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GHTWA_SEASON_NAME_STATUS.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_GHTWA_SEASON_DATE_DEAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_GHTWA_SEASON_DATE_DEAILS));
      }
      return returnObject;
   }

   public SeasonGHTDetails getGHTHSAbroad(String seasonProgramId) {
      try {
         SeasonHSADetail seasonHSADetail = seasonHSADetailsRepository.findOne(Integer.parseInt(seasonProgramId));
         if (seasonHSADetail == null) {
            return null;
         }
         return seasonServiceImplUtil.getGHTHSAbroad(seasonHSADetail);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public SeasonGHTDetails updateGHTHSAbroad(SeasonGHTDetails seasonGHTDetails) {
      try {
         return seasonServiceImplUtil.updateGHTHSAbroad(seasonGHTDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public SeasonGHTDetails getGHTLanguageSchool(String seasonProgramId) {
      try {
         SeasonLSDetail seasonLSDetail = seasonLSDetailsRepository.findOne(Integer.parseInt(seasonProgramId));
         if (seasonLSDetail == null) {
            return null;
         }
         return seasonServiceImplUtil.getGHTLanguageSchool(seasonLSDetail);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public SeasonGHTDetails updateGHTLanguageSchool(SeasonGHTDetails seasonGHTDetails) {
      try {
         return seasonServiceImplUtil.updateGHTLanguageSchool(seasonGHTDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public SeasonGHTDetails getGHTTeachAbroad(String seasonProgramId) {
      try {
         SeasonTADetail seasonTADetail = seasonTADetailsRepository.findOne(Integer.parseInt(seasonProgramId));
         if (seasonTADetail == null) {
            return null;
         }
         return seasonServiceImplUtil.getGHTTeachAbroad(seasonTADetail);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public SeasonGHTDetails updateGHTTeachAbroad(SeasonGHTDetails seasonGHTDetails) {
      try {
         return seasonServiceImplUtil.updateGHTTeachAbroad(seasonGHTDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public GHTSection1Base getGHTHSSection1BaseAbroad(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getGHTHSSection1BaseAbroad(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public GHTSection1Base updateGHTHSSection1BaseAbroad(GHTSection1Base ghtSection1Base) {
      try {
         return seasonServiceImplUtil.updateGHTHSSection1BaseAbroad(ghtSection1Base);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public GHTSection2Dates getGHTHSSection2DatesAbroad(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getGHTHSSection2DatesAbroad(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public GHTSection2Dates updateGHTHSSection2DatesAbroad(GHTSection2Dates ghtSection2Dates) {
      try {
         return seasonServiceImplUtil.updateGHTHSSection2DatesAbroad(ghtSection2Dates);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public GHTSection1Base getGHTLanguageSchoolSection1(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getGHTLanguageSchoolSection1(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public GHTSection1Base updateGHTLanguageSchoolSection1(GHTSection1Base ghtSection1Base) {
      try {
         return seasonServiceImplUtil.updateGHTLanguageSchoolSection1(ghtSection1Base);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public GHTSection2Dates getGHTLanguageSchoolSection2Dates(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getGHTLanguageSchoolSection2Dates(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public GHTSection2Dates updateGHTLanguageSchoolSection2Dates(GHTSection2Dates ghtSection2Dates) {
      try {
         return seasonServiceImplUtil.updateGHTLanguageSchoolSection2Dates(ghtSection2Dates);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public GHTSection1Base getGHTTeachAbroadSection1(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getGHTTeachAbroadSection1(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public GHTSection1Base updateGHTTeachAbroadSection1(GHTSection1Base ghtSection1Base) {
      try {
         return seasonServiceImplUtil.updateGHTTeachAbroadSection1(ghtSection1Base);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public GHTSection2Dates getGHTTeachAbroadSection2Dates(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getGHTTeachAbroadSection2Dates(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public GHTSection2Dates updateGHTTeachAbroadSection2Dates(GHTSection2Dates ghtSection2Dates) {
      try {
         return seasonServiceImplUtil.updateGHTTeachAbroadSection2Dates(ghtSection2Dates);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public SeasonWPCAPDetails getWPCAPDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPCAPDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public SeasonWPCAPDetails updateWPCAPDetails(SeasonWPCAPDetails seasonWPCAPDetails) {
      try {
         return seasonServiceImplUtil.updateWPCAPDetails(seasonWPCAPDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public WPCAPBasicDetails getWPCAPBasicDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPCAPBasicDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public WPCAPBasicDetails updateWPCAPBasicDetails(WPCAPBasicDetails wpcapBasicDetails) {
      try {
         return seasonServiceImplUtil.updateWPCAPBasicDetails(wpcapBasicDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public WPCAPInternshipDetails getWPCAPInternshipDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPCAPInternshipDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public WPCAPInternshipDetails updateWPCAPInternshipDetails(WPCAPInternshipDetails wpcapInternshipDetails) {
      try {
         return seasonServiceImplUtil.updateWPCAPInternshipDetails(wpcapInternshipDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public WPCAPTraineeDetails getWPCAPTraineeDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPCAPTraineeDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public WPCAPTraineeDetails updateWPCAPTraineeDetails(WPCAPTraineeDetails wpcapTraineeDetails) {
      try {
         return seasonServiceImplUtil.updateWPCAPTraineeDetails(wpcapTraineeDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   // Work Programs: Work and Travel summer season service implementations

   public SeasonWPDetails getWPSumDetails(String seasonProgramId) {
      SeasonWPDetails seasonWPDetails = null;
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
            seasonWPDetails = setSeasonWPDetailsStatus(seasonWPDetails, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  seasonWPDetails = setSeasonWPDetailsStatus(seasonWPDetails,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SEASON_WP.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_WP_SUM_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_WP_SUM_DETAILS));
      }
      return seasonWPDetails;
   }

@Transactional
   public SeasonWPDetails updateWPSumDetails(SeasonWPDetails seasonWPDetails) {
      SeasonWPDetails returnObject = null;
      try {
         if (seasonWPDetails == null) {
        	 seasonWPDetails = setSeasonWPDetailsStatus(seasonWPDetails,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SEASON_WP.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_WP_SUM_DETAILS));
             LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_WP_SUM_DETAILS));
            return returnObject;
         }
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(seasonWPDetails.getSeasonProgramId()));
         if (seasonWnTSummerDetail != null) {
            if (seasonWPDetails.getWpBasicDetail() != null) {
               seasonServiceImplUtil.updateWPSummerBaseDetails(seasonWPDetails.getWpBasicDetail(), seasonWnTSummerDetail);
            }
            if (seasonWPDetails.getWpSectionOne() != null) {
               seasonServiceImplUtil.updateWPSummerSection1Details(seasonWPDetails.getWpSectionOne(), seasonWnTSummerDetail);
            }
            if (seasonWPDetails.getWpProgramAllocations() != null) {
               updateWPSumAllocationDetails(seasonWPDetails.getWpProgramAllocations());
            }
            if (seasonWPDetails.getWpNotes() != null) {
               seasonServiceImplUtil.updateWPNotes(seasonWPDetails, seasonWnTSummerDetail.getSeason(), CCIConstants.WP_WT_SUMMER_ID);
            }
            if (seasonWPDetails.getWpDocuments() != null) {
               seasonServiceImplUtil.updateWPDocs(seasonWPDetails, seasonWnTSummerDetail.getSeason(), CCIConstants.WP_WT_SUMMER_ID);
            }
            seasonWnTSummerDetail = seasonWTSummerRepository.saveAndFlush(seasonWnTSummerDetail);
            returnObject = seasonWPDetails;
            returnObject = setSeasonWPDetailsStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  seasonWPDetails = setSeasonWPDetailsStatus(seasonWPDetails,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SEASON_WP.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_SEASON_WP_SUM_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_SEASON_WP_SUM_DETAILS));
      }
      return returnObject;
   }

   public WPBasicDetail getWPSumBaseDetails(String seasonProgramId) {
      WPBasicDetail wpBasicDetail = null;
      try {
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSummerDetail != null) {
            wpBasicDetail = seasonServiceImplUtil.getWPSummerBaseDetails(seasonWnTSummerDetail);
            wpBasicDetail = setWPBasicDetailStatus(wpBasicDetail, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  wpBasicDetail = setWPBasicDetailStatus(wpBasicDetail,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_WP_BASE.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_WP_SUM_BASE_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_WP_SUM_BASE_DETAILS));
      }
      return wpBasicDetail;
   }

@Transactional
   public WPBasicDetail updateWPSumBaseDetails(WPBasicDetail wpBasicDetail) {
      WPBasicDetail returnObject = null;
      try {
         if (wpBasicDetail == null || wpBasicDetail.getSeasonId() == 0) {
            return returnObject;
         }
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(wpBasicDetail.getSeasonProgramId()));
         if (seasonWnTSummerDetail != null) {
            seasonServiceImplUtil.updateWPSummerBaseDetails(wpBasicDetail, seasonWnTSummerDetail);
            seasonWnTSummerDetail = seasonWTSummerRepository.saveAndFlush(seasonWnTSummerDetail);
            returnObject = setWPBasicDetailStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
            returnObject = wpBasicDetail;
         }
      } catch (CcighgoException e) {
    	  wpBasicDetail = setWPBasicDetailStatus(wpBasicDetail,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_WP_BASE.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_WP_SUM_BASE_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_WP_SUM_BASE_DETAILS));
      }
      return returnObject;
   }

   public WPSectionOne getWPSumSectionOneDetails(String seasonProgramId) {
      WPSectionOne wpSectionOne = null;
      try {
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSummerDetail != null) {
            wpSectionOne = seasonServiceImplUtil.getWPSummerSection1Details(seasonWnTSummerDetail);
            wpSectionOne = setWPSectionOneStatus(wpSectionOne, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  wpSectionOne = setWPSectionOneStatus(wpSectionOne,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_WP_SECTION_ONE.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_WP_SUM_SECTION_ONE_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_WP_SUM_SECTION_ONE_DETAILS));
      }
      return wpSectionOne;
   }

@Transactional
   public WPSectionOne updateWPSumSectionOneDetails(WPSectionOne wpSectionOne) {
      WPSectionOne returnObject = null;
      try {
         if (wpSectionOne == null || wpSectionOne.getSeasonProgramId() == 0) {
        	 wpSectionOne = setWPSectionOneStatus(wpSectionOne,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_WP_SECTION_ONE.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_WP_SUM_SECTION_ONE_DETAILS));
             LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_WP_SUM_SECTION_ONE_DETAILS));
        	 return returnObject;
         }
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(wpSectionOne.getSeasonProgramId()));
         if (seasonWnTSummerDetail != null) {
            seasonServiceImplUtil.updateWPSummerSection1Details(wpSectionOne, seasonWnTSummerDetail);
            seasonWnTSummerDetail = seasonWTSummerRepository.saveAndFlush(seasonWnTSummerDetail);
            returnObject = wpSectionOne;
            wpSectionOne = setWPSectionOneStatus(wpSectionOne, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  wpSectionOne = setWPSectionOneStatus(wpSectionOne,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_WP_SECTION_ONE.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_WP_SUM_SECTION_ONE_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_WP_SUM_SECTION_ONE_DETAILS));
      }
      return returnObject;
   }

   @Transactional(readOnly = true)
   public WPProgramAllocations getWPSumAllocationDetails(String seasonProgramId) {
      WPProgramAllocations wpProgramAllocations = null;
      try {
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSummerDetail != null) {
            List<SeasonWPAllocation> wpAllocations = seasonWPAllocationRepository.findSeasonWPAllocationBySeasonId(seasonWnTSummerDetail.getSeason().getSeasonId());
            if (wpAllocations != null) {
               wpProgramAllocations = new WPProgramAllocations();
               // TODO update other values once participants and partners modules are integrated
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
               wpProgramAllocations = setWPProgramAllocationsStatus(wpProgramAllocations, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
            }
         }
      } catch (CcighgoException e) {
    	  wpProgramAllocations = setWPProgramAllocationsStatus(wpProgramAllocations,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_WP_PROGRAM_ALLOCATION.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_WP_PROGRAM_ALLOCATION));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_WP_PROGRAM_ALLOCATION));
      }
      return wpProgramAllocations;
   }

@Transactional
   public WPProgramAllocations updateWPSumAllocationDetails(WPProgramAllocations wpProgramAllocations) {
      WPProgramAllocations returnObject = null;
      try {
         if (wpProgramAllocations != null && wpProgramAllocations.getSeasonId() > 0 && wpProgramAllocations.getSeasonProgramId() > 0) {
            List<SeasonWPAllocation> wpAllocations = seasonWPAllocationRepository.findSeasonWPAllocationBySeasonId(wpProgramAllocations.getSeasonId());
            List<SeasonWPAllocation> updatedList = new ArrayList<SeasonWPAllocation>();
            for (SeasonWPAllocation seasonWPAllocation : wpAllocations) {
               if (seasonWPAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_SUMMER_ID) {
                  seasonServiceImplUtil.updateWPProgramAllocation(wpProgramAllocations, updatedList, seasonWPAllocation);
               }
            }
            seasonWPAllocationRepository.save(updatedList);
            returnObject = getWPSumAllocationDetails(String.valueOf(wpProgramAllocations.getSeasonProgramId()));
            wpProgramAllocations = setWPProgramAllocationsStatus(wpProgramAllocations, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  wpProgramAllocations = setWPProgramAllocationsStatus(wpProgramAllocations,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_WP_PROGRAM_ALLOCATION.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_WP_PROGRAM_ALLOCATION));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_WP_PROGRAM_ALLOCATION));
      }
      return returnObject;
   }

   // Work Programs: Work and Travel Spring season service implementations

   public SeasonWPDetails getWPSpringDetails(String seasonProgramId) {
      SeasonWPDetails seasonWPDetails = null;
      try {
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSpringDetail != null) {
            seasonWPDetails = new SeasonWPDetails();
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
            seasonWPDetails = setSeasonWPDetailsStatus(seasonWPDetails, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  seasonWPDetails = setSeasonWPDetailsStatus(seasonWPDetails,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SEASON_WP.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_WP_SPRING_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_SEASON_WP_SPRING_DETAILS));
      }
      return seasonWPDetails;
   }

   @Transactional
   public SeasonWPDetails updateWPSpringDetails(SeasonWPDetails seasonWPDetails) {
      SeasonWPDetails returnObject = null;
      if (seasonWPDetails == null) {
         return returnObject;
      }
      try {
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(seasonWPDetails.getSeasonProgramId()));
         if (seasonWnTSpringDetail != null) {
            try {
               if (seasonWPDetails.getWpBasicDetail() != null) {
                  seasonServiceImplUtil.updateWPSpringBaseDetails(seasonWPDetails.getWpBasicDetail(), seasonWnTSpringDetail);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }
            try {
               if (seasonWPDetails.getWpSectionOne() != null) {
                  seasonServiceImplUtil.updateWPSpringSection1Details(seasonWPDetails.getWpSectionOne(), seasonWnTSpringDetail);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }
            try {
               if (seasonWPDetails.getWpProgramAllocations() != null) {
                  updateWPSpringAllocationDetails(seasonWPDetails.getWpProgramAllocations());
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }
            try {
               if (seasonWPDetails.getWpNotes() != null) {
                  seasonServiceImplUtil.updateWPNotes(seasonWPDetails, seasonWnTSpringDetail.getSeason(), CCIConstants.WP_WT_SPRING_ID);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }
            try {
               if (seasonWPDetails.getWpDocuments() != null) {
                  seasonServiceImplUtil.updateWPDocs(seasonWPDetails, seasonWnTSpringDetail.getSeason(), CCIConstants.WP_WT_SPRING_ID);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, LOGGER);
            }

            seasonWnTSpringDetail = seasonWTSpringRepository.saveAndFlush(seasonWnTSpringDetail);
            returnObject = seasonWPDetails;
            returnObject = setSeasonWPDetailsStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  seasonWPDetails = setSeasonWPDetailsStatus(seasonWPDetails,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SEASON_WP.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_SEASON_WP_SPRING_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_SEASON_WP_SPRING_DETAILS));
      }
      return returnObject;
   }

   public WPBasicDetail getWPSpringBaseDetails(String seasonProgramId) {
      WPBasicDetail wpBasicDetail = null;
      try {
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSpringDetail != null) {
            wpBasicDetail = seasonServiceImplUtil.getWPSpringBaseDetails(seasonWnTSpringDetail);
            wpBasicDetail = setWPBasicDetailStatus(wpBasicDetail, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  wpBasicDetail = setWPBasicDetailStatus(wpBasicDetail,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_WP_BASE.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_WP_SPRING_BASE_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_WP_SPRING_BASE_DETAILS));
      }
      return wpBasicDetail;
   }

   @Transactional
   public WPBasicDetail updateWPSpringBaseDetails(WPBasicDetail wpBasicDetail) {
      WPBasicDetail returnObject = null;
      try {
         if (wpBasicDetail == null || wpBasicDetail.getSeasonId() == 0) {
            return returnObject;
         }
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(wpBasicDetail.getSeasonProgramId()));
         if (seasonWnTSpringDetail != null) {
            seasonServiceImplUtil.updateWPSpringBaseDetails(wpBasicDetail, seasonWnTSpringDetail);
            seasonWnTSpringDetail = seasonWTSpringRepository.saveAndFlush(seasonWnTSpringDetail);
            returnObject = wpBasicDetail;
            returnObject = setWPBasicDetailStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  wpBasicDetail = setWPBasicDetailStatus(wpBasicDetail,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_WP_BASE.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_WP_SPRING_BASE_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_WP_SPRING_BASE_DETAILS));
      }
      return returnObject;
   }

   public WPSectionOne getWPSpringSectionOneDetails(String seasonProgramId) {
      WPSectionOne wpSectionOne = null;
      try {
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSpringDetail != null) {
            wpSectionOne = seasonServiceImplUtil.getWPSpringSection1Details(seasonWnTSpringDetail);
            wpSectionOne = setWPSectionOneStatus(wpSectionOne, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  wpSectionOne = setWPSectionOneStatus(wpSectionOne,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_WP_SECTION_ONE.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_WP_SPRING_SECTION_ONE_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_WP_SPRING_SECTION_ONE_DETAILS));
      }
      return wpSectionOne;
   }

   @Transactional
   public WPSectionOne updateWPSpringSectionOneDetails(WPSectionOne wpSectionOne) {
      WPSectionOne returnObject = null;
      try {
         if (wpSectionOne == null || wpSectionOne.getSeasonProgramId() == 0) {
            return returnObject;
         }
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(wpSectionOne.getSeasonProgramId()));
         if (seasonWnTSpringDetail != null) {
            seasonServiceImplUtil.updateWPSpringSection1Details(wpSectionOne, seasonWnTSpringDetail);
            seasonWnTSpringDetail = seasonWTSpringRepository.saveAndFlush(seasonWnTSpringDetail);
            returnObject = wpSectionOne;
            returnObject = setWPSectionOneStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  returnObject = setWPSectionOneStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_WP_SECTION_ONE.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_WP_SPRING_SECTION_ONE_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_WP_SPRING_SECTION_ONE_DETAILS));
      }
      return returnObject;
   }

   public WPProgramAllocations getWPSpringAllocationDetails(String seasonProgramId) {
      WPProgramAllocations wpProgramAllocations = null;
      try {
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSpringDetail != null) {
            List<SeasonWPAllocation> wpAllocations = seasonWPAllocationRepository.findSeasonWPAllocationBySeasonId(seasonWnTSpringDetail.getSeason().getSeasonId());
            if (wpAllocations != null) {
               wpProgramAllocations = new WPProgramAllocations();
               // TODO update other values once participants and partners modules are integrated
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
               wpProgramAllocations = setWPProgramAllocationsStatus(wpProgramAllocations, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
            }
         }
      } catch (CcighgoException e) {
    	  wpProgramAllocations = setWPProgramAllocationsStatus(wpProgramAllocations,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_WP_PROGRAM_ALLOCATION.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_WP_SPRING_ALLOCATION_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_WP_SPRING_ALLOCATION_DETAILS));
      }
      return wpProgramAllocations;
   }

   @Transactional
   public WPProgramAllocations updateWPSpringAllocationDetails(WPProgramAllocations wpProgramAllocations) {
      WPProgramAllocations returnObject = null;
      try {
         if (wpProgramAllocations != null && wpProgramAllocations.getSeasonId() > 0 && wpProgramAllocations.getSeasonProgramId() > 0) {
            List<SeasonWPAllocation> wpAllocations = seasonWPAllocationRepository.findSeasonWPAllocationBySeasonId(wpProgramAllocations.getSeasonId());
            List<SeasonWPAllocation> updatedList = new ArrayList<SeasonWPAllocation>();
            for (SeasonWPAllocation seasonWPAllocation : wpAllocations) {
               if (seasonWPAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_SPRING_ID) {
                  seasonServiceImplUtil.updateWPProgramAllocation(wpProgramAllocations, updatedList, seasonWPAllocation);
               }
            }
            seasonWPAllocationRepository.save(updatedList);
            returnObject = getWPSpringAllocationDetails(String.valueOf(wpProgramAllocations.getSeasonProgramId()));
            returnObject = setWPProgramAllocationsStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  wpProgramAllocations = setWPProgramAllocationsStatus(wpProgramAllocations,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_WP_PROGRAM_ALLOCATION.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_WP_SPRING_ALLOCATION_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_WP_SPRING_ALLOCATION_DETAILS));
      }
      return returnObject;
   }

   public SeasonWPDetails getWPWinterDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPWinterDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public SeasonWPDetails editWPWinterDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPWinterDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public SeasonWPDetails updateWPWinterDetails(SeasonWPDetails seasonWPDetails) {
      try {
         return seasonServiceImplUtil.updateWPWinterDetails(seasonWPDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public WPBasicDetail getWPWinterBaseDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPWinterBaseDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public WPBasicDetail editWPWinterBaseDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPWinterBaseDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public WPBasicDetail updateWPWinterBaseDetails(WPBasicDetail wpBasicDetail) {
      try {
         return seasonServiceImplUtil.updateWPWinterBaseDetails(wpBasicDetail);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public WPSectionOne getWPWinterSectionOneDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPWinterSectionOneDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public WPSectionOne editWPWinterSectionOneDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPWinterSectionOneDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public WPSectionOne updateWPWinterSectionOneDetails(WPSectionOne wpSectionOne) {
      try {
         return seasonServiceImplUtil.updateWPWinterSectionOneDetails(wpSectionOne);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public WPProgramAllocations getWPWinterAllocationDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPWinterAllocationDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public WPProgramAllocations editWPWinterAllocationDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPWinterAllocationDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public WPProgramAllocations updateWPWinterAllocationDetails(WPProgramAllocations wpProgramAllocations) {
      try {
         return seasonServiceImplUtil.updateWPWinterAllocationDetails(wpProgramAllocations);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Transactional
   public CloneSeason cloneSeason(CloneSeason cloneSeason) {
      CloneSeason returnObject = null;
      try {
         if (cloneSeason.getSeasonId() == 0 || cloneSeason.getSeasonId() < 0) {
            throw new InvalidServiceConfigurationException("season id must be positive integer greater than 0");
         }
         if (cloneSeason.getDepartmentId() == 0 || cloneSeason.getDepartmentId() < 0) {
            throw new InvalidServiceConfigurationException("department id must be positive integer greater than 0");
         }
         Season existingSeason = seasonRepository.findOne(cloneSeason.getSeasonId());
         if (existingSeason != null) {
            LookupDepartment department = existingSeason.getLookupDepartment();
            if (department != null) {
               List<SeasonDepartmentDocument> clonedSeasonDocs = null;
               List<SeasonDepartmentDocument> existingSeasonDocs = existingSeason.getSeasonDepartmentDocuments();
               List<com.ccighgo.db.entities.SeasonProgramDocument> clonedPrgDocs = null;
               List<com.ccighgo.db.entities.SeasonProgramDocument> existingDocs = existingSeason.getSeasonProgramDocuments();
               try {
                  if (department.getDepartmentName().equals(CCIConstants.DEPT_HIGH_SCHOOL_PROGRAMS)) {
                     Season season = seasonCloningHelper.cloneHighLevelSeason(cloneSeason, existingSeason, department);
                     Season clonedHSPSeason = seasonRepository.saveAndFlush(season);
                     // clone season documents
                     if (existingSeasonDocs != null && existingSeasonDocs.size() > 0) {
                        clonedSeasonDocs = new ArrayList<SeasonDepartmentDocument>();
                        for (SeasonDepartmentDocument doc : existingSeasonDocs) {
                           if (doc.getSeason().getLookupDepartment().getDepartmentName().equals(CCIConstants.DEPT_HIGH_SCHOOL_PROGRAMS)) {
                              clonedSeasonDocs.add(seasonCloningHelper.getSeasonDepartmentDocument(doc, clonedHSPSeason));
                           }
                        }
                        seasonDepartmentDocumentRepository.save(clonedSeasonDocs);
                     }

                     List<SeasonHSPAllocation> seasonHspallocations = existingSeason.getSeasonHspallocations();
                     List<SeasonHSPAllocation> seasonHspallocationNewList = null;
                     if (seasonHspallocations != null && seasonHspallocations.size() > 0) {
                        seasonHspallocationNewList = seasonCloningHelper.cloneHSPAllocations(clonedHSPSeason, seasonHspallocations);
                     }
                     SeasonHSPConfiguration seasonHSPConfiguration = seasonCloningHelper.cloneHSPConfiguration(cloneSeason, clonedHSPSeason);
                     SeasonJ1Detail seasonJ1Detail = seasonCloningHelper.cloneHSPJ1seasonProgram(existingSeason, clonedHSPSeason);
                     SeasonF1Detail seasonF1Detail = seasonCloningHelper.cloneHSPF1SeasonProgram(existingSeason, clonedHSPSeason);
                     SeasonIHPDetail seasonIHPDetail = seasonCloningHelper.cloneHSPIHPProgram(existingSeason, clonedHSPSeason, cloneSeason);
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
                                 clonedPrgDocs.add(seasonCloningHelper.getSeasonProgramDocument(doc, clonedHSPSeason));
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
                                 clonedPrgDocs.add(seasonCloningHelper.getSeasonProgramDocument(doc, clonedHSPSeason));
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
                                 clonedPrgDocs.add(seasonCloningHelper.getSeasonProgramDocument(doc, clonedHSPSeason));
                              }
                           }
                           seasonProgramDocumentRepository.save(clonedPrgDocs);
                        }
                     }
                     cloneSeason.setSeasonId(clonedHSPSeason.getSeasonId());
                     returnObject = cloneSeason;
                  }
               } catch (Exception e) {
                  ExceptionUtil.logException(e, LOGGER);
               }

               // clone WP
               try {
                  if (department.getDepartmentName().equals(CCIConstants.DEPT_WORK_PROGRAMS)) {
                     // clone high level WP season
                     Season season = seasonCloningHelper.cloneHighLevelSeason(cloneSeason, existingSeason, department);
                     Season clonedWPSeason = seasonRepository.saveAndFlush(season);
                     // clone high level season documents
                     if (existingSeasonDocs != null && existingSeasonDocs.size() > 0) {
                        clonedSeasonDocs = new ArrayList<SeasonDepartmentDocument>();
                        for (SeasonDepartmentDocument doc : existingSeasonDocs) {
                           if (doc.getSeason().getLookupDepartment().getDepartmentName().equals(CCIConstants.DEPT_WORK_PROGRAMS)) {
                              clonedSeasonDocs.add(seasonCloningHelper.getSeasonDepartmentDocument(doc, clonedWPSeason));
                           }
                        }
                        seasonDepartmentDocumentRepository.save(clonedSeasonDocs);
                     }
                     List<SeasonWPAllocation> seasonWPAllocations = existingSeason.getSeasonWpallocations();
                     List<SeasonWPAllocation> seasonWPAallocationCloneList = null;
                     if (seasonWPAllocations != null && seasonWPAllocations.size() > 0) {
                        seasonWPAallocationCloneList = seasonCloningHelper.cloneWPAllocations(clonedWPSeason, seasonWPAllocations);
                     }
                     SeasonWPConfiguration seasonWPConfiguration = seasonCloningHelper.cloneWPConfigurations(cloneSeason, clonedWPSeason);
                     SeasonWnTSpringDetail seasonWnTSpringDetail = seasonCloningHelper.cloneWPSpringProgram(existingSeason, clonedWPSeason);
                     SeasonWnTSummerDetail seasonWnTSummerDetail = seasonCloningHelper.cloneWPSummerProgram(existingSeason, clonedWPSeason);
                     SeasonWnTWinterDetail seasonWnTWinterDetail = seasonCloningHelper.cloneWPWinterProgram(existingSeason, clonedWPSeason);
                     SeasonCAPDetail seasonCAPDetail = seasonCloningHelper.cloneWPCapProgram(existingSeason, clonedWPSeason);
                     seasonWPAllocationRepository.save(seasonWPAallocationCloneList);
                     seasonWPConfigurationRepository.save(seasonWPConfiguration);
                     if (seasonWnTSpringDetail != null) {
                        seasonWTSpringRepository.save(seasonWnTSpringDetail);
                        if (existingDocs != null) {
                           clonedPrgDocs = new ArrayList<com.ccighgo.db.entities.SeasonProgramDocument>();
                           for (com.ccighgo.db.entities.SeasonProgramDocument doc : existingDocs) {
                              if (doc.getDepartmentProgram().getProgramName().equals(CCIConstants.WP_WT_SPRING)) {
                                 clonedPrgDocs.add(seasonCloningHelper.getSeasonProgramDocument(doc, clonedWPSeason));
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
                                 clonedPrgDocs.add(seasonCloningHelper.getSeasonProgramDocument(doc, clonedWPSeason));
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
                                 clonedPrgDocs.add(seasonCloningHelper.getSeasonProgramDocument(doc, clonedWPSeason));
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
                                 clonedPrgDocs.add(seasonCloningHelper.getSeasonProgramDocument(doc, clonedWPSeason));
                              }
                           }
                           seasonProgramDocumentRepository.save(clonedPrgDocs);
                        }
                     }
                     cloneSeason.setSeasonId(clonedWPSeason.getSeasonId());
                     returnObject = cloneSeason;
                  }
               } catch (Exception e) {
                  ExceptionUtil.logException(e, LOGGER);
               }

               try {
                  if (department.getDepartmentName().equals(CCIConstants.DEPT_GREEN_HEART_TRAVEL)) {
                     Season season = seasonCloningHelper.cloneHighLevelSeason(cloneSeason, existingSeason, department);
                     Season clonedGHTSeason = seasonRepository.saveAndFlush(season);
                     if (existingSeasonDocs != null && existingSeasonDocs.size() > 0) {
                        clonedSeasonDocs = new ArrayList<SeasonDepartmentDocument>();
                        for (SeasonDepartmentDocument doc : existingSeasonDocs) {
                           if (doc.getSeason().getLookupDepartment().getDepartmentName().equals(CCIConstants.DEPT_GREEN_HEART_TRAVEL)) {
                              clonedSeasonDocs.add(seasonCloningHelper.getSeasonDepartmentDocument(doc, clonedGHTSeason));
                           }
                        }
                        seasonDepartmentDocumentRepository.save(clonedSeasonDocs);
                     }
                     SeasonGHTConfiguration seasonGHTConfiguration = seasonCloningHelper.cloneGHTConfiguration(cloneSeason, clonedGHTSeason);
                     SeasonHSADetail seasonHSADetail = seasonCloningHelper.cloneGHTHSAProgram(existingSeason, clonedGHTSeason);
                     SeasonLSDetail seasonLSDetail = seasonCloningHelper.cloneGHTLSProgram(existingSeason, clonedGHTSeason);
                     SeasonTADetail seasonTADetail = seasonCloningHelper.cloneGHTTAProgram(existingSeason, clonedGHTSeason);
                     SeasonVADetail seasonVADetail = seasonCloningHelper.cloneGHTVAProgram(existingSeason, clonedGHTSeason);
                     SeasonWADetail seasonWADetail = seasonCloningHelper.cloneGHTWAProgram(existingSeason, clonedGHTSeason);
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

                  } else {
                     // update header type of department not applicable
                  }
               } catch (Exception e) {
                  ExceptionUtil.logException(e, LOGGER);
               }

            }
         } else {
            throw new InvalidServiceConfigurationException("no season found with the id: " + cloneSeason.getSeasonId());
         }
      } catch (CcighgoServiceException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   @Override
   @Transactional
   public SeasonDepartmentNotes addSeasonDepartmentNote(SeasonDepartmentNotes seasonDepartmentNotes) {
      SeasonDepartmentNotes returnObject = null;
      try {
         if (seasonDepartmentNotes.getSeasonId() > 0 && seasonDepartmentNotes.getNoteValue() != null) {
            SeasonDepartmentNote departmentNote = new SeasonDepartmentNote();
            departmentNote.setActive(CCIConstants.ACTIVE);
            departmentNote.setSeason(seasonRepository.findOne(seasonDepartmentNotes.getSeasonId()));
            departmentNote.setDepartmentNote(seasonDepartmentNotes.getNoteValue());
            departmentNote.setCreatedBy(1);
            departmentNote.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
            departmentNote.setModifiedBy(1);
            departmentNote.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
            seasonDepartmentNotesRepository.saveAndFlush(departmentNote);
            returnObject = seasonDepartmentNotes;
            returnObject = setSeasonDepartmentNotesStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  returnObject = setSeasonDepartmentNotesStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SEASON_DEPT_NOTES.getValue(), messageUtil.getMessage(SeasonMessageConstants.ADD_SEASON_DEPT_NOTES));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ADD_SEASON_DEPT_NOTES));
      }
      return returnObject;
   }

@Override
   public com.ccighgo.service.transport.season.beans.seasondepartdoc.SeasonDepartmentDocument addSeasonDepartmentDoc(
         com.ccighgo.service.transport.season.beans.seasondepartdoc.SeasonDepartmentDocument seasonDepartmentDocument) {
      com.ccighgo.service.transport.season.beans.seasondepartdoc.SeasonDepartmentDocument returnObject = null;
      try {
         if (seasonDepartmentDocument.getSeasonId() > 0 && seasonDepartmentDocument.getDocUrl() != null && seasonDepartmentDocument.getDocName() != null) {
            DocumentInformation documentInformation = new DocumentInformation();
            documentInformation.setFileName(seasonDepartmentDocument.getFileName() != null ? seasonDepartmentDocument.getFileName() : null);
            documentInformation.setDocumentName(seasonDepartmentDocument.getDocName() != null ? seasonDepartmentDocument.getDocName() : null);
            documentInformation.setUrl(seasonDepartmentDocument.getDocUrl());
            documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(seasonDepartmentDocument.getDocType()));
            documentInformation.setCreatedBy(1);
            documentInformation.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
            documentInformation.setModifiedBy(1);
            documentInformation.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
            documentInformation = documentInformationRepository.saveAndFlush(documentInformation);

            com.ccighgo.db.entities.SeasonDepartmentDocument departmentDocument = new com.ccighgo.db.entities.SeasonDepartmentDocument();
            departmentDocument.setActive(CCIConstants.ACTIVE);
            departmentDocument.setDocumentInformation(documentInformation);
            departmentDocument.setSeason(seasonRepository.findOne(seasonDepartmentDocument.getSeasonId()));
            departmentDocument.setCreatedBy(1);
            departmentDocument.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
            departmentDocument.setModifiedBy(1);
            departmentDocument.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
            seasonDepartmentDocumentRepository.saveAndFlush(departmentDocument);
            returnObject = seasonDepartmentDocument;
            //returnObject = setSeasonDepartmentDocumentStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  /*returnObject = setSeasonDepartmentDocumentStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SEASON_DEPT_NOTES.getValue(), messageUtil.getMessage(SeasonMessageConstants.ADD_SEASON_DEPT_NOTES));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ADD_SEASON_DEPT_NOTES));*/
    	  ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   
@Override
   public SeasonProgramNote addSeasonProgramNote(SeasonProgramNote seasonProgramNote) {
      SeasonProgramNote returnObject = null;
      try {
         if (seasonProgramNote.getSeasonId() > 0 && seasonProgramNote.getNoteValue() != null) {
            com.ccighgo.db.entities.SeasonProgramNote programNote = new com.ccighgo.db.entities.SeasonProgramNote();
            programNote.setActive(CCIConstants.ACTIVE);
            programNote.setSeason(seasonRepository.findOne(seasonProgramNote.getSeasonId()));
            programNote.setDepartmentProgram(departmentProgramRepository.findOne(seasonProgramNote.getDepartmentProgramId()));
            programNote.setProgramNote(seasonProgramNote.getNoteValue());
            programNote.setCreatedBy(1);
            programNote.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
            programNote.setModifiedBy(1);
            programNote.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
            seasonProgramNotesRepository.saveAndFlush(programNote);
            returnObject = seasonProgramNote;
            returnObject = setSeasonProgramNoteStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  returnObject = setSeasonProgramNoteStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SEASON_PROGRAM_NOTES.getValue(), messageUtil.getMessage(SeasonMessageConstants.ADD_SEASON_PROGRAM_NOTES));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ADD_SEASON_PROGRAM_NOTES));
      }
      return returnObject;
   }

@Override
   public SeasonProgramDocument addSeasonProgramDoc(SeasonProgramDocument seasonProgramDocument) {
      SeasonProgramDocument returnObject = null;
      try {
         if (seasonProgramDocument.getSeasonId() > 0) {
            com.ccighgo.db.entities.SeasonProgramDocument programDocument = new com.ccighgo.db.entities.SeasonProgramDocument();
            DocumentInformation documentInformation = new DocumentInformation();
            documentInformation.setFileName(seasonProgramDocument.getFileName() != null ? seasonProgramDocument.getFileName() : null);
            documentInformation.setDocumentName(seasonProgramDocument.getDocName() != null ? seasonProgramDocument.getDocName() : null);
            documentInformation.setUrl(seasonProgramDocument.getDocUrl());
            documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(seasonProgramDocument.getDocType()));
            documentInformation.setCreatedBy(1);
            documentInformation.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
            documentInformation.setModifiedBy(1);
            documentInformation.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
            documentInformation = documentInformationRepository.saveAndFlush(documentInformation);

            programDocument.setActive(CCIConstants.ACTIVE);
            programDocument.setSeason(seasonRepository.findOne(seasonProgramDocument.getSeasonId()));
            programDocument.setDocumentInformation(documentInformation);
            programDocument.setDepartmentProgram(departmentProgramRepository.findOne(seasonProgramDocument.getDepartmentProgramId()));
            programDocument.setCreatedBy(1);
            programDocument.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
            programDocument.setModifiedBy(1);
            programDocument.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
            seasonProgramDocumentRepository.saveAndFlush(programDocument);
            returnObject = seasonProgramDocument;
            returnObject = setSeasonProgramDocumentStatus(returnObject, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  returnObject = setSeasonProgramDocumentStatus(returnObject,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SEASON_PROGRAM_DOC.getValue(), messageUtil.getMessage(SeasonMessageConstants.ADD_SEASON_PROGRAM_DOC));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.ADD_SEASON_PROGRAM_DOC));
      }
      return returnObject;
   }

@Transactional(readOnly = true)
   public WPCAPProgramAllocations getWPCAPAllocationDetails(String seasonProgramId) {
      WPCAPProgramAllocations wpcapProgramAllocations = null;
      try {
         SeasonCAPDetail seasonCapDetail = seasonCAPDetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonCapDetail != null) {
            List<SeasonWPAllocation> wpAllocations = seasonWPAllocationRepository.findSeasonWPAllocationBySeasonId(seasonCapDetail.getSeason().getSeasonId());
            if (wpAllocations != null && !wpAllocations.isEmpty()) {
               wpcapProgramAllocations = new WPCAPProgramAllocations();
               // TODO update other values once participants and partners modules are integrated
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

               wpcapProgramAllocations.setTotalMaximumParticipant(totalMaxParticipants);
               wpcapProgramAllocations = setWPCAPProgramAllocationsStatus(wpcapProgramAllocations, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
            }
         }
      } catch (CcighgoException e) {
    	  wpcapProgramAllocations = setWPCAPProgramAllocationsStatus(wpcapProgramAllocations,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_WPCAP_PROGRAM_ALLOCATION.getValue(), messageUtil.getMessage(SeasonMessageConstants.GET_WPCAP_ALLOCATION_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.GET_WPCAP_ALLOCATION_DETAILS));
      }
      return wpcapProgramAllocations;
   }

@Override
   public WPCAPProgramAllocations updateWPCAPAllocationDetails(WPCAPProgramAllocations wpcapProgramAllocations) {
      WPCAPProgramAllocations returnObject = null;
      try {
         if (wpcapProgramAllocations != null && wpcapProgramAllocations.getSeasonId() > 0 && wpcapProgramAllocations.getSeasonProgramId() > 0) {
            List<SeasonWPAllocation> wpAllocations = seasonWPAllocationRepository.findSeasonWPAllocationBySeasonId(wpcapProgramAllocations.getSeasonId());
            List<SeasonWPAllocation> updatedList = new ArrayList<SeasonWPAllocation>();
            for (SeasonWPAllocation seasonWPAllocation : wpAllocations) {
               if (seasonWPAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_CAP_ID) {
                  seasonServiceImplUtil.updateWPProgramAllocation(wpcapProgramAllocations, updatedList, seasonWPAllocation);
               }
            }
            seasonWPAllocationRepository.save(updatedList);
            returnObject = getWPCAPAllocationDetails(String.valueOf(wpcapProgramAllocations.getSeasonProgramId()));
            wpcapProgramAllocations = setWPCAPProgramAllocationsStatus(wpcapProgramAllocations, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.SEASON_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         }
      } catch (CcighgoException e) {
    	  wpcapProgramAllocations = setWPCAPProgramAllocationsStatus(wpcapProgramAllocations,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_WPCAP_PROGRAM_ALLOCATION.getValue(), messageUtil.getMessage(SeasonMessageConstants.UPDATE_WPCAP_ALLOCATION_DETAILS));
          LOGGER.error(messageUtil.getMessage(SeasonMessageConstants.UPDATE_WPCAP_ALLOCATION_DETAILS));
      }
      return returnObject;
   }

   @Override
   public DocumentTypes getDocumentTypes() {
      DocumentTypes documentType = null;
      try {
         List<com.ccighgo.db.entities.DocumentType> typeList = documentTypeRepository.findAll();
         if (typeList != null) {
            documentType = new DocumentTypes();
            for (com.ccighgo.db.entities.DocumentType docType : typeList) {
               DocumentType dt = new DocumentType();
               dt.setDocumentTypeId(docType.getDocumentTypeId());
               dt.setDocumentTypeName(docType.getDocumentTypeName());
               documentType.getDocumentTypes().add(dt);
            }
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }

      return documentType;
   }

   @Override
   public SeasonHspStpIhpDetails getIHPDetails(String seasonProgramId) {
      try {
         if (Integer.valueOf(seasonProgramId) == 0 || Integer.valueOf(seasonProgramId) < 0) {
            throw new InvalidServiceConfigurationException("program id must be positive integer");
         } else {
            return ihpProgramHelper.getIHPDetails(Integer.valueOf(seasonProgramId));
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }

   }

   @Override
   public IHPNameAndStatus getIHPNameAndStatus(String seasonProgramId) {
      try {
         if (Integer.valueOf(seasonProgramId) == 0 || Integer.valueOf(seasonProgramId) < 0) {
            throw new InvalidServiceConfigurationException("program id must be positive integer");
         } else {
            return ihpProgramHelper.getIHPNameAndStatus(Integer.valueOf(seasonProgramId));
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public IHPDates getIHPDates(String seasonProgramId) {
      try {
         if (Integer.valueOf(seasonProgramId) == 0 || Integer.valueOf(seasonProgramId) < 0) {
            throw new InvalidServiceConfigurationException("program id must be positive integer");
         } else {
            return ihpProgramHelper.getIHPDates(Integer.valueOf(seasonProgramId));
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public IHPProgramConfiguration getIHPProgramConfigurationDetails(String seasonProgramId) {
      try {
         if (Integer.valueOf(seasonProgramId) == 0 || Integer.valueOf(seasonProgramId) < 0) {
            throw new InvalidServiceConfigurationException("program id must be positive integer");
         } else {
            return ihpProgramHelper.getIHPConfiguration(Integer.valueOf(seasonProgramId));
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public SeasonHspStpIhpDetails updateIHPDetails(SeasonHspStpIhpDetails seasonHspStpIhpDetails) {
      try {
         if (seasonHspStpIhpDetails == null) {
            throw new InvalidServiceConfigurationException("Details cannot be null");
         } else if (seasonHspStpIhpDetails.getSeasonProgramId() == 0 || seasonHspStpIhpDetails.getSeasonProgramId() < 0) {
            throw new InvalidServiceConfigurationException("program id must be positive integer");
         } else {
            return ihpProgramHelper.updateIHPDetails(seasonHspStpIhpDetails);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public IHPNameAndStatus updateIHPNameAndStatus(IHPNameAndStatus ihpNameAndStatus) {
      try {
         if (ihpNameAndStatus == null) {
            throw new InvalidServiceConfigurationException("Details cannot be null");
         } else if (ihpNameAndStatus.getSeasonProgramId() == 0 || ihpNameAndStatus.getSeasonProgramId() < 0) {
            throw new InvalidServiceConfigurationException("program id must be positive integer");
         } else {
            return ihpProgramHelper.updateIHPNameAndStatus(ihpNameAndStatus);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public IHPDates updateIHPDates(IHPDates ihpDates) {
      try {
         if (ihpDates == null) {
            throw new InvalidServiceConfigurationException("Details cannot be null");
         } else if (ihpDates.getSeasonProgramId() == 0 || ihpDates.getSeasonProgramId() < 0) {
            throw new InvalidServiceConfigurationException("program id must be positive integer");
         } else {
            return ihpProgramHelper.updateIHPDates(ihpDates);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public IHPProgramConfiguration updateIHPProgramConfigurationDetails(IHPProgramConfiguration ihpProgramConfiguration) {
      try {

         if (ihpProgramConfiguration == null) {
            throw new InvalidServiceConfigurationException("Details cannot be null");
         } else if (ihpProgramConfiguration.getSeasonProgramId() == 0 || ihpProgramConfiguration.getSeasonProgramId() < 0) {
            throw new InvalidServiceConfigurationException("program id must be positive integer");
         } else {
            return ihpProgramHelper.updateIHPProgramConfiguration(ihpProgramConfiguration);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }
   
   
   /**
    * 
    * @param SeasonsList
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return seasonsList
    */
   private SeasonsList setseasonsListStatus(SeasonsList seasonsList, String code, String type, int serviceCode, String message ) {
	   if(seasonsList==null) seasonsList = new SeasonsList(); 
	   seasonsList.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return seasonsList;
	   
   }
   
   /**
    * 
    * @param SeasonBean
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return seasonsList
    */
   
   private SeasonBean setSeasonBeanStatus(SeasonBean seasonBean, String code, String type, int serviceCode, String message ) {
	   if(seasonBean==null) seasonBean = new SeasonBean(); 
	   seasonBean.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return seasonBean;
	   
   }
   
   
   /**
    * 
    * @param SeasonPrograms
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return seasonPrograms
    */
   
   private SeasonPrograms setSeasonProgramsStatus(SeasonPrograms seasonPrograms, String code, String type, int serviceCode, String message ) {
	   if(seasonPrograms==null) seasonPrograms = new SeasonPrograms(); 
	   seasonPrograms.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return seasonPrograms;
	   
   }
   
   /**
    * 
    * @param SeasonStatuses
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return seasonStatuses
    */
   
   private SeasonStatuses setSeasonStatusesStatus(SeasonStatuses seasonStatuses, String code, String type, int serviceCode, String message ) {
	   if(seasonStatuses==null) seasonStatuses = new SeasonStatuses(); 
	   seasonStatuses.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return seasonStatuses;
	   
   }
   
   /**
    * 
    * @param SeasonHspJ1HSDetails
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return seasonHspJ1HSDetails
    */
   
   private SeasonHspJ1HSDetails setSeasonHspJ1HSDetailsStatus(SeasonHspJ1HSDetails seasonHspJ1HSDetails, String code, String type, int serviceCode, String message ) {
	   if(seasonHspJ1HSDetails==null) seasonHspJ1HSDetails = new SeasonHspJ1HSDetails(); 
	   seasonHspJ1HSDetails.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return seasonHspJ1HSDetails;
   }

   /**
    * 
    * @param J1HSBasicDetail
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return j1hsBasicDetail
    */
   
   private J1HSBasicDetail setJ1HSBasicDetailStatus(J1HSBasicDetail j1hsBasicDetail, String code, String type, int serviceCode, String message ) {
	   if(j1hsBasicDetail==null) j1hsBasicDetail = new J1HSBasicDetail(); 
	   j1hsBasicDetail.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return j1hsBasicDetail;
   }
  
   /**
    * 
    * @param J1HSJanStart
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return j1hsJanStart
    */
   
   private J1HSJanStart setJ1HSJanStartStatus(J1HSJanStart j1hsJanStart, String code, String type, int serviceCode, String message ) {
	   if(j1hsJanStart==null) j1hsJanStart = new J1HSJanStart(); 
	   j1hsJanStart.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return j1hsJanStart;
   }
  
   /**
    * 
    * @param J1HSAugStart
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return j1hsAugStart
    */
   
   private J1HSAugStart setJ1HSAugStartStatus(J1HSAugStart j1hsAugStart, String code, String type, int serviceCode, String message ) {
	   if(j1hsAugStart==null) j1hsAugStart = new J1HSAugStart(); 
	   j1hsAugStart.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return j1hsAugStart;
   }
  
   /**
    * 
    * @param J1HSFieldSettings
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return j1hsAugStart
    */
   
   private J1HSFieldSettings setJ1HSFieldSettingsStatus(J1HSFieldSettings j1hsFieldSettings, String code, String type, int serviceCode, String message ) {
	   if(j1hsFieldSettings==null) j1hsFieldSettings = new J1HSFieldSettings(); 
	   j1hsFieldSettings.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return j1hsFieldSettings;
   }
   //J1HSProgramAllocations
   /**
    * 
    * @param J1HSProgramAllocations
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return j1hsProgramAllocations
    */
   
   private J1HSProgramAllocations setJ1HSProgramAllocationsStatus(J1HSProgramAllocations j1hsProgramAllocations, String code, String type, int serviceCode, String message ) {
	   if(j1hsProgramAllocations==null) j1hsProgramAllocations = new J1HSProgramAllocations(); 
	   j1hsProgramAllocations.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return j1hsProgramAllocations;
   }
   //setHSPF1ProgramAllocationsStatus
   
   /**
    * 
    * @param HSPF1ProgramAllocations
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return hSPF1ProgramAllocations
    */
   
   private HSPF1ProgramAllocations setHSPF1ProgramAllocationsStatus(HSPF1ProgramAllocations hSPF1ProgramAllocations, String code, String type, int serviceCode, String message ) {
	   if(hSPF1ProgramAllocations==null) hSPF1ProgramAllocations = new HSPF1ProgramAllocations(); 
	   hSPF1ProgramAllocations.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return hSPF1ProgramAllocations;
   }
   /**
    * 
    * @param HSPF1FieldSettings
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return hSPF1FieldSettings
    */
   private HSPF1FieldSettings setHSPF1FieldSettingsStatus(
			HSPF1FieldSettings hSPF1FieldSettings, String code, String type, int serviceCode, String message ) {
	   if(hSPF1FieldSettings==null) hSPF1FieldSettings = new HSPF1FieldSettings(); 
	   hSPF1FieldSettings.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return hSPF1FieldSettings;
	}
   /**
    * 
    * @param HSPF1AugustStart1StSemesterDetails
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return hSPF1AugustStart1StSemesterDetails
    */
   private HSPF1AugustStart1StSemesterDetails setHSPF1AugustStart1StSemesterDetailsStatus(
			HSPF1AugustStart1StSemesterDetails hSPF1AugustStart1StSemesterDetails,
			String code, String type, int serviceCode, String message ) {
	   if(hSPF1AugustStart1StSemesterDetails==null) hSPF1AugustStart1StSemesterDetails = new HSPF1AugustStart1StSemesterDetails(); 
	   hSPF1AugustStart1StSemesterDetails.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return hSPF1AugustStart1StSemesterDetails;
	}
   /**
    * 
    * @param HSPF1AugustStartFullYearDetails
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return hSPF1AugustStartFullYearDetails
    */
   private HSPF1AugustStartFullYearDetails setHSPF1AugustStartFullYearDetailsStatus(
		HSPF1AugustStartFullYearDetails hSPF1AugustStartFullYearDetails,
		String code, String type, int serviceCode, String message ) {
	   if(hSPF1AugustStartFullYearDetails==null) hSPF1AugustStartFullYearDetails = new HSPF1AugustStartFullYearDetails(); 
	   hSPF1AugustStartFullYearDetails.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return hSPF1AugustStartFullYearDetails;
}

   /**
    * 
    * @param HSPF1JanuaryStart2NdSemesterDetails
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return hSPF1JanuaryStart2NdSemesterDetails
    */
   private HSPF1JanuaryStart2NdSemesterDetails setHSPF1JanuaryStart2NdSemesterDetailsStatus(
		HSPF1JanuaryStart2NdSemesterDetails hSPF1JanuaryStart2NdSemesterDetails,
		String code, String type, int serviceCode, String message ) {
	
	   if(hSPF1JanuaryStart2NdSemesterDetails==null) hSPF1JanuaryStart2NdSemesterDetails = new HSPF1JanuaryStart2NdSemesterDetails(); 
	   hSPF1JanuaryStart2NdSemesterDetails.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	return hSPF1JanuaryStart2NdSemesterDetails;
}
   /**
    * 
    * @param SeasonHSPF1Details
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return seasonHSPF1Details
    */
   private SeasonHSPF1Details setseasonHSPF1DetailsStatus(
		SeasonHSPF1Details seasonHSPF1Details,String code, String type, int serviceCode, String message ) {
	   if(seasonHSPF1Details==null) seasonHSPF1Details = new SeasonHSPF1Details(); 
	   seasonHSPF1Details.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	return seasonHSPF1Details;
}
   /**
    * 
    * @param HSPF1Accounting
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return hSPF1Accounting
    */
   private HSPF1Accounting setHSPF1AccountingStatus(HSPF1Accounting hSPF1Accounting, String code, String type, int serviceCode, String message ) {
	   if(hSPF1Accounting==null) hSPF1Accounting = new HSPF1Accounting(); 
	   hSPF1Accounting.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	return hSPF1Accounting;
}
   /**
    * 
    * @param HSPF1JanuaryStartFullYearDetail
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return hSPF1JanuaryStartFullYearDetail
    */
   private HSPF1JanuaryStartFullYearDetail setHSPF1JanuaryStartFullYearDetailStatus(
		HSPF1JanuaryStartFullYearDetail hSPF1JanuaryStartFullYearDetail,
		String code, String type, int serviceCode, String message ) {
	   if(hSPF1JanuaryStartFullYearDetail==null) hSPF1JanuaryStartFullYearDetail = new HSPF1JanuaryStartFullYearDetail(); 
	   hSPF1JanuaryStartFullYearDetail.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	return hSPF1JanuaryStartFullYearDetail;
}
   /**
    * 
    * @param HSPF1BasicDetails
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return hSPF1BasicDetails
    */
   private HSPF1BasicDetails setHSPF1BasicDetailsStatus(
		HSPF1BasicDetails hSPF1BasicDetails, String code, String type, int serviceCode, String message ) {
		   if(hSPF1BasicDetails==null) hSPF1BasicDetails = new HSPF1BasicDetails(); 
		   hSPF1BasicDetails.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
		return hSPF1BasicDetails;
}
   /**
    * 
    * @param SeasonGHTDetails
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return seasonGHTDetails
    */
   private SeasonGHTDetails setSeasonGHTDetailsStatus(SeasonGHTDetails seasonGHTDetails, String code, String type, int serviceCode, String message ) {
	   if(seasonGHTDetails==null) seasonGHTDetails = new SeasonGHTDetails(); 
	   seasonGHTDetails.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
		return seasonGHTDetails;
	}

   /**
    * 
    * @param GHTSection1Base
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return ghtSection1Base
    */
   private GHTSection1Base setGHTSection1BaseStatus(GHTSection1Base ghtSection1Base, String code, String type, int serviceCode, String message ) {
	   if(ghtSection1Base==null) ghtSection1Base = new GHTSection1Base(); 
	   ghtSection1Base.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
		return ghtSection1Base;
}
   /**
    * 
    * @param GHTSection2Dates
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return ghtSection2Dates
    */

   private GHTSection2Dates setGHTSection2DatesStatus(GHTSection2Dates ghtSection2Dates, String code, String type, int serviceCode, String message ) {
	   if(ghtSection2Dates==null) ghtSection2Dates = new GHTSection2Dates(); 
	   ghtSection2Dates.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	return ghtSection2Dates;
}
   /**
    * 
    * @param SeasonWPDetails
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return seasonWPDetails
    */
   private SeasonWPDetails setSeasonWPDetailsStatus(
		SeasonWPDetails seasonWPDetails, String code, String type, int serviceCode, String message ) {
	   if(seasonWPDetails==null) seasonWPDetails = new SeasonWPDetails(); 
	   seasonWPDetails.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	return seasonWPDetails;
}
   /**
    * 
    * @param WPBasicDetail
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return wpBasicDetail
    */
   private WPBasicDetail setWPBasicDetailStatus(WPBasicDetail wpBasicDetail, String code, String type, int serviceCode, String message ) {
	   if(wpBasicDetail==null) wpBasicDetail = new WPBasicDetail(); 
	   wpBasicDetail.setStatus(componentUtils.getStatus(code, type, serviceCode, message));

	return wpBasicDetail;
}

   /**
    * 
    * @param WPSectionOne
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return wpSectionOne
    */
   private WPSectionOne setWPSectionOneStatus(WPSectionOne wpSectionOne, String code, String type, int serviceCode, String message ) {
	   if(wpSectionOne==null) wpSectionOne = new WPSectionOne(); 
	   wpSectionOne.setStatus(componentUtils.getStatus(code, type, serviceCode, message));

	return wpSectionOne;
}

   /**
    * 
    * @param WPProgramAllocations
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return wpProgramAllocations
    */
   private WPProgramAllocations setWPProgramAllocationsStatus(WPProgramAllocations wpProgramAllocations, String code, String type, int serviceCode, String message ) {
	   if(wpProgramAllocations==null) wpProgramAllocations = new WPProgramAllocations(); 
	   wpProgramAllocations.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	return wpProgramAllocations;
}
   /**
    * 
    * @param SeasonDepartmentNotes
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return returnObject
    */
   private SeasonDepartmentNotes setSeasonDepartmentNotesStatus(SeasonDepartmentNotes returnObject, String code, String type, int serviceCode, String message ) {
	   if(returnObject==null) returnObject = new SeasonDepartmentNotes(); 
	   returnObject.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	return returnObject;
}
   /*private com.ccighgo.service.transport.season.beans.seasondepartdoc.SeasonDepartmentDocument setSeasonDepartmentDocumentStatus(
			com.ccighgo.service.transport.season.beans.seasondepartdoc.SeasonDepartmentDocument returnObject,
			String code, String type, int serviceCode, String message ) {
	   if(returnObject==null) returnObject = new com.ccighgo.service.transport.season.beans.seasondepartdoc.SeasonDepartmentDocument(); 
	   returnObject.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
		return returnObject;
	}*/
   /**
    * 
    * @param SeasonProgramNote
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return returnObject
    */

   private SeasonProgramNote setSeasonProgramNoteStatus(
		SeasonProgramNote returnObject, String code, String type, int serviceCode, String message ) {
	   if(returnObject==null) returnObject = new SeasonProgramNote(); 
	   returnObject.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	return returnObject;
}
   /**
    * 
    * @param SeasonProgramDocument
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return returnObject
    */
   private SeasonProgramDocument setSeasonProgramDocumentStatus(
		SeasonProgramDocument returnObject, String code, String type, int serviceCode, String message ) {
	   if(returnObject==null) returnObject = new SeasonProgramDocument(); 
	   returnObject.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	return returnObject;
}
   /**
    * 
    * @param WPCAPProgramAllocations
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return wpcapProgramAllocations
    */
   private WPCAPProgramAllocations setWPCAPProgramAllocationsStatus(
			WPCAPProgramAllocations wpcapProgramAllocations,String code, String type, int serviceCode, String message ) {
	   if(wpcapProgramAllocations==null) wpcapProgramAllocations = new WPCAPProgramAllocations(); 
	   wpcapProgramAllocations.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
		return wpcapProgramAllocations;
	}

   
}
