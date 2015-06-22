package com.ccighgo.service.components.season;

import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSAugStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSFieldSettings;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSJanStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSProgramAllocations;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.SeasonHspJ1HSDetails;
import com.ccighgo.service.transport.season.beans.seasonprogram.SeasonProgram;
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
   public SeasonProgram getSeasonPrograms(String seasonId);
   
   /**     
    * Method fetches list of available season status
    *  
    * @return SeasonStatuses
    */
   public SeasonStatuses getSeasonStatus();
public String cloneSeason(String seasonId, String newSeasonName);
 
   //HSP J1HS Season methods
   
   /**
    * Method returns complete details for HSP J1HS season program
    * 
    * @param seasonId
    * @return SeasonHspJ1HSDetails
    */
   public SeasonHspJ1HSDetails getHSPJ1HSSeasonDetails(String seasonId);


   /**
    * @param seasonId
    * @return
    */
   public J1HSBasicDetail getHSPJ1HSSeasonNameAndStatus(String seasonId); 


   /**
    * @param seasonId
    * @return
    */
   public J1HSJanStart getHSPJ1HSSeasonJanStartDetails(String seasonId);


   /**
    * @param seasonId
    * @return
    */
   public J1HSAugStart getHSPJ1HSSeasonAugStartDetails(String seasonId);


   /**
    * @param seasonId
    * @return
    */
   public J1HSFieldSettings getHSPJ1HSSeasonFieldSettings(String seasonId);


   /**
    * @param seasonId
    * @return
    */
   public J1HSProgramAllocations getHSPJ1HSSeasonProgramAllocation(String seasonId);
}
