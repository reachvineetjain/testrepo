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
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.DepartmentProgramOption;
import com.ccighgo.db.entities.DocumentInformation;
import com.ccighgo.db.entities.Login;
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
import com.ccighgo.db.entities.SeasonProgramDocument;
import com.ccighgo.db.entities.SeasonProgramNote;
import com.ccighgo.db.entities.SeasonStatus;
import com.ccighgo.db.entities.SeasonTADetail;
import com.ccighgo.db.entities.SeasonVADetail;
import com.ccighgo.db.entities.SeasonWADetail;
import com.ccighgo.db.entities.SeasonWPAllocation;
import com.ccighgo.db.entities.SeasonWPConfiguration;
import com.ccighgo.db.entities.SeasonWnTSpringDetail;
import com.ccighgo.db.entities.SeasonWnTSummerDetail;
import com.ccighgo.db.entities.SeasonWnTWinterDetail;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.DepartmentRepository;
import com.ccighgo.jpa.repositories.DocumentInformationRepository;
import com.ccighgo.jpa.repositories.DocumentTypeDocumentCategoryProcessRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.SeasonCAPDetailsRepository;
import com.ccighgo.jpa.repositories.SeasonDepartmentDocumentRepository;
import com.ccighgo.jpa.repositories.SeasonDepartmentNotesRepository;
import com.ccighgo.jpa.repositories.SeasonF1DetailsRepository;
import com.ccighgo.jpa.repositories.SeasonGHTConfigurationRepository;
import com.ccighgo.jpa.repositories.SeasonHSADetailsRepository;
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
import com.ccighgo.jpa.repositories.SeasonWTWinterRepository;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection1Base;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection2Dates;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection3Notes;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.SeasonGHTDetails;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSAugStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSDocuments;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSFieldSettings;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSJanStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSNotes;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.SeasonHspJ1HSDetails;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.SeasonWPDetails;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.WPBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.WPDocuments;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.WPNotes;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.WPProgramAllocations;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.WPSectionOne;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.season.SeasonDepartmentNotes;
import com.ccighgo.service.transport.seasons.beans.season.SeasonDocument;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1Accounting;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1AugustStart1StSemesterDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1AugustStartFullYearDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1BasicDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1FieldSettings;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1JanuaryStart2NdSemesterDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1JanuaryStartFullYearDetail;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1ProgramAllocations;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1SeasonHspF1Documents;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1SeasonHspF1Notes;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.SeasonHSPF1Details;
import com.ccighgo.service.transport.seasons.beans.seasonslist.DepartmentObject;
import com.ccighgo.service.transport.seasons.beans.seasonslist.ProgramSeason;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonListObject;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.SeasonWPCAPDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.SeasonWPCAPDocuments;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.SeasonWPCAPNotes;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPBasicDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPInternshipDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPProgramAllocations;
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
   @Autowired
   SeasonProgramDocumentRepository seasonProgramDocumentRepository;
   @Autowired
   DepartmentProgramRepository departmentProgramRepository;
   @Autowired
   SeasonDepartmentDocumentRepository seasonDepartmentDocumentRepository;
   @Autowired
   LoginRepository loginRepository;
   @Autowired
   DocumentTypeDocumentCategoryProcessRepository documentTypeDocumentCategoryProcessRepository;
   @Autowired
   DocumentInformationRepository documentInformationRepository;
   @Autowired
   SeasonWPAllocationRepository seasonWPAllocationRepository;
   @Autowired
   SeasonWTWinterRepository seasonWTWinterRepository;
   @Autowired
   SeasonServiceInterface seasonServiceInterface;
   @Autowired
   SeasonIHPDetailRepository seasonIHPDetailRepository;
   @Autowired
   SeasonWPConfigurationRepository seasonWPConfigurationRepository;
   @Autowired
   SeasonGHTConfigurationRepository seasonGHTConfigurationRepository;

   /**
    * @param seasonBean
    * @param seasonEntity
    */
   public void convertEntitySeasonToBeanSeason(SeasonBean seasonBean, Season seasonEntity) {
      seasonBean.setSeasonId(seasonEntity.getSeasonId());
      seasonBean.setDepartmentId(seasonEntity.getLookupDepartment() != null ? seasonEntity.getLookupDepartment().getDepartmentId() : -1);
      seasonBean.setDepartmentCode(seasonEntity.getLookupDepartment() != null ? seasonEntity.getLookupDepartment().getAcronym() : null);
      seasonBean.setDepartmentName(seasonEntity.getLookupDepartment() != null ? seasonEntity.getLookupDepartment().getDepartmentName() : null);
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
            seasonDepartmentNotes.setSeasonDepartmentNotetId(note.getSeasonDepartmentNotesId());
            seasonBean.getNotes().add(seasonDepartmentNotes);
         }
      }

      if (seasonEntity.getSeasonDepartmentDocuments() != null && !seasonEntity.getSeasonDepartmentDocuments().isEmpty()) {
         for (SeasonDepartmentDocument departmentDocument : seasonEntity.getSeasonDepartmentDocuments()) {
            SeasonDocument document = new SeasonDocument();
            document.setSeasonId(departmentDocument.getSeason().getSeasonId());
            if (seasonEntity.getLookupDepartment() != null)
               document.setDepartmentId(seasonEntity.getLookupDepartment().getDepartmentId());
            document.setDocType(departmentDocument.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
            document.setDocUrl(departmentDocument.getDocumentInformation().getUrl());
            seasonBean.getDocuments().add(document);
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
            if (seasonEntity.getSeasonIhpdetails() != null && seasonEntity.getSeasonIhpdetails().size() > 0) {
               ProgramSeason programSeason = new ProgramSeason();
               programSeason.setSeasonProgramId(seasonEntity.getSeasonIhpdetails().get(0).getSeasonIHPDetailsId());
               programSeason.setSeasonProgramName(seasonEntity.getSeasonIhpdetails().get(0).getProgramName());
               programSeason.setSeasonProgramUrl(CCIConstants.HSP_IHP_URL);
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

   public void createSeasonConfiguration(SeasonBean seasonBean, Season seasonEntity) {
      LookupDepartment department = seasonEntity.getLookupDepartment();
      if(department.getDepartmentName().equals(CCIConstants.DEPT_HIGH_SCHOOL_PROGRAMS)){
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
      if(department.getDepartmentName().equals(CCIConstants.DEPT_WORK_PROGRAMS)){
         SeasonWPConfiguration seasonWPConfiguration = new SeasonWPConfiguration();
         seasonWPConfiguration.setSeason(seasonEntity);
         seasonWPConfiguration.setSeasonEndDate(DateUtils.getDateFromString(seasonBean.getEndDate()));
         seasonWPConfiguration.setSeasonStartDate(DateUtils.getDateFromString(seasonBean.getStartDate()));
         seasonWPConfiguration.setCreatedBy(1);
         seasonWPConfiguration.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonWPConfiguration.setModifiedBy(1);
         seasonWPConfiguration.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonWPConfigurationRepository.saveAndFlush(seasonWPConfiguration);
      }
      if(department.getDepartmentName().equals(CCIConstants.DEPT_GREEN_HEART_TRAVEL)){
         SeasonGHTConfiguration seasonGHTConfiguration = new SeasonGHTConfiguration();
         seasonGHTConfiguration.setSeason(seasonEntity);
         seasonGHTConfiguration.setSeasonEndDate(DateUtils.getDateFromString(seasonBean.getEndDate()));
         seasonGHTConfiguration.setSeasonStartDate(DateUtils.getDateFromString(seasonBean.getStartDate()));
         seasonGHTConfiguration.setCreatedBy(1);
         seasonGHTConfiguration.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonGHTConfiguration.setModifiedBy(1);
         seasonGHTConfiguration.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonGHTConfigurationRepository.saveAndFlush(seasonGHTConfiguration);
      }
   }

   public void updateSeasonConfiguration(SeasonBean seasonBean, Season seasonEntity) {
      LookupDepartment department = seasonEntity.getLookupDepartment();
      if(department.getDepartmentName().equals(CCIConstants.DEPT_HIGH_SCHOOL_PROGRAMS)){
         SeasonHSPConfiguration seasonHSPConfiguration = seasonHSPConfigurationRepsitory.getSeasonHSPConfigurationBySeasonId(seasonEntity.getSeasonId());
         if (seasonHSPConfiguration == null)
            return;
         seasonHSPConfiguration.setSeason(seasonEntity);
         seasonHSPConfiguration.setSeasonEndDate(DateUtils.getDateFromString(seasonBean.getEndDate()));
         seasonHSPConfiguration.setSeasonStartDate(DateUtils.getDateFromString(seasonBean.getStartDate()));
         seasonHSPConfiguration.setSeasonHSPConfigurationId(seasonBean.getSeasonHSPConfigurationId());
         seasonHSPConfigurationRepsitory.saveAndFlush(seasonHSPConfiguration);
      }
      if(department.getDepartmentName().equals(CCIConstants.DEPT_WORK_PROGRAMS)){
         SeasonWPConfiguration seasonWPConfiguration =  seasonWPConfigurationRepository.getSeasonWPConfigurationBySeasonId(seasonEntity.getSeasonId());
         if (seasonWPConfiguration == null)
            return;
         seasonWPConfiguration.setSeason(seasonEntity);
         seasonWPConfiguration.setSeasonEndDate(DateUtils.getDateFromString(seasonBean.getEndDate()));
         seasonWPConfiguration.setSeasonStartDate(DateUtils.getDateFromString(seasonBean.getStartDate()));
         seasonWPConfigurationRepository.saveAndFlush(seasonWPConfiguration);
         
      }
      if(department.getDepartmentName().equals(CCIConstants.DEPT_GREEN_HEART_TRAVEL)){
         SeasonGHTConfiguration seasonGHTConfiguration = seasonGHTConfigurationRepository.getSeasonGHTConfigurationBySeasonId(seasonEntity.getSeasonId());
         if (seasonGHTConfiguration == null)
            return;
         seasonGHTConfiguration.setSeason(seasonEntity);
         seasonGHTConfiguration.setSeasonEndDate(DateUtils.getDateFromString(seasonBean.getEndDate()));
         seasonGHTConfiguration.setSeasonStartDate(DateUtils.getDateFromString(seasonBean.getStartDate()));
         seasonGHTConfigurationRepository.saveAndFlush(seasonGHTConfiguration);
      }
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
         if (seasonHSPF1Details.getAccounting() != null)
            allF1Details.setGreenHeartMargin(seasonHSPF1Details.getAccounting().getGreenHeartMargin());
         if (seasonHSPF1Details.getJanuaryStart2NdSemesterDetails() != null) {
            allF1Details.setActiveFullYearJanProgram((byte) (seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().isActivateFullYearProgram() ? 1 : 0));
            allF1Details.setShowSecSemToNewHF((byte) (seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().isShow2NdSemestertoNewHF() ? 1 : 0));
            allF1Details.setSecondSemAppDeadlineDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getApplicationDeadlineDate()));
            allF1Details.setSecondSemEarliestBirthDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getEarliestBirthDate()));
            allF1Details.setSecondSemEndDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getEndDate()));
            allF1Details.setSecondSemLatestBirthDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getLatestBirthDate()));
            allF1Details.setSecondSemStartDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getStartDate()));
         }
         if (seasonHSPF1Details.getJanuaryStartFullYearDetail() != null) {
            allF1Details.setJanFullYearAppDeadlineDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStartFullYearDetail().getApplicationDeadlineDate()));
            allF1Details.setJanFullYearEndDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStartFullYearDetail().getEndDate()));
            allF1Details.setJanFullYearStartDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStartFullYearDetail().getStartDate()));
            allF1Details.setShowJanFullYearToNewHF((byte) (seasonHSPF1Details.getJanuaryStartFullYearDetail().isShowFullYearToHF() ? 1 : 0));
         }
         if (seasonHSPF1Details.getAugustStart1StSemesterDetails() != null) {
            allF1Details.setFirstSemAppDeadlineDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getApplicationDeadlineDate()));
            allF1Details.setFirstSemEarliestBirthDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getEarliestBirthDate()));
            allF1Details.setFirstSemLatestBirthDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getLatestBirthDate()));
            allF1Details.setFirstSemStartDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getStartDate()));
            allF1Details.setFirstSemEndDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getEndDate()));
         }
         if (seasonHSPF1Details.getAugustStartFullYearDetails() != null) {
            allF1Details.setAugFullYearAppDeadlineDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStartFullYearDetails().getApplicationDeadlineDate()));
            allF1Details.setAugFullYearEndDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStartFullYearDetails().getEndDate()));
            allF1Details.setAugFullYearStartDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStartFullYearDetails().getStartDate()));
            allF1Details.setShowAugFullYearToNewHF((byte) (seasonHSPF1Details.getAugustStartFullYearDetails().isShowFullYearToNewHF() ? 1 : 0));
         }
         if (seasonHSPF1Details.getFieldSettings() != null) {
            allF1Details.setHfInquiryDate(DateUtils.getDateFromString(seasonHSPF1Details.getFieldSettings().getAddOrStartHFInquiriesDate()));
            allF1Details.setAllowFieldStaffToStartRenewalProcess((byte) (seasonHSPF1Details.getFieldSettings().isAllowFSToStartRenewalProcess() ? 1 : 0));
            // allF1Details.setLcPaymentScheduleId(seasonHSPF1Details.getFieldSettings().getDefaultLcPaymentSchedule());
            // allF1Details.setFsAgreementId(seasonHSPF1Details.getFieldSettings().getFsAgreement());
            allF1Details.setHfReferences(seasonHSPF1Details.getFieldSettings().getHfReferencesNo());
            allF1Details.setShowSeasonToCurrentHF((byte) (seasonHSPF1Details.getFieldSettings().isShowSeasonProgramToCurrentHF() ? 1 : 0));
            allF1Details.setShowSpecialRequestStudent((byte) (seasonHSPF1Details.getFieldSettings().isShowSpecialRequestStudentToRD() ? 1 : 0));
            allF1Details.setShowWelcomeFamily((byte) (seasonHSPF1Details.getFieldSettings().isShowWelcomeFamilyModle() ? 1 : 0));
         }
         if (seasonHSPF1Details.getNotes() != null)
            updateHSPF1Notes(allF1Details, seasonHSPF1Details);
         if (seasonHSPF1Details.getDocuments() != null)
            updateHSPF1Documents(seasonHSPF1Details, allF1Details.getSeason());

         if (seasonHSPF1Details.getProgramAllocations() != null)
            seasonServiceInterface.updateF1ProgramAllocation(seasonHSPF1Details.getProgramAllocations());
         seasonF1DetailsRepository.saveAndFlush(allF1Details);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return seasonHSPF1Details;
   }

   private void updateHSPF1Documents(SeasonHSPF1Details seasonHSPF1Details, Season season) {
      List<SeasonProgramDocument> seasonProgramDocuments = seasonProgramDocumentRepository.findAllProgramDocumentsBySeasonIdAndDepartmentProgramId(season.getSeasonId(),
            CCIConstants.HSP_F1_ID);
      seasonProgramDocumentRepository.delete(seasonProgramDocuments);
      List<SeasonProgramDocument> newDocList = new ArrayList<SeasonProgramDocument>();
      for (HSPF1SeasonHspF1Documents f1Documents : seasonHSPF1Details.getDocuments()) {
         SeasonProgramDocument sprgDoc = new SeasonProgramDocument();
         DocumentInformation documentInformation = new DocumentInformation();
         documentInformation.setFileName(f1Documents.getFileName());
         documentInformation.setDocumentName(f1Documents.getDocName());
         documentInformation.setUrl(f1Documents.getDocUrl());
         documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(f1Documents.getDocType()));
         documentInformation.setCreatedBy(1);
         documentInformation.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         documentInformation.setModifiedBy(1);
         documentInformation.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         documentInformation = documentInformationRepository.saveAndFlush(documentInformation);
         sprgDoc.setActive(CCIConstants.ACTIVE);
         sprgDoc.setSeason(season);
         sprgDoc.setDepartmentProgram(departmentProgramRepository.findOne(CCIConstants.HSP_F1_ID));
         sprgDoc.setDocumentInformation(documentInformation);
         sprgDoc.setCreatedBy(1);
         sprgDoc.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         sprgDoc.setModifiedBy(1);
         sprgDoc.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         newDocList.add(sprgDoc);
      }
      seasonProgramDocumentRepository.save(newDocList);
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
         createHSPIHPSeasonProgram(seasonBean, seasonEntity);
      } else if (departmentName.equals(CCIConstants.DEPT_SYSTEM)) {
      } else if (departmentName.equals(CCIConstants.DEPT_WORK_PROGRAMS)) {
      }
   }

   private void createGHTTeachAbroad(Season seasonEntity, SeasonBean seasonBean) {
      if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
         SeasonTADetail seasonTADetail = new SeasonTADetail();
         seasonTADetail.setSeason(seasonEntity);
         seasonTADetail.setProgramName(seasonBean.getSeasonName());
         seasonTADetail.setSeasonStatus(seasonEntity.getSeasonStatus());
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
         seasonLSDetail.setSeasonStatus(seasonEntity.getSeasonStatus());
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
         seasonHSADetail.setSeasonStatus(seasonEntity.getSeasonStatus());
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
         seasonWADetail.setSeasonStatus(seasonEntity.getSeasonStatus());
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
         seasonVADetail.setSeasonStatus(seasonEntity.getSeasonStatus());
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
         seasonF1Detail.setProgramName(seasonBean.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.HSP_F1);
         seasonF1Detail.setSeasonStatus(seasonEntity.getSeasonStatus());
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
         seasonJ1Detail.setProgramName(seasonBean.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.HSP_J1_HS);
         seasonJ1Detail.setSeasonStatus(season.getSeasonStatus());
         seasonJ1Detail.setCreatedBy(1);
         seasonJ1Detail.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonJ1Detail.setModifiedBy(1);
         seasonJ1Detail.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
      }
   }

   /**
    * This method creates j1hs season program for HSP high level season
    * 
    * @param seasonBean
    */
   private void createHSPIHPSeasonProgram(SeasonBean seasonBean, Season season) {
      if (season.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
         SeasonIHPDetail seasonIHPDetail = new SeasonIHPDetail();
         seasonIHPDetail.setSeason(season);
         seasonIHPDetail.setProgramName(seasonBean.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.HSP_STP_IHP);
         seasonIHPDetail.setSeasonStatus(season.getSeasonStatus());
         seasonIHPDetail.setCreatedBy(1);
         seasonIHPDetail.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonIHPDetail.setModifiedBy(1);
         seasonIHPDetail.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonIHPDetailRepository.saveAndFlush(seasonIHPDetail);
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
      seasonGHTDetails.setDepartmentProgramId(CCIConstants.GHT_HS_ABRD_ID);
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
      seasonGHTDetails.getGhtNotes().addAll(getGHTWAProgramNotes(seasonId, seasonHSADetail.getSeasonHSADetailsId()));

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
         if (seasonGHTDetails.getGhtNotes() != null) {
            updateGHTNotes(seasonGHTDetails, seasonHsaDetail.getSeason(), CCIConstants.GHT_HS_ABRD_ID);
         }
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
      seasonGHTDetails.setDepartmentProgramId(CCIConstants.GHT_LANG_SCL_ID);
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
      seasonGHTDetails.getGhtNotes().addAll(getGHTLSProgramNotes(seasonId, seasonLSDetail.getSeasonLSDetailsId()));

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
         if (seasonGHTDetails.getGhtNotes() != null) {
            updateGHTNotes(seasonGHTDetails, seasonLSDetail.getSeason(), CCIConstants.GHT_LANG_SCL_ID);
         }
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
      seasonGHTDetails.setDepartmentProgramId(CCIConstants.GHT_TEACH_ABRD_ID);
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
      seasonGHTDetails.getGhtNotes().addAll(getGHTTAProgramNotes(seasonId, seasonTADetail.getSeasonTADetailsId()));

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
         if (seasonGHTDetails.getGhtNotes() != null) {
            updateGHTNotes(seasonGHTDetails, seasonTADetail.getSeason(), CCIConstants.GHT_TEACH_ABRD_ID);
         }
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
      wpcapBasicDetails.setSeasonProgramId(Integer.parseInt(seasonProgramId));
      seasonWPCAPDetails.setDepartmentProgramId(CCIConstants.WP_WT_CAP_ID);
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
      seasonWPCAPDetails.getDocuments().addAll(getWPCAPDocuments(seasonWPcap, Integer.parseInt(seasonProgramId)));
      seasonWPCAPDetails.getNotes().addAll(getWPCAPNotes(seasonWPcap, Integer.parseInt(seasonProgramId)));
      seasonWPCAPDetails.setProgramAllocations(seasonServiceInterface.getWPCAPAllocationDetails(seasonProgramId));
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
         updateWPCAPNotes(seasonCAPDetail, seasonWPCAPDetails);
         updateWPCAPDocuments(seasonWPCAPDetails, seasonCAPDetail.getSeason());
         seasonServiceInterface.updateWPCAPAllocationDetails(seasonWPCAPDetails.getProgramAllocations());
         seasonCAPDetailsRepository.saveAndFlush(seasonCAPDetail);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return seasonWPCAPDetails;

   }

   private void updateWPCAPDocuments(SeasonWPCAPDetails seasonWPCAPDetails, Season season) {
      List<SeasonProgramDocument> seasonProgramDocuments = seasonProgramDocumentRepository.findAllProgramDocumentsBySeasonIdAndDepartmentProgramId(season.getSeasonId(),
            CCIConstants.WP_WT_CAP_ID);
      seasonProgramDocumentRepository.delete(seasonProgramDocuments);
      List<SeasonProgramDocument> newDocList = new ArrayList<SeasonProgramDocument>();
      for (SeasonWPCAPDocuments f1Documents : seasonWPCAPDetails.getDocuments()) {
         SeasonProgramDocument sprgDoc = new SeasonProgramDocument();
         DocumentInformation documentInformation = new DocumentInformation();
         documentInformation.setFileName(f1Documents.getFileName());
         documentInformation.setDocumentName(f1Documents.getDocName());
         documentInformation.setUrl(f1Documents.getDocUrl());
         documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(f1Documents.getDocType()));
         documentInformation.setCreatedBy(1);
         documentInformation.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         documentInformation.setModifiedBy(1);
         documentInformation.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         documentInformation = documentInformationRepository.saveAndFlush(documentInformation);
         sprgDoc.setActive((byte) (f1Documents.isActive() ? 1 : 0));
         sprgDoc.setSeason(season);
         sprgDoc.setDepartmentProgram(departmentProgramRepository.findOne(CCIConstants.WP_WT_CAP_ID));
         sprgDoc.setDocumentInformation(documentInformation);
         sprgDoc.setCreatedBy(1);
         sprgDoc.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         sprgDoc.setModifiedBy(1);
         sprgDoc.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         newDocList.add(sprgDoc);
      }
      seasonProgramDocumentRepository.save(newDocList);
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
      seasonWPDetails.setDepartmentProgramId(CCIConstants.WP_WT_WINTER_ID);
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
      seasonWPDetails.getWpNotes().addAll(getWPWinterNotes(seasonWnTWinterDetail.getSeason().getSeasonId(), seasonWnTWinterDetail.getSeasonWnTWinterDetailsId()));
      seasonWPDetails.getWpDocuments()
            .addAll(
                  getWPDocs(seasonWnTWinterDetail.getSeason().getSeasonId(), seasonWnTWinterDetail.getSeasonWnTWinterDetailsId(), CCIConstants.WP_WT_WINTER,
                        CCIConstants.WP_WT_WINTER_ID));
      seasonWPDetails.setWpProgramAllocations(getWPWinterAllocationDetails(seasonProgramId));
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
      seasonWnTWinterDetail.setProgramName(seasonWPDetails.getWpBasicDetail().getProgramName());
      SeasonStatus seasonStatus = seasonStatusRepository.findSeasonStatusByName(seasonWPDetails.getWpBasicDetail().getProgramStatus());
      seasonWnTWinterDetail.setSeasonStatus(seasonStatus);
      if (seasonWPDetails.getWpProgramAllocations() != null) {
         updateWPWinterAllocationDetails(seasonWPDetails.getWpProgramAllocations());
      }
      if (seasonWPDetails.getWpNotes() != null) {
         updateWPNotes(seasonWPDetails, seasonWnTWinterDetail.getSeason(), CCIConstants.WP_WT_WINTER_ID);
      }
      if (seasonWPDetails.getWpDocuments() != null) {
         updateWPDocs(seasonWPDetails, seasonWnTWinterDetail.getSeason(), CCIConstants.WP_WT_WINTER_ID);
      }
      seasonWinterRepository.saveAndFlush(seasonWnTWinterDetail);
      return seasonWPDetails;
   }

   public WPBasicDetail getWPWinterBaseDetails(String seasonProgramId) {
      SeasonWnTWinterDetail seasonWnTWinterDetail = seasonWinterRepository.findOne(Integer.parseInt(seasonProgramId));
      WPBasicDetail wpBasicDetail = new WPBasicDetail();
      wpBasicDetail.setProgramName(seasonWnTWinterDetail.getProgramName());
      if (seasonWnTWinterDetail.getSeasonStatus() != null) {
         wpBasicDetail.setProgramStatus(seasonWnTWinterDetail.getSeasonStatus().getStatus());
      }
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

   public WPProgramAllocations getWPWinterAllocationDetails(String seasonProgramId) {
      WPProgramAllocations wpProgramAllocations = null;
      SeasonWnTWinterDetail seasonWnTWinterDetail = seasonWTWinterRepository.findOne(Integer.valueOf(seasonProgramId));
      if (seasonWnTWinterDetail != null) {
         List<SeasonWPAllocation> wpAllocations = seasonWPAllocationRepository.findSeasonWPAllocationBySeasonId(seasonWnTWinterDetail.getSeason().getSeasonId());
         if (wpAllocations != null) {
            wpProgramAllocations = new WPProgramAllocations();
            // TODO update other values once participants and partners modules are integrated
            int totalMaxParticipants = 0;
            wpProgramAllocations.setSeasonId(seasonWnTWinterDetail.getSeason().getSeasonId());
            wpProgramAllocations.setSeasonProgramId(seasonWnTWinterDetail.getSeasonWnTWinterDetailsId());
            for (SeasonWPAllocation seasonWPAllocation : wpAllocations) {
               if (seasonWPAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_WINTER_ID) {
                  if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JOB_FAIR_WINTER)) {
                     wpProgramAllocations.setJobFairMaxParticipants(seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0);
                     totalMaxParticipants += seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0;
                  }
                  if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.SELF_PLACED_WINTER)) {
                     wpProgramAllocations.setSelfPlacedMaxParticipants(seasonWPAllocation.getMaxPax());
                     totalMaxParticipants += seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0;
                  }
                  if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.DIRECT_PLACEMENT_WINTER)) {
                     wpProgramAllocations.setDirectPlcmntMaxParticipants(seasonWPAllocation.getMaxPax());
                     totalMaxParticipants += seasonWPAllocation.getMaxPax() > 0 ? seasonWPAllocation.getMaxPax() : 0;
                  }
               }
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
         }
      }
      return wpProgramAllocations;
   }

   public WPProgramAllocations updateWPWinterAllocationDetails(WPProgramAllocations wpProgramAllocations) {
      WPProgramAllocations returnObject = null;
      if (wpProgramAllocations != null && wpProgramAllocations.getSeasonId() > 0 && wpProgramAllocations.getSeasonProgramId() > 0) {
         List<SeasonWPAllocation> wpAllocations = seasonWPAllocationRepository.findSeasonWPAllocationBySeasonId(wpProgramAllocations.getSeasonId());
         List<SeasonWPAllocation> updatedList = new ArrayList<SeasonWPAllocation>();
         for (SeasonWPAllocation seasonWPAllocation : wpAllocations) {
            if (seasonWPAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_SPRING_ID) {
               updateWPProgramAllocation(wpProgramAllocations, updatedList, seasonWPAllocation);
            }
         }
         seasonWPAllocationRepository.save(updatedList);
         returnObject = getWPWinterAllocationDetails(String.valueOf(wpProgramAllocations.getSeasonProgramId()));
      }
      return returnObject;
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
               note.setDepartmentProgramId(CCIConstants.HSP_J1_HS_ID);
               note.setNoteValue(prgNote.getProgramNote());
               note.setCreatedOn(DateUtils.getDateAndTime(prgNote.getCreatedOn()));
               Login login = loginRepository.findOne(1);// TODO find user from session
               note.setCreatedBy(login.getLoginName());
               j1hsNotes.add(note);
            }
         }
      }
      return j1hsNotes;
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<J1HSDocuments> getJ1Docs(Integer seasonId, Integer seasonProgramId) {
      List<J1HSDocuments> j1hsDocuments = null;
      List<SeasonProgramDocument> seasonProgramDocuments = seasonProgramDocumentRepository.findAllProgramDocsBySeasonId(seasonId);
      if (seasonProgramDocuments != null) {
         j1hsDocuments = new ArrayList<J1HSDocuments>();
         for (SeasonProgramDocument programDocument : seasonProgramDocuments) {
            if (programDocument.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_J1_HS)) {
               J1HSDocuments documents = new J1HSDocuments();
               documents.setSeasonId(programDocument.getSeason().getSeasonId());
               documents.setSeasonProgramId(seasonProgramId);
               documents.setDepartmentProgramId(CCIConstants.HSP_J1_HS_ID);
               documents.setDocName(programDocument.getDocumentInformation().getDocumentName());
               documents.setFileName(programDocument.getDocumentInformation().getFileName());
               String docType = null;
               if (programDocument.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName() != null) {
                  docType = programDocument.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName();
               }
               documents.setDocType(docType);
               documents.setDocUrl(programDocument.getDocumentInformation().getUrl());
               documents.setUploadDate(DateUtils.getMMddyyDate(programDocument.getDocumentInformation().getModifiedOn()));
               documents.setActive(programDocument.getActive() == CCIConstants.ACTIVE ? true : false);
               Login login = loginRepository.findOne(1);// TODO find user from session
               documents.setUploadedBy(login.getLoginName());
               j1hsDocuments.add(documents);
            }
         }
      }
      return j1hsDocuments;
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<GHTSection3Notes> getGHTHSAProgramNotes(Integer seasonId, Integer seasonProgramId) {
      return getGHTNotes(seasonId, seasonProgramId, CCIConstants.GHT_HS_ABRD, CCIConstants.GHT_HS_ABRD_ID);
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<GHTSection3Notes> getGHTLSProgramNotes(Integer seasonId, Integer seasonProgramId) {
      return getGHTNotes(seasonId, seasonProgramId, CCIConstants.GHT_LANG_SCL, CCIConstants.GHT_LANG_SCL_ID);
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<GHTSection3Notes> getGHTTAProgramNotes(Integer seasonId, Integer seasonProgramId) {
      return getGHTNotes(seasonId, seasonProgramId, CCIConstants.GHT_TEACH_ABRD, CCIConstants.GHT_TEACH_ABRD_ID);
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<GHTSection3Notes> getGHTVAProgramNotes(Integer seasonId, Integer seasonProgramId) {
      return getGHTNotes(seasonId, seasonProgramId, CCIConstants.GHT_VOL_ABRD, CCIConstants.GHT_VOL_ABRD_ID);
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<GHTSection3Notes> getGHTWAProgramNotes(Integer seasonId, Integer seasonProgramId) {
      return getGHTNotes(seasonId, seasonProgramId, CCIConstants.GHT_WRK_ABRD, CCIConstants.GHT_WRK_ABRD_ID);
   }

   /**
    * @param seasonProgramId
    * @param ghtNotes
    * @param prgNote
    */
   private List<GHTSection3Notes> getGHTNotes(Integer seasonId, Integer seasonProgramId, String programType, Integer departmentProgramId) {
      List<GHTSection3Notes> ghtNotes = null;
      List<SeasonProgramNote> ghtProgramNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonId(seasonId);
      if (ghtProgramNotes != null) {
         ghtNotes = new ArrayList<GHTSection3Notes>();
         for (SeasonProgramNote prgNote : ghtProgramNotes) {
            if (prgNote.getDepartmentProgram().getProgramName().equals(programType)) {
               GHTSection3Notes notes = new GHTSection3Notes();
               notes.setSeasonId(prgNote.getSeason().getSeasonId());
               notes.setSeasonProgramId(seasonProgramId);
               notes.setDepartmentProgramId(departmentProgramId);
               notes.setNoteValue(prgNote.getProgramNote());
               notes.setCreatedOn(DateUtils.getDateAndTime(prgNote.getCreatedOn()));
               Login login = loginRepository.findOne(1);// TODO find user from session
               notes.setCreatedBy(login.getLoginName());
               ghtNotes.add(notes);
            }
         }
      }
      return ghtNotes;
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<WPNotes> getWPSummerNotes(Integer seasonId, Integer seasonProgramId) {
      return getWPNotes(seasonId, seasonProgramId, CCIConstants.WP_WT_SUMMER, CCIConstants.WP_WT_SUMMER_ID);
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<WPNotes> getWPSpringNotes(Integer seasonId, Integer seasonProgramId) {
      return getWPNotes(seasonId, seasonProgramId, CCIConstants.WP_WT_SPRING, CCIConstants.WP_WT_SPRING_ID);
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<WPNotes> getWPWinterNotes(Integer seasonId, Integer seasonProgramId) {
      return getWPNotes(seasonId, seasonProgramId, CCIConstants.WP_WT_WINTER, CCIConstants.WP_WT_WINTER_ID);
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @param wpNotes
    * @return
    */
   private List<WPNotes> getWPNotes(Integer seasonId, Integer seasonProgramId, String programType, Integer departmentProgramId) {
      List<WPNotes> wpNotes = null;
      List<SeasonProgramNote> wpProgramNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonId(seasonId);
      if (wpProgramNotes != null) {
         wpNotes = new ArrayList<WPNotes>();
         for (SeasonProgramNote prgNote : wpProgramNotes) {
            if (prgNote.getDepartmentProgram().getProgramName().equals(programType)) {
               WPNotes notes = new WPNotes();
               notes.setSeasonId(prgNote.getSeason().getSeasonId());
               notes.setSeasonProgramId(seasonProgramId);
               notes.setDepartmentProgramId(departmentProgramId);
               notes.setNoteValue(prgNote.getProgramNote());
               notes.setCreatedOn(DateUtils.getDateAndTime(prgNote.getCreatedOn()));
               Login login = loginRepository.findOne(1);// TODO find user from session
               notes.setCreatedBy(login.getLoginName());
               wpNotes.add(notes);
            }
         }
      }
      return wpNotes;
   }

   /**
    * @param seasonHspJ1HSDetails
    * @param seasonJ1Detail
    */
   public void updateJ1Notes(SeasonHspJ1HSDetails seasonHspJ1HSDetails, Season season) {
      List<SeasonProgramNote> programNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(season.getSeasonId(), CCIConstants.HSP_J1_HS_ID);
      seasonProgramNotesRepository.delete(programNotes);
      List<SeasonProgramNote> updatedNotes = new ArrayList<SeasonProgramNote>();
      for (J1HSNotes j1Note : seasonHspJ1HSDetails.getJ1HsNotes()) {
         SeasonProgramNote sprNote = new SeasonProgramNote();
         sprNote.setSeason(season);
         sprNote.setProgramNote(j1Note.getNoteValue());
         sprNote.setDepartmentProgram(departmentProgramRepository.findOne(CCIConstants.HSP_J1_HS_ID));
         sprNote.setCreatedBy(1);
         sprNote.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         sprNote.setModifiedBy(1);
         sprNote.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         updatedNotes.add(sprNote);
      }
      seasonProgramNotesRepository.save(updatedNotes);
   }

   /**
    * common method to update all GHT program type notes
    * 
    * @param seasonGHTDetails
    * @param season
    * @param programTypeId
    */
   public void updateGHTNotes(SeasonGHTDetails seasonGHTDetails, Season season, Integer programTypeId) {
      List<SeasonProgramNote> programNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(season.getSeasonId(), programTypeId);
      seasonProgramNotesRepository.delete(programNotes);
      List<SeasonProgramNote> updatedNotes = new ArrayList<SeasonProgramNote>();
      for (GHTSection3Notes ghtSection3Notes : seasonGHTDetails.getGhtNotes()) {
         String note = ghtSection3Notes.getNoteValue();
         updateNotes(season, programTypeId, updatedNotes, note);
      }
      seasonProgramNotesRepository.save(updatedNotes);
   }

   /**
    * Common method to update WP Summer, Spring and Winter season program notes
    * 
    * @param seasonWPDetails
    * @param season
    * @param programTypeId
    */
   public void updateWPNotes(SeasonWPDetails seasonWPDetails, Season season, Integer programTypeId) {
      List<SeasonProgramNote> programNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(season.getSeasonId(), programTypeId);
      seasonProgramNotesRepository.delete(programNotes);
      List<SeasonProgramNote> updatedNotes = new ArrayList<SeasonProgramNote>();
      for (WPNotes wpNotes : seasonWPDetails.getWpNotes()) {
         String note = wpNotes.getNoteValue();
         updateNotes(season, programTypeId, updatedNotes, note);
      }
      seasonProgramNotesRepository.save(updatedNotes);
   }

   /**
    * @param season
    * @param programTypeId
    * @param updatedNotes
    * @param note
    */
   private void updateNotes(Season season, Integer programTypeId, List<SeasonProgramNote> updatedNotes, String note) {
      SeasonProgramNote sprNote = new SeasonProgramNote();
      sprNote.setSeason(season);
      sprNote.setProgramNote(note);
      sprNote.setDepartmentProgram(departmentProgramRepository.findOne(programTypeId));
      sprNote.setCreatedBy(1);
      sprNote.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
      sprNote.setModifiedBy(1);
      sprNote.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      updatedNotes.add(sprNote);
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<WPDocuments> getWPDocs(Integer seasonId, Integer seasonProgramId, String programType, Integer departmentProgramId) {
      List<WPDocuments> wpDocuments = null;
      List<SeasonProgramDocument> seasonProgramDocuments = seasonProgramDocumentRepository.findAllProgramDocsBySeasonId(seasonId);
      if (seasonProgramDocuments != null) {
         wpDocuments = new ArrayList<WPDocuments>();
         for (SeasonProgramDocument programDocument : seasonProgramDocuments) {
            if (programDocument.getDepartmentProgram().getProgramName().equals(programType)) {
               WPDocuments documents = new WPDocuments();
               documents.setSeasonId(programDocument.getSeason().getSeasonId());
               documents.setSeasonProgramId(seasonProgramId);
               documents.setDepartmentProgramId(departmentProgramId);
               documents.setDocName(programDocument.getDocumentInformation().getDocumentName());
               documents.setFileName(programDocument.getDocumentInformation().getFileName());
               documents.setDocType(programDocument.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
               documents.setDocUrl(programDocument.getDocumentInformation().getUrl());
               documents.setUploadDate(DateUtils.getMMddyyDate(programDocument.getDocumentInformation().getModifiedOn()));
               documents.setActive(programDocument.getActive() == CCIConstants.ACTIVE ? true : false);
               Login login = loginRepository.findOne(1);// TODO find user from session
               documents.setUploadedBy(login.getLoginName());
               wpDocuments.add(documents);
            }
         }
      }
      return wpDocuments;
   }

   public void updateJ1HSDocs(SeasonHspJ1HSDetails seasonHspJ1HSDetails, Season season) {
      List<SeasonProgramDocument> seasonProgramDocuments = seasonProgramDocumentRepository.findAllProgramDocumentsBySeasonIdAndDepartmentProgramId(season.getSeasonId(),
            CCIConstants.HSP_J1_HS_ID);
      seasonProgramDocumentRepository.delete(seasonProgramDocuments);
      List<SeasonProgramDocument> newDocList = new ArrayList<SeasonProgramDocument>();
      for (J1HSDocuments j1hsDocument : seasonHspJ1HSDetails.getJ1HsDocuments()) {
         SeasonProgramDocument sprgDoc = new SeasonProgramDocument();
         DocumentInformation documentInformation = new DocumentInformation();
         documentInformation.setFileName(j1hsDocument.getFileName());
         documentInformation.setDocumentName(j1hsDocument.getDocName());
         documentInformation.setUrl(j1hsDocument.getDocUrl());
         documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(j1hsDocument.getDocType()));
         documentInformation.setCreatedBy(1);
         documentInformation.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         documentInformation.setModifiedBy(1);
         documentInformation.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         documentInformation = documentInformationRepository.saveAndFlush(documentInformation);
         sprgDoc.setActive(CCIConstants.ACTIVE);
         sprgDoc.setSeason(season);
         sprgDoc.setDepartmentProgram(departmentProgramRepository.findOne(CCIConstants.HSP_J1_HS_ID));
         sprgDoc.setDocumentInformation(documentInformation);
         sprgDoc.setCreatedBy(1);
         sprgDoc.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         sprgDoc.setModifiedBy(1);
         sprgDoc.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         newDocList.add(sprgDoc);
      }
      seasonProgramDocumentRepository.save(newDocList);
   }

   public void updateWPDocs(SeasonWPDetails seasonWPDetails, Season season, Integer programTypeId) {
      List<SeasonProgramDocument> seasonProgramDocuments = seasonProgramDocumentRepository.findAllProgramDocumentsBySeasonIdAndDepartmentProgramId(season.getSeasonId(),
            programTypeId);
      seasonProgramDocumentRepository.delete(seasonProgramDocuments);
      List<SeasonProgramDocument> newDocList = new ArrayList<SeasonProgramDocument>();
      for (WPDocuments wpDocument : seasonWPDetails.getWpDocuments()) {
         SeasonProgramDocument sprgDoc = new SeasonProgramDocument();
         DocumentInformation documentInformation = new DocumentInformation();
         documentInformation.setFileName(wpDocument.getFileName());
         documentInformation.setDocumentName(wpDocument.getDocName());
         documentInformation.setUrl(wpDocument.getDocUrl());
         documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(wpDocument.getDocType()));
         documentInformation.setCreatedBy(1);
         documentInformation.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         documentInformation.setModifiedBy(1);
         documentInformation.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         documentInformation = documentInformationRepository.saveAndFlush(documentInformation);
         sprgDoc.setActive(CCIConstants.ACTIVE);
         sprgDoc.setSeason(season);
         sprgDoc.setDepartmentProgram(departmentProgramRepository.findOne(programTypeId));
         sprgDoc.setDocumentInformation(documentInformation);
         sprgDoc.setCreatedBy(1);
         sprgDoc.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         sprgDoc.setModifiedBy(1);
         sprgDoc.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         newDocList.add(sprgDoc);
      }
      seasonProgramDocumentRepository.save(newDocList);
   }

   public List<SeasonDocument> getHighLevelSeasonDocs(Integer seasonId, Integer departmentId) {
      List<SeasonDocument> seasonDocuments = null;
      List<SeasonDepartmentDocument> seasonDepartmentDocuments = seasonDepartmentDocumentRepository.findAllDepartmentDocsBySeasonId(seasonId);
      if (seasonDepartmentDocuments != null) {
         seasonDocuments = new ArrayList<SeasonDocument>();
         for (SeasonDepartmentDocument departmentDocument : seasonDepartmentDocuments) {
            SeasonDocument document = new SeasonDocument();
            document.setSeasonId(departmentDocument.getSeason().getSeasonId());
            document.setDepartmentId(departmentId);
            document.setDocType(departmentDocument.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
            document.setDocUrl(departmentDocument.getDocumentInformation().getUrl());
            seasonDocuments.add(document);
         }
      }
      return seasonDocuments;
   }

   /**
    * 
    * @param departmentId
    * @param seasonBean
    * @param seasonEntity
    */
   public void createSeasonProgramNotes(CommonNotesObject note) {
      if (note != null && note.getNotes() != null && !note.getNotes().isEmpty()) {
         Season season = seasonRepository.findOne(note.getSeasonId());
         DepartmentProgram departmentProgram = departmentProgramRepository.findOne(note.getDepartmentProgramId());

         for (String noteStr : note.getNotes()) {
            SeasonProgramNote seasonProgramNote = new SeasonProgramNote();
            seasonProgramNote.setActive((byte) 1);
            seasonProgramNote.setProgramNote(noteStr);
            seasonProgramNote.setSeason(season);
            seasonProgramNote.setDepartmentProgram(departmentProgram);
            seasonProgramNote.setCreatedBy(1);
            seasonProgramNote.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
            seasonProgramNote.setModifiedBy(1);
            seasonProgramNote.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
            seasonProgramNotesRepository.saveAndFlush(seasonProgramNote);
         }
      }
   }

   /**
    * 
    * @param seasonBean
    * @param seasonEntity
    */
   public void updateSeasonProgramNotes(CommonNotesObject note) {
      if (note != null && note.getNotes() != null && !note.getNotes().isEmpty()) {
         List<SeasonProgramNote> seasonProgramNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(note.getSeasonId(),
               note.getDepartmentProgramId());
         for (SeasonProgramNote seasonProgramNote : seasonProgramNotes) {
            seasonProgramNotesRepository.delete(seasonProgramNote.getSeasonProgramNotesId());
         }

         Season season = seasonRepository.findOne(note.getSeasonId());
         DepartmentProgram departmentProgram = departmentProgramRepository.findOne(note.getDepartmentProgramId());
         for (String noteStr : note.getNotes()) {
            SeasonProgramNote seasonProgramNote = new SeasonProgramNote();
            seasonProgramNote.setActive((byte) 1);
            seasonProgramNote.setProgramNote(noteStr);
            seasonProgramNote.setSeason(season);
            seasonProgramNote.setDepartmentProgram(departmentProgram);
            seasonProgramNote.setCreatedBy(1);
            seasonProgramNote.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
            seasonProgramNote.setModifiedBy(1);
            seasonProgramNote.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
            seasonProgramNotesRepository.saveAndFlush(seasonProgramNote);
         }
      }
   }

   public List<HSPF1SeasonHspF1Notes> getHSPF1Notes(SeasonF1Detail allF1Details) {
      int seasonId = allF1Details.getSeason().getSeasonId();
      List<SeasonProgramNote> seasonProgramNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(seasonId, CCIConstants.HSP_F1_ID);
      List<HSPF1SeasonHspF1Notes> hspF1Notes = new ArrayList<HSPF1SeasonHspF1Notes>();
      if (seasonProgramNotes != null && !seasonProgramNotes.isEmpty()) {
         for (SeasonProgramNote seasonProgramNote : seasonProgramNotes) {
            HSPF1SeasonHspF1Notes hspf1SeasonHspF1Note = new HSPF1SeasonHspF1Notes();
            hspf1SeasonHspF1Note.setSeasonId(seasonId);
            hspf1SeasonHspF1Note.setNoteValue(seasonProgramNote.getProgramNote());
            hspf1SeasonHspF1Note.setCreatedBy(seasonProgramNote.getCreatedBy() + "");
            hspf1SeasonHspF1Note.setCreatedOn(DateUtils.getDateAndTime(seasonProgramNote.getCreatedOn()));
            hspF1Notes.add(hspf1SeasonHspF1Note);
         }
      }
      return hspF1Notes;
   }

   @Transactional
   private void updateHSPF1Notes(SeasonF1Detail allF1Details, SeasonHSPF1Details seasonHSPF1Details) {
      int seasonId = allF1Details.getSeason().getSeasonId();
      List<SeasonProgramNote> seasonProgramNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(seasonId, CCIConstants.HSP_F1_ID);
      seasonProgramNotesRepository.delete(seasonProgramNotes);
      seasonProgramNotes = new ArrayList<SeasonProgramNote>();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.HSP_F1_ID);
      for (HSPF1SeasonHspF1Notes hspF1Notes : seasonHSPF1Details.getNotes()) {
         SeasonProgramNote programNote = new SeasonProgramNote();
         programNote.setActive((byte) 1);
         programNote.setDepartmentProgram(departmentProgram);
         programNote.setProgramNote(hspF1Notes.getNoteValue());
         programNote.setSeason(allF1Details.getSeason());
         programNote.setCreatedBy(1);
         programNote.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         programNote.setModifiedBy(1);
         programNote.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonProgramNotes.add(programNote);
      }
      seasonProgramNotesRepository.save(seasonProgramNotes);
   }

   public List<HSPF1SeasonHspF1Documents> getHSPF1Documents(SeasonF1Detail allF1Details, int seasonProgramId) {
      int seasonId = allF1Details.getSeason().getSeasonId();
      List<SeasonProgramDocument> spDocument = seasonProgramDocumentRepository.findAllProgramDocumentsBySeasonIdAndDepartmentProgramId(seasonId, CCIConstants.HSP_F1_ID);
      List<HSPF1SeasonHspF1Documents> hspF1Documents = new ArrayList<HSPF1SeasonHspF1Documents>();
      for (SeasonProgramDocument seasonProgramDocument : spDocument) {
         HSPF1SeasonHspF1Documents doc = new HSPF1SeasonHspF1Documents();
         doc.setSeasonId(seasonProgramDocument.getSeason().getSeasonId());
         doc.setSeasonProgramId(seasonProgramId);
         doc.setDepartmentProgramId(CCIConstants.HSP_F1_ID);
         doc.setDocName(seasonProgramDocument.getDocumentInformation().getDocumentName());
         doc.setFileName(seasonProgramDocument.getDocumentInformation().getFileName());
         doc.setDocType(seasonProgramDocument.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
         doc.setDocUrl(seasonProgramDocument.getDocumentInformation().getUrl());
         doc.setUploadDate(DateUtils.getMMddyyDate(seasonProgramDocument.getDocumentInformation().getModifiedOn()));
         doc.setActive(seasonProgramDocument.getActive() == CCIConstants.ACTIVE ? true : false);
         Login login = loginRepository.findOne(1);// TODO find user from session
         doc.setUploadedBy(login.getLoginName());

         hspF1Documents.add(doc);
      }
      return hspF1Documents;
   }

   private List<SeasonWPCAPDocuments> getWPCAPDocuments(SeasonCAPDetail seasonWPcap, int seasonProgramId) {
      int seasonId = seasonWPcap.getSeason().getSeasonId();
      List<SeasonProgramDocument> spDocument = seasonProgramDocumentRepository.findAllProgramDocumentsBySeasonIdAndDepartmentProgramId(seasonId, CCIConstants.WP_WT_CAP_ID);
      List<SeasonWPCAPDocuments> wpcapDocuments = new ArrayList<SeasonWPCAPDocuments>();
      for (SeasonProgramDocument seasonProgramDocument : spDocument) {
         SeasonWPCAPDocuments doc = new SeasonWPCAPDocuments();

         doc.setSeasonId(seasonProgramDocument.getSeason().getSeasonId());
         doc.setSeasonProgramId(seasonProgramId);
         doc.setDepartmentProgramId(CCIConstants.WP_WT_CAP_ID);
         doc.setDocName(seasonProgramDocument.getDocumentInformation().getDocumentName());
         doc.setFileName(seasonProgramDocument.getDocumentInformation().getFileName());
         doc.setDocType(seasonProgramDocument.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
         doc.setDocUrl(seasonProgramDocument.getDocumentInformation().getUrl());
         doc.setUploadDate(DateUtils.getDateAndTime(seasonProgramDocument.getDocumentInformation().getModifiedOn()));
         doc.setActive(seasonProgramDocument.getActive() == CCIConstants.ACTIVE ? true : false);
         Login login = loginRepository.findOne(1);// TODO find user from session
         doc.setUploadedBy(login.getLoginName());

         wpcapDocuments.add(doc);
      }
      return wpcapDocuments;
   }

   public List<SeasonWPCAPNotes> getWPCAPNotes(SeasonCAPDetail seasonWPcap, int seasonProgramId) {
      int seasonId = seasonWPcap.getSeason().getSeasonId();
      List<SeasonProgramNote> seasonProgramNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(seasonId, CCIConstants.WP_WT_CAP_ID);
      List<SeasonWPCAPNotes> seasonWPCAPNotes = new ArrayList<SeasonWPCAPNotes>();
      if (seasonProgramNotes != null && !seasonProgramNotes.isEmpty()) {
         for (SeasonProgramNote seasonProgramNote : seasonProgramNotes) {
            SeasonWPCAPNotes wpCapNote = new SeasonWPCAPNotes();
            wpCapNote.setSeasonId(seasonId);
            wpCapNote.setNoteValue(seasonProgramNote.getProgramNote());
            wpCapNote.setSeasonProgramId(seasonProgramId);
            wpCapNote.setDepartmentProgramId(seasonProgramNote.getDepartmentProgram().getDepartmentProgramId());
            wpCapNote.setCreatedBy(seasonProgramNote.getCreatedBy() + "");
            wpCapNote.setCreatedOn(DateUtils.getDateAndTime(seasonProgramNote.getCreatedOn()));
            seasonWPCAPNotes.add(wpCapNote);
         }
      }
      return seasonWPCAPNotes;
   }

   @Transactional
   private void updateWPCAPNotes(SeasonCAPDetail seasonCapDetail, SeasonWPCAPDetails seasonWpcapDetails) {
      int seasonId = seasonCapDetail.getSeason().getSeasonId();
      List<SeasonProgramNote> seasonProgramNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(seasonId, CCIConstants.WP_WT_CAP_ID);
      seasonProgramNotesRepository.delete(seasonProgramNotes);
      seasonProgramNotes = new ArrayList<SeasonProgramNote>();
      DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.WP_WT_CAP_ID);
      for (SeasonWPCAPNotes notes : seasonWpcapDetails.getNotes()) {
         SeasonProgramNote programNote = new SeasonProgramNote();
         programNote.setActive((byte) 1);
         programNote.setDepartmentProgram(departmentProgram);
         programNote.setProgramNote(notes.getNoteValue());
         programNote.setSeason(seasonCapDetail.getSeason());
         programNote.setCreatedBy(1);
         programNote.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         programNote.setModifiedBy(1);
         programNote.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonProgramNotes.add(programNote);

      }
      seasonProgramNotesRepository.save(seasonProgramNotes);
   }

   /**
    * @param wpProgramAllocations
    * @param updatedList
    * @param seasonWPAllocation
    */
   public void updateWPProgramAllocation(WPProgramAllocations wpProgramAllocations, List<SeasonWPAllocation> updatedList, SeasonWPAllocation seasonWPAllocation) {
      if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JOB_FAIR_SUMMER)) {
         SeasonWPAllocation allocation = new SeasonWPAllocation();
         allocation = seasonWPAllocation;
         allocation.setMaxPax(wpProgramAllocations.getJobFairMaxParticipants());
         updatedList.add(allocation);
      }
      if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.SELF_PLACED_SUMMER)) {
         SeasonWPAllocation allocation = new SeasonWPAllocation();
         allocation = seasonWPAllocation;
         allocation.setMaxPax(wpProgramAllocations.getSelfPlacedMaxParticipants());
         updatedList.add(allocation);
      }
      if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.DIRECT_PLACEMENT_SUMMER)) {
         SeasonWPAllocation allocation = new SeasonWPAllocation();
         allocation = seasonWPAllocation;
         allocation.setMaxPax(wpProgramAllocations.getDirectPlcmntMaxParticipants());
         updatedList.add(allocation);
      }
   }

   /**
    * 
    * @param wpCapProgramAllocations
    * @param updatedList
    * @param seasonWPAllocation
    */
   public void updateWPProgramAllocation(WPCAPProgramAllocations wpCapProgramAllocations, List<SeasonWPAllocation> updatedList, SeasonWPAllocation seasonWPAllocation) {
      if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.CAP_INTERNSHIP)) {
         SeasonWPAllocation allocation = new SeasonWPAllocation();
         allocation = seasonWPAllocation;
         allocation.setMaxPax(wpCapProgramAllocations.getInternshipMaximumParticipant());
         updatedList.add(allocation);
      }
      if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.CAP_TRAINEE)) {
         SeasonWPAllocation allocation = new SeasonWPAllocation();
         allocation = seasonWPAllocation;
         allocation.setMaxPax(wpCapProgramAllocations.getTraineeMaximumParticipant());
         updatedList.add(allocation);
      }
   }

   public void updateWPProgramAllocation(HSPF1ProgramAllocations hspf1ProgramAllocations, List<SeasonWPAllocation> updatedList, SeasonWPAllocation seasonWPAllocation) {
      if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_F1)) {
         SeasonWPAllocation allocation = new SeasonWPAllocation();
         allocation = seasonWPAllocation;
         allocation.setMaxPax(hspf1ProgramAllocations.getAugustStartMaximumParticipants());
         updatedList.add(allocation);
      }
      if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_F1)) {
         SeasonWPAllocation allocation = new SeasonWPAllocation();
         allocation = seasonWPAllocation;
         allocation.setMaxPax(hspf1ProgramAllocations.getJanuaryStartMaximumParticipants());
         updatedList.add(allocation);
      }

   }

   public void updateHSPF1ProgramAllocation(HSPF1ProgramAllocations hspF1ProgramAllocation, List<SeasonHSPAllocation> updatedList, SeasonHSPAllocation seasonHSPAllocation) {
      if (seasonHSPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_F1)) {
         SeasonHSPAllocation allocation = new SeasonHSPAllocation();
         allocation = seasonHSPAllocation;
         allocation.setMaxGuaranteedPax(hspF1ProgramAllocation.getAugustStartMaximumParticipants());
         allocation.setMaxUnguaranteedPax(hspF1ProgramAllocation.getAugustStartMaximumParticipants());
         updatedList.add(allocation);
      }
      if (seasonHSPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_F1)) {
         SeasonHSPAllocation allocation = new SeasonHSPAllocation();
         allocation = seasonHSPAllocation;
         allocation.setMaxGuaranteedPax(hspF1ProgramAllocation.getJanuaryStartMaximumParticipants());
         allocation.setMaxUnguaranteedPax(hspF1ProgramAllocation.getJanuaryStartMaximumParticipants());
         updatedList.add(allocation);
      }
   }
}