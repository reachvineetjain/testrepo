/**
 * 
 */
package com.ccighgo.service.components.season;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.DepartmentProgramOption;
import com.ccighgo.db.entities.DocumentInformation;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LookupDepartment;
import com.ccighgo.db.entities.RegionIHP;
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
import com.ccighgo.db.entities.SeasonIHPDetailsRegionApplication;
import com.ccighgo.db.entities.SeasonIHPGeographyConfiguration;
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
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.exception.ValidationException;
import com.ccighgo.jpa.repositories.DepartmentProgramOptionRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.DepartmentRepository;
import com.ccighgo.jpa.repositories.DocumentInformationRepository;
import com.ccighgo.jpa.repositories.DocumentTypeDocumentCategoryProcessRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.RegionIHPRepository;
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
import com.ccighgo.jpa.repositories.SeasonIHPGeographyConfigurationRepository;
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
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.HostFamilyMessageConstants;
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
   private static final String SP_REGION_FS_ROLLOVER = "CALL SPRegionAndFSRollover(?)";

   @Autowired SeasonStatusRepository seasonStatusRepository;
   @Autowired DepartmentRepository departmentRepository;
   @Autowired SeasonHSPConfigurationRepsitory seasonHSPConfigurationRepsitory;
   @Autowired SeasonF1DetailsRepository seasonF1DetailsRepository;
   @Autowired SeasonJ1DetailsRepository seasonJ1DetailsRepository;
   @Autowired SeasonRepository seasonRepository;
   @Autowired SeasonHSADetailsRepository seasonHSADetailsRepository;
   @Autowired SeasonLSDetailsRepository seasonLSDetailsRepository;
   @Autowired SeasonTADetailsRepository seasonTADetailsRepository;
   @Autowired SeasonVADetailsRepository seasonVADetailsRepository;
   @Autowired SeasonWADetailsRepository seasonWADetailsRepository;
   @Autowired SeasonCAPDetailsRepository seasonCAPDetailsRepository;
   @Autowired SeasonWTWinterRepository seasonWinterRepository;
   @Autowired SeasonDepartmentNotesRepository seasonDepartmentNotesRepository;
   @Autowired SeasonProgramNotesRepository seasonProgramNotesRepository;
   @Autowired SeasonProgramDocumentRepository seasonProgramDocumentRepository;
   @Autowired DepartmentProgramRepository departmentProgramRepository;
   @Autowired SeasonDepartmentDocumentRepository seasonDepartmentDocumentRepository;
   @Autowired LoginRepository loginRepository;
   @Autowired DocumentTypeDocumentCategoryProcessRepository documentTypeDocumentCategoryProcessRepository;
   @Autowired DocumentInformationRepository documentInformationRepository;
   @Autowired SeasonWPAllocationRepository seasonWPAllocationRepository;
   @Autowired SeasonWTWinterRepository seasonWTWinterRepository;
   @Autowired SeasonServiceInterface seasonServiceInterface;
   @Autowired SeasonIHPDetailRepository seasonIHPDetailRepository;
   @Autowired SeasonWPConfigurationRepository seasonWPConfigurationRepository;
   @Autowired SeasonGHTConfigurationRepository seasonGHTConfigurationRepository;
   @Autowired DepartmentProgramOptionRepository departmentProgramOptionRepository;
   @Autowired SeasonWTSpringRepository seasonWTSpringRepository;
   @Autowired SeasonWTSummerRepository seasonWTSummerRepository;
   @Autowired SeasonHSPAllocationRepository seasonHSPAllocationRepository;
   @Autowired RegionIHPRepository regionIHPRepository;
   @Autowired SeasonIHPDetailsRegionApplicationRepository seasonIHPDetailsRegionApplicationRepository;
   @Autowired SeasonIHPGeographyConfigurationRepository seasonIHPGeographyConfigurationRepository;
   @Autowired SeasonGeographyConfigurationRepository seasonGeographyConfigurationRepository;
   @Autowired MessageUtils messageUtil;
   @Autowired EntityManager entityManager;

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
      seasonBean.setCloneSeasonName(seasonEntity.getClonedSeasonName() != null ? seasonEntity.getClonedSeasonName() : null);

      if (seasonEntity.getSeasonStatus() != null) {
         seasonBean.setSeasonStatusValue(seasonEntity.getSeasonStatus() != null ? seasonEntity.getSeasonStatus().getStatus() : CCIConstants.EMPTY_DATA);
         seasonBean.setSeasonStatusId(seasonEntity.getSeasonStatus() != null ? seasonEntity.getSeasonStatus().getSeasonStatusId() : CCIConstants.EMPTY_INTEGER_FIELD);
      }

      String startDate = CCIConstants.EMPTY_DATA, endDate = CCIConstants.EMPTY_DATA;
      Integer seasonHspConfId = CCIConstants.EMPTY_INTEGER_FIELD;

      try {
         LookupDepartment lookupDepartment = seasonEntity.getLookupDepartment();
         if (lookupDepartment != null) {
            try {
               if (lookupDepartment.getDepartmentName().equals(CCIConstants.DEPT_HIGH_SCHOOL_PROGRAMS)) {
                  if (seasonEntity.getSeasonHspconfigurations() != null)
                     for (SeasonHSPConfiguration seasonconf : seasonEntity.getSeasonHspconfigurations()) {
                        if (seasonconf.getSeason() != null && seasonconf.getSeason().getSeasonId() == seasonEntity.getSeasonId()) {
                           startDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonStartDate());
                           endDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonEndDate());
                           seasonHspConfId = seasonconf.getSeasonHSPConfigurationId();
                        }
                     }
                  seasonBean.setStartDate(startDate);
                  seasonBean.setEndDate(endDate);
                  seasonBean.setSeasonHSPConfigurationId(seasonHspConfId);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, logger);
            }
            try {
               if (lookupDepartment.getDepartmentName().equals(CCIConstants.DEPT_WORK_PROGRAMS)) {
                  if (seasonEntity.getSeasonWpconfigurations() != null)
                     for (SeasonWPConfiguration seasonconf : seasonEntity.getSeasonWpconfigurations()) {
                        if (seasonconf.getSeason() != null && seasonconf.getSeason().getSeasonId() == seasonEntity.getSeasonId()) {
                           startDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonStartDate());
                           endDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonEndDate());
                           seasonHspConfId = seasonconf.getSeasonWPConfigurationId();
                        }
                     }
                  seasonBean.setStartDate(startDate);
                  seasonBean.setEndDate(endDate);
                  seasonBean.setSeasonHSPConfigurationId(seasonHspConfId);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, logger);
            }
            try {
               if (lookupDepartment.getDepartmentName().equals(CCIConstants.DEPT_GREEN_HEART_TRAVEL)) {
                  if (seasonEntity.getSeasonGhtconfigurations() != null)
                     for (SeasonGHTConfiguration seasonconf : seasonEntity.getSeasonGhtconfigurations()) {
                        if (seasonconf.getSeason() != null && seasonconf.getSeason().getSeasonId() == seasonEntity.getSeasonId()) {
                           startDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonStartDate());
                           endDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonEndDate());
                           seasonHspConfId = seasonconf.getSeasonGHTConfigurationId();
                        }
                     }
                  seasonBean.setStartDate(startDate);
                  seasonBean.setEndDate(endDate);
                  seasonBean.setSeasonHSPConfigurationId(seasonHspConfId);
               }
            } catch (Exception e) {
               ExceptionUtil.logException(e, logger);
            }
            try {
               if (lookupDepartment.getDepartmentPrograms() != null) {
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
            } catch (Exception e) {
               ExceptionUtil.logException(e, logger);
            }
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      try {
         List<SeasonDepartmentNote> seasonDepartmentNotesList = seasonDepartmentNotesRepository.findAllDepartmentNotesBySeasonIdDateDesc(seasonEntity.getSeasonId());
         if (seasonDepartmentNotesList != null && !seasonDepartmentNotesList.isEmpty()) {
            List<SeasonDepartmentNotes> list = new ArrayList<SeasonDepartmentNotes>();
            for (SeasonDepartmentNote note : seasonDepartmentNotesList) {
               SeasonDepartmentNotes seasonDepartmentNotes = new SeasonDepartmentNotes();
               seasonDepartmentNotes.setSeasonId(seasonEntity.getSeasonId());
               seasonDepartmentNotes.setActive(note.getActive() == CCIConstants.ACTIVE);
               seasonDepartmentNotes.setNoteValue(note.getDepartmentNote());
               seasonDepartmentNotes.setCreatedOn(DateUtils.getTimestamp(note.getCreatedOn()));
               Login login = loginRepository.findOne(1);// TODO find user from
                                                        // session
               if (login != null) {
                  seasonDepartmentNotes.setCreatedBy(login.getLoginName());
               }
               seasonDepartmentNotes.setSeasonDepartmentNotetId(note.getSeasonDepartmentNotesId());
               list.add(seasonDepartmentNotes);

            }
            seasonBean.getNotes().addAll(list);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      try {
         if (seasonEntity.getSeasonDepartmentDocuments() != null && !seasonEntity.getSeasonDepartmentDocuments().isEmpty()) {
            for (SeasonDepartmentDocument departmentDocument : seasonEntity.getSeasonDepartmentDocuments()) {
               try {
                  DocumentInformation dInfo = departmentDocument.getDocumentInformation();
                  if (dInfo != null) {
                     SeasonDocument document = new SeasonDocument();
                     if (dInfo.getDocumentTypeDocumentCategoryProcess() != null && dInfo.getDocumentTypeDocumentCategoryProcess().getDocumentType() != null
                           && dInfo.getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName() != null) {
                        document.setSeasonId(departmentDocument.getSeason().getSeasonId());
                        if (seasonEntity.getLookupDepartment() != null) {
                           document.setDepartmentId(seasonEntity.getLookupDepartment().getDepartmentId());
                        }
                        document.setDocType(departmentDocument.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
                        document.setDocUrl(departmentDocument.getDocumentInformation().getUrl());
                        document.setDocName(departmentDocument.getDocumentInformation().getDocumentName());
                        document.setFileName(departmentDocument.getDocumentInformation().getFileName());
                        document.setActive(departmentDocument.getActive() == CCIConstants.ACTIVE ? true : false);
                        document.setUploadDate(DateUtils.getMMddyyDate(departmentDocument.getDocumentInformation().getModifiedOn()));
                        Login login = loginRepository.findOne(1);// TODO
                                                                 // find
                                                                 // user
                                                                 // from
                                                                 // session
                        if (login != null) {
                           document.setUploadedBy(login.getLoginName());
                        }

                     }

                     seasonBean.getDocuments().add(document);
                  }
               } catch (Exception ex) {
                  ExceptionUtil.logException(ex, logger);
               }
            }
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   private com.ccighgo.service.transport.seasons.beans.season.ProgramOptions mapProgramOptionEntityToBean2(DepartmentProgramOption departmentProgramOption) {
      try {
         com.ccighgo.service.transport.seasons.beans.season.ProgramOptions programOptions = new com.ccighgo.service.transport.seasons.beans.season.ProgramOptions();
         programOptions.setProgramOptionsID(departmentProgramOption.getDepartmentProgramOptionId());
         programOptions.setProgramOptionsCode(departmentProgramOption.getProgramOptionCode() != null ? departmentProgramOption.getProgramOptionCode() : CCIConstants.EMPTY_DATA);
         programOptions.setProgramOptionsName(departmentProgramOption.getProgramOptionName() != null ? departmentProgramOption.getProgramOptionName() : CCIConstants.EMPTY_DATA);
         return programOptions;
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         return null;
      }
   }

   /**
    * @param seasonBean
    * @param seasonEntity
    */
   public void convertEntitySeasonToSeasonListObject(SeasonListObject seasonBean, Season seasonEntity) {
      try {
         seasonBean.setSeasonId(seasonEntity.getSeasonId());
         seasonBean.setSeasonName(seasonEntity.getSeasonName());
         if (seasonEntity.getSeasonStatus() != null) {
            seasonBean.setProgramStatusId(seasonEntity.getSeasonStatus() != null ? seasonEntity.getSeasonStatus().getSeasonStatusId() : CCIConstants.EMPTY_INTEGER_FIELD);
            seasonBean.setProgramStatusValue(seasonEntity.getSeasonStatus() != null ? seasonEntity.getSeasonStatus().getStatus() : CCIConstants.EMPTY_DATA);
         }
         seasonBean.setDepartment(getDepartmentBean(seasonEntity.getLookupDepartment()));
         String startDate = CCIConstants.EMPTY_DATA, endDate = CCIConstants.EMPTY_DATA;
         Integer seasonHspConfId = CCIConstants.EMPTY_INTEGER_FIELD;

         try {
            if (seasonEntity.getLookupDepartment() != null) {
               try {
                  if (seasonEntity.getLookupDepartment().getDepartmentName().equals(CCIConstants.DEPT_HIGH_SCHOOL_PROGRAMS)) {
                     try {
                        if (seasonEntity.getSeasonHspconfigurations() != null)
                           for (SeasonHSPConfiguration seasonconf : seasonEntity.getSeasonHspconfigurations()) {
                              if (seasonconf.getSeason() != null && seasonconf.getSeason().getSeasonId() == seasonEntity.getSeasonId()) {
                                 startDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonStartDate());
                                 endDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonEndDate());
                                 seasonHspConfId = seasonconf.getSeasonHSPConfigurationId();
                              }
                           }
                     } catch (Exception e) {
                        ExceptionUtil.logException(e, logger);
                     }
                     seasonBean.setStartDate(startDate);
                     seasonBean.setEndDate(endDate);
                     seasonBean.setSeasonHSPConfigurationId(seasonHspConfId);

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

               } catch (Exception e) {
                  ExceptionUtil.logException(e, logger);
               }
               try {
                  if (seasonEntity.getLookupDepartment().getDepartmentName().equals(CCIConstants.DEPT_WORK_PROGRAMS)) {
                     if (seasonEntity.getSeasonWpconfigurations() != null)
                        for (SeasonWPConfiguration seasonconf : seasonEntity.getSeasonWpconfigurations()) {
                           if (seasonconf.getSeason() != null && seasonconf.getSeason().getSeasonId() == seasonEntity.getSeasonId()) {
                              startDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonStartDate());
                              endDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonEndDate());
                              seasonHspConfId = seasonconf.getSeasonWPConfigurationId();
                           }
                        }
                     seasonBean.setStartDate(startDate);
                     seasonBean.setEndDate(endDate);
                     seasonBean.setSeasonHSPConfigurationId(seasonHspConfId);

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
               } catch (Exception e) {
                  ExceptionUtil.logException(e, logger);
               }
               try {
                  if (seasonEntity.getLookupDepartment().getDepartmentName().equals(CCIConstants.DEPT_GREEN_HEART_TRAVEL)) {
                     if (seasonEntity.getSeasonGhtconfigurations() != null)
                        for (SeasonGHTConfiguration seasonconf : seasonEntity.getSeasonGhtconfigurations()) {
                           if (seasonconf.getSeason() != null && seasonconf.getSeason().getSeasonId() == seasonEntity.getSeasonId()) {
                              startDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonStartDate());
                              endDate = DateUtils.getMMddYyyyString(seasonconf.getSeasonEndDate());
                              seasonHspConfId = seasonconf.getSeasonGHTConfigurationId();
                           }
                        }
                     seasonBean.setStartDate(startDate);
                     seasonBean.setEndDate(endDate);
                     seasonBean.setSeasonHSPConfigurationId(seasonHspConfId);
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
               } catch (Exception e) {
                  ExceptionUtil.logException(e, logger);
               }
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   /**
    * @param department
    * @return
    */
   private DepartmentObject getDepartmentBean(LookupDepartment department) {
      try {
         DepartmentObject departmentObject = null;
         if (department != null) {
            departmentObject = new DepartmentObject();
            departmentObject.setDepartmentId(department.getDepartmentId());
            departmentObject.setDepartmentCode(department.getAcronym() != null ? department.getAcronym() : CCIConstants.EMPTY_DATA);
            departmentObject.setDepartmentName(department.getDepartmentName() != null ? department.getAcronym() : CCIConstants.EMPTY_DATA);
         }
         return departmentObject;
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
         return null;
      }
   }

   /**
    * @param seasonBean
    * @param seasonEntity
    * @param update
    */
   public void convertSeasonBeanToSeasonEntity(SeasonBean seasonBean, Season seasonEntity, boolean update) {
      try {
         if (update) {
            ValidationUtils.validateRequired(seasonBean.getSeasonId() + "");
            seasonEntity.setSeasonId(seasonBean.getSeasonId());
            SeasonStatus seasonStatus = seasonStatusRepository.findOne(seasonBean.getSeasonStatusId());
            seasonEntity.setSeasonStatus(seasonStatus);
         } else {
            SeasonStatus seasonStatus = seasonStatusRepository.findOne(CCIConstants.DRAFT_STATUS_NO);
            seasonEntity.setSeasonStatus(seasonStatus);
         }
         ValidationUtils.validateRequired(seasonBean.getDepartmentId() + "");
         LookupDepartment department = departmentRepository.findOne(seasonBean.getDepartmentId());
         seasonEntity.setLookupDepartment(department);

         ValidationUtils.validateRequired(seasonBean.getSeasonName());
         seasonEntity.setSeasonName(seasonBean.getSeasonName());
         seasonEntity.setClonedSeasonName(seasonBean.getCloneSeasonName());

         seasonEntity.setCreatedBy(seasonBean.getLoginId());
         seasonEntity.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonEntity.setModifiedBy(seasonBean.getLoginId());
         seasonEntity.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonEntity.setSeasonFullName(seasonBean.getSeasonName());
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   public void createSeasonConfiguration(SeasonBean seasonBean, Season seasonEntity) {
      try {
         LookupDepartment department = seasonEntity.getLookupDepartment();
         if (department.getDepartmentName().equals(CCIConstants.DEPT_HIGH_SCHOOL_PROGRAMS)) {
            SeasonHSPConfiguration seasonHSPConfiguration = new SeasonHSPConfiguration();
            seasonHSPConfiguration.setSeason(seasonEntity);
            seasonHSPConfiguration.setSeasonEndDate(DateUtils.getDateFromString(seasonBean.getEndDate()));
            seasonHSPConfiguration.setSeasonStartDate(DateUtils.getDateFromString(seasonBean.getStartDate()));
            seasonHSPConfiguration.setCreatedBy(seasonBean.getLoginId());
            seasonHSPConfiguration.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonHSPConfiguration.setModifiedBy(seasonBean.getLoginId());
            seasonHSPConfiguration.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonHSPConfigurationRepsitory.saveAndFlush(seasonHSPConfiguration);
         } else if (department.getDepartmentName().equals(CCIConstants.DEPT_WORK_PROGRAMS)) {
            SeasonWPConfiguration seasonWPConfiguration = new SeasonWPConfiguration();
            seasonWPConfiguration.setSeason(seasonEntity);
            seasonWPConfiguration.setSeasonEndDate(DateUtils.getDateFromString(seasonBean.getEndDate()));
            seasonWPConfiguration.setSeasonStartDate(DateUtils.getDateFromString(seasonBean.getStartDate()));
            seasonWPConfiguration.setCreatedBy(seasonBean.getLoginId());
            seasonWPConfiguration.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonWPConfiguration.setModifiedBy(seasonBean.getLoginId());
            seasonWPConfiguration.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonWPConfigurationRepository.saveAndFlush(seasonWPConfiguration);
         } else if (department.getDepartmentName().equals(CCIConstants.DEPT_GREEN_HEART_TRAVEL)) {
            SeasonGHTConfiguration seasonGHTConfiguration = new SeasonGHTConfiguration();
            seasonGHTConfiguration.setSeason(seasonEntity);
            seasonGHTConfiguration.setSeasonEndDate(DateUtils.getDateFromString(seasonBean.getEndDate()));
            seasonGHTConfiguration.setSeasonStartDate(DateUtils.getDateFromString(seasonBean.getStartDate()));
            seasonGHTConfiguration.setCreatedBy(seasonBean.getLoginId());
            seasonGHTConfiguration.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonGHTConfiguration.setModifiedBy(seasonBean.getLoginId());
            seasonGHTConfiguration.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonGHTConfigurationRepository.saveAndFlush(seasonGHTConfiguration);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   public void updateSeasonConfiguration(SeasonBean seasonBean, Season seasonEntity) {
      try {
         LookupDepartment department = seasonEntity.getLookupDepartment();
         if (department.getDepartmentName().equals(CCIConstants.DEPT_HIGH_SCHOOL_PROGRAMS)) {
            SeasonHSPConfiguration seasonHSPConfiguration = seasonHSPConfigurationRepsitory.getSeasonHSPConfigurationBySeasonId(seasonEntity.getSeasonId());
            if (seasonHSPConfiguration == null)
               return;
            seasonHSPConfiguration.setSeason(seasonEntity);
            seasonHSPConfiguration.setSeasonEndDate(DateUtils.getDateFromString(seasonBean.getEndDate()));
            seasonHSPConfiguration.setSeasonStartDate(DateUtils.getDateFromString(seasonBean.getStartDate()));
            seasonHSPConfiguration.setSeasonHSPConfigurationId(seasonBean.getSeasonHSPConfigurationId());
            seasonHSPConfigurationRepsitory.saveAndFlush(seasonHSPConfiguration);
         } else if (department.getDepartmentName().equals(CCIConstants.DEPT_WORK_PROGRAMS)) {
            SeasonWPConfiguration seasonWPConfiguration = seasonWPConfigurationRepository.getSeasonWPConfigurationBySeasonId(seasonEntity.getSeasonId());
            if (seasonWPConfiguration == null)
               return;
            seasonWPConfiguration.setSeason(seasonEntity);
            seasonWPConfiguration.setSeasonEndDate(DateUtils.getDateFromString(seasonBean.getEndDate()));
            seasonWPConfiguration.setSeasonStartDate(DateUtils.getDateFromString(seasonBean.getStartDate()));
            seasonWPConfigurationRepository.saveAndFlush(seasonWPConfiguration);

         } else if (department.getDepartmentName().equals(CCIConstants.DEPT_GREEN_HEART_TRAVEL)) {
            SeasonGHTConfiguration seasonGHTConfiguration = seasonGHTConfigurationRepository.getSeasonGHTConfigurationBySeasonId(seasonEntity.getSeasonId());
            if (seasonGHTConfiguration == null)
               return;
            seasonGHTConfiguration.setSeason(seasonEntity);
            seasonGHTConfiguration.setSeasonEndDate(DateUtils.getDateFromString(seasonBean.getEndDate()));
            seasonGHTConfiguration.setSeasonStartDate(DateUtils.getDateFromString(seasonBean.getStartDate()));
            seasonGHTConfigurationRepository.saveAndFlush(seasonGHTConfiguration);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   public HSPF1BasicDetails getHSPF1NameAndStatus(SeasonF1Detail allF1Details) {
      HSPF1BasicDetails hspf1BasicDetails = null;
      try {
         if (allF1Details != null) {
            hspf1BasicDetails = new HSPF1BasicDetails();
            hspf1BasicDetails.setSeasonId(allF1Details.getSeason().getSeasonId());
            hspf1BasicDetails.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
            hspf1BasicDetails.setProgramName(allF1Details.getProgramName());
            if (allF1Details.getSeasonStatus() != null)
               hspf1BasicDetails.setProgramStatusId(allF1Details.getSeasonStatus().getSeasonStatusId());
            hspf1BasicDetails.setProgramStatusValue(allF1Details.getSeasonStatus().getStatus());
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return hspf1BasicDetails;
   }

   public HSPF1Accounting getHSPF1Accounting(SeasonF1Detail allF1Details) {
      HSPF1Accounting hspf1Accounting = null;
      try {
         if (allF1Details != null) {
            hspf1Accounting = new HSPF1Accounting();
            hspf1Accounting.setSeasonId(allF1Details.getSeason().getSeasonId());
            hspf1Accounting.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
            hspf1Accounting.setGreenHeartMargin(allF1Details.getGreenHeartMargin());
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return hspf1Accounting;
   }

   public HSPF1JanuaryStart2NdSemesterDetails getHSPF1JanuaryStart2NdSemesterDetails(SeasonF1Detail allF1Details) {
      HSPF1JanuaryStart2NdSemesterDetails hspf1JanuaryStart2NdSemesterDetails = null;
      try {
         if (allF1Details != null) {
            hspf1JanuaryStart2NdSemesterDetails = new HSPF1JanuaryStart2NdSemesterDetails();
            hspf1JanuaryStart2NdSemesterDetails.setSeasonId(allF1Details.getSeason().getSeasonId());
            hspf1JanuaryStart2NdSemesterDetails.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
            if (allF1Details.getActiveFullYearJanProgram() != null)
               hspf1JanuaryStart2NdSemesterDetails.setActivateFullYearProgram(allF1Details.getActiveFullYearJanProgram() != CCIConstants.INACTIVE);
            hspf1JanuaryStart2NdSemesterDetails.setApplicationDeadlineDate(DateUtils.getMMddYyyyString(allF1Details.getSecondSemAppDeadlineDate()));
            hspf1JanuaryStart2NdSemesterDetails.setStartDate(DateUtils.getMMddYyyyString(allF1Details.getSecondSemStartDate()));
            hspf1JanuaryStart2NdSemesterDetails.setEndDate(DateUtils.getMMddYyyyString(allF1Details.getSecondSemEndDate()));
            hspf1JanuaryStart2NdSemesterDetails.setShow2NdSemestertoNewHF(allF1Details.getShowSecSemToNewHF() != 0);
            hspf1JanuaryStart2NdSemesterDetails.setEarliestBirthDate(DateUtils.getMMddYyyyString(allF1Details.getSecondSemEarliestBirthDate()));
            hspf1JanuaryStart2NdSemesterDetails.setLatestBirthDate(DateUtils.getMMddYyyyString(allF1Details.getSecondSemLatestBirthDate()));
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return hspf1JanuaryStart2NdSemesterDetails;
   }

   public HSPF1AugustStartFullYearDetails getHSPF1AugustStartFullYearDetails(SeasonF1Detail allF1Details) {
      HSPF1AugustStartFullYearDetails hspAugustStartFullYearDetails = null;
      try {
         if (allF1Details != null) {
            hspAugustStartFullYearDetails = new HSPF1AugustStartFullYearDetails();
            hspAugustStartFullYearDetails.setSeasonId(allF1Details.getSeason().getSeasonId());
            hspAugustStartFullYearDetails.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
            hspAugustStartFullYearDetails.setApplicationDeadlineDate(DateUtils.getMMddYyyyString(allF1Details.getAugFullYearAppDeadlineDate()));
            hspAugustStartFullYearDetails.setEndDate(DateUtils.getMMddYyyyString(allF1Details.getAugFullYearEndDate()));
            hspAugustStartFullYearDetails.setShowFullYearToNewHF(allF1Details.getShowAugFullYearToNewHF() != 0);
            hspAugustStartFullYearDetails.setStartDate(DateUtils.getMMddYyyyString(allF1Details.getAugFullYearStartDate()));
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return hspAugustStartFullYearDetails;
   }

   public HSPF1AugustStart1StSemesterDetails getHSPF1AugustStart1StSemesterDetails(SeasonF1Detail allF1Details) {
      HSPF1AugustStart1StSemesterDetails hspf1AugustStart1StSemesterDetails = null;
      try {
         if (allF1Details != null) {
            hspf1AugustStart1StSemesterDetails = new HSPF1AugustStart1StSemesterDetails();
            hspf1AugustStart1StSemesterDetails.setSeasonId(allF1Details.getSeason().getSeasonId());
            hspf1AugustStart1StSemesterDetails.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
            hspf1AugustStart1StSemesterDetails.setApplicationDeadlineDate(DateUtils.getMMddYyyyString(allF1Details.getFirstSemAppDeadlineDate()));
            hspf1AugustStart1StSemesterDetails.setEarliestBirthDate(DateUtils.getMMddYyyyString(allF1Details.getFirstSemEarliestBirthDate()));
            hspf1AugustStart1StSemesterDetails.setLatestBirthDate(DateUtils.getMMddYyyyString(allF1Details.getFirstSemLatestBirthDate()));
            hspf1AugustStart1StSemesterDetails.setStartDate(DateUtils.getMMddYyyyString(allF1Details.getFirstSemStartDate()));
            hspf1AugustStart1StSemesterDetails.setEndDate(DateUtils.getMMddYyyyString(allF1Details.getFirstSemEndDate()));
            hspf1AugustStart1StSemesterDetails.setShow1StSemesterToNewHF(allF1Details.getShowFirstSemToNewHF() == 1 ? true : false);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return hspf1AugustStart1StSemesterDetails;
   }

   public HSPF1FieldSettings getHSPF1FieldSettings(SeasonF1Detail allF1Details) {
      HSPF1FieldSettings hspf1FieldSettings = null;
      try {
         if (allF1Details != null) {
            hspf1FieldSettings = new HSPF1FieldSettings();
            hspf1FieldSettings.setSeasonId(allF1Details.getSeason().getSeasonId());
            hspf1FieldSettings.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
            if (allF1Details.getHfInquiryDate() != null)
               hspf1FieldSettings.setAddOrStartHFInquiriesDate(DateUtils.getMMddYyyyString(allF1Details.getHfInquiryDate()));
            hspf1FieldSettings.setAllowFSToStartRenewalProcess(allF1Details.getAllowFieldStaffToStartRenewalProcess() != 0);
            // hspf1FieldSettings.setDefaultLcPaymentSchedule(allF1Details.getLcPaymentScheduleId());
            // hspf1FieldSettings.setFsAgreement(allF1Details.getFsAgreementId());
            hspf1FieldSettings.setHfReferencesNo(allF1Details.getHfReferences());
            hspf1FieldSettings.setShowSeasonProgramToCurrentHF(allF1Details.getShowSeasonToCurrentHF() != 0);
            hspf1FieldSettings.setShowSpecialRequestStudentToRD(allF1Details.getShowSpecialRequestStudent() != 0);
            hspf1FieldSettings.setShowWelcomeFamilyModle(allF1Details.getShowWelcomeFamily() != 0);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return hspf1FieldSettings;
   }

   public HSPF1ProgramAllocations getHSPF1ProgramAllocations(SeasonF1Detail allF1Details) {
      HSPF1ProgramAllocations hspf1ProgramAllocations = null;
      try {
         hspf1ProgramAllocations = new HSPF1ProgramAllocations();
         hspf1ProgramAllocations.setSeasonId(allF1Details.getSeason().getSeasonId());
         hspf1ProgramAllocations.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return hspf1ProgramAllocations;
   }

   public HSPF1JanuaryStartFullYearDetail getHSPF1JanuaryStartFullYearDetails(SeasonF1Detail allF1Details) {
      HSPF1JanuaryStartFullYearDetail hspf1JanuaryStartFullYearDetail = null;
      try {
         if (allF1Details != null) {
            hspf1JanuaryStartFullYearDetail = new HSPF1JanuaryStartFullYearDetail();
            hspf1JanuaryStartFullYearDetail.setSeasonId(allF1Details.getSeason().getSeasonId());
            hspf1JanuaryStartFullYearDetail.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
            hspf1JanuaryStartFullYearDetail.setApplicationDeadlineDate(DateUtils.getMMddYyyyString(allF1Details.getJanFullYearAppDeadlineDate()));
            hspf1JanuaryStartFullYearDetail.setEndDate(DateUtils.getMMddYyyyString(allF1Details.getJanFullYearEndDate()));
            hspf1JanuaryStartFullYearDetail.setStartDate(DateUtils.getMMddYyyyString(allF1Details.getJanFullYearStartDate()));
            hspf1JanuaryStartFullYearDetail.setShowFullYearToHF(allF1Details.getShowJanFullYearToNewHF() != 0);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return hspf1JanuaryStartFullYearDetail;
   }

   public SeasonHSPF1Details updateF1Details(SeasonF1Detail allF1Details, SeasonHSPF1Details seasonHSPF1Details) {
      try {
         SeasonStatus seasonStatus = seasonStatusRepository.findOne(seasonHSPF1Details.getDetails().getProgramStatusId());
         if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
            Season season = allF1Details.getSeason();
            allF1Details.getSeason().setSeasonStatus(seasonStatus);
            seasonRepository.saveAndFlush(season);
         }
         allF1Details.setSeasonStatus(seasonStatusRepository.findOne(seasonHSPF1Details.getDetails().getProgramStatusId()));
         allF1Details.setProgramName(seasonHSPF1Details.getDetails().getProgramName());
         if (seasonHSPF1Details.getAccounting() != null)
            allF1Details.setGreenHeartMargin(seasonHSPF1Details.getAccounting().getGreenHeartMargin());
         try {
            if (seasonHSPF1Details.getJanuaryStart2NdSemesterDetails() != null) {
               allF1Details
                     .setActiveFullYearJanProgram(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().isActivateFullYearProgram() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               allF1Details.setShowSecSemToNewHF(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().isShow2NdSemestertoNewHF() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               allF1Details.setSecondSemAppDeadlineDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getApplicationDeadlineDate()));
               allF1Details.setSecondSemEarliestBirthDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getEarliestBirthDate()));
               allF1Details.setSecondSemEndDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getEndDate()));
               allF1Details.setSecondSemLatestBirthDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getLatestBirthDate()));
               allF1Details.setSecondSemStartDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStart2NdSemesterDetails().getStartDate()));
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            if (seasonHSPF1Details.getJanuaryStartFullYearDetail() != null) {
               allF1Details.setJanFullYearAppDeadlineDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStartFullYearDetail().getApplicationDeadlineDate()));
               allF1Details.setJanFullYearEndDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStartFullYearDetail().getEndDate()));
               allF1Details.setJanFullYearStartDate(DateUtils.getDateFromString(seasonHSPF1Details.getJanuaryStartFullYearDetail().getStartDate()));
               allF1Details.setShowJanFullYearToNewHF(seasonHSPF1Details.getJanuaryStartFullYearDetail().isShowFullYearToHF() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            if (seasonHSPF1Details.getAugustStart1StSemesterDetails() != null) {
               allF1Details.setFirstSemAppDeadlineDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getApplicationDeadlineDate()));
               allF1Details.setFirstSemEarliestBirthDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getEarliestBirthDate()));
               allF1Details.setFirstSemLatestBirthDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getLatestBirthDate()));
               allF1Details.setFirstSemStartDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getStartDate()));
               allF1Details.setFirstSemEndDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStart1StSemesterDetails().getEndDate()));
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            if (seasonHSPF1Details.getAugustStartFullYearDetails() != null) {
               allF1Details.setAugFullYearAppDeadlineDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStartFullYearDetails().getApplicationDeadlineDate()));
               allF1Details.setAugFullYearEndDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStartFullYearDetails().getEndDate()));
               allF1Details.setAugFullYearStartDate(DateUtils.getDateFromString(seasonHSPF1Details.getAugustStartFullYearDetails().getStartDate()));
               allF1Details.setShowAugFullYearToNewHF(seasonHSPF1Details.getAugustStartFullYearDetails().isShowFullYearToNewHF() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            if (seasonHSPF1Details.getFieldSettings() != null) {
               allF1Details.setHfInquiryDate(DateUtils.getDateFromString(seasonHSPF1Details.getFieldSettings().getAddOrStartHFInquiriesDate()));
               allF1Details
                     .setAllowFieldStaffToStartRenewalProcess(seasonHSPF1Details.getFieldSettings().isAllowFSToStartRenewalProcess() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               // allF1Details.setLcPaymentScheduleId(seasonHSPF1Details.getFieldSettings().getDefaultLcPaymentSchedule());
               // allF1Details.setFsAgreementId(seasonHSPF1Details.getFieldSettings().getFsAgreement());
               allF1Details.setHfReferences(seasonHSPF1Details.getFieldSettings().getHfReferencesNo());
               allF1Details.setShowSeasonToCurrentHF(seasonHSPF1Details.getFieldSettings().isShowSeasonProgramToCurrentHF() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               allF1Details.setShowSpecialRequestStudent(seasonHSPF1Details.getFieldSettings().isShowSpecialRequestStudentToRD() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
               allF1Details.setShowWelcomeFamily(seasonHSPF1Details.getFieldSettings().isShowWelcomeFamilyModle() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            }
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            if (seasonHSPF1Details.getNotes() != null)
               updateHSPF1Notes(allF1Details, seasonHSPF1Details);
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            if (seasonHSPF1Details.getDocuments() != null)
               updateHSPF1Documents(seasonHSPF1Details, allF1Details.getSeason());
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         try {
            if (seasonHSPF1Details.getProgramAllocations() != null)
               seasonServiceInterface.updateF1ProgramAllocation(seasonHSPF1Details.getProgramAllocations());
         } catch (Exception e) {
            ExceptionUtil.logException(e, logger);
         }
         seasonF1DetailsRepository.saveAndFlush(allF1Details);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return seasonHSPF1Details;
   }

   private void updateHSPF1Documents(SeasonHSPF1Details seasonHSPF1Details, Season season) {
      try {
         List<SeasonProgramDocument> seasonProgramDocuments = seasonProgramDocumentRepository.findAllProgramDocumentsBySeasonIdAndDepartmentProgramId(season.getSeasonId(),
               CCIConstants.HSP_F1_ID);
         seasonProgramDocumentRepository.delete(seasonProgramDocuments);
         List<SeasonProgramDocument> newDocList = new ArrayList<SeasonProgramDocument>();
         for (HSPF1SeasonHspF1Documents f1Documents : seasonHSPF1Details.getDocuments()) {
            try {
               SeasonProgramDocument sprgDoc = new SeasonProgramDocument();
               DocumentInformation documentInformation = new DocumentInformation();
               documentInformation.setFileName(f1Documents.getFileName());
               documentInformation.setDocumentName(f1Documents.getDocName());
               documentInformation.setUrl(f1Documents.getDocUrl());
               documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(f1Documents.getDocType()));
               documentInformation.setCreatedBy(seasonHSPF1Details.getLoginId());
               documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               documentInformation.setModifiedBy(seasonHSPF1Details.getLoginId());
               documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               documentInformation = documentInformationRepository.saveAndFlush(documentInformation);
               sprgDoc.setActive(CCIConstants.ACTIVE);
               sprgDoc.setSeason(season);
               sprgDoc.setDepartmentProgram(departmentProgramRepository.findOne(CCIConstants.HSP_F1_ID));
               sprgDoc.setDocumentInformation(documentInformation);
               sprgDoc.setCreatedBy(seasonHSPF1Details.getLoginId());
               sprgDoc.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               sprgDoc.setModifiedBy(seasonHSPF1Details.getLoginId());
               sprgDoc.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               newDocList.add(sprgDoc);
            } catch (Exception ex) {
               ExceptionUtil.logException(ex, logger);
            }
         }
         seasonProgramDocumentRepository.save(newDocList);
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   public HSPF1BasicDetails updateHSPF1NameAndStatus(SeasonF1Detail currentF1Detail, HSPF1BasicDetails hspf1BasicDetails) {
      try {
         currentF1Detail.setSeasonStatus(seasonStatusRepository.findOne(hspf1BasicDetails.getProgramStatusId()));
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
         allF1Details.setActiveFullYearJanProgram(hspf1JanuaryStart2NdSemesterDetails.isActivateFullYearProgram() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         allF1Details.setShowSecSemToNewHF(hspf1JanuaryStart2NdSemesterDetails.isShow2NdSemestertoNewHF() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
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
         allF1Details.setShowJanFullYearToNewHF(hspf1JanuaryStartFullYearDetail.isShowFullYearToHF() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         seasonF1DetailsRepository.saveAndFlush(allF1Details);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return hspf1JanuaryStartFullYearDetail;
   }

   public HSPF1AugustStart1StSemesterDetails updateF1AugStart1StSemesterDetails(SeasonF1Detail allF1Details,
         HSPF1AugustStart1StSemesterDetails hspf1AugustStart1StSemesterDetails) {
      try {
         allF1Details.setFirstSemAppDeadlineDate(DateUtils.getDateFromString(hspf1AugustStart1StSemesterDetails.getApplicationDeadlineDate()));
         allF1Details.setFirstSemEarliestBirthDate(DateUtils.getDateFromString(hspf1AugustStart1StSemesterDetails.getEarliestBirthDate()));
         allF1Details.setFirstSemLatestBirthDate(DateUtils.getDateFromString(hspf1AugustStart1StSemesterDetails.getLatestBirthDate()));
         allF1Details.setFirstSemStartDate(DateUtils.getDateFromString(hspf1AugustStart1StSemesterDetails.getStartDate()));
         allF1Details.setFirstSemEndDate(DateUtils.getDateFromString(hspf1AugustStart1StSemesterDetails.getEndDate()));
         allF1Details.setShowFirstSemToNewHF(hspf1AugustStart1StSemesterDetails.isShow1StSemesterToNewHF() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
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
         allF1Details.setShowAugFullYearToNewHF(hspf1AugustStartFullYearDetails.isShowFullYearToNewHF() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);

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
         allF1Details.setAllowFieldStaffToStartRenewalProcess(hspf1FieldSettings.isAllowFSToStartRenewalProcess() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         // allF1Details.setLcPaymentScheduleId(hspf1FieldSettings.getDefaultLcPaymentSchedule());
         // allF1Details.setFsAgreementId(hspf1FieldSettings.getFsAgreement());
         allF1Details.setHfReferences(hspf1FieldSettings.getHfReferencesNo());
         allF1Details.setShowSeasonToCurrentHF(hspf1FieldSettings.isShowSeasonProgramToCurrentHF() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         allF1Details.setShowSpecialRequestStudent(hspf1FieldSettings.isShowSpecialRequestStudentToRD() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         allF1Details.setShowWelcomeFamily(hspf1FieldSettings.isShowWelcomeFamilyModle() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
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
      try {
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
            createHSPRegionManagement(seasonEntity.getSeasonId());
            createHSPF1Season(seasonEntity, seasonBean);
            createHSPJ1HSSeasonProgram(seasonBean, seasonEntity);
            createHSPIHPSeasonProgram(seasonBean, seasonEntity);
         } else if (departmentName.equals(CCIConstants.DEPT_SYSTEM)) {
         } else if (departmentName.equals(CCIConstants.DEPT_WORK_PROGRAMS)) {
            createWPSummerSeasonProgram(seasonBean, seasonEntity);
            createWPWinterSeasonProgram(seasonBean, seasonEntity);
            createWPSpringSeasonProgram(seasonBean, seasonEntity);
            createWPCapSeasonProgram(seasonBean, seasonEntity);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   private void createHSPRegionManagement(Integer newSeasonId) {
      if (newSeasonId == 0 || newSeasonId < 0) {
         throw new CcighgoException(messageUtil.getMessage(HostFamilyMessageConstants.INVALID_HF_GOID));
      }
      try {
         logger.info("Calling the procedure to roll over the Region and Field Staff data");
         Query seasonRegFsRollOver = entityManager.createNativeQuery(SP_REGION_FS_ROLLOVER);
         seasonRegFsRollOver.setParameter(1, newSeasonId);
         seasonRegFsRollOver.executeUpdate();
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   private void createWPCapSeasonProgram(SeasonBean seasonBean, Season seasonEntity) {
      try {
         if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
            SeasonCAPDetail seasonCapDetail = new SeasonCAPDetail();
            seasonCapDetail.setSeason(seasonEntity);
            seasonCapDetail.setProgramName(seasonBean.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.WP_WT_CAP);
            seasonCapDetail.setSeasonStatus(seasonEntity.getSeasonStatus());
            seasonCapDetail.setCreatedBy(seasonBean.getLoginId());
            seasonCapDetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonCapDetail.setModifiedBy(seasonBean.getLoginId());
            seasonCapDetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonCAPDetailsRepository.saveAndFlush(seasonCapDetail);
            createWPCapProgramAllocation(seasonEntity, seasonBean.getLoginId());
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   private void createWPSpringSeasonProgram(SeasonBean seasonBean, Season seasonEntity) {
      try {
         if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
            SeasonWnTSpringDetail seasonWnTSpringDetail = new SeasonWnTSpringDetail();
            seasonWnTSpringDetail.setSeason(seasonEntity);
            seasonWnTSpringDetail.setProgramName(seasonBean.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.WP_WT_SPRING);
            seasonWnTSpringDetail.setSeasonStatus(seasonEntity.getSeasonStatus());
            seasonWnTSpringDetail.setIsJobBoardOpen(CCIConstants.INACTIVE);
            seasonWnTSpringDetail.setMaxPendingJobApps(0);
            seasonWnTSpringDetail.setCreatedBy(seasonBean.getLoginId());
            seasonWnTSpringDetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonWnTSpringDetail.setModifiedBy(seasonBean.getLoginId());
            seasonWnTSpringDetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonWTSpringRepository.saveAndFlush(seasonWnTSpringDetail);
            seasonServiceInterface.createWPSpringProgramAllocation(seasonEntity, seasonBean.getLoginId());
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   private void createWPWinterSeasonProgram(SeasonBean seasonBean, Season seasonEntity) {
      try {
         if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
            SeasonWnTWinterDetail seasonWnTWinterDetail = new SeasonWnTWinterDetail();
            seasonWnTWinterDetail.setSeason(seasonEntity);
            seasonWnTWinterDetail.setProgramName(seasonBean.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.WP_WT_WINTER);
            seasonWnTWinterDetail.setSeasonStatus(seasonEntity.getSeasonStatus());
            seasonWnTWinterDetail.setIsJobBoardOpen(CCIConstants.INACTIVE);
            seasonWnTWinterDetail.setMaxPendingJobApps(0);
            seasonWnTWinterDetail.setCreatedBy(seasonBean.getLoginId());
            seasonWnTWinterDetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonWnTWinterDetail.setModifiedBy(seasonBean.getLoginId());
            seasonWnTWinterDetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonWTWinterRepository.saveAndFlush(seasonWnTWinterDetail);
            createWPWinterProgramAllocation(seasonEntity, seasonBean.getLoginId());
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   private void createWPSummerSeasonProgram(SeasonBean seasonBean, Season seasonEntity) {
      try {
         if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
            SeasonWnTSummerDetail seasonsummDetail = new SeasonWnTSummerDetail();
            seasonsummDetail.setSeason(seasonEntity);
            seasonsummDetail.setProgramName(seasonBean.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.WP_WT_SUMMER);
            seasonsummDetail.setIsJobBoardOpen(CCIConstants.INACTIVE);
            seasonsummDetail.setSeasonStatus(seasonEntity.getSeasonStatus());
            seasonsummDetail.setMaxPendingJobApps(0);
            seasonsummDetail.setCreatedBy(seasonBean.getLoginId());
            seasonsummDetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonsummDetail.setModifiedBy(seasonBean.getLoginId());
            seasonsummDetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonWTSummerRepository.saveAndFlush(seasonsummDetail);
            createWPSummerProgramAllocation(seasonEntity, seasonBean.getLoginId());
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   private void createWPSummerProgramAllocation(Season season, int loginId) {
      try {
         List<SeasonWPAllocation> seasonWpAllocations = new ArrayList<SeasonWPAllocation>();
         SeasonWPAllocation jobFairWinter = new SeasonWPAllocation();
         DepartmentProgramOption departmentProgramOption_JobFair = departmentProgramOptionRepository.findOne(CCIConstants.JOB_FAIR_SUMMER_ID);
         jobFairWinter.setDepartmentProgramOption(departmentProgramOption_JobFair);
         jobFairWinter.setMaxPax(0);
         jobFairWinter.setSeason(season);
         jobFairWinter.setCreatedBy(loginId);
         jobFairWinter.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         jobFairWinter.setModifiedBy(loginId);
         jobFairWinter.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWpAllocations.add(jobFairWinter);

         SeasonWPAllocation selfPlacedWinter = new SeasonWPAllocation();
         DepartmentProgramOption departmentProgramOption_SelfPlaceSpring = departmentProgramOptionRepository.findOne(CCIConstants.SELF_PLACED_SUMMER_ID);
         selfPlacedWinter.setDepartmentProgramOption(departmentProgramOption_SelfPlaceSpring);
         selfPlacedWinter.setMaxPax(0);
         selfPlacedWinter.setSeason(season);
         selfPlacedWinter.setCreatedBy(loginId);
         selfPlacedWinter.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         selfPlacedWinter.setModifiedBy(loginId);
         selfPlacedWinter.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWpAllocations.add(selfPlacedWinter);

         SeasonWPAllocation directPlacementWinter = new SeasonWPAllocation();
         DepartmentProgramOption departmentProgramOption_DirectPlacementWinter = departmentProgramOptionRepository.findOne(CCIConstants.DIRECT_PLACEMENT_SUMMER_ID);
         directPlacementWinter.setDepartmentProgramOption(departmentProgramOption_DirectPlacementWinter);
         directPlacementWinter.setMaxPax(0);
         directPlacementWinter.setSeason(season);
         directPlacementWinter.setCreatedBy(loginId);
         directPlacementWinter.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         directPlacementWinter.setModifiedBy(loginId);
         directPlacementWinter.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWpAllocations.add(directPlacementWinter);

         seasonWPAllocationRepository.save(seasonWpAllocations);
         seasonWPAllocationRepository.flush();

      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   private void createGHTTeachAbroad(Season seasonEntity, SeasonBean seasonBean) {
      try {
         if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
            SeasonTADetail seasonTADetail = new SeasonTADetail();
            seasonTADetail.setSeason(seasonEntity);
            seasonTADetail.setProgramName(seasonBean.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.GHT_TEACH_ABRD);
            seasonTADetail.setSeasonStatus(seasonEntity.getSeasonStatus());
            seasonTADetail.setCreatedBy(seasonBean.getLoginId());
            seasonTADetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonTADetail.setModifiedBy(seasonBean.getLoginId());
            seasonTADetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonTADetailsRepository.saveAndFlush(seasonTADetail);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   private void createGHTLanguageSchool(Season seasonEntity, SeasonBean seasonBean) {
      try {
         if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
            SeasonLSDetail seasonLSDetail = new SeasonLSDetail();
            seasonLSDetail.setSeason(seasonEntity);
            seasonLSDetail.setProgramName(seasonBean.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.GHT_LANG_SCL);
            seasonLSDetail.setSeasonStatus(seasonEntity.getSeasonStatus());
            seasonLSDetail.setCreatedBy(seasonBean.getLoginId());
            seasonLSDetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonLSDetail.setModifiedBy(seasonBean.getLoginId());
            seasonLSDetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonLSDetailsRepository.saveAndFlush(seasonLSDetail);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   private void createGHTHSAbroad(Season seasonEntity, SeasonBean seasonBean) {
      try {
         if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
            SeasonHSADetail seasonHSADetail = new SeasonHSADetail();
            seasonHSADetail.setSeason(seasonEntity);
            seasonHSADetail.setProgramName(seasonBean.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.GHT_HS_ABRD);
            seasonHSADetail.setSeasonStatus(seasonEntity.getSeasonStatus());
            seasonHSADetail.setCreatedBy(seasonBean.getLoginId());
            seasonHSADetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonHSADetail.setModifiedBy(seasonBean.getLoginId());
            seasonHSADetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonHSADetailsRepository.saveAndFlush(seasonHSADetail);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   private void createGHTWorkAbroad(Season seasonEntity, SeasonBean seasonBean) {
      try {
         if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
            SeasonWADetail seasonWADetail = new SeasonWADetail();
            seasonWADetail.setSeason(seasonEntity);
            seasonWADetail.setProgramName(seasonBean.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.GHT_WRK_ABRD);
            seasonWADetail.setSeasonStatus(seasonEntity.getSeasonStatus());
            seasonWADetail.setCreatedBy(seasonBean.getLoginId());
            seasonWADetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonWADetail.setModifiedBy(seasonBean.getLoginId());
            seasonWADetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonWADetailsRepository.saveAndFlush(seasonWADetail);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   private void createGHTVolunteerAbroad(Season seasonEntity, SeasonBean seasonBean) {
      try {
         if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
            SeasonVADetail seasonVADetail = new SeasonVADetail();
            seasonVADetail.setSeason(seasonEntity);
            seasonVADetail.setProgramName(seasonBean.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.GHT_VOL_ABRD);
            seasonVADetail.setSeasonStatus(seasonEntity.getSeasonStatus());
            seasonVADetail.setCreatedBy(seasonBean.getLoginId());
            seasonVADetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonVADetail.setModifiedBy(seasonBean.getLoginId());
            seasonVADetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonVADetailsRepository.saveAndFlush(seasonVADetail);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   private void createHSPF1Season(Season seasonEntity, SeasonBean seasonBean) {
      try {
         if (seasonEntity.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
            SeasonF1Detail seasonF1Detail = new SeasonF1Detail();
            seasonF1Detail.setSeason(seasonEntity);
            seasonF1Detail.setProgramName(seasonBean.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.HSP_F1);
            seasonF1Detail.setSeasonStatus(seasonEntity.getSeasonStatus());
            seasonF1Detail.setShowAugFullYearToNewHF(CCIConstants.INACTIVE);
            seasonF1Detail.setShowFirstSemToNewHF(CCIConstants.INACTIVE);
            seasonF1Detail.setShowJanFullYearToNewHF(CCIConstants.INACTIVE);
            seasonF1Detail.setShowSeasonToCurrentHF(CCIConstants.INACTIVE);
            seasonF1Detail.setShowSecSemToNewHF(CCIConstants.INACTIVE);
            seasonF1Detail.setShowSpecialRequestStudent(CCIConstants.INACTIVE);
            seasonF1Detail.setShowWelcomeFamily(CCIConstants.INACTIVE);
            seasonF1Detail.setActiveFullYearJanProgram(CCIConstants.INACTIVE);
            seasonF1Detail.setAllowFieldStaffToStartRenewalProcess(CCIConstants.INACTIVE);
            seasonF1Detail.setCreatedBy(seasonBean.getLoginId());
            seasonF1Detail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonF1Detail.setModifiedBy(seasonBean.getLoginId());
            seasonF1Detail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonF1DetailsRepository.saveAndFlush(seasonF1Detail);
            /**
             * Create F1 Program Allocations Data
             */
            seasonServiceInterface.createF1ProgramAllocation(seasonEntity, seasonBean.getLoginId());
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   /**
    * This method creates j1hs season program for HSP high level season
    * 
    * @param seasonBean
    */
   private void createHSPJ1HSSeasonProgram(SeasonBean seasonBean, Season season) {
      try {
         if (season.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
            SeasonJ1Detail seasonJ1Detail = new SeasonJ1Detail();
            seasonJ1Detail.setSeason(season);
            seasonJ1Detail.setProgramName(seasonBean.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.HSP_J1_HS);
            seasonJ1Detail.setSeasonStatus(season.getSeasonStatus());
            seasonJ1Detail.setShowAugFullYearToNewHF(CCIConstants.INACTIVE);
            seasonJ1Detail.setShowFirstSemToNewHF(CCIConstants.INACTIVE);
            seasonJ1Detail.setShowGuaranteed(CCIConstants.INACTIVE);
            seasonJ1Detail.setShowJanFullYearToNewHF(CCIConstants.INACTIVE);
            seasonJ1Detail.setShowSeasonToCurrentHF(CCIConstants.INACTIVE);
            seasonJ1Detail.setShowSecondSemToNewHF(CCIConstants.INACTIVE);
            seasonJ1Detail.setShowSpecialRequestStudent(CCIConstants.INACTIVE);
            seasonJ1Detail.setShowUnguaranteed(CCIConstants.INACTIVE);
            seasonJ1Detail.setShowWelcomeFamily(CCIConstants.INACTIVE);
            seasonJ1Detail.setActiveFullYearJanProgram(CCIConstants.INACTIVE);
            seasonJ1Detail.setCreatedBy(seasonBean.getLoginId());
            seasonJ1Detail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonJ1Detail.setModifiedBy(seasonBean.getLoginId());
            seasonJ1Detail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonJ1DetailsRepository.saveAndFlush(seasonJ1Detail);
            /**
             * create J1 program allocations
             */
            seasonServiceInterface.createJ1ProgramAllocation(season, seasonBean.getLoginId());
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   /**
    * This method creates j1hs season program for HSP high level season
    * 
    * @param seasonBean
    */
   private void createHSPIHPSeasonProgram(SeasonBean seasonBean, Season season) {
      try {
         if (season.getSeasonId() > 0 && seasonBean.getSeasonName() != null) {
            SeasonIHPDetail seasonIHPDetail = new SeasonIHPDetail();
            seasonIHPDetail.setSeason(season);
            seasonIHPDetail.setProgramName(seasonBean.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.HSP_STP_IHP);
            seasonIHPDetail.setSeasonStatus(season.getSeasonStatus());
            seasonIHPDetail.setStopAcceptingApps(CCIConstants.INACTIVE);
            seasonIHPDetail.setStopAcceptingAppsByGender(CCIConstants.INACTIVE);
            seasonIHPDetail.setStopAcceptingAppsHighSchoolVisits(CCIConstants.INACTIVE);
            seasonIHPDetail.setStopAcceptingAppsHolidayHomestay(CCIConstants.INACTIVE);
            seasonIHPDetail.setStopAcceptingAppsLanguageBuddy(CCIConstants.INACTIVE);
            seasonIHPDetail.setStopAcceptingAppsStandardIHP(CCIConstants.INACTIVE);
            seasonIHPDetail.setStopAcceptingAppsVolunteerHomestay(CCIConstants.INACTIVE);
            seasonIHPDetail.setCreatedBy(seasonBean.getLoginId());
            seasonIHPDetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonIHPDetail.setModifiedBy(seasonBean.getLoginId());
            seasonIHPDetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonIHPDetail = seasonIHPDetailRepository.saveAndFlush(seasonIHPDetail);
            // create region configuration for IHP
            Integer maxSeasonId = seasonIHPGeographyConfigurationRepository.findMaxIHPSeasonId();
            if (maxSeasonId > 0) {
               List<SeasonIHPGeographyConfiguration> previousRecordsToCopy = seasonIHPGeographyConfigurationRepository.findPreviousRecordsByMaxSeeasonId(maxSeasonId);
               if (previousRecordsToCopy != null) {
                  List<SeasonIHPGeographyConfiguration> newList = new ArrayList<SeasonIHPGeographyConfiguration>();
                  for (SeasonIHPGeographyConfiguration config : previousRecordsToCopy) {
                     SeasonIHPGeographyConfiguration newConfig = new SeasonIHPGeographyConfiguration();
                     if (config.getRegionIhp() != null) {
                        newConfig.setRegionIhp(config.getRegionIhp());
                     }
                     if (config.getLookupUsstate() != null) {
                        newConfig.setLookupUsstate(config.getLookupUsstate());
                     }
                     newConfig.setCreatedBy(seasonBean.getLoginId());
                     newConfig.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                     newConfig.setModifiedBy(seasonBean.getLoginId());
                     newConfig.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                     newConfig.setSeason(season);
                     newList.add(newConfig);
                  }
                  seasonIHPGeographyConfigurationRepository.save(newList);
                  seasonIHPGeographyConfigurationRepository.flush();
               }
            }
            List<RegionIHP> ihpRegions = regionIHPRepository.findAll();
            if (ihpRegions != null) {
               List<SeasonIHPDetailsRegionApplication> locationList = new ArrayList<SeasonIHPDetailsRegionApplication>();
               for (RegionIHP ihp : ihpRegions) {
                  SeasonIHPDetailsRegionApplication detailsRegionApplication = new SeasonIHPDetailsRegionApplication();
                  detailsRegionApplication.setRegionIhp(ihp);
                  detailsRegionApplication.setStopAcceptingApps(CCIConstants.INACTIVE);
                  detailsRegionApplication.setSeasonIhpdetail(seasonIHPDetail);
                  detailsRegionApplication.setCreatedBy(seasonBean.getLoginId());
                  detailsRegionApplication.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                  detailsRegionApplication.setModifiedBy(seasonBean.getLoginId());
                  detailsRegionApplication.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                  locationList.add(detailsRegionApplication);
               }
               seasonIHPDetailsRegionApplicationRepository.save(locationList);
            }
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   /**
    * @param seasonJ1Detail
    * @return
    */
   public J1HSBasicDetail getJ1HSBasicDetail(SeasonJ1Detail seasonJ1Detail) {
      J1HSBasicDetail j1hsBasicDetail = null;
      try {
         j1hsBasicDetail = new J1HSBasicDetail();
         j1hsBasicDetail.setSeasonId(seasonJ1Detail.getSeason().getSeasonId());
         j1hsBasicDetail.setSeasonProgramId(seasonJ1Detail.getSeasonJ1DetailsId());
         j1hsBasicDetail.setProgramName(seasonJ1Detail.getProgramName());
         if (seasonJ1Detail.getSeasonStatus() != null) {
            j1hsBasicDetail.setProgramStatusId(seasonJ1Detail.getSeasonStatus().getSeasonStatusId());
            j1hsBasicDetail.setProgramStatusValue(seasonJ1Detail.getSeasonStatus().getStatus());
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return j1hsBasicDetail;
   }

   /**
    * @param seasonJ1Detail
    * @return
    */
   public J1HSJanStart getJ1HSJanStart(SeasonJ1Detail seasonJ1Detail) {
      J1HSJanStart j1hsJanStart = null;
      try {
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
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return j1hsJanStart;
   }

   /**
    * @param seasonJ1Detail
    * @return
    */
   public J1HSAugStart getJ1HSAugStart(SeasonJ1Detail seasonJ1Detail) {
      J1HSAugStart j1hsAugStart = null;
      try {
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

      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return j1hsAugStart;
   }

   /**
    * @param seasonJ1Detail
    * @return
    */
   public J1HSFieldSettings getJ1HSFieldSettings(SeasonJ1Detail seasonJ1Detail) {
      J1HSFieldSettings j1hsFieldSettings = null;
      try {
         j1hsFieldSettings = new J1HSFieldSettings();
         j1hsFieldSettings.setSeasonId(seasonJ1Detail.getSeason().getSeasonId());
         j1hsFieldSettings.setSeasonProgramId(seasonJ1Detail.getSeasonJ1DetailsId());
         j1hsFieldSettings.setShowSeasProgToCurrentHF(seasonJ1Detail.getShowSeasonToCurrentHF() == CCIConstants.ACTIVE ? true : false);
         j1hsFieldSettings.setFsHoldDayLength(String.valueOf(seasonJ1Detail.getFieldStaffHoldLength()));
         j1hsFieldSettings.setHoldExpirationWarning(String.valueOf(seasonJ1Detail.getHoursBeforeHoldExpirationWarning()));
         // j1hsFieldSettings.setDefaultLCPaymentSchedule(String.valueOf(seasonJ1Detail.getLcPaymentScheduleId()));
         // j1hsFieldSettings.setFsAgreement("//TODO fs agreement is missing
         // only id is present leaving it as it is");
         j1hsFieldSettings.setHfReferences(String.valueOf(seasonJ1Detail.getHfReferences()));
         j1hsFieldSettings.setAddStartHFInquiriesDate(DateUtils.getMMddyyDate(seasonJ1Detail.getHfInquiryDate()));
         j1hsFieldSettings.setShowWelcomeFamilyModal(seasonJ1Detail.getShowWelcomeFamily() == CCIConstants.ACTIVE ? true : false);
         j1hsFieldSettings.setShowAllGuranteedParticipantsToFS(seasonJ1Detail.getShowGuaranteed() == CCIConstants.ACTIVE ? true : false);
         j1hsFieldSettings.setShowAllUnGuranteedParticipantsToFS(seasonJ1Detail.getShowUnguaranteed() == CCIConstants.ACTIVE ? true : false);
         j1hsFieldSettings.setShowSpecialRequestStudentsToRD(seasonJ1Detail.getShowSpecialRequestStudent() == CCIConstants.ACTIVE ? true : false);
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return j1hsFieldSettings;
   }

   /**
    * @param j1hsBasicDetail
    * @param seasonJ1Detail
    */
   public void updateJ1BasicDetails(J1HSBasicDetail j1hsBasicDetail, SeasonJ1Detail seasonJ1Detail) {
      try {
         seasonJ1Detail.setProgramName(j1hsBasicDetail.getProgramName());
         SeasonStatus seasonStatus = seasonStatusRepository.findOne(j1hsBasicDetail.getProgramStatusId());
         if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
            seasonJ1Detail.getSeason().setSeasonStatus(seasonStatus);
            seasonRepository.saveAndFlush(seasonJ1Detail.getSeason());
         }
         seasonJ1Detail.setSeasonStatus(seasonStatus);
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   /**
    * @param j1hsJanStart
    * @param seasonJ1Detail
    */
   public void updateJ1JanStartDetails(J1HSJanStart j1hsJanStart, SeasonJ1Detail seasonJ1Detail) {
      try {
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
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   /**
    * @param j1hsAugStart
    * @param seasonJ1Detail
    */
   public void updateJ1AugStartDetails(J1HSAugStart j1hsAugStart, SeasonJ1Detail seasonJ1Detail) {
      try {
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
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   /**
    * @param j1hsFieldSettings
    * @param seasonJ1Detail
    */
   public void updateJ1FSSettings(J1HSFieldSettings j1hsFieldSettings, SeasonJ1Detail seasonJ1Detail) {
      try {
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
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   /**
    * @param seasonVADetail
    * @return
    */
   public GHTSection1Base getVABasicDetail(SeasonVADetail seasonVADetail) {
      GHTSection1Base ghtSection1Base = null;
      try {
         ghtSection1Base = new GHTSection1Base();
         ghtSection1Base.setSeasonId(seasonVADetail.getSeason().getSeasonId());
         ghtSection1Base.setSeasonProgramId(seasonVADetail.getSeasonVADetailsId());
         ghtSection1Base.setProgramName(seasonVADetail.getProgramName());
         if (seasonVADetail.getSeasonStatus() != null) {
            ghtSection1Base.setProgramStatusId(seasonVADetail.getSeasonStatus().getSeasonStatusId());
            ghtSection1Base.setProgramStatusValue(seasonVADetail.getSeasonStatus().getStatus());
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return ghtSection1Base;
   }

   /**
    * @param seasonVADetail
    * @return
    */
   public GHTSection2Dates getVADates(SeasonVADetail seasonVADetail) {
      GHTSection2Dates ghtSection2Dates = null;
      try {
         ghtSection2Dates = new GHTSection2Dates();
         ghtSection2Dates.setSeasonId(seasonVADetail.getSeason().getSeasonId());
         ghtSection2Dates.setSeasonProgramId(seasonVADetail.getSeasonVADetailsId());
         ghtSection2Dates.setStartDate(DateUtils.getMMddyyDate(seasonVADetail.getStartDate()));
         ghtSection2Dates.setEndDate(DateUtils.getMMddyyDate(seasonVADetail.getEndDate()));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return ghtSection2Dates;
   }

   /**
    * @param ghtSection1Base
    * @param seasonVADetail
    */
   public void updateVABasicDetails(GHTSection1Base ghtSection1Base, SeasonVADetail seasonVADetail) {
      try {
         seasonVADetail.setProgramName(ghtSection1Base.getProgramName());
         SeasonStatus seasonStatus = seasonStatusRepository.findOne(ghtSection1Base.getProgramStatusId());
         if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
            seasonVADetail.getSeason().setSeasonStatus(seasonStatus);
            seasonRepository.saveAndFlush(seasonVADetail.getSeason());
         }
         seasonVADetail.setSeasonStatus(seasonStatus);
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   /**
    * @param ghtSection2Dates
    * @param seasonVADetail
    */
   public void updateVADates(GHTSection2Dates ghtSection2Dates, SeasonVADetail seasonVADetail) {
      try {
         seasonVADetail.setStartDate(DateUtils.getMMddyyDateFromString(ghtSection2Dates.getStartDate()));
         seasonVADetail.setEndDate(DateUtils.getMMddyyDateFromString(ghtSection2Dates.getEndDate()));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   /**
    * @param seasonWADetail
    * @return
    */
   public GHTSection1Base getWABasicDetail(SeasonWADetail seasonWADetail) {
      GHTSection1Base ghtSection1Base = null;
      try {
         ghtSection1Base = new GHTSection1Base();
         ghtSection1Base.setSeasonId(seasonWADetail.getSeason().getSeasonId());
         ghtSection1Base.setSeasonProgramId(seasonWADetail.getSeasonWADetailsId());
         ghtSection1Base.setProgramName(seasonWADetail.getProgramName());
         if (seasonWADetail.getSeasonStatus() != null) {
            ghtSection1Base.setProgramStatusId(seasonWADetail.getSeasonStatus().getSeasonStatusId());
            ghtSection1Base.setProgramStatusValue(seasonWADetail.getSeasonStatus().getStatus());
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return ghtSection1Base;
   }

   /**
    * @param seasonWADetail
    * @return
    */
   public GHTSection2Dates getWADates(SeasonWADetail seasonWADetail) {
      GHTSection2Dates ghtSection2Dates = null;
      try {
         ghtSection2Dates = new GHTSection2Dates();
         ghtSection2Dates.setSeasonId(seasonWADetail.getSeason().getSeasonId());
         ghtSection2Dates.setSeasonProgramId(seasonWADetail.getSeasonWADetailsId());
         ghtSection2Dates.setStartDate(DateUtils.getMMddyyDate(seasonWADetail.getStartDate()));
         ghtSection2Dates.setEndDate(DateUtils.getMMddyyDate(seasonWADetail.getEndDate()));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return ghtSection2Dates;
   }

   /**
    * @param ghtSection1Base
    * @param seasonVADetail
    */
   public void updateWABasicDetails(GHTSection1Base ghtSection1Base, SeasonWADetail seasonWADetail) {
      try {
         seasonWADetail.setProgramName(ghtSection1Base.getProgramName());
         SeasonStatus seasonStatus = seasonStatusRepository.findOne(ghtSection1Base.getProgramStatusId());
         if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
            seasonWADetail.getSeason().setSeasonStatus(seasonStatus);
            seasonRepository.saveAndFlush(seasonWADetail.getSeason());
         }
         seasonWADetail.setSeasonStatus(seasonStatus);
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   /**
    * @param ghtSection2Dates
    * @param seasonVADetail
    */
   public void updateWADates(GHTSection2Dates ghtSection2Dates, SeasonWADetail seasonWAADetail) {
      try {
         seasonWAADetail.setStartDate(DateUtils.getMMddyyDateFromString(ghtSection2Dates.getStartDate()));
         seasonWAADetail.setEndDate(DateUtils.getMMddyyDateFromString(ghtSection2Dates.getEndDate()));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   public SeasonGHTDetails getGHTHSAbroad(SeasonHSADetail seasonHSADetail) {
      SeasonGHTDetails seasonGHTDetails = null;
      try {
         seasonGHTDetails = new SeasonGHTDetails();
         int seasonId = seasonHSADetail.getSeason().getSeasonId();
         seasonGHTDetails.setSeasonId(seasonId);
         seasonGHTDetails.setSeasonProgramId(seasonHSADetail.getSeasonHSADetailsId());
         seasonGHTDetails.setDepartmentProgramId(CCIConstants.GHT_HS_ABRD_ID);
         GHTSection1Base ghtSection1Base = new GHTSection1Base();
         ghtSection1Base.setProgramName(seasonHSADetail.getProgramName());
         if (seasonHSADetail.getSeasonStatus() != null) {
            ghtSection1Base.setProgramStatusId(seasonHSADetail.getSeasonStatus().getSeasonStatusId());
            ghtSection1Base.setProgramStatusValue(seasonHSADetail.getSeasonStatus().getStatus());
         }
         ghtSection1Base.setSeasonId(seasonId);
         ghtSection1Base.setSeasonProgramId(seasonHSADetail.getSeasonHSADetailsId());
         seasonGHTDetails.setGhtBaseDetails(ghtSection1Base);
         GHTSection2Dates ghtSection2Dates = new GHTSection2Dates();
         ghtSection2Dates.setEndDate(DateUtils.getMMddyyDate(seasonHSADetail.getEndDate()));
         ghtSection2Dates.setSeasonId(seasonId);
         ghtSection2Dates.setSeasonProgramId(seasonHSADetail.getSeasonHSADetailsId());
         ghtSection2Dates.setStartDate(DateUtils.getMMddyyDate(seasonHSADetail.getStartDate()));
         seasonGHTDetails.setGhtDates(ghtSection2Dates);
         seasonGHTDetails.getGhtNotes().addAll(getGHTHSAProgramNotes(seasonId, seasonHSADetail.getSeasonHSADetailsId()));
      } catch (Exception e) {
         ExceptionUtil.logException(e, logger);
      }
      return seasonGHTDetails;
   }

   public SeasonGHTDetails updateGHTHSAbroad(SeasonGHTDetails seasonGHTDetails) {
      try {
         SeasonHSADetail seasonHsaDetail = seasonHSADetailsRepository.findOne(seasonGHTDetails.getSeasonProgramId());
         if (seasonHsaDetail == null) {
            return null;
         }
         seasonHsaDetail.setProgramName(seasonGHTDetails.getGhtBaseDetails().getProgramName());
         SeasonStatus seasonStatus = seasonStatusRepository.findOne(seasonGHTDetails.getGhtBaseDetails().getProgramStatusId());
         if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
            seasonHsaDetail.getSeason().setSeasonStatus(seasonStatus);
            seasonRepository.saveAndFlush(seasonHsaDetail.getSeason());
         }
         seasonHsaDetail.setSeasonStatus(seasonStatus);
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
      SeasonGHTDetails seasonGHTDetails = null;
      try {
         seasonGHTDetails = new SeasonGHTDetails();
         int seasonId = seasonLSDetail.getSeason().getSeasonId();
         seasonGHTDetails.setSeasonId(seasonId);
         seasonGHTDetails.setSeasonProgramId(seasonLSDetail.getSeasonLSDetailsId());
         seasonGHTDetails.setDepartmentProgramId(CCIConstants.GHT_LANG_SCL_ID);
         GHTSection1Base ghtSection1Base = new GHTSection1Base();
         ghtSection1Base.setProgramName(seasonLSDetail.getProgramName());
         if (seasonLSDetail.getSeasonStatus() != null) {
            ghtSection1Base.setProgramStatusId(seasonLSDetail.getSeasonStatus().getSeasonStatusId());
            ghtSection1Base.setProgramStatusValue(seasonLSDetail.getSeasonStatus().getStatus());
         }
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
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
      return seasonGHTDetails;
   }

   public SeasonGHTDetails updateGHTLanguageSchool(SeasonGHTDetails seasonGHTDetails) {
      try {
         SeasonLSDetail seasonLSDetail = seasonLSDetailsRepository.findOne(seasonGHTDetails.getSeasonProgramId());
         if (seasonLSDetail == null) {
            return null;
         }
         seasonLSDetail.setProgramName(seasonGHTDetails.getGhtBaseDetails().getProgramName());
         SeasonStatus seasonStatus = seasonStatusRepository.findOne(seasonGHTDetails.getGhtBaseDetails().getProgramStatusId());
         if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
            seasonLSDetail.getSeason().setSeasonStatus(seasonStatus);
            seasonRepository.saveAndFlush(seasonLSDetail.getSeason());
         }
         seasonLSDetail.setSeasonStatus(seasonStatus);
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
      SeasonGHTDetails seasonGHTDetails = null;
      try {
         seasonGHTDetails = new SeasonGHTDetails();
         int seasonId = seasonTADetail.getSeason().getSeasonId();
         seasonGHTDetails.setSeasonId(seasonId);
         seasonGHTDetails.setSeasonProgramId(seasonTADetail.getSeasonTADetailsId());
         seasonGHTDetails.setDepartmentProgramId(CCIConstants.GHT_TEACH_ABRD_ID);
         GHTSection1Base ghtSection1Base = new GHTSection1Base();
         ghtSection1Base.setProgramName(seasonTADetail.getProgramName());
         if (seasonTADetail.getSeasonStatus() != null) {
            ghtSection1Base.setProgramStatusId(seasonTADetail.getSeasonStatus().getSeasonStatusId());
            ghtSection1Base.setProgramStatusValue(seasonTADetail.getSeasonStatus().getStatus());
         }
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
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
      return seasonGHTDetails;
   }

   public SeasonGHTDetails updateGHTTeachAbroad(SeasonGHTDetails seasonGHTDetails) {
      try {
         SeasonTADetail seasonTADetail = seasonTADetailsRepository.findOne(seasonGHTDetails.getSeasonProgramId());
         if (seasonTADetail == null) {
            return null;
         }
         seasonTADetail.setProgramName(seasonGHTDetails.getGhtBaseDetails().getProgramName());
         SeasonStatus seasonStatus = seasonStatusRepository.findOne(seasonGHTDetails.getGhtBaseDetails().getProgramStatusId());
         if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
            seasonTADetail.getSeason().setSeasonStatus(seasonStatus);
            seasonRepository.saveAndFlush(seasonTADetail.getSeason());
         }
         seasonTADetail.setSeasonStatus(seasonStatus);
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
      try {
         SeasonHSADetail seasonHSADetail = seasonHSADetailsRepository.findOne(Integer.parseInt(seasonProgramId));
         if (seasonHSADetail == null) {
            return null;
         }
         GHTSection1Base ghtSection1Base = new GHTSection1Base();
         ghtSection1Base.setProgramName(seasonHSADetail.getProgramName());
         if (seasonHSADetail.getSeasonStatus() != null) {
            ghtSection1Base.setProgramStatusId(seasonHSADetail.getSeasonStatus().getSeasonStatusId());
            ghtSection1Base.setProgramStatusValue(seasonHSADetail.getSeasonStatus().getStatus());
         }
         ghtSection1Base.setSeasonId(seasonHSADetail.getSeason().getSeasonId());
         ghtSection1Base.setSeasonProgramId(seasonHSADetail.getSeasonHSADetailsId());
         return ghtSection1Base;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   public GHTSection1Base updateGHTHSSection1BaseAbroad(GHTSection1Base seasonGhtSection1Base) {
      try {
         SeasonHSADetail seasonHSADetail = seasonHSADetailsRepository.findOne(seasonGhtSection1Base.getSeasonProgramId());
         if (seasonHSADetail == null) {
            return null;
         }
         seasonHSADetail.setProgramName(seasonGhtSection1Base.getProgramName());
         SeasonStatus seasonStatus = seasonStatusRepository.findOne(seasonGhtSection1Base.getProgramStatusId());
         if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
            seasonHSADetail.getSeason().setSeasonStatus(seasonStatus);
            seasonRepository.saveAndFlush(seasonHSADetail.getSeason());
         }
         seasonHSADetail.setSeasonStatus(seasonStatus);
         seasonHSADetailsRepository.saveAndFlush(seasonHSADetail);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return seasonGhtSection1Base;
   }

   public GHTSection2Dates getGHTHSSection2DatesAbroad(String seasonProgramId) {
      try {
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
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
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
      try {
         SeasonLSDetail seasonLSDetail = seasonLSDetailsRepository.findOne(Integer.parseInt(seasonProgramId));
         if (seasonLSDetail == null) {
            return null;
         }
         GHTSection1Base ghtSection1Base = new GHTSection1Base();
         ghtSection1Base.setProgramName(seasonLSDetail.getProgramName());
         if (seasonLSDetail.getSeasonStatus() != null) {
            ghtSection1Base.setProgramStatusId(seasonLSDetail.getSeasonStatus().getSeasonStatusId());
            ghtSection1Base.setProgramStatusValue(seasonLSDetail.getSeasonStatus().getStatus());
         }
         ghtSection1Base.setSeasonId(seasonLSDetail.getSeason().getSeasonId());
         ghtSection1Base.setSeasonProgramId(seasonLSDetail.getSeasonLSDetailsId());
         return ghtSection1Base;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   public GHTSection1Base updateGHTLanguageSchoolSection1(GHTSection1Base seasonGHTSection1Base) {
      try {
         SeasonLSDetail seasonLsADetail = seasonLSDetailsRepository.findOne(seasonGHTSection1Base.getSeasonProgramId());
         if (seasonLsADetail == null) {
            return null;
         }
         seasonLsADetail.setProgramName(seasonGHTSection1Base.getProgramName());
         SeasonStatus seasonStatus = seasonStatusRepository.findOne(seasonGHTSection1Base.getProgramStatusId());
         if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
            seasonLsADetail.getSeason().setSeasonStatus(seasonStatus);
            seasonRepository.saveAndFlush(seasonLsADetail.getSeason());
         }
         seasonLsADetail.setSeasonStatus(seasonStatus);
         seasonLSDetailsRepository.saveAndFlush(seasonLsADetail);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return seasonGHTSection1Base;
   }

   public GHTSection2Dates getGHTLanguageSchoolSection2Dates(String seasonProgramId) {
      try {
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
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
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
      try {
         SeasonTADetail seasonTADetail = seasonTADetailsRepository.findOne(Integer.parseInt(seasonProgramId));
         if (seasonTADetail == null) {
            return null;
         }
         GHTSection1Base ghtSection1Base = new GHTSection1Base();
         ghtSection1Base.setProgramName(seasonTADetail.getProgramName());
         if (seasonTADetail.getSeasonStatus() != null) {
            ghtSection1Base.setProgramStatusId(seasonTADetail.getSeasonStatus().getSeasonStatusId());
            ghtSection1Base.setProgramStatusValue(seasonTADetail.getSeasonStatus().getStatus());
         }
         ghtSection1Base.setSeasonId(seasonTADetail.getSeason().getSeasonId());
         ghtSection1Base.setSeasonProgramId(seasonTADetail.getSeasonTADetailsId());
         return ghtSection1Base;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   public GHTSection1Base updateGHTTeachAbroadSection1(GHTSection1Base seasonSection1Base) {
      try {
         SeasonTADetail seasonTADetail = seasonTADetailsRepository.findOne(seasonSection1Base.getSeasonProgramId());
         if (seasonTADetail == null) {
            return null;
         }
         seasonTADetail.setProgramName(seasonSection1Base.getProgramName());
         SeasonStatus seasonStatus = seasonStatusRepository.findOne(seasonSection1Base.getProgramStatusId());
         if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
            seasonTADetail.getSeason().setSeasonStatus(seasonStatus);
            seasonRepository.saveAndFlush(seasonTADetail.getSeason());
         }
         seasonTADetail.setSeasonStatus(seasonStatus);
         seasonTADetailsRepository.saveAndFlush(seasonTADetail);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return seasonSection1Base;
   }

   public GHTSection2Dates getGHTTeachAbroadSection2Dates(String seasonProgramId) {
      try {
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
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
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
      try {
         SeasonCAPDetail seasonWPcap = seasonCAPDetailsRepository.findOne(Integer.parseInt(seasonProgramId));
         SeasonWPCAPDetails seasonWPCAPDetails = new SeasonWPCAPDetails();
         WPCAPBasicDetails wpcapBasicDetails = new WPCAPBasicDetails();
         wpcapBasicDetails.setProgramName(seasonWPcap.getProgramName());
         wpcapBasicDetails.setSeasonId(seasonWPcap.getSeason().getSeasonId());
         wpcapBasicDetails.setSeasonProgramId(Integer.parseInt(seasonProgramId));
         seasonWPCAPDetails.setDepartmentProgramId(CCIConstants.WP_WT_CAP_ID);
         if (seasonWPcap.getSeasonStatus() != null) {
            wpcapBasicDetails.setProgramStatusId(seasonWPcap.getSeasonStatus().getSeasonStatusId());
            wpcapBasicDetails.setProgramStatusValue(seasonWPcap.getSeasonStatus().getStatus());
         }
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
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   public SeasonWPCAPDetails updateWPCAPDetails(SeasonWPCAPDetails seasonWPCAPDetails) {
      try {
         if (seasonWPCAPDetails.getSeasonProgramId() == 0 || seasonWPCAPDetails.getSeasonProgramId() < 0) {
            throw new ValidationException(ErrorCode.INVALID_REQUEST, "provided season program id is either zero or less than zero");
         }
         try {
            SeasonCAPDetail seasonCAPDetail = seasonCAPDetailsRepository.findOne(seasonWPCAPDetails.getSeasonProgramId());
            seasonCAPDetail.setInternAppDeadlineDate(DateUtils.getDateFromString(seasonWPCAPDetails.getInternshipDetails().getApplicationDeadlineDate()));
            seasonCAPDetail.setInternEndDate(DateUtils.getDateFromString(seasonWPCAPDetails.getInternshipDetails().getEndDate()));
            seasonCAPDetail.setInternStartDate(DateUtils.getDateFromString(seasonWPCAPDetails.getInternshipDetails().getStartDate()));
            Season season = seasonRepository.findOne(seasonCAPDetail.getSeason().getSeasonId());
            seasonCAPDetail.setSeason(season);
            seasonCAPDetail.setProgramName(seasonWPCAPDetails.getDetails().getProgramName());
            SeasonStatus seasonStatus = seasonStatusRepository.findOne(seasonWPCAPDetails.getDetails().getProgramStatusId());
            if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
               seasonCAPDetail.getSeason().setSeasonStatus(seasonStatus);
               seasonRepository.saveAndFlush(seasonCAPDetail.getSeason());
            }
            seasonCAPDetail.setSeasonStatus(seasonStatus);
            seasonCAPDetail.setTraineeAppDeadlineDate(DateUtils.getDateFromString(seasonWPCAPDetails.getTraineeDetails().getApplicationDeadlineDate()));
            seasonCAPDetail.setTraineeEndDate(DateUtils.getDateFromString(seasonWPCAPDetails.getTraineeDetails().getEndDate()));
            seasonCAPDetail.setTraineeStartDate(DateUtils.getDateFromString(seasonWPCAPDetails.getTraineeDetails().getStartDate()));

            // seasonCAPDetail.setCreatedBy(1);
            // seasonCAPDetail.setCreatedOn(new
            // java.sql.Timestamp(System.currentTimeMillis()));
            seasonCAPDetail.setModifiedBy(seasonWPCAPDetails.getLoginId());
            seasonCAPDetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            if (seasonWPCAPDetails.getNotes() != null) {
               updateWPCAPNotes(seasonCAPDetail, seasonWPCAPDetails);
            }
            if (seasonWPCAPDetails.getDocuments() != null) {
               updateWPCAPDocuments(seasonWPCAPDetails, seasonCAPDetail.getSeason());
            }
            seasonServiceInterface.updateWPCAPAllocationDetails(seasonWPCAPDetails.getProgramAllocations());
            seasonCAPDetailsRepository.saveAndFlush(seasonCAPDetail);
         } catch (Exception ex) {
            ExceptionUtil.logException(ex, logger);
            return null;
         }
         return seasonWPCAPDetails;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   private void updateWPCAPDocuments(SeasonWPCAPDetails seasonWPCAPDetails, Season season) {
      try {
         List<SeasonProgramDocument> newDocList = null;
         List<SeasonProgramDocument> seasonProgramDocuments = seasonProgramDocumentRepository.findAllProgramDocumentsBySeasonIdAndDepartmentProgramId(season.getSeasonId(),
               CCIConstants.WP_WT_CAP_ID);
         if (seasonProgramDocuments != null) {
            seasonProgramDocumentRepository.delete(seasonProgramDocuments);
         }
         if (seasonWPCAPDetails.getDocuments() != null) {
            newDocList = new ArrayList<SeasonProgramDocument>();
            for (SeasonWPCAPDocuments capDocuments : seasonWPCAPDetails.getDocuments()) {
               try {
                  SeasonProgramDocument sprgDoc = new SeasonProgramDocument();
                  DocumentInformation documentInformation = new DocumentInformation();
                  documentInformation.setFileName(capDocuments.getFileName());
                  documentInformation.setDocumentName(capDocuments.getDocName());
                  documentInformation.setUrl(capDocuments.getDocUrl());
                  documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(capDocuments.getDocType()));
                  documentInformation.setCreatedBy(seasonWPCAPDetails.getLoginId());
                  documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                  documentInformation.setModifiedBy(seasonWPCAPDetails.getLoginId());
                  documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                  documentInformation = documentInformationRepository.saveAndFlush(documentInformation);
                  sprgDoc.setActive(capDocuments.isActive() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                  sprgDoc.setSeason(season);
                  sprgDoc.setDepartmentProgram(departmentProgramRepository.findOne(CCIConstants.WP_WT_CAP_ID));
                  sprgDoc.setDocumentInformation(documentInformation);
                  sprgDoc.setCreatedBy(seasonWPCAPDetails.getLoginId());
                  sprgDoc.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                  sprgDoc.setModifiedBy(seasonWPCAPDetails.getLoginId());
                  sprgDoc.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
                  newDocList.add(sprgDoc);
               } catch (Exception ex) {
                  ExceptionUtil.logException(ex, logger);
               }
            }
            if (newDocList != null) {
               seasonProgramDocumentRepository.save(newDocList);
            }
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   public WPCAPBasicDetails getWPCAPBasicDetails(String seasonProgramId) {
      try {
         SeasonCAPDetail seasonWPcap = seasonCAPDetailsRepository.findOne(Integer.parseInt(seasonProgramId));
         WPCAPBasicDetails wpcapBasicDetails = new WPCAPBasicDetails();
         wpcapBasicDetails.setSeasonId(seasonWPcap.getSeason().getSeasonId());
         wpcapBasicDetails.setSeasonProgramId(seasonWPcap.getSeasonCAPDetailsId());
         wpcapBasicDetails.setProgramName(seasonWPcap.getProgramName());
         if (seasonWPcap.getSeasonStatus() != null) {
            wpcapBasicDetails.setProgramStatusId(seasonWPcap.getSeasonStatus().getSeasonStatusId());
            wpcapBasicDetails.setProgramStatusValue(seasonWPcap.getSeasonStatus().getStatus());
         }
         return wpcapBasicDetails;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   public WPCAPBasicDetails updateWPCAPBasicDetails(WPCAPBasicDetails wpcapBasicDetails) {
      try {
         SeasonCAPDetail seasonWPcap = seasonCAPDetailsRepository.findOne(wpcapBasicDetails.getSeasonProgramId());
         seasonWPcap.setProgramName(wpcapBasicDetails.getProgramName());
         SeasonStatus seasonStatus = seasonStatusRepository.findOne(wpcapBasicDetails.getProgramStatusId());
         if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
            seasonWPcap.getSeason().setSeasonStatus(seasonStatus);
            seasonRepository.saveAndFlush(seasonWPcap.getSeason());
         }
         seasonWPcap.setSeasonStatus(seasonStatus);
         seasonCAPDetailsRepository.saveAndFlush(seasonWPcap);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return wpcapBasicDetails;
   }

   public WPCAPInternshipDetails getWPCAPInternshipDetails(String seasonProgramId) {
      try {
         SeasonCAPDetail seasonWPcap = seasonCAPDetailsRepository.findOne(Integer.parseInt(seasonProgramId));
         WPCAPInternshipDetails wpcapInternshipDetails = new WPCAPInternshipDetails();
         wpcapInternshipDetails.setSeasonId(seasonWPcap.getSeason().getSeasonId());
         wpcapInternshipDetails.setSeasonProgramId(seasonWPcap.getSeasonCAPDetailsId());
         wpcapInternshipDetails.setApplicationDeadlineDate(DateUtils.getMMddyyDate(seasonWPcap.getInternAppDeadlineDate()));
         wpcapInternshipDetails.setEndDate(DateUtils.getMMddyyDate(seasonWPcap.getInternEndDate()));
         wpcapInternshipDetails.setStartDate(DateUtils.getMMddyyDate(seasonWPcap.getInternStartDate()));

         return wpcapInternshipDetails;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
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
      try {
         SeasonCAPDetail seasonWPcap = seasonCAPDetailsRepository.findOne(Integer.parseInt(seasonProgramId));
         WPCAPTraineeDetails wpcapTraineeDetails = new WPCAPTraineeDetails();
         wpcapTraineeDetails.setSeasonId(seasonWPcap.getSeason().getSeasonId());
         wpcapTraineeDetails.setSeasonProgramId(seasonWPcap.getSeasonCAPDetailsId());
         wpcapTraineeDetails.setApplicationDeadlineDate(DateUtils.getMMddyyDate(seasonWPcap.getTraineeAppDeadlineDate()));
         wpcapTraineeDetails.setEndDate(DateUtils.getMMddyyDate(seasonWPcap.getTraineeEndDate()));
         wpcapTraineeDetails.setStartDate(DateUtils.getMMddyyDate(seasonWPcap.getTraineeStartDate()));
         return wpcapTraineeDetails;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
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
      try {
         SeasonWnTWinterDetail seasonWnTWinterDetail = seasonWinterRepository.findOne(Integer.parseInt(seasonProgramId));
         SeasonWPDetails seasonWPDetails = new SeasonWPDetails();
         seasonWPDetails.setSeasonId(seasonWnTWinterDetail.getSeason().getSeasonId());
         seasonWPDetails.setSeasonProgramId(seasonWnTWinterDetail.getSeasonWnTWinterDetailsId());
         seasonWPDetails.setDepartmentProgramId(CCIConstants.WP_WT_WINTER_ID);
         WPBasicDetail wpBasicDetail = new WPBasicDetail();
         wpBasicDetail.setProgramName(seasonWnTWinterDetail.getProgramName());
         if (seasonWnTWinterDetail.getSeasonStatus() != null) {
            wpBasicDetail.setProgramStatusId(seasonWnTWinterDetail.getSeasonStatus().getSeasonStatusId());
            wpBasicDetail.setProgramStatusValue(seasonWnTWinterDetail.getSeasonStatus().getStatus());
         }
         wpBasicDetail.setSeasonId(seasonWnTWinterDetail.getSeason().getSeasonId());
         wpBasicDetail.setSeasonProgramId(seasonWnTWinterDetail.getSeasonWnTWinterDetailsId());
         seasonWPDetails.setWpBasicDetail(wpBasicDetail);

         WPSectionOne wpSectionOne = new WPSectionOne();
         wpSectionOne.setApplicationDeadlineDate(DateUtils.getMMddyyDate(seasonWnTWinterDetail.getApplicationDeadlineDate()));
         wpSectionOne.setEndDate(DateUtils.getMMddyyDate(seasonWnTWinterDetail.getEndDate()));
         wpSectionOne.setIsJobBoardOpen(seasonWnTWinterDetail.getIsJobBoardOpen() != CCIConstants.INACTIVE);
         wpSectionOne.setMaxPendingJobAppls(seasonWnTWinterDetail.getMaxPendingJobApps() + "");
         wpSectionOne.setSeasonId(seasonWnTWinterDetail.getSeason().getSeasonId());
         wpSectionOne.setSeasonProgramId(seasonWnTWinterDetail.getSeasonWnTWinterDetailsId());
         wpSectionOne.setStartDate(DateUtils.getMMddyyDate(seasonWnTWinterDetail.getStartDate()));

         seasonWPDetails.setWpSectionOne(wpSectionOne);
         seasonWPDetails.getWpNotes().addAll(getWPWinterNotes(seasonWnTWinterDetail.getSeason().getSeasonId(), seasonWnTWinterDetail.getSeasonWnTWinterDetailsId()));
         seasonWPDetails.getWpDocuments().addAll(getWPDocs(seasonWnTWinterDetail.getSeason().getSeasonId(), seasonWnTWinterDetail.getSeasonWnTWinterDetailsId(),
               CCIConstants.WP_WT_WINTER, CCIConstants.WP_WT_WINTER_ID));
         seasonWPDetails.setWpProgramAllocations(getWPWinterAllocationDetails(seasonProgramId));
         // program allocations not complete

         return seasonWPDetails;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   public SeasonWPDetails updateWPWinterDetails(SeasonWPDetails seasonWPDetails) {
      try {
         SeasonWnTWinterDetail seasonWnTWinterDetail = seasonWinterRepository.findOne(seasonWPDetails.getSeasonProgramId());
         seasonWnTWinterDetail.setApplicationDeadlineDate(DateUtils.getDateFromString(seasonWPDetails.getWpSectionOne().getApplicationDeadlineDate()));
         seasonWnTWinterDetail.setEndDate(DateUtils.getDateFromString(seasonWPDetails.getWpSectionOne().getEndDate()));
         seasonWnTWinterDetail.setIsJobBoardOpen(seasonWPDetails.getWpSectionOne().isIsJobBoardOpen() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         seasonWnTWinterDetail.setMaxPendingJobApps(Integer.parseInt(seasonWPDetails.getWpSectionOne().getMaxPendingJobAppls()));
         seasonWnTWinterDetail.setStartDate(DateUtils.getDateFromString(seasonWPDetails.getWpSectionOne().getStartDate()));
         seasonWnTWinterDetail.setProgramName(seasonWPDetails.getWpBasicDetail().getProgramName());
         SeasonStatus seasonStatus = seasonStatusRepository.findOne(seasonWPDetails.getWpBasicDetail().getProgramStatusId());
         if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
            seasonWnTWinterDetail.getSeason().setSeasonStatus(seasonStatus);
            seasonRepository.saveAndFlush(seasonWnTWinterDetail.getSeason());
         }
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
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   public WPBasicDetail getWPWinterBaseDetails(String seasonProgramId) {
      try {
         SeasonWnTWinterDetail seasonWnTWinterDetail = seasonWinterRepository.findOne(Integer.parseInt(seasonProgramId));
         WPBasicDetail wpBasicDetail = new WPBasicDetail();
         wpBasicDetail.setProgramName(seasonWnTWinterDetail.getProgramName());
         if (seasonWnTWinterDetail.getSeasonStatus() != null) {
            wpBasicDetail.setProgramStatusId(seasonWnTWinterDetail.getSeasonStatus().getSeasonStatusId());
            wpBasicDetail.setProgramStatusValue(seasonWnTWinterDetail.getSeasonStatus().getStatus());
         }
         wpBasicDetail.setSeasonId(seasonWnTWinterDetail.getSeason().getSeasonId());
         wpBasicDetail.setSeasonProgramId(seasonWnTWinterDetail.getSeasonWnTWinterDetailsId());
         return wpBasicDetail;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   public WPBasicDetail updateWPWinterBaseDetails(WPBasicDetail wpBasicDetail) {
      try {
         SeasonWnTWinterDetail seasonWnTWinterDetail = seasonWinterRepository.findOne(wpBasicDetail.getSeasonProgramId());
         seasonWnTWinterDetail.setProgramName(wpBasicDetail.getProgramName());
         SeasonStatus seasonStatus = seasonStatusRepository.findOne(wpBasicDetail.getProgramStatusId());
         if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
            seasonWnTWinterDetail.getSeason().setSeasonStatus(seasonStatus);
            seasonRepository.saveAndFlush(seasonWnTWinterDetail.getSeason());
         }
         seasonWnTWinterDetail.setSeasonStatus(seasonStatus);
         seasonWinterRepository.saveAndFlush(seasonWnTWinterDetail);
         return wpBasicDetail;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   public WPSectionOne getWPWinterSectionOneDetails(String seasonProgramId) {
      try {
         SeasonWnTWinterDetail seasonWnTWinterDetail = seasonWinterRepository.findOne(Integer.parseInt(seasonProgramId));
         WPSectionOne wpSectionOne = new WPSectionOne();
         wpSectionOne.setApplicationDeadlineDate(DateUtils.getMMddyyDate(seasonWnTWinterDetail.getApplicationDeadlineDate()));
         wpSectionOne.setEndDate(DateUtils.getMMddyyDate(seasonWnTWinterDetail.getEndDate()));
         wpSectionOne.setIsJobBoardOpen(seasonWnTWinterDetail.getIsJobBoardOpen() != CCIConstants.INACTIVE);
         wpSectionOne.setMaxPendingJobAppls(seasonWnTWinterDetail.getMaxPendingJobApps() + "");
         wpSectionOne.setSeasonId(seasonWnTWinterDetail.getSeason().getSeasonId());
         wpSectionOne.setSeasonProgramId(seasonWnTWinterDetail.getSeasonWnTWinterDetailsId());
         wpSectionOne.setStartDate(DateUtils.getMMddyyDate(seasonWnTWinterDetail.getStartDate()));
         return wpSectionOne;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   public WPSectionOne updateWPWinterSectionOneDetails(WPSectionOne wpSectionOne) {
      try {
         SeasonWnTWinterDetail seasonWnTWinterDetail = seasonWinterRepository.findOne(wpSectionOne.getSeasonProgramId());
         seasonWnTWinterDetail.setApplicationDeadlineDate(DateUtils.getDateFromString(wpSectionOne.getApplicationDeadlineDate()));
         seasonWnTWinterDetail.setEndDate(DateUtils.getDateFromString(wpSectionOne.getEndDate()));
         seasonWnTWinterDetail.setIsJobBoardOpen(wpSectionOne.isIsJobBoardOpen() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         seasonWnTWinterDetail.setMaxPendingJobApps(Integer.parseInt(wpSectionOne.getMaxPendingJobAppls()));
         seasonWnTWinterDetail.setStartDate(DateUtils.getDateFromString(wpSectionOne.getStartDate()));
         seasonWinterRepository.saveAndFlush(seasonWnTWinterDetail);
         return wpSectionOne;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   public WPProgramAllocations getWPWinterAllocationDetails(String seasonProgramId) {
      try {
         WPProgramAllocations wpProgramAllocations = null;
         SeasonWnTWinterDetail seasonWnTWinterDetail = seasonWTWinterRepository.findOne(Integer.valueOf(seasonProgramId));
         if (seasonWnTWinterDetail != null) {
            List<SeasonWPAllocation> wpAllocations = seasonWPAllocationRepository.findSeasonWPAllocationBySeasonId(seasonWnTWinterDetail.getSeason().getSeasonId());
            if (wpAllocations != null) {
               wpProgramAllocations = new WPProgramAllocations();
               // TODO update other values once participants and partners
               // modules are integrated
               int totalMaxParticipants = 0;
               wpProgramAllocations.setSeasonId(seasonWnTWinterDetail.getSeason().getSeasonId());
               wpProgramAllocations.setSeasonProgramId(seasonWnTWinterDetail.getSeasonWnTWinterDetailsId());
               try {
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
               } catch (Exception ex) {
                  ExceptionUtil.logException(ex, logger);
                  return null;
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
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   public WPProgramAllocations updateWPWinterAllocationDetails(WPProgramAllocations wpProgramAllocations) {
      try {
         WPProgramAllocations returnObject = null;
         if (wpProgramAllocations != null && wpProgramAllocations.getSeasonId() > 0 && wpProgramAllocations.getSeasonProgramId() > 0) {
            List<SeasonWPAllocation> wpAllocations = seasonWPAllocationRepository.findSeasonWPAllocationBySeasonId(wpProgramAllocations.getSeasonId());
            List<SeasonWPAllocation> updatedList = new ArrayList<SeasonWPAllocation>();
            for (SeasonWPAllocation seasonWPAllocation : wpAllocations) {
               if (seasonWPAllocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.WP_WT_WINTER_ID) {
                  updateWPWinterProgramAllocation(wpProgramAllocations, updatedList, seasonWPAllocation);
               }
            }
            seasonWPAllocationRepository.save(updatedList);
            returnObject = getWPWinterAllocationDetails(String.valueOf(wpProgramAllocations.getSeasonProgramId()));
         }
         return returnObject;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   @Transactional
   public void createWPWinterProgramAllocation(Season season, int loginId) {
      try {
         List<SeasonWPAllocation> seasonWpAllocations = new ArrayList<SeasonWPAllocation>();
         SeasonWPAllocation jobFairWinter = new SeasonWPAllocation();
         DepartmentProgramOption departmentProgramOption_JobFair = departmentProgramOptionRepository.findOne(CCIConstants.JOB_FAIR_WINTER_ID);
         jobFairWinter.setDepartmentProgramOption(departmentProgramOption_JobFair);
         jobFairWinter.setMaxPax(0);
         jobFairWinter.setSeason(season);
         jobFairWinter.setCreatedBy(loginId);
         jobFairWinter.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         jobFairWinter.setModifiedBy(loginId);
         jobFairWinter.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWpAllocations.add(jobFairWinter);

         SeasonWPAllocation selfPlacedWinter = new SeasonWPAllocation();
         DepartmentProgramOption departmentProgramOption_SelfPlaceSpring = departmentProgramOptionRepository.findOne(CCIConstants.SELF_PLACED_WINTER_ID);
         selfPlacedWinter.setDepartmentProgramOption(departmentProgramOption_SelfPlaceSpring);
         selfPlacedWinter.setMaxPax(0);
         selfPlacedWinter.setSeason(season);
         selfPlacedWinter.setCreatedBy(loginId);
         selfPlacedWinter.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         selfPlacedWinter.setModifiedBy(loginId);
         selfPlacedWinter.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWpAllocations.add(selfPlacedWinter);

         SeasonWPAllocation directPlacementWinter = new SeasonWPAllocation();
         DepartmentProgramOption departmentProgramOption_DirectPlacementWinter = departmentProgramOptionRepository.findOne(CCIConstants.DIRECT_PLACEMENT_WINTER_ID);
         directPlacementWinter.setDepartmentProgramOption(departmentProgramOption_DirectPlacementWinter);
         directPlacementWinter.setMaxPax(0);
         directPlacementWinter.setSeason(season);
         directPlacementWinter.setCreatedBy(loginId);
         directPlacementWinter.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         directPlacementWinter.setModifiedBy(loginId);
         directPlacementWinter.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWpAllocations.add(directPlacementWinter);

         seasonWPAllocationRepository.save(seasonWpAllocations);
         seasonWPAllocationRepository.flush();

      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   // WP summer

   /**
    * @param seasonWnTSummerDetail
    * @return
    */
   public WPBasicDetail getWPSummerBaseDetails(SeasonWnTSummerDetail seasonWnTSummerDetail) {
      try {
         WPBasicDetail wpBasicDetail = new WPBasicDetail();
         wpBasicDetail.setSeasonId(seasonWnTSummerDetail.getSeason().getSeasonId());
         wpBasicDetail.setSeasonProgramId(seasonWnTSummerDetail.getSeasonWnTSummerDetailsId());
         wpBasicDetail.setProgramName(seasonWnTSummerDetail.getProgramName() != null ? seasonWnTSummerDetail.getProgramName() : null);
         if (seasonWnTSummerDetail.getSeasonStatus() != null) {
            wpBasicDetail.setProgramStatusId(seasonWnTSummerDetail.getSeasonStatus().getSeasonStatusId());
            wpBasicDetail.setProgramStatusValue(seasonWnTSummerDetail.getSeasonStatus().getStatus());
         }
         return wpBasicDetail;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   /**
    * @param seasonWnTSummerDetail
    * @return
    */
   public WPSectionOne getWPSummerSection1Details(SeasonWnTSummerDetail seasonWnTSummerDetail) {
      try {
         WPSectionOne wpSectionOne = new WPSectionOne();
         wpSectionOne.setSeasonId(seasonWnTSummerDetail.getSeason().getSeasonId());
         wpSectionOne.setSeasonProgramId(seasonWnTSummerDetail.getSeasonWnTSummerDetailsId());
         wpSectionOne.setStartDate(seasonWnTSummerDetail.getStartDate() != null ? DateUtils.getMMddyyDate(seasonWnTSummerDetail.getStartDate()) : null);
         wpSectionOne.setEndDate(seasonWnTSummerDetail.getEndDate() != null ? DateUtils.getMMddyyDate(seasonWnTSummerDetail.getEndDate()) : null);
         wpSectionOne.setApplicationDeadlineDate(
               seasonWnTSummerDetail.getApplicationDeadlineDate() != null ? DateUtils.getMMddyyDate(seasonWnTSummerDetail.getApplicationDeadlineDate()) : null);
         wpSectionOne.setIsJobBoardOpen(seasonWnTSummerDetail.getIsJobBoardOpen() == CCIConstants.ACTIVE ? true : false);
         wpSectionOne.setMaxPendingJobAppls((seasonWnTSummerDetail.getMaxPendingJobApps() != null && seasonWnTSummerDetail.getMaxPendingJobApps() > 0)
               ? String.valueOf(seasonWnTSummerDetail.getMaxPendingJobApps()) : "0");
         return wpSectionOne;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   /**
    * @param wpBasicDetail
    * @param seasonWnTSummerDetail
    */
   public void updateWPSummerBaseDetails(WPBasicDetail wpBasicDetail, SeasonWnTSummerDetail seasonWnTSummerDetail) {
      try {
         seasonWnTSummerDetail.setProgramName(wpBasicDetail.getProgramName() != null ? wpBasicDetail.getProgramName() : null);
         SeasonStatus seasonStatus = seasonStatusRepository.findOne(wpBasicDetail.getProgramStatusId());
         if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
            seasonWnTSummerDetail.getSeason().setSeasonStatus(seasonStatus);
            seasonRepository.saveAndFlush(seasonWnTSummerDetail.getSeason());
         }
         seasonWnTSummerDetail.setSeasonStatus(seasonStatus);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   /**
    * @param wpSectionOne
    * @param seasonWnTSummerDetail
    */
   public void updateWPSummerSection1Details(WPSectionOne wpSectionOne, SeasonWnTSummerDetail seasonWnTSummerDetail) {
      try {
         seasonWnTSummerDetail.setStartDate(wpSectionOne.getStartDate() != null ? DateUtils.getMMddyyDateFromString(wpSectionOne.getStartDate()) : null);
         seasonWnTSummerDetail.setEndDate(wpSectionOne.getEndDate() != null ? DateUtils.getMMddyyDateFromString(wpSectionOne.getEndDate()) : null);
         seasonWnTSummerDetail
               .setApplicationDeadlineDate(wpSectionOne.getApplicationDeadlineDate() != null ? DateUtils.getMMddyyDateFromString(wpSectionOne.getApplicationDeadlineDate()) : null);
         seasonWnTSummerDetail.setIsJobBoardOpen(wpSectionOne.isIsJobBoardOpen() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         seasonWnTSummerDetail.setMaxPendingJobApps(wpSectionOne.getMaxPendingJobAppls() != null ? Integer.valueOf(wpSectionOne.getMaxPendingJobAppls()) : null);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   // WP spring

   /**
    * @param seasonWnTSpringDetail
    * @return
    */
   public WPBasicDetail getWPSpringBaseDetails(SeasonWnTSpringDetail seasonWnTSpringDetail) {
      WPBasicDetail wpBasicDetail = null;
      try {
         wpBasicDetail = new WPBasicDetail();
         wpBasicDetail.setSeasonId(seasonWnTSpringDetail.getSeason().getSeasonId());
         wpBasicDetail.setSeasonProgramId(seasonWnTSpringDetail.getSeasonWnTSpringDetailsId());
         wpBasicDetail.setProgramName(seasonWnTSpringDetail.getProgramName() != null ? seasonWnTSpringDetail.getProgramName() : null);
         if (seasonWnTSpringDetail.getSeasonStatus() != null) {
            wpBasicDetail.setProgramStatusId(seasonWnTSpringDetail.getSeasonStatus().getSeasonStatusId());
            wpBasicDetail.setProgramStatusValue(seasonWnTSpringDetail.getSeasonStatus().getStatus());
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
      return wpBasicDetail;
   }

   /**
    * @param seasonWnTSpringDetail
    * @return
    */
   public WPSectionOne getWPSpringSection1Details(SeasonWnTSpringDetail seasonWnTSpringDetail) {
      WPSectionOne wpSectionOne = null;
      try {
         wpSectionOne = new WPSectionOne();
         wpSectionOne.setSeasonId(seasonWnTSpringDetail.getSeason().getSeasonId());
         wpSectionOne.setSeasonProgramId(seasonWnTSpringDetail.getSeasonWnTSpringDetailsId());
         wpSectionOne.setStartDate(seasonWnTSpringDetail.getStartDate() != null ? DateUtils.getMMddyyDate(seasonWnTSpringDetail.getStartDate()) : null);
         wpSectionOne.setEndDate(seasonWnTSpringDetail.getEndDate() != null ? DateUtils.getMMddyyDate(seasonWnTSpringDetail.getEndDate()) : null);
         wpSectionOne.setApplicationDeadlineDate(
               seasonWnTSpringDetail.getApplicationDeadlineDate() != null ? DateUtils.getMMddyyDate(seasonWnTSpringDetail.getApplicationDeadlineDate()) : null);
         wpSectionOne.setIsJobBoardOpen(seasonWnTSpringDetail.getIsJobBoardOpen() == CCIConstants.ACTIVE ? true : false);
         wpSectionOne.setMaxPendingJobAppls(seasonWnTSpringDetail.getMaxPendingJobApps() > 0 ? String.valueOf(seasonWnTSpringDetail.getMaxPendingJobApps()) : "0");
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
      return wpSectionOne;
   }

   /**
    * @param wpBasicDetail
    * @param seasonWnTSpringDetail
    */
   public void updateWPSpringBaseDetails(WPBasicDetail wpBasicDetail, SeasonWnTSpringDetail seasonWnTSpringDetail) {
      try {
         seasonWnTSpringDetail.setProgramName(wpBasicDetail.getProgramName() != null ? wpBasicDetail.getProgramName() : null);
         SeasonStatus seasonStatus = seasonStatusRepository.findOne(wpBasicDetail.getProgramStatusId());
         if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
            seasonWnTSpringDetail.getSeason().setSeasonStatus(seasonStatus);
            seasonRepository.saveAndFlush(seasonWnTSpringDetail.getSeason());
         }
         seasonWnTSpringDetail.setSeasonStatus(seasonStatus);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   /**
    * @param wpSectionOne
    * @param seasonWnTSpringDetail
    */
   public void updateWPSpringSection1Details(WPSectionOne wpSectionOne, SeasonWnTSpringDetail seasonWnTSpringDetail) {
      try {
         seasonWnTSpringDetail.setStartDate(wpSectionOne.getStartDate() != null ? DateUtils.getMMddyyDateFromString(wpSectionOne.getStartDate()) : null);
         seasonWnTSpringDetail.setEndDate(wpSectionOne.getEndDate() != null ? DateUtils.getMMddyyDateFromString(wpSectionOne.getEndDate()) : null);
         seasonWnTSpringDetail
               .setApplicationDeadlineDate(wpSectionOne.getApplicationDeadlineDate() != null ? DateUtils.getMMddyyDateFromString(wpSectionOne.getApplicationDeadlineDate()) : null);
         seasonWnTSpringDetail.setIsJobBoardOpen(wpSectionOne.isIsJobBoardOpen() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         seasonWnTSpringDetail.setMaxPendingJobApps(wpSectionOne.getMaxPendingJobAppls() != null ? Integer.valueOf(wpSectionOne.getMaxPendingJobAppls()) : null);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   public void createSeasonDepartmentNotes(SeasonBean seasonBean, Season seasonEntity) {
      try {
         if (seasonBean.getNotes() != null && !seasonBean.getNotes().isEmpty()) {
            for (SeasonDepartmentNotes note : seasonBean.getNotes()) {
               SeasonDepartmentNote seasonDepartmentNote = new SeasonDepartmentNote();
               seasonDepartmentNote.setDepartmentNote(note.getNoteValue());
               seasonDepartmentNote.setSeason(seasonEntity);
               seasonDepartmentNote.setActive((byte) (note.isActive() ? 1 : 0));
               seasonDepartmentNote.setCreatedBy(seasonBean.getLoginId());
               seasonDepartmentNote.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               seasonDepartmentNote.setModifiedBy(seasonBean.getLoginId());
               seasonDepartmentNote.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               seasonDepartmentNotesRepository.saveAndFlush(seasonDepartmentNote);
            }
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   public void updateSeasonDepartmentNotes(SeasonBean seasonBean, Season seasonEntity) {
      try {
         List<SeasonDepartmentNote> seasonDepartmentNotes = seasonDepartmentNotesRepository.findAllDepartmentNotesBySeasonId(seasonEntity.getSeasonId());
         if (seasonDepartmentNotes != null && !seasonDepartmentNotes.isEmpty()) {
            seasonDepartmentNotesRepository.delete(seasonDepartmentNotes);
         }
         for (SeasonDepartmentNotes notes : seasonBean.getNotes()) {
            SeasonDepartmentNote currentNote = new SeasonDepartmentNote();
            currentNote.setDepartmentNote(notes.getNoteValue());
            currentNote.setSeason(seasonEntity);
            currentNote.setCreatedBy(seasonBean.getLoginId());
            currentNote.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            currentNote.setModifiedBy(seasonBean.getLoginId());
            currentNote.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonDepartmentNotesRepository.saveAndFlush(currentNote);
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
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
      try {
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
                  note.setCreatedOn(DateUtils.getTimestamp(prgNote.getCreatedOn()));
                  Login login = loginRepository.findOne(1);// TODO find user
                                                           // from session
                  if (login != null) {
                     note.setCreatedBy(login.getLoginName());
                  }
                  j1hsNotes.add(note);
               }
            }
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
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
      try {
         List<SeasonProgramDocument> seasonProgramDocuments = seasonProgramDocumentRepository.findAllProgramDocsBySeasonId(seasonId);
         if (seasonProgramDocuments != null) {
            j1hsDocuments = new ArrayList<J1HSDocuments>();
            for (SeasonProgramDocument programDocument : seasonProgramDocuments) {
               if (programDocument.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_J1_HS)) {
                  J1HSDocuments documents = new J1HSDocuments();
                  documents.setSeasonId(programDocument.getSeason().getSeasonId());
                  documents.setSeasonProgramId(seasonProgramId);
                  documents.setDepartmentProgramId(CCIConstants.HSP_J1_HS_ID);
                  try {
                     if (programDocument.getDocumentInformation().getDocumentName() != null) {
                        documents.setDocName(programDocument.getDocumentInformation().getDocumentName());
                     }
                  } catch (Exception ex) {
                     ExceptionUtil.logException(ex, logger);
                  }
                  try {
                     if (programDocument.getDocumentInformation().getFileName() != null) {
                        documents.setFileName(programDocument.getDocumentInformation().getFileName());
                     }
                  } catch (Exception ex) {
                     ExceptionUtil.logException(ex, logger);
                  }
                  String docType = null;
                  try {
                     if (programDocument.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName() != null) {
                        docType = programDocument.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName();
                     }
                  } catch (Exception ex) {
                     ExceptionUtil.logException(ex, logger);
                  }
                  documents.setDocType(docType);
                  try {
                     if (programDocument.getDocumentInformation().getUrl() != null) {
                        documents.setDocUrl(programDocument.getDocumentInformation().getUrl());
                     }
                  } catch (Exception ex) {
                     ExceptionUtil.logException(ex, logger);
                  }
                  documents.setUploadDate(DateUtils.getMMddyyDate(programDocument.getDocumentInformation().getModifiedOn()));
                  documents.setActive(programDocument.getActive() == CCIConstants.ACTIVE ? true : false);
                  Login login = loginRepository.findOne(1);
                  if (login != null) {
                     documents.setUploadedBy(login.getLoginName());
                  }
                  j1hsDocuments.add(documents);
               }
            }
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
      return j1hsDocuments;
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<GHTSection3Notes> getGHTHSAProgramNotes(Integer seasonId, Integer seasonProgramId) {
      try {
         return getGHTNotes(seasonId, seasonProgramId, CCIConstants.GHT_HS_ABRD, CCIConstants.GHT_HS_ABRD_ID);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<GHTSection3Notes> getGHTLSProgramNotes(Integer seasonId, Integer seasonProgramId) {
      try {
         return getGHTNotes(seasonId, seasonProgramId, CCIConstants.GHT_LANG_SCL, CCIConstants.GHT_LANG_SCL_ID);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<GHTSection3Notes> getGHTTAProgramNotes(Integer seasonId, Integer seasonProgramId) {
      try {
         return getGHTNotes(seasonId, seasonProgramId, CCIConstants.GHT_TEACH_ABRD, CCIConstants.GHT_TEACH_ABRD_ID);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<GHTSection3Notes> getGHTVAProgramNotes(Integer seasonId, Integer seasonProgramId) {
      try {
         return getGHTNotes(seasonId, seasonProgramId, CCIConstants.GHT_VOL_ABRD, CCIConstants.GHT_VOL_ABRD_ID);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<GHTSection3Notes> getGHTWAProgramNotes(Integer seasonId, Integer seasonProgramId) {
      try {
         return getGHTNotes(seasonId, seasonProgramId, CCIConstants.GHT_WRK_ABRD, CCIConstants.GHT_WRK_ABRD_ID);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   /**
    * @param seasonProgramId
    * @param ghtNotes
    * @param prgNote
    */
   private List<GHTSection3Notes> getGHTNotes(Integer seasonId, Integer seasonProgramId, String programType, Integer departmentProgramId) {
      List<GHTSection3Notes> ghtNotes = null;
      try {
         List<SeasonProgramNote> ghtProgramNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonId(seasonId);
         if (ghtProgramNotes != null) {
            ghtNotes = new ArrayList<GHTSection3Notes>();
            for (SeasonProgramNote prgNote : ghtProgramNotes) {
               if (prgNote.getDepartmentProgram().getProgramName().equals(programType)) {
                  GHTSection3Notes notes = new GHTSection3Notes();
                  notes.setSeasonId(prgNote.getSeason().getSeasonId());
                  notes.setSeasonProgramId(seasonProgramId);
                  notes.setDepartmentProgramId(departmentProgramId);
                  if (prgNote.getProgramNote() != null) {
                     notes.setNoteValue(prgNote.getProgramNote());
                  }
                  notes.setCreatedOn(DateUtils.getTimestamp(prgNote.getCreatedOn()));
                  Login login = loginRepository.findOne(1);// TODO find user
                                                           // from session
                  if (login != null) {
                     notes.setCreatedBy(login.getLoginName());
                  }
                  ghtNotes.add(notes);
               }
            }
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
      return ghtNotes;
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<WPNotes> getWPSummerNotes(Integer seasonId, Integer seasonProgramId) {
      try {
         return getWPNotes(seasonId, seasonProgramId, CCIConstants.WP_WT_SUMMER, CCIConstants.WP_WT_SUMMER_ID);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<WPNotes> getWPSpringNotes(Integer seasonId, Integer seasonProgramId) {
      try {
         return getWPNotes(seasonId, seasonProgramId, CCIConstants.WP_WT_SPRING, CCIConstants.WP_WT_SPRING_ID);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<WPNotes> getWPWinterNotes(Integer seasonId, Integer seasonProgramId) {
      try {
         return getWPNotes(seasonId, seasonProgramId, CCIConstants.WP_WT_WINTER, CCIConstants.WP_WT_WINTER_ID);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @param wpNotes
    * @return
    */
   private List<WPNotes> getWPNotes(Integer seasonId, Integer seasonProgramId, String programType, Integer departmentProgramId) {
      List<WPNotes> wpNotes = null;
      try {
         List<SeasonProgramNote> wpProgramNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonId(seasonId);
         if (wpProgramNotes != null) {
            wpNotes = new ArrayList<WPNotes>();
            for (SeasonProgramNote prgNote : wpProgramNotes) {
               if (prgNote.getDepartmentProgram().getProgramName().equals(programType)) {
                  WPNotes notes = new WPNotes();
                  notes.setSeasonId(prgNote.getSeason().getSeasonId());
                  notes.setSeasonProgramId(seasonProgramId);
                  notes.setDepartmentProgramId(departmentProgramId);
                  if (prgNote.getProgramNote() != null) {
                     notes.setNoteValue(prgNote.getProgramNote());
                  }
                  notes.setCreatedOn(DateUtils.getTimestamp(prgNote.getCreatedOn()));
                  Login login = loginRepository.findOne(1);// TODO find user
                                                           // from session
                  if (login != null) {
                     notes.setCreatedBy(login.getLoginName());
                  }

                  wpNotes.add(notes);
               }
            }
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return wpNotes;
   }

   /**
    * @param seasonHspJ1HSDetails
    * @param seasonJ1Detail
    */
   public void updateJ1Notes(SeasonHspJ1HSDetails seasonHspJ1HSDetails, Season season) {
      try {
         List<SeasonProgramNote> programNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(season.getSeasonId(), CCIConstants.HSP_J1_HS_ID);
         seasonProgramNotesRepository.delete(programNotes);
         List<SeasonProgramNote> updatedNotes = new ArrayList<SeasonProgramNote>();
         for (J1HSNotes j1Note : seasonHspJ1HSDetails.getJ1HsNotes()) {
            SeasonProgramNote sprNote = new SeasonProgramNote();
            sprNote.setSeason(season);
            if (j1Note.getNoteValue() != null) {
               sprNote.setProgramNote(j1Note.getNoteValue());
            }
            sprNote.setDepartmentProgram(departmentProgramRepository.findOne(CCIConstants.HSP_J1_HS_ID));
            sprNote.setCreatedBy(seasonHspJ1HSDetails.getLoginId());
            sprNote.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            sprNote.setModifiedBy(seasonHspJ1HSDetails.getLoginId());
            sprNote.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            updatedNotes.add(sprNote);
         }
         seasonProgramNotesRepository.save(updatedNotes);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   /**
    * common method to update all GHT program type notes
    * 
    * @param seasonGHTDetails
    * @param season
    * @param programTypeId
    */
   public void updateGHTNotes(SeasonGHTDetails seasonGHTDetails, Season season, Integer programTypeId) {
      try {
         List<SeasonProgramNote> programNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(season.getSeasonId(), programTypeId);
         seasonProgramNotesRepository.delete(programNotes);
         List<SeasonProgramNote> updatedNotes = new ArrayList<SeasonProgramNote>();
         for (GHTSection3Notes ghtSection3Notes : seasonGHTDetails.getGhtNotes()) {
            String note = ghtSection3Notes.getNoteValue();
            updateNotes(season, programTypeId, updatedNotes, note, seasonGHTDetails.getLoginId());
         }
         seasonProgramNotesRepository.save(updatedNotes);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   /**
    * Common method to update WP Summer, Spring and Winter season program notes
    * 
    * @param seasonWPDetails
    * @param season
    * @param programTypeId
    */
   public void updateWPNotes(SeasonWPDetails seasonWPDetails, Season season, Integer programTypeId) {
      try {
         List<SeasonProgramNote> programNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(season.getSeasonId(), programTypeId);
         seasonProgramNotesRepository.delete(programNotes);
         List<SeasonProgramNote> updatedNotes = new ArrayList<SeasonProgramNote>();
         for (WPNotes wpNotes : seasonWPDetails.getWpNotes()) {
            String note = wpNotes.getNoteValue();
            updateNotes(season, programTypeId, updatedNotes, note, seasonWPDetails.getLoginId());
         }
         seasonProgramNotesRepository.save(updatedNotes);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   /**
    * @param season
    * @param programTypeId
    * @param updatedNotes
    * @param note
    */
   private void updateNotes(Season season, Integer programTypeId, List<SeasonProgramNote> updatedNotes, String note, int loginId) {
      try {
         SeasonProgramNote sprNote = new SeasonProgramNote();
         sprNote.setSeason(season);
         if (note != null) {
            sprNote.setProgramNote(note);
         }
         sprNote.setDepartmentProgram(departmentProgramRepository.findOne(programTypeId));
         sprNote.setCreatedBy(loginId);
         sprNote.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         sprNote.setModifiedBy(loginId);
         sprNote.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         updatedNotes.add(sprNote);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<WPDocuments> getWPDocs(Integer seasonId, Integer seasonProgramId, String programType, Integer departmentProgramId) {
      List<WPDocuments> wpDocuments = null;
      try {
         List<SeasonProgramDocument> seasonProgramDocuments = seasonProgramDocumentRepository.findAllProgramDocsBySeasonId(seasonId);
         if (seasonProgramDocuments != null) {
            wpDocuments = new ArrayList<WPDocuments>();
            for (SeasonProgramDocument programDocument : seasonProgramDocuments) {
               if (programDocument.getDepartmentProgram().getProgramName().equals(programType)) {
                  WPDocuments documents = new WPDocuments();
                  documents.setSeasonId(programDocument.getSeason().getSeasonId());
                  documents.setSeasonProgramId(seasonProgramId);
                  documents.setDepartmentProgramId(departmentProgramId);
                  try {
                     if (programDocument.getDocumentInformation().getDocumentName() != null) {
                        documents.setDocName(programDocument.getDocumentInformation().getDocumentName());
                     }
                  } catch (Exception ex) {
                     ExceptionUtil.logException(ex, logger);
                  }
                  try {
                     if (programDocument.getDocumentInformation().getFileName() != null) {
                        documents.setFileName(programDocument.getDocumentInformation().getFileName());
                     }
                  } catch (Exception ex) {
                     ExceptionUtil.logException(ex, logger);
                  }
                  try {
                     if (programDocument.getDocumentInformation() != null) {
                        documents.setDocType(programDocument.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
                     }
                  } catch (Exception ex) {
                     ExceptionUtil.logException(ex, logger);
                  }
                  try {
                     if (programDocument.getDocumentInformation().getUrl() != null) {
                        documents.setDocUrl(programDocument.getDocumentInformation().getUrl());
                     }
                  } catch (Exception ex) {
                     ExceptionUtil.logException(ex, logger);
                  }
                  documents.setUploadDate(DateUtils.getMMddyyDate(programDocument.getDocumentInformation().getModifiedOn()));
                  documents.setActive(programDocument.getActive() == CCIConstants.ACTIVE ? true : false);
                  Login login = loginRepository.findOne(1);// TODO find user
                                                           // from session
                  if (login != null && login.getLoginName() != null) {
                     documents.setUploadedBy(login.getLoginName());
                  }
                  wpDocuments.add(documents);
               }
            }
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
      return wpDocuments;
   }

   public void updateJ1HSDocs(SeasonHspJ1HSDetails seasonHspJ1HSDetails, Season season) {
      try {
         List<SeasonProgramDocument> seasonProgramDocuments = seasonProgramDocumentRepository.findAllProgramDocumentsBySeasonIdAndDepartmentProgramId(season.getSeasonId(),
               CCIConstants.HSP_J1_HS_ID);
         seasonProgramDocumentRepository.delete(seasonProgramDocuments);
         List<SeasonProgramDocument> newDocList = new ArrayList<SeasonProgramDocument>();
         for (J1HSDocuments j1hsDocument : seasonHspJ1HSDetails.getJ1HsDocuments()) {
            try {
               SeasonProgramDocument sprgDoc = new SeasonProgramDocument();
               DocumentInformation documentInformation = new DocumentInformation();
               documentInformation.setFileName(j1hsDocument.getFileName());
               documentInformation.setDocumentName(j1hsDocument.getDocName());
               documentInformation.setUrl(j1hsDocument.getDocUrl());
               documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(j1hsDocument.getDocType()));
               documentInformation.setCreatedBy(seasonHspJ1HSDetails.getLoginId());
               documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               documentInformation.setModifiedBy(seasonHspJ1HSDetails.getLoginId());
               documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               documentInformation = documentInformationRepository.saveAndFlush(documentInformation);
               sprgDoc.setActive(CCIConstants.ACTIVE);
               sprgDoc.setSeason(season);
               sprgDoc.setDepartmentProgram(departmentProgramRepository.findOne(CCIConstants.HSP_J1_HS_ID));
               sprgDoc.setDocumentInformation(documentInformation);
               sprgDoc.setCreatedBy(seasonHspJ1HSDetails.getLoginId());
               sprgDoc.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               sprgDoc.setModifiedBy(seasonHspJ1HSDetails.getLoginId());
               sprgDoc.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               newDocList.add(sprgDoc);
            } catch (Exception e) {
               ExceptionUtil.logException(e, logger);
            }
         }
         seasonProgramDocumentRepository.save(newDocList);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   public void updateWPDocs(SeasonWPDetails seasonWPDetails, Season season, Integer programTypeId) {
      try {
         List<SeasonProgramDocument> seasonProgramDocuments = seasonProgramDocumentRepository.findAllProgramDocumentsBySeasonIdAndDepartmentProgramId(season.getSeasonId(),
               programTypeId);
         seasonProgramDocumentRepository.delete(seasonProgramDocuments);
         List<SeasonProgramDocument> newDocList = new ArrayList<SeasonProgramDocument>();
         for (WPDocuments wpDocument : seasonWPDetails.getWpDocuments()) {
            try {
               SeasonProgramDocument sprgDoc = new SeasonProgramDocument();
               DocumentInformation documentInformation = new DocumentInformation();
               documentInformation.setFileName(wpDocument.getFileName());
               documentInformation.setDocumentName(wpDocument.getDocName());
               documentInformation.setUrl(wpDocument.getDocUrl());
               documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(wpDocument.getDocType()));
               documentInformation.setCreatedBy(seasonWPDetails.getLoginId());
               documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               documentInformation.setModifiedBy(seasonWPDetails.getLoginId());
               documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               documentInformation = documentInformationRepository.saveAndFlush(documentInformation);
               sprgDoc.setActive(CCIConstants.ACTIVE);
               sprgDoc.setSeason(season);
               sprgDoc.setDepartmentProgram(departmentProgramRepository.findOne(programTypeId));
               sprgDoc.setDocumentInformation(documentInformation);
               sprgDoc.setCreatedBy(seasonWPDetails.getLoginId());
               sprgDoc.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               sprgDoc.setModifiedBy(seasonWPDetails.getLoginId());
               sprgDoc.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               newDocList.add(sprgDoc);
            } catch (Exception ex) {
               ExceptionUtil.logException(ex, logger);
            }
         }
         seasonProgramDocumentRepository.save(newDocList);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   public List<SeasonDocument> getHighLevelSeasonDocs(Integer seasonId, Integer departmentId) {
      List<SeasonDocument> seasonDocuments = null;
      try {
         List<SeasonDepartmentDocument> seasonDepartmentDocuments = seasonDepartmentDocumentRepository.findAllDepartmentDocsBySeasonId(seasonId);
         if (seasonDepartmentDocuments != null) {
            seasonDocuments = new ArrayList<SeasonDocument>();
            for (SeasonDepartmentDocument departmentDocument : seasonDepartmentDocuments) {
               SeasonDocument document = new SeasonDocument();
               document.setSeasonId(departmentDocument.getSeason().getSeasonId());
               document.setDepartmentId(departmentId);
               document.setDocType(departmentDocument.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
               document.setDocUrl(departmentDocument.getDocumentInformation().getUrl());
               document.setUploadDate(DateUtils.getTimestamp(departmentDocument.getModifiedOn()));
               Login login = loginRepository.findOne(1);// TODO find user from
                                                        // session
               if (login != null) {
                  document.setUploadedBy(login.getLoginName());
               }
               seasonDocuments.add(document);
            }
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
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
      try {
         if (note != null && note.getNotes() != null && !note.getNotes().isEmpty()) {
            Season season = seasonRepository.findOne(note.getSeasonId());
            DepartmentProgram departmentProgram = departmentProgramRepository.findOne(note.getDepartmentProgramId());

            for (String noteStr : note.getNotes()) {
               SeasonProgramNote seasonProgramNote = new SeasonProgramNote();
               seasonProgramNote.setActive((byte) 1);
               seasonProgramNote.setProgramNote(noteStr);
               seasonProgramNote.setSeason(season);
               seasonProgramNote.setDepartmentProgram(departmentProgram);
               seasonProgramNote.setCreatedBy(note.getLoginId());
               seasonProgramNote.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               seasonProgramNote.setModifiedBy(note.getLoginId());
               seasonProgramNote.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               seasonProgramNotesRepository.saveAndFlush(seasonProgramNote);
            }
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   /**
    * 
    * @param seasonBean
    * @param seasonEntity
    */
   public void updateSeasonProgramNotes(CommonNotesObject note) {
      try {
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
               seasonProgramNote.setActive(CCIConstants.ACTIVE);
               seasonProgramNote.setProgramNote(noteStr);
               seasonProgramNote.setSeason(season);
               seasonProgramNote.setDepartmentProgram(departmentProgram);
               seasonProgramNote.setCreatedBy(note.getLoginId());
               seasonProgramNote.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               seasonProgramNote.setModifiedBy(note.getLoginId());
               seasonProgramNote.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               seasonProgramNotesRepository.saveAndFlush(seasonProgramNote);
            }
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   public List<HSPF1SeasonHspF1Notes> getHSPF1Notes(SeasonF1Detail allF1Details) {
      try {
         int seasonId = allF1Details.getSeason().getSeasonId();
         List<HSPF1SeasonHspF1Notes> hspF1Notes = null;
         List<SeasonProgramNote> seasonProgramNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(seasonId, CCIConstants.HSP_F1_ID);
         if (seasonProgramNotes != null ) {
            hspF1Notes = new ArrayList<HSPF1SeasonHspF1Notes>();
            for (SeasonProgramNote seasonProgramNote : seasonProgramNotes) {
               HSPF1SeasonHspF1Notes hspf1SeasonHspF1Note = new HSPF1SeasonHspF1Notes();
               hspf1SeasonHspF1Note.setSeasonId(seasonProgramNote.getSeason().getSeasonId());
               hspf1SeasonHspF1Note.setSeasonProgramId(allF1Details.getSeasonF1DetailsId());
               hspf1SeasonHspF1Note.setDepartmentProgramId(CCIConstants.HSP_F1_ID);
               hspf1SeasonHspF1Note.setNoteValue(seasonProgramNote.getProgramNote());
               hspf1SeasonHspF1Note.setCreatedOn(DateUtils.getTimestamp(seasonProgramNote.getCreatedOn()));
               Login login = loginRepository.findOne(1);// TODO find user
                                                        // from session
               if (login != null) {
                  hspf1SeasonHspF1Note.setCreatedBy(login.getLoginName());
               }
               hspF1Notes.add(hspf1SeasonHspF1Note);
            }

         }
         return hspF1Notes;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   @Transactional
   private void updateHSPF1Notes(SeasonF1Detail allF1Details, SeasonHSPF1Details seasonHSPF1Details) {
      try {
         int seasonId = allF1Details.getSeason().getSeasonId();
         List<SeasonProgramNote> seasonProgramNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(seasonId, CCIConstants.HSP_F1_ID);
         seasonProgramNotesRepository.delete(seasonProgramNotes);
         seasonProgramNotes = new ArrayList<SeasonProgramNote>();
         DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.HSP_F1_ID);
         for (HSPF1SeasonHspF1Notes hspF1Notes : seasonHSPF1Details.getNotes()) {
            SeasonProgramNote programNote = new SeasonProgramNote();
            programNote.setActive(CCIConstants.ACTIVE);
            programNote.setDepartmentProgram(departmentProgram);
            programNote.setProgramNote(hspF1Notes.getNoteValue());
            programNote.setSeason(allF1Details.getSeason());
            programNote.setCreatedBy(seasonHSPF1Details.getLoginId());
            programNote.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            programNote.setModifiedBy(seasonHSPF1Details.getLoginId());
            programNote.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonProgramNotes.add(programNote);
         }
         seasonProgramNotesRepository.save(seasonProgramNotes);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   public List<HSPF1SeasonHspF1Documents> getHSPF1Documents(SeasonF1Detail allF1Details, int seasonProgramId) {
      try {
         int seasonId = allF1Details.getSeason().getSeasonId();
         List<HSPF1SeasonHspF1Documents> hspF1Documents = null;
         List<SeasonProgramDocument> spDocument = seasonProgramDocumentRepository.findAllProgramDocumentsBySeasonIdAndDepartmentProgramId(seasonId, CCIConstants.HSP_F1_ID);
         if (spDocument != null) {
            hspF1Documents = new ArrayList<HSPF1SeasonHspF1Documents>();
            for (SeasonProgramDocument seasonProgramDocument : spDocument) {
               try {
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
                  Login login = loginRepository.findOne(1);// TODO find user
                                                           // from session
                  if (login != null) {
                     doc.setUploadedBy(login.getLoginName());
                  }
                  hspF1Documents.add(doc);
               } catch (Exception ex) {
                  ExceptionUtil.logException(ex, logger);
               }
            }
         }
         return hspF1Documents;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   private List<SeasonWPCAPDocuments> getWPCAPDocuments(SeasonCAPDetail seasonWPcap, int seasonProgramId) {
      try {
         int seasonId = seasonWPcap.getSeason().getSeasonId();
         List<SeasonWPCAPDocuments> wpcapDocuments = null;
         List<SeasonProgramDocument> spDocument = seasonProgramDocumentRepository.findAllProgramDocumentsBySeasonIdAndDepartmentProgramId(seasonId, CCIConstants.WP_WT_CAP_ID);
         if (spDocument != null) {
            wpcapDocuments = new ArrayList<SeasonWPCAPDocuments>();
            for (SeasonProgramDocument seasonProgramDocument : spDocument) {
               try {
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
                  Login login = loginRepository.findOne(1);// TODO find user
                                                           // from session
                  if (login != null) {
                     doc.setUploadedBy(login.getLoginName());
                  }
                  wpcapDocuments.add(doc);
               } catch (Exception ex) {
                  ExceptionUtil.logException(ex, logger);
               }
            }
         }
         return wpcapDocuments;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   public List<SeasonWPCAPNotes> getWPCAPNotes(SeasonCAPDetail seasonWPcap, int seasonProgramId) {
      try {
         int seasonId = seasonWPcap.getSeason().getSeasonId();
         List<SeasonWPCAPNotes> seasonWPCAPNotes = null;
         List<SeasonProgramNote> seasonProgramNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(seasonId, CCIConstants.WP_WT_CAP_ID);
         if (seasonProgramNotes != null) {
            seasonWPCAPNotes = new ArrayList<SeasonWPCAPNotes>();
            if (seasonProgramNotes != null && !seasonProgramNotes.isEmpty()) {
               for (SeasonProgramNote seasonProgramNote : seasonProgramNotes) {
                  SeasonWPCAPNotes wpCapNote = new SeasonWPCAPNotes();
                  wpCapNote.setSeasonId(seasonId);
                  wpCapNote.setNoteValue(seasonProgramNote.getProgramNote());
                  wpCapNote.setSeasonProgramId(seasonProgramId);
                  wpCapNote.setDepartmentProgramId(seasonProgramNote.getDepartmentProgram().getDepartmentProgramId());
                  Login login = loginRepository.findOne(1);// TODO find user
                                                           // from session
                  if (login != null) {
                     wpCapNote.setCreatedBy(login.getLoginName());
                  }
                  wpCapNote.setCreatedOn(DateUtils.getTimestamp(seasonProgramNote.getCreatedOn()));
                  seasonWPCAPNotes.add(wpCapNote);
               }
            }
         }
         return seasonWPCAPNotes;
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
         return null;
      }
   }

   @Transactional
   private void updateWPCAPNotes(SeasonCAPDetail seasonCapDetail, SeasonWPCAPDetails seasonWpcapDetails) {
      try {
         int seasonId = seasonCapDetail.getSeason().getSeasonId();
         List<SeasonProgramNote> seasonProgramNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(seasonId, CCIConstants.WP_WT_CAP_ID);
         seasonProgramNotesRepository.delete(seasonProgramNotes);
         seasonProgramNotes = new ArrayList<SeasonProgramNote>();
         DepartmentProgram departmentProgram = departmentProgramRepository.findOne(CCIConstants.WP_WT_CAP_ID);
         for (SeasonWPCAPNotes notes : seasonWpcapDetails.getNotes()) {
            SeasonProgramNote programNote = new SeasonProgramNote();
            programNote.setActive(CCIConstants.ACTIVE);
            programNote.setDepartmentProgram(departmentProgram);
            programNote.setProgramNote(notes.getNoteValue());
            programNote.setSeason(seasonCapDetail.getSeason());
            programNote.setCreatedBy(seasonWpcapDetails.getLoginId());
            programNote.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            programNote.setModifiedBy(seasonWpcapDetails.getLoginId());
            programNote.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonProgramNotes.add(programNote);

         }
         seasonProgramNotesRepository.save(seasonProgramNotes);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   /**
    * @param wpProgramAllocations
    * @param updatedList
    * @param seasonWPAllocation
    */
   public void updateWPSummerProgramAllocation(WPProgramAllocations wpProgramAllocations, List<SeasonWPAllocation> updatedList, SeasonWPAllocation seasonWPAllocation) {
      try {
         if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JOB_FAIR_SUMMER)) {
            SeasonWPAllocation allocation = new SeasonWPAllocation();
            allocation = seasonWPAllocation;
            allocation.setMaxPax(wpProgramAllocations.getJobFairMaxParticipants());
            updatedList.add(allocation);
         } else if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.SELF_PLACED_SUMMER)) {
            SeasonWPAllocation allocation = new SeasonWPAllocation();
            allocation = seasonWPAllocation;
            allocation.setMaxPax(wpProgramAllocations.getSelfPlacedMaxParticipants());
            updatedList.add(allocation);
         } else if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.DIRECT_PLACEMENT_SUMMER)) {
            SeasonWPAllocation allocation = new SeasonWPAllocation();
            allocation = seasonWPAllocation;
            allocation.setMaxPax(wpProgramAllocations.getDirectPlcmntMaxParticipants());
            updatedList.add(allocation);
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   /**
    * @param wpProgramAllocations
    * @param updatedList
    * @param seasonWPAllocation
    */
   public void updateWPSpringProgramAllocation(WPProgramAllocations wpProgramAllocations, List<SeasonWPAllocation> updatedList, SeasonWPAllocation seasonWPAllocation) {
      try {
         if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JOB_FAIR_SPRING)) {
            SeasonWPAllocation allocation = new SeasonWPAllocation();
            allocation = seasonWPAllocation;
            allocation.setMaxPax(wpProgramAllocations.getJobFairMaxParticipants());
            updatedList.add(allocation);
         } else if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.SELF_PLACED_SPRING)) {
            SeasonWPAllocation allocation = new SeasonWPAllocation();
            allocation = seasonWPAllocation;
            allocation.setMaxPax(wpProgramAllocations.getSelfPlacedMaxParticipants());
            updatedList.add(allocation);
         } else if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.DIRECT_PLACEMENT_SPRING)) {
            SeasonWPAllocation allocation = new SeasonWPAllocation();
            allocation = seasonWPAllocation;
            allocation.setMaxPax(wpProgramAllocations.getDirectPlcmntMaxParticipants());
            updatedList.add(allocation);
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   /**
    * @param wpProgramAllocations
    * @param updatedList
    * @param seasonWPAllocation
    */
   public void updateWPWinterProgramAllocation(WPProgramAllocations wpProgramAllocations, List<SeasonWPAllocation> updatedList, SeasonWPAllocation seasonWPAllocation) {
      try {
         if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JOB_FAIR_WINTER)) {
            SeasonWPAllocation allocation = new SeasonWPAllocation();
            allocation = seasonWPAllocation;
            allocation.setMaxPax(wpProgramAllocations.getJobFairMaxParticipants());
            updatedList.add(allocation);
         } else if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.SELF_PLACED_WINTER)) {
            SeasonWPAllocation allocation = new SeasonWPAllocation();
            allocation = seasonWPAllocation;
            allocation.setMaxPax(wpProgramAllocations.getSelfPlacedMaxParticipants());
            updatedList.add(allocation);
         } else if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.DIRECT_PLACEMENT_WINTER)) {
            SeasonWPAllocation allocation = new SeasonWPAllocation();
            allocation = seasonWPAllocation;
            allocation.setMaxPax(wpProgramAllocations.getDirectPlcmntMaxParticipants());
            updatedList.add(allocation);
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   /**
    * 
    * @param wpCapProgramAllocations
    * @param updatedList
    * @param seasonWPAllocation
    */
   public void updateWPProgramAllocation(WPCAPProgramAllocations wpCapProgramAllocations, List<SeasonWPAllocation> updatedList, SeasonWPAllocation seasonWPAllocation) {
      try {
         if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.CAP_INTERNSHIP)) {
            SeasonWPAllocation allocation = new SeasonWPAllocation();
            allocation = seasonWPAllocation;
            allocation.setMaxPax(wpCapProgramAllocations.getInternshipMaximumParticipant());
            updatedList.add(allocation);
         } else if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.CAP_TRAINEE)) {
            SeasonWPAllocation allocation = new SeasonWPAllocation();
            allocation = seasonWPAllocation;
            allocation.setMaxPax(wpCapProgramAllocations.getTraineeMaximumParticipant());
            updatedList.add(allocation);
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   @Transactional
   public void createWPCapProgramAllocation(Season season, int loginId) {
      try {
         List<SeasonWPAllocation> seasonWpAllocations = new ArrayList<SeasonWPAllocation>();
         SeasonWPAllocation capInternship = new SeasonWPAllocation();
         DepartmentProgramOption departmentProgramOptionCapInternship = departmentProgramOptionRepository.findOne(CCIConstants.CAP_INTERNSHIP_ID);
         capInternship.setDepartmentProgramOption(departmentProgramOptionCapInternship);
         capInternship.setMaxPax(0);
         capInternship.setSeason(season);
         capInternship.setCreatedBy(loginId);
         capInternship.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         capInternship.setModifiedBy(loginId);
         capInternship.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWpAllocations.add(capInternship);

         SeasonWPAllocation capTrainee = new SeasonWPAllocation();
         DepartmentProgramOption departmentProgramOptionCapTrainee = departmentProgramOptionRepository.findOne(CCIConstants.CAP_TRAINEE_ID);
         capTrainee.setDepartmentProgramOption(departmentProgramOptionCapTrainee);
         capTrainee.setMaxPax(0);
         capTrainee.setSeason(season);
         capTrainee.setCreatedBy(loginId);
         capTrainee.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         capTrainee.setModifiedBy(loginId);
         capTrainee.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWpAllocations.add(capTrainee);

         seasonWPAllocationRepository.save(seasonWpAllocations);
         seasonWPAllocationRepository.flush();

      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   public void updateWPProgramAllocation(HSPF1ProgramAllocations hspf1ProgramAllocations, List<SeasonWPAllocation> updatedList, SeasonWPAllocation seasonWPAllocation) {
      try {
         if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_F1)) {
            SeasonWPAllocation allocation = new SeasonWPAllocation();
            allocation = seasonWPAllocation;
            allocation.setMaxPax(hspf1ProgramAllocations.getAugustStartMaximumParticipants());
            updatedList.add(allocation);
         } else if (seasonWPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_F1)) {
            SeasonWPAllocation allocation = new SeasonWPAllocation();
            allocation = seasonWPAllocation;
            allocation.setMaxPax(hspf1ProgramAllocations.getJanuaryStartMaximumParticipants());
            updatedList.add(allocation);
         }
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }

   public void updateHSPF1ProgramAllocation(HSPF1ProgramAllocations hspF1ProgramAllocation, List<SeasonHSPAllocation> updatedList, SeasonHSPAllocation seasonHSPAllocation) {
      try {
         if (hspF1ProgramAllocation != null) {
            if (seasonHSPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_F1)) {
               SeasonHSPAllocation allocation = new SeasonHSPAllocation();
               allocation = seasonHSPAllocation;
               allocation.setMaxGuaranteedPax(hspF1ProgramAllocation.getAugustStartMaximumParticipants());
               updatedList.add(allocation);
            }
            if (seasonHSPAllocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_F1)) {
               SeasonHSPAllocation allocation = new SeasonHSPAllocation();
               allocation = seasonHSPAllocation;
               allocation.setMaxGuaranteedPax(hspF1ProgramAllocation.getJanuaryStartMaximumParticipants());
               updatedList.add(allocation);
            }
         }

      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, logger);
      }
   }

   public void updateSeasonDocuments(SeasonBean seasonBean, Season seasonEntity) {
      try {
         List<SeasonDepartmentDocument> seasonDepartmentDocuments = seasonDepartmentDocumentRepository.findAllDepartmentDocsBySeasonId(seasonEntity.getSeasonId());
         seasonDepartmentDocumentRepository.delete(seasonDepartmentDocuments);
         List<SeasonDepartmentDocument> newDocList = new ArrayList<SeasonDepartmentDocument>();
         for (SeasonDocument hlsDocuments : seasonBean.getDocuments()) {
            try {
               SeasonDepartmentDocument sprgDoc = new SeasonDepartmentDocument();
               DocumentInformation documentInformation = new DocumentInformation();
               documentInformation.setFileName(hlsDocuments.getFileName());
               documentInformation.setDocumentName(hlsDocuments.getDocName());
               documentInformation.setUrl(hlsDocuments.getDocUrl());
               documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(hlsDocuments.getDocType()));
               documentInformation.setCreatedBy(seasonBean.getLoginId());
               documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               documentInformation.setModifiedBy(seasonBean.getLoginId());
               documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               documentInformation = documentInformationRepository.saveAndFlush(documentInformation);
               sprgDoc.setActive(CCIConstants.ACTIVE);
               sprgDoc.setSeason(seasonEntity);
               sprgDoc.setDocumentInformation(documentInformation);
               sprgDoc.setCreatedBy(seasonBean.getLoginId());
               sprgDoc.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               sprgDoc.setModifiedBy(seasonBean.getLoginId());
               sprgDoc.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               newDocList.add(sprgDoc);
            } catch (Exception ex) {
               ExceptionUtil.logException(ex, logger);
            }
         }
         seasonDepartmentDocumentRepository.save(newDocList);
      } catch (Exception ex) {
         ExceptionUtil.logException(ex, logger);
      }
   }
}
