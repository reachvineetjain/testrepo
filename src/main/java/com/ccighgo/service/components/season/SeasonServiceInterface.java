package com.ccighgo.service.components.season;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.season.beans.cloneseason.CloneSeason;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection1Base;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection2Dates;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.SeasonGHTDetails;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSAugStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSFieldSettings;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSJanStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSProgramAllocations;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.SeasonHspJ1HSDetails;
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
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.SeasonWPCAPDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPBasicDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPInternshipDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPTraineeDetails;

@Service
public interface SeasonServiceInterface {

   /**
    * @return
    */
   public SeasonsList getAllSeasons();

   /**
    * @param seasonBean
    * @return
    */
   public SeasonBean createSeason(SeasonBean seasonBean);

   /**
    * @param id
    * @return
    */
   public String deleteSeason(String id);

   /**
    * @param id
    * @return
    */
   public SeasonBean editSeason(String id);

   /**
    * @param id
    * @return
    */
   public SeasonBean viewSeason(String id);

   /**
    * @param seasonBean
    * @return
    */
   public SeasonBean updateSeason(SeasonBean seasonBean);

   /**
    * Returns list of season programs for a department
    * 
    * @param seasonId
    * @return
    */
   public SeasonPrograms getSeasonPrograms(String seasonId);

   /**
    * Method fetches list of available season status
    * 
    * @return SeasonStatuses
    */
   public SeasonStatuses getSeasonStatus();

   // HSP J1HS Season methods

   /**
    * Method returns complete details for HSP J1HS season program
    * 
    * @param seasonId
    * @return SeasonHspJ1HSDetails
    */
   public SeasonHspJ1HSDetails getHSPJ1HSSeasonDetails(String seasonId);

   /**
    * Gets the details of first section in J1HS for name and status
    * 
    * @param seasonId
    * @return J1HSBasicDetail
    */
   public J1HSBasicDetail getHSPJ1HSSeasonNameAndStatus(String seasonId);

   /**
    * Gets the details of January semester start
    * 
    * @param seasonId
    * @return J1HSJanStart
    */
   public J1HSJanStart getHSPJ1HSSeasonJanStartDetails(String seasonId);

   /**
    * gets details of August semester start
    * 
    * @param seasonId
    * @return J1HSAugStart
    */
   public J1HSAugStart getHSPJ1HSSeasonAugStartDetails(String seasonId);

   /**
    * Gets field setting details of J1HS season
    * 
    * @param seasonId
    * @return J1HSFieldSettings
    */
   public J1HSFieldSettings getHSPJ1HSSeasonFieldSettings(String seasonId);

   /**
    * Gets list of program allocation matrix for J1HS season
    * 
    * @param seasonId
    * @return J1HSProgramAllocations
    */
   public J1HSProgramAllocations getHSPJ1HSSeasonProgramAllocation(String seasonId);

   /**
    * Updates complete HSP J1HS details
    * 
    * @param seasonHspJ1HSDetails
    * @return SeasonHspJ1HSDetails
    */
   public SeasonHspJ1HSDetails updateHSPJ1HSSeasonDetails(SeasonHspJ1HSDetails seasonHspJ1HSDetails);

   /**
    * Updates HSP J1HS first section
    * 
    * @param j1hsBasicDetail
    * @return J1HSBasicDetail
    */
   public J1HSBasicDetail updateHSPJ1HSSeasonNameAndStatus(J1HSBasicDetail j1hsBasicDetail);

   /**
    * Updates J1HS January semester start section
    * 
    * @param j1hsJanStart
    * @return J1HSJanStart
    */ 
   public J1HSJanStart updateHSPJ1HSSeasonJanStartDetails(J1HSJanStart j1hsJanStart);

   /**
    * Updates J1HS August semester start section
    * 
    * @param j1hsAugStart
    * @return J1HSAugStart
    */
   public J1HSAugStart updateHSPJ1HSSeasonAugStartDetails(J1HSAugStart j1hsAugStart);

   /**
    * Updates J1HS field settings section
    * 
    * @param j1hsFieldSettings
    * @return J1HSFieldSettings
    */
   public J1HSFieldSettings updateHSPJ1HSSeasonFieldSettings(J1HSFieldSettings j1hsFieldSettings);

   /**
    * Updates HSP J1HS program allocations
    * 
    * @param j1hsProgramAllocations
    * @return J1HSProgramAllocations
    */
   public J1HSProgramAllocations updateHSPJ1HSSeasonProgramAllocation(J1HSProgramAllocations j1hsProgramAllocations);

   /**
    * @param seasonId
    * @return
    */
   public SeasonHSPF1Details getSeasonHSPF1Details(String seasonId);

   /**
    * @param seasonId
    * @return
    */
   public HSPF1BasicDetails getHSPF1NameAndStatus(String seasonId);

   /**
    * @param hspf1BasicDetails
    * @return
    */
   public HSPF1BasicDetails updateHSPF1NameAndStatus(HSPF1BasicDetails hspf1BasicDetails);

   /**
    * @param seasonId
    * @return
    */
   public HSPF1Accounting getHSPF1Accounting(String seasonId);

   /**
    * @param hspf1Accounting
    * @return
    */
   public HSPF1Accounting updateF1Accounting(HSPF1Accounting hspf1Accounting);

   /**
    * @param seasonId
    * @return
    */
   public HSPF1JanuaryStart2NdSemesterDetails getHSPF1JanuaryStart2NdSemesterDetails(String seasonId);

   /**
    * @param hspf1JanuaryStart2NdSemesterDetails
    * @return
    */
   public HSPF1JanuaryStart2NdSemesterDetails updateF1JanStart2NdSemesterDetails(HSPF1JanuaryStart2NdSemesterDetails hspf1JanuaryStart2NdSemesterDetails);

   /**
    * @param seasonId
    * @return
    */
   public HSPF1JanuaryStartFullYearDetail getHSPF1JanuaryStartFullYearDetails(String seasonId);

   /**
    * @param hspf1JanuaryStartFullYearDetail
    * @return
    */
   public HSPF1JanuaryStartFullYearDetail updateF1JanStartFullYearDetails(HSPF1JanuaryStartFullYearDetail hspf1JanuaryStartFullYearDetail);

   /**
    * @param seasonId
    * @return
    */
   public HSPF1AugustStart1StSemesterDetails getHSPF1AugustStart1StSemesterDetails(String seasonId);

   /**
    * @param hspf1AugustStart1StSemesterDetails
    * @return
    */
   public HSPF1AugustStart1StSemesterDetails updateF1AugStart1StSemesterDetails(HSPF1AugustStart1StSemesterDetails hspf1AugustStart1StSemesterDetails);

   /**
    * @param seasonId
    * @return
    */
   public HSPF1AugustStartFullYearDetails getHSPF1AugustStartFullYearDetails(String seasonId);

   /**
    * @param hspf1AugustStartFullYearDetails
    * @return
    */
   public HSPF1AugustStartFullYearDetails updateF1AugStartFullYearDetails(HSPF1AugustStartFullYearDetails hspf1AugustStartFullYearDetails);

   /**
    * @param seasonId
    * @return
    */
   public HSPF1FieldSettings getHSPF1FieldSettings(String seasonId);

   /**
    * @param hspf1FieldSettings
    * @return
    */
   public HSPF1FieldSettings updateF1FieldSettings(HSPF1FieldSettings hspf1FieldSettings);

   /**
    * @param seasonId
    * @return
    */
   public HSPF1ProgramAllocations getHSPF1ProgramAllocations(String seasonId);

   /**
    * @param hspf1ProgramAllocations
    * @return
    */
   public HSPF1ProgramAllocations updateF1ProgramAllocation(HSPF1ProgramAllocations hspf1ProgramAllocations);

   /**
    * @param seasonId
    * @return
    */
   public SeasonGHTDetails getGHTVASeasonDetails(String seasonId);

   /**
    * @param seasonId
    * @return
    */
   public GHTSection1Base getGHTVASeasonNameAndStatus(String seasonId);

   /**
    * @param seasonId
    * @return
    */
   public GHTSection2Dates getGHTVASeasonDateDetails(String seasonId);

   /**
    * @param seasonGHTDetails
    * @return
    */
   public SeasonGHTDetails updateGHTVASeasonDetails(SeasonGHTDetails seasonGHTDetails);

   /**
    * @param ghtSection1Base
    * @return
    */
   public GHTSection1Base updateGHTVASeasonNameAndStatus(GHTSection1Base ghtSection1Base);

   /**
    * @param ghtSection2Dates
    * @return
    */
   public GHTSection2Dates updateGHTVASeasonDateDetails(GHTSection2Dates ghtSection2Dates);

   /**
    * @param seasonId
    * @return
    */
   public SeasonGHTDetails getGHTWASeasonDetails(String seasonId);

   /**
    * @param seasonId
    * @return
    */
   public GHTSection1Base getGHTWASeasonNameAndStatus(String seasonId);

   /**
    * @param seasonId
    * @return
    */
   public GHTSection2Dates getGHTWASeasonDateDetails(String seasonId);

   /**
    * @param seasonHSPF1Details
    * @return
    */
   public SeasonHSPF1Details updateF1Details(SeasonHSPF1Details seasonHSPF1Details);

   /**
    * @param seasonGHTDetails
    * @return
    */
   public SeasonGHTDetails updateGHTWASeasonDetails(SeasonGHTDetails seasonGHTDetails);

   /**
    * @param ghtSection1Base
    * @return
    */
   public GHTSection1Base updateGHTWASeasonNameAndStatus(GHTSection1Base ghtSection1Base);

   /**
    * @param ghtSection2Dates
    * @return
    */
   public GHTSection2Dates updateGHTWASeasonDateDetails(GHTSection2Dates ghtSection2Dates);

   /**
    * @param seasonId
    * @return
    */
   public SeasonGHTDetails getGHTHSAbroad(String seasonId);

   /**
    * @param seasonGHTDetails
    * @return
    */
   public SeasonGHTDetails updateGHTHSAbroad(SeasonGHTDetails seasonGHTDetails);

   /**
    * @param seasonId
    * @return
    */
   public GHTSection1Base getGHTHSSection1BaseAbroad(String seasonId);

   /**
    * @param ghtSection1Base
    * @return
    */
   public GHTSection1Base updateGHTHSSection1BaseAbroad(GHTSection1Base ghtSection1Base);

   /**
    * @param seasonId
    * @return
    */
   public GHTSection2Dates getGHTHSSection2DatesAbroad(String seasonId);

   /**
    * @param ghtSection2Dates
    * @return
    */
   public GHTSection2Dates updateGHTHSSection2DatesAbroad(GHTSection2Dates ghtSection2Dates);

   /**
    * @param seasonId
    * @return
    */
   public SeasonGHTDetails getGHTLanguageSchool(String seasonId);

   /**
    * @param seasonGHTDetails
    * @return
    */
   public SeasonGHTDetails updateGHTLanguageSchool(SeasonGHTDetails seasonGHTDetails);

   /**
    * @param seasonId
    * @return
    */
   public GHTSection1Base getGHTLanguageSchoolSection1(String seasonId);

   /**
    * @param seasonGHTDetails
    * @return
    */
   public GHTSection1Base updateGHTLanguageSchoolSection1(GHTSection1Base seasonGHTDetails);

   /**
    * @param seasonId
    * @return
    */
   public GHTSection2Dates getGHTLanguageSchoolSection2Dates(String seasonId);

   /**
    * @param ghtSection2Dates
    * @return
    */
   public GHTSection2Dates updateGHTLanguageSchoolSection2Dates(GHTSection2Dates ghtSection2Dates);

   /**
    * @param seasonId
    * @return
    */
   public SeasonGHTDetails getGHTTeachAbroad(String seasonId);

   /**
    * @param seasonGHTDetails
    * @return
    */
   public SeasonGHTDetails updateGHTTeachAbroad(SeasonGHTDetails seasonGHTDetails);

   /**
    * @param seasonId
    * @return
    */
   public GHTSection1Base getGHTTeachAbroadSection1(String seasonId);

   /**
    * @param ghtSection1Base
    * @return
    */
   public GHTSection1Base updateGHTTeachAbroadSection1(GHTSection1Base ghtSection1Base);

   /**
    * @param seasonId
    * @return
    */
   public GHTSection2Dates getGHTTeachAbroadSection2Dates(String seasonId);

   /**
    * @param ghtSection2Dates
    * @return
    */
   public GHTSection2Dates updateGHTTeachAbroadSection2Dates(GHTSection2Dates ghtSection2Dates);

   /**
    * @param seasonId
    * @return
    */
   public SeasonWPCAPDetails getWPCAPDetails(String seasonId);

   /**
    * @param seasonWPCAPDetails
    * @return
    */
   public SeasonWPCAPDetails updateWPCAPDetails(SeasonWPCAPDetails seasonWPCAPDetails);

   /**
    * @param seasonId
    * @return
    */
   public WPCAPBasicDetails getWPCAPBasicDetails(String seasonId);

   /**
    * @param seasonWpcapBasicDetails
    * @return
    */
   public WPCAPBasicDetails updateWPCAPBasicDetails(WPCAPBasicDetails seasonWpcapBasicDetails);

   /**
    * @param seasonId
    * @return
    */
   public WPCAPInternshipDetails getWPCAPInternshipDetails(String seasonId);

   /**
    * @param wpcapInternshipDetails
    * @return
    */
   public WPCAPInternshipDetails updateWPCAPInternshipDetails(WPCAPInternshipDetails wpcapInternshipDetails);

   /**
    * @param seasonId
    * @return
    */
   public WPCAPTraineeDetails getWPCAPTraineeDetails(String seasonId);

   /**
    * @param wpcapTraineeDetails
    * @return
    */
   public WPCAPTraineeDetails updateWPCAPTraineeDetails(WPCAPTraineeDetails wpcapTraineeDetails);

   /**
    * @param seasonId
    * @return
    */
   public SeasonWPDetails getWPSumDetails(String seasonId);

   /**
    * @param seasonWPDetails
    * @return
    */
   public SeasonWPDetails updateWPSumDetails(SeasonWPDetails seasonWPDetails);

   /**
    * @param seasonId
    * @return
    */
   public WPBasicDetail getWPSumBaseDetails(String seasonId);

   /**
    * @param wpBasicDetail
    * @return
    */
   public WPBasicDetail updateWPSumBaseDetails(WPBasicDetail wpBasicDetail);

   /**
    * @param seasonId
    * @return
    */
   public WPSectionOne getWPSumSectionOneDetails(String seasonId);

   /**
    * @param wpSectionOne
    * @return
    */
   public WPSectionOne updateWPSumSectionOneDetails(WPSectionOne wpSectionOne);

   /**
    * @param seasonId
    * @return
    */
   public WPSectionOne getWPSumAllocationDetails(String seasonId);

   /**
    * @param wpSectionOne
    * @return
    */
   public WPSectionOne updateWPSumAllocationDetails(WPSectionOne wpSectionOne);

   /**
    * @param seasonId
    * @return
    */
   public SeasonWPDetails getWPSpringDetails(String seasonId);

   /**
    * @param seasonWPDetails
    * @return
    */
   public SeasonWPDetails updateWPSpringDetails(SeasonWPDetails seasonWPDetails);

   /**
    * @param seasonId
    * @return
    */
   public WPBasicDetail getWPSpringBaseDetails(String seasonId);

   /**
    * @param wpBasicDetail
    * @return
    */
   public WPBasicDetail updateWPSpringBaseDetails(WPBasicDetail wpBasicDetail);

   /**
    * @param seasonId
    * @return
    */
   public WPSectionOne getWPSpringSectionOneDetails(String seasonId);

   /**
    * @param wpSectionOne
    * @return
    */
   public WPSectionOne updateWPSpringSectionOneDetails(WPSectionOne wpSectionOne);

   /**
    * @param seasonId
    * @return
    */
   public WPSectionOne getWPSpringAllocationDetails(String seasonId);

   /**
    * @param wpSectionOne
    * @return
    */
   public WPSectionOne updateWPSpringAllocationDetails(WPSectionOne wpSectionOne);

   /**
    * @param seasonId
    * @return
    */
   public SeasonWPDetails getWPWinterDetails(String seasonId);

   /**
    * @param seasonId
    * @return
    */
   public SeasonWPDetails editWPWinterDetails(String seasonId);

   /**
    * @param seasonWPDetails
    * @return
    */
   public SeasonWPDetails updateWPWinterDetails(SeasonWPDetails seasonWPDetails);

   /**
    * @param seasonId
    * @return
    */
   public WPBasicDetail getWPWinterBaseDetails(String seasonId);

   /**
    * @param seasonId
    * @return
    */
   public WPBasicDetail editWPWinterBaseDetails(String seasonId);

   /**
    * @param wpBasicDetail
    * @return
    */
   public WPBasicDetail updateWPWinterBaseDetails(WPBasicDetail wpBasicDetail);

   /**
    * @param seasonId
    * @return
    */
   public WPSectionOne getWPWinterSectionOneDetails(String seasonId);

   /**
    * @param seasonId
    * @return
    */
   public WPSectionOne editWPWinterSectionOneDetails(String seasonId);

   /**
    * @param wpSectionOne
    * @return
    */
   public WPSectionOne updateWPWinterSectionOneDetails(WPSectionOne wpSectionOne);

   /**
    * @param seasonId
    * @return
    */
   public WPSectionOne getWPWinterAllocationDetails(String seasonId);

   /**
    * @param seasonId
    * @return
    */
   public WPSectionOne editWPWinterAllocationDetails(String seasonId);

   /**
    * @param wpSectionOne
    * @return
    */
   public WPSectionOne updateWPWinterAllocationDetails(WPSectionOne wpSectionOne);

   /**
    * @param cloneSeason
    * @return
    */
   public CloneSeason cloneSeason(CloneSeason cloneSeason);

}
