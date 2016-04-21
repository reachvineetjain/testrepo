package com.ccighgo.service.components.season;

import org.springframework.stereotype.Service;

import com.ccighgo.db.entities.Season;
import com.ccighgo.service.transport.season.beans.cloneseason.CloneSeason;
import com.ccighgo.service.transport.season.beans.seasondepartdoc.SeasonDepartmentDocument;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection1Base;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection2Dates;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.SeasonGHTDetails;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.IHPDates;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.IHPNameAndStatus;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.IHPProgramConfiguration;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.SeasonHspStpIhpDetails;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSAugStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSFieldSettings;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSJanStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSProgramAllocations;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.SeasonHspJ1HSDetails;
import com.ccighgo.service.transport.season.beans.seasonprgdoc.SeasonProgramDocument;
import com.ccighgo.service.transport.season.beans.seasonprgnote.SeasonProgramNote;
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
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.SeasonWPCAPDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPBasicDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPInternshipDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPProgramAllocations;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPTraineeDetails;
import com.ccighgo.service.transport.utility.beans.documenttype.DocumentTypes;

@Service
public interface SeasonServiceInterface {

   /**
    * Service to return list of seasons in the system.
    * 
    * @return
    */
   public SeasonsList getAllSeasons();

   /**
    * Creates a new season and season programs for the season.
    * 
    * @param seasonBean
    * @return
    */
   public SeasonBean createSeason(SeasonBean seasonBean);

   /**
    * Edits already existing season.
    * 
    * @param id
    * @return
    */
   public SeasonBean editSeason(String id);

   /**
    * View details on an existing season.
    * 
    * @param id
    * @return
    */
   public SeasonBean viewSeason(String id);

   /**
    * Updates an existing season with new details.
    * 
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
    * @param seasonProgramId
    * @return SeasonHspJ1HSDetails
    */
   public SeasonHspJ1HSDetails getHSPJ1HSSeasonDetails(String seasonProgramId);

   /**
    * Gets the details of first section in J1HS for name and status
    * 
    * @param seasonProgramId
    * @return J1HSBasicDetail
    */
   public J1HSBasicDetail getHSPJ1HSSeasonNameAndStatus(String seasonProgramId);

   /**
    * Gets the details of January semester start
    * 
    * @param seasonProgramId
    * @return J1HSJanStart
    */
   public J1HSJanStart getHSPJ1HSSeasonJanStartDetails(String seasonProgramId);

   /**
    * gets details of August semester start
    * 
    * @param seasonProgramId
    * @return J1HSAugStart
    */
   public J1HSAugStart getHSPJ1HSSeasonAugStartDetails(String seasonProgramId);

   /**
    * Gets field setting details of J1HS season
    * 
    * @param seasonProgramId
    * @return J1HSFieldSettings
    */
   public J1HSFieldSettings getHSPJ1HSSeasonFieldSettings(String seasonProgramId);

   /**
    * Gets list of program allocation matrix for J1HS season
    * 
    * @param seasonProgramId
    * @return J1HSProgramAllocations
    */
   public J1HSProgramAllocations getHSPJ1HSSeasonProgramAllocation(String seasonProgramId);

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
    * @param seasonProgramId
    * @return
    */
   public SeasonHSPF1Details getSeasonHSPF1Details(String seasonProgramId);

   /**
    * @param seasonProgramId
    * @return
    */
   public HSPF1BasicDetails getHSPF1NameAndStatus(String seasonProgramId);

   /**
    * @param hspf1BasicDetails
    * @return
    */
   public HSPF1BasicDetails updateHSPF1NameAndStatus(HSPF1BasicDetails hspf1BasicDetails);

   /**
    * @param seasonProgramId
    * @return
    */
   public HSPF1Accounting getHSPF1Accounting(String seasonProgramId);

   /**
    * @param hspf1Accounting
    * @return
    */
   public HSPF1Accounting updateF1Accounting(HSPF1Accounting hspf1Accounting);

   /**
    * @param seasonProgramId
    * @return
    */
   public HSPF1JanuaryStart2NdSemesterDetails getHSPF1JanuaryStart2NdSemesterDetails(String seasonProgramId);

   /**
    * @param hspf1JanuaryStart2NdSemesterDetails
    * @return
    */
   public HSPF1JanuaryStart2NdSemesterDetails updateF1JanStart2NdSemesterDetails(HSPF1JanuaryStart2NdSemesterDetails hspf1JanuaryStart2NdSemesterDetails);

   /**
    * @param seasonProgramId
    * @return
    */
   public HSPF1JanuaryStartFullYearDetail getHSPF1JanuaryStartFullYearDetails(String seasonProgramId);

   /**
    * @param hspf1JanuaryStartFullYearDetail
    * @return
    */
   public HSPF1JanuaryStartFullYearDetail updateF1JanStartFullYearDetails(HSPF1JanuaryStartFullYearDetail hspf1JanuaryStartFullYearDetail);

   /**
    * @param seasonProgramId
    * @return
    */
   public HSPF1AugustStart1StSemesterDetails getHSPF1AugustStart1StSemesterDetails(String seasonProgramId);

   /**
    * @param hspf1AugustStart1StSemesterDetails
    * @return
    */
   public HSPF1AugustStart1StSemesterDetails updateF1AugStart1StSemesterDetails(HSPF1AugustStart1StSemesterDetails hspf1AugustStart1StSemesterDetails);

   /**
    * @param seasonProgramId
    * @return
    */
   public HSPF1AugustStartFullYearDetails getHSPF1AugustStartFullYearDetails(String seasonProgramId);

   /**
    * @param hspf1AugustStartFullYearDetails
    * @return
    */
   public HSPF1AugustStartFullYearDetails updateF1AugStartFullYearDetails(HSPF1AugustStartFullYearDetails hspf1AugustStartFullYearDetails);

   /**
    * @param seasonProgramId
    * @return
    */
   public HSPF1FieldSettings getHSPF1FieldSettings(String seasonProgramId);

   /**
    * @param hspf1FieldSettings
    * @return
    */
   public HSPF1FieldSettings updateF1FieldSettings(HSPF1FieldSettings hspf1FieldSettings);

   /**
    * @param seasonProgramId
    * @return
    */
   public HSPF1ProgramAllocations getHSPF1ProgramAllocations(String seasonProgramId);

   /**
    * @param hspf1ProgramAllocations
    * @return
    */
   public HSPF1ProgramAllocations updateF1ProgramAllocation(HSPF1ProgramAllocations hspf1ProgramAllocations);

   /**
    * @param seasonProgramId
    * @return
    */
   public SeasonGHTDetails getGHTVASeasonDetails(String seasonProgramId);

   /**
    * @param seasonProgramId
    * @return
    */
   public GHTSection1Base getGHTVASeasonNameAndStatus(String seasonProgramId);

   /**
    * @param seasonProgramId
    * @return
    */
   public GHTSection2Dates getGHTVASeasonDateDetails(String seasonProgramId);

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
    * @param seasonProgramId
    * @return
    */
   public SeasonGHTDetails getGHTWASeasonDetails(String seasonProgramId);

   /**
    * @param seasonProgramId
    * @return
    */
   public GHTSection1Base getGHTWASeasonNameAndStatus(String seasonProgramId);

   /**
    * @param seasonProgramId
    * @return
    */
   public GHTSection2Dates getGHTWASeasonDateDetails(String seasonProgramId);

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
    * @param seasonProgramId
    * @return
    */
   public SeasonGHTDetails getGHTHSAbroad(String seasonProgramId);

   /**
    * @param seasonGHTDetails
    * @return
    */
   public SeasonGHTDetails updateGHTHSAbroad(SeasonGHTDetails seasonGHTDetails);

   /**
    * @param seasonProgramId
    * @return
    */
   public GHTSection1Base getGHTHSSection1BaseAbroad(String seasonProgramId);

   /**
    * @param ghtSection1Base
    * @return
    */
   public GHTSection1Base updateGHTHSSection1BaseAbroad(GHTSection1Base ghtSection1Base);

   /**
    * @param seasonProgramId
    * @return
    */
   public GHTSection2Dates getGHTHSSection2DatesAbroad(String seasonProgramId);

   /**
    * @param ghtSection2Dates
    * @return
    */
   public GHTSection2Dates updateGHTHSSection2DatesAbroad(GHTSection2Dates ghtSection2Dates);

   /**
    * @param seasonProgramId
    * @return
    */
   public SeasonGHTDetails getGHTLanguageSchool(String seasonProgramId);

   /**
    * @param seasonGHTDetails
    * @return
    */
   public SeasonGHTDetails updateGHTLanguageSchool(SeasonGHTDetails seasonGHTDetails);

   /**
    * @param seasonProgramId
    * @return
    */
   public GHTSection1Base getGHTLanguageSchoolSection1(String seasonProgramId);

   /**
    * @param seasonGHTDetails
    * @return
    */
   public GHTSection1Base updateGHTLanguageSchoolSection1(GHTSection1Base seasonGHTDetails);

   /**
    * @param seasonProgramId
    * @return
    */
   public GHTSection2Dates getGHTLanguageSchoolSection2Dates(String seasonProgramId);

   /**
    * @param ghtSection2Dates
    * @return
    */
   public GHTSection2Dates updateGHTLanguageSchoolSection2Dates(GHTSection2Dates ghtSection2Dates);

   /**
    * @param seasonProgramId
    * @return
    */
   public SeasonGHTDetails getGHTTeachAbroad(String seasonProgramId);

   /**
    * @param seasonGHTDetails
    * @return
    */
   public SeasonGHTDetails updateGHTTeachAbroad(SeasonGHTDetails seasonGHTDetails);

   /**
    * @param seasonProgramId
    * @return
    */
   public GHTSection1Base getGHTTeachAbroadSection1(String seasonProgramId);

   /**
    * @param ghtSection1Base
    * @return
    */
   public GHTSection1Base updateGHTTeachAbroadSection1(GHTSection1Base ghtSection1Base);

   /**
    * @param seasonProgramId
    * @return
    */
   public GHTSection2Dates getGHTTeachAbroadSection2Dates(String seasonProgramId);

   /**
    * @param ghtSection2Dates
    * @return
    */
   public GHTSection2Dates updateGHTTeachAbroadSection2Dates(GHTSection2Dates ghtSection2Dates);

   /**
    * @param seasonProgramId
    * @return
    */
   public SeasonWPCAPDetails getWPCAPDetails(String seasonProgramId);

   /**
    * @param seasonWPCAPDetails
    * @return
    */
   public SeasonWPCAPDetails updateWPCAPDetails(SeasonWPCAPDetails seasonWPCAPDetails);

   /**
    * @param seasonProgramId
    * @return
    */
   public WPCAPBasicDetails getWPCAPBasicDetails(String seasonProgramId);

   /**
    * @param seasonWpcapBasicDetails
    * @return
    */
   public WPCAPBasicDetails updateWPCAPBasicDetails(WPCAPBasicDetails seasonWpcapBasicDetails);

   /**
    * @param seasonProgramId
    * @return
    */
   public WPCAPInternshipDetails getWPCAPInternshipDetails(String seasonProgramId);

   /**
    * @param wpcapInternshipDetails
    * @return
    */
   public WPCAPInternshipDetails updateWPCAPInternshipDetails(WPCAPInternshipDetails wpcapInternshipDetails);

   /**
    * @param seasonProgramId
    * @return
    */
   public WPCAPTraineeDetails getWPCAPTraineeDetails(String seasonProgramId);

   /**
    * @param wpcapTraineeDetails
    * @return
    */
   public WPCAPTraineeDetails updateWPCAPTraineeDetails(WPCAPTraineeDetails wpcapTraineeDetails);

   /**
    * @param seasonProgramId
    * @return
    */
   public SeasonWPDetails getWPSumDetails(String seasonProgramId);

   /**
    * @param seasonWPDetails
    * @return
    */
   public SeasonWPDetails updateWPSumDetails(SeasonWPDetails seasonWPDetails);

   /**
    * @param seasonProgramId
    * @return
    */
   public WPBasicDetail getWPSumBaseDetails(String seasonProgramId);

   /**
    * @param wpBasicDetail
    * @return
    */
   public WPBasicDetail updateWPSumBaseDetails(WPBasicDetail wpBasicDetail);

   /**
    * @param seasonProgramId
    * @return
    */
   public WPSectionOne getWPSumSectionOneDetails(String seasonProgramId);

   /**
    * @param wpSectionOne
    * @return
    */
   public WPSectionOne updateWPSumSectionOneDetails(WPSectionOne wpSectionOne);

   /**
    * @param seasonProgramId
    * @return
    */
   public WPProgramAllocations getWPSumAllocationDetails(String seasonProgramId);

   /**
    * @param wpSectionOne
    * @return
    */
   public WPProgramAllocations updateWPSumAllocationDetails(WPProgramAllocations wpProgramAllocations);

   /**
    * @param seasonProgramId
    * @return
    */
   public SeasonWPDetails getWPSpringDetails(String seasonProgramId);

   /**
    * @param seasonWPDetails
    * @return
    */
   public SeasonWPDetails updateWPSpringDetails(SeasonWPDetails seasonWPDetails);

   /**
    * @param seasonProgramId
    * @return
    */
   public WPBasicDetail getWPSpringBaseDetails(String seasonProgramId);

   /**
    * @param wpBasicDetail
    * @return
    */
   public WPBasicDetail updateWPSpringBaseDetails(WPBasicDetail wpBasicDetail);

   /**
    * @param seasonProgramId
    * @return
    */
   public WPSectionOne getWPSpringSectionOneDetails(String seasonProgramId);

   /**
    * @param wpSectionOne
    * @return
    */
   public WPSectionOne updateWPSpringSectionOneDetails(WPSectionOne wpSectionOne);

   /**
    * @param seasonProgramId
    * @return
    */
   public WPProgramAllocations getWPSpringAllocationDetails(String seasonProgramId);

   /**
    * @param wpSectionOne
    * @return
    */
   public WPProgramAllocations updateWPSpringAllocationDetails(WPProgramAllocations wpProgramAllocations);

   /**
    * @param seasonProgramId
    * @return
    */
   public SeasonWPDetails getWPWinterDetails(String seasonProgramId);

   /**
    * @param seasonWPDetails
    * @return
    */
   public SeasonWPDetails updateWPWinterDetails(SeasonWPDetails seasonWPDetails);

   /**
    * @param seasonProgramId
    * @return
    */
   public WPBasicDetail getWPWinterBaseDetails(String seasonProgramId);

   /**
    * @param wpBasicDetail
    * @return
    */
   public WPBasicDetail updateWPWinterBaseDetails(WPBasicDetail wpBasicDetail);

   /**
    * @param seasonProgramId
    * @return
    */
   public WPSectionOne getWPWinterSectionOneDetails(String seasonProgramId);

   /**
    * @param wpSectionOne
    * @return
    */
   public WPSectionOne updateWPWinterSectionOneDetails(WPSectionOne wpSectionOne);

   /**
    * @param seasonProgramId
    * @return
    */
   public WPProgramAllocations getWPWinterAllocationDetails(String seasonProgramId);

   /**
    * @param wpSectionOne
    * @return
    */
   public WPProgramAllocations updateWPWinterAllocationDetails(WPProgramAllocations wpProgramAllocations);

   /**
    * @param cloneSeason
    * @return
    */
   public CloneSeason cloneSeason(CloneSeason cloneSeason);

   /**
    * Method adds notes on high level season
    * 
    * @param seasonDepartmentNotes
    * @return
    */
   public SeasonDepartmentNotes addSeasonDepartmentNote(SeasonDepartmentNotes seasonDepartmentNotes);

   /**
    * Method adds document to a high level season
    * 
    * @param seasonDepartmentDocument
    * @return
    */
   public SeasonDepartmentDocument addSeasonDepartmentDoc(SeasonDepartmentDocument seasonDepartmentDocument);

   /**
    * Method adds note to a season program based on season id and program id
    * 
    * @param seasonProgramNote
    * @return
    */
   public SeasonProgramNote addSeasonProgramNote(SeasonProgramNote seasonProgramNote);

   /**
    * Method adds document to a season program based on season id and program id
    * 
    * @param seasonProgramDocument
    * @return
    */
   public SeasonProgramDocument addSeasonProgramDoc(SeasonProgramDocument seasonProgramDocument);

   /**
    * <<<<<<< HEAD Method to fetch program allocations for WP CAP
    * 
    * @param seasonProgramId
    * @return
    */
   public WPCAPProgramAllocations getWPCAPAllocationDetails(String seasonProgramId);

   /**
    * Method update Program Allocations for WP CAP
    * 
    * @param wpcapProgramAllocations
    * @return
    */
   public WPCAPProgramAllocations updateWPCAPAllocationDetails(WPCAPProgramAllocations wpcapProgramAllocations);

   /**
    * Util service to get list of document types
    */
   public DocumentTypes getDocumentTypes();

   /**
    * @param seasonProgramId
    * @return
    */
   public SeasonHspStpIhpDetails getIHPDetails(String seasonProgramId);

   /**
    * @param seasonProgramId
    * @return
    */
   public IHPNameAndStatus getIHPNameAndStatus(String seasonProgramId);

   /**
    * @param seasonProgramId
    * @return
    */
   public IHPDates getIHPDates(String seasonProgramId);

   /**
    * @param seasonProgramId
    * @return
    */
   public IHPProgramConfiguration getIHPProgramConfigurationDetails(String seasonProgramId);

   /**
    * @param seasonHspStpIhpDetails
    * @return
    */
   public SeasonHspStpIhpDetails updateIHPDetails(SeasonHspStpIhpDetails seasonHspStpIhpDetails);

   /**
    * @param ihpNameAndStatus
    * @return
    */
   public IHPNameAndStatus updateIHPNameAndStatus(IHPNameAndStatus ihpNameAndStatus);

   /**
    * @param ihpDates
    * @return
    */
   public IHPDates updateIHPDates(IHPDates ihpDates);

   /**
    * @param ihpProgramConfiguration
    * @return
    */
   public IHPProgramConfiguration updateIHPProgramConfigurationDetails(IHPProgramConfiguration ihpProgramConfiguration);

   /**
    * 
    * @param season
    */
   public void createJ1ProgramAllocation(Season season, int loginId);

   /**
    * 
    * @param season
    */
   public void createF1ProgramAllocation(Season season, int loginId);

   /**
    * 
    * @param season
    */
   public void createWPSumProgramAllocation(Season season, int loginId);

   /**
    * 
    * @param season
    */
   public void createWPSpringProgramAllocation(Season season, int loginId);
}
