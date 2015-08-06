/**
 * 
 */
package com.ccighgo.service.components.season;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.DocumentInformation;
import com.ccighgo.db.entities.LookupDepartment;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonCAPDetail;
import com.ccighgo.db.entities.SeasonDepartmentDocument;
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
import com.ccighgo.jpa.repositories.DocumentInformationRepository;
import com.ccighgo.jpa.repositories.SeasonStatusRepository;
import com.ccighgo.service.components.fileutils.FileUtilInterface;
import com.ccighgo.service.transport.season.beans.cloneseason.CloneSeason;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;

/**
 * @author ravi
 *
 */
@Component
public class SeasonCloningHelper {

   private Logger LOGGER = LoggerFactory.getLogger(SeasonCloningHelper.class);

   @Autowired
   SeasonStatusRepository seasonStatusRepository;
   @Autowired
   DocumentInformationRepository documentInformationRepository;
   @Autowired
   FileUtilInterface fileUtilInterface;

   /**
    * Method returns deep copy of an existing season
    * 
    * @param cloneSeason
    * @param existingSeason
    * @param department
    * @return cloned season object
    */
   public Season cloneHighLevelSeason(CloneSeason cloneSeason, Season existingSeason, LookupDepartment department) {
      Season season = new Season();
      season.setSeasonName(cloneSeason.getNewCloneSeasonName());
      season.setSeasonFullName(cloneSeason.getNewCloneSeasonName());
      season.setClonedSeasonName(existingSeason.getSeasonName());
      season.setLookupDepartment(department);
      season.setSeasonStatus(seasonStatusRepository.findOne(CCIConstants.DRAFT_STATUS_NO));
      season.setCreatedBy(1);
      season.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      season.setModifiedBy(1);
      season.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      return season;
   }

   /**
    * @param clonedHSPSeason
    * @param seasonHspallocations
    * @return
    */
   public List<SeasonHSPAllocation> cloneHSPAllocations(Season clonedHSPSeason, List<SeasonHSPAllocation> seasonHspallocations) {
      List<SeasonHSPAllocation> seasonHspallocationNewList = null;
      if (seasonHspallocations != null) {
         seasonHspallocationNewList = new ArrayList<SeasonHSPAllocation>();
         for (SeasonHSPAllocation hspAllocation : seasonHspallocations) {
            SeasonHSPAllocation allocation = new SeasonHSPAllocation();
            allocation.setDepartmentProgramOption(hspAllocation.getDepartmentProgramOption());
            allocation.setMaxGuaranteedPax(hspAllocation.getMaxGuaranteedPax());
            allocation.setMaxUnguaranteedPax(hspAllocation.getMaxUnguaranteedPax());
            allocation.setSeason(clonedHSPSeason);
            allocation.setCreatedBy(1);
            allocation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            allocation.setModifiedBy(1);
            allocation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonHspallocationNewList.add(allocation);
         }
      }
      return seasonHspallocationNewList;
   }

   /**
    * @param cloneSeason
    * @param clonedHSPSeason
    * @return
    */
   public SeasonHSPConfiguration cloneHSPConfiguration(CloneSeason cloneSeason, Season clonedHSPSeason) {
      SeasonHSPConfiguration seasonHSPConfiguration = new SeasonHSPConfiguration();
      seasonHSPConfiguration.setSeason(clonedHSPSeason);
      seasonHSPConfiguration.setSeasonStartDate(DateUtils.getMMddyyDateFromString(cloneSeason.getCloneSeasonStartDate()));
      seasonHSPConfiguration.setSeasonEndDate(DateUtils.getMMddyyDateFromString(cloneSeason.getCloneSeasonEndDate()));
      seasonHSPConfiguration.setCreatedBy(1);
      seasonHSPConfiguration.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      seasonHSPConfiguration.setModifiedBy(1);
      seasonHSPConfiguration.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      return seasonHSPConfiguration;
   }

   /**
    * @param existingSeason
    * @param clonedHSPSeason
    * @return
    */
   public SeasonJ1Detail cloneHSPJ1seasonProgram(Season existingSeason, Season clonedHSPSeason) {
      SeasonJ1Detail seasonJ1Detail = null;
      if (existingSeason.getSeasonJ1details() != null && existingSeason.getSeasonJ1details().size() > 0) {
         seasonJ1Detail = new SeasonJ1Detail();
         seasonJ1Detail.setSeason(clonedHSPSeason);
         seasonJ1Detail.setSeasonStatus(clonedHSPSeason.getSeasonStatus());
         seasonJ1Detail.setProgramName(clonedHSPSeason.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.HSP_J1_HS);
         seasonJ1Detail.setFieldStaffAgreement(existingSeason.getSeasonJ1details().get(0).getFieldStaffAgreement());
         seasonJ1Detail.setPaymentSchedule(existingSeason.getSeasonJ1details().get(0).getPaymentSchedule());
         seasonJ1Detail.setActiveFullYearJanProgram(existingSeason.getSeasonJ1details().get(0).getActiveFullYearJanProgram());
         seasonJ1Detail.setAugFullYearStartDate(existingSeason.getSeasonJ1details().get(0).getAugFullYearStartDate());
         seasonJ1Detail.setAugFullYearEndDate(existingSeason.getSeasonJ1details().get(0).getAugFullYearEndDate());
         seasonJ1Detail.setAugFullYearAppDeadlineDate(existingSeason.getSeasonJ1details().get(0).getAugFullYearAppDeadlineDate());
         seasonJ1Detail.setFieldStaffHoldLength(existingSeason.getSeasonJ1details().get(0).getFieldStaffHoldLength());
         seasonJ1Detail.setFirstSemStartDate(existingSeason.getSeasonJ1details().get(0).getFirstSemStartDate());
         seasonJ1Detail.setFirstSemEndDate(existingSeason.getSeasonJ1details().get(0).getFirstSemEndDate());
         seasonJ1Detail.setFirstSemAppDeadlineDate(existingSeason.getSeasonJ1details().get(0).getFirstSemAppDeadlineDate());
         seasonJ1Detail.setFirstSemEarliestBirthDate(existingSeason.getSeasonJ1details().get(0).getFirstSemEarliestBirthDate());
         seasonJ1Detail.setFirstSemLatestBirthDate(existingSeason.getSeasonJ1details().get(0).getFirstSemLatestBirthDate());
         seasonJ1Detail.setHfInquiryDate(existingSeason.getSeasonJ1details().get(0).getHfInquiryDate());
         seasonJ1Detail.setHfReferences(existingSeason.getSeasonJ1details().get(0).getHfReferences());
         seasonJ1Detail.setHoursBeforeHoldExpirationWarning(existingSeason.getSeasonJ1details().get(0).getHoursBeforeHoldExpirationWarning());
         seasonJ1Detail.setJanFullYearAppDeadlineDate(existingSeason.getSeasonJ1details().get(0).getJanFullYearAppDeadlineDate());
         seasonJ1Detail.setJanFullYearEndDate(existingSeason.getSeasonJ1details().get(0).getJanFullYearEndDate());
         seasonJ1Detail.setJanFullYearStartDate(existingSeason.getSeasonJ1details().get(0).getJanFullYearStartDate());
         seasonJ1Detail.setSecondSemAppDeadlineDate(existingSeason.getSeasonJ1details().get(0).getSecondSemAppDeadlineDate());
         seasonJ1Detail.setSecondSemEarliestBirthDate(existingSeason.getSeasonJ1details().get(0).getSecondSemEarliestBirthDate());
         seasonJ1Detail.setSecondSemEndDate(existingSeason.getSeasonJ1details().get(0).getSecondSemEndDate());
         seasonJ1Detail.setSecondSemLatestBirthDate(existingSeason.getSeasonJ1details().get(0).getSecondSemLatestBirthDate());
         seasonJ1Detail.setSecondSemStartDate(existingSeason.getSeasonJ1details().get(0).getSecondSemStartDate());
         seasonJ1Detail.setShowAugFullYearToNewHF(existingSeason.getSeasonJ1details().get(0).getShowAugFullYearToNewHF());
         seasonJ1Detail.setShowFirstSemToNewHF(existingSeason.getSeasonJ1details().get(0).getShowFirstSemToNewHF());
         seasonJ1Detail.setShowGuaranteed(existingSeason.getSeasonJ1details().get(0).getShowGuaranteed());
         seasonJ1Detail.setShowJanFullYearToNewHF(existingSeason.getSeasonJ1details().get(0).getShowJanFullYearToNewHF());
         seasonJ1Detail.setShowSeasonToCurrentHF(existingSeason.getSeasonJ1details().get(0).getShowSeasonToCurrentHF());
         seasonJ1Detail.setShowSecondSemToNewHF(existingSeason.getSeasonJ1details().get(0).getShowSecondSemToNewHF());
         seasonJ1Detail.setShowSpecialRequestStudent(existingSeason.getSeasonJ1details().get(0).getShowSpecialRequestStudent());
         seasonJ1Detail.setShowUnguaranteed(existingSeason.getSeasonJ1details().get(0).getShowUnguaranteed());
         seasonJ1Detail.setShowWelcomeFamily(existingSeason.getSeasonJ1details().get(0).getShowWelcomeFamily());
         seasonJ1Detail.setCreatedBy(1);
         seasonJ1Detail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonJ1Detail.setModifiedBy(1);
         seasonJ1Detail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      }
      return seasonJ1Detail;
   }

   /**
    * @param existingSeason
    * @param clonedHSPSeason
    * @return
    */
   public SeasonF1Detail cloneHSPF1SeasonProgram(Season existingSeason, Season clonedHSPSeason) {
      SeasonF1Detail seasonF1Detail = null;
      if (existingSeason.getSeasonF1details() != null && existingSeason.getSeasonF1details().size() > 0) {
         seasonF1Detail = new SeasonF1Detail();
         seasonF1Detail.setSeason(clonedHSPSeason);
         seasonF1Detail.setSeasonStatus(clonedHSPSeason.getSeasonStatus());
         seasonF1Detail.setProgramName(clonedHSPSeason.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.HSP_F1);
         seasonF1Detail.setFieldStaffAgreement(existingSeason.getSeasonF1details().get(0).getFieldStaffAgreement());
         seasonF1Detail.setPaymentSchedule(existingSeason.getSeasonF1details().get(0).getPaymentSchedule());
         seasonF1Detail.setActiveFullYearJanProgram(existingSeason.getSeasonF1details().get(0).getActiveFullYearJanProgram());
         seasonF1Detail.setAllowFieldStaffToStartRenewalProcess(existingSeason.getSeasonF1details().get(0).getAllowFieldStaffToStartRenewalProcess());
         seasonF1Detail.setAugFullYearAppDeadlineDate(existingSeason.getSeasonF1details().get(0).getAugFullYearAppDeadlineDate());
         seasonF1Detail.setAugFullYearEndDate(existingSeason.getSeasonF1details().get(0).getAugFullYearEndDate());
         seasonF1Detail.setAugFullYearStartDate(existingSeason.getSeasonF1details().get(0).getAugFullYearStartDate());
         seasonF1Detail.setFirstSemAppDeadlineDate(existingSeason.getSeasonF1details().get(0).getFirstSemAppDeadlineDate());
         seasonF1Detail.setFirstSemEarliestBirthDate(existingSeason.getSeasonF1details().get(0).getFirstSemEarliestBirthDate());
         seasonF1Detail.setFirstSemEndDate(existingSeason.getSeasonF1details().get(0).getFirstSemEndDate());
         seasonF1Detail.setFirstSemLatestBirthDate(existingSeason.getSeasonF1details().get(0).getFirstSemLatestBirthDate());
         seasonF1Detail.setFirstSemStartDate(existingSeason.getSeasonF1details().get(0).getFirstSemStartDate());
         seasonF1Detail.setGreenHeartMargin(existingSeason.getSeasonF1details().get(0).getGreenHeartMargin());
         seasonF1Detail.setHfInquiryDate(existingSeason.getSeasonF1details().get(0).getHfInquiryDate());
         seasonF1Detail.setHfReferences(existingSeason.getSeasonF1details().get(0).getHfReferences());
         seasonF1Detail.setJanFullYearAppDeadlineDate(existingSeason.getSeasonF1details().get(0).getJanFullYearAppDeadlineDate());
         seasonF1Detail.setJanFullYearEndDate(existingSeason.getSeasonF1details().get(0).getJanFullYearEndDate());
         seasonF1Detail.setJanFullYearStartDate(existingSeason.getSeasonF1details().get(0).getJanFullYearStartDate());
         seasonF1Detail.setSecondSemAppDeadlineDate(existingSeason.getSeasonF1details().get(0).getSecondSemAppDeadlineDate());
         seasonF1Detail.setSecondSemEarliestBirthDate(existingSeason.getSeasonF1details().get(0).getSecondSemEarliestBirthDate());
         seasonF1Detail.setSecondSemEndDate(existingSeason.getSeasonF1details().get(0).getSecondSemEndDate());
         seasonF1Detail.setSecondSemLatestBirthDate(existingSeason.getSeasonF1details().get(0).getSecondSemLatestBirthDate());
         seasonF1Detail.setSecondSemStartDate(existingSeason.getSeasonF1details().get(0).getSecondSemStartDate());
         seasonF1Detail.setShowAugFullYearToNewHF(existingSeason.getSeasonF1details().get(0).getShowAugFullYearToNewHF());
         seasonF1Detail.setShowFirstSemToNewHF(existingSeason.getSeasonF1details().get(0).getShowFirstSemToNewHF());
         seasonF1Detail.setShowJanFullYearToNewHF(existingSeason.getSeasonF1details().get(0).getShowJanFullYearToNewHF());
         seasonF1Detail.setShowSeasonToCurrentHF(existingSeason.getSeasonF1details().get(0).getShowSeasonToCurrentHF());
         seasonF1Detail.setShowSecSemToNewHF(existingSeason.getSeasonF1details().get(0).getShowSecSemToNewHF());
         seasonF1Detail.setShowSpecialRequestStudent(existingSeason.getSeasonF1details().get(0).getShowSpecialRequestStudent());
         seasonF1Detail.setShowWelcomeFamily(existingSeason.getSeasonF1details().get(0).getShowWelcomeFamily());
         seasonF1Detail.setCreatedBy(1);
         seasonF1Detail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonF1Detail.setModifiedBy(1);
         seasonF1Detail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      }
      return seasonF1Detail;
   }

   public SeasonIHPDetail cloneHSPIHPProgram(Season existingSeason, Season clonedHSPSeason, CloneSeason cloneSeason) {
      SeasonIHPDetail seasonIHPDetail = null;
      if (existingSeason.getSeasonIhpdetails() != null && existingSeason.getSeasonIhpdetails().size() > 0) {
         seasonIHPDetail = new SeasonIHPDetail();
         seasonIHPDetail.setSeason(clonedHSPSeason);
         seasonIHPDetail.setSeasonStatus(clonedHSPSeason.getSeasonStatus());
         seasonIHPDetail.setProgramName(clonedHSPSeason.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.HSP_STP_IHP);
         seasonIHPDetail.setStartDate(DateUtils.getMMddyyDateFromString(cloneSeason.getCloneSeasonStartDate()));
         seasonIHPDetail.setEndDate(DateUtils.getMMddyyDateFromString(cloneSeason.getCloneSeasonEndDate()));
         seasonIHPDetail.setMaxParticipants(existingSeason.getSeasonIhpdetails().get(0).getMaxParticipants());
         seasonIHPDetail.setLcHoldTime(existingSeason.getSeasonIhpdetails().get(0).getLcHoldTime());
         seasonIHPDetail.setNumberOfLCToRequestHold(existingSeason.getSeasonIhpdetails().get(0).getNumberOfLCToRequestHold());
         seasonIHPDetail.setSplitPlacementPending(existingSeason.getSeasonIhpdetails().get(0).getSplitPlacementPending());
         seasonIHPDetail.setStopAcceptingApps(existingSeason.getSeasonIhpdetails().get(0).getStopAcceptingApps());
         seasonIHPDetail.setLookupGender(existingSeason.getSeasonIhpdetails().get(0).getLookupGender());
         seasonIHPDetail.setApplicationDeadLineWeeks(existingSeason.getSeasonIhpdetails().get(0).getApplicationDeadLineWeeks());
         seasonIHPDetail.setStopAcceptingAppsStandardIHP(existingSeason.getSeasonIhpdetails().get(0).getStopAcceptingAppsStandardIHP());
         seasonIHPDetail.setStopAcceptingAppsVolunteerHomestay(existingSeason.getSeasonIhpdetails().get(0).getStopAcceptingAppsVolunteerHomestay());
         seasonIHPDetail.setStopAcceptingAppsLanguageBuddy(existingSeason.getSeasonIhpdetails().get(0).getStopAcceptingAppsLanguageBuddy());
         seasonIHPDetail.setStopAcceptingAppsHolidayHomestay(existingSeason.getSeasonIhpdetails().get(0).getStopAcceptingAppsHolidayHomestay());
         seasonIHPDetail.setStopAcceptingAppsHighSchoolVisits(existingSeason.getSeasonIhpdetails().get(0).getStopAcceptingAppsHighSchoolVisits());
         seasonIHPDetail.setCreatedBy(1);
         seasonIHPDetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonIHPDetail.setModifiedBy(1);
         seasonIHPDetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      }
      return seasonIHPDetail;
   };

   /**
    * @param clonedWPSeason
    * @param seasonWPAllocations
    * @return
    */
   public List<SeasonWPAllocation> cloneWPAllocations(Season clonedWPSeason, List<SeasonWPAllocation> seasonWPAllocations) {
      List<SeasonWPAllocation> seasonWPAallocationCloneList;
      seasonWPAallocationCloneList = new ArrayList<SeasonWPAllocation>();
      for (SeasonWPAllocation allocation : seasonWPAllocations) {
         SeasonWPAllocation wpAllocation = new SeasonWPAllocation();
         wpAllocation.setDepartmentProgramOption(allocation.getDepartmentProgramOption());
         wpAllocation.setMaxPax(allocation.getMaxPax());
         wpAllocation.setSeason(clonedWPSeason);
         wpAllocation.setCreatedBy(1);
         wpAllocation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         wpAllocation.setModifiedBy(1);
         wpAllocation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWPAallocationCloneList.add(wpAllocation);
      }
      return seasonWPAallocationCloneList;
   }

   /**
    * @param cloneSeason
    * @param clonedWPSeason
    * @return
    */
   public SeasonWPConfiguration cloneWPConfigurations(CloneSeason cloneSeason, Season clonedWPSeason) {
      SeasonWPConfiguration seasonWPConfiguration = new SeasonWPConfiguration();
      seasonWPConfiguration.setSeason(clonedWPSeason);
      seasonWPConfiguration.setSeasonStartDate(DateUtils.getMMddyyDateFromString(cloneSeason.getCloneSeasonStartDate()));
      seasonWPConfiguration.setSeasonEndDate(DateUtils.getMMddyyDateFromString(cloneSeason.getCloneSeasonEndDate()));
      seasonWPConfiguration.setCreatedBy(1);
      seasonWPConfiguration.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      seasonWPConfiguration.setModifiedBy(1);
      seasonWPConfiguration.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      return seasonWPConfiguration;
   }

   /**
    * @param existingSeason
    * @param clonedWPSeason
    * @return
    */
   public SeasonWnTSpringDetail cloneWPSpringProgram(Season existingSeason, Season clonedWPSeason) {
      SeasonWnTSpringDetail seasonWnTSpringDetail = null;
      if (existingSeason.getSeasonWnTspringDetails() != null && existingSeason.getSeasonWnTspringDetails().size() > 0) {
         seasonWnTSpringDetail = new SeasonWnTSpringDetail();
         seasonWnTSpringDetail.setSeason(clonedWPSeason);
         seasonWnTSpringDetail.setSeasonStatus(clonedWPSeason.getSeasonStatus());
         seasonWnTSpringDetail.setProgramName(clonedWPSeason.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.WP_WT_SPRING);
         seasonWnTSpringDetail.setApplicationDeadlineDate(existingSeason.getSeasonWnTspringDetails().get(0).getApplicationDeadlineDate());
         seasonWnTSpringDetail.setStartDate(existingSeason.getSeasonWnTspringDetails().get(0).getStartDate());
         seasonWnTSpringDetail.setEndDate(existingSeason.getSeasonWnTspringDetails().get(0).getEndDate());
         seasonWnTSpringDetail.setIsJobBoardOpen(existingSeason.getSeasonWnTspringDetails().get(0).getIsJobBoardOpen());
         seasonWnTSpringDetail.setMaxPendingJobApps(existingSeason.getSeasonWnTspringDetails().get(0).getMaxPendingJobApps());
         seasonWnTSpringDetail.setCreatedBy(1);
         seasonWnTSpringDetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWnTSpringDetail.setModifiedBy(1);
         seasonWnTSpringDetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      }

      return seasonWnTSpringDetail;
   }

   /**
    * @param existingSeason
    * @param clonedWPSeason
    * @return
    */
   public SeasonWnTSummerDetail cloneWPSummerProgram(Season existingSeason, Season clonedWPSeason) {
      SeasonWnTSummerDetail seasonWnTSummerDetail = null;
      if (existingSeason.getSeasonWnTsummerDetails() != null && existingSeason.getSeasonWnTsummerDetails().size() > 0) {
         seasonWnTSummerDetail = new SeasonWnTSummerDetail();
         seasonWnTSummerDetail.setSeason(clonedWPSeason);
         seasonWnTSummerDetail.setSeasonStatus(clonedWPSeason.getSeasonStatus());
         seasonWnTSummerDetail.setProgramName(clonedWPSeason.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.WP_WT_SUMMER);
         seasonWnTSummerDetail.setApplicationDeadlineDate(existingSeason.getSeasonWnTsummerDetails().get(0).getApplicationDeadlineDate());
         seasonWnTSummerDetail.setStartDate(existingSeason.getSeasonWnTsummerDetails().get(0).getStartDate());
         seasonWnTSummerDetail.setEndDate(existingSeason.getSeasonWnTsummerDetails().get(0).getEndDate());
         seasonWnTSummerDetail.setIsJobBoardOpen(existingSeason.getSeasonWnTsummerDetails().get(0).getIsJobBoardOpen());
         seasonWnTSummerDetail.setMaxPendingJobApps(existingSeason.getSeasonWnTsummerDetails().get(0).getMaxPendingJobApps());
         seasonWnTSummerDetail.setCreatedBy(1);
         seasonWnTSummerDetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWnTSummerDetail.setModifiedBy(1);
         seasonWnTSummerDetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      }
      return seasonWnTSummerDetail;
   }

   /**
    * @param existingSeason
    * @param clonedWPSeason
    * @return
    */
   public SeasonWnTWinterDetail cloneWPWinterProgram(Season existingSeason, Season clonedWPSeason) {
      SeasonWnTWinterDetail seasonWnTWinterDetail = null;
      if (existingSeason.getSeasonWnTwinterDetails() != null && existingSeason.getSeasonWnTwinterDetails().size() > 0) {
         seasonWnTWinterDetail = new SeasonWnTWinterDetail();
         seasonWnTWinterDetail.setSeason(clonedWPSeason);
         seasonWnTWinterDetail.setSeasonStatus(clonedWPSeason.getSeasonStatus());
         seasonWnTWinterDetail.setProgramName(clonedWPSeason.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.WP_WT_WINTER);
         seasonWnTWinterDetail.setApplicationDeadlineDate(existingSeason.getSeasonWnTwinterDetails().get(0).getApplicationDeadlineDate());
         seasonWnTWinterDetail.setStartDate(existingSeason.getSeasonWnTwinterDetails().get(0).getStartDate());
         seasonWnTWinterDetail.setEndDate(existingSeason.getSeasonWnTwinterDetails().get(0).getEndDate());
         seasonWnTWinterDetail.setIsJobBoardOpen(existingSeason.getSeasonWnTwinterDetails().get(0).getIsJobBoardOpen());
         seasonWnTWinterDetail.setMaxPendingJobApps(existingSeason.getSeasonWnTwinterDetails().get(0).getMaxPendingJobApps());
         seasonWnTWinterDetail.setCreatedBy(1);
         seasonWnTWinterDetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWnTWinterDetail.setModifiedBy(1);
         seasonWnTWinterDetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      }
      return seasonWnTWinterDetail;
   }

   /**
    * @param existingSeason
    * @param clonedWPSeason
    * @return
    */
   public SeasonCAPDetail cloneWPCapProgram(Season existingSeason, Season clonedWPSeason) {
      SeasonCAPDetail seasonCAPDetail = null;
      if (existingSeason.getSeasonCapdetails() != null && existingSeason.getSeasonCapdetails().size() > 0) {
         seasonCAPDetail = new SeasonCAPDetail();
         seasonCAPDetail.setSeason(clonedWPSeason);
         seasonCAPDetail.setSeasonStatus(clonedWPSeason.getSeasonStatus());
         seasonCAPDetail.setProgramName(clonedWPSeason.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.WP_WT_CAP);
         seasonCAPDetail.setInternStartDate(existingSeason.getSeasonCapdetails().get(0).getInternStartDate());
         seasonCAPDetail.setInternEndDate(existingSeason.getSeasonCapdetails().get(0).getInternEndDate());
         seasonCAPDetail.setInternAppDeadlineDate(existingSeason.getSeasonCapdetails().get(0).getInternAppDeadlineDate());
         seasonCAPDetail.setTraineeAppDeadlineDate(existingSeason.getSeasonCapdetails().get(0).getTraineeAppDeadlineDate());
         seasonCAPDetail.setTraineeStartDate(existingSeason.getSeasonCapdetails().get(0).getTraineeStartDate());
         seasonCAPDetail.setTraineeEndDate(existingSeason.getSeasonCapdetails().get(0).getTraineeEndDate());
         seasonCAPDetail.setCreatedBy(1);
         seasonCAPDetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonCAPDetail.setModifiedBy(1);
         seasonCAPDetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      }
      return seasonCAPDetail;
   }

   /**
    * @param cloneSeason
    * @param clonedGHTSeason
    * @return
    */
   public SeasonGHTConfiguration cloneGHTConfiguration(CloneSeason cloneSeason, Season clonedGHTSeason) {
      SeasonGHTConfiguration seasonGHTConfiguration = new SeasonGHTConfiguration();
      seasonGHTConfiguration.setSeason(clonedGHTSeason);
      seasonGHTConfiguration.setSeasonStartDate(DateUtils.getMMddyyDateFromString(cloneSeason.getCloneSeasonStartDate()));
      seasonGHTConfiguration.setSeasonEndDate(DateUtils.getMMddyyDateFromString(cloneSeason.getCloneSeasonEndDate()));
      seasonGHTConfiguration.setCreatedBy(1);
      seasonGHTConfiguration.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      seasonGHTConfiguration.setModifiedBy(1);
      seasonGHTConfiguration.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      return seasonGHTConfiguration;
   }

   /**
    * @param existingSeason
    * @param clonedGHTSeason
    * @return
    */
   public SeasonHSADetail cloneGHTHSAProgram(Season existingSeason, Season clonedGHTSeason) {
      SeasonHSADetail seasonHSADetail = null;
      if (existingSeason.getSeasonHsadetails() != null && existingSeason.getSeasonHsadetails().size() > 0) {
         seasonHSADetail = new SeasonHSADetail();
         seasonHSADetail.setSeason(clonedGHTSeason);
         seasonHSADetail.setSeasonStatus(clonedGHTSeason.getSeasonStatus());
         seasonHSADetail.setProgramName(clonedGHTSeason.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.GHT_HS_ABRD);
         seasonHSADetail.setStartDate(existingSeason.getSeasonHsadetails().get(0).getStartDate());
         seasonHSADetail.setEndDate(existingSeason.getSeasonHsadetails().get(0).getEndDate());
         seasonHSADetail.setCreatedBy(1);
         seasonHSADetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonHSADetail.setModifiedBy(1);
         seasonHSADetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      }
      return seasonHSADetail;
   }

   /**
    * @param existingSeason
    * @param clonedGHTSeason
    * @return
    */
   public SeasonLSDetail cloneGHTLSProgram(Season existingSeason, Season clonedGHTSeason) {
      SeasonLSDetail seasonLSDetail = null;
      if (existingSeason.getSeasonLsdetails() != null && existingSeason.getSeasonLsdetails().size() > 0) {
         seasonLSDetail = new SeasonLSDetail();
         seasonLSDetail.setSeason(clonedGHTSeason);
         seasonLSDetail.setSeasonStatus(clonedGHTSeason.getSeasonStatus());
         seasonLSDetail.setProgramName(clonedGHTSeason.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.GHT_HS_ABRD);
         seasonLSDetail.setStartDate(existingSeason.getSeasonLsdetails().get(0).getStartDate());
         seasonLSDetail.setEndDate(existingSeason.getSeasonLsdetails().get(0).getEndDate());
         seasonLSDetail.setCreatedBy(1);
         seasonLSDetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonLSDetail.setModifiedBy(1);
         seasonLSDetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      }
      return seasonLSDetail;
   }

   /**
    * @param existingSeason
    * @param clonedGHTSeason
    * @return
    */
   public SeasonTADetail cloneGHTTAProgram(Season existingSeason, Season clonedGHTSeason) {
      SeasonTADetail seasonTADetail = null;
      if (existingSeason.getSeasonTadetails() != null && existingSeason.getSeasonTadetails().size() > 0) {
         seasonTADetail = new SeasonTADetail();
         seasonTADetail.setSeason(clonedGHTSeason);
         seasonTADetail.setSeasonStatus(clonedGHTSeason.getSeasonStatus());
         seasonTADetail.setProgramName(clonedGHTSeason.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.GHT_HS_ABRD);
         seasonTADetail.setStartDate(existingSeason.getSeasonTadetails().get(0).getStartDate());
         seasonTADetail.setEndDate(existingSeason.getSeasonTadetails().get(0).getEndDate());
         seasonTADetail.setCreatedBy(1);
         seasonTADetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonTADetail.setModifiedBy(1);
         seasonTADetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      }
      return seasonTADetail;
   }

   /**
    * @param existingSeason
    * @param clonedGHTSeason
    * @return
    */
   public SeasonVADetail cloneGHTVAProgram(Season existingSeason, Season clonedGHTSeason) {
      SeasonVADetail seasonVADetail = null;
      if (existingSeason.getSeasonVadetails() != null && existingSeason.getSeasonVadetails().size() > 0) {
         seasonVADetail = new SeasonVADetail();
         seasonVADetail.setSeason(clonedGHTSeason);
         seasonVADetail.setSeasonStatus(clonedGHTSeason.getSeasonStatus());
         seasonVADetail.setProgramName(clonedGHTSeason.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.GHT_HS_ABRD);
         seasonVADetail.setStartDate(existingSeason.getSeasonVadetails().get(0).getStartDate());
         seasonVADetail.setEndDate(existingSeason.getSeasonVadetails().get(0).getEndDate());
         seasonVADetail.setCreatedBy(1);
         seasonVADetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonVADetail.setModifiedBy(1);
         seasonVADetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      }
      return seasonVADetail;
   }

   /**
    * @param existingSeason
    * @param clonedGHTSeason
    * @return
    */
   public SeasonWADetail cloneGHTWAProgram(Season existingSeason, Season clonedGHTSeason) {
      SeasonWADetail seasonWADetail = null;
      if (existingSeason.getSeasonWadetails() != null && existingSeason.getSeasonWadetails().size() > 0) {
         seasonWADetail = new SeasonWADetail();
         seasonWADetail.setSeason(clonedGHTSeason);
         seasonWADetail.setSeasonStatus(clonedGHTSeason.getSeasonStatus());
         seasonWADetail.setProgramName(clonedGHTSeason.getSeasonName() + CCIConstants.HYPHEN_SPACE + CCIConstants.GHT_HS_ABRD);
         seasonWADetail.setStartDate(existingSeason.getSeasonWadetails().get(0).getStartDate());
         seasonWADetail.setEndDate(existingSeason.getSeasonWadetails().get(0).getEndDate());
         seasonWADetail.setCreatedBy(1);
         seasonWADetail.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         seasonWADetail.setModifiedBy(1);
         seasonWADetail.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      }
      return seasonWADetail;
   }

   /**
    * @param existingDoc
    * @param clonedSeason
    * @return
    */
   public SeasonDepartmentDocument getSeasonDepartmentDocument(SeasonDepartmentDocument existingDoc, Season clonedSeason) {
      SeasonDepartmentDocument document = new SeasonDepartmentDocument();
      document.setActive(existingDoc.getActive());
      document.setSeason(clonedSeason);
      document.setDocumentInformation(getDepartmentDocumentInformation(existingDoc));
      document.setCreatedBy(1);
      document.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      document.setModifiedBy(1);
      document.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      return document;
   }

   /**
    * @param doc
    * @return
    */
   public DocumentInformation getDepartmentDocumentInformation(com.ccighgo.db.entities.SeasonDepartmentDocument doc) {
      DocumentInformation documentInformation = new DocumentInformation();
      documentInformation.setActive(doc.getDocumentInformation().getActive());
      documentInformation.setDocumentName(doc.getDocumentInformation().getDocumentName() != null ? doc.getDocumentInformation().getDocumentName() : null);
      documentInformation.setFileName(doc.getDocumentInformation().getFileName() != null ? doc.getDocumentInformation().getFileName() : null);
      documentInformation.setDocumentTypeDocumentCategoryProcess(doc.getDocumentInformation().getDocumentTypeDocumentCategoryProcess());
     // documentInformation.setUrl(fileUtilInterface.cloneUploadedFile(doc.getDocumentInformation().getUrl()));
      documentInformation.setCreatedBy(1);
      documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      documentInformation.setModifiedBy(1);
      documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      documentInformation = documentInformationRepository.saveAndFlush(documentInformation);
      return documentInformation;
   }

   /**
    * @param existingDoc
    * @param clonedSeason
    * @return
    */
   public com.ccighgo.db.entities.SeasonProgramDocument getSeasonProgramDocument(com.ccighgo.db.entities.SeasonProgramDocument existingDoc, Season clonedSeason) {
      com.ccighgo.db.entities.SeasonProgramDocument sprgDoc = new com.ccighgo.db.entities.SeasonProgramDocument();
      sprgDoc.setActive(existingDoc.getActive());
      sprgDoc.setDepartmentProgram(existingDoc.getDepartmentProgram());
      sprgDoc.setSeason(clonedSeason);
      sprgDoc.setDocumentInformation(getProgramDocumentInformation(existingDoc));
      sprgDoc.setCreatedBy(1);
      sprgDoc.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      sprgDoc.setModifiedBy(1);
      sprgDoc.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      return sprgDoc;

   }

   /**
    * @param doc
    * @return
    */
   public DocumentInformation getProgramDocumentInformation(com.ccighgo.db.entities.SeasonProgramDocument doc) {
      DocumentInformation documentInformation = new DocumentInformation();
      documentInformation.setActive(doc.getDocumentInformation().getActive());
      documentInformation.setDocumentName(doc.getDocumentInformation().getDocumentName() != null ? doc.getDocumentInformation().getDocumentName() : null);
      documentInformation.setFileName(doc.getDocumentInformation().getFileName() != null ? doc.getDocumentInformation().getFileName() : null);
      documentInformation.setDocumentTypeDocumentCategoryProcess(doc.getDocumentInformation().getDocumentTypeDocumentCategoryProcess());
      //documentInformation.setUrl(fileUtilInterface.cloneUploadedFile(doc.getDocumentInformation().getUrl()));
      documentInformation.setCreatedBy(1);
      documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      documentInformation.setModifiedBy(1);
      documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      documentInformation = documentInformationRepository.saveAndFlush(documentInformation);
      return documentInformation;
   }

}
