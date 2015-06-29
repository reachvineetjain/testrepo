package com.ccighgo.service.components.season;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.LookupDepartment;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonF1Detail;
import com.ccighgo.db.entities.SeasonHSADetail;
import com.ccighgo.db.entities.SeasonJ1Detail;
import com.ccighgo.db.entities.SeasonLSDetail;
import com.ccighgo.db.entities.SeasonTADetail;
import com.ccighgo.db.entities.SeasonVADetail;
import com.ccighgo.db.entities.SeasonWADetail;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.jpa.repositories.SeasonF1DetailsRepository;
import com.ccighgo.jpa.repositories.SeasonHSADetailsRepository;
import com.ccighgo.jpa.repositories.SeasonJ1DetailsRepository;
import com.ccighgo.jpa.repositories.SeasonLSDetailsRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.jpa.repositories.SeasonStatusRepository;
import com.ccighgo.jpa.repositories.SeasonTADetailsRepository;
import com.ccighgo.jpa.repositories.SeasonVADetailsRepository;
import com.ccighgo.jpa.repositories.SeasonWADetailsRepository;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection1Base;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection2Dates;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.SeasonGHTDetails;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSAugStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSFieldSettings;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSJanStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSProgramAllocations;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.SeasonHspJ1HSDetails;
import com.ccighgo.service.transport.season.beans.seasonprogram.SeasonProgram;
import com.ccighgo.service.transport.season.beans.seasonprogram.SeasonPrograms;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.SeasonWPDetails;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.WPBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.WPSectionOne;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
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
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPTraineeDetails;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
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
   SeasonRepositoryCustomImpl seasonRepositoryService;
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

   SeasonServiceInterfaceImpl() {
   }

   @Override
   public SeasonsList getAllSeasons() {
      try {
         List<Season> allseasons = seasonRepository.getAllSeasons();
         SeasonsList seasonsList = new SeasonsList();
         if (allseasons != null && !(allseasons.isEmpty())) {
            seasonsList.setRecordCount(allseasons.size());
            for (int i = 0; i < allseasons.size(); i++) {
               SeasonListObject seasonBean = new SeasonListObject();
               Season seasonEntity = allseasons.get(i);
               seasonServiceImplUtil.convertEntitySeasonToSeasonListObject(seasonBean, seasonEntity);
               seasonsList.getSeasons().add(seasonBean);
            }
         }
         return seasonsList;
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public SeasonBean createSeason(SeasonBean seasonBean) {
      try {
         Season seasonEntity = new Season();
         seasonServiceImplUtil.convertSeasonBeanToSeasonEntity(seasonBean, seasonEntity, false);
         seasonEntity = seasonRepository.saveAndFlush(seasonEntity);
         seasonServiceImplUtil.createSeasonHspConfiguration(seasonBean, seasonEntity);
         seasonServiceImplUtil.createSeasonPrograms(seasonEntity, seasonBean);
         return viewSeason(seasonEntity.getSeasonId() + CCIConstants.EMPTY_DATA);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public String deleteSeason(String id) {
      Season seasonEntity = seasonRepository.findOne(Integer.parseInt(id));
      if (seasonEntity != null) {
         // seasonEntity.setActive(CCIConstants.INACTIVE);
         seasonRepository.saveAndFlush(seasonEntity);
         return "Season Deactivated";
      }
      return "Error Deactivating Season";
   }

   @Override
   public SeasonBean editSeason(String id) {
      return viewSeason(id);
   }

   @Override
   public SeasonBean viewSeason(String id) {
      ValidationUtils.isValidSeasonId(id);
      try {
         Season seasonEntity = seasonRepository.findOne(Integer.parseInt(id));
         if (seasonEntity != null) {
            SeasonBean seasonBean = new SeasonBean();
            seasonServiceImplUtil.convertEntitySeasonToBeanSeason(seasonBean, seasonEntity);
            return seasonBean;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public SeasonBean updateSeason(SeasonBean seasonBean) {
      try {
         Season seasonEntity = new Season();
         seasonServiceImplUtil.convertSeasonBeanToSeasonEntity(seasonBean, seasonEntity, true);
         seasonRepository.saveAndFlush(seasonEntity);
         seasonServiceImplUtil.updateSeasonHspConfiguration(seasonBean, seasonEntity);
         return viewSeason(seasonEntity.getSeasonId() + CCIConstants.EMPTY_DATA);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public SeasonPrograms getSeasonPrograms(String seasonId) {
      SeasonPrograms seasonPrograms = null;
      try {
         Season season = seasonRepository.findOne(Integer.valueOf(seasonId));
         if (season != null) {
            List<SeasonProgram> seasonProgramsList = new ArrayList<SeasonProgram>();
            seasonPrograms = new SeasonPrograms();
            LookupDepartment dept = season.getLookupDepartment();
            List<DepartmentProgram> departmentPrograms = dept.getDepartmentPrograms();
            for (DepartmentProgram dPrg : departmentPrograms) {
               SeasonProgram sprg = new SeasonProgram();
               // crapy code but no better option
               if (dPrg.getProgramName().equals(CCIConstants.HSP_J1_HS)) {
                  sprg.setSeasonId(Integer.valueOf(seasonId));
                  sprg.setProgramName(season.getSeasonName() + CCIConstants.HYPHEN_SPACE + dPrg.getProgramName());
                  sprg.setUrl(CCIConstants.HSP_J1_URL);
               }
               if (dPrg.getProgramName().equals(CCIConstants.HSP_F1)) {
                  sprg.setSeasonId(Integer.valueOf(seasonId));
                  sprg.setProgramName(season.getSeasonName() + CCIConstants.HYPHEN_SPACE + dPrg.getProgramName());
                  sprg.setUrl(CCIConstants.HSP_F1_URL);
               }
               if (dPrg.getProgramName().equals(CCIConstants.HSP_STP_IHP)) {
                  sprg.setSeasonId(Integer.valueOf(seasonId));
                  sprg.setProgramName(season.getSeasonName() + CCIConstants.HYPHEN_SPACE + dPrg.getProgramName());
                  sprg.setUrl(null);
               }
               if (dPrg.getProgramName().equals(CCIConstants.HSP_STP_GHP)) {
                  sprg.setSeasonId(Integer.valueOf(seasonId));
                  sprg.setProgramName(season.getSeasonName() + CCIConstants.HYPHEN_SPACE + dPrg.getProgramName());
                  sprg.setUrl(null);
               }
               if (dPrg.getProgramName().equals(CCIConstants.HSP_STP_SSE)) {
                  sprg.setSeasonId(Integer.valueOf(seasonId));
                  sprg.setProgramName(season.getSeasonName() + CCIConstants.HYPHEN_SPACE + dPrg.getProgramName());
                  sprg.setUrl(null);
               }
               if (dPrg.getProgramName().equals(CCIConstants.WP_WT_SUMMER)) {
                  sprg.setSeasonId(Integer.valueOf(seasonId));
                  sprg.setProgramName(season.getSeasonName() + CCIConstants.HYPHEN_SPACE + dPrg.getProgramName());
                  sprg.setUrl(null);
               }
               if (dPrg.getProgramName().equals(CCIConstants.WP_WT_WINTER)) {
                  sprg.setSeasonId(Integer.valueOf(seasonId));
                  sprg.setProgramName(season.getSeasonName() + CCIConstants.HYPHEN_SPACE + dPrg.getProgramName());
                  sprg.setUrl(null);
               }
               if (dPrg.getProgramName().equals(CCIConstants.WP_WT_SPRING)) {
                  sprg.setSeasonId(Integer.valueOf(seasonId));
                  sprg.setProgramName(season.getSeasonName() + CCIConstants.HYPHEN_SPACE + dPrg.getProgramName());
                  sprg.setUrl(null);
               }
               if (dPrg.getProgramName().equals(CCIConstants.WP_WT_CAP)) {
                  sprg.setSeasonId(Integer.valueOf(seasonId));
                  sprg.setProgramName(season.getSeasonName() + CCIConstants.HYPHEN_SPACE + dPrg.getProgramName());
                  sprg.setUrl(null);
               }
               if (dPrg.getProgramName().equals(CCIConstants.GHT_HS_ABRD)) {
                  sprg.setSeasonId(Integer.valueOf(seasonId));
                  sprg.setProgramName(season.getSeasonName() + CCIConstants.HYPHEN_SPACE + dPrg.getProgramName());
                  sprg.setUrl(null);
               }
               if (dPrg.getProgramName().equals(CCIConstants.GHT_LANG_SCL)) {
                  sprg.setSeasonId(Integer.valueOf(seasonId));
                  sprg.setProgramName(season.getSeasonName() + CCIConstants.HYPHEN_SPACE + dPrg.getProgramName());
                  sprg.setUrl(null);
               }
               if (dPrg.getProgramName().equals(CCIConstants.GHT_TEACH_ABRD)) {
                  sprg.setSeasonId(Integer.valueOf(seasonId));
                  sprg.setProgramName(season.getSeasonName() + CCIConstants.HYPHEN_SPACE + dPrg.getProgramName());
                  sprg.setUrl(null);
               }
               if (dPrg.getProgramName().equals(CCIConstants.GHT_VOL_ABRD)) {
                  sprg.setSeasonId(Integer.valueOf(seasonId));
                  sprg.setProgramName(season.getSeasonName() + CCIConstants.HYPHEN_SPACE + dPrg.getProgramName());
                  sprg.setUrl(CCIConstants.GHT_VA_URL);
               }
               if (dPrg.getProgramName().equals(CCIConstants.GHT_WRK_ABRD)) {
                  sprg.setSeasonId(Integer.valueOf(seasonId));
                  sprg.setProgramName(season.getSeasonName() + CCIConstants.HYPHEN_SPACE + dPrg.getProgramName());
                  sprg.setUrl(CCIConstants.GHT_WA_URL);
               }
               seasonProgramsList.add(sprg);
            }
            seasonPrograms.getSeasonPrograms().addAll(seasonProgramsList);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return seasonPrograms;
   }

   public SeasonStatuses getSeasonStatus() {
      SeasonStatuses seasonStatuses = null;
      try {
         LOGGER.info("SeasonStatus: fetch");
         seasonStatuses = seasonServiceImplUtil.getSeasonStatus();
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return seasonStatuses;
   }

   public SeasonHspJ1HSDetails getHSPJ1HSSeasonDetails(String seasonId) {
      SeasonHspJ1HSDetails seasonHspJ1HSDetails = null;
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findJ1DetailsBySeasonId(Integer.valueOf(seasonId));
         if (seasonJ1Detail != null) {
            seasonHspJ1HSDetails = new SeasonHspJ1HSDetails();
            seasonHspJ1HSDetails.setSeasonId(seasonJ1Detail.getSeason().getSeasonId());
            seasonHspJ1HSDetails.setJ1HsBasicDetail(seasonServiceImplUtil.getJ1HSBasicDetail(seasonJ1Detail));
            seasonHspJ1HSDetails.setJ1HsJanStart(seasonServiceImplUtil.getJ1HSJanStart(seasonJ1Detail));
            seasonHspJ1HSDetails.setJ1HsAugStart(seasonServiceImplUtil.getJ1HSAugStart(seasonJ1Detail));
            seasonHspJ1HSDetails.setJ1HsFieldSettings(seasonServiceImplUtil.getJ1HSFieldSettings(seasonJ1Detail));
            seasonHspJ1HSDetails.setJ1HsProgramAllocations(null);
            // TODO add document and notes
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return seasonHspJ1HSDetails;
   }

   public J1HSBasicDetail getHSPJ1HSSeasonNameAndStatus(String seasonId) {
      J1HSBasicDetail j1hsBasicDetail = null;
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findJ1DetailsBySeasonId(Integer.valueOf(seasonId));
         if (seasonJ1Detail != null) {
            j1hsBasicDetail = seasonServiceImplUtil.getJ1HSBasicDetail(seasonJ1Detail);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return j1hsBasicDetail;
   }

   public J1HSJanStart getHSPJ1HSSeasonJanStartDetails(String seasonId) {
      J1HSJanStart j1hsJanStart = null;
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findJ1DetailsBySeasonId(Integer.valueOf(seasonId));
         if (seasonJ1Detail != null) {
            j1hsJanStart = seasonServiceImplUtil.getJ1HSJanStart(seasonJ1Detail);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return j1hsJanStart;
   }

   public J1HSAugStart getHSPJ1HSSeasonAugStartDetails(String seasonId) {
      J1HSAugStart j1hsAugStart = null;
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findJ1DetailsBySeasonId(Integer.valueOf(seasonId));
         if (seasonJ1Detail != null) {
            j1hsAugStart = seasonServiceImplUtil.getJ1HSAugStart(seasonJ1Detail);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return j1hsAugStart;
   }

   public J1HSFieldSettings getHSPJ1HSSeasonFieldSettings(String seasonId) {
      J1HSFieldSettings j1hsFieldSettings = null;
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findJ1DetailsBySeasonId(Integer.valueOf(seasonId));
         if (seasonJ1Detail != null) {
            j1hsFieldSettings = seasonServiceImplUtil.getJ1HSFieldSettings(seasonJ1Detail);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return j1hsFieldSettings;
   }

   public J1HSProgramAllocations getHSPJ1HSSeasonProgramAllocation(String seasonId) {
      J1HSProgramAllocations j1hsProgramAllocations = null;

      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findJ1DetailsBySeasonId(Integer.valueOf(seasonId));
         if (seasonJ1Detail != null) {
            j1hsProgramAllocations = new J1HSProgramAllocations();
            // TODO iterate allocations once the accurate values are
            // populated
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return j1hsProgramAllocations;
   }

   @Override
   public String cloneSeason(String id, String newSeasonName) {
      try {
         return seasonRepositoryService.cloneSeason(id, newSeasonName);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public void customService(String id) {
      seasonRepositoryService.findSeasonByName("CAP-2010");
      // seasonRepositoryService.findSeasonByDepartment("1");
      System.out.println("Line for Debug");
   }

   @Override
   public SeasonHspJ1HSDetails updateHSPJ1HSSeasonDetails(SeasonHspJ1HSDetails seasonHspJ1HSDetails) {
      SeasonHspJ1HSDetails returnObject = null;
      if (seasonHspJ1HSDetails == null) {
         return returnObject;
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findJ1DetailsBySeasonId(Integer.valueOf(seasonHspJ1HSDetails.getSeasonId()));
         if (seasonJ1Detail != null) {
            if (seasonHspJ1HSDetails.getJ1HsBasicDetail() != null) {
               seasonServiceImplUtil.updateJ1BasicDetails(seasonHspJ1HSDetails.getJ1HsBasicDetail(), seasonJ1Detail);
            }
            if (seasonHspJ1HSDetails.getJ1HsJanStart() != null) {
               seasonServiceImplUtil.updateJ1JanStartDetails(seasonHspJ1HSDetails.getJ1HsJanStart(), seasonJ1Detail);
            }
            if (seasonHspJ1HSDetails.getJ1HsAugStart() != null) {
               seasonServiceImplUtil.updateJ1AugStartDetails(seasonHspJ1HSDetails.getJ1HsAugStart(), seasonJ1Detail);
            }
            if (seasonHspJ1HSDetails.getJ1HsFieldSettings() != null) {
               seasonServiceImplUtil.updateJ1FSSettings(seasonHspJ1HSDetails.getJ1HsFieldSettings(), seasonJ1Detail);
            }
            seasonJ1Detail = seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
            returnObject = seasonHspJ1HSDetails;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   @Override
   public J1HSBasicDetail updateHSPJ1HSSeasonNameAndStatus(J1HSBasicDetail j1hsBasicDetail) {
      J1HSBasicDetail returnObject = null;
      if (j1hsBasicDetail == null || j1hsBasicDetail.getSeasonId() == 0) {
         return returnObject;
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findJ1DetailsBySeasonId(Integer.valueOf(j1hsBasicDetail.getSeasonId()));
         if (seasonJ1Detail != null) {
            seasonServiceImplUtil.updateJ1BasicDetails(j1hsBasicDetail, seasonJ1Detail);
            seasonJ1Detail = seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
            returnObject = j1hsBasicDetail;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   @Override
   public J1HSJanStart updateHSPJ1HSSeasonJanStartDetails(J1HSJanStart j1hsJanStart) {
      J1HSJanStart returnObject = null;
      if (j1hsJanStart == null) {
         // throw exception
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findJ1DetailsBySeasonId(Integer.valueOf(j1hsJanStart.getSeasonId()));
         if (seasonJ1Detail != null) {
            seasonServiceImplUtil.updateJ1JanStartDetails(j1hsJanStart, seasonJ1Detail);
            seasonJ1Detail = seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
            returnObject = j1hsJanStart;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   @Override
   public J1HSAugStart updateHSPJ1HSSeasonAugStartDetails(J1HSAugStart j1hsAugStart) {
      J1HSAugStart returnObject = null;
      if (j1hsAugStart == null) {
         // throw exception
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findJ1DetailsBySeasonId(Integer.valueOf(j1hsAugStart.getSeasonId()));
         if (seasonJ1Detail != null) {
            seasonServiceImplUtil.updateJ1AugStartDetails(j1hsAugStart, seasonJ1Detail);
            seasonJ1Detail = seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
            returnObject = j1hsAugStart;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   @Override
   public J1HSFieldSettings updateHSPJ1HSSeasonFieldSettings(J1HSFieldSettings j1hsFieldSettings) {
      J1HSFieldSettings returnObject = null;
      if (j1hsFieldSettings == null) {
         // throw exception
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findJ1DetailsBySeasonId(Integer.valueOf(j1hsFieldSettings.getSeasonId()));
         if (seasonJ1Detail != null) {
            seasonServiceImplUtil.updateJ1FSSettings(j1hsFieldSettings, seasonJ1Detail);
            seasonJ1Detail = seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
            returnObject = j1hsFieldSettings;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   @Override
   public J1HSProgramAllocations updateHSPJ1HSSeasonProgramAllocation(J1HSProgramAllocations j1hsProgramAllocations) {
      return null;
   }

   public HSPF1ProgramAllocations getHSPF1ProgramAllocations(String seasonId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
         return seasonServiceImplUtil.getHSPF1ProgramAllocations(allF1Details, seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1FieldSettings getHSPF1FieldSettings(String seasonId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
         return seasonServiceImplUtil.getHSPF1FieldSettings(allF1Details, seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1AugustStart1StSemesterDetails getHSPF1AugustStart1StSemesterDetails(String seasonId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
         return seasonServiceImplUtil.getHSPF1AugustStart1StSemesterDetails(allF1Details, seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1AugustStartFullYearDetails getHSPF1AugustStartFullYearDetails(String seasonId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
         return seasonServiceImplUtil.getHSPF1AugustStartFullYearDetails(allF1Details, seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1JanuaryStart2NdSemesterDetails getHSPF1JanuaryStart2NdSemesterDetails(String seasonId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
         return seasonServiceImplUtil.getHSPF1JanuaryStart2NdSemesterDetails(allF1Details, seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1JanuaryStartFullYearDetail getHSPF1JanuaryStartFullYearDetails(String seasonId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
         return seasonServiceImplUtil.getHSPF1JanuaryStartFullYearDetails(allF1Details, seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1BasicDetails getHSPF1NameAndStatus(String seasonId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
         return seasonServiceImplUtil.getHSPF1NameAndStatus(allF1Details, seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1Accounting getHSPF1Accounting(String seasonId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
         return seasonServiceImplUtil.getHSPF1Accounting(allF1Details, seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public SeasonHSPF1Details getSeasonHSPF1Details(String seasonId) {
      try {
         SeasonHSPF1Details seasonHSPF1Details = new SeasonHSPF1Details();
         seasonHSPF1Details.setSeasonId(Integer.valueOf(seasonId));
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(Integer.valueOf(seasonId));
         seasonHSPF1Details.setDetails(seasonServiceImplUtil.getHSPF1NameAndStatus(allF1Details, seasonId));
         seasonHSPF1Details.setJanuaryStart2NdSemesterDetails(seasonServiceImplUtil.getHSPF1JanuaryStart2NdSemesterDetails(allF1Details, seasonId));
         seasonHSPF1Details.setJanuaryStartFullYearDetail(seasonServiceImplUtil.getHSPF1JanuaryStartFullYearDetails(allF1Details, seasonId));
         seasonHSPF1Details.setAugustStart1StSemesterDetails(seasonServiceImplUtil.getHSPF1AugustStart1StSemesterDetails(allF1Details, seasonId));
         seasonHSPF1Details.setAugustStartFullYearDetails(seasonServiceImplUtil.getHSPF1AugustStartFullYearDetails(allF1Details, seasonId));
         seasonHSPF1Details.setAccounting(seasonServiceImplUtil.getHSPF1Accounting(allF1Details, seasonId));
         seasonHSPF1Details.setFieldSettings(seasonServiceImplUtil.getHSPF1FieldSettings(allF1Details, seasonId));
         seasonHSPF1Details.setProgramAllocations(seasonServiceImplUtil.getHSPF1ProgramAllocations(allF1Details, seasonId));
         return seasonHSPF1Details;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public SeasonHSPF1Details updateF1Details(SeasonHSPF1Details seasonHSPF1Details) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(seasonHSPF1Details.getSeasonId());
         if (allF1Details != null) {
            SeasonHSPF1Details updatedSeasonHSPF1Details = seasonServiceImplUtil.updateF1Details(allF1Details, seasonHSPF1Details);
            seasonHSPF1Details = seasonServiceImplUtil.updateF1Details(allF1Details, seasonHSPF1Details);
         }

         return seasonHSPF1Details;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1BasicDetails updateHSPF1NameAndStatus(HSPF1BasicDetails hspf1BasicDetails) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(hspf1BasicDetails.getSeasonId());
         if (allF1Details != null) {
            hspf1BasicDetails = seasonServiceImplUtil.updateHSPF1NameAndStatus(allF1Details, hspf1BasicDetails);
         }
         return hspf1BasicDetails;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1Accounting updateF1Accounting(HSPF1Accounting hspf1Accounting) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(hspf1Accounting.getSeasonId());
         if (allF1Details != null) {
            hspf1Accounting = seasonServiceImplUtil.updateF1Accounting(allF1Details, hspf1Accounting);
         }
         return hspf1Accounting;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1JanuaryStart2NdSemesterDetails updateF1JanStart2NdSemesterDetails(HSPF1JanuaryStart2NdSemesterDetails hspf1JanuaryStart2NdSemesterDetails) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(hspf1JanuaryStart2NdSemesterDetails.getSeasonId());
         if (allF1Details != null) {
            hspf1JanuaryStart2NdSemesterDetails = seasonServiceImplUtil.updateF1JanStart2NdSemesterDetails(allF1Details, hspf1JanuaryStart2NdSemesterDetails);
         }
         return hspf1JanuaryStart2NdSemesterDetails;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1JanuaryStartFullYearDetail updateF1JanStartFullYearDetails(HSPF1JanuaryStartFullYearDetail hspf1JanuaryStartFullYearDetail) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(hspf1JanuaryStartFullYearDetail.getSeasonId());
         if (allF1Details != null) {
            hspf1JanuaryStartFullYearDetail = seasonServiceImplUtil.updateF1JanStartFullYearDetails(allF1Details, hspf1JanuaryStartFullYearDetail);
         }
         return hspf1JanuaryStartFullYearDetail;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1AugustStart1StSemesterDetails updateF1AugStart1StSemesterDetails(HSPF1AugustStart1StSemesterDetails hspf1AugustStart1StSemesterDetails) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(hspf1AugustStart1StSemesterDetails.getSeasonId());
         if (allF1Details != null) {
            hspf1AugustStart1StSemesterDetails = seasonServiceImplUtil.updateF1AugStart1StSemesterDetails(allF1Details, hspf1AugustStart1StSemesterDetails);
         }
         return hspf1AugustStart1StSemesterDetails;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1AugustStartFullYearDetails updateF1AugStartFullYearDetails(HSPF1AugustStartFullYearDetails hspf1AugustStartFullYearDetails) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(hspf1AugustStartFullYearDetails.getSeasonId());
         if (allF1Details != null) {
            hspf1AugustStartFullYearDetails = seasonServiceImplUtil.updateF1AugStartFullYearDetails(allF1Details, hspf1AugustStartFullYearDetails);
         }
         return hspf1AugustStartFullYearDetails;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1FieldSettings updateF1FieldSettings(HSPF1FieldSettings hspf1FieldSettings) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(hspf1FieldSettings.getSeasonId());
         if (allF1Details != null) {
            hspf1FieldSettings = seasonServiceImplUtil.updateF1FieldSettings(allF1Details, hspf1FieldSettings);
         }
         return hspf1FieldSettings;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1ProgramAllocations updateF1ProgramAllocation(HSPF1ProgramAllocations hspf1ProgramAllocations) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.getAllSeasonF1DetailById(hspf1ProgramAllocations.getSeasonId());
         if (allF1Details != null) {
            hspf1ProgramAllocations = seasonServiceImplUtil.updateF1ProgramAllocation(allF1Details, hspf1ProgramAllocations);
         }
         return hspf1ProgramAllocations;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   // view, edit GHT Volunteer Abroad season details

   public SeasonGHTDetails getGHTVASeasonDetails(String seasonId) {
      SeasonGHTDetails seasonGHTDetails = null;
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findGHTVADetailsBySeasonId(Integer.valueOf(seasonId));
         if (seasonVADetail != null) {
            seasonGHTDetails = new SeasonGHTDetails();
            seasonGHTDetails.setSeasonId(seasonVADetail.getSeason().getSeasonId());
            seasonGHTDetails.setGhtBaseDetails(seasonServiceImplUtil.getVABasicDetail(seasonVADetail));
            seasonGHTDetails.setGhtDates(seasonServiceImplUtil.getVADates(seasonVADetail));
            // TODO add notes
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return seasonGHTDetails;
   }

   public GHTSection1Base getGHTVASeasonNameAndStatus(String seasonId) {
      GHTSection1Base ghtSection1Base = null;
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findGHTVADetailsBySeasonId(Integer.valueOf(seasonId));
         if (seasonVADetail != null) {
            ghtSection1Base = seasonServiceImplUtil.getVABasicDetail(seasonVADetail);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return ghtSection1Base;
   }

   public GHTSection2Dates getGHTVASeasonDateDetails(String seasonId) {
      GHTSection2Dates ghtSection2Dates = null;
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findGHTVADetailsBySeasonId(Integer.valueOf(seasonId));
         if (seasonVADetail != null) {
            ghtSection2Dates = seasonServiceImplUtil.getVADates(seasonVADetail);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return ghtSection2Dates;
   }

   // update GHT Volunteer Abroad season details

   public SeasonGHTDetails updateGHTVASeasonDetails(SeasonGHTDetails seasonGHTDetails) {
      SeasonGHTDetails returnObject = null;
      if (seasonGHTDetails == null) {
         return returnObject;
      }
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findGHTVADetailsBySeasonId(Integer.valueOf(seasonGHTDetails.getSeasonId()));
         if (seasonVADetail != null) {
            if (seasonGHTDetails.getGhtBaseDetails() != null) {
               seasonServiceImplUtil.updateVABasicDetails(seasonGHTDetails.getGhtBaseDetails(), seasonVADetail);
            }
            if (seasonGHTDetails.getGhtDates() != null) {
               seasonServiceImplUtil.updateVADates(seasonGHTDetails.getGhtDates(), seasonVADetail);
            }
            seasonVADetail = seasonVADetailsRepository.saveAndFlush(seasonVADetail);
            returnObject = seasonGHTDetails;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   public GHTSection1Base updateGHTVASeasonNameAndStatus(GHTSection1Base ghtSection1Base) {
      GHTSection1Base returnObject = null;
      if (ghtSection1Base == null || ghtSection1Base.getSeasonId() == 0) {
         return returnObject;
      }
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findGHTVADetailsBySeasonId(Integer.valueOf(ghtSection1Base.getSeasonId()));
         if (seasonVADetail != null) {
            seasonServiceImplUtil.updateVABasicDetails(ghtSection1Base, seasonVADetail);
            seasonVADetail = seasonVADetailsRepository.saveAndFlush(seasonVADetail);
            returnObject = ghtSection1Base;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   public GHTSection2Dates updateGHTVASeasonDateDetails(GHTSection2Dates ghtSection2Dates) {
      GHTSection2Dates returnObject = null;
      if (ghtSection2Dates == null || ghtSection2Dates.getSeasonId() == 0) {
         return returnObject;
      }
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findGHTVADetailsBySeasonId(Integer.valueOf(ghtSection2Dates.getSeasonId()));
         if (seasonVADetail != null) {
            seasonServiceImplUtil.updateVADates(ghtSection2Dates, seasonVADetail);
            seasonVADetail = seasonVADetailsRepository.saveAndFlush(seasonVADetail);
            returnObject = ghtSection2Dates;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   // view, edit GHT Work Abroad season details

   public SeasonGHTDetails getGHTWASeasonDetails(String seasonId) {
      SeasonGHTDetails seasonGHTDetails = null;
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findGHTWADetailsBySeasonId(Integer.valueOf(seasonId));
         if (seasonWADetail != null) {
            seasonGHTDetails = new SeasonGHTDetails();
            seasonGHTDetails.setSeasonId(seasonWADetail.getSeason().getSeasonId());
            seasonGHTDetails.setGhtBaseDetails(seasonServiceImplUtil.getWABasicDetail(seasonWADetail));
            seasonGHTDetails.setGhtDates(seasonServiceImplUtil.getWADates(seasonWADetail));
            // TODO add notes
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return seasonGHTDetails;
   }

   public GHTSection1Base getGHTWASeasonNameAndStatus(String seasonId) {
      GHTSection1Base ghtSection1Base = null;
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findGHTWADetailsBySeasonId(Integer.valueOf(seasonId));
         if (seasonWADetail != null) {
            ghtSection1Base = seasonServiceImplUtil.getWABasicDetail(seasonWADetail);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return ghtSection1Base;
   }

   public GHTSection2Dates getGHTWASeasonDateDetails(String seasonId) {
      GHTSection2Dates ghtSection2Dates = null;
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findGHTWADetailsBySeasonId(Integer.valueOf(seasonId));
         if (seasonWADetail != null) {
            ghtSection2Dates = seasonServiceImplUtil.getWADates(seasonWADetail);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return ghtSection2Dates;
   }

   // update GHT Work Abroad season details

   public SeasonGHTDetails updateGHTWASeasonDetails(SeasonGHTDetails seasonGHTDetails) {
      SeasonGHTDetails returnObject = null;
      if (seasonGHTDetails == null) {
         return returnObject;
      }
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findGHTWADetailsBySeasonId(Integer.valueOf(seasonGHTDetails.getSeasonId()));
         if (seasonWADetail != null) {
            if (seasonGHTDetails.getGhtBaseDetails() != null) {
               seasonServiceImplUtil.updateWABasicDetails(seasonGHTDetails.getGhtBaseDetails(), seasonWADetail);
            }
            if (seasonGHTDetails.getGhtDates() != null) {
               seasonServiceImplUtil.updateWADates(seasonGHTDetails.getGhtDates(), seasonWADetail);
            }
            seasonWADetail = seasonWADetailsRepository.saveAndFlush(seasonWADetail);
            returnObject = seasonGHTDetails;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   public GHTSection1Base updateGHTWASeasonNameAndStatus(GHTSection1Base ghtSection1Base) {
      GHTSection1Base returnObject = null;
      if (ghtSection1Base == null || ghtSection1Base.getSeasonId() == 0) {
         return returnObject;
      }
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findGHTWADetailsBySeasonId(Integer.valueOf(ghtSection1Base.getSeasonId()));
         if (seasonWADetail != null) {
            seasonServiceImplUtil.updateWABasicDetails(ghtSection1Base, seasonWADetail);
            seasonWADetail = seasonWADetailsRepository.saveAndFlush(seasonWADetail);
            returnObject = ghtSection1Base;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   public GHTSection2Dates updateGHTWASeasonDateDetails(GHTSection2Dates ghtSection2Dates) {
      GHTSection2Dates returnObject = null;
      if (ghtSection2Dates == null || ghtSection2Dates.getSeasonId() == 0) {
         return returnObject;
      }
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findGHTWADetailsBySeasonId(Integer.valueOf(ghtSection2Dates.getSeasonId()));
         if (seasonWADetail != null) {
            seasonServiceImplUtil.updateWADates(ghtSection2Dates, seasonWADetail);
            seasonWADetail = seasonWADetailsRepository.saveAndFlush(seasonWADetail);
            returnObject = ghtSection2Dates;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   public SeasonGHTDetails getGHTHSAbroad(String seasonId) {
      try {
         SeasonHSADetail seasonHSADetail = seasonHSADetailsRepository.findGHTHSBySeasonId(Integer.parseInt(seasonId));
         if (seasonHSADetail == null) {
            return null;
         }
         return seasonServiceImplUtil.getGHTHSAbroad(seasonHSADetail);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public SeasonGHTDetails updateGHTHSAbroad(SeasonGHTDetails seasonGHTDetails) {
      try {
         return seasonServiceImplUtil.updateGHTHSAbroad(seasonGHTDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public SeasonGHTDetails getGHTLanguageSchool(String seasonId) {
      try {
         SeasonLSDetail seasonLSDetail = seasonLSDetailsRepository.findGHTLSBySeasonId(Integer.parseInt(seasonId));
         if (seasonLSDetail == null) {
            return null;
         }
         return seasonServiceImplUtil.getGHTLanguageSchool(seasonLSDetail);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public SeasonGHTDetails updateGHTLanguageSchool(SeasonGHTDetails seasonGHTDetails) {
      try {
         return seasonServiceImplUtil.updateGHTLanguageSchool(seasonGHTDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public SeasonGHTDetails getGHTTeachAbroad(String seasonId) {
      try {
         SeasonTADetail seasonTADetail = seasonTADetailsRepository.findGHTTABySeasonId(Integer.parseInt(seasonId));
         if (seasonTADetail == null) {
            return null;
         }
         return seasonServiceImplUtil.getGHTTeachAbroad(seasonTADetail);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public SeasonGHTDetails updateGHTTeachAbroad(SeasonGHTDetails seasonGHTDetails) {
      try {
         return seasonServiceImplUtil.updateGHTTeachAbroad(seasonGHTDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection1Base getGHTHSSection1BaseAbroad(String seasonId) {
      try {
         return seasonServiceImplUtil.getGHTHSSection1BaseAbroad(seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection1Base updateGHTHSSection1BaseAbroad(GHTSection1Base ghtSection1Base) {
      try {
         return seasonServiceImplUtil.updateGHTHSSection1BaseAbroad(ghtSection1Base);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection2Dates getGHTHSSection2DatesAbroad(String seasonId) {
      try {
         return seasonServiceImplUtil.getGHTHSSection2DatesAbroad(seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection2Dates updateGHTHSSection2DatesAbroad(GHTSection2Dates ghtSection2Dates) {
      try {
         return seasonServiceImplUtil.updateGHTHSSection2DatesAbroad(ghtSection2Dates);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection1Base getGHTLanguageSchoolSection1(String seasonId) {
      try {
         return seasonServiceImplUtil.getGHTLanguageSchoolSection1(seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection1Base updateGHTLanguageSchoolSection1(GHTSection1Base ghtSection1Base) {
      try {
         return seasonServiceImplUtil.updateGHTLanguageSchoolSection1(ghtSection1Base);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection2Dates getGHTLanguageSchoolSection2Dates(String seasonId) {
      try {
         return seasonServiceImplUtil.getGHTLanguageSchoolSection2Dates(seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection2Dates updateGHTLanguageSchoolSection2Dates(GHTSection2Dates ghtSection2Dates) {
      try {
         return seasonServiceImplUtil.updateGHTLanguageSchoolSection2Dates(ghtSection2Dates);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection1Base getGHTTeachAbroadSection1(String seasonId) {
      try {
         return seasonServiceImplUtil.getGHTTeachAbroadSection1(seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection1Base updateGHTTeachAbroadSection1(GHTSection1Base ghtSection1Base) {
      try {
         return seasonServiceImplUtil.updateGHTTeachAbroadSection1(ghtSection1Base);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection2Dates getGHTTeachAbroadSection2Dates(String seasonId) {
      try {
         return seasonServiceImplUtil.getGHTTeachAbroadSection2Dates(seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection2Dates updateGHTTeachAbroadSection2Dates(GHTSection2Dates ghtSection2Dates) {
      try {
         return seasonServiceImplUtil.updateGHTTeachAbroadSection2Dates(ghtSection2Dates);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public SeasonWPCAPDetails getWPCAPDetails(String seasonId) {
      try {
         return seasonServiceImplUtil.getWPCAPDetails(seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public SeasonWPCAPDetails updateWPCAPDetails(SeasonWPCAPDetails seasonWPCAPDetails) {
      try {
         return seasonServiceImplUtil.updateWPCAPDetails(seasonWPCAPDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public WPCAPBasicDetails getWPCAPBasicDetails(String seasonId) {
      try {
         return seasonServiceImplUtil.getWPCAPBasicDetails(seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public WPCAPBasicDetails updateWPCAPBasicDetails(WPCAPBasicDetails wpcapBasicDetails) {
      try {
         return seasonServiceImplUtil.updateWPCAPBasicDetails(wpcapBasicDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public WPCAPInternshipDetails getWPCAPInternshipDetails(String seasonId) {
      try {
         return seasonServiceImplUtil.getWPCAPInternshipDetails(seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public WPCAPInternshipDetails updateWPCAPInternshipDetails(WPCAPInternshipDetails wpcapInternshipDetails) {
      try {
         return seasonServiceImplUtil.updateWPCAPInternshipDetails(wpcapInternshipDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public WPCAPTraineeDetails getWPCAPTraineeDetails(String seasonId) {
      try {
         return seasonServiceImplUtil.getWPCAPTraineeDetails(seasonId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public WPCAPTraineeDetails updateWPCAPTraineeDetails(WPCAPTraineeDetails wpcapTraineeDetails) {
      try {
         return seasonServiceImplUtil.updateWPCAPTraineeDetails(wpcapTraineeDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public SeasonWPDetails getWPSumDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public SeasonWPDetails editWPSumDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public SeasonWPDetails updateWPSumDetails(SeasonWPDetails seasonWPDetails) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPBasicDetail getWPSumBaseDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPBasicDetail editWPSumBaseDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPBasicDetail updateWPSumBaseDetails(WPBasicDetail wpBasicDetail) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne getWPSumSectionOneDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne editWPSumSectionOneDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne updateWPSumSectionOneDetails(WPSectionOne wpSectionOne) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne getWPSumAllocationDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne editWPSumAllocationDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne updateWPSumAllocationDetails(WPSectionOne wpSectionOne) {
      // TODO Auto-generated method stub
      return null;
   }

   public SeasonWPDetails getWPSpringDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public SeasonWPDetails editWPSpringDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public SeasonWPDetails updateWPSpringDetails(SeasonWPDetails seasonWPDetails) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPBasicDetail getWPSpringBaseDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPBasicDetail editWPSpringBaseDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPBasicDetail updateWPSpringBaseDetails(WPBasicDetail wpBasicDetail) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne getWPSpringSectionOneDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne editWPSpringSectionOneDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne updateWPSpringSectionOneDetails(WPSectionOne wpSectionOne) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne getWPSpringAllocationDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne editWPSpringAllocationDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne updateWPSpringAllocationDetails(WPSectionOne wpSectionOne) {
      // TODO Auto-generated method stub
      return null;
   }

   public SeasonWPDetails getWPWinterDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public SeasonWPDetails editWPWinterDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public SeasonWPDetails updateWPWinterDetails(SeasonWPDetails seasonWPDetails) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPBasicDetail getWPWinterBaseDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPBasicDetail editWPWinterBaseDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPBasicDetail updateWPWinterBaseDetails(WPBasicDetail wpBasicDetail) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne getWPWinterSectionOneDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne editWPWinterSectionOneDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne updateWPWinterSectionOneDetails(WPSectionOne wpSectionOne) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne getWPWinterAllocationDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne editWPWinterAllocationDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne updateWPWinterAllocationDetails(WPSectionOne wpSectionOne) {
      // TODO Auto-generated method stub
      return null;
   }

}
