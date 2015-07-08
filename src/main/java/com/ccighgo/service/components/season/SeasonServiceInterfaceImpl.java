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
import com.ccighgo.db.entities.SeasonDepartmentNote;
import com.ccighgo.db.entities.SeasonF1Detail;
import com.ccighgo.db.entities.SeasonGHTConfiguration;
import com.ccighgo.db.entities.SeasonHSADetail;
import com.ccighgo.db.entities.SeasonHSPAllocation;
import com.ccighgo.db.entities.SeasonHSPConfiguration;
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
import com.ccighgo.jpa.repositories.DepartmentProgramOptionRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.DepartmentRepository;
import com.ccighgo.jpa.repositories.DocumentInformationRepository;
import com.ccighgo.jpa.repositories.DocumentTypeDocumentCategoryProcessRepository;
import com.ccighgo.jpa.repositories.SeasonCAPDetailsRepository;
import com.ccighgo.jpa.repositories.SeasonDepartmentDocumentRepository;
import com.ccighgo.jpa.repositories.SeasonDepartmentNotesRepository;
import com.ccighgo.jpa.repositories.SeasonF1DetailsRepository;
import com.ccighgo.jpa.repositories.SeasonGHTConfigurationRepository;
import com.ccighgo.jpa.repositories.SeasonHSADetailsRepository;
import com.ccighgo.jpa.repositories.SeasonHSPAllocationRepository;
import com.ccighgo.jpa.repositories.SeasonHSPConfigurationRepsitory;
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
import com.ccighgo.service.transport.season.beans.cloneseason.CloneSeason;
import com.ccighgo.service.transport.season.beans.seasondepartdoc.SeasonDepartmentDocument;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection1Base;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection2Dates;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.SeasonGHTDetails;
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
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPTraineeDetails;
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
   SeasonCloningHelper seasonHelper;
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

   SeasonServiceInterfaceImpl() {
   }

   @Override
   @Transactional(readOnly = true)
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
         int seasonId = createSeasonLogic(seasonBean);
         return viewSeason(seasonId + CCIConstants.EMPTY_DATA);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   private int createSeasonLogic(SeasonBean seasonBean) {
      Season seasonEntity = new Season();
      seasonServiceImplUtil.convertSeasonBeanToSeasonEntity(seasonBean, seasonEntity, false);
      seasonEntity = seasonRepository.saveAndFlush(seasonEntity);
      seasonServiceImplUtil.createSeasonHspConfiguration(seasonBean, seasonEntity);
      seasonServiceImplUtil.createSeasonDepartmentNotes(seasonBean, seasonEntity);
      seasonServiceImplUtil.createSeasonPrograms(seasonEntity, seasonBean);
      return seasonEntity.getSeasonId();
   }

   @Override
   @Transactional
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
   @Transactional(readOnly = true)
   public SeasonBean editSeason(String id) {
      return viewSeason(id);
   }

   @Override
   @Transactional(readOnly = true)
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
         int seasonId = updateSeasonLogic(seasonBean);
         return viewSeason(seasonId + CCIConstants.EMPTY_DATA);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   private int updateSeasonLogic(SeasonBean seasonBean) {
      Season seasonEntity = new Season();
      seasonServiceImplUtil.convertSeasonBeanToSeasonEntity(seasonBean, seasonEntity, true);
      seasonRepository.saveAndFlush(seasonEntity);
      seasonServiceImplUtil.updateSeasonHspConfiguration(seasonBean, seasonEntity);
      seasonServiceImplUtil.updateSeasonDepartmentNotes(seasonBean, seasonEntity);
      return seasonEntity.getSeasonId();
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
                  // TODO implement when STP tables are available
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
            }
            seasonPrograms.getSeasonPrograms().addAll(seasonProgramsList);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return seasonPrograms;
   }

   @Transactional(readOnly = true)
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
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
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
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
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
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
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
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
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
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
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
               j1hsProgramAllocations = new J1HSProgramAllocations();
               j1hsProgramAllocations.setSeasonId(seasonJ1Detail.getSeason().getSeasonId());
               j1hsProgramAllocations.setSeasonProgramId(seasonJ1Detail.getSeasonJ1DetailsId());
               for (SeasonHSPAllocation hspAllocation : hspAllocations) {
                  if (hspAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_J1_HS_ID) {
                     if (hspAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_J1)) {
                        j1hsProgramAllocations.setAugStartUnGuarnteedParticipants(hspAllocation.getMaxUnguaranteedPax());
                        totalUnGuarant += hspAllocation.getMaxUnguaranteedPax();
                        j1hsProgramAllocations.setAugStartGuarnteedParticipants(hspAllocation.getMaxGuaranteedPax());
                        totalGurant += hspAllocation.getMaxGuaranteedPax();
                     }
                     if (hspAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_J1)) {
                        j1hsProgramAllocations.setJanStartUnGuarnteedParticipants(hspAllocation.getMaxUnguaranteedPax());
                        totalUnGuarant += hspAllocation.getMaxUnguaranteedPax();
                        j1hsProgramAllocations.setJanStartGuarnteedParticipants(hspAllocation.getMaxGuaranteedPax());
                        totalGurant += hspAllocation.getMaxGuaranteedPax();
                     }
                  }
               }
               j1hsProgramAllocations.setAugStartAcceptedParticipants(0);
               j1hsProgramAllocations.setAugStartRemainingParticpants(0);
               j1hsProgramAllocations.setJanStartAcceptedParticipants(0);
               j1hsProgramAllocations.setJanStartRemainingParticpants(0);
               j1hsProgramAllocations.setTotalAcceptedParticipants(0);
               j1hsProgramAllocations.setTotalRemainingParticpants(0);
               j1hsProgramAllocations.setTotalUnGuarnteedParticipants(totalUnGuarant);
               j1hsProgramAllocations.setTotalGuarnteedParticipants(totalGurant);
            }
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
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
            if (seasonHspJ1HSDetails.getJ1HsProgramAllocations() != null) {
               updateHSPJ1HSSeasonProgramAllocation(seasonHspJ1HSDetails.getJ1HsProgramAllocations());
            }
            if (seasonHspJ1HSDetails.getJ1HsNotes() != null) {
               seasonServiceImplUtil.updateJ1Notes(seasonHspJ1HSDetails, seasonJ1Detail.getSeason());
            }
            if (seasonHspJ1HSDetails.getJ1HsDocuments() != null) {
               seasonServiceImplUtil.updateJ1HSDocs(seasonHspJ1HSDetails, seasonJ1Detail.getSeason());
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
   @Transactional
   public J1HSBasicDetail updateHSPJ1HSSeasonNameAndStatus(J1HSBasicDetail j1hsBasicDetail) {
      J1HSBasicDetail returnObject = null;
      if (j1hsBasicDetail == null || j1hsBasicDetail.getSeasonId() == 0) {
         return returnObject;
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(j1hsBasicDetail.getSeasonProgramId()));
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
   @Transactional
   public J1HSJanStart updateHSPJ1HSSeasonJanStartDetails(J1HSJanStart j1hsJanStart) {
      J1HSJanStart returnObject = null;
      if (j1hsJanStart == null) {
         // throw exception
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(j1hsJanStart.getSeasonProgramId()));
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
   @Transactional
   public J1HSAugStart updateHSPJ1HSSeasonAugStartDetails(J1HSAugStart j1hsAugStart) {
      J1HSAugStart returnObject = null;
      if (j1hsAugStart == null) {
         // throw exception
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(j1hsAugStart.getSeasonProgramId()));
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
   @Transactional
   public J1HSFieldSettings updateHSPJ1HSSeasonFieldSettings(J1HSFieldSettings j1hsFieldSettings) {
      J1HSFieldSettings returnObject = null;
      if (j1hsFieldSettings == null) {
         // throw exception
      }
      try {
         SeasonJ1Detail seasonJ1Detail = seasonJ1DetailsRepository.findOne(Integer.valueOf(j1hsFieldSettings.getSeasonProgramId()));
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
   @Transactional
   public J1HSProgramAllocations updateHSPJ1HSSeasonProgramAllocation(J1HSProgramAllocations j1hsProgramAllocations) {
      J1HSProgramAllocations returnObject = null;
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
            returnObject = getHSPJ1HSSeasonProgramAllocation(String.valueOf(j1hsProgramAllocations.getSeasonProgramId()));
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   public HSPF1ProgramAllocations getHSPF1ProgramAllocations(String seasonProgramId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         return seasonServiceImplUtil.getHSPF1ProgramAllocations(allF1Details);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1FieldSettings getHSPF1FieldSettings(String seasonProgramId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         return seasonServiceImplUtil.getHSPF1FieldSettings(allF1Details);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1AugustStart1StSemesterDetails getHSPF1AugustStart1StSemesterDetails(String seasonProgramId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         return seasonServiceImplUtil.getHSPF1AugustStart1StSemesterDetails(allF1Details);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1AugustStartFullYearDetails getHSPF1AugustStartFullYearDetails(String seasonProgramId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         return seasonServiceImplUtil.getHSPF1AugustStartFullYearDetails(allF1Details);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1JanuaryStart2NdSemesterDetails getHSPF1JanuaryStart2NdSemesterDetails(String seasonProgramId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         return seasonServiceImplUtil.getHSPF1JanuaryStart2NdSemesterDetails(allF1Details);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1JanuaryStartFullYearDetail getHSPF1JanuaryStartFullYearDetails(String seasonProgramId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         return seasonServiceImplUtil.getHSPF1JanuaryStartFullYearDetails(allF1Details);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1BasicDetails getHSPF1NameAndStatus(String seasonProgramId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         return seasonServiceImplUtil.getHSPF1NameAndStatus(allF1Details);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public HSPF1Accounting getHSPF1Accounting(String seasonProgramId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         return seasonServiceImplUtil.getHSPF1Accounting(allF1Details);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public SeasonHSPF1Details getSeasonHSPF1Details(String seasonProgramId) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         SeasonHSPF1Details seasonHSPF1Details = new SeasonHSPF1Details();
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
         seasonHSPF1Details.setProgramAllocations(seasonServiceImplUtil.getHSPF1ProgramAllocations(allF1Details));
         seasonHSPF1Details.getNotes().addAll(seasonServiceImplUtil.getHSPF1Notes(allF1Details));
         seasonHSPF1Details.getDocuments().addAll(seasonServiceImplUtil.getHSPF1Documents(allF1Details));
         return seasonHSPF1Details;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public SeasonHSPF1Details updateF1Details(SeasonHSPF1Details seasonHSPF1Details) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(seasonHSPF1Details.getSeasonProgramId());
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

   @Transactional
   public HSPF1BasicDetails updateHSPF1NameAndStatus(HSPF1BasicDetails hspf1BasicDetails) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1BasicDetails.getSeasonProgramId());
         if (allF1Details != null) {
            hspf1BasicDetails = seasonServiceImplUtil.updateHSPF1NameAndStatus(allF1Details, hspf1BasicDetails);
         }
         return hspf1BasicDetails;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public HSPF1Accounting updateF1Accounting(HSPF1Accounting hspf1Accounting) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1Accounting.getSeasonProgramId());
         if (allF1Details != null) {
            hspf1Accounting = seasonServiceImplUtil.updateF1Accounting(allF1Details, hspf1Accounting);
         }
         return hspf1Accounting;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public HSPF1JanuaryStart2NdSemesterDetails updateF1JanStart2NdSemesterDetails(HSPF1JanuaryStart2NdSemesterDetails hspf1JanuaryStart2NdSemesterDetails) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1JanuaryStart2NdSemesterDetails.getSeasonProgramId());
         if (allF1Details != null) {
            hspf1JanuaryStart2NdSemesterDetails = seasonServiceImplUtil.updateF1JanStart2NdSemesterDetails(allF1Details, hspf1JanuaryStart2NdSemesterDetails);
         }
         return hspf1JanuaryStart2NdSemesterDetails;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public HSPF1JanuaryStartFullYearDetail updateF1JanStartFullYearDetails(HSPF1JanuaryStartFullYearDetail hspf1JanuaryStartFullYearDetail) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1JanuaryStartFullYearDetail.getSeasonProgramId());
         if (allF1Details != null) {
            hspf1JanuaryStartFullYearDetail = seasonServiceImplUtil.updateF1JanStartFullYearDetails(allF1Details, hspf1JanuaryStartFullYearDetail);
         }
         return hspf1JanuaryStartFullYearDetail;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public HSPF1AugustStart1StSemesterDetails updateF1AugStart1StSemesterDetails(HSPF1AugustStart1StSemesterDetails hspf1AugustStart1StSemesterDetails) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1AugustStart1StSemesterDetails.getSeasonProgramId());
         if (allF1Details != null) {
            hspf1AugustStart1StSemesterDetails = seasonServiceImplUtil.updateF1AugStart1StSemesterDetails(allF1Details, hspf1AugustStart1StSemesterDetails);
         }
         return hspf1AugustStart1StSemesterDetails;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public HSPF1AugustStartFullYearDetails updateF1AugStartFullYearDetails(HSPF1AugustStartFullYearDetails hspf1AugustStartFullYearDetails) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1AugustStartFullYearDetails.getSeasonProgramId());
         if (allF1Details != null) {
            hspf1AugustStartFullYearDetails = seasonServiceImplUtil.updateF1AugStartFullYearDetails(allF1Details, hspf1AugustStartFullYearDetails);
         }
         return hspf1AugustStartFullYearDetails;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public HSPF1FieldSettings updateF1FieldSettings(HSPF1FieldSettings hspf1FieldSettings) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1FieldSettings.getSeasonProgramId());
         if (allF1Details != null) {
            hspf1FieldSettings = seasonServiceImplUtil.updateF1FieldSettings(allF1Details, hspf1FieldSettings);
         }
         return hspf1FieldSettings;
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public HSPF1ProgramAllocations updateF1ProgramAllocation(HSPF1ProgramAllocations hspf1ProgramAllocations) {
      try {
         SeasonF1Detail allF1Details = seasonF1DetailsRepository.findOne(hspf1ProgramAllocations.getSeasonProgramId());
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
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return seasonGHTDetails;
   }

   public GHTSection1Base getGHTVASeasonNameAndStatus(String seasonProgramId) {
      GHTSection1Base ghtSection1Base = null;
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonVADetail != null) {
            ghtSection1Base = seasonServiceImplUtil.getVABasicDetail(seasonVADetail);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return ghtSection1Base;
   }

   public GHTSection2Dates getGHTVASeasonDateDetails(String seasonProgramId) {
      GHTSection2Dates ghtSection2Dates = null;
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonVADetail != null) {
            ghtSection2Dates = seasonServiceImplUtil.getVADates(seasonVADetail);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return ghtSection2Dates;
   }

   // update GHT Volunteer Abroad season details

   @Transactional
   public SeasonGHTDetails updateGHTVASeasonDetails(SeasonGHTDetails seasonGHTDetails) {
      SeasonGHTDetails returnObject = null;
      if (seasonGHTDetails == null) {
         return returnObject;
      }
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findOne(Integer.valueOf(seasonGHTDetails.getSeasonProgramId()));
         if (seasonVADetail != null) {
            if (seasonGHTDetails.getGhtBaseDetails() != null) {
               seasonServiceImplUtil.updateVABasicDetails(seasonGHTDetails.getGhtBaseDetails(), seasonVADetail);
            }
            if (seasonGHTDetails.getGhtDates() != null) {
               seasonServiceImplUtil.updateVADates(seasonGHTDetails.getGhtDates(), seasonVADetail);
            }
            if (seasonGHTDetails.getGhtNotes() != null) {
               seasonServiceImplUtil.updateGHTNotes(seasonGHTDetails, seasonVADetail.getSeason(), CCIConstants.GHT_VOL_ABRD_ID);
            }
            seasonVADetail = seasonVADetailsRepository.saveAndFlush(seasonVADetail);
            returnObject = seasonGHTDetails;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   @Transactional
   public GHTSection1Base updateGHTVASeasonNameAndStatus(GHTSection1Base ghtSection1Base) {
      GHTSection1Base returnObject = null;
      if (ghtSection1Base == null || ghtSection1Base.getSeasonId() == 0) {
         return returnObject;
      }
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findOne(Integer.valueOf(ghtSection1Base.getSeasonProgramId()));
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

   @Transactional
   public GHTSection2Dates updateGHTVASeasonDateDetails(GHTSection2Dates ghtSection2Dates) {
      GHTSection2Dates returnObject = null;
      if (ghtSection2Dates == null || ghtSection2Dates.getSeasonId() == 0) {
         return returnObject;
      }
      try {
         SeasonVADetail seasonVADetail = seasonVADetailsRepository.findOne(Integer.valueOf(ghtSection2Dates.getSeasonProgramId()));
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
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return seasonGHTDetails;
   }

   public GHTSection1Base getGHTWASeasonNameAndStatus(String seasonProgramId) {
      GHTSection1Base ghtSection1Base = null;
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWADetail != null) {
            ghtSection1Base = seasonServiceImplUtil.getWABasicDetail(seasonWADetail);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return ghtSection1Base;
   }

   public GHTSection2Dates getGHTWASeasonDateDetails(String seasonProgramId) {
      GHTSection2Dates ghtSection2Dates = null;
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWADetail != null) {
            ghtSection2Dates = seasonServiceImplUtil.getWADates(seasonWADetail);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return ghtSection2Dates;
   }

   // update GHT Work Abroad season details

   @Transactional
   public SeasonGHTDetails updateGHTWASeasonDetails(SeasonGHTDetails seasonGHTDetails) {
      SeasonGHTDetails returnObject = null;
      if (seasonGHTDetails == null) {
         return returnObject;
      }
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(seasonGHTDetails.getSeasonProgramId()));
         if (seasonWADetail != null) {
            if (seasonGHTDetails.getGhtBaseDetails() != null) {
               seasonServiceImplUtil.updateWABasicDetails(seasonGHTDetails.getGhtBaseDetails(), seasonWADetail);
            }
            if (seasonGHTDetails.getGhtDates() != null) {
               seasonServiceImplUtil.updateWADates(seasonGHTDetails.getGhtDates(), seasonWADetail);
            }
            if (seasonGHTDetails.getGhtNotes() != null) {
               seasonServiceImplUtil.updateGHTNotes(seasonGHTDetails, seasonWADetail.getSeason(), CCIConstants.GHT_WRK_ABRD_ID);
            }
            seasonWADetail = seasonWADetailsRepository.saveAndFlush(seasonWADetail);
            returnObject = seasonGHTDetails;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   @Transactional
   public GHTSection1Base updateGHTWASeasonNameAndStatus(GHTSection1Base ghtSection1Base) {
      GHTSection1Base returnObject = null;
      if (ghtSection1Base == null || ghtSection1Base.getSeasonId() == 0) {
         return returnObject;
      }
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(ghtSection1Base.getSeasonProgramId()));
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

   @Transactional
   public GHTSection2Dates updateGHTWASeasonDateDetails(GHTSection2Dates ghtSection2Dates) {
      GHTSection2Dates returnObject = null;
      if (ghtSection2Dates == null || ghtSection2Dates.getSeasonId() == 0) {
         return returnObject;
      }
      try {
         SeasonWADetail seasonWADetail = seasonWADetailsRepository.findOne(Integer.valueOf(ghtSection2Dates.getSeasonProgramId()));
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

   public SeasonGHTDetails getGHTHSAbroad(String seasonProgramId) {
      try {
         SeasonHSADetail seasonHSADetail = seasonHSADetailsRepository.findOne(Integer.parseInt(seasonProgramId));
         if (seasonHSADetail == null) {
            return null;
         }
         return seasonServiceImplUtil.getGHTHSAbroad(seasonHSADetail);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public SeasonGHTDetails updateGHTHSAbroad(SeasonGHTDetails seasonGHTDetails) {
      try {
         return seasonServiceImplUtil.updateGHTHSAbroad(seasonGHTDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
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
      }
      return null;
   }

   @Transactional
   public SeasonGHTDetails updateGHTLanguageSchool(SeasonGHTDetails seasonGHTDetails) {
      try {
         return seasonServiceImplUtil.updateGHTLanguageSchool(seasonGHTDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
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
      }
      return null;
   }

   @Transactional
   public SeasonGHTDetails updateGHTTeachAbroad(SeasonGHTDetails seasonGHTDetails) {
      try {
         return seasonServiceImplUtil.updateGHTTeachAbroad(seasonGHTDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection1Base getGHTHSSection1BaseAbroad(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getGHTHSSection1BaseAbroad(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public GHTSection1Base updateGHTHSSection1BaseAbroad(GHTSection1Base ghtSection1Base) {
      try {
         return seasonServiceImplUtil.updateGHTHSSection1BaseAbroad(ghtSection1Base);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection2Dates getGHTHSSection2DatesAbroad(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getGHTHSSection2DatesAbroad(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public GHTSection2Dates updateGHTHSSection2DatesAbroad(GHTSection2Dates ghtSection2Dates) {
      try {
         return seasonServiceImplUtil.updateGHTHSSection2DatesAbroad(ghtSection2Dates);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection1Base getGHTLanguageSchoolSection1(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getGHTLanguageSchoolSection1(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public GHTSection1Base updateGHTLanguageSchoolSection1(GHTSection1Base ghtSection1Base) {
      try {
         return seasonServiceImplUtil.updateGHTLanguageSchoolSection1(ghtSection1Base);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection2Dates getGHTLanguageSchoolSection2Dates(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getGHTLanguageSchoolSection2Dates(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public GHTSection2Dates updateGHTLanguageSchoolSection2Dates(GHTSection2Dates ghtSection2Dates) {
      try {
         return seasonServiceImplUtil.updateGHTLanguageSchoolSection2Dates(ghtSection2Dates);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection1Base getGHTTeachAbroadSection1(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getGHTTeachAbroadSection1(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public GHTSection1Base updateGHTTeachAbroadSection1(GHTSection1Base ghtSection1Base) {
      try {
         return seasonServiceImplUtil.updateGHTTeachAbroadSection1(ghtSection1Base);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public GHTSection2Dates getGHTTeachAbroadSection2Dates(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getGHTTeachAbroadSection2Dates(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public GHTSection2Dates updateGHTTeachAbroadSection2Dates(GHTSection2Dates ghtSection2Dates) {
      try {
         return seasonServiceImplUtil.updateGHTTeachAbroadSection2Dates(ghtSection2Dates);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public SeasonWPCAPDetails getWPCAPDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPCAPDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public SeasonWPCAPDetails updateWPCAPDetails(SeasonWPCAPDetails seasonWPCAPDetails) {
      try {
         return seasonServiceImplUtil.updateWPCAPDetails(seasonWPCAPDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public WPCAPBasicDetails getWPCAPBasicDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPCAPBasicDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public WPCAPBasicDetails updateWPCAPBasicDetails(WPCAPBasicDetails wpcapBasicDetails) {
      try {
         return seasonServiceImplUtil.updateWPCAPBasicDetails(wpcapBasicDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public WPCAPInternshipDetails getWPCAPInternshipDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPCAPInternshipDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public WPCAPInternshipDetails updateWPCAPInternshipDetails(WPCAPInternshipDetails wpcapInternshipDetails) {
      try {
         return seasonServiceImplUtil.updateWPCAPInternshipDetails(wpcapInternshipDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public WPCAPTraineeDetails getWPCAPTraineeDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPCAPTraineeDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public WPCAPTraineeDetails updateWPCAPTraineeDetails(WPCAPTraineeDetails wpcapTraineeDetails) {
      try {
         return seasonServiceImplUtil.updateWPCAPTraineeDetails(wpcapTraineeDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
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
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return seasonWPDetails;
   }

   @Transactional
   public SeasonWPDetails updateWPSumDetails(SeasonWPDetails seasonWPDetails) {
      SeasonWPDetails returnObject = null;
      if (seasonWPDetails == null) {
         return returnObject;
      }
      try {
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
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   public WPBasicDetail getWPSumBaseDetails(String seasonProgramId) {
      WPBasicDetail wpBasicDetail = null;
      try {
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSummerDetail != null) {
            wpBasicDetail = seasonServiceImplUtil.getWPSummerBaseDetails(seasonWnTSummerDetail);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return wpBasicDetail;
   }

   @Transactional
   public WPBasicDetail updateWPSumBaseDetails(WPBasicDetail wpBasicDetail) {
      WPBasicDetail returnObject = null;
      if (wpBasicDetail == null || wpBasicDetail.getSeasonId() == 0) {
         return returnObject;
      }
      try {
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(wpBasicDetail.getSeasonProgramId()));
         if (seasonWnTSummerDetail != null) {
            seasonServiceImplUtil.updateWPSummerBaseDetails(wpBasicDetail, seasonWnTSummerDetail);
            seasonWnTSummerDetail = seasonWTSummerRepository.saveAndFlush(seasonWnTSummerDetail);
            returnObject = wpBasicDetail;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   public WPSectionOne getWPSumSectionOneDetails(String seasonProgramId) {
      WPSectionOne wpSectionOne = null;
      try {
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSummerDetail != null) {
            wpSectionOne = seasonServiceImplUtil.getWPSummerSection1Details(seasonWnTSummerDetail);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return wpSectionOne;
   }

   @Transactional
   public WPSectionOne updateWPSumSectionOneDetails(WPSectionOne wpSectionOne) {
      WPSectionOne returnObject = null;
      if (wpSectionOne == null || wpSectionOne.getSeasonProgramId() == 0) {
         return returnObject;
      }
      try {
         SeasonWnTSummerDetail seasonWnTSummerDetail = seasonWTSummerRepository.findOne(Integer.valueOf(wpSectionOne.getSeasonProgramId()));
         if (seasonWnTSummerDetail != null) {
            seasonServiceImplUtil.updateWPSummerSection1Details(wpSectionOne, seasonWnTSummerDetail);
            seasonWnTSummerDetail = seasonWTSummerRepository.saveAndFlush(seasonWnTSummerDetail);
            returnObject = wpSectionOne;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
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
               for (SeasonWPAllocation seasonWPAllocation : wpAllocations) {
                  if (seasonWPAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_SUMMER_ID) {
                     if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JOB_FAIR_SUMMER)) {
                        wpProgramAllocations.setJobFairMaxParticipants(seasonWPAllocation.getMaxPax());
                        totalMaxParticipants += seasonWPAllocation.getMaxPax();
                     }
                     if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.SELF_PLACED_SUMMER)) {
                        wpProgramAllocations.setSelfPlacedMaxParticipants(seasonWPAllocation.getMaxPax());
                        totalMaxParticipants += seasonWPAllocation.getMaxPax();
                     }
                     if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.DIRECT_PLACEMENT_SUMMER)) {
                        wpProgramAllocations.setDirectPlcmntMaxParticipants(seasonWPAllocation.getMaxPax());
                        totalMaxParticipants += seasonWPAllocation.getMaxPax();
                     }
                  }
               }
               wpProgramAllocations.setDirectPlcmntAcceptedParticipants(0);
               wpProgramAllocations.setDirectPlcmntCCIReview(0);
               wpProgramAllocations.setDirectPlcmntExpectedParticipants(0);
               wpProgramAllocations.setDirectPlcmntPendingVerification(0);
               wpProgramAllocations.setJobFairAcceptedParticipants(0);
               wpProgramAllocations.setJobFairCCIReview(0);
               wpProgramAllocations.setJobFairExpectedParticipants(0);
               wpProgramAllocations.setJobFairPendingVerification(0);
               wpProgramAllocations.setJobFairRemainingParticpants(0);
               wpProgramAllocations.setSelfPlacedAcceptedParticipants(0);
               wpProgramAllocations.setSelfPlacedCCIReview(0);
               wpProgramAllocations.setSelfPlacedExpectedParticipants(0);
               wpProgramAllocations.setSelfPlacedPendingVerification(0);
               wpProgramAllocations.setSelfPlacedRemainingParticpants(0);
               wpProgramAllocations.setTotalAcceptedParticipants(0);
               wpProgramAllocations.setTotalCCIReview(0);
               wpProgramAllocations.setTotalExpectedParticipants(0);
               wpProgramAllocations.setTotalPendingVerification(0);
               wpProgramAllocations.setTotalRemainingParticpants(0);
               wpProgramAllocations.setTotalMaxParticipants(totalMaxParticipants);
            }
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
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
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
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
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
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
            if (seasonWPDetails.getWpBasicDetail() != null) {
               seasonServiceImplUtil.updateWPSpringBaseDetails(seasonWPDetails.getWpBasicDetail(), seasonWnTSpringDetail);
            }
            if (seasonWPDetails.getWpSectionOne() != null) {
               seasonServiceImplUtil.updateWPSpringSection1Details(seasonWPDetails.getWpSectionOne(), seasonWnTSpringDetail);
            }
            if (seasonWPDetails.getWpProgramAllocations() != null) {
               updateWPSpringAllocationDetails(seasonWPDetails.getWpProgramAllocations());
            }
            if (seasonWPDetails.getWpNotes() != null) {
               seasonServiceImplUtil.updateWPNotes(seasonWPDetails, seasonWnTSpringDetail.getSeason(), CCIConstants.WP_WT_SPRING_ID);
            }
            if (seasonWPDetails.getWpDocuments() != null) {
               seasonServiceImplUtil.updateWPDocs(seasonWPDetails, seasonWnTSpringDetail.getSeason(), CCIConstants.WP_WT_SPRING_ID);
            }
            seasonWnTSpringDetail = seasonWTSpringRepository.saveAndFlush(seasonWnTSpringDetail);
            returnObject = seasonWPDetails;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   public WPBasicDetail getWPSpringBaseDetails(String seasonProgramId) {
      WPBasicDetail wpBasicDetail = null;
      try {
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSpringDetail != null) {
            wpBasicDetail = seasonServiceImplUtil.getWPSpringBaseDetails(seasonWnTSpringDetail);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return wpBasicDetail;
   }

   @Transactional
   public WPBasicDetail updateWPSpringBaseDetails(WPBasicDetail wpBasicDetail) {
      WPBasicDetail returnObject = null;
      if (wpBasicDetail == null || wpBasicDetail.getSeasonId() == 0) {
         return returnObject;
      }
      try {
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(wpBasicDetail.getSeasonProgramId()));
         if (seasonWnTSpringDetail != null) {
            seasonServiceImplUtil.updateWPSpringBaseDetails(wpBasicDetail, seasonWnTSpringDetail);
            seasonWnTSpringDetail = seasonWTSpringRepository.saveAndFlush(seasonWnTSpringDetail);
            returnObject = wpBasicDetail;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   public WPSectionOne getWPSpringSectionOneDetails(String seasonProgramId) {
      WPSectionOne wpSectionOne = null;
      try {
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTSpringDetail != null) {
            wpSectionOne = seasonServiceImplUtil.getWPSpringSection1Details(seasonWnTSpringDetail);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return wpSectionOne;
   }

   @Transactional
   public WPSectionOne updateWPSpringSectionOneDetails(WPSectionOne wpSectionOne) {
      WPSectionOne returnObject = null;
      if (wpSectionOne == null || wpSectionOne.getSeasonProgramId() == 0) {
         return returnObject;
      }
      try {
         SeasonWnTSpringDetail seasonWnTSpringDetail = seasonWTSpringRepository.findOne(Integer.valueOf(wpSectionOne.getSeasonProgramId()));
         if (seasonWnTSpringDetail != null) {
            seasonServiceImplUtil.updateWPSpringSection1Details(wpSectionOne, seasonWnTSpringDetail);
            seasonWnTSpringDetail = seasonWTSpringRepository.saveAndFlush(seasonWnTSpringDetail);
            returnObject = wpSectionOne;
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
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
               for (SeasonWPAllocation seasonWPAllocation : wpAllocations) {
                  if (seasonWPAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_SPRING_ID) {
                     if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JOB_FAIR_SPRING)) {
                        wpProgramAllocations.setJobFairMaxParticipants(seasonWPAllocation.getMaxPax());
                        totalMaxParticipants += seasonWPAllocation.getMaxPax();
                     }
                     if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.SELF_PLACED_SPRING)) {
                        wpProgramAllocations.setSelfPlacedMaxParticipants(seasonWPAllocation.getMaxPax());
                        totalMaxParticipants += seasonWPAllocation.getMaxPax();
                     }
                     if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.DIRECT_PLACEMENT_SPRING)) {
                        wpProgramAllocations.setDirectPlcmntMaxParticipants(seasonWPAllocation.getMaxPax());
                        totalMaxParticipants += seasonWPAllocation.getMaxPax();
                     }
                  }
               }
               wpProgramAllocations.setDirectPlcmntAcceptedParticipants(0);
               wpProgramAllocations.setDirectPlcmntCCIReview(0);
               wpProgramAllocations.setDirectPlcmntExpectedParticipants(0);
               wpProgramAllocations.setDirectPlcmntPendingVerification(0);
               wpProgramAllocations.setJobFairAcceptedParticipants(0);
               wpProgramAllocations.setJobFairCCIReview(0);
               wpProgramAllocations.setJobFairExpectedParticipants(0);
               wpProgramAllocations.setJobFairPendingVerification(0);
               wpProgramAllocations.setJobFairRemainingParticpants(0);
               wpProgramAllocations.setSelfPlacedAcceptedParticipants(0);
               wpProgramAllocations.setSelfPlacedCCIReview(0);
               wpProgramAllocations.setSelfPlacedExpectedParticipants(0);
               wpProgramAllocations.setSelfPlacedPendingVerification(0);
               wpProgramAllocations.setSelfPlacedRemainingParticpants(0);
               wpProgramAllocations.setTotalAcceptedParticipants(0);
               wpProgramAllocations.setTotalCCIReview(0);
               wpProgramAllocations.setTotalExpectedParticipants(0);
               wpProgramAllocations.setTotalPendingVerification(0);
               wpProgramAllocations.setTotalRemainingParticpants(0);
               wpProgramAllocations.setTotalMaxParticipants(totalMaxParticipants);
            }
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
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
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   public SeasonWPDetails getWPWinterDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPWinterDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public SeasonWPDetails editWPWinterDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPWinterDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public SeasonWPDetails updateWPWinterDetails(SeasonWPDetails seasonWPDetails) {
      try {
         return seasonServiceImplUtil.updateWPWinterDetails(seasonWPDetails);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public WPBasicDetail getWPWinterBaseDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPWinterBaseDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public WPBasicDetail editWPWinterBaseDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPWinterBaseDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public WPBasicDetail updateWPWinterBaseDetails(WPBasicDetail wpBasicDetail) {
      try {
         return seasonServiceImplUtil.updateWPWinterBaseDetails(wpBasicDetail);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public WPSectionOne getWPWinterSectionOneDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPWinterSectionOneDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public WPSectionOne editWPWinterSectionOneDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPWinterSectionOneDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public WPSectionOne updateWPWinterSectionOneDetails(WPSectionOne wpSectionOne) {
      try {
         return seasonServiceImplUtil.updateWPWinterSectionOneDetails(wpSectionOne);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public WPProgramAllocations getWPWinterAllocationDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPWinterAllocationDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   public WPProgramAllocations editWPWinterAllocationDetails(String seasonProgramId) {
      try {
         return seasonServiceImplUtil.getWPWinterAllocationDetails(seasonProgramId);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public WPProgramAllocations updateWPWinterAllocationDetails(WPProgramAllocations wpProgramAllocations) {
      try {
         return seasonServiceImplUtil.updateWPWinterAllocationDetails(wpProgramAllocations);
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Transactional
   public CloneSeason cloneSeason(CloneSeason cloneSeason) {
      if (cloneSeason.getExistingSeasonId() == 0 || cloneSeason.getExistingSeasonId() < 0) {
         // throw exception
      }
      if (cloneSeason.getDepartmentId() == 0 || cloneSeason.getDepartmentId() < 0) {
         // throw exception
      }
      try {
         Season existingSeason = seasonRepository.findOne(cloneSeason.getExistingSeasonId());
         if (existingSeason != null) {
            LookupDepartment department = existingSeason.getLookupDepartment();
            if (department != null) {
               if (department.getDepartmentName().equals(CCIConstants.DEPT_HIGH_SCHOOL_PROGRAMS)) {
                  Season season = seasonHelper.cloneHighLevelSeason(cloneSeason, existingSeason, department);
                  Season clonedHSPSeason = seasonRepository.saveAndFlush(season);
                  List<SeasonHSPAllocation> seasonHspallocations = existingSeason.getSeasonHspallocations();
                  List<SeasonHSPAllocation> seasonHspallocationNewList = null;
                  if (seasonHspallocations != null && seasonHspallocations.size() > 0) {
                     seasonHspallocationNewList = seasonHelper.cloneHSPAllocations(clonedHSPSeason, seasonHspallocations);
                  }
                  SeasonHSPConfiguration seasonHSPConfiguration = seasonHelper.cloneHSPConfiguration(cloneSeason, clonedHSPSeason);
                  SeasonJ1Detail seasonJ1Detail = seasonHelper.cloneHSPJ1seasonProgram(existingSeason, clonedHSPSeason);
                  SeasonF1Detail seasonF1Detail = seasonHelper.cloneHSPF1SeasonProgram(existingSeason, clonedHSPSeason);
                  if (seasonHspallocationNewList != null) {
                     seasonHSPAllocationRepository.save(seasonHspallocationNewList);
                  }
                  hspConfigurationRepsitory.save(seasonHSPConfiguration);
                  if (seasonJ1Detail != null) {
                     seasonJ1DetailsRepository.save(seasonJ1Detail);
                  }
                  if (seasonF1Detail != null) {
                     seasonF1DetailsRepository.save(seasonF1Detail);
                  }
               }
               if (department.getDepartmentName().equals(CCIConstants.DEPT_WORK_PROGRAMS)) {
                  Season season = seasonHelper.cloneHighLevelSeason(cloneSeason, existingSeason, department);
                  Season clonedWPSeason = seasonRepository.saveAndFlush(season);

                  List<SeasonWPAllocation> seasonWPAllocations = existingSeason.getSeasonWpallocations();
                  List<SeasonWPAllocation> seasonWPAallocationCloneList = null;
                  if (seasonWPAllocations != null && seasonWPAllocations.size() > 0) {
                     seasonWPAallocationCloneList = seasonHelper.cloneWPAllocations(clonedWPSeason, seasonWPAllocations);
                  }
                  SeasonWPConfiguration seasonWPConfiguration = seasonHelper.cloneWPConfigurations(cloneSeason, clonedWPSeason);
                  SeasonWnTSpringDetail seasonWnTSpringDetail = seasonHelper.cloneWPSprtingProgram(existingSeason, clonedWPSeason);
                  SeasonWnTSummerDetail seasonWnTSummerDetail = seasonHelper.cloneWPSummerProgram(existingSeason, clonedWPSeason);
                  SeasonWnTWinterDetail seasonWnTWinterDetail = seasonHelper.cloneWPWinterProgram(existingSeason, clonedWPSeason);
                  SeasonCAPDetail seasonCAPDetail = seasonHelper.cloneWPCapProgram(existingSeason, clonedWPSeason);
                  seasonWPAllocationRepository.save(seasonWPAallocationCloneList);
                  seasonWPConfigurationRepository.save(seasonWPConfiguration);
                  seasonWTSpringRepository.save(seasonWnTSpringDetail);
                  seasonWTSummerRepository.save(seasonWnTSummerDetail);
                  seasonWTWinterRepository.save(seasonWnTWinterDetail);
                  seasonCAPDetailsRepository.save(seasonCAPDetail);

               }
               if (department.getDepartmentName().equals(CCIConstants.DEPT_GREEN_HEART_TRAVEL)) {
                  Season season = seasonHelper.cloneHighLevelSeason(cloneSeason, existingSeason, department);
                  Season clonedGHTSeason = seasonRepository.saveAndFlush(season);
                  SeasonGHTConfiguration seasonGHTConfiguration = seasonHelper.cloneGHTConfiguration(cloneSeason, clonedGHTSeason);
                  SeasonHSADetail seasonHSADetail = seasonHelper.cloneGHTHSAProgram(existingSeason, clonedGHTSeason);
                  SeasonLSDetail seasonLSDetail = seasonHelper.cloneGHTLSProgram(existingSeason, clonedGHTSeason);
                  SeasonTADetail seasonTADetail = seasonHelper.cloneGHTTAProgram(existingSeason, clonedGHTSeason);
                  SeasonVADetail seasonVADetail = seasonHelper.cloneGHTVAProgram(existingSeason, clonedGHTSeason);
                  SeasonWADetail seasonWADetail = seasonHelper.cloneGHTWAProgram(existingSeason, clonedGHTSeason);
                  seasonGHTConfigurationRepository.save(seasonGHTConfiguration);
                  seasonHSADetailsRepository.save(seasonHSADetail);
                  seasonLSDetailsRepository.save(seasonLSDetail);
                  seasonTADetailsRepository.save(seasonTADetail);
                  seasonVADetailsRepository.save(seasonVADetail);
                  seasonWADetailsRepository.save(seasonWADetail);

               } else {
                  // update header type of department not applicable
               }
            }
         }
      } catch (CcighgoServiceException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return cloneSeason;
   }

   @Override
   @Transactional
   public SeasonDepartmentNotes addSeasonDepartmentNote(SeasonDepartmentNotes seasonDepartmentNotes) {
      SeasonDepartmentNotes returnObject = null;
      if (seasonDepartmentNotes.getSeasonId() > 0 && seasonDepartmentNotes.getNoteValue() != null) {
         try {
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
         } catch (CcighgoException e) {
            ExceptionUtil.logException(e, LOGGER);
         }
      }
      return returnObject;
   }

   @Override
   public SeasonDepartmentDocument addSeasonDepartmentDoc(SeasonDepartmentDocument seasonDepartmentDocument) {
      SeasonDepartmentDocument returnObject = null;
      if (seasonDepartmentDocument.getSeasonId() > 0 && seasonDepartmentDocument.getDocUrl() != null && seasonDepartmentDocument.getDocName() != null) {
         try {
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
         } catch (CcighgoException e) {
            ExceptionUtil.logException(e, LOGGER);
         }
      }
      return returnObject;
   }

   @Override
   public SeasonProgramNote addSeasonProgramNote(SeasonProgramNote seasonProgramNote) {
      SeasonProgramNote returnObject = null;
      if (seasonProgramNote.getSeasonId() > 0 && seasonProgramNote.getNoteValue() != null) {
         try {
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
         } catch (CcighgoException e) {
            ExceptionUtil.logException(e, LOGGER);
         }
      }
      return returnObject;
   }

   @Override
   public SeasonProgramDocument addSeasonProgramDoc(SeasonProgramDocument seasonProgramDocument) {
      SeasonProgramDocument returnObject = null;
      if (seasonProgramDocument.getSeasonId() > 0) {
         try {
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
         } catch (CcighgoException e) {
            ExceptionUtil.logException(e, LOGGER);
         }
      }
      return returnObject;
   }

}
