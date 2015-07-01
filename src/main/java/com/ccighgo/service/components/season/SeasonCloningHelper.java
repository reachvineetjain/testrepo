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

import com.ccighgo.db.entities.LookupDepartment;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonF1Detail;
import com.ccighgo.db.entities.SeasonHSPAllocation;
import com.ccighgo.db.entities.SeasonHSPConfiguration;
import com.ccighgo.db.entities.SeasonJ1Detail;
import com.ccighgo.jpa.repositories.SeasonStatusRepository;
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
      season.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
      season.setModifiedBy(1);
      season.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
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
            allocation.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
            allocation.setModifiedBy(1);
            allocation.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
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
      seasonHSPConfiguration.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
      seasonHSPConfiguration.setModifiedBy(1);
      seasonHSPConfiguration.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
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
         seasonJ1Detail.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonJ1Detail.setModifiedBy(1);
         seasonJ1Detail.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
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
         seasonF1Detail.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonF1Detail.setModifiedBy(1);
         seasonF1Detail.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      }
      return seasonF1Detail;
   }

}
