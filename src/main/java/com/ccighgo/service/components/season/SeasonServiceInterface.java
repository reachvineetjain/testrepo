package com.ccighgo.service.components.season;

import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSAugStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSFieldSettings;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSJanStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSProgramAllocations;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.SeasonHspJ1HSDetails;
import com.ccighgo.service.transport.season.beans.seasonprogram.SeasonPrograms;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;

public interface SeasonServiceInterface {

   public SeasonsList getAllSeasons();

   public SeasonBean createSeason(SeasonBean seasonBean);

   public String deleteSeason(String id);

   public SeasonBean editSeason(String id);

   public SeasonBean viewSeason(String id);

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

   public String cloneSeason(String seasonId, String newSeasonName);

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

}
