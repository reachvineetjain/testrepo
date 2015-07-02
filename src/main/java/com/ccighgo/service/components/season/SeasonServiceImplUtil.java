/**
 * 
 */
package com.ccighgo.service.components.season;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.DepartmentProgramOption;
import com.ccighgo.db.entities.LookupDepartment;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonCAPDetail;
import com.ccighgo.db.entities.SeasonDepartmentNote;
import com.ccighgo.db.entities.SeasonF1Detail;
import com.ccighgo.db.entities.SeasonHSADetail;
import com.ccighgo.db.entities.SeasonHSPConfiguration;
import com.ccighgo.db.entities.SeasonJ1Detail;
import com.ccighgo.db.entities.SeasonLSDetail;
import com.ccighgo.db.entities.SeasonProgramNote;
import com.ccighgo.db.entities.SeasonStatus;
import com.ccighgo.db.entities.SeasonTADetail;
import com.ccighgo.db.entities.SeasonVADetail;
import com.ccighgo.db.entities.SeasonWADetail;
import com.ccighgo.db.entities.SeasonWnTSpringDetail;
import com.ccighgo.db.entities.SeasonWnTSummerDetail;
import com.ccighgo.db.entities.SeasonWnTWinterDetail;
import com.ccighgo.jpa.repositories.DepartmentRepository;
import com.ccighgo.jpa.repositories.SeasonCAPDetailsRepository;
import com.ccighgo.jpa.repositories.SeasonDepartmentNotesRepository;
import com.ccighgo.jpa.repositories.SeasonF1DetailsRepository;
import com.ccighgo.jpa.repositories.SeasonHSADetailsRepository;
import com.ccighgo.jpa.repositories.SeasonHSPConfigurationRepsitory;
import com.ccighgo.jpa.repositories.SeasonJ1DetailsRepository;
import com.ccighgo.jpa.repositories.SeasonLSDetailsRepository;
import com.ccighgo.jpa.repositories.SeasonProgramNotesRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.jpa.repositories.SeasonStatusRepository;
import com.ccighgo.jpa.repositories.SeasonTADetailsRepository;
import com.ccighgo.jpa.repositories.SeasonVADetailsRepository;
import com.ccighgo.jpa.repositories.SeasonWADetailsRepository;
import com.ccighgo.jpa.repositories.SeasonWTWinterRepository;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection1Base;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection2Dates;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.SeasonGHTDetails;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSAugStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSFieldSettings;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSJanStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSNotes;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.SeasonWPDetails;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.WPBasicDetail;
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
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1ProgramAllocationDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1ProgramAllocations;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.SeasonHSPF1Details;
import com.ccighgo.service.transport.seasons.beans.seasonslist.DepartmentObject;
import com.ccighgo.service.transport.seasons.beans.seasonslist.ProgramSeason;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonListObject;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.SeasonWPCAPDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPBasicDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPInternshipDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPTraineeDetails;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.ValidationUtils;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Component
public class SeasonServiceImplUtil {

   private Logger logger = LoggerFactory.getLogger(SeasonServiceImplUtil.class);

   @Autowired
   SeasonStatusRepository seasonStatusRepository;
   @Autowired
   DepartmentRepository departmentRepository;
   @Autowired
   SeasonHSPConfigurationRepsitory seasonHSPConfigurationRepsitory;
   @Autowired
   SeasonF1DetailsRepository seasonF1DetailsRepository;
   @Autowired
   SeasonJ1DetailsRepository seasonJ1DetailsRepository;
   @Autowired
   SeasonRepository seasonRepository;
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
   SeasonCAPDetailsRepository seasonCAPDetailsRepository;
   @Autowired
   SeasonWTWinterRepository seasonWinterRepository;
   @Autowired
   SeasonDepartmentNotesRepository seasonDepartmentNotesRepository;
   @Autowired
   SeasonProgramNotesRepository seasonProgramNotesRepository;

   /**
    * @param seasonBean
    * @param seasonEntity
    */
   public void convertEntitySeasonToBeanSeason(SeasonBean seasonBean, Season seasonEntity) {
      seasonBean.setSeasonId(seasonEntity.getSeasonId());
      seasonBean.setDepartmentId(seasonEntity.getLookupDepartment() != null ? seasonEntity.getLookupDepartment().getDepartmentId() : -1);
      seasonBean.setSeasonName(seasonEntity.getSeasonName() != null ? seasonEntity.getSeasonName() : CCIConstants.EMPTY_DATA);
      if (seasonEntity.getSeasonStatus() != null) {
         seasonBean.setStatus(seasonEntity.getSeasonStatus() != null ? seasonEntity.getSeasonStatus().getStatus() : CCIConstants.EMPTY_DATA);
         seasonBean.setStatusId(seasonEntity.getSeasonStatus() != null ? seasonEntity.getSeasonStatus().getSeasonStatusId() : CCIConstants.EMPTY_INTEGER_FIELD);
      }

      String startDate = CCIConstants.EMPTY_DATA, endDate = CCIConstants.EMPTY_DATA;
      Integer seasonHspConfId = CCIConstants.EMPTY_INTEGER_FIELD;

      if (seasonEntity.getSeasonHspconfigurations() != null)
         for (SeasonHSPConfiguration seasonconf : seasonEntity.getSeasonHspconfigurations()) {
            if (seasonconf.getSeason() != null)
               if (seasonconf.getSeason().getSeasonId() == seasonEntity.getSeasonId()) {
                  startDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonStartDate());
                  endDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonEndDate());
                  seasonHspConfId = seasonconf.getSeasonHSPConfigurationId();
               }
         }
      seasonBean.setStartDate(startDate);
      seasonBean.setEndDate(endDate);
      seasonBean.setSeasonHSPConfigurationId(seasonHspConfId);

      if (seasonEntity.getLookupDepartment() != null) {
         if (seasonEntity.getLookupDepartment().getDepartmentPrograms() != null) {
            for (DepartmentProgram departmentProgram : seasonEntity.getLookupDepartment().getDepartmentPrograms()) {
               if (departmentProgram.getLookupDepartment().getDepartmentId() == seasonEntity.getLookupDepartment().getDepartmentId()
                     && departmentProgram.getDepartmentProgramOptions() != null) {
                  for (DepartmentProgramOption departmentProgramOption : departmentProgram.getDepartmentProgramOptions()) {
                     if (departmentProgramOption.getDepartmentProgram().getDepartmentProgramId() == departmentProgram.getDepartmentProgramId())
                        seasonBean.getProgramOptions().add(mapProgramOptionEntityToBean2(departmentProgramOption));
                  }
               }
            }
         }
      }
      if (seasonEntity.getSeasonDepartmentNotes() != null && !seasonEntity.getSeasonDepartmentNotes().isEmpty()) {
         for (SeasonDepartmentNote note : seasonEntity.getSeasonDepartmentNotes()) {
            SeasonDepartmentNotes seasonDepartmentNotes = new SeasonDepartmentNotes();
            seasonDepartmentNotes.setSeasonId(seasonEntity.getSeasonId());
            seasonDepartmentNotes.setActive(note.getActive() == 1);
            seasonDepartmentNotes.setNoteValue(note.getDepartmentNote());
            seasonDepartmentNotes.setSeasonDepartmenNotetId(note.getSeasonDepartmentNotesId());
            seasonBean.getNotes().add(seasonDepartmentNotes);
         }
      }
   }

   private com.ccighgo.service.transport.seasons.beans.season.ProgramOptions mapProgramOptionEntityToBean2(DepartmentProgramOption departmentProgramOption) {
      com.ccighgo.service.transport.seasons.beans.season.ProgramOptions programOptions = new com.ccighgo.service.transport.seasons.beans.season.ProgramOptions();
      programOptions.setProgramOptionsID(departmentProgramOption.getDepartmentProgramOptionId());
      programOptions.setProgramOptionsCode(departmentProgramOption.getProgramOptionCode() != null ? departmentProgramOption.getProgramOptionCode() : CCIConstants.EMPTY_DATA);
      programOptions.setProgramOptionsName(departmentProgramOption.getProgramOptionName() != null ? departmentProgramOption.getProgramOptionName() : CCIConstants.EMPTY_DATA);
      return programOptions;
   }

   /**
    * @param seasonBean
    * @param seasonEntity
    */
   public void convertEntitySeasonToSeasonListObject(SeasonListObject seasonBean, Season seasonEntity) {
      seasonBean.setSeasonId(seasonEntity.getSeasonId());
      seasonBean.setSeasonName(seasonEntity.getSeasonName());
      if (seasonEntity.getSeasonStatus() != null) {
         seasonBean.setStatus(seasonEntity.getSeasonStatus() != null ? seasonEntity.getSeasonStatus().getStatus() : CCIConstants.EMPTY_DATA);
         seasonBean.setStatusId(seasonEntity.getSeasonStatus() != null ? seasonEntity.getSeasonStatus().getSeasonStatusId() : CCIConstants.EMPTY_INTEGER_FIELD);
      }
      seasonBean.setDepartment(getDepartmentBean(seasonEntity.getLookupDepartment()));
      String startDate = CCIConstants.EMPTY_DATA, endDate = CCIConstants.EMPTY_DATA;
      Integer seasonHspConfId = CCIConstants.EMPTY_INTEGER_FIELD;

      if (seasonEntity.getSeasonHspconfigurations() != null)
         for (SeasonHSPConfiguration seasonconf : seasonEntity.getSeasonHspconfigurations()) {
            if (seasonconf.getSeason() != null)
               if (seasonconf.getSeason().getSeasonId() == seasonEntity.getSeasonId()) {
                  startDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonStartDate());
                  endDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonEndDate());
                  seasonHspConfId = seasonconf.getSeasonHSPConfigurationId();
               }
         }
      seasonBean.setStartDate(startDate);
      seasonBean.setEndDate(endDate);
      seasonBean.setSeasonHSPConfigurationId(seasonHspConfId);

      if (seasonEntity.getLookupDepartment() != null) {
         if (seasonEntity.getLookupDepartment().getDepartmentName().equals(CCIConstants.DEPT_HIGH_SCHOOL_PROGRAMS)) {
            if (seasonEntity.getSeasonJ1details() != null && seasonEntity.getSeasonJ1details().size() > 0) {
               ProgramSeason programSeason = new ProgramSeason();
               programSeason.setSeasonProgramId(seasonEntity.getSeasonJ1details().get(0).getSeasonJ1DetailsId());
               programSeason.setSeasonProgramName(seasonEntity.getSeasonJ1details().get(0).getProgramName());
               programSeason.setSeasonProgramUrl(CCIConstants.HSP_J1_URL);
               seasonBean.getSeasonPrograms().add(programSeason);
            }
            if (seasonEntity.getSeasonF1details() != null && seasonEntity.getSeasonF1details().size() > 0) {
               ProgramSeason programSeason = new ProgramSeason();
               programSeason.setSeasonProgramId(seasonEntity.getSeasonF1details().get(0).getSeasonF1DetailsId());
               programSeason.setSeasonProgramName(seasonEntity.getSeasonF1details().get(0).getProgramName());
               programSeason.setSeasonProgramUrl(CCIConstants.HSP_F1_URL);
               seasonBean.getSeasonPrograms().add(programSeason);
            }
            // TODO implement when STP tables are available
         }
         if (seasonEntity.getLookupDepartment().getDepartmentName().equals(CCIConstants.DEPT_WORK_PROGRAMS)) {
            if (seasonEntity.getSeasonWnTsummerDetails() != null && seasonEntity.getSeasonWnTsummerDetails().size() > 0) {
               ProgramSeason programSeason = new ProgramSeason();
               programSeason.setSeasonProgramId(seasonEntity.getSeasonWnTsummerDetails().get(0).getSeasonWnTSummerDetailsId());
               programSeason.setSeasonProgramName(seasonEntity.getSeasonWnTsummerDetails().get(0).getProgramName());
               programSeason.setSeasonProgramUrl(CCIConstants.WP_SUMM_URL);
               seasonBean.getSeasonPrograms().add(programSeason);
            }
            if (seasonEntity.getSeasonWnTwinterDetails() != null && seasonEntity.getSeasonWnTwinterDetails().size() > 0) {
               ProgramSeason programSeason = new ProgramSeason();
               programSeason.setSeasonProgramId(seasonEntity.getSeasonWnTwinterDetails().get(0).getSeasonWnTWinterDetailsId());
               programSeason.setSeasonProgramName(seasonEntity.getSeasonWnTwinterDetails().get(0).getProgramName());
               programSeason.setSeasonProgramUrl(CCIConstants.WP_WINT_URL);
               seasonBean.getSeasonPrograms().add(programSeason);
            }
            if (seasonEntity.getSeasonWnTspringDetails() != null && seasonEntity.getSeasonWnTspringDetails().size() > 0) {
               ProgramSeason programSeason = new ProgramSeason();
               programSeason.setSeasonProgramId(seasonEntity.getSeasonWnTspringDetails().get(0).getSeasonWnTSpringDetailsId());
               programSeason.setSeasonProgramName(seasonEntity.getSeasonWnTspringDetails().get(0).getProgramName());
               programSeason.setSeasonProgramUrl(CCIConstants.WP_SPRING_URL);
               seasonBean.getSeasonPrograms().add(programSeason);
            }
            if (seasonEntity.getSeasonCapdetails() != null && seasonEntity.getSeasonCapdetails().size() > 0) {
               ProgramSeason programSeason = new ProgramSeason();
               programSeason.setSeasonProgramId(seasonEntity.getSeasonCapdetails().get(0).getSeasonCAPDetailsId());
               programSeason.setSeasonProgramName(seasonEntity.getSeasonCapdetails().get(0).getProgramName());
               programSeason.setSeasonProgramUrl(CCIConstants.WP_CAP_URL);
               seasonBean.getSeasonPrograms().add(programSeason);
            }
         }
         if (seasonEntity.getLookupDepartment().getDepartmentName().equals(CCIConstants.DEPT_GREEN_HEART_TRAVEL)) {
            if (seasonEntity.getSeasonHsadetails() != null && seasonEntity.getSeasonHsadetails().size() > 0) {
               ProgramSeason programSeason = new ProgramSeason();
               programSeason.setSeasonProgramId(seasonEntity.getSeasonHsadetails().get(0).getSeasonHSADetailsId());
               programSeason.setSeasonProgramName(seasonEntity.getSeasonHsadetails().get(0).getProgramName());
               programSeason.setSeasonProgramUrl(CCIConstants.GHT_HSA_URL);
               seasonBean.getSeasonPrograms().add(programSeason);
            }
            if (seasonEntity.getSeasonLsdetails() != null && seasonEntity.getSeasonLsdetails().size() > 0) {
               ProgramSeason programSeason = new ProgramSeason();
               programSeason.setSeasonProgramId(seasonEntity.getSeasonLsdetails().get(0).getSeasonLSDetailsId());
               programSeason.setSeasonProgramName(seasonEntity.getSeasonLsdetails().get(0).getProgramName());
               programSeason.setSeasonProgramUrl(CCIConstants.GHT_LS_URL);
               seasonBean.getSeasonPrograms().add(programSeason);
            }
            if (seasonEntity.getSeasonTadetails() != null && seasonEntity.getSeasonTadetails().size() > 0) {
               ProgramSeason programSeason = new ProgramSeason();
               programSeason.setSeasonProgramId(seasonEntity.getSeasonTadetails().get(0).getSeasonTADetailsId());
               programSeason.setSeasonProgramName(seasonEntity.getSeasonTadetails().get(0).getProgramName());
               programSeason.setSeasonProgramUrl(CCIConstants.GHT_TA_URL);
               seasonBean.getSeasonPrograms().add(programSeason);
            }
            if (seasonEntity.getSeasonVadetails() != null && seasonEntity.getSeasonVadetails().size() > 0) {
               ProgramSeason programSeason = new ProgramSeason();
               programSeason.setSeasonProgramId(seasonEntity.getSeasonVadetails().get(0).getSeasonVADetailsId());
               programSeason.setSeasonProgramName(seasonEntity.getSeasonVadetails().get(0).getProgramName());
               programSeason.setSeasonProgramUrl(CCIConstants.GHT_VA_URL);
               seasonBean.getSeasonPrograms().add(programSeason);
            }
            if (seasonEntity.getSeasonWadetails() != null && seasonEntity.getSeasonWadetails().size() > 0) {
               ProgramSeason programSeason = new ProgramSeason();
               programSeason.setSeasonProgramId(seasonEntity.getSeasonWadetails().get(0).getSeasonWADetailsId());
               programSeason.setSeasonProgramName(seasonEntity.getSeasonWadetails().get(0).getProgramName());
               programSeason.setSeasonProgramUrl(CCIConstants.GHT_WA_URL);
               seasonBean.getSeasonPrograms().add(programSeason);
            }
         }
      }
   }

   /**
    * @param department
    * @return
    */
   private DepartmentObject getDepartmentBean(LookupDepartment department) {
      if (department != null) {
         DepartmentObject departmentObject = new DepartmentObject();
         departmentObject.setDepartmentId(department.getDepartmentId());
         departmentObject.setDepartmentCode(department.getAcronym() != null ? department.getAcronym() : CCIConstants.EMPTY_DATA);
         departmentObject.setDepartmentName(department.getDepartmentName() != null ? department.getAcronym() : CCIConstants.EMPTY_DATA);
         return departmentObject;
      }
      return null;
   }

   /**
    * @param seasonBean
    * @param seasonEntity
    * @param update
    */
   public void convertSeasonBeanToSeasonEntity(SeasonBean seasonBean, Season seasonEntity, boolean update) {
      if (update) {
         ValidationUtils.validateRequired(seasonBean.getSeasonId() + "");
         seasonEntity.setSeasonId(seasonBean.getSeasonId());
      }
      ValidationUtils.validateRequired(seasonBean.getDepartmentId() + "");
      LookupDepartment department = departmentRepository.findOne(seasonBean.getDepartmentId());
      seasonEntity.setLookupDepartment(department);

      ValidationUtils.validateRequired(seasonBean.getSeasonName());
      seasonEntity.setSeasonName(seasonBean.getSeasonName());

      SeasonStatus seasonStatus = seasonStatusRepository.findOne(CCIConstants.DRAFT_STATUS_NO);
      seasonEntity.setSeasonStatus(seasonStatus);
      seasonEntity.setCreatedBy(1);
      seasonEntity.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
      seasonEntity.setModifiedBy(1);
      seasonEntity.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      seasonEntity.setSeasonFullName(seasonBean.getSeasonName());
   }

   public void createSeasonHspConfiguration(SeasonBean seasonBean, Season seasonEntity) {
      SeasonHSPConfiguration seasonHSPConfiguration = new SeasonHSPConfiguration();
      seasonHSPConfiguration.setSeason(seasonEntity);
      seasonHSPConfiguration.setSeasonEndDate(DateUtils.getDateFromString(seasonBean.getEndDate()));
      seasonHSPConfiguration.setSeasonStartDate(DateUtils.getDateFromString(seasonBean.getStartDate()));
      seasonHSPConfiguration.setCreatedBy(1);
      seasonHSPConfiguration.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
      seasonHSPConfiguration.setModifiedBy(1);
      seasonHSPConfiguration.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      seasonHSPConfigurationRepsitory.saveAndFlush(seasonHSPConfiguration);
   }

   public void updateSeasonHspConfiguration(SeasonBean seasonBean, Season seasonEntity) {
      SeasonHSPConfiguration seasonHSPConfiguration = seasonHSPConfigurationRepsitory.getSeasonHSPConfigurationBySeasonId(seasonEntity.getSeasonId());
      if (seasonHSPConfiguration == null)
         return;
      seasonHSPConfiguration.setSeason(seasonEntity);
      seasonHSPConfiguration.setSeasonEndDate(DateUtils.getDateFromString(seasonBean.getEndDate()));
      seasonHSPConfiguration.setSeasonStartDate(DateUtils.getDateFromString(seasonBean.getStartDate()));
      seasonHSPConfiguration.setSeasonHSPConfigurationId(seasonBean.getSeasonHSPConfigurationId());
      seasonHSPConfigurationRepsitory.saveAndFlush(seasonHSPConfiguration);
   }

   public SeasonStatuses getSeasonStatus() {
      SeasonStatuses seasonStatuses = null;
      Sort sort = new Sort(Sort.Direction.ASC, "status");
      List<SeasonStatus> seasonStatusDBList = seasonStatusRepository.findAll(sort);
      if (seasonStatusDBList != null) {
         seasonStatuses = new SeasonStatuses();
         List<com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus> seasonStatusList = new ArrayList<com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus>();
         for (SeasonStatus ss : seasonStatusDBList) {
            com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus status = new com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus();
            status.setSeasonStatusId(ss.getSeasonStatusId());
            status.setSeasonStatus(ss.getStatus());
            status.setActive(ss.getActive() == CCIConstants.ACTIVE ? true : false);
            seasonStatusList.add(status);
         }
         seasonStatuses.getSeasonStatuses().addAll(seasonStatusList);
      }
      return seasonStatuses;
   }

   public HSPF1BasicDetails getHSPF1NameAndStatus(SeasonF1Detail allF1Details) {
      HSPF1BasicDetails hspf1BasicDetails = null;
      if (allF1Details != null) {
         hspf1BasicDetails = new HSPF1BasicDetails();
         hspf1BasicDetails.setSeasonId(allF1Details.getSeason().getSeasonId());
         hspf1BasicDetails.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
         hspf1BasicDetails.setProgramName(allF1Details.getProgramName());
         if (allF1Details.getSeasonStatus() != null)
            hspf1BasicDetails.setProgramStatus(allF1Details.getSeasonStatus().getStatus());
      }

      return hspf1BasicDetails;
   }

   public HSPF1Accounting getHSPF1Accounting(SeasonF1Detail allF1Details) {
      HSPF1Accounting hspf1Accounting = null;
      if (allF1Details != null) {
         hspf1Accounting = new HSPF1Accounting();
         hspf1Accounting.setSeasonId(allF1Details.getSeason().getSeasonId());
         hspf1Accounting.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
         hspf1Accounting.setGreenHeartMargin(allF1Details.getGreenHeartMargin());
      }
      return hspf1Accounting;
   }

   public HSPF1JanuaryStart2NdSemesterDetails getHSPF1JanuaryStart2NdSemesterDetails(SeasonF1Detail allF1Details) {
      HSPF1JanuaryStart2NdSemesterDetails hspf1JanuaryStart2NdSemesterDetails = null;
      if (allF1Details != null) {
         hspf1JanuaryStart2NdSemesterDetails = new HSPF1JanuaryStart2NdSemesterDetails();
         hspf1JanuaryStart2NdSemesterDetails.setSeasonId(allF1Details.getSeason().getSeasonId());
         hspf1JanuaryStart2NdSemesterDetails.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
         hspf1JanuaryStart2NdSemesterDetails.setActivateFullYearProgram(allF1Details.getActiveFullYearJanProgram() != 0);
         hspf1JanuaryStart2NdSemesterDetails.setApplicationDeadlineDate(DateUtils.getMMddYyyyString(allF1Details.getSecondSemAppDeadlineDate()));
         hspf1JanuaryStart2NdSemesterDetails.setStartDate(DateUtils.getMMddYyyyString(allF1Details.getSecondSemStartDate()));
         hspf1JanuaryStart2NdSemesterDetails.setEndDate(DateUtils.getMMddYyyyString(allF1Details.getSecondSemEndDate()));
         hspf1JanuaryStart2NdSemesterDetails.setShow2NdSemestertoNewHF(allF1Details.getShowSecSemToNewHF() != 0);
         hspf1JanuaryStart2NdSemesterDetails.setEarliestBirthDate(DateUtils.getMMddYyyyString(allF1Details.getSecondSemEarliestBirthDate()));
         hspf1JanuaryStart2NdSemesterDetails.setLatestBirthDate(DateUtils.getMMddYyyyString(allF1Details.getSecondSemLatestBirthDate()));
      }
      return hspf1JanuaryStart2NdSemesterDetails;
   }

   public HSPF1AugustStartFullYearDetails getHSPF1AugustStartFullYearDetails(SeasonF1Detail allF1Details) {
      HSPF1AugustStartFullYearDetails hspAugustStartFullYearDetails = null;
      if (allF1Details != null) {
         hspAugustStartFullYearDetails = new HSPF1AugustStartFullYearDetails();
         hspAugustStartFullYearDetails.setSeasonId(allF1Details.getSeason().getSeasonId());
         hspAugustStartFullYearDetails.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
         hspAugustStartFullYearDetails.setApplicationDeadlineDate(DateUtils.getMMddYyyyString(allF1Details.getAugFullYearAppDeadlineDate()));
         hspAugustStartFullYearDetails.setEndDate(DateUtils.getMMddYyyyString(allF1Details.getAugFullYearEndDate()));
         hspAugustStartFullYearDetails.setShowFullYearToNewHF(allF1Details.getShowAugFullYearToNewHF() != 0);
         hspAugustStartFullYearDetails.setStartDate(DateUtils.getMMddYyyyString(allF1Details.getAugFullYearStartDate()));
      }
      return hspAugustStartFullYearDetails;
   }

   public HSPF1AugustStart1StSemesterDetails getHSPF1AugustStart1StSemesterDetails(SeasonF1Detail allF1Details) {
      HSPF1AugustStart1StSemesterDetails hspf1AugustStart1StSemesterDetails = null;
      if (allF1Details != null) {
         hspf1AugustStart1StSemesterDetails = new HSPF1AugustStart1StSemesterDetails();
         hspf1AugustStart1StSemesterDetails.setSeasonId(allF1Details.getSeason().getSeasonId());
         hspf1AugustStart1StSemesterDetails.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
         hspf1AugustStart1StSemesterDetails.setApplicationDeadlineDate(DateUtils.getMMddYyyyString(allF1Details.getFirstSemAppDeadlineDate()));
         hspf1AugustStart1StSemesterDetails.setEarliestBirthDate(DateUtils.getMMddYyyyString(allF1Details.getFirstSemEarliestBirthDate()));
         hspf1AugustStart1StSemesterDetails.setLatestBirthDate(DateUtils.getMMddYyyyString(allF1Details.getFirstSemLatestBirthDate()));
         hspf1AugustStart1StSemesterDetails.setStartDate(DateUtils.getMMddYyyyString(allF1Details.getFirstSemStartDate()));
         hspf1AugustStart1StSemesterDetails.setEndDate(DateUtils.getMMddYyyyString(allF1Details.getFirstSemEndDate()));
      }
      return hspf1AugustStart1StSemesterDetails;
   }

   public HSPF1FieldSettings getHSPF1FieldSettings(SeasonF1Detail allF1Details) {
      HSPF1FieldSettings hspf1FieldSettings = null;
      if (allF1Details != null) {
         hspf1FieldSettings = new HSPF1FieldSettings();
         hspf1FieldSettings.setSeasonId(allF1Details.getSeason().getSeasonId());
         hspf1FieldSettings.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
         hspf1FieldSettings.setAddOrStartHFInquiriesDate(DateUtils.getMMddYyyyString(allF1Details.getHfInquiryDate()));
         hspf1FieldSettings.setAllowFSToStartRenewalProcess(allF1Details.getAllowFieldStaffToStartRenewalProcess() != 0);
         // hspf1FieldSettings.setDefaultLcPaymentSchedule(allF1Details.getLcPaymentScheduleId());
         // hspf1FieldSettings.setFsAgreement(allF1Details.getFsAgreementId());
         hspf1FieldSettings.setHfReferencesNo(allF1Details.getHfReferences());
         hspf1FieldSettings.setShowSeasonProgramToCurrentHF(allF1Details.getShowSeasonToCurrentHF() != 0);
         hspf1FieldSettings.setShowSpecialRequestStudentToRD(allF1Details.getShowSpecialRequestStudent() != 0);
         hspf1FieldSettings.setShowWelcomeFamilyModle(allF1Details.getShowWelcomeFamily() != 0);
      }
      return hspf1FieldSettings;
   }

   public HSPF1ProgramAllocations getHSPF1ProgramAllocations(SeasonF1Detail allF1Details) {
      HSPF1ProgramAllocations hspf1ProgramAllocations = new HSPF1ProgramAllocations();
      hspf1ProgramAllocations.setSeasonId(allF1Details.getSeason().getSeasonId());
      hspf1ProgramAllocations.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
      HSPF1ProgramAllocationDetails hspf1ProgramAllocationDetails = null;
      if (allF1Details != null) {
         hspf1ProgramAllocationDetails = new HSPF1ProgramAllocationDetails();
      }
      // TODO not complete
      hspf1ProgramAllocations.getProgramAllocationDetails().add(hspf1ProgramAllocationDetails);
      return hspf1ProgramAllocations;
   }

   public HSPF1JanuaryStartFullYearDetail getHSPF1JanuaryStartFullYearDetails(SeasonF1Detail allF1Details) {
      HSPF1JanuaryStartFullYearDetail hspf1JanuaryStartFullYearDetail = null;
      if (allF1Details != null) {
         hspf1JanuaryStartFullYearDetail = new HSPF1JanuaryStartFullYearDetail();
         hspf1JanuaryStartFullYearDetail.setSeasonId(allF1Details.getSeason().getSeasonId());
         hspf1JanuaryStartFullYearDetail.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
         hspf1JanuaryStartFullYearDetail.setApplicationDeadlineDate(DateUtils.getMMddYyyyString(allF1Details.getJanFullYearAppDeadlineDate()));
         hspf1JanuaryStartFullYearDetail.setEndDate(DateUtils.getMMddYyyyString(allF1Details.getJanFullYearEndDate()));
         hspf1JanuaryStartFullYearDetail.setStartDate(DateUtils.getMMddYyyyString(allF1Details.getJanFullYearStartDate()));
         hspf1JanuaryStartFullYearDetail.setShowFullYearToHF(allF1Details.getShowJanFullYearToNewHF() != 0);
      }
      return hspf1JanuaryStartFullYearDetail;
   }

   public SeasonHSPF1Details updateF1Details(SeasonF1Detail allF1Details, SeasonHSPF1Details seasonHSPF1Details) {
      try {
         allF1Details.setSeasonStatus(seasonStatusRepository.getSeasonStatusByName(seasonHSPF1Details.getDetails().getProgramStatus()));
         allF1Details.setProgramName(seasonHSPF1Details.getDetails().getProgramName());
         allF1Details.setGreenHeartMargin(seasonHSPF1Details.getAccounting().getGreenHeartMargin());
         allF1Details.setActiveFullYearJanProgram((byte) (seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().isActivateFullYearProgram() ? 1 : 0));
         allF1Details.setShowSecSemToNewHF((byte) (seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().isShow2NdSemestertoNewHF() ? 1 : 0));
         allF1Details.setSecondSemAppDeadlineDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getApplicationDeadlineDate()));
         allF1Details.setSecondSemEarliestBirthDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getEarliestBirthDate()));
         allF1Details.setSecondSemEndDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getEndDate()));
         allF1Details.setSecondSemLatestBirthDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getLatestBirthDate()));
         allF1Details.setSecondSemStartDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getStartDate()));
         allF1Details.setJanFullYearAppDeadlineDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStartFullYearDetail().getApplicationDeadlineDate()));
         allF1Details.setJanFullYearEndDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStartFullYearDetail().getEndDate()));
         allF1Details.setJanFullYearStartDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStartFullYearDetail().getStartDate()));
         allF1Details.setShowJanFullYearToNewHF((byte) (seasonHSPF1Details.getJanuaryStartFullYearDetail().isShowFullYearToHF() ? 1 : 0));
         allF1Details.setFirstSemAppDeadlineDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getApplicationDeadlineDate()));
         allF1Details.setFirstSemEarliestBirthDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getEarliestBirthDate()));
         allF1Details.setFirstSemLatestBirthDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getLatestBirthDate()));
         allF1Details.setFirstSemStartDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getStartDate()));
         allF1Details.setFirstSemEndDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getEndDate()));
         allF1Details.setAugFullYearAppDeadlineDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStartFullYearDetails().getApplicationDeadlineDate()));
         allF1Details.setAugFullYearEndDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStartFullYearDetails().getEndDate()));
         allF1Details.setAugFullYearStartDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStartFullYearDetails().getStartDate()));
         allF1Details.setShowAugFullYearToNewHF((byte) (seasonHSPF1Details.getAugustStartFullYearDetails().isShowFullYearToNewHF() ? 1 : 0));
         allF1Details.setHfInquiryDate(DateUtils.getDateFromString(seasonHSPF1Details.getFieldSettings().getAddOrStartHFInquiriesDate()));
         allF1Details.setAllowFieldStaffToStartRenewalProcess((byte) (seasonHSPF1Details.getFieldSettings().isAllowFSToStartRenewalProcess() ? 1 : 0));
         // allF1Details.setLcPaymentScheduleId(seasonHSPF1Details.getFieldSettings().getDefaultLcPaymentSchedule());
         // allF1Details.setFsAgreementId(seasonHSPF1Details.getFieldSettings().getFsAgreement());
         allF1Details.setHfReferences(seasonHSPF1Details.getFieldSettings().getHfReferencesNo());
         allF1Details.setShowSeasonToCurrentHF((byte) (seasonHSPF1Details.getFieldSettings().isShowSeasonProgramToCurrentHF() ? 1 : 0));
         allF1Details.setShowSpecialRequestStudent((byte) (seasonHSPF1Details.getFieldSettings().isShowSpecialRequestStudentToRD() ? 1 : 0));
         allF1Details.setShowWelcomeFamily((byte) (seasonHSPF1Details.getFieldSettings().isShowWelcomeFamilyModle() ? 1 : 0));

         seasonF1DetailsRepository.saveAndFlush(allF1Details);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return seasonHSPF1Details;
   }

   public HSPF1BasicDetails updateHSPF1NameAndStatus(SeasonF1Detail currentF1Detail, HSPF1BasicDetails hspf1BasicDetails) {
      try {
         currentF1Detail.setSeasonStatus(seasonStatusRepository.getSeasonStatusByName(hspf1BasicDetails.getProgramStatus()));
         currentF1Detail.setProgramName(hspf1BasicDetails.getProgramName());
         seasonF1DetailsRepository.saveAndFlush(currentF1Detail);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return hspf1BasicDetails;
   }

   public HSPF1Accounting updateF1Accounting(SeasonF1Detail currentF1Detail, HSPF1Accounting hspf1Accounting) {
      try {
         currentF1Detail.setGreenHeartMargin(hspf1Accounting.getGreenHeartMargin());
         seasonF1DetailsRepository.saveAndFlush(currentF1Detail);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return hspf1Accounting;
   }

   public HSPF1JanuaryStart2NdSemesterDetails updateF1JanStart2NdSemesterDetails(SeasonF1Detail allF1Details,
         HSPF1JanuaryStart2NdSemesterDetails hspf1JanuaryStart2NdSemesterDetails) {
      try {
         allF1Details.setActiveFullYearJanProgram((byte) (hspf1JanuaryStart2NdSemesterDetails.isActivateFullYearProgram() ? 1 : 0));
         allF1Details.setShowSecSemToNewHF((byte) (hspf1JanuaryStart2NdSemesterDetails.isShow2NdSemestertoNewHF() ? 1 : 0));
         allF1Details.setSecondSemAppDeadlineDate(DateUtils.getDateFromString(hspf1JanuaryStart2NdSemesterDetails.getApplicationDeadlineDate()));
         allF1Details.setSecondSemEarliestBirthDate(DateUtils.getDateFromString(hspf1JanuaryStart2NdSemesterDetails.getEarliestBirthDate()));
         allF1Details.setSecondSemEndDate(DateUtils.getDateFromString(hspf1JanuaryStart2NdSemesterDetails.getEndDate()));
         allF1Details.setSecondSemLatestBirthDate(DateUtils.getDateFromString(hspf1JanuaryStart2NdSemesterDetails.getLatestBirthDate()));
         allF1Details.setSecondSemStartDate(DateUtils.getDateFromString(hspf1JanuaryStart2NdSemesterDetails.getStartDate()));
         seasonF1DetailsRepository.saveAndFlush(allF1Details);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return hspf1JanuaryStart2NdSemesterDetails;
   }

   public HSPF1JanuaryStartFullYearDetail updateF1JanStartFullYearDetails(SeasonF1Detail allF1Details, HSPF1JanuaryStartFullYearDetail hspf1JanuaryStartFullYearDetail) {
      try {
         allF1Details.setJanFullYearAppDeadlineDate(DateUtils.getDateFromString(hspf1JanuaryStartFullYearDetail.getApplicationDeadlineDate()));
         allF1Details.setJanFullYearEndDate(DateUtils.getDateFromString(hspf1JanuaryStartFullYearDetail.getEndDate()));
         allF1Details.setJanFullYearStartDate(DateUtils.getDateFromString(hspf1JanuaryStartFullYearDetail.getStartDate()));
         allF1Details.setShowJanFullYearToNewHF((byte) (hspf1JanuaryStartFullYearDetail.isShowFullYearToHF() ? 1 : 0));
         seasonF1DetailsRepository.saveAndFlush(allF1Details);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return hspf1JanuaryStartFullYearDetail;
   }

   public HSPF1AugustStart1StSemesterDetails updateF1AugStart1StSemesterDetails(SeasonF1Detail allF1Details, HSPF1AugustStart1StSemesterDetails hspf1AugustStart1StSemesterDetails) {
      try {
         allF1Details.setFirstSemAppDeadlineDate(DateUtils.getDateFromString(hspf1AugustStart1StSemesterDetails.getApplicationDeadlineDate()));
         allF1Details.setFirstSemEarliestBirthDate(DateUtils.getDateFromString(hspf1AugustStart1StSemesterDetails.getEarliestBirthDate()));
         allF1Details.setFirstSemLatestBirthDate(DateUtils.getDateFromString(hspf1AugustStart1StSemesterDetails.getLatestBirthDate()));
         allF1Details.setFirstSemStartDate(DateUtils.getDateFromString(hspf1AugustStart1StSemesterDetails.getStartDate()));
         allF1Details.setFirstSemEndDate(DateUtils.getDateFromString(hspf1AugustStart1StSemesterDetails.getEndDate()));
         seasonF1DetailsRepository.saveAndFlush(allF1Details);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return hspf1AugustStart1StSemesterDetails;
   }

   public HSPF1AugustStartFullYearDetails updateF1AugStartFullYearDetails(SeasonF1Detail allF1Details, HSPF1AugustStartFullYearDetails hspf1AugustStartFullYearDetails) {
      try {
         allF1Details.setAugFullYearAppDeadlineDate(DateUtils.getDateFromString(hspf1AugustStartFullYearDetails.getApplicationDeadlineDate()));
         allF1Details.setAugFullYearEndDate(DateUtils.getDateFromString(hspf1AugustStartFullYearDetails.getEndDate()));
         allF1Details.setAugFullYearStartDate(DateUtils.getDateFromString(hspf1AugustStartFullYearDetails.getStartDate()));
         allF1Details.setShowAugFullYearToNewHF((byte) (hspf1AugustStartFullYearDetails.isShowFullYearToNewHF() ? 1 : 0));

         seasonF1DetailsRepository.saveAndFlush(allF1Details);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return hspf1AugustStartFullYearDetails;
   }

   public HSPF1FieldSettings updateF1FieldSettings(SeasonF1Detail allF1Details, HSPF1FieldSettings hspf1FieldSettings) {
      try {
         allF1Details.setHfInquiryDate(DateUtils.getDateFromString(hspf1FieldSettings.getAddOrStartHFInquiriesDate()));
         allF1Details.setAllowFieldStaffToStartRenewalProcess((byte) (hspf1FieldSettings.isAllowFSToStartRenewalProcess() ? 1 : 0));
         // allF1Details.setLcPaymentScheduleId(hspf1FieldSettings.getDefaultLcPaymentSchedule());
         // allF1Details.setFsAgreementId(hspf1FieldSettings.getFsAgreement());
         allF1Details.setHfReferences(hspf1FieldSettings.getHfReferencesNo());
         allF1Details.setShowSeasonToCurrentHF((byte) (hspf1FieldSettings.isShowSeasonProgramToCurrentHF() ? 1 : 0));
         allF1Details.setShowSpecialRequestStudent((byte) (hspf1FieldSettings.isShowSpecialRequestStudentToRD() ? 1 : 0));
         allF1Details.setShowWelcomeFamily((byte) (hspf1FieldSettings.isShowWelcomeFamilyModle() ? 1 : 0));
         seasonF1DetailsRepository.saveAndFlush(allF1Details);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return hspf1FieldSettings;
   }

   public HSPF1ProgramAllocations updateF1ProgramAllocation(SeasonF1Detail allF1Details, HSPF1ProgramAllocations hspf1ProgramAllocations) {
      try {
         // TODO Not complete
         seasonF1DetailsRepository.saveAndFlush(allF1Details);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return hspf1ProgramAllocations;
   }

   public void createSeasonPrograms(Season seasonEntity, SeasonBean seasonBean) {
      LookupDepartment lookupDepartment = seasonEntity.getLookupDepartment();
      String departmentName = lookupDepartment.getDepartmentName();
      if (departmentName.equals(CCIConstants.DEPT_ACCOUNTING)) {
      } else if (departmentName.equals(CCIConstants.DEPT_GREEN_HEART_CLUB)) {
      } else if (departmentName.equals(CCIConstants.DEPT_GREEN_HEART_TRAVEL)) {
         createGHTVolunteerAbroad(seasonEntity, seasonBean);
         createGHTWorkAbroad(seasonEntity, seasonBean);
         createGHTHSAbroad(seasonEntity, seasonBean);
         createGHTLanguageSchool(seasonEntity, seasonBean);
         createGHTTeachAbroad(seasonEntity, seasonBean);
      } else if (departmentName.equals(CCIConstants.DEPT_GREEN_HEART_TRANSFORMS)) {
      } else if (departmentName.equals(CCIConstants.DEPT_HIGH_SCHOOL_PROGRAMS)) {
         createHSPF1Season(seasonEntity, seasonBean);
         createHSPJ1HSSeasonProgram(seasonBean, seasonEntity);
      } else if (departmentName.equals(CCIConstants.DEPT_SYSTEM)) {
      } else if (departmentName.equals(CCIConstants.DEPT_WORK_PROGRAMS)) {
      }
   }

   private void createGHTTeachAbroad(Season seasonEntity, SeasonBean seasonBean) {
      if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
         SeasonTADetail seasonTADetail = new SeasonTADetail();
         seasonTADetail.setSeason(seasonEntity);
         seasonTADetail.setProgramName(seasonBean.getSeasonName());
         seasonTADetail.setCreatedBy(1);
         seasonTADetail.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonTADetail.setModifiedBy(1);
         seasonTADetail.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonTADetailsRepository.saveAndFlush(seasonTADetail);
      }

   }

   private void createGHTLanguageSchool(Season seasonEntity, SeasonBean seasonBean) {
      if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
         SeasonLSDetail seasonLSDetail = new SeasonLSDetail();
         seasonLSDetail.setSeason(seasonEntity);
         seasonLSDetail.setProgramName(seasonBean.getSeasonName());
         seasonLSDetail.setCreatedBy(1);
         seasonLSDetail.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonLSDetail.setModifiedBy(1);
         seasonLSDetail.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonLSDetailsRepository.saveAndFlush(seasonLSDetail);
      }

   }

   private void createGHTHSAbroad(Season seasonEntity, SeasonBean seasonBean) {
      if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
         SeasonHSADetail seasonHSADetail = new SeasonHSADetail();
         seasonHSADetail.setSeason(seasonEntity);
         seasonHSADetail.setProgramName(seasonBean.getSeasonName());
         seasonHSADetail.setCreatedBy(1);
         seasonHSADetail.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonHSADetail.setModifiedBy(1);
         seasonHSADetail.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonHSADetailsRepository.saveAndFlush(seasonHSADetail);
      }

   }

   private void createGHTWorkAbroad(Season seasonEntity, SeasonBean seasonBean) {
      if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
         SeasonWADetail seasonWADetail = new SeasonWADetail();
         seasonWADetail.setSeason(seasonEntity);
         seasonWADetail.setProgramName(seasonBean.getSeasonName());
         seasonWADetail.setCreatedBy(1);
         seasonWADetail.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonWADetail.setModifiedBy(1);
         seasonWADetail.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonWADetailsRepository.saveAndFlush(seasonWADetail);
      }

   }

   private void createGHTVolunteerAbroad(Season seasonEntity, SeasonBean seasonBean) {
      if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
         SeasonVADetail seasonVADetail = new SeasonVADetail();
         seasonVADetail.setSeason(seasonEntity);
         seasonVADetail.setProgramName(seasonBean.getSeasonName());
         seasonVADetail.setCreatedBy(1);
         seasonVADetail.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonVADetail.setModifiedBy(1);
         seasonVADetail.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonVADetailsRepository.saveAndFlush(seasonVADetail);
      }
   }

   private void createHSPF1Season(Season seasonEntity, SeasonBean seasonBean) {
      if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
         SeasonF1Detail seasonF1Detail = new SeasonF1Detail();
         seasonF1Detail.setSeason(seasonEntity);
         seasonF1Detail.setProgramName(seasonBean.getSeasonName());
         seasonF1Detail.setCreatedBy(1);
         seasonF1Detail.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonF1Detail.setModifiedBy(1);
         seasonF1Detail.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonF1DetailsRepository.saveAndFlush(seasonF1Detail);
      }
   }

   /**
    * This method creates j1hs season program for HSP high level season
    * 
    * @param seasonBean
    */
   private void createHSPJ1HSSeasonProgram(SeasonBean seasonBean, Season season) {
      if (season.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
         SeasonJ1Detail seasonJ1Detail = new SeasonJ1Detail();
         seasonJ1Detail.setSeason(season);
         seasonJ1Detail.setProgramName(seasonBean.getSeasonName());
         seasonJ1Detail.setCreatedBy(1);
         seasonJ1Detail.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonJ1Detail.setModifiedBy(1);
         seasonJ1Detail.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
      }
   }

   /**
    * @param seasonJ1Detail
    * @return
    */
   public J1HSBasicDetail getJ1HSBasicDetail(SeasonJ1Detail seasonJ1Detail) {
      J1HSBasicDetail j1hsBasicDetail;
      j1hsBasicDetail = new J1HSBasicDetail();
      j1hsBasicDetail.setSeasonId(seasonJ1Detail.getSeason().getSeasonId());
      j1hsBasicDetail.setSeasonProgramId(seasonJ1Detail.getSeasonJ1DetailsId());
      j1hsBasicDetail.setProgramName(seasonJ1Detail.getProgramName());
      if (seasonJ1Detail.getSeasonStatus() != null)
         j1hsBasicDetail.setProgramStatus(seasonJ1Detail.getSeasonStatus().getStatus());
      return j1hsBasicDetail;
   }

   /**
    * @param seasonJ1Detail
    * @return
    */
   public J1HSJanStart getJ1HSJanStart(SeasonJ1Detail seasonJ1Detail) {
      J1HSJanStart j1hsJanStart;
      j1hsJanStart = new J1HSJanStart();
      j1hsJanStart.setSeasonId(seasonJ1Detail.getSeason().getSeasonId());
      j1hsJanStart.setSeasonProgramId(seasonJ1Detail.getSeasonJ1DetailsId());
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
   public J1HSAugStart getJ1HSAugStart(SeasonJ1Detail seasonJ1Detail) {
      J1HSAugStart j1hsAugStart;
      j1hsAugStart = new J1HSAugStart();
      j1hsAugStart.setSeasonId(seasonJ1Detail.getSeason().getSeasonId());
      j1hsAugStart.setSeasonProgramId(seasonJ1Detail.getSeasonJ1DetailsId());
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
   public J1HSFieldSettings getJ1HSFieldSettings(SeasonJ1Detail seasonJ1Detail) {
      J1HSFieldSettings j1hsFieldSettings;
      j1hsFieldSettings = new J1HSFieldSettings();
      j1hsFieldSettings.setSeasonId(seasonJ1Detail.getSeason().getSeasonId());
      j1hsFieldSettings.setSeasonProgramId(seasonJ1Detail.getSeasonJ1DetailsId());
      j1hsFieldSettings.setShowSeasProgToCurrentHF(seasonJ1Detail.getShowSeasonToCurrentHF() == CCIConstants.ACTIVE ? true : false);
      j1hsFieldSettings.setFsHoldDayLength(String.valueOf(seasonJ1Detail.getFieldStaffHoldLength()));
      j1hsFieldSettings.setHoldExpirationWarning(String.valueOf(seasonJ1Detail.getHoursBeforeHoldExpirationWarning()));
      // j1hsFieldSettings.setDefaultLCPaymentSchedule(String.valueOf(seasonJ1Detail.getLcPaymentScheduleId()));
      // j1hsFieldSettings.setFsAgreement("//TODO fs agreement is missing only id is present leaving it as it is");
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
   public void updateJ1BasicDetails(J1HSBasicDetail j1hsBasicDetail, SeasonJ1Detail seasonJ1Detail) {
      seasonJ1Detail.setProgramName(j1hsBasicDetail.getProgramName());
      seasonJ1Detail.setSeasonStatus(seasonStatusRepository.findSeasonStatusByName(j1hsBasicDetail.getProgramStatus()));
   }

   /**
    * @param j1hsJanStart
    * @param seasonJ1Detail
    */
   public void updateJ1JanStartDetails(J1HSJanStart j1hsJanStart, SeasonJ1Detail seasonJ1Detail) {
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
   public void updateJ1AugStartDetails(J1HSAugStart j1hsAugStart, SeasonJ1Detail seasonJ1Detail) {
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
   public void updateJ1FSSettings(J1HSFieldSettings j1hsFieldSettings, SeasonJ1Detail seasonJ1Detail) {
      seasonJ1Detail.setShowSeasonToCurrentHF(j1hsFieldSettings.isShowSeasProgToCurrentHF() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
      seasonJ1Detail.setFieldStaffHoldLength(Integer.valueOf(j1hsFieldSettings.getFsHoldDayLength()));
      seasonJ1Detail.setHoursBeforeHoldExpirationWarning(Integer.valueOf(j1hsFieldSettings.getHoldExpirationWarning()));
      // seasonJ1Detail.setLcPaymentScheduleId(Integer.valueOf(j1hsFieldSettings.getDefaultLCPaymentSchedule()));
      // seasonJ1Detail.setFsAgreementId(1);// TODO dummy value
      seasonJ1Detail.setHfReferences(Integer.valueOf(j1hsFieldSettings.getHfReferences()));
      seasonJ1Detail.setHfInquiryDate(DateUtils.getMMddyyDateFromString(j1hsFieldSettings.getAddStartHFInquiriesDate()));
      seasonJ1Detail.setShowWelcomeFamily(j1hsFieldSettings.isShowWelcomeFamilyModal() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
      seasonJ1Detail.setShowGuaranteed(j1hsFieldSettings.isShowAllGuranteedParticipantsToFS() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
      seasonJ1Detail.setShowUnguaranteed(j1hsFieldSettings.isShowAllUnGuranteedParticipantsToFS() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
      seasonJ1Detail.setShowSpecialRequestStudent(j1hsFieldSettings.isShowSpecialRequestStudentsToRD() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
   }

   /**
    * @param seasonVADetail
    * @return
    */
   public GHTSection1Base getVABasicDetail(SeasonVADetail seasonVADetail) {
      GHTSection1Base ghtSection1Base = new GHTSection1Base();
      ghtSection1Base.setSeasonId(seasonVADetail.getSeason().getSeasonId());
      ghtSection1Base.setSeasonProgramId(seasonVADetail.getSeasonVADetailsId());
      ghtSection1Base.setProgramName(seasonVADetail.getProgramName());
      if (seasonVADetail.getSeasonStatus() != null)
         ghtSection1Base.setProgramStatus(seasonVADetail.getSeasonStatus().getStatus());
      return ghtSection1Base;
   }

   /**
    * @param seasonVADetail
    * @return
    */
   public GHTSection2Dates getVADates(SeasonVADetail seasonVADetail) {
      GHTSection2Dates ghtSection2Dates = new GHTSection2Dates();
      ghtSection2Dates.setSeasonId(seasonVADetail.getSeason().getSeasonId());
      ghtSection2Dates.setSeasonProgramId(seasonVADetail.getSeasonVADetailsId());
      ghtSection2Dates.setStartDate(DateUtils.getMMddyyDate(seasonVADetail.getStartDate()));
      ghtSection2Dates.setEndDate(DateUtils.getMMddyyDate(seasonVADetail.getEndDate()));
      return ghtSection2Dates;
   }

   /**
    * @param ghtSection1Base
    * @param seasonVADetail
    */
   public void updateVABasicDetails(GHTSection1Base ghtSection1Base, SeasonVADetail seasonVADetail) {
      seasonVADetail.setProgramName(ghtSection1Base.getProgramName());
      seasonVADetail.setSeasonStatus(seasonStatusRepository.findSeasonStatusByName(ghtSection1Base.getProgramStatus()));
   }

   /**
    * @param ghtSection2Dates
    * @param seasonVADetail
    */
   public void updateVADates(GHTSection2Dates ghtSection2Dates, SeasonVADetail seasonVADetail) {
      seasonVADetail.setStartDate(DateUtils.getMMddyyDateFromString(ghtSection2Dates.getStartDate()));
      seasonVADetail.setEndDate(DateUtils.getMMddyyDateFromString(ghtSection2Dates.getEndDate()));
   }

   /**
    * @param seasonWADetail
    * @return
    */
   public GHTSection1Base getWABasicDetail(SeasonWADetail seasonWADetail) {
      GHTSection1Base ghtSection1Base = new GHTSection1Base();
      ghtSection1Base.setSeasonId(seasonWADetail.getSeason().getSeasonId());
      ghtSection1Base.setSeasonProgramId(seasonWADetail.getSeasonWADetailsId());
      ghtSection1Base.setProgramName(seasonWADetail.getProgramName());
      if (seasonWADetail.getSeasonStatus() != null)
         ghtSection1Base.setProgramStatus(seasonWADetail.getSeasonStatus().getStatus());
      return ghtSection1Base;
   }

   /**
    * @param seasonWADetail
    * @return
    */
   public GHTSection2Dates getWADates(SeasonWADetail seasonWADetail) {
      GHTSection2Dates ghtSection2Dates = new GHTSection2Dates();
      ghtSection2Dates.setSeasonId(seasonWADetail.getSeason().getSeasonId());
      ghtSection2Dates.setSeasonProgramId(seasonWADetail.getSeasonWADetailsId());
      ghtSection2Dates.setStartDate(DateUtils.getMMddyyDate(seasonWADetail.getStartDate()));
      ghtSection2Dates.setEndDate(DateUtils.getMMddyyDate(seasonWADetail.getEndDate()));
      return ghtSection2Dates;
   }

   /**
    * @param ghtSection1Base
    * @param seasonVADetail
    */
   public void updateWABasicDetails(GHTSection1Base ghtSection1Base, SeasonWADetail seasonWADetail) {
      seasonWADetail.setProgramName(ghtSection1Base.getProgramName());
      seasonWADetail.setSeasonStatus(seasonStatusRepository.findSeasonStatusByName(ghtSection1Base.getProgramStatus()));
   }

   /**
    * @param ghtSection2Dates
    * @param seasonVADetail
    */
   public void updateWADates(GHTSection2Dates ghtSection2Dates, SeasonWADetail seasonWAADetail) {
      seasonWAADetail.setStartDate(DateUtils.getMMddyyDateFromString(ghtSection2Dates.getStartDate()));
      seasonWAADetail.setEndDate(DateUtils.getMMddyyDateFromString(ghtSection2Dates.getEndDate()));
   }

   public SeasonGHTDetails getGHTHSAbroad(SeasonHSADetail seasonHSADetail) {
      SeasonGHTDetails seasonGHTDetails = new SeasonGHTDetails();
      int seasonId = seasonHSADetail.getSeason().getSeasonId();
      seasonGHTDetails.setSeasonId(seasonId);
      seasonGHTDetails.setSeasonProgramId(seasonHSADetail.getSeasonHSADetailsId());
      GHTSection1Base ghtSection1Base = new GHTSection1Base();
      ghtSection1Base.setProgramName(seasonHSADetail.getProgramName());
      if (seasonHSADetail.getSeasonStatus() != null)
         ghtSection1Base.setProgramStatus(seasonHSADetail.getSeasonStatus().getStatus());
      ghtSection1Base.setSeasonId(seasonId);
      ghtSection1Base.setSeasonProgramId(seasonHSADetail.getSeasonHSADetailsId());
      seasonGHTDetails.setGhtBaseDetails(ghtSection1Base);
      GHTSection2Dates ghtSection2Dates = new GHTSection2Dates();
      ghtSection2Dates.setEndDate(DateUtils.getMMddyyDate(seasonHSADetail.getEndDate()));
      ghtSection2Dates.setSeasonId(seasonId);
      ghtSection2Dates.setSeasonProgramId(seasonHSADetail.getSeasonHSADetailsId());
      ghtSection2Dates.setStartDate(DateUtils.getMMddyyDate(seasonHSADetail.getStartDate()));
      seasonGHTDetails.setGhtDates(ghtSection2Dates);

      return seasonGHTDetails;
   }

   public SeasonGHTDetails updateGHTHSAbroad(SeasonGHTDetails seasonGHTDetails) {
      try {

         SeasonHSADetail seasonHsaDetail = seasonHSADetailsRepository.findOne(seasonGHTDetails.getSeasonProgramId());
         if (seasonHsaDetail == null) {
            return null;
         }
         seasonHsaDetail.setProgramName(seasonGHTDetails.getGhtBaseDetails().getProgramName());
         seasonHsaDetail.setSeasonStatus(seasonStatusRepository.getSeasonStatusByName(seasonGHTDetails.getGhtBaseDetails().getProgramStatus()));
         seasonHsaDetail.setStartDate(DateUtils.getDateFromString(seasonGHTDetails.getGhtDates().getStartDate()));
         seasonHsaDetail.setEndDate(DateUtils.getDateFromString(seasonGHTDetails.getGhtDates().getEndDate()));
         seasonHSADetailsRepository.saveAndFlush(seasonHsaDetail);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return seasonGHTDetails;
   }

   public SeasonGHTDetails getGHTLanguageSchool(SeasonLSDetail seasonLSDetail) {
      SeasonGHTDetails seasonGHTDetails = new SeasonGHTDetails();
      int seasonId = seasonLSDetail.getSeason().getSeasonId();
      seasonGHTDetails.setSeasonId(seasonId);
      seasonGHTDetails.setSeasonProgramId(seasonLSDetail.getSeasonLSDetailsId());
      GHTSection1Base ghtSection1Base = new GHTSection1Base();
      ghtSection1Base.setProgramName(seasonLSDetail.getProgramName());
      if (seasonLSDetail.getSeasonStatus() != null)
         ghtSection1Base.setProgramStatus(seasonLSDetail.getSeasonStatus().getStatus());
      ghtSection1Base.setSeasonId(seasonId);
      ghtSection1Base.setSeasonProgramId(seasonLSDetail.getSeasonLSDetailsId());
      seasonGHTDetails.setGhtBaseDetails(ghtSection1Base);
      GHTSection2Dates ghtSection2Dates = new GHTSection2Dates();
      ghtSection2Dates.setEndDate(DateUtils.getMMddyyDate(seasonLSDetail.getEndDate()));
      ghtSection2Dates.setSeasonId(seasonId);
      ghtSection2Dates.setSeasonProgramId(seasonLSDetail.getSeasonLSDetailsId());
      ghtSection2Dates.setStartDate(DateUtils.getMMddyyDate(seasonLSDetail.getStartDate()));
      seasonGHTDetails.setGhtDates(ghtSection2Dates);

      return seasonGHTDetails;
   }

   public SeasonGHTDetails updateGHTLanguageSchool(SeasonGHTDetails seasonGHTDetails) {
      try {

         SeasonLSDetail seasonLSDetail = seasonLSDetailsRepository.findOne(seasonGHTDetails.getSeasonProgramId());
         if (seasonLSDetail == null) {
            return null;
         }
         seasonLSDetail.setProgramName(seasonGHTDetails.getGhtBaseDetails().getProgramName());
         seasonLSDetail.setSeasonStatus(seasonStatusRepository.getSeasonStatusByName(seasonGHTDetails.getGhtBaseDetails().getProgramStatus()));
         seasonLSDetail.setStartDate(DateUtils.getDateFromString(seasonGHTDetails.getGhtDates().getStartDate()));
         seasonLSDetail.setEndDate(DateUtils.getDateFromString(seasonGHTDetails.getGhtDates().getEndDate()));
         seasonLSDetailsRepository.saveAndFlush(seasonLSDetail);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return seasonGHTDetails;
   }

   public SeasonGHTDetails getGHTTeachAbroad(SeasonTADetail seasonTADetail) {
      SeasonGHTDetails seasonGHTDetails = new SeasonGHTDetails();
      int seasonId = seasonTADetail.getSeason().getSeasonId();
      seasonGHTDetails.setSeasonId(seasonId);
      seasonGHTDetails.setSeasonProgramId(seasonTADetail.getSeasonTADetailsId());
      GHTSection1Base ghtSection1Base = new GHTSection1Base();
      ghtSection1Base.setProgramName(seasonTADetail.getProgramName());
      if (seasonTADetail.getSeasonStatus() != null)
         ghtSection1Base.setProgramStatus(seasonTADetail.getSeasonStatus().getStatus());
      ghtSection1Base.setSeasonId(seasonId);
      ghtSection1Base.setSeasonProgramId(seasonTADetail.getSeasonTADetailsId());
      seasonGHTDetails.setGhtBaseDetails(ghtSection1Base);
      GHTSection2Dates ghtSection2Dates = new GHTSection2Dates();
      ghtSection2Dates.setEndDate(DateUtils.getMMddyyDate(seasonTADetail.getEndDate()));
      ghtSection2Dates.setSeasonId(seasonId);
      ghtSection2Dates.setSeasonProgramId(seasonTADetail.getSeasonTADetailsId());
      ghtSection2Dates.setStartDate(DateUtils.getMMddyyDate(seasonTADetail.getStartDate()));
      seasonGHTDetails.setGhtDates(ghtSection2Dates);

      return seasonGHTDetails;
   }

   public SeasonGHTDetails updateGHTTeachAbroad(SeasonGHTDetails seasonGHTDetails) {
      try {
         SeasonTADetail seasonTADetail = seasonTADetailsRepository.findOne(seasonGHTDetails.getSeasonProgramId());
         if (seasonTADetail == null) {
            return null;
         }
         seasonTADetail.setProgramName(seasonGHTDetails.getGhtBaseDetails().getProgramName());
         seasonTADetail.setSeasonStatus(seasonStatusRepository.getSeasonStatusByName(seasonGHTDetails.getGhtBaseDetails().getProgramStatus()));
         seasonTADetail.setStartDate(DateUtils.getDateFromString(seasonGHTDetails.getGhtDates().getStartDate()));
         seasonTADetail.setEndDate(DateUtils.getDateFromString(seasonGHTDetails.getGhtDates().getEndDate()));
         seasonTADetailsRepository.saveAndFlush(seasonTADetail);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return seasonGHTDetails;
   }

   public GHTSection1Base getGHTHSSection1BaseAbroad(String seasonProgramId) {
      SeasonHSADetail seasonHSADetail = seasonHSADetailsRepository.findOne(Integer.parseInt(seasonProgramId));
      if (seasonHSADetail == null) {
         return null;
      }
      GHTSection1Base ghtSection1Base = new GHTSection1Base();
      ghtSection1Base.setProgramName(seasonHSADetail.getProgramName());
      if (seasonHSADetail.getSeasonStatus() != null)
         ghtSection1Base.setProgramStatus(seasonHSADetail.getSeasonStatus().getStatus());
      ghtSection1Base.setSeasonId(seasonHSADetail.getSeason().getSeasonId());
      ghtSection1Base.setSeasonProgramId(seasonHSADetail.getSeasonHSADetailsId());
      return ghtSection1Base;
   }

   public GHTSection1Base updateGHTHSSection1BaseAbroad(GHTSection1Base seasonGhtSection1Base) {
      try {
         SeasonHSADetail seasonHSADetail = seasonHSADetailsRepository.findOne(seasonGhtSection1Base.getSeasonProgramId());
         if (seasonHSADetail == null) {
            return null;
         }
         seasonHSADetail.setProgramName(seasonGhtSection1Base.getProgramName());
         seasonHSADetail.setSeasonStatus(seasonStatusRepository.getSeasonStatusByName(seasonGhtSection1Base.getProgramStatus()));

         seasonHSADetailsRepository.saveAndFlush(seasonHSADetail);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return seasonGhtSection1Base;
   }

   public GHTSection2Dates getGHTHSSection2DatesAbroad(String seasonProgramId) {
      SeasonHSADetail seasonHSADetail = seasonHSADetailsRepository.findOne(Integer.parseInt(seasonProgramId));
      if (seasonHSADetail == null) {
         return null;
      }
      GHTSection2Dates ghtSection2Dates = new GHTSection2Dates();
      ghtSection2Dates.setEndDate(DateUtils.getMMddyyDate(seasonHSADetail.getEndDate()));
      ghtSection2Dates.setStartDate(DateUtils.getMMddyyDate(seasonHSADetail.getStartDate()));
      ghtSection2Dates.setSeasonId(seasonHSADetail.getSeason().getSeasonId());
      ghtSection2Dates.setSeasonProgramId(seasonHSADetail.getSeasonHSADetailsId());
      return ghtSection2Dates;
   }

   public GHTSection2Dates updateGHTHSSection2DatesAbroad(GHTSection2Dates ghtSection2Dates) {
      try {
         SeasonHSADetail seasonHSADetail = seasonHSADetailsRepository.findOne(ghtSection2Dates.getSeasonProgramId());
         if (seasonHSADetail == null) {
            return null;
         }
         seasonHSADetail.setStartDate(DateUtils.getDateFromString(ghtSection2Dates.getStartDate()));
         seasonHSADetail.setEndDate(DateUtils.getDateFromString(ghtSection2Dates.getEndDate()));
         seasonHSADetailsRepository.saveAndFlush(seasonHSADetail);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return ghtSection2Dates;
   }

   public GHTSection1Base getGHTLanguageSchoolSection1(String seasonProgramId) {
      SeasonLSDetail seasonLSDetail = seasonLSDetailsRepository.findOne(Integer.parseInt(seasonProgramId));
      if (seasonLSDetail == null) {
         return null;
      }
      GHTSection1Base ghtSection1Base = new GHTSection1Base();
      ghtSection1Base.setProgramName(seasonLSDetail.getProgramName());
      if (seasonLSDetail.getSeasonStatus() != null)
         ghtSection1Base.setProgramStatus(seasonLSDetail.getSeasonStatus().getStatus());
      ghtSection1Base.setSeasonId(seasonLSDetail.getSeason().getSeasonId());
      ghtSection1Base.setSeasonProgramId(seasonLSDetail.getSeasonLSDetailsId());
      return ghtSection1Base;
   }

   public GHTSection1Base updateGHTLanguageSchoolSection1(GHTSection1Base seasonGHTSection1Base) {
      try {
         SeasonLSDetail seasonLsADetail = seasonLSDetailsRepository.findOne(seasonGHTSection1Base.getSeasonProgramId());
         if (seasonLsADetail == null) {
            return null;
         }
         seasonLsADetail.setProgramName(seasonGHTSection1Base.getProgramName());
         seasonLsADetail.setSeasonStatus(seasonStatusRepository.getSeasonStatusByName(seasonGHTSection1Base.getProgramStatus()));

         seasonLSDetailsRepository.saveAndFlush(seasonLsADetail);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return seasonGHTSection1Base;
   }

   public GHTSection2Dates getGHTLanguageSchoolSection2Dates(String seasonProgramId) {
      SeasonLSDetail seasonLsADetail = seasonLSDetailsRepository.findOne(Integer.parseInt(seasonProgramId));
      if (seasonLsADetail == null) {
         return null;
      }
      GHTSection2Dates ghtSection2Dates = new GHTSection2Dates();
      ghtSection2Dates.setEndDate(DateUtils.getMMddyyDate(seasonLsADetail.getEndDate()));
      ghtSection2Dates.setStartDate(DateUtils.getMMddyyDate(seasonLsADetail.getStartDate()));
      ghtSection2Dates.setSeasonId(seasonLsADetail.getSeason().getSeasonId());
      ghtSection2Dates.setSeasonProgramId(seasonLsADetail.getSeasonLSDetailsId());
      return ghtSection2Dates;
   }

   public GHTSection2Dates updateGHTLanguageSchoolSection2Dates(GHTSection2Dates ghtSection2Dates) {
      try {
         SeasonLSDetail seasonLsADetail = seasonLSDetailsRepository.findOne(ghtSection2Dates.getSeasonProgramId());
         if (seasonLsADetail == null) {
            return null;
         }
         seasonLsADetail.setStartDate(DateUtils.getDateFromString(ghtSection2Dates.getStartDate()));
         seasonLsADetail.setEndDate(DateUtils.getDateFromString(ghtSection2Dates.getEndDate()));
         seasonLSDetailsRepository.saveAndFlush(seasonLsADetail);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return ghtSection2Dates;
   }

   public GHTSection1Base getGHTTeachAbroadSection1(String seasonProgramId) {
      SeasonTADetail seasonTADetail = seasonTADetailsRepository.findOne(Integer.parseInt(seasonProgramId));
      if (seasonTADetail == null) {
         return null;
      }
      GHTSection1Base ghtSection1Base = new GHTSection1Base();
      ghtSection1Base.setProgramName(seasonTADetail.getProgramName());
      if (seasonTADetail.getSeasonStatus() != null)
         ghtSection1Base.setProgramStatus(seasonTADetail.getSeasonStatus().getStatus());
      ghtSection1Base.setSeasonId(seasonTADetail.getSeason().getSeasonId());
      ghtSection1Base.setSeasonProgramId(seasonTADetail.getSeasonTADetailsId());
      return ghtSection1Base;
   }

   public GHTSection1Base updateGHTTeachAbroadSection1(GHTSection1Base seasonSection1Base) {
      try {
         SeasonTADetail seasonTADetail = seasonTADetailsRepository.findOne(seasonSection1Base.getSeasonProgramId());
         if (seasonTADetail == null) {
            return null;
         }
         seasonTADetail.setProgramName(seasonSection1Base.getProgramName());
         seasonTADetail.setSeasonStatus(seasonStatusRepository.getSeasonStatusByName(seasonSection1Base.getProgramStatus()));

         seasonTADetailsRepository.saveAndFlush(seasonTADetail);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return seasonSection1Base;
   }

   public GHTSection2Dates getGHTTeachAbroadSection2Dates(String seasonProgramId) {
      SeasonTADetail seasonTADetail = seasonTADetailsRepository.findOne(Integer.parseInt(seasonProgramId));
      if (seasonTADetail == null) {
         return null;
      }
      GHTSection2Dates ghtSection2Dates = new GHTSection2Dates();
      ghtSection2Dates.setEndDate(DateUtils.getMMddyyDate(seasonTADetail.getEndDate()));
      ghtSection2Dates.setStartDate(DateUtils.getMMddyyDate(seasonTADetail.getStartDate()));
      ghtSection2Dates.setSeasonId(seasonTADetail.getSeason().getSeasonId());
      ghtSection2Dates.setSeasonProgramId(seasonTADetail.getSeasonTADetailsId());
      return ghtSection2Dates;
   }

   public GHTSection2Dates updateGHTTeachAbroadSection2Dates(GHTSection2Dates ghtSection2Dates) {
      try {
         SeasonTADetail seasonTADetail = seasonTADetailsRepository.findOne(ghtSection2Dates.getSeasonProgramId());
         if (seasonTADetail == null) {
            return null;
         }
         seasonTADetail.setStartDate(DateUtils.getDateFromString(ghtSection2Dates.getStartDate()));
         seasonTADetail.setEndDate(DateUtils.getDateFromString(ghtSection2Dates.getEndDate()));
         seasonTADetailsRepository.saveAndFlush(seasonTADetail);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return ghtSection2Dates;
   }

   public SeasonWPCAPDetails getWPCAPDetails(String seasonProgramId) {
      SeasonCAPDetail seasonWPcap = seasonCAPDetailsRepository.findOne(Integer.parseInt(seasonProgramId));
      SeasonWPCAPDetails seasonWPCAPDetails = new SeasonWPCAPDetails();
      WPCAPBasicDetails wpcapBasicDetails = new WPCAPBasicDetails();
      wpcapBasicDetails.setProgramName(seasonWPcap.getProgramName());
      wpcapBasicDetails.setSeasonId(seasonWPcap.getSeason().getSeasonId());
      wpcapBasicDetails.setSeasonProgramId(seasonWPcap.getSeasonCAPDetailsId());
      if (seasonWPcap.getSeasonStatus() != null)
         wpcapBasicDetails.setProgramStatus(seasonWPcap.getSeasonStatus().getStatus());
      seasonWPCAPDetails.setDetails(wpcapBasicDetails);

      WPCAPInternshipDetails wpcapInternshipDetails = new WPCAPInternshipDetails();
      wpcapInternshipDetails.setApplicationDeadlineDate(DateUtils.getMMddyyDate(seasonWPcap.getInternAppDeadlineDate()));
      wpcapInternshipDetails.setEndDate(DateUtils.getMMddyyDate(seasonWPcap.getInternEndDate()));
      wpcapInternshipDetails.setSeasonId(seasonWPcap.getSeason().getSeasonId());
      wpcapInternshipDetails.setSeasonProgramId(seasonWPcap.getSeasonCAPDetailsId());
      wpcapInternshipDetails.setStartDate(DateUtils.getMMddyyDate(seasonWPcap.getInternStartDate()));
      seasonWPCAPDetails.setInternshipDetails(wpcapInternshipDetails);

      WPCAPTraineeDetails wpcapTraineeDetails = new WPCAPTraineeDetails();
      wpcapTraineeDetails.setApplicationDeadlineDate(DateUtils.getMMddyyDate(seasonWPcap.getTraineeAppDeadlineDate()));
      wpcapTraineeDetails.setEndDate(DateUtils.getMMddyyDate(seasonWPcap.getTraineeEndDate()));
      wpcapTraineeDetails.setStartDate(DateUtils.getMMddyyDate(seasonWPcap.getTraineeStartDate()));
      wpcapTraineeDetails.setSeasonId(seasonWPcap.getSeason().getSeasonId());
      wpcapTraineeDetails.setSeasonProgramId(seasonWPcap.getSeasonCAPDetailsId());
      seasonWPCAPDetails.setTraineeDetails(wpcapTraineeDetails);
      seasonWPCAPDetails.setSeasonId(seasonWPcap.getSeason().getSeasonId());
      seasonWPCAPDetails.setSeasonProgramId(seasonWPcap.getSeasonCAPDetailsId());
      // missing program options and general settings
      return seasonWPCAPDetails;
   }

   public SeasonWPCAPDetails updateWPCAPDetails(SeasonWPCAPDetails seasonWPCAPDetails) {
      try {
         SeasonCAPDetail seasonCAPDetail = seasonCAPDetailsRepository.findOne(seasonWPCAPDetails.getSeasonProgramId());
         seasonCAPDetail.setInternAppDeadlineDate(DateUtils.getDateFromString(seasonWPCAPDetails.getInternshipDetails().getApplicationDeadlineDate()));
         seasonCAPDetail.setInternEndDate(DateUtils.getDateFromString(seasonWPCAPDetails.getInternshipDetails().getEndDate()));
         seasonCAPDetail.setInternStartDate(DateUtils.getDateFromString(seasonWPCAPDetails.getInternshipDetails().getStartDate()));
         Season season = seasonRepository.findOne(seasonCAPDetail.getSeason().getSeasonId());
         seasonCAPDetail.setSeason(season);
         seasonCAPDetail.setProgramName(seasonWPCAPDetails.getDetails().getProgramName());
         SeasonStatus seasonStatus = seasonStatusRepository.findSeasonStatusByName(seasonWPCAPDetails.getDetails().getProgramStatus());
         seasonCAPDetail.setSeasonStatus(seasonStatus);
         seasonCAPDetail.setTraineeAppDeadlineDate(DateUtils.getDateFromString(seasonWPCAPDetails.getTraineeDetails().getApplicationDeadlineDate()));
         seasonCAPDetail.setTraineeEndDate(DateUtils.getDateFromString(seasonWPCAPDetails.getTraineeDetails().getEndDate()));
         seasonCAPDetail.setTraineeStartDate(DateUtils.getDateFromString(seasonWPCAPDetails.getTraineeDetails().getStartDate()));

         // seasonCAPDetail.setCreatedBy(1);
         // seasonCAPDetail.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonCAPDetail.setModifiedBy(1);
         seasonCAPDetail.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonCAPDetailsRepository.saveAndFlush(seasonCAPDetail);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return seasonWPCAPDetails;

   }

   public WPCAPBasicDetails getWPCAPBasicDetails(String seasonProgramId) {
      SeasonCAPDetail seasonWPcap = seasonCAPDetailsRepository.findOne(Integer.parseInt(seasonProgramId));
      WPCAPBasicDetails wpcapBasicDetails = new WPCAPBasicDetails();
      wpcapBasicDetails.setSeasonId(seasonWPcap.getSeason().getSeasonId());
      wpcapBasicDetails.setSeasonProgramId(seasonWPcap.getSeasonCAPDetailsId());
      wpcapBasicDetails.setProgramName(seasonWPcap.getProgramName());
      if (seasonWPcap.getSeasonStatus() != null)
         wpcapBasicDetails.setProgramStatus(seasonWPcap.getSeasonStatus().getStatus());
      return wpcapBasicDetails;
   }

   public WPCAPBasicDetails updateWPCAPBasicDetails(WPCAPBasicDetails wpcapBasicDetails) {
      try {
         SeasonCAPDetail seasonWPcap = seasonCAPDetailsRepository.findOne(wpcapBasicDetails.getSeasonProgramId());
         seasonWPcap.setProgramName(wpcapBasicDetails.getProgramName());
         SeasonStatus seasonStatus = seasonStatusRepository.findSeasonStatusByName(wpcapBasicDetails.getProgramStatus());
         seasonWPcap.setSeasonStatus(seasonStatus);

         seasonCAPDetailsRepository.saveAndFlush(seasonWPcap);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return wpcapBasicDetails;
   }

   public WPCAPInternshipDetails getWPCAPInternshipDetails(String seasonProgramId) {
      SeasonCAPDetail seasonWPcap = seasonCAPDetailsRepository.findOne(Integer.parseInt(seasonProgramId));
      WPCAPInternshipDetails wpcapInternshipDetails = new WPCAPInternshipDetails();
      wpcapInternshipDetails.setSeasonId(seasonWPcap.getSeason().getSeasonId());
      wpcapInternshipDetails.setSeasonProgramId(seasonWPcap.getSeasonCAPDetailsId());
      wpcapInternshipDetails.setApplicationDeadlineDate(DateUtils.getMMddyyDate(seasonWPcap.getInternAppDeadlineDate()));
      wpcapInternshipDetails.setEndDate(DateUtils.getMMddyyDate(seasonWPcap.getInternEndDate()));
      wpcapInternshipDetails.setStartDate(DateUtils.getMMddyyDate(seasonWPcap.getInternStartDate()));

      return wpcapInternshipDetails;
   }

   public WPCAPInternshipDetails updateWPCAPInternshipDetails(WPCAPInternshipDetails wpcapInternshipDetails) {
      try {
         SeasonCAPDetail seasonWPcap = seasonCAPDetailsRepository.findOne(wpcapInternshipDetails.getSeasonProgramId());
         seasonWPcap.setInternAppDeadlineDate(DateUtils.getDateFromString(wpcapInternshipDetails.getApplicationDeadlineDate()));
         seasonWPcap.setInternEndDate(DateUtils.getDateFromString(wpcapInternshipDetails.getEndDate()));
         seasonWPcap.setInternStartDate(DateUtils.getDateFromString(wpcapInternshipDetails.getStartDate()));
         seasonCAPDetailsRepository.saveAndFlush(seasonWPcap);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return wpcapInternshipDetails;
   }

   public WPCAPTraineeDetails getWPCAPTraineeDetails(String seasonProgramId) {
      SeasonCAPDetail seasonWPcap = seasonCAPDetailsRepository.findOne(Integer.parseInt(seasonProgramId));
      WPCAPTraineeDetails wpcapTraineeDetails = new WPCAPTraineeDetails();
      wpcapTraineeDetails.setSeasonId(seasonWPcap.getSeason().getSeasonId());
      wpcapTraineeDetails.setSeasonProgramId(seasonWPcap.getSeasonCAPDetailsId());
      wpcapTraineeDetails.setApplicationDeadlineDate(DateUtils.getMMddyyDate(seasonWPcap.getTraineeAppDeadlineDate()));
      wpcapTraineeDetails.setEndDate(DateUtils.getMMddyyDate(seasonWPcap.getTraineeEndDate()));
      wpcapTraineeDetails.setStartDate(DateUtils.getMMddyyDate(seasonWPcap.getTraineeStartDate()));
      return wpcapTraineeDetails;
   }

   public WPCAPTraineeDetails updateWPCAPTraineeDetails(WPCAPTraineeDetails wpcapTraineeDetails) {
      try {
         SeasonCAPDetail seasonWPcap = seasonCAPDetailsRepository.findOne(wpcapTraineeDetails.getSeasonProgramId());
         seasonWPcap.setTraineeAppDeadlineDate(DateUtils.getDateFromString(wpcapTraineeDetails.getApplicationDeadlineDate()));
         seasonWPcap.setTraineeEndDate(DateUtils.getDateFromString(wpcapTraineeDetails.getEndDate()));
         seasonWPcap.setTraineeStartDate(DateUtils.getDateFromString(wpcapTraineeDetails.getStartDate()));
         seasonCAPDetailsRepository.saveAndFlush(seasonWPcap);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return wpcapTraineeDetails;
   }

   public SeasonWPDetails getWPWinterDetails(String seasonProgramId) {
      SeasonWnTWinterDetail seasonWnTWinterDetail = seasonWinterRepository.findOne(Integer.parseInt(seasonProgramId));
      SeasonWPDetails seasonWPDetails = new SeasonWPDetails();
      seasonWPDetails.setSeasonId(seasonWnTWinterDetail.getSeason().getSeasonId());
      seasonWPDetails.setSeasonProgramId(seasonWnTWinterDetail.getSeasonWnTWinterDetailsId());
      WPBasicDetail wpBasicDetail = new WPBasicDetail();
      wpBasicDetail.setProgramName(seasonWnTWinterDetail.getProgramName());
      if (seasonWnTWinterDetail.getSeasonStatus() != null)
         wpBasicDetail.setProgramStatus(seasonWnTWinterDetail.getSeasonStatus().getStatus());
      wpBasicDetail.setSeasonId(seasonWnTWinterDetail.getSeason().getSeasonId());
      wpBasicDetail.setSeasonProgramId(seasonWnTWinterDetail.getSeasonWnTWinterDetailsId());
      seasonWPDetails.setWpBasicDetail(wpBasicDetail);

      WPSectionOne wpSectionOne = new WPSectionOne();
      wpSectionOne.setApplicationDeadlineDate(DateUtils.getMMddyyDate(seasonWnTWinterDetail.getApplicationDeadlineDate()));
      wpSectionOne.setEndDate(DateUtils.getMMddyyDate(seasonWnTWinterDetail.getEndDate()));
      wpSectionOne.setIsJobBoardOpen(seasonWnTWinterDetail.getIsJobBoardOpen() != 0);
      wpSectionOne.setMaxPendingJobAppls(seasonWnTWinterDetail.getMaxPendingJobApps() + "");
      wpSectionOne.setSeasonId(seasonWnTWinterDetail.getSeason().getSeasonId());
      wpSectionOne.setSeasonProgramId(seasonWnTWinterDetail.getSeasonWnTWinterDetailsId());
      wpSectionOne.setStartDate(DateUtils.getMMddyyDate(seasonWnTWinterDetail.getStartDate()));

      seasonWPDetails.setWpSectionOne(wpSectionOne);
      // program allocations not complete

      return seasonWPDetails;
   }

   public SeasonWPDetails updateWPWinterDetails(SeasonWPDetails seasonWPDetails) {
      SeasonWnTWinterDetail seasonWnTWinterDetail = seasonWinterRepository.findOne(seasonWPDetails.getSeasonProgramId());
      seasonWnTWinterDetail.setApplicationDeadlineDate(DateUtils.getDateFromString(seasonWPDetails.getWpSectionOne().getApplicationDeadlineDate()));
      seasonWnTWinterDetail.setEndDate(DateUtils.getDateFromString(seasonWPDetails.getWpSectionOne().getEndDate()));
      seasonWnTWinterDetail.setIsJobBoardOpen((byte) (seasonWPDetails.getWpSectionOne().isIsJobBoardOpen() ? 1 : 0));
      seasonWnTWinterDetail.setMaxPendingJobApps(Integer.parseInt(seasonWPDetails.getWpSectionOne().getMaxPendingJobAppls()));
      seasonWnTWinterDetail.setStartDate(DateUtils.getDateFromString(seasonWPDetails.getWpSectionOne().getStartDate()));

      // Season season = seasonRepository.findOne(seasonWPDetails.getSeasonId());
      // seasonWnTWinterDetail.setSeason(season);

      seasonWnTWinterDetail.setProgramName(seasonWPDetails.getWpBasicDetail().getProgramName());
      SeasonStatus seasonStatus = seasonStatusRepository.findSeasonStatusByName(seasonWPDetails.getWpBasicDetail().getProgramStatus());
      seasonWnTWinterDetail.setSeasonStatus(seasonStatus);

      seasonWinterRepository.saveAndFlush(seasonWnTWinterDetail);

      return seasonWPDetails;
   }

   public WPBasicDetail getWPWinterBaseDetails(String seasonProgramId) {
      SeasonWnTWinterDetail seasonWnTWinterDetail = seasonWinterRepository.findOne(Integer.parseInt(seasonProgramId));
      WPBasicDetail wpBasicDetail = new WPBasicDetail();
      wpBasicDetail.setProgramName(seasonWnTWinterDetail.getProgramName());
      if (seasonWnTWinterDetail.getSeasonStatus() != null)
         wpBasicDetail.setProgramStatus(seasonWnTWinterDetail.getSeasonStatus().getStatus());
      wpBasicDetail.setSeasonId(seasonWnTWinterDetail.getSeason().getSeasonId());
      wpBasicDetail.setSeasonProgramId(seasonWnTWinterDetail.getSeasonWnTWinterDetailsId());
      return wpBasicDetail;
   }

   public WPBasicDetail updateWPWinterBaseDetails(WPBasicDetail wpBasicDetail) {
      SeasonWnTWinterDetail seasonWnTWinterDetail = seasonWinterRepository.findOne(wpBasicDetail.getSeasonProgramId());
      seasonWnTWinterDetail.setProgramName(wpBasicDetail.getProgramName());
      SeasonStatus seasonStatus = seasonStatusRepository.findSeasonStatusByName(wpBasicDetail.getProgramStatus());
      seasonWnTWinterDetail.setSeasonStatus(seasonStatus);
      seasonWinterRepository.saveAndFlush(seasonWnTWinterDetail);
      return wpBasicDetail;
   }

   public WPSectionOne getWPWinterSectionOneDetails(String seasonProgramId) {
      SeasonWnTWinterDetail seasonWnTWinterDetail = seasonWinterRepository.findOne(Integer.parseInt(seasonProgramId));
      WPSectionOne wpSectionOne = new WPSectionOne();
      wpSectionOne.setApplicationDeadlineDate(DateUtils.getMMddyyDate(seasonWnTWinterDetail.getApplicationDeadlineDate()));
      wpSectionOne.setEndDate(DateUtils.getMMddyyDate(seasonWnTWinterDetail.getEndDate()));
      wpSectionOne.setIsJobBoardOpen(seasonWnTWinterDetail.getIsJobBoardOpen() != 0);
      wpSectionOne.setMaxPendingJobAppls(seasonWnTWinterDetail.getMaxPendingJobApps() + "");
      wpSectionOne.setSeasonId(seasonWnTWinterDetail.getSeason().getSeasonId());
      wpSectionOne.setSeasonProgramId(seasonWnTWinterDetail.getSeasonWnTWinterDetailsId());
      wpSectionOne.setStartDate(DateUtils.getMMddyyDate(seasonWnTWinterDetail.getStartDate()));
      return wpSectionOne;
   }

   public WPSectionOne updateWPWinterSectionOneDetails(WPSectionOne wpSectionOne) {
      SeasonWnTWinterDetail seasonWnTWinterDetail = seasonWinterRepository.findOne(wpSectionOne.getSeasonProgramId());
      seasonWnTWinterDetail.setApplicationDeadlineDate(DateUtils.getDateFromString(wpSectionOne.getApplicationDeadlineDate()));
      seasonWnTWinterDetail.setEndDate(DateUtils.getDateFromString(wpSectionOne.getEndDate()));
      seasonWnTWinterDetail.setIsJobBoardOpen((byte) (wpSectionOne.isIsJobBoardOpen() ? 1 : 0));
      seasonWnTWinterDetail.setMaxPendingJobApps(Integer.parseInt(wpSectionOne.getMaxPendingJobAppls()));
      seasonWnTWinterDetail.setStartDate(DateUtils.getDateFromString(wpSectionOne.getStartDate()));
      seasonWinterRepository.saveAndFlush(seasonWnTWinterDetail);
      return wpSectionOne;
   }

   public WPSectionOne getWPWinterAllocationDetails(String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

   public WPSectionOne updateWPWinterAllocationDetails(WPSectionOne wpSectionOne) {
      // TODO Auto-generated method stub
      return null;
   }

   // WP summer

   /**
    * @param seasonWnTSummerDetail
    * @return
    */
   public WPBasicDetail getWPSummerBaseDetails(SeasonWnTSummerDetail seasonWnTSummerDetail) {
      WPBasicDetail wpBasicDetail = new WPBasicDetail();
      wpBasicDetail.setSeasonId(seasonWnTSummerDetail.getSeason().getSeasonId());
      wpBasicDetail.setSeasonProgramId(seasonWnTSummerDetail.getSeasonWnTSummerDetailsId());
      wpBasicDetail.setProgramName(seasonWnTSummerDetail.getProgramName() != null ? seasonWnTSummerDetail.getProgramName() : null);
      if (seasonWnTSummerDetail.getSeasonStatus() != null)
         wpBasicDetail.setProgramStatus(seasonWnTSummerDetail.getSeasonStatus().getStatus() != null ? seasonWnTSummerDetail.getSeasonStatus().getStatus() : null);
      return wpBasicDetail;
   }

   /**
    * @param seasonWnTSummerDetail
    * @return
    */
   public WPSectionOne getWPSummerSection1Details(SeasonWnTSummerDetail seasonWnTSummerDetail) {
      WPSectionOne wpSectionOne = new WPSectionOne();
      wpSectionOne.setSeasonId(seasonWnTSummerDetail.getSeason().getSeasonId());
      wpSectionOne.setSeasonProgramId(seasonWnTSummerDetail.getSeasonWnTSummerDetailsId());
      wpSectionOne.setStartDate(seasonWnTSummerDetail.getStartDate() != null ? DateUtils.getMMddyyDate(seasonWnTSummerDetail.getStartDate()) : null);
      wpSectionOne.setEndDate(seasonWnTSummerDetail.getEndDate() != null ? DateUtils.getMMddyyDate(seasonWnTSummerDetail.getEndDate()) : null);
      wpSectionOne.setApplicationDeadlineDate(seasonWnTSummerDetail.getApplicationDeadlineDate() != null ? DateUtils.getMMddyyDate(seasonWnTSummerDetail
            .getApplicationDeadlineDate()) : null);
      wpSectionOne.setIsJobBoardOpen(seasonWnTSummerDetail.getIsJobBoardOpen() == CCIConstants.ACTIVE ? true : false);
      wpSectionOne.setMaxPendingJobAppls(seasonWnTSummerDetail.getMaxPendingJobApps() > 0 ? String.valueOf(seasonWnTSummerDetail.getMaxPendingJobApps()) : null);
      return wpSectionOne;
   }

   /**
    * @param wpBasicDetail
    * @param seasonWnTSummerDetail
    */
   public void updateWPSummerBaseDetails(WPBasicDetail wpBasicDetail, SeasonWnTSummerDetail seasonWnTSummerDetail) {
      seasonWnTSummerDetail.setProgramName(wpBasicDetail.getProgramName() != null ? wpBasicDetail.getProgramName() : null);
      seasonWnTSummerDetail.setSeasonStatus(wpBasicDetail.getProgramStatus() != null ? seasonStatusRepository.findSeasonStatusByName(wpBasicDetail.getProgramStatus()) : null);
   }

   /**
    * @param wpSectionOne
    * @param seasonWnTSummerDetail
    */
   public void updateWPSummerSection1Details(WPSectionOne wpSectionOne, SeasonWnTSummerDetail seasonWnTSummerDetail) {
      seasonWnTSummerDetail.setStartDate(wpSectionOne.getStartDate() != null ? DateUtils.getMMddyyDateFromString(wpSectionOne.getStartDate()) : null);
      seasonWnTSummerDetail.setEndDate(wpSectionOne.getEndDate() != null ? DateUtils.getMMddyyDateFromString(wpSectionOne.getEndDate()) : null);
      seasonWnTSummerDetail.setApplicationDeadlineDate(wpSectionOne.getApplicationDeadlineDate() != null ? DateUtils.getMMddyyDateFromString(wpSectionOne
            .getApplicationDeadlineDate()) : null);
      seasonWnTSummerDetail.setIsJobBoardOpen(wpSectionOne.isIsJobBoardOpen() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
      seasonWnTSummerDetail.setMaxPendingJobApps(wpSectionOne.getMaxPendingJobAppls() != null ? Integer.valueOf(wpSectionOne.getMaxPendingJobAppls()) : null);
   }

   // WP spring

   /**
    * @param seasonWnTSpringDetail
    * @return
    */
   public WPBasicDetail getWPSpringBaseDetails(SeasonWnTSpringDetail seasonWnTSpringDetail) {
      WPBasicDetail wpBasicDetail;
      wpBasicDetail = new WPBasicDetail();
      wpBasicDetail.setSeasonId(seasonWnTSpringDetail.getSeason().getSeasonId());
      wpBasicDetail.setSeasonProgramId(seasonWnTSpringDetail.getSeasonWnTSpringDetailsId());
      wpBasicDetail.setProgramName(seasonWnTSpringDetail.getProgramName() != null ? seasonWnTSpringDetail.getProgramName() : null);
      if (seasonWnTSpringDetail.getSeasonStatus() != null)
         wpBasicDetail.setProgramStatus(seasonWnTSpringDetail.getSeasonStatus().getStatus() != null ? seasonWnTSpringDetail.getSeasonStatus().getStatus() : null);
      return wpBasicDetail;
   }

   /**
    * @param seasonWnTSpringDetail
    * @return
    */
   public WPSectionOne getWPSpringSection1Details(SeasonWnTSpringDetail seasonWnTSpringDetail) {
      WPSectionOne wpSectionOne;
      wpSectionOne = new WPSectionOne();
      wpSectionOne.setSeasonId(seasonWnTSpringDetail.getSeason().getSeasonId());
      wpSectionOne.setSeasonProgramId(seasonWnTSpringDetail.getSeasonWnTSpringDetailsId());
      wpSectionOne.setStartDate(seasonWnTSpringDetail.getStartDate() != null ? DateUtils.getMMddyyDate(seasonWnTSpringDetail.getStartDate()) : null);
      wpSectionOne.setEndDate(seasonWnTSpringDetail.getEndDate() != null ? DateUtils.getMMddyyDate(seasonWnTSpringDetail.getEndDate()) : null);
      wpSectionOne.setApplicationDeadlineDate(seasonWnTSpringDetail.getApplicationDeadlineDate() != null ? DateUtils.getMMddyyDate(seasonWnTSpringDetail
            .getApplicationDeadlineDate()) : null);
      wpSectionOne.setIsJobBoardOpen(seasonWnTSpringDetail.getIsJobBoardOpen() == CCIConstants.ACTIVE ? true : false);
      wpSectionOne.setMaxPendingJobAppls(seasonWnTSpringDetail.getMaxPendingJobApps() > 0 ? String.valueOf(seasonWnTSpringDetail.getMaxPendingJobApps()) : null);
      return wpSectionOne;
   }

   /**
    * @param wpBasicDetail
    * @param seasonWnTSpringDetail
    */
   public void updateWPSpringBaseDetails(WPBasicDetail wpBasicDetail, SeasonWnTSpringDetail seasonWnTSpringDetail) {
      seasonWnTSpringDetail.setProgramName(wpBasicDetail.getProgramName() != null ? wpBasicDetail.getProgramName() : null);
      seasonWnTSpringDetail.setSeasonStatus(wpBasicDetail.getProgramStatus() != null ? seasonStatusRepository.findSeasonStatusByName(wpBasicDetail.getProgramStatus()) : null);
   }

   /**
    * @param wpSectionOne
    * @param seasonWnTSpringDetail
    */
   public void updateWPSpringSection1Details(WPSectionOne wpSectionOne, SeasonWnTSpringDetail seasonWnTSpringDetail) {
      seasonWnTSpringDetail.setStartDate(wpSectionOne.getStartDate() != null ? DateUtils.getMMddyyDateFromString(wpSectionOne.getStartDate()) : null);
      seasonWnTSpringDetail.setEndDate(wpSectionOne.getEndDate() != null ? DateUtils.getMMddyyDateFromString(wpSectionOne.getEndDate()) : null);
      seasonWnTSpringDetail.setApplicationDeadlineDate(wpSectionOne.getApplicationDeadlineDate() != null ? DateUtils.getMMddyyDateFromString(wpSectionOne
            .getApplicationDeadlineDate()) : null);
      seasonWnTSpringDetail.setIsJobBoardOpen(wpSectionOne.isIsJobBoardOpen() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
      seasonWnTSpringDetail.setMaxPendingJobApps(wpSectionOne.getMaxPendingJobAppls() != null ? Integer.valueOf(wpSectionOne.getMaxPendingJobAppls()) : null);
   }

   public void createSeasonDepartmentNotes(SeasonBean seasonBean, Season seasonEntity) {
      if (seasonBean.getNotes() != null && !seasonBean.getNotes().isEmpty()) {
         for (SeasonDepartmentNotes note : seasonBean.getNotes()) {
            SeasonDepartmentNote seasonDepartmentNote = new SeasonDepartmentNote();
            seasonDepartmentNote.setDepartmentNote(note.getNoteValue());
            seasonDepartmentNote.setSeason(seasonEntity);
            seasonDepartmentNote.setActive((byte) (note.isActive() ? 1 : 0));
            seasonDepartmentNote.setCreatedBy(1);
            seasonDepartmentNote.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
            seasonDepartmentNote.setModifiedBy(1);
            seasonDepartmentNote.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
            seasonDepartmentNotesRepository.saveAndFlush(seasonDepartmentNote);
         }
      }
   }

   public void updateSeasonDepartmentNotes(SeasonBean seasonBean, Season seasonEntity) {
      List<SeasonDepartmentNote> seasonDepartmentNotes = seasonDepartmentNotesRepository.findAllDepartmentNotesBySeasonId(seasonEntity.getSeasonId());
      if (seasonDepartmentNotes != null && !seasonDepartmentNotes.isEmpty()) {
         seasonDepartmentNotesRepository.delete(seasonDepartmentNotes);
      }
      for (SeasonDepartmentNotes notes : seasonBean.getNotes()) {
         SeasonDepartmentNote currentNote = new SeasonDepartmentNote();
         currentNote.setDepartmentNote(notes.getNoteValue());
         currentNote.setSeason(seasonEntity);
         seasonDepartmentNotesRepository.saveAndFlush(currentNote);
      }
   }

   /**
    * gets list of notes for J1 program
    * 
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<J1HSNotes> getJ1Notes(Integer seasonId, Integer seasonProgramId) {
      List<J1HSNotes> j1hsNotes = null;
      List<SeasonProgramNote> j1hsProgramNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonId(seasonId);
      if (j1hsProgramNotes != null) {
         j1hsNotes = new ArrayList<J1HSNotes>();
         for (SeasonProgramNote prgNote : j1hsProgramNotes) {
            if (prgNote.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_J1_HS)) {
               J1HSNotes note = new J1HSNotes();
               note.setSeasonId(prgNote.getSeason().getSeasonId());
               note.setSeasonProgramId(seasonProgramId);
               note.setNote(prgNote.getProgramNote());
               j1hsNotes.add(note);
            }
         }
      }
      return j1hsNotes;
   }

}