/**
 * 
 */
package com.ccighgo.service.components.season;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.DocumentInformation;
import com.ccighgo.db.entities.DocumentTypeDocumentCategoryProcess;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LookupGender;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonIHPDetail;
import com.ccighgo.db.entities.SeasonIHPDetailsRegionApplication;
import com.ccighgo.db.entities.SeasonProgramDocument;
import com.ccighgo.db.entities.SeasonProgramNote;
import com.ccighgo.db.entities.SeasonStatus;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.DocumentInformationRepository;
import com.ccighgo.jpa.repositories.DocumentTypeDocumentCategoryProcessRepository;
import com.ccighgo.jpa.repositories.GenderRepository;
import com.ccighgo.jpa.repositories.IHPRegionsRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.SeasonIHPDetailRepository;
import com.ccighgo.jpa.repositories.SeasonIHPDetailsRegionApplicationRepository;
import com.ccighgo.jpa.repositories.SeasonProgramDocumentRepository;
import com.ccighgo.jpa.repositories.SeasonProgramNotesRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.jpa.repositories.SeasonStatusRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.IHPApplicationByRegion;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.IHPDates;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.IHPDocuments;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.IHPNameAndStatus;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.IHPNotes;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.IHPProgramConfiguration;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.SeasonHspStpIhpDetails;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;

/**
 * Helper class for Season High School Programs short term independent home stay
 * program(HSP STP-IHP).
 * 
 * @see
 * 
 * @author ravi
 *
 */
@Component
public class SeasonIHPProgramHelper {

   private static final Logger LOGGER = LoggerFactory.getLogger(SeasonIHPProgramHelper.class);

   @Autowired SeasonIHPDetailRepository ihpDetailRepository;
   @Autowired SeasonProgramDocumentRepository seasonProgramDocumentRepository;
   @Autowired LoginRepository loginRepository;
   @Autowired SeasonProgramNotesRepository seasonProgramNotesRepository;
   @Autowired SeasonStatusRepository seasonStatusRepository;
   @Autowired SeasonIHPDetailRepository seasonIHPDetailRepository;
   @Autowired DocumentTypeDocumentCategoryProcessRepository documentTypeDocumentCategoryProcessRepository;
   @Autowired DocumentInformationRepository documentInformationRepository;
   @Autowired DepartmentProgramRepository departmentProgramRepository;
   @Autowired GenderRepository genderRepository;
   @Autowired SeasonIHPDetailsRegionApplicationRepository seasonIHPDetailsRegionApplicationRepository;
   @Autowired IHPRegionsRepository ihpRegionsRepository;
   @Autowired SeasonRepository seasonRepository;
   @Autowired CommonComponentUtils componentUtils;
   @Autowired MessageUtils messageUtil;


   /**
    * @param seasonProgramId
    * @return
    */
   public IHPNameAndStatus getIHPNameAndStatus(Integer seasonProgramId) {
      IHPNameAndStatus ihpNameAndStatus = null;
      try {
         SeasonIHPDetail seasonIHPDetail = ihpDetailRepository.findOne(seasonProgramId);
         if (seasonIHPDetail != null) {
            ihpNameAndStatus = new IHPNameAndStatus();
            ihpNameAndStatus.setSeasonId(seasonIHPDetail.getSeason().getSeasonId());
            ihpNameAndStatus.setSeasonProgramId(seasonIHPDetail.getSeasonIHPDetailsId());
            ihpNameAndStatus.setProgramName(seasonIHPDetail.getProgramName() != null ? seasonIHPDetail.getProgramName() : null);
            ihpNameAndStatus.setProgramStatusId(seasonIHPDetail.getSeasonStatus().getSeasonStatusId());
            ihpNameAndStatus.setProgramStatusValue(seasonIHPDetail.getSeasonStatus().getStatus());
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return ihpNameAndStatus;
   }

   /**
    * @param seasonProgramId
    * @return
    */
   public IHPDates getIHPDates(Integer seasonProgramId) {
      IHPDates ihpDates = null;
      try {
         SeasonIHPDetail seasonIHPDetail = ihpDetailRepository.findOne(seasonProgramId);
         if (seasonIHPDetail != null) {
            ihpDates = new IHPDates();
            ihpDates.setSeasonId(seasonIHPDetail.getSeason().getSeasonId());
            ihpDates.setSeasonProgramId(seasonIHPDetail.getSeasonIHPDetailsId());
            ihpDates.setProgramStartDate(seasonIHPDetail.getStartDate() != null ? DateUtils.getMMddyyDate(seasonIHPDetail.getStartDate()) : null);
            ihpDates.setProgramEndDate(seasonIHPDetail.getEndDate() != null ? DateUtils.getMMddyyDate(seasonIHPDetail.getEndDate()) : null);
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return ihpDates;
   }

   /**
    * @param seasonProgramId
    * @return
    */
   public IHPProgramConfiguration getIHPConfiguration(Integer seasonProgramId) {
      IHPProgramConfiguration ihpProgramConfiguration = null;
      try {
         SeasonIHPDetail seasonIHPDetail = ihpDetailRepository.findOne(seasonProgramId);
         if (seasonIHPDetail != null) {
            ihpProgramConfiguration = new IHPProgramConfiguration();
            ihpProgramConfiguration.setSeasonId(seasonIHPDetail.getSeason().getSeasonId());
            ihpProgramConfiguration.setSeasonProgramId(seasonIHPDetail.getSeasonIHPDetailsId());
            ihpProgramConfiguration.setMaxNoOfParticipants(seasonIHPDetail.getMaxParticipants() != null ? seasonIHPDetail.getMaxParticipants() : 0);
            ihpProgramConfiguration.setApplicationCutOffPriorToProgStart(String.valueOf(seasonIHPDetail.getApplicationDeadLineWeeks()));
            ihpProgramConfiguration.setLcHoldTimeDays(seasonIHPDetail.getLcHoldTime() != null ? seasonIHPDetail.getLcHoldTime() : 0);
            ihpProgramConfiguration.setNoOfLcCanRequestHold(seasonIHPDetail.getNumberOfLCToRequestHold() != null ? seasonIHPDetail.getNumberOfLCToRequestHold() : 0);
            ihpProgramConfiguration.setSplitPlacementInPending(String.valueOf(seasonIHPDetail.getSplitPlacementPending() != null ? seasonIHPDetail.getSplitPlacementPending() : 0));
            ihpProgramConfiguration.setStopAcceptingApplications(seasonIHPDetail.getStopAcceptingApps() == CCIConstants.ACTIVE ? true : false);
            ihpProgramConfiguration.setStopAcceptingIhpStandardSettings(seasonIHPDetail.getStopAcceptingAppsStandardIHP() == CCIConstants.ACTIVE ? true : false);
            ihpProgramConfiguration.setStopAcceptingVolunteerHomeStayApplications(seasonIHPDetail.getStopAcceptingAppsVolunteerHomestay() == CCIConstants.ACTIVE ? true : false);
            ihpProgramConfiguration.setStopAcceptingLanguageBuddyApplications(seasonIHPDetail.getStopAcceptingAppsLanguageBuddy() == CCIConstants.ACTIVE ? true : false);
            ihpProgramConfiguration.setStopAcceptingHolidayHomeStayApplications(seasonIHPDetail.getStopAcceptingAppsHolidayHomestay() == CCIConstants.ACTIVE ? true : false);
            ihpProgramConfiguration.setStopAcceptingHighSchoolApplications(seasonIHPDetail.getStopAcceptingAppsHighSchoolVisits() == CCIConstants.ACTIVE ? true : false);
            ihpProgramConfiguration.setStopAcceptingApplicationByGender(seasonIHPDetail.getStopAcceptingAppsByGender() == CCIConstants.ACTIVE ? true : false);
            if (seasonIHPDetail.getApplicationDeadLineWeeks() != null && seasonIHPDetail.getApplicationDeadLineWeeks() > 0) {
               ihpProgramConfiguration.setApplicationCutOffPriorToProgStart(String.valueOf(seasonIHPDetail.getApplicationDeadLineWeeks()));
            }
            if (seasonIHPDetail.getLcHoldTime() != null && seasonIHPDetail.getLcHoldTime() > 0) {
               ihpProgramConfiguration.setLcHoldTimeDays(seasonIHPDetail.getLcHoldTime());
            }
            if (seasonIHPDetail.getNumberOfLCToRequestHold() != null && seasonIHPDetail.getNumberOfLCToRequestHold() > 0) {
               ihpProgramConfiguration.setNoOfLcCanRequestHold(seasonIHPDetail.getNumberOfLCToRequestHold());
            }
            if (seasonIHPDetail.getSplitPlacementPending() != null && seasonIHPDetail.getSplitPlacementPending() > 0) {
               ihpProgramConfiguration.setSplitPlacementInPending(String.valueOf(seasonIHPDetail.getSplitPlacementPending()));
            }
            if (seasonIHPDetail.getStopAcceptingApps() == CCIConstants.INACTIVE || seasonIHPDetail.getStopAcceptingApps() == CCIConstants.ACTIVE) {
               ihpProgramConfiguration.setStopAcceptingApplications(seasonIHPDetail.getStopAcceptingApps() == CCIConstants.ACTIVE ? true : false);
            }
            if (seasonIHPDetail.getStopAcceptingAppsStandardIHP() == CCIConstants.INACTIVE || seasonIHPDetail.getStopAcceptingAppsStandardIHP() == CCIConstants.ACTIVE) {
               ihpProgramConfiguration.setStopAcceptingIhpStandardSettings(seasonIHPDetail.getStopAcceptingAppsStandardIHP() == CCIConstants.ACTIVE ? true : false);
            }
            if (seasonIHPDetail.getStopAcceptingAppsVolunteerHomestay() == CCIConstants.INACTIVE || seasonIHPDetail.getStopAcceptingAppsVolunteerHomestay() == CCIConstants.ACTIVE) {
               ihpProgramConfiguration.setStopAcceptingVolunteerHomeStayApplications(seasonIHPDetail.getStopAcceptingAppsVolunteerHomestay() == CCIConstants.ACTIVE ? true : false);
            }
            if (seasonIHPDetail.getStopAcceptingAppsLanguageBuddy() == CCIConstants.INACTIVE || seasonIHPDetail.getStopAcceptingAppsLanguageBuddy() == CCIConstants.ACTIVE) {
               ihpProgramConfiguration.setStopAcceptingLanguageBuddyApplications(seasonIHPDetail.getStopAcceptingAppsLanguageBuddy() == CCIConstants.ACTIVE ? true : false);
            }
            if (seasonIHPDetail.getStopAcceptingAppsHolidayHomestay() == CCIConstants.INACTIVE || seasonIHPDetail.getStopAcceptingAppsHolidayHomestay() == CCIConstants.ACTIVE) {
               ihpProgramConfiguration.setStopAcceptingHolidayHomeStayApplications(seasonIHPDetail.getStopAcceptingAppsHolidayHomestay() == CCIConstants.ACTIVE ? true : false);
            }
            if (seasonIHPDetail.getStopAcceptingAppsHighSchoolVisits() == CCIConstants.INACTIVE || seasonIHPDetail.getStopAcceptingAppsHighSchoolVisits() == CCIConstants.ACTIVE) {
               ihpProgramConfiguration.setStopAcceptingHighSchoolApplications(seasonIHPDetail.getStopAcceptingAppsHighSchoolVisits() == CCIConstants.ACTIVE ? true : false);
            }
            if (seasonIHPDetail.getStopAcceptingAppsByGender() == CCIConstants.INACTIVE || seasonIHPDetail.getStopAcceptingAppsByGender() == CCIConstants.ACTIVE) {
               ihpProgramConfiguration.setStopAcceptingApplicationByGender(seasonIHPDetail.getStopAcceptingAppsByGender() == CCIConstants.ACTIVE ? true : false);
            }

            // set gender
            LookupGender gender = seasonIHPDetail.getLookupGender();
            if (gender != null) {
               if (gender.getGenderId() == CCIConstants.MALE || gender.getGenderId() == CCIConstants.FEMALE) {
                  ihpProgramConfiguration.setGenderId(gender.getGenderId());
                  ihpProgramConfiguration.setGenderCode(gender.getGenderName());
               } else {
                  ihpProgramConfiguration.setGenderId(gender.getGenderId());
                  ihpProgramConfiguration.setGenderCode(gender.getGenderName());
               }
            }
            // set regions
            if (seasonIHPDetail.getSeasonIhpdetailsRegionApplications() != null) {
               for (SeasonIHPDetailsRegionApplication regionApplication : seasonIHPDetail.getSeasonIhpdetailsRegionApplications()) {
                  IHPApplicationByRegion ihpApplicationByRegion = new IHPApplicationByRegion();
                  ihpApplicationByRegion.setSeasonId(seasonIHPDetail.getSeason().getSeasonId());
                  ihpApplicationByRegion.setSeasonProgramId(seasonIHPDetail.getSeasonIHPDetailsId());
                  ihpApplicationByRegion.setApplicationRegionId(regionApplication.getRegionIhp().getRegionIHPId());
                  ihpApplicationByRegion.setApplicationRegionName(regionApplication.getRegionIhp().getRegionName());
                  ihpApplicationByRegion.setAcceptApplicationFlag(regionApplication.getStopAcceptingApps() == CCIConstants.ACTIVE ? true : false);
                  ihpProgramConfiguration.getStopAcceptingApplicationByRegion().add(ihpApplicationByRegion);
               }
            }
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return ihpProgramConfiguration;
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<IHPDocuments> getIHPDocs(Integer seasonId, Integer seasonProgramId) {
      List<IHPDocuments> ihpDocuments = null;
      List<SeasonProgramDocument> seasonProgramDocuments = seasonProgramDocumentRepository.findAllProgramDocumentsBySeasonIdAndDepartmentProgramId(seasonId,
            CCIConstants.HSP_STP_IHP_ID);
      if (seasonProgramDocuments != null) {
         ihpDocuments = new ArrayList<IHPDocuments>();
         for (SeasonProgramDocument programDocument : seasonProgramDocuments) {
            if (programDocument.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_STP_IHP)) {
               IHPDocuments documents = new IHPDocuments();
               documents.setSeasonId(programDocument.getSeason().getSeasonId());
               documents.setSeasonProgramId(seasonProgramId);
               documents.setDepartmentProgramId(CCIConstants.HSP_STP_IHP_ID);
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
               Login login = loginRepository.findOne(1);// TODO find user from
                                                        // session
               if (login != null) {
                  documents.setUploadedBy(login.getLoginName());
               }
               ihpDocuments.add(documents);
            }
         }
      }
      return ihpDocuments;
   }

   /**
    * @param seasonId
    * @param seasonProgramId
    * @return
    */
   public List<IHPNotes> getIHPNotes(Integer seasonId, Integer seasonProgramId) {
      List<IHPNotes> ihpNotes = null;
      List<SeasonProgramNote> programNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(seasonId, CCIConstants.HSP_STP_IHP_ID);
      if (programNotes != null) {
         ihpNotes = new ArrayList<IHPNotes>();
         for (SeasonProgramNote prgNote : programNotes) {
            if (prgNote.getDepartmentProgram().getProgramName().equals(CCIConstants.HSP_STP_IHP)) {
               IHPNotes note = new IHPNotes();
               note.setSeasonId(prgNote.getSeason().getSeasonId());
               note.setSeasonProgramId(seasonProgramId);
               note.setDepartmentProgramId(CCIConstants.HSP_STP_IHP_ID);
               note.setNoteValue(prgNote.getProgramNote());
               note.setCreatedOn(DateUtils.getTimestamp(prgNote.getCreatedOn()));
               Login login = loginRepository.findOne(1);// TODO find user from
                                                        // session
               if (login != null) {
                  note.setCreatedBy(login.getLoginName());
               }
               ihpNotes.add(note);
            }
         }
      }
      return ihpNotes;
   }

   public SeasonHspStpIhpDetails updateIHPDetails(SeasonHspStpIhpDetails stpIhpDetails) {
      SeasonHspStpIhpDetails returnObject = null;
      try {
         SeasonIHPDetail seasonIHPDetail = ihpDetailRepository.findOne(stpIhpDetails.getSeasonProgramId());
         if (seasonIHPDetail != null) {
            if (stpIhpDetails.getIhpNameAndStatus() != null) {
               updateIHPNameAndStatus(stpIhpDetails.getIhpNameAndStatus());
            }
            if (stpIhpDetails.getIhpDates() != null) {
               updateIHPDates(stpIhpDetails.getIhpDates());
            }
            if (stpIhpDetails.getIhpProgramConfiguration() != null) {
               updateIHPProgramConfiguration(stpIhpDetails.getIhpProgramConfiguration());
            }
            if (stpIhpDetails.getIhpDocuments() != null) {
               updateIHPDocs(stpIhpDetails, seasonIHPDetail.getSeason());
            }
            if (stpIhpDetails.getIhpNotes() != null) {
               updateIHPNotes(stpIhpDetails, seasonIHPDetail.getSeason());
            }
            returnObject = stpIhpDetails;
         } else {
            LOGGER.debug("we cannot find the program for the id: " + stpIhpDetails.getSeasonProgramId() + ". Please contact support.");
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   /**
    * @param nameAndStatus
    * @return
    */
   public IHPNameAndStatus updateIHPNameAndStatus(IHPNameAndStatus nameAndStatus) {
      IHPNameAndStatus returnObject = null;
      if (nameAndStatus == null || nameAndStatus.getSeasonId() == 0) {
         return returnObject;
      }
      try {
         SeasonIHPDetail seasonIHPDetail = ihpDetailRepository.findOne(nameAndStatus.getSeasonProgramId());
         if (seasonIHPDetail != null) {
            if (nameAndStatus.getProgramName() != null) {
               seasonIHPDetail.setProgramName(nameAndStatus.getProgramName());
            }
            if (nameAndStatus.getProgramStatusId() > 0) {
               SeasonStatus seasonStatus = seasonStatusRepository.findOne(nameAndStatus.getProgramStatusId());
               if (seasonStatus.getStatus().equals(CCIConstants.STATUS_OPEN)) {
                  seasonIHPDetail.getSeason().setSeasonStatus(seasonStatus);
                  seasonRepository.saveAndFlush(seasonIHPDetail.getSeason());
               }
               seasonIHPDetail.setSeasonStatus(seasonStatus);
            }
            seasonIHPDetailRepository.saveAndFlush(seasonIHPDetail);
            returnObject = nameAndStatus;
         } else {
            LOGGER.debug("update nameAndStatus HSP_STP_IHP failed because no season program found for the id: " + nameAndStatus.getSeasonProgramId());
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   /**
    * @param dates
    * @return
    */
   public IHPDates updateIHPDates(IHPDates dates) {
      IHPDates returnObject = null;
      if (dates == null || dates.getSeasonId() == 0) {
         return returnObject;
      }
      try {
         SeasonIHPDetail seasonIHPDetail = ihpDetailRepository.findOne(dates.getSeasonProgramId());
         if (seasonIHPDetail != null) {
            if (dates.getProgramStartDate() != null) {
               seasonIHPDetail.setStartDate(DateUtils.getMMddyyDateFromString(dates.getProgramStartDate()));
            }
            if (dates.getProgramEndDate() != null) {
               seasonIHPDetail.setEndDate(DateUtils.getMMddyyDateFromString(dates.getProgramEndDate()));
            }
            seasonIHPDetailRepository.saveAndFlush(seasonIHPDetail);
            returnObject = dates;
         } else {
            LOGGER.debug("update dates HSP_STP_IHP failed because no season program found for the id: " + dates.getSeasonProgramId());
         }
      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   /**
    * @param ihpProgramConfiguration
    * @return
    */
   public IHPProgramConfiguration updateIHPProgramConfiguration(IHPProgramConfiguration ihpProgramConfiguration) {
      IHPProgramConfiguration returnObject = null;
      try {
         SeasonIHPDetail seasonIHPDetail = ihpDetailRepository.findOne(ihpProgramConfiguration.getSeasonProgramId());
         if (seasonIHPDetail != null) {
            seasonIHPDetail.setMaxParticipants(ihpProgramConfiguration.getMaxNoOfParticipants());
            seasonIHPDetail.setApplicationDeadLineWeeks(Integer.valueOf(ihpProgramConfiguration.getApplicationCutOffPriorToProgStart() != null ? ihpProgramConfiguration
                  .getApplicationCutOffPriorToProgStart() : "0"));
            seasonIHPDetail.setLcHoldTime(ihpProgramConfiguration.getLcHoldTimeDays());
            seasonIHPDetail.setNumberOfLCToRequestHold(ihpProgramConfiguration.getNoOfLcCanRequestHold());
            seasonIHPDetail.setSplitPlacementPending(Integer.valueOf(ihpProgramConfiguration.getSplitPlacementInPending() != null ? ihpProgramConfiguration
                  .getSplitPlacementInPending() : "0"));
            seasonIHPDetail.setStopAcceptingApps(ihpProgramConfiguration.isStopAcceptingApplications() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            seasonIHPDetail.setStopAcceptingAppsStandardIHP(ihpProgramConfiguration.isStopAcceptingIhpStandardSettings() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            seasonIHPDetail.setStopAcceptingAppsVolunteerHomestay(ihpProgramConfiguration.isStopAcceptingVolunteerHomeStayApplications() ? CCIConstants.ACTIVE
                  : CCIConstants.INACTIVE);
            seasonIHPDetail.setStopAcceptingAppsLanguageBuddy(ihpProgramConfiguration.isStopAcceptingLanguageBuddyApplications() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            seasonIHPDetail.setStopAcceptingAppsHolidayHomestay(ihpProgramConfiguration.isStopAcceptingHolidayHomeStayApplications() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            seasonIHPDetail.setStopAcceptingAppsHighSchoolVisits(ihpProgramConfiguration.isStopAcceptingHighSchoolApplications() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            seasonIHPDetail.setStopAcceptingAppsByGender(ihpProgramConfiguration.isStopAcceptingApplicationByGender() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
            if (ihpProgramConfiguration.getGenderId() > 0
                  && (ihpProgramConfiguration.getGenderId() == CCIConstants.MALE || ihpProgramConfiguration.getGenderId() == CCIConstants.FEMALE)) {
               LookupGender gender = genderRepository.findOne(ihpProgramConfiguration.getGenderId());
               seasonIHPDetail.setLookupGender(gender);
            } else {
               LookupGender gender = genderRepository.findOne(CCIConstants.GENDER_UNDEFINED);// gender
                                                                                             // id
                                                                                             // 3
                                                                                             // is
                                                                                             // undefined
               seasonIHPDetail.setLookupGender(gender);
            }
            if (ihpProgramConfiguration.getStopAcceptingApplicationByRegion() != null) {
               List<IHPApplicationByRegion> ihpApplicationByRegions = ihpProgramConfiguration.getStopAcceptingApplicationByRegion();
               List<SeasonIHPDetailsRegionApplication> existingList = seasonIHPDetailsRegionApplicationRepository.findBySeasonIHPId(seasonIHPDetail.getSeasonIHPDetailsId());
               List<SeasonIHPDetailsRegionApplication> seasonIhpdetailsRegionApplications = new ArrayList<SeasonIHPDetailsRegionApplication>();
               for (SeasonIHPDetailsRegionApplication application : existingList) {
                  for (IHPApplicationByRegion applicationByRegion : ihpApplicationByRegions) {
                     if (application.getRegionIhp().getRegionIHPId() == applicationByRegion.getApplicationRegionId()) {
                        application.setStopAcceptingApps(applicationByRegion.isAcceptApplicationFlag() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
                        seasonIhpdetailsRegionApplications.add(application);
                     }
                  }
               }
               seasonIHPDetailsRegionApplicationRepository.save(seasonIhpdetailsRegionApplications);
            }
            seasonIHPDetailRepository.saveAndFlush(seasonIHPDetail);
            returnObject = getIHPConfiguration(seasonIHPDetail.getSeasonIHPDetailsId());
         } else {
            LOGGER.debug("update ihpProgramConfiguration HSP_STP_IHP failed because no season program found for the id: " + ihpProgramConfiguration.getSeasonProgramId());
         }

      } catch (CcighgoException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return returnObject;
   }

   /**
    * @param seasonHspStpIhpDetails
    * @param season
    */
   private void updateIHPDocs(SeasonHspStpIhpDetails seasonHspStpIhpDetails, Season season) {
      List<SeasonProgramDocument> seasonProgramDocuments = seasonProgramDocumentRepository.findAllProgramDocumentsBySeasonIdAndDepartmentProgramId(season.getSeasonId(),
            CCIConstants.HSP_STP_IHP_ID);
      seasonProgramDocumentRepository.delete(seasonProgramDocuments);
      List<SeasonProgramDocument> newDocList = new ArrayList<SeasonProgramDocument>();
      for (IHPDocuments ihpDocument : seasonHspStpIhpDetails.getIhpDocuments()) {
         SeasonProgramDocument sprgDoc = new SeasonProgramDocument();
         DocumentInformation documentInformation = new DocumentInformation();
         if (ihpDocument.getFileName() != null) {
            documentInformation.setFileName(ihpDocument.getFileName());
         }
         if (ihpDocument.getDocName() != null) {
            documentInformation.setDocumentName(ihpDocument.getDocName());
         } else {
            LOGGER.debug("update HSP_STP_IHP documents failed because document name cannot be null: " + ihpDocument.getSeasonProgramId());
         }
         if (ihpDocument.getDocUrl() != null) {
            documentInformation.setUrl(ihpDocument.getDocUrl());
         } else {
            LOGGER.debug("update HSP_STP_IHP documents failed because document url cannot be null:" + ihpDocument.getSeasonProgramId());
         }
         if (ihpDocument.getDocType() != null) {
            DocumentTypeDocumentCategoryProcess categoryProcess = documentTypeDocumentCategoryProcessRepository.findByDocumentType(ihpDocument.getDocType());
            if (categoryProcess != null) {
               documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(ihpDocument.getDocType()));
            } else {
               LOGGER.debug("update HSP_STP_IHP documents failed because document type selected is not found in system: " + ihpDocument.getDocType());
            }
         }
         documentInformation.setCreatedBy(seasonHspStpIhpDetails.getLoginId());
         documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         documentInformation.setModifiedBy(seasonHspStpIhpDetails.getLoginId());
         documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         documentInformation = documentInformationRepository.saveAndFlush(documentInformation);
         sprgDoc.setActive(CCIConstants.ACTIVE);
         sprgDoc.setSeason(season);
         sprgDoc.setDepartmentProgram(departmentProgramRepository.findOne(CCIConstants.HSP_STP_IHP_ID));
         sprgDoc.setDocumentInformation(documentInformation);
         sprgDoc.setCreatedBy(seasonHspStpIhpDetails.getLoginId());
         sprgDoc.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         sprgDoc.setModifiedBy(seasonHspStpIhpDetails.getLoginId());
         sprgDoc.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         newDocList.add(sprgDoc);
      }
      seasonProgramDocumentRepository.save(newDocList);
   }

   /**
    * @param seasonHspStpIhpDetails
    * @param season
    */
   private void updateIHPNotes(SeasonHspStpIhpDetails seasonHspStpIhpDetails, Season season) {
      List<SeasonProgramNote> programNotes = seasonProgramNotesRepository.findAllProgramNotesBySeasonIdAndDepartmentProgramId(season.getSeasonId(), CCIConstants.HSP_STP_IHP_ID);
      seasonProgramNotesRepository.delete(programNotes);
      List<SeasonProgramNote> updatedNotes = new ArrayList<SeasonProgramNote>();
      for (IHPNotes ihpNote : seasonHspStpIhpDetails.getIhpNotes()) {
         SeasonProgramNote sprNote = new SeasonProgramNote();
         sprNote.setSeason(season);
         sprNote.setProgramNote(ihpNote.getNoteValue());
         sprNote.setDepartmentProgram(departmentProgramRepository.findOne(CCIConstants.HSP_STP_IHP_ID));
         sprNote.setCreatedBy(seasonHspStpIhpDetails.getLoginId());
         sprNote.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         sprNote.setModifiedBy(seasonHspStpIhpDetails.getLoginId());
         sprNote.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         updatedNotes.add(sprNote);
      }
      seasonProgramNotesRepository.save(updatedNotes);
   }

}
