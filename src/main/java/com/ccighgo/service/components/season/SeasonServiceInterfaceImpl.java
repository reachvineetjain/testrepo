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
import com.ccighgo.db.entities.SeasonJ1Detail;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.jpa.repositories.SeasonJ1DetailsRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.jpa.repositories.SeasonStatusRepository;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSAugStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSFieldSettings;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSJanStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSProgramAllocations;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.SeasonHspJ1HSDetails;
import com.ccighgo.service.transport.season.beans.seasonprogram.SeasonProgram;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonListObject;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.ValidationUtils;

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

   public SeasonProgram getSeasonPrograms(String seasonId) {
      SeasonProgram seasonProgram = null;
      try {
         Season season = seasonRepository.findOne(Integer.valueOf(seasonId));
         if (season != null) {
            List<String> seasonPrograms = new ArrayList<String>();
            seasonProgram = new SeasonProgram();
            LookupDepartment dept = season.getLookupDepartment();
            List<DepartmentProgram> departmentPrograms = dept.getDepartmentPrograms();
            for (DepartmentProgram dPrg : departmentPrograms) {
               String seasonPrg = season.getSeasonName() + CCIConstants.HYPHEN_SPACE + dPrg.getProgramName();
               seasonPrograms.add(seasonPrg);
            }
            seasonProgram.getSeasonPrograms().addAll(seasonPrograms);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return seasonProgram;
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
            seasonHspJ1HSDetails.setJ1HsBasicDetail(getJ1HSBasicDetail(seasonJ1Detail));
            seasonHspJ1HSDetails.setJ1HsJanStart(getJ1HSJanStart(seasonJ1Detail));
            seasonHspJ1HSDetails.setJ1HsAugStart(getJ1HSAugStart(seasonJ1Detail));
            seasonHspJ1HSDetails.setJ1HsFieldSettings(getJ1HSFieldSettings(seasonJ1Detail));
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
            j1hsBasicDetail = getJ1HSBasicDetail(seasonJ1Detail);
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
            j1hsJanStart = getJ1HSJanStart(seasonJ1Detail);
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
            j1hsAugStart = getJ1HSAugStart(seasonJ1Detail);
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
            j1hsFieldSettings = getJ1HSFieldSettings(seasonJ1Detail);
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
            // TODO iterate allocations once the accurate values are populated
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
               updateJ1BasicDetails(seasonHspJ1HSDetails.getJ1HsBasicDetail(), seasonJ1Detail);
            }
            if (seasonHspJ1HSDetails.getJ1HsJanStart() != null) {
               updateJ1JanStartDetails(seasonHspJ1HSDetails.getJ1HsJanStart(), seasonJ1Detail);
            }
            if (seasonHspJ1HSDetails.getJ1HsAugStart() != null) {
               updateJ1AugStartDetails(seasonHspJ1HSDetails.getJ1HsAugStart(), seasonJ1Detail);
            }
            if (seasonHspJ1HSDetails.getJ1HsFieldSettings() != null) {
               updateJ1FSSettings(seasonHspJ1HSDetails.getJ1HsFieldSettings(), seasonJ1Detail);
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
            updateJ1BasicDetails(j1hsBasicDetail, seasonJ1Detail);
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
            updateJ1JanStartDetails(j1hsJanStart, seasonJ1Detail);
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
            updateJ1AugStartDetails(j1hsAugStart, seasonJ1Detail);
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
            updateJ1FSSettings(j1hsFieldSettings, seasonJ1Detail);
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
   
   /**
    * @param seasonJ1Detail
    * @return
    */
   private J1HSBasicDetail getJ1HSBasicDetail(SeasonJ1Detail seasonJ1Detail) {
      J1HSBasicDetail j1hsBasicDetail;
      j1hsBasicDetail = new J1HSBasicDetail();
      j1hsBasicDetail.setSeasonId(seasonJ1Detail.getSeason().getSeasonId());
      j1hsBasicDetail.setProgramName(seasonJ1Detail.getProgramName());
      j1hsBasicDetail.setProgramStatus(seasonJ1Detail.getSeasonStatus().getStatus());
      return j1hsBasicDetail;
   }
   
      /**
    * @param seasonJ1Detail
    * @return
    */
   private J1HSJanStart getJ1HSJanStart(SeasonJ1Detail seasonJ1Detail) {
      J1HSJanStart j1hsJanStart;
      j1hsJanStart = new J1HSJanStart();
      j1hsJanStart.setSeasonId(seasonJ1Detail.getSeason().getSeasonId());
      j1hsJanStart.setJanSecondSemStartDate(DateUtils.getMMddyyDate(seasonJ1Detail.getSecondSemStartDate()));
      j1hsJanStart.setJanSecondSemEndDate(DateUtils.getMMddyyDate(seasonJ1Detail.getSecondSemEndDate()));
      j1hsJanStart.setJanSecondSemApplDeadlineDate(DateUtils.getMMddyyDate(seasonJ1Detail.getSecondSemAppDeadlineDate()));
      j1hsJanStart.setJanSecondSemEarliestBirthDate(DateUtils.getMMddyyDate(seasonJ1Detail.getSecondSemEarliestBirthDate()));
      j1hsJanStart.setJanSecondSemLatestDate(DateUtils.getMMddyyDate(seasonJ1Detail.getSecondSemLatestBirthDate()));
      j1hsJanStart.setShowJanSecondSemToNewHF(seasonJ1Detail.getShowSecondSemToNewHF() == CCIConstants.ACTIVE ? true : false);
      j1hsJanStart.setAtivateJanFullYearProgram(seasonJ1Detail.getActiveFullYearJanProgram() == CCIConstants.ACTIVE ? true : false);
      j1hsJanStart.setJanFullYrStartDate(DateUtils.getMMddyyDate(seasonJ1Detail.getJanFullYearStartDate()));
      j1hsJanStart.setJanFullYrEndDate(DateUtils.getMMddyyDate(seasonJ1Detail.getJanFullYearEndDate()));
      j1hsJanStart.setJanFullYrApplDeadlineDate(DateUtils.getMMddyyDate(seasonJ1Detail.getJanFullYearAppDeadlineDate()));
      j1hsJanStart.setShowJanFullYrToNewHF(seasonJ1Detail.getShowJanFullYearToNewHF() == CCIConstants.ACTIVE ? true : false);
      return j1hsJanStart;
   }
   
      /**
    * @param seasonJ1Detail
    * @return
    */
   private J1HSAugStart getJ1HSAugStart(SeasonJ1Detail seasonJ1Detail) {
      J1HSAugStart j1hsAugStart;
      j1hsAugStart = new J1HSAugStart();
      j1hsAugStart.setSeasonId(seasonJ1Detail.getSeason().getSeasonId());
      j1hsAugStart.setAugFirstSemStartDate(DateUtils.getMMddyyDate(seasonJ1Detail.getFirstSemStartDate()));
      j1hsAugStart.setAugFirstSemEndDate(DateUtils.getMMddyyDate(seasonJ1Detail.getFirstSemEndDate()));
      j1hsAugStart.setAugFirstSemApplDeadlineDate(DateUtils.getMMddyyDate(seasonJ1Detail.getFirstSemAppDeadlineDate()));
      j1hsAugStart.setAugFirstSemEarliestBirthDate(DateUtils.getMMddyyDate(seasonJ1Detail.getFirstSemEarliestBirthDate()));
      j1hsAugStart.setAugFirstSemLatestDate(DateUtils.getMMddyyDate(seasonJ1Detail.getFirstSemLatestBirthDate()));
      j1hsAugStart.setShowAugFirstSemToNewHF(seasonJ1Detail.getShowFirstSemToNewHF() == CCIConstants.ACTIVE ? true : false);
      j1hsAugStart.setAugFullYrStartDate(DateUtils.getMMddyyDate(seasonJ1Detail.getAugFullYearStartDate()));
      j1hsAugStart.setAugFullYrEndDate(DateUtils.getMMddyyDate(seasonJ1Detail.getAugFullYearEndDate()));
      j1hsAugStart.setAugFulllYrApplDeadlineDate(DateUtils.getMMddyyDate(seasonJ1Detail.getAugFullYearAppDeadlineDate()));
      j1hsAugStart.setShowAugFullYrToNewHF(seasonJ1Detail.getShowAugFullYearToNewHF() == CCIConstants.ACTIVE ? true : false);
      return j1hsAugStart;
   }
   
   
   /**
    * @param seasonJ1Detail
    * @return
    */
   private J1HSFieldSettings getJ1HSFieldSettings(SeasonJ1Detail seasonJ1Detail) {
      J1HSFieldSettings j1hsFieldSettings;
      j1hsFieldSettings = new J1HSFieldSettings();
      j1hsFieldSettings.setSeasonId(seasonJ1Detail.getSeason().getSeasonId());
      j1hsFieldSettings.setShowSeasProgToCurrentHF(seasonJ1Detail.getShowSeasonToCurrentHF() == CCIConstants.ACTIVE ? true : false);
      j1hsFieldSettings.setFsHoldDayLength(String.valueOf(seasonJ1Detail.getFieldStaffHoldLength()));
      j1hsFieldSettings.setHoldExpirationWarning(String.valueOf(seasonJ1Detail.getHoursBeforeHoldExpirationWarning()));
      j1hsFieldSettings.setDefaultLCPaymentSchedule(String.valueOf(seasonJ1Detail.getLcPaymentScheduleId()));
      j1hsFieldSettings.setFsAgreement("//TODO fs agreement is missing only id is present leaving it as it is");
      j1hsFieldSettings.setHfReferences(String.valueOf(seasonJ1Detail.getHfReferences()));
      j1hsFieldSettings.setAddStartHFInquiriesDate(DateUtils.getMMddyyDate(seasonJ1Detail.getHfInquiryDate()));
      j1hsFieldSettings.setShowWelcomeFamilyModal(seasonJ1Detail.getShowWelcomeFamily() == CCIConstants.ACTIVE ? true : false);
      j1hsFieldSettings.setShowAllGuranteedParticipantsToFS(seasonJ1Detail.getShowGuaranteed() == CCIConstants.ACTIVE ? true : false);
      j1hsFieldSettings.setShowAllUnGuranteedParticipantsToFS(seasonJ1Detail.getShowUnguaranteed() == CCIConstants.ACTIVE ? true : false);
      j1hsFieldSettings.setShowSpecialRequestStudentsToRD(seasonJ1Detail.getShowSpecialRequestStudent() == CCIConstants.ACTIVE ? true : false);
      return j1hsFieldSettings;
   }
   
   
   /**
    * @param j1hsBasicDetail
    * @param seasonJ1Detail
    */
   private void updateJ1BasicDetails(J1HSBasicDetail j1hsBasicDetail, SeasonJ1Detail seasonJ1Detail) {
      seasonJ1Detail.setProgramName(j1hsBasicDetail.getProgramName());
      seasonJ1Detail.setSeasonStatus(seasonStatusRepository.findSeasonStatusByName(j1hsBasicDetail.getProgramStatus()));
   }
   
   
   /**
    * @param j1hsJanStart
    * @param seasonJ1Detail
    */
   private void updateJ1JanStartDetails(J1HSJanStart j1hsJanStart, SeasonJ1Detail seasonJ1Detail) {
      seasonJ1Detail.setSecondSemStartDate(DateUtils.getMMddyyDateFromString(j1hsJanStart.getJanSecondSemStartDate()));
      seasonJ1Detail.setSecondSemEndDate(DateUtils.getMMddyyDateFromString(j1hsJanStart.getJanSecondSemEndDate()));
      seasonJ1Detail.setSecondSemAppDeadlineDate(DateUtils.getMMddyyDateFromString(j1hsJanStart.getJanSecondSemApplDeadlineDate()));
      seasonJ1Detail.setSecondSemEarliestBirthDate(DateUtils.getMMddyyDateFromString(j1hsJanStart.getJanSecondSemEarliestBirthDate()));
      seasonJ1Detail.setSecondSemLatestBirthDate(DateUtils.getMMddyyDateFromString(j1hsJanStart.getJanSecondSemLatestDate()));
      seasonJ1Detail.setShowSecondSemToNewHF(j1hsJanStart.isShowJanSecondSemToNewHF() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
      seasonJ1Detail.setActiveFullYearJanProgram(j1hsJanStart.isAtivateJanFullYearProgram() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
      seasonJ1Detail.setJanFullYearStartDate(DateUtils.getMMddyyDateFromString(j1hsJanStart.getJanFullYrStartDate()));
      seasonJ1Detail.setJanFullYearEndDate(DateUtils.getMMddyyDateFromString(j1hsJanStart.getJanFullYrEndDate()));
      seasonJ1Detail.setJanFullYearAppDeadlineDate(DateUtils.getMMddyyDateFromString(j1hsJanStart.getJanFullYrApplDeadlineDate()));
      seasonJ1Detail.setShowJanFullYearToNewHF(j1hsJanStart.isShowJanFullYrToNewHF() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
   }
   
   
   /**
    * @param j1hsAugStart
    * @param seasonJ1Detail
    */
   private void updateJ1AugStartDetails(J1HSAugStart j1hsAugStart, SeasonJ1Detail seasonJ1Detail) {
      seasonJ1Detail.setFirstSemStartDate(DateUtils.getMMddyyDateFromString(j1hsAugStart.getAugFirstSemStartDate()));
      seasonJ1Detail.setFirstSemEndDate(DateUtils.getMMddyyDateFromString(j1hsAugStart.getAugFirstSemEndDate()));
      seasonJ1Detail.setFirstSemAppDeadlineDate(DateUtils.getMMddyyDateFromString(j1hsAugStart.getAugFirstSemApplDeadlineDate()));
      seasonJ1Detail.setFirstSemEarliestBirthDate(DateUtils.getMMddyyDateFromString(j1hsAugStart.getAugFirstSemEarliestBirthDate()));
      seasonJ1Detail.setFirstSemLatestBirthDate(DateUtils.getMMddyyDateFromString(j1hsAugStart.getAugFirstSemLatestDate()));
      seasonJ1Detail.setShowFirstSemToNewHF(j1hsAugStart.isShowAugFirstSemToNewHF() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
      seasonJ1Detail.setAugFullYearStartDate(DateUtils.getMMddyyDateFromString(j1hsAugStart.getAugFullYrStartDate()));
      seasonJ1Detail.setAugFullYearEndDate(DateUtils.getMMddyyDateFromString(j1hsAugStart.getAugFullYrEndDate()));
      seasonJ1Detail.setAugFullYearAppDeadlineDate(DateUtils.getMMddyyDateFromString(j1hsAugStart.getAugFulllYrApplDeadlineDate()));
      seasonJ1Detail.setShowAugFullYearToNewHF(j1hsAugStart.isShowAugFullYrToNewHF() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
   }
   
   
   /**
    * @param j1hsFieldSettings
    * @param seasonJ1Detail
    */
   private void updateJ1FSSettings(J1HSFieldSettings j1hsFieldSettings, SeasonJ1Detail seasonJ1Detail) {
      seasonJ1Detail.setShowSeasonToCurrentHF(j1hsFieldSettings.isShowSeasProgToCurrentHF() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
      seasonJ1Detail.setFieldStaffHoldLength(Integer.valueOf(j1hsFieldSettings.getFsHoldDayLength()));
      seasonJ1Detail.setHoursBeforeHoldExpirationWarning(Integer.valueOf(j1hsFieldSettings.getHoldExpirationWarning()));
      seasonJ1Detail.setLcPaymentScheduleId(Integer.valueOf(j1hsFieldSettings.getDefaultLCPaymentSchedule()));
      seasonJ1Detail.setFsAgreementId(1);// TODO dummy value
      seasonJ1Detail.setHfReferences(Integer.valueOf(j1hsFieldSettings.getHfReferences()));
      seasonJ1Detail.setHfInquiryDate(DateUtils.getMMddyyDateFromString(j1hsFieldSettings.getAddStartHFInquiriesDate()));
      seasonJ1Detail.setShowWelcomeFamily(j1hsFieldSettings.isShowWelcomeFamilyModal() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
      seasonJ1Detail.setShowGuaranteed(j1hsFieldSettings.isShowAllGuranteedParticipantsToFS() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
      seasonJ1Detail.setShowUnguaranteed(j1hsFieldSettings.isShowAllUnGuranteedParticipantsToFS() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
      seasonJ1Detail.setShowSpecialRequestStudent(j1hsFieldSettings.isShowSpecialRequestStudentsToRD() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
   }

}
